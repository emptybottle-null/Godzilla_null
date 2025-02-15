/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import org.springframework.cglib.proxy.Callback;

public interface NoOp
extends Callback {
    public static final NoOp INSTANCE = new NoOp(){};
}

