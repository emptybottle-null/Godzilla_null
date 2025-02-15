/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.common;

import org.apache.poi.util.LittleEndianOutput;

public interface SharedFeature {
    public String toString();

    public void serialize(LittleEndianOutput var1);

    public int getDataSize();

    public SharedFeature copy();
}

