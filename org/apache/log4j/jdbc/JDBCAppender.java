/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class JDBCAppender
extends AppenderSkeleton
implements Appender {
    protected String databaseURL = "jdbc:odbc:myDB";
    protected String databaseUser = "me";
    protected String databasePassword = "mypassword";
    protected Connection connection = null;
    protected String sqlStatement = "";
    protected int bufferSize = 1;
    protected ArrayList buffer = new ArrayList(this.bufferSize);
    protected ArrayList removes = new ArrayList(this.bufferSize);
    private boolean locationInfo = false;

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public void setLocationInfo(boolean flag) {
        this.locationInfo = flag;
    }

    public void append(LoggingEvent event) {
        event.getNDC();
        event.getThreadName();
        event.getMDCCopy();
        if (this.locationInfo) {
            event.getLocationInformation();
        }
        event.getRenderedMessage();
        event.getThrowableStrRep();
        this.buffer.add(event);
        if (this.buffer.size() >= this.bufferSize) {
            this.flushBuffer();
        }
    }

    protected String getLogStatement(LoggingEvent event) {
        return this.getLayout().format(event);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void execute(String sql) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        try {
            con = this.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            this.closeConnection(con);
        }
    }

    protected void closeConnection(Connection con) {
    }

    protected Connection getConnection() throws SQLException {
        if (!DriverManager.getDrivers().hasMoreElements()) {
            this.setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
        }
        if (this.connection == null) {
            this.connection = DriverManager.getConnection(this.databaseURL, this.databaseUser, this.databasePassword);
        }
        return this.connection;
    }

    public void close() {
        this.flushBuffer();
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            this.errorHandler.error("Error closing connection", e, 0);
        }
        this.closed = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void flushBuffer() {
        this.removes.ensureCapacity(this.buffer.size());
        Iterator i = this.buffer.iterator();
        while (i.hasNext()) {
            LoggingEvent logEvent = (LoggingEvent)i.next();
            try {
                String sql = this.getLogStatement(logEvent);
                this.execute(sql);
            } catch (SQLException e) {
                this.errorHandler.error("Failed to excute sql", e, 2);
            } finally {
                this.removes.add(logEvent);
            }
        }
        this.buffer.removeAll(this.removes);
        this.removes.clear();
    }

    public void finalize() {
        this.close();
    }

    public boolean requiresLayout() {
        return true;
    }

    public void setSql(String s) {
        this.sqlStatement = s;
        if (this.getLayout() == null) {
            this.setLayout(new PatternLayout(s));
        } else {
            ((PatternLayout)this.getLayout()).setConversionPattern(s);
        }
    }

    public String getSql() {
        return this.sqlStatement;
    }

    public void setUser(String user) {
        this.databaseUser = user;
    }

    public void setURL(String url) {
        this.databaseURL = url;
    }

    public void setPassword(String password) {
        this.databasePassword = password;
    }

    public void setBufferSize(int newBufferSize) {
        this.bufferSize = newBufferSize;
        this.buffer.ensureCapacity(this.bufferSize);
        this.removes.ensureCapacity(this.bufferSize);
    }

    public String getUser() {
        return this.databaseUser;
    }

    public String getURL() {
        return this.databaseURL;
    }

    public String getPassword() {
        return this.databasePassword;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setDriver(String driverClass) {
        try {
            Class.forName(driverClass);
        } catch (Exception e) {
            this.errorHandler.error("Failed to load driver", e, 0);
        }
    }
}

