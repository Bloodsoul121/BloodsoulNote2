package com.example.cgz.bloodsoulnote2.menu.top;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cgz.bloodsoulnote2.R;

public class TopMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_menu);
    }

    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用) Inflate the menu; this adds items to the action bar
     * if it is present.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        menu.add(Menu.NONE, 2 , 0, "设置").setIcon(R.drawable.star);
        return true;
    }

    /**
     * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
     * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等） TODO
     * Auto-generated method stub
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 每次菜单被关闭时调用. （菜单被关闭有三种情形，menu按钮被再次点击、back按钮被点击或者用户选择了某一个菜单项） TODO
     * Auto-generated method stub
     */
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    /**
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象。 TODO Auto-generated
     * method stub
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) //得到被点击的item的itemId
        {
            case 1 :  //对应的ID就是在add方法中所设定的Id
                break;
            case 2 :
                break;
        }
        return true;
    }

    /*
        菜单栏不显示Menu的问题:
        仔细查看了Api文档之后才发现原来是 minSdkVersion 设置的问题！！！
        在2.3的系统上编译的代码，我写的是 minSdkVersion 10，
        而在Android Studio编译时使用的是 minSdkVersion 15
        所以只要修改minSdkVersion，把它改成10就可以了，也把targetSdkVersion 改为10。
     */

}
