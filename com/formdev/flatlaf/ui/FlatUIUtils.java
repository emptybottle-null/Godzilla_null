/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatBorder;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.IdentityHashMap;
import java.util.WeakHashMap;
import java.util.function.Supplier;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;

public class FlatUIUtils {
    public static final boolean MAC_USE_QUARTZ = Boolean.getBoolean("apple.awt.graphics.UseQuartz");
    private static WeakHashMap<LookAndFeel, IdentityHashMap<Object, ComponentUI>> sharedUIinstances = new WeakHashMap();

    public static Rectangle addInsets(Rectangle r, Insets insets) {
        return new Rectangle(r.x - insets.left, r.y - insets.top, r.width + insets.left + insets.right, r.height + insets.top + insets.bottom);
    }

    public static Rectangle subtractInsets(Rectangle r, Insets insets) {
        return new Rectangle(r.x + insets.left, r.y + insets.top, r.width - insets.left - insets.right, r.height - insets.top - insets.bottom);
    }

    public static Dimension addInsets(Dimension dim, Insets insets) {
        return new Dimension(dim.width + insets.left + insets.right, dim.height + insets.top + insets.bottom);
    }

    public static Insets addInsets(Insets insets1, Insets insets2) {
        return new Insets(insets1.top + insets2.top, insets1.left + insets2.left, insets1.bottom + insets2.bottom, insets1.right + insets2.right);
    }

    public static void setInsets(Insets dest, Insets src) {
        dest.top = src.top;
        dest.left = src.left;
        dest.bottom = src.bottom;
        dest.right = src.right;
    }

    public static Color getUIColor(String key, int defaultColorRGB) {
        Color color = UIManager.getColor(key);
        return color != null ? color : new Color(defaultColorRGB);
    }

    public static Color getUIColor(String key, Color defaultColor) {
        Color color = UIManager.getColor(key);
        return color != null ? color : defaultColor;
    }

    public static Color getUIColor(String key, String defaultKey) {
        Color color = UIManager.getColor(key);
        return color != null ? color : UIManager.getColor(defaultKey);
    }

    public static int getUIInt(String key, int defaultValue) {
        Object value = UIManager.get(key);
        return value instanceof Integer ? (Integer)value : defaultValue;
    }

    public static float getUIFloat(String key, float defaultValue) {
        Object value = UIManager.get(key);
        return value instanceof Number ? ((Number)value).floatValue() : defaultValue;
    }

    public static boolean isChevron(String arrowType) {
        return !"triangle".equals(arrowType);
    }

    public static Color nonUIResource(Color c) {
        return c instanceof UIResource ? new Color(c.getRGB(), true) : c;
    }

    public static Font nonUIResource(Font font) {
        return font instanceof UIResource ? font.deriveFont(font.getStyle()) : font;
    }

    public static int minimumWidth(JComponent c, int minimumWidth) {
        return FlatClientProperties.clientPropertyInt(c, "JComponent.minimumWidth", minimumWidth);
    }

    public static int minimumHeight(JComponent c, int minimumHeight) {
        return FlatClientProperties.clientPropertyInt(c, "JComponent.minimumHeight", minimumHeight);
    }

    public static boolean isCellEditor(Component c) {
        Component c2 = c;
        for (int i = 0; i <= 2 && c2 != null; ++i) {
            Container parent = c2.getParent();
            if (parent instanceof JTable && ((JTable)parent).getEditorComponent() == c2) {
                return true;
            }
            c2 = parent;
        }
        String name = c.getName();
        if ("Table.editor".equals(name) || "Tree.cellEditor".equals(name)) {
            return true;
        }
        return c instanceof JComponent && Boolean.TRUE.equals(((JComponent)c).getClientProperty("JComboBox.isTableCellEditor"));
    }

    public static boolean isPermanentFocusOwner(Component c) {
        KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        return keyboardFocusManager.getPermanentFocusOwner() == c && keyboardFocusManager.getActiveWindow() == SwingUtilities.windowForComponent(c);
    }

    public static boolean isFullScreen(Component c) {
        GraphicsConfiguration gc = c.getGraphicsConfiguration();
        GraphicsDevice gd = gc != null ? gc.getDevice() : null;
        Window fullScreenWindow = gd != null ? gd.getFullScreenWindow() : null;
        return fullScreenWindow != null && fullScreenWindow == SwingUtilities.windowForComponent(c);
    }

    public static Boolean isRoundRect(Component c) {
        return c instanceof JComponent ? FlatClientProperties.clientPropertyBooleanStrict((JComponent)c, "JComponent.roundRect", null) : null;
    }

    public static float getBorderFocusWidth(JComponent c) {
        FlatBorder border = FlatUIUtils.getOutsideFlatBorder(c);
        return border != null ? UIScale.scale((float)border.getFocusWidth(c)) : 0.0f;
    }

    public static float getBorderArc(JComponent c) {
        FlatBorder border = FlatUIUtils.getOutsideFlatBorder(c);
        return border != null ? UIScale.scale((float)border.getArc(c)) : 0.0f;
    }

    public static boolean hasRoundBorder(JComponent c) {
        return FlatUIUtils.getBorderArc(c) >= (float)c.getHeight();
    }

    public static FlatBorder getOutsideFlatBorder(JComponent c) {
        Border border = c.getBorder();
        while (true) {
            if (border instanceof FlatBorder) {
                return (FlatBorder)border;
            }
            if (!(border instanceof CompoundBorder)) break;
            border = ((CompoundBorder)border).getOutsideBorder();
        }
        return null;
    }

    public static Object[] setRenderingHints(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Object[] oldRenderingHints = new Object[]{g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING), g2.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL)};
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, MAC_USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);
        return oldRenderingHints;
    }

    public static void resetRenderingHints(Graphics g, Object[] oldRenderingHints) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldRenderingHints[0]);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, oldRenderingHints[1]);
    }

    public static void runWithoutRenderingHints(Graphics g, Object[] oldRenderingHints, Runnable runnable) {
        if (oldRenderingHints == null) {
            runnable.run();
            return;
        }
        Graphics2D g2 = (Graphics2D)g;
        Object[] oldRenderingHints2 = new Object[]{g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING), g2.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL)};
        FlatUIUtils.resetRenderingHints(g2, oldRenderingHints);
        runnable.run();
        FlatUIUtils.resetRenderingHints(g2, oldRenderingHints2);
    }

    public static Color deriveColor(Color color, Color baseColor) {
        return color instanceof DerivedColor ? ((DerivedColor)color).derive(baseColor) : color;
    }

    public static void paintComponentOuterBorder(Graphics2D g, int x, int y, int width, int height, float focusWidth, float lineWidth, float arc) {
        if (focusWidth + lineWidth == 0.0f) {
            return;
        }
        double systemScaleFactor = UIScale.getSystemScaleFactor(g);
        if (systemScaleFactor != 1.0 && systemScaleFactor != 2.0) {
            HiDPIUtils.paintAtScale1x(g, x, y, width, height, (g2d, x2, y2, width2, height2, scaleFactor) -> FlatUIUtils.paintComponentOuterBorderImpl(g2d, x2, y2, width2, height2, (float)((double)focusWidth * scaleFactor), (float)((double)lineWidth * scaleFactor), (float)((double)arc * scaleFactor)));
            return;
        }
        FlatUIUtils.paintComponentOuterBorderImpl(g, x, y, width, height, focusWidth, lineWidth, arc);
    }

    private static void paintComponentOuterBorderImpl(Graphics2D g, int x, int y, int width, int height, float focusWidth, float lineWidth, float arc) {
        float ow = focusWidth + lineWidth;
        float outerArc = arc + focusWidth * 2.0f;
        float innerArc = arc - lineWidth * 2.0f;
        if (arc > 0.0f && arc < (float)UIScale.scale(10)) {
            outerArc -= UIScale.scale(2.0f);
        }
        Path2D.Float path = new Path2D.Float(0);
        path.append(FlatUIUtils.createComponentRectangle(x, y, width, height, outerArc), false);
        path.append(FlatUIUtils.createComponentRectangle((float)x + ow, (float)y + ow, (float)width - ow * 2.0f, (float)height - ow * 2.0f, innerArc), false);
        g.fill(path);
    }

    public static void paintComponentBorder(Graphics2D g, int x, int y, int width, int height, float focusWidth, float lineWidth, float arc) {
        if (lineWidth == 0.0f) {
            return;
        }
        double systemScaleFactor = UIScale.getSystemScaleFactor(g);
        if (systemScaleFactor != 1.0 && systemScaleFactor != 2.0) {
            HiDPIUtils.paintAtScale1x(g, x, y, width, height, (g2d, x2, y2, width2, height2, scaleFactor) -> FlatUIUtils.paintComponentBorderImpl(g2d, x2, y2, width2, height2, (float)((double)focusWidth * scaleFactor), (float)((double)lineWidth * scaleFactor), (float)((double)arc * scaleFactor)));
            return;
        }
        FlatUIUtils.paintComponentBorderImpl(g, x, y, width, height, focusWidth, lineWidth, arc);
    }

    private static void paintComponentBorderImpl(Graphics2D g, int x, int y, int width, int height, float focusWidth, float lineWidth, float arc) {
        float x1 = (float)x + focusWidth;
        float y1 = (float)y + focusWidth;
        float width1 = (float)width - focusWidth * 2.0f;
        float height1 = (float)height - focusWidth * 2.0f;
        float arc2 = arc - lineWidth * 2.0f;
        Shape r1 = FlatUIUtils.createComponentRectangle(x1, y1, width1, height1, arc);
        Shape r2 = FlatUIUtils.createComponentRectangle(x1 + lineWidth, y1 + lineWidth, width1 - lineWidth * 2.0f, height1 - lineWidth * 2.0f, arc2);
        Path2D.Float border = new Path2D.Float(0);
        border.append(r1, false);
        border.append(r2, false);
        g.fill(border);
    }

    public static void paintComponentBackground(Graphics2D g, int x, int y, int width, int height, float focusWidth, float arc) {
        double systemScaleFactor = UIScale.getSystemScaleFactor(g);
        if (systemScaleFactor != 1.0 && systemScaleFactor != 2.0) {
            HiDPIUtils.paintAtScale1x(g, x, y, width, height, (g2d, x2, y2, width2, height2, scaleFactor) -> FlatUIUtils.paintComponentBackgroundImpl(g2d, x2, y2, width2, height2, (float)((double)focusWidth * scaleFactor), (float)((double)arc * scaleFactor)));
            return;
        }
        FlatUIUtils.paintComponentBackgroundImpl(g, x, y, width, height, focusWidth, arc);
    }

    private static void paintComponentBackgroundImpl(Graphics2D g, int x, int y, int width, int height, float focusWidth, float arc) {
        g.fill(FlatUIUtils.createComponentRectangle((float)x + focusWidth, (float)y + focusWidth, (float)width - focusWidth * 2.0f, (float)height - focusWidth * 2.0f, arc));
    }

    public static Shape createComponentRectangle(float x, float y, float w, float h, float arc) {
        if (arc <= 0.0f) {
            return new Rectangle2D.Float(x, y, w, h);
        }
        arc = Math.min(arc, Math.min(w, h));
        return new RoundRectangle2D.Float(x, y, w, h, arc, arc);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void paintFilledRectangle(Graphics g, Color color, float x, float y, float w, float h) {
        Graphics2D g2 = (Graphics2D)g.create();
        try {
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(color);
            g2.fill(new Rectangle2D.Float(x, y, w, h));
        } finally {
            g2.dispose();
        }
    }

    public static void paintGrip(Graphics g, int x, int y, int width, int height, boolean horizontal, int dotCount, int dotSize, int gap, boolean centerPrecise) {
        float gy;
        float gx;
        dotSize = UIScale.scale(dotSize);
        gap = UIScale.scale(gap);
        int gripSize = dotSize * dotCount + gap * (dotCount - 1);
        if (horizontal) {
            gx = x + Math.round((float)(width - gripSize) / 2.0f);
            gy = (float)y + (float)(height - dotSize) / 2.0f;
            if (!centerPrecise) {
                gy = Math.round(gy);
            }
        } else {
            gx = (float)x + (float)(width - dotSize) / 2.0f;
            gy = y + Math.round((float)(height - gripSize) / 2.0f);
            if (!centerPrecise) {
                gx = Math.round(gx);
            }
        }
        for (int i = 0; i < dotCount; ++i) {
            ((Graphics2D)g).fill(new Ellipse2D.Float(gx, gy, dotSize, dotSize));
            if (horizontal) {
                gx += (float)(dotSize + gap);
                continue;
            }
            gy += (float)(dotSize + gap);
        }
    }

    public static void paintParentBackground(Graphics g, JComponent c) {
        Container parent = FlatUIUtils.findOpaqueParent(c);
        if (parent != null) {
            g.setColor(parent.getBackground());
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        }
    }

    public static Color getParentBackground(JComponent c) {
        Container parent = FlatUIUtils.findOpaqueParent(c);
        return parent != null ? parent.getBackground() : UIManager.getColor("Panel.background");
    }

    private static Container findOpaqueParent(Container c) {
        while ((c = c.getParent()) != null) {
            if (!c.isOpaque()) continue;
            return c;
        }
        return null;
    }

    public static Path2D createRectangle(float x, float y, float width, float height, float lineWidth) {
        Path2D.Float path = new Path2D.Float(0);
        path.append(new Rectangle2D.Float(x, y, width, height), false);
        path.append(new Rectangle2D.Float(x + lineWidth, y + lineWidth, width - lineWidth * 2.0f, height - lineWidth * 2.0f), false);
        return path;
    }

    public static Path2D createRoundRectangle(float x, float y, float width, float height, float lineWidth, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
        Path2D.Float path = new Path2D.Float(0);
        path.append(FlatUIUtils.createRoundRectanglePath(x, y, width, height, arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight), false);
        path.append(FlatUIUtils.createRoundRectanglePath(x + lineWidth, y + lineWidth, width - lineWidth * 2.0f, height - lineWidth * 2.0f, arcTopLeft - lineWidth, arcTopRight - lineWidth, arcBottomLeft - lineWidth, arcBottomRight - lineWidth), false);
        return path;
    }

    public static Shape createRoundRectanglePath(float x, float y, float width, float height, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
        if (arcTopLeft <= 0.0f && arcTopRight <= 0.0f && arcBottomLeft <= 0.0f && arcBottomRight <= 0.0f) {
            return new Rectangle2D.Float(x, y, width, height);
        }
        float maxArc = Math.min(width, height) / 2.0f;
        arcTopLeft = arcTopLeft > 0.0f ? Math.min(arcTopLeft, maxArc) : 0.0f;
        arcTopRight = arcTopRight > 0.0f ? Math.min(arcTopRight, maxArc) : 0.0f;
        arcBottomLeft = arcBottomLeft > 0.0f ? Math.min(arcBottomLeft, maxArc) : 0.0f;
        arcBottomRight = arcBottomRight > 0.0f ? Math.min(arcBottomRight, maxArc) : 0.0f;
        float x2 = x + width;
        float y2 = y + height;
        double c = 0.5522847498307933;
        double ci = 1.0 - c;
        double ciTopLeft = (double)arcTopLeft * ci;
        double ciTopRight = (double)arcTopRight * ci;
        double ciBottomLeft = (double)arcBottomLeft * ci;
        double ciBottomRight = (double)arcBottomRight * ci;
        Path2D.Float rect = new Path2D.Float();
        ((Path2D)rect).moveTo(x2 - arcTopRight, y);
        ((Path2D)rect).curveTo((double)x2 - ciTopRight, y, x2, (double)y + ciTopRight, x2, y + arcTopRight);
        ((Path2D)rect).lineTo(x2, y2 - arcBottomRight);
        ((Path2D)rect).curveTo(x2, (double)y2 - ciBottomRight, (double)x2 - ciBottomRight, y2, x2 - arcBottomRight, y2);
        ((Path2D)rect).lineTo(x + arcBottomLeft, y2);
        ((Path2D)rect).curveTo((double)x + ciBottomLeft, y2, x, (double)y2 - ciBottomLeft, x, y2 - arcBottomLeft);
        ((Path2D)rect).lineTo(x, y + arcTopLeft);
        ((Path2D)rect).curveTo(x, (double)y + ciTopLeft, (double)x + ciTopLeft, y, x + arcTopLeft, y);
        rect.closePath();
        return rect;
    }

    public static Path2D createPath(double ... points) {
        return FlatUIUtils.createPath(true, points);
    }

    public static Path2D createPath(boolean close, double ... points) {
        Path2D.Float path = new Path2D.Float();
        ((Path2D)path).moveTo(points[0], points[1]);
        for (int i = 2; i < points.length; i += 2) {
            ((Path2D)path).lineTo(points[i], points[i + 1]);
        }
        if (close) {
            path.closePath();
        }
        return path;
    }

    public static void drawString(JComponent c, Graphics g, String text, int x, int y) {
        HiDPIUtils.drawStringWithYCorrection(c, (Graphics2D)g, text, x, y);
    }

    public static void drawStringUnderlineCharAt(JComponent c, Graphics g, String text, int underlinedIndex, int x, int y) {
        if (underlinedIndex >= 0 && UIScale.getUserScaleFactor() > 1.0f) {
            g = new Graphics2DProxy((Graphics2D)g){

                @Override
                public void fillRect(int x, int y, int width, int height) {
                    if (height == 1) {
                        height = Math.round(UIScale.scale(0.9f));
                        y += height - 1;
                    }
                    super.fillRect(x, y, width, height);
                }
            };
        }
        HiDPIUtils.drawStringUnderlineCharAtWithYCorrection(c, (Graphics2D)g, text, underlinedIndex, x, y);
    }

    public static boolean hasOpaqueBeenExplicitlySet(JComponent c) {
        boolean oldOpaque = c.isOpaque();
        LookAndFeel.installProperty(c, "opaque", !oldOpaque);
        boolean explicitlySet = c.isOpaque() == oldOpaque;
        LookAndFeel.installProperty(c, "opaque", oldOpaque);
        return explicitlySet;
    }

    public static ComponentUI createSharedUI(Object key, Supplier<ComponentUI> newInstanceSupplier) {
        return sharedUIinstances.computeIfAbsent(UIManager.getLookAndFeel(), k -> new IdentityHashMap()).computeIfAbsent(key, k -> (ComponentUI)newInstanceSupplier.get());
    }

    public static class RepaintFocusListener
    implements FocusListener {
        private final Component repaintComponent;

        public RepaintFocusListener(Component repaintComponent) {
            this.repaintComponent = repaintComponent;
        }

        @Override
        public void focusGained(FocusEvent e) {
            this.repaintComponent.repaint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            this.repaintComponent.repaint();
        }
    }
}

