/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.ess.ESSCertID;
import org.bouncycastle.asn1.ess.ESSCertIDv2;
import org.bouncycastle.asn1.ess.SigningCertificate;
import org.bouncycastle.asn1.ess.SigningCertificateV2;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TSPUtil;
import org.bouncycastle.tsp.TSPValidationException;
import org.bouncycastle.tsp.TimeStampTokenInfo;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Store;

public class TimeStampToken {
    CMSSignedData tsToken;
    SignerInformation tsaSignerInfo;
    Date genTime;
    TimeStampTokenInfo tstInfo;
    CertID certID;

    public TimeStampToken(ContentInfo contentInfo) throws TSPException, IOException {
        this(TimeStampToken.getSignedData(contentInfo));
    }

    private static CMSSignedData getSignedData(ContentInfo contentInfo) throws TSPException {
        try {
            return new CMSSignedData(contentInfo);
        } catch (CMSException cMSException) {
            throw new TSPException("TSP parsing error: " + cMSException.getMessage(), cMSException.getCause());
        }
    }

    public TimeStampToken(CMSSignedData cMSSignedData) throws TSPException, IOException {
        this.tsToken = cMSSignedData;
        if (!this.tsToken.getSignedContentTypeOID().equals(PKCSObjectIdentifiers.id_ct_TSTInfo.getId())) {
            throw new TSPValidationException("ContentInfo object not for a time stamp.");
        }
        Collection<SignerInformation> collection = this.tsToken.getSignerInfos().getSigners();
        if (collection.size() != 1) {
            throw new IllegalArgumentException("Time-stamp token signed by " + collection.size() + " signers, but it must contain just the TSA signature.");
        }
        this.tsaSignerInfo = collection.iterator().next();
        try {
            CMSTypedData cMSTypedData = this.tsToken.getSignedContent();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            cMSTypedData.write(byteArrayOutputStream);
            ASN1InputStream aSN1InputStream = new ASN1InputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            this.tstInfo = new TimeStampTokenInfo(TSTInfo.getInstance(aSN1InputStream.readObject()));
            Attribute attribute = this.tsaSignerInfo.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
            if (attribute != null) {
                SigningCertificate signingCertificate = SigningCertificate.getInstance(attribute.getAttrValues().getObjectAt(0));
                this.certID = new CertID(ESSCertID.getInstance(signingCertificate.getCerts()[0]));
            } else {
                attribute = this.tsaSignerInfo.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
                if (attribute == null) {
                    throw new TSPValidationException("no signing certificate attribute found, time stamp invalid.");
                }
                SigningCertificateV2 signingCertificateV2 = SigningCertificateV2.getInstance(attribute.getAttrValues().getObjectAt(0));
                this.certID = new CertID(ESSCertIDv2.getInstance(signingCertificateV2.getCerts()[0]));
            }
        } catch (CMSException cMSException) {
            throw new TSPException(cMSException.getMessage(), cMSException.getUnderlyingException());
        }
    }

    public TimeStampTokenInfo getTimeStampInfo() {
        return this.tstInfo;
    }

    public SignerId getSID() {
        return this.tsaSignerInfo.getSID();
    }

    public AttributeTable getSignedAttributes() {
        return this.tsaSignerInfo.getSignedAttributes();
    }

    public AttributeTable getUnsignedAttributes() {
        return this.tsaSignerInfo.getUnsignedAttributes();
    }

    public Store getCertificates() {
        return this.tsToken.getCertificates();
    }

    public Store getCRLs() {
        return this.tsToken.getCRLs();
    }

    public Store getAttributeCertificates() {
        return this.tsToken.getAttributeCertificates();
    }

    public void validate(SignerInformationVerifier signerInformationVerifier) throws TSPException, TSPValidationException {
        if (!signerInformationVerifier.hasAssociatedCertificate()) {
            throw new IllegalArgumentException("verifier provider needs an associated certificate");
        }
        try {
            X509CertificateHolder x509CertificateHolder = signerInformationVerifier.getAssociatedCertificate();
            DigestCalculator digestCalculator = signerInformationVerifier.getDigestCalculator(this.certID.getHashAlgorithm());
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(x509CertificateHolder.getEncoded());
            outputStream.close();
            if (!Arrays.constantTimeAreEqual(this.certID.getCertHash(), digestCalculator.getDigest())) {
                throw new TSPValidationException("certificate hash does not match certID hash.");
            }
            if (this.certID.getIssuerSerial() != null) {
                IssuerAndSerialNumber issuerAndSerialNumber = new IssuerAndSerialNumber(x509CertificateHolder.toASN1Structure());
                if (!this.certID.getIssuerSerial().getSerial().equals(issuerAndSerialNumber.getSerialNumber())) {
                    throw new TSPValidationException("certificate serial number does not match certID for signature.");
                }
                GeneralName[] generalNameArray = this.certID.getIssuerSerial().getIssuer().getNames();
                boolean bl = false;
                for (int i = 0; i != generalNameArray.length; ++i) {
                    if (generalNameArray[i].getTagNo() != 4 || !X500Name.getInstance(generalNameArray[i].getName()).equals(X500Name.getInstance(issuerAndSerialNumber.getName()))) continue;
                    bl = true;
                    break;
                }
                if (!bl) {
                    throw new TSPValidationException("certificate name does not match certID for signature. ");
                }
            }
            TSPUtil.validateCertificate(x509CertificateHolder);
            if (!x509CertificateHolder.isValidOn(this.tstInfo.getGenTime())) {
                throw new TSPValidationException("certificate not valid when time stamp created.");
            }
            if (!this.tsaSignerInfo.verify(signerInformationVerifier)) {
                throw new TSPValidationException("signature not created by certificate.");
            }
        } catch (CMSException cMSException) {
            if (cMSException.getUnderlyingException() != null) {
                throw new TSPException(cMSException.getMessage(), cMSException.getUnderlyingException());
            }
            throw new TSPException("CMS exception: " + cMSException, cMSException);
        } catch (IOException iOException) {
            throw new TSPException("problem processing certificate: " + iOException, iOException);
        } catch (OperatorCreationException operatorCreationException) {
            throw new TSPException("unable to create digest: " + operatorCreationException.getMessage(), operatorCreationException);
        }
    }

    public boolean isSignatureValid(SignerInformationVerifier signerInformationVerifier) throws TSPException {
        try {
            return this.tsaSignerInfo.verify(signerInformationVerifier);
        } catch (CMSException cMSException) {
            if (cMSException.getUnderlyingException() != null) {
                throw new TSPException(cMSException.getMessage(), cMSException.getUnderlyingException());
            }
            throw new TSPException("CMS exception: " + cMSException, cMSException);
        }
    }

    public CMSSignedData toCMSSignedData() {
        return this.tsToken;
    }

    public byte[] getEncoded() throws IOException {
        return this.tsToken.getEncoded();
    }

    private class CertID {
        private ESSCertID certID;
        private ESSCertIDv2 certIDv2;

        CertID(ESSCertID eSSCertID) {
            this.certID = eSSCertID;
            this.certIDv2 = null;
        }

        CertID(ESSCertIDv2 eSSCertIDv2) {
            this.certIDv2 = eSSCertIDv2;
            this.certID = null;
        }

        public String getHashAlgorithmName() {
            if (this.certID != null) {
                return "SHA-1";
            }
            if (NISTObjectIdentifiers.id_sha256.equals(this.certIDv2.getHashAlgorithm().getAlgorithm())) {
                return "SHA-256";
            }
            return this.certIDv2.getHashAlgorithm().getAlgorithm().getId();
        }

        public AlgorithmIdentifier getHashAlgorithm() {
            if (this.certID != null) {
                return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
            }
            return this.certIDv2.getHashAlgorithm();
        }

        public byte[] getCertHash() {
            if (this.certID != null) {
                return this.certID.getCertHash();
            }
            return this.certIDv2.getCertHash();
        }

        public IssuerSerial getIssuerSerial() {
            if (this.certID != null) {
                return this.certID.getIssuerSerial();
            }
            return this.certIDv2.getIssuerSerial();
        }
    }
}

