/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.util.ArrayList;
import java.util.List;
import org.fife.ui.rsyntaxtextarea.parser.ParseResult;
import org.fife.ui.rsyntaxtextarea.parser.Parser;
import org.fife.ui.rsyntaxtextarea.parser.ParserNotice;

public class DefaultParseResult
implements ParseResult {
    private Parser parser;
    private int firstLineParsed;
    private int lastLineParsed;
    private List<ParserNotice> notices;
    private long parseTime;
    private Exception error;

    public DefaultParseResult(Parser parser) {
        this.parser = parser;
        this.notices = new ArrayList<ParserNotice>();
    }

    public void addNotice(ParserNotice notice) {
        this.notices.add(notice);
    }

    public void clearNotices() {
        this.notices.clear();
    }

    @Override
    public Exception getError() {
        return this.error;
    }

    @Override
    public int getFirstLineParsed() {
        return this.firstLineParsed;
    }

    @Override
    public int getLastLineParsed() {
        return this.lastLineParsed;
    }

    @Override
    public List<ParserNotice> getNotices() {
        return this.notices;
    }

    @Override
    public Parser getParser() {
        return this.parser;
    }

    @Override
    public long getParseTime() {
        return this.parseTime;
    }

    public void setError(Exception e) {
        this.error = e;
    }

    public void setParsedLines(int first, int last) {
        this.firstLineParsed = first;
        this.lastLineParsed = last;
    }

    public void setParseTime(long time) {
        this.parseTime = time;
    }
}

