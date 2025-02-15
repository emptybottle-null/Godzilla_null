/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.X509Extension;

public class X509Extensions
extends ASN1Object {
    public static final ASN1ObjectIdentifier SubjectDirectoryAttributes = new ASN1ObjectIdentifier("2.5.29.9");
    public static final ASN1ObjectIdentifier SubjectKeyIdentifier = new ASN1ObjectIdentifier("2.5.29.14");
    public static final ASN1ObjectIdentifier KeyUsage = new ASN1ObjectIdentifier("2.5.29.15");
    public static final ASN1ObjectIdentifier PrivateKeyUsagePeriod = new ASN1ObjectIdentifier("2.5.29.16");
    public static final ASN1ObjectIdentifier SubjectAlternativeName = new ASN1ObjectIdentifier("2.5.29.17");
    public static final ASN1ObjectIdentifier IssuerAlternativeName = new ASN1ObjectIdentifier("2.5.29.18");
    public static final ASN1ObjectIdentifier BasicConstraints = new ASN1ObjectIdentifier("2.5.29.19");
    public static final ASN1ObjectIdentifier CRLNumber = new ASN1ObjectIdentifier("2.5.29.20");
    public static final ASN1ObjectIdentifier ReasonCode = new ASN1ObjectIdentifier("2.5.29.21");
    public static final ASN1ObjectIdentifier InstructionCode = new ASN1ObjectIdentifier("2.5.29.23");
    public static final ASN1ObjectIdentifier InvalidityDate = new ASN1ObjectIdentifier("2.5.29.24");
    public static final ASN1ObjectIdentifier DeltaCRLIndicator = new ASN1ObjectIdentifier("2.5.29.27");
    public static final ASN1ObjectIdentifier IssuingDistributionPoint = new ASN1ObjectIdentifier("2.5.29.28");
    public static final ASN1ObjectIdentifier CertificateIssuer = new ASN1ObjectIdentifier("2.5.29.29");
    public static final ASN1ObjectIdentifier NameConstraints = new ASN1ObjectIdentifier("2.5.29.30");
    public static final ASN1ObjectIdentifier CRLDistributionPoints = new ASN1ObjectIdentifier("2.5.29.31");
    public static final ASN1ObjectIdentifier CertificatePolicies = new ASN1ObjectIdentifier("2.5.29.32");
    public static final ASN1ObjectIdentifier PolicyMappings = new ASN1ObjectIdentifier("2.5.29.33");
    public static final ASN1ObjectIdentifier AuthorityKeyIdentifier = new ASN1ObjectIdentifier("2.5.29.35");
    public static final ASN1ObjectIdentifier PolicyConstraints = new ASN1ObjectIdentifier("2.5.29.36");
    public static final ASN1ObjectIdentifier ExtendedKeyUsage = new ASN1ObjectIdentifier("2.5.29.37");
    public static final ASN1ObjectIdentifier FreshestCRL = new ASN1ObjectIdentifier("2.5.29.46");
    public static final ASN1ObjectIdentifier InhibitAnyPolicy = new ASN1ObjectIdentifier("2.5.29.54");
    public static final ASN1ObjectIdentifier AuthorityInfoAccess = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.1");
    public static final ASN1ObjectIdentifier SubjectInfoAccess = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.11");
    public static final ASN1ObjectIdentifier LogoType = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.12");
    public static final ASN1ObjectIdentifier BiometricInfo = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.2");
    public static final ASN1ObjectIdentifier QCStatements = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.3");
    public static final ASN1ObjectIdentifier AuditIdentity = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.4");
    public static final ASN1ObjectIdentifier NoRevAvail = new ASN1ObjectIdentifier("2.5.29.56");
    public static final ASN1ObjectIdentifier TargetInformation = new ASN1ObjectIdentifier("2.5.29.55");
    private Hashtable extensions = new Hashtable();
    private Vector ordering = new Vector();

    public static X509Extensions getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return X509Extensions.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static X509Extensions getInstance(Object object) {
        if (object == null || object instanceof X509Extensions) {
            return (X509Extensions)object;
        }
        if (object instanceof ASN1Sequence) {
            return new X509Extensions((ASN1Sequence)object);
        }
        if (object instanceof Extensions) {
            return new X509Extensions((ASN1Sequence)((Extensions)object).toASN1Primitive());
        }
        if (object instanceof ASN1TaggedObject) {
            return X509Extensions.getInstance(((ASN1TaggedObject)object).getObject());
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public X509Extensions(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(enumeration.nextElement());
            if (aSN1Sequence2.size() == 3) {
                this.extensions.put(aSN1Sequence2.getObjectAt(0), new X509Extension(ASN1Boolean.getInstance(aSN1Sequence2.getObjectAt(1)), ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(2))));
            } else if (aSN1Sequence2.size() == 2) {
                this.extensions.put(aSN1Sequence2.getObjectAt(0), new X509Extension(false, ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(1))));
            } else {
                throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence2.size());
            }
            this.ordering.addElement(aSN1Sequence2.getObjectAt(0));
        }
    }

    public X509Extensions(Hashtable hashtable) {
        this(null, hashtable);
    }

    public X509Extensions(Vector vector, Hashtable hashtable) {
        Enumeration<Object> enumeration = vector == null ? hashtable.keys() : vector.elements();
        while (enumeration.hasMoreElements()) {
            this.ordering.addElement(ASN1ObjectIdentifier.getInstance(enumeration.nextElement()));
        }
        enumeration = this.ordering.elements();
        while (enumeration.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(enumeration.nextElement());
            X509Extension x509Extension = (X509Extension)hashtable.get(aSN1ObjectIdentifier);
            this.extensions.put(aSN1ObjectIdentifier, x509Extension);
        }
    }

    public X509Extensions(Vector vector, Vector vector2) {
        Enumeration enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            this.ordering.addElement(enumeration.nextElement());
        }
        int n = 0;
        enumeration = this.ordering.elements();
        while (enumeration.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
            X509Extension x509Extension = (X509Extension)vector2.elementAt(n);
            this.extensions.put(aSN1ObjectIdentifier, x509Extension);
            ++n;
        }
    }

    public Enumeration oids() {
        return this.ordering.elements();
    }

    public X509Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (X509Extension)this.extensions.get(aSN1ObjectIdentifier);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Enumeration enumeration = this.ordering.elements();
        while (enumeration.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
            X509Extension x509Extension = (X509Extension)this.extensions.get(aSN1ObjectIdentifier);
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.add(aSN1ObjectIdentifier);
            if (x509Extension.isCritical()) {
                aSN1EncodableVector2.add(ASN1Boolean.TRUE);
            }
            aSN1EncodableVector2.add(x509Extension.getValue());
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public boolean equivalent(X509Extensions x509Extensions) {
        if (this.extensions.size() != x509Extensions.extensions.size()) {
            return false;
        }
        Enumeration enumeration = this.extensions.keys();
        while (enumeration.hasMoreElements()) {
            Object k = enumeration.nextElement();
            if (this.extensions.get(k).equals(x509Extensions.extensions.get(k))) continue;
            return false;
        }
        return true;
    }

    public ASN1ObjectIdentifier[] getExtensionOIDs() {
        return this.toOidArray(this.ordering);
    }

    public ASN1ObjectIdentifier[] getNonCriticalExtensionOIDs() {
        return this.getExtensionOIDs(false);
    }

    public ASN1ObjectIdentifier[] getCriticalExtensionOIDs() {
        return this.getExtensionOIDs(true);
    }

    private ASN1ObjectIdentifier[] getExtensionOIDs(boolean bl) {
        Vector vector = new Vector();
        for (int i = 0; i != this.ordering.size(); ++i) {
            Object e = this.ordering.elementAt(i);
            if (((X509Extension)this.extensions.get(e)).isCritical() != bl) continue;
            vector.addElement(e);
        }
        return this.toOidArray(vector);
    }

    private ASN1ObjectIdentifier[] toOidArray(Vector vector) {
        ASN1ObjectIdentifier[] aSN1ObjectIdentifierArray = new ASN1ObjectIdentifier[vector.size()];
        for (int i = 0; i != aSN1ObjectIdentifierArray.length; ++i) {
            aSN1ObjectIdentifierArray[i] = (ASN1ObjectIdentifier)vector.elementAt(i);
        }
        return aSN1ObjectIdentifierArray;
    }
}

