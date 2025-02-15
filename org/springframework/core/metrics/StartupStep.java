/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.metrics;

import java.util.function.Supplier;
import org.springframework.lang.Nullable;

public interface StartupStep {
    public String getName();

    public long getId();

    @Nullable
    public Long getParentId();

    public StartupStep tag(String var1, String var2);

    public StartupStep tag(String var1, Supplier<String> var2);

    public Tags getTags();

    public void end();

    public static interface Tag {
        public String getKey();

        public String getValue();
    }

    public static interface Tags
    extends Iterable<Tag> {
    }
}

