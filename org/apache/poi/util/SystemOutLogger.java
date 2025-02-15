/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import org.apache.poi.util.POILogger;
import org.apache.poi.util.SuppressForbidden;

public class SystemOutLogger
implements POILogger {
    private static final String LEVEL_STRINGS_SHORT = "?D?I?W?E?F?";
    private String _cat;

    @Override
    public void initialize(String cat) {
        this._cat = cat;
    }

    @Override
    public void _log(int level, Object obj1) {
        this._log(level, obj1, null);
    }

    @Override
    @SuppressForbidden(value="uses printStackTrace")
    public void _log(int level, Object obj1, Throwable exception) {
        if (!this.check(level)) {
            return;
        }
        System.out.println("[" + this._cat + "]" + LEVEL_STRINGS_SHORT.charAt(Math.min(LEVEL_STRINGS_SHORT.length() - 1, level)) + " " + obj1);
        if (exception != null) {
            exception.printStackTrace(System.out);
        }
    }

    @Override
    public boolean check(int level) {
        int currentLevel;
        try {
            currentLevel = Integer.parseInt(System.getProperty("poi.log.level", "5"));
        } catch (SecurityException e) {
            currentLevel = 1;
        }
        return level >= currentLevel;
    }
}

