/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.Collection;
import org.springframework.util.InstanceFilter;

public class ExceptionTypeFilter
extends InstanceFilter<Class<? extends Throwable>> {
    public ExceptionTypeFilter(Collection<? extends Class<? extends Throwable>> includes, Collection<? extends Class<? extends Throwable>> excludes, boolean matchIfEmpty) {
        super(includes, excludes, matchIfEmpty);
    }

    @Override
    protected boolean match(Class<? extends Throwable> instance, Class<? extends Throwable> candidate) {
        return candidate.isAssignableFrom(instance);
    }
}

