/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.fife.rsta.ui.EscapableDialog;
import org.fife.rsta.ui.ResizableFrameContentPane;
import org.fife.rsta.ui.UIUtil;

public class GoToDialog
extends EscapableDialog {
    private JButton okButton;
    private JButton cancelButton;
    private JTextField lineNumberField;
    private int maxLineNumberAllowed;
    private int lineNumber;
    private String errorDialogTitle;
    private static final ResourceBundle MSG = ResourceBundle.getBundle("org.fife.rsta.ui.GoToDialog");

    public GoToDialog(Dialog owner) {
        super(owner);
        this.init();
    }

    public GoToDialog(Frame owner) {
        super(owner);
        this.init();
    }

    private void init() {
        ComponentOrientation orientation = ComponentOrientation.getOrientation(this.getLocale());
        this.lineNumber = -1;
        this.maxLineNumberAllowed = 1;
        Listener l = new Listener();
        ResizableFrameContentPane contentPane = new ResizableFrameContentPane(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(contentPane);
        Box enterLineNumberPane = new Box(2);
        enterLineNumberPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        this.lineNumberField = new JTextField(16);
        this.lineNumberField.setText("1");
        AbstractDocument doc = (AbstractDocument)this.lineNumberField.getDocument();
        doc.addDocumentListener(l);
        doc.setDocumentFilter(new NumberDocumentFilter());
        JLabel label = UIUtil.newLabel(MSG, "LineNumber", this.lineNumberField);
        enterLineNumberPane.add(label);
        enterLineNumberPane.add(Box.createHorizontalStrut(15));
        enterLineNumberPane.add(this.lineNumberField);
        this.okButton = UIUtil.newButton(MSG, "OK");
        this.okButton.addActionListener(l);
        this.cancelButton = UIUtil.newButton(MSG, "Cancel");
        this.cancelButton.addActionListener(l);
        Container bottomPanel = this.createButtonPanel(this.okButton, this.cancelButton);
        contentPane.add((Component)enterLineNumberPane, "North");
        contentPane.add((Component)bottomPanel, "South");
        JRootPane rootPane = this.getRootPane();
        rootPane.setDefaultButton(this.okButton);
        this.setTitle(MSG.getString("GotoDialogTitle"));
        this.setModal(true);
        this.applyComponentOrientation(orientation);
        this.pack();
        this.setLocationRelativeTo(this.getParent());
    }

    private boolean attemptToGetGoToLine() {
        try {
            this.lineNumber = Integer.parseInt(this.lineNumberField.getText());
            if (this.lineNumber < 1 || this.lineNumber > this.maxLineNumberAllowed) {
                this.lineNumber = -1;
                throw new NumberFormatException();
            }
        } catch (NumberFormatException nfe) {
            this.displayInvalidLineNumberMessage();
            return false;
        }
        this.setVisible(false);
        return true;
    }

    protected Container createButtonPanel(JButton ok, JButton cancel) {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonPanel.add(ok);
        buttonPanel.add(cancel);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add((Component)buttonPanel, "After");
        return bottomPanel;
    }

    protected void displayInvalidLineNumberMessage() {
        JOptionPane.showMessageDialog(this, MSG.getString("LineNumberRange") + this.maxLineNumberAllowed + ".", this.getErrorDialogTitle(), 0);
    }

    @Override
    protected void escapePressed() {
        this.lineNumber = -1;
        super.escapePressed();
    }

    public String getErrorDialogTitle() {
        String title = this.errorDialogTitle;
        if (title == null) {
            title = MSG.getString("ErrorDialog.Title");
        }
        return title;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getMaxLineNumberAllowed() {
        return this.maxLineNumberAllowed;
    }

    public void setErrorDialogTitle(String title) {
        this.errorDialogTitle = title;
    }

    public void setMaxLineNumberAllowed(int max) {
        this.maxLineNumberAllowed = max;
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            this.lineNumber = -1;
            this.okButton.setEnabled(this.lineNumberField.getDocument().getLength() > 0);
            SwingUtilities.invokeLater(() -> {
                this.lineNumberField.requestFocusInWindow();
                this.lineNumberField.selectAll();
            });
        }
        super.setVisible(visible);
    }

    private class NumberDocumentFilter
    extends DocumentFilter {
        private NumberDocumentFilter() {
        }

        private String fix(String str) {
            if (str != null) {
                int origLength = str.length();
                for (int i = 0; i < str.length(); ++i) {
                    if (Character.isDigit(str.charAt(i))) continue;
                    str = str.substring(0, i) + str.substring(i + 1);
                    --i;
                }
                if (origLength != str.length()) {
                    UIManager.getLookAndFeel().provideErrorFeedback(GoToDialog.this);
                }
            }
            return str;
        }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            fb.insertString(offset, this.fix(string), attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
            fb.replace(offset, length, this.fix(text), attr);
        }
    }

    private class Listener
    implements ActionListener,
    DocumentListener {
        private Listener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (GoToDialog.this.okButton == source) {
                GoToDialog.this.attemptToGetGoToLine();
            } else if (GoToDialog.this.cancelButton == source) {
                GoToDialog.this.escapePressed();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            GoToDialog.this.okButton.setEnabled(GoToDialog.this.lineNumberField.getDocument().getLength() > 0);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            GoToDialog.this.okButton.setEnabled(GoToDialog.this.lineNumberField.getDocument().getLength() > 0);
        }
    }
}

