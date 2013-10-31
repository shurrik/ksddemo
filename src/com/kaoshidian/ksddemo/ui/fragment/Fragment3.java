package com.kaoshidian.ksddemo.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.kaoshidian.ksddemo.R;
import com.kaoshidian.ksddemo.support.BaseActivity;
import com.kaoshidian.ksddemo.support.RequestHandler;
import com.kaoshidian.ksddemo.support.StaticVariable;
import com.kaoshidian.ksddemo.ui.activity.MediaPlayerDemo_Video;
import com.kaoshidian.ksddemo.ui.adapter.MyCourseAdapter;

public class Fragment3 extends SherlockFragment{
	
	private MyCourseAdapter myCourseAdapter;
    private List<Map> data = new ArrayList();
    private ListView myCourses;
    private ImageView f3_loading;
    private boolean ok;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    	MyCourseTask task = new MyCourseTask((BaseActivity)getSherlockActivity());
    	task.execute();
    	
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.fragment_3, container, false);
        myCourses = (ListView) view.findViewById(R.id.mycourses);
        //myCourses.setAdapter(myCourseAdapter);
        f3_loading = (ImageView) view.findViewById(R.id.f3_loading);
        if(ok)
        {
        	f3_loading.setVisibility(View.GONE);
        	myCourses.setAdapter(myCourseAdapter);
        }
        else
        {
        	myCourses.setVisibility(View.GONE);
        }        
        final Activity activity = getSherlockActivity();
        myCourses.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity,MediaPlayerDemo_Video.class);
				activity.startActivity(intent);
				
			}
        	
		});
        return view;
    }
    
    public class MyCourseTask extends AsyncTask<Object, Void, Void> {
        
        private BaseActivity activity;
        public MyCourseTask(BaseActivity activity) {
            super();
            this.activity = activity;
        }

        
        @Override
        protected Void doInBackground(Object... arg0) {
/*            users = new stringGetJson().getJson();
            return null;*/
            String url = StaticVariable.WWW_ROOT+"/android/mycourse";
            String res = "";
            RequestHandler handler = new RequestHandler(activity);
            try {
                res = handler.httpGet(url);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            try {
                JSONArray ja = new JSONArray(res);
                for(int i=0;i<ja.length();i++)
                {
                    JSONObject obj = ja.getJSONObject(i);
                    Map<String,String> item = new HashMap(); 
                    item.put("courseCover", obj.getString("imgUrl"));
                    item.put("courseNo", obj.getString("courseNo"));
                    item.put("courseName", obj.getString("courseName"));
                    data.add(item);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            myCourseAdapter = new MyCourseAdapter(activity, data);
            ListView lv = (ListView) activity.findViewById(R.id.mycourses);
        	ImageView iv = (ImageView) activity.findViewById(R.id.f3_loading);
        	iv.setVisibility(View.GONE);
        	lv.setVisibility(View.VISIBLE);
        	lv.setAdapter(myCourseAdapter);
        	ok=true;
        }
    }    
}
