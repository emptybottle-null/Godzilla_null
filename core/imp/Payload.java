/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.imp;

import core.shell.ShellEntity;
import java.util.Map;
import util.http.ReqParameter;

public interface Payload {
    public void init(ShellEntity var1);

    public byte[] getPayload();

    public String getFile(String var1);

    public String[] listFileRoot();

    public byte[] downloadFile(String var1);

    public String getOsInfo();

    public String getBasicsInfo();

    public boolean include(String var1, byte[] var2);

    public void fillParameter(String var1, String var2, ReqParameter var3);

    public byte[] evalFunc(String var1, String var2, ReqParameter var3);

    public String execCommand(String var1);

    public boolean uploadFile(String var1, byte[] var2);

    public boolean copyFile(String var1, String var2);

    public boolean deleteFile(String var1);

    public boolean moveFile(String var1, String var2);

    public boolean newFile(String var1);

    public boolean newDir(String var1);

    public boolean test();

    public boolean fileRemoteDown(String var1, String var2);

    public boolean setFileAttr(String var1, String var2, String var3);

    public boolean close();

    public String execSql(String var1, String var2, int var3, String var4, String var5, String var6, Map var7, String var8);

    public String[] getAllDatabaseType();

    public String currentDir();

    public String currentUserName();

    public String bigFileUpload(String var1, int var2, byte[] var3);

    public String getTempDirectory();

    public byte[] bigFileDownload(String var1, int var2, int var3);

    public int getFileSize(String var1);

    public boolean isWindows();

    public boolean isAlive();

    public boolean isX64();
}

