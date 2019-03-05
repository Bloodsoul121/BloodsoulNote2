package com.example.cgz.bloodsoulnote2.otherframe.arouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.cgz.bloodsoulnote2.R;

@Route(path = "/otherframe/arouter/arouter_activity")
public class ARouterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter);

        ARouter.getInstance().inject(this);
    }

    public void skip(View view) {
        ARouter.getInstance().build("/otherframe/arouter/skip_other_activity").navigation(this, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                Log.i("ARouter", "NavigationCallback onFound");
            }

            @Override
            public void onLost(Postcard postcard) {
                Log.i("ARouter", "NavigationCallback onLost");
            }

            @Override
            public void onArrival(Postcard postcard) {
                Log.i("ARouter", "NavigationCallback onArrival");
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                Log.i("ARouter", "NavigationCallback onInterrupt");
            }
        });
    }
}
