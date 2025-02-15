/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import org.apache.poi.hssf.usermodel.EscherGraphics;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public final class EscherGraphics2d
extends Graphics2D {
    private EscherGraphics _escherGraphics;
    private BufferedImage _img;
    private AffineTransform _trans;
    private Stroke _stroke;
    private Paint _paint;
    private Shape _deviceclip;
    private POILogger logger = POILogFactory.getLogger(this.getClass());

    public EscherGraphics2d(EscherGraphics escherGraphics) {
        this._escherGraphics = escherGraphics;
        this.setImg(new BufferedImage(1, 1, 2));
        this.setColor(Color.black);
    }

    @Override
    public void addRenderingHints(Map<?, ?> map) {
        this.getG2D().addRenderingHints(map);
    }

    @Override
    public void clearRect(int i, int j, int k, int l) {
        Paint paint1 = this.getPaint();
        this.setColor(this.getBackground());
        this.fillRect(i, j, k, l);
        this.setPaint(paint1);
    }

    @Override
    public void clip(Shape shape) {
        if (this.getDeviceclip() != null) {
            Area area = new Area(this.getClip());
            if (shape != null) {
                area.intersect(new Area(shape));
            }
            shape = area;
        }
        this.setClip(shape);
    }

    @Override
    public void clipRect(int x, int y, int width, int height) {
        this.clip(new Rectangle(x, y, width, height));
    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        this.getG2D().copyArea(x, y, width, height, dx, dy);
    }

    @Override
    public Graphics create() {
        return new EscherGraphics2d(this._escherGraphics);
    }

    @Override
    public void dispose() {
        this.getEscherGraphics().dispose();
        this.getG2D().dispose();
        this.getImg().flush();
    }

    @Override
    public void draw(Shape shape) {
        if (shape instanceof Line2D) {
            Line2D shape2d = (Line2D)shape;
            int width = 0;
            if (this._stroke != null && this._stroke instanceof BasicStroke) {
                width = (int)((BasicStroke)this._stroke).getLineWidth() * 12700;
            }
            this.drawLine((int)shape2d.getX1(), (int)shape2d.getY1(), (int)shape2d.getX2(), (int)shape2d.getY2(), width);
        } else if (this.logger.check(5)) {
            this.logger.log(5, "draw not fully supported");
        }
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        this.draw(new Arc2D.Float(x, y, width, height, startAngle, arcAngle, 0));
    }

    @Override
    public void drawGlyphVector(GlyphVector g, float x, float y) {
        this.fill(g.getOutline(x, y));
    }

    @Override
    public boolean drawImage(Image image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgColor, ImageObserver imageobserver) {
        if (this.logger.check(5)) {
            this.logger.log(5, "drawImage() not supported");
        }
        return true;
    }

    @Override
    public boolean drawImage(Image image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver imageobserver) {
        if (this.logger.check(5)) {
            this.logger.log(5, "drawImage() not supported");
        }
        return this.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null, imageobserver);
    }

    @Override
    public boolean drawImage(Image image, int dx1, int dy1, int dx2, int dy2, Color bgColor, ImageObserver imageobserver) {
        if (this.logger.check(5)) {
            this.logger.log(5, "drawImage() not supported");
        }
        return true;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        return this.drawImage(img, x, y, width, height, null, observer);
    }

    @Override
    public boolean drawImage(Image image, int x, int y, Color bgColor, ImageObserver imageobserver) {
        return this.drawImage(image, x, y, image.getWidth(imageobserver), image.getHeight(imageobserver), bgColor, imageobserver);
    }

    @Override
    public boolean drawImage(Image image, int x, int y, ImageObserver imageobserver) {
        return this.drawImage(image, x, y, image.getWidth(imageobserver), image.getHeight(imageobserver), imageobserver);
    }

    @Override
    public boolean drawImage(Image image, AffineTransform affinetransform, ImageObserver imageobserver) {
        AffineTransform affinetransform1 = (AffineTransform)this.getTrans().clone();
        this.getTrans().concatenate(affinetransform);
        this.drawImage(image, 0, 0, imageobserver);
        this.setTrans(affinetransform1);
        return true;
    }

    @Override
    public void drawImage(BufferedImage bufferedimage, BufferedImageOp op, int x, int y) {
        BufferedImage img = op.filter(bufferedimage, null);
        this.drawImage(img, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, x, y), null);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int width) {
        this.getEscherGraphics().drawLine(x1, y1, x2, y2, width);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int width = 0;
        if (this._stroke != null && this._stroke instanceof BasicStroke) {
            width = (int)((BasicStroke)this._stroke).getLineWidth() * 12700;
        }
        this.getEscherGraphics().drawLine(x1, y1, x2, y2, width);
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        this.getEscherGraphics().drawOval(x, y, width, height);
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        this.getEscherGraphics().drawPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        if (nPoints > 0) {
            GeneralPath generalpath = new GeneralPath();
            generalpath.moveTo(xPoints[0], yPoints[0]);
            for (int j = 1; j < nPoints; ++j) {
                generalpath.lineTo(xPoints[j], yPoints[j]);
            }
            this.draw(generalpath);
        }
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        this._escherGraphics.drawRect(x, y, width, height);
    }

    @Override
    public void drawRenderableImage(RenderableImage renderableimage, AffineTransform affinetransform) {
        this.drawRenderedImage(renderableimage.createDefaultRendering(), affinetransform);
    }

    @Override
    public void drawRenderedImage(RenderedImage renderedimage, AffineTransform affinetransform) {
        BufferedImage bufferedimage = new BufferedImage(renderedimage.getColorModel(), renderedimage.getData().createCompatibleWritableRaster(), false, null);
        bufferedimage.setData(renderedimage.getData());
        this.drawImage(bufferedimage, affinetransform, null);
    }

    @Override
    public void drawRoundRect(int i, int j, int k, int l, int i1, int j1) {
        this.draw(new RoundRectangle2D.Float(i, j, k, l, i1, j1));
    }

    @Override
    public void drawString(String string, float x, float y) {
        this.getEscherGraphics().drawString(string, (int)x, (int)y);
    }

    @Override
    public void drawString(String string, int x, int y) {
        this.getEscherGraphics().drawString(string, x, y);
    }

    @Override
    public void drawString(AttributedCharacterIterator attributedcharacteriterator, float x, float y) {
        TextLayout textlayout = new TextLayout(attributedcharacteriterator, this.getFontRenderContext());
        Paint paint1 = this.getPaint();
        this.setColor(this.getColor());
        this.fill(textlayout.getOutline(AffineTransform.getTranslateInstance(x, y)));
        this.setPaint(paint1);
    }

    @Override
    public void drawString(AttributedCharacterIterator attributedcharacteriterator, int x, int y) {
        this.getEscherGraphics().drawString(attributedcharacteriterator, x, y);
    }

    @Override
    public void fill(Shape shape) {
        if (this.logger.check(5)) {
            this.logger.log(5, "fill(Shape) not supported");
        }
    }

    @Override
    public void fillArc(int i, int j, int k, int l, int i1, int j1) {
        this.fill(new Arc2D.Float(i, j, k, l, i1, j1, 2));
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        this._escherGraphics.fillOval(x, y, width, height);
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        this._escherGraphics.fillPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        this.getEscherGraphics().fillRect(x, y, width, height);
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        this.fill(new RoundRectangle2D.Float(x, y, width, height, arcWidth, arcHeight));
    }

    @Override
    public Color getBackground() {
        return this.getEscherGraphics().getBackground();
    }

    @Override
    public Shape getClip() {
        try {
            return this.getTrans().createInverse().createTransformedShape(this.getDeviceclip());
        } catch (Exception _ex) {
            return null;
        }
    }

    @Override
    public Rectangle getClipBounds() {
        if (this.getDeviceclip() != null) {
            Shape clip = this.getClip();
            return clip != null ? clip.getBounds() : null;
        }
        return null;
    }

    @Override
    public Color getColor() {
        return this._escherGraphics.getColor();
    }

    @Override
    public Composite getComposite() {
        return this.getG2D().getComposite();
    }

    @Override
    public GraphicsConfiguration getDeviceConfiguration() {
        return this.getG2D().getDeviceConfiguration();
    }

    @Override
    public Font getFont() {
        return this.getEscherGraphics().getFont();
    }

    @Override
    public FontMetrics getFontMetrics(Font font) {
        return this.getEscherGraphics().getFontMetrics(font);
    }

    @Override
    public FontRenderContext getFontRenderContext() {
        this.getG2D().setTransform(this.getTrans());
        return this.getG2D().getFontRenderContext();
    }

    @Override
    public Paint getPaint() {
        return this._paint;
    }

    @Override
    public Object getRenderingHint(RenderingHints.Key key) {
        return this.getG2D().getRenderingHint(key);
    }

    @Override
    public RenderingHints getRenderingHints() {
        return this.getG2D().getRenderingHints();
    }

    @Override
    public Stroke getStroke() {
        return this._stroke;
    }

    @Override
    public AffineTransform getTransform() {
        return (AffineTransform)this.getTrans().clone();
    }

    @Override
    public boolean hit(Rectangle rectangle, Shape shape, boolean flag) {
        this.getG2D().setTransform(this.getTrans());
        this.getG2D().setStroke(this.getStroke());
        this.getG2D().setClip(this.getClip());
        return this.getG2D().hit(rectangle, shape, flag);
    }

    @Override
    public void rotate(double d) {
        this.getTrans().rotate(d);
    }

    @Override
    public void rotate(double d, double d1, double d2) {
        this.getTrans().rotate(d, d1, d2);
    }

    @Override
    public void scale(double d, double d1) {
        this.getTrans().scale(d, d1);
    }

    @Override
    public void setBackground(Color c) {
        this.getEscherGraphics().setBackground(c);
    }

    @Override
    public void setClip(int i, int j, int k, int l) {
        this.setClip(new Rectangle(i, j, k, l));
    }

    @Override
    public void setClip(Shape shape) {
        this.setDeviceclip(this.getTrans().createTransformedShape(shape));
    }

    @Override
    public void setColor(Color c) {
        this._escherGraphics.setColor(c);
    }

    @Override
    public void setComposite(Composite composite) {
        this.getG2D().setComposite(composite);
    }

    @Override
    public void setFont(Font font) {
        this.getEscherGraphics().setFont(font);
    }

    @Override
    public void setPaint(Paint paint1) {
        if (paint1 != null) {
            this._paint = paint1;
            if (paint1 instanceof Color) {
                this.setColor((Color)paint1);
            }
        }
    }

    @Override
    public void setPaintMode() {
        this.getEscherGraphics().setPaintMode();
    }

    @Override
    public void setRenderingHint(RenderingHints.Key key, Object obj) {
        this.getG2D().setRenderingHint(key, obj);
    }

    @Override
    public void setRenderingHints(Map<?, ?> map) {
        this.getG2D().setRenderingHints(map);
    }

    @Override
    public void setStroke(Stroke s) {
        this._stroke = s;
    }

    @Override
    public void setTransform(AffineTransform affinetransform) {
        this.setTrans((AffineTransform)affinetransform.clone());
    }

    @Override
    public void setXORMode(Color color1) {
        this.getEscherGraphics().setXORMode(color1);
    }

    @Override
    public void shear(double d, double d1) {
        this.getTrans().shear(d, d1);
    }

    @Override
    public void transform(AffineTransform affinetransform) {
        this.getTrans().concatenate(affinetransform);
    }

    @Override
    public void translate(double d, double d1) {
        this.getTrans().translate(d, d1);
    }

    @Override
    public void translate(int i, int j) {
        this.getTrans().translate(i, j);
    }

    private EscherGraphics getEscherGraphics() {
        return this._escherGraphics;
    }

    private BufferedImage getImg() {
        return this._img;
    }

    private void setImg(BufferedImage img) {
        this._img = img;
    }

    private Graphics2D getG2D() {
        return (Graphics2D)this._img.getGraphics();
    }

    private AffineTransform getTrans() {
        return this._trans;
    }

    private void setTrans(AffineTransform trans) {
        this._trans = trans;
    }

    private Shape getDeviceclip() {
        return this._deviceclip;
    }

    private void setDeviceclip(Shape deviceclip) {
        this._deviceclip = deviceclip;
    }
}

