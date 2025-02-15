/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaHighlighter;
import org.fife.ui.rsyntaxtextarea.SquiggleUnderlineHighlightPainter;
import org.fife.ui.rsyntaxtextarea.parser.ParseResult;
import org.fife.ui.rsyntaxtextarea.parser.Parser;
import org.fife.ui.rsyntaxtextarea.parser.ParserNotice;
import org.fife.ui.rsyntaxtextarea.parser.ToolTipInfo;
import org.fife.ui.rtextarea.RDocument;
import org.fife.ui.rtextarea.RTextAreaHighlighter;

class ParserManager
implements DocumentListener,
ActionListener,
HyperlinkListener,
PropertyChangeListener {
    private RSyntaxTextArea textArea;
    private List<Parser> parsers;
    private Timer timer;
    private boolean running;
    private Parser parserForTip;
    private Position firstOffsetModded;
    private Position lastOffsetModded;
    private List<NoticeHighlightPair> noticeHighlightPairs;
    private SquiggleUnderlineHighlightPainter parserErrorHighlightPainter = new SquiggleUnderlineHighlightPainter(Color.RED);
    private static final String PROPERTY_DEBUG_PARSING = "rsta.debugParsing";
    private static final boolean DEBUG_PARSING;
    private static final int DEFAULT_DELAY_MS = 1250;

    ParserManager(RSyntaxTextArea textArea) {
        this(1250, textArea);
    }

    ParserManager(int delay, RSyntaxTextArea textArea) {
        this.textArea = textArea;
        textArea.getDocument().addDocumentListener(this);
        textArea.addPropertyChangeListener("document", this);
        this.parsers = new ArrayList<Parser>(1);
        this.timer = new Timer(delay, this);
        this.timer.setRepeats(false);
        this.running = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int parserCount = this.getParserCount();
        if (parserCount == 0) {
            return;
        }
        long begin = 0L;
        if (DEBUG_PARSING) {
            begin = System.currentTimeMillis();
        }
        RSyntaxDocument doc = (RSyntaxDocument)this.textArea.getDocument();
        Element root = doc.getDefaultRootElement();
        int firstLine = this.firstOffsetModded == null ? 0 : root.getElementIndex(this.firstOffsetModded.getOffset());
        int lastLine = this.lastOffsetModded == null ? root.getElementCount() - 1 : root.getElementIndex(this.lastOffsetModded.getOffset());
        this.lastOffsetModded = null;
        this.firstOffsetModded = null;
        if (DEBUG_PARSING) {
            System.out.println("[DEBUG]: Minimum lines to parse: " + firstLine + "-" + lastLine);
        }
        String style = this.textArea.getSyntaxEditingStyle();
        doc.readLock();
        try {
            for (int i = 0; i < parserCount; ++i) {
                Parser parser = this.getParser(i);
                if (parser.isEnabled()) {
                    ParseResult res = parser.parse(doc, style);
                    this.addParserNoticeHighlights(res);
                    continue;
                }
                this.clearParserNoticeHighlights(parser);
            }
            this.textArea.fireParserNoticesChange();
        } finally {
            doc.readUnlock();
        }
        if (DEBUG_PARSING) {
            float time = (float)(System.currentTimeMillis() - begin) / 1000.0f;
            System.out.println("Total parsing time: " + time + " seconds");
        }
    }

    public void addParser(Parser parser) {
        if (parser != null && !this.parsers.contains(parser)) {
            if (this.running) {
                this.timer.stop();
            }
            this.parsers.add(parser);
            if (this.parsers.size() == 1) {
                ToolTipManager.sharedInstance().registerComponent(this.textArea);
            }
            if (this.running) {
                this.timer.restart();
            }
        }
    }

    private void addParserNoticeHighlights(ParseResult res) {
        if (res == null) {
            return;
        }
        if (DEBUG_PARSING) {
            System.out.println("[DEBUG]: Adding parser notices from " + res.getParser());
        }
        if (this.noticeHighlightPairs == null) {
            this.noticeHighlightPairs = new ArrayList<NoticeHighlightPair>();
        }
        this.removeParserNotices(res);
        List<ParserNotice> notices = res.getNotices();
        if (notices.size() > 0) {
            RSyntaxTextAreaHighlighter h = (RSyntaxTextAreaHighlighter)this.textArea.getHighlighter();
            for (ParserNotice notice : notices) {
                if (DEBUG_PARSING) {
                    System.out.println("[DEBUG]: ... adding: " + notice);
                }
                try {
                    RTextAreaHighlighter.HighlightInfo highlight = null;
                    if (notice.getShowInEditor()) {
                        highlight = h.addParserHighlight(notice, this.parserErrorHighlightPainter);
                    }
                    this.noticeHighlightPairs.add(new NoticeHighlightPair(notice, highlight));
                } catch (BadLocationException ble) {
                    ble.printStackTrace();
                }
            }
        }
        if (DEBUG_PARSING) {
            System.out.println("[DEBUG]: Done adding parser notices from " + res.getParser());
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    private void clearParserNoticeHighlights() {
        RSyntaxTextAreaHighlighter h = (RSyntaxTextAreaHighlighter)this.textArea.getHighlighter();
        if (h != null) {
            h.clearParserHighlights();
        }
        if (this.noticeHighlightPairs != null) {
            this.noticeHighlightPairs.clear();
        }
    }

    private void clearParserNoticeHighlights(Parser parser) {
        RSyntaxTextAreaHighlighter h = (RSyntaxTextAreaHighlighter)this.textArea.getHighlighter();
        if (h != null) {
            h.clearParserHighlights(parser);
        }
        if (this.noticeHighlightPairs != null) {
            this.noticeHighlightPairs.removeIf(pair -> ((NoticeHighlightPair)pair).notice.getParser() == parser);
        }
    }

    public void clearParsers() {
        this.timer.stop();
        this.clearParserNoticeHighlights();
        this.parsers.clear();
        this.textArea.fireParserNoticesChange();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void forceReparsing(int parser) {
        Parser p = this.getParser(parser);
        RSyntaxDocument doc = (RSyntaxDocument)this.textArea.getDocument();
        String style = this.textArea.getSyntaxEditingStyle();
        doc.readLock();
        try {
            if (p.isEnabled()) {
                ParseResult res = p.parse(doc, style);
                this.addParserNoticeHighlights(res);
            } else {
                this.clearParserNoticeHighlights(p);
            }
            this.textArea.fireParserNoticesChange();
        } finally {
            doc.readUnlock();
        }
    }

    public int getDelay() {
        return this.timer.getDelay();
    }

    public Parser getParser(int index) {
        return this.parsers.get(index);
    }

    public int getParserCount() {
        return this.parsers.size();
    }

    public List<ParserNotice> getParserNotices() {
        ArrayList<ParserNotice> notices = new ArrayList<ParserNotice>();
        if (this.noticeHighlightPairs != null) {
            for (NoticeHighlightPair pair : this.noticeHighlightPairs) {
                notices.add(pair.notice);
            }
        }
        return notices;
    }

    public ToolTipInfo getToolTipText(MouseEvent e) {
        String tip = null;
        HyperlinkListener listener = null;
        this.parserForTip = null;
        Point p = e.getPoint();
        int pos = this.textArea.viewToModel(p);
        if (this.noticeHighlightPairs != null) {
            for (NoticeHighlightPair pair : this.noticeHighlightPairs) {
                ParserNotice notice = pair.notice;
                if (!this.noticeContainsPosition(notice, pos) || !this.noticeContainsPointInView(notice, p)) continue;
                tip = notice.getToolTipText();
                this.parserForTip = notice.getParser();
                if (!(this.parserForTip instanceof HyperlinkListener)) break;
                listener = (HyperlinkListener)((Object)this.parserForTip);
                break;
            }
        }
        URL imageBase = this.parserForTip == null ? null : this.parserForTip.getImageBase();
        return new ToolTipInfo(tip, listener, imageBase);
    }

    public void handleDocumentEvent(DocumentEvent e) {
        if (this.running && this.parsers.size() > 0) {
            this.timer.restart();
        }
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (this.parserForTip != null && this.parserForTip.getHyperlinkListener() != null) {
            this.parserForTip.getHyperlinkListener().linkClicked(this.textArea, e);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            int offs = e.getOffset();
            if (this.firstOffsetModded == null || offs < this.firstOffsetModded.getOffset()) {
                this.firstOffsetModded = e.getDocument().createPosition(offs);
            }
            offs = e.getOffset() + e.getLength();
            if (this.lastOffsetModded == null || offs > this.lastOffsetModded.getOffset()) {
                this.lastOffsetModded = e.getDocument().createPosition(offs);
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        this.handleDocumentEvent(e);
    }

    private boolean noticeContainsPosition(ParserNotice notice, int offs) {
        if (notice.getKnowsOffsetAndLength()) {
            return notice.containsPosition(offs);
        }
        Document doc = this.textArea.getDocument();
        Element root = doc.getDefaultRootElement();
        int line = notice.getLine();
        if (line < 0) {
            return false;
        }
        Element elem = root.getElement(line);
        return elem != null && offs >= elem.getStartOffset() && offs < elem.getEndOffset();
    }

    private boolean noticeContainsPointInView(ParserNotice notice, Point p) {
        try {
            int end;
            int start;
            if (notice.getKnowsOffsetAndLength()) {
                start = notice.getOffset();
                end = start + notice.getLength() - 1;
            } else {
                Document doc = this.textArea.getDocument();
                Element root = doc.getDefaultRootElement();
                int line = notice.getLine();
                if (line < 0) {
                    return false;
                }
                Element elem = root.getElement(line);
                start = elem.getStartOffset();
                end = elem.getEndOffset() - 1;
            }
            Rectangle r1 = this.textArea.modelToView(start);
            Rectangle r2 = this.textArea.modelToView(end);
            if (r1.y != r2.y) {
                return true;
            }
            --r1.y;
            r1.height += 2;
            return p.x >= r1.x && p.x < r2.x + r2.width && p.y >= r1.y && p.y < r1.y + r1.height;
        } catch (BadLocationException ble) {
            return true;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String name = e.getPropertyName();
        if ("document".equals(name)) {
            RDocument newDoc;
            RDocument old = (RDocument)e.getOldValue();
            if (old != null) {
                old.removeDocumentListener(this);
            }
            if ((newDoc = (RDocument)e.getNewValue()) != null) {
                newDoc.addDocumentListener(this);
            }
        }
    }

    public boolean removeParser(Parser parser) {
        this.removeParserNotices(parser);
        boolean removed = this.parsers.remove(parser);
        if (removed) {
            this.textArea.fireParserNoticesChange();
        }
        return removed;
    }

    private void removeParserNotices(Parser parser) {
        if (this.noticeHighlightPairs != null) {
            RSyntaxTextAreaHighlighter h = (RSyntaxTextAreaHighlighter)this.textArea.getHighlighter();
            Iterator<NoticeHighlightPair> i = this.noticeHighlightPairs.iterator();
            while (i.hasNext()) {
                NoticeHighlightPair pair = i.next();
                if (pair.notice.getParser() != parser || pair.highlight == null) continue;
                h.removeParserHighlight(pair.highlight);
                i.remove();
            }
        }
    }

    private void removeParserNotices(ParseResult res) {
        if (this.noticeHighlightPairs != null) {
            RSyntaxTextAreaHighlighter h = (RSyntaxTextAreaHighlighter)this.textArea.getHighlighter();
            Iterator<NoticeHighlightPair> i = this.noticeHighlightPairs.iterator();
            while (i.hasNext()) {
                NoticeHighlightPair pair = i.next();
                boolean removed = false;
                if (this.shouldRemoveNotice(pair.notice, res)) {
                    if (pair.highlight != null) {
                        h.removeParserHighlight(pair.highlight);
                    }
                    i.remove();
                    removed = true;
                }
                if (!DEBUG_PARSING) continue;
                String text = removed ? "[DEBUG]: ... notice removed: " : "[DEBUG]: ... notice not removed: ";
                System.out.println(text + pair.notice);
            }
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            int offs = e.getOffset();
            if (this.firstOffsetModded == null || offs < this.firstOffsetModded.getOffset()) {
                this.firstOffsetModded = e.getDocument().createPosition(offs);
            }
            if (this.lastOffsetModded == null || offs > this.lastOffsetModded.getOffset()) {
                this.lastOffsetModded = e.getDocument().createPosition(offs);
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        this.handleDocumentEvent(e);
    }

    public void restartParsing() {
        this.timer.restart();
        this.running = true;
    }

    public void setDelay(int millis) {
        if (this.running) {
            this.timer.stop();
        }
        this.timer.setInitialDelay(millis);
        this.timer.setDelay(millis);
        if (this.running) {
            this.timer.start();
        }
    }

    private boolean shouldRemoveNotice(ParserNotice notice, ParseResult res) {
        if (DEBUG_PARSING) {
            System.out.println("[DEBUG]: ... ... shouldRemoveNotice " + notice + ": " + (notice.getParser() == res.getParser()));
        }
        return notice.getParser() == res.getParser();
    }

    public void stopParsing() {
        this.timer.stop();
        this.running = false;
    }

    static {
        boolean debugParsing;
        try {
            debugParsing = Boolean.getBoolean(PROPERTY_DEBUG_PARSING);
        } catch (AccessControlException ace) {
            debugParsing = false;
        }
        DEBUG_PARSING = debugParsing;
    }

    private static class NoticeHighlightPair {
        private ParserNotice notice;
        private RTextAreaHighlighter.HighlightInfo highlight;

        NoticeHighlightPair(ParserNotice notice, RTextAreaHighlighter.HighlightInfo highlight) {
            this.notice = notice;
            this.highlight = highlight;
        }
    }
}

