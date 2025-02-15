/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite;

import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;
import org.sqlite.SQLiteConfig;
import org.sqlite.date.FastDateFormat;

public class SQLiteConnectionConfig
implements Cloneable {
    private SQLiteConfig.DateClass dateClass = SQLiteConfig.DateClass.INTEGER;
    private SQLiteConfig.DatePrecision datePrecision = SQLiteConfig.DatePrecision.MILLISECONDS;
    private String dateStringFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    private FastDateFormat dateFormat = FastDateFormat.getInstance(this.dateStringFormat);
    private int transactionIsolation = 8;
    private SQLiteConfig.TransactionMode transactionMode = SQLiteConfig.TransactionMode.DEFERRED;
    private boolean autoCommit = true;
    private static final Map<SQLiteConfig.TransactionMode, String> beginCommandMap = new EnumMap<SQLiteConfig.TransactionMode, String>(SQLiteConfig.TransactionMode.class);

    public static SQLiteConnectionConfig fromPragmaTable(Properties pragmaTable) {
        return new SQLiteConnectionConfig(SQLiteConfig.DateClass.getDateClass(pragmaTable.getProperty(SQLiteConfig.Pragma.DATE_CLASS.pragmaName, SQLiteConfig.DateClass.INTEGER.name())), SQLiteConfig.DatePrecision.getPrecision(pragmaTable.getProperty(SQLiteConfig.Pragma.DATE_PRECISION.pragmaName, SQLiteConfig.DatePrecision.MILLISECONDS.name())), pragmaTable.getProperty(SQLiteConfig.Pragma.DATE_STRING_FORMAT.pragmaName, "yyyy-MM-dd HH:mm:ss.SSS"), 8, SQLiteConfig.TransactionMode.getMode(pragmaTable.getProperty(SQLiteConfig.Pragma.TRANSACTION_MODE.pragmaName, SQLiteConfig.TransactionMode.DEFERRED.name())), true);
    }

    public SQLiteConnectionConfig(SQLiteConfig.DateClass dateClass, SQLiteConfig.DatePrecision datePrecision, String dateStringFormat, int transactionIsolation, SQLiteConfig.TransactionMode transactionMode, boolean autoCommit) {
        this.setDateClass(dateClass);
        this.setDatePrecision(datePrecision);
        this.setDateStringFormat(dateStringFormat);
        this.setTransactionIsolation(transactionIsolation);
        this.setTransactionMode(transactionMode);
        this.setAutoCommit(autoCommit);
    }

    public SQLiteConnectionConfig copyConfig() {
        return new SQLiteConnectionConfig(this.dateClass, this.datePrecision, this.dateStringFormat, this.transactionIsolation, this.transactionMode, this.autoCommit);
    }

    public long getDateMultiplier() {
        return this.datePrecision == SQLiteConfig.DatePrecision.MILLISECONDS ? 1L : 1000L;
    }

    public SQLiteConfig.DateClass getDateClass() {
        return this.dateClass;
    }

    public void setDateClass(SQLiteConfig.DateClass dateClass) {
        this.dateClass = dateClass;
    }

    public SQLiteConfig.DatePrecision getDatePrecision() {
        return this.datePrecision;
    }

    public void setDatePrecision(SQLiteConfig.DatePrecision datePrecision) {
        this.datePrecision = datePrecision;
    }

    public String getDateStringFormat() {
        return this.dateStringFormat;
    }

    public void setDateStringFormat(String dateStringFormat) {
        this.dateStringFormat = dateStringFormat;
        this.dateFormat = FastDateFormat.getInstance(dateStringFormat);
    }

    public FastDateFormat getDateFormat() {
        return this.dateFormat;
    }

    public boolean isAutoCommit() {
        return this.autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public int getTransactionIsolation() {
        return this.transactionIsolation;
    }

    public void setTransactionIsolation(int transactionIsolation) {
        this.transactionIsolation = transactionIsolation;
    }

    public SQLiteConfig.TransactionMode getTransactionMode() {
        return this.transactionMode;
    }

    public void setTransactionMode(SQLiteConfig.TransactionMode transactionMode) {
        if (transactionMode == SQLiteConfig.TransactionMode.DEFFERED) {
            transactionMode = SQLiteConfig.TransactionMode.DEFERRED;
        }
        this.transactionMode = transactionMode;
    }

    String transactionPrefix() {
        return beginCommandMap.get(this.transactionMode);
    }

    static {
        beginCommandMap.put(SQLiteConfig.TransactionMode.DEFERRED, "begin;");
        beginCommandMap.put(SQLiteConfig.TransactionMode.IMMEDIATE, "begin immediate;");
        beginCommandMap.put(SQLiteConfig.TransactionMode.EXCLUSIVE, "begin exclusive;");
    }
}

