/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt;

import java.io.IOException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.LittleEndianInput;

public interface EncryptionInfoBuilder {
    public void initialize(EncryptionInfo var1, LittleEndianInput var2) throws IOException;

    public void initialize(EncryptionInfo var1, CipherAlgorithm var2, HashAlgorithm var3, int var4, int var5, ChainingMode var6);
}

