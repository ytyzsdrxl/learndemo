package com.xl.opengles.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;

import com.xl.opengles.R;
import com.xl.opengles.views.MyGLRenderer;
import com.xl.opengles.views.MyGLSurfaceView;

public class GLLessionOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyGLSurfaceView myGLSurfaceView=new MyGLSurfaceView(this);
        //检测系统是否支持OpenGL ES 2.0
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        if (supportsEs2) {
            // 请求一个OpenGL ES 2.0兼容的上下文
//            myGLSurfaceView.setEGLContextClientVersion(2);
            // 设置我们的Demo渲染器，定义在后面讲
//            myGLSurfaceView.setRenderer(new MyGLRenderer());
        } else {
            // 如果您想同时支持ES 1.0和2.0的话，这里您可以创建兼容OpenGL ES 1.0的渲染器
            return;
        }
        setContentView(myGLSurfaceView);

    }
}