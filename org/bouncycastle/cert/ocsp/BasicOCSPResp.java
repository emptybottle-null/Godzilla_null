/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.ocsp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPUtils;
import org.bouncycastle.cert.ocsp.RespID;
import org.bouncycastle.cert.ocsp.SingleResp;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.util.Encodable;

public class BasicOCSPResp
implements Encodable {
    private BasicOCSPResponse resp;
    private ResponseData data;
    private Extensions extensions;

    public BasicOCSPResp(BasicOCSPResponse basicOCSPResponse) {
        this.resp = basicOCSPResponse;
        this.data = basicOCSPResponse.getTbsResponseData();
        this.extensions = Extensions.getInstance(basicOCSPResponse.getTbsResponseData().getResponseExtensions());
    }

    public byte[] getTBSResponseData() {
        try {
            return this.resp.getTbsResponseData().getEncoded("DER");
        } catch (IOException iOException) {
            return null;
        }
    }

    public AlgorithmIdentifier getSignatureAlgorithmID() {
        return this.resp.getSignatureAlgorithm();
    }

    public int getVersion() {
        return this.data.getVersion().getValue().intValue() + 1;
    }

    public RespID getResponderId() {
        return new RespID(this.data.getResponderID());
    }

    public Date getProducedAt() {
        return OCSPUtils.extractDate(this.data.getProducedAt());
    }

    public SingleResp[] getResponses() {
        ASN1Sequence aSN1Sequence = this.data.getResponses();
        SingleResp[] singleRespArray = new SingleResp[aSN1Sequence.size()];
        for (int i = 0; i != singleRespArray.length; ++i) {
            singleRespArray[i] = new SingleResp(SingleResponse.getInstance(aSN1Sequence.getObjectAt(i)));
        }
        return singleRespArray;
    }

    public boolean hasExtensions() {
        return this.extensions != null;
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (this.extensions != null) {
            return this.extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return OCSPUtils.getExtensionOIDs(this.extensions);
    }

    public Set getCriticalExtensionOIDs() {
        return OCSPUtils.getCriticalExtensionOIDs(this.extensions);
    }

    public Set getNonCriticalExtensionOIDs() {
        return OCSPUtils.getNonCriticalExtensionOIDs(this.extensions);
    }

    public ASN1ObjectIdentifier getSignatureAlgOID() {
        return this.resp.getSignatureAlgorithm().getAlgorithm();
    }

    public byte[] getSignature() {
        return this.resp.getSignature().getOctets();
    }

    public X509CertificateHolder[] getCerts() {
        if (this.resp.getCerts() != null) {
            ASN1Sequence aSN1Sequence = this.resp.getCerts();
            if (aSN1Sequence != null) {
                X509CertificateHolder[] x509CertificateHolderArray = new X509CertificateHolder[aSN1Sequence.size()];
                for (int i = 0; i != x509CertificateHolderArray.length; ++i) {
                    x509CertificateHolderArray[i] = new X509CertificateHolder(Certificate.getInstance(aSN1Sequence.getObjectAt(i)));
                }
                return x509CertificateHolderArray;
            }
            return OCSPUtils.EMPTY_CERTS;
        }
        return OCSPUtils.EMPTY_CERTS;
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws OCSPException {
        try {
            ContentVerifier contentVerifier = contentVerifierProvider.get(this.resp.getSignatureAlgorithm());
            OutputStream outputStream = contentVerifier.getOutputStream();
            outputStream.write(this.resp.getTbsResponseData().getEncoded("DER"));
            outputStream.close();
            return contentVerifier.verify(this.getSignature());
        } catch (Exception exception) {
            throw new OCSPException("exception processing sig: " + exception, exception);
        }
    }

    public byte[] getEncoded() throws IOException {
        return this.resp.getEncoded();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof BasicOCSPResp)) {
            return false;
        }
        BasicOCSPResp basicOCSPResp = (BasicOCSPResp)object;
        return this.resp.equals(basicOCSPResp.resp);
    }

    public int hashCode() {
        return this.resp.hashCode();
    }
}

