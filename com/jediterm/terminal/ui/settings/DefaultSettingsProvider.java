/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui.settings;

import com.jediterm.terminal.HyperlinkStyle;
import com.jediterm.terminal.TerminalColor;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.emulator.ColorPalette;
import com.jediterm.terminal.emulator.ColorPaletteImpl;
import com.jediterm.terminal.ui.TerminalActionPresentation;
import com.jediterm.terminal.ui.UIUtil;
import com.jediterm.terminal.ui.settings.SettingsProvider;
import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import javax.swing.KeyStroke;
import org.jetbrains.annotations.NotNull;

public class DefaultSettingsProvider
implements SettingsProvider {
    @Override
    @NotNull
    public TerminalActionPresentation getNewSessionActionPresentation() {
        return new TerminalActionPresentation("New Session", UIUtil.isMac ? KeyStroke.getKeyStroke(84, 256) : KeyStroke.getKeyStroke(84, 192));
    }

    @Override
    @NotNull
    public TerminalActionPresentation getOpenUrlActionPresentation() {
        return new TerminalActionPresentation("Open as URL", Collections.emptyList());
    }

    @Override
    @NotNull
    public TerminalActionPresentation getCopyActionPresentation() {
        KeyStroke keyStroke = UIUtil.isMac ? KeyStroke.getKeyStroke(67, 256) : KeyStroke.getKeyStroke(67, 192);
        return new TerminalActionPresentation("Copy", keyStroke);
    }

    @Override
    @NotNull
    public TerminalActionPresentation getPasteActionPresentation() {
        KeyStroke keyStroke = UIUtil.isMac ? KeyStroke.getKeyStroke(86, 256) : KeyStroke.getKeyStroke(86, 192);
        return new TerminalActionPresentation("Paste", keyStroke);
    }

    @Override
    @NotNull
    public TerminalActionPresentation getClearBufferActionPresentation() {
        return new TerminalActionPresentation("Clear Buffer", UIUtil.isMac ? KeyStroke.getKeyStroke(75, 256) : KeyStroke.getKeyStroke(76, 128));
    }

    @Override
    @NotNull
    public TerminalActionPresentation getPageUpActionPresentation() {
        return new TerminalActionPresentation("Page Up", KeyStroke.getKeyStroke(33, 64));
    }

    @Override
    @NotNull
    public TerminalActionPresentation getPageDownActionPresentation() {
        return new TerminalActionPresentation("Page Down", KeyStroke.getKeyStroke(34, 64));
    }

    @Override
    @NotNull
    public TerminalActionPresentation getLineUpActionPresentation() {
        return new TerminalActionPresentation("Line Up", UIUtil.isMac ? KeyStroke.getKeyStroke(38, 256) : KeyStroke.getKeyStroke(38, 128));
    }

    @Override
    @NotNull
    public TerminalActionPresentation getLineDownActionPresentation() {
        return new TerminalActionPresentation("Line Down", UIUtil.isMac ? KeyStroke.getKeyStroke(40, 256) : KeyStroke.getKeyStroke(40, 128));
    }

    @Override
    @NotNull
    public TerminalActionPresentation getCloseSessionActionPresentation() {
        return new TerminalActionPresentation("Close Session", UIUtil.isMac ? KeyStroke.getKeyStroke(87, 256) : KeyStroke.getKeyStroke(87, 192));
    }

    @Override
    @NotNull
    public TerminalActionPresentation getFindActionPresentation() {
        return new TerminalActionPresentation("Find", UIUtil.isMac ? KeyStroke.getKeyStroke(70, 256) : KeyStroke.getKeyStroke(70, 128));
    }

    @Override
    @NotNull
    public TerminalActionPresentation getSelectAllActionPresentation() {
        return new TerminalActionPresentation("Select All", Collections.emptyList());
    }

    @Override
    public ColorPalette getTerminalColorPalette() {
        return UIUtil.isWindows ? ColorPaletteImpl.WINDOWS_PALETTE : ColorPaletteImpl.XTERM_PALETTE;
    }

    @Override
    public Font getTerminalFont() {
        String fontName = UIUtil.isWindows ? "Consolas" : (UIUtil.isMac ? "Menlo" : "Monospaced");
        return new Font(fontName, 0, (int)this.getTerminalFontSize());
    }

    @Override
    public float getTerminalFontSize() {
        return 14.0f;
    }

    @Override
    public TextStyle getDefaultStyle() {
        return new TextStyle(TerminalColor.BLACK, TerminalColor.WHITE);
    }

    @Override
    public TextStyle getSelectionColor() {
        return new TextStyle(TerminalColor.WHITE, TerminalColor.rgb(82, 109, 165));
    }

    @Override
    public TextStyle getFoundPatternColor() {
        return new TextStyle(TerminalColor.BLACK, TerminalColor.rgb(255, 255, 0));
    }

    @Override
    public TextStyle getHyperlinkColor() {
        return new TextStyle(TerminalColor.awt(Color.BLUE), TerminalColor.WHITE);
    }

    @Override
    public HyperlinkStyle.HighlightMode getHyperlinkHighlightingMode() {
        return HyperlinkStyle.HighlightMode.HOVER;
    }

    @Override
    public boolean useInverseSelectionColor() {
        return true;
    }

    @Override
    public boolean copyOnSelect() {
        return this.emulateX11CopyPaste();
    }

    @Override
    public boolean pasteOnMiddleMouseClick() {
        return this.emulateX11CopyPaste();
    }

    @Override
    public boolean emulateX11CopyPaste() {
        return false;
    }

    @Override
    public boolean useAntialiasing() {
        return true;
    }

    @Override
    public int maxRefreshRate() {
        return 50;
    }

    @Override
    public boolean audibleBell() {
        return true;
    }

    @Override
    public boolean enableMouseReporting() {
        return true;
    }

    @Override
    public int caretBlinkingMs() {
        return 505;
    }

    @Override
    public boolean scrollToBottomOnTyping() {
        return true;
    }

    @Override
    public boolean DECCompatibilityMode() {
        return true;
    }

    @Override
    public boolean forceActionOnMouseReporting() {
        return false;
    }

    @Override
    public int getBufferMaxLinesCount() {
        return 5000;
    }

    @Override
    public boolean altSendsEscape() {
        return true;
    }

    @Override
    public boolean ambiguousCharsAreDoubleWidth() {
        return false;
    }
}

