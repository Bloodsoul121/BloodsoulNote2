package com.example.cgz.bloodsoulnote2.view.custom.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;

public class SettingListDialog {

    private final Context mContext;
    private AlertDialog mDialog;

    public SettingListDialog(Context context) {
        mContext = context;
        createDialog();
    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_setting_view_dialog, null);
        builder.setView(view);
        builder.setTitle("Dialog");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mDialog = builder.create();
    }

    public void show() {
        mDialog.show();
    }

}
