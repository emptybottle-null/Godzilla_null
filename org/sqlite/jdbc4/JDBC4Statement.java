/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.jdbc4;

import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteConnection;
import org.sqlite.jdbc3.JDBC3Statement;

public class JDBC4Statement
extends JDBC3Statement
implements Statement {
    private boolean closed = false;
    boolean closeOnCompletion;

    public JDBC4Statement(SQLiteConnection conn) {
        super(conn);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws ClassCastException {
        return iface.cast(this);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return iface.isInstance(this);
    }

    @Override
    public void close() throws SQLException {
        super.close();
        this.closed = true;
    }

    @Override
    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        if (this.closed) {
            throw new SQLException("statement is closed");
        }
        this.closeOnCompletion = true;
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        if (this.closed) {
            throw new SQLException("statement is closed");
        }
        return this.closeOnCompletion;
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return false;
    }
}

