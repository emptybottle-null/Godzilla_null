/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

public abstract class XBLogger {
    public static final int DEBUG = 1;
    public static final int INFO = 3;
    public static final int WARN = 5;
    public static final int ERROR = 7;
    public static final int FATAL = 9;
    protected static final String[] LEVEL_STRINGS_SHORT = new String[]{"?", "D", "?", "I", "?", "W", "?", "E", "?", "F", "?"};
    protected static final String[] LEVEL_STRINGS = new String[]{"?0?", "DEBUG", "?2?", "INFO", "?4?", "WARN", "?6?", "ERROR", "?8?", "FATAL", "?10+?"};

    XBLogger() {
    }

    public abstract void initialize(String var1);

    protected abstract void _log(int var1, Object var2);

    protected abstract void _log(int var1, Object var2, Throwable var3);

    public abstract boolean check(int var1);

    public void log(int level, Object ... objs) {
        if (!this.check(level)) {
            return;
        }
        StringBuilder sb = new StringBuilder(32);
        Throwable lastEx = null;
        for (int i = 0; i < objs.length; ++i) {
            if (i == objs.length - 1 && objs[i] instanceof Throwable) {
                lastEx = (Throwable)objs[i];
                continue;
            }
            sb.append(objs[i]);
        }
        String msg = sb.toString();
        msg = msg.replaceAll("[\r\n]+", " ");
        if (lastEx == null) {
            this._log(level, msg);
        } else {
            this._log(level, msg, lastEx);
        }
    }
}

