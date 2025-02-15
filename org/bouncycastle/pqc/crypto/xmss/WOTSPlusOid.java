/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.xmss.XMSSOid;

final class WOTSPlusOid
implements XMSSOid {
    private static final Map<String, WOTSPlusOid> oidLookupTable;
    private final int oid;
    private final String stringRepresentation;

    private WOTSPlusOid(int n, String string) {
        this.oid = n;
        this.stringRepresentation = string;
    }

    protected static WOTSPlusOid lookup(String string, int n, int n2, int n3) {
        if (string == null) {
            throw new NullPointerException("algorithmName == null");
        }
        return oidLookupTable.get(WOTSPlusOid.createKey(string, n, n2, n3));
    }

    private static String createKey(String string, int n, int n2, int n3) {
        if (string == null) {
            throw new NullPointerException("algorithmName == null");
        }
        return string + "-" + n + "-" + n2 + "-" + n3;
    }

    public int getOid() {
        return this.oid;
    }

    public String toString() {
        return this.stringRepresentation;
    }

    static {
        HashMap<String, WOTSPlusOid> hashMap = new HashMap<String, WOTSPlusOid>();
        hashMap.put(WOTSPlusOid.createKey("SHA-256", 32, 16, 67), new WOTSPlusOid(0x1000001, "WOTSP_SHA2-256_W16"));
        hashMap.put(WOTSPlusOid.createKey("SHA-512", 64, 16, 131), new WOTSPlusOid(0x2000002, "WOTSP_SHA2-512_W16"));
        hashMap.put(WOTSPlusOid.createKey("SHAKE128", 32, 16, 67), new WOTSPlusOid(0x3000003, "WOTSP_SHAKE128_W16"));
        hashMap.put(WOTSPlusOid.createKey("SHAKE256", 64, 16, 131), new WOTSPlusOid(0x4000004, "WOTSP_SHAKE256_W16"));
        oidLookupTable = Collections.unmodifiableMap(hashMap);
    }
}

