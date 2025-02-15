/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.List;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor;

public class CustomIndexedColorMap
implements IndexedColorMap {
    private final byte[][] colorIndex;

    private CustomIndexedColorMap(byte[][] colors) {
        this.colorIndex = colors;
    }

    @Override
    public byte[] getRGB(int index) {
        if (this.colorIndex == null || index < 0 || index >= this.colorIndex.length) {
            return null;
        }
        return this.colorIndex[index];
    }

    public static CustomIndexedColorMap fromColors(CTColors colors) {
        if (colors == null || !colors.isSetIndexedColors()) {
            return null;
        }
        List<CTRgbColor> rgbColorList = colors.getIndexedColors().getRgbColorList();
        byte[][] customColorIndex = new byte[rgbColorList.size()][3];
        for (int i = 0; i < rgbColorList.size(); ++i) {
            customColorIndex[i] = rgbColorList.get(i).getRgb();
        }
        return new CustomIndexedColorMap(customColorIndex);
    }
}

