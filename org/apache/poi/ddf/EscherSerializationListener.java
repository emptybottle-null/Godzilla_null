/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import org.apache.poi.ddf.EscherRecord;

public interface EscherSerializationListener {
    public void beforeRecordSerialize(int var1, short var2, EscherRecord var3);

    public void afterRecordSerialize(int var1, short var2, int var3, EscherRecord var4);
}

