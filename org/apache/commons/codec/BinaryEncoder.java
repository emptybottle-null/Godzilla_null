/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec;

import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.EncoderException;

public interface BinaryEncoder
extends Encoder {
    public byte[] encode(byte[] var1) throws EncoderException;
}

