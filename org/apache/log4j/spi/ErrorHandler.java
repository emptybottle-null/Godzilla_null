/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public interface ErrorHandler
extends OptionHandler {
    public void setLogger(Logger var1);

    public void error(String var1, Exception var2, int var3);

    public void error(String var1);

    public void error(String var1, Exception var2, int var3, LoggingEvent var4);

    public void setAppender(Appender var1);

    public void setBackupAppender(Appender var1);
}

