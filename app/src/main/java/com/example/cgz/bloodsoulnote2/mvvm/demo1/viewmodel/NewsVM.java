package com.example.cgz.bloodsoulnote2.mvvm.demo1.viewmodel;

import android.content.Context;

import com.example.cgz.bloodsoulnote2.mvvm.demo1.other.MainConstant;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.adapter.NewsAdapter;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.bean.SimpleNewsBean;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.i.BaseLoadListener;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.i.INewsModel;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.i.INewsView;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.model.NewsModelImpl;

import java.util.List;

/**
 * Created by cgz on 18-4-26.
 */

public class NewsVM implements BaseLoadListener<SimpleNewsBean> {

    private INewsModel mNewsModel;
    private INewsView mNewsView;
    private NewsAdapter mAdapter;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型

    public NewsVM(Context context, NewsAdapter adapter) {
        this.mNewsView = mNewsView;
        this.mAdapter = mAdapter;
        mNewsModel = new NewsModelImpl();
        getNewsData();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewsData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mNewsModel.loadNewsData(currPage, this);
    }

    public void loadRefreshData() {
        loadType = MainConstant.LoadData.REFRESH;
        currPage = 1;
        mNewsModel.loadNewsData(currPage, this);
    }

    public void loadMoreData() {
        loadType = MainConstant.LoadData.LOAD_MORE;
        currPage++;
        mNewsModel.loadNewsData(currPage, this);
    }

    @Override
    public void loadSuccess(List<SimpleNewsBean> list) {

    }

    @Override
    public void loadFailure(String message) {

    }

    @Override
    public void loadStart() {

    }

    @Override
    public void loadComplete() {

    }
}
