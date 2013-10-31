package com.kaoshidian.ksddemo.support;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class RequestHandler {

    private String session;
    
    public RequestHandler()
    {
        
    }
    
    public RequestHandler(IBaseActivity activity) {
        // TODO Auto-generated constructor stub
        session = activity.getSession();
    }
	
	public String httpGet(String url) throws Exception
	{
		return httpGet(url,null);
	}
	
	public String httpGet(String url, String params) throws Exception {
		String response = null;
		if(null != params && !params.equals("")) {
			url += "?" + params;
		}
		
		int timeOutConnection = 3000;
		int timeOutSocket = 5000; 
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, timeOutConnection);
		HttpConnectionParams.setSoTimeout(httpParams, timeOutSocket);
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		//session = "125so29w513rvuwp2p97d9ky5";
		httpGet.setHeader("Cookie", "JSESSIONID="+session);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode == HttpStatus.SC_OK) {
				response = EntityUtils.toString(httpResponse.getEntity());
			}
			else{
				response = "错误代码:" + statusCode;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		}
		return response;
	}
	
    public String httpPost(String url, List<NameValuePair> nvps) throws Exception {
        String response = null;
        
        int timeOutConnection = 3000;
        int timeOutSocket = 5000; 
        //HttpConnectionParams.setConnectionTimeout(httpParams, timeOutConnection);
        //HttpConnectionParams.setSoTimeout(httpParams, timeOutSocket);
        
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        httpPost.setHeader("Cookie", "JSESSIONID="+session);
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK) {
                response = EntityUtils.toString(httpResponse.getEntity());
            }
            else{
                response = "错误代码:" + statusCode;
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e);
        }
        return response;
    }	
}
