package com.tratao.xcore.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TLog {
    private final String TAG = "xCore";
    private Logger logger;
    private boolean debug;

    private TLog() {
        logger = LoggerFactory.getLogger(TAG);
    }

    private static class SingletonLog {
        private static final TLog instance = new TLog();
    }

    public static TLog getInstance() {
        return SingletonLog.instance;
    }

    public void log(String message) {
        if (debug) {
            logger.info(message);
        }
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
