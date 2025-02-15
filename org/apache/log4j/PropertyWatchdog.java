/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.FileWatchdog;

class PropertyWatchdog
extends FileWatchdog {
    PropertyWatchdog(String filename) {
        super(filename);
    }

    public void doOnChange() {
        new PropertyConfigurator().doConfigure(this.filename, LogManager.getLoggerRepository());
    }
}

