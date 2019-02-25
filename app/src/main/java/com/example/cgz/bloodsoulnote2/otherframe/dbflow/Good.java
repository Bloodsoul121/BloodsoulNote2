package com.example.cgz.bloodsoulnote2.otherframe.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

@Table(database = AppDatabase.class)
public class Good extends BaseModel implements Serializable {
    @Column
    public String gName;
    @Column
    public String gDes;
    @Column
    public int num;

    @PrimaryKey(autoincrement = true)//ID自增
    public long id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column
    public String content;//增加的字段

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgDes() {
        return gDes;
    }

    public void setgDes(String gDes) {
        this.gDes = gDes;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Good{" +
                "gName='" + gName + '\'' +
                ", gDes='" + gDes + '\'' +
                ", num=" + num +
                ", id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
