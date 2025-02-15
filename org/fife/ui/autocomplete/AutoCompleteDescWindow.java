/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import org.fife.ui.autocomplete.AbstractCompletionProvider;
import org.fife.ui.autocomplete.AutoCompletePopupWindow;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DescWindowCallback;
import org.fife.ui.autocomplete.ExternalURLHandler;
import org.fife.ui.autocomplete.LinkRedirector;
import org.fife.ui.autocomplete.SizeGrip;
import org.fife.ui.autocomplete.TipUtil;
import org.fife.ui.autocomplete.Util;
import org.fife.ui.rsyntaxtextarea.PopupWindowDecorator;

class AutoCompleteDescWindow
extends JWindow
implements HyperlinkListener,
DescWindowCallback {
    private AutoCompletion ac;
    private JEditorPane descArea;
    private JScrollPane scrollPane;
    private JToolBar descWindowNavBar;
    private Action backAction;
    private Action forwardAction;
    private List<HistoryEntry> history;
    private int historyPos;
    private Timer timer;
    private TimerAction timerAction;
    private ResourceBundle bundle;
    private static final int INITIAL_TIMER_DELAY = 120;
    private static final String MSG = "org.fife.ui.autocomplete.AutoCompleteDescWindow";

    AutoCompleteDescWindow(Window owner, AutoCompletion ac) {
        super(owner);
        PopupWindowDecorator decorator;
        this.ac = ac;
        ComponentOrientation o = ac.getTextComponentOrientation();
        JPanel cp = new JPanel(new BorderLayout());
        cp.setBorder(TipUtil.getToolTipBorder());
        this.descArea = new JEditorPane("text/html", null);
        TipUtil.tweakTipEditorPane(this.descArea);
        this.descArea.addHyperlinkListener(this);
        this.scrollPane = new JScrollPane(this.descArea);
        Border b = BorderFactory.createEmptyBorder();
        this.descArea.setBackground(ac.getDescWindowColor());
        this.scrollPane.setBorder(b);
        this.scrollPane.setViewportBorder(b);
        this.scrollPane.setBackground(this.descArea.getBackground());
        this.scrollPane.getViewport().setBackground(this.descArea.getBackground());
        cp.add(this.scrollPane);
        this.descWindowNavBar = new JToolBar();
        this.backAction = new ToolBarBackAction(o.isLeftToRight());
        this.forwardAction = new ToolBarForwardAction(o.isLeftToRight());
        this.descWindowNavBar.setFloatable(false);
        this.descWindowNavBar.setBackground(ac.getDescWindowColor());
        this.descWindowNavBar.add(new JButton(this.backAction));
        this.descWindowNavBar.add(new JButton(this.forwardAction));
        JPanel bottomPanel = new JPanel(new BorderLayout());
        b = new AbstractBorder(){

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(1, 0, 0, 0);
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
                g.setColor(UIManager.getColor("controlDkShadow"));
                g.drawLine(x, y, x + w - 1, y);
            }
        };
        bottomPanel.setBorder(b);
        bottomPanel.setBackground(ac.getDescWindowColor());
        SizeGrip rp = new SizeGrip();
        bottomPanel.add((Component)this.descWindowNavBar, "Before");
        bottomPanel.add((Component)rp, "After");
        rp.setBackground(ac.getDescWindowColor());
        cp.add((Component)bottomPanel, "South");
        this.setContentPane(cp);
        this.applyComponentOrientation(o);
        this.setFocusableWindowState(false);
        if (Util.getShouldAllowDecoratingMainAutoCompleteWindows() && (decorator = PopupWindowDecorator.get()) != null) {
            decorator.decorate(this);
        }
        this.history = new ArrayList<HistoryEntry>(1);
        this.historyPos = -1;
        this.timerAction = new TimerAction();
        this.timer = new Timer(120, this.timerAction);
        this.timer.setRepeats(false);
    }

    private void addToHistory(HistoryEntry historyItem) {
        this.history.add(++this.historyPos, historyItem);
        this.clearHistoryAfterCurrentPos();
        this.setActionStates();
    }

    private void clearHistory() {
        this.history.clear();
        this.historyPos = -1;
        if (this.descWindowNavBar != null) {
            this.setActionStates();
        }
    }

    private void clearHistoryAfterCurrentPos() {
        for (int i = this.history.size() - 1; i > this.historyPos; --i) {
            this.history.remove(i);
        }
        this.setActionStates();
    }

    public boolean copy() {
        if (this.isVisible() && this.descArea.getSelectionStart() != this.descArea.getSelectionEnd()) {
            this.descArea.copy();
            return true;
        }
        return false;
    }

    private String getString(String key) {
        if (this.bundle == null) {
            this.bundle = ResourceBundle.getBundle(MSG);
        }
        return this.bundle.getString(key);
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        ExternalURLHandler handler;
        URL newUrl;
        LinkRedirector redirector;
        HyperlinkEvent.EventType type = e.getEventType();
        if (!type.equals(HyperlinkEvent.EventType.ACTIVATED)) {
            return;
        }
        URL url = e.getURL();
        if (url != null && (redirector = AutoCompletion.getLinkRedirector()) != null && (newUrl = redirector.possiblyRedirect(url)) != null && newUrl != url) {
            url = newUrl;
            e = new HyperlinkEvent(e.getSource(), e.getEventType(), newUrl, e.getDescription(), e.getSourceElement());
        }
        if ((handler = this.ac.getExternalURLHandler()) != null) {
            HistoryEntry current = this.history.get(this.historyPos);
            handler.urlClicked(e, current.completion, this);
            return;
        }
        if (url != null) {
            try {
                Util.browse(new URI(url.toString()));
            } catch (URISyntaxException ioe) {
                UIManager.getLookAndFeel().provideErrorFeedback(this.descArea);
                ioe.printStackTrace();
            }
        } else {
            AutoCompletePopupWindow parent = (AutoCompletePopupWindow)this.getParent();
            CompletionProvider p = parent.getSelection().getProvider();
            if (p instanceof AbstractCompletionProvider) {
                String name = e.getDescription();
                List<Completion> l = ((AbstractCompletionProvider)p).getCompletionByInputText(name);
                if (l != null && !l.isEmpty()) {
                    Completion c = l.get(0);
                    this.setDescriptionFor(c, true);
                } else {
                    UIManager.getLookAndFeel().provideErrorFeedback(this.descArea);
                }
            }
        }
    }

    private void setActionStates() {
        String desc = null;
        if (this.historyPos > 0) {
            this.backAction.setEnabled(true);
            desc = "Back to " + this.history.get(this.historyPos - 1);
        } else {
            this.backAction.setEnabled(false);
        }
        this.backAction.putValue("ShortDescription", desc);
        if (this.historyPos > -1 && this.historyPos < this.history.size() - 1) {
            this.forwardAction.setEnabled(true);
            desc = "Forward to " + this.history.get(this.historyPos + 1);
        } else {
            this.forwardAction.setEnabled(false);
            desc = null;
        }
        this.forwardAction.putValue("ShortDescription", desc);
    }

    public void setDescriptionFor(Completion item) {
        this.setDescriptionFor(item, false);
    }

    protected void setDescriptionFor(Completion item, boolean addToHistory) {
        this.setDescriptionFor(item, null, addToHistory);
    }

    protected void setDescriptionFor(Completion item, String anchor, boolean addToHistory) {
        this.timer.stop();
        this.timerAction.setCompletion(item, anchor, addToHistory);
        this.timer.start();
    }

    private void setDisplayedDesc(Completion completion, String anchor, boolean addToHistory) {
        String desc;
        String string = desc = completion == null ? null : completion.getSummary();
        if (desc == null) {
            desc = "<html><em>" + this.getString("NoDescAvailable") + "</em>";
        }
        this.descArea.setText(desc);
        if (anchor != null) {
            SwingUtilities.invokeLater(() -> this.descArea.scrollToReference(anchor));
        } else {
            this.descArea.setCaretPosition(0);
        }
        if (!addToHistory) {
            this.clearHistory();
        }
        this.addToHistory(new HistoryEntry(completion, desc, null));
    }

    @Override
    public void setVisible(boolean visible) {
        if (!visible) {
            this.clearHistory();
        }
        super.setVisible(visible);
    }

    @Override
    public void showSummaryFor(Completion completion, String anchor) {
        this.setDescriptionFor(completion, anchor, true);
    }

    public void updateUI() {
        SwingUtilities.updateComponentTreeUI(this);
        TipUtil.tweakTipEditorPane(this.descArea);
        this.scrollPane.setBackground(this.descArea.getBackground());
        this.scrollPane.getViewport().setBackground(this.descArea.getBackground());
        ((JPanel)this.getContentPane()).setBorder(TipUtil.getToolTipBorder());
    }

    class ToolBarForwardAction
    extends AbstractAction {
        ToolBarForwardAction(boolean ltr) {
            String img = "org/fife/ui/autocomplete/arrow_" + (ltr ? "right.png" : "left.png");
            ClassLoader cl = this.getClass().getClassLoader();
            ImageIcon icon = new ImageIcon(cl.getResource(img));
            this.putValue("SmallIcon", icon);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (AutoCompleteDescWindow.this.history != null && AutoCompleteDescWindow.this.historyPos < AutoCompleteDescWindow.this.history.size() - 1) {
                HistoryEntry pair = (HistoryEntry)AutoCompleteDescWindow.this.history.get(++AutoCompleteDescWindow.this.historyPos);
                AutoCompleteDescWindow.this.descArea.setText(pair.summary);
                if (pair.anchor != null) {
                    AutoCompleteDescWindow.this.descArea.scrollToReference(pair.anchor);
                } else {
                    AutoCompleteDescWindow.this.descArea.setCaretPosition(0);
                }
                AutoCompleteDescWindow.this.setActionStates();
            }
        }
    }

    class ToolBarBackAction
    extends AbstractAction {
        ToolBarBackAction(boolean ltr) {
            String img = "org/fife/ui/autocomplete/arrow_" + (ltr ? "left.png" : "right.png");
            ClassLoader cl = this.getClass().getClassLoader();
            ImageIcon icon = new ImageIcon(cl.getResource(img));
            this.putValue("SmallIcon", icon);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (AutoCompleteDescWindow.this.historyPos > 0) {
                HistoryEntry pair = (HistoryEntry)AutoCompleteDescWindow.this.history.get(--AutoCompleteDescWindow.this.historyPos);
                AutoCompleteDescWindow.this.descArea.setText(pair.summary);
                if (pair.anchor != null) {
                    AutoCompleteDescWindow.this.descArea.scrollToReference(pair.anchor);
                } else {
                    AutoCompleteDescWindow.this.descArea.setCaretPosition(0);
                }
                AutoCompleteDescWindow.this.setActionStates();
            }
        }
    }

    private class TimerAction
    extends AbstractAction {
        private Completion completion;
        private String anchor;
        private boolean addToHistory;

        private TimerAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            AutoCompleteDescWindow.this.setDisplayedDesc(this.completion, this.anchor, this.addToHistory);
        }

        void setCompletion(Completion c, String anchor, boolean addToHistory) {
            this.completion = c;
            this.anchor = anchor;
            this.addToHistory = addToHistory;
        }
    }

    private static class HistoryEntry {
        private Completion completion;
        private String summary;
        private String anchor;

        HistoryEntry(Completion completion, String summary, String anchor) {
            this.completion = completion;
            this.summary = summary;
            this.anchor = anchor;
        }

        public String toString() {
            return this.completion.getInputText();
        }
    }
}

