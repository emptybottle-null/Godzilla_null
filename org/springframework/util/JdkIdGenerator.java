/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.UUID;
import org.springframework.util.IdGenerator;

public class JdkIdGenerator
implements IdGenerator {
    @Override
    public UUID generateId() {
        return UUID.randomUUID();
    }
}

