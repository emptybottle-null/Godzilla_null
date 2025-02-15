/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.xmss;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;

class DigestUtil {
    DigestUtil() {
    }

    static Digest getDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_sha256)) {
            return new SHA256Digest();
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_sha512)) {
            return new SHA512Digest();
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_shake128)) {
            return new SHAKEDigest(128);
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_shake256)) {
            return new SHAKEDigest(256);
        }
        throw new IllegalArgumentException("unrecognized digest OID: " + aSN1ObjectIdentifier);
    }

    public static byte[] getDigestResult(Digest digest) {
        byte[] byArray = new byte[DigestUtil.getDigestSize(digest)];
        if (digest instanceof Xof) {
            ((Xof)digest).doFinal(byArray, 0, byArray.length);
        } else {
            digest.doFinal(byArray, 0);
        }
        return byArray;
    }

    public static int getDigestSize(Digest digest) {
        if (digest instanceof Xof) {
            return digest.getDigestSize() * 2;
        }
        return digest.getDigestSize();
    }

    public static String getXMSSDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_sha256)) {
            return "SHA256";
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_sha512)) {
            return "SHA512";
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_shake128)) {
            return "SHAKE128";
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_shake256)) {
            return "SHAKE256";
        }
        throw new IllegalArgumentException("unrecognized digest OID: " + aSN1ObjectIdentifier);
    }
}

