package com.example.cgz.bloodsoulnote2.otherframe.databinding.demo3;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Toast;

import com.example.cgz.bloodsoulnote2.BR;

public class ManViewModel {

    private Activity mActivity;
    private ViewDataBinding mBinding;
    public Man mMan;

    public ManViewModel(Activity activity, ViewDataBinding binding) {
        mMan = new Man();
        mMan.name.set("man");
        mMan.age.set("16");
        this.mActivity = activity;
        this.mBinding = binding;
        this.mBinding.setVariable(BR.manVM, this);
    }

    public void clickBtn(View view) {
        mMan.age.set("22");
        Toast.makeText(mActivity, "click", Toast.LENGTH_SHORT).show();
    }

}
