/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.lang.reflect.Method;

public interface CallbackFilter {
    public int accept(Method var1);

    public boolean equals(Object var1);
}

