/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

public class FlatDropShadowBorder
extends FlatEmptyBorder {
    private final Color shadowColor;
    private final Insets shadowInsets;
    private final float shadowOpacity;
    private final int shadowSize;
    private Image shadowImage;
    private Color lastShadowColor;
    private double lastSystemScaleFactor;
    private float lastUserScaleFactor;

    public FlatDropShadowBorder() {
        this((Color)null);
    }

    public FlatDropShadowBorder(Color shadowColor) {
        this(shadowColor, 4, 0.5f);
    }

    public FlatDropShadowBorder(Color shadowColor, int shadowSize, float shadowOpacity) {
        this(shadowColor, new Insets(-shadowSize, -shadowSize, shadowSize, shadowSize), shadowOpacity);
    }

    public FlatDropShadowBorder(Color shadowColor, Insets shadowInsets, float shadowOpacity) {
        super(Math.max(shadowInsets.top, 0), Math.max(shadowInsets.left, 0), Math.max(shadowInsets.bottom, 0), Math.max(shadowInsets.right, 0));
        this.shadowColor = shadowColor;
        this.shadowInsets = shadowInsets;
        this.shadowOpacity = shadowOpacity;
        this.shadowSize = Math.max(Math.max(shadowInsets.left, shadowInsets.right), Math.max(shadowInsets.top, shadowInsets.bottom));
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if (this.shadowSize <= 0) {
            return;
        }
        HiDPIUtils.paintAtScale1x((Graphics2D)g, x, y, width, height, this::paintImpl);
    }

    private void paintImpl(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
        Color shadowColor = this.shadowColor != null ? this.shadowColor : g.getColor();
        int shadowSize = this.scale(this.shadowSize, scaleFactor);
        float userScaleFactor = UIScale.getUserScaleFactor();
        if (this.shadowImage == null || !shadowColor.equals(this.lastShadowColor) || this.lastSystemScaleFactor != scaleFactor || this.lastUserScaleFactor != userScaleFactor) {
            this.shadowImage = FlatDropShadowBorder.createShadowImage(shadowColor, shadowSize, this.shadowOpacity, (float)(scaleFactor * (double)userScaleFactor));
            this.lastShadowColor = shadowColor;
            this.lastSystemScaleFactor = scaleFactor;
            this.lastUserScaleFactor = userScaleFactor;
        }
        int left = this.scale(this.shadowInsets.left, scaleFactor);
        int right = this.scale(this.shadowInsets.right, scaleFactor);
        int top = this.scale(this.shadowInsets.top, scaleFactor);
        int bottom = this.scale(this.shadowInsets.bottom, scaleFactor);
        int x1o = x - Math.min(left, 0);
        int y1o = y - Math.min(top, 0);
        int x2o = x + width + Math.min(right, 0);
        int y2o = y + height + Math.min(bottom, 0);
        int x1i = x1o + shadowSize;
        int y1i = y1o + shadowSize;
        int x2i = x2o - shadowSize;
        int y2i = y2o - shadowSize;
        int wh = shadowSize * 2 - 1;
        int center = shadowSize - 1;
        if (left > 0 || top > 0) {
            g.drawImage(this.shadowImage, x1o, y1o, x1i, y1i, 0, 0, center, center, null);
        }
        if (top > 0) {
            g.drawImage(this.shadowImage, x1i, y1o, x2i, y1i, center, 0, center + 1, center, null);
        }
        if (right > 0 || top > 0) {
            g.drawImage(this.shadowImage, x2i, y1o, x2o, y1i, center, 0, wh, center, null);
        }
        if (left > 0) {
            g.drawImage(this.shadowImage, x1o, y1i, x1i, y2i, 0, center, center, center + 1, null);
        }
        if (right > 0) {
            g.drawImage(this.shadowImage, x2i, y1i, x2o, y2i, center, center, wh, center + 1, null);
        }
        if (left > 0 || bottom > 0) {
            g.drawImage(this.shadowImage, x1o, y2i, x1i, y2o, 0, center, center, wh, null);
        }
        if (bottom > 0) {
            g.drawImage(this.shadowImage, x1i, y2i, x2i, y2o, center, center, center + 1, wh, null);
        }
        if (right > 0 || bottom > 0) {
            g.drawImage(this.shadowImage, x2i, y2i, x2o, y2o, center, center, wh, wh, null);
        }
    }

    private int scale(int value, double scaleFactor) {
        return (int)Math.ceil((double)UIScale.scale(value) * scaleFactor);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static BufferedImage createShadowImage(Color shadowColor, int shadowSize, float shadowOpacity, float scaleFactor) {
        int shadowRGB = shadowColor.getRGB() & 0xFFFFFF;
        int shadowAlpha = (int)(255.0f * shadowOpacity);
        Color startColor = new Color(shadowRGB | (shadowAlpha & 0xFF) << 24, true);
        Color midColor = new Color(shadowRGB | (shadowAlpha / 2 & 0xFF) << 24, true);
        Color endColor = new Color(shadowRGB, true);
        int wh = shadowSize * 2 - 1;
        int center = shadowSize - 1;
        RadialGradientPaint p = new RadialGradientPaint(center, (float)center, (float)shadowSize - 0.75f * scaleFactor, new float[]{0.0f, 0.35f, 1.0f}, new Color[]{startColor, midColor, endColor});
        BufferedImage image = new BufferedImage(wh, wh, 2);
        Graphics2D g = image.createGraphics();
        try {
            g.setPaint(p);
            g.fillRect(0, 0, wh, wh);
        } finally {
            g.dispose();
        }
        return image;
    }
}

