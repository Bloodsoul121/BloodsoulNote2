package com.example.cgz.bloodsoulnote2.wifi;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class SendTask extends AsyncTask<String, Integer, Void> {

    private FileBean mFileBean;
    private Context mContext;
    private ProgressDialog mProgressDialog;

    public SendTask(Context ctx, FileBean fileBean) {
        mFileBean = fileBean;
        mContext = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //创建进度条
        mProgressDialog = new ProgressDialog(mContext);
    }

    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }

}
