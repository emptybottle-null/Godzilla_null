/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.CursorShape;
import com.jediterm.terminal.RequestOrigin;
import com.jediterm.terminal.TerminalMode;
import com.jediterm.terminal.TerminalOutputStream;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.emulator.mouse.MouseFormat;
import com.jediterm.terminal.emulator.mouse.MouseMode;
import com.jediterm.terminal.model.StyleState;
import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Terminal {
    public void resize(@NotNull Dimension var1, @NotNull RequestOrigin var2);

    public void resize(@NotNull Dimension var1, @NotNull RequestOrigin var2, @NotNull CompletableFuture<?> var3);

    public void beep();

    public void backspace();

    public void horizontalTab();

    public void carriageReturn();

    public void newLine();

    public void mapCharsetToGL(int var1);

    public void mapCharsetToGR(int var1);

    public void designateCharacterSet(int var1, char var2);

    public void setAnsiConformanceLevel(int var1);

    public void writeDoubleByte(char[] var1) throws UnsupportedEncodingException;

    public void writeCharacters(String var1);

    public int distanceToLineEnd();

    public void reverseIndex();

    public void index();

    public void nextLine();

    public void fillScreen(char var1);

    public void saveCursor();

    public void restoreCursor();

    public void reset();

    public void characterAttributes(TextStyle var1);

    public void setScrollingRegion(int var1, int var2);

    public void scrollUp(int var1);

    public void scrollDown(int var1);

    public void resetScrollRegions();

    public void cursorHorizontalAbsolute(int var1);

    public void linePositionAbsolute(int var1);

    public void cursorPosition(int var1, int var2);

    public void cursorUp(int var1);

    public void cursorDown(int var1);

    public void cursorForward(int var1);

    public void cursorBackward(int var1);

    public void cursorShape(CursorShape var1);

    public void eraseInLine(int var1);

    public void deleteCharacters(int var1);

    public int getTerminalWidth();

    public int getTerminalHeight();

    public void eraseInDisplay(int var1);

    public void setModeEnabled(TerminalMode var1, boolean var2);

    public void disconnected();

    public int getCursorX();

    public int getCursorY();

    public void singleShiftSelect(int var1);

    public void setWindowTitle(String var1);

    public void setCurrentPath(String var1);

    public void clearScreen();

    public void setCursorVisible(boolean var1);

    public void useAlternateBuffer(boolean var1);

    public byte[] getCodeForKey(int var1, int var2);

    public void setApplicationArrowKeys(boolean var1);

    public void setApplicationKeypad(boolean var1);

    public void setAutoNewLine(boolean var1);

    public StyleState getStyleState();

    public void insertLines(int var1);

    public void deleteLines(int var1);

    public void setBlinkingCursor(boolean var1);

    public void eraseCharacters(int var1);

    public void insertBlankCharacters(int var1);

    public void clearTabStopAtCursor();

    public void clearAllTabStops();

    public void setTabStopAtCursor();

    public void writeUnwrappedString(String var1);

    public void setTerminalOutput(@Nullable TerminalOutputStream var1);

    public void setMouseMode(@NotNull MouseMode var1);

    public void setMouseFormat(MouseFormat var1);

    public void setAltSendsEscape(boolean var1);

    public void deviceStatusReport(String var1);

    public void deviceAttributes(byte[] var1);

    public void setLinkUriStarted(@NotNull String var1);

    public void setLinkUriFinished();
}

