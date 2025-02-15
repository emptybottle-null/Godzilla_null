/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.IndexedColorMap;

public class DefaultIndexedColorMap
implements IndexedColorMap {
    @Override
    public byte[] getRGB(int index) {
        return DefaultIndexedColorMap.getDefaultRGB(index);
    }

    public static byte[] getDefaultRGB(int index) {
        HSSFColor hssfColor = HSSFColor.getIndexHash().get(index);
        if (hssfColor == null) {
            return null;
        }
        short[] rgbShort = hssfColor.getTriplet();
        return new byte[]{(byte)rgbShort[0], (byte)rgbShort[1], (byte)rgbShort[2]};
    }
}

