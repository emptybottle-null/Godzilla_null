/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.core;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import org.sqlite.SQLiteConnection;
import org.sqlite.SQLiteConnectionConfig;
import org.sqlite.core.DB;
import org.sqlite.date.FastDateFormat;
import org.sqlite.jdbc4.JDBC4Statement;

public abstract class CorePreparedStatement
extends JDBC4Statement {
    protected int columnCount;
    protected int paramCount;
    protected int batchQueryCount;

    protected CorePreparedStatement(SQLiteConnection conn, String sql) throws SQLException {
        super(conn);
        this.sql = sql;
        DB db = conn.getDatabase();
        db.prepare(this);
        this.rs.colsMeta = db.column_names(this.pointer);
        this.columnCount = db.column_count(this.pointer);
        this.paramCount = db.bind_parameter_count(this.pointer);
        this.batchQueryCount = 0;
        this.batch = null;
        this.batchPos = 0;
    }

    @Override
    public int[] executeBatch() throws SQLException {
        if (this.batchQueryCount == 0) {
            return new int[0];
        }
        try {
            int[] nArray = this.conn.getDatabase().executeBatch(this.pointer, this.batchQueryCount, this.batch, this.conn.getAutoCommit());
            return nArray;
        } finally {
            this.clearBatch();
        }
    }

    @Override
    public void clearBatch() throws SQLException {
        super.clearBatch();
        this.batchQueryCount = 0;
    }

    @Override
    public int getUpdateCount() throws SQLException {
        if (this.pointer == 0L || this.resultsWaiting || this.rs.isOpen()) {
            return -1;
        }
        return this.conn.getDatabase().changes();
    }

    protected void batch(int pos, Object value) throws SQLException {
        this.checkOpen();
        if (this.batch == null) {
            this.batch = new Object[this.paramCount];
        }
        this.batch[this.batchPos + pos - 1] = value;
    }

    protected void setDateByMilliseconds(int pos, Long value, Calendar calendar) throws SQLException {
        SQLiteConnectionConfig config = this.conn.getConnectionConfig();
        switch (config.getDateClass()) {
            case TEXT: {
                this.batch(pos, FastDateFormat.getInstance(config.getDateStringFormat(), calendar.getTimeZone()).format(new Date(value)));
                break;
            }
            case REAL: {
                this.batch(pos, new Double((double)value.longValue() / 8.64E7 + 2440587.5));
                break;
            }
            default: {
                this.batch(pos, new Long(value / config.getDateMultiplier()));
            }
        }
    }
}

