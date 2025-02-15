/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.TerminalColor;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.model.hyperlinks.LinkInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HyperlinkStyle
extends TextStyle {
    @NotNull
    private final LinkInfo myLinkInfo;
    @NotNull
    private final TextStyle myHighlightStyle;
    @Nullable
    private final TextStyle myPrevTextStyle;
    @NotNull
    private final HighlightMode myHighlightMode;

    public HyperlinkStyle(@NotNull TextStyle prevTextStyle, @NotNull LinkInfo hyperlinkInfo) {
        if (prevTextStyle == null) {
            HyperlinkStyle.$$$reportNull$$$0(0);
        }
        if (hyperlinkInfo == null) {
            HyperlinkStyle.$$$reportNull$$$0(1);
        }
        this(prevTextStyle.getForeground(), prevTextStyle.getBackground(), hyperlinkInfo, HighlightMode.HOVER, prevTextStyle);
    }

    public HyperlinkStyle(@Nullable TerminalColor foreground, @Nullable TerminalColor background, @NotNull LinkInfo hyperlinkInfo, @NotNull HighlightMode mode, @Nullable TextStyle prevTextStyle) {
        if (hyperlinkInfo == null) {
            HyperlinkStyle.$$$reportNull$$$0(2);
        }
        if (mode == null) {
            HyperlinkStyle.$$$reportNull$$$0(3);
        }
        this(false, foreground, background, hyperlinkInfo, mode, prevTextStyle);
    }

    private HyperlinkStyle(boolean keepColors, @Nullable TerminalColor foreground, @Nullable TerminalColor background, @NotNull LinkInfo hyperlinkInfo, @NotNull HighlightMode mode, @Nullable TextStyle prevTextStyle) {
        if (hyperlinkInfo == null) {
            HyperlinkStyle.$$$reportNull$$$0(4);
        }
        if (mode == null) {
            HyperlinkStyle.$$$reportNull$$$0(5);
        }
        super(keepColors ? foreground : null, keepColors ? background : null);
        this.myHighlightStyle = new TextStyle.Builder().setBackground(background).setForeground(foreground).setOption(TextStyle.Option.UNDERLINED, true).build();
        this.myLinkInfo = hyperlinkInfo;
        this.myHighlightMode = mode;
        this.myPrevTextStyle = prevTextStyle;
    }

    @Nullable
    public TextStyle getPrevTextStyle() {
        return this.myPrevTextStyle;
    }

    @NotNull
    public TextStyle getHighlightStyle() {
        TextStyle textStyle = this.myHighlightStyle;
        if (textStyle == null) {
            HyperlinkStyle.$$$reportNull$$$0(6);
        }
        return textStyle;
    }

    @NotNull
    public LinkInfo getLinkInfo() {
        LinkInfo linkInfo = this.myLinkInfo;
        if (linkInfo == null) {
            HyperlinkStyle.$$$reportNull$$$0(7);
        }
        return linkInfo;
    }

    @NotNull
    public HighlightMode getHighlightMode() {
        HighlightMode highlightMode = this.myHighlightMode;
        if (highlightMode == null) {
            HyperlinkStyle.$$$reportNull$$$0(8);
        }
        return highlightMode;
    }

    @Override
    @NotNull
    public Builder toBuilder() {
        return new Builder(this);
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
            case 6: 
            case 7: 
            case 8: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 6: 
            case 7: 
            case 8: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "prevTextStyle";
                break;
            }
            case 1: 
            case 2: 
            case 4: {
                objectArray2 = objectArray3;
                objectArray3[0] = "hyperlinkInfo";
                break;
            }
            case 3: 
            case 5: {
                objectArray2 = objectArray3;
                objectArray3[0] = "mode";
                break;
            }
            case 6: 
            case 7: 
            case 8: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/HyperlinkStyle";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/HyperlinkStyle";
                break;
            }
            case 6: {
                objectArray = objectArray2;
                objectArray2[1] = "getHighlightStyle";
                break;
            }
            case 7: {
                objectArray = objectArray2;
                objectArray2[1] = "getLinkInfo";
                break;
            }
            case 8: {
                objectArray = objectArray2;
                objectArray2[1] = "getHighlightMode";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "<init>";
                break;
            }
            case 6: 
            case 7: 
            case 8: {
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 6: 
            case 7: 
            case 8: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }

    public static class Builder
    extends TextStyle.Builder {
        @NotNull
        private LinkInfo myLinkInfo;
        @NotNull
        private TextStyle myHighlightStyle;
        @Nullable
        private TextStyle myPrevTextStyle;
        @NotNull
        private HighlightMode myHighlightMode;

        private Builder(@NotNull HyperlinkStyle style) {
            if (style == null) {
                Builder.$$$reportNull$$$0(0);
            }
            this.myLinkInfo = style.myLinkInfo;
            this.myHighlightStyle = style.myHighlightStyle;
            this.myPrevTextStyle = style.myPrevTextStyle;
            this.myHighlightMode = style.myHighlightMode;
        }

        @Override
        @NotNull
        public HyperlinkStyle build() {
            HyperlinkStyle hyperlinkStyle = this.build(false);
            if (hyperlinkStyle == null) {
                Builder.$$$reportNull$$$0(1);
            }
            return hyperlinkStyle;
        }

        @NotNull
        public HyperlinkStyle build(boolean keepColors) {
            TerminalColor foreground = this.myHighlightStyle.getForeground();
            TerminalColor background = this.myHighlightStyle.getBackground();
            if (keepColors) {
                TextStyle style = super.build();
                foreground = style.getForeground() != null ? style.getForeground() : this.myHighlightStyle.getForeground();
                background = style.getBackground() != null ? style.getBackground() : this.myHighlightStyle.getBackground();
            }
            return new HyperlinkStyle(keepColors, foreground, background, this.myLinkInfo, this.myHighlightMode, this.myPrevTextStyle);
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
                    objectArray3[0] = "style";
                    break;
                }
                case 1: {
                    objectArray2 = objectArray3;
                    objectArray3[0] = "com/jediterm/terminal/HyperlinkStyle$Builder";
                    break;
                }
            }
            switch (n) {
                default: {
                    objectArray = objectArray2;
                    objectArray2[1] = "com/jediterm/terminal/HyperlinkStyle$Builder";
                    break;
                }
                case 1: {
                    objectArray = objectArray2;
                    objectArray2[1] = "build";
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

    public static enum HighlightMode {
        ALWAYS,
        NEVER,
        HOVER;

    }
}

