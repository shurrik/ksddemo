package com.kaoshidian.ksddemo.support;

import android.app.Application;

public class GlobalApp extends Application {
	private boolean isLogin = false;
	private String sessionId;

	public boolean isLogin(){
	    return isLogin;
	  }

	public void setIsLogin(boolean b) {
		isLogin = b;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
