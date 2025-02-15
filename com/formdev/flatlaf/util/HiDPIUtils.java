/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.JavaCompatibility;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Graphics2D;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.text.AttributedCharacterIterator;
import javax.swing.JComponent;

public class HiDPIUtils {
    private static Boolean useTextYCorrection;

    public static void paintAtScale1x(Graphics2D g, JComponent c, Painter painter) {
        HiDPIUtils.paintAtScale1x(g, 0, 0, c.getWidth(), c.getHeight(), painter);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void paintAtScale1x(Graphics2D g, int x, int y, int width, int height, Painter painter) {
        AffineTransform transform = g.getTransform();
        if (transform.getScaleX() == 1.0 && transform.getScaleY() == 1.0) {
            painter.paint(g, x, y, width, height, 1.0);
            return;
        }
        Rectangle2D.Double scaledRect = HiDPIUtils.scale(transform, x, y, width, height);
        try {
            g.setTransform(new AffineTransform(1.0, 0.0, 0.0, 1.0, Math.floor(scaledRect.x), Math.floor(scaledRect.y)));
            int swidth = (int)scaledRect.width;
            int sheight = (int)scaledRect.height;
            painter.paint(g, 0, 0, swidth, sheight, transform.getScaleX());
        } finally {
            g.setTransform(transform);
        }
    }

    private static Rectangle2D.Double scale(AffineTransform transform, int x, int y, int width, int height) {
        double dx1 = transform.getScaleX();
        double dy2 = transform.getScaleY();
        double px = (double)x * dx1 + transform.getTranslateX();
        double py = (double)y * dy2 + transform.getTranslateY();
        dx1 *= (double)width;
        dy2 *= (double)height;
        double newx = HiDPIUtils.normalize(px);
        double newy = HiDPIUtils.normalize(py);
        dx1 = HiDPIUtils.normalize(px + dx1) - newx;
        dy2 = HiDPIUtils.normalize(py + dy2) - newy;
        return new Rectangle2D.Double(newx, newy, dx1, dy2);
    }

    private static double normalize(double value) {
        return Math.floor(value + 0.25) + 0.25;
    }

    private static boolean useTextYCorrection() {
        if (useTextYCorrection == null) {
            useTextYCorrection = FlatSystemProperties.getBoolean("flatlaf.useTextYCorrection", true);
        }
        return useTextYCorrection;
    }

    public static float computeTextYCorrection(Graphics2D g) {
        if (!HiDPIUtils.useTextYCorrection() || !SystemInfo.isWindows) {
            return 0.0f;
        }
        if (!SystemInfo.isJava_9_orLater) {
            return UIScale.getUserScaleFactor() > 1.0f ? -UIScale.scale(0.625f) : 0.0f;
        }
        AffineTransform t = g.getTransform();
        double scaleY = t.getScaleY();
        if (scaleY < 1.25) {
            return 0.0f;
        }
        if (scaleY <= 1.25) {
            return -0.875f;
        }
        if (scaleY <= 1.5) {
            return -0.625f;
        }
        if (scaleY <= 1.75) {
            return -0.875f;
        }
        if (scaleY <= 2.0) {
            return -0.75f;
        }
        if (scaleY <= 2.25) {
            return -0.875f;
        }
        if (scaleY <= 3.5) {
            return -0.75f;
        }
        return -0.875f;
    }

    public static void drawStringWithYCorrection(JComponent c, Graphics2D g, String text, int x, int y) {
        HiDPIUtils.drawStringUnderlineCharAtWithYCorrection(c, g, text, -1, x, y);
    }

    public static void drawStringUnderlineCharAtWithYCorrection(JComponent c, Graphics2D g, String text, int underlinedIndex, int x, int y) {
        float yCorrection = HiDPIUtils.computeTextYCorrection(g);
        if (yCorrection != 0.0f) {
            g.translate(0.0, yCorrection);
            JavaCompatibility.drawStringUnderlineCharAt(c, g, text, underlinedIndex, x, y);
            g.translate(0.0, -yCorrection);
        } else {
            JavaCompatibility.drawStringUnderlineCharAt(c, g, text, underlinedIndex, x, y);
        }
    }

    public static Graphics2D createGraphicsTextYCorrection(Graphics2D g) {
        final float yCorrection = HiDPIUtils.computeTextYCorrection(g);
        if (yCorrection == 0.0f) {
            return g;
        }
        return new Graphics2DProxy(g){

            @Override
            public void drawString(String str, int x, int y) {
                super.drawString(str, (float)x, (float)y + yCorrection);
            }

            @Override
            public void drawString(String str, float x, float y) {
                super.drawString(str, x, y + yCorrection);
            }

            @Override
            public void drawString(AttributedCharacterIterator iterator, int x, int y) {
                super.drawString(iterator, (float)x, (float)y + yCorrection);
            }

            @Override
            public void drawString(AttributedCharacterIterator iterator, float x, float y) {
                super.drawString(iterator, x, y + yCorrection);
            }

            @Override
            public void drawChars(char[] data, int offset, int length, int x, int y) {
                super.drawChars(data, offset, length, x, Math.round((float)y + yCorrection));
            }

            @Override
            public void drawBytes(byte[] data, int offset, int length, int x, int y) {
                super.drawBytes(data, offset, length, x, Math.round((float)y + yCorrection));
            }

            @Override
            public void drawGlyphVector(GlyphVector g, float x, float y) {
                super.drawGlyphVector(g, x, y + yCorrection);
            }
        };
    }

    public static interface Painter {
        public void paint(Graphics2D var1, int var2, int var3, int var4, int var5, double var6);
    }
}

