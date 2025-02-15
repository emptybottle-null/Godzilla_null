/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Locale;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.StrokeStyle;

public class DrawShape
implements Drawable {
    protected final Shape<?, ?> shape;

    public DrawShape(Shape<?, ?> shape) {
        this.shape = shape;
    }

    static boolean isHSLF(Object shape) {
        return shape.getClass().getName().toLowerCase(Locale.ROOT).contains("hslf");
    }

    @Override
    public void applyTransform(Graphics2D graphics) {
        if (!(this.shape instanceof PlaceableShape) || graphics == null) {
            return;
        }
        Rectangle2D anchor = DrawShape.getAnchor(graphics, (PlaceableShape)((Object)this.shape));
        if (anchor == null) {
            return;
        }
        if (DrawShape.isHSLF(this.shape)) {
            this.flipHorizontal(graphics, anchor);
            this.flipVertical(graphics, anchor);
            this.rotate(graphics, anchor);
        } else {
            this.rotate(graphics, anchor);
            this.flipHorizontal(graphics, anchor);
            this.flipVertical(graphics, anchor);
        }
    }

    private void flipHorizontal(Graphics2D graphics, Rectangle2D anchor) {
        assert (this.shape instanceof PlaceableShape && anchor != null);
        if (((PlaceableShape)((Object)this.shape)).getFlipHorizontal()) {
            graphics.translate(anchor.getX() + anchor.getWidth(), anchor.getY());
            graphics.scale(-1.0, 1.0);
            graphics.translate(-anchor.getX(), -anchor.getY());
        }
    }

    private void flipVertical(Graphics2D graphics, Rectangle2D anchor) {
        assert (this.shape instanceof PlaceableShape && anchor != null);
        if (((PlaceableShape)((Object)this.shape)).getFlipVertical()) {
            graphics.translate(anchor.getX(), anchor.getY() + anchor.getHeight());
            graphics.scale(1.0, -1.0);
            graphics.translate(-anchor.getX(), -anchor.getY());
        }
    }

    private void rotate(Graphics2D graphics, Rectangle2D anchor) {
        assert (this.shape instanceof PlaceableShape && anchor != null);
        double rotation = ((PlaceableShape)((Object)this.shape)).getRotation();
        if (rotation != 0.0) {
            graphics.rotate(Math.toRadians(rotation), anchor.getCenterX(), anchor.getCenterY());
        }
    }

    private static double safeScale(double dim1, double dim2) {
        return dim1 == 0.0 || dim2 == 0.0 ? 1.0 : dim1 / dim2;
    }

    @Override
    public void draw(Graphics2D graphics) {
    }

    @Override
    public void drawContent(Graphics2D graphics) {
    }

    public static Rectangle2D getAnchor(Graphics2D graphics, PlaceableShape<?, ?> shape) {
        Rectangle2D normalizedShape;
        double rotation;
        int quadrant;
        AffineTransform tx;
        Rectangle2D shapeAnchor = shape.getAnchor();
        if (shapeAnchor == null) {
            return null;
        }
        boolean isHSLF = DrawShape.isHSLF(shape);
        AffineTransform affineTransform = tx = graphics == null ? null : (AffineTransform)graphics.getRenderingHint(Drawable.GROUP_TRANSFORM);
        if (tx == null) {
            tx = new AffineTransform();
        }
        if ((quadrant = ((int)(rotation = (shape.getRotation() % 360.0 + 360.0) % 360.0) + 45) / 90 % 4) == 1 || quadrant == 3) {
            Rectangle2D anchorO = tx.createTransformedShape(shapeAnchor).getBounds2D();
            double centerX = anchorO.getCenterX();
            double centerY = anchorO.getCenterY();
            AffineTransform txs2 = new AffineTransform();
            if (!isHSLF) {
                txs2.quadrantRotate(1, centerX, centerY);
                txs2.concatenate(tx);
            }
            txs2.quadrantRotate(3, centerX, centerY);
            if (isHSLF) {
                txs2.concatenate(tx);
            }
            Rectangle2D anchorT = txs2.createTransformedShape(shapeAnchor).getBounds2D();
            double scaleX2 = DrawShape.safeScale(anchorO.getWidth(), anchorT.getWidth());
            double scaleY2 = DrawShape.safeScale(anchorO.getHeight(), anchorT.getHeight());
            double centerX2 = shapeAnchor.getCenterX();
            double centerY2 = shapeAnchor.getCenterY();
            AffineTransform txs22 = new AffineTransform();
            txs22.translate(centerX2, centerY2);
            txs22.scale(scaleY2, scaleX2);
            txs22.translate(-centerX2, -centerY2);
            normalizedShape = txs22.createTransformedShape(shapeAnchor).getBounds2D();
        } else {
            normalizedShape = shapeAnchor;
        }
        if (tx.isIdentity()) {
            return normalizedShape;
        }
        java.awt.Shape anc = tx.createTransformedShape(normalizedShape);
        return anc != null ? anc.getBounds2D() : normalizedShape;
    }

    public static Rectangle2D getAnchor(Graphics2D graphics, Rectangle2D anchor) {
        if (graphics == null) {
            return anchor;
        }
        AffineTransform tx = (AffineTransform)graphics.getRenderingHint(Drawable.GROUP_TRANSFORM);
        if (tx != null && !tx.isIdentity() && tx.createTransformedShape(anchor) != null) {
            anchor = tx.createTransformedShape(anchor).getBounds2D();
        }
        return anchor;
    }

    protected Shape<?, ?> getShape() {
        return this.shape;
    }

    protected static BasicStroke getStroke(StrokeStyle strokeStyle) {
        int lineCap;
        StrokeStyle.LineCap lineCapE;
        StrokeStyle.LineDash lineDash;
        float lineWidth = (float)strokeStyle.getLineWidth();
        if (lineWidth == 0.0f) {
            lineWidth = 0.25f;
        }
        if ((lineDash = strokeStyle.getLineDash()) == null) {
            lineDash = StrokeStyle.LineDash.SOLID;
        }
        int[] dashPatI = lineDash.pattern;
        float dash_phase = 0.0f;
        float[] dashPatF = null;
        if (dashPatI != null) {
            dashPatF = new float[dashPatI.length];
            for (int i = 0; i < dashPatI.length; ++i) {
                dashPatF[i] = (float)dashPatI[i] * Math.max(1.0f, lineWidth);
            }
        }
        if ((lineCapE = strokeStyle.getLineCap()) == null) {
            lineCapE = StrokeStyle.LineCap.FLAT;
        }
        switch (lineCapE) {
            case ROUND: {
                lineCap = 1;
                break;
            }
            case SQUARE: {
                lineCap = 2;
                break;
            }
            default: {
                lineCap = 0;
            }
        }
        int lineJoin = 1;
        return new BasicStroke(lineWidth, lineCap, lineJoin, lineWidth, dashPatF, 0.0f);
    }
}

