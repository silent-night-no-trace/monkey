package com.google.style.redis.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;

/**
 * @author liangz
 * @date 2018/2/28 15:46
 *  序列化工具类
 **/
public class SerializeUtil {

    /**
     * 软引用kryo对象池
     */
    private final static SoftReferenceObjectPool<Kryo> KRYO_POOL = new SoftReferenceObjectPool<>(
            new PooledObjectFactory<Kryo>() {
                @Override
                public PooledObject<Kryo> makeObject() {
                    return new DefaultPooledObject<>(new Kryo());
                }

                @Override
                public void destroyObject(PooledObject<Kryo> pooledObject) {
                }

                @Override
                public boolean validateObject(PooledObject<Kryo> pooledObject) {
                    return true;
                }

                @Override
                public void activateObject(PooledObject<Kryo> pooledObject) {
                }

                @Override
                public void passivateObject(PooledObject<Kryo> pooledObject) {
                }
            });

    /**
     * 序列化对象
     *
     * @param obj 对象
     * @return 二进制结果
     */
    public static byte[] serialize(Object obj) throws Exception {
        Output output = new Output(4096, Integer.MAX_VALUE);
        Kryo kryo = SerializeUtil.KRYO_POOL.borrowObject();
        kryo.writeClassAndObject(output, obj);
        SerializeUtil.KRYO_POOL.returnObject(kryo);
        return output.getBuffer();
    }

    /**
     * 反序列化
     *
     * @param bytes 序列化二进制
     * @return 对象
     */
    public static Object deserialize(byte[] bytes) throws Exception {
        Kryo kryo = SerializeUtil.KRYO_POOL.borrowObject();
        Object object = kryo.readClassAndObject(new Input(bytes));
        SerializeUtil.KRYO_POOL.returnObject(kryo);
        return object;
    }



//    public static byte[] serialize(Object object) {
//        ObjectOutputStream oos = null;
//        ByteArrayOutputStream baos = null;
//        try {
//            // 序列化
//            baos = new ByteArrayOutputStream();
//            oos = new ObjectOutputStream(baos);
//            oos.writeObject(object);
//            byte[] bytes = baos.toByteArray();
//            return bytes;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static Object unserialize(byte[] bytes) {
//        ByteArrayInputStream bais = null;
//        try {
//            // 反序列化
//            bais = new ByteArrayInputStream(bytes);
//            ObjectInputStream ois = new ObjectInputStream(bais);
//            return ois.readObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
