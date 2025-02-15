/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.xmss.XMSSOid;

public final class DefaultXMSSMTOid
implements XMSSOid {
    private static final Map<String, DefaultXMSSMTOid> oidLookupTable;
    private final int oid;
    private final String stringRepresentation;

    private DefaultXMSSMTOid(int n, String string) {
        this.oid = n;
        this.stringRepresentation = string;
    }

    public static DefaultXMSSMTOid lookup(String string, int n, int n2, int n3, int n4, int n5) {
        if (string == null) {
            throw new NullPointerException("algorithmName == null");
        }
        return oidLookupTable.get(DefaultXMSSMTOid.createKey(string, n, n2, n3, n4, n5));
    }

    private static String createKey(String string, int n, int n2, int n3, int n4, int n5) {
        if (string == null) {
            throw new NullPointerException("algorithmName == null");
        }
        return string + "-" + n + "-" + n2 + "-" + n3 + "-" + n4 + "-" + n5;
    }

    public int getOid() {
        return this.oid;
    }

    public String toString() {
        return this.stringRepresentation;
    }

    static {
        HashMap<String, DefaultXMSSMTOid> hashMap = new HashMap<String, DefaultXMSSMTOid>();
        hashMap.put(DefaultXMSSMTOid.createKey("SHA-256", 32, 16, 67, 20, 2), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-256_W16_H20_D2"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA-256", 32, 16, 67, 20, 4), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-256_W16_H20_D4"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA-256", 32, 16, 67, 40, 2), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-256_W16_H40_D2"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA-256", 32, 16, 67, 40, 2), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-256_W16_H40_D4"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA-256", 32, 16, 67, 40, 4), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-256_W16_H40_D8"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA-256", 32, 16, 67, 60, 8), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-256_W16_H60_D3"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA-256", 32, 16, 67, 60, 6), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-256_W16_H60_D6"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA-256", 32, 16, 67, 60, 12), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-256_W16_H60_D12"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA2-512", 64, 16, 131, 20, 2), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-512_W16_H20_D2"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA2-512", 64, 16, 131, 20, 4), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-512_W16_H20_D4"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA2-512", 64, 16, 131, 40, 2), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-512_W16_H40_D2"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA2-512", 64, 16, 131, 40, 4), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-512_W16_H40_D4"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA2-512", 64, 16, 131, 40, 8), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-512_W16_H40_D8"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA2-512", 64, 16, 131, 60, 3), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-512_W16_H60_D3"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA2-512", 64, 16, 131, 60, 6), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-512_W16_H60_D6"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHA2-512", 64, 16, 131, 60, 12), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHA2-512_W16_H60_D12"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE128", 32, 16, 67, 20, 2), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE128_W16_H20_D2"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE128", 32, 16, 67, 20, 4), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE128_W16_H20_D4"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE128", 32, 16, 67, 40, 2), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE128_W16_H40_D2"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE128", 32, 16, 67, 40, 4), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE128_W16_H40_D4"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE128", 32, 16, 67, 40, 8), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE128_W16_H40_D8"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE128", 32, 16, 67, 60, 3), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE128_W16_H60_D3"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE128", 32, 16, 67, 60, 6), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE128_W16_H60_D6"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE128", 32, 16, 67, 60, 12), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE128_W16_H60_D12"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE256", 64, 16, 131, 20, 2), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE256_W16_H20_D2"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE256", 64, 16, 131, 20, 4), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE256_W16_H20_D4"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE256", 64, 16, 131, 40, 2), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE256_W16_H40_D2"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE256", 64, 16, 131, 40, 4), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE256_W16_H40_D4"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE256", 64, 16, 131, 40, 8), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE256_W16_H40_D8"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE256", 64, 16, 131, 60, 3), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE256_W16_H60_D3"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE256", 64, 16, 131, 60, 6), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE256_W16_H60_D6"));
        hashMap.put(DefaultXMSSMTOid.createKey("SHAKE256", 64, 16, 131, 60, 12), new DefaultXMSSMTOid(0x1000001, "XMSSMT_SHAKE256_W16_H60_D12"));
        oidLookupTable = Collections.unmodifiableMap(hashMap);
    }
}

