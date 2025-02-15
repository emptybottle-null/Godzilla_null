/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogger;

@Internal
public class NullLogger
implements POILogger {
    @Override
    public void initialize(String cat) {
    }

    @Override
    public void _log(int level, Object obj1) {
    }

    @Override
    public void _log(int level, Object obj1, Throwable exception) {
    }

    @Override
    public void log(int level, Object ... objs) {
    }

    @Override
    public boolean check(int level) {
        return false;
    }
}

