/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.util.Strings;

public class DESUtil {
    private static final Set<String> des = new HashSet<String>();

    public static boolean isDES(String string) {
        String string2 = Strings.toUpperCase(string);
        return des.contains(string2);
    }

    public static void setOddParity(byte[] byArray) {
        for (int i = 0; i < byArray.length; ++i) {
            byte by = byArray[i];
            byArray[i] = (byte)(by & 0xFE | (by >> 1 ^ by >> 2 ^ by >> 3 ^ by >> 4 ^ by >> 5 ^ by >> 6 ^ by >> 7 ^ 1) & 1);
        }
    }

    static {
        des.add("DES");
        des.add("DESEDE");
        des.add(OIWObjectIdentifiers.desCBC.getId());
        des.add(PKCSObjectIdentifiers.des_EDE3_CBC.getId());
        des.add(PKCSObjectIdentifiers.des_EDE3_CBC.getId());
        des.add(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId());
    }
}

