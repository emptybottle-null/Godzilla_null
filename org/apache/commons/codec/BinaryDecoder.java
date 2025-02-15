/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec;

import org.apache.commons.codec.Decoder;
import org.apache.commons.codec.DecoderException;

public interface BinaryDecoder
extends Decoder {
    public byte[] decode(byte[] var1) throws DecoderException;
}

