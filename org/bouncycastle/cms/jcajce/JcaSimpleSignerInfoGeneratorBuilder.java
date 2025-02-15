/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.DefaultSignedAttributeTableGenerator;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.cms.SignerInfoGeneratorBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;

public class JcaSimpleSignerInfoGeneratorBuilder {
    private Helper helper = new Helper();
    private boolean hasNoSignedAttributes;
    private CMSAttributeTableGenerator signedGen;
    private CMSAttributeTableGenerator unsignedGen;

    public JcaSimpleSignerInfoGeneratorBuilder setProvider(String string) throws OperatorCreationException {
        this.helper = new NamedHelper(string);
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setProvider(Provider provider) throws OperatorCreationException {
        this.helper = new ProviderHelper(provider);
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setDirectSignature(boolean bl) {
        this.hasNoSignedAttributes = bl;
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setSignedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.signedGen = cMSAttributeTableGenerator;
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setSignedAttributeGenerator(AttributeTable attributeTable) {
        this.signedGen = new DefaultSignedAttributeTableGenerator(attributeTable);
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setUnsignedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.unsignedGen = cMSAttributeTableGenerator;
        return this;
    }

    public SignerInfoGenerator build(String string, PrivateKey privateKey, X509Certificate x509Certificate) throws OperatorCreationException, CertificateEncodingException {
        ContentSigner contentSigner = this.helper.createContentSigner(string, privateKey);
        return this.configureAndBuild().build(contentSigner, new JcaX509CertificateHolder(x509Certificate));
    }

    public SignerInfoGenerator build(String string, PrivateKey privateKey, byte[] byArray) throws OperatorCreationException, CertificateEncodingException {
        ContentSigner contentSigner = this.helper.createContentSigner(string, privateKey);
        return this.configureAndBuild().build(contentSigner, byArray);
    }

    private SignerInfoGeneratorBuilder configureAndBuild() throws OperatorCreationException {
        SignerInfoGeneratorBuilder signerInfoGeneratorBuilder = new SignerInfoGeneratorBuilder(this.helper.createDigestCalculatorProvider());
        signerInfoGeneratorBuilder.setDirectSignature(this.hasNoSignedAttributes);
        signerInfoGeneratorBuilder.setSignedAttributeGenerator(this.signedGen);
        signerInfoGeneratorBuilder.setUnsignedAttributeGenerator(this.unsignedGen);
        return signerInfoGeneratorBuilder;
    }

    private class Helper {
        private Helper() {
        }

        ContentSigner createContentSigner(String string, PrivateKey privateKey) throws OperatorCreationException {
            return new JcaContentSignerBuilder(string).build(privateKey);
        }

        DigestCalculatorProvider createDigestCalculatorProvider() throws OperatorCreationException {
            return new JcaDigestCalculatorProviderBuilder().build();
        }
    }

    private class NamedHelper
    extends Helper {
        private final String providerName;

        public NamedHelper(String string) {
            this.providerName = string;
        }

        ContentSigner createContentSigner(String string, PrivateKey privateKey) throws OperatorCreationException {
            return new JcaContentSignerBuilder(string).setProvider(this.providerName).build(privateKey);
        }

        DigestCalculatorProvider createDigestCalculatorProvider() throws OperatorCreationException {
            return new JcaDigestCalculatorProviderBuilder().setProvider(this.providerName).build();
        }
    }

    private class ProviderHelper
    extends Helper {
        private final Provider provider;

        public ProviderHelper(Provider provider) {
            this.provider = provider;
        }

        ContentSigner createContentSigner(String string, PrivateKey privateKey) throws OperatorCreationException {
            return new JcaContentSignerBuilder(string).setProvider(this.provider).build(privateKey);
        }

        DigestCalculatorProvider createDigestCalculatorProvider() throws OperatorCreationException {
            return new JcaDigestCalculatorProviderBuilder().setProvider(this.provider).build();
        }
    }
}

