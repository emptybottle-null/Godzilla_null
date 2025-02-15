/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.layout;

import java.util.HashMap;
import java.util.WeakHashMap;

public final class LinkHandler {
    public static final int X = 0;
    public static final int Y = 1;
    public static final int WIDTH = 2;
    public static final int HEIGHT = 3;
    public static final int X2 = 4;
    public static final int Y2 = 5;
    private static final int VALUES = 0;
    private static final int VALUES_TEMP = 1;
    private static final WeakHashMap<Object, HashMap<String, int[]>[]> LAYOUTS = new WeakHashMap();

    private LinkHandler() {
    }

    public static synchronized Integer getValue(Object layout, String key, int type) {
        Integer ret = null;
        HashMap<String, int[]>[] layoutValues = LAYOUTS.get(layout);
        if (layoutValues != null) {
            int[] rect = layoutValues[1].get(key);
            ret = rect != null && rect[type] != -2147471302 ? Integer.valueOf(rect[type]) : ((rect = layoutValues[0].get(key)) != null && rect[type] != -2147471302 ? Integer.valueOf(rect[type]) : null);
        }
        return ret;
    }

    public static synchronized boolean setBounds(Object layout, String key, int x, int y, int width, int height) {
        return LinkHandler.setBounds(layout, key, x, y, width, height, false, false);
    }

    static synchronized boolean setBounds(Object layout, String key, int x, int y, int width, int height, boolean temporary, boolean incCur) {
        HashMap<String, int[]>[] layoutValues = LAYOUTS.get(layout);
        if (layoutValues != null) {
            HashMap<String, int[]> map = layoutValues[temporary ? 1 : 0];
            int[] old = map.get(key);
            if (old == null || old[0] != x || old[1] != y || old[2] != width || old[3] != height) {
                if (old == null || !incCur) {
                    map.put(key, new int[]{x, y, width, height, x + width, y + height});
                    return true;
                }
                boolean changed = false;
                if (x != -2147471302) {
                    if (old[0] == -2147471302 || x < old[0]) {
                        old[0] = x;
                        old[2] = old[4] - x;
                        changed = true;
                    }
                    if (width != -2147471302) {
                        int x2 = x + width;
                        if (old[4] == -2147471302 || x2 > old[4]) {
                            old[4] = x2;
                            old[2] = x2 - old[0];
                            changed = true;
                        }
                    }
                }
                if (y != -2147471302) {
                    if (old[1] == -2147471302 || y < old[1]) {
                        old[1] = y;
                        old[3] = old[5] - y;
                        changed = true;
                    }
                    if (height != -2147471302) {
                        int y2 = y + height;
                        if (old[5] == -2147471302 || y2 > old[5]) {
                            old[5] = y2;
                            old[3] = y2 - old[1];
                            changed = true;
                        }
                    }
                }
                return changed;
            }
            return false;
        }
        int[] bounds = new int[]{x, y, width, height, x + width, y + height};
        HashMap<String, int[]> values_temp = new HashMap<String, int[]>(4);
        if (temporary) {
            values_temp.put(key, bounds);
        }
        HashMap<String, int[]> values = new HashMap<String, int[]>(4);
        if (!temporary) {
            values.put(key, bounds);
        }
        LAYOUTS.put(layout, new HashMap[]{values, values_temp});
        return true;
    }

    public static synchronized void clearWeakReferencesNow() {
        LAYOUTS.clear();
    }

    public static synchronized boolean clearBounds(Object layout, String key) {
        HashMap<String, int[]>[] layoutValues = LAYOUTS.get(layout);
        if (layoutValues != null) {
            return layoutValues[0].remove(key) != null;
        }
        return false;
    }

    static synchronized void clearTemporaryBounds(Object layout) {
        HashMap<String, int[]>[] layoutValues = LAYOUTS.get(layout);
        if (layoutValues != null) {
            layoutValues[1].clear();
        }
    }
}

