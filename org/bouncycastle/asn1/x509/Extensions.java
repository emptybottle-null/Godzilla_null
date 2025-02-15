/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.Extension;

public class Extensions
extends ASN1Object {
    private Hashtable extensions = new Hashtable();
    private Vector ordering = new Vector();

    public static Extensions getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return Extensions.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static Extensions getInstance(Object object) {
        if (object instanceof Extensions) {
            return (Extensions)object;
        }
        if (object != null) {
            return new Extensions(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private Extensions(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            Extension extension = Extension.getInstance(enumeration.nextElement());
            if (this.extensions.containsKey(extension.getExtnId())) {
                throw new IllegalArgumentException("repeated extension found: " + extension.getExtnId());
            }
            this.extensions.put(extension.getExtnId(), extension);
            this.ordering.addElement(extension.getExtnId());
        }
    }

    public Extensions(Extension extension) {
        this.ordering.addElement(extension.getExtnId());
        this.extensions.put(extension.getExtnId(), extension);
    }

    public Extensions(Extension[] extensionArray) {
        for (int i = 0; i != extensionArray.length; ++i) {
            Extension extension = extensionArray[i];
            this.ordering.addElement(extension.getExtnId());
            this.extensions.put(extension.getExtnId(), extension);
        }
    }

    public Enumeration oids() {
        return this.ordering.elements();
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (Extension)this.extensions.get(aSN1ObjectIdentifier);
    }

    public ASN1Encodable getExtensionParsedValue(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extension extension = this.getExtension(aSN1ObjectIdentifier);
        if (extension != null) {
            return extension.getParsedValue();
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Enumeration enumeration = this.ordering.elements();
        while (enumeration.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
            Extension extension = (Extension)this.extensions.get(aSN1ObjectIdentifier);
            aSN1EncodableVector.add(extension);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public boolean equivalent(Extensions extensions) {
        if (this.extensions.size() != extensions.extensions.size()) {
            return false;
        }
        Enumeration enumeration = this.extensions.keys();
        while (enumeration.hasMoreElements()) {
            Object k = enumeration.nextElement();
            if (this.extensions.get(k).equals(extensions.extensions.get(k))) continue;
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
            if (((Extension)this.extensions.get(e)).isCritical() != bl) continue;
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

