/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui.search;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ui.AssistanceIconPanel;
import org.fife.rsta.ui.ResizableFrameContentPane;
import org.fife.rsta.ui.UIUtil;
import org.fife.rsta.ui.search.AbstractFindReplaceDialog;
import org.fife.rsta.ui.search.SearchListener;
import org.fife.ui.rtextarea.SearchContext;

public class FindDialog
extends AbstractFindReplaceDialog {
    private static final long serialVersionUID = 1L;
    private String lastSearchString;
    protected SearchListener searchListener;

    public FindDialog(Dialog owner, SearchListener listener) {
        super(owner);
        this.init(listener);
    }

    public FindDialog(Frame owner, SearchListener listener) {
        super(owner);
        this.init(listener);
    }

    private void init(SearchListener listener) {
        this.searchListener = listener;
        ComponentOrientation orientation = ComponentOrientation.getOrientation(this.getLocale());
        JPanel enterTextPane = new JPanel(new SpringLayout());
        enterTextPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        JTextComponent textField = UIUtil.getTextComponent(this.findTextCombo);
        textField.addFocusListener(new FindFocusAdapter());
        textField.getDocument().addDocumentListener(new FindDocumentListener());
        JPanel temp = new JPanel(new BorderLayout());
        temp.add(this.findTextCombo);
        AssistanceIconPanel aip = new AssistanceIconPanel(this.findTextCombo);
        temp.add((Component)aip, "Before");
        if (orientation.isLeftToRight()) {
            enterTextPane.add(this.findFieldLabel);
            enterTextPane.add(temp);
        } else {
            enterTextPane.add(temp);
            enterTextPane.add(this.findFieldLabel);
        }
        UIUtil.makeSpringCompactGrid(enterTextPane, 1, 2, 0, 0, 6, 6);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        temp = new JPanel(new BorderLayout());
        bottomPanel.setBorder(UIUtil.getEmpty5Border());
        temp.add((Component)this.searchConditionsPanel, "Before");
        JPanel temp2 = new JPanel(new BorderLayout());
        temp2.add((Component)this.dirPanel, "North");
        temp.add(temp2);
        bottomPanel.add((Component)temp, "Before");
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, 1));
        leftPanel.add(enterTextPane);
        leftPanel.add(bottomPanel);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 5, 5));
        buttonPanel.add(this.findNextButton);
        buttonPanel.add(this.cancelButton);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add((Component)buttonPanel, "North");
        JPanel contentPane = new JPanel(new BorderLayout());
        if (orientation.isLeftToRight()) {
            contentPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 5));
        } else {
            contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
        }
        contentPane.add(leftPanel);
        contentPane.add((Component)rightPanel, "After");
        temp = new ResizableFrameContentPane(new BorderLayout());
        temp.add((Component)contentPane, "North");
        this.setContentPane(temp);
        this.getRootPane().setDefaultButton(this.findNextButton);
        this.setTitle(FindDialog.getString("FindDialogTitle"));
        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setSearchContext(new SearchContext());
        this.addSearchListener(listener);
        this.applyComponentOrientation(orientation);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            String selectedItem;
            String text = this.searchListener.getSelectedText();
            if (text != null) {
                this.findTextCombo.addItem(text);
            }
            boolean nonEmpty = (selectedItem = this.findTextCombo.getSelectedString()) != null && selectedItem.length() > 0;
            this.findNextButton.setEnabled(nonEmpty);
            super.setVisible(true);
            this.focusFindTextField();
        } else {
            super.setVisible(false);
        }
    }

    public void updateUI() {
        SwingUtilities.updateComponentTreeUI(this);
        this.pack();
        JTextComponent textField = UIUtil.getTextComponent(this.findTextCombo);
        textField.addFocusListener(new FindFocusAdapter());
        textField.getDocument().addDocumentListener(new FindDocumentListener());
    }

    private class FindFocusAdapter
    extends FocusAdapter {
        private FindFocusAdapter() {
        }

        @Override
        public void focusGained(FocusEvent e) {
            UIUtil.getTextComponent(FindDialog.this.findTextCombo).selectAll();
            FindDialog.this.lastSearchString = (String)FindDialog.this.findTextCombo.getSelectedItem();
        }
    }

    private class FindDocumentListener
    implements DocumentListener {
        private FindDocumentListener() {
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            FindDialog.this.handleToggleButtons();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            JTextComponent comp = UIUtil.getTextComponent(FindDialog.this.findTextCombo);
            if (comp.getDocument().getLength() == 0) {
                FindDialog.this.findNextButton.setEnabled(false);
            } else {
                FindDialog.this.handleToggleButtons();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }
}

