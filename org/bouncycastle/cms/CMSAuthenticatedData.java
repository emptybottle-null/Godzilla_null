/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.AuthenticatedData;
import org.bouncycastle.asn1.cms.CMSAlgorithmProtection;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.AuthAttributesProvider;
import org.bouncycastle.cms.CMSEnvelopedHelper;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSecureReadable;
import org.bouncycastle.cms.CMSUtils;
import org.bouncycastle.cms.OriginatorInformation;
import org.bouncycastle.cms.RecipientInformationStore;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Encodable;

public class CMSAuthenticatedData
implements Encodable {
    RecipientInformationStore recipientInfoStore;
    ContentInfo contentInfo;
    private AlgorithmIdentifier macAlg;
    private ASN1Set authAttrs;
    private ASN1Set unauthAttrs;
    private byte[] mac;
    private OriginatorInformation originatorInfo;

    public CMSAuthenticatedData(byte[] byArray) throws CMSException {
        this(CMSUtils.readContentInfo(byArray));
    }

    public CMSAuthenticatedData(byte[] byArray, DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        this(CMSUtils.readContentInfo(byArray), digestCalculatorProvider);
    }

    public CMSAuthenticatedData(InputStream inputStream) throws CMSException {
        this(CMSUtils.readContentInfo(inputStream));
    }

    public CMSAuthenticatedData(InputStream inputStream, DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        this(CMSUtils.readContentInfo(inputStream), digestCalculatorProvider);
    }

    public CMSAuthenticatedData(ContentInfo contentInfo) throws CMSException {
        this(contentInfo, null);
    }

    public CMSAuthenticatedData(ContentInfo contentInfo, DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        this.contentInfo = contentInfo;
        AuthenticatedData authenticatedData = AuthenticatedData.getInstance(contentInfo.getContent());
        if (authenticatedData.getOriginatorInfo() != null) {
            this.originatorInfo = new OriginatorInformation(authenticatedData.getOriginatorInfo());
        }
        ASN1Set aSN1Set = authenticatedData.getRecipientInfos();
        this.macAlg = authenticatedData.getMacAlgorithm();
        this.authAttrs = authenticatedData.getAuthAttrs();
        this.mac = authenticatedData.getMac().getOctets();
        this.unauthAttrs = authenticatedData.getUnauthAttrs();
        ContentInfo contentInfo2 = authenticatedData.getEncapsulatedContentInfo();
        CMSProcessableByteArray cMSProcessableByteArray = new CMSProcessableByteArray(ASN1OctetString.getInstance(contentInfo2.getContent()).getOctets());
        if (this.authAttrs != null) {
            Object object;
            if (digestCalculatorProvider == null) {
                throw new CMSException("a digest calculator provider is required if authenticated attributes are present");
            }
            AttributeTable attributeTable = new AttributeTable(this.authAttrs);
            ASN1EncodableVector aSN1EncodableVector = attributeTable.getAll(CMSAttributes.cmsAlgorithmProtect);
            if (aSN1EncodableVector.size() > 1) {
                throw new CMSException("Only one instance of a cmsAlgorithmProtect attribute can be present");
            }
            if (aSN1EncodableVector.size() > 0) {
                object = Attribute.getInstance(aSN1EncodableVector.get(0));
                if (((Attribute)object).getAttrValues().size() != 1) {
                    throw new CMSException("A cmsAlgorithmProtect attribute MUST contain exactly one value");
                }
                CMSAlgorithmProtection cMSAlgorithmProtection = CMSAlgorithmProtection.getInstance(((Attribute)object).getAttributeValues()[0]);
                if (!CMSUtils.isEquivalent(cMSAlgorithmProtection.getDigestAlgorithm(), authenticatedData.getDigestAlgorithm())) {
                    throw new CMSException("CMS Algorithm Identifier Protection check failed for digestAlgorithm");
                }
                if (!CMSUtils.isEquivalent(cMSAlgorithmProtection.getMacAlgorithm(), this.macAlg)) {
                    throw new CMSException("CMS Algorithm Identifier Protection check failed for macAlgorithm");
                }
            }
            try {
                object = new CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable(digestCalculatorProvider.get(authenticatedData.getDigestAlgorithm()), cMSProcessableByteArray);
                this.recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(aSN1Set, this.macAlg, (CMSSecureReadable)object, new AuthAttributesProvider(){

                    public ASN1Set getAuthAttributes() {
                        return CMSAuthenticatedData.this.authAttrs;
                    }
                });
            } catch (OperatorCreationException operatorCreationException) {
                throw new CMSException("unable to create digest calculator: " + operatorCreationException.getMessage(), operatorCreationException);
            }
        } else {
            CMSEnvelopedHelper.CMSAuthenticatedSecureReadable cMSAuthenticatedSecureReadable = new CMSEnvelopedHelper.CMSAuthenticatedSecureReadable(this.macAlg, cMSProcessableByteArray);
            this.recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(aSN1Set, this.macAlg, cMSAuthenticatedSecureReadable);
        }
    }

    public OriginatorInformation getOriginatorInfo() {
        return this.originatorInfo;
    }

    public byte[] getMac() {
        return Arrays.clone(this.mac);
    }

    private byte[] encodeObj(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public AlgorithmIdentifier getMacAlgorithm() {
        return this.macAlg;
    }

    public String getMacAlgOID() {
        return this.macAlg.getAlgorithm().getId();
    }

    public byte[] getMacAlgParams() {
        try {
            return this.encodeObj(this.macAlg.getParameters());
        } catch (Exception exception) {
            throw new RuntimeException("exception getting encryption parameters " + exception);
        }
    }

    public RecipientInformationStore getRecipientInfos() {
        return this.recipientInfoStore;
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public ContentInfo toASN1Structure() {
        return this.contentInfo;
    }

    public AttributeTable getAuthAttrs() {
        if (this.authAttrs == null) {
            return null;
        }
        return new AttributeTable(this.authAttrs);
    }

    public AttributeTable getUnauthAttrs() {
        if (this.unauthAttrs == null) {
            return null;
        }
        return new AttributeTable(this.unauthAttrs);
    }

    public byte[] getEncoded() throws IOException {
        return this.contentInfo.getEncoded();
    }

    public byte[] getContentDigest() {
        if (this.authAttrs != null) {
            return ASN1OctetString.getInstance(this.getAuthAttrs().get(CMSAttributes.messageDigest).getAttrValues().getObjectAt(0)).getOctets();
        }
        return null;
    }
}

