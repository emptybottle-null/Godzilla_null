/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.asn1.pkcs.CertificationRequestInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

public class PKCS10CertificationRequestBuilder {
    private SubjectPublicKeyInfo publicKeyInfo;
    private X500Name subject;
    private List attributes = new ArrayList();
    private boolean leaveOffEmpty = false;

    public PKCS10CertificationRequestBuilder(PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder) {
        this.publicKeyInfo = pKCS10CertificationRequestBuilder.publicKeyInfo;
        this.subject = pKCS10CertificationRequestBuilder.subject;
        this.leaveOffEmpty = pKCS10CertificationRequestBuilder.leaveOffEmpty;
        this.attributes = new ArrayList(pKCS10CertificationRequestBuilder.attributes);
    }

    public PKCS10CertificationRequestBuilder(X500Name x500Name, SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.subject = x500Name;
        this.publicKeyInfo = subjectPublicKeyInfo;
    }

    public PKCS10CertificationRequestBuilder setAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        Iterator iterator = this.attributes.iterator();
        while (iterator.hasNext()) {
            if (!((Attribute)iterator.next()).getAttrType().equals(aSN1ObjectIdentifier)) continue;
            throw new IllegalStateException("Attribute " + aSN1ObjectIdentifier.toString() + " is already set");
        }
        this.addAttribute(aSN1ObjectIdentifier, aSN1Encodable);
        return this;
    }

    public PKCS10CertificationRequestBuilder setAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable[] aSN1EncodableArray) {
        Iterator iterator = this.attributes.iterator();
        while (iterator.hasNext()) {
            if (!((Attribute)iterator.next()).getAttrType().equals(aSN1ObjectIdentifier)) continue;
            throw new IllegalStateException("Attribute " + aSN1ObjectIdentifier.toString() + " is already set");
        }
        this.addAttribute(aSN1ObjectIdentifier, aSN1EncodableArray);
        return this;
    }

    public PKCS10CertificationRequestBuilder addAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.attributes.add(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1Encodable)));
        return this;
    }

    public PKCS10CertificationRequestBuilder addAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable[] aSN1EncodableArray) {
        this.attributes.add(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1EncodableArray)));
        return this;
    }

    public PKCS10CertificationRequestBuilder setLeaveOffEmptyAttributes(boolean bl) {
        this.leaveOffEmpty = bl;
        return this;
    }

    public PKCS10CertificationRequest build(ContentSigner contentSigner) {
        Object object;
        CertificationRequestInfo certificationRequestInfo;
        if (this.attributes.isEmpty()) {
            certificationRequestInfo = this.leaveOffEmpty ? new CertificationRequestInfo(this.subject, this.publicKeyInfo, null) : new CertificationRequestInfo(this.subject, this.publicKeyInfo, (ASN1Set)new DERSet());
        } else {
            object = new ASN1EncodableVector();
            Iterator iterator = this.attributes.iterator();
            while (iterator.hasNext()) {
                ((ASN1EncodableVector)object).add(Attribute.getInstance(iterator.next()));
            }
            certificationRequestInfo = new CertificationRequestInfo(this.subject, this.publicKeyInfo, (ASN1Set)new DERSet((ASN1EncodableVector)object));
        }
        try {
            object = contentSigner.getOutputStream();
            ((OutputStream)object).write(certificationRequestInfo.getEncoded("DER"));
            ((OutputStream)object).close();
            return new PKCS10CertificationRequest(new CertificationRequest(certificationRequestInfo, contentSigner.getAlgorithmIdentifier(), new DERBitString(contentSigner.getSignature())));
        } catch (IOException iOException) {
            throw new IllegalStateException("cannot produce certification request signature");
        }
    }
}

