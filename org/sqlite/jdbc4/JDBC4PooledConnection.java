/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.jdbc4;

import javax.sql.PooledConnection;
import javax.sql.StatementEventListener;

public abstract class JDBC4PooledConnection
implements PooledConnection {
    @Override
    public void addStatementEventListener(StatementEventListener listener) {
    }

    @Override
    public void removeStatementEventListener(StatementEventListener listener) {
    }
}

