package com.example.cgz.bloodsoulnote2.wifi;


import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class Wifip2pService extends IntentService {

    private static final String TAG = "Wifip2pService";

    private ReceiveSocket mReceiveSocket;

    public class MyBinder extends Binder {

        public MyBinder() {
            super();
        }
        public void initListener(ReceiveSocket.ProgressReceiveListener listener){
            mReceiveSocket.setOnProgressReceiveListener(listener);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public Wifip2pService() {
        super("Wifip2pService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "服务启动了");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mReceiveSocket.clean();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mReceiveSocket = new ReceiveSocket();
        mReceiveSocket.receive();
        Log.i(TAG, "传输完毕");
    }

}
