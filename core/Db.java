/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core;

import core.ApplicationContext;
import core.shell.ShellEntity;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import util.Log;
import util.functions;

public class Db {
    private static Connection dbConn;
    private static final String Drivde = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:data.db";
    private static final String CREATE_SHELL_TABLE = "CREATE TABLE \"shell\" ( \"id\" text NOT NULL,  \"url\" TEXT NOT NULL,  \"password\" TEXT NOT NULL,  \"secretKey\" TEXT NOT NULL,  \"payload\" TEXT NOT NULL,  \"cryption\" TEXT NOT NULL,  \"encoding\" TEXT NOT NULL,  \"headers\" TEXT NOT NULL,  \"reqLeft\" TEXT NOT NULL,  \"reqRight\" TEXT NOT NULL,  \"connTimeout\" integer NOT NULL,  \"readTimeout\" integer NOT NULL,  \"proxyType\" TEXT NOT NULL,  \"proxyHost\" TEXT NOT NULL,  \"proxyPort\" TEXT NOT NULL,  \"remark\" TEXT NOT NULL,  \"note\" TEXT NOT NULL,  \"createTime\" TEXT NOT NULL,  \"updateTime\" text NOT NULL,  PRIMARY KEY (\"id\"))";
    private static final String CREATE_SHELLENV_TABLE = "CREATE TABLE shellEnv (\"shellId\" text NOT NULL,\"key\" TEXT NOT NULL,\"value\" TEXT);";
    private static final String CREATE_PLUGIN_TABLE = "CREATE TABLE plugin (pluginJarFile TEXT NOT NULL,PRIMARY KEY (\"pluginJarFile\"))";
    private static final String CREATE_SETING_TABLE = "CREATE TABLE seting (\"key\" TEXT NOT NULL,\"value\" TEXT NOT NULL,PRIMARY KEY (\"key\"))";
    private static final String CREATE_SHELLGROUP_TABLE = "CREATE TABLE shellGroup (\"groupId\" text NOT NULL,  PRIMARY KEY (\"groupId\"));";

    public static boolean tableExists(String tableName) {
        String selectTable = "SELECT COUNT(1) as result FROM sqlite_master WHERE name=?";
        boolean ret = false;
        try {
            PreparedStatement preparedStatement = Db.getPreparedStatement(selectTable);
            preparedStatement.setString(1, tableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int result = resultSet.getInt("result");
            if (result == 1) {
                ret = true;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            Log.error(e);
        }
        return ret;
    }

    public static synchronized Vector<Vector<String>> getAllShell() {
        String selectShell = "SELECT id,url,payload,cryption,encoding,proxyType,remark,createTime,updateTime FROM shell";
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        try {
            Statement statement = Db.getStatement();
            ResultSet resultSet = statement.executeQuery(selectShell);
            Vector<String> columns = Db.getAllcolumn(resultSet.getMetaData());
            rows.add(columns);
            while (resultSet.next()) {
                Vector<String> rowVector = new Vector<String>();
                for (int i = 0; i < columns.size(); ++i) {
                    rowVector.add(resultSet.getString(i + 1));
                }
                rows.add(rowVector);
            }
            resultSet.close();
            statement.close();
            return rows;
        } catch (Exception e) {
            Log.error(e);
            return null;
        }
    }

    public static synchronized Vector<Vector<String>> getAllShell(String groupId) {
        if ("/".equals(groupId)) {
            return Db.getAllShell();
        }
        String selectShell = "SELECT shell.id,shell.url,shell.payload,shell.cryption,shell.encoding,shell.proxyType,shell.remark,shell.createTime,shell.updateTime FROM shellEnv  LEFT JOIN shell ON shell.id = shellId  WHERE key='ENV_GROUP_ID' and value LIKE ?";
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        try {
            Statement statement = Db.getStatement();
            PreparedStatement preparedStatement = Db.getPreparedStatement(selectShell);
            preparedStatement.setString(1, groupId + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            Vector<String> columns = Db.getAllcolumn(resultSet.getMetaData());
            rows.add(columns);
            while (resultSet.next()) {
                Vector<String> rowVector = new Vector<String>();
                for (int i = 0; i < columns.size(); ++i) {
                    rowVector.add(resultSet.getString(i + 1));
                }
                rows.add(rowVector);
            }
            resultSet.close();
            statement.close();
            return rows;
        } catch (Exception e) {
            Log.error(e);
            return null;
        }
    }

    public static synchronized ShellEntity getOneShell(String id) {
        String selectShell = "SELECT id,url,password,secretKey,payload,cryption,encoding,headers,reqLeft,reqRight,connTimeout,readTimeout,proxyType,proxyHost,proxyPort,remark FROM SHELL WHERE id = ?";
        try {
            PreparedStatement preparedStatement = Db.getPreparedStatement(selectShell);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ShellEntity context = new ShellEntity();
                context.setId(resultSet.getString("id"));
                context.setUrl(resultSet.getString("url"));
                context.setPassword(resultSet.getString("password"));
                context.setPayload(resultSet.getString("payload"));
                context.setSecretKey(resultSet.getString("secretKey"));
                context.setCryption(resultSet.getString("cryption"));
                context.setEncoding(resultSet.getString("encoding"));
                context.setRemark(resultSet.getString("remark"));
                context.setHeader(resultSet.getString("headers"));
                context.setReqLeft(resultSet.getString("reqLeft"));
                context.setReqRight(resultSet.getString("reqRight"));
                context.setConnTimeout(resultSet.getInt("connTimeout"));
                context.setReadTimeout(resultSet.getInt("readTimeout"));
                context.setProxyType(resultSet.getString("proxyType"));
                context.setProxyHost(resultSet.getString("proxyHost"));
                context.setProxyPort(resultSet.getInt("proxyPort"));
                resultSet.close();
                preparedStatement.close();
                return context;
            }
            return null;
        } catch (Exception e) {
            Log.error(e);
            return null;
        }
    }

    public static synchronized int addShell(ShellEntity shellContext) {
        String uuid = UUID.randomUUID().toString();
        String addShellSql = "INSERT INTO \"shell\"(\"id\", \"url\", \"password\", \"secretKey\", \"payload\", \"cryption\", \"encoding\", \"headers\", \"reqLeft\", \"reqRight\", \"connTimeout\", \"readTimeout\", \"proxyType\", \"proxyHost\", \"proxyPort\", \"remark\", \"note\", \"createTime\", \"updateTime\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        PreparedStatement preparedStatement = Db.getPreparedStatement(addShellSql);
        shellContext.setId(uuid);
        try {
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, shellContext.getUrl());
            preparedStatement.setString(3, shellContext.getPassword());
            preparedStatement.setString(4, shellContext.getSecretKey());
            preparedStatement.setString(5, shellContext.getPayload());
            preparedStatement.setString(6, shellContext.getCryption());
            preparedStatement.setString(7, shellContext.getEncoding());
            preparedStatement.setString(8, shellContext.getHeaderS());
            preparedStatement.setString(9, shellContext.getReqLeft());
            preparedStatement.setString(10, shellContext.getReqRight());
            preparedStatement.setInt(11, shellContext.getConnTimeout());
            preparedStatement.setInt(12, shellContext.getReadTimeout());
            preparedStatement.setString(13, shellContext.getProxyType());
            preparedStatement.setString(14, shellContext.getProxyHost());
            preparedStatement.setInt(15, shellContext.getProxyPort());
            preparedStatement.setString(16, shellContext.getRemark());
            preparedStatement.setString(17, "");
            preparedStatement.setString(18, createTime);
            preparedStatement.setString(19, createTime);
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectNum;
        } catch (Exception e) {
            Log.error(e);
            return 0;
        }
    }

    public static synchronized int updateShell(ShellEntity shellContext) {
        String updateShell = "UPDATE \"shell\" SET \"url\" = ?, \"password\" = ?, \"secretKey\" = ?, \"payload\" = ?, \"cryption\" = ?, \"encoding\" = ?, \"headers\" = ?, \"reqLeft\" = ?, \"reqRight\" = ?, \"connTimeout\" = ?, \"readTimeout\" = ?, \"proxyType\" = ?, \"proxyHost\" = ?, \"proxyPort\" = ?, \"remark\" = ?, \"updateTime\" = ? WHERE id = ?";
        String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        PreparedStatement preparedStatement = Db.getPreparedStatement(updateShell);
        try {
            preparedStatement.setString(1, shellContext.getUrl());
            preparedStatement.setString(2, shellContext.getPassword());
            preparedStatement.setString(3, shellContext.getSecretKey());
            preparedStatement.setString(4, shellContext.getPayload());
            preparedStatement.setString(5, shellContext.getCryption());
            preparedStatement.setString(6, shellContext.getEncoding());
            preparedStatement.setString(7, shellContext.getHeaderS());
            preparedStatement.setString(8, shellContext.getReqLeft());
            preparedStatement.setString(9, shellContext.getReqRight());
            preparedStatement.setInt(10, shellContext.getConnTimeout());
            preparedStatement.setInt(11, shellContext.getReadTimeout());
            preparedStatement.setString(12, shellContext.getProxyType());
            preparedStatement.setString(13, shellContext.getProxyHost());
            preparedStatement.setInt(14, shellContext.getProxyPort());
            preparedStatement.setString(15, shellContext.getRemark());
            preparedStatement.setString(16, updateTime);
            preparedStatement.setString(17, shellContext.getId());
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectNum;
        } catch (Exception e) {
            Log.error(e);
            return 0;
        }
    }

    public static synchronized int removeShell(String id) {
        String addShellSql = "DELETE FROM shell WHERE \"id\"= ?";
        PreparedStatement preparedStatement = Db.getPreparedStatement(addShellSql);
        try {
            preparedStatement.setString(1, id);
            int affectNum = preparedStatement.executeUpdate();
            functions.delFiles(new File(String.format("%s/%s", "GodzillaCache", id)));
            preparedStatement.close();
            Db.clearShellEnv(id);
            return affectNum;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static List getAllGroup() {
        String addShellSql = "SELECT groupId FROM shellGroup";
        ArrayList<String> ids = new ArrayList<String>();
        PreparedStatement preparedStatement = Db.getPreparedStatement(addShellSql);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static synchronized int removeShellByGroup(String groupId) {
        String addShellSql = "SELECT id FROM shellEnv  LEFT JOIN shell ON shell.id = shellId  WHERE key='ENV_GROUP_ID' and value LIKE ?";
        PreparedStatement preparedStatement = Db.getPreparedStatement(addShellSql);
        try {
            preparedStatement.setString(1, groupId + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> ids = new ArrayList<String>();
            while (resultSet.next()) {
                ids.add(resultSet.getString(1));
            }
            int affectNum = ids.stream().mapToInt(id -> Db.removeShell(id.toString())).sum();
            return affectNum;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static synchronized int removeGroup(String groupId, String defaultGroupId) {
        String addShellSql = "DELETE FROM shellGroup WHERE groupId LIKE ?";
        PreparedStatement preparedStatement = Db.getPreparedStatement(addShellSql);
        try {
            preparedStatement.setString(1, groupId + "%");
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement = Db.getPreparedStatement("UPDATE shellEnv SET value=?  WHERE key='ENV_GROUP_ID' AND value LIKE ?");
            preparedStatement.setString(1, defaultGroupId);
            preparedStatement.setString(2, groupId + "%");
            return affectNum += preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static synchronized int addGroup(String groupId) {
        String addShellSql = "INSERT INTO shellGroup (groupId) VALUES(?)";
        PreparedStatement preparedStatement = Db.getPreparedStatement(addShellSql);
        try {
            preparedStatement.setString(1, groupId);
            int affectNum = preparedStatement.executeUpdate();
            return affectNum;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static synchronized int renameGroup(String groupId, String newGroupId) {
        String addShellSql = "UPDATE shellEnv SET  value = ? || SUBSTR(value,LENGTH(?)+1) WHERE key = 'ENV_GROUP_ID' AND value LIKE ?";
        PreparedStatement preparedStatement = Db.getPreparedStatement(addShellSql);
        try {
            preparedStatement.setString(1, newGroupId);
            preparedStatement.setString(2, groupId);
            preparedStatement.setString(3, groupId + "%");
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement = Db.getPreparedStatement("UPDATE shellGroup SET  groupId = ? || SUBSTR(groupId,LENGTH(?)+1) WHERE groupId LIKE ?");
            preparedStatement.setString(1, newGroupId);
            preparedStatement.setString(2, groupId);
            preparedStatement.setString(3, groupId + "%");
            return affectNum += preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static synchronized int updateShellNote(String id, String note) {
        String updateNote = "UPDATE \"shell\" SET \"note\" = ?, \"updateTime\" = ? WHERE id = ?";
        String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        PreparedStatement preparedStatement = Db.getPreparedStatement(updateNote);
        try {
            preparedStatement.setString(1, note);
            preparedStatement.setString(2, updateTime);
            preparedStatement.setString(3, id);
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectNum;
        } catch (Exception e) {
            Log.error(e);
            return 0;
        }
    }

    public static synchronized String getShellNote(String id) {
        String selectShell = "SELECT note FROM shell WHERE id = ?";
        try {
            PreparedStatement preparedStatement = Db.getPreparedStatement(selectShell);
            preparedStatement.setString(1, id);
            String note = preparedStatement.executeQuery().getString("note");
            preparedStatement.close();
            return note;
        } catch (Exception e) {
            Log.error(e);
            return null;
        }
    }

    public static String[] getAllPlugin() {
        String selectPlugin = "SELECT pluginJarFile FROM plugin";
        ArrayList<String> pluginArrayList = new ArrayList<String>();
        try {
            Statement statement = Db.getStatement();
            ResultSet resultSet = statement.executeQuery(selectPlugin);
            while (resultSet.next()) {
                pluginArrayList.add(resultSet.getString("pluginJarFile"));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            Log.error(e);
        }
        return pluginArrayList.toArray(new String[0]);
    }

    public static synchronized int removePlugin(String jarFile) {
        String addShellSql = "DELETE FROM plugin WHERE pluginJarFile=?";
        PreparedStatement preparedStatement = Db.getPreparedStatement(addShellSql);
        try {
            preparedStatement.setString(1, jarFile);
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectNum;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static synchronized int addPlugin(String jarFile) {
        String addPluginSql = "INSERT INTO plugin (pluginJarFile) VALUES (?)";
        PreparedStatement preparedStatement = Db.getPreparedStatement(addPluginSql);
        try {
            preparedStatement.setString(1, jarFile);
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectNum;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static synchronized boolean addSetingKV(String key, String value) {
        if (Db.existsSetingKey(key)) {
            return Db.updateSetingKV(key, value);
        }
        String updateSetingSql = "INSERT INTO seting (\"key\", \"value\") VALUES (?, ?)";
        PreparedStatement preparedStatement = Db.getPreparedStatement(updateSetingSql);
        try {
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, value);
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectNum > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean updateSetingKV(String key, String value) {
        if (ApplicationContext.isOpenC("isSuperLog")) {
            Log.log(String.format("updateSetingKV key:%s value:%s", key, value), new Object[0]);
        }
        if (Db.existsSetingKey(key)) {
            String updateSetingSql = "UPDATE seting set value=? WHERE key=?";
            PreparedStatement preparedStatement = Db.getPreparedStatement(updateSetingSql);
            try {
                preparedStatement.setString(1, value);
                preparedStatement.setString(2, key);
                int affectNum = preparedStatement.executeUpdate();
                preparedStatement.close();
                return affectNum > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return Db.addSetingKV(key, value);
    }

    public static synchronized boolean removeSetingK(String key) {
        String updateSetingSql = "DELETE FROM seting WHERE key=?";
        PreparedStatement preparedStatement = Db.getPreparedStatement(updateSetingSql);
        try {
            preparedStatement.setString(1, key);
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectNum > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void clearShellEnv(String shellId) {
        String updateSetingSql = "DELETE FROM shellEnv WHERE shellId=?";
        PreparedStatement preparedStatement = Db.getPreparedStatement(updateSetingSql);
        try {
            preparedStatement.setString(1, shellId);
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSetingValue(String key, String defaultVal) {
        String ret = Db.getSetingValue(key);
        if (ret == null) {
            Db.updateSetingKV(key, defaultVal);
            ret = defaultVal;
        }
        return ret;
    }

    public static String getSetingValue(String key) {
        String getSetingValueSql = "SELECT value FROM seting WHERE key=?";
        try {
            PreparedStatement preparedStatement = Db.getPreparedStatement(getSetingValueSql);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            String value = resultSet.next() ? resultSet.getString("value") : null;
            resultSet.close();
            preparedStatement.close();
            return value;
        } catch (Exception e) {
            Log.error(e);
            return null;
        }
    }

    public static boolean getSetingBooleanValue(String key) {
        return Db.getSetingBooleanValue(key, false);
    }

    public static boolean getSetingBooleanValue(String key, boolean defaultValue) {
        String valueString = Db.getSetingValue(key);
        boolean ret = defaultValue;
        if (valueString != null) {
            try {
                ret = Boolean.valueOf(valueString);
            } catch (Exception e) {
                Log.error(e);
                Db.updateSetingKV(key, String.valueOf(ret));
            }
        } else {
            Db.updateSetingKV(key, String.valueOf(ret));
        }
        return ret;
    }

    public static int getSetingIntValue(String key) {
        return Db.getSetingIntValue(key, -1);
    }

    public static int getSetingIntValue(String key, int defaultValue) {
        String valueString = Db.getSetingValue(key);
        int ret = defaultValue;
        if (valueString != null) {
            try {
                ret = Integer.valueOf(valueString);
            } catch (Exception e) {
                Log.error(e);
                Db.updateSetingKV(key, String.valueOf(ret));
            }
        } else {
            Db.updateSetingKV(key, String.valueOf(ret));
        }
        return ret;
    }

    public static String tryGetSetingValue(String key, String ret) {
        String tRet = Db.getSetingValue(key);
        if (tRet == null) {
            return ret;
        }
        return tRet;
    }

    public static boolean existsSetingKey(String key) {
        String selectKeyNumSql = "SELECT COUNT(1) as c FROM seting WHERE key=?";
        try {
            PreparedStatement preparedStatement = Db.getPreparedStatement(selectKeyNumSql);
            preparedStatement.setString(1, key);
            int c = preparedStatement.executeQuery().getInt("c");
            preparedStatement.close();
            return c > 0;
        } catch (Exception e) {
            Log.error(e);
            return false;
        }
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        if (dbConn != null) {
            try {
                return dbConn.prepareStatement(sql);
            } catch (SQLException e) {
                Log.error(e);
                return null;
            }
        }
        return null;
    }

    public static Statement getStatement() {
        if (dbConn != null) {
            try {
                return dbConn.createStatement();
            } catch (SQLException e) {
                Log.error(e);
                return null;
            }
        }
        return null;
    }

    private static Vector<String> getAllcolumn(ResultSetMetaData metaData) {
        if (metaData != null) {
            Vector<String> columns = new Vector<String>();
            try {
                int columnNum = metaData.getColumnCount();
                for (int i = 0; i < columnNum; ++i) {
                    columns.add(metaData.getColumnName(i + 1));
                }
                return columns;
            } catch (Exception e) {
                Log.error(e);
                return columns;
            }
        }
        return null;
    }

    public static void Tclose() {
        try {
            if (dbConn != null && !dbConn.isClosed()) {
                dbConn.close();
            }
        } catch (SQLException e) {
            Log.error(e);
        }
    }

    static {
        try {
            Class.forName(Drivde);
            dbConn = DriverManager.getConnection(DB_URL);
            if (!Db.tableExists("shell")) {
                dbConn.createStatement().execute(CREATE_SHELL_TABLE);
            }
            if (!Db.tableExists("plugin")) {
                dbConn.createStatement().execute(CREATE_PLUGIN_TABLE);
            }
            if (!Db.tableExists("seting")) {
                dbConn.createStatement().execute(CREATE_SETING_TABLE);
            }
            if (!Db.tableExists("shellEnv")) {
                dbConn.createStatement().execute(CREATE_SHELLENV_TABLE);
            }
            if (!Db.tableExists("shellGroup")) {
                dbConn.createStatement().execute(CREATE_SHELLGROUP_TABLE);
            }
            dbConn.setAutoCommit(true);
            functions.addShutdownHook(Db.class, null);
        } catch (Exception e) {
            Log.error(e);
        }
    }
}

