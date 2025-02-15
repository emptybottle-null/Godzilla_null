/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.buffer;

import org.springframework.core.io.buffer.DataBuffer;

public interface PooledDataBuffer
extends DataBuffer {
    public boolean isAllocated();

    public PooledDataBuffer retain();

    public PooledDataBuffer touch(Object var1);

    public boolean release();
}

