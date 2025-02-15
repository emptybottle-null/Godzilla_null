/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSAlgorithmProtection;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.SignerIdentifier;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.asn1.cms.Time;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedHelper;
import org.bouncycastle.cms.CMSSignerDigestMismatchException;
import org.bouncycastle.cms.CMSUtils;
import org.bouncycastle.cms.CMSVerifierCertificateNotValidException;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.RawContentVerifier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.TeeOutputStream;

public class SignerInformation {
    private final SignerId sid;
    private final CMSProcessable content;
    private final byte[] signature;
    private final ASN1ObjectIdentifier contentType;
    private final boolean isCounterSignature;
    private AttributeTable signedAttributeValues;
    private AttributeTable unsignedAttributeValues;
    private byte[] resultDigest;
    protected final SignerInfo info;
    protected final AlgorithmIdentifier digestAlgorithm;
    protected final AlgorithmIdentifier encryptionAlgorithm;
    protected final ASN1Set signedAttributeSet;
    protected final ASN1Set unsignedAttributeSet;

    SignerInformation(SignerInfo signerInfo, ASN1ObjectIdentifier aSN1ObjectIdentifier, CMSProcessable cMSProcessable, byte[] byArray) {
        this.info = signerInfo;
        this.contentType = aSN1ObjectIdentifier;
        this.isCounterSignature = aSN1ObjectIdentifier == null;
        SignerIdentifier signerIdentifier = signerInfo.getSID();
        if (signerIdentifier.isTagged()) {
            ASN1OctetString aSN1OctetString = ASN1OctetString.getInstance(signerIdentifier.getId());
            this.sid = new SignerId(aSN1OctetString.getOctets());
        } else {
            IssuerAndSerialNumber issuerAndSerialNumber = IssuerAndSerialNumber.getInstance(signerIdentifier.getId());
            this.sid = new SignerId(issuerAndSerialNumber.getName(), issuerAndSerialNumber.getSerialNumber().getValue());
        }
        this.digestAlgorithm = signerInfo.getDigestAlgorithm();
        this.signedAttributeSet = signerInfo.getAuthenticatedAttributes();
        this.unsignedAttributeSet = signerInfo.getUnauthenticatedAttributes();
        this.encryptionAlgorithm = signerInfo.getDigestEncryptionAlgorithm();
        this.signature = signerInfo.getEncryptedDigest().getOctets();
        this.content = cMSProcessable;
        this.resultDigest = byArray;
    }

    protected SignerInformation(SignerInformation signerInformation) {
        this.info = signerInformation.info;
        this.contentType = signerInformation.contentType;
        this.isCounterSignature = signerInformation.isCounterSignature();
        this.sid = signerInformation.getSID();
        this.digestAlgorithm = this.info.getDigestAlgorithm();
        this.signedAttributeSet = this.info.getAuthenticatedAttributes();
        this.unsignedAttributeSet = this.info.getUnauthenticatedAttributes();
        this.encryptionAlgorithm = this.info.getDigestEncryptionAlgorithm();
        this.signature = this.info.getEncryptedDigest().getOctets();
        this.content = signerInformation.content;
        this.resultDigest = signerInformation.resultDigest;
    }

    public boolean isCounterSignature() {
        return this.isCounterSignature;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentType;
    }

    private byte[] encodeObj(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public SignerId getSID() {
        return this.sid;
    }

    public int getVersion() {
        return this.info.getVersion().getValue().intValue();
    }

    public AlgorithmIdentifier getDigestAlgorithmID() {
        return this.digestAlgorithm;
    }

    public String getDigestAlgOID() {
        return this.digestAlgorithm.getAlgorithm().getId();
    }

    public byte[] getDigestAlgParams() {
        try {
            return this.encodeObj(this.digestAlgorithm.getParameters());
        } catch (Exception exception) {
            throw new RuntimeException("exception getting digest parameters " + exception);
        }
    }

    public byte[] getContentDigest() {
        if (this.resultDigest == null) {
            throw new IllegalStateException("method can only be called after verify.");
        }
        return Arrays.clone(this.resultDigest);
    }

    public String getEncryptionAlgOID() {
        return this.encryptionAlgorithm.getAlgorithm().getId();
    }

    public byte[] getEncryptionAlgParams() {
        try {
            return this.encodeObj(this.encryptionAlgorithm.getParameters());
        } catch (Exception exception) {
            throw new RuntimeException("exception getting encryption parameters " + exception);
        }
    }

    public AttributeTable getSignedAttributes() {
        if (this.signedAttributeSet != null && this.signedAttributeValues == null) {
            this.signedAttributeValues = new AttributeTable(this.signedAttributeSet);
        }
        return this.signedAttributeValues;
    }

    public AttributeTable getUnsignedAttributes() {
        if (this.unsignedAttributeSet != null && this.unsignedAttributeValues == null) {
            this.unsignedAttributeValues = new AttributeTable(this.unsignedAttributeSet);
        }
        return this.unsignedAttributeValues;
    }

    public byte[] getSignature() {
        return Arrays.clone(this.signature);
    }

    public SignerInformationStore getCounterSignatures() {
        AttributeTable attributeTable = this.getUnsignedAttributes();
        if (attributeTable == null) {
            return new SignerInformationStore(new ArrayList<SignerInformation>(0));
        }
        ArrayList<SignerInformation> arrayList = new ArrayList<SignerInformation>();
        ASN1EncodableVector aSN1EncodableVector = attributeTable.getAll(CMSAttributes.counterSignature);
        for (int i = 0; i < aSN1EncodableVector.size(); ++i) {
            Attribute attribute = (Attribute)aSN1EncodableVector.get(i);
            ASN1Set aSN1Set = attribute.getAttrValues();
            if (aSN1Set.size() < 1) {
                // empty if block
            }
            Enumeration enumeration = aSN1Set.getObjects();
            while (enumeration.hasMoreElements()) {
                SignerInfo signerInfo = SignerInfo.getInstance(enumeration.nextElement());
                arrayList.add(new SignerInformation(signerInfo, null, new CMSProcessableByteArray(this.getSignature()), null));
            }
        }
        return new SignerInformationStore(arrayList);
    }

    public byte[] getEncodedSignedAttributes() throws IOException {
        if (this.signedAttributeSet != null) {
            return this.signedAttributeSet.getEncoded("DER");
        }
        return null;
    }

    private boolean doVerify(SignerInformationVerifier signerInformationVerifier) throws CMSException {
        ASN1Object aSN1Object;
        Object object;
        Object object2;
        Object object3;
        Object object4;
        ContentVerifier contentVerifier;
        String string = CMSSignedHelper.INSTANCE.getEncryptionAlgName(this.getEncryptionAlgOID());
        try {
            contentVerifier = signerInformationVerifier.getContentVerifier(this.encryptionAlgorithm, this.info.getDigestAlgorithm());
        } catch (OperatorCreationException operatorCreationException) {
            throw new CMSException("can't create content verifier: " + operatorCreationException.getMessage(), operatorCreationException);
        }
        try {
            object4 = contentVerifier.getOutputStream();
            if (this.resultDigest == null) {
                object3 = signerInformationVerifier.getDigestCalculator(this.getDigestAlgorithmID());
                if (this.content != null) {
                    object2 = object3.getOutputStream();
                    if (this.signedAttributeSet == null) {
                        if (contentVerifier instanceof RawContentVerifier) {
                            this.content.write((OutputStream)object2);
                        } else {
                            object = new TeeOutputStream((OutputStream)object2, (OutputStream)object4);
                            this.content.write((OutputStream)object);
                            ((OutputStream)object).close();
                        }
                    } else {
                        this.content.write((OutputStream)object2);
                        ((OutputStream)object4).write(this.getEncodedSignedAttributes());
                    }
                    ((OutputStream)object2).close();
                } else if (this.signedAttributeSet != null) {
                    ((OutputStream)object4).write(this.getEncodedSignedAttributes());
                } else {
                    throw new CMSException("data not encapsulated in signature - use detached constructor.");
                }
                this.resultDigest = object3.getDigest();
            } else if (this.signedAttributeSet == null) {
                if (this.content != null) {
                    this.content.write((OutputStream)object4);
                }
            } else {
                ((OutputStream)object4).write(this.getEncodedSignedAttributes());
            }
            ((OutputStream)object4).close();
        } catch (IOException iOException) {
            throw new CMSException("can't process mime object to create signature.", iOException);
        } catch (OperatorCreationException operatorCreationException) {
            throw new CMSException("can't create digest calculator: " + operatorCreationException.getMessage(), operatorCreationException);
        }
        object4 = this.getSingleValuedSignedAttribute(CMSAttributes.contentType, "content-type");
        if (object4 == null) {
            if (!this.isCounterSignature && this.signedAttributeSet != null) {
                throw new CMSException("The content-type attribute type MUST be present whenever signed attributes are present in signed-data");
            }
        } else {
            if (this.isCounterSignature) {
                throw new CMSException("[For counter signatures,] the signedAttributes field MUST NOT contain a content-type attribute");
            }
            if (!(object4 instanceof ASN1ObjectIdentifier)) {
                throw new CMSException("content-type attribute value not of ASN.1 type 'OBJECT IDENTIFIER'");
            }
            object3 = (ASN1ObjectIdentifier)object4;
            if (!((ASN1Primitive)object3).equals(this.contentType)) {
                throw new CMSException("content-type attribute value does not match eContentType");
            }
        }
        object4 = this.getSignedAttributes();
        object3 = this.getUnsignedAttributes();
        if (object3 != null && ((AttributeTable)object3).getAll(CMSAttributes.cmsAlgorithmProtect).size() > 0) {
            throw new CMSException("A cmsAlgorithmProtect attribute MUST be a signed attribute");
        }
        if (object4 != null) {
            object2 = ((AttributeTable)object4).getAll(CMSAttributes.cmsAlgorithmProtect);
            if (((ASN1EncodableVector)object2).size() > 1) {
                throw new CMSException("Only one instance of a cmsAlgorithmProtect attribute can be present");
            }
            if (((ASN1EncodableVector)object2).size() > 0) {
                object = Attribute.getInstance(((ASN1EncodableVector)object2).get(0));
                if (((Attribute)object).getAttrValues().size() != 1) {
                    throw new CMSException("A cmsAlgorithmProtect attribute MUST contain exactly one value");
                }
                aSN1Object = CMSAlgorithmProtection.getInstance(((Attribute)object).getAttributeValues()[0]);
                if (!CMSUtils.isEquivalent(((CMSAlgorithmProtection)aSN1Object).getDigestAlgorithm(), this.info.getDigestAlgorithm())) {
                    throw new CMSException("CMS Algorithm Identifier Protection check failed for digestAlgorithm");
                }
                if (!CMSUtils.isEquivalent(((CMSAlgorithmProtection)aSN1Object).getSignatureAlgorithm(), this.info.getDigestEncryptionAlgorithm())) {
                    throw new CMSException("CMS Algorithm Identifier Protection check failed for signatureAlgorithm");
                }
            }
        }
        if ((object3 = this.getSingleValuedSignedAttribute(CMSAttributes.messageDigest, "message-digest")) == null) {
            if (this.signedAttributeSet != null) {
                throw new CMSException("the message-digest signed attribute type MUST be present when there are any signed attributes present");
            }
        } else {
            if (!(object3 instanceof ASN1OctetString)) {
                throw new CMSException("message-digest attribute value not of ASN.1 type 'OCTET STRING'");
            }
            object2 = (ASN1OctetString)object3;
            if (!Arrays.constantTimeAreEqual(this.resultDigest, ((ASN1OctetString)object2).getOctets())) {
                throw new CMSSignerDigestMismatchException("message-digest attribute value does not match calculated value");
            }
        }
        if (object4 != null && ((AttributeTable)object4).getAll(CMSAttributes.counterSignature).size() > 0) {
            throw new CMSException("A countersignature attribute MUST NOT be a signed attribute");
        }
        object3 = this.getUnsignedAttributes();
        if (object3 != null) {
            object2 = ((AttributeTable)object3).getAll(CMSAttributes.counterSignature);
            for (int i = 0; i < ((ASN1EncodableVector)object2).size(); ++i) {
                aSN1Object = Attribute.getInstance(((ASN1EncodableVector)object2).get(i));
                if (((Attribute)aSN1Object).getAttrValues().size() >= 1) continue;
                throw new CMSException("A countersignature attribute MUST contain at least one AttributeValue");
            }
        }
        try {
            if (this.signedAttributeSet == null && this.resultDigest != null && contentVerifier instanceof RawContentVerifier) {
                object3 = (RawContentVerifier)((Object)contentVerifier);
                if (string.equals("RSA")) {
                    object2 = new DigestInfo(new AlgorithmIdentifier(this.digestAlgorithm.getAlgorithm(), DERNull.INSTANCE), this.resultDigest);
                    return object3.verify(((ASN1Object)object2).getEncoded("DER"), this.getSignature());
                }
                return object3.verify(this.resultDigest, this.getSignature());
            }
            return contentVerifier.verify(this.getSignature());
        } catch (IOException iOException) {
            throw new CMSException("can't process mime object to create signature.", iOException);
        }
    }

    public boolean verify(SignerInformationVerifier signerInformationVerifier) throws CMSException {
        X509CertificateHolder x509CertificateHolder;
        Time time = this.getSigningTime();
        if (signerInformationVerifier.hasAssociatedCertificate() && time != null && !(x509CertificateHolder = signerInformationVerifier.getAssociatedCertificate()).isValidOn(time.getDate())) {
            throw new CMSVerifierCertificateNotValidException("verifier not valid at signingTime");
        }
        return this.doVerify(signerInformationVerifier);
    }

    public SignerInfo toASN1Structure() {
        return this.info;
    }

    private ASN1Primitive getSingleValuedSignedAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) throws CMSException {
        AttributeTable attributeTable = this.getUnsignedAttributes();
        if (attributeTable != null && attributeTable.getAll(aSN1ObjectIdentifier).size() > 0) {
            throw new CMSException("The " + string + " attribute MUST NOT be an unsigned attribute");
        }
        AttributeTable attributeTable2 = this.getSignedAttributes();
        if (attributeTable2 == null) {
            return null;
        }
        ASN1EncodableVector aSN1EncodableVector = attributeTable2.getAll(aSN1ObjectIdentifier);
        switch (aSN1EncodableVector.size()) {
            case 0: {
                return null;
            }
            case 1: {
                Attribute attribute = (Attribute)aSN1EncodableVector.get(0);
                ASN1Set aSN1Set = attribute.getAttrValues();
                if (aSN1Set.size() != 1) {
                    throw new CMSException("A " + string + " attribute MUST have a single attribute value");
                }
                return aSN1Set.getObjectAt(0).toASN1Primitive();
            }
        }
        throw new CMSException("The SignedAttributes in a signerInfo MUST NOT include multiple instances of the " + string + " attribute");
    }

    private Time getSigningTime() throws CMSException {
        ASN1Primitive aSN1Primitive = this.getSingleValuedSignedAttribute(CMSAttributes.signingTime, "signing-time");
        if (aSN1Primitive == null) {
            return null;
        }
        try {
            return Time.getInstance(aSN1Primitive);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CMSException("signing-time attribute value not a valid 'Time' structure");
        }
    }

    public static SignerInformation replaceUnsignedAttributes(SignerInformation signerInformation, AttributeTable attributeTable) {
        SignerInfo signerInfo = signerInformation.info;
        DERSet dERSet = null;
        if (attributeTable != null) {
            dERSet = new DERSet(attributeTable.toASN1EncodableVector());
        }
        return new SignerInformation(new SignerInfo(signerInfo.getSID(), signerInfo.getDigestAlgorithm(), signerInfo.getAuthenticatedAttributes(), signerInfo.getDigestEncryptionAlgorithm(), signerInfo.getEncryptedDigest(), dERSet), signerInformation.contentType, signerInformation.content, null);
    }

    public static SignerInformation addCounterSigners(SignerInformation signerInformation, SignerInformationStore signerInformationStore) {
        SignerInfo signerInfo = signerInformation.info;
        AttributeTable attributeTable = signerInformation.getUnsignedAttributes();
        ASN1EncodableVector aSN1EncodableVector = attributeTable != null ? attributeTable.toASN1EncodableVector() : new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        Iterator<SignerInformation> iterator = signerInformationStore.getSigners().iterator();
        while (iterator.hasNext()) {
            aSN1EncodableVector2.add(iterator.next().toASN1Structure());
        }
        aSN1EncodableVector.add(new Attribute(CMSAttributes.counterSignature, new DERSet(aSN1EncodableVector2)));
        return new SignerInformation(new SignerInfo(signerInfo.getSID(), signerInfo.getDigestAlgorithm(), signerInfo.getAuthenticatedAttributes(), signerInfo.getDigestEncryptionAlgorithm(), signerInfo.getEncryptedDigest(), new DERSet(aSN1EncodableVector)), signerInformation.contentType, signerInformation.content, null);
    }
}

