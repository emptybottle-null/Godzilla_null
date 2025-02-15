/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;

public class DSADigestSigner
implements Signer {
    private final Digest digest;
    private final DSA dsaSigner;
    private boolean forSigning;

    public DSADigestSigner(DSA dSA, Digest digest) {
        this.digest = digest;
        this.dsaSigner = dSA;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forSigning = bl;
        AsymmetricKeyParameter asymmetricKeyParameter = cipherParameters instanceof ParametersWithRandom ? (AsymmetricKeyParameter)((ParametersWithRandom)cipherParameters).getParameters() : (AsymmetricKeyParameter)cipherParameters;
        if (bl && !asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("Signing Requires Private Key.");
        }
        if (!bl && asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("Verification Requires Public Key.");
        }
        this.reset();
        this.dsaSigner.init(bl, cipherParameters);
    }

    public void update(byte by) {
        this.digest.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.digest.update(byArray, n, n2);
    }

    public byte[] generateSignature() {
        if (!this.forSigning) {
            throw new IllegalStateException("DSADigestSigner not initialised for signature generation.");
        }
        byte[] byArray = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray, 0);
        BigInteger[] bigIntegerArray = this.dsaSigner.generateSignature(byArray);
        try {
            return this.derEncode(bigIntegerArray[0], bigIntegerArray[1]);
        } catch (IOException iOException) {
            throw new IllegalStateException("unable to encode signature");
        }
    }

    public boolean verifySignature(byte[] byArray) {
        if (this.forSigning) {
            throw new IllegalStateException("DSADigestSigner not initialised for verification");
        }
        byte[] byArray2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray2, 0);
        try {
            BigInteger[] bigIntegerArray = this.derDecode(byArray);
            return this.dsaSigner.verifySignature(byArray2, bigIntegerArray[0], bigIntegerArray[1]);
        } catch (IOException iOException) {
            return false;
        }
    }

    public void reset() {
        this.digest.reset();
    }

    private byte[] derEncode(BigInteger bigInteger, BigInteger bigInteger2) throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(bigInteger));
        aSN1EncodableVector.add(new ASN1Integer(bigInteger2));
        return new DERSequence(aSN1EncodableVector).getEncoded("DER");
    }

    private BigInteger[] derDecode(byte[] byArray) throws IOException {
        ASN1Sequence aSN1Sequence = (ASN1Sequence)ASN1Primitive.fromByteArray(byArray);
        return new BigInteger[]{((ASN1Integer)aSN1Sequence.getObjectAt(0)).getValue(), ((ASN1Integer)aSN1Sequence.getObjectAt(1)).getValue()};
    }
}

