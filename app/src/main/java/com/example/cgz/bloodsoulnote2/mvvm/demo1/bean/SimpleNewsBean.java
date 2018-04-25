package com.example.cgz.bloodsoulnote2.mvvm.demo1.bean;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by cgz on 18-4-25.
 */

public class SimpleNewsBean {
    public ObservableInt color = new ObservableInt();
    public ObservableField<String> thumbnail = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableInt id = new ObservableInt();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableBoolean isGood = new ObservableBoolean(); //是否点赞
}
