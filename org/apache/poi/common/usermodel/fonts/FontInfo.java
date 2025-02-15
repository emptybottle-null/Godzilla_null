/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.common.usermodel.fonts;

import java.util.Collections;
import java.util.List;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.common.usermodel.fonts.FontFacet;
import org.apache.poi.common.usermodel.fonts.FontFamily;
import org.apache.poi.common.usermodel.fonts.FontPitch;

public interface FontInfo {
    default public Integer getIndex() {
        return null;
    }

    default public void setIndex(int index) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    public String getTypeface();

    default public void setTypeface(String typeface) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default public FontCharset getCharset() {
        return FontCharset.ANSI;
    }

    default public void setCharset(FontCharset charset) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default public FontFamily getFamily() {
        return FontFamily.FF_DONTCARE;
    }

    default public void setFamily(FontFamily family) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default public FontPitch getPitch() {
        return null;
    }

    default public void setPitch(FontPitch pitch) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default public byte[] getPanose() {
        return null;
    }

    default public void setPanose(byte[] panose) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default public List<? extends FontFacet> getFacets() {
        return Collections.emptyList();
    }
}

