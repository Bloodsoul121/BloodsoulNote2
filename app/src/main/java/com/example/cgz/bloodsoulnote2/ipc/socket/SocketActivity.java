package com.example.cgz.bloodsoulnote2.ipc.socket;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketActivity extends BaseActivity {

    private Button bt_send;

    private EditText et_receive;

    private TextView tv_message;

    private LinearLayout mInputLine;

    private PrintWriter mPrintWriter;
    private Intent mServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        initView();

    }

    private void initView() {
        et_receive = (EditText) findViewById(R.id.et_receive);
        bt_send = (Button) findViewById(R.id.bt_send);
        tv_message = (TextView) findViewById(R.id.tv_message);
        mInputLine = (LinearLayout) findViewById(R.id.inputline);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String msg = et_receive.getText().toString();
                //向服务器发送信息
                if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
                    mPrintWriter.println(msg);
                    tv_message.setText(tv_message.getText() + "\n" + "client ： " + msg);
                    et_receive.setText("");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initService();
    }

    private void initService() {
        // 开启服务
        mServer = new Intent(this, SocketServer.class);
        startService(mServer);
        // 子线程接收server的信息
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectSocketServer();
            }
        }).start();
    }

    private void connectSocketServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", SocketServer.PORT);
                // 写入流
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 读出流
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!isFinishing()) {
                final String msg = reader.readLine();
                if (!TextUtils.isEmpty(msg)) {
                    runOnMain(new Runnable() {
                        @Override
                        public void run() {
                            tv_message.setText(tv_message.getText() + "\n" + "server： " + msg);
                        }
                    });
                }
            }

            socket.close();
            reader.close();
            mPrintWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(mServer);
    }
}
