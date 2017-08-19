package net.pool.generic.connect;

import org.apache.log4j.Logger;

public class CustomzingConnection {
    private static Logger logger = Logger.getLogger(CustomzingConnection.class);

    private String name;
    private boolean connected;

    public CustomzingConnection (String name) {
        this.name = name;
    }

    public void connect () {
        this.connected = true;
        logger.info(" name = '" + this.name + "' connection created");
    }

    public void close () {
        this.connected =  false;
        logger.info(" name = " + this.name + " is disconnected");
    }

    public String getName () {
        return this.name;
    }

    public boolean isConnected () {
        return this.connected;
    }

    public void print () {
        logger.info(" ========== " + this.name);
    }
}
