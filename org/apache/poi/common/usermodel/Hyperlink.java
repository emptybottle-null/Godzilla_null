/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.common.usermodel;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.util.Removal;

public interface Hyperlink {
    public String getAddress();

    public void setAddress(String var1);

    public String getLabel();

    public void setLabel(String var1);

    public HyperlinkType getType();

    @Deprecated
    @Removal(version="4.2")
    public HyperlinkType getTypeEnum();
}

