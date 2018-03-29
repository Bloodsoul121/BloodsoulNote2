package com.example.cgz.bloodsoulnote2.ipc.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.util.List;

public class AidlActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
    }

    public void clickBtn1(View view) {
        Intent intent = new Intent(this, AIDLService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IGameManager iGameManager = IGameManager.Stub.asInterface(service);
            try {
                iGameManager.addGame(new Game("贪玩蓝月", "一款上瘾的游戏"));
                List<Game> gameList = iGameManager.getGameList();
                for (Game game : gameList) {
                    log("game: " + game.toString());
                }
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
