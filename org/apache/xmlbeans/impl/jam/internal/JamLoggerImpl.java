/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;

public class JamLoggerImpl
implements JamLogger {
    private boolean mShowWarnings = true;
    private Set mVerboseClasses = null;
    private PrintWriter mOut = new PrintWriter(System.out, true);

    protected void setOut(PrintWriter out) {
        this.mOut = out;
    }

    @Override
    public boolean isVerbose(Object o) {
        if (this.mVerboseClasses == null) {
            return false;
        }
        for (Class c : this.mVerboseClasses) {
            if (!c.isAssignableFrom(o.getClass())) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean isVerbose(Class aClass) {
        if (this.mVerboseClasses == null) {
            return false;
        }
        for (Class c : this.mVerboseClasses) {
            if (!c.isAssignableFrom(aClass)) continue;
            return true;
        }
        return false;
    }

    @Override
    public void setVerbose(Class c) {
        if (c == null) {
            throw new IllegalArgumentException();
        }
        if (this.mVerboseClasses == null) {
            this.mVerboseClasses = new HashSet();
        }
        this.mVerboseClasses.add(c);
    }

    @Override
    public void setShowWarnings(boolean b) {
        this.mShowWarnings = b;
    }

    @Override
    public void verbose(String msg, Object o) {
        if (this.isVerbose(o)) {
            this.verbose(msg);
        }
    }

    @Override
    public void verbose(Throwable t, Object o) {
        if (this.isVerbose(o)) {
            this.verbose(t);
        }
    }

    @Override
    public void verbose(String msg) {
        this.printVerbosePrefix();
        this.mOut.println(msg);
    }

    @Override
    public void verbose(Throwable t) {
        this.printVerbosePrefix();
        this.mOut.println();
        t.printStackTrace(this.mOut);
    }

    @Override
    public void warning(Throwable t) {
        if (this.mShowWarnings) {
            this.mOut.println("[JAM] Warning: unexpected exception thrown: ");
            t.printStackTrace();
        }
    }

    @Override
    public void warning(String w) {
        if (this.mShowWarnings) {
            this.mOut.print("[JAM] Warning: ");
            this.mOut.println(w);
        }
    }

    @Override
    public void error(Throwable t) {
        this.mOut.println("[JAM] Error: unexpected exception thrown: ");
        t.printStackTrace(this.mOut);
    }

    @Override
    public void error(String msg) {
        this.mOut.print("[JAM] Error: ");
        this.mOut.println(msg);
    }

    public void setVerbose(boolean v) {
        this.setVerbose(Object.class);
    }

    @Override
    public boolean isVerbose() {
        return this.mVerboseClasses != null;
    }

    private void printVerbosePrefix() {
        StackTraceElement[] st = new Exception().getStackTrace();
        this.mOut.println("[JAM] Verbose: ");
        this.mOut.print('(');
        this.mOut.print(JamLoggerImpl.shortName(st[2].getClassName()));
        this.mOut.print('.');
        this.mOut.print(st[2].getMethodName());
        this.mOut.print(':');
        this.mOut.print(st[2].getLineNumber());
        this.mOut.print(")  ");
    }

    private static String shortName(String className) {
        int index = className.lastIndexOf(46);
        if (index != -1) {
            className = className.substring(index + 1, className.length());
        }
        return className;
    }
}

