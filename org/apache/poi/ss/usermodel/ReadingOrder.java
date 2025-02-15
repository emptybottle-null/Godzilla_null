/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum ReadingOrder {
    CONTEXT,
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT;


    public short getCode() {
        return (short)this.ordinal();
    }

    public static ReadingOrder forLong(long code) {
        if (code < 0L || code >= (long)ReadingOrder.values().length) {
            throw new IllegalArgumentException("Invalid ReadingOrder code: " + code);
        }
        return ReadingOrder.values()[(int)code];
    }
}

