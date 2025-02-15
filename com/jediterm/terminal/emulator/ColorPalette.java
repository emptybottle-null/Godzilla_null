/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.emulator;

import com.jediterm.terminal.TerminalColor;
import java.awt.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ColorPalette {
    private static final TerminalColor[] COL_RES_256 = new TerminalColor[240];

    @NotNull
    public Color getForeground(@NotNull TerminalColor color) {
        if (color == null) {
            ColorPalette.$$$reportNull$$$0(0);
        }
        if (color.isIndexed()) {
            int colorIndex = color.getColorIndex();
            this.assertColorIndexIsLessThan16(colorIndex);
            Color color2 = this.getForegroundByColorIndex(colorIndex);
            if (color2 == null) {
                ColorPalette.$$$reportNull$$$0(1);
            }
            return color2;
        }
        Color color3 = color.toAwtColor();
        if (color3 == null) {
            ColorPalette.$$$reportNull$$$0(2);
        }
        return color3;
    }

    @NotNull
    protected abstract Color getForegroundByColorIndex(int var1);

    @NotNull
    public Color getBackground(@NotNull TerminalColor color) {
        if (color == null) {
            ColorPalette.$$$reportNull$$$0(3);
        }
        if (color.isIndexed()) {
            int colorIndex = color.getColorIndex();
            this.assertColorIndexIsLessThan16(colorIndex);
            Color color2 = this.getBackgroundByColorIndex(colorIndex);
            if (color2 == null) {
                ColorPalette.$$$reportNull$$$0(4);
            }
            return color2;
        }
        Color color3 = color.toAwtColor();
        if (color3 == null) {
            ColorPalette.$$$reportNull$$$0(5);
        }
        return color3;
    }

    @NotNull
    protected abstract Color getBackgroundByColorIndex(int var1);

    private void assertColorIndexIsLessThan16(int colorIndex) {
        if (colorIndex < 0 || colorIndex >= 16) {
            throw new AssertionError((Object)("Color index is out of bounds [0,15]: " + colorIndex));
        }
    }

    @Nullable
    public static TerminalColor getIndexedTerminalColor(int colorIndex) {
        return colorIndex < 16 ? TerminalColor.index(colorIndex) : ColorPalette.getXTerm256(colorIndex);
    }

    @Nullable
    private static TerminalColor getXTerm256(int colorIndex) {
        return colorIndex < 256 ? COL_RES_256[colorIndex - 16] : null;
    }

    private static int getCubeColorValue(int value) {
        return value == 0 ? 0 : 40 * value + 55;
    }

    static {
        for (int red = 0; red < 6; ++red) {
            for (int green = 0; green < 6; ++green) {
                for (int blue = 0; blue < 6; ++blue) {
                    ColorPalette.COL_RES_256[36 * red + 6 * green + blue] = new TerminalColor(ColorPalette.getCubeColorValue(red), ColorPalette.getCubeColorValue(green), ColorPalette.getCubeColorValue(blue));
                }
            }
        }
        for (int gray = 0; gray < 24; ++gray) {
            int level = 10 * gray + 8;
            ColorPalette.COL_RES_256[216 + gray] = new TerminalColor(level, level, level);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        RuntimeException runtimeException;
        Object[] objectArray;
        Object[] objectArray2;
        int n2;
        String string;
        switch (n) {
            default: {
                string = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            }
            case 1: 
            case 2: 
            case 4: 
            case 5: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 1: 
            case 2: 
            case 4: 
            case 5: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "color";
                break;
            }
            case 1: 
            case 2: 
            case 4: 
            case 5: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/emulator/ColorPalette";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/emulator/ColorPalette";
                break;
            }
            case 1: 
            case 2: {
                objectArray = objectArray2;
                objectArray2[1] = "getForeground";
                break;
            }
            case 4: 
            case 5: {
                objectArray = objectArray2;
                objectArray2[1] = "getBackground";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "getForeground";
                break;
            }
            case 1: 
            case 2: 
            case 4: 
            case 5: {
                break;
            }
            case 3: {
                objectArray = objectArray;
                objectArray[2] = "getBackground";
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 1: 
            case 2: 
            case 4: 
            case 5: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

