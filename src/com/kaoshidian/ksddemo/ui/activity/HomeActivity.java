package com.kaoshidian.ksddemo.ui.activity;

import android.os.Bundle;
import android.widget.TabHost;

import com.kaoshidian.ksddemo.R;
import com.kaoshidian.ksddemo.support.BaseActivity;

public class HomeActivity extends BaseActivity {
    //声明一个tabhost的引用
    private TabHost tabHost;
//  private TabWidget tabWidget;
    //定义一个用于fragment的当前layout下标，默认为0
    private static int currentlayout = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题模式
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home);
        //该方法用于设置各个fragment布局与TabWidget之间的关联
        setFragment();
        //该方法用于响应用户的点击事件
        changeLayout();
    }

    private void changeLayout() {
        //根据用户的点击位置的下标显示相应的fragment
        tabHost.setCurrentTab(currentlayout);
    }

    private void setFragment() {
        //通过组件的id初始化tabHost的实例
        tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup();
        //tabWidget = tabHost.getTabWidget();
        //往tabHost分别添加fragment
        tabHost.addTab(tabHost.newTabSpec("f1").setIndicator("今日推荐", this.getResources().getDrawable(R.drawable.p1)).setContent(R.id.fragment_1));
        tabHost.addTab(tabHost.newTabSpec("f2").setIndicator("全部课程", this.getResources().getDrawable(R.drawable.p2)).setContent(R.id.fragment_2));
        tabHost.addTab(tabHost.newTabSpec("f3").setIndicator("我的课程", this.getResources().getDrawable(R.drawable.p3)).setContent(R.id.fragment_3));
        //tabHost.addTab(tabHost.newTabSpec("f4").setIndicator("个人设置", this.getResources().getDrawable(R.drawable.p4)).setContent(R.id.fragment_4));        
        //设置默认显示布局
        //tabHost.setCurrentTab(0);
    }
    
/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	menu.add("设置");
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	Toast.makeText(this, "设置", 3).show();
    	return super.onOptionsItemSelected(item);
    }*/
}

