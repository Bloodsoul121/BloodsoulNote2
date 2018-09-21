package com.example.cgz.bloodsoulnote2.otherframe.greendao;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cgz.bloodsoulnote2.main.MainApplication;
import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.util.List;

public class GreendaoActivity extends BaseActivity {

    private ShopDao mShopDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);

        DaoSession daoSession = MainApplication.getInstance().getDaoSession();
        mShopDao = daoSession.getShopDao();
    }

    public void add(View view) {
        Shop shop = new Shop();
        shop.setId(123l);
        shop.setName("haha");
        shop.setPrice("111");
        mShopDao.insert(shop);
    }

    public void update(View view) {
        Shop shop = new Shop();
        shop.setId(123l);
        shop.setName("haha");
        shop.setPrice("222");
        mShopDao.update(shop);
    }

    public void delete(View view) {
        mShopDao.deleteByKey(123l);
    }

    public void query(View view) {
        List<Shop> list = mShopDao.queryBuilder().where(ShopDao.Properties.Id.between(100, 200)).limit(5).build().list();
        for (Shop shop : list) {
            Log.i("greendao", shop.toString());
        }
    }
}
