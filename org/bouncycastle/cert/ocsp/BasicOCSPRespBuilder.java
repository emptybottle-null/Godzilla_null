/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.ocsp;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERGeneralizedTime;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.asn1.ocsp.CertStatus;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.ocsp.RevokedInfo;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.CRLReason;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.CertificateID;
import org.bouncycastle.cert.ocsp.CertificateStatus;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.RespID;
import org.bouncycastle.cert.ocsp.RevokedStatus;
import org.bouncycastle.cert.ocsp.UnknownStatus;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DigestCalculator;

public class BasicOCSPRespBuilder {
    private List list = new ArrayList();
    private Extensions responseExtensions = null;
    private RespID responderID;

    public BasicOCSPRespBuilder(RespID respID) {
        this.responderID = respID;
    }

    public BasicOCSPRespBuilder(SubjectPublicKeyInfo subjectPublicKeyInfo, DigestCalculator digestCalculator) throws OCSPException {
        this.responderID = new RespID(subjectPublicKeyInfo, digestCalculator);
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certificateID, CertificateStatus certificateStatus) {
        this.addResponse(certificateID, certificateStatus, new Date(), null, null);
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certificateID, CertificateStatus certificateStatus, Extensions extensions) {
        this.addResponse(certificateID, certificateStatus, new Date(), null, extensions);
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certificateID, CertificateStatus certificateStatus, Date date, Extensions extensions) {
        this.addResponse(certificateID, certificateStatus, new Date(), date, extensions);
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certificateID, CertificateStatus certificateStatus, Date date, Date date2) {
        this.addResponse(certificateID, certificateStatus, date, date2, null);
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certificateID, CertificateStatus certificateStatus, Date date, Date date2, Extensions extensions) {
        this.list.add(new ResponseObject(certificateID, certificateStatus, date, date2, extensions));
        return this;
    }

    public BasicOCSPRespBuilder setResponseExtensions(Extensions extensions) {
        this.responseExtensions = extensions;
        return this;
    }

    public BasicOCSPResp build(ContentSigner contentSigner, X509CertificateHolder[] x509CertificateHolderArray, Date date) throws OCSPException {
        DERBitString dERBitString;
        Object object;
        Iterator iterator = this.list.iterator();
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (iterator.hasNext()) {
            try {
                aSN1EncodableVector.add(((ResponseObject)iterator.next()).toResponse());
            } catch (Exception exception) {
                throw new OCSPException("exception creating Request", exception);
            }
        }
        ResponseData responseData = new ResponseData(this.responderID.toASN1Primitive(), new ASN1GeneralizedTime(date), (ASN1Sequence)new DERSequence(aSN1EncodableVector), this.responseExtensions);
        try {
            object = contentSigner.getOutputStream();
            ((OutputStream)object).write(responseData.getEncoded("DER"));
            ((OutputStream)object).close();
            dERBitString = new DERBitString(contentSigner.getSignature());
        } catch (Exception exception) {
            throw new OCSPException("exception processing TBSRequest: " + exception.getMessage(), exception);
        }
        object = contentSigner.getAlgorithmIdentifier();
        DERSequence dERSequence = null;
        if (x509CertificateHolderArray != null && x509CertificateHolderArray.length > 0) {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            for (int i = 0; i != x509CertificateHolderArray.length; ++i) {
                aSN1EncodableVector2.add(x509CertificateHolderArray[i].toASN1Structure());
            }
            dERSequence = new DERSequence(aSN1EncodableVector2);
        }
        return new BasicOCSPResp(new BasicOCSPResponse(responseData, (AlgorithmIdentifier)object, dERBitString, dERSequence));
    }

    private class ResponseObject {
        CertificateID certId;
        CertStatus certStatus;
        ASN1GeneralizedTime thisUpdate;
        ASN1GeneralizedTime nextUpdate;
        Extensions extensions;

        public ResponseObject(CertificateID certificateID, CertificateStatus certificateStatus, Date date, Date date2, Extensions extensions) {
            RevokedStatus revokedStatus;
            this.certId = certificateID;
            this.certStatus = certificateStatus == null ? new CertStatus() : (certificateStatus instanceof UnknownStatus ? new CertStatus(2, DERNull.INSTANCE) : ((revokedStatus = (RevokedStatus)certificateStatus).hasRevocationReason() ? new CertStatus(new RevokedInfo(new ASN1GeneralizedTime(revokedStatus.getRevocationTime()), CRLReason.lookup(revokedStatus.getRevocationReason()))) : new CertStatus(new RevokedInfo(new ASN1GeneralizedTime(revokedStatus.getRevocationTime()), null))));
            this.thisUpdate = new DERGeneralizedTime(date);
            this.nextUpdate = date2 != null ? new DERGeneralizedTime(date2) : null;
            this.extensions = extensions;
        }

        public SingleResponse toResponse() throws Exception {
            return new SingleResponse(this.certId.toASN1Primitive(), this.certStatus, this.thisUpdate, this.nextUpdate, this.extensions);
        }
    }
}

