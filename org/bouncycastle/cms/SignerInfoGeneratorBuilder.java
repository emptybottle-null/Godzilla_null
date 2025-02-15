/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.SignerIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSSignatureEncryptionAlgorithmFinder;
import org.bouncycastle.cms.DefaultCMSSignatureEncryptionAlgorithmFinder;
import org.bouncycastle.cms.DefaultSignedAttributeTableGenerator;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;

public class SignerInfoGeneratorBuilder {
    private DigestCalculatorProvider digestProvider;
    private boolean directSignature;
    private CMSAttributeTableGenerator signedGen;
    private CMSAttributeTableGenerator unsignedGen;
    private CMSSignatureEncryptionAlgorithmFinder sigEncAlgFinder;

    public SignerInfoGeneratorBuilder(DigestCalculatorProvider digestCalculatorProvider) {
        this(digestCalculatorProvider, new DefaultCMSSignatureEncryptionAlgorithmFinder());
    }

    public SignerInfoGeneratorBuilder(DigestCalculatorProvider digestCalculatorProvider, CMSSignatureEncryptionAlgorithmFinder cMSSignatureEncryptionAlgorithmFinder) {
        this.digestProvider = digestCalculatorProvider;
        this.sigEncAlgFinder = cMSSignatureEncryptionAlgorithmFinder;
    }

    public SignerInfoGeneratorBuilder setDirectSignature(boolean bl) {
        this.directSignature = bl;
        return this;
    }

    public SignerInfoGeneratorBuilder setSignedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.signedGen = cMSAttributeTableGenerator;
        return this;
    }

    public SignerInfoGeneratorBuilder setUnsignedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.unsignedGen = cMSAttributeTableGenerator;
        return this;
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, X509CertificateHolder x509CertificateHolder) throws OperatorCreationException {
        SignerIdentifier signerIdentifier = new SignerIdentifier(new IssuerAndSerialNumber(x509CertificateHolder.toASN1Structure()));
        SignerInfoGenerator signerInfoGenerator = this.createGenerator(contentSigner, signerIdentifier);
        signerInfoGenerator.setAssociatedCertificate(x509CertificateHolder);
        return signerInfoGenerator;
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, byte[] byArray) throws OperatorCreationException {
        SignerIdentifier signerIdentifier = new SignerIdentifier(new DEROctetString(byArray));
        return this.createGenerator(contentSigner, signerIdentifier);
    }

    private SignerInfoGenerator createGenerator(ContentSigner contentSigner, SignerIdentifier signerIdentifier) throws OperatorCreationException {
        if (this.directSignature) {
            return new SignerInfoGenerator(signerIdentifier, contentSigner, this.digestProvider, this.sigEncAlgFinder, true);
        }
        if (this.signedGen != null || this.unsignedGen != null) {
            if (this.signedGen == null) {
                this.signedGen = new DefaultSignedAttributeTableGenerator();
            }
            return new SignerInfoGenerator(signerIdentifier, contentSigner, this.digestProvider, this.sigEncAlgFinder, this.signedGen, this.unsignedGen);
        }
        return new SignerInfoGenerator(signerIdentifier, contentSigner, this.digestProvider, this.sigEncAlgFinder);
    }
}

