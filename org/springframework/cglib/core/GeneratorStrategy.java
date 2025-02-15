/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import org.springframework.cglib.core.ClassGenerator;

public interface GeneratorStrategy {
    public byte[] generate(ClassGenerator var1) throws Exception;

    public boolean equals(Object var1);
}

