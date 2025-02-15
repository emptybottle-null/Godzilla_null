/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import org.apache.poi.sl.draw.BitmapImageRenderer;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.DrawShape;
import org.apache.poi.sl.draw.DrawTexturePaint;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.draw.ImageRenderer;
import org.apache.poi.sl.draw.PathGradientPaint;
import org.apache.poi.sl.draw.geom.ArcToCommand;
import org.apache.poi.sl.usermodel.AbstractColorStyle;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class DrawPaint {
    private static final POILogger LOG = POILogFactory.getLogger(DrawPaint.class);
    private static final Color TRANSPARENT = new Color(1.0f, 1.0f, 1.0f, 0.0f);
    protected PlaceableShape<?, ?> shape;

    public DrawPaint(PlaceableShape<?, ?> shape) {
        this.shape = shape;
    }

    public static PaintStyle.SolidPaint createSolidPaint(Color color) {
        return color == null ? null : new SimpleSolidPaint(color);
    }

    public static PaintStyle.SolidPaint createSolidPaint(ColorStyle color) {
        return color == null ? null : new SimpleSolidPaint(color);
    }

    public Paint getPaint(Graphics2D graphics, PaintStyle paint) {
        return this.getPaint(graphics, paint, PaintStyle.PaintModifier.NORM);
    }

    public Paint getPaint(Graphics2D graphics, PaintStyle paint, PaintStyle.PaintModifier modifier) {
        if (modifier == PaintStyle.PaintModifier.NONE) {
            return TRANSPARENT;
        }
        if (paint instanceof PaintStyle.SolidPaint) {
            return this.getSolidPaint((PaintStyle.SolidPaint)paint, graphics, modifier);
        }
        if (paint instanceof PaintStyle.GradientPaint) {
            return this.getGradientPaint((PaintStyle.GradientPaint)paint, graphics);
        }
        if (paint instanceof PaintStyle.TexturePaint) {
            return this.getTexturePaint((PaintStyle.TexturePaint)paint, graphics);
        }
        return TRANSPARENT;
    }

    protected Paint getSolidPaint(PaintStyle.SolidPaint fill, Graphics2D graphics, final PaintStyle.PaintModifier modifier) {
        final ColorStyle orig = fill.getSolidColor();
        AbstractColorStyle cs = new AbstractColorStyle(){

            @Override
            public Color getColor() {
                return orig.getColor();
            }

            @Override
            public int getAlpha() {
                return orig.getAlpha();
            }

            @Override
            public int getHueOff() {
                return orig.getHueOff();
            }

            @Override
            public int getHueMod() {
                return orig.getHueMod();
            }

            @Override
            public int getSatOff() {
                return orig.getSatOff();
            }

            @Override
            public int getSatMod() {
                return orig.getSatMod();
            }

            @Override
            public int getLumOff() {
                return orig.getLumOff();
            }

            @Override
            public int getLumMod() {
                return orig.getLumMod();
            }

            @Override
            public int getShade() {
                return this.scale(orig.getShade(), PaintStyle.PaintModifier.DARKEN_LESS, PaintStyle.PaintModifier.DARKEN);
            }

            @Override
            public int getTint() {
                return this.scale(orig.getTint(), PaintStyle.PaintModifier.LIGHTEN_LESS, PaintStyle.PaintModifier.LIGHTEN);
            }

            private int scale(int value, PaintStyle.PaintModifier lessModifier, PaintStyle.PaintModifier moreModifier) {
                if (value == -1) {
                    return -1;
                }
                int delta = modifier == lessModifier ? 20000 : (modifier == moreModifier ? 40000 : 0);
                return Math.min(100000, Math.max(0, value) + delta);
            }
        };
        return DrawPaint.applyColorTransform(cs);
    }

    protected Paint getGradientPaint(PaintStyle.GradientPaint fill, Graphics2D graphics) {
        switch (fill.getGradientType()) {
            case linear: {
                return this.createLinearGradientPaint(fill, graphics);
            }
            case rectangular: 
            case circular: {
                return this.createRadialGradientPaint(fill, graphics);
            }
            case shape: {
                return this.createPathGradientPaint(fill, graphics);
            }
        }
        throw new UnsupportedOperationException("gradient fill of type " + fill + " not supported.");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected Paint getTexturePaint(PaintStyle.TexturePaint fill, Graphics2D graphics) {
        assert (graphics != null);
        String contentType = fill.getContentType();
        if (contentType == null) return TRANSPARENT;
        if (contentType.isEmpty()) {
            return TRANSPARENT;
        }
        ImageRenderer renderer = DrawPictureShape.getImageRenderer(graphics, contentType);
        Rectangle2D textAnchor = this.shape.getAnchor();
        try (InputStream is = fill.getImageData();){
            BufferedImage image;
            if (is == null) {
                Color color = TRANSPARENT;
                return color;
            }
            renderer.loadImage(is, contentType);
            int alpha = fill.getAlpha();
            if (0 <= alpha && alpha < 100000) {
                renderer.setAlpha((float)alpha / 100000.0f);
            }
            Dimension2D imgDim = renderer.getDimension();
            if ("image/x-wmf".contains(contentType)) {
                imgDim = new Dimension2DDouble(textAnchor.getWidth(), textAnchor.getHeight());
            }
            if ((image = renderer.getImage(imgDim)) == null) {
                LOG.log(7, "Can't load image data");
                Color color = TRANSPARENT;
                return color;
            }
            double flipX = 1.0;
            double flipY = 1.0;
            PaintStyle.FlipMode flip = fill.getFlipMode();
            if (flip != null && flip != PaintStyle.FlipMode.NONE) {
                int width = image.getWidth();
                int height = image.getHeight();
                switch (flip) {
                    case X: {
                        flipX = 2.0;
                        break;
                    }
                    case Y: {
                        flipY = 2.0;
                        break;
                    }
                    case XY: {
                        flipX = 2.0;
                        flipY = 2.0;
                        break;
                    }
                }
                BufferedImage img = new BufferedImage((int)((double)width * flipX), (int)((double)height * flipY), 2);
                Graphics2D g = img.createGraphics();
                g.drawImage((Image)image, 0, 0, null);
                switch (flip) {
                    case X: {
                        g.drawImage(image, 2 * width, 0, -width, height, null);
                        break;
                    }
                    case Y: {
                        g.drawImage(image, 0, 2 * height, width, -height, null);
                        break;
                    }
                    case XY: {
                        g.drawImage(image, 2 * width, 0, -width, height, null);
                        g.drawImage(image, 0, 2 * height, width, -height, null);
                        g.drawImage(image, 2 * width, 2 * height, -width, -height, null);
                        break;
                    }
                }
                g.dispose();
                image = img;
            }
            image = DrawPaint.colorizePattern(fill, image);
            Shape s = (Shape)graphics.getRenderingHint(Drawable.GRADIENT_SHAPE);
            DrawTexturePaint drawTexturePaint = new DrawTexturePaint(image, s, fill, flipX, flipY, renderer instanceof BitmapImageRenderer);
            return drawTexturePaint;
        } catch (IOException e) {
            LOG.log(7, "Can't load image data - using transparent color", e);
            return TRANSPARENT;
        }
    }

    private static BufferedImage colorizePattern(PaintStyle.TexturePaint fill, BufferedImage pattern) {
        List<ColorStyle> duoTone = fill.getDuoTone();
        if (duoTone == null || duoTone.size() != 2) {
            return pattern;
        }
        int redBits = pattern.getSampleModel().getSampleSize(0);
        int blendBits = Math.max(Math.min(redBits, 8), 1);
        int blendShades = 1 << blendBits;
        double blendRatio = (double)blendShades / (double)(1 << Math.max(redBits, 1));
        int[] gradSample = DrawPaint.linearBlendedColors(duoTone, blendShades);
        IndexColorModel icm = new IndexColorModel(blendBits, blendShades, gradSample, 0, true, -1, 0);
        BufferedImage patIdx = new BufferedImage(pattern.getWidth(), pattern.getHeight(), 13, icm);
        WritableRaster rasterRGBA = pattern.getRaster();
        WritableRaster rasterIdx = patIdx.getRaster();
        int[] redSample = new int[pattern.getWidth()];
        for (int y = 0; y < pattern.getHeight(); ++y) {
            rasterRGBA.getSamples(0, y, redSample.length, 1, 0, redSample);
            DrawPaint.scaleShades(redSample, blendRatio);
            rasterIdx.setSamples(0, y, redSample.length, 1, 0, redSample);
        }
        return patIdx;
    }

    private static void scaleShades(int[] samples, double ratio) {
        if (ratio != 1.0) {
            for (int x = 0; x < samples.length; ++x) {
                samples[x] = (int)Math.rint((double)samples[x] * ratio);
            }
        }
    }

    private static int[] linearBlendedColors(List<ColorStyle> duoTone, int blendShades) {
        Color[] colors = (Color[])duoTone.stream().map(DrawPaint::applyColorTransform).toArray(Color[]::new);
        float[] fractions = new float[]{0.0f, 1.0f};
        BufferedImage gradBI = new BufferedImage(blendShades, 1, 2);
        Graphics2D gradG = gradBI.createGraphics();
        gradG.setPaint(new LinearGradientPaint(0.0f, 0.0f, blendShades, 0.0f, fractions, colors));
        gradG.fillRect(0, 0, blendShades, 1);
        gradG.dispose();
        return gradBI.getRGB(0, 0, blendShades, 1, null, 0, blendShades);
    }

    public static Color applyColorTransform(ColorStyle color) {
        if (color == null || color.getColor() == null) {
            return TRANSPARENT;
        }
        Color result = color.getColor();
        double alpha = DrawPaint.getAlpha(result, color);
        double[] hsl = DrawPaint.RGB2HSL(result);
        DrawPaint.applyHslModOff(hsl, 0, color.getHueMod(), color.getHueOff());
        DrawPaint.applyHslModOff(hsl, 1, color.getSatMod(), color.getSatOff());
        DrawPaint.applyHslModOff(hsl, 2, color.getLumMod(), color.getLumOff());
        DrawPaint.applyShade(hsl, color);
        DrawPaint.applyTint(hsl, color);
        result = DrawPaint.HSL2RGB(hsl[0], hsl[1], hsl[2], alpha);
        return result;
    }

    private static double getAlpha(Color c, ColorStyle fc) {
        double alpha = (double)c.getAlpha() / 255.0;
        int fcAlpha = fc.getAlpha();
        if (fcAlpha != -1) {
            alpha *= (double)fcAlpha / 100000.0;
        }
        return Math.min(1.0, Math.max(0.0, alpha));
    }

    private static void applyHslModOff(double[] hsl, int hslPart, int mod, int off) {
        if (mod != -1) {
            int n = hslPart;
            hsl[n] = hsl[n] * ((double)mod / 100000.0);
        }
        if (off != -1) {
            int n = hslPart;
            hsl[n] = hsl[n] + (double)off / 1000.0;
        }
    }

    private static void applyShade(double[] hsl, ColorStyle fc) {
        int shade = fc.getShade();
        if (shade == -1) {
            return;
        }
        double shadePct = (double)shade / 100000.0;
        hsl[2] = hsl[2] * shadePct;
    }

    private static void applyTint(double[] hsl, ColorStyle fc) {
        int tint = fc.getTint();
        if (tint == -1 || tint == 0) {
            return;
        }
        double tintPct = (double)tint / 100000.0;
        hsl[2] = tintPct < 0.0 ? hsl[2] * (1.0 + tintPct) : hsl[2] * (1.0 - tintPct) + (100.0 - 100.0 * (1.0 - tintPct));
    }

    protected Paint createLinearGradientPaint(PaintStyle.GradientPaint fill, Graphics2D graphics) {
        Point2D p2;
        Rectangle2D anchor;
        double angle = fill.getGradientAngle();
        if (!fill.isRotatedWithShape()) {
            angle -= this.shape.getRotation();
        }
        if ((anchor = DrawShape.getAnchor(graphics, this.shape)) == null) {
            return TRANSPARENT;
        }
        angle = ArcToCommand.convertOoxml2AwtAngle(-angle, anchor.getWidth(), anchor.getHeight());
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), anchor.getCenterX(), anchor.getCenterY());
        double diagonal = Math.sqrt(Math.pow(anchor.getWidth(), 2.0) + Math.pow(anchor.getHeight(), 2.0));
        Point2D p1 = at.transform(new Point2D.Double(anchor.getCenterX() - diagonal / 2.0, anchor.getCenterY()), null);
        return p1.equals(p2 = at.transform(new Point2D.Double(anchor.getMaxX(), anchor.getCenterY()), null)) || fill.getGradientFractions().length < 2 ? null : this.safeFractions((f, c) -> new LinearGradientPaint(p1, p2, (float[])f, (Color[])c), fill);
    }

    protected Paint createRadialGradientPaint(PaintStyle.GradientPaint fill, Graphics2D graphics) {
        Rectangle2D anchor = DrawShape.getAnchor(graphics, this.shape);
        if (anchor == null) {
            return TRANSPARENT;
        }
        Insets2D insets = fill.getFillToInsets();
        if (insets == null) {
            insets = new Insets2D(0.0, 0.0, 0.0, 0.0);
        }
        Point2D.Double pCenter = new Point2D.Double(anchor.getCenterX(), anchor.getCenterY());
        Point2D.Double pFocus = new Point2D.Double(DrawPaint.getCenterVal(anchor.getMinX(), anchor.getMaxX(), insets.left, insets.right), DrawPaint.getCenterVal(anchor.getMinY(), anchor.getMaxY(), insets.top, insets.bottom));
        float radius = (float)Math.max(anchor.getWidth(), anchor.getHeight());
        AffineTransform at = new AffineTransform();
        at.translate(((Point2D)pFocus).getX(), ((Point2D)pFocus).getY());
        at.scale(DrawPaint.getScale(anchor.getMinX(), anchor.getMaxX(), insets.left, insets.right), DrawPaint.getScale(anchor.getMinY(), anchor.getMaxY(), insets.top, insets.bottom));
        at.translate(-((Point2D)pFocus).getX(), -((Point2D)pFocus).getY());
        return this.safeFractions((f, c) -> new RadialGradientPaint(pCenter, radius, pFocus, (float[])f, (Color[])c, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, at), fill);
    }

    private static double getScale(double absMin, double absMax, double relMin, double relMax) {
        double absDelta = absMax - absMin;
        double absStart = absMin + absDelta * relMin;
        double absStop = relMin + relMax <= 1.0 ? absMax - absDelta * relMax : absMax + absDelta * relMax;
        return absDelta == 0.0 ? 1.0 : (absStop - absStart) / absDelta;
    }

    private static double getCenterVal(double absMin, double absMax, double relMin, double relMax) {
        double absDelta = absMax - absMin;
        double absStart = absMin + absDelta * relMin;
        double absStop = relMin + relMax <= 1.0 ? absMax - absDelta * relMax : absMax + absDelta * relMax;
        return absStart + (absStop - absStart) / 2.0;
    }

    protected Paint createPathGradientPaint(PaintStyle.GradientPaint fill, Graphics2D graphics) {
        return this.safeFractions(PathGradientPaint::new, fill);
    }

    private Paint safeFractions(BiFunction<float[], Color[], Paint> init, PaintStyle.GradientPaint fill) {
        Iterator styles = Stream.of(fill.getGradientColors()).map(s -> s == null ? TRANSPARENT : DrawPaint.applyColorTransform(s)).iterator();
        TreeMap m = new TreeMap();
        for (float fraction : fill.getGradientFractions()) {
            m.put(Float.valueOf(fraction), styles.next());
        }
        return init.apply(DrawPaint.toArray(m.keySet()), m.values().toArray(new Color[0]));
    }

    private static float[] toArray(Collection<Float> floatList) {
        int[] idx = new int[]{0};
        float[] ret = new float[floatList.size()];
        floatList.forEach(f -> {
            int n = idx[0];
            idx[0] = n + 1;
            ret[n] = f.floatValue();
        });
        return ret;
    }

    public static Color HSL2RGB(double h, double s, double l, double alpha) {
        s = Math.max(0.0, Math.min(100.0, s));
        l = Math.max(0.0, Math.min(100.0, l));
        if (alpha < 0.0 || alpha > 1.0) {
            String message = "Color parameter outside of expected range - Alpha: " + alpha;
            throw new IllegalArgumentException(message);
        }
        h %= 360.0;
        double q = (l /= 100.0) < 0.5 ? l * (1.0 + s) : l + (s /= 100.0) - s * l;
        double p = 2.0 * l - q;
        double r = Math.max(0.0, DrawPaint.HUE2RGB(p, q, (h /= 360.0) + 0.3333333333333333));
        double g = Math.max(0.0, DrawPaint.HUE2RGB(p, q, h));
        double b = Math.max(0.0, DrawPaint.HUE2RGB(p, q, h - 0.3333333333333333));
        r = Math.min(r, 1.0);
        g = Math.min(g, 1.0);
        b = Math.min(b, 1.0);
        return new Color((float)r, (float)g, (float)b, (float)alpha);
    }

    private static double HUE2RGB(double p, double q, double h) {
        if (h < 0.0) {
            h += 1.0;
        }
        if (h > 1.0) {
            h -= 1.0;
        }
        if (6.0 * h < 1.0) {
            return p + (q - p) * 6.0 * h;
        }
        if (2.0 * h < 1.0) {
            return q;
        }
        if (3.0 * h < 2.0) {
            return p + (q - p) * 6.0 * (0.6666666666666666 - h);
        }
        return p;
    }

    public static double[] RGB2HSL(Color color) {
        float[] rgb = color.getRGBColorComponents(null);
        double r = rgb[0];
        double g = rgb[1];
        double b = rgb[2];
        double min = Math.min(r, Math.min(g, b));
        double max = Math.max(r, Math.max(g, b));
        double h = 0.0;
        if (max == min) {
            h = 0.0;
        } else if (max == r) {
            h = (60.0 * (g - b) / (max - min) + 360.0) % 360.0;
        } else if (max == g) {
            h = 60.0 * (b - r) / (max - min) + 120.0;
        } else if (max == b) {
            h = 60.0 * (r - g) / (max - min) + 240.0;
        }
        double l = (max + min) / 2.0;
        double s = max == min ? 0.0 : (l <= 0.5 ? (max - min) / (max + min) : (max - min) / (2.0 - max - min));
        return new double[]{h, s * 100.0, l * 100.0};
    }

    public static int srgb2lin(float sRGB) {
        if ((double)sRGB <= 0.04045) {
            return (int)Math.rint(100000.0 * (double)sRGB / 12.92);
        }
        return (int)Math.rint(100000.0 * Math.pow(((double)sRGB + 0.055) / 1.055, 2.4));
    }

    public static float lin2srgb(int linRGB) {
        if ((double)linRGB <= 0.0031308) {
            return (float)((double)linRGB / 100000.0 * 12.92);
        }
        return (float)(1.055 * Math.pow((double)linRGB / 100000.0, 0.4166666666666667) - 0.055);
    }

    static void fillPaintWorkaround(Graphics2D graphics, Shape shape) {
        try {
            graphics.fill(shape);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOG.log(5, "IBM JDK failed with TexturePaintContext AIOOBE - try adding the following to the VM parameter:\n-Xjit:exclude={sun/java2d/pipe/AlphaPaintPipe.renderPathTile(Ljava/lang/Object;[BIIIIII)V} and search for 'JIT Problem Determination for IBM SDK using -Xjit' (http://www-01.ibm.com/support/docview.wss?uid=swg21294023) for how to add/determine further excludes", e);
        }
    }

    private static class SimpleSolidPaint
    implements PaintStyle.SolidPaint {
        private final ColorStyle solidColor;

        SimpleSolidPaint(final Color color) {
            if (color == null) {
                throw new NullPointerException("Color needs to be specified");
            }
            this.solidColor = new AbstractColorStyle(){

                @Override
                public Color getColor() {
                    return new Color(color.getRed(), color.getGreen(), color.getBlue());
                }

                @Override
                public int getAlpha() {
                    return (int)Math.round((double)color.getAlpha() * 100000.0 / 255.0);
                }

                @Override
                public int getHueOff() {
                    return -1;
                }

                @Override
                public int getHueMod() {
                    return -1;
                }

                @Override
                public int getSatOff() {
                    return -1;
                }

                @Override
                public int getSatMod() {
                    return -1;
                }

                @Override
                public int getLumOff() {
                    return -1;
                }

                @Override
                public int getLumMod() {
                    return -1;
                }

                @Override
                public int getShade() {
                    return -1;
                }

                @Override
                public int getTint() {
                    return -1;
                }
            };
        }

        SimpleSolidPaint(ColorStyle color) {
            if (color == null) {
                throw new NullPointerException("Color needs to be specified");
            }
            this.solidColor = color;
        }

        @Override
        public ColorStyle getSolidColor() {
            return this.solidColor;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof PaintStyle.SolidPaint)) {
                return false;
            }
            return Objects.equals(this.getSolidColor(), ((PaintStyle.SolidPaint)o).getSolidColor());
        }

        public int hashCode() {
            return Objects.hash(this.solidColor);
        }
    }
}

