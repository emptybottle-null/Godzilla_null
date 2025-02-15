/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.DEROctetString;

public class BERConstructedOctetString
extends BEROctetString {
    private static final int MAX_LENGTH = 1000;
    private Vector octs;

    private static byte[] toBytes(Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != vector.size(); ++i) {
            try {
                DEROctetString dEROctetString = (DEROctetString)vector.elementAt(i);
                byteArrayOutputStream.write(dEROctetString.getOctets());
                continue;
            } catch (ClassCastException classCastException) {
                throw new IllegalArgumentException(vector.elementAt(i).getClass().getName() + " found in input should only contain DEROctetString");
            } catch (IOException iOException) {
                throw new IllegalArgumentException("exception converting octets " + iOException.toString());
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public BERConstructedOctetString(byte[] byArray) {
        super(byArray);
    }

    public BERConstructedOctetString(Vector vector) {
        super(BERConstructedOctetString.toBytes(vector));
        this.octs = vector;
    }

    public BERConstructedOctetString(ASN1Primitive aSN1Primitive) {
        super(BERConstructedOctetString.toByteArray(aSN1Primitive));
    }

    private static byte[] toByteArray(ASN1Primitive aSN1Primitive) {
        try {
            return aSN1Primitive.getEncoded();
        } catch (IOException iOException) {
            throw new IllegalArgumentException("Unable to encode object");
        }
    }

    public BERConstructedOctetString(ASN1Encodable aSN1Encodable) {
        this(aSN1Encodable.toASN1Primitive());
    }

    public byte[] getOctets() {
        return this.string;
    }

    public Enumeration getObjects() {
        if (this.octs == null) {
            return this.generateOcts().elements();
        }
        return this.octs.elements();
    }

    private Vector generateOcts() {
        Vector<DEROctetString> vector = new Vector<DEROctetString>();
        for (int i = 0; i < this.string.length; i += 1000) {
            int n = i + 1000 > this.string.length ? this.string.length : i + 1000;
            byte[] byArray = new byte[n - i];
            System.arraycopy(this.string, i, byArray, 0, byArray.length);
            vector.addElement(new DEROctetString(byArray));
        }
        return vector;
    }

    public static BEROctetString fromSequence(ASN1Sequence aSN1Sequence) {
        Vector vector = new Vector();
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            vector.addElement(enumeration.nextElement());
        }
        return new BERConstructedOctetString(vector);
    }
}

