/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui.search;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ui.EscapableDialog;
import org.fife.rsta.ui.UIUtil;
import org.fife.rsta.ui.search.FindReplaceButtonsEnableResult;
import org.fife.rsta.ui.search.SearchComboBox;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rtextarea.SearchContext;

public class AbstractSearchDialog
extends EscapableDialog
implements ActionListener {
    private static final long serialVersionUID = 1L;
    protected SearchContext context;
    private SearchContextListener contextListener;
    protected JCheckBox caseCheckBox;
    protected JCheckBox wholeWordCheckBox;
    protected JCheckBox regexCheckBox;
    protected JCheckBox wrapCheckBox;
    protected JPanel searchConditionsPanel;
    private static Image contentAssistImage;
    protected SearchComboBox findTextCombo;
    protected JButton cancelButton;
    private static final ResourceBundle MSG;

    public AbstractSearchDialog(Dialog owner) {
        super(owner);
        this.init();
    }

    public AbstractSearchDialog(Frame owner) {
        super(owner);
        this.init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command;
        switch (command = e.getActionCommand()) {
            case "FlipMatchCase": {
                boolean matchCase = this.caseCheckBox.isSelected();
                this.context.setMatchCase(matchCase);
                break;
            }
            case "FlipWholeWord": {
                boolean wholeWord = this.wholeWordCheckBox.isSelected();
                this.context.setWholeWord(wholeWord);
                break;
            }
            case "FlipRegEx": {
                boolean useRegEx = this.regexCheckBox.isSelected();
                this.context.setRegularExpression(useRegEx);
                break;
            }
            case "FlipWrap": {
                boolean wrap = this.wrapCheckBox.isSelected();
                this.context.setSearchWrap(wrap);
                break;
            }
            default: {
                this.setVisible(false);
            }
        }
    }

    private JCheckBox createCheckBox(ResourceBundle msg, String keyRoot) {
        JCheckBox cb = new JCheckBox(msg.getString(keyRoot));
        cb.setMnemonic((int)msg.getString(keyRoot + "Mnemonic").charAt(0));
        cb.setActionCommand("Flip" + keyRoot);
        cb.addActionListener(this);
        return cb;
    }

    protected SearchContext createDefaultSearchContext() {
        return new SearchContext();
    }

    protected Border createTitledBorder(String title) {
        if (title != null && title.charAt(title.length() - 1) != ':') {
            title = title + ":";
        }
        return BorderFactory.createTitledBorder(title);
    }

    @Override
    protected void escapePressed() {
        if (this.findTextCombo.hideAutoCompletePopups()) {
            return;
        }
        super.escapePressed();
    }

    protected void focusFindTextField() {
        JTextComponent textField = UIUtil.getTextComponent(this.findTextCombo);
        textField.requestFocusInWindow();
        textField.selectAll();
    }

    protected ResourceBundle getBundle() {
        return MSG;
    }

    public final String getCancelButtonText() {
        return this.cancelButton.getText();
    }

    public static Image getContentAssistImage() {
        if (contentAssistImage == null) {
            URL url = AbstractSearchDialog.class.getResource("lightbulb.png");
            try {
                contentAssistImage = ImageIO.read(url);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return contentAssistImage;
    }

    public final String getMatchCaseCheckboxText() {
        return this.caseCheckBox.getText();
    }

    public final String getRegularExpressionCheckboxText() {
        return this.regexCheckBox.getText();
    }

    public SearchContext getSearchContext() {
        return this.context;
    }

    public String getSearchString() {
        return this.findTextCombo.getSelectedString();
    }

    public static String getString(String key) {
        return MSG.getString(key);
    }

    public final String getWholeWordCheckboxText() {
        return this.wholeWordCheckBox.getText();
    }

    public final String getWrapCheckboxText() {
        return this.wrapCheckBox.getText();
    }

    protected void handleRegExCheckBoxClicked() {
        this.handleToggleButtons();
        boolean b = this.regexCheckBox.isSelected();
        this.findTextCombo.setAutoCompleteEnabled(b);
    }

    protected void handleSearchContextPropertyChanged(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        if ("Search.MatchCase".equals(prop)) {
            boolean newValue = (Boolean)e.getNewValue();
            this.caseCheckBox.setSelected(newValue);
        } else if ("Search.MatchWholeWord".equals(prop)) {
            boolean newValue = (Boolean)e.getNewValue();
            this.wholeWordCheckBox.setSelected(newValue);
        } else if ("Search.UseRegex".equals(prop)) {
            boolean newValue = (Boolean)e.getNewValue();
            this.regexCheckBox.setSelected(newValue);
            this.handleRegExCheckBoxClicked();
        } else if ("Search.searchFor".equals(prop)) {
            String oldValue;
            String newValue = (String)e.getNewValue();
            if (!newValue.equals(oldValue = this.getSearchString())) {
                this.setSearchString(newValue);
            }
        } else if ("Search.Wrap".equals(prop)) {
            boolean newValue = (Boolean)e.getNewValue();
            this.wrapCheckBox.setSelected(newValue);
        }
    }

    protected FindReplaceButtonsEnableResult handleToggleButtons() {
        JTextComponent tc = UIUtil.getTextComponent(this.findTextCombo);
        String text = tc.getText();
        if (text.length() == 0) {
            return new FindReplaceButtonsEnableResult(false, null);
        }
        if (this.regexCheckBox.isSelected()) {
            try {
                Pattern.compile(text);
            } catch (PatternSyntaxException pse) {
                return new FindReplaceButtonsEnableResult(false, pse.getMessage());
            }
        }
        return new FindReplaceButtonsEnableResult(true, null);
    }

    private void init() {
        this.contextListener = new SearchContextListener();
        this.setSearchContext(this.createDefaultSearchContext());
        this.searchConditionsPanel = new JPanel();
        this.searchConditionsPanel.setLayout(new BoxLayout(this.searchConditionsPanel, 1));
        this.caseCheckBox = this.createCheckBox(MSG, "MatchCase");
        this.searchConditionsPanel.add(this.caseCheckBox);
        this.wholeWordCheckBox = this.createCheckBox(MSG, "WholeWord");
        this.searchConditionsPanel.add(this.wholeWordCheckBox);
        this.regexCheckBox = this.createCheckBox(MSG, "RegEx");
        this.searchConditionsPanel.add(this.regexCheckBox);
        this.wrapCheckBox = this.createCheckBox(MSG, "Wrap");
        this.searchConditionsPanel.add(this.wrapCheckBox);
        this.findTextCombo = new SearchComboBox(null, false);
        this.cancelButton = new JButton(AbstractSearchDialog.getString("Cancel"));
        this.cancelButton.setActionCommand("Cancel");
        this.cancelButton.addActionListener(this);
    }

    protected boolean matchesSearchFor(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }
        String searchFor = this.findTextCombo.getSelectedString();
        if (searchFor != null && searchFor.length() > 0) {
            boolean matchCase = this.caseCheckBox.isSelected();
            if (this.regexCheckBox.isSelected()) {
                Pattern pattern;
                int flags = 8;
                flags = RSyntaxUtilities.getPatternFlags(matchCase, flags);
                try {
                    pattern = Pattern.compile(searchFor, flags);
                } catch (PatternSyntaxException pse) {
                    pse.printStackTrace();
                    return false;
                }
                return pattern.matcher(text).matches();
            }
            if (matchCase) {
                return searchFor.equals(text);
            }
            return searchFor.equalsIgnoreCase(text);
        }
        return false;
    }

    @Deprecated
    protected static boolean isPreJava6JRE() {
        String version = System.getProperty("java.specification.version");
        return version.startsWith("1.5") || version.startsWith("1.4");
    }

    public static boolean isWholeWord(CharSequence searchIn, int offset, int len) {
        boolean wsAfter;
        boolean wsBefore;
        try {
            wsBefore = Character.isWhitespace(searchIn.charAt(offset - 1));
        } catch (IndexOutOfBoundsException e) {
            wsBefore = true;
        }
        try {
            wsAfter = Character.isWhitespace(searchIn.charAt(offset + len));
        } catch (IndexOutOfBoundsException e) {
            wsAfter = true;
        }
        return wsBefore && wsAfter;
    }

    protected void refreshUIFromContext() {
        if (this.caseCheckBox == null) {
            return;
        }
        this.caseCheckBox.setSelected(this.context.getMatchCase());
        this.regexCheckBox.setSelected(this.context.isRegularExpression());
        this.wholeWordCheckBox.setSelected(this.context.getWholeWord());
        this.wrapCheckBox.setSelected(this.context.getSearchWrap());
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
        this.focusFindTextField();
    }

    public final void setCancelButtonText(String text) {
        this.cancelButton.setText(text);
    }

    public void setContentAssistImage(Image image) {
        this.findTextCombo.setContentAssistImage(image);
    }

    public final void setMatchCaseCheckboxText(String text) {
        this.caseCheckBox.setText(text);
    }

    public final void setRegularExpressionCheckboxText(String text) {
        this.regexCheckBox.setText(text);
    }

    public void setSearchContext(SearchContext context) {
        if (this.context != null) {
            this.context.removePropertyChangeListener(this.contextListener);
        }
        this.context = context;
        this.context.addPropertyChangeListener(this.contextListener);
        this.refreshUIFromContext();
    }

    public void setSearchString(String newSearchString) {
        this.findTextCombo.addItem(newSearchString);
    }

    public final void setWholeWordCheckboxText(String text) {
        this.wholeWordCheckBox.setText(text);
    }

    public final void setWrapCheckboxText(String text) {
        this.wrapCheckBox.setText(text);
    }

    static {
        MSG = ResourceBundle.getBundle("org.fife.rsta.ui.search.Search");
    }

    private class SearchContextListener
    implements PropertyChangeListener {
        private SearchContextListener() {
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            AbstractSearchDialog.this.handleSearchContextPropertyChanged(e);
        }
    }
}

