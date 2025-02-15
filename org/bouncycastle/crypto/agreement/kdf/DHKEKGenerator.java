/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement.kdf;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.agreement.kdf.DHKDFParameters;
import org.bouncycastle.util.Pack;

public class DHKEKGenerator
implements DerivationFunction {
    private final Digest digest;
    private ASN1ObjectIdentifier algorithm;
    private int keySize;
    private byte[] z;
    private byte[] partyAInfo;

    public DHKEKGenerator(Digest digest) {
        this.digest = digest;
    }

    public void init(DerivationParameters derivationParameters) {
        DHKDFParameters dHKDFParameters = (DHKDFParameters)derivationParameters;
        this.algorithm = dHKDFParameters.getAlgorithm();
        this.keySize = dHKDFParameters.getKeySize();
        this.z = dHKDFParameters.getZ();
        this.partyAInfo = dHKDFParameters.getExtraInfo();
    }

    public Digest getDigest() {
        return this.digest;
    }

    public int generateBytes(byte[] byArray, int n, int n2) throws DataLengthException, IllegalArgumentException {
        if (byArray.length - n2 < n) {
            throw new OutputLengthException("output buffer too small");
        }
        long l = n2;
        int n3 = this.digest.getDigestSize();
        if (l > 0x1FFFFFFFFL) {
            throw new IllegalArgumentException("Output length too large");
        }
        int n4 = (int)((l + (long)n3 - 1L) / (long)n3);
        byte[] byArray2 = new byte[this.digest.getDigestSize()];
        int n5 = 1;
        for (int i = 0; i < n4; ++i) {
            this.digest.update(this.z, 0, this.z.length);
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.add(this.algorithm);
            aSN1EncodableVector2.add(new DEROctetString(Pack.intToBigEndian(n5)));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
            if (this.partyAInfo != null) {
                aSN1EncodableVector.add(new DERTaggedObject(true, 0, new DEROctetString(this.partyAInfo)));
            }
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, new DEROctetString(Pack.intToBigEndian(this.keySize))));
            try {
                byte[] byArray3 = new DERSequence(aSN1EncodableVector).getEncoded("DER");
                this.digest.update(byArray3, 0, byArray3.length);
            } catch (IOException iOException) {
                throw new IllegalArgumentException("unable to encode parameter info: " + iOException.getMessage());
            }
            this.digest.doFinal(byArray2, 0);
            if (n2 > n3) {
                System.arraycopy(byArray2, 0, byArray, n, n3);
                n += n3;
                n2 -= n3;
            } else {
                System.arraycopy(byArray2, 0, byArray, n, n2);
            }
            ++n5;
        }
        this.digest.reset();
        return (int)l;
    }
}

