package com.kaoshidian.ksddemo.support;

import android.app.Application;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class BaseActivity extends SherlockFragmentActivity implements IBaseActivity{
	
	public String getSession() {
		Application app = getApplication();
		GlobalApp ga = (GlobalApp) app;

		String sessionId = ga.getSessionId();
		if (sessionId == null || (sessionId != null && sessionId.length() == 0)) {
			sessionId = new GetSessionTask().getSession();
		}
		ga.setSessionId(sessionId);
		return sessionId;
	}
	
	public GlobalApp getGlobalApp()
	{
		Application app = getApplication();
		GlobalApp ga = (GlobalApp) app;
		return ga;
	}
}
