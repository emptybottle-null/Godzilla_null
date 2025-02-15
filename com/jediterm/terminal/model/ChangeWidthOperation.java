/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.google.common.base.Preconditions;
import com.jediterm.terminal.model.LinesBuffer;
import com.jediterm.terminal.model.TerminalLine;
import com.jediterm.terminal.model.TerminalTextBuffer;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

class ChangeWidthOperation {
    private static final Logger LOG = Logger.getLogger(TerminalTextBuffer.class);
    private final TerminalTextBuffer myTextBuffer;
    private final int myNewWidth;
    private final int myNewHeight;
    private final Map<Point, Point> myTrackingPoints;
    private final List<TerminalLine> myAllLines;
    private TerminalLine myCurrentLine;
    private int myCurrentLineLength;

    ChangeWidthOperation(@NotNull TerminalTextBuffer textBuffer, int newWidth, int newHeight) {
        if (textBuffer == null) {
            ChangeWidthOperation.$$$reportNull$$$0(0);
        }
        this.myTrackingPoints = new HashMap<Point, Point>();
        this.myAllLines = new ArrayList<TerminalLine>();
        this.myTextBuffer = textBuffer;
        this.myNewWidth = newWidth;
        this.myNewHeight = newHeight;
    }

    void addPointToTrack(@NotNull Point original) {
        if (original == null) {
            ChangeWidthOperation.$$$reportNull$$$0(1);
        }
        this.myTrackingPoints.put(new Point(original), null);
    }

    @NotNull
    Point getTrackedPoint(@NotNull Point original) {
        Point result;
        if (original == null) {
            ChangeWidthOperation.$$$reportNull$$$0(2);
        }
        if ((result = this.myTrackingPoints.get(new Point(original))) == null) {
            LOG.warn("Not tracked point: " + original);
            Point point = original;
            if (point == null) {
                ChangeWidthOperation.$$$reportNull$$$0(3);
            }
            return point;
        }
        Point point = result;
        if (point == null) {
            ChangeWidthOperation.$$$reportNull$$$0(4);
        }
        return point;
    }

    void run() {
        int newY;
        int newX;
        List<Point> points;
        int i;
        LinesBuffer historyBuffer = this.myTextBuffer.getHistoryBufferOrBackup();
        for (int i2 = 0; i2 < historyBuffer.getLineCount(); ++i2) {
            TerminalLine line = historyBuffer.getLine(i2);
            this.addLine(line);
        }
        int screenStartInd = this.myAllLines.size() - 1;
        if (this.myCurrentLine == null || this.myCurrentLineLength == this.myNewWidth) {
            ++screenStartInd;
        }
        Preconditions.checkState(screenStartInd >= 0, "screenStartInd < 0: %d", screenStartInd);
        LinesBuffer screenBuffer = this.myTextBuffer.getScreenBufferOrBackup();
        if (screenBuffer.getLineCount() > this.myTextBuffer.getHeight()) {
            LOG.warn("Terminal height < screen buffer line count: " + this.myTextBuffer.getHeight() + " < " + screenBuffer.getLineCount());
        }
        int oldScreenLineCount = Math.min(screenBuffer.getLineCount(), this.myTextBuffer.getHeight());
        for (i = 0; i < oldScreenLineCount; ++i) {
            points = this.findPointsAtY(i);
            for (Point point : points) {
                newX = (this.myCurrentLineLength + point.x) % this.myNewWidth;
                newY = this.myAllLines.size() + (this.myCurrentLineLength + point.x) / this.myNewWidth;
                if (this.myCurrentLine != null) {
                    --newY;
                }
                this.myTrackingPoints.put(point, new Point(newX, newY));
            }
            this.addLine(screenBuffer.getLine(i));
        }
        for (i = oldScreenLineCount; i < this.myTextBuffer.getHeight(); ++i) {
            points = this.findPointsAtY(i);
            for (Point point : points) {
                newX = point.x % this.myNewWidth;
                newY = i - oldScreenLineCount + this.myAllLines.size() + point.x / this.myNewWidth;
                this.myTrackingPoints.put(point, new Point(newX, newY));
            }
        }
        int emptyBottomLineCount = this.getEmptyBottomLineCount();
        screenStartInd = Math.max(screenStartInd, this.myAllLines.size() - Math.min(this.myAllLines.size(), this.myNewHeight) - emptyBottomLineCount);
        screenStartInd = Math.min(screenStartInd, this.myAllLines.size() - Math.min(this.myAllLines.size(), this.myNewHeight));
        historyBuffer.clearAll();
        historyBuffer.addLines(this.myAllLines.subList(0, screenStartInd));
        screenBuffer.clearAll();
        screenBuffer.addLines(this.myAllLines.subList(screenStartInd, Math.min(screenStartInd + this.myNewHeight, this.myAllLines.size())));
        for (Map.Entry<Point, Point> entry : this.myTrackingPoints.entrySet()) {
            Point p = entry.getValue();
            if (p != null) {
                p.y -= screenStartInd;
            } else {
                p = new Point(entry.getKey());
                entry.setValue(p);
            }
            p.x = Math.min(this.myNewWidth, Math.max(0, p.x));
            p.y = Math.min(this.myNewHeight, Math.max(0, p.y));
        }
    }

    private int getEmptyBottomLineCount() {
        int result;
        for (result = 0; result < this.myAllLines.size() && this.myAllLines.get(this.myAllLines.size() - result - 1).isNul(); ++result) {
        }
        return result;
    }

    @NotNull
    private List<Point> findPointsAtY(int y) {
        List<Point> result = Collections.emptyList();
        for (Point key : this.myTrackingPoints.keySet()) {
            if (key.y != y) continue;
            if (result.isEmpty()) {
                result = new ArrayList<Point>();
            }
            result.add(key);
        }
        List<Point> list = result;
        if (list == null) {
            ChangeWidthOperation.$$$reportNull$$$0(5);
        }
        return list;
    }

    private void addLine(@NotNull TerminalLine line) {
        if (line == null) {
            ChangeWidthOperation.$$$reportNull$$$0(6);
        }
        if (line.isNul()) {
            if (this.myCurrentLine != null) {
                this.myCurrentLine = null;
                this.myCurrentLineLength = 0;
            }
            this.myAllLines.add(TerminalLine.createEmpty());
            return;
        }
        line.forEachEntry(entry -> {
            int len;
            if (entry.isNul()) {
                return;
            }
            for (int entryProcessedLength = 0; entryProcessedLength < entry.getLength(); entryProcessedLength += len) {
                if (this.myCurrentLine != null && this.myCurrentLineLength == this.myNewWidth) {
                    this.myCurrentLine.setWrapped(true);
                    this.myCurrentLine = null;
                    this.myCurrentLineLength = 0;
                }
                if (this.myCurrentLine == null) {
                    this.myCurrentLine = new TerminalLine();
                    this.myCurrentLineLength = 0;
                    this.myAllLines.add(this.myCurrentLine);
                }
                len = Math.min(this.myNewWidth - this.myCurrentLineLength, entry.getLength() - entryProcessedLength);
                TerminalLine.TextEntry newEntry = ChangeWidthOperation.subEntry(entry, entryProcessedLength, len);
                this.myCurrentLine.appendEntry(newEntry);
                this.myCurrentLineLength += len;
            }
        });
        if (!line.isWrapped()) {
            this.myCurrentLine = null;
            this.myCurrentLineLength = 0;
        }
    }

    @NotNull
    private static TerminalLine.TextEntry subEntry(@NotNull TerminalLine.TextEntry entry, int startInd, int count) {
        if (entry == null) {
            ChangeWidthOperation.$$$reportNull$$$0(7);
        }
        if (startInd == 0 && count == entry.getLength()) {
            TerminalLine.TextEntry textEntry = entry;
            if (textEntry == null) {
                ChangeWidthOperation.$$$reportNull$$$0(8);
            }
            return textEntry;
        }
        return new TerminalLine.TextEntry(entry.getStyle(), entry.getText().subBuffer(startInd, count));
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
            case 3: 
            case 4: 
            case 5: 
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
            case 3: 
            case 4: 
            case 5: 
            case 8: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "textBuffer";
                break;
            }
            case 1: 
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "original";
                break;
            }
            case 3: 
            case 4: 
            case 5: 
            case 8: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/model/ChangeWidthOperation";
                break;
            }
            case 6: {
                objectArray2 = objectArray3;
                objectArray3[0] = "line";
                break;
            }
            case 7: {
                objectArray2 = objectArray3;
                objectArray3[0] = "entry";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/model/ChangeWidthOperation";
                break;
            }
            case 3: 
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getTrackedPoint";
                break;
            }
            case 5: {
                objectArray = objectArray2;
                objectArray2[1] = "findPointsAtY";
                break;
            }
            case 8: {
                objectArray = objectArray2;
                objectArray2[1] = "subEntry";
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
                objectArray = objectArray;
                objectArray[2] = "addPointToTrack";
                break;
            }
            case 2: {
                objectArray = objectArray;
                objectArray[2] = "getTrackedPoint";
                break;
            }
            case 3: 
            case 4: 
            case 5: 
            case 8: {
                break;
            }
            case 6: {
                objectArray = objectArray;
                objectArray[2] = "addLine";
                break;
            }
            case 7: {
                objectArray = objectArray;
                objectArray[2] = "subEntry";
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 3: 
            case 4: 
            case 5: 
            case 8: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

