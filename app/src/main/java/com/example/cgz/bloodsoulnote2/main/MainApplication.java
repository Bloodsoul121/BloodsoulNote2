package com.example.cgz.bloodsoulnote2.main;

import android.app.Application;

import com.example.cgz.bloodsoulnote2.otherframe.greendao.DaoMaster;
import com.example.cgz.bloodsoulnote2.otherframe.greendao.DaoSession;

/**
 * Created by cgz on 18-3-1.
 */

public class MainApplication extends Application {

    private static MainApplication mainApplication = null;

    private DaoSession mDaoSession;

    public static MainApplication getInstance() {
        return mainApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mainApplication = this;

        setupDatabase();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "shop.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}
