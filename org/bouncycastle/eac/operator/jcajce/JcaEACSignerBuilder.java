/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.eac.operator.EACSigner;
import org.bouncycastle.eac.operator.jcajce.DefaultEACHelper;
import org.bouncycastle.eac.operator.jcajce.EACHelper;
import org.bouncycastle.eac.operator.jcajce.NamedEACHelper;
import org.bouncycastle.eac.operator.jcajce.ProviderEACHelper;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OperatorStreamException;
import org.bouncycastle.operator.RuntimeOperatorException;

public class JcaEACSignerBuilder {
    private static final Hashtable sigNames = new Hashtable();
    private EACHelper helper = new DefaultEACHelper();

    public JcaEACSignerBuilder setProvider(String string) {
        this.helper = new NamedEACHelper(string);
        return this;
    }

    public JcaEACSignerBuilder setProvider(Provider provider) {
        this.helper = new ProviderEACHelper(provider);
        return this;
    }

    public EACSigner build(String string, PrivateKey privateKey) throws OperatorCreationException {
        return this.build((ASN1ObjectIdentifier)sigNames.get(string), privateKey);
    }

    public EACSigner build(final ASN1ObjectIdentifier aSN1ObjectIdentifier, PrivateKey privateKey) throws OperatorCreationException {
        Signature signature;
        try {
            signature = this.helper.getSignature(aSN1ObjectIdentifier);
            signature.initSign(privateKey);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new OperatorCreationException("unable to find algorithm: " + noSuchAlgorithmException.getMessage(), noSuchAlgorithmException);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new OperatorCreationException("unable to find provider: " + noSuchProviderException.getMessage(), noSuchProviderException);
        } catch (InvalidKeyException invalidKeyException) {
            throw new OperatorCreationException("invalid key: " + invalidKeyException.getMessage(), invalidKeyException);
        }
        final SignatureOutputStream signatureOutputStream = new SignatureOutputStream(signature);
        return new EACSigner(){

            public ASN1ObjectIdentifier getUsageIdentifier() {
                return aSN1ObjectIdentifier;
            }

            public OutputStream getOutputStream() {
                return signatureOutputStream;
            }

            public byte[] getSignature() {
                try {
                    byte[] byArray = signatureOutputStream.getSignature();
                    if (aSN1ObjectIdentifier.on(EACObjectIdentifiers.id_TA_ECDSA)) {
                        return JcaEACSignerBuilder.reencode(byArray);
                    }
                    return byArray;
                } catch (SignatureException signatureException) {
                    throw new RuntimeOperatorException("exception obtaining signature: " + signatureException.getMessage(), signatureException);
                }
            }
        };
    }

    public static int max(int n, int n2) {
        return n > n2 ? n : n2;
    }

    private static byte[] reencode(byte[] byArray) {
        ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(byArray);
        BigInteger bigInteger = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue();
        BigInteger bigInteger2 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue();
        byte[] byArray2 = bigInteger.toByteArray();
        byte[] byArray3 = bigInteger2.toByteArray();
        int n = JcaEACSignerBuilder.unsignedIntLength(byArray2);
        int n2 = JcaEACSignerBuilder.unsignedIntLength(byArray3);
        int n3 = JcaEACSignerBuilder.max(n, n2);
        byte[] byArray4 = new byte[n3 * 2];
        Arrays.fill(byArray4, (byte)0);
        JcaEACSignerBuilder.copyUnsignedInt(byArray2, byArray4, n3 - n);
        JcaEACSignerBuilder.copyUnsignedInt(byArray3, byArray4, 2 * n3 - n2);
        return byArray4;
    }

    private static int unsignedIntLength(byte[] byArray) {
        int n = byArray.length;
        if (byArray[0] == 0) {
            --n;
        }
        return n;
    }

    private static void copyUnsignedInt(byte[] byArray, byte[] byArray2, int n) {
        int n2 = byArray.length;
        int n3 = 0;
        if (byArray[0] == 0) {
            --n2;
            n3 = 1;
        }
        System.arraycopy(byArray, n3, byArray2, n, n2);
    }

    static {
        sigNames.put("SHA1withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1);
        sigNames.put("SHA256withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256);
        sigNames.put("SHA1withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_1);
        sigNames.put("SHA256withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_256);
        sigNames.put("SHA512withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_512);
        sigNames.put("SHA512withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_512);
        sigNames.put("SHA1withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_1);
        sigNames.put("SHA224withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_224);
        sigNames.put("SHA256withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_256);
        sigNames.put("SHA384withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_384);
        sigNames.put("SHA512withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_512);
    }

    private class SignatureOutputStream
    extends OutputStream {
        private Signature sig;

        SignatureOutputStream(Signature signature) {
            this.sig = signature;
        }

        public void write(byte[] byArray, int n, int n2) throws IOException {
            try {
                this.sig.update(byArray, n, n2);
            } catch (SignatureException signatureException) {
                throw new OperatorStreamException("exception in content signer: " + signatureException.getMessage(), signatureException);
            }
        }

        public void write(byte[] byArray) throws IOException {
            try {
                this.sig.update(byArray);
            } catch (SignatureException signatureException) {
                throw new OperatorStreamException("exception in content signer: " + signatureException.getMessage(), signatureException);
            }
        }

        public void write(int n) throws IOException {
            try {
                this.sig.update((byte)n);
            } catch (SignatureException signatureException) {
                throw new OperatorStreamException("exception in content signer: " + signatureException.getMessage(), signatureException);
            }
        }

        byte[] getSignature() throws SignatureException {
            return this.sig.sign();
        }
    }
}

