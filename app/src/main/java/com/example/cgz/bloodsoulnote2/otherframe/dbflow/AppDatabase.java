package com.example.cgz.bloodsoulnote2.otherframe.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public final class AppDatabase {
    //数据库名称
    public static final String NAME = "AppDatabase";
    //数据库版本号
    public static final int VERSION = 2;

    @Migration(version = VERSION, database = AppDatabase.class)
    public static class Migration2UserData extends AlterTableMigration<Good> {

        public Migration2UserData(Class<Good> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.TEXT, "content");
        }
    }
}
