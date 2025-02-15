/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig.services;

import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.services.RevocationData;

public interface TimeStampService
extends SignatureConfig.SignatureConfigurable {
    public byte[] timeStamp(byte[] var1, RevocationData var2) throws Exception;
}

