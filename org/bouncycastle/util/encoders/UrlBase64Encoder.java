/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.encoders;

import org.bouncycastle.util.encoders.Base64Encoder;

public class UrlBase64Encoder
extends Base64Encoder {
    public UrlBase64Encoder() {
        this.encodingTable[this.encodingTable.length - 2] = 45;
        this.encodingTable[this.encodingTable.length - 1] = 95;
        this.padding = (byte)46;
        this.initialiseDecodingTable();
    }
}

