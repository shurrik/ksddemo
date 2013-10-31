package com.kaoshidian.ksddemo.ui.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.kaoshidian.ksddemo.support.BaseActivity;
import com.kaoshidian.ksddemo.support.GlobalApp;
import com.kaoshidian.ksddemo.support.RequestHandler;
import com.kaoshidian.ksddemo.support.StaticVariable;
import com.kaoshidian.ksddemo.ui.activity.FragmentTabsPager;
import com.kaoshidian.ksddemo.ui.activity.HomeActivity;
import com.kaoshidian.ksddemo.ui.activity.WelcomeActivity;

public class LoginTask extends AsyncTask<Object, Void, String>{
    private String username;
    private String password;
    private BaseActivity activity;
    private ProgressDialog pDialog;
    private List<Map> data = new ArrayList();
    public LoginTask(BaseActivity activity,ProgressDialog pDialog,String username,String password) {
        super();
        this.activity = activity;
        this.pDialog = pDialog;
        this.username = username;
        this.password = password;
    }

    
    @Override
    protected String doInBackground(Object... arg0) {

    	try {
			return login();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }

    @Override
    protected void onPostExecute(String result) {
    	pDialog.dismiss();
    	GlobalApp ga = activity.getGlobalApp();
        if(result.equals("success"))
        {
            
            ga.setIsLogin(true);
            /*Intent intent = new Intent(activity,HomeActivity.class);*/
            Intent intent = new Intent(activity,FragmentTabsPager.class);
            activity.startActivity(intent);
            activity.finish();
        }
        else
        {
            Toast.makeText(ga, "账号密码错误", 3).show(); 
        }
    }
    
    private String login() throws Exception {

        List<HashMap<String, Object>> data = new ArrayList();
    
        RequestHandler rh = new RequestHandler(activity);
        String casUrl = StaticVariable.CAS_ROOT+"/testlogin?username="+username+"&password="+password+"&service="+StaticVariable.WWW_ROOT+"/sso";
        rh.httpGet(casUrl);
        
        String url = StaticVariable.WWW_ROOT+"/test";
        String res = "";
        try {
            res = rh.httpGet(url);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        //mHandler.sendMessage(msg);
        return res;
    }
}
