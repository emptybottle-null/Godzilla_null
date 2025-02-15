/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.digest;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.codec.digest.Sha2Crypt;
import org.apache.commons.codec.digest.UnixCrypt;

public class Crypt {
    public static String crypt(byte[] keyBytes) {
        return Crypt.crypt(keyBytes, null);
    }

    public static String crypt(byte[] keyBytes, String salt) {
        if (salt == null) {
            return Sha2Crypt.sha512Crypt(keyBytes);
        }
        if (salt.startsWith("$6$")) {
            return Sha2Crypt.sha512Crypt(keyBytes, salt);
        }
        if (salt.startsWith("$5$")) {
            return Sha2Crypt.sha256Crypt(keyBytes, salt);
        }
        if (salt.startsWith("$1$")) {
            return Md5Crypt.md5Crypt(keyBytes, salt);
        }
        return UnixCrypt.crypt(keyBytes, salt);
    }

    public static String crypt(String key) {
        return Crypt.crypt(key, null);
    }

    public static String crypt(String key, String salt) {
        return Crypt.crypt(key.getBytes(Charsets.UTF_8), salt);
    }
}

