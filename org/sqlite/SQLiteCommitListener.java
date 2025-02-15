/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite;

public interface SQLiteCommitListener {
    public void onCommit();

    public void onRollback();
}

