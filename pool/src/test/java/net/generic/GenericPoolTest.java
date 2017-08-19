package net.generic;

import net.pool.generic.factory.CustomzingConnectionPoolableFactory;
import net.pool.generic.thread.CustomzingThread;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.junit.Test;

public class GenericPoolTest {
    public static void test1() {
        PoolableObjectFactory factory = new CustomzingConnectionPoolableFactory();
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.lifo = false;
        config.maxActive = 5;
        config.maxIdle = 5;
        config.minIdle = 1;
        config.maxWait = 5 * 1000;

        ObjectPool pool = new GenericObjectPool(factory, config);

        new Thread(new CustomzingThread(pool)).start();
    }

    public static void main(String[] args) {
        GenericPoolTest.test1();
    }
}
