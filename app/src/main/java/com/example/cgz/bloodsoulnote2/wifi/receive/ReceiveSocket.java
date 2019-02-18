package com.example.cgz.bloodsoulnote2.wifi.receive;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.cgz.bloodsoulnote2.wifi.FileBean;
import com.example.cgz.bloodsoulnote2.wifi.FileUtils;
import com.example.cgz.bloodsoulnote2.wifi.Md5Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveSocket {

    private static final int PORT = 8899;
    private static final String TAG = "ReceiveSocket";
    private FileOutputStream mFileOutputStream;
    private File mFile;
    private ObjectInputStream mObjectInputStream;
    private InputStream mInputStream;

    /**
     * 监听接收进度
     */
    private ProgressReceiveListener mListener;
    private ServerSocket mServerSocket;
    private Socket mSocket;

    public void setOnProgressReceiveListener(ProgressReceiveListener listener) {
        mListener = listener;
    }

    public interface ProgressReceiveListener {

        //开始传输
        void onSatrt();

        //当传输进度发生变化时
        void onProgressChanged(File file, int progress);

        //当传输结束时
        void onFinished(File file);

        //传输失败回调
        void onFaliure(File file);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 40:
                    if (mListener != null) {
                        mListener.onSatrt();
                    }
                    break;
                case 50:
                    int progress = (int) msg.obj;
                    if (mListener != null) {
                        mListener.onProgressChanged(mFile, progress);
                    }
                    break;
                case 60:
                    if (mListener != null) {
                        mListener.onFinished(mFile);
                    }
                    break;
                case 70:
                    if (mListener != null) {
                        mListener.onFaliure(mFile);
                    }
                    break;
            }
        }
    };

    public void receive() {
        try {
            mServerSocket = new ServerSocket();
            mServerSocket.setReuseAddress(true);
            mServerSocket.bind(new InetSocketAddress(PORT));
            mSocket = mServerSocket.accept();
            Log.i(TAG, "客户端IP地址 : " + mSocket.getRemoteSocketAddress());
            mInputStream = mSocket.getInputStream();
            mObjectInputStream = new ObjectInputStream(mInputStream);
            FileBean fileBean = (FileBean) mObjectInputStream.readObject();
            String name = new File(fileBean.filePath).getName();
            Log.i(TAG, "客户端传递的文件名称 : " + name);
            Log.i(TAG, "客户端传递的MD5 : " + fileBean.md5);
            mFile = new File(FileUtils.SdCardPath(name));
            mFileOutputStream = new FileOutputStream(mFile);
            //开始接收文件
            mHandler.sendEmptyMessage(40);
            byte bytes[] = new byte[1024];
            int len;
            long total = 0;
            int progress;
            while ((len = mInputStream.read(bytes)) != -1) {
                mFileOutputStream.write(bytes, 0, len);
                total += len;
                progress = (int) ((total * 100) / fileBean.fileLength);
                Log.i(TAG, "文件接收进度: " + progress);
                Message message = Message.obtain();
                message.what = 50;
                message.obj = progress;
                mHandler.sendMessage(message);
            }

            //新写入文件的MD5
            String md5New = Md5Util.getMd5(mFile);
            //发送过来的MD5
            String md5Old = fileBean.md5;
            if (md5New != null || md5Old != null) {
                if (md5New.equals(md5Old)) {
                    mHandler.sendEmptyMessage(60);
                    Log.e(TAG, "文件接收成功");
                }
            } else {
                mHandler.sendEmptyMessage(70);
            }

            mServerSocket.close();
            mInputStream.close();
            mObjectInputStream.close();
            mFileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(70);
            Log.e(TAG, "文件接收异常");
        }
    }


    /**
     * 服务断开：释放内存
     */
    public void clean() {
        if (mServerSocket != null) {
            try {
                mServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mInputStream != null) {
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mObjectInputStream != null) {
            try {
                mObjectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mFileOutputStream != null) {
            try {
                mFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
