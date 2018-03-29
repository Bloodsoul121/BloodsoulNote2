package com.example.cgz.bloodsoulnote2.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/**
 * Created by cgz on 18-3-29.
 */

public class MessengerService extends Service {

    public static final String TAG = "MessengerService";

    public static final int MSG_FROMCLIENT=1000;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROMCLIENT:
                    Log.i(TAG, "接收到客户端消息 --> " + msg.getData().getString("msg"));
                    break;
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }

}
