/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.DTLSRecordLayer;
import org.bouncycastle.crypto.tls.MaxFragmentLength;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public abstract class DTLSProtocol {
    protected final SecureRandom secureRandom;

    protected DTLSProtocol(SecureRandom secureRandom) {
        if (secureRandom == null) {
            throw new IllegalArgumentException("'secureRandom' cannot be null");
        }
        this.secureRandom = secureRandom;
    }

    protected void processFinished(byte[] byArray, byte[] byArray2) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        byte[] byArray3 = TlsUtils.readFully(byArray2.length, (InputStream)byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (!Arrays.constantTimeAreEqual(byArray2, byArray3)) {
            throw new TlsFatalAlert(40);
        }
    }

    protected static void applyMaxFragmentLengthExtension(DTLSRecordLayer dTLSRecordLayer, short s) throws IOException {
        if (s >= 0) {
            if (!MaxFragmentLength.isValid(s)) {
                throw new TlsFatalAlert(80);
            }
            int n = 1 << 8 + s;
            dTLSRecordLayer.setPlaintextLimit(n);
        }
    }

    protected static short evaluateMaxFragmentLengthExtension(boolean bl, Hashtable hashtable, Hashtable hashtable2, short s) throws IOException {
        short s2 = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (s2 >= 0 && (!MaxFragmentLength.isValid(s2) || !bl && s2 != TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable))) {
            throw new TlsFatalAlert(s);
        }
        return s2;
    }

    protected static byte[] generateCertificate(Certificate certificate) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificate.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected static byte[] generateSupplementalData(Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsProtocol.writeSupplementalData(byteArrayOutputStream, vector);
        return byteArrayOutputStream.toByteArray();
    }

    protected static void validateSelectedCipherSuite(int n, short s) throws IOException {
        switch (TlsUtils.getEncryptionAlgorithm(n)) {
            case 1: 
            case 2: {
                throw new TlsFatalAlert(s);
            }
        }
    }
}

