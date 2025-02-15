/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import org.apache.log4j.spi.ThrowableRenderer;

public interface ThrowableRendererSupport {
    public ThrowableRenderer getThrowableRenderer();

    public void setThrowableRenderer(ThrowableRenderer var1);
}

