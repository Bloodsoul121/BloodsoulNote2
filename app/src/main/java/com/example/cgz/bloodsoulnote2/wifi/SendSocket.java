package com.example.cgz.bloodsoulnote2.wifi;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SendSocket {

    private static final int PORT = 8899;
    private static final String TAG = "SendSocket";
    private final FileBean mFileBean;
    private final String mAddress;
    /**
     * 监听发送进度
     */
    private ProgressSendListener mlistener;
    private File mFile;

    public interface ProgressSendListener {

        //当传输进度发生变化时
        void onProgressChanged(File file, int progress);

        //当传输结束时
        void onFinished(File file);

        //传输失败时
        void onFaliure(File file);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10:
                    int progress = (int) msg.obj;
                    if (mlistener != null) {
                        mlistener.onProgressChanged(mFile, progress);
                    }
                    break;
                case 20:
                    if (mlistener != null) {
                        mlistener.onFinished(mFile);
                    }
                    break;
                case 30:
                    if (mlistener != null) {
                        mlistener.onFaliure(mFile);
                    }
                    break;
            }
        }
    };

    public SendSocket(FileBean fileBean, String address, ProgressSendListener listener) {
        mFileBean = fileBean;
        mAddress = address;
        mlistener = listener;
    }

    public void send() {
        try {
            Socket socket = new Socket(mAddress, PORT);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(mFileBean);

            mFile = new File(mFileBean.filePath);
            FileInputStream inputStream = new FileInputStream(mFile);
            long size = mFileBean.fileLength;
            long total = 0;
            byte bytes[] = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                total += len;
                // 进度
                int progress = (int) ((total * 100) / size);
                Log.i(TAG, "文件发送进度：" + progress);
                Message message = Message.obtain();
                message.what = 10;
                message.obj = progress;
                mHandler.sendMessage(message);
            }

            outputStream.close();
            oos.close();
            inputStream.close();
            socket.close();
            mHandler.sendEmptyMessage(20);
            Log.i(TAG, "文件发送成功");

        } catch (IOException e) {
            mHandler.sendEmptyMessage(30);
            Log.i(TAG, "文件发送异常");
            e.printStackTrace();
        }
    }

}
