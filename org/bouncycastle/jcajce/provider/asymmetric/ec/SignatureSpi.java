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
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.ECNRSigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils;
import org.bouncycastle.jcajce.provider.asymmetric.util.DSABase;
import org.bouncycastle.jcajce.provider.asymmetric.util.DSAEncoder;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.util.Arrays;

public class SignatureSpi
extends DSABase {
    SignatureSpi(Digest digest, DSA dSA, DSAEncoder dSAEncoder) {
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

    private static class PlainDSAEncoder
    implements DSAEncoder {
        private PlainDSAEncoder() {
        }

        public byte[] encode(BigInteger bigInteger, BigInteger bigInteger2) throws IOException {
            byte[] byArray;
            byte[] byArray2 = this.makeUnsigned(bigInteger);
            byte[] byArray3 = byArray2.length > (byArray = this.makeUnsigned(bigInteger2)).length ? new byte[byArray2.length * 2] : new byte[byArray.length * 2];
            System.arraycopy(byArray2, 0, byArray3, byArray3.length / 2 - byArray2.length, byArray2.length);
            System.arraycopy(byArray, 0, byArray3, byArray3.length - byArray.length, byArray.length);
            return byArray3;
        }

        private byte[] makeUnsigned(BigInteger bigInteger) {
            byte[] byArray = bigInteger.toByteArray();
            if (byArray[0] == 0) {
                byte[] byArray2 = new byte[byArray.length - 1];
                System.arraycopy(byArray, 1, byArray2, 0, byArray2.length);
                return byArray2;
            }
            return byArray;
        }

        public BigInteger[] decode(byte[] byArray) throws IOException {
            BigInteger[] bigIntegerArray = new BigInteger[2];
            byte[] byArray2 = new byte[byArray.length / 2];
            byte[] byArray3 = new byte[byArray.length / 2];
            System.arraycopy(byArray, 0, byArray2, 0, byArray2.length);
            System.arraycopy(byArray, byArray2.length, byArray3, 0, byArray3.length);
            bigIntegerArray[0] = new BigInteger(1, byArray2);
            bigIntegerArray[1] = new BigInteger(1, byArray3);
            return bigIntegerArray;
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

    public static class ecCVCDSA
    extends SignatureSpi {
        public ecCVCDSA() {
            super(DigestFactory.createSHA1(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    public static class ecCVCDSA224
    extends SignatureSpi {
        public ecCVCDSA224() {
            super(DigestFactory.createSHA224(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    public static class ecCVCDSA256
    extends SignatureSpi {
        public ecCVCDSA256() {
            super(DigestFactory.createSHA256(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    public static class ecCVCDSA384
    extends SignatureSpi {
        public ecCVCDSA384() {
            super(DigestFactory.createSHA384(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    public static class ecCVCDSA512
    extends SignatureSpi {
        public ecCVCDSA512() {
            super(DigestFactory.createSHA512(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    public static class ecDSA
    extends SignatureSpi {
        public ecDSA() {
            super(DigestFactory.createSHA1(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSA224
    extends SignatureSpi {
        public ecDSA224() {
            super(DigestFactory.createSHA224(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSA256
    extends SignatureSpi {
        public ecDSA256() {
            super(DigestFactory.createSHA256(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSA384
    extends SignatureSpi {
        public ecDSA384() {
            super(DigestFactory.createSHA384(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSA512
    extends SignatureSpi {
        public ecDSA512() {
            super(DigestFactory.createSHA512(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSARipeMD160
    extends SignatureSpi {
        public ecDSARipeMD160() {
            super(new RIPEMD160Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSASha3_224
    extends SignatureSpi {
        public ecDSASha3_224() {
            super(DigestFactory.createSHA3_224(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSASha3_256
    extends SignatureSpi {
        public ecDSASha3_256() {
            super(DigestFactory.createSHA3_256(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSASha3_384
    extends SignatureSpi {
        public ecDSASha3_384() {
            super(DigestFactory.createSHA3_384(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSASha3_512
    extends SignatureSpi {
        public ecDSASha3_512() {
            super(DigestFactory.createSHA3_512(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDSAnone
    extends SignatureSpi {
        public ecDSAnone() {
            super(new NullDigest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    public static class ecDetDSA
    extends SignatureSpi {
        public ecDetDSA() {
            super(DigestFactory.createSHA1(), new ECDSASigner(new HMacDSAKCalculator(DigestFactory.createSHA1())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSA224
    extends SignatureSpi {
        public ecDetDSA224() {
            super(DigestFactory.createSHA224(), new ECDSASigner(new HMacDSAKCalculator(DigestFactory.createSHA224())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSA256
    extends SignatureSpi {
        public ecDetDSA256() {
            super(DigestFactory.createSHA256(), new ECDSASigner(new HMacDSAKCalculator(DigestFactory.createSHA256())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSA384
    extends SignatureSpi {
        public ecDetDSA384() {
            super(DigestFactory.createSHA384(), new ECDSASigner(new HMacDSAKCalculator(DigestFactory.createSHA384())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSA512
    extends SignatureSpi {
        public ecDetDSA512() {
            super(DigestFactory.createSHA512(), new ECDSASigner(new HMacDSAKCalculator(DigestFactory.createSHA512())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSASha3_224
    extends SignatureSpi {
        public ecDetDSASha3_224() {
            super(DigestFactory.createSHA3_224(), new ECDSASigner(new HMacDSAKCalculator(DigestFactory.createSHA3_224())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSASha3_256
    extends SignatureSpi {
        public ecDetDSASha3_256() {
            super(DigestFactory.createSHA3_256(), new ECDSASigner(new HMacDSAKCalculator(DigestFactory.createSHA3_256())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSASha3_384
    extends SignatureSpi {
        public ecDetDSASha3_384() {
            super(DigestFactory.createSHA3_384(), new ECDSASigner(new HMacDSAKCalculator(DigestFactory.createSHA3_384())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSASha3_512
    extends SignatureSpi {
        public ecDetDSASha3_512() {
            super(DigestFactory.createSHA3_512(), new ECDSASigner(new HMacDSAKCalculator(DigestFactory.createSHA3_512())), new StdDSAEncoder());
        }
    }

    public static class ecNR
    extends SignatureSpi {
        public ecNR() {
            super(DigestFactory.createSHA1(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    public static class ecNR224
    extends SignatureSpi {
        public ecNR224() {
            super(DigestFactory.createSHA224(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    public static class ecNR256
    extends SignatureSpi {
        public ecNR256() {
            super(DigestFactory.createSHA256(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    public static class ecNR384
    extends SignatureSpi {
        public ecNR384() {
            super(DigestFactory.createSHA384(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    public static class ecNR512
    extends SignatureSpi {
        public ecNR512() {
            super(DigestFactory.createSHA512(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    public static class ecPlainDSARP160
    extends SignatureSpi {
        public ecPlainDSARP160() {
            super(new RIPEMD160Digest(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }
}

