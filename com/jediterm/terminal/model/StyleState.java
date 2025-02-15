/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.jediterm.terminal.TerminalColor;
import com.jediterm.terminal.TextStyle;
import org.jetbrains.annotations.NotNull;

public class StyleState {
    private TextStyle myCurrentStyle = TextStyle.EMPTY;
    private TextStyle myDefaultStyle = TextStyle.EMPTY;
    private TextStyle myMergedStyle = null;

    public TextStyle getCurrent() {
        return TextStyle.getCanonicalStyle(this.getMergedStyle());
    }

    @NotNull
    private static TextStyle merge(@NotNull TextStyle style, @NotNull TextStyle defaultStyle) {
        if (style == null) {
            StyleState.$$$reportNull$$$0(0);
        }
        if (defaultStyle == null) {
            StyleState.$$$reportNull$$$0(1);
        }
        TextStyle.Builder builder = style.toBuilder();
        if (style.getBackground() == null && defaultStyle.getBackground() != null) {
            builder.setBackground(defaultStyle.getBackground());
        }
        if (style.getForeground() == null && defaultStyle.getForeground() != null) {
            builder.setForeground(defaultStyle.getForeground());
        }
        TextStyle textStyle = builder.build();
        if (textStyle == null) {
            StyleState.$$$reportNull$$$0(2);
        }
        return textStyle;
    }

    public void reset() {
        this.myCurrentStyle = this.myDefaultStyle;
        this.myMergedStyle = null;
    }

    public void set(StyleState styleState) {
        this.setCurrent(styleState.getCurrent());
    }

    public void setDefaultStyle(TextStyle defaultStyle) {
        this.myDefaultStyle = defaultStyle;
        this.myMergedStyle = null;
    }

    public TerminalColor getBackground() {
        return this.getBackground(null);
    }

    public TerminalColor getBackground(TerminalColor color) {
        return color != null ? color : this.myDefaultStyle.getBackground();
    }

    public TerminalColor getForeground() {
        return this.getForeground(null);
    }

    public TerminalColor getForeground(TerminalColor color) {
        return color != null ? color : this.myDefaultStyle.getForeground();
    }

    public void setCurrent(TextStyle current) {
        this.myCurrentStyle = current;
        this.myMergedStyle = null;
    }

    private TextStyle getMergedStyle() {
        if (this.myMergedStyle == null) {
            this.myMergedStyle = StyleState.merge(this.myCurrentStyle, this.myDefaultStyle);
        }
        return this.myMergedStyle;
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
            case 2: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "style";
                break;
            }
            case 1: {
                objectArray2 = objectArray3;
                objectArray3[0] = "defaultStyle";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/model/StyleState";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/model/StyleState";
                break;
            }
            case 2: {
                objectArray = objectArray2;
                objectArray2[1] = "merge";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "merge";
                break;
            }
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
            case 2: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

