/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.util.IdGenerator;

public class SimpleIdGenerator
implements IdGenerator {
    private final AtomicLong leastSigBits = new AtomicLong();

    @Override
    public UUID generateId() {
        return new UUID(0L, this.leastSigBits.incrementAndGet());
    }
}

