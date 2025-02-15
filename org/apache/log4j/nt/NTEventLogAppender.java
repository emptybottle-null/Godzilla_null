/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.nt;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.TTCCLayout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class NTEventLogAppender
extends AppenderSkeleton {
    private int _handle = 0;
    private String source = null;
    private String server = null;

    public NTEventLogAppender() {
        this(null, null, null);
    }

    public NTEventLogAppender(String source) {
        this(null, source, null);
    }

    public NTEventLogAppender(String server, String source) {
        this(server, source, null);
    }

    public NTEventLogAppender(Layout layout) {
        this(null, null, layout);
    }

    public NTEventLogAppender(String source, Layout layout) {
        this(null, source, layout);
    }

    public NTEventLogAppender(String server, String source, Layout layout) {
        if (source == null) {
            source = "Log4j";
        }
        this.layout = layout == null ? new TTCCLayout() : layout;
        try {
            this._handle = this.registerEventSource(server, source);
        } catch (Exception e) {
            e.printStackTrace();
            this._handle = 0;
        }
    }

    public void close() {
    }

    public void activateOptions() {
        if (this.source != null) {
            try {
                this._handle = this.registerEventSource(this.server, this.source);
            } catch (Exception e) {
                LogLog.error("Could not register event source.", e);
                this._handle = 0;
            }
        }
    }

    public void append(LoggingEvent event) {
        String[] s;
        StringBuffer sbuf = new StringBuffer();
        sbuf.append(this.layout.format(event));
        if (this.layout.ignoresThrowable() && (s = event.getThrowableStrRep()) != null) {
            int len = s.length;
            for (int i = 0; i < len; ++i) {
                sbuf.append(s[i]);
            }
        }
        int nt_category = event.getLevel().toInt();
        this.reportEvent(this._handle, sbuf.toString(), nt_category);
    }

    public void finalize() {
        this.deregisterEventSource(this._handle);
        this._handle = 0;
    }

    public void setSource(String source) {
        this.source = source.trim();
    }

    public String getSource() {
        return this.source;
    }

    public boolean requiresLayout() {
        return true;
    }

    private native int registerEventSource(String var1, String var2);

    private native void reportEvent(int var1, String var2, int var3);

    private native void deregisterEventSource(int var1);

    static {
        String[] archs;
        try {
            archs = new String[]{System.getProperty("os.arch")};
        } catch (SecurityException e) {
            archs = new String[]{"amd64", "ia64", "x86"};
        }
        boolean loaded = false;
        for (int i = 0; i < archs.length; ++i) {
            try {
                System.loadLibrary("NTEventLogAppender." + archs[i]);
                loaded = true;
                break;
            } catch (UnsatisfiedLinkError e) {
                loaded = false;
                continue;
            }
        }
        if (!loaded) {
            System.loadLibrary("NTEventLogAppender");
        }
    }
}

