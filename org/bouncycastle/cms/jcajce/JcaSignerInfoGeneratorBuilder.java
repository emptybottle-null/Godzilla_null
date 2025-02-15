/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSSignatureEncryptionAlgorithmFinder;
import org.bouncycastle.cms.DefaultCMSSignatureEncryptionAlgorithmFinder;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.cms.SignerInfoGeneratorBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;

public class JcaSignerInfoGeneratorBuilder {
    private SignerInfoGeneratorBuilder builder;

    public JcaSignerInfoGeneratorBuilder(DigestCalculatorProvider digestCalculatorProvider) {
        this(digestCalculatorProvider, new DefaultCMSSignatureEncryptionAlgorithmFinder());
    }

    public JcaSignerInfoGeneratorBuilder(DigestCalculatorProvider digestCalculatorProvider, CMSSignatureEncryptionAlgorithmFinder cMSSignatureEncryptionAlgorithmFinder) {
        this.builder = new SignerInfoGeneratorBuilder(digestCalculatorProvider, cMSSignatureEncryptionAlgorithmFinder);
    }

    public JcaSignerInfoGeneratorBuilder setDirectSignature(boolean bl) {
        this.builder.setDirectSignature(bl);
        return this;
    }

    public JcaSignerInfoGeneratorBuilder setSignedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.builder.setSignedAttributeGenerator(cMSAttributeTableGenerator);
        return this;
    }

    public JcaSignerInfoGeneratorBuilder setUnsignedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.builder.setUnsignedAttributeGenerator(cMSAttributeTableGenerator);
        return this;
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, X509CertificateHolder x509CertificateHolder) throws OperatorCreationException {
        return this.builder.build(contentSigner, x509CertificateHolder);
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, byte[] byArray) throws OperatorCreationException {
        return this.builder.build(contentSigner, byArray);
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, X509Certificate x509Certificate) throws OperatorCreationException, CertificateEncodingException {
        return this.build(contentSigner, new JcaX509CertificateHolder(x509Certificate));
    }
}

