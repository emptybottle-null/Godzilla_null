/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.hssf.util.HSSFColor;

public class HSSFExtendedColor
extends org.apache.poi.ss.usermodel.ExtendedColor {
    private ExtendedColor color;

    public HSSFExtendedColor(ExtendedColor color) {
        this.color = color;
    }

    protected ExtendedColor getExtendedColor() {
        return this.color;
    }

    @Override
    public boolean isAuto() {
        return this.color.getType() == 0;
    }

    @Override
    public boolean isIndexed() {
        return this.color.getType() == 1;
    }

    @Override
    public boolean isRGB() {
        return this.color.getType() == 2;
    }

    @Override
    public boolean isThemed() {
        return this.color.getType() == 3;
    }

    @Override
    public short getIndex() {
        return (short)this.color.getColorIndex();
    }

    @Override
    public int getTheme() {
        return this.color.getThemeIndex();
    }

    @Override
    public byte[] getRGB() {
        byte[] rgb = new byte[3];
        byte[] rgba = this.color.getRGBA();
        if (rgba == null) {
            return null;
        }
        System.arraycopy(rgba, 0, rgb, 0, 3);
        return rgb;
    }

    @Override
    public byte[] getARGB() {
        byte[] argb = new byte[4];
        byte[] rgba = this.color.getRGBA();
        if (rgba == null) {
            return null;
        }
        System.arraycopy(rgba, 0, argb, 1, 3);
        argb[0] = rgba[3];
        return argb;
    }

    @Override
    protected byte[] getStoredRBG() {
        return this.getARGB();
    }

    @Override
    public void setRGB(byte[] rgb) {
        if (rgb.length == 3) {
            byte[] rgba = new byte[4];
            System.arraycopy(rgb, 0, rgba, 0, 3);
            rgba[3] = -1;
        } else {
            byte a = rgb[0];
            rgb[0] = rgb[1];
            rgb[1] = rgb[2];
            rgb[2] = rgb[3];
            rgb[3] = a;
            this.color.setRGBA(rgb);
        }
        this.color.setType(2);
    }

    @Override
    public double getTint() {
        return this.color.getTint();
    }

    @Override
    public void setTint(double tint) {
        this.color.setTint(tint);
    }

    @Override
    protected byte[] getIndexedRGB() {
        if (this.isIndexed() && this.getIndex() > 0) {
            short indexNum = this.getIndex();
            HSSFColor indexed = HSSFColor.getIndexHash().get(indexNum);
            if (indexed != null) {
                byte[] rgb = new byte[]{(byte)indexed.getTriplet()[0], (byte)indexed.getTriplet()[1], (byte)indexed.getTriplet()[2]};
                return rgb;
            }
        }
        return null;
    }
}

