
package com.kaoshidian.ksddemo.support;


import android.os.AsyncTask;

public class GetSessionTask  {

    
    public String getSession()
    {
        String url = StaticVariable.WWW_ROOT+"/getsession";
        String sessionId = "";
        RequestHandler handler = new RequestHandler();
        try {
            sessionId = handler.httpGet(url);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return sessionId;
    }
/*    @Overrid
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub

        String url = "http://192.168.1.195/getsession";
        String sessionId = "";
        RequestHandler handler = new RequestHandler();
        try {
            sessionId = handler.httpGet(url);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return sessionId;
    }*/

}
