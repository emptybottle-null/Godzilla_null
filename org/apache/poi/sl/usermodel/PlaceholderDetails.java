/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import org.apache.poi.sl.usermodel.Placeholder;

public interface PlaceholderDetails {
    public Placeholder getPlaceholder();

    public void setPlaceholder(Placeholder var1);

    public boolean isVisible();

    public void setVisible(boolean var1);

    public PlaceholderSize getSize();

    public void setSize(PlaceholderSize var1);

    public String getText();

    public void setText(String var1);

    public static enum PlaceholderSize {
        quarter,
        half,
        full;

    }
}

