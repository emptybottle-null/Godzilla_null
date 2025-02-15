/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import org.apache.xmlbeans.impl.common.XBLogger;

public class NullLogger
extends XBLogger {
    @Override
    public void initialize(String cat) {
    }

    @Override
    protected void _log(int level, Object obj1) {
    }

    @Override
    protected void _log(int level, Object obj1, Throwable exception) {
    }

    @Override
    public void log(int level, Object ... objs) {
    }

    @Override
    public boolean check(int level) {
        return false;
    }
}

