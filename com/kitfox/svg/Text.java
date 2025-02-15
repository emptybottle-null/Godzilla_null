/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.Font;
import com.kitfox.svg.MissingGlyph;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.util.FontSystem;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text
extends ShapeElement {
    public static final String TAG_NAME = "text";
    float x = 0.0f;
    float y = 0.0f;
    AffineTransform transform = null;
    String fontFamily;
    float fontSize;
    LinkedList<Serializable> content = new LinkedList();
    Shape textShape;
    public static final int TXAN_START = 0;
    public static final int TXAN_MIDDLE = 1;
    public static final int TXAN_END = 2;
    int textAnchor = 0;
    public static final int TXST_NORMAL = 0;
    public static final int TXST_ITALIC = 1;
    public static final int TXST_OBLIQUE = 2;
    int fontStyle;
    public static final int TXWE_NORMAL = 0;
    public static final int TXWE_BOLD = 1;
    public static final int TXWE_BOLDER = 2;
    public static final int TXWE_LIGHTER = 3;
    public static final int TXWE_100 = 4;
    public static final int TXWE_200 = 5;
    public static final int TXWE_300 = 6;
    public static final int TXWE_400 = 7;
    public static final int TXWE_500 = 8;
    public static final int TXWE_600 = 9;
    public static final int TXWE_700 = 10;
    public static final int TXWE_800 = 11;
    public static final int TXWE_900 = 12;
    int fontWeight;
    float textLength = -1.0f;
    String lengthAdjust = "spacing";

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    public void clearContent() {
        this.content.clear();
    }

    public void appendText(String text) {
        this.content.addLast((Serializable)((Object)text));
    }

    public void appendTspan(Tspan tspan) throws SVGElementException {
        super.loaderAddChild(null, tspan);
        this.content.addLast(tspan);
    }

    public void rebuild() throws SVGException {
        this.build();
    }

    public List<Serializable> getContent() {
        return this.content;
    }

    @Override
    public void loaderAddChild(SVGLoaderHelper helper, SVGElement child) throws SVGElementException {
        super.loaderAddChild(helper, child);
        this.content.addLast(child);
    }

    @Override
    public void loaderAddText(SVGLoaderHelper helper, String text) {
        Matcher matchWs = Pattern.compile("\\s*").matcher(text);
        if (!matchWs.matches()) {
            this.content.addLast((Serializable)((Object)text));
        }
    }

    @Override
    public void build() throws SVGException {
        String s;
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("x"))) {
            this.x = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("y"))) {
            this.y = sty.getFloatValueWithUnits();
        }
        this.fontFamily = this.getStyle(sty.setName("font-family")) ? sty.getStringValue() : "SansSerif";
        this.fontSize = this.getStyle(sty.setName("font-size")) ? sty.getFloatValueWithUnits() : 12.0f;
        this.textLength = this.getStyle(sty.setName("textLength")) ? sty.getFloatValueWithUnits() : -1.0f;
        this.lengthAdjust = this.getStyle(sty.setName("lengthAdjust")) ? sty.getStringValue() : "spacing";
        if (this.getStyle(sty.setName("font-style"))) {
            s = sty.getStringValue();
            if ("normal".equals(s)) {
                this.fontStyle = 0;
            } else if ("italic".equals(s)) {
                this.fontStyle = 1;
            } else if ("oblique".equals(s)) {
                this.fontStyle = 2;
            }
        } else {
            this.fontStyle = 0;
        }
        if (this.getStyle(sty.setName("font-weight"))) {
            s = sty.getStringValue();
            if ("normal".equals(s)) {
                this.fontWeight = 0;
            } else if ("bold".equals(s)) {
                this.fontWeight = 1;
            }
        } else {
            this.fontWeight = 0;
        }
        this.textAnchor = this.getStyle(sty.setName("text-anchor")) ? ((s = sty.getStringValue()).equals("middle") ? 1 : (s.equals("end") ? 2 : 0)) : 0;
        this.buildText();
    }

    protected void buildText() throws SVGException {
        String[] families = this.fontFamily.split(",");
        Font font = null;
        for (int i = 0; i < families.length && (font = this.diagram.getUniverse().getFont(families[i])) == null; ++i) {
        }
        if (font == null) {
            font = FontSystem.createFont(this.fontFamily, this.fontStyle, this.fontWeight, this.fontSize);
        }
        if (font == null) {
            Logger.getLogger(Text.class.getName()).log(Level.WARNING, "Could not create font " + this.fontFamily);
            font = FontSystem.createFont("Serif", this.fontStyle, this.fontWeight, this.fontSize);
        }
        GeneralPath textPath = new GeneralPath();
        this.textShape = textPath;
        float cursorX = this.x;
        float cursorY = this.y;
        AffineTransform xform = new AffineTransform();
        for (Serializable obj : this.content) {
            if (obj instanceof String) {
                String text = (String)((Object)obj);
                if (text != null) {
                    text = text.trim();
                }
                for (int i = 0; i < text.length(); ++i) {
                    xform.setToIdentity();
                    xform.setToTranslation(cursorX, cursorY);
                    String unicode2 = text.substring(i, i + 1);
                    MissingGlyph glyph = font.getGlyph(unicode2);
                    Shape path = glyph.getPath();
                    if (path != null) {
                        path = xform.createTransformedShape(path);
                        textPath.append(path, false);
                    }
                    cursorX += glyph.getHorizAdvX();
                }
                this.strokeWidthScalar = 1.0f;
                continue;
            }
            if (!(obj instanceof Tspan)) continue;
            Tspan tspan = (Tspan)obj;
            Point2D.Float cursor = new Point2D.Float(cursorX, cursorY);
            tspan.appendToShape(textPath, cursor);
            cursorX = (float)((Point2D)cursor).getX();
            cursorY = (float)((Point2D)cursor).getY();
        }
        switch (this.textAnchor) {
            case 1: {
                AffineTransform at = new AffineTransform();
                at.translate(-textPath.getBounds().getWidth() / 2.0, 0.0);
                textPath.transform(at);
                break;
            }
            case 2: {
                AffineTransform at = new AffineTransform();
                at.translate(-textPath.getBounds().getWidth(), 0.0);
                textPath.transform(at);
                break;
            }
        }
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        this.beginLayer(g);
        this.renderShape(g, this.textShape);
        this.finishLayer(g);
    }

    @Override
    public Shape getShape() {
        return this.shapeToParent(this.textShape);
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        return this.boundsToParent(this.includeStrokeInBounds(this.textShape.getBounds2D()));
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        int newVal;
        float newVal2;
        boolean changeState = super.updateTime(curTime);
        StyleAttribute sty = new StyleAttribute();
        boolean shapeChange = false;
        if (this.getPres(sty.setName("x")) && (newVal2 = sty.getFloatValueWithUnits()) != this.x) {
            this.x = newVal2;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("y")) && (newVal2 = sty.getFloatValueWithUnits()) != this.y) {
            this.y = newVal2;
            shapeChange = true;
        }
        this.textLength = this.getStyle(sty.setName("textLength")) ? sty.getFloatValueWithUnits() : -1.0f;
        this.lengthAdjust = this.getStyle(sty.setName("lengthAdjust")) ? sty.getStringValue() : "spacing";
        if (this.getPres(sty.setName("font-family")) && !(newVal = sty.getStringValue()).equals(this.fontFamily)) {
            this.fontFamily = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("font-size")) && (newVal = sty.getFloatValueWithUnits()) != this.fontSize) {
            this.fontSize = newVal;
            shapeChange = true;
        }
        if (this.getStyle(sty.setName("font-style"))) {
            String s = sty.getStringValue();
            newVal = this.fontStyle;
            if ("normal".equals(s)) {
                newVal = 0;
            } else if ("italic".equals(s)) {
                newVal = 1;
            } else if ("oblique".equals(s)) {
                newVal = 2;
            }
            if (newVal != this.fontStyle) {
                this.fontStyle = newVal;
                shapeChange = true;
            }
        }
        if (this.getStyle(sty.setName("font-weight"))) {
            String s = sty.getStringValue();
            newVal = this.fontWeight;
            if ("normal".equals(s)) {
                newVal = 0;
            } else if ("bold".equals(s)) {
                newVal = 1;
            }
            if (newVal != this.fontWeight) {
                this.fontWeight = newVal;
                shapeChange = true;
            }
        }
        if (shapeChange) {
            this.build();
        }
        return changeState || shapeChange;
    }
}

