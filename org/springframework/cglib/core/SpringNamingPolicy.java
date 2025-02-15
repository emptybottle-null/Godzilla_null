/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import org.springframework.cglib.core.DefaultNamingPolicy;

public class SpringNamingPolicy
extends DefaultNamingPolicy {
    public static final SpringNamingPolicy INSTANCE = new SpringNamingPolicy();

    @Override
    protected String getTag() {
        return "BySpringCGLIB";
    }
}

