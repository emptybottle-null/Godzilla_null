/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform.impl;

import org.springframework.cglib.transform.impl.InterceptFieldCallback;

public interface InterceptFieldEnabled {
    public void setInterceptFieldCallback(InterceptFieldCallback var1);

    public InterceptFieldCallback getInterceptFieldCallback();
}

