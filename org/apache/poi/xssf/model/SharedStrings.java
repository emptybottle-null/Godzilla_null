/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.model;

import org.apache.poi.ss.usermodel.RichTextString;

public interface SharedStrings {
    public RichTextString getItemAt(int var1);

    public int getCount();

    public int getUniqueCount();
}

