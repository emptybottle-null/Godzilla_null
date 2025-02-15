/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.util.Iterator;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.common.FormatRun;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;

public final class HSSFRichTextString
implements Comparable<HSSFRichTextString>,
RichTextString {
    public static final short NO_FONT = 0;
    private UnicodeString _string;
    private InternalWorkbook _book;
    private LabelSSTRecord _record;

    public HSSFRichTextString() {
        this("");
    }

    public HSSFRichTextString(String string) {
        this._string = string == null ? new UnicodeString("") : new UnicodeString(string);
    }

    HSSFRichTextString(InternalWorkbook book, LabelSSTRecord record) {
        this.setWorkbookReferences(book, record);
        this._string = book.getSSTString(record.getSSTIndex());
    }

    void setWorkbookReferences(InternalWorkbook book, LabelSSTRecord record) {
        this._book = book;
        this._record = record;
    }

    private UnicodeString cloneStringIfRequired() {
        return this._book == null ? this._string : this._string.copy();
    }

    private void addToSSTIfRequired() {
        if (this._book != null) {
            int index = this._book.addSSTString(this._string);
            this._record.setSSTIndex(index);
            this._string = this._book.getSSTString(index);
        }
    }

    @Override
    public void applyFont(int startIndex, int endIndex, short fontIndex) {
        if (startIndex > endIndex) {
            throw new IllegalArgumentException("Start index must be less than end index.");
        }
        if (startIndex < 0 || endIndex > this.length()) {
            throw new IllegalArgumentException("Start and end index not in range.");
        }
        if (startIndex == endIndex) {
            return;
        }
        short currentFont = 0;
        if (endIndex != this.length()) {
            currentFont = this.getFontAtIndex(endIndex);
        }
        this._string = this.cloneStringIfRequired();
        Iterator<FormatRun> formatting = this._string.formatIterator();
        if (formatting != null) {
            while (formatting.hasNext()) {
                FormatRun r = formatting.next();
                if (r.getCharacterPos() < startIndex || r.getCharacterPos() >= endIndex) continue;
                formatting.remove();
            }
        }
        this._string.addFormatRun(new FormatRun((short)startIndex, fontIndex));
        if (endIndex != this.length()) {
            this._string.addFormatRun(new FormatRun((short)endIndex, currentFont));
        }
        this.addToSSTIfRequired();
    }

    @Override
    public void applyFont(int startIndex, int endIndex, Font font) {
        this.applyFont(startIndex, endIndex, font.getIndex());
    }

    @Override
    public void applyFont(Font font) {
        this.applyFont(0, this._string.getCharCount(), font);
    }

    @Override
    public void clearFormatting() {
        this._string = this.cloneStringIfRequired();
        this._string.clearFormatting();
        this.addToSSTIfRequired();
    }

    @Override
    public String getString() {
        return this._string.getString();
    }

    UnicodeString getUnicodeString() {
        return this.cloneStringIfRequired();
    }

    UnicodeString getRawUnicodeString() {
        return this._string;
    }

    void setUnicodeString(UnicodeString str) {
        this._string = str;
    }

    @Override
    public int length() {
        return this._string.getCharCount();
    }

    public short getFontAtIndex(int index) {
        FormatRun r;
        int size = this._string.getFormatRunCount();
        FormatRun currentRun = null;
        for (int i = 0; i < size && (r = this._string.getFormatRun(i)).getCharacterPos() <= index; ++i) {
            currentRun = r;
        }
        if (currentRun == null) {
            return 0;
        }
        return currentRun.getFontIndex();
    }

    @Override
    public int numFormattingRuns() {
        return this._string.getFormatRunCount();
    }

    @Override
    public int getIndexOfFormattingRun(int index) {
        FormatRun r = this._string.getFormatRun(index);
        return r.getCharacterPos();
    }

    public short getFontOfFormattingRun(int index) {
        FormatRun r = this._string.getFormatRun(index);
        return r.getFontIndex();
    }

    @Override
    public int compareTo(HSSFRichTextString r) {
        return this._string.compareTo(r._string);
    }

    public boolean equals(Object o) {
        if (o instanceof HSSFRichTextString) {
            return this._string.equals(((HSSFRichTextString)o)._string);
        }
        return false;
    }

    public int hashCode() {
        assert (false) : "hashCode not designed";
        return 42;
    }

    public String toString() {
        return this._string.toString();
    }

    @Override
    public void applyFont(short fontIndex) {
        this.applyFont(0, this._string.getCharCount(), fontIndex);
    }
}

