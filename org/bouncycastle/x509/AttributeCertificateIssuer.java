/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.CertSelector;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AttCertIssuer;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.V2Form;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.util.Selector;

public class AttributeCertificateIssuer
implements CertSelector,
Selector {
    final ASN1Encodable form;

    public AttributeCertificateIssuer(AttCertIssuer attCertIssuer) {
        this.form = attCertIssuer.getIssuer();
    }

    public AttributeCertificateIssuer(X500Principal x500Principal) throws IOException {
        this(new X509Principal(x500Principal.getEncoded()));
    }

    public AttributeCertificateIssuer(X509Principal x509Principal) {
        this.form = new V2Form(GeneralNames.getInstance(new DERSequence(new GeneralName(x509Principal))));
    }

    private Object[] getNames() {
        GeneralNames generalNames = this.form instanceof V2Form ? ((V2Form)this.form).getIssuerName() : (GeneralNames)this.form;
        GeneralName[] generalNameArray = generalNames.getNames();
        ArrayList<X500Principal> arrayList = new ArrayList<X500Principal>(generalNameArray.length);
        for (int i = 0; i != generalNameArray.length; ++i) {
            if (generalNameArray[i].getTagNo() != 4) continue;
            try {
                arrayList.add(new X500Principal(generalNameArray[i].getName().toASN1Primitive().getEncoded()));
                continue;
            } catch (IOException iOException) {
                throw new RuntimeException("badly formed Name object");
            }
        }
        return arrayList.toArray(new Object[arrayList.size()]);
    }

    public Principal[] getPrincipals() {
        Object[] objectArray = this.getNames();
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i = 0; i != objectArray.length; ++i) {
            if (!(objectArray[i] instanceof Principal)) continue;
            arrayList.add(objectArray[i]);
        }
        return arrayList.toArray(new Principal[arrayList.size()]);
    }

    private boolean matchesDN(X500Principal x500Principal, GeneralNames generalNames) {
        GeneralName[] generalNameArray = generalNames.getNames();
        for (int i = 0; i != generalNameArray.length; ++i) {
            GeneralName generalName = generalNameArray[i];
            if (generalName.getTagNo() != 4) continue;
            try {
                if (!new X500Principal(generalName.getName().toASN1Primitive().getEncoded()).equals(x500Principal)) continue;
                return true;
            } catch (IOException iOException) {
                // empty catch block
            }
        }
        return false;
    }

    public Object clone() {
        return new AttributeCertificateIssuer(AttCertIssuer.getInstance(this.form));
    }

    public boolean match(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            return false;
        }
        X509Certificate x509Certificate = (X509Certificate)certificate;
        if (this.form instanceof V2Form) {
            V2Form v2Form = (V2Form)this.form;
            if (v2Form.getBaseCertificateID() != null) {
                return v2Form.getBaseCertificateID().getSerial().getValue().equals(x509Certificate.getSerialNumber()) && this.matchesDN(x509Certificate.getIssuerX500Principal(), v2Form.getBaseCertificateID().getIssuer());
            }
            GeneralNames generalNames = v2Form.getIssuerName();
            if (this.matchesDN(x509Certificate.getSubjectX500Principal(), generalNames)) {
                return true;
            }
        } else {
            GeneralNames generalNames = (GeneralNames)this.form;
            if (this.matchesDN(x509Certificate.getSubjectX500Principal(), generalNames)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof AttributeCertificateIssuer)) {
            return false;
        }
        AttributeCertificateIssuer attributeCertificateIssuer = (AttributeCertificateIssuer)object;
        return this.form.equals(attributeCertificateIssuer.form);
    }

    public int hashCode() {
        return this.form.hashCode();
    }

    public boolean match(Object object) {
        if (!(object instanceof X509Certificate)) {
            return false;
        }
        return this.match((Certificate)object);
    }
}

