package com.example.cgz.bloodsoulnote2.ipc.messenger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

public class MessengerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
    }

    public void clickBtn1(View view) {
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);

            Message msg = Message.obtain();
            msg.what = MessengerService.MSG_FROMCLIENT;
//            msg.obj = "兄弟, 来完贪玩蓝月啊...";  // 跨进程不能用这种方式

            Bundle bundle = new Bundle();
            bundle.putString("msg", "兄弟, 来完贪玩蓝月啊...");
            msg.setData(bundle);

            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
