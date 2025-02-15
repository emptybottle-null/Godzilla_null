/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite;

public interface SQLiteUpdateListener {
    public void onUpdate(Type var1, String var2, String var3, long var4);

    public static enum Type {
        INSERT,
        DELETE,
        UPDATE;

    }
}

