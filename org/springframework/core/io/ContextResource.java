/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import org.springframework.core.io.Resource;

public interface ContextResource
extends Resource {
    public String getPathWithinContext();
}

