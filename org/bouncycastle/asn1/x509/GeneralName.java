/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.io.IOException;
import java.util.StringTokenizer;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.util.IPAddress;

public class GeneralName
extends ASN1Object
implements ASN1Choice {
    public static final int otherName = 0;
    public static final int rfc822Name = 1;
    public static final int dNSName = 2;
    public static final int x400Address = 3;
    public static final int directoryName = 4;
    public static final int ediPartyName = 5;
    public static final int uniformResourceIdentifier = 6;
    public static final int iPAddress = 7;
    public static final int registeredID = 8;
    private ASN1Encodable obj;
    private int tag;

    public GeneralName(X509Name x509Name) {
        this.obj = X500Name.getInstance(x509Name);
        this.tag = 4;
    }

    public GeneralName(X500Name x500Name) {
        this.obj = x500Name;
        this.tag = 4;
    }

    public GeneralName(int n, ASN1Encodable aSN1Encodable) {
        this.obj = aSN1Encodable;
        this.tag = n;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public GeneralName(int n, String string) {
        this.tag = n;
        if (n == 1 || n == 2 || n == 6) {
            this.obj = new DERIA5String(string);
            return;
        } else if (n == 8) {
            this.obj = new ASN1ObjectIdentifier(string);
            return;
        } else if (n == 4) {
            this.obj = new X500Name(string);
            return;
        } else {
            if (n != 7) throw new IllegalArgumentException("can't process String for tag: " + n);
            byte[] byArray = this.toGeneralNameEncoding(string);
            if (byArray == null) throw new IllegalArgumentException("IP Address is invalid");
            this.obj = new DEROctetString(byArray);
        }
    }

    public static GeneralName getInstance(Object object) {
        if (object == null || object instanceof GeneralName) {
            return (GeneralName)object;
        }
        if (object instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)object;
            int n = aSN1TaggedObject.getTagNo();
            switch (n) {
                case 0: {
                    return new GeneralName(n, ASN1Sequence.getInstance(aSN1TaggedObject, false));
                }
                case 1: {
                    return new GeneralName(n, DERIA5String.getInstance(aSN1TaggedObject, false));
                }
                case 2: {
                    return new GeneralName(n, DERIA5String.getInstance(aSN1TaggedObject, false));
                }
                case 3: {
                    throw new IllegalArgumentException("unknown tag: " + n);
                }
                case 4: {
                    return new GeneralName(n, X500Name.getInstance(aSN1TaggedObject, true));
                }
                case 5: {
                    return new GeneralName(n, ASN1Sequence.getInstance(aSN1TaggedObject, false));
                }
                case 6: {
                    return new GeneralName(n, DERIA5String.getInstance(aSN1TaggedObject, false));
                }
                case 7: {
                    return new GeneralName(n, ASN1OctetString.getInstance(aSN1TaggedObject, false));
                }
                case 8: {
                    return new GeneralName(n, ASN1ObjectIdentifier.getInstance(aSN1TaggedObject, false));
                }
            }
        }
        if (object instanceof byte[]) {
            try {
                return GeneralName.getInstance(ASN1Primitive.fromByteArray((byte[])object));
            } catch (IOException iOException) {
                throw new IllegalArgumentException("unable to parse encoded general name");
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + object.getClass().getName());
    }

    public static GeneralName getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return GeneralName.getInstance(ASN1TaggedObject.getInstance(aSN1TaggedObject, true));
    }

    public int getTagNo() {
        return this.tag;
    }

    public ASN1Encodable getName() {
        return this.obj;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.tag);
        stringBuffer.append(": ");
        switch (this.tag) {
            case 1: 
            case 2: 
            case 6: {
                stringBuffer.append(DERIA5String.getInstance(this.obj).getString());
                break;
            }
            case 4: {
                stringBuffer.append(X500Name.getInstance(this.obj).toString());
                break;
            }
            default: {
                stringBuffer.append(this.obj.toString());
            }
        }
        return stringBuffer.toString();
    }

    private byte[] toGeneralNameEncoding(String string) {
        if (IPAddress.isValidIPv6WithNetmask(string) || IPAddress.isValidIPv6(string)) {
            int n = string.indexOf(47);
            if (n < 0) {
                byte[] byArray = new byte[16];
                int[] nArray = this.parseIPv6(string);
                this.copyInts(nArray, byArray, 0);
                return byArray;
            }
            byte[] byArray = new byte[32];
            int[] nArray = this.parseIPv6(string.substring(0, n));
            this.copyInts(nArray, byArray, 0);
            String string2 = string.substring(n + 1);
            nArray = string2.indexOf(58) > 0 ? this.parseIPv6(string2) : this.parseMask(string2);
            this.copyInts(nArray, byArray, 16);
            return byArray;
        }
        if (IPAddress.isValidIPv4WithNetmask(string) || IPAddress.isValidIPv4(string)) {
            int n = string.indexOf(47);
            if (n < 0) {
                byte[] byArray = new byte[4];
                this.parseIPv4(string, byArray, 0);
                return byArray;
            }
            byte[] byArray = new byte[8];
            this.parseIPv4(string.substring(0, n), byArray, 0);
            String string3 = string.substring(n + 1);
            if (string3.indexOf(46) > 0) {
                this.parseIPv4(string3, byArray, 4);
            } else {
                this.parseIPv4Mask(string3, byArray, 4);
            }
            return byArray;
        }
        return null;
    }

    private void parseIPv4Mask(String string, byte[] byArray, int n) {
        int n2 = Integer.parseInt(string);
        for (int i = 0; i != n2; ++i) {
            int n3 = i / 8 + n;
            byArray[n3] = (byte)(byArray[n3] | 1 << 7 - i % 8);
        }
    }

    private void parseIPv4(String string, byte[] byArray, int n) {
        StringTokenizer stringTokenizer = new StringTokenizer(string, "./");
        int n2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            byArray[n + n2++] = (byte)Integer.parseInt(stringTokenizer.nextToken());
        }
    }

    private int[] parseMask(String string) {
        int[] nArray = new int[8];
        int n = Integer.parseInt(string);
        for (int i = 0; i != n; ++i) {
            int n2 = i / 16;
            nArray[n2] = nArray[n2] | 1 << 15 - i % 16;
        }
        return nArray;
    }

    private void copyInts(int[] nArray, byte[] byArray, int n) {
        for (int i = 0; i != nArray.length; ++i) {
            byArray[i * 2 + n] = (byte)(nArray[i] >> 8);
            byArray[i * 2 + 1 + n] = (byte)nArray[i];
        }
    }

    private int[] parseIPv6(String string) {
        StringTokenizer stringTokenizer = new StringTokenizer(string, ":", true);
        int n = 0;
        int[] nArray = new int[8];
        if (string.charAt(0) == ':' && string.charAt(1) == ':') {
            stringTokenizer.nextToken();
        }
        int n2 = -1;
        while (stringTokenizer.hasMoreTokens()) {
            String string2 = stringTokenizer.nextToken();
            if (string2.equals(":")) {
                n2 = n;
                nArray[n++] = 0;
                continue;
            }
            if (string2.indexOf(46) < 0) {
                nArray[n++] = Integer.parseInt(string2, 16);
                if (!stringTokenizer.hasMoreTokens()) continue;
                stringTokenizer.nextToken();
                continue;
            }
            StringTokenizer stringTokenizer2 = new StringTokenizer(string2, ".");
            nArray[n++] = Integer.parseInt(stringTokenizer2.nextToken()) << 8 | Integer.parseInt(stringTokenizer2.nextToken());
            nArray[n++] = Integer.parseInt(stringTokenizer2.nextToken()) << 8 | Integer.parseInt(stringTokenizer2.nextToken());
        }
        if (n != nArray.length) {
            System.arraycopy(nArray, n2, nArray, nArray.length - (n - n2), n - n2);
            for (int i = n2; i != nArray.length - (n - n2); ++i) {
                nArray[i] = 0;
            }
        }
        return nArray;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.tag == 4) {
            return new DERTaggedObject(true, this.tag, this.obj);
        }
        return new DERTaggedObject(false, this.tag, this.obj);
    }
}

