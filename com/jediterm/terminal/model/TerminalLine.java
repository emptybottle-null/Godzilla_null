/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.jediterm.terminal.StyledTextConsumer;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.model.CharBuffer;
import com.jediterm.terminal.model.SubCharBuffer;
import com.jediterm.terminal.model.TerminalLineIntervalHighlighting;
import com.jediterm.terminal.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TerminalLine {
    private static final Logger LOG = Logger.getLogger(TerminalLine.class);
    private TextEntries myTextEntries;
    private boolean myWrapped;
    private final List<TerminalLineIntervalHighlighting> myCustomHighlightings;

    public TerminalLine() {
        this.myTextEntries = new TextEntries();
        this.myWrapped = false;
        this.myCustomHighlightings = new ArrayList<TerminalLineIntervalHighlighting>();
    }

    public TerminalLine(@NotNull TextEntry entry) {
        if (entry == null) {
            TerminalLine.$$$reportNull$$$0(0);
        }
        this.myTextEntries = new TextEntries();
        this.myWrapped = false;
        this.myCustomHighlightings = new ArrayList<TerminalLineIntervalHighlighting>();
        this.myTextEntries.add(entry);
    }

    public static TerminalLine createEmpty() {
        return new TerminalLine();
    }

    public synchronized String getText() {
        StringBuilder sb = new StringBuilder();
        for (TextEntry textEntry : Lists.newArrayList(this.myTextEntries)) {
            if (textEntry.getText().isNul()) break;
            sb.append(textEntry.getText());
        }
        return sb.toString();
    }

    public char charAt(int x) {
        String text = this.getText();
        return x < text.length() ? text.charAt(x) : (char)' ';
    }

    public boolean isWrapped() {
        return this.myWrapped;
    }

    public void setWrapped(boolean wrapped) {
        this.myWrapped = wrapped;
    }

    public synchronized void clear(@NotNull TextEntry filler) {
        if (filler == null) {
            TerminalLine.$$$reportNull$$$0(1);
        }
        this.myTextEntries.clear();
        this.myTextEntries.add(filler);
        this.setWrapped(false);
    }

    public void writeString(int x, @NotNull CharBuffer str, @NotNull TextStyle style) {
        if (str == null) {
            TerminalLine.$$$reportNull$$$0(2);
        }
        if (style == null) {
            TerminalLine.$$$reportNull$$$0(3);
        }
        this.writeCharacters(x, style, str);
    }

    private synchronized void writeCharacters(int x, @NotNull TextStyle style, @NotNull CharBuffer characters) {
        int len;
        if (style == null) {
            TerminalLine.$$$reportNull$$$0(4);
        }
        if (characters == null) {
            TerminalLine.$$$reportNull$$$0(5);
        }
        if (x >= (len = this.myTextEntries.length())) {
            if (x - len > 0) {
                this.myTextEntries.add(new TextEntry(TextStyle.EMPTY, new CharBuffer('\u0000', x - len)));
            }
            this.myTextEntries.add(new TextEntry(style, characters));
        } else {
            len = Math.max(len, x + characters.length());
            this.myTextEntries = TerminalLine.merge(x, characters, style, this.myTextEntries, len);
        }
    }

    private static TextEntries merge(int x, @NotNull CharBuffer str, @NotNull TextStyle style, @NotNull TextEntries entries, int lineLength) {
        if (str == null) {
            TerminalLine.$$$reportNull$$$0(6);
        }
        if (style == null) {
            TerminalLine.$$$reportNull$$$0(7);
        }
        if (entries == null) {
            TerminalLine.$$$reportNull$$$0(8);
        }
        Pair<char[], TextStyle[]> pair = TerminalLine.toBuf(entries, lineLength);
        for (int i = 0; i < str.length(); ++i) {
            ((char[])pair.first)[i + x] = str.charAt(i);
            ((TextStyle[])pair.second)[i + x] = style;
        }
        return TerminalLine.collectFromBuffer((char[])pair.first, (TextStyle[])pair.second);
    }

    private static Pair<char[], TextStyle[]> toBuf(TextEntries entries, int lineLength) {
        Pair<char[], TextStyle[]> pair = Pair.create(new char[lineLength], new TextStyle[lineLength]);
        int p = 0;
        for (TextEntry entry : entries) {
            for (int i = 0; i < entry.getLength(); ++i) {
                ((char[])pair.first)[p + i] = entry.getText().charAt(i);
                ((TextStyle[])pair.second)[p + i] = entry.getStyle();
            }
            p += entry.getLength();
        }
        return pair;
    }

    private static TextEntries collectFromBuffer(char[] buf, @NotNull TextStyle[] styles) {
        if (styles == null) {
            TerminalLine.$$$reportNull$$$0(9);
        }
        TextEntries result = new TextEntries();
        TextStyle curStyle = styles[0];
        int start = 0;
        for (int i = 1; i < buf.length; ++i) {
            if (styles[i] == curStyle) continue;
            result.add(new TextEntry(curStyle, new CharBuffer(buf, start, i - start)));
            curStyle = styles[i];
            start = i;
        }
        result.add(new TextEntry(curStyle, new CharBuffer(buf, start, buf.length - start)));
        return result;
    }

    public synchronized void deleteCharacters(int x) {
        this.deleteCharacters(x, TextStyle.EMPTY);
    }

    public synchronized void deleteCharacters(int x, @NotNull TextStyle style) {
        if (style == null) {
            TerminalLine.$$$reportNull$$$0(10);
        }
        this.deleteCharacters(x, this.myTextEntries.length() - x, style);
        this.setWrapped(false);
    }

    public synchronized void deleteCharacters(int x, int count, @NotNull TextStyle style) {
        if (style == null) {
            TerminalLine.$$$reportNull$$$0(11);
        }
        int p = 0;
        TextEntries newEntries = new TextEntries();
        int remaining = count;
        for (TextEntry entry : this.myTextEntries) {
            if (remaining == 0) {
                newEntries.add(entry);
                continue;
            }
            int len = entry.getLength();
            if (p + len <= x) {
                p += len;
                newEntries.add(entry);
                continue;
            }
            int dx = x - p;
            if (dx > 0) {
                newEntries.add(new TextEntry(entry.getStyle(), entry.getText().subBuffer(0, dx)));
                p = x;
            }
            if (dx + remaining < len) {
                newEntries.add(new TextEntry(entry.getStyle(), entry.getText().subBuffer(dx + remaining, len - (dx + remaining))));
                remaining = 0;
                continue;
            }
            remaining -= len - dx;
            p = x;
        }
        if (count > 0 && style != TextStyle.EMPTY) {
            newEntries.add(new TextEntry(style, new CharBuffer('\u0000', count)));
        }
        this.myTextEntries = newEntries;
    }

    public synchronized void insertBlankCharacters(int x, int count, int maxLen, @NotNull TextStyle style) {
        if (style == null) {
            TerminalLine.$$$reportNull$$$0(12);
        }
        int len = this.myTextEntries.length();
        len = Math.min(len + count, maxLen);
        char[] buf = new char[len];
        TextStyle[] styles = new TextStyle[len];
        int p = 0;
        for (TextEntry entry : this.myTextEntries) {
            for (int i = 0; i < entry.getLength() && p < len; ++i) {
                if (p == x) {
                    for (int j = 0; j < count && p < len; ++p, ++j) {
                        buf[p] = 32;
                        styles[p] = style;
                    }
                }
                if (p >= len) continue;
                buf[p] = entry.getText().charAt(i);
                styles[p] = entry.getStyle();
                ++p;
            }
            if (p < len) continue;
            break;
        }
        while (p < x && p < len) {
            buf[p] = 32;
            styles[p] = TextStyle.EMPTY;
            ++p;
            ++p;
        }
        while (p < x + count && p < len) {
            buf[p] = 32;
            styles[p] = style;
            ++p;
            ++p;
        }
        this.myTextEntries = TerminalLine.collectFromBuffer(buf, styles);
    }

    public synchronized void clearArea(int leftX, int rightX, @NotNull TextStyle style) {
        if (style == null) {
            TerminalLine.$$$reportNull$$$0(13);
        }
        if (rightX == -1) {
            rightX = Math.max(this.myTextEntries.length(), leftX);
        }
        this.writeCharacters(leftX, style, new CharBuffer(rightX >= this.myTextEntries.length() ? (char)'\u0000' : ' ', rightX - leftX));
    }

    @Nullable
    public synchronized TextStyle getStyleAt(int x) {
        int i = 0;
        for (TextEntry te : this.myTextEntries) {
            if (x >= i && x < i + te.getLength()) {
                return te.getStyle();
            }
            i += te.getLength();
        }
        return null;
    }

    public synchronized void process(int y, StyledTextConsumer consumer, int startRow) {
        int x = 0;
        int nulIndex = -1;
        TerminalLineIntervalHighlighting highlighting = this.myCustomHighlightings.stream().findFirst().orElse(null);
        for (TextEntry te : this.myTextEntries) {
            if (te.getText().isNul()) {
                if (nulIndex < 0) {
                    nulIndex = x;
                }
                consumer.consumeNul(x, y, nulIndex, te.getStyle(), te.getText(), startRow);
            } else if (highlighting != null && te.getLength() > 0 && highlighting.intersectsWith(x, x + te.getLength())) {
                this.processIntersection(x, y, te, consumer, startRow, highlighting);
            } else {
                consumer.consume(x, y, te.getStyle(), te.getText(), startRow);
            }
            x += te.getLength();
        }
        consumer.consumeQueue(x, y, nulIndex < 0 ? x : nulIndex, startRow);
    }

    private void processIntersection(int startTextOffset, int y, @NotNull TextEntry te, @NotNull StyledTextConsumer consumer, int startRow, @NotNull TerminalLineIntervalHighlighting highlighting) {
        if (te == null) {
            TerminalLine.$$$reportNull$$$0(14);
        }
        if (consumer == null) {
            TerminalLine.$$$reportNull$$$0(15);
        }
        if (highlighting == null) {
            TerminalLine.$$$reportNull$$$0(16);
        }
        CharBuffer text = te.getText();
        int endTextOffset = startTextOffset + text.length();
        int[] offsets = new int[]{startTextOffset, endTextOffset, highlighting.getStartOffset(), highlighting.getEndOffset()};
        Arrays.sort(offsets);
        int startTextOffsetInd = Arrays.binarySearch(offsets, startTextOffset);
        int endTextOffsetInd = Arrays.binarySearch(offsets, endTextOffset);
        if (startTextOffsetInd < 0 || endTextOffsetInd < 0) {
            LOG.error("Cannot find " + Arrays.toString(new int[]{startTextOffset, endTextOffset}) + " in " + Arrays.toString(offsets) + ": " + Arrays.toString(new int[]{startTextOffsetInd, endTextOffsetInd}));
            consumer.consume(startTextOffset, y, te.getStyle(), text, startRow);
            return;
        }
        for (int i = startTextOffsetInd; i < endTextOffsetInd; ++i) {
            int length = offsets[i + 1] - offsets[i];
            if (length == 0) continue;
            SubCharBuffer subText = new SubCharBuffer(text, offsets[i] - startTextOffset, length);
            if (highlighting.intersectsWith(offsets[i], offsets[i + 1])) {
                consumer.consume(offsets[i], y, highlighting.mergeWith(te.getStyle()), subText, startRow);
                continue;
            }
            consumer.consume(offsets[i], y, te.getStyle(), subText, startRow);
        }
    }

    public synchronized boolean isNul() {
        for (TextEntry e : this.myTextEntries.entries()) {
            if (e.isNul()) continue;
            return false;
        }
        return true;
    }

    void forEachEntry(@NotNull Consumer<TextEntry> action) {
        if (action == null) {
            TerminalLine.$$$reportNull$$$0(17);
        }
        this.myTextEntries.forEach(action);
    }

    public List<TextEntry> getEntries() {
        return this.myTextEntries.entries();
    }

    void appendEntry(@NotNull TextEntry entry) {
        if (entry == null) {
            TerminalLine.$$$reportNull$$$0(18);
        }
        this.myTextEntries.add(entry);
    }

    @NotNull
    public synchronized TerminalLineIntervalHighlighting addCustomHighlighting(int startOffset, int length, @NotNull TextStyle textStyle) {
        if (textStyle == null) {
            TerminalLine.$$$reportNull$$$0(19);
        }
        TerminalLineIntervalHighlighting highlighting = new TerminalLineIntervalHighlighting(this, startOffset, length, textStyle){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            protected void doDispose() {
                TerminalLine terminalLine = TerminalLine.this;
                synchronized (terminalLine) {
                    TerminalLine.this.myCustomHighlightings.remove(this);
                }
            }
        };
        this.myCustomHighlightings.add(highlighting);
        TerminalLineIntervalHighlighting terminalLineIntervalHighlighting = highlighting;
        if (terminalLineIntervalHighlighting == null) {
            TerminalLine.$$$reportNull$$$0(20);
        }
        return terminalLineIntervalHighlighting;
    }

    public String toString() {
        return this.myTextEntries.length() + " chars, " + (this.myWrapped ? "wrapped, " : "") + this.myTextEntries.myTextEntries.size() + " entries: " + Joiner.on("|").join(this.myTextEntries.myTextEntries.stream().map(entry -> entry.getText().toString()).collect(Collectors.toList()));
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
            case 20: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 20: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "entry";
                break;
            }
            case 1: {
                objectArray2 = objectArray3;
                objectArray3[0] = "filler";
                break;
            }
            case 2: 
            case 6: {
                objectArray2 = objectArray3;
                objectArray3[0] = "str";
                break;
            }
            case 3: 
            case 4: 
            case 7: 
            case 10: 
            case 11: 
            case 12: 
            case 13: {
                objectArray2 = objectArray3;
                objectArray3[0] = "style";
                break;
            }
            case 5: {
                objectArray2 = objectArray3;
                objectArray3[0] = "characters";
                break;
            }
            case 8: {
                objectArray2 = objectArray3;
                objectArray3[0] = "entries";
                break;
            }
            case 9: {
                objectArray2 = objectArray3;
                objectArray3[0] = "styles";
                break;
            }
            case 14: {
                objectArray2 = objectArray3;
                objectArray3[0] = "te";
                break;
            }
            case 15: {
                objectArray2 = objectArray3;
                objectArray3[0] = "consumer";
                break;
            }
            case 16: {
                objectArray2 = objectArray3;
                objectArray3[0] = "highlighting";
                break;
            }
            case 17: {
                objectArray2 = objectArray3;
                objectArray3[0] = "action";
                break;
            }
            case 19: {
                objectArray2 = objectArray3;
                objectArray3[0] = "textStyle";
                break;
            }
            case 20: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/model/TerminalLine";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/model/TerminalLine";
                break;
            }
            case 20: {
                objectArray = objectArray2;
                objectArray2[1] = "addCustomHighlighting";
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
                objectArray[2] = "clear";
                break;
            }
            case 2: 
            case 3: {
                objectArray = objectArray;
                objectArray[2] = "writeString";
                break;
            }
            case 4: 
            case 5: {
                objectArray = objectArray;
                objectArray[2] = "writeCharacters";
                break;
            }
            case 6: 
            case 7: 
            case 8: {
                objectArray = objectArray;
                objectArray[2] = "merge";
                break;
            }
            case 9: {
                objectArray = objectArray;
                objectArray[2] = "collectFromBuffer";
                break;
            }
            case 10: 
            case 11: {
                objectArray = objectArray;
                objectArray[2] = "deleteCharacters";
                break;
            }
            case 12: {
                objectArray = objectArray;
                objectArray[2] = "insertBlankCharacters";
                break;
            }
            case 13: {
                objectArray = objectArray;
                objectArray[2] = "clearArea";
                break;
            }
            case 14: 
            case 15: 
            case 16: {
                objectArray = objectArray;
                objectArray[2] = "processIntersection";
                break;
            }
            case 17: {
                objectArray = objectArray;
                objectArray[2] = "forEachEntry";
                break;
            }
            case 18: {
                objectArray = objectArray;
                objectArray[2] = "appendEntry";
                break;
            }
            case 19: {
                objectArray = objectArray;
                objectArray[2] = "addCustomHighlighting";
                break;
            }
            case 20: {
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 20: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }

    private static class TextEntries
    implements Iterable<TextEntry> {
        private final List<TextEntry> myTextEntries = new ArrayList<TextEntry>();
        private int myLength = 0;

        private TextEntries() {
        }

        public void add(TextEntry entry) {
            if (!entry.getText().isNul()) {
                for (TextEntry t : this.myTextEntries) {
                    if (!t.getText().isNul()) continue;
                    t.getText().unNullify();
                }
            }
            this.myTextEntries.add(entry);
            this.myLength += entry.getLength();
        }

        private List<TextEntry> entries() {
            return Collections.unmodifiableList(this.myTextEntries);
        }

        @Override
        @NotNull
        public Iterator<TextEntry> iterator() {
            Iterator<TextEntry> iterator = this.entries().iterator();
            if (iterator == null) {
                TextEntries.$$$reportNull$$$0(0);
            }
            return iterator;
        }

        public int length() {
            return this.myLength;
        }

        public void clear() {
            this.myTextEntries.clear();
            this.myLength = 0;
        }

        private static /* synthetic */ void $$$reportNull$$$0(int n) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jediterm/terminal/model/TerminalLine$TextEntries", "iterator"));
        }
    }

    public static class TextEntry {
        private final TextStyle myStyle;
        private final CharBuffer myText;

        public TextEntry(@NotNull TextStyle style, @NotNull CharBuffer text) {
            if (style == null) {
                TextEntry.$$$reportNull$$$0(0);
            }
            if (text == null) {
                TextEntry.$$$reportNull$$$0(1);
            }
            this.myStyle = style;
            this.myText = text.clone();
        }

        public TextStyle getStyle() {
            return this.myStyle;
        }

        public CharBuffer getText() {
            return this.myText;
        }

        public int getLength() {
            return this.myText.length();
        }

        public boolean isNul() {
            return this.myText.isNul();
        }

        public String toString() {
            return this.myText.length() + " chars, style: " + this.myStyle + ", text: " + this.myText;
        }

        private static /* synthetic */ void $$$reportNull$$$0(int n) {
            Object[] objectArray;
            Object[] objectArray2 = new Object[3];
            switch (n) {
                default: {
                    objectArray = objectArray2;
                    objectArray2[0] = "style";
                    break;
                }
                case 1: {
                    objectArray = objectArray2;
                    objectArray2[0] = "text";
                    break;
                }
            }
            objectArray[1] = "com/jediterm/terminal/model/TerminalLine$TextEntry";
            objectArray[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
        }
    }
}

