package com.example.cgz.bloodsoulnote2.install;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.wifi.WifiP2PActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.functions.Consumer;

public class AppInstallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_install);
        requestPermissions();
    }

    public void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.INSTALL_PACKAGES)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Toast.makeText(AppInstallActivity.this, "accept", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AppInstallActivity.this, "deny", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // 无法使用，没有root及权限，第三方的应用行不通
    public void installSilent(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppInstallUtils.installAppSilent(copy2file("Bloodsoul2.apk"));
            }
        }).start();
    }

    public void installNormal(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = copy2file("Bloodsoul2.apk");

                try {
                    Runtime.getRuntime().exec("chmod 777 " + file.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                AppInstallUtils.installApp(file);
            }
        }).start();
    }

    private File copy2file(String fileName) {
        OutputStream out = null;
        InputStream in = null;
        File file = null;
        try {
            file = new File(getFilesDir(), fileName);
            out = new FileOutputStream(file);
            in = getAssets().open(fileName);
            byte[] arr = new byte[1024];
            int len;
            while ((len = in.read(arr)) != -1) {
                out.write(arr, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
