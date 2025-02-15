/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl;

import java.io.IOException;
import org.bouncycastle.openssl.PEMDecryptor;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMKeyPairParser;
import org.bouncycastle.operator.OperatorCreationException;

public class PEMEncryptedKeyPair {
    private final String dekAlgName;
    private final byte[] iv;
    private final byte[] keyBytes;
    private final PEMKeyPairParser parser;

    PEMEncryptedKeyPair(String string, byte[] byArray, byte[] byArray2, PEMKeyPairParser pEMKeyPairParser) {
        this.dekAlgName = string;
        this.iv = byArray;
        this.keyBytes = byArray2;
        this.parser = pEMKeyPairParser;
    }

    public PEMKeyPair decryptKeyPair(PEMDecryptorProvider pEMDecryptorProvider) throws IOException {
        try {
            PEMDecryptor pEMDecryptor = pEMDecryptorProvider.get(this.dekAlgName);
            return this.parser.parse(pEMDecryptor.decrypt(this.keyBytes, this.iv));
        } catch (IOException iOException) {
            throw iOException;
        } catch (OperatorCreationException operatorCreationException) {
            throw new PEMException("cannot create extraction operator: " + operatorCreationException.getMessage(), operatorCreationException);
        } catch (Exception exception) {
            throw new PEMException("exception processing key pair: " + exception.getMessage(), exception);
        }
    }
}

