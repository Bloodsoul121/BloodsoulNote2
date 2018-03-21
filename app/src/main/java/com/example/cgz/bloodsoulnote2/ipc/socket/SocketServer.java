package com.example.cgz.bloodsoulnote2.ipc.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cgz on 18-3-20.
 */

public class SocketServer extends Service {

    public static int PORT = 8868;
    private boolean mIsServerDestroyed;
    private Socket mClient;
    private BufferedReader in;
    private PrintWriter out;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("overlord", "socket server onCreate");
        new Thread(new TcpServer()).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("overlord", "socket server onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServerDestroyed = true;

        Log.i("overlord", "socket server 已经关闭");

        try {
            Log.i("overlord", "资源 close 1");

            if (mClient != null) {
                mClient.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }

            Log.i("overlord", "资源 close");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TcpServer implements Runnable {

        @Override
        public void run() {
            Log.i("overlord", "TcpServer run");
            ServerSocket socket = null;
            try {
                socket = new ServerSocket(PORT);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("overlord", "TcpServer failed !");
                return;
            }

            while (!mIsServerDestroyed) {
                try {
                    mClient = socket.accept();
                    in = new BufferedReader(new InputStreamReader(mClient.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mClient.getOutputStream())));
                    out.println("您好, 我是服务端");

                    while (!mIsServerDestroyed) {
                        String s = in.readLine();
                        Log.i("overlord", "server : 收到客户端发来的信息 --> " + s);

                        if (TextUtils.isEmpty(s)) {
                            // 客户端断开了连接
                            Log.i("overlord", "客户端断开连接");
                            break;
                        }

                        // 从客户端收到的消息加工再发送给客户端
                        String message = "我接收到了你的信息, 如：" + s;
                        out.println(message);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
