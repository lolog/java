package net.pool.generic.thread;

import net.pool.generic.connect.CustomzingConnection;
import net.until.BasicUntil;
import org.apache.commons.pool.ObjectPool;
import org.apache.log4j.Logger;

public class CustomzingThread implements Runnable {
    private static Logger logger = Logger.getLogger(CustomzingThread.class);
    private ObjectPool pool;

    public CustomzingThread (ObjectPool pool) {
        this.pool = pool;
    }

    public void run() {
        CustomzingConnection connection = null;

        try {
            // 获取对象池的实例
            connection = (CustomzingConnection) pool.borrowObject();

            logger.info(" ==== " + connection.getName() + " in thread");

            if (connection == null) {
                logger.info(" connection is null : " + Thread.currentThread().getName());
            }

            logger.info(" Thread name = " + Thread.currentThread().getName() + "");
            Thread.sleep(1 * 1000L);
        } catch (Exception e) {
            logger.error("error = " + e);
        }
        finally {
            if (BasicUntil.isEmpty(connection) == false) {
                try {
                    pool.returnObject(connection);
                    logger.info(" ==== " + connection.getName() + " is already return pool");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
