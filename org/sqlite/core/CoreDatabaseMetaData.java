/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.core;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import org.sqlite.SQLiteConnection;

public abstract class CoreDatabaseMetaData
implements DatabaseMetaData {
    protected SQLiteConnection conn;
    protected PreparedStatement getTables = null;
    protected PreparedStatement getTableTypes = null;
    protected PreparedStatement getTypeInfo = null;
    protected PreparedStatement getCatalogs = null;
    protected PreparedStatement getSchemas = null;
    protected PreparedStatement getUDTs = null;
    protected PreparedStatement getColumnsTblName = null;
    protected PreparedStatement getSuperTypes = null;
    protected PreparedStatement getSuperTables = null;
    protected PreparedStatement getTablePrivileges = null;
    protected PreparedStatement getIndexInfo = null;
    protected PreparedStatement getProcedures = null;
    protected PreparedStatement getProcedureColumns = null;
    protected PreparedStatement getAttributes = null;
    protected PreparedStatement getBestRowIdentifier = null;
    protected PreparedStatement getVersionColumns = null;
    protected PreparedStatement getColumnPrivileges = null;
    protected PreparedStatement getGeneratedKeys = null;
    protected static final Pattern PK_UNNAMED_PATTERN = Pattern.compile(".*\\sPRIMARY\\s+KEY\\s+\\((.*?,+.*?)\\).*", 34);
    protected static final Pattern PK_NAMED_PATTERN = Pattern.compile(".*\\sCONSTRAINT\\s+(.*?)\\s+PRIMARY\\s+KEY\\s+\\((.*?)\\).*", 34);

    protected CoreDatabaseMetaData(SQLiteConnection conn) {
        this.conn = conn;
    }

    public abstract ResultSet getGeneratedKeys() throws SQLException;

    protected void checkOpen() throws SQLException {
        if (this.conn == null) {
            throw new SQLException("connection closed");
        }
    }

    public synchronized void close() throws SQLException {
        if (this.conn == null) {
            return;
        }
        try {
            if (this.getTables != null) {
                this.getTables.close();
            }
            if (this.getTableTypes != null) {
                this.getTableTypes.close();
            }
            if (this.getTypeInfo != null) {
                this.getTypeInfo.close();
            }
            if (this.getCatalogs != null) {
                this.getCatalogs.close();
            }
            if (this.getSchemas != null) {
                this.getSchemas.close();
            }
            if (this.getUDTs != null) {
                this.getUDTs.close();
            }
            if (this.getColumnsTblName != null) {
                this.getColumnsTblName.close();
            }
            if (this.getSuperTypes != null) {
                this.getSuperTypes.close();
            }
            if (this.getSuperTables != null) {
                this.getSuperTables.close();
            }
            if (this.getTablePrivileges != null) {
                this.getTablePrivileges.close();
            }
            if (this.getIndexInfo != null) {
                this.getIndexInfo.close();
            }
            if (this.getProcedures != null) {
                this.getProcedures.close();
            }
            if (this.getProcedureColumns != null) {
                this.getProcedureColumns.close();
            }
            if (this.getAttributes != null) {
                this.getAttributes.close();
            }
            if (this.getBestRowIdentifier != null) {
                this.getBestRowIdentifier.close();
            }
            if (this.getVersionColumns != null) {
                this.getVersionColumns.close();
            }
            if (this.getColumnPrivileges != null) {
                this.getColumnPrivileges.close();
            }
            if (this.getGeneratedKeys != null) {
                this.getGeneratedKeys.close();
            }
            this.getTables = null;
            this.getTableTypes = null;
            this.getTypeInfo = null;
            this.getCatalogs = null;
            this.getSchemas = null;
            this.getUDTs = null;
            this.getColumnsTblName = null;
            this.getSuperTypes = null;
            this.getSuperTables = null;
            this.getTablePrivileges = null;
            this.getIndexInfo = null;
            this.getProcedures = null;
            this.getProcedureColumns = null;
            this.getAttributes = null;
            this.getBestRowIdentifier = null;
            this.getVersionColumns = null;
            this.getColumnPrivileges = null;
            this.getGeneratedKeys = null;
        } finally {
            this.conn = null;
        }
    }

    protected static String quote(String tableName) {
        if (tableName == null) {
            return "null";
        }
        return String.format("'%s'", tableName);
    }

    protected String escape(String val) {
        int len = val.length();
        StringBuilder buf = new StringBuilder(len);
        for (int i = 0; i < len; ++i) {
            if (val.charAt(i) == '\'') {
                buf.append('\'');
            }
            buf.append(val.charAt(i));
        }
        return buf.toString();
    }

    protected void finalize() throws Throwable {
        this.close();
    }
}

