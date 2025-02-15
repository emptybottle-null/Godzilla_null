/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawShape;
import org.apache.poi.sl.draw.DrawSimpleShape;
import org.apache.poi.sl.draw.DrawTextParagraph;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;

public class DrawTextShape
extends DrawSimpleShape {
    public DrawTextShape(TextShape<?, ?> shape) {
        super((SimpleShape<?, ?>)shape);
    }

    @Override
    public void drawContent(Graphics2D graphics) {
        Double textRot;
        SimpleShape s = this.getShape();
        Rectangle2D anchor = DrawShape.getAnchor(graphics, s);
        if (anchor == null) {
            return;
        }
        Insets2D insets = s.getInsets();
        double x = anchor.getX() + insets.left;
        double y = anchor.getY();
        AffineTransform tx = graphics.getTransform();
        boolean vertFlip = s.getFlipVertical();
        boolean horzFlip = s.getFlipHorizontal();
        ShapeContainer sc = s.getParent();
        while (sc instanceof PlaceableShape) {
            PlaceableShape ps = (PlaceableShape)((Object)sc);
            vertFlip ^= ps.getFlipVertical();
            horzFlip ^= ps.getFlipHorizontal();
            sc = ps.getParent();
        }
        if (horzFlip ^ vertFlip) {
            double ax = anchor.getX();
            double ay = anchor.getY();
            graphics.translate(ax + anchor.getWidth(), ay);
            graphics.scale(-1.0, 1.0);
            graphics.translate(-ax, -ay);
        }
        if ((textRot = s.getTextRotation()) != null && textRot != 0.0) {
            double cx = anchor.getCenterX();
            double cy = anchor.getCenterY();
            graphics.translate(cx, cy);
            graphics.rotate(Math.toRadians(textRot));
            graphics.translate(-cx, -cy);
        }
        switch (s.getVerticalAlignment()) {
            default: {
                y += insets.top;
                break;
            }
            case BOTTOM: {
                double textHeight = this.getTextHeight(graphics);
                y += anchor.getHeight() - textHeight - insets.bottom;
                break;
            }
            case MIDDLE: {
                double textHeight = this.getTextHeight(graphics);
                double delta = anchor.getHeight() - textHeight - insets.top - insets.bottom;
                y += insets.top + delta / 2.0;
            }
        }
        TextShape.TextDirection textDir = s.getTextDirection();
        if (textDir == TextShape.TextDirection.VERTICAL || textDir == TextShape.TextDirection.VERTICAL_270) {
            double deg = textDir == TextShape.TextDirection.VERTICAL ? 90.0 : 270.0;
            double cx = anchor.getCenterX();
            double cy = anchor.getCenterY();
            graphics.translate(cx, cy);
            graphics.rotate(Math.toRadians(deg));
            graphics.translate(-cx, -cy);
            double w = anchor.getWidth();
            double h = anchor.getHeight();
            double dx = (w - h) / 2.0;
            graphics.translate(dx, -dx);
        }
        this.drawParagraphs(graphics, x, y);
        graphics.setTransform(tx);
    }

    public double drawParagraphs(Graphics2D graphics, double x, double y) {
        DrawFactory fact = DrawFactory.getInstance(graphics);
        double y0 = y;
        Iterator paragraphs = this.getShape().iterator();
        boolean isFirstLine = true;
        int autoNbrIdx = 0;
        while (paragraphs.hasNext()) {
            TextParagraph p = (TextParagraph)paragraphs.next();
            DrawTextParagraph dp = fact.getDrawable(p);
            TextParagraph.BulletStyle bs = p.getBulletStyle();
            if (bs == null || bs.getAutoNumberingScheme() == null) {
                autoNbrIdx = -1;
            } else {
                Integer startAt = bs.getAutoNumberingStartAt();
                if (startAt == null) {
                    startAt = 1;
                }
                if (startAt > autoNbrIdx) {
                    autoNbrIdx = startAt;
                }
            }
            dp.setAutoNumberingIdx(autoNbrIdx);
            dp.breakText(graphics);
            if (isFirstLine) {
                y += (double)dp.getFirstLineLeading();
            } else {
                Double spaceBefore = p.getSpaceBefore();
                if (spaceBefore == null) {
                    spaceBefore = 0.0;
                }
                y = spaceBefore > 0.0 ? (y += spaceBefore * 0.01 * (double)dp.getFirstLineHeight()) : (y += -spaceBefore.doubleValue());
            }
            isFirstLine = false;
            dp.setPosition(x, y);
            dp.draw(graphics);
            y += dp.getY();
            if (paragraphs.hasNext()) {
                Double spaceAfter = p.getSpaceAfter();
                if (spaceAfter == null) {
                    spaceAfter = 0.0;
                }
                y = spaceAfter > 0.0 ? (y += spaceAfter * 0.01 * (double)dp.getLastLineHeight()) : (y += -spaceAfter.doubleValue());
            }
            ++autoNbrIdx;
        }
        return y - y0;
    }

    public double getTextHeight() {
        return this.getTextHeight(null);
    }

    public double getTextHeight(Graphics2D oldGraphics) {
        BufferedImage img = new BufferedImage(1, 1, 1);
        Graphics2D graphics = img.createGraphics();
        if (oldGraphics != null) {
            graphics.addRenderingHints(oldGraphics.getRenderingHints());
            graphics.setTransform(oldGraphics.getTransform());
        }
        return this.drawParagraphs(graphics, 0.0, 0.0);
    }

    @Override
    protected TextShape<?, ? extends TextParagraph<?, ?, ? extends TextRun>> getShape() {
        return (TextShape)this.shape;
    }
}

