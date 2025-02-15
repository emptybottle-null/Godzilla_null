/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Executor;
import org.sqlite.SQLiteCommitListener;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConnectionConfig;
import org.sqlite.SQLiteLimits;
import org.sqlite.SQLiteUpdateListener;
import org.sqlite.core.CoreDatabaseMetaData;
import org.sqlite.core.DB;
import org.sqlite.core.NativeDB;
import org.sqlite.jdbc4.JDBC4DatabaseMetaData;

public abstract class SQLiteConnection
implements Connection {
    private static final String RESOURCE_NAME_PREFIX = ":resource:";
    private final DB db;
    private CoreDatabaseMetaData meta = null;
    private final SQLiteConnectionConfig connectionConfig;

    public SQLiteConnection(DB db) {
        this.db = db;
        this.connectionConfig = db.getConfig().newConnectionConfig();
    }

    public SQLiteConnection(String url, String fileName) throws SQLException {
        this(url, fileName, new Properties());
    }

    public SQLiteConnection(String url, String fileName, Properties prop) throws SQLException {
        this.db = SQLiteConnection.open(url, fileName, prop);
        SQLiteConfig config = this.db.getConfig();
        this.connectionConfig = this.db.getConfig().newConnectionConfig();
        config.apply(this);
    }

    public SQLiteConnectionConfig getConnectionConfig() {
        return this.connectionConfig;
    }

    public CoreDatabaseMetaData getSQLiteDatabaseMetaData() throws SQLException {
        this.checkOpen();
        if (this.meta == null) {
            this.meta = new JDBC4DatabaseMetaData(this);
        }
        return this.meta;
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return this.getSQLiteDatabaseMetaData();
    }

    public String getUrl() {
        return this.db.getUrl();
    }

    @Override
    public void setSchema(String schema) throws SQLException {
    }

    @Override
    public String getSchema() throws SQLException {
        return null;
    }

    @Override
    public void abort(Executor executor) throws SQLException {
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return 0;
    }

    protected void checkCursor(int rst, int rsc, int rsh) throws SQLException {
        if (rst != 1003) {
            throw new SQLException("SQLite only supports TYPE_FORWARD_ONLY cursors");
        }
        if (rsc != 1007) {
            throw new SQLException("SQLite only supports CONCUR_READ_ONLY cursors");
        }
        if (rsh != 2) {
            throw new SQLException("SQLite only supports closing cursors at commit");
        }
    }

    protected void setTransactionMode(SQLiteConfig.TransactionMode mode) {
        this.connectionConfig.setTransactionMode(mode);
    }

    @Override
    public int getTransactionIsolation() {
        return this.connectionConfig.getTransactionIsolation();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        this.checkOpen();
        switch (level) {
            case 8: {
                this.getDatabase().exec("PRAGMA read_uncommitted = false;", this.getAutoCommit());
                break;
            }
            case 1: {
                this.getDatabase().exec("PRAGMA read_uncommitted = true;", this.getAutoCommit());
                break;
            }
            default: {
                throw new SQLException("SQLite supports only TRANSACTION_SERIALIZABLE and TRANSACTION_READ_UNCOMMITTED.");
            }
        }
        this.connectionConfig.setTransactionIsolation(level);
    }

    private static DB open(String url, String origFileName, Properties props) throws SQLException {
        Properties newProps = new Properties();
        newProps.putAll(props);
        String fileName = SQLiteConnection.extractPragmasFromFilename(url, origFileName, newProps);
        SQLiteConfig config = new SQLiteConfig(newProps);
        if (!(fileName.isEmpty() || ":memory:".equals(fileName) || fileName.startsWith("file:") || fileName.contains("mode=memory"))) {
            if (fileName.startsWith(RESOURCE_NAME_PREFIX)) {
                String resourceName = fileName.substring(RESOURCE_NAME_PREFIX.length());
                ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
                URL resourceAddr = contextCL.getResource(resourceName);
                if (resourceAddr == null) {
                    try {
                        resourceAddr = new URL(resourceName);
                    } catch (MalformedURLException e) {
                        throw new SQLException(String.format("resource %s not found: %s", resourceName, e));
                    }
                }
                try {
                    fileName = SQLiteConnection.extractResource(resourceAddr).getAbsolutePath();
                } catch (IOException e) {
                    throw new SQLException(String.format("failed to load %s: %s", resourceName, e));
                }
            }
            File file = new File(fileName).getAbsoluteFile();
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                for (File up = parent; up != null && !up.exists(); up = up.getParentFile()) {
                    parent = up;
                }
                throw new SQLException("path to '" + fileName + "': '" + parent + "' does not exist");
            }
            try {
                if (!file.exists() && file.createNewFile()) {
                    file.delete();
                }
            } catch (Exception e) {
                throw new SQLException("opening db: '" + fileName + "': " + e.getMessage());
            }
            fileName = file.getAbsolutePath();
        }
        NativeDB db = null;
        try {
            NativeDB.load();
            db = new NativeDB(url, fileName, config);
        } catch (Exception e) {
            SQLException err = new SQLException("Error opening connection");
            err.initCause(e);
            throw err;
        }
        db.open(fileName, config.getOpenModeFlags());
        return db;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static File extractResource(URL resourceAddr) throws IOException {
        if (resourceAddr.getProtocol().equals("file")) {
            try {
                return new File(resourceAddr.toURI());
            } catch (URISyntaxException e) {
                throw new IOException(e.getMessage());
            }
        }
        String tempFolder = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
        String dbFileName = String.format("sqlite-jdbc-tmp-%d.db", resourceAddr.hashCode());
        File dbFile = new File(tempFolder, dbFileName);
        if (dbFile.exists()) {
            long tmpFileLastModified;
            long resourceLastModified = resourceAddr.openConnection().getLastModified();
            if (resourceLastModified < (tmpFileLastModified = dbFile.lastModified())) {
                return dbFile;
            }
            boolean deletionSucceeded = dbFile.delete();
            if (!deletionSucceeded) {
                throw new IOException("failed to remove existing DB file: " + dbFile.getAbsolutePath());
            }
        }
        byte[] buffer = new byte[8192];
        FileOutputStream writer = new FileOutputStream(dbFile);
        InputStream reader = resourceAddr.openStream();
        try {
            int bytesRead = 0;
            while ((bytesRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, bytesRead);
            }
            File file = dbFile;
            return file;
        } finally {
            writer.close();
            reader.close();
        }
    }

    public DB getDatabase() {
        return this.db;
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        this.checkOpen();
        return this.connectionConfig.isAutoCommit();
    }

    @Override
    public void setAutoCommit(boolean ac) throws SQLException {
        this.checkOpen();
        if (this.connectionConfig.isAutoCommit() == ac) {
            return;
        }
        this.connectionConfig.setAutoCommit(ac);
        this.db.exec(this.connectionConfig.isAutoCommit() ? "commit;" : this.connectionConfig.transactionPrefix(), ac);
    }

    public int getBusyTimeout() {
        return this.db.getConfig().getBusyTimeout();
    }

    public void setBusyTimeout(int timeoutMillis) throws SQLException {
        this.db.getConfig().setBusyTimeout(timeoutMillis);
        this.db.busy_timeout(timeoutMillis);
    }

    public void setLimit(SQLiteLimits limit, int value) throws SQLException {
        this.db.limit(limit.getId(), value);
    }

    public void getLimit(SQLiteLimits limit) throws SQLException {
        this.db.limit(limit.getId(), -1);
    }

    @Override
    public boolean isClosed() throws SQLException {
        return this.db.isClosed();
    }

    @Override
    public void close() throws SQLException {
        if (this.isClosed()) {
            return;
        }
        if (this.meta != null) {
            this.meta.close();
        }
        this.db.close();
    }

    protected void checkOpen() throws SQLException {
        if (this.isClosed()) {
            throw new SQLException("database connection closed");
        }
    }

    public String libversion() throws SQLException {
        this.checkOpen();
        return this.db.libversion();
    }

    @Override
    public void commit() throws SQLException {
        this.checkOpen();
        if (this.connectionConfig.isAutoCommit()) {
            throw new SQLException("database in auto-commit mode");
        }
        this.db.exec("commit;", this.getAutoCommit());
        this.db.exec(this.connectionConfig.transactionPrefix(), this.getAutoCommit());
    }

    @Override
    public void rollback() throws SQLException {
        this.checkOpen();
        if (this.connectionConfig.isAutoCommit()) {
            throw new SQLException("database in auto-commit mode");
        }
        this.db.exec("rollback;", this.getAutoCommit());
        this.db.exec(this.connectionConfig.transactionPrefix(), this.getAutoCommit());
    }

    public void addUpdateListener(SQLiteUpdateListener listener) {
        this.db.addUpdateListener(listener);
    }

    public void removeUpdateListener(SQLiteUpdateListener listener) {
        this.db.removeUpdateListener(listener);
    }

    public void addCommitListener(SQLiteCommitListener listener) {
        this.db.addCommitListener(listener);
    }

    public void removeCommitListener(SQLiteCommitListener listener) {
        this.db.removeCommitListener(listener);
    }

    protected static String extractPragmasFromFilename(String url, String filename, Properties prop) throws SQLException {
        int parameterDelimiter = filename.indexOf(63);
        if (parameterDelimiter == -1) {
            return filename;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(filename.substring(0, parameterDelimiter));
        int nonPragmaCount = 0;
        String[] parameters = filename.substring(parameterDelimiter + 1).split("&");
        for (int i = 0; i < parameters.length; ++i) {
            String parameter = parameters[parameters.length - 1 - i].trim();
            if (parameter.isEmpty()) continue;
            String[] kvp = parameter.split("=");
            String key = kvp[0].trim().toLowerCase();
            if (SQLiteConfig.pragmaSet.contains(key)) {
                if (kvp.length == 1) {
                    throw new SQLException(String.format("Please specify a value for PRAGMA %s in URL %s", key, url));
                }
                String value = kvp[1].trim();
                if (value.isEmpty() || prop.containsKey(key)) continue;
                prop.setProperty(key, value);
                continue;
            }
            sb.append(nonPragmaCount == 0 ? (char)'?' : '&');
            sb.append(parameter);
            ++nonPragmaCount;
        }
        String newFilename = sb.toString();
        return newFilename;
    }
}

