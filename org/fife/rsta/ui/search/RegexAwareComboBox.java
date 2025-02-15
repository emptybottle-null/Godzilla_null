/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui.search;

import java.awt.Image;
import javax.swing.ComboBoxModel;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ui.ContentAssistable;
import org.fife.rsta.ui.MaxWidthComboBox;
import org.fife.rsta.ui.RComboBoxModel;
import org.fife.rsta.ui.search.AbstractSearchDialog;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

public class RegexAwareComboBox<E>
extends MaxWidthComboBox<E>
implements ContentAssistable {
    private boolean enabled;
    private boolean replace;
    private AutoCompletion ac;
    private RegexAwareProvider provider;
    private Image contentAssistImage;

    public RegexAwareComboBox(boolean replace) {
        this(new RComboBoxModel(), 200, replace);
    }

    public RegexAwareComboBox(ComboBoxModel<E> model, int maxWidth, boolean replace) {
        super(model, maxWidth);
        this.setEditable(true);
        this.replace = replace;
    }

    private void addFindFieldCompletions(RegexAwareProvider p) {
        p.addCompletion(new RegexCompletion(p, "\\\\", "\\\\", "\\\\ - Backslash"));
        p.addCompletion(new RegexCompletion(p, "\\t", "\\t", "\\t - Tab"));
        p.addCompletion(new RegexCompletion(p, "\\n", "\\n", "\\n - Newline"));
        p.addCompletion(new RegexCompletion(p, "[", "[", "[abc] - Any of a, b, or c"));
        p.addCompletion(new RegexCompletion(p, "[^", "[^", "[^abc] - Any character except a, b, or c"));
        p.addCompletion(new RegexCompletion(p, ".", ".", ". - Any character"));
        p.addCompletion(new RegexCompletion(p, "\\d", "\\d", "\\d - A digit"));
        p.addCompletion(new RegexCompletion(p, "\\D", "\\D", "\\D - Not a digit"));
        p.addCompletion(new RegexCompletion(p, "\\s", "\\s", "\\s - A whitespace"));
        p.addCompletion(new RegexCompletion(p, "\\S", "\\S", "\\S - Not a whitespace"));
        p.addCompletion(new RegexCompletion(p, "\\w", "\\w", "\\w - An alphanumeric (word character)"));
        p.addCompletion(new RegexCompletion(p, "\\W", "\\W", "\\W - Not an alphanumeric"));
        p.addCompletion(new RegexCompletion(p, "^", "^", "^ - Line Start"));
        p.addCompletion(new RegexCompletion(p, "$", "$", "$ - Line End"));
        p.addCompletion(new RegexCompletion(p, "\\b", "\b", "\\b - Word beginning or end"));
        p.addCompletion(new RegexCompletion(p, "\\B", "\\B", "\\B - Not a word beginning or end"));
        p.addCompletion(new RegexCompletion(p, "?", "?", "X? - Greedy match, 0 or 1 times"));
        p.addCompletion(new RegexCompletion(p, "*", "*", "X* - Greedy match, 0 or more times"));
        p.addCompletion(new RegexCompletion(p, "+", "+", "X+ - Greedy match, 1 or more times"));
        p.addCompletion(new RegexCompletion(p, "{", "{", "X{n} - Greedy match, exactly n times"));
        p.addCompletion(new RegexCompletion(p, "{", "{", "X{n,} - Greedy match, at least n times"));
        p.addCompletion(new RegexCompletion(p, "{", "{", "X{n,m} - Greedy match, at least n but no more than m times"));
        p.addCompletion(new RegexCompletion(p, "??", "??", "X?? - Lazy match, 0 or 1 times"));
        p.addCompletion(new RegexCompletion(p, "*?", "*?", "X*? - Lazy match, 0 or more times"));
        p.addCompletion(new RegexCompletion(p, "+?", "+?", "X+? - Lazy match, 1 or more times"));
        p.addCompletion(new RegexCompletion(p, "?+", "?+", "X?+ - Possessive match, 0 or 1 times"));
        p.addCompletion(new RegexCompletion(p, "*+", "*+", "X*+ - Possessive match, 0 or more times"));
        p.addCompletion(new RegexCompletion(p, "++", "++", "X++ - Possessive match, 0 or more times"));
        p.addCompletion(new RegexCompletion(p, "\\i", "\\i", "\\i - Match of the capturing group i"));
        p.addCompletion(new RegexCompletion(p, "(", "(", "(Expr) - Mark Expr as capturing group"));
        p.addCompletion(new RegexCompletion(p, "(?:", "(?:", "(?:Expr) - Non-capturing group"));
    }

    private void addReplaceFieldCompletions(RegexAwareProvider p) {
        p.addCompletion(new RegexCompletion(p, "$", "$", "$i - Match of the capturing group i"));
        p.addCompletion(new RegexCompletion(p, "\\", "\\", "\\ - Quote next character"));
        p.addCompletion(new RegexCompletion(p, "\\t", "\\t", "\\t - Tab"));
        p.addCompletion(new RegexCompletion(p, "\\n", "\\n", "\\n - Newline"));
    }

    private AutoCompletion getAutoCompletion() {
        if (this.ac == null) {
            this.ac = new AutoCompletion(this.getCompletionProvider());
        }
        return this.ac;
    }

    protected synchronized CompletionProvider getCompletionProvider() {
        if (this.provider == null) {
            this.provider = new RegexAwareProvider();
            if (this.replace) {
                this.addReplaceFieldCompletions(this.provider);
            } else {
                this.addFindFieldCompletions(this.provider);
            }
        }
        return this.provider;
    }

    public Image getContentAssistImage() {
        if (this.contentAssistImage != null) {
            return this.contentAssistImage;
        }
        return AbstractSearchDialog.getContentAssistImage();
    }

    public boolean hideAutoCompletePopups() {
        return this.ac != null && this.ac.hideChildWindows();
    }

    public boolean isAutoCompleteEnabled() {
        return this.enabled;
    }

    public void setAutoCompleteEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                AutoCompletion ac = this.getAutoCompletion();
                JTextComponent tc = (JTextComponent)this.getEditor().getEditorComponent();
                ac.install(tc);
            } else {
                this.ac.uninstall();
            }
            String prop = "AssistanceImage";
            if (enabled) {
                this.firePropertyChange(prop, null, this.getContentAssistImage());
            } else {
                this.firePropertyChange(prop, null, null);
            }
        }
    }

    public void setContentAssistImage(Image image) {
        this.contentAssistImage = image;
    }

    private static class RegexCompletion
    extends BasicCompletion {
        private String inputText;

        RegexCompletion(CompletionProvider provider, String inputText, String replacementText, String shortDesc) {
            super(provider, replacementText, shortDesc);
            this.inputText = inputText;
        }

        @Override
        public String getInputText() {
            return this.inputText;
        }

        @Override
        public String toString() {
            return this.getShortDescription();
        }
    }

    private static class RegexAwareProvider
    extends DefaultCompletionProvider {
        private RegexAwareProvider() {
        }

        @Override
        protected boolean isValidChar(char ch) {
            switch (ch) {
                case '$': 
                case '(': 
                case '*': 
                case '+': 
                case '.': 
                case ':': 
                case '?': 
                case '[': 
                case '\\': 
                case '^': 
                case '{': {
                    return true;
                }
            }
            return false;
        }
    }
}

