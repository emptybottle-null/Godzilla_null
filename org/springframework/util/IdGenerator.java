/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.UUID;

@FunctionalInterface
public interface IdGenerator {
    public UUID generateId();
}

