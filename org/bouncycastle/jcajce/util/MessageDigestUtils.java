/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.util;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.gnu.GNUObjectIdentifiers;
import org.bouncycastle.asn1.iso.ISOIECObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;

public class MessageDigestUtils {
    private static Map<ASN1ObjectIdentifier, String> digestOidMap = new HashMap<ASN1ObjectIdentifier, String>();

    public static String getDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String string = digestOidMap.get(aSN1ObjectIdentifier);
        if (string != null) {
            return string;
        }
        return aSN1ObjectIdentifier.getId();
    }

    static {
        digestOidMap.put(PKCSObjectIdentifiers.md2, "MD2");
        digestOidMap.put(PKCSObjectIdentifiers.md4, "MD4");
        digestOidMap.put(PKCSObjectIdentifiers.md5, "MD5");
        digestOidMap.put(OIWObjectIdentifiers.idSHA1, "SHA-1");
        digestOidMap.put(NISTObjectIdentifiers.id_sha224, "SHA-224");
        digestOidMap.put(NISTObjectIdentifiers.id_sha256, "SHA-256");
        digestOidMap.put(NISTObjectIdentifiers.id_sha384, "SHA-384");
        digestOidMap.put(NISTObjectIdentifiers.id_sha512, "SHA-512");
        digestOidMap.put(TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD-128");
        digestOidMap.put(TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD-160");
        digestOidMap.put(TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD-128");
        digestOidMap.put(ISOIECObjectIdentifiers.ripemd128, "RIPEMD-128");
        digestOidMap.put(ISOIECObjectIdentifiers.ripemd160, "RIPEMD-160");
        digestOidMap.put(CryptoProObjectIdentifiers.gostR3411, "GOST3411");
        digestOidMap.put(GNUObjectIdentifiers.Tiger_192, "Tiger");
        digestOidMap.put(ISOIECObjectIdentifiers.whirlpool, "Whirlpool");
        digestOidMap.put(NISTObjectIdentifiers.id_sha3_224, "SHA3-224");
        digestOidMap.put(NISTObjectIdentifiers.id_sha3_256, "SHA3-256");
        digestOidMap.put(NISTObjectIdentifiers.id_sha3_384, "SHA3-384");
        digestOidMap.put(NISTObjectIdentifiers.id_sha3_512, "SHA3-512");
        digestOidMap.put(GMObjectIdentifiers.sm3, "SM3");
    }
}

