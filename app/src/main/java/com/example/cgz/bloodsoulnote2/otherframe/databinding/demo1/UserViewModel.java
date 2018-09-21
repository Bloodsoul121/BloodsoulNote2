package com.example.cgz.bloodsoulnote2.otherframe.databinding.demo1;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Toast;

import com.example.cgz.bloodsoulnote2.BR;

public class UserViewModel {

    private Activity mActivity;
    private ViewDataBinding mBinding;
    public User mUser;

    public UserViewModel(Activity activity, ViewDataBinding binding) {
        mUser = new User("user", "12");
        this.mActivity = activity;
        this.mBinding = binding;
        this.mBinding.setVariable(BR.userVM, this);
    }

    public void clickBtn(View view) {
        Toast.makeText(mActivity, "click", Toast.LENGTH_SHORT).show();
        mUser.setAge("18");
        this.mBinding.setVariable(BR.userVM, this);
    }

}
