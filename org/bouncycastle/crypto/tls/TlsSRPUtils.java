/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Hashtable;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;

public class TlsSRPUtils {
    public static final Integer EXT_SRP = Integers.valueOf(12);

    public static void addSRPExtension(Hashtable hashtable, byte[] byArray) throws IOException {
        hashtable.put(EXT_SRP, TlsSRPUtils.createSRPExtension(byArray));
    }

    public static byte[] getSRPExtension(Hashtable hashtable) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, EXT_SRP);
        return byArray == null ? null : TlsSRPUtils.readSRPExtension(byArray);
    }

    public static byte[] createSRPExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new TlsFatalAlert(80);
        }
        return TlsUtils.encodeOpaque8(byArray);
    }

    public static byte[] readSRPExtension(byte[] byArray) throws IOException {
        if (byArray == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        byte[] byArray2 = TlsUtils.readOpaque8(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        return byArray2;
    }

    public static BigInteger readSRPParameter(InputStream inputStream) throws IOException {
        return new BigInteger(1, TlsUtils.readOpaque16(inputStream));
    }

    public static void writeSRPParameter(BigInteger bigInteger, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque16(BigIntegers.asUnsignedByteArray(bigInteger), outputStream);
    }

    public static boolean isSRPCipherSuite(int n) {
        switch (n) {
            case 49178: 
            case 49179: 
            case 49180: 
            case 49181: 
            case 49182: 
            case 49183: 
            case 49184: 
            case 49185: 
            case 49186: {
                return true;
            }
        }
        return false;
    }
}

