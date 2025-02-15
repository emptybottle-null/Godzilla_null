/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.util.Internal;

@Internal
class DrawFontInfo
implements FontInfo {
    private final String typeface;

    DrawFontInfo(String typeface) {
        this.typeface = typeface;
    }

    @Override
    public String getTypeface() {
        return this.typeface;
    }
}

