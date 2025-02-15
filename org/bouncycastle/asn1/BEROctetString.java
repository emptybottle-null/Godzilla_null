/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;

public class BEROctetString
extends ASN1OctetString {
    private static final int MAX_LENGTH = 1000;
    private ASN1OctetString[] octs;

    private static byte[] toBytes(ASN1OctetString[] aSN1OctetStringArray) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != aSN1OctetStringArray.length; ++i) {
            try {
                DEROctetString dEROctetString = (DEROctetString)aSN1OctetStringArray[i];
                byteArrayOutputStream.write(dEROctetString.getOctets());
                continue;
            } catch (ClassCastException classCastException) {
                throw new IllegalArgumentException(aSN1OctetStringArray[i].getClass().getName() + " found in input should only contain DEROctetString");
            } catch (IOException iOException) {
                throw new IllegalArgumentException("exception converting octets " + iOException.toString());
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public BEROctetString(byte[] byArray) {
        super(byArray);
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArray) {
        super(BEROctetString.toBytes(aSN1OctetStringArray));
        this.octs = aSN1OctetStringArray;
    }

    public byte[] getOctets() {
        return this.string;
    }

    public Enumeration getObjects() {
        if (this.octs == null) {
            return this.generateOcts().elements();
        }
        return new Enumeration(){
            int counter = 0;

            public boolean hasMoreElements() {
                return this.counter < BEROctetString.this.octs.length;
            }

            public Object nextElement() {
                return BEROctetString.this.octs[this.counter++];
            }
        };
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

    boolean isConstructed() {
        return true;
    }

    int encodedLength() throws IOException {
        int n = 0;
        Enumeration enumeration = this.getObjects();
        while (enumeration.hasMoreElements()) {
            n += ((ASN1Encodable)enumeration.nextElement()).toASN1Primitive().encodedLength();
        }
        return 2 + n + 2;
    }

    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.write(36);
        aSN1OutputStream.write(128);
        Enumeration enumeration = this.getObjects();
        while (enumeration.hasMoreElements()) {
            aSN1OutputStream.writeObject((ASN1Encodable)enumeration.nextElement());
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    static BEROctetString fromSequence(ASN1Sequence aSN1Sequence) {
        ASN1OctetString[] aSN1OctetStringArray = new ASN1OctetString[aSN1Sequence.size()];
        Enumeration enumeration = aSN1Sequence.getObjects();
        int n = 0;
        while (enumeration.hasMoreElements()) {
            aSN1OctetStringArray[n++] = (ASN1OctetString)enumeration.nextElement();
        }
        return new BEROctetString(aSN1OctetStringArray);
    }
}

