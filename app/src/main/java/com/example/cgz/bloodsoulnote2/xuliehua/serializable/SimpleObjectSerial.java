package com.example.cgz.bloodsoulnote2.xuliehua.serializable;

import java.io.Serializable;

/**
 * Created by cgz on 18-3-29.
 */

public class SimpleObjectSerial extends SimpleSuperClass implements Serializable {

    /**
     * 固定写法,故名思意,指定序列化的版本,这个信息在对象序列化时写入到字节流中.
     * 比如我们把SimpleObjectSerial对象序列化之后,字节流中不仅仅有对象中各个域的数据,同时还会存储序列化
     * 时SimpleObjectSerial类当时的一些信息,比如类名、VERSION_UID等
     *
     * 反序列化时,JVM会自动检查我们用来反序列化的类信息和字节流中的类信息定义是否一致,如果不一致,会抛出异常
     * 一般我们都会在实现了Serializable的类中定义UID字段用于版本控制.
     */
    private static final long serialVersionUID = 1L;

    //一个普通的域
    public String desc;
    //静态域
    public static int static_field;
    //transient域
    public transient int transient_field;

    public SimpleObjectSerial(String desc ,String super_filed) {
        super(super_filed);
        this.desc = desc;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        appendFiled("desc",desc,stringBuilder);
        appendFiled("static_field",static_field,stringBuilder);
        appendFiled("transient_field",transient_field,stringBuilder);
        appendFiled("super_filed",super_filed,stringBuilder);
        return stringBuilder.toString();
    }

    private StringBuilder appendFiled(String filedName,Object filed,StringBuilder stringBuilder) {
        return stringBuilder.append(filedName).append(":  ").append(filed).append("\n");
    }
}
/**
 * 如果一个类实现了Serializable类,同时它继承了一个类,比如这里的SimpleSuperClass,那么父类用么实现
 * Serializable接口,要么提供一个默认无参数构造器.道理很简单,构造一个子类需要先构造它的父类
 */
class SimpleSuperClass {

    public String super_filed;

    public SimpleSuperClass() {}

    public SimpleSuperClass(String super_filed) {
        this.super_filed = super_filed;
    }
}

