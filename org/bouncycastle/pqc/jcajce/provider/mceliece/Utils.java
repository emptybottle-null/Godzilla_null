/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.mceliece;

import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.util.DigestFactory;

class Utils {
    Utils() {
    }

    static AlgorithmIdentifier getDigAlgId(String string) {
        if (string.equals("SHA-1")) {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        }
        if (string.equals("SHA-224")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, DERNull.INSTANCE);
        }
        if (string.equals("SHA-256")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE);
        }
        if (string.equals("SHA-384")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, DERNull.INSTANCE);
        }
        if (string.equals("SHA-512")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, DERNull.INSTANCE);
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + string);
    }

    static Digest getDigest(AlgorithmIdentifier algorithmIdentifier) {
        if (algorithmIdentifier.getAlgorithm().equals(OIWObjectIdentifiers.idSHA1)) {
            return DigestFactory.createSHA1();
        }
        if (algorithmIdentifier.getAlgorithm().equals(NISTObjectIdentifiers.id_sha224)) {
            return DigestFactory.createSHA224();
        }
        if (algorithmIdentifier.getAlgorithm().equals(NISTObjectIdentifiers.id_sha256)) {
            return DigestFactory.createSHA256();
        }
        if (algorithmIdentifier.getAlgorithm().equals(NISTObjectIdentifiers.id_sha384)) {
            return DigestFactory.createSHA384();
        }
        if (algorithmIdentifier.getAlgorithm().equals(NISTObjectIdentifiers.id_sha512)) {
            return DigestFactory.createSHA512();
        }
        throw new IllegalArgumentException("unrecognised OID in digest algorithm identifier: " + algorithmIdentifier.getAlgorithm());
    }
}

