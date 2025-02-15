/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.eac.ECDSAPublicKey;
import org.bouncycastle.asn1.eac.RSAPublicKey;

public abstract class PublicKeyDataObject
extends ASN1Object {
    public static PublicKeyDataObject getInstance(Object object) {
        if (object instanceof PublicKeyDataObject) {
            return (PublicKeyDataObject)object;
        }
        if (object != null) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(object);
            ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            if (aSN1ObjectIdentifier.on(EACObjectIdentifiers.id_TA_ECDSA)) {
                return new ECDSAPublicKey(aSN1Sequence);
            }
            return new RSAPublicKey(aSN1Sequence);
        }
        return null;
    }

    public abstract ASN1ObjectIdentifier getUsage();
}

