/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.asn1.pkcs.CertificationRequestInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.pkcs.PKCSIOException;

public class PKCS10CertificationRequest {
    private static Attribute[] EMPTY_ARRAY = new Attribute[0];
    private CertificationRequest certificationRequest;

    private static CertificationRequest parseBytes(byte[] byArray) throws IOException {
        try {
            return CertificationRequest.getInstance(ASN1Primitive.fromByteArray(byArray));
        } catch (ClassCastException classCastException) {
            throw new PKCSIOException("malformed data: " + classCastException.getMessage(), classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new PKCSIOException("malformed data: " + illegalArgumentException.getMessage(), illegalArgumentException);
        }
    }

    public PKCS10CertificationRequest(CertificationRequest certificationRequest) {
        this.certificationRequest = certificationRequest;
    }

    public PKCS10CertificationRequest(byte[] byArray) throws IOException {
        this(PKCS10CertificationRequest.parseBytes(byArray));
    }

    public CertificationRequest toASN1Structure() {
        return this.certificationRequest;
    }

    public X500Name getSubject() {
        return X500Name.getInstance(this.certificationRequest.getCertificationRequestInfo().getSubject());
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.certificationRequest.getSignatureAlgorithm();
    }

    public byte[] getSignature() {
        return this.certificationRequest.getSignature().getOctets();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.certificationRequest.getCertificationRequestInfo().getSubjectPublicKeyInfo();
    }

    public Attribute[] getAttributes() {
        ASN1Set aSN1Set = this.certificationRequest.getCertificationRequestInfo().getAttributes();
        if (aSN1Set == null) {
            return EMPTY_ARRAY;
        }
        Attribute[] attributeArray = new Attribute[aSN1Set.size()];
        for (int i = 0; i != aSN1Set.size(); ++i) {
            attributeArray[i] = Attribute.getInstance(aSN1Set.getObjectAt(i));
        }
        return attributeArray;
    }

    public Attribute[] getAttributes(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        ASN1Set aSN1Set = this.certificationRequest.getCertificationRequestInfo().getAttributes();
        if (aSN1Set == null) {
            return EMPTY_ARRAY;
        }
        ArrayList<Attribute> arrayList = new ArrayList<Attribute>();
        for (int i = 0; i != aSN1Set.size(); ++i) {
            Attribute attribute = Attribute.getInstance(aSN1Set.getObjectAt(i));
            if (!attribute.getAttrType().equals(aSN1ObjectIdentifier)) continue;
            arrayList.add(attribute);
        }
        if (arrayList.size() == 0) {
            return EMPTY_ARRAY;
        }
        return arrayList.toArray(new Attribute[arrayList.size()]);
    }

    public byte[] getEncoded() throws IOException {
        return this.certificationRequest.getEncoded();
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws PKCSException {
        ContentVerifier contentVerifier;
        CertificationRequestInfo certificationRequestInfo = this.certificationRequest.getCertificationRequestInfo();
        try {
            contentVerifier = contentVerifierProvider.get(this.certificationRequest.getSignatureAlgorithm());
            OutputStream outputStream = contentVerifier.getOutputStream();
            outputStream.write(certificationRequestInfo.getEncoded("DER"));
            outputStream.close();
        } catch (Exception exception) {
            throw new PKCSException("unable to process signature: " + exception.getMessage(), exception);
        }
        return contentVerifier.verify(this.getSignature());
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof PKCS10CertificationRequest)) {
            return false;
        }
        PKCS10CertificationRequest pKCS10CertificationRequest = (PKCS10CertificationRequest)object;
        return this.toASN1Structure().equals(pKCS10CertificationRequest.toASN1Structure());
    }

    public int hashCode() {
        return this.toASN1Structure().hashCode();
    }
}

