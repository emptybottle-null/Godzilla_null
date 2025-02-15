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
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.eac.operator.EACSignatureVerifier;
import org.bouncycastle.eac.operator.jcajce.DefaultEACHelper;
import org.bouncycastle.eac.operator.jcajce.EACHelper;
import org.bouncycastle.eac.operator.jcajce.NamedEACHelper;
import org.bouncycastle.eac.operator.jcajce.ProviderEACHelper;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OperatorStreamException;
import org.bouncycastle.operator.RuntimeOperatorException;

public class JcaEACSignatureVerifierBuilder {
    private EACHelper helper = new DefaultEACHelper();

    public JcaEACSignatureVerifierBuilder setProvider(String string) {
        this.helper = new NamedEACHelper(string);
        return this;
    }

    public JcaEACSignatureVerifierBuilder setProvider(Provider provider) {
        this.helper = new ProviderEACHelper(provider);
        return this;
    }

    public EACSignatureVerifier build(final ASN1ObjectIdentifier aSN1ObjectIdentifier, PublicKey publicKey) throws OperatorCreationException {
        Signature signature;
        try {
            signature = this.helper.getSignature(aSN1ObjectIdentifier);
            signature.initVerify(publicKey);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new OperatorCreationException("unable to find algorithm: " + noSuchAlgorithmException.getMessage(), noSuchAlgorithmException);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new OperatorCreationException("unable to find provider: " + noSuchProviderException.getMessage(), noSuchProviderException);
        } catch (InvalidKeyException invalidKeyException) {
            throw new OperatorCreationException("invalid key: " + invalidKeyException.getMessage(), invalidKeyException);
        }
        final SignatureOutputStream signatureOutputStream = new SignatureOutputStream(signature);
        return new EACSignatureVerifier(){

            public ASN1ObjectIdentifier getUsageIdentifier() {
                return aSN1ObjectIdentifier;
            }

            public OutputStream getOutputStream() {
                return signatureOutputStream;
            }

            public boolean verify(byte[] byArray) {
                try {
                    if (aSN1ObjectIdentifier.on(EACObjectIdentifiers.id_TA_ECDSA)) {
                        try {
                            byte[] byArray2 = JcaEACSignatureVerifierBuilder.derEncode(byArray);
                            return signatureOutputStream.verify(byArray2);
                        } catch (Exception exception) {
                            return false;
                        }
                    }
                    return signatureOutputStream.verify(byArray);
                } catch (SignatureException signatureException) {
                    throw new RuntimeOperatorException("exception obtaining signature: " + signatureException.getMessage(), signatureException);
                }
            }
        };
    }

    private static byte[] derEncode(byte[] byArray) throws IOException {
        int n = byArray.length / 2;
        byte[] byArray2 = new byte[n];
        byte[] byArray3 = new byte[n];
        System.arraycopy(byArray, 0, byArray2, 0, n);
        System.arraycopy(byArray, n, byArray3, 0, n);
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(new BigInteger(1, byArray2)));
        aSN1EncodableVector.add(new ASN1Integer(new BigInteger(1, byArray3)));
        DERSequence dERSequence = new DERSequence(aSN1EncodableVector);
        return dERSequence.getEncoded();
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

        boolean verify(byte[] byArray) throws SignatureException {
            return this.sig.verify(byArray);
        }
    }
}

