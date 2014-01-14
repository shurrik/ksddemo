package com.kaoshidian.ksddemo.support;
 
import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
 
/***
 * 异步加载图片 缓存的实现
 * 
 */ 
public class AsyncImageLoader { 
    // 软引用 
    private HashMap<String, SoftReference<Drawable>> imageCache; 
 
    public AsyncImageLoader() { 
        imageCache = new HashMap<String, SoftReference<Drawable>>(); 
    } 
 
    /***
     * 下载图片
     * 
     * @param imageUrl
     *            图片地址
     * @param imageCallback
     *            回调接口
     * @return
     */ 
    public Drawable loadDrawable(final String imageUrl, 
            final ImageCallback imageCallback) { 
        if (imageCache.containsKey(imageUrl)) { 
            SoftReference<Drawable> softReference = imageCache.get(imageUrl); 
            Drawable drawable = softReference.get(); 
            if (drawable != null) { 
                return drawable; 
            } 
        } 
        final Handler handler = new Handler() { 
            public void handleMessage(Message message) { 
                imageCallback.imageLoaded((Drawable) message.obj, imageUrl); 
            } 
        }; 
        // 开启线程下载图片 
        new Thread() { 
            @Override 
            public void run() { 
                Drawable drawable = loadImageFromUrl(imageUrl); 
                // 将下载的图片保存至缓存中 
                imageCache.put(imageUrl, new SoftReference<Drawable>(drawable)); 
                Message message = handler.obtainMessage(0, drawable); 
                handler.sendMessage(message); 
            } 
        }.start(); 
        return null; 
    } 
 
    /***
     * 根据URL下载图片（这里要进行判断，先去本地sd中查找，没有则根据URL下载，有则返回该drawable）
     * 
     * @param url
     * @return
     */ 
    public static Drawable loadImageFromUrl(String imageURL) { 
 
        //Bitmap bitmap = MyUtil.GetBitmap(imageURL, 100); 
        Bitmap bitmap = HttpUtils.getBitmap(imageURL);
        Drawable drawable = new BitmapDrawable(bitmap); 
        return drawable; 
 
    } 
    
    public Drawable loadDrawable(final String imageUrl,final ImageView imageView) { 
        if (imageCache.containsKey(imageUrl)) { 
            SoftReference<Drawable> softReference = imageCache.get(imageUrl); 
            Drawable drawable = softReference.get(); 
            if (drawable != null) { 
                imageView.setImageDrawable(drawable);
                return drawable; 
            } 
        } 
        final Handler handler = new Handler() { 
            public void handleMessage(Message message) { 
                //imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
                imageView.setImageDrawable((Drawable) message.obj);
            } 
        }; 
        // 开启线程下载图片 
        new Thread() { 
            @Override 
            public void run() { 
                Drawable drawable = loadImageFromUrl(imageUrl); 
                // 将下载的图片保存至缓存中 
                imageCache.put(imageUrl, new SoftReference<Drawable>(drawable)); 
                Message message = handler.obtainMessage(0, drawable); 
                handler.sendMessage(message); 
            } 
        }.start(); 
        return null; 
    } 
 
    // 回调接口 
    public interface ImageCallback { 
        public void imageLoaded(Drawable imageDrawable, String imageUrl); 
    } 
 
} 