/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.xmss.XMSSOid;

public final class DefaultXMSSOid
implements XMSSOid {
    private static final Map<String, DefaultXMSSOid> oidLookupTable;
    private final int oid;
    private final String stringRepresentation;

    private DefaultXMSSOid(int n, String string) {
        this.oid = n;
        this.stringRepresentation = string;
    }

    public static DefaultXMSSOid lookup(String string, int n, int n2, int n3, int n4) {
        if (string == null) {
            throw new NullPointerException("algorithmName == null");
        }
        return oidLookupTable.get(DefaultXMSSOid.createKey(string, n, n2, n3, n4));
    }

    private static String createKey(String string, int n, int n2, int n3, int n4) {
        if (string == null) {
            throw new NullPointerException("algorithmName == null");
        }
        return string + "-" + n + "-" + n2 + "-" + n3 + "-" + n4;
    }

    public int getOid() {
        return this.oid;
    }

    public String toString() {
        return this.stringRepresentation;
    }

    static {
        HashMap<String, DefaultXMSSOid> hashMap = new HashMap<String, DefaultXMSSOid>();
        hashMap.put(DefaultXMSSOid.createKey("SHA-256", 32, 16, 67, 10), new DefaultXMSSOid(0x1000001, "XMSS_SHA2-256_W16_H10"));
        hashMap.put(DefaultXMSSOid.createKey("SHA-256", 32, 16, 67, 16), new DefaultXMSSOid(0x2000002, "XMSS_SHA2-256_W16_H16"));
        hashMap.put(DefaultXMSSOid.createKey("SHA-256", 32, 16, 67, 20), new DefaultXMSSOid(0x3000003, "XMSS_SHA2-256_W16_H20"));
        hashMap.put(DefaultXMSSOid.createKey("SHA-512", 64, 16, 131, 10), new DefaultXMSSOid(0x4000004, "XMSS_SHA2-512_W16_H10"));
        hashMap.put(DefaultXMSSOid.createKey("SHA-512", 64, 16, 131, 16), new DefaultXMSSOid(0x5000005, "XMSS_SHA2-512_W16_H16"));
        hashMap.put(DefaultXMSSOid.createKey("SHA-512", 64, 16, 131, 20), new DefaultXMSSOid(0x6000006, "XMSS_SHA2-512_W16_H20"));
        hashMap.put(DefaultXMSSOid.createKey("SHAKE128", 32, 16, 67, 10), new DefaultXMSSOid(0x7000007, "XMSS_SHAKE128_W16_H10"));
        hashMap.put(DefaultXMSSOid.createKey("SHAKE128", 32, 16, 67, 16), new DefaultXMSSOid(0x8000008, "XMSS_SHAKE128_W16_H16"));
        hashMap.put(DefaultXMSSOid.createKey("SHAKE128", 32, 16, 67, 20), new DefaultXMSSOid(0x9000009, "XMSS_SHAKE128_W16_H20"));
        hashMap.put(DefaultXMSSOid.createKey("SHAKE256", 64, 16, 131, 10), new DefaultXMSSOid(0xA00000A, "XMSS_SHAKE256_W16_H10"));
        hashMap.put(DefaultXMSSOid.createKey("SHAKE256", 64, 16, 131, 16), new DefaultXMSSOid(0xB00000B, "XMSS_SHAKE256_W16_H16"));
        hashMap.put(DefaultXMSSOid.createKey("SHAKE256", 64, 16, 131, 20), new DefaultXMSSOid(0xC00000C, "XMSS_SHAKE256_W16_H20"));
        oidLookupTable = Collections.unmodifiableMap(hashMap);
    }
}

