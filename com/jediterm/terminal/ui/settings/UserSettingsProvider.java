/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui.settings;

import com.jediterm.terminal.HyperlinkStyle;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.emulator.ColorPalette;
import java.awt.Font;

public interface UserSettingsProvider {
    public ColorPalette getTerminalColorPalette();

    public Font getTerminalFont();

    public float getTerminalFontSize();

    default public float getLineSpacing() {
        return this.getLineSpace();
    }

    @Deprecated
    default public float getLineSpace() {
        return 1.0f;
    }

    public TextStyle getDefaultStyle();

    public TextStyle getSelectionColor();

    public TextStyle getFoundPatternColor();

    public TextStyle getHyperlinkColor();

    public HyperlinkStyle.HighlightMode getHyperlinkHighlightingMode();

    public boolean useInverseSelectionColor();

    public boolean copyOnSelect();

    public boolean pasteOnMiddleMouseClick();

    public boolean emulateX11CopyPaste();

    public boolean useAntialiasing();

    public int maxRefreshRate();

    public boolean audibleBell();

    public boolean enableMouseReporting();

    public int caretBlinkingMs();

    public boolean scrollToBottomOnTyping();

    public boolean DECCompatibilityMode();

    public boolean forceActionOnMouseReporting();

    public int getBufferMaxLinesCount();

    public boolean altSendsEscape();

    public boolean ambiguousCharsAreDoubleWidth();
}

