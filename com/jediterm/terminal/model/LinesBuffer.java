/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.google.common.collect.Lists;
import com.jediterm.terminal.StyledTextConsumer;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.model.CharBuffer;
import com.jediterm.terminal.model.TerminalLine;
import com.jediterm.terminal.model.hyperlinks.TextProcessing;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LinesBuffer {
    private static final Logger LOG = Logger.getLogger(LinesBuffer.class);
    public static final int DEFAULT_MAX_LINES_COUNT = 5000;
    private int myBufferMaxLinesCount = 5000;
    private ArrayList<TerminalLine> myLines = Lists.newArrayList();
    @Nullable
    private final TextProcessing myTextProcessing;

    public LinesBuffer(@Nullable TextProcessing textProcessing) {
        this.myTextProcessing = textProcessing;
    }

    public LinesBuffer(int bufferMaxLinesCount, @Nullable TextProcessing textProcessing) {
        this.myBufferMaxLinesCount = bufferMaxLinesCount;
        this.myTextProcessing = textProcessing;
    }

    public synchronized String getLines() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (TerminalLine line : this.myLines) {
            if (!first) {
                sb.append("\n");
            }
            sb.append(line.getText());
            first = false;
        }
        return sb.toString();
    }

    public synchronized void addNewLine(@NotNull TextStyle style, @NotNull CharBuffer characters) {
        if (style == null) {
            LinesBuffer.$$$reportNull$$$0(0);
        }
        if (characters == null) {
            LinesBuffer.$$$reportNull$$$0(1);
        }
        this.addNewLine(new TerminalLine.TextEntry(style, characters));
    }

    private synchronized void addNewLine(@NotNull TerminalLine.TextEntry entry) {
        if (entry == null) {
            LinesBuffer.$$$reportNull$$$0(2);
        }
        this.addLine(new TerminalLine(entry));
    }

    private synchronized void addLine(@NotNull TerminalLine line) {
        if (line == null) {
            LinesBuffer.$$$reportNull$$$0(3);
        }
        if (this.myBufferMaxLinesCount > 0 && this.myLines.size() >= this.myBufferMaxLinesCount) {
            this.removeTopLines(1);
        }
        this.myLines.add(line);
    }

    public synchronized int getLineCount() {
        return this.myLines.size();
    }

    public synchronized void removeTopLines(int count) {
        this.myLines = count >= this.myLines.size() ? Lists.newArrayList() : Lists.newArrayList(this.myLines.subList(count, this.myLines.size()));
    }

    public String getLineText(int row) {
        TerminalLine line = this.getLine(row);
        return line.getText();
    }

    public synchronized void insertLines(int y, int count, int lastLine, @NotNull TerminalLine.TextEntry filler) {
        if (filler == null) {
            LinesBuffer.$$$reportNull$$$0(4);
        }
        LinesBuffer tail = new LinesBuffer(this.myTextProcessing);
        if (lastLine < this.getLineCount() - 1) {
            this.moveBottomLinesTo(this.getLineCount() - lastLine - 1, tail);
        }
        LinesBuffer head = new LinesBuffer(this.myTextProcessing);
        if (y > 0) {
            this.moveTopLinesTo(y, head);
        }
        for (int i = 0; i < count; ++i) {
            head.addNewLine(filler);
        }
        head.moveBottomLinesTo(head.getLineCount(), this);
        this.removeBottomLines(count);
        tail.moveTopLinesTo(tail.getLineCount(), this);
    }

    public synchronized LinesBuffer deleteLines(int y, int count, int lastLine, @NotNull TerminalLine.TextEntry filler) {
        if (filler == null) {
            LinesBuffer.$$$reportNull$$$0(5);
        }
        LinesBuffer tail = new LinesBuffer(this.myTextProcessing);
        if (lastLine < this.getLineCount() - 1) {
            this.moveBottomLinesTo(this.getLineCount() - lastLine - 1, tail);
        }
        LinesBuffer head = new LinesBuffer(this.myTextProcessing);
        if (y > 0) {
            this.moveTopLinesTo(y, head);
        }
        int toRemove = Math.min(count, this.getLineCount());
        LinesBuffer removed = new LinesBuffer(this.myTextProcessing);
        this.moveTopLinesTo(toRemove, removed);
        head.moveBottomLinesTo(head.getLineCount(), this);
        for (int i = 0; i < toRemove; ++i) {
            this.addNewLine(filler);
        }
        tail.moveTopLinesTo(tail.getLineCount(), this);
        return removed;
    }

    public synchronized void writeString(int x, int y, CharBuffer str, @NotNull TextStyle style) {
        if (style == null) {
            LinesBuffer.$$$reportNull$$$0(6);
        }
        TerminalLine line = this.getLine(y);
        line.writeString(x, str, style);
        if (this.myTextProcessing != null) {
            this.myTextProcessing.processHyperlinks(this, line);
        }
    }

    public synchronized void clearLines(int startRow, int endRow, @NotNull TerminalLine.TextEntry filler) {
        if (filler == null) {
            LinesBuffer.$$$reportNull$$$0(7);
        }
        for (int i = startRow; i <= endRow; ++i) {
            this.getLine(i).clear(filler);
        }
    }

    public synchronized void clearAll() {
        this.myLines.clear();
    }

    public synchronized void deleteCharacters(int x, int y, int count, @NotNull TextStyle style) {
        if (style == null) {
            LinesBuffer.$$$reportNull$$$0(8);
        }
        TerminalLine line = this.getLine(y);
        line.deleteCharacters(x, count, style);
    }

    public synchronized void insertBlankCharacters(int x, int y, int count, int maxLen, @NotNull TextStyle style) {
        if (style == null) {
            LinesBuffer.$$$reportNull$$$0(9);
        }
        TerminalLine line = this.getLine(y);
        line.insertBlankCharacters(x, count, maxLen, style);
    }

    public synchronized void clearArea(int leftX, int topY, int rightX, int bottomY, @NotNull TextStyle style) {
        if (style == null) {
            LinesBuffer.$$$reportNull$$$0(10);
        }
        for (int y = topY; y < bottomY; ++y) {
            TerminalLine line = this.getLine(y);
            line.clearArea(leftX, rightX, style);
        }
    }

    public synchronized void processLines(int yStart, int yCount, @NotNull StyledTextConsumer consumer) {
        if (consumer == null) {
            LinesBuffer.$$$reportNull$$$0(11);
        }
        this.processLines(yStart, yCount, consumer, -this.getLineCount());
    }

    public synchronized void processLines(int firstLine, int count, @NotNull StyledTextConsumer consumer, int startRow) {
        if (consumer == null) {
            LinesBuffer.$$$reportNull$$$0(12);
        }
        if (firstLine < 0) {
            throw new IllegalArgumentException("firstLine=" + firstLine + ", should be >0");
        }
        for (int y = firstLine; y < Math.min(firstLine + count, this.myLines.size()); ++y) {
            this.myLines.get(y).process(y, consumer, startRow);
        }
    }

    public synchronized void moveTopLinesTo(int count, @NotNull LinesBuffer buffer) {
        if (buffer == null) {
            LinesBuffer.$$$reportNull$$$0(13);
        }
        count = Math.min(count, this.getLineCount());
        buffer.addLines(this.myLines.subList(0, count));
        this.removeTopLines(count);
    }

    public synchronized void addLines(@NotNull List<TerminalLine> lines) {
        if (lines == null) {
            LinesBuffer.$$$reportNull$$$0(14);
        }
        if (this.myBufferMaxLinesCount > 0) {
            if (lines.size() >= this.myBufferMaxLinesCount) {
                int index = lines.size() - this.myBufferMaxLinesCount;
                this.myLines = Lists.newArrayList(lines.subList(index, lines.size()));
                return;
            }
            int count = this.myLines.size() + lines.size();
            if (count >= this.myBufferMaxLinesCount) {
                this.removeTopLines(count - this.myBufferMaxLinesCount);
            }
        }
        this.myLines.addAll(lines);
    }

    @NotNull
    public synchronized TerminalLine getLine(int row) {
        if (row < 0) {
            LOG.error("Negative line number: " + row);
            TerminalLine terminalLine = TerminalLine.createEmpty();
            if (terminalLine == null) {
                LinesBuffer.$$$reportNull$$$0(15);
            }
            return terminalLine;
        }
        for (int i = this.getLineCount(); i <= row; ++i) {
            this.addLine(TerminalLine.createEmpty());
        }
        TerminalLine terminalLine = this.myLines.get(row);
        if (terminalLine == null) {
            LinesBuffer.$$$reportNull$$$0(16);
        }
        return terminalLine;
    }

    public synchronized void moveBottomLinesTo(int count, @NotNull LinesBuffer buffer) {
        if (buffer == null) {
            LinesBuffer.$$$reportNull$$$0(17);
        }
        count = Math.min(count, this.getLineCount());
        buffer.addLinesFirst(this.myLines.subList(this.getLineCount() - count, this.getLineCount()));
        this.removeBottomLines(count);
    }

    private synchronized void addLinesFirst(@NotNull List<TerminalLine> lines) {
        if (lines == null) {
            LinesBuffer.$$$reportNull$$$0(18);
        }
        ArrayList<TerminalLine> list = Lists.newArrayList(lines);
        list.addAll(this.myLines);
        this.myLines = Lists.newArrayList(list);
    }

    private synchronized void removeBottomLines(int count) {
        this.myLines = Lists.newArrayList(this.myLines.subList(0, this.getLineCount() - count));
    }

    public int removeBottomEmptyLines(int ind, int maxCount) {
        int i = 0;
        while (maxCount - i > 0 && (ind >= this.myLines.size() || this.myLines.get(ind).isNul())) {
            if (ind < this.myLines.size()) {
                this.myLines.remove(ind);
            }
            --ind;
            ++i;
        }
        return i;
    }

    synchronized int findLineIndex(@NotNull TerminalLine line) {
        if (line == null) {
            LinesBuffer.$$$reportNull$$$0(19);
        }
        return this.myLines.indexOf(line);
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
            case 15: 
            case 16: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 15: 
            case 16: {
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
                objectArray3[0] = "characters";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "entry";
                break;
            }
            case 3: 
            case 19: {
                objectArray2 = objectArray3;
                objectArray3[0] = "line";
                break;
            }
            case 4: 
            case 5: 
            case 7: {
                objectArray2 = objectArray3;
                objectArray3[0] = "filler";
                break;
            }
            case 11: 
            case 12: {
                objectArray2 = objectArray3;
                objectArray3[0] = "consumer";
                break;
            }
            case 13: 
            case 17: {
                objectArray2 = objectArray3;
                objectArray3[0] = "buffer";
                break;
            }
            case 14: 
            case 18: {
                objectArray2 = objectArray3;
                objectArray3[0] = "lines";
                break;
            }
            case 15: 
            case 16: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/model/LinesBuffer";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/model/LinesBuffer";
                break;
            }
            case 15: 
            case 16: {
                objectArray = objectArray2;
                objectArray2[1] = "getLine";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "addNewLine";
                break;
            }
            case 3: {
                objectArray = objectArray;
                objectArray[2] = "addLine";
                break;
            }
            case 4: {
                objectArray = objectArray;
                objectArray[2] = "insertLines";
                break;
            }
            case 5: {
                objectArray = objectArray;
                objectArray[2] = "deleteLines";
                break;
            }
            case 6: {
                objectArray = objectArray;
                objectArray[2] = "writeString";
                break;
            }
            case 7: {
                objectArray = objectArray;
                objectArray[2] = "clearLines";
                break;
            }
            case 8: {
                objectArray = objectArray;
                objectArray[2] = "deleteCharacters";
                break;
            }
            case 9: {
                objectArray = objectArray;
                objectArray[2] = "insertBlankCharacters";
                break;
            }
            case 10: {
                objectArray = objectArray;
                objectArray[2] = "clearArea";
                break;
            }
            case 11: 
            case 12: {
                objectArray = objectArray;
                objectArray[2] = "processLines";
                break;
            }
            case 13: {
                objectArray = objectArray;
                objectArray[2] = "moveTopLinesTo";
                break;
            }
            case 14: {
                objectArray = objectArray;
                objectArray[2] = "addLines";
                break;
            }
            case 15: 
            case 16: {
                break;
            }
            case 17: {
                objectArray = objectArray;
                objectArray[2] = "moveBottomLinesTo";
                break;
            }
            case 18: {
                objectArray = objectArray;
                objectArray[2] = "addLinesFirst";
                break;
            }
            case 19: {
                objectArray = objectArray;
                objectArray[2] = "findLineIndex";
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 15: 
            case 16: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

