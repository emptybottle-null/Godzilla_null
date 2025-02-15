/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import java.awt.Color;
import java.util.Objects;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TerminalColor {
    public static final TerminalColor BLACK = TerminalColor.index(0);
    public static final TerminalColor WHITE = TerminalColor.index(15);
    private final int myColorIndex;
    private final Color myColor;
    private final Supplier<Color> myColorSupplier;

    public TerminalColor(int colorIndex) {
        this(colorIndex, null, null);
    }

    public TerminalColor(int r, int g, int b) {
        this(-1, new Color(r, g, b), null);
    }

    public TerminalColor(@NotNull Supplier<Color> colorSupplier) {
        if (colorSupplier == null) {
            TerminalColor.$$$reportNull$$$0(0);
        }
        this(-1, null, colorSupplier);
    }

    private TerminalColor(int colorIndex, @Nullable Color color, @Nullable Supplier<Color> colorSupplier) {
        if (colorIndex != -1) {
            assert (color == null);
            assert (colorSupplier == null);
        } else if (color != null ? !$assertionsDisabled && colorSupplier != null : !$assertionsDisabled && colorSupplier == null) {
            throw new AssertionError();
        }
        this.myColorIndex = colorIndex;
        this.myColor = color;
        this.myColorSupplier = colorSupplier;
    }

    @NotNull
    public static TerminalColor index(int colorIndex) {
        return new TerminalColor(colorIndex);
    }

    public static TerminalColor rgb(int r, int g, int b) {
        return new TerminalColor(r, g, b);
    }

    public boolean isIndexed() {
        return this.myColorIndex != -1;
    }

    @NotNull
    public Color toAwtColor() {
        if (this.isIndexed()) {
            throw new IllegalArgumentException("Color is indexed color so a palette is needed");
        }
        Color color = this.myColor != null ? this.myColor : Objects.requireNonNull(this.myColorSupplier).get();
        if (color == null) {
            TerminalColor.$$$reportNull$$$0(1);
        }
        return color;
    }

    public int getColorIndex() {
        return this.myColorIndex;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        TerminalColor that = (TerminalColor)o;
        return this.myColorIndex == that.myColorIndex && Objects.equals(this.myColor, that.myColor);
    }

    public int hashCode() {
        return Objects.hash(this.myColorIndex, this.myColor);
    }

    @Nullable
    public static TerminalColor awt(@Nullable Color color) {
        if (color == null) {
            return null;
        }
        return TerminalColor.rgb(color.getRed(), color.getGreen(), color.getBlue());
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
            case 1: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 1: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "colorSupplier";
                break;
            }
            case 1: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/TerminalColor";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/TerminalColor";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[1] = "toAwtColor";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "<init>";
                break;
            }
            case 1: {
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 1: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

