/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.SignerIdentifier;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignatureEncryptionAlgorithmFinder;
import org.bouncycastle.cms.DefaultSignedAttributeTableGenerator;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.TeeOutputStream;

public class SignerInfoGenerator {
    private final SignerIdentifier signerIdentifier;
    private final CMSAttributeTableGenerator sAttrGen;
    private final CMSAttributeTableGenerator unsAttrGen;
    private final ContentSigner signer;
    private final DigestCalculator digester;
    private final DigestAlgorithmIdentifierFinder digAlgFinder = new DefaultDigestAlgorithmIdentifierFinder();
    private final CMSSignatureEncryptionAlgorithmFinder sigEncAlgFinder;
    private byte[] calculatedDigest = null;
    private X509CertificateHolder certHolder;

    SignerInfoGenerator(SignerIdentifier signerIdentifier, ContentSigner contentSigner, DigestCalculatorProvider digestCalculatorProvider, CMSSignatureEncryptionAlgorithmFinder cMSSignatureEncryptionAlgorithmFinder) throws OperatorCreationException {
        this(signerIdentifier, contentSigner, digestCalculatorProvider, cMSSignatureEncryptionAlgorithmFinder, false);
    }

    SignerInfoGenerator(SignerIdentifier signerIdentifier, ContentSigner contentSigner, DigestCalculatorProvider digestCalculatorProvider, CMSSignatureEncryptionAlgorithmFinder cMSSignatureEncryptionAlgorithmFinder, boolean bl) throws OperatorCreationException {
        this.signerIdentifier = signerIdentifier;
        this.signer = contentSigner;
        this.digester = digestCalculatorProvider != null ? digestCalculatorProvider.get(this.digAlgFinder.find(contentSigner.getAlgorithmIdentifier())) : null;
        if (bl) {
            this.sAttrGen = null;
            this.unsAttrGen = null;
        } else {
            this.sAttrGen = new DefaultSignedAttributeTableGenerator();
            this.unsAttrGen = null;
        }
        this.sigEncAlgFinder = cMSSignatureEncryptionAlgorithmFinder;
    }

    public SignerInfoGenerator(SignerInfoGenerator signerInfoGenerator, CMSAttributeTableGenerator cMSAttributeTableGenerator, CMSAttributeTableGenerator cMSAttributeTableGenerator2) {
        this.signerIdentifier = signerInfoGenerator.signerIdentifier;
        this.signer = signerInfoGenerator.signer;
        this.digester = signerInfoGenerator.digester;
        this.sigEncAlgFinder = signerInfoGenerator.sigEncAlgFinder;
        this.sAttrGen = cMSAttributeTableGenerator;
        this.unsAttrGen = cMSAttributeTableGenerator2;
    }

    SignerInfoGenerator(SignerIdentifier signerIdentifier, ContentSigner contentSigner, DigestCalculatorProvider digestCalculatorProvider, CMSSignatureEncryptionAlgorithmFinder cMSSignatureEncryptionAlgorithmFinder, CMSAttributeTableGenerator cMSAttributeTableGenerator, CMSAttributeTableGenerator cMSAttributeTableGenerator2) throws OperatorCreationException {
        this.signerIdentifier = signerIdentifier;
        this.signer = contentSigner;
        this.digester = digestCalculatorProvider != null ? digestCalculatorProvider.get(this.digAlgFinder.find(contentSigner.getAlgorithmIdentifier())) : null;
        this.sAttrGen = cMSAttributeTableGenerator;
        this.unsAttrGen = cMSAttributeTableGenerator2;
        this.sigEncAlgFinder = cMSSignatureEncryptionAlgorithmFinder;
    }

    public SignerIdentifier getSID() {
        return this.signerIdentifier;
    }

    public int getGeneratedVersion() {
        return this.signerIdentifier.isTagged() ? 3 : 1;
    }

    public boolean hasAssociatedCertificate() {
        return this.certHolder != null;
    }

    public X509CertificateHolder getAssociatedCertificate() {
        return this.certHolder;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        if (this.digester != null) {
            return this.digester.getAlgorithmIdentifier();
        }
        return this.digAlgFinder.find(this.signer.getAlgorithmIdentifier());
    }

    public OutputStream getCalculatingOutputStream() {
        if (this.digester != null) {
            if (this.sAttrGen == null) {
                return new TeeOutputStream(this.digester.getOutputStream(), this.signer.getOutputStream());
            }
            return this.digester.getOutputStream();
        }
        return this.signer.getOutputStream();
    }

    public SignerInfo generate(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        try {
            Object object;
            Object object2;
            Object object3;
            ASN1Set aSN1Set = null;
            AlgorithmIdentifier algorithmIdentifier = this.sigEncAlgFinder.findEncryptionAlgorithm(this.signer.getAlgorithmIdentifier());
            AlgorithmIdentifier algorithmIdentifier2 = null;
            if (this.sAttrGen != null) {
                algorithmIdentifier2 = this.digester.getAlgorithmIdentifier();
                this.calculatedDigest = this.digester.getDigest();
                object3 = this.getBaseParameters(aSN1ObjectIdentifier, this.digester.getAlgorithmIdentifier(), algorithmIdentifier, this.calculatedDigest);
                object2 = this.sAttrGen.getAttributes(Collections.unmodifiableMap(object3));
                aSN1Set = this.getAttributeSet((AttributeTable)object2);
                object = this.signer.getOutputStream();
                ((OutputStream)object).write(aSN1Set.getEncoded("DER"));
                ((OutputStream)object).close();
            } else if (this.digester != null) {
                algorithmIdentifier2 = this.digester.getAlgorithmIdentifier();
                this.calculatedDigest = this.digester.getDigest();
            } else {
                algorithmIdentifier2 = this.digAlgFinder.find(this.signer.getAlgorithmIdentifier());
                this.calculatedDigest = null;
            }
            object3 = this.signer.getSignature();
            object2 = null;
            if (this.unsAttrGen != null) {
                object = this.getBaseParameters(aSN1ObjectIdentifier, algorithmIdentifier2, algorithmIdentifier, this.calculatedDigest);
                object.put("encryptedDigest", Arrays.clone(object3));
                AttributeTable attributeTable = this.unsAttrGen.getAttributes(Collections.unmodifiableMap(object));
                object2 = this.getAttributeSet(attributeTable);
            }
            return new SignerInfo(this.signerIdentifier, algorithmIdentifier2, aSN1Set, algorithmIdentifier, (ASN1OctetString)new DEROctetString((byte[])object3), (ASN1Set)object2);
        } catch (IOException iOException) {
            throw new CMSException("encoding error.", iOException);
        }
    }

    void setAssociatedCertificate(X509CertificateHolder x509CertificateHolder) {
        this.certHolder = x509CertificateHolder;
    }

    private ASN1Set getAttributeSet(AttributeTable attributeTable) {
        if (attributeTable != null) {
            return new DERSet(attributeTable.toASN1EncodableVector());
        }
        return null;
    }

    private Map getBaseParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        if (aSN1ObjectIdentifier != null) {
            hashMap.put("contentType", aSN1ObjectIdentifier);
        }
        hashMap.put("digestAlgID", algorithmIdentifier);
        hashMap.put("signatureAlgID", algorithmIdentifier2);
        hashMap.put("digest", Arrays.clone(byArray));
        return hashMap;
    }

    public byte[] getCalculatedDigest() {
        if (this.calculatedDigest != null) {
            return Arrays.clone(this.calculatedDigest);
        }
        return null;
    }

    public CMSAttributeTableGenerator getSignedAttributeTableGenerator() {
        return this.sAttrGen;
    }

    public CMSAttributeTableGenerator getUnsignedAttributeTableGenerator() {
        return this.unsAttrGen;
    }
}

