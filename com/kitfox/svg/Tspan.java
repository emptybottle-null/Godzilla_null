/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.Font;
import com.kitfox.svg.FontFace;
import com.kitfox.svg.MissingGlyph;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.util.FontSystem;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Tspan
extends ShapeElement {
    public static final String TAG_NAME = "tspan";
    float[] x = null;
    float[] y = null;
    float[] dx = null;
    float[] dy = null;
    float[] rotate = null;
    private String text = "";

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderAddText(SVGLoaderHelper helper, String text) {
        this.text = this.text + text;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("x"))) {
            this.x = sty.getFloatList();
        }
        if (this.getPres(sty.setName("y"))) {
            this.y = sty.getFloatList();
        }
        if (this.getPres(sty.setName("dx"))) {
            this.dx = sty.getFloatList();
        }
        if (this.getPres(sty.setName("dy"))) {
            this.dy = sty.getFloatList();
        }
        if (this.getPres(sty.setName("rotate"))) {
            this.rotate = sty.getFloatList();
            for (int i = 0; i < this.rotate.length; ++i) {
                this.rotate[i] = (float)Math.toRadians(this.rotate[i]);
            }
        }
    }

    public void appendToShape(GeneralPath addShape, Point2D cursor) throws SVGException {
        StyleAttribute sty = new StyleAttribute();
        String fontFamily = null;
        if (this.getStyle(sty.setName("font-family"))) {
            fontFamily = sty.getStringValue();
        }
        float fontSize = 12.0f;
        if (this.getStyle(sty.setName("font-size"))) {
            fontSize = sty.getFloatValueWithUnits();
        }
        float letterSpacing = 0.0f;
        if (this.getStyle(sty.setName("letter-spacing"))) {
            letterSpacing = sty.getFloatValueWithUnits();
        }
        int fontStyle = 0;
        if (this.getStyle(sty.setName("font-style"))) {
            String s = sty.getStringValue();
            if ("normal".equals(s)) {
                fontStyle = 0;
            } else if ("italic".equals(s)) {
                fontStyle = 1;
            } else if ("oblique".equals(s)) {
                fontStyle = 2;
            }
        } else {
            fontStyle = 0;
        }
        int fontWeight = 0;
        if (this.getStyle(sty.setName("font-weight"))) {
            String s = sty.getStringValue();
            if ("normal".equals(s)) {
                fontWeight = 0;
            } else if ("bold".equals(s)) {
                fontWeight = 1;
            }
        } else {
            fontWeight = 0;
        }
        Font font = this.diagram.getUniverse().getFont(fontFamily);
        if (font == null && fontFamily != null) {
            font = FontSystem.createFont(fontFamily, fontStyle, fontWeight, fontSize);
        }
        if (font == null) {
            font = FontSystem.createFont("Serif", fontStyle, fontWeight, fontSize);
        }
        AffineTransform xform = new AffineTransform();
        float cursorX = (float)cursor.getX();
        float cursorY = (float)cursor.getY();
        String drawText = this.text;
        drawText = drawText.trim();
        for (int i = 0; i < drawText.length(); ++i) {
            String unicode2;
            MissingGlyph glyph;
            Shape path;
            if (this.x != null && i < this.x.length) {
                cursorX = this.x[i];
            } else if (this.dx != null && i < this.dx.length) {
                cursorX += this.dx[i];
            }
            if (this.y != null && i < this.y.length) {
                cursorY = this.y[i];
            } else if (this.dy != null && i < this.dy.length) {
                cursorY += this.dy[i];
            }
            xform.setToIdentity();
            xform.setToTranslation(cursorX, cursorY);
            if (this.rotate != null) {
                xform.rotate(this.rotate[i]);
            }
            if ((path = (glyph = font.getGlyph(unicode2 = drawText.substring(i, i + 1))).getPath()) != null) {
                path = xform.createTransformedShape(path);
                addShape.append(path, false);
            }
            cursorX += glyph.getHorizAdvX() + letterSpacing;
        }
        cursor.setLocation(cursorX, cursorY);
        this.strokeWidthScalar = 1.0f;
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        Font font;
        float cursorX = 0.0f;
        float cursorY = 0.0f;
        if (this.x != null) {
            cursorX = this.x[0];
            cursorY = this.y[0];
        } else if (this.dx != null) {
            cursorX += this.dx[0];
            cursorY += this.dy[0];
        }
        StyleAttribute sty = new StyleAttribute();
        String fontFamily = null;
        if (this.getPres(sty.setName("font-family"))) {
            fontFamily = sty.getStringValue();
        }
        float fontSize = 12.0f;
        if (this.getPres(sty.setName("font-size"))) {
            fontSize = sty.getFloatValueWithUnits();
        }
        if ((font = this.diagram.getUniverse().getFont(fontFamily)) == null) {
            System.err.println("Could not load font");
            java.awt.Font sysFont = new java.awt.Font(fontFamily, 0, (int)fontSize);
            this.renderSysFont(g, sysFont);
            return;
        }
        FontFace fontFace = font.getFontFace();
        int ascent = fontFace.getAscent();
        float fontScale = fontSize / (float)ascent;
        AffineTransform oldXform = g.getTransform();
        AffineTransform xform = new AffineTransform();
        this.strokeWidthScalar = 1.0f / fontScale;
        int posPtr = 1;
        for (int i = 0; i < this.text.length(); ++i) {
            xform.setToTranslation(cursorX, cursorY);
            xform.scale(fontScale, fontScale);
            g.transform(xform);
            String unicode2 = this.text.substring(i, i + 1);
            MissingGlyph glyph = font.getGlyph(unicode2);
            Shape path = glyph.getPath();
            if (path != null) {
                this.renderShape(g, path);
            } else {
                glyph.render(g);
            }
            if (this.x != null && posPtr < this.x.length) {
                cursorX = this.x[posPtr];
                cursorY = this.y[posPtr++];
            } else if (this.dx != null && posPtr < this.dx.length) {
                cursorX += this.dx[posPtr];
                cursorY += this.dy[posPtr++];
            }
            cursorX += fontScale * glyph.getHorizAdvX();
            g.setTransform(oldXform);
        }
        this.strokeWidthScalar = 1.0f;
    }

    protected void renderSysFont(Graphics2D g, java.awt.Font font) throws SVGException {
        float cursorX = 0.0f;
        float cursorY = 0.0f;
        FontRenderContext frc = g.getFontRenderContext();
        Shape textShape = font.createGlyphVector(frc, this.text).getOutline(cursorX, cursorY);
        this.renderShape(g, textShape);
        Rectangle2D rect = font.getStringBounds(this.text, frc);
        cursorX += (float)rect.getWidth();
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return null;
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        return false;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

