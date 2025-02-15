/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.NavigationFilter;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import javax.swing.text.Utilities;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rtextarea.ClipboardHistory;
import org.fife.ui.rtextarea.ClipboardHistoryPopup;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.GutterIconInfo;
import org.fife.ui.rtextarea.IconRowHeader;
import org.fife.ui.rtextarea.LineNumberList;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RecordableTextAction;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;

public class RTextAreaEditorKit
extends DefaultEditorKit {
    public static final String rtaBeginRecordingMacroAction = "RTA.BeginRecordingMacroAction";
    public static final String rtaDecreaseFontSizeAction = "RTA.DecreaseFontSizeAction";
    public static final String rtaDeleteLineAction = "RTA.DeleteLineAction";
    public static final String rtaDeletePrevWordAction = "RTA.DeletePrevWordAction";
    public static final String rtaDeleteRestOfLineAction = "RTA.DeleteRestOfLineAction";
    public static final String rtaDumbCompleteWordAction = "RTA.DumbCompleteWordAction";
    public static final String rtaEndRecordingMacroAction = "RTA.EndRecordingMacroAction";
    public static final String rtaIncreaseFontSizeAction = "RTA.IncreaseFontSizeAction";
    public static final String rtaInvertSelectionCaseAction = "RTA.InvertCaseAction";
    public static final String rtaJoinLinesAction = "RTA.JoinLinesAction";
    public static final String rtaLineDownAction = "RTA.LineDownAction";
    public static final String rtaLineUpAction = "RTA.LineUpAction";
    public static final String rtaLowerSelectionCaseAction = "RTA.LowerCaseAction";
    public static final String rtaNextOccurrenceAction = "RTA.NextOccurrenceAction";
    public static final String rtaPrevOccurrenceAction = "RTA.PrevOccurrenceAction";
    public static final String rtaNextBookmarkAction = "RTA.NextBookmarkAction";
    public static final String clipboardHistoryAction = "RTA.PasteHistoryAction";
    public static final String rtaPrevBookmarkAction = "RTA.PrevBookmarkAction";
    public static final String rtaPlaybackLastMacroAction = "RTA.PlaybackLastMacroAction";
    public static final String rtaRedoAction = "RTA.RedoAction";
    public static final String rtaScrollDownAction = "RTA.ScrollDownAction";
    public static final String rtaScrollUpAction = "RTA.ScrollUpAction";
    public static final String rtaSelectionPageUpAction = "RTA.SelectionPageUpAction";
    public static final String rtaSelectionPageDownAction = "RTA.SelectionPageDownAction";
    public static final String rtaSelectionPageLeftAction = "RTA.SelectionPageLeftAction";
    public static final String rtaSelectionPageRightAction = "RTA.SelectionPageRightAction";
    public static final String rtaTimeDateAction = "RTA.TimeDateAction";
    public static final String rtaToggleBookmarkAction = "RTA.ToggleBookmarkAction";
    public static final String rtaToggleTextModeAction = "RTA.ToggleTextModeAction";
    public static final String rtaUndoAction = "RTA.UndoAction";
    public static final String rtaUnselectAction = "RTA.UnselectAction";
    public static final String rtaUpperSelectionCaseAction = "RTA.UpperCaseAction";
    private static final RecordableTextAction[] defaultActions = new RecordableTextAction[]{new BeginAction("caret-begin", false), new BeginAction("selection-begin", true), new BeginLineAction("caret-begin-line", false), new BeginLineAction("selection-begin-line", true), new BeginRecordingMacroAction(), new BeginWordAction("caret-begin-word", false), new BeginWordAction("selection-begin-word", true), new ClipboardHistoryAction(), new CopyAction(), new CutAction(), new DefaultKeyTypedAction(), new DeleteLineAction(), new DeleteNextCharAction(), new DeletePrevCharAction(), new DeletePrevWordAction(), new DeleteRestOfLineAction(), new DumbCompleteWordAction(), new EndAction("caret-end", false), new EndAction("selection-end", true), new EndLineAction("caret-end-line", false), new EndLineAction("selection-end-line", true), new EndRecordingMacroAction(), new EndWordAction("caret-end-word", false), new EndWordAction("caret-end-word", true), new InsertBreakAction(), new InsertContentAction(), new InsertTabAction(), new InvertSelectionCaseAction(), new JoinLinesAction(), new LowerSelectionCaseAction(), new LineMoveAction("RTA.LineUpAction", -1), new LineMoveAction("RTA.LineDownAction", 1), new NextBookmarkAction("RTA.NextBookmarkAction", true), new NextBookmarkAction("RTA.PrevBookmarkAction", false), new NextVisualPositionAction("caret-forward", false, 3), new NextVisualPositionAction("caret-backward", false, 7), new NextVisualPositionAction("selection-forward", true, 3), new NextVisualPositionAction("selection-backward", true, 7), new NextVisualPositionAction("caret-up", false, 1), new NextVisualPositionAction("caret-down", false, 5), new NextVisualPositionAction("selection-up", true, 1), new NextVisualPositionAction("selection-down", true, 5), new NextOccurrenceAction("RTA.NextOccurrenceAction"), new PreviousOccurrenceAction("RTA.PrevOccurrenceAction"), new NextWordAction("caret-next-word", false), new NextWordAction("selection-next-word", true), new PageAction("RTA.SelectionPageLeftAction", true, true), new PageAction("RTA.SelectionPageRightAction", false, true), new PasteAction(), new PlaybackLastMacroAction(), new PreviousWordAction("caret-previous-word", false), new PreviousWordAction("selection-previous-word", true), new RedoAction(), new ScrollAction("RTA.ScrollUpAction", -1), new ScrollAction("RTA.ScrollDownAction", 1), new SelectAllAction(), new SelectLineAction(), new SelectWordAction(), new SetReadOnlyAction(), new SetWritableAction(), new ToggleBookmarkAction(), new ToggleTextModeAction(), new UndoAction(), new UnselectAction(), new UpperSelectionCaseAction(), new VerticalPageAction("page-up", -1, false), new VerticalPageAction("page-down", 1, false), new VerticalPageAction("RTA.SelectionPageUpAction", -1, true), new VerticalPageAction("RTA.SelectionPageDownAction", 1, true)};
    private static final int READBUFFER_SIZE = 32768;

    public IconRowHeader createIconRowHeader(RTextArea textArea) {
        return new IconRowHeader(textArea);
    }

    public LineNumberList createLineNumberList(RTextArea textArea) {
        return new LineNumberList(textArea);
    }

    @Override
    public Action[] getActions() {
        return defaultActions;
    }

    @Override
    public void read(Reader in, Document doc, int pos) throws IOException, BadLocationException {
        int nch;
        boolean wasEmpty;
        char[] buff = new char[32768];
        boolean lastWasCR = false;
        boolean isCRLF = false;
        boolean isCR = false;
        boolean bl = wasEmpty = doc.getLength() == 0;
        while ((nch = in.read(buff, 0, buff.length)) != -1) {
            int last = 0;
            block5: for (int counter = 0; counter < nch; ++counter) {
                switch (buff[counter]) {
                    case '\r': {
                        if (lastWasCR) {
                            isCR = true;
                            if (counter == 0) {
                                doc.insertString(pos, "\n", null);
                                ++pos;
                                continue block5;
                            }
                            buff[counter - 1] = 10;
                            continue block5;
                        }
                        lastWasCR = true;
                        continue block5;
                    }
                    case '\n': {
                        if (!lastWasCR) continue block5;
                        if (counter > last + 1) {
                            doc.insertString(pos, new String(buff, last, counter - last - 1), null);
                            pos += counter - last - 1;
                        }
                        lastWasCR = false;
                        last = counter;
                        isCRLF = true;
                        continue block5;
                    }
                    default: {
                        if (!lastWasCR) continue block5;
                        isCR = true;
                        if (counter == 0) {
                            doc.insertString(pos, "\n", null);
                            ++pos;
                        } else {
                            buff[counter - 1] = 10;
                        }
                        lastWasCR = false;
                    }
                }
            }
            if (last >= nch) continue;
            if (lastWasCR) {
                if (last >= nch - 1) continue;
                doc.insertString(pos, new String(buff, last, nch - last - 1), null);
                pos += nch - last - 1;
                continue;
            }
            doc.insertString(pos, new String(buff, last, nch - last), null);
            pos += nch - last;
        }
        if (lastWasCR) {
            doc.insertString(pos, "\n", null);
            isCR = true;
        }
        if (wasEmpty) {
            if (isCRLF) {
                doc.putProperty("__EndOfLine__", "\r\n");
            } else if (isCR) {
                doc.putProperty("__EndOfLine__", "\r");
            } else {
                doc.putProperty("__EndOfLine__", "\n");
            }
        }
    }

    public static class VerticalPageAction
    extends RecordableTextAction {
        private boolean select;
        private int direction;

        public VerticalPageAction(String name, int direction, boolean select) {
            super(name);
            this.select = select;
            this.direction = direction;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            Point magicPosition;
            Caret caret;
            Rectangle newVis;
            block6: {
                Rectangle visible = textArea.getVisibleRect();
                newVis = new Rectangle(visible);
                int selectedIndex = textArea.getCaretPosition();
                int scrollAmount = textArea.getScrollableBlockIncrement(visible, 1, this.direction);
                int initialY = visible.y;
                caret = textArea.getCaret();
                magicPosition = caret.getMagicCaretPosition();
                if (selectedIndex != -1) {
                    try {
                        Rectangle dotBounds = textArea.modelToView(selectedIndex);
                        int x = magicPosition != null ? magicPosition.x : dotBounds.x;
                        int h = dotBounds.height;
                        int yOffset = this.direction * ((int)Math.ceil((double)scrollAmount / (double)h) - 1) * h;
                        newVis.y = this.constrainY(textArea, initialY + yOffset, yOffset, visible.height);
                        int newIndex = visible.contains(dotBounds.x, dotBounds.y) ? textArea.viewToModel(new Point(x, this.constrainY(textArea, dotBounds.y + yOffset, 0, 0))) : (this.direction == -1 ? textArea.viewToModel(new Point(x, newVis.y)) : textArea.viewToModel(new Point(x, newVis.y + visible.height)));
                        newIndex = this.constrainOffset(textArea, newIndex);
                        if (newIndex == selectedIndex) break block6;
                        this.adjustScrollIfNecessary(textArea, newVis, initialY, newIndex);
                        if (this.select) {
                            textArea.moveCaretPosition(newIndex);
                            break block6;
                        }
                        textArea.setCaretPosition(newIndex);
                    } catch (BadLocationException badLocationException) {}
                } else {
                    int yOffset = this.direction * scrollAmount;
                    newVis.y = this.constrainY(textArea, initialY + yOffset, yOffset, visible.height);
                }
            }
            if (magicPosition != null) {
                caret.setMagicCaretPosition(magicPosition);
            }
            textArea.scrollRectToVisible(newVis);
        }

        private int constrainY(JTextComponent textArea, int y, int vis, int screenHeight) {
            if (y < 0) {
                y = 0;
            } else if (y + vis > textArea.getHeight()) {
                y = Math.max(0, textArea.getHeight() - screenHeight);
            }
            return y;
        }

        private int constrainOffset(JTextComponent text, int offset) {
            Document doc = text.getDocument();
            if (offset != 0 && offset > doc.getLength()) {
                offset = doc.getLength();
            }
            if (offset < 0) {
                offset = 0;
            }
            return offset;
        }

        private void adjustScrollIfNecessary(JTextComponent text, Rectangle visible, int initialY, int index) {
            try {
                Rectangle dotBounds = text.modelToView(index);
                if (dotBounds.y < visible.y || dotBounds.y > visible.y + visible.height || dotBounds.y + dotBounds.height > visible.y + visible.height) {
                    int y = dotBounds.y < visible.y ? dotBounds.y : dotBounds.y + dotBounds.height - visible.height;
                    if (this.direction == -1 && y < initialY || this.direction == 1 && y > initialY) {
                        visible.y = y;
                    }
                }
            } catch (BadLocationException badLocationException) {
                // empty catch block
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class UpperSelectionCaseAction
    extends RecordableTextAction {
        public UpperSelectionCaseAction() {
            super(RTextAreaEditorKit.rtaUpperSelectionCaseAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            String selection = textArea.getSelectedText();
            if (selection != null) {
                textArea.replaceSelection(selection.toUpperCase());
            }
            textArea.requestFocusInWindow();
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class UnselectAction
    extends RecordableTextAction {
        public UnselectAction() {
            super(RTextAreaEditorKit.rtaUnselectAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            textArea.setCaretPosition(textArea.getCaretPosition());
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaUnselectAction;
        }
    }

    public static class UndoAction
    extends RecordableTextAction {
        public UndoAction() {
            super(RTextAreaEditorKit.rtaUndoAction);
        }

        public UndoAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (textArea.isEnabled() && textArea.isEditable()) {
                textArea.undoLastAction();
                textArea.requestFocusInWindow();
            }
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaUndoAction;
        }
    }

    public static class ToggleTextModeAction
    extends RecordableTextAction {
        public ToggleTextModeAction() {
            super(RTextAreaEditorKit.rtaToggleTextModeAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            int textMode = textArea.getTextMode();
            if (textMode == 0) {
                textArea.setTextMode(1);
            } else {
                textArea.setTextMode(0);
            }
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaToggleTextModeAction;
        }
    }

    public static class ToggleBookmarkAction
    extends RecordableTextAction {
        public ToggleBookmarkAction() {
            super(RTextAreaEditorKit.rtaToggleBookmarkAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            Gutter gutter = RSyntaxUtilities.getGutter(textArea);
            if (gutter != null) {
                int line = textArea.getCaretLineNumber();
                try {
                    gutter.toggleBookmark(line);
                } catch (BadLocationException ble) {
                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                    ble.printStackTrace();
                }
            }
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaToggleBookmarkAction;
        }
    }

    public static class TimeDateAction
    extends RecordableTextAction {
        public TimeDateAction() {
            super(RTextAreaEditorKit.rtaTimeDateAction);
        }

        public TimeDateAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            Date today = new Date();
            DateFormat timeDateStamp = DateFormat.getDateTimeInstance();
            String dateString = timeDateStamp.format(today);
            textArea.replaceSelection(dateString);
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaTimeDateAction;
        }
    }

    public static class SetWritableAction
    extends RecordableTextAction {
        public SetWritableAction() {
            super("set-writable");
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            textArea.setEditable(true);
        }

        @Override
        public final String getMacroID() {
            return "set-writable";
        }

        @Override
        public boolean isRecordable() {
            return false;
        }
    }

    public static class SetReadOnlyAction
    extends RecordableTextAction {
        public SetReadOnlyAction() {
            super("set-read-only");
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            textArea.setEditable(false);
        }

        @Override
        public final String getMacroID() {
            return "set-read-only";
        }

        @Override
        public boolean isRecordable() {
            return false;
        }
    }

    public static class SelectWordAction
    extends RecordableTextAction {
        protected Action start;
        protected Action end;

        public SelectWordAction() {
            super("select-word");
            this.createActions();
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            this.start.actionPerformed(e);
            this.end.actionPerformed(e);
        }

        protected void createActions() {
            this.start = new BeginWordAction("pigdog", false);
            this.end = new EndWordAction("pigdog", true);
        }

        @Override
        public final String getMacroID() {
            return "select-word";
        }
    }

    public static class SelectLineAction
    extends RecordableTextAction {
        private Action start = new BeginLineAction("pigdog", false);
        private Action end = new EndLineAction("pigdog", true);

        public SelectLineAction() {
            super("select-line");
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            this.start.actionPerformed(e);
            this.end.actionPerformed(e);
        }

        @Override
        public final String getMacroID() {
            return "select-line";
        }
    }

    public static class SelectAllAction
    extends RecordableTextAction {
        public SelectAllAction() {
            super("select-all");
        }

        public SelectAllAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            Document doc = textArea.getDocument();
            textArea.setCaretPosition(0);
            textArea.moveCaretPosition(doc.getLength());
        }

        @Override
        public final String getMacroID() {
            return "select-all";
        }
    }

    public static class ScrollAction
    extends RecordableTextAction {
        private int delta;

        public ScrollAction(String name, int delta) {
            super(name);
            this.delta = delta;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            Container parent = textArea.getParent();
            if (parent instanceof JViewport) {
                JViewport viewport = (JViewport)parent;
                Point p = viewport.getViewPosition();
                p.y += this.delta * textArea.getLineHeight();
                if (p.y < 0) {
                    p.y = 0;
                } else {
                    Rectangle viewRect = viewport.getViewRect();
                    int visibleEnd = p.y + viewRect.height;
                    if (visibleEnd >= textArea.getHeight()) {
                        p.y = textArea.getHeight() - viewRect.height;
                    }
                }
                viewport.setViewPosition(p);
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class RedoAction
    extends RecordableTextAction {
        public RedoAction() {
            super(RTextAreaEditorKit.rtaRedoAction);
        }

        public RedoAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (textArea.isEnabled() && textArea.isEditable()) {
                textArea.redoLastAction();
                textArea.requestFocusInWindow();
            }
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaRedoAction;
        }
    }

    public static class PreviousWordAction
    extends RecordableTextAction {
        private boolean select;

        public PreviousWordAction(String name, boolean select) {
            super(name);
            this.select = select;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            int offs = textArea.getCaretPosition();
            boolean failed = false;
            try {
                Element curPara = Utilities.getParagraphElement(textArea, offs);
                offs = this.getPreviousWord(textArea, offs);
                if (offs < curPara.getStartOffset()) {
                    offs = Utilities.getParagraphElement(textArea, offs).getEndOffset() - 1;
                }
            } catch (BadLocationException bl) {
                if (offs != 0) {
                    offs = 0;
                }
                failed = true;
            }
            if (!failed) {
                if (this.select) {
                    textArea.moveCaretPosition(offs);
                } else {
                    textArea.setCaretPosition(offs);
                }
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }

        protected int getPreviousWord(RTextArea textArea, int offs) throws BadLocationException {
            return Utilities.getPreviousWord(textArea, offs);
        }
    }

    public static class PreviousOccurrenceAction
    extends RecordableTextAction {
        public PreviousOccurrenceAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            String selectedText = textArea.getSelectedText();
            if (!(selectedText != null && selectedText.length() != 0 || (selectedText = RTextArea.getSelectedOccurrenceText()) != null && selectedText.length() != 0)) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            SearchContext context = new SearchContext(selectedText);
            if (!textArea.getMarkAllOnOccurrenceSearches()) {
                context.setMarkAll(false);
            }
            context.setSearchForward(false);
            if (!SearchEngine.find(textArea, context).wasFound()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
            RTextArea.setSelectedOccurrenceText(selectedText);
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class PlaybackLastMacroAction
    extends RecordableTextAction {
        public PlaybackLastMacroAction() {
            super(RTextAreaEditorKit.rtaPlaybackLastMacroAction);
        }

        public PlaybackLastMacroAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            textArea.playbackLastMacro();
        }

        @Override
        public boolean isRecordable() {
            return false;
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaPlaybackLastMacroAction;
        }
    }

    public static class PasteAction
    extends RecordableTextAction {
        public PasteAction() {
            super("paste-from-clipboard");
        }

        public PasteAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            textArea.paste();
            textArea.requestFocusInWindow();
        }

        @Override
        public final String getMacroID() {
            return "paste-from-clipboard";
        }
    }

    static class PageAction
    extends RecordableTextAction {
        private boolean select;
        private boolean left;

        PageAction(String name, boolean left, boolean select) {
            super(name);
            this.select = select;
            this.left = left;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            Rectangle visible = new Rectangle();
            textArea.computeVisibleRect(visible);
            visible.x = this.left ? Math.max(0, visible.x - visible.width) : (visible.x += visible.width);
            int selectedIndex = textArea.getCaretPosition();
            if (selectedIndex != -1) {
                selectedIndex = this.left ? textArea.viewToModel(new Point(visible.x, visible.y)) : textArea.viewToModel(new Point(visible.x + visible.width - 1, visible.y + visible.height - 1));
                Document doc = textArea.getDocument();
                if (selectedIndex != 0 && selectedIndex > doc.getLength() - 1) {
                    selectedIndex = doc.getLength() - 1;
                } else if (selectedIndex < 0) {
                    selectedIndex = 0;
                }
                if (this.select) {
                    textArea.moveCaretPosition(selectedIndex);
                } else {
                    textArea.setCaretPosition(selectedIndex);
                }
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class NextWordAction
    extends RecordableTextAction {
        private boolean select;

        public NextWordAction(String name, boolean select) {
            super(name);
            this.select = select;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            int offs;
            block5: {
                int oldOffs = offs = textArea.getCaretPosition();
                Element curPara = Utilities.getParagraphElement(textArea, offs);
                try {
                    offs = this.getNextWord(textArea, offs);
                    if (offs >= curPara.getEndOffset() && oldOffs != curPara.getEndOffset() - 1) {
                        offs = curPara.getEndOffset() - 1;
                    }
                } catch (BadLocationException ble) {
                    int end = textArea.getDocument().getLength();
                    if (offs == end) break block5;
                    offs = oldOffs != curPara.getEndOffset() - 1 ? curPara.getEndOffset() - 1 : end;
                }
            }
            if (this.select) {
                textArea.moveCaretPosition(offs);
            } else {
                textArea.setCaretPosition(offs);
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }

        protected int getNextWord(RTextArea textArea, int offs) throws BadLocationException {
            return Utilities.getNextWord(textArea, offs);
        }
    }

    public static class NextVisualPositionAction
    extends RecordableTextAction {
        private boolean select;
        private int direction;

        public NextVisualPositionAction(String nm, boolean select, int dir) {
            super(nm);
            this.select = select;
            this.direction = dir;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            Caret caret = textArea.getCaret();
            int dot = caret.getDot();
            if (!this.select) {
                switch (this.direction) {
                    case 3: {
                        int mark = caret.getMark();
                        if (dot == mark) break;
                        caret.setDot(Math.max(dot, mark));
                        return;
                    }
                    case 7: {
                        int mark = caret.getMark();
                        if (dot == mark) break;
                        caret.setDot(Math.min(dot, mark));
                        return;
                    }
                }
            }
            Position.Bias[] bias = new Position.Bias[1];
            Point magicPosition = caret.getMagicCaretPosition();
            try {
                NavigationFilter filter;
                if (magicPosition == null && (this.direction == 1 || this.direction == 5)) {
                    Rectangle r = textArea.modelToView(dot);
                    magicPosition = new Point(r.x, r.y);
                }
                dot = (filter = textArea.getNavigationFilter()) != null ? filter.getNextVisualPositionFrom(textArea, dot, Position.Bias.Forward, this.direction, bias) : textArea.getUI().getNextVisualPositionFrom(textArea, dot, Position.Bias.Forward, this.direction, bias);
                if (this.select) {
                    caret.moveDot(dot);
                } else {
                    caret.setDot(dot);
                }
                if (magicPosition != null && (this.direction == 1 || this.direction == 5)) {
                    caret.setMagicCaretPosition(magicPosition);
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class NextOccurrenceAction
    extends RecordableTextAction {
        public NextOccurrenceAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            String selectedText = textArea.getSelectedText();
            if (!(selectedText != null && selectedText.length() != 0 || (selectedText = RTextArea.getSelectedOccurrenceText()) != null && selectedText.length() != 0)) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            SearchContext context = new SearchContext(selectedText);
            if (!textArea.getMarkAllOnOccurrenceSearches()) {
                context.setMarkAll(false);
            }
            if (!SearchEngine.find(textArea, context).wasFound()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
            RTextArea.setSelectedOccurrenceText(selectedText);
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class NextBookmarkAction
    extends RecordableTextAction {
        private boolean forward;

        public NextBookmarkAction(String name, boolean forward) {
            super(name);
            this.forward = forward;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            Gutter gutter = RSyntaxUtilities.getGutter(textArea);
            if (gutter != null) {
                try {
                    RSyntaxTextArea rsta;
                    GutterIconInfo[] bookmarks = gutter.getBookmarks();
                    if (bookmarks.length == 0) {
                        UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                        return;
                    }
                    GutterIconInfo moveTo = null;
                    int curLine = textArea.getCaretLineNumber();
                    if (this.forward) {
                        for (GutterIconInfo bookmark : bookmarks) {
                            int offs = bookmark.getMarkedOffset();
                            int line = textArea.getLineOfOffset(offs);
                            if (line <= curLine) continue;
                            moveTo = bookmark;
                            break;
                        }
                        if (moveTo == null) {
                            moveTo = bookmarks[0];
                        }
                    } else {
                        for (int i = bookmarks.length - 1; i >= 0; --i) {
                            GutterIconInfo bookmark = bookmarks[i];
                            int offs = bookmark.getMarkedOffset();
                            int line = textArea.getLineOfOffset(offs);
                            if (line >= curLine) continue;
                            moveTo = bookmark;
                            break;
                        }
                        if (moveTo == null) {
                            moveTo = bookmarks[bookmarks.length - 1];
                        }
                    }
                    int offs = moveTo.getMarkedOffset();
                    if (textArea instanceof RSyntaxTextArea && (rsta = (RSyntaxTextArea)textArea).isCodeFoldingEnabled()) {
                        rsta.getFoldManager().ensureOffsetNotInClosedFold(offs);
                    }
                    int line = textArea.getLineOfOffset(offs);
                    offs = textArea.getLineStartOffset(line);
                    textArea.setCaretPosition(offs);
                } catch (BadLocationException ble) {
                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                    ble.printStackTrace();
                }
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class LowerSelectionCaseAction
    extends RecordableTextAction {
        public LowerSelectionCaseAction() {
            super(RTextAreaEditorKit.rtaLowerSelectionCaseAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            String selection = textArea.getSelectedText();
            if (selection != null) {
                textArea.replaceSelection(selection.toLowerCase());
            }
            textArea.requestFocusInWindow();
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class LineMoveAction
    extends RecordableTextAction {
        private int moveAmt;

        public LineMoveAction(String name, int moveAmt) {
            super(name);
            this.moveAmt = moveAmt;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            block6: {
                if (!textArea.isEditable() || !textArea.isEnabled()) {
                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                    return;
                }
                try {
                    Element elem;
                    int dot = textArea.getCaretPosition();
                    int mark = textArea.getCaret().getMark();
                    Document doc = textArea.getDocument();
                    Element root = doc.getDefaultRootElement();
                    int startLine = root.getElementIndex(Math.min(dot, mark));
                    int endLine = root.getElementIndex(Math.max(dot, mark));
                    int moveCount = endLine - startLine + 1;
                    if (moveCount > 1 && (dot == (elem = root.getElement(endLine)).getStartOffset() || mark == elem.getStartOffset())) {
                        --moveCount;
                    }
                    if (this.moveAmt == -1 && startLine > 0) {
                        this.moveLineUp(textArea, startLine, moveCount);
                        break block6;
                    }
                    if (this.moveAmt == 1 && endLine < root.getElementCount() - 1) {
                        this.moveLineDown(textArea, startLine, moveCount);
                        break block6;
                    }
                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                    return;
                } catch (BadLocationException ble) {
                    ble.printStackTrace();
                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                    return;
                }
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void moveLineDown(RTextArea textArea, int line, int lineCount) throws BadLocationException {
            Document doc = textArea.getDocument();
            Element root = doc.getDefaultRootElement();
            Element elem = root.getElement(line);
            int start = elem.getStartOffset();
            int endLine = line + lineCount - 1;
            elem = root.getElement(endLine);
            int end = elem.getEndOffset();
            textArea.beginAtomicEdit();
            try {
                String text = doc.getText(start, end - start);
                doc.remove(start, end - start);
                int insertLine = Math.min(line + 1, textArea.getLineCount());
                boolean newlineInserted = false;
                if (insertLine == textArea.getLineCount()) {
                    textArea.append("\n");
                    newlineInserted = true;
                }
                int insertOffs = textArea.getLineStartOffset(insertLine);
                doc.insertString(insertOffs, text, null);
                textArea.setSelectionStart(insertOffs);
                textArea.setSelectionEnd(insertOffs + text.length() - 1);
                if (newlineInserted) {
                    doc.remove(doc.getLength() - 1, 1);
                }
            } finally {
                textArea.endAtomicEdit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void moveLineUp(RTextArea textArea, int line, int moveCount) throws BadLocationException {
            Document doc = textArea.getDocument();
            Element root = doc.getDefaultRootElement();
            Element elem = root.getElement(line);
            int start = elem.getStartOffset();
            int endLine = line + moveCount - 1;
            elem = root.getElement(endLine);
            int end = elem.getEndOffset();
            int lineCount = textArea.getLineCount();
            boolean movingLastLine = false;
            if (endLine == lineCount - 1) {
                movingLastLine = true;
                --end;
            }
            int insertLine = Math.max(line - 1, 0);
            textArea.beginAtomicEdit();
            try {
                String text = doc.getText(start, end - start);
                if (movingLastLine) {
                    text = text + '\n';
                }
                doc.remove(start, end - start);
                int insertOffs = textArea.getLineStartOffset(insertLine);
                doc.insertString(insertOffs, text, null);
                textArea.setSelectionStart(insertOffs);
                int selEnd = insertOffs + text.length() - 1;
                textArea.setSelectionEnd(selEnd);
                if (movingLastLine) {
                    doc.remove(doc.getLength() - 1, 1);
                }
            } finally {
                textArea.endAtomicEdit();
            }
        }
    }

    public static class JoinLinesAction
    extends RecordableTextAction {
        public JoinLinesAction() {
            super(RTextAreaEditorKit.rtaJoinLinesAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            try {
                Caret c = textArea.getCaret();
                int caretPos = c.getDot();
                Document doc = textArea.getDocument();
                Element map = doc.getDefaultRootElement();
                int lineCount = map.getElementCount();
                int line = map.getElementIndex(caretPos);
                if (line == lineCount - 1) {
                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                    return;
                }
                Element lineElem = map.getElement(line);
                caretPos = lineElem.getEndOffset() - 1;
                c.setDot(caretPos);
                doc.remove(caretPos, 1);
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
            textArea.requestFocusInWindow();
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class InvertSelectionCaseAction
    extends RecordableTextAction {
        public InvertSelectionCaseAction() {
            super(RTextAreaEditorKit.rtaInvertSelectionCaseAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            String selection = textArea.getSelectedText();
            if (selection != null) {
                StringBuilder buffer = new StringBuilder(selection);
                int length = buffer.length();
                for (int i = 0; i < length; ++i) {
                    char c = buffer.charAt(i);
                    if (Character.isUpperCase(c)) {
                        buffer.setCharAt(i, Character.toLowerCase(c));
                        continue;
                    }
                    if (!Character.isLowerCase(c)) continue;
                    buffer.setCharAt(i, Character.toUpperCase(c));
                }
                textArea.replaceSelection(buffer.toString());
            }
            textArea.requestFocusInWindow();
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class InsertTabAction
    extends RecordableTextAction {
        public InsertTabAction() {
            super("insert-tab");
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            textArea.replaceSelection("\t");
        }

        @Override
        public final String getMacroID() {
            return "insert-tab";
        }
    }

    public static class InsertContentAction
    extends RecordableTextAction {
        public InsertContentAction() {
            super("insert-content", null, null, null, null);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            String content = e.getActionCommand();
            if (content != null) {
                textArea.replaceSelection(content);
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
        }

        @Override
        public final String getMacroID() {
            return "insert-content";
        }
    }

    public static class InsertBreakAction
    extends RecordableTextAction {
        public InsertBreakAction() {
            super("insert-break");
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            textArea.replaceSelection("\n");
        }

        @Override
        public final String getMacroID() {
            return "insert-break";
        }

        @Override
        public boolean isEnabled() {
            JTextComponent tc = this.getTextComponent(null);
            return tc == null || tc.isEditable() ? super.isEnabled() : false;
        }
    }

    public static class IncreaseFontSizeAction
    extends RecordableTextAction {
        protected float increaseAmount;
        protected static final float MAXIMUM_SIZE = 40.0f;

        public IncreaseFontSizeAction() {
            super(RTextAreaEditorKit.rtaIncreaseFontSizeAction);
            this.initialize();
        }

        public IncreaseFontSizeAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            this.initialize();
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            Font font = textArea.getFont();
            float oldSize = font.getSize2D();
            float newSize = oldSize + this.increaseAmount;
            if (newSize <= 40.0f) {
                font = font.deriveFont(newSize);
                textArea.setFont(font);
            } else if (oldSize < 40.0f) {
                font = font.deriveFont(40.0f);
                textArea.setFont(font);
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
            textArea.requestFocusInWindow();
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaIncreaseFontSizeAction;
        }

        protected void initialize() {
            this.increaseAmount = 1.0f;
        }
    }

    protected static class EndWordAction
    extends RecordableTextAction {
        private boolean select;

        protected EndWordAction(String name, boolean select) {
            super(name);
            this.select = select;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            try {
                int offs = textArea.getCaretPosition();
                int endOffs = this.getWordEnd(textArea, offs);
                if (this.select) {
                    textArea.moveCaretPosition(endOffs);
                } else {
                    textArea.setCaretPosition(endOffs);
                }
            } catch (BadLocationException ble) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }

        protected int getWordEnd(RTextArea textArea, int offs) throws BadLocationException {
            return Utilities.getWordEnd(textArea, offs);
        }
    }

    public static class EndRecordingMacroAction
    extends RecordableTextAction {
        public EndRecordingMacroAction() {
            super(RTextAreaEditorKit.rtaEndRecordingMacroAction);
        }

        public EndRecordingMacroAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            RTextArea.endRecordingMacro();
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaEndRecordingMacroAction;
        }

        @Override
        public boolean isRecordable() {
            return false;
        }
    }

    public static class EndLineAction
    extends RecordableTextAction {
        private boolean select;

        public EndLineAction(String name, boolean select) {
            super(name);
            this.select = select;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            int offs = textArea.getCaretPosition();
            int endOffs = 0;
            try {
                if (textArea.getLineWrap()) {
                    endOffs = Utilities.getRowEnd(textArea, offs);
                } else {
                    Element root = textArea.getDocument().getDefaultRootElement();
                    int line = root.getElementIndex(offs);
                    endOffs = root.getElement(line).getEndOffset() - 1;
                }
                if (this.select) {
                    textArea.moveCaretPosition(endOffs);
                } else {
                    textArea.setCaretPosition(endOffs);
                }
            } catch (Exception ex) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class EndAction
    extends RecordableTextAction {
        private boolean select;

        public EndAction(String name, boolean select) {
            super(name);
            this.select = select;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            int dot = this.getVisibleEnd(textArea);
            if (this.select) {
                textArea.moveCaretPosition(dot);
            } else {
                textArea.setCaretPosition(dot);
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }

        protected int getVisibleEnd(RTextArea textArea) {
            return textArea.getDocument().getLength();
        }
    }

    public static class DumbCompleteWordAction
    extends RecordableTextAction {
        private int lastWordStart = -1;
        private int lastDot = -1;
        private int searchOffs = -1;
        private String lastPrefix;

        public DumbCompleteWordAction() {
            super(RTextAreaEditorKit.rtaDumbCompleteWordAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                return;
            }
            try {
                int dot = textArea.getCaretPosition();
                if (dot == 0) {
                    return;
                }
                int curWordStart = this.getWordStart(textArea, dot);
                if (this.lastWordStart != curWordStart || dot != this.lastDot) {
                    this.lastPrefix = textArea.getText(curWordStart, dot - curWordStart);
                    if (!this.isAcceptablePrefix(this.lastPrefix)) {
                        UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                        return;
                    }
                    this.lastWordStart = dot - this.lastPrefix.length();
                    this.searchOffs = Math.max(this.lastWordStart - 1, 0);
                }
                while (this.searchOffs > 0) {
                    int wordStart;
                    try {
                        wordStart = this.getPreviousWord(textArea, this.searchOffs);
                    } catch (BadLocationException ble) {
                        wordStart = -1;
                    }
                    if (wordStart == -1) {
                        UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                    } else {
                        int end = this.getWordEnd(textArea, wordStart);
                        String word = textArea.getText(wordStart, end - wordStart);
                        this.searchOffs = wordStart;
                        if (!word.startsWith(this.lastPrefix)) continue;
                        textArea.replaceRange(word, this.lastWordStart, dot);
                        this.lastDot = textArea.getCaretPosition();
                    }
                    break;
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }

        protected int getPreviousWord(RTextArea textArea, int offs) throws BadLocationException {
            return Utilities.getPreviousWord(textArea, offs);
        }

        protected int getWordEnd(RTextArea textArea, int offs) throws BadLocationException {
            return Utilities.getWordEnd(textArea, offs);
        }

        protected int getWordStart(RTextArea textArea, int offs) throws BadLocationException {
            return Utilities.getWordStart(textArea, offs);
        }

        protected boolean isAcceptablePrefix(String prefix) {
            return prefix.length() > 0 && Character.isLetter(prefix.charAt(prefix.length() - 1));
        }
    }

    public static class DeleteRestOfLineAction
    extends RecordableTextAction {
        public DeleteRestOfLineAction() {
            super(RTextAreaEditorKit.rtaDeleteRestOfLineAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            try {
                Document document = textArea.getDocument();
                int caretPosition = textArea.getCaretPosition();
                Element map = document.getDefaultRootElement();
                int currentLineNum = map.getElementIndex(caretPosition);
                Element currentLineElement = map.getElement(currentLineNum);
                int currentLineEnd = currentLineElement.getEndOffset() - 1;
                if (caretPosition < currentLineEnd) {
                    document.remove(caretPosition, currentLineEnd - caretPosition);
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaDeleteRestOfLineAction;
        }
    }

    public static class DeletePrevWordAction
    extends RecordableTextAction {
        public DeletePrevWordAction() {
            super(RTextAreaEditorKit.rtaDeletePrevWordAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            try {
                int end = textArea.getSelectionStart();
                int start = this.getPreviousWordStart(textArea, end);
                if (end > start) {
                    textArea.getDocument().remove(start, end - start);
                }
            } catch (BadLocationException ex) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
        }

        @Override
        public String getMacroID() {
            return RTextAreaEditorKit.rtaDeletePrevWordAction;
        }

        protected int getPreviousWordStart(RTextArea textArea, int end) throws BadLocationException {
            return Utilities.getPreviousWord(textArea, end);
        }
    }

    public static class DeletePrevCharAction
    extends RecordableTextAction {
        public DeletePrevCharAction() {
            super("delete-previous");
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            boolean beep = true;
            if (textArea != null && textArea.isEditable()) {
                try {
                    Document doc = textArea.getDocument();
                    Caret caret = textArea.getCaret();
                    int dot = caret.getDot();
                    int mark = caret.getMark();
                    if (dot != mark) {
                        doc.remove(Math.min(dot, mark), Math.abs(dot - mark));
                        beep = false;
                    } else if (dot > 0) {
                        int delChars = 1;
                        if (dot > 1) {
                            String dotChars = doc.getText(dot - 2, 2);
                            char c0 = dotChars.charAt(0);
                            char c1 = dotChars.charAt(1);
                            if (c0 >= '\ud800' && c0 <= '\udbff' && c1 >= '\udc00' && c1 <= '\udfff') {
                                delChars = 2;
                            }
                        }
                        doc.remove(dot - delChars, delChars);
                        beep = false;
                    }
                } catch (BadLocationException badLocationException) {
                    // empty catch block
                }
            }
            if (beep) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
        }

        @Override
        public final String getMacroID() {
            return "delete-previous";
        }
    }

    public static class DeleteNextCharAction
    extends RecordableTextAction {
        public DeleteNextCharAction() {
            super("delete-next", null, null, null, null);
        }

        public DeleteNextCharAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            boolean beep = true;
            if (textArea != null && textArea.isEditable()) {
                try {
                    Document doc = textArea.getDocument();
                    Caret caret = textArea.getCaret();
                    int dot = caret.getDot();
                    int mark = caret.getMark();
                    if (dot != mark) {
                        doc.remove(Math.min(dot, mark), Math.abs(dot - mark));
                        beep = false;
                    } else if (dot < doc.getLength()) {
                        int delChars = 1;
                        if (dot < doc.getLength() - 1) {
                            String dotChars = doc.getText(dot, 2);
                            char c0 = dotChars.charAt(0);
                            char c1 = dotChars.charAt(1);
                            if (c0 >= '\ud800' && c0 <= '\udbff' && c1 >= '\udc00' && c1 <= '\udfff') {
                                delChars = 2;
                            }
                        }
                        doc.remove(dot, delChars);
                        beep = false;
                    }
                } catch (BadLocationException badLocationException) {
                    // empty catch block
                }
            }
            if (beep) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
            if (textArea != null) {
                textArea.requestFocusInWindow();
            }
        }

        @Override
        public final String getMacroID() {
            return "delete-next";
        }
    }

    public static class DeleteLineAction
    extends RecordableTextAction {
        public DeleteLineAction() {
            super(RTextAreaEditorKit.rtaDeleteLineAction, null, null, null, null);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            int selStart = textArea.getSelectionStart();
            int selEnd = textArea.getSelectionEnd();
            try {
                int line1 = textArea.getLineOfOffset(selStart);
                int startOffs = textArea.getLineStartOffset(line1);
                int line2 = textArea.getLineOfOffset(selEnd);
                int endOffs = textArea.getLineEndOffset(line2);
                if (line2 > line1 && selEnd == textArea.getLineStartOffset(line2)) {
                    endOffs = selEnd;
                }
                textArea.replaceRange(null, startOffs, endOffs);
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaDeleteLineAction;
        }
    }

    public static class DefaultKeyTypedAction
    extends RecordableTextAction {
        private Action delegate = new DefaultEditorKit.DefaultKeyTypedAction();

        public DefaultKeyTypedAction() {
            super("default-typed", null, null, null, null);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            this.delegate.actionPerformed(e);
        }

        @Override
        public final String getMacroID() {
            return "default-typed";
        }
    }

    public static class DecreaseFontSizeAction
    extends RecordableTextAction {
        protected float decreaseAmount;
        protected static final float MINIMUM_SIZE = 2.0f;

        public DecreaseFontSizeAction() {
            super(RTextAreaEditorKit.rtaDecreaseFontSizeAction);
            this.initialize();
        }

        public DecreaseFontSizeAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            this.initialize();
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            Font font = textArea.getFont();
            float oldSize = font.getSize2D();
            float newSize = oldSize - this.decreaseAmount;
            if (newSize >= 2.0f) {
                font = font.deriveFont(newSize);
                textArea.setFont(font);
            } else if (oldSize > 2.0f) {
                font = font.deriveFont(2.0f);
                textArea.setFont(font);
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
            textArea.requestFocusInWindow();
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaDecreaseFontSizeAction;
        }

        protected void initialize() {
            this.decreaseAmount = 1.0f;
        }
    }

    public static class CutAction
    extends RecordableTextAction {
        public CutAction() {
            super("cut-to-clipboard");
        }

        public CutAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            textArea.cut();
            textArea.requestFocusInWindow();
        }

        @Override
        public final String getMacroID() {
            return "cut-to-clipboard";
        }
    }

    public static class CopyAction
    extends RecordableTextAction {
        public CopyAction() {
            super("copy-to-clipboard");
        }

        public CopyAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            textArea.copy();
            textArea.requestFocusInWindow();
        }

        @Override
        public final String getMacroID() {
            return "copy-to-clipboard";
        }
    }

    public static class ClipboardHistoryAction
    extends RecordableTextAction {
        private ClipboardHistory clipboardHistory = ClipboardHistory.get();

        public ClipboardHistoryAction() {
            super(RTextAreaEditorKit.clipboardHistoryAction);
        }

        public ClipboardHistoryAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            Window owner = SwingUtilities.getWindowAncestor(textArea);
            ClipboardHistoryPopup popup = new ClipboardHistoryPopup(owner, textArea);
            popup.setContents(this.clipboardHistory.getHistory());
            popup.setVisible(true);
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.clipboardHistoryAction;
        }
    }

    protected static class BeginWordAction
    extends RecordableTextAction {
        private boolean select;

        protected BeginWordAction(String name, boolean select) {
            super(name);
            this.select = select;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            try {
                int offs = textArea.getCaretPosition();
                int begOffs = this.getWordStart(textArea, offs);
                if (this.select) {
                    textArea.moveCaretPosition(begOffs);
                } else {
                    textArea.setCaretPosition(begOffs);
                }
            } catch (BadLocationException ble) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }

        protected int getWordStart(RTextArea textArea, int offs) throws BadLocationException {
            return Utilities.getWordStart(textArea, offs);
        }
    }

    public static class BeginRecordingMacroAction
    extends RecordableTextAction {
        public BeginRecordingMacroAction() {
            super(RTextAreaEditorKit.rtaBeginRecordingMacroAction);
        }

        public BeginRecordingMacroAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            RTextArea.beginRecordingMacro();
        }

        @Override
        public boolean isRecordable() {
            return false;
        }

        @Override
        public final String getMacroID() {
            return RTextAreaEditorKit.rtaBeginRecordingMacroAction;
        }
    }

    public static class BeginLineAction
    extends RecordableTextAction {
        private Segment currentLine = new Segment();
        private boolean select;

        public BeginLineAction(String name, boolean select) {
            super(name);
            this.select = select;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            int newPos = 0;
            try {
                if (textArea.getLineWrap()) {
                    int begOffs;
                    int offs = textArea.getCaretPosition();
                    newPos = begOffs = Utilities.getRowStart(textArea, offs);
                } else {
                    int caretPosition = textArea.getCaretPosition();
                    Document document = textArea.getDocument();
                    Element map = document.getDefaultRootElement();
                    int currentLineNum = map.getElementIndex(caretPosition);
                    Element currentLineElement = map.getElement(currentLineNum);
                    int currentLineStart = currentLineElement.getStartOffset();
                    int currentLineEnd = currentLineElement.getEndOffset();
                    int count = currentLineEnd - currentLineStart;
                    if (count > 0) {
                        document.getText(currentLineStart, count, this.currentLine);
                        int firstNonWhitespace = this.getFirstNonWhitespacePos();
                        firstNonWhitespace = currentLineStart + (firstNonWhitespace - this.currentLine.offset);
                        newPos = caretPosition != firstNonWhitespace ? firstNonWhitespace : currentLineStart;
                    } else {
                        newPos = currentLineStart;
                    }
                }
                if (this.select) {
                    textArea.moveCaretPosition(newPos);
                } else {
                    textArea.setCaretPosition(newPos);
                }
            } catch (BadLocationException ble) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                ble.printStackTrace();
            }
        }

        private int getFirstNonWhitespacePos() {
            int offset = this.currentLine.offset;
            int end = offset + this.currentLine.count - 1;
            int pos = offset;
            char[] array = this.currentLine.array;
            char currentChar = array[pos];
            while ((currentChar == '\t' || currentChar == ' ') && ++pos < end) {
                currentChar = array[pos];
            }
            return pos;
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class BeginAction
    extends RecordableTextAction {
        private boolean select;

        public BeginAction(String name, boolean select) {
            super(name);
            this.select = select;
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (this.select) {
                textArea.moveCaretPosition(0);
            } else {
                textArea.setCaretPosition(0);
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class BeepAction
    extends RecordableTextAction {
        public BeepAction() {
            super("beep");
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            UIManager.getLookAndFeel().provideErrorFeedback(textArea);
        }

        @Override
        public final String getMacroID() {
            return "beep";
        }
    }
}

