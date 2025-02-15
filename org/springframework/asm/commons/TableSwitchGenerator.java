/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm.commons;

import org.springframework.asm.Label;

public interface TableSwitchGenerator {
    public void generateCase(int var1, Label var2);

    public void generateDefault();
}

