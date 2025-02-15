/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui.search;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.EventListenerList;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ui.UIUtil;
import org.fife.rsta.ui.search.AbstractSearchDialog;
import org.fife.rsta.ui.search.FindReplaceButtonsEnableResult;
import org.fife.rsta.ui.search.SearchEvent;
import org.fife.rsta.ui.search.SearchListener;
import org.fife.rsta.ui.search.SearchUtil;
import org.fife.ui.rtextarea.SearchContext;

public abstract class AbstractFindReplaceDialog
extends AbstractSearchDialog {
    public static final String SEARCH_DOWNWARD_PROPERTY = "SearchDialog.SearchDownward";
    protected JRadioButton upButton;
    protected JRadioButton downButton;
    protected JPanel dirPanel;
    private String dirPanelTitle;
    protected JLabel findFieldLabel;
    protected JButton findNextButton;
    protected JCheckBox markAllCheckBox;
    private EventListenerList listenerList;

    public AbstractFindReplaceDialog(Dialog owner) {
        super(owner);
        this.init();
    }

    public AbstractFindReplaceDialog(Frame owner) {
        super(owner);
        this.init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("UpRadioButtonClicked".equals(command)) {
            this.context.setSearchForward(false);
        } else if ("DownRadioButtonClicked".equals(command)) {
            this.context.setSearchForward(true);
        } else if ("MarkAll".equals(command)) {
            boolean checked = this.markAllCheckBox.isSelected();
            this.context.setMarkAll(checked);
        } else if (SearchEvent.Type.FIND.name().equals(command)) {
            this.doSearch(this.context.getSearchForward());
        } else {
            super.actionPerformed(e);
        }
    }

    public void addSearchListener(SearchListener l) {
        this.listenerList.add(SearchListener.class, l);
    }

    private void doSearch(boolean forward) {
        JTextComponent tc = UIUtil.getTextComponent(this.findTextCombo);
        this.findTextCombo.addItem(tc.getText());
        this.context.setSearchFor(this.getSearchString());
        SearchContext contextToFire = this.context;
        if (forward != this.context.getSearchForward()) {
            contextToFire = this.context.clone();
            contextToFire.setSearchForward(forward);
        }
        this.fireSearchEvent(SearchEvent.Type.FIND, contextToFire);
    }

    protected void fireSearchEvent(SearchEvent.Type type, SearchContext context) {
        if (context == null) {
            context = this.context;
        }
        Object[] listeners = this.listenerList.getListenerList();
        SearchEvent e = null;
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] != SearchListener.class) continue;
            if (e == null) {
                e = new SearchEvent(this, type, context);
            }
            ((SearchListener)listeners[i + 1]).searchEvent(e);
        }
    }

    public final String getDownRadioButtonText() {
        return this.downButton.getText();
    }

    public final String getFindButtonText() {
        return this.findNextButton.getText();
    }

    public final String getFindWhatLabelText() {
        return this.findFieldLabel.getText();
    }

    public final String getSearchButtonsBorderText() {
        return this.dirPanelTitle;
    }

    public final String getUpRadioButtonText() {
        return this.upButton.getText();
    }

    @Override
    protected void handleSearchContextPropertyChanged(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        if ("Search.Forward".equals(prop)) {
            boolean newValue = (Boolean)e.getNewValue();
            JRadioButton button = newValue ? this.downButton : this.upButton;
            button.setSelected(true);
        } else if ("Search.MarkAll".equals(prop)) {
            boolean newValue = (Boolean)e.getNewValue();
            this.markAllCheckBox.setSelected(newValue);
        } else {
            super.handleSearchContextPropertyChanged(e);
        }
    }

    @Override
    protected FindReplaceButtonsEnableResult handleToggleButtons() {
        FindReplaceButtonsEnableResult er = super.handleToggleButtons();
        boolean enable = er.getEnable();
        this.findNextButton.setEnabled(enable);
        JTextComponent tc = UIUtil.getTextComponent(this.findTextCombo);
        tc.setForeground(enable ? UIManager.getColor("TextField.foreground") : UIUtil.getErrorTextForeground());
        String tooltip = SearchUtil.getToolTip(er);
        tc.setToolTipText(tooltip);
        return er;
    }

    private void init() {
        this.listenerList = new EventListenerList();
        this.dirPanel = new JPanel();
        this.dirPanel.setLayout(new BoxLayout(this.dirPanel, 2));
        this.setSearchButtonsBorderText(AbstractFindReplaceDialog.getString("Direction"));
        ButtonGroup bg = new ButtonGroup();
        this.upButton = new JRadioButton(AbstractFindReplaceDialog.getString("Up"), false);
        this.upButton.setMnemonic((int)AbstractFindReplaceDialog.getString("UpMnemonic").charAt(0));
        this.downButton = new JRadioButton(AbstractFindReplaceDialog.getString("Down"), true);
        this.downButton.setMnemonic((int)AbstractFindReplaceDialog.getString("DownMnemonic").charAt(0));
        this.upButton.setActionCommand("UpRadioButtonClicked");
        this.upButton.addActionListener(this);
        this.downButton.setActionCommand("DownRadioButtonClicked");
        this.downButton.addActionListener(this);
        bg.add(this.upButton);
        bg.add(this.downButton);
        this.dirPanel.add(this.upButton);
        this.dirPanel.add(this.downButton);
        this.markAllCheckBox = new JCheckBox(AbstractFindReplaceDialog.getString("MarkAll"));
        this.markAllCheckBox.setMnemonic((int)AbstractFindReplaceDialog.getString("MarkAllMnemonic").charAt(0));
        this.markAllCheckBox.setActionCommand("MarkAll");
        this.markAllCheckBox.addActionListener(this);
        this.searchConditionsPanel.removeAll();
        this.searchConditionsPanel.setLayout(new BorderLayout());
        JPanel temp = new JPanel();
        temp.setLayout(new BoxLayout(temp, 3));
        temp.add(this.caseCheckBox);
        temp.add(this.wholeWordCheckBox);
        temp.add(this.wrapCheckBox);
        this.searchConditionsPanel.add((Component)temp, "Before");
        temp = new JPanel();
        temp.setLayout(new BoxLayout(temp, 3));
        temp.add(this.regexCheckBox);
        temp.add(this.markAllCheckBox);
        this.searchConditionsPanel.add((Component)temp, "After");
        this.findFieldLabel = UIUtil.newLabel(this.getBundle(), "FindWhat", this.findTextCombo);
        this.findNextButton = UIUtil.newButton(this.getBundle(), "Find");
        this.findNextButton.setActionCommand(SearchEvent.Type.FIND.name());
        this.findNextButton.addActionListener(this);
        this.findNextButton.setDefaultCapable(true);
        this.findNextButton.setEnabled(false);
        this.installKeyboardActions();
    }

    private void installKeyboardActions() {
        JRootPane rootPane = this.getRootPane();
        InputMap im = rootPane.getInputMap(1);
        ActionMap am = rootPane.getActionMap();
        int modifier = this.getToolkit().getMenuShortcutKeyMask();
        KeyStroke ctrlF = KeyStroke.getKeyStroke(70, modifier);
        im.put(ctrlF, "focusSearchForField");
        am.put("focusSearchForField", new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractFindReplaceDialog.this.requestFocus();
            }
        });
        int shift = 1;
        int ctrl = 2;
        if (System.getProperty("os.name").toLowerCase().contains("os x")) {
            ctrl = 4;
        }
        KeyStroke ks = KeyStroke.getKeyStroke(10, shift);
        im.put(ks, "searchBackward");
        ks = KeyStroke.getKeyStroke(10, ctrl);
        im.put(ks, "searchBackward");
        am.put("searchBackward", new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractFindReplaceDialog.this.doSearch(!AbstractFindReplaceDialog.this.context.getSearchForward());
            }
        });
    }

    @Override
    protected void refreshUIFromContext() {
        if (this.markAllCheckBox == null) {
            return;
        }
        super.refreshUIFromContext();
        this.markAllCheckBox.setSelected(this.context.getMarkAll());
        boolean searchForward = this.context.getSearchForward();
        this.upButton.setSelected(!searchForward);
        this.downButton.setSelected(searchForward);
    }

    public void removeSearchListener(SearchListener l) {
        this.listenerList.remove(SearchListener.class, l);
    }

    public void setDownRadioButtonText(String text) {
        this.downButton.setText(text);
    }

    public final void setFindButtonText(String text) {
        this.findNextButton.setText(text);
    }

    public void setFindWhatLabelText(String text) {
        this.findFieldLabel.setText(text);
    }

    public final void setSearchButtonsBorderText(String text) {
        this.dirPanelTitle = text;
        this.dirPanel.setBorder(this.createTitledBorder(this.dirPanelTitle));
    }

    public void setUpRadioButtonText(String text) {
        this.upButton.setText(text);
    }
}

