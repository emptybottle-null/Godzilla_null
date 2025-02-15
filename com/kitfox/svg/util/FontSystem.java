/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.util;

import com.kitfox.svg.Font;
import com.kitfox.svg.FontFace;
import com.kitfox.svg.Glyph;
import com.kitfox.svg.MissingGlyph;
import java.awt.Canvas;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphVector;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class FontSystem
extends Font {
    java.awt.Font sysFont;
    FontMetrics fm;
    HashMap<String, Glyph> glyphCache = new HashMap();
    static HashSet<String> sysFontNames = new HashSet();

    public static boolean checkIfSystemFontExists(String fontName) {
        if (sysFontNames.isEmpty()) {
            for (String name : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.ENGLISH)) {
                sysFontNames.add(name);
            }
        }
        return sysFontNames.contains(fontName);
    }

    public static FontSystem createFont(String fontFamily, int fontStyle, int fontWeight, float fontSize) {
        String[] families;
        for (String fontName : families = fontFamily.split(",")) {
            String javaFontName = FontSystem.mapJavaFontName(fontName);
            if (!FontSystem.checkIfSystemFontExists(javaFontName)) continue;
            return new FontSystem(javaFontName, fontStyle, fontWeight, fontSize);
        }
        return null;
    }

    private static String mapJavaFontName(String fontName) {
        if ("serif".equals(fontName)) {
            return "Serif";
        }
        if ("sans-serif".equals(fontName)) {
            return "SansSerif";
        }
        if ("monospace".equals(fontName)) {
            return "Monospaced";
        }
        return fontName;
    }

    private FontSystem(String fontFamily, int fontStyle, int fontWeight, float fontSize) {
        int weight;
        int style;
        switch (fontStyle) {
            case 1: {
                style = 2;
                break;
            }
            default: {
                style = 0;
            }
        }
        switch (fontWeight) {
            case 1: 
            case 2: {
                weight = 1;
                break;
            }
            default: {
                weight = 0;
            }
        }
        this.sysFont = new java.awt.Font(fontFamily, style | weight, 1).deriveFont(fontSize);
        Canvas c = new Canvas();
        this.fm = c.getFontMetrics(this.sysFont);
        FontFace face = new FontFace();
        face.setAscent(this.fm.getAscent());
        face.setDescent(this.fm.getDescent());
        face.setUnitsPerEm(this.fm.charWidth('M'));
        this.setFontFace(face);
    }

    @Override
    public MissingGlyph getGlyph(String unicode2) {
        FontRenderContext frc = new FontRenderContext(null, true, true);
        GlyphVector vec = this.sysFont.createGlyphVector(frc, unicode2);
        Glyph glyph = this.glyphCache.get(unicode2);
        if (glyph == null) {
            glyph = new Glyph();
            glyph.setPath(vec.getGlyphOutline(0));
            GlyphMetrics gm = vec.getGlyphMetrics(0);
            glyph.setHorizAdvX(gm.getAdvanceX());
            glyph.setVertAdvY(gm.getAdvanceY());
            glyph.setVertOriginX(0.0f);
            glyph.setVertOriginY(0.0f);
            this.glyphCache.put(unicode2, glyph);
        }
        return glyph;
    }
}

