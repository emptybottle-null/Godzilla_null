/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteConnection;
import org.sqlite.SQLiteConnectionConfig;
import org.sqlite.core.Codes;
import org.sqlite.core.CoreResultSet;
import org.sqlite.core.DB;
import org.sqlite.jdbc4.JDBC4ResultSet;

public abstract class CoreStatement
implements Codes {
    public final SQLiteConnection conn;
    protected final CoreResultSet rs;
    public long pointer;
    protected String sql = null;
    protected int batchPos;
    protected Object[] batch = null;
    protected boolean resultsWaiting = false;

    protected CoreStatement(SQLiteConnection c) {
        this.conn = c;
        this.rs = new JDBC4ResultSet(this);
    }

    public DB getDatbase() {
        return this.conn.getDatabase();
    }

    public SQLiteConnectionConfig getConnectionConfig() {
        return this.conn.getConnectionConfig();
    }

    protected final void checkOpen() throws SQLException {
        if (this.pointer == 0L) {
            throw new SQLException("statement is not executing");
        }
    }

    boolean isOpen() throws SQLException {
        return this.pointer != 0L;
    }

    protected boolean exec() throws SQLException {
        if (this.sql == null) {
            throw new SQLException("SQLiteJDBC internal error: sql==null");
        }
        if (this.rs.isOpen()) {
            throw new SQLException("SQLite JDBC internal error: rs.isOpen() on exec.");
        }
        boolean success = false;
        boolean rc = false;
        try {
            rc = this.conn.getDatabase().execute(this, null);
            success = true;
        } finally {
            this.resultsWaiting = rc;
            if (!success) {
                this.conn.getDatabase().finalize(this);
            }
        }
        return this.conn.getDatabase().column_count(this.pointer) != 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected boolean exec(String sql) throws SQLException {
        if (sql == null) {
            throw new SQLException("SQLiteJDBC internal error: sql==null");
        }
        if (this.rs.isOpen()) {
            throw new SQLException("SQLite JDBC internal error: rs.isOpen() on exec.");
        }
        boolean rc = false;
        boolean success = false;
        try {
            rc = this.conn.getDatabase().execute(sql, this.conn.getAutoCommit());
            success = true;
        } finally {
            this.resultsWaiting = rc;
            if (!success) {
                this.conn.getDatabase().finalize(this);
            }
        }
        return this.conn.getDatabase().column_count(this.pointer) != 0;
    }

    protected void internalClose() throws SQLException {
        if (this.pointer == 0L) {
            return;
        }
        if (this.conn.isClosed()) {
            throw DB.newSQLException(1, "Connection is closed");
        }
        this.rs.close();
        this.batch = null;
        this.batchPos = 0;
        int resp = this.conn.getDatabase().finalize(this);
        if (resp != 0 && resp != 21) {
            this.conn.getDatabase().throwex(resp);
        }
    }

    public abstract ResultSet executeQuery(String var1, boolean var2) throws SQLException;
}

