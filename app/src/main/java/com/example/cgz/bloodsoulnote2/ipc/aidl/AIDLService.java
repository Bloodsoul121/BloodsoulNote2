package com.example.cgz.bloodsoulnote2.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by cgz on 18-3-29.
 */

public class AIDLService extends Service {

    private CopyOnWriteArrayList<Game> mGameList = new CopyOnWriteArrayList<>();

    private Binder mBinder = new IGameManager.Stub() {

        @Override
        public List<Game> getGameList() throws RemoteException {
            return mGameList;
        }

        @Override
        public void addGame(Game game) throws RemoteException {
            mGameList.add(game);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGameList.add(new Game("aaa", "fuck"));
        mGameList.add(new Game("bbb", "shirt"));
    }
}