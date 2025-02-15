/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public class TlsRSAUtils {
    public static byte[] generateEncryptedPreMasterSecret(TlsContext tlsContext, RSAKeyParameters rSAKeyParameters, OutputStream outputStream) throws IOException {
        byte[] byArray = new byte[48];
        tlsContext.getSecureRandom().nextBytes(byArray);
        TlsUtils.writeVersion(tlsContext.getClientVersion(), byArray, 0);
        PKCS1Encoding pKCS1Encoding = new PKCS1Encoding(new RSABlindedEngine());
        pKCS1Encoding.init(true, new ParametersWithRandom(rSAKeyParameters, tlsContext.getSecureRandom()));
        try {
            byte[] byArray2 = pKCS1Encoding.processBlock(byArray, 0, byArray.length);
            if (TlsUtils.isSSL(tlsContext)) {
                outputStream.write(byArray2);
            } else {
                TlsUtils.writeOpaque16(byArray2, outputStream);
            }
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new TlsFatalAlert(80, (Throwable)invalidCipherTextException);
        }
        return byArray;
    }

    public static byte[] safeDecryptPreMasterSecret(TlsContext tlsContext, RSAKeyParameters rSAKeyParameters, byte[] byArray) {
        ProtocolVersion protocolVersion = tlsContext.getClientVersion();
        boolean bl = false;
        byte[] byArray2 = new byte[48];
        tlsContext.getSecureRandom().nextBytes(byArray2);
        byte[] byArray3 = Arrays.clone(byArray2);
        try {
            PKCS1Encoding pKCS1Encoding = new PKCS1Encoding((AsymmetricBlockCipher)new RSABlindedEngine(), byArray2);
            pKCS1Encoding.init(false, new ParametersWithRandom(rSAKeyParameters, tlsContext.getSecureRandom()));
            byArray3 = pKCS1Encoding.processBlock(byArray, 0, byArray.length);
        } catch (Exception exception) {
            // empty catch block
        }
        if (!bl || !protocolVersion.isEqualOrEarlierVersionOf(ProtocolVersion.TLSv10)) {
            int n = protocolVersion.getMajorVersion() ^ byArray3[0] & 0xFF | protocolVersion.getMinorVersion() ^ byArray3[1] & 0xFF;
            n |= n >> 1;
            n |= n >> 2;
            n |= n >> 4;
            int n2 = ~((n & 1) - 1);
            for (int i = 0; i < 48; ++i) {
                byArray3[i] = (byte)(byArray3[i] & ~n2 | byArray2[i] & n2);
            }
        }
        return byArray3;
    }
}

