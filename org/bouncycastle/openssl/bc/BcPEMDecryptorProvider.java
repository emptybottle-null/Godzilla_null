/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.bc;

import org.bouncycastle.openssl.PEMDecryptor;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PasswordException;
import org.bouncycastle.openssl.bc.PEMUtilities;

public class BcPEMDecryptorProvider
implements PEMDecryptorProvider {
    private final char[] password;

    public BcPEMDecryptorProvider(char[] cArray) {
        this.password = cArray;
    }

    public PEMDecryptor get(final String string) {
        return new PEMDecryptor(){

            public byte[] decrypt(byte[] byArray, byte[] byArray2) throws PEMException {
                if (BcPEMDecryptorProvider.this.password == null) {
                    throw new PasswordException("Password is null, but a password is required");
                }
                return PEMUtilities.crypt(false, byArray, BcPEMDecryptorProvider.this.password, string, byArray2);
            }
        };
    }
}

