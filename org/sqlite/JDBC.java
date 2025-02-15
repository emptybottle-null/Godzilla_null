/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConnection;
import org.sqlite.SQLiteJDBCLoader;
import org.sqlite.jdbc4.JDBC4Connection;

public class JDBC
implements Driver {
    public static final String PREFIX = "jdbc:sqlite:";

    @Override
    public int getMajorVersion() {
        return SQLiteJDBCLoader.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return SQLiteJDBCLoader.getMinorVersion();
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public boolean acceptsURL(String url) {
        return JDBC.isValidURL(url);
    }

    public static boolean isValidURL(String url) {
        return url != null && url.toLowerCase().startsWith(PREFIX);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return SQLiteConfig.getDriverPropertyInfo();
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return JDBC.createConnection(url, info);
    }

    static String extractAddress(String url) {
        return url.substring(PREFIX.length());
    }

    public static SQLiteConnection createConnection(String url, Properties prop) throws SQLException {
        if (!JDBC.isValidURL(url)) {
            return null;
        }
        url = url.trim();
        return new JDBC4Connection(url, JDBC.extractAddress(url), prop);
    }

    static {
        try {
            DriverManager.registerDriver(new JDBC());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

