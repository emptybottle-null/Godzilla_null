/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import org.springframework.cglib.proxy.Callback;

public interface Dispatcher
extends Callback {
    public Object loadObject() throws Exception;
}

