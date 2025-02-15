/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac.operator.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;

abstract class EACHelper {
    private static final Hashtable sigNames = new Hashtable();

    EACHelper() {
    }

    public Signature getSignature(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws NoSuchProviderException, NoSuchAlgorithmException {
        return this.createSignature((String)sigNames.get(aSN1ObjectIdentifier));
    }

    protected abstract Signature createSignature(String var1) throws NoSuchProviderException, NoSuchAlgorithmException;

    static {
        sigNames.put(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1, "SHA1withRSA");
        sigNames.put(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256, "SHA256withRSA");
        sigNames.put(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_1, "SHA1withRSAandMGF1");
        sigNames.put(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_256, "SHA256withRSAandMGF1");
        sigNames.put(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_512, "SHA512withRSA");
        sigNames.put(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_512, "SHA512withRSAandMGF1");
        sigNames.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_1, "SHA1withECDSA");
        sigNames.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_224, "SHA224withECDSA");
        sigNames.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_256, "SHA256withECDSA");
        sigNames.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_384, "SHA384withECDSA");
        sigNames.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_512, "SHA512withECDSA");
    }
}

