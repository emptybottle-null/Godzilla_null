/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.newhope;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.bouncycastle.pqc.jcajce.interfaces.NHPrivateKey;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class BCNHPrivateKey
implements NHPrivateKey {
    private static final long serialVersionUID = 1L;
    private final NHPrivateKeyParameters params;

    public BCNHPrivateKey(NHPrivateKeyParameters nHPrivateKeyParameters) {
        this.params = nHPrivateKeyParameters;
    }

    public BCNHPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        this.params = new NHPrivateKeyParameters(BCNHPrivateKey.convert(ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets()));
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof BCNHPrivateKey)) {
            return false;
        }
        BCNHPrivateKey bCNHPrivateKey = (BCNHPrivateKey)object;
        return Arrays.areEqual(this.params.getSecData(), bCNHPrivateKey.params.getSecData());
    }

    public int hashCode() {
        return Arrays.hashCode(this.params.getSecData());
    }

    public final String getAlgorithm() {
        return "NH";
    }

    public byte[] getEncoded() {
        try {
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PQCObjectIdentifiers.newHope);
            short[] sArray = this.params.getSecData();
            byte[] byArray = new byte[sArray.length * 2];
            for (int i = 0; i != sArray.length; ++i) {
                Pack.shortToLittleEndian(sArray[i], byArray, i * 2);
            }
            PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(algorithmIdentifier, new DEROctetString(byArray));
            return privateKeyInfo.getEncoded();
        } catch (IOException iOException) {
            return null;
        }
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public short[] getSecretData() {
        return this.params.getSecData();
    }

    CipherParameters getKeyParams() {
        return this.params;
    }

    private static short[] convert(byte[] byArray) {
        short[] sArray = new short[byArray.length / 2];
        for (int i = 0; i != sArray.length; ++i) {
            sArray[i] = Pack.littleEndianToShort(byArray, i * 2);
        }
        return sArray;
    }
}

