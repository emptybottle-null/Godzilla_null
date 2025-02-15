/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import org.apache.poi.util.Dimension2DDouble;

public class Units {
    public static final int EMU_PER_PIXEL = 9525;
    public static final int EMU_PER_POINT = 12700;
    public static final int EMU_PER_CENTIMETER = 360000;
    public static final int MASTER_DPI = 576;
    public static final int PIXEL_DPI = 96;
    public static final int POINT_DPI = 72;
    public static final float DEFAULT_CHARACTER_WIDTH = 7.0017f;
    public static final int EMU_PER_CHARACTER = 66691;

    public static int toEMU(double points) {
        return (int)Math.rint(12700.0 * points);
    }

    public static int pixelToEMU(int pixels) {
        return pixels * 9525;
    }

    public static double toPoints(long emu) {
        return (double)emu / 12700.0;
    }

    public static double fixedPointToDouble(int fixedPoint) {
        int i = fixedPoint >> 16;
        int f = fixedPoint & 0xFFFF;
        return (double)i + (double)f / 65536.0;
    }

    public static int doubleToFixedPoint(double floatPoint) {
        double fractionalPart = floatPoint % 1.0;
        double integralPart = floatPoint - fractionalPart;
        int i = (int)Math.floor(integralPart);
        int f = (int)Math.rint(fractionalPart * 65536.0);
        return i << 16 | f & 0xFFFF;
    }

    public static double masterToPoints(int masterDPI) {
        double points = masterDPI;
        points *= 72.0;
        return points /= 576.0;
    }

    public static int pointsToMaster(double points) {
        points *= 576.0;
        return (int)Math.rint(points /= 72.0);
    }

    public static int pointsToPixel(double points) {
        points *= 96.0;
        return (int)Math.rint(points /= 72.0);
    }

    public static double pixelToPoints(double pixel) {
        double points = pixel;
        points *= 72.0;
        return points /= 96.0;
    }

    public static Dimension2D pointsToPixel(Dimension2D pointsDim) {
        double width = pointsDim.getWidth() * 96.0 / 72.0;
        double height = pointsDim.getHeight() * 96.0 / 72.0;
        return new Dimension2DDouble(width, height);
    }

    public static Dimension2D pixelToPoints(Dimension2D pointsDim) {
        double width = pointsDim.getWidth() * 72.0 / 96.0;
        double height = pointsDim.getHeight() * 72.0 / 96.0;
        return new Dimension2DDouble(width, height);
    }

    public static Rectangle2D pointsToPixel(Rectangle2D pointsDim) {
        double x = pointsDim.getX() * 96.0 / 72.0;
        double y = pointsDim.getY() * 96.0 / 72.0;
        double width = pointsDim.getWidth() * 96.0 / 72.0;
        double height = pointsDim.getHeight() * 96.0 / 72.0;
        return new Rectangle2D.Double(x, y, width, height);
    }

    public static Rectangle2D pixelToPoints(Rectangle2D pointsDim) {
        double x = pointsDim.getX() * 72.0 / 96.0;
        double y = pointsDim.getY() * 72.0 / 96.0;
        double width = pointsDim.getWidth() * 72.0 / 96.0;
        double height = pointsDim.getHeight() * 72.0 / 96.0;
        return new Rectangle2D.Double(x, y, width, height);
    }

    public static int charactersToEMU(double characters) {
        return (int)characters * 66691;
    }

    public static int columnWidthToEMU(int columnWidth) {
        return Units.charactersToEMU((double)columnWidth / 256.0);
    }

    public static int TwipsToEMU(short twips) {
        return (int)((double)twips / 20.0 * 12700.0);
    }
}

