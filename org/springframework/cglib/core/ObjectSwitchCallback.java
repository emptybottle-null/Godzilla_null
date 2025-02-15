/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import org.springframework.asm.Label;

public interface ObjectSwitchCallback {
    public void processCase(Object var1, Label var2) throws Exception;

    public void processDefault() throws Exception;
}

