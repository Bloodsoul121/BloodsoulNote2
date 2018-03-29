package com.example.cgz.bloodsoulnote2.xuliehua.serializable;

import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable);
    }

    public void clickBtn1(View view) {
        try {
            SimpleObjectSerial simpleObjectSerial = new SimpleObjectSerial("desc","super_filed");
            simpleObjectSerial.static_field = 10086;
            simpleObjectSerial.transient_field = 100;

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(simpleObjectSerial);
            outputStream.flush();
            outputStream.close();

            // 常量池更改值
            simpleObjectSerial.static_field = 10087;

            byte[] bytes = byteArrayOutputStream.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            SimpleObjectSerial deserialObj = (SimpleObjectSerial) objectInputStream.readObject();
            System.out.println(deserialObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickBtn2(View view) {
        try {
            Pojo pojo = new Pojo("Hello world");
            byte[] bytes = serialize(pojo); // Serialization
            Pojo p = (Pojo) deserialize(bytes); // De-serialization
            System.out.println(p.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] serialize(Object o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.flush();
        oos.close();
        return baos.toByteArray();
    }

    private Object deserialize(byte[] bytes) throws ClassNotFoundException, IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    /**
     * 03-29 10:52:08.919 1407-1407/com.example.cgz.bloodsoulnote2 I/System.out: desc:  desc
     * 03-29 10:52:08.919 1407-1407/com.example.cgz.bloodsoulnote2 I/System.out: static_field:  10087
     * 03-29 10:52:08.919 1407-1407/com.example.cgz.bloodsoulnote2 I/System.out: transient_field:  0
     * 03-29 10:52:08.919 1407-1407/com.example.cgz.bloodsoulnote2 I/System.out: super_filed:  null
     */

}
