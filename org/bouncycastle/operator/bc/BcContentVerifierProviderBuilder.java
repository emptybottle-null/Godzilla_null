/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcDefaultDigestProvider;
import org.bouncycastle.operator.bc.BcDigestProvider;
import org.bouncycastle.operator.bc.BcSignerOutputStream;

public abstract class BcContentVerifierProviderBuilder {
    protected BcDigestProvider digestProvider = BcDefaultDigestProvider.INSTANCE;

    public ContentVerifierProvider build(final X509CertificateHolder x509CertificateHolder) throws OperatorCreationException {
        return new ContentVerifierProvider(){

            public boolean hasAssociatedCertificate() {
                return true;
            }

            public X509CertificateHolder getAssociatedCertificate() {
                return x509CertificateHolder;
            }

            public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                try {
                    AsymmetricKeyParameter asymmetricKeyParameter = BcContentVerifierProviderBuilder.this.extractKeyParameters(x509CertificateHolder.getSubjectPublicKeyInfo());
                    BcSignerOutputStream bcSignerOutputStream = BcContentVerifierProviderBuilder.this.createSignatureStream(algorithmIdentifier, asymmetricKeyParameter);
                    return new SigVerifier(algorithmIdentifier, bcSignerOutputStream);
                } catch (IOException iOException) {
                    throw new OperatorCreationException("exception on setup: " + iOException, iOException);
                }
            }
        };
    }

    public ContentVerifierProvider build(final AsymmetricKeyParameter asymmetricKeyParameter) throws OperatorCreationException {
        return new ContentVerifierProvider(){

            public boolean hasAssociatedCertificate() {
                return false;
            }

            public X509CertificateHolder getAssociatedCertificate() {
                return null;
            }

            public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                BcSignerOutputStream bcSignerOutputStream = BcContentVerifierProviderBuilder.this.createSignatureStream(algorithmIdentifier, asymmetricKeyParameter);
                return new SigVerifier(algorithmIdentifier, bcSignerOutputStream);
            }
        };
    }

    private BcSignerOutputStream createSignatureStream(AlgorithmIdentifier algorithmIdentifier, AsymmetricKeyParameter asymmetricKeyParameter) throws OperatorCreationException {
        Signer signer = this.createSigner(algorithmIdentifier);
        signer.init(false, asymmetricKeyParameter);
        return new BcSignerOutputStream(signer);
    }

    protected abstract AsymmetricKeyParameter extractKeyParameters(SubjectPublicKeyInfo var1) throws IOException;

    protected abstract Signer createSigner(AlgorithmIdentifier var1) throws OperatorCreationException;

    private class SigVerifier
    implements ContentVerifier {
        private BcSignerOutputStream stream;
        private AlgorithmIdentifier algorithm;

        SigVerifier(AlgorithmIdentifier algorithmIdentifier, BcSignerOutputStream bcSignerOutputStream) {
            this.algorithm = algorithmIdentifier;
            this.stream = bcSignerOutputStream;
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
            return this.stream.verify(byArray);
        }
    }
}

