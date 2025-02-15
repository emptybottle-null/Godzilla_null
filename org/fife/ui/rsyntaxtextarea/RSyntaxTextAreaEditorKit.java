/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import java.util.Stack;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Segment;
import javax.swing.text.TextAction;
import org.fife.ui.rsyntaxtextarea.CodeTemplateManager;
import org.fife.ui.rsyntaxtextarea.FoldingAwareIconRowHeader;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Style;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldCollapser;
import org.fife.ui.rsyntaxtextarea.folding.FoldManager;
import org.fife.ui.rsyntaxtextarea.templates.CodeTemplate;
import org.fife.ui.rtextarea.IconRowHeader;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextAreaEditorKit;
import org.fife.ui.rtextarea.RecordableTextAction;

public class RSyntaxTextAreaEditorKit
extends RTextAreaEditorKit {
    private static final long serialVersionUID = 1L;
    public static final String rstaCloseCurlyBraceAction = "RSTA.CloseCurlyBraceAction";
    public static final String rstaCloseMarkupTagAction = "RSTA.CloseMarkupTagAction";
    public static final String rstaCollapseAllFoldsAction = "RSTA.CollapseAllFoldsAction";
    public static final String rstaCollapseAllCommentFoldsAction = "RSTA.CollapseAllCommentFoldsAction";
    public static final String rstaCollapseFoldAction = "RSTA.CollapseFoldAction";
    public static final String rstaCopyAsStyledTextAction = "RSTA.CopyAsStyledTextAction";
    public static final String rstaDecreaseIndentAction = "RSTA.DecreaseIndentAction";
    public static final String rstaExpandAllFoldsAction = "RSTA.ExpandAllFoldsAction";
    public static final String rstaExpandFoldAction = "RSTA.ExpandFoldAction";
    public static final String rstaGoToMatchingBracketAction = "RSTA.GoToMatchingBracketAction";
    public static final String rstaPossiblyInsertTemplateAction = "RSTA.TemplateAction";
    public static final String rstaToggleCommentAction = "RSTA.ToggleCommentAction";
    public static final String rstaToggleCurrentFoldAction = "RSTA.ToggleCurrentFoldAction";
    private static final String MSG = "org.fife.ui.rsyntaxtextarea.RSyntaxTextArea";
    private static final ResourceBundle msg = ResourceBundle.getBundle("org.fife.ui.rsyntaxtextarea.RSyntaxTextArea");
    private static final Action[] defaultActions = new Action[]{new CloseCurlyBraceAction(), new CloseMarkupTagAction(), new BeginWordAction("caret-begin-word", false), new BeginWordAction("selection-begin-word", true), new ChangeFoldStateAction("RSTA.CollapseFoldAction", true), new ChangeFoldStateAction("RSTA.ExpandFoldAction", false), new CollapseAllFoldsAction(), new CopyAsStyledTextAction(), new DecreaseIndentAction(), new DeletePrevWordAction(), new DumbCompleteWordAction(), new RTextAreaEditorKit.EndAction("caret-end", false), new RTextAreaEditorKit.EndAction("selection-end", true), new EndWordAction("caret-end-word", false), new EndWordAction("caret-end-word", true), new ExpandAllFoldsAction(), new GoToMatchingBracketAction(), new InsertBreakAction(), new InsertTabAction(), new NextWordAction("caret-next-word", false), new NextWordAction("selection-next-word", true), new PossiblyInsertTemplateAction(), new PreviousWordAction("caret-previous-word", false), new PreviousWordAction("selection-previous-word", true), new SelectWordAction(), new ToggleCommentAction()};

    @Override
    public Document createDefaultDocument() {
        return new RSyntaxDocument("text/plain");
    }

    @Override
    public IconRowHeader createIconRowHeader(RTextArea textArea) {
        return new FoldingAwareIconRowHeader((RSyntaxTextArea)textArea);
    }

    @Override
    public Action[] getActions() {
        return TextAction.augmentList(super.getActions(), defaultActions);
    }

    public static String getString(String key) {
        return msg.getString(key);
    }

    public static class ToggleCurrentFoldAction
    extends FoldRelatedAction {
        private static final long serialVersionUID = 1L;

        public ToggleCurrentFoldAction() {
            super(RSyntaxTextAreaEditorKit.rstaToggleCurrentFoldAction);
            this.setProperties(msg, "Action.ToggleCurrentFold");
        }

        public ToggleCurrentFoldAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
            if (rsta.isCodeFoldingEnabled()) {
                Fold fold = this.getClosestFold(rsta);
                if (fold != null) {
                    fold.toggleCollapsedState();
                }
                RSyntaxUtilities.possiblyRepaintGutter(textArea);
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(rsta);
            }
        }

        @Override
        public final String getMacroID() {
            return RSyntaxTextAreaEditorKit.rstaToggleCurrentFoldAction;
        }
    }

    public static class ToggleCommentAction
    extends RecordableTextAction {
        public ToggleCommentAction() {
            super(RSyntaxTextAreaEditorKit.rstaToggleCommentAction);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            Element map = doc.getDefaultRootElement();
            Caret c = textArea.getCaret();
            int dot = c.getDot();
            int mark = c.getMark();
            int line1 = map.getElementIndex(dot);
            int line2 = map.getElementIndex(mark);
            int start = Math.min(line1, line2);
            int end = Math.max(line1, line2);
            Token t = doc.getTokenListForLine(start);
            int languageIndex = t != null ? t.getLanguageIndex() : 0;
            String[] startEnd = doc.getLineCommentStartAndEnd(languageIndex);
            if (startEnd == null) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            if (start != end) {
                Element elem = map.getElement(end);
                if (Math.max(dot, mark) == elem.getStartOffset()) {
                    --end;
                }
            }
            textArea.beginAtomicEdit();
            try {
                boolean add = this.getDoAdd(doc, map, start, end, startEnd);
                for (line1 = start; line1 <= end; ++line1) {
                    Element elem = map.getElement(line1);
                    this.handleToggleComment(elem, doc, startEnd, add);
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            } finally {
                textArea.endAtomicEdit();
            }
        }

        private boolean getDoAdd(Document doc, Element map, int startLine, int endLine, String[] startEnd) throws BadLocationException {
            boolean doAdd = false;
            for (int i = startLine; i <= endLine; ++i) {
                Element elem = map.getElement(i);
                int start = elem.getStartOffset();
                String t = doc.getText(start, elem.getEndOffset() - start - 1);
                if (t.startsWith(startEnd[0]) && (startEnd[1] == null || t.endsWith(startEnd[1]))) continue;
                doAdd = true;
                break;
            }
            return doAdd;
        }

        private void handleToggleComment(Element elem, Document doc, String[] startEnd, boolean add) throws BadLocationException {
            int start = elem.getStartOffset();
            int end = elem.getEndOffset() - 1;
            if (add) {
                if (startEnd[1] != null) {
                    doc.insertString(end, startEnd[1], null);
                }
                doc.insertString(start, startEnd[0], null);
            } else {
                if (startEnd[1] != null) {
                    int temp = startEnd[1].length();
                    doc.remove(end - temp, temp);
                }
                doc.remove(start, startEnd[0].length());
            }
        }

        @Override
        public final String getMacroID() {
            return RSyntaxTextAreaEditorKit.rstaToggleCommentAction;
        }
    }

    public static class SelectWordAction
    extends RTextAreaEditorKit.SelectWordAction {
        @Override
        protected void createActions() {
            this.start = new BeginWordAction("pigdog", false);
            this.end = new EndWordAction("pigdog", true);
        }
    }

    public static class PreviousWordAction
    extends RTextAreaEditorKit.PreviousWordAction {
        private Segment seg = new Segment();

        public PreviousWordAction(String nm, boolean select) {
            super(nm, select);
        }

        @Override
        protected int getPreviousWord(RTextArea textArea, int offs) throws BadLocationException {
            int line;
            if (offs == 0) {
                return offs;
            }
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            Element root = doc.getDefaultRootElement();
            int start = root.getElement(line = root.getElementIndex(offs)).getStartOffset();
            if (offs == start) {
                RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
                if (rsta.isCodeFoldingEnabled()) {
                    FoldManager fm = rsta.getFoldManager();
                    while (--line >= 0 && fm.isLineHidden(line)) {
                    }
                    if (line >= 0) {
                        offs = root.getElement(line).getEndOffset() - 1;
                    }
                    return offs;
                }
                return start - 1;
            }
            doc.getText(start, offs - start, this.seg);
            char ch = this.seg.last();
            while (Character.isWhitespace(ch)) {
                ch = this.seg.previous();
            }
            int languageIndex = 0;
            if (doc.isIdentifierChar(languageIndex, ch)) {
                while (doc.isIdentifierChar(languageIndex, ch = this.seg.previous()) && ch != '\uffff') {
                }
            } else if (!Character.isWhitespace(ch)) {
                while ((ch = this.seg.previous()) != '\uffff' && !doc.isIdentifierChar(languageIndex, ch) && !Character.isWhitespace(ch)) {
                }
            }
            offs -= this.seg.getEndIndex() - this.seg.getIndex();
            if (ch != '\uffff') {
                ++offs;
            }
            return offs;
        }
    }

    public static class PossiblyInsertTemplateAction
    extends RecordableTextAction {
        private static final long serialVersionUID = 1L;

        public PossiblyInsertTemplateAction() {
            super(RSyntaxTextAreaEditorKit.rstaPossiblyInsertTemplateAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            block7: {
                if (!textArea.isEditable() || !textArea.isEnabled()) {
                    return;
                }
                RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
                if (RSyntaxTextArea.getTemplatesEnabled()) {
                    Document doc = textArea.getDocument();
                    if (doc != null) {
                        try {
                            CodeTemplate template;
                            CodeTemplateManager manager = RSyntaxTextArea.getCodeTemplateManager();
                            CodeTemplate codeTemplate = template = manager == null ? null : manager.getTemplate(rsta);
                            if (template != null) {
                                template.invoke(rsta);
                                break block7;
                            }
                            this.doDefaultInsert(rsta);
                        } catch (BadLocationException ble) {
                            UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                        }
                    }
                } else {
                    this.doDefaultInsert(rsta);
                }
            }
        }

        private void doDefaultInsert(RTextArea textArea) {
            textArea.replaceSelection(" ");
        }

        @Override
        public final String getMacroID() {
            return RSyntaxTextAreaEditorKit.rstaPossiblyInsertTemplateAction;
        }
    }

    public static class NextWordAction
    extends RTextAreaEditorKit.NextWordAction {
        private Segment seg = new Segment();

        public NextWordAction(String nm, boolean select) {
            super(nm, select);
        }

        @Override
        protected int getNextWord(RTextArea textArea, int offs) throws BadLocationException {
            int line;
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            if (offs == doc.getLength()) {
                return offs;
            }
            Element root = doc.getDefaultRootElement();
            int end = root.getElement(line = root.getElementIndex(offs)).getEndOffset() - 1;
            if (offs == end) {
                RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
                if (rsta.isCodeFoldingEnabled()) {
                    FoldManager fm = rsta.getFoldManager();
                    int lineCount = root.getElementCount();
                    while (++line < lineCount && fm.isLineHidden(line)) {
                    }
                    if (line < lineCount) {
                        offs = root.getElement(line).getStartOffset();
                    }
                    return offs;
                }
                return offs + 1;
            }
            doc.getText(offs, end - offs, this.seg);
            char ch = this.seg.first();
            int languageIndex = 0;
            if (doc.isIdentifierChar(languageIndex, ch)) {
                while (doc.isIdentifierChar(languageIndex, ch = this.seg.next()) && ch != '\uffff') {
                }
            } else if (!Character.isWhitespace(ch)) {
                while ((ch = this.seg.next()) != '\uffff' && !doc.isIdentifierChar(languageIndex, ch) && !Character.isWhitespace(ch)) {
                }
            }
            while (Character.isWhitespace(ch)) {
                ch = this.seg.next();
            }
            return offs += this.seg.getIndex() - this.seg.getBeginIndex();
        }
    }

    public static class InsertTabAction
    extends RecordableTextAction {
        private static final long serialVersionUID = 1L;

        public InsertTabAction() {
            super("insert-tab");
        }

        public InsertTabAction(String name) {
            super(name);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            int mark;
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            Document document = textArea.getDocument();
            Element map = document.getDefaultRootElement();
            Caret c = textArea.getCaret();
            int dot = c.getDot();
            if (dot != (mark = c.getMark())) {
                int dotLine = map.getElementIndex(dot);
                int markLine = map.getElementIndex(mark);
                int first = Math.min(dotLine, markLine);
                int last = Math.max(dotLine, markLine);
                String replacement = "\t";
                if (textArea.getTabsEmulated()) {
                    StringBuilder sb = new StringBuilder();
                    int temp = textArea.getTabSize();
                    for (int i = 0; i < temp; ++i) {
                        sb.append(' ');
                    }
                    replacement = sb.toString();
                }
                textArea.beginAtomicEdit();
                try {
                    int start;
                    Element elem;
                    for (int i = first; i < last; ++i) {
                        elem = map.getElement(i);
                        start = elem.getStartOffset();
                        document.insertString(start, replacement, null);
                    }
                    elem = map.getElement(last);
                    start = elem.getStartOffset();
                    if (Math.max(c.getDot(), c.getMark()) == start) return;
                    document.insertString(start, replacement, null);
                    return;
                } catch (BadLocationException ble) {
                    ble.printStackTrace();
                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                    return;
                } finally {
                    textArea.endAtomicEdit();
                }
            } else {
                textArea.replaceSelection("\t");
            }
        }

        @Override
        public final String getMacroID() {
            return "insert-tab";
        }
    }

    public static class InsertBreakAction
    extends RTextAreaEditorKit.InsertBreakAction {
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            RSyntaxTextArea sta = (RSyntaxTextArea)textArea;
            boolean noSelection = sta.getSelectionStart() == sta.getSelectionEnd();
            boolean handled = false;
            if (noSelection) {
                RSyntaxDocument doc = (RSyntaxDocument)sta.getDocument();
                handled = doc.insertBreakSpecialHandling(e);
            }
            if (!handled) {
                this.handleInsertBreak(sta, noSelection);
            }
        }

        private static int atEndOfLine(int pos, String s, int sLen) {
            for (int i = pos; i < sLen; ++i) {
                if (RSyntaxUtilities.isWhitespace(s.charAt(i))) continue;
                return i;
            }
            return -1;
        }

        private static int getOpenBraceCount(RSyntaxDocument doc, int languageIndex) {
            int openCount = 0;
            for (Token t : doc) {
                if (t.getType() != 22 || t.length() != 1 || t.getLanguageIndex() != languageIndex) continue;
                char ch = t.charAt(0);
                if (ch == '{') {
                    ++openCount;
                    continue;
                }
                if (ch != '}') continue;
                --openCount;
            }
            return openCount;
        }

        protected void handleInsertBreak(RSyntaxTextArea textArea, boolean noSelection) {
            if (noSelection && textArea.isAutoIndentEnabled()) {
                this.insertNewlineWithAutoIndent(textArea);
            } else {
                textArea.replaceSelection("\n");
                if (noSelection) {
                    this.possiblyCloseCurlyBrace(textArea, null);
                }
            }
        }

        private void insertNewlineWithAutoIndent(RSyntaxTextArea sta) {
            try {
                int caretPos = sta.getCaretPosition();
                Document doc = sta.getDocument();
                Element map = doc.getDefaultRootElement();
                int lineNum = map.getElementIndex(caretPos);
                Element line = map.getElement(lineNum);
                int start = line.getStartOffset();
                int end = line.getEndOffset() - 1;
                int len = end - start;
                String s = doc.getText(start, len);
                String leadingWS = RSyntaxUtilities.getLeadingWhitespace(s);
                StringBuilder sb = new StringBuilder("\n");
                sb.append(leadingWS);
                int nonWhitespacePos = InsertBreakAction.atEndOfLine(caretPos - start, s, len);
                if (nonWhitespacePos == -1) {
                    if (leadingWS.length() == len && sta.isClearWhitespaceLinesEnabled()) {
                        sta.setSelectionStart(start);
                        sta.setSelectionEnd(end);
                    }
                    sta.replaceSelection(sb.toString());
                } else {
                    sb.append(s.substring(nonWhitespacePos));
                    sta.replaceRange(sb.toString(), caretPos, end);
                    sta.setCaretPosition(caretPos + leadingWS.length() + 1);
                }
                if (sta.getShouldIndentNextLine(lineNum)) {
                    sta.replaceSelection("\t");
                }
                this.possiblyCloseCurlyBrace(sta, leadingWS);
            } catch (BadLocationException ble) {
                sta.replaceSelection("\n");
                ble.printStackTrace();
            }
        }

        private void possiblyCloseCurlyBrace(RSyntaxTextArea textArea, String leadingWS) {
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            if (textArea.getCloseCurlyBraces()) {
                int languageIndex;
                int line = textArea.getCaretLineNumber();
                Token t = doc.getTokenListForLine(line - 1);
                if ((t = t.getLastNonCommentNonWhitespaceToken()) != null && t.isLeftCurly() && doc.getCurlyBracesDenoteCodeBlocks(languageIndex = t.getLanguageIndex()) && InsertBreakAction.getOpenBraceCount(doc, languageIndex) > 0) {
                    StringBuilder sb = new StringBuilder();
                    if (line == textArea.getLineCount() - 1) {
                        sb.append('\n');
                    }
                    if (leadingWS != null) {
                        sb.append(leadingWS);
                    }
                    sb.append("}\n");
                    int dot = textArea.getCaretPosition();
                    int end = textArea.getLineEndOffsetOfCurrentLine();
                    textArea.insert(sb.toString(), end);
                    textArea.setCaretPosition(dot);
                }
            }
        }
    }

    public static class IncreaseFontSizeAction
    extends RTextAreaEditorKit.IncreaseFontSizeAction {
        private static final long serialVersionUID = 1L;

        public IncreaseFontSizeAction() {
        }

        public IncreaseFontSizeAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
            SyntaxScheme scheme = rsta.getSyntaxScheme();
            boolean changed = false;
            int count = scheme.getStyleCount();
            for (int i = 0; i < count; ++i) {
                Font font;
                Style ss = scheme.getStyle(i);
                if (ss == null || (font = ss.font) == null) continue;
                float oldSize = font.getSize2D();
                float newSize = oldSize + this.increaseAmount;
                if (newSize <= 40.0f) {
                    ss.font = font.deriveFont(newSize);
                    changed = true;
                    continue;
                }
                if (!(oldSize < 40.0f)) continue;
                ss.font = font.deriveFont(40.0f);
                changed = true;
            }
            Font font = rsta.getFont();
            float oldSize = font.getSize2D();
            float newSize = oldSize + this.increaseAmount;
            if (newSize <= 40.0f) {
                rsta.setFont(font.deriveFont(newSize));
                changed = true;
            } else if (oldSize < 40.0f) {
                rsta.setFont(font.deriveFont(40.0f));
                changed = true;
            }
            if (changed) {
                rsta.setSyntaxScheme(scheme);
                Container parent = rsta.getParent();
                if (parent instanceof JViewport && (parent = parent.getParent()) instanceof JScrollPane) {
                    parent.repaint();
                }
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(rsta);
            }
        }
    }

    public static class GoToMatchingBracketAction
    extends RecordableTextAction {
        private static final long serialVersionUID = 1L;
        private Point bracketInfo;

        public GoToMatchingBracketAction() {
            super(RSyntaxTextAreaEditorKit.rstaGoToMatchingBracketAction);
        }

        public GoToMatchingBracketAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
            this.bracketInfo = RSyntaxUtilities.getMatchingBracketPosition(rsta, this.bracketInfo);
            if (this.bracketInfo.y > -1) {
                rsta.setCaretPosition(this.bracketInfo.y + 1);
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(rsta);
            }
        }

        @Override
        public final String getMacroID() {
            return RSyntaxTextAreaEditorKit.rstaGoToMatchingBracketAction;
        }

        public static class EndAction
        extends RTextAreaEditorKit.EndAction {
            public EndAction(String name, boolean select) {
                super(name, select);
            }

            @Override
            protected int getVisibleEnd(RTextArea textArea) {
                RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
                return rsta.getLastVisibleOffset();
            }
        }
    }

    static abstract class FoldRelatedAction
    extends RecordableTextAction {
        FoldRelatedAction(String name) {
            super(name);
        }

        FoldRelatedAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        protected Fold getClosestFold(RSyntaxTextArea textArea) {
            int offs = textArea.getCaretPosition();
            int line = textArea.getCaretLineNumber();
            FoldManager fm = textArea.getFoldManager();
            Fold fold = fm.getFoldForLine(line);
            if (fold == null) {
                fold = fm.getDeepestOpenFoldContaining(offs);
            }
            return fold;
        }
    }

    public static class ExpandAllFoldsAction
    extends FoldRelatedAction {
        private static final long serialVersionUID = 1L;

        public ExpandAllFoldsAction() {
            this(false);
        }

        public ExpandAllFoldsAction(boolean localizedName) {
            super(RSyntaxTextAreaEditorKit.rstaExpandAllFoldsAction);
            if (localizedName) {
                this.setProperties(msg, "Action.ExpandAllFolds");
            }
        }

        public ExpandAllFoldsAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
            if (rsta.isCodeFoldingEnabled()) {
                FoldManager fm = rsta.getFoldManager();
                for (int i = 0; i < fm.getFoldCount(); ++i) {
                    this.expand(fm.getFold(i));
                }
                RSyntaxUtilities.possiblyRepaintGutter(rsta);
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(rsta);
            }
        }

        private void expand(Fold fold) {
            fold.setCollapsed(false);
            for (int i = 0; i < fold.getChildCount(); ++i) {
                this.expand(fold.getChild(i));
            }
        }

        @Override
        public final String getMacroID() {
            return RSyntaxTextAreaEditorKit.rstaExpandAllFoldsAction;
        }
    }

    protected static class EndWordAction
    extends RTextAreaEditorKit.EndWordAction {
        private Segment seg = new Segment();

        protected EndWordAction(String name, boolean select) {
            super(name, select);
        }

        @Override
        protected int getWordEnd(RTextArea textArea, int offs) throws BadLocationException {
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            if (offs == doc.getLength()) {
                return offs;
            }
            int line = textArea.getLineOfOffset(offs);
            int end = textArea.getLineEndOffset(line);
            if (line != textArea.getLineCount() - 1) {
                --end;
            }
            if (offs == end) {
                return end;
            }
            doc.getText(offs, end - offs, this.seg);
            char ch = this.seg.first();
            int languageIndex = 0;
            if (doc.isIdentifierChar(languageIndex, ch)) {
                while (doc.isIdentifierChar(languageIndex, ch = this.seg.next()) && ch != '\uffff') {
                }
            } else if (Character.isWhitespace(ch)) {
                while (Character.isWhitespace(ch = this.seg.next())) {
                }
            }
            return offs += this.seg.getIndex() - this.seg.getBeginIndex();
        }
    }

    public static class DumbCompleteWordAction
    extends RTextAreaEditorKit.DumbCompleteWordAction {
        @Override
        protected int getPreviousWord(RTextArea textArea, int offs) throws BadLocationException {
            int line;
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            Element root = doc.getDefaultRootElement();
            Element elem = root.getElement(line = root.getElementIndex(offs));
            int start = elem.getStartOffset();
            if (offs > start) {
                char ch = doc.charAt(offs);
                if (DumbCompleteWordAction.isIdentifierChar(ch)) {
                    --offs;
                }
            } else {
                if (line == 0) {
                    return -1;
                }
                elem = root.getElement(--line);
                offs = elem.getEndOffset() - 1;
            }
            int prevWordStart = this.getPreviousWordStartInLine(doc, elem, offs);
            while (prevWordStart == -1 && line > 0) {
                elem = root.getElement(--line);
                prevWordStart = this.getPreviousWordStartInLine(doc, elem, elem.getEndOffset());
            }
            return prevWordStart;
        }

        private int getPreviousWordStartInLine(RSyntaxDocument doc, Element elem, int offs) throws BadLocationException {
            char ch;
            int cur;
            int start = elem.getStartOffset();
            for (cur = offs; cur >= start && !DumbCompleteWordAction.isIdentifierChar(ch = doc.charAt(cur)); --cur) {
            }
            if (cur < start) {
                return -1;
            }
            return DumbCompleteWordAction.getWordStartImpl(doc, elem, cur);
        }

        @Override
        protected int getWordEnd(RTextArea textArea, int offs) throws BadLocationException {
            int wordEnd;
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            Element root = doc.getDefaultRootElement();
            int line = root.getElementIndex(offs);
            Element elem = root.getElement(line);
            int end = elem.getEndOffset() - 1;
            for (wordEnd = offs; wordEnd <= end && DumbCompleteWordAction.isIdentifierChar(doc.charAt(wordEnd)); ++wordEnd) {
            }
            return wordEnd;
        }

        @Override
        protected int getWordStart(RTextArea textArea, int offs) throws BadLocationException {
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            Element root = doc.getDefaultRootElement();
            int line = root.getElementIndex(offs);
            Element elem = root.getElement(line);
            return DumbCompleteWordAction.getWordStartImpl(doc, elem, offs);
        }

        private static int getWordStartImpl(RSyntaxDocument doc, Element elem, int offs) throws BadLocationException {
            char ch;
            int wordStart;
            int start = elem.getStartOffset();
            for (wordStart = offs; wordStart >= start && (DumbCompleteWordAction.isIdentifierChar(ch = doc.charAt(wordStart)) || ch == '\n'); --wordStart) {
            }
            return wordStart == offs ? offs : wordStart + 1;
        }

        @Override
        protected boolean isAcceptablePrefix(String prefix) {
            return prefix.length() > 0 && DumbCompleteWordAction.isIdentifierChar(prefix.charAt(prefix.length() - 1));
        }

        private static boolean isIdentifierChar(char ch) {
            return Character.isLetterOrDigit(ch) || ch == '_' || ch == '$';
        }
    }

    public static class DeletePrevWordAction
    extends RTextAreaEditorKit.DeletePrevWordAction {
        private Segment seg = new Segment();

        @Override
        protected int getPreviousWordStart(RTextArea textArea, int offs) throws BadLocationException {
            int languageIndex;
            if (offs == 0) {
                return offs;
            }
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            int line = textArea.getLineOfOffset(offs);
            int start = textArea.getLineStartOffset(line);
            if (offs == start) {
                return start - 1;
            }
            int end = textArea.getLineEndOffset(line);
            if (line != textArea.getLineCount() - 1) {
                --end;
            }
            doc.getText(start, end - start, this.seg);
            int firstIndex = this.seg.getBeginIndex() + (offs - start) - 1;
            this.seg.setIndex(firstIndex);
            char ch = this.seg.current();
            if (Character.isWhitespace(ch)) {
                while (Character.isWhitespace(ch = this.seg.previous())) {
                }
            }
            if (doc.isIdentifierChar(languageIndex = 0, ch)) {
                while (doc.isIdentifierChar(languageIndex, ch = this.seg.previous())) {
                }
            } else {
                while (!Character.isWhitespace(ch) && !doc.isIdentifierChar(languageIndex, ch) && ch != '\uffff') {
                    ch = this.seg.previous();
                }
            }
            if (ch == '\uffff') {
                return start;
            }
            return offs -= firstIndex - this.seg.getIndex();
        }
    }

    public static class DecreaseIndentAction
    extends RecordableTextAction {
        private static final long serialVersionUID = 1L;
        private Segment s = new Segment();

        public DecreaseIndentAction() {
            this(RSyntaxTextAreaEditorKit.rstaDecreaseIndentAction);
        }

        public DecreaseIndentAction(String name) {
            super(name);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            Document document = textArea.getDocument();
            Element map = document.getDefaultRootElement();
            Caret c = textArea.getCaret();
            int dot = c.getDot();
            int mark = c.getMark();
            int line1 = map.getElementIndex(dot);
            int tabSize = textArea.getTabSize();
            if (dot != mark) {
                int line2 = map.getElementIndex(mark);
                dot = Math.min(line1, line2);
                mark = Math.max(line1, line2);
                textArea.beginAtomicEdit();
                try {
                    Element elem;
                    for (line1 = dot; line1 < mark; ++line1) {
                        elem = map.getElement(line1);
                        this.handleDecreaseIndent(elem, document, tabSize);
                    }
                    elem = map.getElement(mark);
                    int start = elem.getStartOffset();
                    if (Math.max(c.getDot(), c.getMark()) == start) return;
                    this.handleDecreaseIndent(elem, document, tabSize);
                    return;
                } catch (BadLocationException ble) {
                    ble.printStackTrace();
                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                    return;
                } finally {
                    textArea.endAtomicEdit();
                }
            }
            Element elem = map.getElement(line1);
            try {
                this.handleDecreaseIndent(elem, document, tabSize);
                return;
            } catch (BadLocationException ble) {
                ble.printStackTrace();
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            }
        }

        @Override
        public final String getMacroID() {
            return RSyntaxTextAreaEditorKit.rstaDecreaseIndentAction;
        }

        private void handleDecreaseIndent(Element elem, Document doc, int tabSize) throws BadLocationException {
            int start = elem.getStartOffset();
            int end = elem.getEndOffset() - 1;
            doc.getText(start, end - start, this.s);
            int i = this.s.offset;
            end = i + this.s.count;
            if (end > i) {
                if (this.s.array[i] == '\t') {
                    doc.remove(start, 1);
                } else if (this.s.array[i] == ' ') {
                    int toRemove;
                    ++i;
                    for (toRemove = 1; i < end && this.s.array[i] == ' ' && toRemove < tabSize; ++i, ++toRemove) {
                    }
                    doc.remove(start, toRemove);
                }
            }
        }
    }

    public static class DecreaseFontSizeAction
    extends RTextAreaEditorKit.DecreaseFontSizeAction {
        private static final long serialVersionUID = 1L;

        public DecreaseFontSizeAction() {
        }

        public DecreaseFontSizeAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
            SyntaxScheme scheme = rsta.getSyntaxScheme();
            boolean changed = false;
            int count = scheme.getStyleCount();
            for (int i = 0; i < count; ++i) {
                Font font;
                Style ss = scheme.getStyle(i);
                if (ss == null || (font = ss.font) == null) continue;
                float oldSize = font.getSize2D();
                float newSize = oldSize - this.decreaseAmount;
                if (newSize >= 2.0f) {
                    ss.font = font.deriveFont(newSize);
                    changed = true;
                    continue;
                }
                if (!(oldSize > 2.0f)) continue;
                ss.font = font.deriveFont(2.0f);
                changed = true;
            }
            Font font = rsta.getFont();
            float oldSize = font.getSize2D();
            float newSize = oldSize - this.decreaseAmount;
            if (newSize >= 2.0f) {
                rsta.setFont(font.deriveFont(newSize));
                changed = true;
            } else if (oldSize > 2.0f) {
                rsta.setFont(font.deriveFont(2.0f));
                changed = true;
            }
            if (changed) {
                rsta.setSyntaxScheme(scheme);
                Container parent = rsta.getParent();
                if (parent instanceof JViewport && (parent = parent.getParent()) instanceof JScrollPane) {
                    parent.repaint();
                }
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(rsta);
            }
        }
    }

    public static class CopyAsStyledTextAction
    extends RecordableTextAction {
        private Theme theme;
        private static final long serialVersionUID = 1L;

        public CopyAsStyledTextAction() {
            super(RSyntaxTextAreaEditorKit.rstaCopyAsStyledTextAction);
        }

        public CopyAsStyledTextAction(String themeName, Theme theme) {
            super("RSTA.CopyAsStyledTextAction_" + themeName);
            this.theme = theme;
        }

        public CopyAsStyledTextAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            ((RSyntaxTextArea)textArea).copyAsStyledText(this.theme);
            textArea.requestFocusInWindow();
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    public static class CollapseAllFoldsAction
    extends FoldRelatedAction {
        private static final long serialVersionUID = 1L;

        public CollapseAllFoldsAction() {
            this(false);
        }

        public CollapseAllFoldsAction(boolean localizedName) {
            super(RSyntaxTextAreaEditorKit.rstaCollapseAllFoldsAction);
            if (localizedName) {
                this.setProperties(msg, "Action.CollapseAllFolds");
            }
        }

        public CollapseAllFoldsAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
            if (rsta.isCodeFoldingEnabled()) {
                FoldCollapser collapser = new FoldCollapser(){

                    @Override
                    public boolean getShouldCollapse(Fold fold) {
                        return true;
                    }
                };
                collapser.collapseFolds(rsta.getFoldManager());
                RSyntaxUtilities.possiblyRepaintGutter(textArea);
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(rsta);
            }
        }

        @Override
        public final String getMacroID() {
            return RSyntaxTextAreaEditorKit.rstaCollapseAllFoldsAction;
        }
    }

    public static class CollapseAllCommentFoldsAction
    extends FoldRelatedAction {
        private static final long serialVersionUID = 1L;

        public CollapseAllCommentFoldsAction() {
            super(RSyntaxTextAreaEditorKit.rstaCollapseAllCommentFoldsAction);
            this.setProperties(msg, "Action.CollapseCommentFolds");
        }

        public CollapseAllCommentFoldsAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
            if (rsta.isCodeFoldingEnabled()) {
                FoldCollapser collapser = new FoldCollapser();
                collapser.collapseFolds(rsta.getFoldManager());
                RSyntaxUtilities.possiblyRepaintGutter(textArea);
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(rsta);
            }
        }

        @Override
        public final String getMacroID() {
            return RSyntaxTextAreaEditorKit.rstaCollapseAllCommentFoldsAction;
        }
    }

    public static class CloseMarkupTagAction
    extends RecordableTextAction {
        private static final long serialVersionUID = 1L;

        public CloseMarkupTagAction() {
            super(RSyntaxTextAreaEditorKit.rstaCloseMarkupTagAction);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
            RSyntaxDocument doc = (RSyntaxDocument)rsta.getDocument();
            Caret c = rsta.getCaret();
            boolean selection = c.getDot() != c.getMark();
            rsta.replaceSelection("/");
            int dot = c.getDot();
            if (doc.getLanguageIsMarkup() && doc.getCompleteMarkupCloseTags() && !selection && rsta.getCloseMarkupTags() && dot > 1) {
                try {
                    char ch = doc.charAt(dot - 2);
                    if (ch == '<' || ch == '[') {
                        String tagName;
                        Token t = doc.getTokenListForLine(rsta.getCaretLineNumber());
                        if ((t = RSyntaxUtilities.getTokenAtOffset(t, dot - 1)) != null && t.getType() == 25 && (tagName = this.discoverTagName(doc, dot)) != null) {
                            rsta.replaceSelection(tagName + (char)(ch + 2));
                        }
                    }
                } catch (BadLocationException ble) {
                    UIManager.getLookAndFeel().provideErrorFeedback(rsta);
                    ble.printStackTrace();
                }
            }
        }

        private String discoverTagName(RSyntaxDocument doc, int dot) {
            Stack<String> stack = new Stack<String>();
            Element root = doc.getDefaultRootElement();
            int curLine = root.getElementIndex(dot);
            for (int i = 0; i <= curLine; ++i) {
                Token t = doc.getTokenListForLine(i);
                while (t != null && t.isPaintable()) {
                    if (t.getType() == 25) {
                        if (t.isSingleChar('<') || t.isSingleChar('[')) {
                            for (t = t.getNextToken(); t != null && t.isPaintable(); t = t.getNextToken()) {
                                if (t.getType() != 26 && t.getType() != 27) continue;
                                stack.push(t.getLexeme());
                                break;
                            }
                        } else if (t.length() == 2 && t.charAt(0) == '/' && (t.charAt(1) == '>' || t.charAt(1) == ']')) {
                            if (!stack.isEmpty()) {
                                stack.pop();
                            }
                        } else if (t.length() == 2 && (t.charAt(0) == '<' || t.charAt(0) == '[') && t.charAt(1) == '/') {
                            String tagName = null;
                            if (!stack.isEmpty()) {
                                tagName = (String)stack.pop();
                            }
                            if (t.getEndOffset() >= dot) {
                                return tagName;
                            }
                        }
                    }
                    t = t == null ? null : t.getNextToken();
                }
            }
            return null;
        }

        @Override
        public String getMacroID() {
            return this.getName();
        }
    }

    public static class CloseCurlyBraceAction
    extends RecordableTextAction {
        private static final long serialVersionUID = 1L;
        private Point bracketInfo;
        private Segment seg = new Segment();

        public CloseCurlyBraceAction() {
            super(RSyntaxTextAreaEditorKit.rstaCloseCurlyBraceAction);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            block15: {
                boolean alignCurlyBraces;
                RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
                RSyntaxDocument doc = (RSyntaxDocument)rsta.getDocument();
                int languageIndex = 0;
                int dot = textArea.getCaretPosition();
                if (dot > 0) {
                    Token t = RSyntaxUtilities.getTokenAtOffset(rsta, dot - 1);
                    languageIndex = t == null ? 0 : t.getLanguageIndex();
                }
                boolean bl = alignCurlyBraces = rsta.isAutoIndentEnabled() && doc.getCurlyBracesDenoteCodeBlocks(languageIndex);
                if (alignCurlyBraces) {
                    textArea.beginAtomicEdit();
                }
                try {
                    textArea.replaceSelection("}");
                    if (!alignCurlyBraces) break block15;
                    Element root = doc.getDefaultRootElement();
                    dot = rsta.getCaretPosition() - 1;
                    int line = root.getElementIndex(dot);
                    Element elem = root.getElement(line);
                    int start = elem.getStartOffset();
                    try {
                        doc.getText(start, dot - start, this.seg);
                    } catch (BadLocationException ble) {
                        ble.printStackTrace();
                        if (alignCurlyBraces) {
                            textArea.endAtomicEdit();
                        }
                        return;
                    }
                    for (int i = 0; i < this.seg.count; ++i) {
                        char ch = this.seg.array[this.seg.offset + i];
                        if (Character.isWhitespace(ch)) continue;
                        return;
                    }
                    this.bracketInfo = RSyntaxUtilities.getMatchingBracketPosition(rsta, this.bracketInfo);
                    if (this.bracketInfo.y <= -1) break block15;
                    try {
                        String ws = RSyntaxUtilities.getLeadingWhitespace(doc, this.bracketInfo.y);
                        rsta.replaceRange(ws, start, dot);
                    } catch (BadLocationException ble) {
                        ble.printStackTrace();
                        if (alignCurlyBraces) {
                            textArea.endAtomicEdit();
                        }
                        return;
                    }
                } finally {
                    if (alignCurlyBraces) {
                        textArea.endAtomicEdit();
                    }
                }
            }
        }

        @Override
        public final String getMacroID() {
            return RSyntaxTextAreaEditorKit.rstaCloseCurlyBraceAction;
        }
    }

    public static class ChangeFoldStateAction
    extends FoldRelatedAction {
        private boolean collapse;

        public ChangeFoldStateAction(String name, boolean collapse) {
            super(name);
            this.collapse = collapse;
        }

        public ChangeFoldStateAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
            if (rsta.isCodeFoldingEnabled()) {
                Fold fold = this.getClosestFold(rsta);
                if (fold != null) {
                    fold.setCollapsed(this.collapse);
                }
                RSyntaxUtilities.possiblyRepaintGutter(textArea);
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(rsta);
            }
        }

        @Override
        public final String getMacroID() {
            return this.getName();
        }
    }

    protected static class BeginWordAction
    extends RTextAreaEditorKit.BeginWordAction {
        private Segment seg = new Segment();

        protected BeginWordAction(String name, boolean select) {
            super(name, select);
        }

        @Override
        protected int getWordStart(RTextArea textArea, int offs) throws BadLocationException {
            if (offs == 0) {
                return offs;
            }
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            int line = textArea.getLineOfOffset(offs);
            int start = textArea.getLineStartOffset(line);
            if (offs == start) {
                return start;
            }
            int end = textArea.getLineEndOffset(line);
            if (line != textArea.getLineCount() - 1) {
                --end;
            }
            doc.getText(start, end - start, this.seg);
            int firstIndex = this.seg.getBeginIndex() + (offs - start) - 1;
            this.seg.setIndex(firstIndex);
            char ch = this.seg.current();
            char nextCh = offs == end ? (char)'\u0000' : this.seg.array[this.seg.getIndex() + 1];
            int languageIndex = 0;
            if (doc.isIdentifierChar(languageIndex, ch)) {
                if (offs != end && !doc.isIdentifierChar(languageIndex, nextCh)) {
                    return offs;
                }
                while (doc.isIdentifierChar(languageIndex, ch = this.seg.previous()) && ch != '\uffff') {
                }
            } else if (Character.isWhitespace(ch)) {
                if (offs != end && !Character.isWhitespace(nextCh)) {
                    return offs;
                }
                while (Character.isWhitespace(ch = this.seg.previous())) {
                }
            }
            offs -= firstIndex - this.seg.getIndex() + 1;
            if (ch != '\uffff' && nextCh != '\n') {
                ++offs;
            }
            return offs;
        }
    }
}

