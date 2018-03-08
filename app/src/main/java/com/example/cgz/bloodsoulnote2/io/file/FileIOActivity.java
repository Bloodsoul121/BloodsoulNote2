package com.example.cgz.bloodsoulnote2.io.file;

import android.os.Bundle;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);
    }

    private void test() {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("aaa.txt");
            out = new FileOutputStream("bbb.txt");
            byte[] arr = new byte[1024];
            int len;
            while ((len = in.read(arr)) != -1) {
                out.write(arr, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
