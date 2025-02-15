/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

public enum RectAlign {
    TOP_LEFT("tl"),
    TOP("t"),
    TOP_RIGHT("tr"),
    LEFT("l"),
    CENTER("ctr"),
    RIGHT("r"),
    BOTTOM_LEFT("bl"),
    BOTTOM("b"),
    BOTTOM_RIGHT("br");

    private final String dir;

    private RectAlign(String dir) {
        this.dir = dir;
    }

    public String toString() {
        return this.dir;
    }
}

