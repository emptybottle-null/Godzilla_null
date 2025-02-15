/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.draw.DrawShape;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.sl.draw.geom.IAdjustableShape;
import org.apache.poi.sl.draw.geom.Outline;
import org.apache.poi.sl.draw.geom.Path;
import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Shadow;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.util.Units;

public class DrawSimpleShape
extends DrawShape {
    private static final double DECO_SIZE_POW = 1.5;

    public DrawSimpleShape(SimpleShape<?, ?> shape) {
        super(shape);
    }

    @Override
    public void draw(Graphics2D graphics) {
        if (DrawSimpleShape.getAnchor(graphics, this.getShape()) == null) {
            return;
        }
        Paint oldPaint = graphics.getPaint();
        Stroke oldStroke = graphics.getStroke();
        Color oldColor = graphics.getColor();
        Paint fill = this.getFillPaint(graphics);
        Paint line = this.getLinePaint(graphics);
        BasicStroke stroke = this.getStroke();
        graphics.setStroke(stroke);
        Collection<Outline> elems = this.computeOutlines(graphics);
        this.drawShadow(graphics, elems, fill, line);
        if (fill != null) {
            Path2D.Double area = new Path2D.Double();
            graphics.setRenderingHint(Drawable.GRADIENT_SHAPE, area);
            Consumer<PaintStyle.PaintModifier> fun = pm -> this.fillArea(graphics, (PaintStyle.PaintModifier)((Object)pm), area);
            PaintStyle.PaintModifier pm2 = null;
            for (Outline o : elems) {
                Path path = o.getPath();
                if (!path.isFilled()) continue;
                PaintStyle.PaintModifier pmOld = pm2;
                pm2 = path.getFill();
                if (pmOld != null && pmOld != pm2) {
                    fun.accept(pmOld);
                    area.reset();
                    continue;
                }
                area.append(o.getOutline(), false);
            }
            if (area.getCurrentPoint() != null) {
                fun.accept(pm2);
            }
        }
        this.drawContent(graphics);
        if (line != null) {
            graphics.setPaint(line);
            graphics.setStroke(stroke);
            for (Outline o : elems) {
                if (!o.getPath().isStroked()) continue;
                Shape s = o.getOutline();
                graphics.setRenderingHint(Drawable.GRADIENT_SHAPE, s);
                graphics.draw(s);
            }
        }
        this.drawDecoration(graphics, line, stroke);
        graphics.setColor(oldColor);
        graphics.setPaint(oldPaint);
        graphics.setStroke(oldStroke);
    }

    private void fillArea(Graphics2D graphics, PaintStyle.PaintModifier pm, Path2D area) {
        org.apache.poi.sl.usermodel.Shape ss = this.getShape();
        PaintStyle ps = ss.getFillStyle().getPaint();
        DrawPaint drawPaint = DrawFactory.getInstance(graphics).getPaint((PlaceableShape<?, ?>)((Object)ss));
        Paint fillMod = drawPaint.getPaint(graphics, ps, pm);
        if (fillMod != null) {
            graphics.setPaint(fillMod);
            DrawPaint.fillPaintWorkaround(graphics, area);
        }
    }

    protected Paint getFillPaint(Graphics2D graphics) {
        PaintStyle ps = this.getShape().getFillStyle().getPaint();
        DrawPaint drawPaint = DrawFactory.getInstance(graphics).getPaint((PlaceableShape<?, ?>)((Object)this.getShape()));
        return drawPaint.getPaint(graphics, ps);
    }

    protected Paint getLinePaint(Graphics2D graphics) {
        PaintStyle ps = this.getShape().getFillStyle().getPaint();
        DrawPaint drawPaint = DrawFactory.getInstance(graphics).getPaint((PlaceableShape<?, ?>)((Object)this.getShape()));
        return drawPaint.getPaint(graphics, this.getShape().getStrokeStyle().getPaint());
    }

    protected void drawDecoration(Graphics2D graphics, Paint line, BasicStroke stroke) {
        Outline tail;
        if (line == null) {
            return;
        }
        graphics.setPaint(line);
        ArrayList<Outline> lst = new ArrayList<Outline>();
        LineDecoration deco = this.getShape().getLineDecoration();
        Outline head = this.getHeadDecoration(graphics, deco, stroke);
        if (head != null) {
            lst.add(head);
        }
        if ((tail = this.getTailDecoration(graphics, deco, stroke)) != null) {
            lst.add(tail);
        }
        for (Outline o : lst) {
            Shape s = o.getOutline();
            Path p = o.getPath();
            graphics.setRenderingHint(Drawable.GRADIENT_SHAPE, s);
            if (p.isFilled()) {
                graphics.fill(s);
            }
            if (!p.isStroked()) continue;
            graphics.draw(s);
        }
    }

    protected Outline getTailDecoration(Graphics2D graphics, LineDecoration deco, BasicStroke stroke) {
        LineDecoration.DecorationSize tailWidth;
        if (deco == null || stroke == null) {
            return null;
        }
        LineDecoration.DecorationSize tailLength = deco.getTailLength();
        if (tailLength == null) {
            tailLength = LineDecoration.DecorationSize.MEDIUM;
        }
        if ((tailWidth = deco.getTailWidth()) == null) {
            tailWidth = LineDecoration.DecorationSize.MEDIUM;
        }
        double lineWidth = Math.max(2.5, (double)stroke.getLineWidth());
        Rectangle2D anchor = DrawSimpleShape.getAnchor(graphics, this.getShape());
        double x2 = anchor.getX() + anchor.getWidth();
        double y2 = anchor.getY() + anchor.getHeight();
        double alpha = Math.atan(anchor.getHeight() / anchor.getWidth());
        AffineTransform at = new AffineTransform();
        Shape tailShape = null;
        Path p = null;
        double scaleY = Math.pow(1.5, (double)tailWidth.ordinal() + 1.0);
        double scaleX = Math.pow(1.5, (double)tailLength.ordinal() + 1.0);
        LineDecoration.DecorationShape tailShapeEnum = deco.getTailShape();
        if (tailShapeEnum == null) {
            return null;
        }
        switch (tailShapeEnum) {
            case OVAL: {
                p = new Path();
                tailShape = new Ellipse2D.Double(0.0, 0.0, lineWidth * scaleX, lineWidth * scaleY);
                Rectangle2D bounds = tailShape.getBounds2D();
                at.translate(x2 - bounds.getWidth() / 2.0, y2 - bounds.getHeight() / 2.0);
                at.rotate(alpha, bounds.getX() + bounds.getWidth() / 2.0, bounds.getY() + bounds.getHeight() / 2.0);
                break;
            }
            case STEALTH: 
            case ARROW: {
                p = new Path(false, true);
                Path2D.Double arrow = new Path2D.Double();
                arrow.moveTo(-lineWidth * scaleX, -lineWidth * scaleY / 2.0);
                arrow.lineTo(0.0, 0.0);
                arrow.lineTo(-lineWidth * scaleX, lineWidth * scaleY / 2.0);
                tailShape = arrow;
                at.translate(x2, y2);
                at.rotate(alpha);
                break;
            }
            case TRIANGLE: {
                p = new Path();
                Path2D.Double triangle = new Path2D.Double();
                triangle.moveTo(-lineWidth * scaleX, -lineWidth * scaleY / 2.0);
                triangle.lineTo(0.0, 0.0);
                triangle.lineTo(-lineWidth * scaleX, lineWidth * scaleY / 2.0);
                triangle.closePath();
                tailShape = triangle;
                at.translate(x2, y2);
                at.rotate(alpha);
                break;
            }
        }
        if (tailShape != null) {
            tailShape = at.createTransformedShape(tailShape);
        }
        return tailShape == null ? null : new Outline(tailShape, p);
    }

    protected Outline getHeadDecoration(Graphics2D graphics, LineDecoration deco, BasicStroke stroke) {
        LineDecoration.DecorationSize headWidth;
        if (deco == null || stroke == null) {
            return null;
        }
        LineDecoration.DecorationSize headLength = deco.getHeadLength();
        if (headLength == null) {
            headLength = LineDecoration.DecorationSize.MEDIUM;
        }
        if ((headWidth = deco.getHeadWidth()) == null) {
            headWidth = LineDecoration.DecorationSize.MEDIUM;
        }
        double lineWidth = Math.max(2.5, (double)stroke.getLineWidth());
        Rectangle2D anchor = DrawSimpleShape.getAnchor(graphics, this.getShape());
        double x1 = anchor.getX();
        double y1 = anchor.getY();
        double alpha = Math.atan(anchor.getHeight() / anchor.getWidth());
        AffineTransform at = new AffineTransform();
        Shape headShape = null;
        Path p = null;
        double scaleY = Math.pow(1.5, (double)headWidth.ordinal() + 1.0);
        double scaleX = Math.pow(1.5, (double)headLength.ordinal() + 1.0);
        LineDecoration.DecorationShape headShapeEnum = deco.getHeadShape();
        if (headShapeEnum == null) {
            return null;
        }
        switch (headShapeEnum) {
            case OVAL: {
                p = new Path();
                headShape = new Ellipse2D.Double(0.0, 0.0, lineWidth * scaleX, lineWidth * scaleY);
                Rectangle2D bounds = headShape.getBounds2D();
                at.translate(x1 - bounds.getWidth() / 2.0, y1 - bounds.getHeight() / 2.0);
                at.rotate(alpha, bounds.getX() + bounds.getWidth() / 2.0, bounds.getY() + bounds.getHeight() / 2.0);
                break;
            }
            case STEALTH: 
            case ARROW: {
                p = new Path(false, true);
                Path2D.Double arrow = new Path2D.Double();
                arrow.moveTo(lineWidth * scaleX, -lineWidth * scaleY / 2.0);
                arrow.lineTo(0.0, 0.0);
                arrow.lineTo(lineWidth * scaleX, lineWidth * scaleY / 2.0);
                headShape = arrow;
                at.translate(x1, y1);
                at.rotate(alpha);
                break;
            }
            case TRIANGLE: {
                p = new Path();
                Path2D.Double triangle = new Path2D.Double();
                triangle.moveTo(lineWidth * scaleX, -lineWidth * scaleY / 2.0);
                triangle.lineTo(0.0, 0.0);
                triangle.lineTo(lineWidth * scaleX, lineWidth * scaleY / 2.0);
                triangle.closePath();
                headShape = triangle;
                at.translate(x1, y1);
                at.rotate(alpha);
                break;
            }
        }
        if (headShape != null) {
            headShape = at.createTransformedShape(headShape);
        }
        return headShape == null ? null : new Outline(headShape, p);
    }

    public BasicStroke getStroke() {
        return DrawSimpleShape.getStroke(this.getShape().getStrokeStyle());
    }

    protected void drawShadow(Graphics2D graphics, Collection<Outline> outlines, Paint fill, Paint line) {
        Shadow shadow = this.getShape().getShadow();
        if (shadow == null || fill == null && line == null) {
            return;
        }
        PaintStyle.SolidPaint shadowPaint = shadow.getFillStyle();
        Color shadowColor = DrawPaint.applyColorTransform(shadowPaint.getSolidColor());
        double shapeRotation = this.getShape().getRotation();
        if (this.getShape().getFlipVertical()) {
            shapeRotation += 180.0;
        }
        double angle = shadow.getAngle() - shapeRotation;
        double dist = shadow.getDistance();
        double dx = dist * Math.cos(Math.toRadians(angle));
        double dy = dist * Math.sin(Math.toRadians(angle));
        graphics.translate(dx, dy);
        for (Outline o : outlines) {
            Shape s = o.getOutline();
            Path p = o.getPath();
            graphics.setRenderingHint(Drawable.GRADIENT_SHAPE, s);
            graphics.setPaint(shadowColor);
            if (fill != null && p.isFilled()) {
                DrawPaint.fillPaintWorkaround(graphics, s);
                continue;
            }
            if (line == null || !p.isStroked()) continue;
            graphics.draw(s);
        }
        graphics.translate(-dx, -dy);
    }

    protected Collection<Outline> computeOutlines(Graphics2D graphics) {
        org.apache.poi.sl.usermodel.Shape sh = this.getShape();
        ArrayList<Outline> lst = new ArrayList<Outline>();
        CustomGeometry geom = sh.getGeometry();
        if (geom == null) {
            return lst;
        }
        Rectangle2D anchor = DrawSimpleShape.getAnchor(graphics, sh);
        if (anchor == null) {
            return lst;
        }
        for (Path p : geom) {
            double scaleY;
            double scaleX;
            double w = p.getW();
            double h = p.getH();
            if (w == -1.0) {
                w = Units.toEMU(anchor.getWidth());
                scaleX = Units.toPoints(1L);
            } else {
                scaleX = anchor.getWidth() == 0.0 ? 1.0 : anchor.getWidth() / w;
            }
            if (h == -1.0) {
                h = Units.toEMU(anchor.getHeight());
                scaleY = Units.toPoints(1L);
            } else {
                scaleY = anchor.getHeight() == 0.0 ? 1.0 : anchor.getHeight() / h;
            }
            Rectangle2D.Double pathAnchor = new Rectangle2D.Double(0.0, 0.0, w, h);
            Context ctx = new Context(geom, pathAnchor, (IAdjustableShape)((Object)sh));
            Path2D.Double gp = p.getPath(ctx);
            AffineTransform at = new AffineTransform();
            at.translate(anchor.getX(), anchor.getY());
            at.scale(scaleX, scaleY);
            Shape canvasShape = at.createTransformedShape(gp);
            lst.add(new Outline(canvasShape, p));
        }
        return lst;
    }

    protected SimpleShape<?, ?> getShape() {
        return (SimpleShape)this.shape;
    }
}

