package com.example.administrator.aop_aspectj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.aop_aspectj.annotation.LoginTrace;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //如果你想区分不同方法可以使用标记区分--》@LoginTrace("send")
    @LoginTrace
    public void send(View view) {
        Log.e("zdh", "------------测试");
        //注意这里需要做是否登录判断，要不没有登录，也不跳转到TextActivity
        //切面主要处理没有登录的逻辑，是否登录的逻辑还是需要自己处理
        if (MyApp.getInstance().isLogin()) {
            startActivity(new Intent(this, TextActivity.class));
        }
    }

}
