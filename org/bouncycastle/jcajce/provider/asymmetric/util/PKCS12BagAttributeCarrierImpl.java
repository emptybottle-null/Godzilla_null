/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;

public class PKCS12BagAttributeCarrierImpl
implements PKCS12BagAttributeCarrier {
    private Hashtable pkcs12Attributes;
    private Vector pkcs12Ordering;

    PKCS12BagAttributeCarrierImpl(Hashtable hashtable, Vector vector) {
        this.pkcs12Attributes = hashtable;
        this.pkcs12Ordering = vector;
    }

    public PKCS12BagAttributeCarrierImpl() {
        this(new Hashtable(), new Vector());
    }

    public void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        if (this.pkcs12Attributes.containsKey(aSN1ObjectIdentifier)) {
            this.pkcs12Attributes.put(aSN1ObjectIdentifier, aSN1Encodable);
        } else {
            this.pkcs12Attributes.put(aSN1ObjectIdentifier, aSN1Encodable);
            this.pkcs12Ordering.addElement(aSN1ObjectIdentifier);
        }
    }

    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (ASN1Encodable)this.pkcs12Attributes.get(aSN1ObjectIdentifier);
    }

    public Enumeration getBagAttributeKeys() {
        return this.pkcs12Ordering.elements();
    }

    int size() {
        return this.pkcs12Ordering.size();
    }

    Hashtable getAttributes() {
        return this.pkcs12Attributes;
    }

    Vector getOrdering() {
        return this.pkcs12Ordering;
    }

    public void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        if (this.pkcs12Ordering.size() == 0) {
            objectOutputStream.writeObject(new Hashtable());
            objectOutputStream.writeObject(new Vector());
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
            Enumeration enumeration = this.getBagAttributeKeys();
            while (enumeration.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
                aSN1OutputStream.writeObject(aSN1ObjectIdentifier);
                aSN1OutputStream.writeObject((ASN1Encodable)this.pkcs12Attributes.get(aSN1ObjectIdentifier));
            }
            objectOutputStream.writeObject(byteArrayOutputStream.toByteArray());
        }
    }

    public void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Object object = objectInputStream.readObject();
        if (object instanceof Hashtable) {
            this.pkcs12Attributes = (Hashtable)object;
            this.pkcs12Ordering = (Vector)objectInputStream.readObject();
        } else {
            ASN1ObjectIdentifier aSN1ObjectIdentifier;
            ASN1InputStream aSN1InputStream = new ASN1InputStream((byte[])object);
            while ((aSN1ObjectIdentifier = (ASN1ObjectIdentifier)aSN1InputStream.readObject()) != null) {
                this.setBagAttribute(aSN1ObjectIdentifier, aSN1InputStream.readObject());
            }
        }
    }
}

