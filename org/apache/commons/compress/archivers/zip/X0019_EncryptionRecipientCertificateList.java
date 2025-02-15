/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.archivers.zip.PKWareExtraHeader;
import org.apache.commons.compress.archivers.zip.ZipShort;

public class X0019_EncryptionRecipientCertificateList
extends PKWareExtraHeader {
    public X0019_EncryptionRecipientCertificateList() {
        super(new ZipShort(25));
    }
}

