/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.util.List;
import org.fife.ui.rsyntaxtextarea.parser.Parser;
import org.fife.ui.rsyntaxtextarea.parser.ParserNotice;

public interface ParseResult {
    public Exception getError();

    public int getFirstLineParsed();

    public int getLastLineParsed();

    public List<ParserNotice> getNotices();

    public Parser getParser();

    public long getParseTime();
}

