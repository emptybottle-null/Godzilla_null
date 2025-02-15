/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OperatorStreamException;
import org.bouncycastle.operator.RawContentVerifier;
import org.bouncycastle.operator.RuntimeOperatorException;
import org.bouncycastle.operator.jcajce.OperatorHelper;

public class JcaContentVerifierProviderBuilder {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());

    public JcaContentVerifierProviderBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcaContentVerifierProviderBuilder setProvider(String string) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(string));
        return this;
    }

    public ContentVerifierProvider build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
        return this.build(this.helper.convertCertificate(x509CertificateHolder));
    }

    public ContentVerifierProvider build(final X509Certificate x509Certificate) throws OperatorCreationException {
        JcaX509CertificateHolder jcaX509CertificateHolder;
        try {
            jcaX509CertificateHolder = new JcaX509CertificateHolder(x509Certificate);
        } catch (CertificateEncodingException certificateEncodingException) {
            throw new OperatorCreationException("cannot process certificate: " + certificateEncodingException.getMessage(), certificateEncodingException);
        }
        return new ContentVerifierProvider(){
            private SignatureOutputStream stream;

            public boolean hasAssociatedCertificate() {
                return true;
            }

            public X509CertificateHolder getAssociatedCertificate() {
                return jcaX509CertificateHolder;
            }

            public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                Signature signature;
                try {
                    signature = JcaContentVerifierProviderBuilder.this.helper.createSignature(algorithmIdentifier);
                    signature.initVerify(x509Certificate.getPublicKey());
                    this.stream = new SignatureOutputStream(signature);
                } catch (GeneralSecurityException generalSecurityException) {
                    throw new OperatorCreationException("exception on setup: " + generalSecurityException, generalSecurityException);
                }
                signature = JcaContentVerifierProviderBuilder.this.createRawSig(algorithmIdentifier, x509Certificate.getPublicKey());
                if (signature != null) {
                    return new RawSigVerifier(algorithmIdentifier, this.stream, signature);
                }
                return new SigVerifier(algorithmIdentifier, this.stream);
            }
        };
    }

    public ContentVerifierProvider build(final PublicKey publicKey) throws OperatorCreationException {
        return new ContentVerifierProvider(){

            public boolean hasAssociatedCertificate() {
                return false;
            }

            public X509CertificateHolder getAssociatedCertificate() {
                return null;
            }

            public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                SignatureOutputStream signatureOutputStream = JcaContentVerifierProviderBuilder.this.createSignatureStream(algorithmIdentifier, publicKey);
                Signature signature = JcaContentVerifierProviderBuilder.this.createRawSig(algorithmIdentifier, publicKey);
                if (signature != null) {
                    return new RawSigVerifier(algorithmIdentifier, signatureOutputStream, signature);
                }
                return new SigVerifier(algorithmIdentifier, signatureOutputStream);
            }
        };
    }

    public ContentVerifierProvider build(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        return this.build(this.helper.convertPublicKey(subjectPublicKeyInfo));
    }

    private SignatureOutputStream createSignatureStream(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) throws OperatorCreationException {
        try {
            Signature signature = this.helper.createSignature(algorithmIdentifier);
            signature.initVerify(publicKey);
            return new SignatureOutputStream(signature);
        } catch (GeneralSecurityException generalSecurityException) {
            throw new OperatorCreationException("exception on setup: " + generalSecurityException, generalSecurityException);
        }
    }

    private Signature createRawSig(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        Signature signature;
        try {
            signature = this.helper.createRawSignature(algorithmIdentifier);
            if (signature != null) {
                signature.initVerify(publicKey);
            }
        } catch (Exception exception) {
            signature = null;
        }
        return signature;
    }

    private class RawSigVerifier
    extends SigVerifier
    implements RawContentVerifier {
        private Signature rawSignature;

        RawSigVerifier(AlgorithmIdentifier algorithmIdentifier, SignatureOutputStream signatureOutputStream, Signature signature) {
            super(algorithmIdentifier, signatureOutputStream);
            this.rawSignature = signature;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public boolean verify(byte[] byArray) {
            try {
                boolean bl = super.verify(byArray);
                return bl;
            } finally {
                try {
                    this.rawSignature.verify(byArray);
                } catch (Exception exception) {}
            }
        }

        public boolean verify(byte[] byArray, byte[] byArray2) {
            try {
                this.rawSignature.update(byArray);
                boolean bl = this.rawSignature.verify(byArray2);
                return bl;
            } catch (SignatureException signatureException) {
                throw new RuntimeOperatorException("exception obtaining raw signature: " + signatureException.getMessage(), signatureException);
            } finally {
                try {
                    this.stream.verify(byArray2);
                } catch (Exception exception) {}
            }
        }
    }

    private class SigVerifier
    implements ContentVerifier {
        private AlgorithmIdentifier algorithm;
        protected SignatureOutputStream stream;

        SigVerifier(AlgorithmIdentifier algorithmIdentifier, SignatureOutputStream signatureOutputStream) {
            this.algorithm = algorithmIdentifier;
            this.stream = signatureOutputStream;
        }

        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithm;
        }

        public OutputStream getOutputStream() {
            if (this.stream == null) {
                throw new IllegalStateException("verifier not initialised");
            }
            return this.stream;
        }

        public boolean verify(byte[] byArray) {
            try {
                return this.stream.verify(byArray);
            } catch (SignatureException signatureException) {
                throw new RuntimeOperatorException("exception obtaining signature: " + signatureException.getMessage(), signatureException);
            }
        }
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

