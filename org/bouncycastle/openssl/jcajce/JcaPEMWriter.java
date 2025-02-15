/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.jcajce;

import java.io.IOException;
import java.io.Writer;
import org.bouncycastle.openssl.PEMEncryptor;
import org.bouncycastle.openssl.jcajce.JcaMiscPEMGenerator;
import org.bouncycastle.util.io.pem.PemGenerationException;
import org.bouncycastle.util.io.pem.PemObjectGenerator;
import org.bouncycastle.util.io.pem.PemWriter;

public class JcaPEMWriter
extends PemWriter {
    public JcaPEMWriter(Writer writer) {
        super(writer);
    }

    public void writeObject(Object object) throws IOException {
        this.writeObject(object, null);
    }

    public void writeObject(Object object, PEMEncryptor pEMEncryptor) throws IOException {
        try {
            super.writeObject(new JcaMiscPEMGenerator(object, pEMEncryptor));
        } catch (PemGenerationException pemGenerationException) {
            if (pemGenerationException.getCause() instanceof IOException) {
                throw (IOException)pemGenerationException.getCause();
            }
            throw pemGenerationException;
        }
    }

    public void writeObject(PemObjectGenerator pemObjectGenerator) throws IOException {
        super.writeObject(pemObjectGenerator);
    }
}

