/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras;

import com.formdev.flatlaf.FlatIconColors;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.GrayFilter;
import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.UIScale;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class FlatSVGIcon
extends ImageIcon
implements FlatLaf.DisabledIconProvider {
    private static final SVGUniverse svgUniverse = new SVGUniverse();
    private final String name;
    private final int width;
    private final int height;
    private final float scale;
    private final boolean disabled;
    private final ClassLoader classLoader;
    private SVGDiagram diagram;
    private boolean dark;
    private static Boolean darkLaf;

    public FlatSVGIcon(String name) {
        this(name, -1, -1, 1.0f, false, null);
    }

    public FlatSVGIcon(String name, ClassLoader classLoader) {
        this(name, -1, -1, 1.0f, false, classLoader);
    }

    public FlatSVGIcon(String name, int width, int height) {
        this(name, width, height, 1.0f, false, null);
    }

    public FlatSVGIcon(String name, int width, int height, ClassLoader classLoader) {
        this(name, width, height, 1.0f, false, classLoader);
    }

    public FlatSVGIcon(String name, float scale) {
        this(name, -1, -1, scale, false, null);
    }

    public FlatSVGIcon(String name, float scale, ClassLoader classLoader) {
        this(name, -1, -1, scale, false, classLoader);
    }

    private FlatSVGIcon(String name, int width, int height, float scale, boolean disabled, ClassLoader classLoader) {
        this.name = name;
        this.classLoader = classLoader;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.disabled = disabled;
    }

    public FlatSVGIcon derive(int width, int height) {
        if (width == this.width && height == this.height) {
            return this;
        }
        FlatSVGIcon icon = new FlatSVGIcon(this.name, width, height, this.scale, false, this.classLoader);
        icon.diagram = this.diagram;
        icon.dark = this.dark;
        return icon;
    }

    public FlatSVGIcon derive(float scale) {
        if (scale == this.scale) {
            return this;
        }
        FlatSVGIcon icon = new FlatSVGIcon(this.name, this.width, this.height, scale, false, this.classLoader);
        icon.diagram = this.diagram;
        icon.dark = this.dark;
        return icon;
    }

    @Override
    public Icon getDisabledIcon() {
        if (this.disabled) {
            return this;
        }
        FlatSVGIcon icon = new FlatSVGIcon(this.name, this.width, this.height, this.scale, true, this.classLoader);
        icon.diagram = this.diagram;
        icon.dark = this.dark;
        return icon;
    }

    private void update() {
        if (this.dark == FlatSVGIcon.isDarkLaf() && this.diagram != null) {
            return;
        }
        this.dark = FlatSVGIcon.isDarkLaf();
        URL url = this.getIconURL(this.name, this.dark);
        if (url == null & this.dark) {
            url = this.getIconURL(this.name, false);
        }
        try {
            this.diagram = svgUniverse.getDiagram(url.toURI());
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    private URL getIconURL(String name, boolean dark) {
        if (dark) {
            int dotIndex = name.lastIndexOf(46);
            name = name.substring(0, dotIndex) + "_dark" + name.substring(dotIndex);
        }
        ClassLoader cl = this.classLoader != null ? this.classLoader : FlatSVGIcon.class.getClassLoader();
        return cl.getResource(name);
    }

    public boolean hasFound() {
        this.update();
        return this.diagram != null;
    }

    @Override
    public int getIconWidth() {
        if (this.width > 0) {
            return this.scaleSize(this.width);
        }
        this.update();
        return this.scaleSize(this.diagram != null ? Math.round(this.diagram.getWidth()) : 16);
    }

    @Override
    public int getIconHeight() {
        if (this.height > 0) {
            return this.scaleSize(this.height);
        }
        this.update();
        return this.scaleSize(this.diagram != null ? Math.round(this.diagram.getHeight()) : 16);
    }

    private int scaleSize(int size) {
        int scaledSize = UIScale.scale(size);
        if (this.scale != 1.0f) {
            scaledSize = Math.round((float)scaledSize * this.scale);
        }
        return scaledSize;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        this.update();
        Rectangle clipBounds = g.getClipBounds();
        if (clipBounds != null && !clipBounds.intersects(new Rectangle(x, y, this.getIconWidth(), this.getIconHeight()))) {
            return;
        }
        RGBImageFilter grayFilter = null;
        if (this.disabled) {
            Object grayFilterObj = UIManager.get("Component.grayFilter");
            grayFilter = grayFilterObj instanceof RGBImageFilter ? (RGBImageFilter)grayFilterObj : GrayFilter.createDisabledIconFilter(this.dark);
        }
        GraphicsFilter g2 = new GraphicsFilter((Graphics2D)g.create(), ColorFilter.getInstance(), grayFilter);
        try {
            FlatUIUtils.setRenderingHints(g2);
            ((Graphics2D)g2).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            this.paintSvg(g2, x, y);
        } finally {
            ((Graphics)g2).dispose();
        }
    }

    private void paintSvg(Graphics2D g, int x, int y) {
        if (this.diagram == null) {
            this.paintSvgError(g, x, y);
            return;
        }
        g.translate(x, y);
        g.clipRect(0, 0, this.getIconWidth(), this.getIconHeight());
        UIScale.scaleGraphics(g);
        if (this.width > 0 || this.height > 0) {
            double sy;
            double sx = this.width > 0 ? (double)((float)this.width / this.diagram.getWidth()) : 1.0;
            double d = sy = this.height > 0 ? (double)((float)this.height / this.diagram.getHeight()) : 1.0;
            if (sx != 1.0 || sy != 1.0) {
                g.scale(sx, sy);
            }
        }
        if (this.scale != 1.0f) {
            g.scale(this.scale, this.scale);
        }
        this.diagram.setIgnoringClipHeuristic(true);
        try {
            this.diagram.render(g);
        } catch (SVGException ex) {
            this.paintSvgError(g, 0, 0);
        }
    }

    private void paintSvgError(Graphics2D g, int x, int y) {
        g.setColor(Color.red);
        g.fillRect(x, y, this.getIconWidth(), this.getIconHeight());
    }

    @Override
    public Image getImage() {
        this.update();
        int iconWidth = this.getIconWidth();
        int iconHeight = this.getIconHeight();
        Dimension[] dimensions = new Dimension[]{new Dimension(iconWidth, iconHeight), new Dimension(iconWidth * 2, iconHeight * 2)};
        Function<Dimension, Image> producer = size -> {
            BufferedImage image = new BufferedImage(size.width, size.height, 2);
            Graphics2D g = image.createGraphics();
            try {
                double sy;
                double sx = size.width > 0 ? (double)((float)size.width / (float)iconWidth) : 1.0;
                double d = sy = size.height > 0 ? (double)((float)size.height / (float)iconHeight) : 1.0;
                if (sx != 1.0 || sy != 1.0) {
                    g.scale(sx, sy);
                }
                this.paintIcon(null, g, 0, 0);
            } finally {
                g.dispose();
            }
            return image;
        };
        return MultiResolutionImageSupport.create(0, dimensions, producer);
    }

    private static boolean isDarkLaf() {
        if (darkLaf == null) {
            FlatSVGIcon.lafChanged();
            UIManager.addPropertyChangeListener(e -> FlatSVGIcon.lafChanged());
        }
        return darkLaf;
    }

    private static void lafChanged() {
        darkLaf = FlatLaf.isLafDark();
    }

    private static class GraphicsFilter
    extends Graphics2DProxy {
        private final ColorFilter colorFilter;
        private final RGBImageFilter grayFilter;

        public GraphicsFilter(Graphics2D delegate, ColorFilter colorFilter, RGBImageFilter grayFilter) {
            super(delegate);
            this.colorFilter = colorFilter;
            this.grayFilter = grayFilter;
        }

        @Override
        public void setColor(Color c) {
            super.setColor(this.filterColor(c));
        }

        @Override
        public void setPaint(Paint paint) {
            if (paint instanceof Color) {
                paint = this.filterColor((Color)paint);
            }
            super.setPaint(paint);
        }

        private Color filterColor(Color color) {
            if (this.colorFilter != null) {
                color = this.colorFilter.filter(color);
            }
            if (this.grayFilter != null) {
                int oldRGB = color.getRGB();
                int newRGB = this.grayFilter.filterRGB(0, 0, oldRGB);
                color = newRGB != oldRGB ? new Color(newRGB, true) : color;
            }
            return color;
        }
    }

    public static class ColorFilter {
        private static ColorFilter instance;
        private final Map<Integer, String> rgb2keyMap = new HashMap<Integer, String>();
        private final Map<Color, Color> color2colorMap = new HashMap<Color, Color>();

        public static ColorFilter getInstance() {
            if (instance == null) {
                instance = new ColorFilter();
            }
            return instance;
        }

        public ColorFilter() {
            for (FlatIconColors c : FlatIconColors.values()) {
                this.rgb2keyMap.put(c.rgb, c.key);
            }
        }

        public void addAll(Map<Color, Color> from2toMap) {
            this.color2colorMap.putAll(from2toMap);
        }

        public void add(Color from, Color to) {
            this.color2colorMap.put(from, to);
        }

        public void remove(Color from) {
            this.color2colorMap.remove(from);
        }

        public Color filter(Color color) {
            Color newColor = this.color2colorMap.get(color);
            if (newColor != null) {
                return newColor;
            }
            String colorKey = this.rgb2keyMap.get(color.getRGB() & 0xFFFFFF);
            if (colorKey == null) {
                return color;
            }
            newColor = UIManager.getColor(colorKey);
            if (newColor == null) {
                return color;
            }
            return newColor.getAlpha() != color.getAlpha() ? new Color(newColor.getRGB() & 0xFFFFFF | color.getRGB() & 0xFF000000) : newColor;
        }
    }
}

