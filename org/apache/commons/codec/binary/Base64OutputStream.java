/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.binary;

import java.io.OutputStream;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.BaseNCodecOutputStream;

public class Base64OutputStream
extends BaseNCodecOutputStream {
    public Base64OutputStream(OutputStream out) {
        this(out, true);
    }

    public Base64OutputStream(OutputStream out, boolean doEncode) {
        super(out, new Base64(false), doEncode);
    }

    public Base64OutputStream(OutputStream out, boolean doEncode, int lineLength, byte[] lineSeparator) {
        super(out, new Base64(lineLength, lineSeparator), doEncode);
    }
}

