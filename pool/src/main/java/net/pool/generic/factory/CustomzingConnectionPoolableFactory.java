package net.pool.generic.factory;


import net.pool.generic.connect.CustomzingConnection;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.log4j.Logger;

public class CustomzingConnectionPoolableFactory implements PoolableObjectFactory {
    private static Logger logger = Logger.getLogger(CustomzingConnection.class);
    private static int count = 0;

    /**
     * 创建一个可以由池服务的实例
     */
    public Object makeObject() throws Exception {
        CustomzingConnection connection = new CustomzingConnection(this.generateName());
        connection.connect();

        logger.info(" name = '" + connection.getName() + "' pool create connection");
        return connection;
    }

    /**
     * 销毁池服务不要的实例
     */
    public void destroyObject(Object conn) throws Exception {
        CustomzingConnection connection = (CustomzingConnection) conn;
        connection.close();
    }

    /**
     * 校验对象池的实例是否可用
     * <pre>
     *     1. 创建实例，可用则返回，反之重建
     *     2. 归还，可用则归还，反之销毁
     * </pre>
     */
    public boolean validateObject(Object conn) {
        CustomzingConnection connection = (CustomzingConnection) conn;
        return connection.isConnected();
    }

    /**
     * 激活对象，即对对象分配资源
     */
    public void activateObject(Object conn) throws Exception {
        CustomzingConnection connection = (CustomzingConnection) conn;

        logger.info(" name = '" + connection.getName() + "' activateObject ()");
    }

    /**
     * 钝化对象，即释放对象所使用的资源
     */
    public void passivateObject(Object conn) throws Exception {
        CustomzingConnection connection = (CustomzingConnection) conn;

        logger.info(" name = '" + connection.getName() + "' passivateObject()");
    }

    private synchronized String generateName () {
        return "conn_" + count++;
    }
}
