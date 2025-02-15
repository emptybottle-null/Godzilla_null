/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.shell.cache;

import core.imp.Payload;
import core.shell.ShellEntity;
import core.shell.cache.PayloadCacheHandler;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import util.Log;
import util.RC4;
import util.functions;
import util.http.ReqParameter;

public class UsePayloadCacheHandler
extends PayloadCacheHandler {
    public UsePayloadCacheHandler(ShellEntity entity, Payload payload) {
        super(entity, payload);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] evalFunc(byte[] realResult, String className, String funcName, ReqParameter parameter) {
        block21: {
            if (className == null && funcName != null) {
                try {
                    StackTraceElement[] stack = Thread.currentThread().getStackTrace();
                    String methodName = stack[3].getMethodName();
                    if (Arrays.binarySearch(blackMethod, methodName) >= 0) break block21;
                    if ("downloadFile".equals(methodName)) {
                        RC4 rC4 = this.rc4;
                        synchronized (rC4) {
                            byte[] ret;
                            File file = new File(this.currentDirectory + functions.byteArrayToHex(functions.md5(parameter.getParameterByteArray("fileName"))));
                            try (FileInputStream fileInputStream = new FileInputStream(file);){
                                ret = functions.gzipD(this.rc4.decryptMessage(functions.readInputStream(fileInputStream), this.shellId));
                            } catch (Throwable e) {
                                return "The cache file does not exist".getBytes();
                            }
                            return ret == null ? new byte[]{} : ret;
                        }
                    }
                    this.payload.fillParameter(className, funcName, parameter);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byteArrayOutputStream.write(funcName.getBytes());
                    byteArrayOutputStream.write(parameter.formatEx());
                    byte[] ret = this.cacheDb.getSetingValue(functions.byteArrayToHex(functions.md5(byteArrayOutputStream.toByteArray())));
                    return ret == null ? "The operation has no cache".getBytes() : functions.gzipD(ret);
                } catch (Exception e) {
                    Log.error(e);
                }
            }
        }
        return "Payload does not cache the plugin return".getBytes();
    }
}

