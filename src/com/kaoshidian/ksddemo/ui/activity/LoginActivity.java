package com.kaoshidian.ksddemo.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.kaoshidian.ksddemo.R;
import com.kaoshidian.ksddemo.support.BaseActivity;
import com.kaoshidian.ksddemo.ui.task.LoginTask;

public class LoginActivity extends BaseActivity{
    private EditText username;
    private EditText password;
    private Button submit;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pDialog = new ProgressDialog(LoginActivity.this);
                pDialog.setTitle("登录中……");
                pDialog.show();
                /*new LoginThread().start();*/
                String un = username.getText().toString();
                String ps = password.getText().toString();
                LoginTask task = new LoginTask(LoginActivity.this,pDialog,un,ps);
                task.execute();
            }
        });

    }

}
