/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite;

import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.sqlite.JDBC;
import org.sqlite.SQLiteConnection;
import org.sqlite.SQLiteConnectionConfig;
import org.sqlite.SQLiteLimits;
import org.sqlite.SQLiteOpenMode;

public class SQLiteConfig {
    public static final String DEFAULT_DATE_STRING_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private final Properties pragmaTable;
    private int openModeFlag = 0;
    private final int busyTimeout;
    private final SQLiteConnectionConfig defaultConnectionConfig;
    private static final String[] OnOff = new String[]{"true", "false"};
    static final Set<String> pragmaSet = new TreeSet<String>();

    public SQLiteConfig() {
        this(new Properties());
    }

    public SQLiteConfig(Properties prop) {
        this.pragmaTable = prop;
        String openMode = this.pragmaTable.getProperty(Pragma.OPEN_MODE.pragmaName);
        if (openMode != null) {
            this.openModeFlag = Integer.parseInt(openMode);
        } else {
            this.setOpenMode(SQLiteOpenMode.READWRITE);
            this.setOpenMode(SQLiteOpenMode.CREATE);
        }
        this.setSharedCache(Boolean.parseBoolean(this.pragmaTable.getProperty(Pragma.SHARED_CACHE.pragmaName, "false")));
        this.setOpenMode(SQLiteOpenMode.OPEN_URI);
        this.busyTimeout = Integer.parseInt(this.pragmaTable.getProperty(Pragma.BUSY_TIMEOUT.pragmaName, "3000"));
        this.defaultConnectionConfig = SQLiteConnectionConfig.fromPragmaTable(this.pragmaTable);
    }

    public SQLiteConnectionConfig newConnectionConfig() {
        return this.defaultConnectionConfig.copyConfig();
    }

    public Connection createConnection(String url) throws SQLException {
        return JDBC.createConnection(url, this.toProperties());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void apply(Connection conn) throws SQLException {
        HashSet<String> pragmaParams = new HashSet<String>();
        for (Pragma each : Pragma.values()) {
            pragmaParams.add(each.pragmaName);
        }
        if (conn instanceof SQLiteConnection) {
            if (this.pragmaTable.containsKey(Pragma.LIMIT_ATTACHED.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_ATTACHED, this.getInteger(Pragma.LIMIT_ATTACHED, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_COLUMN.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_COLUMN, this.getInteger(Pragma.LIMIT_COLUMN, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_COMPOUND_SELECT.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_COMPOUND_SELECT, this.getInteger(Pragma.LIMIT_COMPOUND_SELECT, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_EXPR_DEPTH.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_EXPR_DEPTH, this.getInteger(Pragma.LIMIT_EXPR_DEPTH, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_FUNCTION_ARG.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_FUNCTION_ARG, this.getInteger(Pragma.LIMIT_FUNCTION_ARG, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_LENGTH.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_LENGTH, this.getInteger(Pragma.LIMIT_LENGTH, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_LIKE_PATTERN_LENGTH.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_LIKE_PATTERN_LENGTH, this.getInteger(Pragma.LIMIT_LIKE_PATTERN_LENGTH, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_SQL_LENGTH.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_SQL_LENGTH, this.getInteger(Pragma.LIMIT_SQL_LENGTH, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_TRIGGER_DEPTH.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_TRIGGER_DEPTH, this.getInteger(Pragma.LIMIT_TRIGGER_DEPTH, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_VARIABLE_NUMBER.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_VARIABLE_NUMBER, this.getInteger(Pragma.LIMIT_VARIABLE_NUMBER, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_VDBE_OP.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_VDBE_OP, this.getInteger(Pragma.LIMIT_VDBE_OP, "-1"));
            }
            if (this.pragmaTable.containsKey(Pragma.LIMIT_WORKER_THREADS.pragmaName)) {
                ((SQLiteConnection)conn).setLimit(SQLiteLimits.SQLITE_LIMIT_WORKER_THREADS, this.getInteger(Pragma.LIMIT_WORKER_THREADS, "-1"));
            }
        }
        pragmaParams.remove(Pragma.OPEN_MODE.pragmaName);
        pragmaParams.remove(Pragma.SHARED_CACHE.pragmaName);
        pragmaParams.remove(Pragma.LOAD_EXTENSION.pragmaName);
        pragmaParams.remove(Pragma.DATE_PRECISION.pragmaName);
        pragmaParams.remove(Pragma.DATE_CLASS.pragmaName);
        pragmaParams.remove(Pragma.DATE_STRING_FORMAT.pragmaName);
        pragmaParams.remove(Pragma.PASSWORD.pragmaName);
        pragmaParams.remove(Pragma.HEXKEY_MODE.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_ATTACHED.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_COLUMN.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_COMPOUND_SELECT.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_EXPR_DEPTH.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_FUNCTION_ARG.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_LENGTH.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_LIKE_PATTERN_LENGTH.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_SQL_LENGTH.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_TRIGGER_DEPTH.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_VARIABLE_NUMBER.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_VDBE_OP.pragmaName);
        pragmaParams.remove(Pragma.LIMIT_WORKER_THREADS.pragmaName);
        try (Statement stat = conn.createStatement();){
            String password;
            if (this.pragmaTable.containsKey(Pragma.PASSWORD.pragmaName) && (password = this.pragmaTable.getProperty(Pragma.PASSWORD.pragmaName)) != null && !password.isEmpty()) {
                String hexkeyMode = this.pragmaTable.getProperty(Pragma.HEXKEY_MODE.pragmaName);
                String passwordPragma = HexKeyMode.SSE.name().equalsIgnoreCase(hexkeyMode) ? "pragma hexkey = '%s'" : (HexKeyMode.SQLCIPHER.name().equalsIgnoreCase(hexkeyMode) ? "pragma key = \"x'%s'\"" : "pragma key = '%s'");
                stat.execute(String.format(passwordPragma, password.replace("'", "''")));
                stat.execute("select 1 from sqlite_master");
            }
            for (Object each : this.pragmaTable.keySet()) {
                String value;
                String key = each.toString();
                if (!pragmaParams.contains(key) || (value = this.pragmaTable.getProperty(key)) == null) continue;
                stat.execute(String.format("pragma %s=%s", key, value));
            }
        }
    }

    private void set(Pragma pragma, boolean flag) {
        this.setPragma(pragma, Boolean.toString(flag));
    }

    private void set(Pragma pragma, int num) {
        this.setPragma(pragma, Integer.toString(num));
    }

    private boolean getBoolean(Pragma pragma, String defaultValue) {
        return Boolean.parseBoolean(this.pragmaTable.getProperty(pragma.pragmaName, defaultValue));
    }

    private int getInteger(Pragma pragma, String defaultValue) {
        return Integer.parseInt(this.pragmaTable.getProperty(pragma.pragmaName, defaultValue));
    }

    public boolean isEnabledSharedCache() {
        return this.getBoolean(Pragma.SHARED_CACHE, "false");
    }

    public boolean isEnabledLoadExtension() {
        return this.getBoolean(Pragma.LOAD_EXTENSION, "false");
    }

    public int getOpenModeFlags() {
        return this.openModeFlag;
    }

    public void setPragma(Pragma pragma, String value) {
        this.pragmaTable.put(pragma.pragmaName, value);
    }

    public Properties toProperties() {
        this.pragmaTable.setProperty(Pragma.OPEN_MODE.pragmaName, Integer.toString(this.openModeFlag));
        this.pragmaTable.setProperty(Pragma.TRANSACTION_MODE.pragmaName, this.defaultConnectionConfig.getTransactionMode().getValue());
        this.pragmaTable.setProperty(Pragma.DATE_CLASS.pragmaName, this.defaultConnectionConfig.getDateClass().getValue());
        this.pragmaTable.setProperty(Pragma.DATE_PRECISION.pragmaName, this.defaultConnectionConfig.getDatePrecision().getValue());
        this.pragmaTable.setProperty(Pragma.DATE_STRING_FORMAT.pragmaName, this.defaultConnectionConfig.getDateStringFormat());
        return this.pragmaTable;
    }

    static DriverPropertyInfo[] getDriverPropertyInfo() {
        Pragma[] pragma = Pragma.values();
        DriverPropertyInfo[] result = new DriverPropertyInfo[pragma.length];
        int index = 0;
        for (Pragma p : Pragma.values()) {
            DriverPropertyInfo di = new DriverPropertyInfo(p.pragmaName, null);
            di.choices = p.choices;
            di.description = p.description;
            di.required = false;
            result[index++] = di;
        }
        return result;
    }

    public void setOpenMode(SQLiteOpenMode mode) {
        this.openModeFlag |= mode.flag;
    }

    public void resetOpenMode(SQLiteOpenMode mode) {
        this.openModeFlag &= ~mode.flag;
    }

    public void setSharedCache(boolean enable) {
        this.set(Pragma.SHARED_CACHE, enable);
    }

    public void enableLoadExtension(boolean enable) {
        this.set(Pragma.LOAD_EXTENSION, enable);
    }

    public void setReadOnly(boolean readOnly) {
        if (readOnly) {
            this.setOpenMode(SQLiteOpenMode.READONLY);
            this.resetOpenMode(SQLiteOpenMode.CREATE);
            this.resetOpenMode(SQLiteOpenMode.READWRITE);
        } else {
            this.setOpenMode(SQLiteOpenMode.READWRITE);
            this.setOpenMode(SQLiteOpenMode.CREATE);
            this.resetOpenMode(SQLiteOpenMode.READONLY);
        }
    }

    public void setCacheSize(int numberOfPages) {
        this.set(Pragma.CACHE_SIZE, numberOfPages);
    }

    public void enableCaseSensitiveLike(boolean enable) {
        this.set(Pragma.CASE_SENSITIVE_LIKE, enable);
    }

    @Deprecated
    public void enableCountChanges(boolean enable) {
        this.set(Pragma.COUNT_CHANGES, enable);
    }

    public void setDefaultCacheSize(int numberOfPages) {
        this.set(Pragma.DEFAULT_CACHE_SIZE, numberOfPages);
    }

    public void deferForeignKeys(boolean enable) {
        this.set(Pragma.DEFER_FOREIGN_KEYS, enable);
    }

    @Deprecated
    public void enableEmptyResultCallBacks(boolean enable) {
        this.set(Pragma.EMPTY_RESULT_CALLBACKS, enable);
    }

    private static String[] toStringArray(PragmaValue[] list) {
        String[] result = new String[list.length];
        for (int i = 0; i < list.length; ++i) {
            result[i] = list[i].getValue();
        }
        return result;
    }

    public void setEncoding(Encoding encoding) {
        this.setPragma(Pragma.ENCODING, encoding.typeName);
    }

    public void enforceForeignKeys(boolean enforce) {
        this.set(Pragma.FOREIGN_KEYS, enforce);
    }

    @Deprecated
    public void enableFullColumnNames(boolean enable) {
        this.set(Pragma.FULL_COLUMN_NAMES, enable);
    }

    public void enableFullSync(boolean enable) {
        this.set(Pragma.FULL_SYNC, enable);
    }

    public void incrementalVacuum(int numberOfPagesToBeRemoved) {
        this.set(Pragma.INCREMENTAL_VACUUM, numberOfPagesToBeRemoved);
    }

    public void setJournalMode(JournalMode mode) {
        this.setPragma(Pragma.JOURNAL_MODE, mode.name());
    }

    public void setJounalSizeLimit(int limit) {
        this.set(Pragma.JOURNAL_SIZE_LIMIT, limit);
    }

    public void useLegacyFileFormat(boolean use) {
        this.set(Pragma.LEGACY_FILE_FORMAT, use);
    }

    public void setLockingMode(LockingMode mode) {
        this.setPragma(Pragma.LOCKING_MODE, mode.name());
    }

    public void setPageSize(int numBytes) {
        this.set(Pragma.PAGE_SIZE, numBytes);
    }

    public void setMaxPageCount(int numPages) {
        this.set(Pragma.MAX_PAGE_COUNT, numPages);
    }

    public void setReadUncommited(boolean useReadUncommitedIsolationMode) {
        this.set(Pragma.READ_UNCOMMITTED, useReadUncommitedIsolationMode);
    }

    public void enableRecursiveTriggers(boolean enable) {
        this.set(Pragma.RECURSIVE_TRIGGERS, enable);
    }

    public void enableReverseUnorderedSelects(boolean enable) {
        this.set(Pragma.REVERSE_UNORDERED_SELECTS, enable);
    }

    public void enableShortColumnNames(boolean enable) {
        this.set(Pragma.SHORT_COLUMN_NAMES, enable);
    }

    public void setSynchronous(SynchronousMode mode) {
        this.setPragma(Pragma.SYNCHRONOUS, mode.name());
    }

    public void setHexKeyMode(HexKeyMode mode) {
        this.setPragma(Pragma.HEXKEY_MODE, mode.name());
    }

    public void setTempStore(TempStore storeType) {
        this.setPragma(Pragma.TEMP_STORE, storeType.name());
    }

    public void setTempStoreDirectory(String directoryName) {
        this.setPragma(Pragma.TEMP_STORE_DIRECTORY, String.format("'%s'", directoryName));
    }

    public void setUserVersion(int version) {
        this.set(Pragma.USER_VERSION, version);
    }

    public void setApplicationId(int id) {
        this.set(Pragma.APPLICATION_ID, id);
    }

    public void setTransactionMode(TransactionMode transactionMode) {
        this.defaultConnectionConfig.setTransactionMode(transactionMode);
    }

    public void setTransactionMode(String transactionMode) {
        this.setTransactionMode(TransactionMode.getMode(transactionMode));
    }

    public TransactionMode getTransactionMode() {
        return this.defaultConnectionConfig.getTransactionMode();
    }

    public void setDatePrecision(String datePrecision) throws SQLException {
        this.defaultConnectionConfig.setDatePrecision(DatePrecision.getPrecision(datePrecision));
    }

    public void setDateClass(String dateClass) {
        this.defaultConnectionConfig.setDateClass(DateClass.getDateClass(dateClass));
    }

    public void setDateStringFormat(String dateStringFormat) {
        this.defaultConnectionConfig.setDateStringFormat(dateStringFormat);
    }

    public void setBusyTimeout(int milliseconds) {
        this.setPragma(Pragma.BUSY_TIMEOUT, Integer.toString(milliseconds));
    }

    public int getBusyTimeout() {
        return this.busyTimeout;
    }

    static /* synthetic */ String[] access$000() {
        return OnOff;
    }

    static /* synthetic */ String[] access$100(PragmaValue[] x0) {
        return SQLiteConfig.toStringArray(x0);
    }

    static {
        for (Pragma pragma : Pragma.values()) {
            pragmaSet.add(pragma.pragmaName);
        }
    }

    public static enum DateClass implements PragmaValue
    {
        INTEGER,
        TEXT,
        REAL;


        @Override
        public String getValue() {
            return this.name();
        }

        public static DateClass getDateClass(String dateClass) {
            return DateClass.valueOf(dateClass.toUpperCase());
        }
    }

    public static enum DatePrecision implements PragmaValue
    {
        SECONDS,
        MILLISECONDS;


        @Override
        public String getValue() {
            return this.name();
        }

        public static DatePrecision getPrecision(String precision) {
            return DatePrecision.valueOf(precision.toUpperCase());
        }
    }

    public static enum TransactionMode implements PragmaValue
    {
        DEFFERED,
        DEFERRED,
        IMMEDIATE,
        EXCLUSIVE;


        @Override
        public String getValue() {
            return this.name();
        }

        public static TransactionMode getMode(String mode) {
            if ("DEFFERED".equalsIgnoreCase(mode)) {
                return DEFERRED;
            }
            return TransactionMode.valueOf(mode.toUpperCase());
        }
    }

    public static enum HexKeyMode implements PragmaValue
    {
        NONE,
        SSE,
        SQLCIPHER;


        @Override
        public String getValue() {
            return this.name();
        }
    }

    public static enum TempStore implements PragmaValue
    {
        DEFAULT,
        FILE,
        MEMORY;


        @Override
        public String getValue() {
            return this.name();
        }
    }

    public static enum SynchronousMode implements PragmaValue
    {
        OFF,
        NORMAL,
        FULL;


        @Override
        public String getValue() {
            return this.name();
        }
    }

    public static enum LockingMode implements PragmaValue
    {
        NORMAL,
        EXCLUSIVE;


        @Override
        public String getValue() {
            return this.name();
        }
    }

    public static enum JournalMode implements PragmaValue
    {
        DELETE,
        TRUNCATE,
        PERSIST,
        MEMORY,
        WAL,
        OFF;


        @Override
        public String getValue() {
            return this.name();
        }
    }

    public static enum Encoding implements PragmaValue
    {
        UTF8("'UTF-8'"),
        UTF16("'UTF-16'"),
        UTF16_LITTLE_ENDIAN("'UTF-16le'"),
        UTF16_BIG_ENDIAN("'UTF-16be'"),
        UTF_8(UTF8),
        UTF_16(UTF16),
        UTF_16LE(UTF16_LITTLE_ENDIAN),
        UTF_16BE(UTF16_BIG_ENDIAN);

        public final String typeName;

        private Encoding(String typeName) {
            this.typeName = typeName;
        }

        private Encoding(Encoding encoding) {
            this.typeName = encoding.getValue();
        }

        @Override
        public String getValue() {
            return this.typeName;
        }

        public static Encoding getEncoding(String value) {
            return Encoding.valueOf(value.replaceAll("-", "_").toUpperCase());
        }
    }

    private static interface PragmaValue {
        public String getValue();
    }

    public static enum Pragma {
        OPEN_MODE("open_mode", "Database open-mode flag", null),
        SHARED_CACHE("shared_cache", "Enable SQLite Shared-Cache mode, native driver only", SQLiteConfig.access$000()),
        LOAD_EXTENSION("enable_load_extension", "Enable SQLite load_extention() function, native driver only", SQLiteConfig.access$000()),
        CACHE_SIZE("cache_size"),
        MMAP_SIZE("mmap_size"),
        CASE_SENSITIVE_LIKE("case_sensitive_like", SQLiteConfig.access$000()),
        COUNT_CHANGES("count_changes", SQLiteConfig.access$000()),
        DEFAULT_CACHE_SIZE("default_cache_size"),
        DEFER_FOREIGN_KEYS("defer_foreign_keys", SQLiteConfig.access$000()),
        EMPTY_RESULT_CALLBACKS("empty_result_callback", SQLiteConfig.access$000()),
        ENCODING("encoding", SQLiteConfig.access$100(Encoding.values())),
        FOREIGN_KEYS("foreign_keys", SQLiteConfig.access$000()),
        FULL_COLUMN_NAMES("full_column_names", SQLiteConfig.access$000()),
        FULL_SYNC("fullsync", SQLiteConfig.access$000()),
        INCREMENTAL_VACUUM("incremental_vacuum"),
        JOURNAL_MODE("journal_mode", SQLiteConfig.access$100(JournalMode.values())),
        JOURNAL_SIZE_LIMIT("journal_size_limit"),
        LEGACY_FILE_FORMAT("legacy_file_format", SQLiteConfig.access$000()),
        LOCKING_MODE("locking_mode", SQLiteConfig.access$100(LockingMode.values())),
        PAGE_SIZE("page_size"),
        MAX_PAGE_COUNT("max_page_count"),
        READ_UNCOMMITTED("read_uncommitted", SQLiteConfig.access$000()),
        RECURSIVE_TRIGGERS("recursive_triggers", SQLiteConfig.access$000()),
        REVERSE_UNORDERED_SELECTS("reverse_unordered_selects", SQLiteConfig.access$000()),
        SECURE_DELETE("secure_delete", new String[]{"true", "false", "fast"}),
        SHORT_COLUMN_NAMES("short_column_names", SQLiteConfig.access$000()),
        SYNCHRONOUS("synchronous", SQLiteConfig.access$100(SynchronousMode.values())),
        TEMP_STORE("temp_store", SQLiteConfig.access$100(TempStore.values())),
        TEMP_STORE_DIRECTORY("temp_store_directory"),
        USER_VERSION("user_version"),
        APPLICATION_ID("application_id"),
        LIMIT_LENGTH("limit_length", "The maximum size of any string or BLOB or table row, in bytes.", null),
        LIMIT_SQL_LENGTH("limit_sql_length", "The maximum length of an SQL statement, in bytes.", null),
        LIMIT_COLUMN("limit_column", "The maximum number of columns in a table definition or in the result set of a SELECT or the maximum number of columns in an index or in an ORDER BY or GROUP BY clause.", null),
        LIMIT_EXPR_DEPTH("limit_expr_depth", "The maximum depth of the parse tree on any expression.", null),
        LIMIT_COMPOUND_SELECT("limit_compound_select", "The maximum number of terms in a compound SELECT statement.", null),
        LIMIT_VDBE_OP("limit_vdbe_op", "The maximum number of instructions in a virtual machine program used to implement an SQL statement. If sqlite3_prepare_v2() or the equivalent tries to allocate space for more than this many opcodes in a single prepared statement, an SQLITE_NOMEM error is returned.", null),
        LIMIT_FUNCTION_ARG("limit_function_arg", "The maximum number of arguments on a function.", null),
        LIMIT_ATTACHED("limit_attached", "The maximum number of attached databases.", null),
        LIMIT_LIKE_PATTERN_LENGTH("limit_like_pattern_length", "The maximum length of the pattern argument to the LIKE or GLOB operators.", null),
        LIMIT_VARIABLE_NUMBER("limit_variable_number", "The maximum index number of any parameter in an SQL statement.", null),
        LIMIT_TRIGGER_DEPTH("limit_trigger_depth", "The maximum depth of recursion for triggers.", null),
        LIMIT_WORKER_THREADS("limit_worker_threads", "The maximum number of auxiliary worker threads that a single prepared statement may start.", null),
        TRANSACTION_MODE("transaction_mode", SQLiteConfig.access$100(TransactionMode.values())),
        DATE_PRECISION("date_precision", "\"seconds\": Read and store integer dates as seconds from the Unix Epoch (SQLite standard).\n\"milliseconds\": (DEFAULT) Read and store integer dates as milliseconds from the Unix Epoch (Java standard).", SQLiteConfig.access$100(DatePrecision.values())),
        DATE_CLASS("date_class", "\"integer\": (Default) store dates as number of seconds or milliseconds from the Unix Epoch\n\"text\": store dates as a string of text\n\"real\": store dates as Julian Dates", SQLiteConfig.access$100(DateClass.values())),
        DATE_STRING_FORMAT("date_string_format", "Format to store and retrieve dates stored as text. Defaults to \"yyyy-MM-dd HH:mm:ss.SSS\"", null),
        BUSY_TIMEOUT("busy_timeout", null),
        HEXKEY_MODE("hexkey_mode", SQLiteConfig.access$100(HexKeyMode.values())),
        PASSWORD("password", null);

        public final String pragmaName;
        public final String[] choices;
        public final String description;

        private Pragma(String pragmaName) {
            this(pragmaName, null);
        }

        private Pragma(String pragmaName, String[] choices) {
            this(pragmaName, null, choices);
        }

        private Pragma(String pragmaName, String description, String[] choices) {
            this.pragmaName = pragmaName;
            this.description = description;
            this.choices = choices;
        }

        public final String getPragmaName() {
            return this.pragmaName;
        }
    }
}

