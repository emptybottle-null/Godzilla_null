/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.FreeformShape;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.SuppressForbidden;

public class SLGraphics
extends Graphics2D
implements Cloneable {
    private static final POILogger LOG = POILogFactory.getLogger(SLGraphics.class);
    private GroupShape<?, ?> _group;
    private AffineTransform _transform;
    private Stroke _stroke;
    private Paint _paint;
    private Font _font;
    private Color _foreground;
    private Color _background;
    private RenderingHints _hints;

    public SLGraphics(GroupShape<?, ?> group) {
        this._group = group;
        this._transform = new AffineTransform();
        this._stroke = new BasicStroke();
        this._paint = Color.black;
        this._font = new Font("Arial", 0, 12);
        this._background = Color.black;
        this._foreground = Color.white;
        this._hints = new RenderingHints(null);
    }

    public GroupShape<?, ?> getShapeGroup() {
        return this._group;
    }

    @Override
    public Font getFont() {
        return this._font;
    }

    @Override
    public void setFont(Font font) {
        this._font = font;
    }

    @Override
    public Color getColor() {
        return this._foreground;
    }

    @Override
    public void setColor(Color c) {
        this.setPaint(c);
    }

    @Override
    public Stroke getStroke() {
        return this._stroke;
    }

    @Override
    public void setStroke(Stroke s) {
        this._stroke = s;
    }

    @Override
    public Paint getPaint() {
        return this._paint;
    }

    @Override
    public void setPaint(Paint paint) {
        if (paint == null) {
            return;
        }
        this._paint = paint;
        if (paint instanceof Color) {
            this._foreground = (Color)paint;
        }
    }

    @Override
    public AffineTransform getTransform() {
        return new AffineTransform(this._transform);
    }

    @Override
    public void setTransform(AffineTransform Tx) {
        this._transform = new AffineTransform(Tx);
    }

    @Override
    public void draw(Shape shape) {
        Path2D.Double path = new Path2D.Double(this._transform.createTransformedShape(shape));
        FreeformShape p = this._group.createFreeform();
        p.setPath(path);
        p.setFillColor(null);
        this.applyStroke(p);
        if (this._paint instanceof Color) {
            p.setStrokeStyle((Color)this._paint);
        }
    }

    @Override
    public void drawString(String s, float x, float y) {
        TextBox txt = this._group.createTextBox();
        TextRun rt = (TextRun)((TextParagraph)txt.getTextParagraphs().get(0)).getTextRuns().get(0);
        rt.setFontSize(Double.valueOf(this._font.getSize()));
        rt.setFontFamily(this._font.getFamily());
        if (this.getColor() != null) {
            rt.setFontColor(DrawPaint.createSolidPaint(this.getColor()));
        }
        if (this._font.isBold()) {
            rt.setBold(true);
        }
        if (this._font.isItalic()) {
            rt.setItalic(true);
        }
        txt.setText(s);
        txt.setInsets(new Insets2D(0.0, 0.0, 0.0, 0.0));
        txt.setWordWrap(false);
        txt.setHorizontalCentered(false);
        txt.setVerticalAlignment(VerticalAlignment.MIDDLE);
        TextLayout layout = new TextLayout(s, this._font, this.getFontRenderContext());
        float ascent = layout.getAscent();
        float width = (float)Math.floor(layout.getAdvance());
        float height = ascent * 2.0f;
        txt.setAnchor(new Rectangle((int)x, (int)(y -= height / 2.0f + ascent / 2.0f), (int)width, (int)height));
    }

    @Override
    public void fill(Shape shape) {
        Path2D.Double path = new Path2D.Double(this._transform.createTransformedShape(shape));
        FreeformShape p = this._group.createFreeform();
        p.setPath(path);
        this.applyPaint(p);
        p.setStrokeStyle(new Object[0]);
    }

    @Override
    public void translate(int x, int y) {
        this._transform.translate(x, y);
    }

    @Override
    @NotImplemented
    public void clip(Shape s) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
    }

    @Override
    @NotImplemented
    public Shape getClip() {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
        return null;
    }

    @Override
    public void scale(double sx, double sy) {
        this._transform.scale(sx, sy);
    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, y, width, height, arcWidth, arcHeight);
        this.draw(rect);
    }

    @Override
    public void drawString(String str, int x, int y) {
        this.drawString(str, (float)x, (float)y);
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        Ellipse2D.Double oval = new Ellipse2D.Double(x, y, width, height);
        this.fill(oval);
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, y, width, height, arcWidth, arcHeight);
        this.fill(rect);
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        Arc2D.Double arc = new Arc2D.Double(x, y, width, height, startAngle, arcAngle, 2);
        this.fill(arc);
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        Arc2D.Double arc = new Arc2D.Double(x, y, width, height, startAngle, arcAngle, 0);
        this.draw(arc);
    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        if (nPoints > 0) {
            GeneralPath path = new GeneralPath();
            path.moveTo(xPoints[0], yPoints[0]);
            for (int i = 1; i < nPoints; ++i) {
                path.lineTo(xPoints[i], yPoints[i]);
            }
            this.draw(path);
        }
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        Ellipse2D.Double oval = new Ellipse2D.Double(x, y, width, height);
        this.draw(oval);
    }

    @Override
    @NotImplemented
    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
        return false;
    }

    @Override
    @NotImplemented
    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
        return false;
    }

    @Override
    @NotImplemented
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
        return false;
    }

    @Override
    @NotImplemented
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
        return false;
    }

    @Override
    @NotImplemented
    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
        return false;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
        this.draw(line);
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        Polygon polygon = new Polygon(xPoints, yPoints, nPoints);
        this.fill(polygon);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        this.fill(rect);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        this.draw(rect);
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        Polygon polygon = new Polygon(xPoints, yPoints, nPoints);
        this.draw(polygon);
    }

    @Override
    public void clipRect(int x, int y, int width, int height) {
        this.clip(new Rectangle(x, y, width, height));
    }

    @Override
    @NotImplemented
    public void setClip(Shape clip) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
    }

    @Override
    public Rectangle getClipBounds() {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
        return null;
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        this.drawString(iterator, (float)x, (float)y);
    }

    @Override
    public void clearRect(int x, int y, int width, int height) {
        Paint paint = this.getPaint();
        this.setColor(this.getBackground());
        this.fillRect(x, y, width, height);
        this.setPaint(paint);
    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
    }

    @Override
    public void setClip(int x, int y, int width, int height) {
        this.setClip(new Rectangle(x, y, width, height));
    }

    @Override
    public void rotate(double theta) {
        this._transform.rotate(theta);
    }

    @Override
    public void rotate(double theta, double x, double y) {
        this._transform.rotate(theta, x, y);
    }

    @Override
    public void shear(double shx, double shy) {
        this._transform.shear(shx, shy);
    }

    @Override
    public FontRenderContext getFontRenderContext() {
        boolean isAntiAliased = RenderingHints.VALUE_TEXT_ANTIALIAS_ON.equals(this.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING));
        boolean usesFractionalMetrics = RenderingHints.VALUE_FRACTIONALMETRICS_ON.equals(this.getRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS));
        return new FontRenderContext(new AffineTransform(), isAntiAliased, usesFractionalMetrics);
    }

    @Override
    public void transform(AffineTransform Tx) {
        this._transform.concatenate(Tx);
    }

    @Override
    public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
        img = op.filter(img, null);
        this.drawImage((Image)img, x, y, null);
    }

    @Override
    public void setBackground(Color color) {
        if (color == null) {
            return;
        }
        this._background = color;
    }

    @Override
    public Color getBackground() {
        return this._background;
    }

    @Override
    @NotImplemented
    public void setComposite(Composite comp) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
    }

    @Override
    @NotImplemented
    public Composite getComposite() {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
        return null;
    }

    @Override
    public Object getRenderingHint(RenderingHints.Key hintKey) {
        return this._hints.get(hintKey);
    }

    @Override
    public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
        this._hints.put(hintKey, hintValue);
    }

    @Override
    public void drawGlyphVector(GlyphVector g, float x, float y) {
        Shape glyphOutline = g.getOutline(x, y);
        this.fill(glyphOutline);
    }

    @Override
    public GraphicsConfiguration getDeviceConfiguration() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }

    @Override
    public void addRenderingHints(Map<?, ?> hints) {
        this._hints.putAll(hints);
    }

    @Override
    public void translate(double tx, double ty) {
        this._transform.translate(tx, ty);
    }

    @Override
    @NotImplemented
    public void drawString(AttributedCharacterIterator iterator, float x, float y) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
    }

    @Override
    public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
        if (onStroke) {
            s = this.getStroke().createStrokedShape(s);
        }
        s = this.getTransform().createTransformedShape(s);
        return s.intersects(rect);
    }

    @Override
    public RenderingHints getRenderingHints() {
        return this._hints;
    }

    @Override
    public void setRenderingHints(Map<?, ?> hints) {
        this._hints = new RenderingHints(null);
        this._hints.putAll(hints);
    }

    @Override
    @NotImplemented
    public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
        return false;
    }

    @Override
    @NotImplemented
    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
        return false;
    }

    @Override
    public Graphics create() {
        try {
            return (Graphics)this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SuppressForbidden
    public FontMetrics getFontMetrics(Font f) {
        return Toolkit.getDefaultToolkit().getFontMetrics(f);
    }

    @Override
    @NotImplemented
    public void setXORMode(Color c1) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
    }

    @Override
    @NotImplemented
    public void setPaintMode() {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
    }

    @Override
    @NotImplemented
    public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
    }

    @Override
    @NotImplemented
    public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
        if (LOG.check(5)) {
            LOG.log(5, "Not implemented");
        }
    }

    protected void applyStroke(SimpleShape<?, ?> shape) {
        if (this._stroke instanceof BasicStroke) {
            BasicStroke bs = (BasicStroke)this._stroke;
            shape.setStrokeStyle(bs.getLineWidth());
            float[] dash = bs.getDashArray();
            if (dash != null) {
                shape.setStrokeStyle(new Object[]{StrokeStyle.LineDash.DASH});
            }
        }
    }

    protected void applyPaint(SimpleShape<?, ?> shape) {
        if (this._paint instanceof Color) {
            shape.setFillColor((Color)this._paint);
        }
    }
}

