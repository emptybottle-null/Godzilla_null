/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.emulator;

import com.jediterm.terminal.emulator.ColorPalette;
import java.awt.Color;
import org.jetbrains.annotations.NotNull;

public class ColorPaletteImpl
extends ColorPalette {
    private static final Color[] XTERM_COLORS = new Color[]{new Color(0), new Color(0xCD0000), new Color(52480), new Color(0xCDCD00), new Color(2003199), new Color(0xCD00CD), new Color(52685), new Color(0xE5E5E5), new Color(0x4C4C4C), new Color(0xFF0000), new Color(65280), new Color(0xFFFF00), new Color(4620980), new Color(0xFF00FF), new Color(65535), new Color(0xFFFFFF)};
    public static final ColorPalette XTERM_PALETTE = new ColorPaletteImpl(XTERM_COLORS);
    private static final Color[] WINDOWS_COLORS = new Color[]{new Color(0), new Color(0x800000), new Color(32768), new Color(0x808000), new Color(128), new Color(0x800080), new Color(32896), new Color(0xC0C0C0), new Color(0x808080), new Color(0xFF0000), new Color(65280), new Color(0xFFFF00), new Color(4620980), new Color(0xFF00FF), new Color(65535), new Color(0xFFFFFF)};
    public static final ColorPalette WINDOWS_PALETTE = new ColorPaletteImpl(WINDOWS_COLORS);
    private final Color[] myColors;

    private ColorPaletteImpl(@NotNull Color[] colors) {
        if (colors == null) {
            ColorPaletteImpl.$$$reportNull$$$0(0);
        }
        this.myColors = colors;
    }

    @Override
    @NotNull
    public Color getForegroundByColorIndex(int colorIndex) {
        Color color = this.myColors[colorIndex];
        if (color == null) {
            ColorPaletteImpl.$$$reportNull$$$0(1);
        }
        return color;
    }

    @Override
    @NotNull
    protected Color getBackgroundByColorIndex(int colorIndex) {
        Color color = this.myColors[colorIndex];
        if (color == null) {
            ColorPaletteImpl.$$$reportNull$$$0(2);
        }
        return color;
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
            case 2: {
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
            case 2: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "colors";
                break;
            }
            case 1: 
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/emulator/ColorPaletteImpl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/emulator/ColorPaletteImpl";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[1] = "getForegroundByColorIndex";
                break;
            }
            case 2: {
                objectArray = objectArray2;
                objectArray2[1] = "getBackgroundByColorIndex";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "<init>";
                break;
            }
            case 1: 
            case 2: {
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
            case 2: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

