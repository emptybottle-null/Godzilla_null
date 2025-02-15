/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.ocsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Exception;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ocsp.OCSPRequest;
import org.bouncycastle.asn1.ocsp.Request;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPUtils;
import org.bouncycastle.cert.ocsp.Req;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;

public class OCSPReq {
    private static final X509CertificateHolder[] EMPTY_CERTS = new X509CertificateHolder[0];
    private OCSPRequest req;
    private Extensions extensions;

    public OCSPReq(OCSPRequest oCSPRequest) {
        this.req = oCSPRequest;
        this.extensions = oCSPRequest.getTbsRequest().getRequestExtensions();
    }

    public OCSPReq(byte[] byArray) throws IOException {
        this(new ASN1InputStream(byArray));
    }

    private OCSPReq(ASN1InputStream aSN1InputStream) throws IOException {
        try {
            this.req = OCSPRequest.getInstance(aSN1InputStream.readObject());
            if (this.req == null) {
                throw new CertIOException("malformed request: no request data found");
            }
            this.extensions = this.req.getTbsRequest().getRequestExtensions();
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CertIOException("malformed request: " + illegalArgumentException.getMessage(), illegalArgumentException);
        } catch (ClassCastException classCastException) {
            throw new CertIOException("malformed request: " + classCastException.getMessage(), classCastException);
        } catch (ASN1Exception aSN1Exception) {
            throw new CertIOException("malformed request: " + aSN1Exception.getMessage(), aSN1Exception);
        }
    }

    public int getVersionNumber() {
        return this.req.getTbsRequest().getVersion().getValue().intValue() + 1;
    }

    public GeneralName getRequestorName() {
        return GeneralName.getInstance(this.req.getTbsRequest().getRequestorName());
    }

    public Req[] getRequestList() {
        ASN1Sequence aSN1Sequence = this.req.getTbsRequest().getRequestList();
        Req[] reqArray = new Req[aSN1Sequence.size()];
        for (int i = 0; i != reqArray.length; ++i) {
            reqArray[i] = new Req(Request.getInstance(aSN1Sequence.getObjectAt(i)));
        }
        return reqArray;
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
        if (!this.isSigned()) {
            return null;
        }
        return this.req.getOptionalSignature().getSignatureAlgorithm().getAlgorithm();
    }

    public byte[] getSignature() {
        if (!this.isSigned()) {
            return null;
        }
        return this.req.getOptionalSignature().getSignature().getOctets();
    }

    public X509CertificateHolder[] getCerts() {
        if (this.req.getOptionalSignature() != null) {
            ASN1Sequence aSN1Sequence = this.req.getOptionalSignature().getCerts();
            if (aSN1Sequence != null) {
                X509CertificateHolder[] x509CertificateHolderArray = new X509CertificateHolder[aSN1Sequence.size()];
                for (int i = 0; i != x509CertificateHolderArray.length; ++i) {
                    x509CertificateHolderArray[i] = new X509CertificateHolder(Certificate.getInstance(aSN1Sequence.getObjectAt(i)));
                }
                return x509CertificateHolderArray;
            }
            return EMPTY_CERTS;
        }
        return EMPTY_CERTS;
    }

    public boolean isSigned() {
        return this.req.getOptionalSignature() != null;
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws OCSPException {
        if (!this.isSigned()) {
            throw new OCSPException("attempt to verify signature on unsigned object");
        }
        try {
            ContentVerifier contentVerifier = contentVerifierProvider.get(this.req.getOptionalSignature().getSignatureAlgorithm());
            OutputStream outputStream = contentVerifier.getOutputStream();
            outputStream.write(this.req.getTbsRequest().getEncoded("DER"));
            return contentVerifier.verify(this.getSignature());
        } catch (Exception exception) {
            throw new OCSPException("exception processing signature: " + exception, exception);
        }
    }

    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
        aSN1OutputStream.writeObject(this.req);
        return byteArrayOutputStream.toByteArray();
    }
}

