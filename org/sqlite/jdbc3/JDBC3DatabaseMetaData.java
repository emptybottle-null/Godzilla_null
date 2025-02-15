/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.jdbc3;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.sqlite.SQLiteConnection;
import org.sqlite.core.CoreDatabaseMetaData;
import org.sqlite.core.CoreStatement;
import org.sqlite.util.StringUtils;

public abstract class JDBC3DatabaseMetaData
extends CoreDatabaseMetaData {
    private static String driverName;
    private static String driverVersion;
    protected static final Pattern TYPE_INTEGER;
    protected static final Pattern TYPE_VARCHAR;
    protected static final Pattern TYPE_FLOAT;
    private static final Map<String, Integer> RULE_MAP;
    protected static final Pattern PK_UNNAMED_PATTERN;
    protected static final Pattern PK_NAMED_PATTERN;

    protected JDBC3DatabaseMetaData(SQLiteConnection conn) {
        super(conn);
    }

    @Override
    public Connection getConnection() {
        return this.conn;
    }

    @Override
    public int getDatabaseMajorVersion() throws SQLException {
        return Integer.valueOf(this.conn.libversion().split("\\.")[0]);
    }

    @Override
    public int getDatabaseMinorVersion() throws SQLException {
        return Integer.valueOf(this.conn.libversion().split("\\.")[1]);
    }

    @Override
    public int getDriverMajorVersion() {
        return Integer.valueOf(driverVersion.split("\\.")[0]);
    }

    @Override
    public int getDriverMinorVersion() {
        return Integer.valueOf(driverVersion.split("\\.")[1]);
    }

    @Override
    public int getJDBCMajorVersion() {
        return 2;
    }

    @Override
    public int getJDBCMinorVersion() {
        return 1;
    }

    @Override
    public int getDefaultTransactionIsolation() {
        return 8;
    }

    @Override
    public int getMaxBinaryLiteralLength() {
        return 0;
    }

    @Override
    public int getMaxCatalogNameLength() {
        return 0;
    }

    @Override
    public int getMaxCharLiteralLength() {
        return 0;
    }

    @Override
    public int getMaxColumnNameLength() {
        return 0;
    }

    @Override
    public int getMaxColumnsInGroupBy() {
        return 0;
    }

    @Override
    public int getMaxColumnsInIndex() {
        return 0;
    }

    @Override
    public int getMaxColumnsInOrderBy() {
        return 0;
    }

    @Override
    public int getMaxColumnsInSelect() {
        return 0;
    }

    @Override
    public int getMaxColumnsInTable() {
        return 0;
    }

    @Override
    public int getMaxConnections() {
        return 0;
    }

    @Override
    public int getMaxCursorNameLength() {
        return 0;
    }

    @Override
    public int getMaxIndexLength() {
        return 0;
    }

    @Override
    public int getMaxProcedureNameLength() {
        return 0;
    }

    @Override
    public int getMaxRowSize() {
        return 0;
    }

    @Override
    public int getMaxSchemaNameLength() {
        return 0;
    }

    @Override
    public int getMaxStatementLength() {
        return 0;
    }

    @Override
    public int getMaxStatements() {
        return 0;
    }

    @Override
    public int getMaxTableNameLength() {
        return 0;
    }

    @Override
    public int getMaxTablesInSelect() {
        return 0;
    }

    @Override
    public int getMaxUserNameLength() {
        return 0;
    }

    @Override
    public int getResultSetHoldability() {
        return 2;
    }

    @Override
    public int getSQLStateType() {
        return 2;
    }

    @Override
    public String getDatabaseProductName() {
        return "SQLite";
    }

    @Override
    public String getDatabaseProductVersion() throws SQLException {
        return this.conn.libversion();
    }

    @Override
    public String getDriverName() {
        return driverName;
    }

    @Override
    public String getDriverVersion() {
        return driverVersion;
    }

    @Override
    public String getExtraNameCharacters() {
        return "";
    }

    @Override
    public String getCatalogSeparator() {
        return ".";
    }

    @Override
    public String getCatalogTerm() {
        return "catalog";
    }

    @Override
    public String getSchemaTerm() {
        return "schema";
    }

    @Override
    public String getProcedureTerm() {
        return "not_implemented";
    }

    @Override
    public String getSearchStringEscape() {
        return null;
    }

    @Override
    public String getIdentifierQuoteString() {
        return "\"";
    }

    @Override
    public String getSQLKeywords() {
        return "ABORT,ACTION,AFTER,ANALYZE,ATTACH,AUTOINCREMENT,BEFORE,CASCADE,CONFLICT,DATABASE,DEFERRABLE,DEFERRED,DESC,DETACH,EXCLUSIVE,EXPLAIN,FAIL,GLOB,IGNORE,INDEX,INDEXED,INITIALLY,INSTEAD,ISNULL,KEY,LIMIT,NOTNULL,OFFSET,PLAN,PRAGMA,QUERY,RAISE,REGEXP,REINDEX,RENAME,REPLACE,RESTRICT,TEMP,TEMPORARY,TRANSACTION,VACUUM,VIEW,VIRTUAL";
    }

    @Override
    public String getNumericFunctions() {
        return "";
    }

    @Override
    public String getStringFunctions() {
        return "";
    }

    @Override
    public String getSystemFunctions() {
        return "";
    }

    @Override
    public String getTimeDateFunctions() {
        return "DATE,TIME,DATETIME,JULIANDAY,STRFTIME";
    }

    @Override
    public String getURL() {
        return this.conn.getUrl();
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public boolean allProceduresAreCallable() {
        return false;
    }

    @Override
    public boolean allTablesAreSelectable() {
        return true;
    }

    @Override
    public boolean dataDefinitionCausesTransactionCommit() {
        return false;
    }

    @Override
    public boolean dataDefinitionIgnoredInTransactions() {
        return false;
    }

    @Override
    public boolean doesMaxRowSizeIncludeBlobs() {
        return false;
    }

    @Override
    public boolean deletesAreDetected(int type) {
        return false;
    }

    @Override
    public boolean insertsAreDetected(int type) {
        return false;
    }

    @Override
    public boolean isCatalogAtStart() {
        return true;
    }

    @Override
    public boolean locatorsUpdateCopy() {
        return false;
    }

    @Override
    public boolean nullPlusNonNullIsNull() {
        return true;
    }

    @Override
    public boolean nullsAreSortedAtEnd() {
        return !this.nullsAreSortedAtStart();
    }

    @Override
    public boolean nullsAreSortedAtStart() {
        return true;
    }

    @Override
    public boolean nullsAreSortedHigh() {
        return true;
    }

    @Override
    public boolean nullsAreSortedLow() {
        return !this.nullsAreSortedHigh();
    }

    @Override
    public boolean othersDeletesAreVisible(int type) {
        return false;
    }

    @Override
    public boolean othersInsertsAreVisible(int type) {
        return false;
    }

    @Override
    public boolean othersUpdatesAreVisible(int type) {
        return false;
    }

    @Override
    public boolean ownDeletesAreVisible(int type) {
        return false;
    }

    @Override
    public boolean ownInsertsAreVisible(int type) {
        return false;
    }

    @Override
    public boolean ownUpdatesAreVisible(int type) {
        return false;
    }

    @Override
    public boolean storesLowerCaseIdentifiers() {
        return false;
    }

    @Override
    public boolean storesLowerCaseQuotedIdentifiers() {
        return false;
    }

    @Override
    public boolean storesMixedCaseIdentifiers() {
        return true;
    }

    @Override
    public boolean storesMixedCaseQuotedIdentifiers() {
        return false;
    }

    @Override
    public boolean storesUpperCaseIdentifiers() {
        return false;
    }

    @Override
    public boolean storesUpperCaseQuotedIdentifiers() {
        return false;
    }

    @Override
    public boolean supportsAlterTableWithAddColumn() {
        return false;
    }

    @Override
    public boolean supportsAlterTableWithDropColumn() {
        return false;
    }

    @Override
    public boolean supportsANSI92EntryLevelSQL() {
        return false;
    }

    @Override
    public boolean supportsANSI92FullSQL() {
        return false;
    }

    @Override
    public boolean supportsANSI92IntermediateSQL() {
        return false;
    }

    @Override
    public boolean supportsBatchUpdates() {
        return true;
    }

    @Override
    public boolean supportsCatalogsInDataManipulation() {
        return false;
    }

    @Override
    public boolean supportsCatalogsInIndexDefinitions() {
        return false;
    }

    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() {
        return false;
    }

    @Override
    public boolean supportsCatalogsInProcedureCalls() {
        return false;
    }

    @Override
    public boolean supportsCatalogsInTableDefinitions() {
        return false;
    }

    @Override
    public boolean supportsColumnAliasing() {
        return true;
    }

    @Override
    public boolean supportsConvert() {
        return false;
    }

    @Override
    public boolean supportsConvert(int fromType, int toType) {
        return false;
    }

    @Override
    public boolean supportsCorrelatedSubqueries() {
        return false;
    }

    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() {
        return true;
    }

    @Override
    public boolean supportsDataManipulationTransactionsOnly() {
        return false;
    }

    @Override
    public boolean supportsDifferentTableCorrelationNames() {
        return false;
    }

    @Override
    public boolean supportsExpressionsInOrderBy() {
        return true;
    }

    @Override
    public boolean supportsMinimumSQLGrammar() {
        return true;
    }

    @Override
    public boolean supportsCoreSQLGrammar() {
        return true;
    }

    @Override
    public boolean supportsExtendedSQLGrammar() {
        return false;
    }

    @Override
    public boolean supportsLimitedOuterJoins() {
        return true;
    }

    @Override
    public boolean supportsFullOuterJoins() {
        return false;
    }

    @Override
    public boolean supportsGetGeneratedKeys() {
        return true;
    }

    @Override
    public boolean supportsGroupBy() {
        return true;
    }

    @Override
    public boolean supportsGroupByBeyondSelect() {
        return false;
    }

    @Override
    public boolean supportsGroupByUnrelated() {
        return false;
    }

    @Override
    public boolean supportsIntegrityEnhancementFacility() {
        return false;
    }

    @Override
    public boolean supportsLikeEscapeClause() {
        return false;
    }

    @Override
    public boolean supportsMixedCaseIdentifiers() {
        return true;
    }

    @Override
    public boolean supportsMixedCaseQuotedIdentifiers() {
        return false;
    }

    @Override
    public boolean supportsMultipleOpenResults() {
        return false;
    }

    @Override
    public boolean supportsMultipleResultSets() {
        return false;
    }

    @Override
    public boolean supportsMultipleTransactions() {
        return true;
    }

    @Override
    public boolean supportsNamedParameters() {
        return true;
    }

    @Override
    public boolean supportsNonNullableColumns() {
        return true;
    }

    @Override
    public boolean supportsOpenCursorsAcrossCommit() {
        return false;
    }

    @Override
    public boolean supportsOpenCursorsAcrossRollback() {
        return false;
    }

    @Override
    public boolean supportsOpenStatementsAcrossCommit() {
        return false;
    }

    @Override
    public boolean supportsOpenStatementsAcrossRollback() {
        return false;
    }

    @Override
    public boolean supportsOrderByUnrelated() {
        return false;
    }

    @Override
    public boolean supportsOuterJoins() {
        return true;
    }

    @Override
    public boolean supportsPositionedDelete() {
        return false;
    }

    @Override
    public boolean supportsPositionedUpdate() {
        return false;
    }

    @Override
    public boolean supportsResultSetConcurrency(int t, int c) {
        return t == 1003 && c == 1007;
    }

    @Override
    public boolean supportsResultSetHoldability(int h) {
        return h == 2;
    }

    @Override
    public boolean supportsResultSetType(int t) {
        return t == 1003;
    }

    @Override
    public boolean supportsSavepoints() {
        return true;
    }

    @Override
    public boolean supportsSchemasInDataManipulation() {
        return false;
    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() {
        return false;
    }

    @Override
    public boolean supportsSchemasInPrivilegeDefinitions() {
        return false;
    }

    @Override
    public boolean supportsSchemasInProcedureCalls() {
        return false;
    }

    @Override
    public boolean supportsSchemasInTableDefinitions() {
        return false;
    }

    @Override
    public boolean supportsSelectForUpdate() {
        return false;
    }

    @Override
    public boolean supportsStatementPooling() {
        return false;
    }

    @Override
    public boolean supportsStoredProcedures() {
        return false;
    }

    @Override
    public boolean supportsSubqueriesInComparisons() {
        return false;
    }

    @Override
    public boolean supportsSubqueriesInExists() {
        return true;
    }

    @Override
    public boolean supportsSubqueriesInIns() {
        return true;
    }

    @Override
    public boolean supportsSubqueriesInQuantifieds() {
        return false;
    }

    @Override
    public boolean supportsTableCorrelationNames() {
        return false;
    }

    @Override
    public boolean supportsTransactionIsolationLevel(int level) {
        return level == 8;
    }

    @Override
    public boolean supportsTransactions() {
        return true;
    }

    @Override
    public boolean supportsUnion() {
        return true;
    }

    @Override
    public boolean supportsUnionAll() {
        return true;
    }

    @Override
    public boolean updatesAreDetected(int type) {
        return false;
    }

    @Override
    public boolean usesLocalFilePerTable() {
        return false;
    }

    @Override
    public boolean usesLocalFiles() {
        return true;
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return this.conn.isReadOnly();
    }

    @Override
    public ResultSet getAttributes(String c, String s, String t, String a) throws SQLException {
        if (this.getAttributes == null) {
            this.getAttributes = this.conn.prepareStatement("select null as TYPE_CAT, null as TYPE_SCHEM, null as TYPE_NAME, null as ATTR_NAME, null as DATA_TYPE, null as ATTR_TYPE_NAME, null as ATTR_SIZE, null as DECIMAL_DIGITS, null as NUM_PREC_RADIX, null as NULLABLE, null as REMARKS, null as ATTR_DEF, null as SQL_DATA_TYPE, null as SQL_DATETIME_SUB, null as CHAR_OCTET_LENGTH, null as ORDINAL_POSITION, null as IS_NULLABLE, null as SCOPE_CATALOG, null as SCOPE_SCHEMA, null as SCOPE_TABLE, null as SOURCE_DATA_TYPE limit 0;");
        }
        return this.getAttributes.executeQuery();
    }

    @Override
    public ResultSet getBestRowIdentifier(String c, String s, String t, int scope, boolean n) throws SQLException {
        if (this.getBestRowIdentifier == null) {
            this.getBestRowIdentifier = this.conn.prepareStatement("select null as SCOPE, null as COLUMN_NAME, null as DATA_TYPE, null as TYPE_NAME, null as COLUMN_SIZE, null as BUFFER_LENGTH, null as DECIMAL_DIGITS, null as PSEUDO_COLUMN limit 0;");
        }
        return this.getBestRowIdentifier.executeQuery();
    }

    @Override
    public ResultSet getColumnPrivileges(String c, String s, String t, String colPat) throws SQLException {
        if (this.getColumnPrivileges == null) {
            this.getColumnPrivileges = this.conn.prepareStatement("select null as TABLE_CAT, null as TABLE_SCHEM, null as TABLE_NAME, null as COLUMN_NAME, null as GRANTOR, null as GRANTEE, null as PRIVILEGE, null as IS_GRANTABLE limit 0;");
        }
        return this.getColumnPrivileges.executeQuery();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getColumns(String c, String s, String tblNamePattern, String colNamePattern) throws SQLException {
        this.checkOpen();
        StringBuilder sql = new StringBuilder(700);
        sql.append("select null as TABLE_CAT, null as TABLE_SCHEM, tblname as TABLE_NAME, ").append("cn as COLUMN_NAME, ct as DATA_TYPE, tn as TYPE_NAME, 2000000000 as COLUMN_SIZE, ").append("2000000000 as BUFFER_LENGTH, 10   as DECIMAL_DIGITS, 10   as NUM_PREC_RADIX, ").append("colnullable as NULLABLE, null as REMARKS, colDefault as COLUMN_DEF, ").append("0    as SQL_DATA_TYPE, 0    as SQL_DATETIME_SUB, 2000000000 as CHAR_OCTET_LENGTH, ").append("ordpos as ORDINAL_POSITION, (case colnullable when 0 then 'NO' when 1 then 'YES' else '' end)").append("    as IS_NULLABLE, null as SCOPE_CATLOG, null as SCOPE_SCHEMA, ").append("null as SCOPE_TABLE, null as SOURCE_DATA_TYPE, ").append("(case colautoincrement when 0 then 'NO' when 1 then 'YES' else '' end) as IS_AUTOINCREMENT, ").append("'' as IS_GENERATEDCOLUMN from (");
        boolean colFound = false;
        ResultSet rs = null;
        try {
            String[] types = new String[]{"TABLE", "VIEW"};
            rs = this.getTables(c, s, tblNamePattern, types);
            while (rs.next()) {
                String tableName = rs.getString(3);
                boolean isAutoIncrement = false;
                Statement statColAutoinc = this.conn.createStatement();
                ResultSet rsColAutoinc = null;
                try {
                    statColAutoinc = this.conn.createStatement();
                    rsColAutoinc = statColAutoinc.executeQuery("SELECT LIKE('%autoincrement%', LOWER(sql)) FROM sqlite_master WHERE LOWER(name) = LOWER('" + this.escape(tableName) + "') AND TYPE IN ('table', 'view')");
                    rsColAutoinc.next();
                    isAutoIncrement = rsColAutoinc.getInt(1) == 1;
                } finally {
                    if (rsColAutoinc != null) {
                        try {
                            rsColAutoinc.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (statColAutoinc != null) {
                        try {
                            statColAutoinc.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                Statement colstat = this.conn.createStatement();
                ResultSet rscol = null;
                try {
                    String pragmaStatement = "PRAGMA table_info('" + this.escape(tableName) + "')";
                    rscol = colstat.executeQuery(pragmaStatement);
                    int i = 0;
                    while (rscol.next()) {
                        String colName = rscol.getString(2);
                        String colType = rscol.getString(3);
                        String colNotNull = rscol.getString(4);
                        String colDefault = rscol.getString(5);
                        boolean isPk = "1".equals(rscol.getString(6));
                        int colNullable = 2;
                        if (colNotNull != null) {
                            int n = colNullable = colNotNull.equals("0") ? 1 : 0;
                        }
                        if (colFound) {
                            sql.append(" union all ");
                        }
                        colFound = true;
                        colType = colType == null ? "TEXT" : colType.toUpperCase();
                        int colAutoIncrement = 0;
                        if (isPk && isAutoIncrement) {
                            colAutoIncrement = 1;
                        }
                        int colJavaType = -1;
                        colJavaType = TYPE_INTEGER.matcher(colType).find() ? 4 : (TYPE_VARCHAR.matcher(colType).find() ? 12 : (TYPE_FLOAT.matcher(colType).find() ? 6 : 12));
                        sql.append("select ").append(i + 1).append(" as ordpos, ").append(colNullable).append(" as colnullable,").append("'").append(colJavaType).append("' as ct, ").append("'").append(tableName).append("' as tblname, ").append("'").append(this.escape(colName)).append("' as cn, ").append("'").append(this.escape(colType)).append("' as tn, ").append(JDBC3DatabaseMetaData.quote(colDefault == null ? null : this.escape(colDefault))).append(" as colDefault,").append(colAutoIncrement).append(" as colautoincrement");
                        if (colNamePattern != null) {
                            sql.append(" where upper(cn) like upper('").append(this.escape(colNamePattern)).append("')");
                        }
                        ++i;
                    }
                } finally {
                    if (rscol != null) {
                        try {
                            rscol.close();
                        } catch (SQLException sQLException) {}
                    }
                    if (colstat == null) continue;
                    try {
                        colstat.close();
                    } catch (SQLException sQLException) {}
                }
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (colFound) {
            sql.append(") order by TABLE_SCHEM, TABLE_NAME, ORDINAL_POSITION;");
        } else {
            sql.append("select null as ordpos, null as colnullable, null as ct, null as tblname, null as cn, null as tn, null as colDefault, null as colautoincrement) limit 0;");
        }
        Statement stat = this.conn.createStatement();
        return ((CoreStatement)((Object)stat)).executeQuery(sql.toString(), true);
    }

    @Override
    public ResultSet getCrossReference(String pc, String ps, String pt, String fc, String fs, String ft) throws SQLException {
        if (pt == null) {
            return this.getExportedKeys(fc, fs, ft);
        }
        if (ft == null) {
            return this.getImportedKeys(pc, ps, pt);
        }
        StringBuilder query = new StringBuilder();
        query.append("select ").append(JDBC3DatabaseMetaData.quote(pc)).append(" as PKTABLE_CAT, ").append(JDBC3DatabaseMetaData.quote(ps)).append(" as PKTABLE_SCHEM, ").append(JDBC3DatabaseMetaData.quote(pt)).append(" as PKTABLE_NAME, ").append("'' as PKCOLUMN_NAME, ").append(JDBC3DatabaseMetaData.quote(fc)).append(" as FKTABLE_CAT, ").append(JDBC3DatabaseMetaData.quote(fs)).append(" as FKTABLE_SCHEM, ").append(JDBC3DatabaseMetaData.quote(ft)).append(" as FKTABLE_NAME, ").append("'' as FKCOLUMN_NAME, -1 as KEY_SEQ, 3 as UPDATE_RULE, 3 as DELETE_RULE, '' as FK_NAME, '' as PK_NAME, ").append(Integer.toString(5)).append(" as DEFERRABILITY limit 0 ");
        return ((CoreStatement)((Object)this.conn.createStatement())).executeQuery(query.toString(), true);
    }

    @Override
    public ResultSet getSchemas() throws SQLException {
        if (this.getSchemas == null) {
            this.getSchemas = this.conn.prepareStatement("select null as TABLE_SCHEM, null as TABLE_CATALOG limit 0;");
        }
        return this.getSchemas.executeQuery();
    }

    @Override
    public ResultSet getCatalogs() throws SQLException {
        if (this.getCatalogs == null) {
            this.getCatalogs = this.conn.prepareStatement("select null as TABLE_CAT limit 0;");
        }
        return this.getCatalogs.executeQuery();
    }

    @Override
    public ResultSet getPrimaryKeys(String c, String s, String table) throws SQLException {
        PrimaryKeyFinder pkFinder = new PrimaryKeyFinder(table);
        String[] columns = pkFinder.getColumns();
        Statement stat = this.conn.createStatement();
        StringBuilder sql = new StringBuilder(512);
        sql.append("select null as TABLE_CAT, null as TABLE_SCHEM, '").append(this.escape(table)).append("' as TABLE_NAME, cn as COLUMN_NAME, ks as KEY_SEQ, pk as PK_NAME from (");
        if (columns == null) {
            sql.append("select null as cn, null as pk, 0 as ks) limit 0;");
            return ((CoreStatement)((Object)stat)).executeQuery(sql.toString(), true);
        }
        String pkName = pkFinder.getName();
        if (pkName != null) {
            pkName = "'" + pkName + "'";
        }
        for (int i = 0; i < columns.length; ++i) {
            if (i > 0) {
                sql.append(" union ");
            }
            sql.append("select ").append(pkName).append(" as pk, '").append(this.escape(this.unquoteIdentifier(columns[i]))).append("' as cn, ").append(i + 1).append(" as ks");
        }
        return ((CoreStatement)((Object)stat)).executeQuery(sql.append(") order by cn;").toString(), true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
        PrimaryKeyFinder pkFinder = new PrimaryKeyFinder(table);
        String[] pkColumns = pkFinder.getColumns();
        Statement stat = this.conn.createStatement();
        catalog = catalog != null ? JDBC3DatabaseMetaData.quote(catalog) : null;
        schema = schema != null ? JDBC3DatabaseMetaData.quote(schema) : null;
        StringBuilder exportedKeysQuery = new StringBuilder(512);
        String target = null;
        int count = 0;
        if (pkColumns != null) {
            ResultSet rs = stat.executeQuery("select name from sqlite_master where type = 'table'");
            ArrayList<String> tableList = new ArrayList<String>();
            while (rs.next()) {
                String tblname = rs.getString(1);
                tableList.add(tblname);
                if (!tblname.equalsIgnoreCase(table)) continue;
                target = tblname;
            }
            rs.close();
            for (String tbl : tableList) {
                try {
                    ImportedKeyFinder impFkFinder = new ImportedKeyFinder(tbl);
                    List<ImportedKeyFinder.ForeignKey> fkNames = impFkFinder.getFkList();
                    for (ImportedKeyFinder.ForeignKey foreignKey : fkNames) {
                        String PKTabName = foreignKey.getPkTableName();
                        if (PKTabName == null || !PKTabName.equalsIgnoreCase(target)) continue;
                        for (int j = 0; j < foreignKey.getColumnMappingCount(); ++j) {
                            int keySeq = j + 1;
                            String[] columnMapping = foreignKey.getColumnMapping(j);
                            String PKColName = columnMapping[1];
                            PKColName = PKColName == null ? "" : PKColName;
                            String FKColName = columnMapping[0];
                            FKColName = FKColName == null ? "" : FKColName;
                            boolean usePkName = false;
                            for (int k = 0; k < pkColumns.length; ++k) {
                                if (pkColumns[k] == null || !pkColumns[k].equalsIgnoreCase(PKColName)) continue;
                                usePkName = true;
                                break;
                            }
                            String pkName = usePkName && pkFinder.getName() != null ? pkFinder.getName() : "";
                            exportedKeysQuery.append(count > 0 ? " union all select " : "select ").append(Integer.toString(keySeq)).append(" as ks, '").append(this.escape(tbl)).append("' as fkt, '").append(this.escape(FKColName)).append("' as fcn, '").append(this.escape(PKColName)).append("' as pcn, '").append(this.escape(pkName)).append("' as pkn, ").append(RULE_MAP.get(foreignKey.getOnUpdate())).append(" as ur, ").append(RULE_MAP.get(foreignKey.getOnDelete())).append(" as dr, ");
                            String fkName = foreignKey.getFkName();
                            if (fkName != null) {
                                exportedKeysQuery.append("'").append(this.escape(fkName)).append("' as fkn");
                            } else {
                                exportedKeysQuery.append("'' as fkn");
                            }
                            ++count;
                        }
                    }
                } finally {
                    try {
                        if (rs == null) continue;
                        rs.close();
                    } catch (SQLException sQLException) {}
                }
            }
        }
        boolean hasImportedKey = count > 0;
        StringBuilder sql = new StringBuilder(512);
        sql.append("select ").append(catalog).append(" as PKTABLE_CAT, ").append(schema).append(" as PKTABLE_SCHEM, ").append(JDBC3DatabaseMetaData.quote(target)).append(" as PKTABLE_NAME, ").append(hasImportedKey ? "pcn" : "''").append(" as PKCOLUMN_NAME, ").append(catalog).append(" as FKTABLE_CAT, ").append(schema).append(" as FKTABLE_SCHEM, ").append(hasImportedKey ? "fkt" : "''").append(" as FKTABLE_NAME, ").append(hasImportedKey ? "fcn" : "''").append(" as FKCOLUMN_NAME, ").append(hasImportedKey ? "ks" : "-1").append(" as KEY_SEQ, ").append(hasImportedKey ? "ur" : "3").append(" as UPDATE_RULE, ").append(hasImportedKey ? "dr" : "3").append(" as DELETE_RULE, ").append(hasImportedKey ? "fkn" : "''").append(" as FK_NAME, ").append(hasImportedKey ? "pkn" : "''").append(" as PK_NAME, ").append(Integer.toString(5)).append(" as DEFERRABILITY ");
        if (hasImportedKey) {
            sql.append("from (").append((CharSequence)exportedKeysQuery).append(") ORDER BY FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, KEY_SEQ");
        } else {
            sql.append("limit 0");
        }
        return ((CoreStatement)((Object)stat)).executeQuery(sql.toString(), true);
    }

    private StringBuilder appendDummyForeignKeyList(StringBuilder sql) {
        sql.append("select -1 as ks, '' as ptn, '' as fcn, '' as pcn, ").append(3).append(" as ur, ").append(3).append(" as dr, ").append(" '' as fkn, ").append(" '' as pkn ").append(") limit 0;");
        return sql;
    }

    @Override
    public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
        ResultSet rs = null;
        Statement stat = this.conn.createStatement();
        StringBuilder sql = new StringBuilder(700);
        sql.append("select ").append(JDBC3DatabaseMetaData.quote(catalog)).append(" as PKTABLE_CAT, ").append(JDBC3DatabaseMetaData.quote(schema)).append(" as PKTABLE_SCHEM, ").append("ptn as PKTABLE_NAME, pcn as PKCOLUMN_NAME, ").append(JDBC3DatabaseMetaData.quote(catalog)).append(" as FKTABLE_CAT, ").append(JDBC3DatabaseMetaData.quote(schema)).append(" as FKTABLE_SCHEM, ").append(JDBC3DatabaseMetaData.quote(table)).append(" as FKTABLE_NAME, ").append("fcn as FKCOLUMN_NAME, ks as KEY_SEQ, ur as UPDATE_RULE, dr as DELETE_RULE, fkn as FK_NAME, pkn as PK_NAME, ").append(Integer.toString(5)).append(" as DEFERRABILITY from (");
        try {
            rs = stat.executeQuery("pragma foreign_key_list('" + this.escape(table) + "');");
        } catch (SQLException e) {
            sql = this.appendDummyForeignKeyList(sql);
            return ((CoreStatement)((Object)stat)).executeQuery(sql.toString(), true);
        }
        ImportedKeyFinder impFkFinder = new ImportedKeyFinder(table);
        List<ImportedKeyFinder.ForeignKey> fkNames = impFkFinder.getFkList();
        int i = 0;
        while (rs.next()) {
            int keySeq = rs.getInt(2) + 1;
            int keyId = rs.getInt(1);
            String PKTabName = rs.getString(3);
            String FKColName = rs.getString(4);
            String PKColName = rs.getString(5);
            PrimaryKeyFinder pkFinder = new PrimaryKeyFinder(PKTabName);
            String pkName = pkFinder.getName();
            if (PKColName == null) {
                PKColName = pkFinder.getColumns()[0];
            }
            String updateRule = rs.getString(6);
            String deleteRule = rs.getString(7);
            if (i > 0) {
                sql.append(" union all ");
            }
            String fkName = null;
            if (fkNames.size() > keyId) {
                fkName = fkNames.get(keyId).getFkName();
            }
            sql.append("select ").append(keySeq).append(" as ks,").append("'").append(this.escape(PKTabName)).append("' as ptn, '").append(this.escape(FKColName)).append("' as fcn, '").append(this.escape(PKColName)).append("' as pcn,").append("case '").append(this.escape(updateRule)).append("'").append(" when 'NO ACTION' then ").append(3).append(" when 'CASCADE' then ").append(0).append(" when 'RESTRICT' then ").append(1).append(" when 'SET NULL' then ").append(2).append(" when 'SET DEFAULT' then ").append(4).append(" end as ur, ").append("case '").append(this.escape(deleteRule)).append("'").append(" when 'NO ACTION' then ").append(3).append(" when 'CASCADE' then ").append(0).append(" when 'RESTRICT' then ").append(1).append(" when 'SET NULL' then ").append(2).append(" when 'SET DEFAULT' then ").append(4).append(" end as dr, ").append(fkName == null ? "''" : JDBC3DatabaseMetaData.quote(fkName)).append(" as fkn, ").append(pkName == null ? "''" : JDBC3DatabaseMetaData.quote(pkName)).append(" as pkn");
            ++i;
        }
        rs.close();
        if (i == 0) {
            sql = this.appendDummyForeignKeyList(sql);
        }
        sql.append(") ORDER BY PKTABLE_CAT, PKTABLE_SCHEM, PKTABLE_NAME, KEY_SEQ;");
        return ((CoreStatement)((Object)stat)).executeQuery(sql.toString(), true);
    }

    @Override
    public ResultSet getIndexInfo(String c, String s, String table, boolean u, boolean approximate) throws SQLException {
        ResultSet rs = null;
        Statement stat = this.conn.createStatement();
        StringBuilder sql = new StringBuilder(500);
        sql.append("select null as TABLE_CAT, null as TABLE_SCHEM, '").append(this.escape(table)).append("' as TABLE_NAME, un as NON_UNIQUE, null as INDEX_QUALIFIER, n as INDEX_NAME, ").append(Integer.toString(3)).append(" as TYPE, op as ORDINAL_POSITION, ").append("cn as COLUMN_NAME, null as ASC_OR_DESC, 0 as CARDINALITY, 0 as PAGES, null as FILTER_CONDITION from (");
        rs = stat.executeQuery("pragma index_list('" + this.escape(table) + "');");
        ArrayList indexList = new ArrayList();
        while (rs.next()) {
            indexList.add(new ArrayList());
            ((ArrayList)indexList.get(indexList.size() - 1)).add(rs.getString(2));
            ((ArrayList)indexList.get(indexList.size() - 1)).add(rs.getInt(3));
        }
        rs.close();
        if (indexList.size() == 0) {
            sql.append("select null as un, null as n, null as op, null as cn) limit 0;");
            return ((CoreStatement)((Object)stat)).executeQuery(sql.toString(), true);
        }
        boolean i = false;
        Iterator indexIterator = indexList.iterator();
        ArrayList<String> unionAll = new ArrayList<String>();
        while (indexIterator.hasNext()) {
            ArrayList currentIndex = (ArrayList)indexIterator.next();
            String indexName = currentIndex.get(0).toString();
            rs = stat.executeQuery("pragma index_info('" + this.escape(indexName) + "');");
            while (rs.next()) {
                StringBuilder sqlRow = new StringBuilder();
                String colName = rs.getString(3);
                sqlRow.append("select ").append(Integer.toString(1 - (Integer)currentIndex.get(1))).append(" as un,'").append(this.escape(indexName)).append("' as n,").append(Integer.toString(rs.getInt(1) + 1)).append(" as op,");
                if (colName == null) {
                    sqlRow.append("null");
                } else {
                    sqlRow.append("'").append(this.escape(colName)).append("'");
                }
                sqlRow.append(" as cn");
                unionAll.add(sqlRow.toString());
            }
            rs.close();
        }
        String sqlBlock = StringUtils.join(unionAll, " union all ");
        return ((CoreStatement)((Object)stat)).executeQuery(sql.append(sqlBlock).append(");").toString(), true);
    }

    @Override
    public ResultSet getProcedureColumns(String c, String s, String p, String colPat) throws SQLException {
        if (this.getProcedureColumns == null) {
            this.getProcedureColumns = this.conn.prepareStatement("select null as PROCEDURE_CAT, null as PROCEDURE_SCHEM, null as PROCEDURE_NAME, null as COLUMN_NAME, null as COLUMN_TYPE, null as DATA_TYPE, null as TYPE_NAME, null as PRECISION, null as LENGTH, null as SCALE, null as RADIX, null as NULLABLE, null as REMARKS limit 0;");
        }
        return this.getProcedureColumns.executeQuery();
    }

    @Override
    public ResultSet getProcedures(String c, String s, String p) throws SQLException {
        if (this.getProcedures == null) {
            this.getProcedures = this.conn.prepareStatement("select null as PROCEDURE_CAT, null as PROCEDURE_SCHEM, null as PROCEDURE_NAME, null as UNDEF1, null as UNDEF2, null as UNDEF3, null as REMARKS, null as PROCEDURE_TYPE limit 0;");
        }
        return this.getProcedures.executeQuery();
    }

    @Override
    public ResultSet getSuperTables(String c, String s, String t) throws SQLException {
        if (this.getSuperTables == null) {
            this.getSuperTables = this.conn.prepareStatement("select null as TABLE_CAT, null as TABLE_SCHEM, null as TABLE_NAME, null as SUPERTABLE_NAME limit 0;");
        }
        return this.getSuperTables.executeQuery();
    }

    @Override
    public ResultSet getSuperTypes(String c, String s, String t) throws SQLException {
        if (this.getSuperTypes == null) {
            this.getSuperTypes = this.conn.prepareStatement("select null as TYPE_CAT, null as TYPE_SCHEM, null as TYPE_NAME, null as SUPERTYPE_CAT, null as SUPERTYPE_SCHEM, null as SUPERTYPE_NAME limit 0;");
        }
        return this.getSuperTypes.executeQuery();
    }

    @Override
    public ResultSet getTablePrivileges(String c, String s, String t) throws SQLException {
        if (this.getTablePrivileges == null) {
            this.getTablePrivileges = this.conn.prepareStatement("select  null as TABLE_CAT, null as TABLE_SCHEM, null as TABLE_NAME, null as GRANTOR, null GRANTEE,  null as PRIVILEGE, null as IS_GRANTABLE limit 0;");
        }
        return this.getTablePrivileges.executeQuery();
    }

    @Override
    public synchronized ResultSet getTables(String c, String s, String tblNamePattern, String[] types) throws SQLException {
        this.checkOpen();
        tblNamePattern = tblNamePattern == null || "".equals(tblNamePattern) ? "%" : this.escape(tblNamePattern);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT").append("\n");
        sql.append("  NULL AS TABLE_CAT,").append("\n");
        sql.append("  NULL AS TABLE_SCHEM,").append("\n");
        sql.append("  NAME AS TABLE_NAME,").append("\n");
        sql.append("  TYPE AS TABLE_TYPE,").append("\n");
        sql.append("  NULL AS REMARKS,").append("\n");
        sql.append("  NULL AS TYPE_CAT,").append("\n");
        sql.append("  NULL AS TYPE_SCHEM,").append("\n");
        sql.append("  NULL AS TYPE_NAME,").append("\n");
        sql.append("  NULL AS SELF_REFERENCING_COL_NAME,").append("\n");
        sql.append("  NULL AS REF_GENERATION").append("\n");
        sql.append("FROM").append("\n");
        sql.append("  (").append("\n");
        sql.append("    SELECT").append("\n");
        sql.append("      NAME,").append("\n");
        sql.append("      UPPER(TYPE) AS TYPE").append("\n");
        sql.append("    FROM").append("\n");
        sql.append("      sqlite_master").append("\n");
        sql.append("    WHERE").append("\n");
        sql.append("      NAME NOT LIKE 'sqlite\\_%' ESCAPE '\\'").append("\n");
        sql.append("      AND UPPER(TYPE) IN ('TABLE', 'VIEW')").append("\n");
        sql.append("    UNION ALL").append("\n");
        sql.append("    SELECT").append("\n");
        sql.append("      NAME,").append("\n");
        sql.append("      'GLOBAL TEMPORARY' AS TYPE").append("\n");
        sql.append("    FROM").append("\n");
        sql.append("      sqlite_temp_master").append("\n");
        sql.append("    UNION ALL").append("\n");
        sql.append("    SELECT").append("\n");
        sql.append("      NAME,").append("\n");
        sql.append("      'SYSTEM TABLE' AS TYPE").append("\n");
        sql.append("    FROM").append("\n");
        sql.append("      sqlite_master").append("\n");
        sql.append("    WHERE").append("\n");
        sql.append("      NAME LIKE 'sqlite\\_%' ESCAPE '\\'").append("\n");
        sql.append("  )").append("\n");
        sql.append(" WHERE TABLE_NAME LIKE '").append(tblNamePattern).append("' AND TABLE_TYPE IN (");
        if (types == null || types.length == 0) {
            sql.append("'TABLE','VIEW'");
        } else {
            sql.append("'").append(types[0].toUpperCase()).append("'");
            for (int i = 1; i < types.length; ++i) {
                sql.append(",'").append(types[i].toUpperCase()).append("'");
            }
        }
        sql.append(") ORDER BY TABLE_TYPE, TABLE_NAME;");
        return ((CoreStatement)((Object)this.conn.createStatement())).executeQuery(sql.toString(), true);
    }

    @Override
    public ResultSet getTableTypes() throws SQLException {
        this.checkOpen();
        String sql = "SELECT 'TABLE' AS TABLE_TYPE UNION SELECT 'VIEW' AS TABLE_TYPE UNION SELECT 'SYSTEM TABLE' AS TABLE_TYPE UNION SELECT 'GLOBAL TEMPORARY' AS TABLE_TYPE;";
        if (this.getTableTypes == null) {
            this.getTableTypes = this.conn.prepareStatement(sql);
        }
        this.getTableTypes.clearParameters();
        return this.getTableTypes.executeQuery();
    }

    @Override
    public ResultSet getTypeInfo() throws SQLException {
        if (this.getTypeInfo == null) {
            this.getTypeInfo = this.conn.prepareStatement("select tn as TYPE_NAME, dt as DATA_TYPE, 0 as PRECISION, null as LITERAL_PREFIX, null as LITERAL_SUFFIX, null as CREATE_PARAMS, 1 as NULLABLE, 1 as CASE_SENSITIVE, 3 as SEARCHABLE, 0 as UNSIGNED_ATTRIBUTE, 0 as FIXED_PREC_SCALE, 0 as AUTO_INCREMENT, null as LOCAL_TYPE_NAME, 0 as MINIMUM_SCALE, 0 as MAXIMUM_SCALE, 0 as SQL_DATA_TYPE, 0 as SQL_DATETIME_SUB, 10 as NUM_PREC_RADIX from (    select 'BLOB' as tn, 2004 as dt union    select 'NULL' as tn, 0 as dt union    select 'REAL' as tn, 7 as dt union    select 'TEXT' as tn, 12 as dt union    select 'INTEGER' as tn, 4 as dt) order by TYPE_NAME;");
        }
        this.getTypeInfo.clearParameters();
        return this.getTypeInfo.executeQuery();
    }

    @Override
    public ResultSet getUDTs(String c, String s, String t, int[] types) throws SQLException {
        if (this.getUDTs == null) {
            this.getUDTs = this.conn.prepareStatement("select  null as TYPE_CAT, null as TYPE_SCHEM, null as TYPE_NAME,  null as CLASS_NAME,  null as DATA_TYPE, null as REMARKS, null as BASE_TYPE limit 0;");
        }
        this.getUDTs.clearParameters();
        return this.getUDTs.executeQuery();
    }

    @Override
    public ResultSet getVersionColumns(String c, String s, String t) throws SQLException {
        if (this.getVersionColumns == null) {
            this.getVersionColumns = this.conn.prepareStatement("select null as SCOPE, null as COLUMN_NAME, null as DATA_TYPE, null as TYPE_NAME, null as COLUMN_SIZE, null as BUFFER_LENGTH, null as DECIMAL_DIGITS, null as PSEUDO_COLUMN limit 0;");
        }
        return this.getVersionColumns.executeQuery();
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        if (this.getGeneratedKeys == null) {
            this.getGeneratedKeys = this.conn.prepareStatement("select last_insert_rowid();");
        }
        return this.getGeneratedKeys.executeQuery();
    }

    public Struct createStruct(String t, Object[] attr) throws SQLException {
        throw new SQLException("Not yet implemented by SQLite JDBC driver");
    }

    @Override
    public ResultSet getFunctionColumns(String a, String b, String c, String d) throws SQLException {
        throw new SQLException("Not yet implemented by SQLite JDBC driver");
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
    }

    private String unquoteIdentifier(String name) {
        if (name == null) {
            return name;
        }
        if ((name = name.trim()).length() > 2 && (name.startsWith("`") && name.endsWith("`") || name.startsWith("\"") && name.endsWith("\"") || name.startsWith("[") && name.endsWith("]"))) {
            name = name.substring(1, name.length() - 1);
        }
        return name;
    }

    static {
        InputStream sqliteJdbcPropStream = null;
        try {
            sqliteJdbcPropStream = JDBC3DatabaseMetaData.class.getClassLoader().getResourceAsStream("sqlite-jdbc.properties");
            if (sqliteJdbcPropStream == null) {
                throw new IOException("Cannot load sqlite-jdbc.properties from jar");
            }
            Properties sqliteJdbcProp = new Properties();
            sqliteJdbcProp.load(sqliteJdbcPropStream);
            driverName = sqliteJdbcProp.getProperty("name");
            driverVersion = sqliteJdbcProp.getProperty("version");
        } catch (Exception e) {
            driverName = "SQLite JDBC";
            driverVersion = "3.0.0-UNKNOWN";
        } finally {
            if (null != sqliteJdbcPropStream) {
                try {
                    sqliteJdbcPropStream.close();
                } catch (Exception exception) {}
            }
        }
        TYPE_INTEGER = Pattern.compile(".*(INT|BOOL).*");
        TYPE_VARCHAR = Pattern.compile(".*(CHAR|CLOB|TEXT|BLOB).*");
        TYPE_FLOAT = Pattern.compile(".*(REAL|FLOA|DOUB|DEC|NUM).*");
        RULE_MAP = new HashMap<String, Integer>();
        RULE_MAP.put("NO ACTION", 3);
        RULE_MAP.put("CASCADE", 0);
        RULE_MAP.put("RESTRICT", 1);
        RULE_MAP.put("SET NULL", 2);
        RULE_MAP.put("SET DEFAULT", 4);
        PK_UNNAMED_PATTERN = Pattern.compile(".*PRIMARY\\s+KEY\\s*\\((.*?)\\).*", 34);
        PK_NAMED_PATTERN = Pattern.compile(".*CONSTRAINT\\s*(.*?)\\s*PRIMARY\\s+KEY\\s*\\((.*?)\\).*", 34);
    }

    class ImportedKeyFinder {
        private final Pattern FK_NAMED_PATTERN = Pattern.compile("CONSTRAINT\\s*([A-Za-z_][A-Za-z\\d_]*)?\\s*FOREIGN\\s+KEY\\s*\\((.*?)\\)", 34);
        private String fkTableName;
        private List<ForeignKey> fkList = new ArrayList<ForeignKey>();

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public ImportedKeyFinder(String table) throws SQLException {
            if (table == null || table.trim().length() == 0) {
                throw new SQLException("Invalid table name: '" + table + "'");
            }
            this.fkTableName = table;
            List<String> fkNames = this.getForeignKeyNames(this.fkTableName);
            Statement stat = null;
            ResultSet rs = null;
            try {
                stat = JDBC3DatabaseMetaData.this.conn.createStatement();
                rs = stat.executeQuery("pragma foreign_key_list('" + JDBC3DatabaseMetaData.this.escape(this.fkTableName.toLowerCase()) + "')");
                int prevFkId = -1;
                int count = 0;
                ForeignKey fk = null;
                while (rs.next()) {
                    int fkId = rs.getInt(1);
                    int colSeq = rs.getInt(2);
                    String pkTableName = rs.getString(3);
                    String fkColName = rs.getString(4);
                    String pkColName = rs.getString(5);
                    String onUpdate = rs.getString(6);
                    String onDelete = rs.getString(7);
                    String match = rs.getString(8);
                    String fkName = null;
                    if (fkNames.size() > count) {
                        fkName = fkNames.get(count);
                    }
                    if (fkId != prevFkId) {
                        fk = new ForeignKey(fkName, pkTableName, this.fkTableName, onUpdate, onDelete, match);
                        this.fkList.add(fk);
                        prevFkId = fkId;
                        ++count;
                    }
                    fk.addColumnMapping(fkColName, pkColName);
                }
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception exception) {}
                try {
                    if (stat != null) {
                        stat.close();
                    }
                } catch (Exception exception) {}
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private List<String> getForeignKeyNames(String tbl) throws SQLException {
            ArrayList<String> fkNames = new ArrayList<String>();
            if (tbl == null) {
                return fkNames;
            }
            Statement stat2 = null;
            ResultSet rs = null;
            try {
                stat2 = JDBC3DatabaseMetaData.this.conn.createStatement();
                rs = stat2.executeQuery("select sql from sqlite_master where lower(name) = lower('" + JDBC3DatabaseMetaData.this.escape(tbl) + "')");
                if (rs.next()) {
                    Matcher matcher = this.FK_NAMED_PATTERN.matcher(rs.getString(1));
                    while (matcher.find()) {
                        fkNames.add(matcher.group(1));
                    }
                }
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException sQLException) {}
                try {
                    if (stat2 != null) {
                        stat2.close();
                    }
                } catch (SQLException sQLException) {}
            }
            Collections.reverse(fkNames);
            return fkNames;
        }

        public String getFkTableName() {
            return this.fkTableName;
        }

        public List<ForeignKey> getFkList() {
            return this.fkList;
        }

        class ForeignKey {
            private String fkName;
            private String pkTableName;
            private String fkTableName;
            private List<String> fkColNames = new ArrayList<String>();
            private List<String> pkColNames = new ArrayList<String>();
            private String onUpdate;
            private String onDelete;
            private String match;

            ForeignKey(String fkName, String pkTableName, String fkTableName, String onUpdate, String onDelete, String match) {
                this.fkName = fkName;
                this.pkTableName = pkTableName;
                this.fkTableName = fkTableName;
                this.onUpdate = onUpdate;
                this.onDelete = onDelete;
                this.match = match;
            }

            public String getFkName() {
                return this.fkName;
            }

            void addColumnMapping(String fkColName, String pkColName) {
                this.fkColNames.add(fkColName);
                this.pkColNames.add(pkColName);
            }

            public String[] getColumnMapping(int colSeq) {
                return new String[]{this.fkColNames.get(colSeq), this.pkColNames.get(colSeq)};
            }

            public int getColumnMappingCount() {
                return this.fkColNames.size();
            }

            public String getPkTableName() {
                return this.pkTableName;
            }

            public String getFkTableName() {
                return this.fkTableName;
            }

            public String getOnUpdate() {
                return this.onUpdate;
            }

            public String getOnDelete() {
                return this.onDelete;
            }

            public String getMatch() {
                return this.match;
            }

            public String toString() {
                return "ForeignKey [fkName=" + this.fkName + ", pkTableName=" + this.pkTableName + ", fkTableName=" + this.fkTableName + ", pkColNames=" + this.pkColNames + ", fkColNames=" + this.fkColNames + "]";
            }
        }
    }

    class PrimaryKeyFinder {
        String table;
        String pkName = null;
        String[] pkColumns = null;

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public PrimaryKeyFinder(String table) throws SQLException {
            this.table = table;
            if (table == null || table.trim().length() == 0) {
                throw new SQLException("Invalid table name: '" + this.table + "'");
            }
            Statement stat = null;
            ResultSet rs = null;
            try {
                stat = JDBC3DatabaseMetaData.this.conn.createStatement();
                rs = stat.executeQuery("select sql from sqlite_master where lower(name) = lower('" + JDBC3DatabaseMetaData.this.escape(table) + "') and type in ('table', 'view')");
                if (!rs.next()) {
                    throw new SQLException("Table not found: '" + table + "'");
                }
                Matcher matcher = PK_NAMED_PATTERN.matcher(rs.getString(1));
                if (matcher.find()) {
                    this.pkName = JDBC3DatabaseMetaData.this.unquoteIdentifier(JDBC3DatabaseMetaData.this.escape(matcher.group(1)));
                    this.pkColumns = matcher.group(2).split(",");
                } else {
                    matcher = PK_UNNAMED_PATTERN.matcher(rs.getString(1));
                    if (matcher.find()) {
                        this.pkColumns = matcher.group(1).split(",");
                    }
                }
                if (this.pkColumns == null) {
                    rs = stat.executeQuery("pragma table_info('" + JDBC3DatabaseMetaData.this.escape(table) + "');");
                    while (rs.next()) {
                        if (!rs.getBoolean(6)) continue;
                        this.pkColumns = new String[]{rs.getString(2)};
                    }
                }
                if (this.pkColumns != null) {
                    for (int i = 0; i < this.pkColumns.length; ++i) {
                        this.pkColumns[i] = JDBC3DatabaseMetaData.this.unquoteIdentifier(this.pkColumns[i]);
                    }
                }
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception exception) {}
                try {
                    if (stat != null) {
                        stat.close();
                    }
                } catch (Exception exception) {}
            }
        }

        public String getName() {
            return this.pkName;
        }

        public String[] getColumns() {
            return this.pkColumns;
        }
    }
}

