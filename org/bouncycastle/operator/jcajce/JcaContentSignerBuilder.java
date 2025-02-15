/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OperatorStreamException;
import org.bouncycastle.operator.RuntimeOperatorException;
import org.bouncycastle.operator.jcajce.OperatorHelper;

public class JcaContentSignerBuilder {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private SecureRandom random;
    private String signatureAlgorithm;
    private AlgorithmIdentifier sigAlgId;

    public JcaContentSignerBuilder(String string) {
        this.signatureAlgorithm = string;
        this.sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find(string);
    }

    public JcaContentSignerBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcaContentSignerBuilder setProvider(String string) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(string));
        return this;
    }

    public JcaContentSignerBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public ContentSigner build(PrivateKey privateKey) throws OperatorCreationException {
        try {
            final Signature signature = this.helper.createSignature(this.sigAlgId);
            final AlgorithmIdentifier algorithmIdentifier = this.sigAlgId;
            if (this.random != null) {
                signature.initSign(privateKey, this.random);
            } else {
                signature.initSign(privateKey);
            }
            return new ContentSigner(){
                private SignatureOutputStream stream;
                {
                    this.stream = new SignatureOutputStream(signature);
                }

                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return algorithmIdentifier;
                }

                public OutputStream getOutputStream() {
                    return this.stream;
                }

                public byte[] getSignature() {
                    try {
                        return this.stream.getSignature();
                    } catch (SignatureException signatureException) {
                        throw new RuntimeOperatorException("exception obtaining signature: " + signatureException.getMessage(), signatureException);
                    }
                }
            };
        } catch (GeneralSecurityException generalSecurityException) {
            throw new OperatorCreationException("cannot create signer: " + generalSecurityException.getMessage(), generalSecurityException);
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

        byte[] getSignature() throws SignatureException {
            return this.sig.sign();
        }
    }
}

