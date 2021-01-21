package com.xl.opengles.views;

import android.content.Context;
import android.opengl.GLSurfaceView;

/* 一般绘制步骤：
        1.设置视图展示窗口(viewport) :在onSurfaceChanged中调用GLES20.glViewport(0, 0, width, height);
        2.创建图形类，确定好顶点位置和图形颜色，将顶点和颜色数据转换为OpenGl使用的数据格式
        3.加载顶点找色器和片段着色器用来修改图形的颜色，纹理，坐标等属性
        4.创建投影和相机视图来显示视图的显示状态，并将投影和相机视图的转换传递给着色器。
        5.创建项目(Program),连接顶点着色器片段着色器。
        6.将坐标数据传入到OpenGl ES程序中：*/

public class MyGLSurfaceView extends GLSurfaceView {
    private MyGLRenderer mGLRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);

        mGLRenderer=new MyGLRenderer();

        setRenderer(mGLRenderer);
    }
}
