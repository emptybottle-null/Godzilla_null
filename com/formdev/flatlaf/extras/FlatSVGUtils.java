/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras;

import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class FlatSVGUtils {
    public static List<Image> createWindowIconImages(String svgName) {
        SVGDiagram diagram = FlatSVGUtils.loadSVG(svgName);
        return Arrays.asList(FlatSVGUtils.svg2image(diagram, 16, 16), FlatSVGUtils.svg2image(diagram, 24, 24), FlatSVGUtils.svg2image(diagram, 32, 32), FlatSVGUtils.svg2image(diagram, 48, 48), FlatSVGUtils.svg2image(diagram, 64, 64));
    }

    public static BufferedImage svg2image(String svgName, int width, int height) {
        return FlatSVGUtils.svg2image(FlatSVGUtils.loadSVG(svgName), width, height);
    }

    public static BufferedImage svg2image(String svgName, float scaleFactor) {
        SVGDiagram diagram = FlatSVGUtils.loadSVG(svgName);
        int width = (int)(diagram.getWidth() * scaleFactor);
        int height = (int)(diagram.getHeight() * scaleFactor);
        return FlatSVGUtils.svg2image(diagram, width, height);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage svg2image(SVGDiagram diagram, int width, int height) {
        try {
            BufferedImage image = new BufferedImage(width, height, 2);
            Graphics2D g = image.createGraphics();
            try {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                double sx = (float)width / diagram.getWidth();
                double sy = (float)height / diagram.getHeight();
                if (sx != 1.0 || sy != 1.0) {
                    g.scale(sx, sy);
                }
                diagram.setIgnoringClipHeuristic(true);
                diagram.render(g);
            } finally {
                g.dispose();
            }
            return image;
        } catch (SVGException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static SVGDiagram loadSVG(String svgName) {
        try {
            URL url = FlatSVGUtils.class.getResource(svgName);
            return SVGCache.getSVGUniverse().getDiagram(url.toURI());
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }
}

