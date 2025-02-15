/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.shell.cache;

import core.imp.Payload;
import core.shell.ShellEntity;
import core.shell.cache.PayloadCacheHandler;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import util.RC4;
import util.functions;
import util.http.ReqParameter;

public class UpdatePayloadCacheHandler
extends PayloadCacheHandler {
    public UpdatePayloadCacheHandler(ShellEntity entity, Payload payload) {
        super(entity, payload);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] evalFunc(byte[] realResult, String className, String funcName, ReqParameter parameter) {
        block19: {
            if (className == null && funcName != null && realResult != null && realResult.length > 0) {
                try {
                    StackTraceElement[] stack = Thread.currentThread().getStackTrace();
                    String methodName = stack[3].getMethodName();
                    if (Arrays.binarySearch(blackMethod, methodName) >= 0) break block19;
                    if ("downloadFile".equals(methodName)) {
                        RC4 rC4 = this.rc4;
                        synchronized (rC4) {
                            File file = new File(this.currentDirectory + functions.byteArrayToHex(functions.md5(parameter.getParameterByteArray("fileName"))));
                            try (FileOutputStream fileOutputStream = new FileOutputStream(file);){
                                fileOutputStream.write(this.rc4.encryptMessage(functions.gzipE(realResult), this.shellId));
                            }
                            break block19;
                        }
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byteArrayOutputStream.write(funcName.getBytes());
                    byteArrayOutputStream.write(parameter.formatEx());
                    this.cacheDb.updateSetingKV(functions.byteArrayToHex(functions.md5(byteArrayOutputStream.toByteArray())), functions.gzipE(realResult));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return realResult;
    }
}

