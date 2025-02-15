/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.core;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.sqlite.BusyHandler;
import org.sqlite.Function;
import org.sqlite.ProgressHandler;
import org.sqlite.SQLiteCommitListener;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteErrorCode;
import org.sqlite.SQLiteException;
import org.sqlite.SQLiteUpdateListener;
import org.sqlite.core.Codes;
import org.sqlite.core.CoreStatement;

public abstract class DB
implements Codes {
    private final String url;
    private final String fileName;
    private final SQLiteConfig config;
    private final AtomicBoolean closed = new AtomicBoolean(true);
    long begin = 0L;
    long commit = 0L;
    private final Map<Long, CoreStatement> stmts = new HashMap<Long, CoreStatement>();
    private final Set<SQLiteUpdateListener> updateListeners = new HashSet<SQLiteUpdateListener>();
    private final Set<SQLiteCommitListener> commitListeners = new HashSet<SQLiteCommitListener>();

    public DB(String url, String fileName, SQLiteConfig config) throws SQLException {
        this.url = url;
        this.fileName = fileName;
        this.config = config;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isClosed() {
        return this.closed.get();
    }

    public SQLiteConfig getConfig() {
        return this.config;
    }

    public abstract void interrupt() throws SQLException;

    public abstract void busy_timeout(int var1) throws SQLException;

    public abstract void busy_handler(BusyHandler var1) throws SQLException;

    abstract String errmsg() throws SQLException;

    public abstract String libversion() throws SQLException;

    public abstract int changes() throws SQLException;

    public abstract int total_changes() throws SQLException;

    public abstract int shared_cache(boolean var1) throws SQLException;

    public abstract int enable_load_extension(boolean var1) throws SQLException;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final synchronized void exec(String sql, boolean autoCommit) throws SQLException {
        long pointer = 0L;
        try {
            pointer = this.prepare(sql);
            int rc = this.step(pointer);
            switch (rc) {
                case 101: {
                    this.ensureAutoCommit(autoCommit);
                    return;
                }
                case 100: {
                    return;
                }
            }
            this.throwex(rc);
        } finally {
            this.finalize(pointer);
        }
    }

    public final synchronized void open(String file, int openFlags) throws SQLException {
        this._open(file, openFlags);
        this.closed.set(false);
        if (this.fileName.startsWith("file:") && !this.fileName.contains("cache=")) {
            this.shared_cache(this.config.isEnabledSharedCache());
        }
        this.enable_load_extension(this.config.isEnabledLoadExtension());
        this.busy_timeout(this.config.getBusyTimeout());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final synchronized void close() throws SQLException {
        Map<Long, CoreStatement> map = this.stmts;
        synchronized (map) {
            Iterator<Map.Entry<Long, CoreStatement>> i = this.stmts.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry<Long, CoreStatement> entry = i.next();
                CoreStatement stmt = entry.getValue();
                this.finalize(entry.getKey());
                if (stmt != null) {
                    stmt.pointer = 0L;
                }
                i.remove();
            }
        }
        this.free_functions();
        if (this.begin != 0L) {
            this.finalize(this.begin);
            this.begin = 0L;
        }
        if (this.commit != 0L) {
            this.finalize(this.commit);
            this.commit = 0L;
        }
        this.closed.set(true);
        this._close();
    }

    public final synchronized void prepare(CoreStatement stmt) throws SQLException {
        if (stmt.sql == null) {
            throw new NullPointerException();
        }
        if (stmt.pointer != 0L) {
            this.finalize(stmt);
        }
        stmt.pointer = this.prepare(stmt.sql);
        this.stmts.put(new Long(stmt.pointer), stmt);
    }

    public final synchronized int finalize(CoreStatement stmt) throws SQLException {
        if (stmt.pointer == 0L) {
            return 0;
        }
        int rc = 1;
        try {
            rc = this.finalize(stmt.pointer);
        } finally {
            this.stmts.remove(new Long(stmt.pointer));
            stmt.pointer = 0L;
        }
        return rc;
    }

    protected abstract void _open(String var1, int var2) throws SQLException;

    protected abstract void _close() throws SQLException;

    public abstract int _exec(String var1) throws SQLException;

    protected abstract long prepare(String var1) throws SQLException;

    protected abstract int finalize(long var1) throws SQLException;

    public abstract int step(long var1) throws SQLException;

    public abstract int reset(long var1) throws SQLException;

    public abstract int clear_bindings(long var1) throws SQLException;

    abstract int bind_parameter_count(long var1) throws SQLException;

    public abstract int column_count(long var1) throws SQLException;

    public abstract int column_type(long var1, int var3) throws SQLException;

    public abstract String column_decltype(long var1, int var3) throws SQLException;

    public abstract String column_table_name(long var1, int var3) throws SQLException;

    public abstract String column_name(long var1, int var3) throws SQLException;

    public abstract String column_text(long var1, int var3) throws SQLException;

    public abstract byte[] column_blob(long var1, int var3) throws SQLException;

    public abstract double column_double(long var1, int var3) throws SQLException;

    public abstract long column_long(long var1, int var3) throws SQLException;

    public abstract int column_int(long var1, int var3) throws SQLException;

    abstract int bind_null(long var1, int var3) throws SQLException;

    abstract int bind_int(long var1, int var3, int var4) throws SQLException;

    abstract int bind_long(long var1, int var3, long var4) throws SQLException;

    abstract int bind_double(long var1, int var3, double var4) throws SQLException;

    abstract int bind_text(long var1, int var3, String var4) throws SQLException;

    abstract int bind_blob(long var1, int var3, byte[] var4) throws SQLException;

    public abstract void result_null(long var1) throws SQLException;

    public abstract void result_text(long var1, String var3) throws SQLException;

    public abstract void result_blob(long var1, byte[] var3) throws SQLException;

    public abstract void result_double(long var1, double var3) throws SQLException;

    public abstract void result_long(long var1, long var3) throws SQLException;

    public abstract void result_int(long var1, int var3) throws SQLException;

    public abstract void result_error(long var1, String var3) throws SQLException;

    public abstract String value_text(Function var1, int var2) throws SQLException;

    public abstract byte[] value_blob(Function var1, int var2) throws SQLException;

    public abstract double value_double(Function var1, int var2) throws SQLException;

    public abstract long value_long(Function var1, int var2) throws SQLException;

    public abstract int value_int(Function var1, int var2) throws SQLException;

    public abstract int value_type(Function var1, int var2) throws SQLException;

    public abstract int create_function(String var1, Function var2, int var3, int var4) throws SQLException;

    public abstract int destroy_function(String var1, int var2) throws SQLException;

    abstract void free_functions() throws SQLException;

    public abstract int backup(String var1, String var2, ProgressObserver var3) throws SQLException;

    public abstract int restore(String var1, String var2, ProgressObserver var3) throws SQLException;

    public abstract int limit(int var1, int var2) throws SQLException;

    public abstract void register_progress_handler(int var1, ProgressHandler var2) throws SQLException;

    public abstract void clear_progress_handler() throws SQLException;

    abstract boolean[][] column_metadata(long var1) throws SQLException;

    public final synchronized String[] column_names(long stmt) throws SQLException {
        String[] names = new String[this.column_count(stmt)];
        for (int i = 0; i < names.length; ++i) {
            names[i] = this.column_name(stmt, i);
        }
        return names;
    }

    final synchronized int sqlbind(long stmt, int pos, Object v) throws SQLException {
        ++pos;
        if (v == null) {
            return this.bind_null(stmt, pos);
        }
        if (v instanceof Integer) {
            return this.bind_int(stmt, pos, (Integer)v);
        }
        if (v instanceof Short) {
            return this.bind_int(stmt, pos, ((Short)v).intValue());
        }
        if (v instanceof Long) {
            return this.bind_long(stmt, pos, (Long)v);
        }
        if (v instanceof Float) {
            return this.bind_double(stmt, pos, ((Float)v).doubleValue());
        }
        if (v instanceof Double) {
            return this.bind_double(stmt, pos, (Double)v);
        }
        if (v instanceof String) {
            return this.bind_text(stmt, pos, (String)v);
        }
        if (v instanceof byte[]) {
            return this.bind_blob(stmt, pos, (byte[])v);
        }
        throw new SQLException("unexpected param type: " + v.getClass());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final synchronized int[] executeBatch(long stmt, int count, Object[] vals, boolean autoCommit) throws SQLException {
        if (count < 1) {
            throw new SQLException("count (" + count + ") < 1");
        }
        int params = this.bind_parameter_count(stmt);
        int[] changes = new int[count];
        try {
            for (int i = 0; i < count; ++i) {
                int rc;
                this.reset(stmt);
                for (int j = 0; j < params; ++j) {
                    rc = this.sqlbind(stmt, j, vals[i * params + j]);
                    if (rc == 0) continue;
                    this.throwex(rc);
                }
                rc = this.step(stmt);
                if (rc != 101) {
                    this.reset(stmt);
                    if (rc == 100) {
                        throw new BatchUpdateException("batch entry " + i + ": query returns results", changes);
                    }
                    this.throwex(rc);
                }
                changes[i] = this.changes();
            }
        } finally {
            this.ensureAutoCommit(autoCommit);
        }
        this.reset(stmt);
        return changes;
    }

    public final synchronized boolean execute(CoreStatement stmt, Object[] vals) throws SQLException {
        if (vals != null) {
            int params = this.bind_parameter_count(stmt.pointer);
            if (params > vals.length) {
                throw new SQLException("assertion failure: param count (" + params + ") > value count (" + vals.length + ")");
            }
            for (int i = 0; i < params; ++i) {
                int rc = this.sqlbind(stmt.pointer, i, vals[i]);
                if (rc == 0) continue;
                this.throwex(rc);
            }
        }
        int statusCode = this.step(stmt.pointer);
        switch (statusCode & 0xFF) {
            case 101: {
                this.reset(stmt.pointer);
                this.ensureAutoCommit(stmt.conn.getAutoCommit());
                return false;
            }
            case 100: {
                return true;
            }
            case 5: 
            case 6: 
            case 19: 
            case 21: {
                throw this.newSQLException(statusCode);
            }
        }
        this.finalize(stmt);
        throw this.newSQLException(statusCode);
    }

    final synchronized boolean execute(String sql, boolean autoCommit) throws SQLException {
        int statusCode = this._exec(sql);
        switch (statusCode) {
            case 0: {
                return false;
            }
            case 101: {
                this.ensureAutoCommit(autoCommit);
                return false;
            }
            case 100: {
                return true;
            }
        }
        throw this.newSQLException(statusCode);
    }

    public final synchronized int executeUpdate(CoreStatement stmt, Object[] vals) throws SQLException {
        try {
            if (this.execute(stmt, vals)) {
                throw new SQLException("query returns results");
            }
        } finally {
            if (stmt.pointer != 0L) {
                this.reset(stmt.pointer);
            }
        }
        return this.changes();
    }

    abstract void set_commit_listener(boolean var1);

    abstract void set_update_listener(boolean var1);

    public synchronized void addUpdateListener(SQLiteUpdateListener listener) {
        if (this.updateListeners.add(listener) && this.updateListeners.size() == 1) {
            this.set_update_listener(true);
        }
    }

    public synchronized void addCommitListener(SQLiteCommitListener listener) {
        if (this.commitListeners.add(listener) && this.commitListeners.size() == 1) {
            this.set_commit_listener(true);
        }
    }

    public synchronized void removeUpdateListener(SQLiteUpdateListener listener) {
        if (this.updateListeners.remove(listener) && this.updateListeners.isEmpty()) {
            this.set_update_listener(false);
        }
    }

    public synchronized void removeCommitListener(SQLiteCommitListener listener) {
        if (this.commitListeners.remove(listener) && this.commitListeners.isEmpty()) {
            this.set_commit_listener(false);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void onUpdate(int type, String database, String table, long rowId) {
        HashSet<SQLiteUpdateListener> listeners;
        DB dB = this;
        synchronized (dB) {
            listeners = new HashSet<SQLiteUpdateListener>(this.updateListeners);
        }
        for (SQLiteUpdateListener listener : listeners) {
            SQLiteUpdateListener.Type operationType;
            switch (type) {
                case 18: {
                    operationType = SQLiteUpdateListener.Type.INSERT;
                    break;
                }
                case 9: {
                    operationType = SQLiteUpdateListener.Type.DELETE;
                    break;
                }
                case 23: {
                    operationType = SQLiteUpdateListener.Type.UPDATE;
                    break;
                }
                default: {
                    throw new AssertionError((Object)("Unknown type: " + type));
                }
            }
            listener.onUpdate(operationType, database, table, rowId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void onCommit(boolean commit) {
        HashSet<SQLiteCommitListener> listeners;
        DB dB = this;
        synchronized (dB) {
            listeners = new HashSet<SQLiteCommitListener>(this.commitListeners);
        }
        for (SQLiteCommitListener listener : listeners) {
            if (commit) {
                listener.onCommit();
                continue;
            }
            listener.onRollback();
        }
    }

    final void throwex() throws SQLException {
        throw new SQLException(this.errmsg());
    }

    public final void throwex(int errorCode) throws SQLException {
        throw this.newSQLException(errorCode);
    }

    static final void throwex(int errorCode, String errorMessage) throws SQLiteException {
        throw DB.newSQLException(errorCode, errorMessage);
    }

    public static SQLiteException newSQLException(int errorCode, String errorMessage) {
        SQLiteErrorCode code = SQLiteErrorCode.getErrorCode(errorCode);
        SQLiteException e = new SQLiteException(String.format("%s (%s)", new Object[]{code, errorMessage}), code);
        return e;
    }

    private SQLiteException newSQLException(int errorCode) throws SQLException {
        return DB.newSQLException(errorCode, this.errmsg());
    }

    final void ensureAutoCommit(boolean autoCommit) throws SQLException {
        if (!autoCommit) {
            return;
        }
        if (this.begin == 0L) {
            this.begin = this.prepare("begin;");
        }
        if (this.commit == 0L) {
            this.commit = this.prepare("commit;");
        }
        try {
            if (this.step(this.begin) != 101) {
                return;
            }
            int rc = this.step(this.commit);
            if (rc != 101) {
                this.reset(this.commit);
                this.throwex(rc);
            }
        } finally {
            this.reset(this.begin);
            this.reset(this.commit);
        }
    }

    public static interface ProgressObserver {
        public void progress(int var1, int var2);
    }
}

