/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CombinedHash;
import org.bouncycastle.crypto.tls.HashAlgorithm;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.SecurityParameters;
import org.bouncycastle.crypto.tls.SessionParameters;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsDSSSigner;
import org.bouncycastle.crypto.tls.TlsECDSASigner;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsRSASigner;
import org.bouncycastle.crypto.tls.TlsSession;
import org.bouncycastle.crypto.tls.TlsSessionImpl;
import org.bouncycastle.crypto.tls.TlsSigner;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Shorts;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.Streams;

public class TlsUtils {
    public static final byte[] EMPTY_BYTES = new byte[0];
    public static final short[] EMPTY_SHORTS = new short[0];
    public static final int[] EMPTY_INTS = new int[0];
    public static final long[] EMPTY_LONGS = new long[0];
    public static final Integer EXT_signature_algorithms = Integers.valueOf(13);
    static final byte[] SSL_CLIENT = new byte[]{67, 76, 78, 84};
    static final byte[] SSL_SERVER = new byte[]{83, 82, 86, 82};
    static final byte[][] SSL3_CONST = TlsUtils.genSSL3Const();

    public static void checkUint8(short s) throws IOException {
        if (!TlsUtils.isValidUint8(s)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint8(int n) throws IOException {
        if (!TlsUtils.isValidUint8(n)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint8(long l) throws IOException {
        if (!TlsUtils.isValidUint8(l)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint16(int n) throws IOException {
        if (!TlsUtils.isValidUint16(n)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint16(long l) throws IOException {
        if (!TlsUtils.isValidUint16(l)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint24(int n) throws IOException {
        if (!TlsUtils.isValidUint24(n)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint24(long l) throws IOException {
        if (!TlsUtils.isValidUint24(l)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint32(long l) throws IOException {
        if (!TlsUtils.isValidUint32(l)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint48(long l) throws IOException {
        if (!TlsUtils.isValidUint48(l)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint64(long l) throws IOException {
        if (!TlsUtils.isValidUint64(l)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static boolean isValidUint8(short s) {
        return (s & 0xFF) == s;
    }

    public static boolean isValidUint8(int n) {
        return (n & 0xFF) == n;
    }

    public static boolean isValidUint8(long l) {
        return (l & 0xFFL) == l;
    }

    public static boolean isValidUint16(int n) {
        return (n & 0xFFFF) == n;
    }

    public static boolean isValidUint16(long l) {
        return (l & 0xFFFFL) == l;
    }

    public static boolean isValidUint24(int n) {
        return (n & 0xFFFFFF) == n;
    }

    public static boolean isValidUint24(long l) {
        return (l & 0xFFFFFFL) == l;
    }

    public static boolean isValidUint32(long l) {
        return (l & 0xFFFFFFFFL) == l;
    }

    public static boolean isValidUint48(long l) {
        return (l & 0xFFFFFFFFFFFFL) == l;
    }

    public static boolean isValidUint64(long l) {
        return true;
    }

    public static boolean isSSL(TlsContext tlsContext) {
        return tlsContext.getServerVersion().isSSL();
    }

    public static boolean isTLSv11(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv11.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv11(TlsContext tlsContext) {
        return TlsUtils.isTLSv11(tlsContext.getServerVersion());
    }

    public static boolean isTLSv12(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv12(TlsContext tlsContext) {
        return TlsUtils.isTLSv12(tlsContext.getServerVersion());
    }

    public static void writeUint8(short s, OutputStream outputStream) throws IOException {
        outputStream.write(s);
    }

    public static void writeUint8(int n, OutputStream outputStream) throws IOException {
        outputStream.write(n);
    }

    public static void writeUint8(short s, byte[] byArray, int n) {
        byArray[n] = (byte)s;
    }

    public static void writeUint8(int n, byte[] byArray, int n2) {
        byArray[n2] = (byte)n;
    }

    public static void writeUint16(int n, OutputStream outputStream) throws IOException {
        outputStream.write(n >>> 8);
        outputStream.write(n);
    }

    public static void writeUint16(int n, byte[] byArray, int n2) {
        byArray[n2] = (byte)(n >>> 8);
        byArray[n2 + 1] = (byte)n;
    }

    public static void writeUint24(int n, OutputStream outputStream) throws IOException {
        outputStream.write((byte)(n >>> 16));
        outputStream.write((byte)(n >>> 8));
        outputStream.write((byte)n);
    }

    public static void writeUint24(int n, byte[] byArray, int n2) {
        byArray[n2] = (byte)(n >>> 16);
        byArray[n2 + 1] = (byte)(n >>> 8);
        byArray[n2 + 2] = (byte)n;
    }

    public static void writeUint32(long l, OutputStream outputStream) throws IOException {
        outputStream.write((byte)(l >>> 24));
        outputStream.write((byte)(l >>> 16));
        outputStream.write((byte)(l >>> 8));
        outputStream.write((byte)l);
    }

    public static void writeUint32(long l, byte[] byArray, int n) {
        byArray[n] = (byte)(l >>> 24);
        byArray[n + 1] = (byte)(l >>> 16);
        byArray[n + 2] = (byte)(l >>> 8);
        byArray[n + 3] = (byte)l;
    }

    public static void writeUint48(long l, OutputStream outputStream) throws IOException {
        outputStream.write((byte)(l >>> 40));
        outputStream.write((byte)(l >>> 32));
        outputStream.write((byte)(l >>> 24));
        outputStream.write((byte)(l >>> 16));
        outputStream.write((byte)(l >>> 8));
        outputStream.write((byte)l);
    }

    public static void writeUint48(long l, byte[] byArray, int n) {
        byArray[n] = (byte)(l >>> 40);
        byArray[n + 1] = (byte)(l >>> 32);
        byArray[n + 2] = (byte)(l >>> 24);
        byArray[n + 3] = (byte)(l >>> 16);
        byArray[n + 4] = (byte)(l >>> 8);
        byArray[n + 5] = (byte)l;
    }

    public static void writeUint64(long l, OutputStream outputStream) throws IOException {
        outputStream.write((byte)(l >>> 56));
        outputStream.write((byte)(l >>> 48));
        outputStream.write((byte)(l >>> 40));
        outputStream.write((byte)(l >>> 32));
        outputStream.write((byte)(l >>> 24));
        outputStream.write((byte)(l >>> 16));
        outputStream.write((byte)(l >>> 8));
        outputStream.write((byte)l);
    }

    public static void writeUint64(long l, byte[] byArray, int n) {
        byArray[n] = (byte)(l >>> 56);
        byArray[n + 1] = (byte)(l >>> 48);
        byArray[n + 2] = (byte)(l >>> 40);
        byArray[n + 3] = (byte)(l >>> 32);
        byArray[n + 4] = (byte)(l >>> 24);
        byArray[n + 5] = (byte)(l >>> 16);
        byArray[n + 6] = (byte)(l >>> 8);
        byArray[n + 7] = (byte)l;
    }

    public static void writeOpaque8(byte[] byArray, OutputStream outputStream) throws IOException {
        TlsUtils.checkUint8(byArray.length);
        TlsUtils.writeUint8(byArray.length, outputStream);
        outputStream.write(byArray);
    }

    public static void writeOpaque16(byte[] byArray, OutputStream outputStream) throws IOException {
        TlsUtils.checkUint16(byArray.length);
        TlsUtils.writeUint16(byArray.length, outputStream);
        outputStream.write(byArray);
    }

    public static void writeOpaque24(byte[] byArray, OutputStream outputStream) throws IOException {
        TlsUtils.checkUint24(byArray.length);
        TlsUtils.writeUint24(byArray.length, outputStream);
        outputStream.write(byArray);
    }

    public static void writeUint8Array(short[] sArray, OutputStream outputStream) throws IOException {
        for (int i = 0; i < sArray.length; ++i) {
            TlsUtils.writeUint8(sArray[i], outputStream);
        }
    }

    public static void writeUint8Array(short[] sArray, byte[] byArray, int n) throws IOException {
        for (int i = 0; i < sArray.length; ++i) {
            TlsUtils.writeUint8(sArray[i], byArray, n);
            ++n;
        }
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArray, OutputStream outputStream) throws IOException {
        TlsUtils.checkUint8(sArray.length);
        TlsUtils.writeUint8(sArray.length, outputStream);
        TlsUtils.writeUint8Array(sArray, outputStream);
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArray, byte[] byArray, int n) throws IOException {
        TlsUtils.checkUint8(sArray.length);
        TlsUtils.writeUint8(sArray.length, byArray, n);
        TlsUtils.writeUint8Array(sArray, byArray, n + 1);
    }

    public static void writeUint16Array(int[] nArray, OutputStream outputStream) throws IOException {
        for (int i = 0; i < nArray.length; ++i) {
            TlsUtils.writeUint16(nArray[i], outputStream);
        }
    }

    public static void writeUint16Array(int[] nArray, byte[] byArray, int n) throws IOException {
        for (int i = 0; i < nArray.length; ++i) {
            TlsUtils.writeUint16(nArray[i], byArray, n);
            n += 2;
        }
    }

    public static void writeUint16ArrayWithUint16Length(int[] nArray, OutputStream outputStream) throws IOException {
        int n = 2 * nArray.length;
        TlsUtils.checkUint16(n);
        TlsUtils.writeUint16(n, outputStream);
        TlsUtils.writeUint16Array(nArray, outputStream);
    }

    public static void writeUint16ArrayWithUint16Length(int[] nArray, byte[] byArray, int n) throws IOException {
        int n2 = 2 * nArray.length;
        TlsUtils.checkUint16(n2);
        TlsUtils.writeUint16(n2, byArray, n);
        TlsUtils.writeUint16Array(nArray, byArray, n + 2);
    }

    public static byte[] encodeOpaque8(byte[] byArray) throws IOException {
        TlsUtils.checkUint8(byArray.length);
        return Arrays.prepend(byArray, (byte)byArray.length);
    }

    public static byte[] encodeUint8ArrayWithUint8Length(short[] sArray) throws IOException {
        byte[] byArray = new byte[1 + sArray.length];
        TlsUtils.writeUint8ArrayWithUint8Length(sArray, byArray, 0);
        return byArray;
    }

    public static byte[] encodeUint16ArrayWithUint16Length(int[] nArray) throws IOException {
        int n = 2 * nArray.length;
        byte[] byArray = new byte[2 + n];
        TlsUtils.writeUint16ArrayWithUint16Length(nArray, byArray, 0);
        return byArray;
    }

    public static short readUint8(InputStream inputStream) throws IOException {
        int n = inputStream.read();
        if (n < 0) {
            throw new EOFException();
        }
        return (short)n;
    }

    public static short readUint8(byte[] byArray, int n) {
        return (short)(byArray[n] & 0xFF);
    }

    public static int readUint16(InputStream inputStream) throws IOException {
        int n = inputStream.read();
        int n2 = inputStream.read();
        if (n2 < 0) {
            throw new EOFException();
        }
        return n << 8 | n2;
    }

    public static int readUint16(byte[] byArray, int n) {
        int n2 = (byArray[n] & 0xFF) << 8;
        return n2 |= byArray[++n] & 0xFF;
    }

    public static int readUint24(InputStream inputStream) throws IOException {
        int n = inputStream.read();
        int n2 = inputStream.read();
        int n3 = inputStream.read();
        if (n3 < 0) {
            throw new EOFException();
        }
        return n << 16 | n2 << 8 | n3;
    }

    public static int readUint24(byte[] byArray, int n) {
        int n2 = (byArray[n] & 0xFF) << 16;
        n2 |= (byArray[++n] & 0xFF) << 8;
        return n2 |= byArray[++n] & 0xFF;
    }

    public static long readUint32(InputStream inputStream) throws IOException {
        int n = inputStream.read();
        int n2 = inputStream.read();
        int n3 = inputStream.read();
        int n4 = inputStream.read();
        if (n4 < 0) {
            throw new EOFException();
        }
        return (long)(n << 24 | n2 << 16 | n3 << 8 | n4) & 0xFFFFFFFFL;
    }

    public static long readUint32(byte[] byArray, int n) {
        int n2 = (byArray[n] & 0xFF) << 24;
        n2 |= (byArray[++n] & 0xFF) << 16;
        n2 |= (byArray[++n] & 0xFF) << 8;
        return (long)(n2 |= byArray[++n] & 0xFF) & 0xFFFFFFFFL;
    }

    public static long readUint48(InputStream inputStream) throws IOException {
        int n = TlsUtils.readUint24(inputStream);
        int n2 = TlsUtils.readUint24(inputStream);
        return ((long)n & 0xFFFFFFFFL) << 24 | (long)n2 & 0xFFFFFFFFL;
    }

    public static long readUint48(byte[] byArray, int n) {
        int n2 = TlsUtils.readUint24(byArray, n);
        int n3 = TlsUtils.readUint24(byArray, n + 3);
        return ((long)n2 & 0xFFFFFFFFL) << 24 | (long)n3 & 0xFFFFFFFFL;
    }

    public static byte[] readAllOrNothing(int n, InputStream inputStream) throws IOException {
        if (n < 1) {
            return EMPTY_BYTES;
        }
        byte[] byArray = new byte[n];
        int n2 = Streams.readFully(inputStream, byArray);
        if (n2 == 0) {
            return null;
        }
        if (n2 != n) {
            throw new EOFException();
        }
        return byArray;
    }

    public static byte[] readFully(int n, InputStream inputStream) throws IOException {
        if (n < 1) {
            return EMPTY_BYTES;
        }
        byte[] byArray = new byte[n];
        if (n != Streams.readFully(inputStream, byArray)) {
            throw new EOFException();
        }
        return byArray;
    }

    public static void readFully(byte[] byArray, InputStream inputStream) throws IOException {
        int n = byArray.length;
        if (n > 0 && n != Streams.readFully(inputStream, byArray)) {
            throw new EOFException();
        }
    }

    public static byte[] readOpaque8(InputStream inputStream) throws IOException {
        short s = TlsUtils.readUint8(inputStream);
        return TlsUtils.readFully(s, inputStream);
    }

    public static byte[] readOpaque16(InputStream inputStream) throws IOException {
        int n = TlsUtils.readUint16(inputStream);
        return TlsUtils.readFully(n, inputStream);
    }

    public static byte[] readOpaque24(InputStream inputStream) throws IOException {
        int n = TlsUtils.readUint24(inputStream);
        return TlsUtils.readFully(n, inputStream);
    }

    public static short[] readUint8Array(int n, InputStream inputStream) throws IOException {
        short[] sArray = new short[n];
        for (int i = 0; i < n; ++i) {
            sArray[i] = TlsUtils.readUint8(inputStream);
        }
        return sArray;
    }

    public static int[] readUint16Array(int n, InputStream inputStream) throws IOException {
        int[] nArray = new int[n];
        for (int i = 0; i < n; ++i) {
            nArray[i] = TlsUtils.readUint16(inputStream);
        }
        return nArray;
    }

    public static ProtocolVersion readVersion(byte[] byArray, int n) throws IOException {
        return ProtocolVersion.get(byArray[n] & 0xFF, byArray[n + 1] & 0xFF);
    }

    public static ProtocolVersion readVersion(InputStream inputStream) throws IOException {
        int n = inputStream.read();
        int n2 = inputStream.read();
        if (n2 < 0) {
            throw new EOFException();
        }
        return ProtocolVersion.get(n, n2);
    }

    public static int readVersionRaw(byte[] byArray, int n) throws IOException {
        return byArray[n] << 8 | byArray[n + 1];
    }

    public static int readVersionRaw(InputStream inputStream) throws IOException {
        int n = inputStream.read();
        int n2 = inputStream.read();
        if (n2 < 0) {
            throw new EOFException();
        }
        return n << 8 | n2;
    }

    public static ASN1Primitive readASN1Object(byte[] byArray) throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(byArray);
        ASN1Primitive aSN1Primitive = aSN1InputStream.readObject();
        if (null == aSN1Primitive) {
            throw new TlsFatalAlert(50);
        }
        if (null != aSN1InputStream.readObject()) {
            throw new TlsFatalAlert(50);
        }
        return aSN1Primitive;
    }

    public static ASN1Primitive readDERObject(byte[] byArray) throws IOException {
        ASN1Primitive aSN1Primitive = TlsUtils.readASN1Object(byArray);
        byte[] byArray2 = aSN1Primitive.getEncoded("DER");
        if (!Arrays.areEqual(byArray2, byArray)) {
            throw new TlsFatalAlert(50);
        }
        return aSN1Primitive;
    }

    public static void writeGMTUnixTime(byte[] byArray, int n) {
        int n2 = (int)(System.currentTimeMillis() / 1000L);
        byArray[n] = (byte)(n2 >>> 24);
        byArray[n + 1] = (byte)(n2 >>> 16);
        byArray[n + 2] = (byte)(n2 >>> 8);
        byArray[n + 3] = (byte)n2;
    }

    public static void writeVersion(ProtocolVersion protocolVersion, OutputStream outputStream) throws IOException {
        outputStream.write(protocolVersion.getMajorVersion());
        outputStream.write(protocolVersion.getMinorVersion());
    }

    public static void writeVersion(ProtocolVersion protocolVersion, byte[] byArray, int n) {
        byArray[n] = (byte)protocolVersion.getMajorVersion();
        byArray[n + 1] = (byte)protocolVersion.getMinorVersion();
    }

    public static Vector getAllSignatureAlgorithms() {
        Vector<Short> vector = new Vector<Short>(4);
        vector.addElement(Shorts.valueOf((short)0));
        vector.addElement(Shorts.valueOf((short)1));
        vector.addElement(Shorts.valueOf((short)2));
        vector.addElement(Shorts.valueOf((short)3));
        return vector;
    }

    public static Vector getDefaultDSSSignatureAlgorithms() {
        return TlsUtils.vectorOfOne(new SignatureAndHashAlgorithm(2, 2));
    }

    public static Vector getDefaultECDSASignatureAlgorithms() {
        return TlsUtils.vectorOfOne(new SignatureAndHashAlgorithm(2, 3));
    }

    public static Vector getDefaultRSASignatureAlgorithms() {
        return TlsUtils.vectorOfOne(new SignatureAndHashAlgorithm(2, 1));
    }

    public static Vector getDefaultSupportedSignatureAlgorithms() {
        short[] sArray = new short[]{2, 3, 4, 5, 6};
        short[] sArray2 = new short[]{1, 2, 3};
        Vector<SignatureAndHashAlgorithm> vector = new Vector<SignatureAndHashAlgorithm>();
        for (int i = 0; i < sArray2.length; ++i) {
            for (int j = 0; j < sArray.length; ++j) {
                vector.addElement(new SignatureAndHashAlgorithm(sArray[j], sArray2[i]));
            }
        }
        return vector;
    }

    public static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(TlsContext tlsContext, TlsSignerCredentials tlsSignerCredentials) throws IOException {
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = null;
        if (TlsUtils.isTLSv12(tlsContext) && (signatureAndHashAlgorithm = tlsSignerCredentials.getSignatureAndHashAlgorithm()) == null) {
            throw new TlsFatalAlert(80);
        }
        return signatureAndHashAlgorithm;
    }

    public static byte[] getExtensionData(Hashtable hashtable, Integer n) {
        return hashtable == null ? null : (byte[])hashtable.get(n);
    }

    public static boolean hasExpectedEmptyExtensionData(Hashtable hashtable, Integer n, short s) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, n);
        if (byArray == null) {
            return false;
        }
        if (byArray.length != 0) {
            throw new TlsFatalAlert(s);
        }
        return true;
    }

    public static TlsSession importSession(byte[] byArray, SessionParameters sessionParameters) {
        return new TlsSessionImpl(byArray, sessionParameters);
    }

    public static boolean isSignatureAlgorithmsExtensionAllowed(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static void addSignatureAlgorithmsExtension(Hashtable hashtable, Vector vector) throws IOException {
        hashtable.put(EXT_signature_algorithms, TlsUtils.createSignatureAlgorithmsExtension(vector));
    }

    public static Vector getSignatureAlgorithmsExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_signature_algorithms);
        return byArray == null ? null : TlsUtils.readSignatureAlgorithmsExtension(byArray);
    }

    public static byte[] createSignatureAlgorithmsExtension(Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsUtils.encodeSupportedSignatureAlgorithms(vector, false, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Vector readSignatureAlgorithmsExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        Vector vector = TlsUtils.parseSupportedSignatureAlgorithms(false, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        return vector;
    }

    public static void encodeSupportedSignatureAlgorithms(Vector vector, boolean bl, OutputStream outputStream) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        int n = 2 * vector.size();
        TlsUtils.checkUint16(n);
        TlsUtils.writeUint16(n, outputStream);
        for (int i = 0; i < vector.size(); ++i) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm)vector.elementAt(i);
            if (!bl && signatureAndHashAlgorithm.getSignature() == 0) {
                throw new IllegalArgumentException("SignatureAlgorithm.anonymous MUST NOT appear in the signature_algorithms extension");
            }
            signatureAndHashAlgorithm.encode(outputStream);
        }
    }

    public static Vector parseSupportedSignatureAlgorithms(boolean bl, InputStream inputStream) throws IOException {
        int n = TlsUtils.readUint16(inputStream);
        if (n < 2 || (n & 1) != 0) {
            throw new TlsFatalAlert(50);
        }
        int n2 = n / 2;
        Vector<SignatureAndHashAlgorithm> vector = new Vector<SignatureAndHashAlgorithm>(n2);
        for (int i = 0; i < n2; ++i) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = SignatureAndHashAlgorithm.parse(inputStream);
            if (!bl && signatureAndHashAlgorithm.getSignature() == 0) {
                throw new TlsFatalAlert(47);
            }
            vector.addElement(signatureAndHashAlgorithm);
        }
        return vector;
    }

    public static void verifySupportedSignatureAlgorithm(Vector vector, SignatureAndHashAlgorithm signatureAndHashAlgorithm) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        if (signatureAndHashAlgorithm == null) {
            throw new IllegalArgumentException("'signatureAlgorithm' cannot be null");
        }
        if (signatureAndHashAlgorithm.getSignature() != 0) {
            for (int i = 0; i < vector.size(); ++i) {
                SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = (SignatureAndHashAlgorithm)vector.elementAt(i);
                if (signatureAndHashAlgorithm2.getHash() != signatureAndHashAlgorithm.getHash() || signatureAndHashAlgorithm2.getSignature() != signatureAndHashAlgorithm.getSignature()) continue;
                return;
            }
        }
        throw new TlsFatalAlert(47);
    }

    public static byte[] PRF(TlsContext tlsContext, byte[] byArray, String string, byte[] byArray2, int n) {
        ProtocolVersion protocolVersion = tlsContext.getServerVersion();
        if (protocolVersion.isSSL()) {
            throw new IllegalStateException("No PRF available for SSLv3 session");
        }
        byte[] byArray3 = Strings.toByteArray(string);
        byte[] byArray4 = TlsUtils.concat(byArray3, byArray2);
        int n2 = tlsContext.getSecurityParameters().getPrfAlgorithm();
        if (n2 == 0) {
            return TlsUtils.PRF_legacy(byArray, byArray3, byArray4, n);
        }
        Digest digest = TlsUtils.createPRFHash(n2);
        byte[] byArray5 = new byte[n];
        TlsUtils.hmac_hash(digest, byArray, byArray4, byArray5);
        return byArray5;
    }

    public static byte[] PRF_legacy(byte[] byArray, String string, byte[] byArray2, int n) {
        byte[] byArray3 = Strings.toByteArray(string);
        byte[] byArray4 = TlsUtils.concat(byArray3, byArray2);
        return TlsUtils.PRF_legacy(byArray, byArray3, byArray4, n);
    }

    static byte[] PRF_legacy(byte[] byArray, byte[] byArray2, byte[] byArray3, int n) {
        int n2 = (byArray.length + 1) / 2;
        byte[] byArray4 = new byte[n2];
        byte[] byArray5 = new byte[n2];
        System.arraycopy(byArray, 0, byArray4, 0, n2);
        System.arraycopy(byArray, byArray.length - n2, byArray5, 0, n2);
        byte[] byArray6 = new byte[n];
        byte[] byArray7 = new byte[n];
        TlsUtils.hmac_hash(TlsUtils.createHash((short)1), byArray4, byArray3, byArray6);
        TlsUtils.hmac_hash(TlsUtils.createHash((short)2), byArray5, byArray3, byArray7);
        for (int i = 0; i < n; ++i) {
            int n3 = i;
            byArray6[n3] = (byte)(byArray6[n3] ^ byArray7[i]);
        }
        return byArray6;
    }

    static byte[] concat(byte[] byArray, byte[] byArray2) {
        byte[] byArray3 = new byte[byArray.length + byArray2.length];
        System.arraycopy(byArray, 0, byArray3, 0, byArray.length);
        System.arraycopy(byArray2, 0, byArray3, byArray.length, byArray2.length);
        return byArray3;
    }

    static void hmac_hash(Digest digest, byte[] byArray, byte[] byArray2, byte[] byArray3) {
        HMac hMac = new HMac(digest);
        hMac.init(new KeyParameter(byArray));
        byte[] byArray4 = byArray2;
        int n = digest.getDigestSize();
        int n2 = (byArray3.length + n - 1) / n;
        byte[] byArray5 = new byte[hMac.getMacSize()];
        byte[] byArray6 = new byte[hMac.getMacSize()];
        for (int i = 0; i < n2; ++i) {
            hMac.update(byArray4, 0, byArray4.length);
            hMac.doFinal(byArray5, 0);
            byArray4 = byArray5;
            hMac.update(byArray4, 0, byArray4.length);
            hMac.update(byArray2, 0, byArray2.length);
            hMac.doFinal(byArray6, 0);
            System.arraycopy(byArray6, 0, byArray3, n * i, Math.min(n, byArray3.length - n * i));
        }
    }

    static void validateKeyUsage(org.bouncycastle.asn1.x509.Certificate certificate, int n) throws IOException {
        int n2;
        KeyUsage keyUsage;
        Extensions extensions = certificate.getTBSCertificate().getExtensions();
        if (extensions != null && (keyUsage = KeyUsage.fromExtensions(extensions)) != null && ((n2 = keyUsage.getBytes()[0] & 0xFF) & n) != n) {
            throw new TlsFatalAlert(46);
        }
    }

    static byte[] calculateKeyBlock(TlsContext tlsContext, int n) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] byArray = securityParameters.getMasterSecret();
        byte[] byArray2 = TlsUtils.concat(securityParameters.getServerRandom(), securityParameters.getClientRandom());
        if (TlsUtils.isSSL(tlsContext)) {
            return TlsUtils.calculateKeyBlock_SSL(byArray, byArray2, n);
        }
        return TlsUtils.PRF(tlsContext, byArray, "key expansion", byArray2, n);
    }

    static byte[] calculateKeyBlock_SSL(byte[] byArray, byte[] byArray2, int n) {
        Digest digest = TlsUtils.createHash((short)1);
        Digest digest2 = TlsUtils.createHash((short)2);
        int n2 = digest.getDigestSize();
        byte[] byArray3 = new byte[digest2.getDigestSize()];
        byte[] byArray4 = new byte[n + n2];
        int n3 = 0;
        int n4 = 0;
        while (n4 < n) {
            byte[] byArray5 = SSL3_CONST[n3];
            digest2.update(byArray5, 0, byArray5.length);
            digest2.update(byArray, 0, byArray.length);
            digest2.update(byArray2, 0, byArray2.length);
            digest2.doFinal(byArray3, 0);
            digest.update(byArray, 0, byArray.length);
            digest.update(byArray3, 0, byArray3.length);
            digest.doFinal(byArray4, n4);
            n4 += n2;
            ++n3;
        }
        return Arrays.copyOfRange(byArray4, 0, n);
    }

    static byte[] calculateMasterSecret(TlsContext tlsContext, byte[] byArray) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] byArray2 = securityParameters.extendedMasterSecret ? securityParameters.getSessionHash() : TlsUtils.concat(securityParameters.getClientRandom(), securityParameters.getServerRandom());
        if (TlsUtils.isSSL(tlsContext)) {
            return TlsUtils.calculateMasterSecret_SSL(byArray, byArray2);
        }
        String string = securityParameters.extendedMasterSecret ? "extended master secret" : "master secret";
        return TlsUtils.PRF(tlsContext, byArray, string, byArray2, 48);
    }

    static byte[] calculateMasterSecret_SSL(byte[] byArray, byte[] byArray2) {
        Digest digest = TlsUtils.createHash((short)1);
        Digest digest2 = TlsUtils.createHash((short)2);
        int n = digest.getDigestSize();
        byte[] byArray3 = new byte[digest2.getDigestSize()];
        byte[] byArray4 = new byte[n * 3];
        int n2 = 0;
        for (int i = 0; i < 3; ++i) {
            byte[] byArray5 = SSL3_CONST[i];
            digest2.update(byArray5, 0, byArray5.length);
            digest2.update(byArray, 0, byArray.length);
            digest2.update(byArray2, 0, byArray2.length);
            digest2.doFinal(byArray3, 0);
            digest.update(byArray, 0, byArray.length);
            digest.update(byArray3, 0, byArray3.length);
            digest.doFinal(byArray4, n2);
            n2 += n;
        }
        return byArray4;
    }

    static byte[] calculateVerifyData(TlsContext tlsContext, String string, byte[] byArray) {
        if (TlsUtils.isSSL(tlsContext)) {
            return byArray;
        }
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] byArray2 = securityParameters.getMasterSecret();
        int n = securityParameters.getVerifyDataLength();
        return TlsUtils.PRF(tlsContext, byArray2, string, byArray, n);
    }

    public static Digest createHash(short s) {
        switch (s) {
            case 1: {
                return new MD5Digest();
            }
            case 2: {
                return new SHA1Digest();
            }
            case 3: {
                return new SHA224Digest();
            }
            case 4: {
                return new SHA256Digest();
            }
            case 5: {
                return new SHA384Digest();
            }
            case 6: {
                return new SHA512Digest();
            }
        }
        throw new IllegalArgumentException("unknown HashAlgorithm");
    }

    public static Digest createHash(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        return signatureAndHashAlgorithm == null ? new CombinedHash() : TlsUtils.createHash(signatureAndHashAlgorithm.getHash());
    }

    public static Digest cloneHash(short s, Digest digest) {
        switch (s) {
            case 1: {
                return new MD5Digest((MD5Digest)digest);
            }
            case 2: {
                return new SHA1Digest((SHA1Digest)digest);
            }
            case 3: {
                return new SHA224Digest((SHA224Digest)digest);
            }
            case 4: {
                return new SHA256Digest((SHA256Digest)digest);
            }
            case 5: {
                return new SHA384Digest((SHA384Digest)digest);
            }
            case 6: {
                return new SHA512Digest((SHA512Digest)digest);
            }
        }
        throw new IllegalArgumentException("unknown HashAlgorithm");
    }

    public static Digest createPRFHash(int n) {
        switch (n) {
            case 0: {
                return new CombinedHash();
            }
        }
        return TlsUtils.createHash(TlsUtils.getHashAlgorithmForPRFAlgorithm(n));
    }

    public static Digest clonePRFHash(int n, Digest digest) {
        switch (n) {
            case 0: {
                return new CombinedHash((CombinedHash)digest);
            }
        }
        return TlsUtils.cloneHash(TlsUtils.getHashAlgorithmForPRFAlgorithm(n), digest);
    }

    public static short getHashAlgorithmForPRFAlgorithm(int n) {
        switch (n) {
            case 0: {
                throw new IllegalArgumentException("legacy PRF not a valid algorithm");
            }
            case 1: {
                return 4;
            }
            case 2: {
                return 5;
            }
        }
        throw new IllegalArgumentException("unknown PRFAlgorithm");
    }

    public static ASN1ObjectIdentifier getOIDForHashAlgorithm(short s) {
        switch (s) {
            case 1: {
                return PKCSObjectIdentifiers.md5;
            }
            case 2: {
                return X509ObjectIdentifiers.id_SHA1;
            }
            case 3: {
                return NISTObjectIdentifiers.id_sha224;
            }
            case 4: {
                return NISTObjectIdentifiers.id_sha256;
            }
            case 5: {
                return NISTObjectIdentifiers.id_sha384;
            }
            case 6: {
                return NISTObjectIdentifiers.id_sha512;
            }
        }
        throw new IllegalArgumentException("unknown HashAlgorithm");
    }

    static short getClientCertificateType(Certificate certificate, Certificate certificate2) throws IOException {
        if (certificate.isEmpty()) {
            return -1;
        }
        org.bouncycastle.asn1.x509.Certificate certificate3 = certificate.getCertificateAt(0);
        SubjectPublicKeyInfo subjectPublicKeyInfo = certificate3.getSubjectPublicKeyInfo();
        try {
            AsymmetricKeyParameter asymmetricKeyParameter = PublicKeyFactory.createKey(subjectPublicKeyInfo);
            if (asymmetricKeyParameter.isPrivate()) {
                throw new TlsFatalAlert(80);
            }
            if (asymmetricKeyParameter instanceof RSAKeyParameters) {
                TlsUtils.validateKeyUsage(certificate3, 128);
                return 1;
            }
            if (asymmetricKeyParameter instanceof DSAPublicKeyParameters) {
                TlsUtils.validateKeyUsage(certificate3, 128);
                return 2;
            }
            if (asymmetricKeyParameter instanceof ECPublicKeyParameters) {
                TlsUtils.validateKeyUsage(certificate3, 128);
                return 64;
            }
            throw new TlsFatalAlert(43);
        } catch (Exception exception) {
            throw new TlsFatalAlert(43, (Throwable)exception);
        }
    }

    static void trackHashAlgorithms(TlsHandshakeHash tlsHandshakeHash, Vector vector) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm)vector.elementAt(i);
                short s = signatureAndHashAlgorithm.getHash();
                if (HashAlgorithm.isPrivate(s)) continue;
                tlsHandshakeHash.trackHashAlgorithm(s);
            }
        }
    }

    public static boolean hasSigningCapability(short s) {
        switch (s) {
            case 1: 
            case 2: 
            case 64: {
                return true;
            }
        }
        return false;
    }

    public static TlsSigner createTlsSigner(short s) {
        switch (s) {
            case 2: {
                return new TlsDSSSigner();
            }
            case 64: {
                return new TlsECDSASigner();
            }
            case 1: {
                return new TlsRSASigner();
            }
        }
        throw new IllegalArgumentException("'clientCertificateType' is not a type with signing capability");
    }

    private static byte[][] genSSL3Const() {
        int n = 10;
        byte[][] byArrayArray = new byte[n][];
        for (int i = 0; i < n; ++i) {
            byte[] byArray = new byte[i + 1];
            Arrays.fill(byArray, (byte)(65 + i));
            byArrayArray[i] = byArray;
        }
        return byArrayArray;
    }

    private static Vector vectorOfOne(Object object) {
        Vector<Object> vector = new Vector<Object>(1);
        vector.addElement(object);
        return vector;
    }

    public static int getCipherType(int n) throws IOException {
        switch (TlsUtils.getEncryptionAlgorithm(n)) {
            case 10: 
            case 11: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 103: 
            case 104: {
                return 2;
            }
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 12: 
            case 13: 
            case 14: {
                return 1;
            }
            case 0: 
            case 1: 
            case 2: {
                return 0;
            }
        }
        throw new TlsFatalAlert(80);
    }

    public static int getEncryptionAlgorithm(int n) throws IOException {
        switch (n) {
            case 10: 
            case 13: 
            case 16: 
            case 19: 
            case 22: 
            case 27: 
            case 139: 
            case 143: 
            case 147: 
            case 49155: 
            case 49160: 
            case 49165: 
            case 49170: 
            case 49175: 
            case 49178: 
            case 49179: 
            case 49180: 
            case 49204: {
                return 7;
            }
            case 47: 
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 60: 
            case 62: 
            case 63: 
            case 64: 
            case 103: 
            case 108: 
            case 140: 
            case 144: 
            case 148: 
            case 174: 
            case 178: 
            case 182: 
            case 49156: 
            case 49161: 
            case 49166: 
            case 49171: 
            case 49176: 
            case 49181: 
            case 49182: 
            case 49183: 
            case 49187: 
            case 49189: 
            case 49191: 
            case 49193: 
            case 49205: 
            case 49207: {
                return 8;
            }
            case 49308: 
            case 49310: 
            case 49316: 
            case 49318: 
            case 49324: {
                return 15;
            }
            case 49312: 
            case 49314: 
            case 49320: 
            case 49322: 
            case 49326: {
                return 16;
            }
            case 156: 
            case 158: 
            case 160: 
            case 162: 
            case 164: 
            case 166: 
            case 168: 
            case 170: 
            case 172: 
            case 49195: 
            case 49197: 
            case 49199: 
            case 49201: {
                return 10;
            }
            case 65280: 
            case 65282: 
            case 65284: 
            case 65296: 
            case 65298: 
            case 65300: {
                return 103;
            }
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: 
            case 58: 
            case 61: 
            case 104: 
            case 105: 
            case 106: 
            case 107: 
            case 109: 
            case 141: 
            case 145: 
            case 149: 
            case 175: 
            case 179: 
            case 183: 
            case 49157: 
            case 49162: 
            case 49167: 
            case 49172: 
            case 49177: 
            case 49184: 
            case 49185: 
            case 49186: 
            case 49188: 
            case 49190: 
            case 49192: 
            case 49194: 
            case 49206: 
            case 49208: {
                return 9;
            }
            case 49309: 
            case 49311: 
            case 49317: 
            case 49319: 
            case 49325: {
                return 17;
            }
            case 49313: 
            case 49315: 
            case 49321: 
            case 49323: 
            case 49327: {
                return 18;
            }
            case 157: 
            case 159: 
            case 161: 
            case 163: 
            case 165: 
            case 167: 
            case 169: 
            case 171: 
            case 173: 
            case 49196: 
            case 49198: 
            case 49200: 
            case 49202: {
                return 11;
            }
            case 65281: 
            case 65283: 
            case 65285: 
            case 65297: 
            case 65299: 
            case 65301: {
                return 104;
            }
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 186: 
            case 187: 
            case 188: 
            case 189: 
            case 190: 
            case 191: 
            case 49266: 
            case 49268: 
            case 49270: 
            case 49272: 
            case 49300: 
            case 49302: 
            case 49304: 
            case 49306: {
                return 12;
            }
            case 49274: 
            case 49276: 
            case 49278: 
            case 49280: 
            case 49282: 
            case 49284: 
            case 49286: 
            case 49288: 
            case 49290: 
            case 49292: 
            case 49294: 
            case 49296: 
            case 49298: {
                return 19;
            }
            case 132: 
            case 133: 
            case 134: 
            case 135: 
            case 136: 
            case 137: 
            case 192: 
            case 193: 
            case 194: 
            case 195: 
            case 196: 
            case 197: 
            case 49267: 
            case 49269: 
            case 49271: 
            case 49273: 
            case 49301: 
            case 49303: 
            case 49305: 
            case 49307: {
                return 13;
            }
            case 49275: 
            case 49277: 
            case 49279: 
            case 49281: 
            case 49283: 
            case 49285: 
            case 49287: 
            case 49289: 
            case 49291: 
            case 49293: 
            case 49295: 
            case 49297: 
            case 49299: {
                return 20;
            }
            case 52392: 
            case 52393: 
            case 52394: 
            case 52395: 
            case 52396: 
            case 52397: 
            case 52398: {
                return 21;
            }
            case 1: {
                return 0;
            }
            case 2: 
            case 44: 
            case 45: 
            case 46: 
            case 49153: 
            case 49158: 
            case 49163: 
            case 49168: 
            case 49173: 
            case 49209: {
                return 0;
            }
            case 59: 
            case 176: 
            case 180: 
            case 184: 
            case 49210: {
                return 0;
            }
            case 177: 
            case 181: 
            case 185: 
            case 49211: {
                return 0;
            }
            case 4: 
            case 24: {
                return 2;
            }
            case 5: 
            case 138: 
            case 142: 
            case 146: 
            case 49154: 
            case 49159: 
            case 49164: 
            case 49169: 
            case 49174: 
            case 49203: {
                return 2;
            }
            case 150: 
            case 151: 
            case 152: 
            case 153: 
            case 154: 
            case 155: {
                return 14;
            }
        }
        throw new TlsFatalAlert(80);
    }

    public static int getKeyExchangeAlgorithm(int n) throws IOException {
        switch (n) {
            case 24: 
            case 27: 
            case 52: 
            case 58: 
            case 70: 
            case 108: 
            case 109: 
            case 137: 
            case 155: 
            case 166: 
            case 167: 
            case 191: 
            case 197: 
            case 49284: 
            case 49285: {
                return 11;
            }
            case 13: 
            case 48: 
            case 54: 
            case 62: 
            case 66: 
            case 104: 
            case 133: 
            case 151: 
            case 164: 
            case 165: 
            case 187: 
            case 193: 
            case 49282: 
            case 49283: {
                return 7;
            }
            case 16: 
            case 49: 
            case 55: 
            case 63: 
            case 67: 
            case 105: 
            case 134: 
            case 152: 
            case 160: 
            case 161: 
            case 188: 
            case 194: 
            case 49278: 
            case 49279: {
                return 9;
            }
            case 19: 
            case 50: 
            case 56: 
            case 64: 
            case 68: 
            case 106: 
            case 135: 
            case 153: 
            case 162: 
            case 163: 
            case 189: 
            case 195: 
            case 49280: 
            case 49281: {
                return 3;
            }
            case 45: 
            case 142: 
            case 143: 
            case 144: 
            case 145: 
            case 170: 
            case 171: 
            case 178: 
            case 179: 
            case 180: 
            case 181: 
            case 49296: 
            case 49297: 
            case 49302: 
            case 49303: 
            case 49318: 
            case 49319: 
            case 49322: 
            case 49323: 
            case 52397: 
            case 65298: 
            case 65299: {
                return 14;
            }
            case 22: 
            case 51: 
            case 57: 
            case 69: 
            case 103: 
            case 107: 
            case 136: 
            case 154: 
            case 158: 
            case 159: 
            case 190: 
            case 196: 
            case 49276: 
            case 49277: 
            case 49310: 
            case 49311: 
            case 49314: 
            case 49315: 
            case 52394: 
            case 65280: 
            case 65281: {
                return 5;
            }
            case 49173: 
            case 49174: 
            case 49175: 
            case 49176: 
            case 49177: {
                return 20;
            }
            case 49153: 
            case 49154: 
            case 49155: 
            case 49156: 
            case 49157: 
            case 49189: 
            case 49190: 
            case 49197: 
            case 49198: 
            case 49268: 
            case 49269: 
            case 49288: 
            case 49289: {
                return 16;
            }
            case 49163: 
            case 49164: 
            case 49165: 
            case 49166: 
            case 49167: 
            case 49193: 
            case 49194: 
            case 49201: 
            case 49202: 
            case 49272: 
            case 49273: 
            case 49292: 
            case 49293: {
                return 18;
            }
            case 49158: 
            case 49159: 
            case 49160: 
            case 49161: 
            case 49162: 
            case 49187: 
            case 49188: 
            case 49195: 
            case 49196: 
            case 49266: 
            case 49267: 
            case 49286: 
            case 49287: 
            case 49324: 
            case 49325: 
            case 49326: 
            case 49327: 
            case 52393: 
            case 65284: 
            case 65285: {
                return 17;
            }
            case 49203: 
            case 49204: 
            case 49205: 
            case 49206: 
            case 49207: 
            case 49208: 
            case 49209: 
            case 49210: 
            case 49211: 
            case 49306: 
            case 49307: 
            case 52396: 
            case 65300: 
            case 65301: {
                return 24;
            }
            case 49168: 
            case 49169: 
            case 49170: 
            case 49171: 
            case 49172: 
            case 49191: 
            case 49192: 
            case 49199: 
            case 49200: 
            case 49270: 
            case 49271: 
            case 49290: 
            case 49291: 
            case 52392: 
            case 65282: 
            case 65283: {
                return 19;
            }
            case 44: 
            case 138: 
            case 139: 
            case 140: 
            case 141: 
            case 168: 
            case 169: 
            case 174: 
            case 175: 
            case 176: 
            case 177: 
            case 49294: 
            case 49295: 
            case 49300: 
            case 49301: 
            case 49316: 
            case 49317: 
            case 49320: 
            case 49321: 
            case 52395: 
            case 65296: 
            case 65297: {
                return 13;
            }
            case 1: 
            case 2: 
            case 4: 
            case 5: 
            case 10: 
            case 47: 
            case 53: 
            case 59: 
            case 60: 
            case 61: 
            case 65: 
            case 132: 
            case 150: 
            case 156: 
            case 157: 
            case 186: 
            case 192: 
            case 49274: 
            case 49275: 
            case 49308: 
            case 49309: 
            case 49312: 
            case 49313: {
                return 1;
            }
            case 46: 
            case 146: 
            case 147: 
            case 148: 
            case 149: 
            case 172: 
            case 173: 
            case 182: 
            case 183: 
            case 184: 
            case 185: 
            case 49298: 
            case 49299: 
            case 49304: 
            case 49305: 
            case 52398: {
                return 15;
            }
            case 49178: 
            case 49181: 
            case 49184: {
                return 21;
            }
            case 49180: 
            case 49183: 
            case 49186: {
                return 22;
            }
            case 49179: 
            case 49182: 
            case 49185: {
                return 23;
            }
        }
        throw new TlsFatalAlert(80);
    }

    public static int getMACAlgorithm(int n) throws IOException {
        switch (n) {
            case 156: 
            case 157: 
            case 158: 
            case 159: 
            case 160: 
            case 161: 
            case 162: 
            case 163: 
            case 164: 
            case 165: 
            case 166: 
            case 167: 
            case 168: 
            case 169: 
            case 170: 
            case 171: 
            case 172: 
            case 173: 
            case 49195: 
            case 49196: 
            case 49197: 
            case 49198: 
            case 49199: 
            case 49200: 
            case 49201: 
            case 49202: 
            case 49274: 
            case 49275: 
            case 49276: 
            case 49277: 
            case 49278: 
            case 49279: 
            case 49280: 
            case 49281: 
            case 49282: 
            case 49283: 
            case 49284: 
            case 49285: 
            case 49286: 
            case 49287: 
            case 49288: 
            case 49289: 
            case 49290: 
            case 49291: 
            case 49292: 
            case 49293: 
            case 49294: 
            case 49295: 
            case 49296: 
            case 49297: 
            case 49298: 
            case 49299: 
            case 49308: 
            case 49309: 
            case 49310: 
            case 49311: 
            case 49312: 
            case 49313: 
            case 49314: 
            case 49315: 
            case 49316: 
            case 49317: 
            case 49318: 
            case 49319: 
            case 49320: 
            case 49321: 
            case 49322: 
            case 49323: 
            case 49324: 
            case 49325: 
            case 49326: 
            case 49327: 
            case 52392: 
            case 52393: 
            case 52394: 
            case 52395: 
            case 52396: 
            case 52397: 
            case 52398: 
            case 65280: 
            case 65281: 
            case 65282: 
            case 65283: 
            case 65284: 
            case 65285: 
            case 65296: 
            case 65297: 
            case 65298: 
            case 65299: 
            case 65300: 
            case 65301: {
                return 0;
            }
            case 1: 
            case 4: 
            case 24: {
                return 1;
            }
            case 2: 
            case 5: 
            case 10: 
            case 13: 
            case 16: 
            case 19: 
            case 22: 
            case 27: 
            case 44: 
            case 45: 
            case 46: 
            case 47: 
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: 
            case 58: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 132: 
            case 133: 
            case 134: 
            case 135: 
            case 136: 
            case 137: 
            case 138: 
            case 139: 
            case 140: 
            case 141: 
            case 142: 
            case 143: 
            case 144: 
            case 145: 
            case 146: 
            case 147: 
            case 148: 
            case 149: 
            case 150: 
            case 151: 
            case 152: 
            case 153: 
            case 154: 
            case 155: 
            case 49153: 
            case 49154: 
            case 49155: 
            case 49156: 
            case 49157: 
            case 49158: 
            case 49159: 
            case 49160: 
            case 49161: 
            case 49162: 
            case 49163: 
            case 49164: 
            case 49165: 
            case 49166: 
            case 49167: 
            case 49168: 
            case 49169: 
            case 49170: 
            case 49171: 
            case 49172: 
            case 49173: 
            case 49174: 
            case 49175: 
            case 49176: 
            case 49177: 
            case 49178: 
            case 49179: 
            case 49180: 
            case 49181: 
            case 49182: 
            case 49183: 
            case 49184: 
            case 49185: 
            case 49186: 
            case 49203: 
            case 49204: 
            case 49205: 
            case 49206: 
            case 49209: {
                return 2;
            }
            case 59: 
            case 60: 
            case 61: 
            case 62: 
            case 63: 
            case 64: 
            case 103: 
            case 104: 
            case 105: 
            case 106: 
            case 107: 
            case 108: 
            case 109: 
            case 174: 
            case 176: 
            case 178: 
            case 180: 
            case 182: 
            case 184: 
            case 186: 
            case 187: 
            case 188: 
            case 189: 
            case 190: 
            case 191: 
            case 192: 
            case 193: 
            case 194: 
            case 195: 
            case 196: 
            case 197: 
            case 49187: 
            case 49189: 
            case 49191: 
            case 49193: 
            case 49207: 
            case 49210: 
            case 49266: 
            case 49268: 
            case 49270: 
            case 49272: 
            case 49300: 
            case 49302: 
            case 49304: 
            case 49306: {
                return 3;
            }
            case 175: 
            case 177: 
            case 179: 
            case 181: 
            case 183: 
            case 185: 
            case 49188: 
            case 49190: 
            case 49192: 
            case 49194: 
            case 49208: 
            case 49211: 
            case 49267: 
            case 49269: 
            case 49271: 
            case 49273: 
            case 49301: 
            case 49303: 
            case 49305: 
            case 49307: {
                return 4;
            }
        }
        throw new TlsFatalAlert(80);
    }

    public static ProtocolVersion getMinimumVersion(int n) {
        switch (n) {
            case 59: 
            case 60: 
            case 61: 
            case 62: 
            case 63: 
            case 64: 
            case 103: 
            case 104: 
            case 105: 
            case 106: 
            case 107: 
            case 108: 
            case 109: 
            case 156: 
            case 157: 
            case 158: 
            case 159: 
            case 160: 
            case 161: 
            case 162: 
            case 163: 
            case 164: 
            case 165: 
            case 166: 
            case 167: 
            case 168: 
            case 169: 
            case 170: 
            case 171: 
            case 172: 
            case 173: 
            case 186: 
            case 187: 
            case 188: 
            case 189: 
            case 190: 
            case 191: 
            case 192: 
            case 193: 
            case 194: 
            case 195: 
            case 196: 
            case 197: 
            case 49187: 
            case 49188: 
            case 49189: 
            case 49190: 
            case 49191: 
            case 49192: 
            case 49193: 
            case 49194: 
            case 49195: 
            case 49196: 
            case 49197: 
            case 49198: 
            case 49199: 
            case 49200: 
            case 49201: 
            case 49202: 
            case 49266: 
            case 49267: 
            case 49268: 
            case 49269: 
            case 49270: 
            case 49271: 
            case 49272: 
            case 49273: 
            case 49274: 
            case 49275: 
            case 49276: 
            case 49277: 
            case 49278: 
            case 49279: 
            case 49280: 
            case 49281: 
            case 49282: 
            case 49283: 
            case 49284: 
            case 49285: 
            case 49286: 
            case 49287: 
            case 49288: 
            case 49289: 
            case 49290: 
            case 49291: 
            case 49292: 
            case 49293: 
            case 49294: 
            case 49295: 
            case 49296: 
            case 49297: 
            case 49298: 
            case 49299: 
            case 49308: 
            case 49309: 
            case 49310: 
            case 49311: 
            case 49312: 
            case 49313: 
            case 49314: 
            case 49315: 
            case 49316: 
            case 49317: 
            case 49318: 
            case 49319: 
            case 49320: 
            case 49321: 
            case 49322: 
            case 49323: 
            case 49324: 
            case 49325: 
            case 49326: 
            case 49327: 
            case 52392: 
            case 52393: 
            case 52394: 
            case 52395: 
            case 52396: 
            case 52397: 
            case 52398: 
            case 65280: 
            case 65281: 
            case 65282: 
            case 65283: 
            case 65284: 
            case 65285: 
            case 65296: 
            case 65297: 
            case 65298: 
            case 65299: 
            case 65300: 
            case 65301: {
                return ProtocolVersion.TLSv12;
            }
        }
        return ProtocolVersion.SSLv3;
    }

    public static boolean isAEADCipherSuite(int n) throws IOException {
        return 2 == TlsUtils.getCipherType(n);
    }

    public static boolean isBlockCipherSuite(int n) throws IOException {
        return 1 == TlsUtils.getCipherType(n);
    }

    public static boolean isStreamCipherSuite(int n) throws IOException {
        return 0 == TlsUtils.getCipherType(n);
    }

    public static boolean isValidCipherSuiteForSignatureAlgorithms(int n, Vector vector) {
        int n2;
        try {
            n2 = TlsUtils.getKeyExchangeAlgorithm(n);
        } catch (IOException iOException) {
            return true;
        }
        switch (n2) {
            case 11: 
            case 12: 
            case 20: {
                return vector.contains(Shorts.valueOf((short)0));
            }
            case 5: 
            case 6: 
            case 19: 
            case 23: {
                return vector.contains(Shorts.valueOf((short)1));
            }
            case 3: 
            case 4: 
            case 22: {
                return vector.contains(Shorts.valueOf((short)2));
            }
            case 17: {
                return vector.contains(Shorts.valueOf((short)3));
            }
        }
        return true;
    }

    public static boolean isValidCipherSuiteForVersion(int n, ProtocolVersion protocolVersion) {
        return TlsUtils.getMinimumVersion(n).isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static Vector getUsableSignatureAlgorithms(Vector vector) {
        if (vector == null) {
            return TlsUtils.getAllSignatureAlgorithms();
        }
        Vector<Short> vector2 = new Vector<Short>(4);
        vector2.addElement(Shorts.valueOf((short)0));
        for (int i = 0; i < vector.size(); ++i) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm)vector.elementAt(i);
            Short s = Shorts.valueOf(signatureAndHashAlgorithm.getSignature());
            if (vector2.contains(s)) continue;
            vector2.addElement(s);
        }
        return vector2;
    }
}

