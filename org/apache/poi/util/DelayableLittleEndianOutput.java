/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import org.apache.poi.util.LittleEndianOutput;

public interface DelayableLittleEndianOutput
extends LittleEndianOutput {
    public LittleEndianOutput createDelayedOutput(int var1);
}

