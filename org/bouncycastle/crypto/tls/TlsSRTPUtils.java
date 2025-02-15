/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.crypto.tls.UseSRTPData;
import org.bouncycastle.util.Integers;

public class TlsSRTPUtils {
    public static final Integer EXT_use_srtp = Integers.valueOf(14);

    public static void addUseSRTPExtension(Hashtable hashtable, UseSRTPData useSRTPData) throws IOException {
        hashtable.put(EXT_use_srtp, TlsSRTPUtils.createUseSRTPExtension(useSRTPData));
    }

    public static UseSRTPData getUseSRTPExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_use_srtp);
        return byArray == null ? null : TlsSRTPUtils.readUseSRTPExtension(byArray);
    }

    public static byte[] createUseSRTPExtension(UseSRTPData useSRTPData) throws IOException {
        if (useSRTPData == null) {
            throw new IllegalArgumentException("'useSRTPData' cannot be null");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsUtils.writeUint16ArrayWithUint16Length(useSRTPData.getProtectionProfiles(), byteArrayOutputStream);
        TlsUtils.writeOpaque8(useSRTPData.getMki(), byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static UseSRTPData readUseSRTPExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        int n = TlsUtils.readUint16(byteArrayInputStream);
        if (n < 2 || (n & 1) != 0) {
            throw new TlsFatalAlert(50);
        }
        int[] nArray = TlsUtils.readUint16Array(n / 2, byteArrayInputStream);
        byte[] byArray2 = TlsUtils.readOpaque8(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        return new UseSRTPData(nArray, byArray2);
    }
}

