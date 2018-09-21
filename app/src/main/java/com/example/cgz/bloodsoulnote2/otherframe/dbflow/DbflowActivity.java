package com.example.cgz.bloodsoulnote2.otherframe.dbflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cgz.bloodsoulnote2.R;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class DbflowActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbflow);

        FlowManager.init(new FlowConfig.Builder(this).build());

        mTv = (TextView) findViewById(R.id.tv);
    }

    public void add(View view) {
        Good good = new Good();
        good.setgName("food");
        good.setgDes("it is good");

        boolean save = good.save();
        mTv.setText("add : " + save);
    }

    public void update(View view) {
        // 例如根据id修改某条数据为名字叫小王，年龄为20：
        SQLite.update(Good.class)
                .set(Good_Table.gName.eq("meat"),Good_Table.gDes.eq("bad"))
                .where(Good_Table.id.eq((long) 2))
                .query();
    }

    public void delete(View view) {
        // 在where中可以添加删除条件，比如删除id等于1的，或者名字为张三的等等 都可以：
        SQLite.delete()
                .from(Good.class)
                .where(Good_Table.id.eq((long) 1))
                .query();

        // 如果删除整个表，可以这样：
//        Delete.table(Good.class);
    }

    public void query(View view) {
        List<Good> goods = SQLite.select().from(Good.class).queryList();// 查询所有记录

        StringBuilder builder = new StringBuilder();
        for (Good good : goods) {
            builder.append(good.toString());
        }

        mTv.setText(builder.toString());

        Good good = SQLite.select().from(Good.class).querySingle();//   查询第一条记录
    }
}
