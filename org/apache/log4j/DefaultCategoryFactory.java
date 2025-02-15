/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

class DefaultCategoryFactory
implements LoggerFactory {
    DefaultCategoryFactory() {
    }

    public Logger makeNewLoggerInstance(String name) {
        return new Logger(name);
    }
}

