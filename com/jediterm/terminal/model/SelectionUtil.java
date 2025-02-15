/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.jediterm.terminal.model.TerminalLine;
import com.jediterm.terminal.model.TerminalSelection;
import com.jediterm.terminal.model.TerminalTextBuffer;
import com.jediterm.terminal.util.Pair;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class SelectionUtil {
    private static final Logger LOG = Logger.getLogger(SelectionUtil.class);
    private static final List<Character> SEPARATORS = new ArrayList<Character>();

    public static List<Character> getDefaultSeparators() {
        return new ArrayList<Character>(SEPARATORS);
    }

    public static Pair<Point, Point> sortPoints(Point a, Point b) {
        if (a.y == b.y) {
            return Pair.create(a.x <= b.x ? a : b, a.x > b.x ? a : b);
        }
        return Pair.create(a.y < b.y ? a : b, a.y > b.y ? a : b);
    }

    public static String getSelectionText(TerminalSelection selection, TerminalTextBuffer terminalTextBuffer) {
        return SelectionUtil.getSelectionText(selection.getStart(), selection.getEnd(), terminalTextBuffer);
    }

    @NotNull
    public static String getSelectionText(@NotNull Point selectionStart, @NotNull Point selectionEnd, @NotNull TerminalTextBuffer terminalTextBuffer) {
        if (selectionStart == null) {
            SelectionUtil.$$$reportNull$$$0(0);
        }
        if (selectionEnd == null) {
            SelectionUtil.$$$reportNull$$$0(1);
        }
        if (terminalTextBuffer == null) {
            SelectionUtil.$$$reportNull$$$0(2);
        }
        Pair<Point, Point> pair = SelectionUtil.sortPoints(selectionStart, selectionEnd);
        ((Point)pair.first).y = Math.max(((Point)pair.first).y, -terminalTextBuffer.getHistoryLinesCount());
        pair = SelectionUtil.sortPoints((Point)pair.first, (Point)pair.second);
        Point top = (Point)pair.first;
        Point bottom = (Point)pair.second;
        StringBuilder selectionText = new StringBuilder();
        for (int i = top.y; i <= bottom.y; ++i) {
            TerminalLine line = terminalTextBuffer.getLine(i);
            String text = line.getText();
            if (i == top.y) {
                if (i == bottom.y) {
                    selectionText.append(SelectionUtil.processForSelection(text.substring(Math.min(text.length(), top.x), Math.min(text.length(), bottom.x))));
                } else {
                    selectionText.append(SelectionUtil.processForSelection(text.substring(Math.min(text.length(), top.x))));
                }
            } else if (i == bottom.y) {
                selectionText.append(SelectionUtil.processForSelection(text.substring(0, Math.min(text.length(), bottom.x))));
            } else {
                selectionText.append(SelectionUtil.processForSelection(line.getText()));
            }
            if ((line.isWrapped() || i >= bottom.y) && bottom.x <= text.length()) continue;
            selectionText.append("\n");
        }
        String string = selectionText.toString();
        if (string == null) {
            SelectionUtil.$$$reportNull$$$0(3);
        }
        return string;
    }

    private static String processForSelection(String text) {
        if (text.indexOf(57344) != 0) {
            StringBuilder sb = new StringBuilder();
            for (char c : text.toCharArray()) {
                if (c == '\ue000') continue;
                sb.append(c);
            }
            return sb.toString();
        }
        return text;
    }

    public static Point getPreviousSeparator(Point charCoords, TerminalTextBuffer terminalTextBuffer) {
        return SelectionUtil.getPreviousSeparator(charCoords, terminalTextBuffer, SEPARATORS);
    }

    public static Point getPreviousSeparator(Point charCoords, TerminalTextBuffer terminalTextBuffer, @NotNull List<Character> separators) {
        if (separators == null) {
            SelectionUtil.$$$reportNull$$$0(4);
        }
        int x = charCoords.x;
        int y = charCoords.y;
        int terminalWidth = terminalTextBuffer.getWidth();
        if (separators.contains(Character.valueOf(terminalTextBuffer.getBuffersCharAt(x, y)))) {
            return new Point(x, y);
        }
        String line = terminalTextBuffer.getLine(y).getText();
        while (x < line.length() && !separators.contains(Character.valueOf(line.charAt(x)))) {
            if (--x >= 0) continue;
            if (y <= -terminalTextBuffer.getHistoryLinesCount()) {
                return new Point(0, y);
            }
            x = terminalWidth - 1;
            line = terminalTextBuffer.getLine(--y).getText();
        }
        if (++x >= terminalWidth) {
            ++y;
            x = 0;
        }
        return new Point(x, y);
    }

    public static Point getNextSeparator(Point charCoords, TerminalTextBuffer terminalTextBuffer) {
        return SelectionUtil.getNextSeparator(charCoords, terminalTextBuffer, SEPARATORS);
    }

    public static Point getNextSeparator(Point charCoords, TerminalTextBuffer terminalTextBuffer, @NotNull List<Character> separators) {
        if (separators == null) {
            SelectionUtil.$$$reportNull$$$0(5);
        }
        int x = charCoords.x;
        int y = charCoords.y;
        int terminalWidth = terminalTextBuffer.getWidth();
        int terminalHeight = terminalTextBuffer.getHeight();
        if (separators.contains(Character.valueOf(terminalTextBuffer.getBuffersCharAt(x, y)))) {
            return new Point(x, y);
        }
        String line = terminalTextBuffer.getLine(y).getText();
        while (x < line.length() && !separators.contains(Character.valueOf(line.charAt(x)))) {
            if (++x < terminalWidth) continue;
            if (y >= terminalHeight - 1) {
                return new Point(terminalWidth - 1, terminalHeight - 1);
            }
            x = 0;
            line = terminalTextBuffer.getLine(++y).getText();
        }
        if (--x < 0) {
            --y;
            x = terminalWidth - 1;
        }
        return new Point(x, y);
    }

    static {
        SEPARATORS.add(Character.valueOf(' '));
        SEPARATORS.add(Character.valueOf('\u00a0'));
        SEPARATORS.add(Character.valueOf('\t'));
        SEPARATORS.add(Character.valueOf('\''));
        SEPARATORS.add(Character.valueOf('\"'));
        SEPARATORS.add(Character.valueOf('$'));
        SEPARATORS.add(Character.valueOf('('));
        SEPARATORS.add(Character.valueOf(')'));
        SEPARATORS.add(Character.valueOf('['));
        SEPARATORS.add(Character.valueOf(']'));
        SEPARATORS.add(Character.valueOf('{'));
        SEPARATORS.add(Character.valueOf('}'));
        SEPARATORS.add(Character.valueOf('<'));
        SEPARATORS.add(Character.valueOf('>'));
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
            case 3: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "selectionStart";
                break;
            }
            case 1: {
                objectArray2 = objectArray3;
                objectArray3[0] = "selectionEnd";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "terminalTextBuffer";
                break;
            }
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/model/SelectionUtil";
                break;
            }
            case 4: 
            case 5: {
                objectArray2 = objectArray3;
                objectArray3[0] = "separators";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/model/SelectionUtil";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getSelectionText";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "getSelectionText";
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                objectArray = objectArray;
                objectArray[2] = "getPreviousSeparator";
                break;
            }
            case 5: {
                objectArray = objectArray;
                objectArray[2] = "getNextSeparator";
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 3: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

