package com.kaoshidian.ksddemo.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;
import com.kaoshidian.ksddemo.R;
import com.kaoshidian.ksddemo.support.BaseActivity;
import com.kaoshidian.ksddemo.support.RequestHandler;
import com.kaoshidian.ksddemo.support.StaticVariable;
import com.kaoshidian.ksddemo.ui.adapter.TodayRecommendAdapter;

public class Fragment1 extends SherlockFragment{
	
	
	private GridView todayrecommends;
    private List<Map> data = new ArrayList();
    private TodayRecommendAdapter todayRecommendAdapter;
    private ProgressDialog pDialog;
    private ImageView f1_loading;
    private boolean ok;
    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    	TodayRecommendTask task = new TodayRecommendTask((BaseActivity)getSherlockActivity());
    	task.execute();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View view = inflater.inflate(R.layout.fragment_1, container, false);
        todayrecommends = (GridView) view.findViewById(R.id.todayRecommends);
        f1_loading = (ImageView) view.findViewById(R.id.f1_loading);
        if(ok)
        {
        	f1_loading.setVisibility(View.GONE);
        	todayrecommends.setAdapter(todayRecommendAdapter);
        }
        else
        {
        	todayrecommends.setVisibility(View.GONE);
        }
    	
        return view;
    }
    
    public class TodayRecommendTask extends AsyncTask<Object, Void, Void> {
        
        private BaseActivity activity;
        public TodayRecommendTask(BaseActivity activity) {
            super();
            this.activity = activity;
        }

        
        @Override
        protected void onPreExecute() {
        	// TODO Auto-generated method stub
        	super.onPreExecute();
        }
        
        @Override
        protected Void doInBackground(Object... arg0) {
            String url = StaticVariable.WWW_ROOT+"/android/todayrecommend";
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
                    item.put("courseGrade", obj.getString("courseGrade"));
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
        	todayRecommendAdapter = new TodayRecommendAdapter(activity, data);
        	GridView gv = (GridView) activity.findViewById(R.id.todayRecommends);
        	ImageView iv = (ImageView) activity.findViewById(R.id.f1_loading);
        	iv.setVisibility(View.GONE);
        	gv.setVisibility(View.VISIBLE);
        	gv.setAdapter(todayRecommendAdapter);
        	ok=true;
        }

    }    
}
