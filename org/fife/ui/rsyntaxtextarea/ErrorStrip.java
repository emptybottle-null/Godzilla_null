/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.BadLocationException;
import org.fife.ui.rsyntaxtextarea.DocumentRange;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.parser.Parser;
import org.fife.ui.rsyntaxtextarea.parser.ParserNotice;
import org.fife.ui.rsyntaxtextarea.parser.TaskTagParser;

public class ErrorStrip
extends JPanel {
    private RSyntaxTextArea textArea;
    private transient Listener listener;
    private boolean showMarkedOccurrences;
    private boolean showMarkAll;
    private Map<Color, Color> brighterColors;
    private ParserNotice.Level levelThreshold;
    private boolean followCaret;
    private Color caretMarkerColor;
    private int caretLineY;
    private int lastLineY;
    private transient ErrorStripMarkerToolTipProvider markerToolTipProvider;
    private static final int PREFERRED_WIDTH = 14;
    private static final ResourceBundle MSG = ResourceBundle.getBundle("org.fife.ui.rsyntaxtextarea.ErrorStrip");

    public ErrorStrip(RSyntaxTextArea textArea) {
        this.textArea = textArea;
        this.listener = new Listener();
        ToolTipManager.sharedInstance().registerComponent(this);
        this.setLayout(null);
        this.addMouseListener(this.listener);
        this.setShowMarkedOccurrences(true);
        this.setShowMarkAll(true);
        this.setLevelThreshold(ParserNotice.Level.WARNING);
        this.setFollowCaret(true);
        this.setCaretMarkerColor(this.getDefaultCaretMarkerColor());
        this.setMarkerToolTipProvider(null);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.textArea.addCaretListener(this.listener);
        this.textArea.addPropertyChangeListener("RSTA.parserNotices", this.listener);
        this.textArea.addPropertyChangeListener("RSTA.markOccurrences", this.listener);
        this.textArea.addPropertyChangeListener("RSTA.markedOccurrencesChanged", this.listener);
        this.textArea.addPropertyChangeListener("RTA.markAllOccurrencesChanged", this.listener);
        this.refreshMarkers();
    }

    @Override
    public void doLayout() {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            Marker m = (Marker)this.getComponent(i);
            m.updateLocation();
        }
        this.listener.caretUpdate(null);
    }

    private Color getBrighterColor(Color c) {
        Color brighter;
        if (this.brighterColors == null) {
            this.brighterColors = new HashMap<Color, Color>(5);
        }
        if ((brighter = this.brighterColors.get(c)) == null) {
            int r = ErrorStrip.possiblyBrighter(c.getRed());
            int g = ErrorStrip.possiblyBrighter(c.getGreen());
            int b = ErrorStrip.possiblyBrighter(c.getBlue());
            brighter = new Color(r, g, b);
            this.brighterColors.put(c, brighter);
        }
        return brighter;
    }

    public Color getCaretMarkerColor() {
        return this.caretMarkerColor;
    }

    private ColorUIResource getDefaultCaretMarkerColor() {
        if (RSyntaxUtilities.isLightForeground(this.getForeground())) {
            return new ColorUIResource(this.textArea.getCaretColor());
        }
        return new ColorUIResource(Color.BLACK);
    }

    public boolean getFollowCaret() {
        return this.followCaret;
    }

    @Override
    public Dimension getPreferredSize() {
        int height = this.textArea.getPreferredScrollableViewportSize().height;
        return new Dimension(14, height);
    }

    public ParserNotice.Level getLevelThreshold() {
        return this.levelThreshold;
    }

    public boolean getShowMarkAll() {
        return this.showMarkAll;
    }

    public boolean getShowMarkedOccurrences() {
        return this.showMarkedOccurrences;
    }

    @Override
    public String getToolTipText(MouseEvent e) {
        String text = null;
        int line = this.yToLine(e.getY());
        if (line > -1) {
            text = MSG.getString("Line");
            text = MessageFormat.format(text, line + 1);
        }
        return text;
    }

    private int lineToY(int line) {
        int h = this.textArea.getVisibleRect().height;
        float lineCount = this.textArea.getLineCount();
        return (int)((float)(line - 1) / (lineCount - 1.0f) * (float)(h - 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.caretLineY > -1) {
            g.setColor(this.getCaretMarkerColor());
            g.fillRect(0, this.caretLineY, this.getWidth(), 2);
        }
    }

    private static int possiblyBrighter(int i) {
        if (i < 255) {
            i += (int)((float)(255 - i) * 0.8f);
        }
        return i;
    }

    private void refreshMarkers() {
        this.removeAll();
        HashMap<Integer, Marker> markerMap = new HashMap<Integer, Marker>();
        List<ParserNotice> notices = this.textArea.getParserNotices();
        for (ParserNotice notice : notices) {
            if (!notice.getLevel().isEqualToOrWorseThan(this.levelThreshold) && !(notice instanceof TaskTagParser.TaskNotice)) continue;
            Integer key = notice.getLine();
            Marker m = (Marker)markerMap.get(key);
            if (m == null) {
                m = new Marker(notice);
                m.addMouseListener(this.listener);
                markerMap.put(key, m);
                this.add(m);
                continue;
            }
            m.addNotice(notice);
        }
        if (this.getShowMarkedOccurrences() && this.textArea.getMarkOccurrences()) {
            List<DocumentRange> occurrences = this.textArea.getMarkedOccurrences();
            this.addMarkersForRanges(occurrences, markerMap, this.textArea.getMarkOccurrencesColor());
        }
        if (this.getShowMarkAll()) {
            Color markAllColor = this.textArea.getMarkAllHighlightColor();
            List<DocumentRange> ranges = this.textArea.getMarkAllHighlightRanges();
            this.addMarkersForRanges(ranges, markerMap, markAllColor);
        }
        this.revalidate();
        this.repaint();
    }

    private void addMarkersForRanges(List<DocumentRange> ranges, Map<Integer, Marker> markerMap, Color color) {
        for (DocumentRange range : ranges) {
            int line = 0;
            try {
                line = this.textArea.getLineOfOffset(range.getStartOffset());
            } catch (BadLocationException ble) {
                continue;
            }
            MarkedOccurrenceNotice notice = new MarkedOccurrenceNotice(range, color);
            Integer key = line;
            Marker m = markerMap.get(key);
            if (m == null) {
                m = new Marker(notice);
                m.addMouseListener(this.listener);
                markerMap.put(key, m);
                this.add(m);
                continue;
            }
            if (m.containsMarkedOccurence()) continue;
            m.addNotice(notice);
        }
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        this.textArea.removeCaretListener(this.listener);
        this.textArea.removePropertyChangeListener("RSTA.parserNotices", this.listener);
        this.textArea.removePropertyChangeListener("RSTA.markOccurrences", this.listener);
        this.textArea.removePropertyChangeListener("RSTA.markedOccurrencesChanged", this.listener);
        this.textArea.removePropertyChangeListener("RTA.markAllOccurrencesChanged", this.listener);
    }

    public void setCaretMarkerColor(Color color) {
        if (color != null) {
            this.caretMarkerColor = color;
            this.listener.caretUpdate(null);
        }
    }

    public void setFollowCaret(boolean follow) {
        if (this.followCaret != follow) {
            if (this.followCaret) {
                this.repaint(0, this.caretLineY, this.getWidth(), 2);
            }
            this.caretLineY = -1;
            this.lastLineY = -1;
            this.followCaret = follow;
            this.listener.caretUpdate(null);
        }
    }

    public void setLevelThreshold(ParserNotice.Level level) {
        this.levelThreshold = level;
        if (this.isDisplayable()) {
            this.refreshMarkers();
        }
    }

    public void setMarkerToolTipProvider(ErrorStripMarkerToolTipProvider provider) {
        this.markerToolTipProvider = provider != null ? provider : new DefaultErrorStripMarkerToolTipProvider();
    }

    public void setShowMarkAll(boolean show) {
        if (show != this.showMarkAll) {
            this.showMarkAll = show;
            if (this.isDisplayable()) {
                this.refreshMarkers();
            }
        }
    }

    public void setShowMarkedOccurrences(boolean show) {
        if (show != this.showMarkedOccurrences) {
            this.showMarkedOccurrences = show;
            if (this.isDisplayable()) {
                this.refreshMarkers();
            }
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (this.caretMarkerColor instanceof ColorUIResource) {
            this.setCaretMarkerColor(this.getDefaultCaretMarkerColor());
        }
    }

    private int yToLine(int y) {
        int line = -1;
        int h = this.textArea.getVisibleRect().height;
        if (y < h) {
            float at = (float)y / (float)h;
            line = Math.round((float)(this.textArea.getLineCount() - 1) * at);
        }
        return line;
    }

    private class Marker
    extends JComponent {
        private List<ParserNotice> notices = new ArrayList<ParserNotice>(1);

        Marker(ParserNotice notice) {
            this.addNotice(notice);
            this.setCursor(Cursor.getPredefinedCursor(12));
            this.setSize(this.getPreferredSize());
            ToolTipManager.sharedInstance().registerComponent(this);
        }

        public void addNotice(ParserNotice notice) {
            this.notices.add(notice);
        }

        public boolean containsMarkedOccurence() {
            boolean result = false;
            for (ParserNotice notice : this.notices) {
                if (!(notice instanceof MarkedOccurrenceNotice)) continue;
                result = true;
                break;
            }
            return result;
        }

        public Color getColor() {
            Color c = null;
            int lowestLevel = Integer.MAX_VALUE;
            for (ParserNotice notice : this.notices) {
                if (notice.getLevel().getNumericValue() >= lowestLevel) continue;
                lowestLevel = notice.getLevel().getNumericValue();
                c = notice.getColor();
            }
            return c;
        }

        @Override
        public Dimension getPreferredSize() {
            int w = 10;
            return new Dimension(w, 5);
        }

        @Override
        public String getToolTipText() {
            return ErrorStrip.this.markerToolTipProvider.getToolTipText(Collections.unmodifiableList(this.notices));
        }

        protected void mouseClicked(MouseEvent e) {
            ParserNotice pn = this.notices.get(0);
            int offs = pn.getOffset();
            int len = pn.getLength();
            if (offs > -1 && len > -1) {
                DocumentRange range = new DocumentRange(offs, offs + len);
                RSyntaxUtilities.selectAndPossiblyCenter(ErrorStrip.this.textArea, range, true);
            } else {
                int line = pn.getLine();
                try {
                    offs = ErrorStrip.this.textArea.getLineStartOffset(line);
                    ErrorStrip.this.textArea.getFoldManager().ensureOffsetNotInClosedFold(offs);
                    ErrorStrip.this.textArea.setCaretPosition(offs);
                } catch (BadLocationException ble) {
                    UIManager.getLookAndFeel().provideErrorFeedback(ErrorStrip.this.textArea);
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Color borderColor = this.getColor();
            if (borderColor == null) {
                borderColor = Color.DARK_GRAY;
            }
            Color fillColor = ErrorStrip.this.getBrighterColor(borderColor);
            int w = this.getWidth();
            int h = this.getHeight();
            g.setColor(fillColor);
            g.fillRect(0, 0, w, h);
            g.setColor(borderColor);
            g.drawRect(0, 0, w - 1, h - 1);
        }

        @Override
        public void removeNotify() {
            super.removeNotify();
            ToolTipManager.sharedInstance().unregisterComponent(this);
            this.removeMouseListener(ErrorStrip.this.listener);
        }

        public void updateLocation() {
            int line = this.notices.get(0).getLine();
            int y = ErrorStrip.this.lineToY(line);
            this.setLocation(2, y);
        }
    }

    private class MarkedOccurrenceNotice
    implements ParserNotice {
        private DocumentRange range;
        private Color color;

        MarkedOccurrenceNotice(DocumentRange range, Color color) {
            this.range = range;
            this.color = color;
        }

        @Override
        public int compareTo(ParserNotice other) {
            return 0;
        }

        @Override
        public boolean containsPosition(int pos) {
            return pos >= this.range.getStartOffset() && pos < this.range.getEndOffset();
        }

        public boolean equals(Object o) {
            if (!(o instanceof ParserNotice)) {
                return false;
            }
            return this.compareTo((ParserNotice)o) == 0;
        }

        @Override
        public Color getColor() {
            return this.color;
        }

        @Override
        public boolean getKnowsOffsetAndLength() {
            return true;
        }

        @Override
        public int getLength() {
            return this.range.getEndOffset() - this.range.getStartOffset();
        }

        @Override
        public ParserNotice.Level getLevel() {
            return ParserNotice.Level.INFO;
        }

        @Override
        public int getLine() {
            try {
                return ErrorStrip.this.textArea.getLineOfOffset(this.range.getStartOffset()) + 1;
            } catch (BadLocationException ble) {
                return 0;
            }
        }

        @Override
        public String getMessage() {
            String text = null;
            try {
                String word = ErrorStrip.this.textArea.getText(this.range.getStartOffset(), this.getLength());
                text = MSG.getString("OccurrenceOf");
                text = MessageFormat.format(text, word);
            } catch (BadLocationException ble) {
                UIManager.getLookAndFeel().provideErrorFeedback(ErrorStrip.this.textArea);
            }
            return text;
        }

        @Override
        public int getOffset() {
            return this.range.getStartOffset();
        }

        @Override
        public Parser getParser() {
            return null;
        }

        @Override
        public boolean getShowInEditor() {
            return false;
        }

        @Override
        public String getToolTipText() {
            return null;
        }

        public int hashCode() {
            return 0;
        }
    }

    private class Listener
    extends MouseAdapter
    implements PropertyChangeListener,
    CaretListener {
        private Rectangle visibleRect = new Rectangle();

        private Listener() {
        }

        @Override
        public void caretUpdate(CaretEvent e) {
            if (ErrorStrip.this.getFollowCaret()) {
                int line = ErrorStrip.this.textArea.getCaretLineNumber();
                float percent = (float)line / (float)(ErrorStrip.this.textArea.getLineCount() - 1);
                ErrorStrip.this.textArea.computeVisibleRect(this.visibleRect);
                ErrorStrip.this.caretLineY = (int)((float)this.visibleRect.height * percent);
                if (ErrorStrip.this.caretLineY != ErrorStrip.this.lastLineY) {
                    ErrorStrip.this.repaint(0, ErrorStrip.this.lastLineY, ErrorStrip.this.getWidth(), 2);
                    ErrorStrip.this.repaint(0, ErrorStrip.this.caretLineY, ErrorStrip.this.getWidth(), 2);
                    ErrorStrip.this.lastLineY = ErrorStrip.this.caretLineY;
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Component source = (Component)e.getSource();
            if (source instanceof Marker) {
                ((Marker)source).mouseClicked(e);
                return;
            }
            int line = ErrorStrip.this.yToLine(e.getY());
            if (line > -1) {
                try {
                    int offs = ErrorStrip.this.textArea.getLineStartOffset(line);
                    ErrorStrip.this.textArea.setCaretPosition(offs);
                } catch (BadLocationException ble) {
                    UIManager.getLookAndFeel().provideErrorFeedback(ErrorStrip.this.textArea);
                }
            }
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            String propName = e.getPropertyName();
            if ("RSTA.markOccurrences".equals(propName)) {
                if (ErrorStrip.this.getShowMarkedOccurrences()) {
                    ErrorStrip.this.refreshMarkers();
                }
            } else if ("RSTA.parserNotices".equals(propName)) {
                ErrorStrip.this.refreshMarkers();
            } else if ("RSTA.markedOccurrencesChanged".equals(propName)) {
                if (ErrorStrip.this.getShowMarkedOccurrences()) {
                    ErrorStrip.this.refreshMarkers();
                }
            } else if ("RTA.markAllOccurrencesChanged".equals(propName) && ErrorStrip.this.getShowMarkAll()) {
                ErrorStrip.this.refreshMarkers();
            }
        }
    }

    public static interface ErrorStripMarkerToolTipProvider {
        public String getToolTipText(List<ParserNotice> var1);
    }

    private static class DefaultErrorStripMarkerToolTipProvider
    implements ErrorStripMarkerToolTipProvider {
        private DefaultErrorStripMarkerToolTipProvider() {
        }

        @Override
        public String getToolTipText(List<ParserNotice> notices) {
            String text;
            if (notices.size() == 1) {
                text = notices.get(0).getMessage();
            } else {
                StringBuilder sb = new StringBuilder("<html>");
                sb.append(MSG.getString("MultipleMarkers"));
                sb.append("<br>");
                for (ParserNotice pn : notices) {
                    sb.append("&nbsp;&nbsp;&nbsp;- ");
                    sb.append(pn.getMessage());
                    sb.append("<br>");
                }
                text = sb.toString();
            }
            return text;
        }
    }
}

