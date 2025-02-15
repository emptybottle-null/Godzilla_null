/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import org.apache.poi.hssf.usermodel.FontDetails;
import org.apache.poi.hssf.usermodel.HSSFChildAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPolygon;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFShapeGroup;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFTextbox;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.StaticFontMetrics;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.SuppressForbidden;

public class EscherGraphics
extends Graphics {
    private final HSSFShapeGroup escherGroup;
    private final HSSFWorkbook workbook;
    private float verticalPointsPerPixel = 1.0f;
    private final float verticalPixelsPerPoint;
    private Color foreground;
    private Color background = Color.white;
    private Font font;
    private static final POILogger logger = POILogFactory.getLogger(EscherGraphics.class);

    public EscherGraphics(HSSFShapeGroup escherGroup, HSSFWorkbook workbook, Color forecolor, float verticalPointsPerPixel) {
        this.escherGroup = escherGroup;
        this.workbook = workbook;
        this.verticalPointsPerPixel = verticalPointsPerPixel;
        this.verticalPixelsPerPoint = 1.0f / verticalPointsPerPixel;
        this.font = new Font("Arial", 0, 10);
        this.foreground = forecolor;
    }

    EscherGraphics(HSSFShapeGroup escherGroup, HSSFWorkbook workbook, Color foreground, Font font, float verticalPointsPerPixel) {
        this.escherGroup = escherGroup;
        this.workbook = workbook;
        this.foreground = foreground;
        this.font = font;
        this.verticalPointsPerPixel = verticalPointsPerPixel;
        this.verticalPixelsPerPoint = 1.0f / verticalPointsPerPixel;
    }

    @Override
    @NotImplemented
    public void clearRect(int x, int y, int width, int height) {
        Color color = this.foreground;
        this.setColor(this.background);
        this.fillRect(x, y, width, height);
        this.setColor(color);
    }

    @Override
    @NotImplemented
    public void clipRect(int x, int y, int width, int height) {
        if (logger.check(5)) {
            logger.log(5, "clipRect not supported");
        }
    }

    @Override
    @NotImplemented
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        if (logger.check(5)) {
            logger.log(5, "copyArea not supported");
        }
    }

    @Override
    public Graphics create() {
        return new EscherGraphics(this.escherGroup, this.workbook, this.foreground, this.font, this.verticalPointsPerPixel);
    }

    @Override
    public void dispose() {
    }

    @Override
    @NotImplemented
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        if (logger.check(5)) {
            logger.log(5, "drawArc not supported");
        }
    }

    @Override
    @NotImplemented
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
        if (logger.check(5)) {
            logger.log(5, "drawImage not supported");
        }
        return true;
    }

    @Override
    @NotImplemented
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
        if (logger.check(5)) {
            logger.log(5, "drawImage not supported");
        }
        return true;
    }

    @Override
    public boolean drawImage(Image image, int i, int j, int k, int l, Color color, ImageObserver imageobserver) {
        return this.drawImage(image, i, j, i + k, j + l, 0, 0, image.getWidth(imageobserver), image.getHeight(imageobserver), color, imageobserver);
    }

    @Override
    public boolean drawImage(Image image, int i, int j, int k, int l, ImageObserver imageobserver) {
        return this.drawImage(image, i, j, i + k, j + l, 0, 0, image.getWidth(imageobserver), image.getHeight(imageobserver), imageobserver);
    }

    @Override
    public boolean drawImage(Image image, int i, int j, Color color, ImageObserver imageobserver) {
        return this.drawImage(image, i, j, image.getWidth(imageobserver), image.getHeight(imageobserver), color, imageobserver);
    }

    @Override
    public boolean drawImage(Image image, int i, int j, ImageObserver imageobserver) {
        return this.drawImage(image, i, j, image.getWidth(imageobserver), image.getHeight(imageobserver), imageobserver);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        this.drawLine(x1, y1, x2, y2, 0);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int width) {
        HSSFSimpleShape shape = this.escherGroup.createShape(new HSSFChildAnchor(x1, y1, x2, y2));
        shape.setShapeType(20);
        shape.setLineWidth(width);
        shape.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        HSSFSimpleShape shape = this.escherGroup.createShape(new HSSFChildAnchor(x, y, x + width, y + height));
        shape.setShapeType(3);
        shape.setLineWidth(0);
        shape.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        shape.setNoFill(true);
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        int right = this.findBiggest(xPoints);
        int bottom = this.findBiggest(yPoints);
        int left = this.findSmallest(xPoints);
        int top = this.findSmallest(yPoints);
        HSSFPolygon shape = this.escherGroup.createPolygon(new HSSFChildAnchor(left, top, right, bottom));
        shape.setPolygonDrawArea(right - left, bottom - top);
        shape.setPoints(this.addToAll(xPoints, -left), this.addToAll(yPoints, -top));
        shape.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        shape.setLineWidth(0);
        shape.setNoFill(true);
    }

    private int[] addToAll(int[] values, int amount) {
        int[] result = new int[values.length];
        for (int i = 0; i < values.length; ++i) {
            result[i] = values[i] + amount;
        }
        return result;
    }

    @Override
    @NotImplemented
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        if (logger.check(5)) {
            logger.log(5, "drawPolyline not supported");
        }
    }

    @Override
    @NotImplemented
    public void drawRect(int x, int y, int width, int height) {
        if (logger.check(5)) {
            logger.log(5, "drawRect not supported");
        }
    }

    @Override
    @NotImplemented
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        if (logger.check(5)) {
            logger.log(5, "drawRoundRect not supported");
        }
    }

    @Override
    public void drawString(String str, int x, int y) {
        if (str == null || str.isEmpty()) {
            return;
        }
        Font excelFont = this.font;
        excelFont = this.font.getName().equals("SansSerif") ? new Font("Arial", this.font.getStyle(), (int)((float)this.font.getSize() / this.verticalPixelsPerPoint)) : new Font(this.font.getName(), this.font.getStyle(), (int)((float)this.font.getSize() / this.verticalPixelsPerPoint));
        FontDetails d = StaticFontMetrics.getFontDetails(excelFont);
        int width = d.getStringWidth(str) * 8 + 12;
        int height = (int)((float)this.font.getSize() / this.verticalPixelsPerPoint + 6.0f) * 2;
        y = (int)((float)y - ((float)this.font.getSize() / this.verticalPixelsPerPoint + 2.0f * this.verticalPixelsPerPoint));
        HSSFTextbox textbox = this.escherGroup.createTextbox(new HSSFChildAnchor(x, y, x + width, y + height));
        textbox.setNoFill(true);
        textbox.setLineStyle(-1);
        HSSFRichTextString s = new HSSFRichTextString(str);
        HSSFFont hssfFont = this.matchFont(excelFont);
        s.applyFont(hssfFont);
        textbox.setString(s);
    }

    private HSSFFont matchFont(Font matchFont) {
        HSSFColor hssfColor = this.workbook.getCustomPalette().findColor((byte)this.foreground.getRed(), (byte)this.foreground.getGreen(), (byte)this.foreground.getBlue());
        if (hssfColor == null) {
            hssfColor = this.workbook.getCustomPalette().findSimilarColor((byte)this.foreground.getRed(), (byte)this.foreground.getGreen(), (byte)this.foreground.getBlue());
        }
        boolean bold = (matchFont.getStyle() & 1) != 0;
        boolean italic = (matchFont.getStyle() & 2) != 0;
        HSSFFont hssfFont = this.workbook.findFont(bold, hssfColor.getIndex(), (short)(matchFont.getSize() * 20), matchFont.getName(), italic, false, (short)0, (byte)0);
        if (hssfFont == null) {
            hssfFont = this.workbook.createFont();
            hssfFont.setBold(bold);
            hssfFont.setColor(hssfColor.getIndex());
            hssfFont.setFontHeight((short)(matchFont.getSize() * 20));
            hssfFont.setFontName(matchFont.getName());
            hssfFont.setItalic(italic);
            hssfFont.setStrikeout(false);
            hssfFont.setTypeOffset((short)0);
            hssfFont.setUnderline((byte)0);
        }
        return hssfFont;
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        if (logger.check(5)) {
            logger.log(5, "drawString not supported");
        }
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        if (logger.check(5)) {
            logger.log(5, "fillArc not supported");
        }
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        HSSFSimpleShape shape = this.escherGroup.createShape(new HSSFChildAnchor(x, y, x + width, y + height));
        shape.setShapeType(3);
        shape.setLineStyle(-1);
        shape.setFillColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        shape.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        shape.setNoFill(false);
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        int right = this.findBiggest(xPoints);
        int bottom = this.findBiggest(yPoints);
        int left = this.findSmallest(xPoints);
        int top = this.findSmallest(yPoints);
        HSSFPolygon shape = this.escherGroup.createPolygon(new HSSFChildAnchor(left, top, right, bottom));
        shape.setPolygonDrawArea(right - left, bottom - top);
        shape.setPoints(this.addToAll(xPoints, -left), this.addToAll(yPoints, -top));
        shape.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        shape.setFillColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
    }

    private int findBiggest(int[] values) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; ++i) {
            if (values[i] <= result) continue;
            result = values[i];
        }
        return result;
    }

    private int findSmallest(int[] values) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < values.length; ++i) {
            if (values[i] >= result) continue;
            result = values[i];
        }
        return result;
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        HSSFSimpleShape shape = this.escherGroup.createShape(new HSSFChildAnchor(x, y, x + width, y + height));
        shape.setShapeType(1);
        shape.setLineStyle(-1);
        shape.setFillColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        shape.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        if (logger.check(5)) {
            logger.log(5, "fillRoundRect not supported");
        }
    }

    @Override
    public Shape getClip() {
        return this.getClipBounds();
    }

    @Override
    public Rectangle getClipBounds() {
        return null;
    }

    @Override
    public Color getColor() {
        return this.foreground;
    }

    @Override
    public Font getFont() {
        return this.font;
    }

    @Override
    @SuppressForbidden
    public FontMetrics getFontMetrics(Font f) {
        return Toolkit.getDefaultToolkit().getFontMetrics(f);
    }

    @Override
    public void setClip(int x, int y, int width, int height) {
        this.setClip(new Rectangle(x, y, width, height));
    }

    @Override
    @NotImplemented
    public void setClip(Shape shape) {
    }

    @Override
    public void setColor(Color color) {
        this.foreground = color;
    }

    @Override
    public void setFont(Font f) {
        this.font = f;
    }

    @Override
    @NotImplemented
    public void setPaintMode() {
        if (logger.check(5)) {
            logger.log(5, "setPaintMode not supported");
        }
    }

    @Override
    @NotImplemented
    public void setXORMode(Color color) {
        if (logger.check(5)) {
            logger.log(5, "setXORMode not supported");
        }
    }

    @Override
    @NotImplemented
    public void translate(int x, int y) {
        if (logger.check(5)) {
            logger.log(5, "translate not supported");
        }
    }

    public Color getBackground() {
        return this.background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    HSSFShapeGroup getEscherGraphics() {
        return this.escherGroup;
    }
}

