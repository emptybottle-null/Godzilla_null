/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.plaf.TextUI;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Segment;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import org.fife.print.RPrintUtilities;
import org.fife.ui.rsyntaxtextarea.DocumentRange;
import org.fife.ui.rtextarea.CaretStyle;
import org.fife.ui.rtextarea.ConfigurableCaret;
import org.fife.ui.rtextarea.IconGroup;
import org.fife.ui.rtextarea.LineHighlightManager;
import org.fife.ui.rtextarea.Macro;
import org.fife.ui.rtextarea.RDocument;
import org.fife.ui.rtextarea.RTextAreaBase;
import org.fife.ui.rtextarea.RTextAreaEditorKit;
import org.fife.ui.rtextarea.RTextAreaHighlighter;
import org.fife.ui.rtextarea.RTextAreaUI;
import org.fife.ui.rtextarea.RUndoManager;
import org.fife.ui.rtextarea.RecordableTextAction;
import org.fife.ui.rtextarea.SmartHighlightPainter;
import org.fife.ui.rtextarea.ToolTipSupplier;

public class RTextArea
extends RTextAreaBase
implements Printable {
    public static final int INSERT_MODE = 0;
    public static final int OVERWRITE_MODE = 1;
    public static final String MARK_ALL_COLOR_PROPERTY = "RTA.markAllColor";
    public static final String MARK_ALL_ON_OCCURRENCE_SEARCHES_PROPERTY = "RTA.markAllOnOccurrenceSearches";
    public static final String MARK_ALL_OCCURRENCES_CHANGED_PROPERTY = "RTA.markAllOccurrencesChanged";
    private static final int MIN_ACTION_CONSTANT = 0;
    public static final int COPY_ACTION = 0;
    public static final int CUT_ACTION = 1;
    public static final int DELETE_ACTION = 2;
    public static final int PASTE_ACTION = 3;
    public static final int REDO_ACTION = 4;
    public static final int SELECT_ALL_ACTION = 5;
    public static final int UNDO_ACTION = 6;
    private static final int MAX_ACTION_CONSTANT = 6;
    private static final Color DEFAULT_MARK_ALL_COLOR = new Color(16762880);
    private int textMode;
    private static boolean recordingMacro;
    private static Macro currentMacro;
    private JPopupMenu popupMenu;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    private JMenuItem cutMenuItem;
    private JMenuItem pasteMenuItem;
    private JMenuItem deleteMenuItem;
    private boolean popupMenuCreated;
    private static String selectedOccurrenceText;
    private ToolTipSupplier toolTipSupplier;
    private static RecordableTextAction cutAction;
    private static RecordableTextAction copyAction;
    private static RecordableTextAction pasteAction;
    private static RecordableTextAction deleteAction;
    private static RecordableTextAction undoAction;
    private static RecordableTextAction redoAction;
    private static RecordableTextAction selectAllAction;
    private static IconGroup iconGroup;
    private transient RUndoManager undoManager;
    private transient LineHighlightManager lineHighlightManager;
    private SmartHighlightPainter markAllHighlightPainter;
    private boolean markAllOnOccurrenceSearches;
    private CaretStyle[] carets;
    private static final String MSG = "org.fife.ui.rtextarea.RTextArea";
    private static StringBuilder repTabsSB;
    private static Segment repTabsSeg;

    public RTextArea() {
    }

    public RTextArea(AbstractDocument doc) {
        super(doc);
    }

    public RTextArea(String text) {
        super(text);
    }

    public RTextArea(int rows, int cols) {
        super(rows, cols);
    }

    public RTextArea(String text, int rows, int cols) {
        super(text, rows, cols);
    }

    public RTextArea(AbstractDocument doc, String text, int rows, int cols) {
        super(doc, text, rows, cols);
    }

    public RTextArea(int textMode) {
        this.setTextMode(textMode);
    }

    static synchronized void addToCurrentMacro(String id, String actionCommand) {
        currentMacro.addMacroRecord(new Macro.MacroRecord(id, actionCommand));
    }

    public Object addLineHighlight(int line, Color color) throws BadLocationException {
        if (this.lineHighlightManager == null) {
            this.lineHighlightManager = new LineHighlightManager(this);
        }
        return this.lineHighlightManager.addLineHighlight(line, color);
    }

    public void beginAtomicEdit() {
        this.undoManager.beginInternalAtomicEdit();
    }

    public static synchronized void beginRecordingMacro() {
        if (RTextArea.isRecordingMacro()) {
            return;
        }
        if (currentMacro != null) {
            currentMacro = null;
        }
        currentMacro = new Macro();
        recordingMacro = true;
    }

    public boolean canUndo() {
        return this.undoManager.canUndo();
    }

    public boolean canRedo() {
        return this.undoManager.canRedo();
    }

    void clearMarkAllHighlights() {
        ((RTextAreaHighlighter)this.getHighlighter()).clearMarkAllHighlights();
        this.repaint();
    }

    protected void configurePopupMenu(JPopupMenu popupMenu) {
        boolean canType;
        boolean bl = canType = this.isEditable() && this.isEnabled();
        if (this.undoMenuItem != null) {
            this.undoMenuItem.setEnabled(undoAction.isEnabled() && canType);
            this.redoMenuItem.setEnabled(redoAction.isEnabled() && canType);
            this.cutMenuItem.setEnabled(cutAction.isEnabled() && canType);
            this.pasteMenuItem.setEnabled(pasteAction.isEnabled() && canType);
            this.deleteMenuItem.setEnabled(deleteAction.isEnabled() && canType);
        }
    }

    @Override
    protected Document createDefaultModel() {
        return new RDocument();
    }

    @Override
    protected RTextAreaBase.RTAMouseListener createMouseListener() {
        return new RTextAreaMutableCaretEvent(this);
    }

    protected JPopupMenu createPopupMenu() {
        JPopupMenu menu = new JPopupMenu();
        this.undoMenuItem = this.createPopupMenuItem(undoAction);
        menu.add(this.undoMenuItem);
        this.redoMenuItem = this.createPopupMenuItem(redoAction);
        menu.add(this.redoMenuItem);
        menu.addSeparator();
        this.cutMenuItem = this.createPopupMenuItem(cutAction);
        menu.add(this.cutMenuItem);
        menu.add(this.createPopupMenuItem(copyAction));
        this.pasteMenuItem = this.createPopupMenuItem(pasteAction);
        menu.add(this.pasteMenuItem);
        this.deleteMenuItem = this.createPopupMenuItem(deleteAction);
        menu.add(this.deleteMenuItem);
        menu.addSeparator();
        menu.add(this.createPopupMenuItem(selectAllAction));
        return menu;
    }

    private static void createPopupMenuActions() {
        int mod = RTextArea.getDefaultModifier();
        ResourceBundle msg = ResourceBundle.getBundle(MSG);
        cutAction = new RTextAreaEditorKit.CutAction();
        cutAction.setProperties(msg, "Action.Cut");
        cutAction.setAccelerator(KeyStroke.getKeyStroke(88, mod));
        copyAction = new RTextAreaEditorKit.CopyAction();
        copyAction.setProperties(msg, "Action.Copy");
        copyAction.setAccelerator(KeyStroke.getKeyStroke(67, mod));
        pasteAction = new RTextAreaEditorKit.PasteAction();
        pasteAction.setProperties(msg, "Action.Paste");
        pasteAction.setAccelerator(KeyStroke.getKeyStroke(86, mod));
        deleteAction = new RTextAreaEditorKit.DeleteNextCharAction();
        deleteAction.setProperties(msg, "Action.Delete");
        deleteAction.setAccelerator(KeyStroke.getKeyStroke(127, 0));
        undoAction = new RTextAreaEditorKit.UndoAction();
        undoAction.setProperties(msg, "Action.Undo");
        undoAction.setAccelerator(KeyStroke.getKeyStroke(90, mod));
        redoAction = new RTextAreaEditorKit.RedoAction();
        redoAction.setProperties(msg, "Action.Redo");
        redoAction.setAccelerator(KeyStroke.getKeyStroke(89, mod));
        selectAllAction = new RTextAreaEditorKit.SelectAllAction();
        selectAllAction.setProperties(msg, "Action.SelectAll");
        selectAllAction.setAccelerator(KeyStroke.getKeyStroke(65, mod));
    }

    protected JMenuItem createPopupMenuItem(Action a) {
        JMenuItem item = new JMenuItem(a){

            @Override
            public void setToolTipText(String text) {
            }
        };
        item.setAccelerator(null);
        return item;
    }

    @Override
    protected RTextAreaUI createRTextAreaUI() {
        return new RTextAreaUI(this);
    }

    private String createSpacer(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    protected RUndoManager createUndoManager() {
        return new RUndoManager(this);
    }

    public void discardAllEdits() {
        this.undoManager.discardAllEdits();
        this.getDocument().removeUndoableEditListener(this.undoManager);
        this.undoManager = this.createUndoManager();
        this.getDocument().addUndoableEditListener(this.undoManager);
        this.undoManager.updateActions();
    }

    public void endAtomicEdit() {
        this.undoManager.endInternalAtomicEdit();
    }

    public static synchronized void endRecordingMacro() {
        if (!RTextArea.isRecordingMacro()) {
            return;
        }
        recordingMacro = false;
    }

    @Override
    protected void fireCaretUpdate(CaretEvent e) {
        this.possiblyUpdateCurrentLineHighlightLocation();
        if (e != null && e.getDot() != e.getMark()) {
            cutAction.setEnabled(true);
            copyAction.setEnabled(true);
        } else if (cutAction.isEnabled()) {
            cutAction.setEnabled(false);
            copyAction.setEnabled(false);
        }
        super.fireCaretUpdate(e);
    }

    private void fixCtrlH() {
        InputMap inputMap = this.getInputMap();
        KeyStroke char010 = KeyStroke.getKeyStroke("typed \b");
        for (InputMap parent = inputMap; parent != null; parent = parent.getParent()) {
            parent.remove(char010);
        }
        if (inputMap != null) {
            KeyStroke backspace = KeyStroke.getKeyStroke("BACK_SPACE");
            inputMap.put(backspace, "delete-previous");
        }
    }

    public static RecordableTextAction getAction(int action) {
        if (action < 0 || action > 6) {
            return null;
        }
        switch (action) {
            case 0: {
                return copyAction;
            }
            case 1: {
                return cutAction;
            }
            case 2: {
                return deleteAction;
            }
            case 3: {
                return pasteAction;
            }
            case 4: {
                return redoAction;
            }
            case 5: {
                return selectAllAction;
            }
            case 6: {
                return undoAction;
            }
        }
        return null;
    }

    public static synchronized Macro getCurrentMacro() {
        return currentMacro;
    }

    public static Color getDefaultMarkAllHighlightColor() {
        return DEFAULT_MARK_ALL_COLOR;
    }

    public static int getDefaultModifier() {
        int modifier = RTextAreaBase.isOSX() ? 4 : 2;
        try {
            modifier = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        } catch (HeadlessException headlessException) {
            // empty catch block
        }
        return modifier;
    }

    public static IconGroup getIconGroup() {
        return iconGroup;
    }

    public boolean getMarkAllOnOccurrenceSearches() {
        return this.markAllOnOccurrenceSearches;
    }

    LineHighlightManager getLineHighlightManager() {
        return this.lineHighlightManager;
    }

    public Color getMarkAllHighlightColor() {
        return (Color)this.markAllHighlightPainter.getPaint();
    }

    public int getMaxAscent() {
        return this.getFontMetrics(this.getFont()).getAscent();
    }

    public JPopupMenu getPopupMenu() {
        if (!this.popupMenuCreated) {
            this.popupMenu = this.createPopupMenu();
            if (this.popupMenu != null) {
                ComponentOrientation orientation = ComponentOrientation.getOrientation(Locale.getDefault());
                this.popupMenu.applyComponentOrientation(orientation);
            }
            this.popupMenuCreated = true;
        }
        return this.popupMenu;
    }

    public static String getSelectedOccurrenceText() {
        return selectedOccurrenceText;
    }

    public final int getTextMode() {
        return this.textMode;
    }

    public ToolTipSupplier getToolTipSupplier() {
        return this.toolTipSupplier;
    }

    @Override
    public String getToolTipText(MouseEvent e) {
        String tip = null;
        if (this.getToolTipSupplier() != null) {
            tip = this.getToolTipSupplier().getToolTipText(this, e);
        }
        return tip != null ? tip : super.getToolTipText();
    }

    protected void handleReplaceSelection(String content) {
        super.replaceSelection(content);
    }

    @Override
    protected void init() {
        super.init();
        if (cutAction == null) {
            RTextArea.createPopupMenuActions();
        }
        this.undoManager = this.createUndoManager();
        this.getDocument().addUndoableEditListener(this.undoManager);
        Color markAllHighlightColor = RTextArea.getDefaultMarkAllHighlightColor();
        this.markAllHighlightPainter = new SmartHighlightPainter(markAllHighlightColor);
        this.setMarkAllHighlightColor(markAllHighlightColor);
        this.carets = new CaretStyle[2];
        this.setCaretStyle(0, CaretStyle.THICK_VERTICAL_LINE_STYLE);
        this.setCaretStyle(1, CaretStyle.BLOCK_STYLE);
        this.setDragEnabled(true);
        this.setTextMode(0);
        this.setMarkAllOnOccurrenceSearches(true);
        this.fixCtrlH();
    }

    public static synchronized boolean isRecordingMacro() {
        return recordingMacro;
    }

    public static synchronized void loadMacro(Macro macro) {
        currentMacro = macro;
    }

    void markAll(List<DocumentRange> ranges) {
        RTextAreaHighlighter h = (RTextAreaHighlighter)this.getHighlighter();
        if (h != null) {
            if (ranges != null) {
                for (DocumentRange range : ranges) {
                    try {
                        h.addMarkAllHighlight(range.getStartOffset(), range.getEndOffset(), this.markAllHighlightPainter);
                    } catch (BadLocationException ble) {
                        ble.printStackTrace();
                    }
                }
            }
            this.repaint();
            this.firePropertyChange(MARK_ALL_OCCURRENCES_CHANGED_PROPERTY, null, ranges);
        }
    }

    @Override
    public void paste() {
        this.beginAtomicEdit();
        try {
            super.paste();
        } finally {
            this.endAtomicEdit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void playbackLastMacro() {
        List<Macro.MacroRecord> macroRecords;
        if (currentMacro != null && !(macroRecords = currentMacro.getMacroRecords()).isEmpty()) {
            Action[] actions = this.getActions();
            this.undoManager.beginInternalAtomicEdit();
            try {
                block3: for (Macro.MacroRecord record : macroRecords) {
                    for (Action action : actions) {
                        if (!(action instanceof RecordableTextAction) || !record.id.equals(((RecordableTextAction)action).getMacroID())) continue;
                        action.actionPerformed(new ActionEvent(this, 1001, record.actionCommand));
                        continue block3;
                    }
                }
            } finally {
                this.undoManager.endInternalAtomicEdit();
            }
        }
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
        return RPrintUtilities.printDocumentWordWrap(g, this, this.getFont(), pageIndex, pageFormat, this.getTabSize());
    }

    @Override
    public void read(Reader in, Object desc) throws IOException {
        RTextAreaEditorKit kit = (RTextAreaEditorKit)this.getUI().getEditorKit(this);
        this.setText(null);
        Document doc = this.getDocument();
        this.setDocument(this.createDefaultModel());
        if (desc != null) {
            doc.putProperty("stream", desc);
        }
        try {
            kit.read(in, doc, 0);
        } catch (BadLocationException e) {
            throw new IOException(e.getMessage());
        }
        this.setDocument(doc);
    }

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
        s.defaultReadObject();
        this.undoManager = this.createUndoManager();
        this.getDocument().addUndoableEditListener(this.undoManager);
        this.lineHighlightManager = null;
    }

    public void redoLastAction() {
        try {
            if (this.undoManager.canRedo()) {
                this.undoManager.redo();
            }
        } catch (CannotRedoException cre) {
            cre.printStackTrace();
        }
    }

    public void removeAllLineHighlights() {
        if (this.lineHighlightManager != null) {
            this.lineHighlightManager.removeAllLineHighlights();
        }
    }

    public void removeLineHighlight(Object tag) {
        if (this.lineHighlightManager != null) {
            this.lineHighlightManager.removeLineHighlight(tag);
        }
    }

    @Override
    public void replaceRange(String str, int start, int end) {
        if (end < start) {
            throw new IllegalArgumentException("end before start");
        }
        Document doc = this.getDocument();
        if (doc != null) {
            try {
                this.undoManager.beginInternalAtomicEdit();
                ((AbstractDocument)doc).replace(start, end - start, str, null);
            } catch (BadLocationException e) {
                throw new IllegalArgumentException(e.getMessage());
            } finally {
                this.undoManager.endInternalAtomicEdit();
            }
        }
    }

    @Override
    public void replaceSelection(String text) {
        int firstTab;
        if (text == null) {
            this.handleReplaceSelection(text);
            return;
        }
        if (this.getTabsEmulated() && (firstTab = text.indexOf(9)) > -1) {
            int docOffs = this.getSelectionStart();
            try {
                text = this.replaceTabsWithSpaces(text, docOffs, firstTab);
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        if (this.textMode == 1 && !"\n".equals(text)) {
            Caret caret = this.getCaret();
            int caretPos = caret.getDot();
            Document doc = this.getDocument();
            Element map = doc.getDefaultRootElement();
            int curLine = map.getElementIndex(caretPos);
            int lastLine = map.getElementCount() - 1;
            try {
                int curLineEnd = this.getLineEndOffset(curLine);
                if (caretPos == caret.getMark() && caretPos != curLineEnd) {
                    caretPos = curLine == lastLine ? Math.min(caretPos + text.length(), curLineEnd) : Math.min(caretPos + text.length(), curLineEnd - 1);
                    caret.moveDot(caretPos);
                }
            } catch (BadLocationException ble) {
                UIManager.getLookAndFeel().provideErrorFeedback(this);
                ble.printStackTrace();
            }
        }
        this.handleReplaceSelection(text);
    }

    private String replaceTabsWithSpaces(String text, int docOffs, int firstTab) throws BadLocationException {
        int lineIndex;
        int tabSize = this.getTabSize();
        Document doc = this.getDocument();
        Element root = doc.getDefaultRootElement();
        Element line = root.getElement(lineIndex = root.getElementIndex(docOffs));
        int lineStart = line.getStartOffset();
        int charCount = docOffs - lineStart;
        if (charCount > 0) {
            doc.getText(lineStart, charCount, repTabsSeg);
            charCount = 0;
            for (int i = 0; i < RTextArea.repTabsSeg.count; ++i) {
                char ch = RTextArea.repTabsSeg.array[RTextArea.repTabsSeg.offset + i];
                charCount = ch == '\t' ? 0 : (charCount + 1) % tabSize;
            }
        }
        if (text.length() == 1) {
            return this.createSpacer(tabSize - charCount);
        }
        if (repTabsSB == null) {
            repTabsSB = new StringBuilder();
        }
        repTabsSB.setLength(0);
        char[] array = text.toCharArray();
        int lastPos = 0;
        int offsInLine = charCount;
        block5: for (int pos = firstTab; pos < array.length; ++pos) {
            char ch = array[pos];
            switch (ch) {
                case '\t': {
                    if (pos > lastPos) {
                        repTabsSB.append(array, lastPos, pos - lastPos);
                    }
                    int thisTabSize = tabSize - offsInLine % tabSize;
                    repTabsSB.append(this.createSpacer(thisTabSize));
                    lastPos = pos + 1;
                    offsInLine = 0;
                    continue block5;
                }
                case '\n': {
                    offsInLine = 0;
                    continue block5;
                }
                default: {
                    ++offsInLine;
                }
            }
        }
        if (lastPos < array.length) {
            repTabsSB.append(array, lastPos, array.length - lastPos);
        }
        return repTabsSB.toString();
    }

    public static void setActionProperties(int action, String name, char mnemonic, KeyStroke accelerator) {
        RTextArea.setActionProperties(action, name, Integer.valueOf(mnemonic), accelerator);
    }

    public static void setActionProperties(int action, String name, Integer mnemonic, KeyStroke accelerator) {
        RecordableTextAction tempAction;
        switch (action) {
            case 1: {
                tempAction = cutAction;
                break;
            }
            case 0: {
                tempAction = copyAction;
                break;
            }
            case 3: {
                tempAction = pasteAction;
                break;
            }
            case 2: {
                tempAction = deleteAction;
                break;
            }
            case 5: {
                tempAction = selectAllAction;
                break;
            }
            default: {
                return;
            }
        }
        tempAction.putValue("Name", name);
        tempAction.putValue("ShortDescription", name);
        tempAction.putValue("AcceleratorKey", accelerator);
        tempAction.putValue("MnemonicKey", mnemonic);
    }

    @Override
    public void setCaret(Caret caret) {
        super.setCaret(caret);
        if (this.carets != null && caret instanceof ConfigurableCaret) {
            ((ConfigurableCaret)caret).setStyle(this.carets[this.getTextMode()]);
        }
    }

    public void setCaretStyle(int mode, CaretStyle style) {
        if (style == null) {
            style = CaretStyle.THICK_VERTICAL_LINE_STYLE;
        }
        this.carets[mode] = style;
        if (mode == this.getTextMode() && this.getCaret() instanceof ConfigurableCaret) {
            ((ConfigurableCaret)this.getCaret()).setStyle(style);
        }
    }

    @Override
    public void setDocument(Document document) {
        Document old;
        if (!(document instanceof RDocument)) {
            throw new IllegalArgumentException("RTextArea requires instances of RDocument for its document");
        }
        if (this.undoManager != null && (old = this.getDocument()) != null) {
            old.removeUndoableEditListener(this.undoManager);
        }
        super.setDocument(document);
        if (this.undoManager != null) {
            document.addUndoableEditListener(this.undoManager);
            this.discardAllEdits();
        }
    }

    public static synchronized void setIconGroup(IconGroup group) {
        Icon icon = group.getIcon("cut");
        cutAction.putValue("SmallIcon", icon);
        icon = group.getIcon("copy");
        copyAction.putValue("SmallIcon", icon);
        icon = group.getIcon("paste");
        pasteAction.putValue("SmallIcon", icon);
        icon = group.getIcon("delete");
        deleteAction.putValue("SmallIcon", icon);
        icon = group.getIcon("undo");
        undoAction.putValue("SmallIcon", icon);
        icon = group.getIcon("redo");
        redoAction.putValue("SmallIcon", icon);
        icon = group.getIcon("selectall");
        selectAllAction.putValue("SmallIcon", icon);
        iconGroup = group;
    }

    public void setMarkAllHighlightColor(Color color) {
        Color old = (Color)this.markAllHighlightPainter.getPaint();
        if (old != null && !old.equals(color)) {
            this.markAllHighlightPainter.setPaint(color);
            RTextAreaHighlighter h = (RTextAreaHighlighter)this.getHighlighter();
            if (h.getMarkAllHighlightCount() > 0) {
                this.repaint();
            }
            this.firePropertyChange(MARK_ALL_COLOR_PROPERTY, old, color);
        }
    }

    public void setMarkAllOnOccurrenceSearches(boolean markAll) {
        if (markAll != this.markAllOnOccurrenceSearches) {
            this.markAllOnOccurrenceSearches = markAll;
            this.firePropertyChange(MARK_ALL_ON_OCCURRENCE_SEARCHES_PROPERTY, !markAll, markAll);
        }
    }

    public void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
        this.popupMenuCreated = true;
    }

    @Override
    public void setRoundedSelectionEdges(boolean rounded) {
        if (this.getRoundedSelectionEdges() != rounded) {
            this.markAllHighlightPainter.setRoundedEdges(rounded);
            super.setRoundedSelectionEdges(rounded);
        }
    }

    public static void setSelectedOccurrenceText(String text) {
        selectedOccurrenceText = text;
    }

    public void setTextMode(int mode) {
        if (mode != 0 && mode != 1) {
            mode = 0;
        }
        if (this.textMode != mode) {
            Caret caret = this.getCaret();
            if (caret instanceof ConfigurableCaret) {
                ((ConfigurableCaret)caret).setStyle(this.carets[mode]);
            }
            this.textMode = mode;
            caret.setVisible(false);
            caret.setVisible(true);
        }
    }

    public void setToolTipSupplier(ToolTipSupplier supplier) {
        this.toolTipSupplier = supplier;
    }

    @Override
    public final void setUI(TextUI ui) {
        RTextAreaUI rtaui;
        if (this.popupMenu != null) {
            SwingUtilities.updateComponentTreeUI(this.popupMenu);
        }
        if ((rtaui = (RTextAreaUI)this.getUI()) != null) {
            rtaui.installDefaults();
        }
    }

    public void undoLastAction() {
        try {
            if (this.undoManager.canUndo()) {
                this.undoManager.undo();
            }
        } catch (CannotUndoException cre) {
            cre.printStackTrace();
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        this.getDocument().removeUndoableEditListener(this.undoManager);
        s.defaultWriteObject();
        this.getDocument().addUndoableEditListener(this.undoManager);
    }

    static {
        repTabsSeg = new Segment();
    }

    protected class RTextAreaMutableCaretEvent
    extends RTextAreaBase.RTAMouseListener {
        protected RTextAreaMutableCaretEvent(RTextArea textArea) {
            super(textArea);
        }

        @Override
        public void focusGained(FocusEvent e) {
            Caret c = RTextArea.this.getCaret();
            boolean enabled = c.getDot() != c.getMark();
            cutAction.setEnabled(enabled);
            copyAction.setEnabled(enabled);
            RTextArea.this.undoManager.updateActions();
        }

        @Override
        public void focusLost(FocusEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if ((e.getModifiers() & 0x10) != 0) {
                Caret caret = RTextArea.this.getCaret();
                this.dot = caret.getDot();
                this.mark = caret.getMark();
                RTextArea.this.fireCaretUpdate(this);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                this.showPopup(e);
            } else if ((e.getModifiers() & 0x10) != 0) {
                Caret caret = RTextArea.this.getCaret();
                this.dot = caret.getDot();
                this.mark = caret.getMark();
                RTextArea.this.fireCaretUpdate(this);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                this.showPopup(e);
            }
        }

        private void showPopup(MouseEvent e) {
            JPopupMenu popupMenu = RTextArea.this.getPopupMenu();
            if (popupMenu != null) {
                RTextArea.this.configurePopupMenu(popupMenu);
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                e.consume();
            }
        }
    }
}

