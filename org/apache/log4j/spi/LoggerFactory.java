/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import org.apache.log4j.Logger;

public interface LoggerFactory {
    public Logger makeNewLoggerInstance(String var1);
}

