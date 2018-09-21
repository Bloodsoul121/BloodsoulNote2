package com.example.cgz.bloodsoulnote2.otherframe.databinding.demo2;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.example.cgz.bloodsoulnote2.BR;

public class PersonViewModel {

    private Activity mActivity;
    private ViewDataBinding mBinding;
    public Person mPerson;

    public PersonViewModel(Activity activity, ViewDataBinding binding) {
        mPerson = new Person("person", "12");
        this.mActivity = activity;
        this.mBinding = binding;
        this.mBinding.setVariable(BR.personVM, this);
    }

    public void clickBtn(View view) {
        this.mBinding.setVariable(BR.personVM, this);
    }

}
