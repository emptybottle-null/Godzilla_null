/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.crypto.tls.CertificateStatusRequest;
import org.bouncycastle.crypto.tls.HeartbeatExtension;
import org.bouncycastle.crypto.tls.ServerNameList;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Integers;

public class TlsExtensionsUtils {
    public static final Integer EXT_encrypt_then_mac = Integers.valueOf(22);
    public static final Integer EXT_extended_master_secret = Integers.valueOf(23);
    public static final Integer EXT_heartbeat = Integers.valueOf(15);
    public static final Integer EXT_max_fragment_length = Integers.valueOf(1);
    public static final Integer EXT_padding = Integers.valueOf(21);
    public static final Integer EXT_server_name = Integers.valueOf(0);
    public static final Integer EXT_status_request = Integers.valueOf(5);
    public static final Integer EXT_truncated_hmac = Integers.valueOf(4);

    public static Hashtable ensureExtensionsInitialised(Hashtable hashtable) {
        return hashtable == null ? new Hashtable() : hashtable;
    }

    public static void addEncryptThenMACExtension(Hashtable hashtable) {
        hashtable.put(EXT_encrypt_then_mac, TlsExtensionsUtils.createEncryptThenMACExtension());
    }

    public static void addExtendedMasterSecretExtension(Hashtable hashtable) {
        hashtable.put(EXT_extended_master_secret, TlsExtensionsUtils.createExtendedMasterSecretExtension());
    }

    public static void addHeartbeatExtension(Hashtable hashtable, HeartbeatExtension heartbeatExtension) throws IOException {
        hashtable.put(EXT_heartbeat, TlsExtensionsUtils.createHeartbeatExtension(heartbeatExtension));
    }

    public static void addMaxFragmentLengthExtension(Hashtable hashtable, short s) throws IOException {
        hashtable.put(EXT_max_fragment_length, TlsExtensionsUtils.createMaxFragmentLengthExtension(s));
    }

    public static void addPaddingExtension(Hashtable hashtable, int n) throws IOException {
        hashtable.put(EXT_padding, TlsExtensionsUtils.createPaddingExtension(n));
    }

    public static void addServerNameExtension(Hashtable hashtable, ServerNameList serverNameList) throws IOException {
        hashtable.put(EXT_server_name, TlsExtensionsUtils.createServerNameExtension(serverNameList));
    }

    public static void addStatusRequestExtension(Hashtable hashtable, CertificateStatusRequest certificateStatusRequest) throws IOException {
        hashtable.put(EXT_status_request, TlsExtensionsUtils.createStatusRequestExtension(certificateStatusRequest));
    }

    public static void addTruncatedHMacExtension(Hashtable hashtable) {
        hashtable.put(EXT_truncated_hmac, TlsExtensionsUtils.createTruncatedHMacExtension());
    }

    public static HeartbeatExtension getHeartbeatExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_heartbeat);
        return byArray == null ? null : TlsExtensionsUtils.readHeartbeatExtension(byArray);
    }

    public static short getMaxFragmentLengthExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_max_fragment_length);
        return byArray == null ? (short)-1 : (short)TlsExtensionsUtils.readMaxFragmentLengthExtension(byArray);
    }

    public static int getPaddingExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_padding);
        return byArray == null ? -1 : TlsExtensionsUtils.readPaddingExtension(byArray);
    }

    public static ServerNameList getServerNameExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_server_name);
        return byArray == null ? null : TlsExtensionsUtils.readServerNameExtension(byArray);
    }

    public static CertificateStatusRequest getStatusRequestExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_status_request);
        return byArray == null ? null : TlsExtensionsUtils.readStatusRequestExtension(byArray);
    }

    public static boolean hasEncryptThenMACExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_encrypt_then_mac);
        return byArray == null ? false : TlsExtensionsUtils.readEncryptThenMACExtension(byArray);
    }

    public static boolean hasExtendedMasterSecretExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_extended_master_secret);
        return byArray == null ? false : TlsExtensionsUtils.readExtendedMasterSecretExtension(byArray);
    }

    public static boolean hasTruncatedHMacExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_truncated_hmac);
        return byArray == null ? false : TlsExtensionsUtils.readTruncatedHMacExtension(byArray);
    }

    public static byte[] createEmptyExtensionData() {
        return TlsUtils.EMPTY_BYTES;
    }

    public static byte[] createEncryptThenMACExtension() {
        return TlsExtensionsUtils.createEmptyExtensionData();
    }

    public static byte[] createExtendedMasterSecretExtension() {
        return TlsExtensionsUtils.createEmptyExtensionData();
    }

    public static byte[] createHeartbeatExtension(HeartbeatExtension heartbeatExtension) throws IOException {
        if (heartbeatExtension == null) {
            throw new TlsFatalAlert(80);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        heartbeatExtension.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] createMaxFragmentLengthExtension(short s) throws IOException {
        TlsUtils.checkUint8(s);
        byte[] byArray = new byte[1];
        TlsUtils.writeUint8(s, byArray, 0);
        return byArray;
    }

    public static byte[] createPaddingExtension(int n) throws IOException {
        TlsUtils.checkUint16(n);
        return new byte[n];
    }

    public static byte[] createServerNameExtension(ServerNameList serverNameList) throws IOException {
        if (serverNameList == null) {
            throw new TlsFatalAlert(80);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        serverNameList.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] createStatusRequestExtension(CertificateStatusRequest certificateStatusRequest) throws IOException {
        if (certificateStatusRequest == null) {
            throw new TlsFatalAlert(80);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateStatusRequest.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] createTruncatedHMacExtension() {
        return TlsExtensionsUtils.createEmptyExtensionData();
    }

    private static boolean readEmptyExtensionData(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        if (byArray.length != 0) {
            throw new TlsFatalAlert(47);
        }
        return true;
    }

    public static boolean readEncryptThenMACExtension(byte[] byArray) throws IOException {
        return TlsExtensionsUtils.readEmptyExtensionData(byArray);
    }

    public static boolean readExtendedMasterSecretExtension(byte[] byArray) throws IOException {
        return TlsExtensionsUtils.readEmptyExtensionData(byArray);
    }

    public static HeartbeatExtension readHeartbeatExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        HeartbeatExtension heartbeatExtension = HeartbeatExtension.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        return heartbeatExtension;
    }

    public static short readMaxFragmentLengthExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        if (byArray.length != 1) {
            throw new TlsFatalAlert(50);
        }
        return TlsUtils.readUint8(byArray, 0);
    }

    public static int readPaddingExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        for (int i = 0; i < byArray.length; ++i) {
            if (byArray[i] == 0) continue;
            throw new TlsFatalAlert(47);
        }
        return byArray.length;
    }

    public static ServerNameList readServerNameExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        ServerNameList serverNameList = ServerNameList.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        return serverNameList;
    }

    public static CertificateStatusRequest readStatusRequestExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        CertificateStatusRequest certificateStatusRequest = CertificateStatusRequest.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        return certificateStatusRequest;
    }

    public static boolean readTruncatedHMacExtension(byte[] byArray) throws IOException {
        return TlsExtensionsUtils.readEmptyExtensionData(byArray);
    }
}

