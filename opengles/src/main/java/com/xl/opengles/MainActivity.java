package com.xl.opengles;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

       /* 一般绘制步骤：
        1.设置视图展示窗口(viewport) :在onSurfaceChanged中调用GLES20.glViewport(0, 0, width, height);
        2.创建图形类，确定好顶点位置和图形颜色，将顶点和颜色数据转换为OpenGl使用的数据格式
        3.加载顶点找色器和片段着色器用来修改图形的颜色，纹理，坐标等属性
        4.创建投影和相机视图来显示视图的显示状态，并将投影和相机视图的转换传递给着色器。
        5.创建项目(Program),连接顶点着色器片段着色器。
        6.将坐标数据传入到OpenGl ES程序中：*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GlSurfaceView从名字就可以看出，它是一个SurfaceView，看源码可知，GlSurfaceView继承自SurfaceView。
//        并增加了Renderer.它的作用就是专门为OpenGl显示渲染使用的
        GLSurfaceView glSurfaceView=new GLSurfaceView(this);

//        GLSurfaceView.Renderer   该接口定义了用于绘制在图形所需的方法GLSurfaceView。你必须提供这个接口作为一个单独的类的实现，
//        并将其连接到您的GLSurfaceView使用实例 GLSurfaceView.setRenderer()。如上面的代码所示。作用就是提供各种渲染方法，OpenGl的渲染操作均在此接口中实习
        glSurfaceView.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//                系统调用这个方法一次创建时GLSurfaceView。使用此方法来执行只需要发生一次的操作，比如设置OpenGL的环境参数或初始化的OpenGL图形对象

            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
//              系统调用此方法时的GLSurfaceView几何形状的变化，包括尺寸变化GLSurfaceView或设备屏幕的取向。
//              例如，当设备从纵向变为横向的系统调用这个方法。使用此方法可以在变化做出反应GLSurfaceView容器。

            }

            @Override
            public void onDrawFrame(GL10 gl) {
//              系统调用上的每个重绘此方法GLSurfaceView。使用此方法作为主要执行点用于绘制（和重新绘制）的图形对象。
            }
        });
        setContentView(R.layout.activity_main);
    }
}
