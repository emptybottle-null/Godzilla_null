/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.shell;

import core.ApplicationContext;
import core.Db;
import core.Encoding;
import core.imp.Cryption;
import core.imp.Payload;
import core.shell.cache.CachePayload;
import core.ui.ShellManage;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import util.Log;
import util.functions;
import util.http.Http;

public class ShellEntity {
    private String url = "";
    private String password = "";
    private String secretKey = "";
    private String payload = "";
    private String cryption = "";
    private String remark = "";
    private String encoding = "";
    private Map<String, String> headers = new HashMap<String, String>();
    private String reqLeft = "";
    private String reqRight = "";
    private int connTimeout = 60000;
    private int readTimeout = 60000;
    private String proxyType = "";
    private String proxyHost = "";
    private int proxyPort = 8888;
    private Cryption cryptionModel;
    private Payload payloadModel;
    private String id = "";
    private ShellManage frame;
    private Encoding encodingModule;
    private Encoding dbEncodingModule;
    private String dbEncoding;
    private Http http;
    private boolean isSendLRReqData;
    private boolean useCache;

    public ShellEntity(boolean useCache) {
        this.useCache = useCache;
    }

    public ShellEntity() {
        this(false);
    }

    public boolean initShellOpertion() {
        boolean state = false;
        try {
            this.http = ApplicationContext.getHttp(this);
            if (this.isUseCache()) {
                this.payloadModel = CachePayload.openUseCachePayload(this, ApplicationContext.getPayload(this.payload).getClass());
                if (this.payloadModel != null) {
                    this.payloadModel.init(this);
                    return true;
                }
                return false;
            }
            this.payloadModel = ApplicationContext.isOpenCache() ? CachePayload.openCachePayload(this, ApplicationContext.getPayload(this.payload).getClass()) : ApplicationContext.getPayload(this.payload);
            this.cryptionModel = ApplicationContext.getCryption(this.payload, this.cryption);
            this.cryptionModel.init(this);
            if (this.cryptionModel.check()) {
                this.payloadModel.init(this);
                if (this.payloadModel.test()) {
                    state = true;
                } else {
                    Log.error("payload Initialize Fail !");
                }
            } else {
                Log.error("cryption Initialize Fail !");
            }
        } catch (Throwable e) {
            Log.error(e);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(stream);
            e.printStackTrace(printStream);
            printStream.flush();
            printStream.close();
            Log.log(new String(stream.toByteArray()), new Object[0]);
            return state;
        }
        return state;
    }

    public Http getHttp() {
        return this.http;
    }

    public Cryption getCryptionModule() {
        return this.cryptionModel;
    }

    public Payload getPayloadModule() {
        return this.payloadModel;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getSecretKeyX() {
        return functions.md5(this.getSecretKey()).substring(0, 16);
    }

    public String getPayload() {
        return this.payload;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public synchronized Encoding getEncodingModule() {
        if (this.encodingModule == null) {
            this.encodingModule = Encoding.getEncoding(this.getEncoding());
        }
        return this.encodingModule;
    }

    public synchronized String getDbEncoding() {
        if (this.dbEncoding == null) {
            this.dbEncoding = "UTF-8";
        }
        return this.dbEncoding;
    }

    public synchronized Encoding getDbEncodingModule() {
        if (this.dbEncodingModule == null) {
            this.dbEncodingModule = Encoding.getEncoding(this.getDbEncoding());
        }
        return this.dbEncodingModule;
    }

    public String getProxyType() {
        return this.proxyType;
    }

    public String getProxyHost() {
        return this.proxyHost;
    }

    public int getProxyPort() {
        return this.proxyPort;
    }

    public String getCryption() {
        return this.cryption;
    }

    public void setCryption(String cryption) {
        this.cryption = cryption;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setPayload(String Payload2) {
        this.payload = Payload2;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public int getConnTimeout() {
        return this.connTimeout;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public void setConnTimeout(int connTimeout) {
        this.connTimeout = connTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getHeaderS() {
        StringBuilder builder = new StringBuilder();
        for (String key : this.headers.keySet()) {
            String value = this.headers.get(key);
            builder.append(key);
            builder.append(": ");
            builder.append(value);
            builder.append("\r\n");
        }
        return builder.toString();
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ShellManage getFrame() {
        return this.frame;
    }

    public void setFrame(ShellManage frame) {
        this.frame = frame;
    }

    public void setHeader(String reqString) {
        if (reqString != null) {
            String[] reqLines = reqString.split("\n");
            this.headers = new Hashtable<String, String>();
            for (int i = 0; i < reqLines.length; ++i) {
                int index;
                if (reqLines[i].trim().isEmpty() || (index = reqLines[i].indexOf(":")) <= 1) continue;
                String keyName = reqLines[i].substring(0, index).trim();
                String keyValue = reqLines[i].substring(index + 1).trim();
                this.headers.put(keyName, keyValue);
            }
        }
    }

    public String getReqLeft() {
        return this.reqLeft;
    }

    public void setReqLeft(String reqLeft) {
        this.reqLeft = reqLeft;
    }

    public String getReqRight() {
        return this.reqRight;
    }

    public void setReqRight(String reqRight) {
        this.reqRight = reqRight;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSendLRReqData() {
        return this.cryptionModel.isSendRLData();
    }

    public boolean setEnv(String key, String value) {
        if (ApplicationContext.isOpenC("isSuperLog")) {
            Log.log(String.format("updateShellEnv id:%s key:%s value:%s", this.getId(), key, value), new Object[0]);
        }
        if (this.existsSetingKey(key)) {
            String updateShellEnvSql = "UPDATE shellEnv set value=? WHERE shellId=? and key=?";
            PreparedStatement preparedStatement = Db.getPreparedStatement(updateShellEnvSql);
            try {
                preparedStatement.setString(1, value);
                preparedStatement.setString(2, this.getId());
                preparedStatement.setString(3, key);
                int affectNum = preparedStatement.executeUpdate();
                preparedStatement.close();
                return affectNum > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        String updateSetingSql = "INSERT INTO shellEnv (\"shellId\",\"key\", \"value\") VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = Db.getPreparedStatement(updateSetingSql);
        try {
            preparedStatement.setString(1, this.getId());
            preparedStatement.setString(2, key);
            preparedStatement.setString(3, value);
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectNum > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getEnv(String key, String defaultValue) {
        String getShellEnvSql = "SELECT value FROM shellEnv WHERE shellId=? and key=?";
        if (this.existsSetingKey(key)) {
            try {
                PreparedStatement preparedStatement = Db.getPreparedStatement(getShellEnvSql);
                preparedStatement.setString(1, this.getId());
                preparedStatement.setString(2, key);
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
        this.setEnv(key, defaultValue);
        return defaultValue;
    }

    public void setGroup(String groupId) {
        this.setEnv("ENV_GROUP_ID", groupId);
    }

    public String getGroup() {
        return this.getEnv("ENV_GROUP_ID", "/");
    }

    public boolean removeEnv(String key) {
        String updateSetingSql = "DELETE FROM shellEnv WHERE shellId=? and key=?";
        PreparedStatement preparedStatement = Db.getPreparedStatement(updateSetingSql);
        try {
            preparedStatement.setString(1, this.getId());
            preparedStatement.setString(2, key);
            int affectNum = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectNum > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsSetingKey(String key) {
        String selectKeyNumSql = "SELECT COUNT(1) as c FROM shellEnv WHERE shellId=? and key=?";
        try {
            PreparedStatement preparedStatement = Db.getPreparedStatement(selectKeyNumSql);
            preparedStatement.setString(1, this.getId());
            preparedStatement.setString(2, key);
            int c = preparedStatement.executeQuery().getInt("c");
            preparedStatement.close();
            return c > 0;
        } catch (Exception e) {
            Log.error(e);
            return false;
        }
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    public boolean isUseCache() {
        return this.useCache;
    }

    public String toString() {
        return "ShellEntity [id=" + this.id + ", url=" + this.url + ", password=" + this.password + ", secretKey=" + this.secretKey + ", payload=" + this.payload + ", cryption=" + this.cryption + ", remark=" + this.remark + ", encoding=" + this.encoding + ", headers=" + this.headers + ", reqLeft=" + this.reqLeft + ", reqRight=" + this.reqRight + ", connTimeout=" + this.connTimeout + ", readTimeout=" + this.readTimeout + ", proxyType=" + this.proxyType + ", proxyHost=" + this.proxyHost + ", proxyPort=" + this.proxyPort + "]";
    }
}

