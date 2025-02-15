/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.helpers.LogLog;

public class ConsoleAppender
extends WriterAppender {
    public static final String SYSTEM_OUT = "System.out";
    public static final String SYSTEM_ERR = "System.err";
    protected String target = "System.out";
    private boolean follow = false;

    public ConsoleAppender() {
    }

    public ConsoleAppender(Layout layout) {
        this(layout, SYSTEM_OUT);
    }

    public ConsoleAppender(Layout layout, String target) {
        this.setLayout(layout);
        this.setTarget(target);
        this.activateOptions();
    }

    public void setTarget(String value) {
        String v = value.trim();
        if (SYSTEM_OUT.equalsIgnoreCase(v)) {
            this.target = SYSTEM_OUT;
        } else if (SYSTEM_ERR.equalsIgnoreCase(v)) {
            this.target = SYSTEM_ERR;
        } else {
            this.targetWarn(value);
        }
    }

    public String getTarget() {
        return this.target;
    }

    public final void setFollow(boolean newValue) {
        this.follow = newValue;
    }

    public final boolean getFollow() {
        return this.follow;
    }

    void targetWarn(String val) {
        LogLog.warn("[" + val + "] should be System.out or System.err.");
        LogLog.warn("Using previously set target, System.out by default.");
    }

    public void activateOptions() {
        if (this.follow) {
            if (this.target.equals(SYSTEM_ERR)) {
                this.setWriter(this.createWriter(new SystemErrStream()));
            } else {
                this.setWriter(this.createWriter(new SystemOutStream()));
            }
        } else if (this.target.equals(SYSTEM_ERR)) {
            this.setWriter(this.createWriter(System.err));
        } else {
            this.setWriter(this.createWriter(System.out));
        }
        super.activateOptions();
    }

    protected final void closeWriter() {
        if (this.follow) {
            super.closeWriter();
        }
    }

    private static class SystemOutStream
    extends OutputStream {
        public void close() {
        }

        public void flush() {
            System.out.flush();
        }

        public void write(byte[] b) throws IOException {
            System.out.write(b);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            System.out.write(b, off, len);
        }

        public void write(int b) throws IOException {
            System.out.write(b);
        }
    }

    private static class SystemErrStream
    extends OutputStream {
        public void close() {
        }

        public void flush() {
            System.err.flush();
        }

        public void write(byte[] b) throws IOException {
            System.err.write(b);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            System.err.write(b, off, len);
        }

        public void write(int b) throws IOException {
            System.err.write(b);
        }
    }
}

