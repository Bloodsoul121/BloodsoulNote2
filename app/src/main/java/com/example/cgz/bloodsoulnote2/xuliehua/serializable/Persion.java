package com.example.cgz.bloodsoulnote2.xuliehua.serializable;

/**
 * Created by cgz on 18-3-29.
 */

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Persion类除了writeReplace在序列化时用到,其他的黑科技方法都不会被调用
 * 原因是我们在writeReplace中将序列化对象替换成了内部代理类,所以以后的序列化过程就是针对SerializableProxy
 */
class Persion implements Serializable {

    public String desc;

    public Persion(String desc) {
        this.desc = desc;
    }

    static class SerializableProxy implements Serializable{
        private String desc;

        private SerializableProxy(Persion s) {
            this.desc = s.desc;
        }

        /**
         * 在这里恢复外围类
         * 注意看这里!!!最大的好处就是我们最后得到的外围类是通过构造器构建的!
         * @return
         */
        private Object readResolve() {
            return new Persion(desc);
        }

    }

    /**
     * 外围类直接替换成静态内部代理类作为真正的序列化对象
     * @return
     */
    private Object writeReplace() {
        return new SerializableProxy(this);
    }

    /**
     * 这里主要是为了防止攻击,任何以Persion声明的对象字节流都是流氓!!
     * 因为我在writeReplace中已经把序列化的实例指向了SerializableProxy
     * @param stream
     * @throws InvalidObjectException
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("proxy requied!");
    }
}
