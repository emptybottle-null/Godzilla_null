/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import java.util.Enumeration;
import org.apache.log4j.Appender;

public interface AppenderAttachable {
    public void addAppender(Appender var1);

    public Enumeration getAllAppenders();

    public Appender getAppender(String var1);

    public boolean isAttached(Appender var1);

    public void removeAllAppenders();

    public void removeAppender(Appender var1);

    public void removeAppender(String var1);
}

