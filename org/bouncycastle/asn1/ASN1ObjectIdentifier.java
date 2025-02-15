/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.OIDTokenizer;
import org.bouncycastle.asn1.StreamUtil;
import org.bouncycastle.util.Arrays;

public class ASN1ObjectIdentifier
extends ASN1Primitive {
    private final String identifier;
    private byte[] body;
    private static final long LONG_LIMIT = 0xFFFFFFFFFFFF80L;
    private static final ConcurrentMap<OidHandle, ASN1ObjectIdentifier> pool = new ConcurrentHashMap<OidHandle, ASN1ObjectIdentifier>();

    public static ASN1ObjectIdentifier getInstance(Object object) {
        if (object == null || object instanceof ASN1ObjectIdentifier) {
            return (ASN1ObjectIdentifier)object;
        }
        if (object instanceof ASN1Encodable && ((ASN1Encodable)object).toASN1Primitive() instanceof ASN1ObjectIdentifier) {
            return (ASN1ObjectIdentifier)((ASN1Encodable)object).toASN1Primitive();
        }
        if (object instanceof byte[]) {
            byte[] byArray = (byte[])object;
            try {
                return (ASN1ObjectIdentifier)ASN1ObjectIdentifier.fromByteArray(byArray);
            } catch (IOException iOException) {
                throw new IllegalArgumentException("failed to construct object identifier from byte[]: " + iOException.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static ASN1ObjectIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof ASN1ObjectIdentifier) {
            return ASN1ObjectIdentifier.getInstance(aSN1Primitive);
        }
        return ASN1ObjectIdentifier.fromOctetString(ASN1OctetString.getInstance(aSN1TaggedObject.getObject()).getOctets());
    }

    ASN1ObjectIdentifier(byte[] byArray) {
        StringBuffer stringBuffer = new StringBuffer();
        long l = 0L;
        BigInteger bigInteger = null;
        boolean bl = true;
        for (int i = 0; i != byArray.length; ++i) {
            int n = byArray[i] & 0xFF;
            if (l <= 0xFFFFFFFFFFFF80L) {
                l += (long)(n & 0x7F);
                if ((n & 0x80) == 0) {
                    if (bl) {
                        if (l < 40L) {
                            stringBuffer.append('0');
                        } else if (l < 80L) {
                            stringBuffer.append('1');
                            l -= 40L;
                        } else {
                            stringBuffer.append('2');
                            l -= 80L;
                        }
                        bl = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(l);
                    l = 0L;
                    continue;
                }
                l <<= 7;
                continue;
            }
            if (bigInteger == null) {
                bigInteger = BigInteger.valueOf(l);
            }
            bigInteger = bigInteger.or(BigInteger.valueOf(n & 0x7F));
            if ((n & 0x80) == 0) {
                if (bl) {
                    stringBuffer.append('2');
                    bigInteger = bigInteger.subtract(BigInteger.valueOf(80L));
                    bl = false;
                }
                stringBuffer.append('.');
                stringBuffer.append(bigInteger);
                bigInteger = null;
                l = 0L;
                continue;
            }
            bigInteger = bigInteger.shiftLeft(7);
        }
        this.identifier = stringBuffer.toString();
        this.body = Arrays.clone(byArray);
    }

    public ASN1ObjectIdentifier(String string) {
        if (string == null) {
            throw new IllegalArgumentException("'identifier' cannot be null");
        }
        if (!ASN1ObjectIdentifier.isValidIdentifier(string)) {
            throw new IllegalArgumentException("string " + string + " not an OID");
        }
        this.identifier = string;
    }

    ASN1ObjectIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        if (!ASN1ObjectIdentifier.isValidBranchID(string, 0)) {
            throw new IllegalArgumentException("string " + string + " not a valid OID branch");
        }
        this.identifier = aSN1ObjectIdentifier.getId() + "." + string;
    }

    public String getId() {
        return this.identifier;
    }

    public ASN1ObjectIdentifier branch(String string) {
        return new ASN1ObjectIdentifier(this, string);
    }

    public boolean on(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String string = this.getId();
        String string2 = aSN1ObjectIdentifier.getId();
        return string.length() > string2.length() && string.charAt(string2.length()) == '.' && string.startsWith(string2);
    }

    private void writeField(ByteArrayOutputStream byteArrayOutputStream, long l) {
        byte[] byArray = new byte[9];
        int n = 8;
        byArray[n] = (byte)((int)l & 0x7F);
        while (l >= 128L) {
            byArray[--n] = (byte)((int)(l >>= 7) & 0x7F | 0x80);
        }
        byteArrayOutputStream.write(byArray, n, 9 - n);
    }

    private void writeField(ByteArrayOutputStream byteArrayOutputStream, BigInteger bigInteger) {
        int n = (bigInteger.bitLength() + 6) / 7;
        if (n == 0) {
            byteArrayOutputStream.write(0);
        } else {
            BigInteger bigInteger2 = bigInteger;
            byte[] byArray = new byte[n];
            for (int i = n - 1; i >= 0; --i) {
                byArray[i] = (byte)(bigInteger2.intValue() & 0x7F | 0x80);
                bigInteger2 = bigInteger2.shiftRight(7);
            }
            int n2 = n - 1;
            byArray[n2] = (byte)(byArray[n2] & 0x7F);
            byteArrayOutputStream.write(byArray, 0, byArray.length);
        }
    }

    private void doOutput(ByteArrayOutputStream byteArrayOutputStream) {
        OIDTokenizer oIDTokenizer = new OIDTokenizer(this.identifier);
        int n = Integer.parseInt(oIDTokenizer.nextToken()) * 40;
        String string = oIDTokenizer.nextToken();
        if (string.length() <= 18) {
            this.writeField(byteArrayOutputStream, (long)n + Long.parseLong(string));
        } else {
            this.writeField(byteArrayOutputStream, new BigInteger(string).add(BigInteger.valueOf(n)));
        }
        while (oIDTokenizer.hasMoreTokens()) {
            String string2 = oIDTokenizer.nextToken();
            if (string2.length() <= 18) {
                this.writeField(byteArrayOutputStream, Long.parseLong(string2));
                continue;
            }
            this.writeField(byteArrayOutputStream, new BigInteger(string2));
        }
    }

    private synchronized byte[] getBody() {
        if (this.body == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.doOutput(byteArrayOutputStream);
            this.body = byteArrayOutputStream.toByteArray();
        }
        return this.body;
    }

    boolean isConstructed() {
        return false;
    }

    int encodedLength() throws IOException {
        int n = this.getBody().length;
        return 1 + StreamUtil.calculateBodyLength(n) + n;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] byArray = this.getBody();
        aSN1OutputStream.write(6);
        aSN1OutputStream.writeLength(byArray.length);
        aSN1OutputStream.write(byArray);
    }

    public int hashCode() {
        return this.identifier.hashCode();
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive == this) {
            return true;
        }
        if (!(aSN1Primitive instanceof ASN1ObjectIdentifier)) {
            return false;
        }
        return this.identifier.equals(((ASN1ObjectIdentifier)aSN1Primitive).identifier);
    }

    public String toString() {
        return this.getId();
    }

    private static boolean isValidBranchID(String string, int n) {
        boolean bl = false;
        int n2 = string.length();
        while (--n2 >= n) {
            char c = string.charAt(n2);
            if ('0' <= c && c <= '9') {
                bl = true;
                continue;
            }
            if (c == '.') {
                if (!bl) {
                    return false;
                }
                bl = false;
                continue;
            }
            return false;
        }
        return bl;
    }

    private static boolean isValidIdentifier(String string) {
        if (string.length() < 3 || string.charAt(1) != '.') {
            return false;
        }
        char c = string.charAt(0);
        if (c < '0' || c > '2') {
            return false;
        }
        return ASN1ObjectIdentifier.isValidBranchID(string, 2);
    }

    public ASN1ObjectIdentifier intern() {
        OidHandle oidHandle = new OidHandle(this.getBody());
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)pool.get(oidHandle);
        if (aSN1ObjectIdentifier == null && (aSN1ObjectIdentifier = pool.putIfAbsent(oidHandle, this)) == null) {
            aSN1ObjectIdentifier = this;
        }
        return aSN1ObjectIdentifier;
    }

    static ASN1ObjectIdentifier fromOctetString(byte[] byArray) {
        OidHandle oidHandle = new OidHandle(byArray);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)pool.get(oidHandle);
        if (aSN1ObjectIdentifier == null) {
            return new ASN1ObjectIdentifier(byArray);
        }
        return aSN1ObjectIdentifier;
    }

    private static class OidHandle {
        private final int key;
        private final byte[] enc;

        OidHandle(byte[] byArray) {
            this.key = Arrays.hashCode(byArray);
            this.enc = byArray;
        }

        public int hashCode() {
            return this.key;
        }

        public boolean equals(Object object) {
            if (object instanceof OidHandle) {
                return Arrays.areEqual(this.enc, ((OidHandle)object).enc);
            }
            return false;
        }
    }
}

