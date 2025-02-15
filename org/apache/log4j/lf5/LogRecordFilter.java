/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5;

import org.apache.log4j.lf5.LogRecord;

public interface LogRecordFilter {
    public boolean passes(LogRecord var1);
}

