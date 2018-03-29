package com.example.cgz.bloodsoulnote2.xuliehua.serializable;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by cgz on 18-3-29.
 */

public class Pojo implements Serializable, ObjectInputValidation {

    private String msg;

    public Pojo(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 是否替换需要序列化的对象
     * @return
     * @throws ObjectStreamException
     */
    private Object writeReplace() throws ObjectStreamException {
        //还没有替换对象之前,这里是helloworld
        System.out.println("writeReplace! msg: "+msg);
        //替换对象,这里指定为replace
        //注意,这里我们不一定要返回一个Pojo对象,我们可以返回任意Object
        return new Pojo("replace!");
    }

    /**
     * 写入outputstream流
     * @param out
     * @throws IOException
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        //这里对象已经被替换成msg为replace的对象
        System.out.println("writeObject! msg: "+msg);
        //序列化对象
        out.defaultWriteObject();
        //额外往流中写入一些其它数据,注意顺序
        out.writeInt(10086);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        //在执行defaultReadObject之前,这个对象的msg还没有被指定
        //这里需要注意的是,当前这个反序列化出来的对象并不是通过构造器创建的
        System.out.println("readObject! msg: "+msg);
        //读取
        in.defaultReadObject();
        //msg被读取出来
        System.out.println("readObject! msg: "+msg);
        //读取写入的额外数据
        System.out.println(in.readInt());
        //注册一个检验数据是否完整的回调,注意这个this
        in.registerValidation(this, 0);
    }

    /**
     * 是否替换从流中读取出来的对象
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        //替换之前的对象
        System.out.println("readResolve! msg: "+msg);
        //直接替换一个对象,这里需要注意的是readObject中注册的合法检验对象是反序列化产生的对象
        // 如果在这里替换,那么检测地还是之前的对象
        return new Pojo("readResolve!");
    }

    @Override
    public void validateObject() throws InvalidObjectException {
        //注意,这里是readObject中读取的对象,而并不是readResolve替换之后的对象
        System.out.println("validateObject! msg: "+msg);
        //在这里进行合法性检查,比如msg不能为空
        if(msg == null || "".equals(msg)) {
            throw new InvalidObjectException("Pojo msg null!");
        }
    }

    /**
     * 03-29 11:06:51.085 5142-5142/com.example.cgz.bloodsoulnote2 I/System.out: writeReplace! msg: Hello world
     * 03-29 11:06:51.086 5142-5142/com.example.cgz.bloodsoulnote2 I/System.out: writeObject! msg: replace!
     * 03-29 11:06:51.087 5142-5142/com.example.cgz.bloodsoulnote2 I/System.out: readObject! msg: null
     * 03-29 11:06:51.087 5142-5142/com.example.cgz.bloodsoulnote2 I/System.out: readObject! msg: replace!
     * 03-29 11:06:51.087 5142-5142/com.example.cgz.bloodsoulnote2 I/System.out: 10086
     * 03-29 11:06:51.087 5142-5142/com.example.cgz.bloodsoulnote2 I/System.out: readResolve! msg: replace!
     * 03-29 11:06:51.088 5142-5142/com.example.cgz.bloodsoulnote2 I/System.out: validateObject! msg: replace!
     * 03-29 11:06:51.088 5142-5142/com.example.cgz.bloodsoulnote2 I/System.out: readResolve!
     */

}
