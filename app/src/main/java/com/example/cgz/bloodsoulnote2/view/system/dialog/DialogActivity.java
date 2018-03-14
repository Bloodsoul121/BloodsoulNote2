package com.example.cgz.bloodsoulnote2.view.system.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.util.ArrayList;

public class DialogActivity extends BaseActivity {

    private int yourChoice;

    private ArrayList<Integer> yourChoices = new ArrayList<>();
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void clickBtn1(View view) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(DialogActivity.this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("我是一个普通Dialog");
        normalDialog.setMessage("你要点击哪一个按钮呢?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    public void clickBtn2(View view) {
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(DialogActivity.this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("我是一个普通Dialog").setMessage("你要点击哪一个按钮呢?");
        normalDialog.setPositiveButton("按钮1",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ...To-do
                    }
                });
        normalDialog.setNeutralButton("按钮2",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ...To-do
                    }
                });
        normalDialog.setNegativeButton("按钮3", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ...To-do
            }
        });
        // 创建实例并显示
        normalDialog.show();
    }

    public void clickBtn3(View view) {
        final String[] items = { "我是1","我是2","我是3","我是4" };
        AlertDialog.Builder listDialog = new AlertDialog.Builder(DialogActivity.this);
        listDialog.setTitle("我是一个列表Dialog");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                Toast.makeText(DialogActivity.this,
                        "你点击了" + items[which],
                        Toast.LENGTH_SHORT).show();
            }
        });
        listDialog.show();
    }

    public void clickBtn4(View view) {
        final String[] items = { "我是1","我是2","我是3","我是4" };
        yourChoice = -1;
        final AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(DialogActivity.this);
        singleChoiceDialog.setTitle("我是一个单选Dialog");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                        mDialog.hide();
                        Toast.makeText(DialogActivity.this,
                                    "你选择了" + items[yourChoice],
                                    Toast.LENGTH_SHORT).show();
                    }
                });
//        singleChoiceDialog.setPositiveButton("确定",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (yourChoice != -1) {
//                            Toast.makeText(DialogActivity.this,
//                                    "你选择了" + items[yourChoice],
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
        mDialog = singleChoiceDialog.show();
    }

    public void clickBtn5(View view) {
        final String[] items = { "我是1","我是2","我是3","我是4" };
        // 设置默认选中的选项，全为false默认均未选中
        final boolean initChoiceSets[]={false,false,false,false};
        yourChoices.clear();
        AlertDialog.Builder multiChoiceDialog = new AlertDialog.Builder(DialogActivity.this);
        multiChoiceDialog.setTitle("我是一个多选Dialog");
        multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        if (isChecked) {
                            yourChoices.add(which);
                        } else {
                            yourChoices.remove(which);
                        }
                    }
                });
        multiChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int size = yourChoices.size();
                        String str = "";
                        for (int i = 0; i < size; i++) {
                            str += items[yourChoices.get(i)] + " ";
                        }
                        Toast.makeText(DialogActivity.this,
                                "你选中了" + str,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        multiChoiceDialog.show();
    }

    public void clickBtn6(View view) {
        /* 等待Dialog具有屏蔽其他控件的交互能力
         * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
         * 下载等事件完成后，主动调用函数关闭该Dialog
         */
        ProgressDialog waitingDialog= new ProgressDialog(DialogActivity.this);
        waitingDialog.setTitle("我是一个等待Dialog");
        waitingDialog.setMessage("等待中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

    public void clickBtn7(View view) {
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog = new ProgressDialog(DialogActivity.this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("我是一个进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
    /* 模拟进度增加的过程
     * 新开一个线程，每个100ms，进度增加1
     */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress= 0;
                while (progress < MAX_PROGRESS){
                    try {
                        Thread.sleep(100);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                // 进度达到最大值后，窗口消失
                progressDialog.cancel();
            }
        }).start();
    }

    public void clickBtn8(View view) {
        final EditText editText = new EditText(DialogActivity.this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(DialogActivity.this);
        inputDialog.setTitle("我是一个输入Dialog").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this,
                                editText.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    public void clickBtn9(View view) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(DialogActivity.this);
        final View dialogView = LayoutInflater.from(DialogActivity.this).inflate(R.layout.dialog_customize,null);
        customizeDialog.setTitle("我是一个自定义Dialog");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText edit_text = (EditText) dialogView.findViewById(R.id.edit_text);
                        Toast.makeText(DialogActivity.this,
                                edit_text.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        customizeDialog.show();
    }
}
