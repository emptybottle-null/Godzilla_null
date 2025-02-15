/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.jdbc3;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import org.sqlite.SQLiteConnection;
import org.sqlite.SQLiteOpenMode;
import org.sqlite.jdbc3.JDBC3Savepoint;

public abstract class JDBC3Connection
extends SQLiteConnection {
    private final AtomicInteger savePoint = new AtomicInteger(0);
    private Map<String, Class<?>> typeMap;

    protected JDBC3Connection(String url, String fileName, Properties prop) throws SQLException {
        super(url, fileName, prop);
    }

    @Override
    public String getCatalog() throws SQLException {
        this.checkOpen();
        return null;
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        this.checkOpen();
    }

    @Override
    public int getHoldability() throws SQLException {
        this.checkOpen();
        return 2;
    }

    @Override
    public void setHoldability(int h) throws SQLException {
        this.checkOpen();
        if (h != 2) {
            throw new SQLException("SQLite only supports CLOSE_CURSORS_AT_COMMIT");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        JDBC3Connection jDBC3Connection = this;
        synchronized (jDBC3Connection) {
            if (this.typeMap == null) {
                this.typeMap = new HashMap();
            }
            return this.typeMap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setTypeMap(Map map) throws SQLException {
        JDBC3Connection jDBC3Connection = this;
        synchronized (jDBC3Connection) {
            this.typeMap = map;
        }
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return (this.getDatabase().getConfig().getOpenModeFlags() & SQLiteOpenMode.READONLY.flag) != 0;
    }

    @Override
    public void setReadOnly(boolean ro) throws SQLException {
        if (ro != this.isReadOnly()) {
            throw new SQLException("Cannot change read-only flag after establishing a connection. Use SQLiteConfig#setReadOnly and SQLiteConfig.createConnection().");
        }
    }

    @Override
    public String nativeSQL(String sql) {
        return sql;
    }

    @Override
    public void clearWarnings() throws SQLException {
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return this.createStatement(1003, 1007, 2);
    }

    @Override
    public Statement createStatement(int rsType, int rsConcurr) throws SQLException {
        return this.createStatement(rsType, rsConcurr, 2);
    }

    @Override
    public abstract Statement createStatement(int var1, int var2, int var3) throws SQLException;

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return this.prepareCall(sql, 1003, 1007, 2);
    }

    @Override
    public CallableStatement prepareCall(String sql, int rst, int rsc) throws SQLException {
        return this.prepareCall(sql, rst, rsc, 2);
    }

    @Override
    public CallableStatement prepareCall(String sql, int rst, int rsc, int rsh) throws SQLException {
        throw new SQLException("SQLite does not support Stored Procedures");
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.prepareStatement(sql, 1003, 1007);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoC) throws SQLException {
        return this.prepareStatement(sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] colInds) throws SQLException {
        return this.prepareStatement(sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] colNames) throws SQLException {
        return this.prepareStatement(sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int rst, int rsc) throws SQLException {
        return this.prepareStatement(sql, rst, rsc, 2);
    }

    @Override
    public abstract PreparedStatement prepareStatement(String var1, int var2, int var3, int var4) throws SQLException;

    @Override
    public Savepoint setSavepoint() throws SQLException {
        this.checkOpen();
        if (this.getAutoCommit()) {
            this.getConnectionConfig().setAutoCommit(false);
        }
        JDBC3Savepoint sp = new JDBC3Savepoint(this.savePoint.incrementAndGet());
        this.getDatabase().exec(String.format("SAVEPOINT %s", sp.getSavepointName()), false);
        return sp;
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        this.checkOpen();
        if (this.getAutoCommit()) {
            this.getConnectionConfig().setAutoCommit(false);
        }
        JDBC3Savepoint sp = new JDBC3Savepoint(this.savePoint.incrementAndGet(), name);
        this.getDatabase().exec(String.format("SAVEPOINT %s", sp.getSavepointName()), false);
        return sp;
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        this.checkOpen();
        if (this.getAutoCommit()) {
            throw new SQLException("database in auto-commit mode");
        }
        this.getDatabase().exec(String.format("RELEASE SAVEPOINT %s", savepoint.getSavepointName()), false);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        this.checkOpen();
        if (this.getAutoCommit()) {
            throw new SQLException("database in auto-commit mode");
        }
        this.getDatabase().exec(String.format("ROLLBACK TO SAVEPOINT %s", savepoint.getSavepointName()), this.getAutoCommit());
    }

    @Override
    public Struct createStruct(String t, Object[] attr) throws SQLException {
        throw new SQLException("unsupported by SQLite");
    }
}

