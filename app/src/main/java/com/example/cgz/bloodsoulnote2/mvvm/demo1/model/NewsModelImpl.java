package com.example.cgz.bloodsoulnote2.mvvm.demo1.model;

import com.example.cgz.bloodsoulnote2.mvvm.demo1.bean.SimpleNewsBean;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.i.BaseLoadListener;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.i.INewsModel;

/**
 * Created by cgz on 18-4-26.
 */

public class NewsModelImpl implements INewsModel {
    @Override
    public void loadNewsData(int page, BaseLoadListener<SimpleNewsBean> loadListener) {

    }
}
