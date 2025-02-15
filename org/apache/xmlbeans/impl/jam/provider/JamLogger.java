/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.provider;

public interface JamLogger {
    public void setVerbose(Class var1);

    public boolean isVerbose(Object var1);

    public boolean isVerbose(Class var1);

    public void setShowWarnings(boolean var1);

    public void verbose(String var1, Object var2);

    public void verbose(Throwable var1, Object var2);

    public void verbose(String var1);

    public void verbose(Throwable var1);

    public void warning(Throwable var1);

    public void warning(String var1);

    public void error(Throwable var1);

    public void error(String var1);

    public boolean isVerbose();
}

