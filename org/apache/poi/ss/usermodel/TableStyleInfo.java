/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.TableStyle;

public interface TableStyleInfo {
    public boolean isShowColumnStripes();

    public boolean isShowRowStripes();

    public boolean isShowFirstColumn();

    public boolean isShowLastColumn();

    public String getName();

    public TableStyle getStyle();
}

