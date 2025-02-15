/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.HyperlinkStyle;
import com.jediterm.terminal.TerminalColor;
import java.lang.ref.WeakReference;
import java.util.EnumSet;
import java.util.Objects;
import java.util.WeakHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TextStyle {
    private static final EnumSet<Option> NO_OPTIONS = EnumSet.noneOf(Option.class);
    public static final TextStyle EMPTY = new TextStyle();
    private static final WeakHashMap<TextStyle, WeakReference<TextStyle>> styles = new WeakHashMap();
    private final TerminalColor myForeground;
    private final TerminalColor myBackground;
    private final EnumSet<Option> myOptions;

    public TextStyle() {
        this(null, null, NO_OPTIONS);
    }

    public TextStyle(@Nullable TerminalColor foreground, @Nullable TerminalColor background) {
        this(foreground, background, NO_OPTIONS);
    }

    public TextStyle(@Nullable TerminalColor foreground, @Nullable TerminalColor background, @NotNull EnumSet<Option> options) {
        if (options == null) {
            TextStyle.$$$reportNull$$$0(0);
        }
        this.myForeground = foreground;
        this.myBackground = background;
        this.myOptions = options.clone();
    }

    @NotNull
    public static TextStyle getCanonicalStyle(TextStyle currentStyle) {
        TextStyle canonStyle;
        if (currentStyle instanceof HyperlinkStyle) {
            TextStyle textStyle = currentStyle;
            if (textStyle == null) {
                TextStyle.$$$reportNull$$$0(1);
            }
            return textStyle;
        }
        WeakReference<TextStyle> canonRef = styles.get(currentStyle);
        if (canonRef != null && (canonStyle = (TextStyle)canonRef.get()) != null) {
            TextStyle textStyle = canonStyle;
            if (textStyle == null) {
                TextStyle.$$$reportNull$$$0(2);
            }
            return textStyle;
        }
        styles.put(currentStyle, new WeakReference<TextStyle>(currentStyle));
        TextStyle textStyle = currentStyle;
        if (textStyle == null) {
            TextStyle.$$$reportNull$$$0(3);
        }
        return textStyle;
    }

    @Nullable
    public TerminalColor getForeground() {
        return this.myForeground;
    }

    @Nullable
    public TerminalColor getBackground() {
        return this.myBackground;
    }

    public TextStyle createEmptyWithColors() {
        return new TextStyle(this.myForeground, this.myBackground);
    }

    public int getId() {
        return this.hashCode();
    }

    public boolean hasOption(Option option) {
        return this.myOptions.contains((Object)option);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        TextStyle textStyle = (TextStyle)o;
        return Objects.equals(this.myForeground, textStyle.myForeground) && Objects.equals(this.myBackground, textStyle.myBackground) && this.myOptions.equals(textStyle.myOptions);
    }

    public int hashCode() {
        return Objects.hash(this.myForeground, this.myBackground, this.myOptions);
    }

    public TerminalColor getBackgroundForRun() {
        return this.myOptions.contains((Object)Option.INVERSE) ? this.myForeground : this.myBackground;
    }

    public TerminalColor getForegroundForRun() {
        return this.myOptions.contains((Object)Option.INVERSE) ? this.myBackground : this.myForeground;
    }

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
            case 1: 
            case 2: 
            case 3: {
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
            case 3: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "options";
                break;
            }
            case 1: 
            case 2: 
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/TextStyle";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/TextStyle";
                break;
            }
            case 1: 
            case 2: 
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getCanonicalStyle";
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
            case 2: 
            case 3: {
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
            case 3: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }

    public static class Builder {
        private TerminalColor myForeground;
        private TerminalColor myBackground;
        private EnumSet<Option> myOptions;

        public Builder(@NotNull TextStyle textStyle) {
            if (textStyle == null) {
                Builder.$$$reportNull$$$0(0);
            }
            this.myForeground = textStyle.myForeground;
            this.myBackground = textStyle.myBackground;
            this.myOptions = textStyle.myOptions.clone();
        }

        public Builder() {
            this.myForeground = null;
            this.myBackground = null;
            this.myOptions = EnumSet.noneOf(Option.class);
        }

        @NotNull
        public Builder setForeground(@Nullable TerminalColor foreground) {
            this.myForeground = foreground;
            Builder builder = this;
            if (builder == null) {
                Builder.$$$reportNull$$$0(1);
            }
            return builder;
        }

        @NotNull
        public Builder setBackground(@Nullable TerminalColor background) {
            this.myBackground = background;
            Builder builder = this;
            if (builder == null) {
                Builder.$$$reportNull$$$0(2);
            }
            return builder;
        }

        @NotNull
        public Builder setOption(@NotNull Option option, boolean val) {
            if (option == null) {
                Builder.$$$reportNull$$$0(3);
            }
            option.set(this.myOptions, val);
            Builder builder = this;
            if (builder == null) {
                Builder.$$$reportNull$$$0(4);
            }
            return builder;
        }

        @NotNull
        public TextStyle build() {
            return new TextStyle(this.myForeground, this.myBackground, this.myOptions);
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
                case 4: {
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
                case 4: {
                    n2 = 2;
                    break;
                }
            }
            Object[] objectArray3 = new Object[n2];
            switch (n) {
                default: {
                    objectArray2 = objectArray3;
                    objectArray3[0] = "textStyle";
                    break;
                }
                case 1: 
                case 2: 
                case 4: {
                    objectArray2 = objectArray3;
                    objectArray3[0] = "com/jediterm/terminal/TextStyle$Builder";
                    break;
                }
                case 3: {
                    objectArray2 = objectArray3;
                    objectArray3[0] = "option";
                    break;
                }
            }
            switch (n) {
                default: {
                    objectArray = objectArray2;
                    objectArray2[1] = "com/jediterm/terminal/TextStyle$Builder";
                    break;
                }
                case 1: {
                    objectArray = objectArray2;
                    objectArray2[1] = "setForeground";
                    break;
                }
                case 2: {
                    objectArray = objectArray2;
                    objectArray2[1] = "setBackground";
                    break;
                }
                case 4: {
                    objectArray = objectArray2;
                    objectArray2[1] = "setOption";
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
                case 2: 
                case 4: {
                    break;
                }
                case 3: {
                    objectArray = objectArray;
                    objectArray[2] = "setOption";
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
                case 4: {
                    runtimeException = new IllegalStateException(string2);
                    break;
                }
            }
            throw runtimeException;
        }
    }

    public static enum Option {
        BOLD,
        ITALIC,
        BLINK,
        DIM,
        INVERSE,
        UNDERLINED,
        HIDDEN;


        private void set(@NotNull EnumSet<Option> options, boolean val) {
            if (options == null) {
                Option.$$$reportNull$$$0(0);
            }
            if (val) {
                options.add(this);
            } else {
                options.remove((Object)this);
            }
        }

        private static /* synthetic */ void $$$reportNull$$$0(int n) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jediterm/terminal/TextStyle$Option", "set"));
        }
    }
}

