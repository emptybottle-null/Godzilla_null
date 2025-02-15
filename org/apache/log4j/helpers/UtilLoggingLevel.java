/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.helpers;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;

public class UtilLoggingLevel
extends Level {
    private static final long serialVersionUID = 909301162611820211L;
    public static final int SEVERE_INT = 22000;
    public static final int WARNING_INT = 21000;
    public static final int CONFIG_INT = 14000;
    public static final int FINE_INT = 13000;
    public static final int FINER_INT = 12000;
    public static final int FINEST_INT = 11000;
    public static final int UNKNOWN_INT = 10000;
    public static final UtilLoggingLevel SEVERE = new UtilLoggingLevel(22000, "SEVERE", 0);
    public static final UtilLoggingLevel WARNING = new UtilLoggingLevel(21000, "WARNING", 4);
    public static final UtilLoggingLevel INFO = new UtilLoggingLevel(20000, "INFO", 5);
    public static final UtilLoggingLevel CONFIG = new UtilLoggingLevel(14000, "CONFIG", 6);
    public static final UtilLoggingLevel FINE = new UtilLoggingLevel(13000, "FINE", 7);
    public static final UtilLoggingLevel FINER = new UtilLoggingLevel(12000, "FINER", 8);
    public static final UtilLoggingLevel FINEST = new UtilLoggingLevel(11000, "FINEST", 9);

    protected UtilLoggingLevel(int level, String levelStr, int syslogEquivalent) {
        super(level, levelStr, syslogEquivalent);
    }

    public static UtilLoggingLevel toLevel(int val, UtilLoggingLevel defaultLevel) {
        switch (val) {
            case 22000: {
                return SEVERE;
            }
            case 21000: {
                return WARNING;
            }
            case 20000: {
                return INFO;
            }
            case 14000: {
                return CONFIG;
            }
            case 13000: {
                return FINE;
            }
            case 12000: {
                return FINER;
            }
            case 11000: {
                return FINEST;
            }
        }
        return defaultLevel;
    }

    public static Level toLevel(int val) {
        return UtilLoggingLevel.toLevel(val, FINEST);
    }

    public static List getAllPossibleLevels() {
        ArrayList<UtilLoggingLevel> list = new ArrayList<UtilLoggingLevel>();
        list.add(FINE);
        list.add(FINER);
        list.add(FINEST);
        list.add(INFO);
        list.add(CONFIG);
        list.add(WARNING);
        list.add(SEVERE);
        return list;
    }

    public static Level toLevel(String s) {
        return UtilLoggingLevel.toLevel(s, Level.DEBUG);
    }

    public static Level toLevel(String sArg, Level defaultLevel) {
        if (sArg == null) {
            return defaultLevel;
        }
        String s = sArg.toUpperCase();
        if (s.equals("SEVERE")) {
            return SEVERE;
        }
        if (s.equals("WARNING")) {
            return WARNING;
        }
        if (s.equals("INFO")) {
            return INFO;
        }
        if (s.equals("CONFI")) {
            return CONFIG;
        }
        if (s.equals("FINE")) {
            return FINE;
        }
        if (s.equals("FINER")) {
            return FINER;
        }
        if (s.equals("FINEST")) {
            return FINEST;
        }
        return defaultLevel;
    }
}

