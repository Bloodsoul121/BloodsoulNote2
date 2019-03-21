package com.example.cgz.bloodsoulnote2.media.opengl.draw;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {

    private static final String TAG = "OpenGL";

    protected final String vertexShaderCode =
            "attribute vec4 vPosition;" +
            "void main() {" +
            "  gl_Position = vPosition;" +
            "}";

    protected final String fragmentShaderCode =
            "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";

    // 数组中每个顶点的坐标数
    protected static final int COORDS_PER_VERTEX = 3;

    protected static final int VERTEX_STRIDE = COORDS_PER_VERTEX * 4; // 每个顶点四个字节

    // 按逆时针方向顺序
    protected static final float[] TRIANGLE_COORDS = {
            0.0f, 0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    };

    // 设置颜色
    private float[] color = {1.0f, 1.0f, 1.0f, 1.0f};

    // 顶点个数
    protected static final int VERTEX_COUNT = TRIANGLE_COORDS.length / COORDS_PER_VERTEX;

    protected int mProgram;

    protected FloatBuffer mVertexBuffer;

    public Triangle() {
        // 申请底色空间
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

        // (坐标数 * 4) float占四字节
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(TRIANGLE_COORDS.length * 4);
        // 使用设备的本点字节序
        byteBuffer.order(ByteOrder.nativeOrder());

        // 从ByteBuffer创建一个浮点缓冲
        mVertexBuffer = byteBuffer.asFloatBuffer();
        //把坐标们加入FloatBuffer中
        mVertexBuffer.put(TRIANGLE_COORDS);
        //设置buffer，从第一个坐标开始读
        mVertexBuffer.position(0);

        mProgram = createOpenGLProgram(vertexShaderCode, fragmentShaderCode);
    }

    public void draw() {
        //将程序加入到OpenGLES2.0环境
        GLES20.glUseProgram(mProgram);
        //获取顶点着色器的vPosition成员句柄
        int mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角形的坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, VERTEX_STRIDE, mVertexBuffer);
        //获取片元着色器的vColor成员的句柄
        int mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, VERTEX_COUNT);
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

    /**
     * 成OpenGL Program
     *
     * @param vertexSource      顶点着色器代码
     * @param fragmentSource    片元着色器代码
     * @return                   生成的OpenGL Program，如果为0，则表示创建失败
     */

    protected int createOpenGLProgram(String vertexSource, String fragmentSource) {
        int vertex = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        if (vertex == 0) {
            Log.e(TAG, "loadShader vertex failed");
            return 0;
        }
        int fragment = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
        if (fragment == 0) {
            Log.e(TAG, "loadShader fragment failed");
            return 0;
        }
        int program = GLES20.glCreateProgram();
        if (program != 0) {
            GLES20.glAttachShader(program, vertex);
            GLES20.glAttachShader(program, fragment);
            GLES20.glLinkProgram(program);
            int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] != GLES20.GL_TRUE) {
                Log.e(TAG, "Could not link program:" + GLES20.glGetProgramInfoLog(program));
                GLES20.glDeleteProgram(program);
                program = 0;
            }
        }
        return program;
    }

    /**
     *加载着色器
     *
     * @param type              加载着色器类型
     * @param shaderCode       加载着色器的代码
     */
    private int loadShader(int type, String shaderCode) {
        //根据type创建顶点着色器或者片元着色器
        int shader = GLES20.glCreateShader(type);
        //将着色器的代码加入到着色器中
        GLES20.glShaderSource(shader, shaderCode);
        //编译着色器
        GLES20.glCompileShader(shader);
        return shader;
    }

}
