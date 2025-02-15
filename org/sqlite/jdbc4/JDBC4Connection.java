/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.jdbc4;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.util.Properties;
import org.sqlite.jdbc3.JDBC3Connection;
import org.sqlite.jdbc4.JDBC4PreparedStatement;
import org.sqlite.jdbc4.JDBC4Statement;

public class JDBC4Connection
extends JDBC3Connection {
    public JDBC4Connection(String url, String fileName, Properties prop) throws SQLException {
        super(url, fileName, prop);
    }

    @Override
    public Statement createStatement(int rst, int rsc, int rsh) throws SQLException {
        this.checkOpen();
        this.checkCursor(rst, rsc, rsh);
        return new JDBC4Statement(this);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int rst, int rsc, int rsh) throws SQLException {
        this.checkOpen();
        this.checkCursor(rst, rsc, rsh);
        return new JDBC4PreparedStatement(this, sql);
    }

    @Override
    public boolean isClosed() throws SQLException {
        return super.isClosed();
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
    public Clob createClob() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Blob createBlob() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public NClob createNClob() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isValid(int timeout) throws SQLException {
        if (this.isClosed()) {
            return false;
        }
        try (Statement statement = this.createStatement();){
            boolean bl = statement.execute("select 1");
            return bl;
        }
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return null;
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return null;
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return null;
    }
}

