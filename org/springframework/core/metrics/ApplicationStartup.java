/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.metrics;

import org.springframework.core.metrics.DefaultApplicationStartup;
import org.springframework.core.metrics.StartupStep;

public interface ApplicationStartup {
    public static final ApplicationStartup DEFAULT = new DefaultApplicationStartup();

    public StartupStep start(String var1);
}

