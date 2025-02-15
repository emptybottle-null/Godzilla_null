/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.Element;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.parser.AbstractParser;
import org.fife.ui.rsyntaxtextarea.parser.DefaultParseResult;
import org.fife.ui.rsyntaxtextarea.parser.DefaultParserNotice;
import org.fife.ui.rsyntaxtextarea.parser.ParseResult;
import org.fife.ui.rsyntaxtextarea.parser.Parser;
import org.fife.ui.rsyntaxtextarea.parser.ParserNotice;

public class TaskTagParser
extends AbstractParser {
    private DefaultParseResult result = new DefaultParseResult(this);
    private static final String DEFAULT_TASK_PATTERN = "TODO|FIXME|HACK";
    private Pattern taskPattern;
    private static final Color COLOR = new Color(48, 150, 252);

    public TaskTagParser() {
        this.setTaskPattern(DEFAULT_TASK_PATTERN);
    }

    public String getTaskPattern() {
        return this.taskPattern == null ? null : this.taskPattern.pattern();
    }

    @Override
    public ParseResult parse(RSyntaxDocument doc, String style) {
        Element root = doc.getDefaultRootElement();
        int lineCount = root.getElementCount();
        if (this.taskPattern == null || style == null || "text/plain".equals(style)) {
            this.result.clearNotices();
            this.result.setParsedLines(0, lineCount - 1);
            return this.result;
        }
        this.result.clearNotices();
        this.result.setParsedLines(0, lineCount - 1);
        for (int line = 0; line < lineCount; ++line) {
            int offs = -1;
            int start = -1;
            String text = null;
            for (Token t = doc.getTokenListForLine(line); t != null && t.isPaintable(); t = t.getNextToken()) {
                if (!t.isComment()) continue;
                offs = t.getOffset();
                text = t.getLexeme();
                Matcher m = this.taskPattern.matcher(text);
                if (!m.find()) continue;
                start = m.start();
                offs += start;
                break;
            }
            if (start <= -1 || text == null) continue;
            text = text.substring(start);
            int len = text.length();
            TaskNotice pn = new TaskNotice(this, text, line + 1, offs, len);
            pn.setLevel(ParserNotice.Level.INFO);
            pn.setShowInEditor(false);
            pn.setColor(COLOR);
            this.result.addNotice(pn);
        }
        return this.result;
    }

    public void setTaskPattern(String pattern) {
        this.taskPattern = pattern == null || pattern.length() == 0 ? null : Pattern.compile(pattern);
    }

    public static class TaskNotice
    extends DefaultParserNotice {
        public TaskNotice(Parser parser, String message, int line, int offs, int length) {
            super(parser, message, line, offs, length);
        }
    }
}

