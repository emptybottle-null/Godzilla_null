/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.buffer;

import java.nio.ByteBuffer;
import java.util.List;
import org.springframework.core.io.buffer.DataBuffer;

public interface DataBufferFactory {
    public DataBuffer allocateBuffer();

    public DataBuffer allocateBuffer(int var1);

    public DataBuffer wrap(ByteBuffer var1);

    public DataBuffer wrap(byte[] var1);

    public DataBuffer join(List<? extends DataBuffer> var1);
}

