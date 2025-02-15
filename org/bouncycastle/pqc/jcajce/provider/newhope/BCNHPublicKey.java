/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.newhope;

import java.io.IOException;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.crypto.newhope.NHPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.interfaces.NHPublicKey;
import org.bouncycastle.util.Arrays;

public class BCNHPublicKey
implements NHPublicKey {
    private static final long serialVersionUID = 1L;
    private final NHPublicKeyParameters params;

    public BCNHPublicKey(NHPublicKeyParameters nHPublicKeyParameters) {
        this.params = nHPublicKeyParameters;
    }

    public BCNHPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.params = new NHPublicKeyParameters(subjectPublicKeyInfo.getPublicKeyData().getBytes());
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof BCNHPublicKey)) {
            return false;
        }
        BCNHPublicKey bCNHPublicKey = (BCNHPublicKey)object;
        return Arrays.areEqual(this.params.getPubData(), bCNHPublicKey.params.getPubData());
    }

    public int hashCode() {
        return Arrays.hashCode(this.params.getPubData());
    }

    public final String getAlgorithm() {
        return "NH";
    }

    public byte[] getEncoded() {
        try {
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PQCObjectIdentifiers.newHope);
            SubjectPublicKeyInfo subjectPublicKeyInfo = new SubjectPublicKeyInfo(algorithmIdentifier, this.params.getPubData());
            return subjectPublicKeyInfo.getEncoded();
        } catch (IOException iOException) {
            return null;
        }
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getPublicData() {
        return this.params.getPubData();
    }

    CipherParameters getKeyParams() {
        return this.params;
    }
}

