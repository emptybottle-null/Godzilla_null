/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui.search;

import org.fife.rsta.ui.search.FindReplaceButtonsEnableResult;

final class SearchUtil {
    private SearchUtil() {
    }

    public static String getToolTip(FindReplaceButtonsEnableResult res) {
        String tooltip = res.getError();
        if (tooltip != null && tooltip.indexOf(10) > -1) {
            tooltip = tooltip.replaceFirst("\\\n", "</b><br><pre>");
            tooltip = "<html><b>" + tooltip;
        }
        return tooltip;
    }
}

