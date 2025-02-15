/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.crmf.AttributeTypeAndValue;
import org.bouncycastle.asn1.crmf.CertReqMsg;
import org.bouncycastle.asn1.crmf.CertRequest;
import org.bouncycastle.asn1.crmf.CertTemplate;
import org.bouncycastle.asn1.crmf.CertTemplateBuilder;
import org.bouncycastle.asn1.crmf.OptionalValidity;
import org.bouncycastle.asn1.crmf.POPOPrivKey;
import org.bouncycastle.asn1.crmf.ProofOfPossession;
import org.bouncycastle.asn1.crmf.SubsequentMessage;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.CRMFUtil;
import org.bouncycastle.cert.crmf.CertificateRequestMessage;
import org.bouncycastle.cert.crmf.Control;
import org.bouncycastle.cert.crmf.PKMACBuilder;
import org.bouncycastle.cert.crmf.PKMACValueGenerator;
import org.bouncycastle.cert.crmf.ProofOfPossessionSigningKeyBuilder;
import org.bouncycastle.operator.ContentSigner;

public class CertificateRequestMessageBuilder {
    private final BigInteger certReqId;
    private ExtensionsGenerator extGenerator;
    private CertTemplateBuilder templateBuilder;
    private List controls;
    private ContentSigner popSigner;
    private PKMACBuilder pkmacBuilder;
    private char[] password;
    private GeneralName sender;
    private POPOPrivKey popoPrivKey;
    private ASN1Null popRaVerified;

    public CertificateRequestMessageBuilder(BigInteger bigInteger) {
        this.certReqId = bigInteger;
        this.extGenerator = new ExtensionsGenerator();
        this.templateBuilder = new CertTemplateBuilder();
        this.controls = new ArrayList();
    }

    public CertificateRequestMessageBuilder setPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        if (subjectPublicKeyInfo != null) {
            this.templateBuilder.setPublicKey(subjectPublicKeyInfo);
        }
        return this;
    }

    public CertificateRequestMessageBuilder setIssuer(X500Name x500Name) {
        if (x500Name != null) {
            this.templateBuilder.setIssuer(x500Name);
        }
        return this;
    }

    public CertificateRequestMessageBuilder setSubject(X500Name x500Name) {
        if (x500Name != null) {
            this.templateBuilder.setSubject(x500Name);
        }
        return this;
    }

    public CertificateRequestMessageBuilder setSerialNumber(BigInteger bigInteger) {
        if (bigInteger != null) {
            this.templateBuilder.setSerialNumber(new ASN1Integer(bigInteger));
        }
        return this;
    }

    public CertificateRequestMessageBuilder setValidity(Date date, Date date2) {
        this.templateBuilder.setValidity(new OptionalValidity(this.createTime(date), this.createTime(date2)));
        return this;
    }

    private Time createTime(Date date) {
        if (date != null) {
            return new Time(date);
        }
        return null;
    }

    public CertificateRequestMessageBuilder addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, ASN1Encodable aSN1Encodable) throws CertIOException {
        CRMFUtil.addExtension(this.extGenerator, aSN1ObjectIdentifier, bl, aSN1Encodable);
        return this;
    }

    public CertificateRequestMessageBuilder addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, byte[] byArray) {
        this.extGenerator.addExtension(aSN1ObjectIdentifier, bl, byArray);
        return this;
    }

    public CertificateRequestMessageBuilder addControl(Control control) {
        this.controls.add(control);
        return this;
    }

    public CertificateRequestMessageBuilder setProofOfPossessionSigningKeySigner(ContentSigner contentSigner) {
        if (this.popoPrivKey != null || this.popRaVerified != null) {
            throw new IllegalStateException("only one proof of possession allowed");
        }
        this.popSigner = contentSigner;
        return this;
    }

    public CertificateRequestMessageBuilder setProofOfPossessionSubsequentMessage(SubsequentMessage subsequentMessage) {
        if (this.popSigner != null || this.popRaVerified != null) {
            throw new IllegalStateException("only one proof of possession allowed");
        }
        this.popoPrivKey = new POPOPrivKey(subsequentMessage);
        return this;
    }

    public CertificateRequestMessageBuilder setProofOfPossessionRaVerified() {
        if (this.popSigner != null || this.popoPrivKey != null) {
            throw new IllegalStateException("only one proof of possession allowed");
        }
        this.popRaVerified = DERNull.INSTANCE;
        return this;
    }

    public CertificateRequestMessageBuilder setAuthInfoPKMAC(PKMACBuilder pKMACBuilder, char[] cArray) {
        this.pkmacBuilder = pKMACBuilder;
        this.password = cArray;
        return this;
    }

    public CertificateRequestMessageBuilder setAuthInfoSender(X500Name x500Name) {
        return this.setAuthInfoSender(new GeneralName(x500Name));
    }

    public CertificateRequestMessageBuilder setAuthInfoSender(GeneralName generalName) {
        this.sender = generalName;
        return this;
    }

    public CertificateRequestMessage build() throws CRMFException {
        Object object;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.certReqId));
        if (!this.extGenerator.isEmpty()) {
            this.templateBuilder.setExtensions(this.extGenerator.generate());
        }
        aSN1EncodableVector.add(this.templateBuilder.build());
        if (!this.controls.isEmpty()) {
            object = new ASN1EncodableVector();
            for (Object object2 : this.controls) {
                ((ASN1EncodableVector)object).add(new AttributeTypeAndValue(object2.getType(), object2.getValue()));
            }
            aSN1EncodableVector.add(new DERSequence((ASN1EncodableVector)object));
        }
        object = CertRequest.getInstance(new DERSequence(aSN1EncodableVector));
        aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add((ASN1Encodable)object);
        if (this.popSigner != null) {
            Object object2;
            CertTemplate certTemplate = ((CertRequest)object).getCertTemplate();
            if (certTemplate.getSubject() == null || certTemplate.getPublicKey() == null) {
                object2 = ((CertRequest)object).getCertTemplate().getPublicKey();
                ProofOfPossessionSigningKeyBuilder proofOfPossessionSigningKeyBuilder = new ProofOfPossessionSigningKeyBuilder((SubjectPublicKeyInfo)object2);
                if (this.sender != null) {
                    proofOfPossessionSigningKeyBuilder.setSender(this.sender);
                } else {
                    PKMACValueGenerator pKMACValueGenerator = new PKMACValueGenerator(this.pkmacBuilder);
                    proofOfPossessionSigningKeyBuilder.setPublicKeyMac(pKMACValueGenerator, this.password);
                }
                aSN1EncodableVector.add(new ProofOfPossession(proofOfPossessionSigningKeyBuilder.build(this.popSigner)));
            } else {
                object2 = new ProofOfPossessionSigningKeyBuilder((CertRequest)object);
                aSN1EncodableVector.add(new ProofOfPossession(((ProofOfPossessionSigningKeyBuilder)object2).build(this.popSigner)));
            }
        } else if (this.popoPrivKey != null) {
            aSN1EncodableVector.add(new ProofOfPossession(2, this.popoPrivKey));
        } else if (this.popRaVerified != null) {
            aSN1EncodableVector.add(new ProofOfPossession());
        }
        return new CertificateRequestMessage(CertReqMsg.getInstance(new DERSequence(aSN1EncodableVector)));
    }
}

