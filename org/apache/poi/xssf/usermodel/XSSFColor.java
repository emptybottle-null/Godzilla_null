/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.awt.Color;
import java.util.Arrays;
import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;

public class XSSFColor
extends ExtendedColor {
    private final CTColor ctColor;
    private final IndexedColorMap indexedColorMap;

    public static XSSFColor from(CTColor color, IndexedColorMap map) {
        return color == null ? null : new XSSFColor(color, map);
    }

    @Deprecated
    @Removal(version="4.2")
    public XSSFColor(CTColor color) {
        this(color, (IndexedColorMap)new DefaultIndexedColorMap());
    }

    @Deprecated
    @Removal(version="4.2")
    public XSSFColor(CTColor color, IndexedColorMap map) {
        this.ctColor = color;
        this.indexedColorMap = map;
    }

    @Deprecated
    @Removal(version="4.2")
    public XSSFColor() {
        this(CTColor.Factory.newInstance(), (IndexedColorMap)new DefaultIndexedColorMap());
    }

    public XSSFColor(IndexedColorMap colorMap) {
        this(CTColor.Factory.newInstance(), colorMap);
    }

    @Deprecated
    @Removal(version="4.2")
    public XSSFColor(Color clr) {
        this(clr, (IndexedColorMap)new DefaultIndexedColorMap());
    }

    public XSSFColor(Color clr, IndexedColorMap map) {
        this(map);
        this.setColor(clr);
    }

    public XSSFColor(byte[] rgb, IndexedColorMap colorMap) {
        this(CTColor.Factory.newInstance(), colorMap);
        this.ctColor.setRgb(rgb);
    }

    public XSSFColor(IndexedColors indexedColor, IndexedColorMap colorMap) {
        this(CTColor.Factory.newInstance(), colorMap);
        this.ctColor.setIndexed(indexedColor.index);
    }

    @Override
    public boolean isAuto() {
        return this.ctColor.getAuto();
    }

    public void setAuto(boolean auto) {
        this.ctColor.setAuto(auto);
    }

    @Override
    public boolean isIndexed() {
        return this.ctColor.isSetIndexed();
    }

    @Override
    public boolean isRGB() {
        return this.ctColor.isSetRgb();
    }

    @Override
    public boolean isThemed() {
        return this.ctColor.isSetTheme();
    }

    public boolean hasAlpha() {
        return this.ctColor.isSetRgb() && this.ctColor.getRgb().length == 4;
    }

    public boolean hasTint() {
        return this.ctColor.isSetTint() && this.ctColor.getTint() != 0.0;
    }

    @Override
    public short getIndex() {
        return (short)this.ctColor.getIndexed();
    }

    public short getIndexed() {
        return this.getIndex();
    }

    public void setIndexed(int indexed) {
        this.ctColor.setIndexed(indexed);
    }

    @Override
    public byte[] getRGB() {
        byte[] rgb = this.getRGBOrARGB();
        if (rgb == null) {
            return null;
        }
        if (rgb.length == 4) {
            byte[] tmp = new byte[3];
            System.arraycopy(rgb, 1, tmp, 0, 3);
            return tmp;
        }
        return rgb;
    }

    @Override
    public byte[] getARGB() {
        byte[] rgb = this.getRGBOrARGB();
        if (rgb == null) {
            return null;
        }
        if (rgb.length == 3) {
            byte[] tmp = new byte[4];
            tmp[0] = -1;
            System.arraycopy(rgb, 0, tmp, 1, 3);
            return tmp;
        }
        return rgb;
    }

    @Override
    protected byte[] getStoredRBG() {
        return this.ctColor.getRgb();
    }

    @Override
    protected byte[] getIndexedRGB() {
        if (this.isIndexed()) {
            if (this.indexedColorMap != null) {
                return this.indexedColorMap.getRGB(this.getIndex());
            }
            return DefaultIndexedColorMap.getDefaultRGB(this.getIndex());
        }
        return null;
    }

    @Override
    public void setRGB(byte[] rgb) {
        this.ctColor.setRgb(rgb);
    }

    @Override
    public int getTheme() {
        return (int)this.ctColor.getTheme();
    }

    public void setTheme(int theme) {
        this.ctColor.setTheme(theme);
    }

    @Override
    public double getTint() {
        return this.ctColor.getTint();
    }

    @Override
    public void setTint(double tint) {
        this.ctColor.setTint(tint);
    }

    @Internal
    public CTColor getCTColor() {
        return this.ctColor;
    }

    public static XSSFColor toXSSFColor(org.apache.poi.ss.usermodel.Color color) {
        if (color != null && !(color instanceof XSSFColor)) {
            throw new IllegalArgumentException("Only XSSFColor objects are supported, but had " + color.getClass());
        }
        return (XSSFColor)color;
    }

    public int hashCode() {
        return this.ctColor.toString().hashCode();
    }

    private boolean sameIndexed(XSSFColor other) {
        if (this.isIndexed() == other.isIndexed()) {
            return !this.isIndexed() || this.getIndexed() == other.getIndexed();
        }
        return false;
    }

    private boolean sameARGB(XSSFColor other) {
        if (this.isRGB() == other.isRGB()) {
            return !this.isRGB() || Arrays.equals(this.getARGB(), other.getARGB());
        }
        return false;
    }

    private boolean sameTheme(XSSFColor other) {
        if (this.isThemed() == other.isThemed()) {
            return !this.isThemed() || this.getTheme() == other.getTheme();
        }
        return false;
    }

    private boolean sameTint(XSSFColor other) {
        if (this.hasTint() == other.hasTint()) {
            return !this.hasTint() || this.getTint() == other.getTint();
        }
        return false;
    }

    private boolean sameAuto(XSSFColor other) {
        return this.isAuto() == other.isAuto();
    }

    public boolean equals(Object o) {
        if (!(o instanceof XSSFColor)) {
            return false;
        }
        XSSFColor other = (XSSFColor)o;
        return this.sameARGB(other) && this.sameTheme(other) && this.sameIndexed(other) && this.sameTint(other) && this.sameAuto(other);
    }
}

