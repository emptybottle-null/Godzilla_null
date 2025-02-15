/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.ocsp;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.ocsp.OCSPRequest;
import org.bouncycastle.asn1.ocsp.Request;
import org.bouncycastle.asn1.ocsp.Signature;
import org.bouncycastle.asn1.ocsp.TBSRequest;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.ocsp.CertificateID;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPReq;
import org.bouncycastle.operator.ContentSigner;

public class OCSPReqBuilder {
    private List list = new ArrayList();
    private GeneralName requestorName = null;
    private Extensions requestExtensions = null;

    public OCSPReqBuilder addRequest(CertificateID certificateID) {
        this.list.add(new RequestObject(certificateID, null));
        return this;
    }

    public OCSPReqBuilder addRequest(CertificateID certificateID, Extensions extensions) {
        this.list.add(new RequestObject(certificateID, extensions));
        return this;
    }

    public OCSPReqBuilder setRequestorName(X500Name x500Name) {
        this.requestorName = new GeneralName(4, x500Name);
        return this;
    }

    public OCSPReqBuilder setRequestorName(GeneralName generalName) {
        this.requestorName = generalName;
        return this;
    }

    public OCSPReqBuilder setRequestExtensions(Extensions extensions) {
        this.requestExtensions = extensions;
        return this;
    }

    private OCSPReq generateRequest(ContentSigner contentSigner, X509CertificateHolder[] x509CertificateHolderArray) throws OCSPException {
        Iterator iterator = this.list.iterator();
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (iterator.hasNext()) {
            try {
                aSN1EncodableVector.add(((RequestObject)iterator.next()).toRequest());
            } catch (Exception exception) {
                throw new OCSPException("exception creating Request", exception);
            }
        }
        TBSRequest tBSRequest = new TBSRequest(this.requestorName, (ASN1Sequence)new DERSequence(aSN1EncodableVector), this.requestExtensions);
        Signature signature = null;
        if (contentSigner != null) {
            Object object;
            if (this.requestorName == null) {
                throw new OCSPException("requestorName must be specified if request is signed.");
            }
            try {
                object = contentSigner.getOutputStream();
                ((OutputStream)object).write(tBSRequest.getEncoded("DER"));
                ((OutputStream)object).close();
            } catch (Exception exception) {
                throw new OCSPException("exception processing TBSRequest: " + exception, exception);
            }
            object = new DERBitString(contentSigner.getSignature());
            AlgorithmIdentifier algorithmIdentifier = contentSigner.getAlgorithmIdentifier();
            if (x509CertificateHolderArray != null && x509CertificateHolderArray.length > 0) {
                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                for (int i = 0; i != x509CertificateHolderArray.length; ++i) {
                    aSN1EncodableVector2.add(x509CertificateHolderArray[i].toASN1Structure());
                }
                signature = new Signature(algorithmIdentifier, (DERBitString)object, new DERSequence(aSN1EncodableVector2));
            } else {
                signature = new Signature(algorithmIdentifier, (DERBitString)object);
            }
        }
        return new OCSPReq(new OCSPRequest(tBSRequest, signature));
    }

    public OCSPReq build() throws OCSPException {
        return this.generateRequest(null, null);
    }

    public OCSPReq build(ContentSigner contentSigner, X509CertificateHolder[] x509CertificateHolderArray) throws OCSPException, IllegalArgumentException {
        if (contentSigner == null) {
            throw new IllegalArgumentException("no signer specified");
        }
        return this.generateRequest(contentSigner, x509CertificateHolderArray);
    }

    private class RequestObject {
        CertificateID certId;
        Extensions extensions;

        public RequestObject(CertificateID certificateID, Extensions extensions) {
            this.certId = certificateID;
            this.extensions = extensions;
        }

        public Request toRequest() throws Exception {
            return new Request(this.certId.toASN1Primitive(), this.extensions);
        }
    }
}

