package com.kaoshidian.ksddemo.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class HttpUtils {

	//private final static String URL_PATH = "http://192.168.0.102:8080/myhttp/koala.jpg";// 访问网路图片的路径

	public HttpUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 从网络中获取图片信息，以流的形式返回
	 * 
	 * @return
	 */
	public static InputStream getImageViewInputStream(String imgUrl) throws IOException {
		InputStream inputStream = null;

		URL url = new URL(imgUrl);
		if (url != null) {
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setConnectTimeout(3000);// 设置连接超时的时间
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setDoInput(true);
			int response_code = httpURLConnection.getResponseCode();
			if (response_code == 200) {
				inputStream = httpURLConnection.getInputStream();
			}
		}
		return inputStream;
	}

	/**
	 * 从网络中获取图片信息，以字节数组的形式返回
	 * 
	 * @return
	 */
	public static byte[] getImageViewArray(String imgUrl) {
		byte[] data = null;
		InputStream inputStream = null;
		// 不需要关闭的输出流，直接写入到内存中
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			URL url = new URL(imgUrl);
			if (url != null) {
				HttpURLConnection httpURLConnection = (HttpURLConnection) url
						.openConnection();
				httpURLConnection.setConnectTimeout(3000);// 设置连接超时的时间
				httpURLConnection.setRequestMethod("GET");// 请求方法
				httpURLConnection.setDoInput(true);// 打开输入流
				int response_code = httpURLConnection.getResponseCode();
				int len = 0;
				byte[] b_data = new byte[1024];

				if (response_code == 200) {
					inputStream = httpURLConnection.getInputStream();
					while ((len = inputStream.read(b_data)) != -1) {
						outputStream.write(b_data, 0, len);
					}
					data = outputStream.toByteArray();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	public static Bitmap getBitmap(String url)
	{
        byte[] data = getImageViewArray(url);
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
                data.length);
        return bitmap;
	}
}
