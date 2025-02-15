/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ec;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils;
import org.bouncycastle.jcajce.provider.asymmetric.util.DSABase;
import org.bouncycastle.jcajce.provider.asymmetric.util.DSAEncoder;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.util.Arrays;

public class GMSignatureSpi
extends DSABase {
    GMSignatureSpi(Digest digest, DSA dSA, DSAEncoder dSAEncoder) {
        super(digest, dSA, dSAEncoder);
    }

    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        AsymmetricKeyParameter asymmetricKeyParameter = ECUtils.generatePublicKeyParameter(publicKey);
        this.digest.reset();
        this.signer.init(false, asymmetricKeyParameter);
    }

    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        AsymmetricKeyParameter asymmetricKeyParameter = ECUtil.generatePrivateKeyParameter(privateKey);
        this.digest.reset();
        if (this.appRandom != null) {
            this.signer.init(true, new ParametersWithRandom(asymmetricKeyParameter, this.appRandom));
        } else {
            this.signer.init(true, asymmetricKeyParameter);
        }
    }

    private static class StdDSAEncoder
    implements DSAEncoder {
        private StdDSAEncoder() {
        }

        public byte[] encode(BigInteger bigInteger, BigInteger bigInteger2) throws IOException {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            aSN1EncodableVector.add(new ASN1Integer(bigInteger));
            aSN1EncodableVector.add(new ASN1Integer(bigInteger2));
            return new DERSequence(aSN1EncodableVector).getEncoded("DER");
        }

        public BigInteger[] decode(byte[] byArray) throws IOException {
            ASN1Sequence aSN1Sequence = (ASN1Sequence)ASN1Primitive.fromByteArray(byArray);
            if (aSN1Sequence.size() != 2) {
                throw new IOException("malformed signature");
            }
            if (!Arrays.areEqual(byArray, aSN1Sequence.getEncoded("DER"))) {
                throw new IOException("malformed signature");
            }
            BigInteger[] bigIntegerArray = new BigInteger[]{ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue(), ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue()};
            return bigIntegerArray;
        }
    }

    public static class sm3WithSM2
    extends GMSignatureSpi {
        public sm3WithSM2() {
            super(new SM3Digest(), new SM2Signer(), new StdDSAEncoder());
        }
    }
}

