/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js;

public class Logger {
    private static boolean DEBUG = Boolean.parseBoolean(System.getProperty("javascript.debug"));

    public static final void log(String msg) {
        if (DEBUG) {
            System.out.println(msg);
        }
    }

    public static final void logError(String msg) {
        System.err.println(msg);
    }
}

