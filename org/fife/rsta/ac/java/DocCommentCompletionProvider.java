/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Graphics;
import javax.swing.Icon;
import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.JavaShorthandCompletion;
import org.fife.rsta.ac.java.JavaSourceCompletion;
import org.fife.rsta.ac.java.JavaTemplateCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

class DocCommentCompletionProvider
extends DefaultCompletionProvider {
    public DocCommentCompletionProvider() {
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@author"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@deprecated"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@exception"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@param"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@return"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@see"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@serial"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@serialData"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@serialField"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@since"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@throws"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@version"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@category"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@example"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@tutorial"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@index"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@exclude"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@todo"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@internal"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@obsolete"));
        this.addCompletion(new JavadocCompletion((CompletionProvider)this, "@threadsafety"));
        this.addCompletion(new JavadocTemplateCompletion(this, "{@code}", "{@code}", "{@code ${}}${cursor}"));
        this.addCompletion(new JavadocTemplateCompletion(this, "{@docRoot}", "{@docRoot}", "{@docRoot ${}}${cursor}"));
        this.addCompletion(new JavadocTemplateCompletion(this, "{@inheritDoc}", "{@inheritDoc}", "{@inheritDoc ${}}${cursor}"));
        this.addCompletion(new JavadocTemplateCompletion(this, "{@link}", "{@link}", "{@link ${}}${cursor}"));
        this.addCompletion(new JavadocTemplateCompletion(this, "{@linkplain}", "{@linkplain}", "{@linkplain ${}}${cursor}"));
        this.addCompletion(new JavadocTemplateCompletion(this, "{@literal}", "{@literal}", "{@literal ${}}${cursor}"));
        this.addCompletion(new JavadocTemplateCompletion(this, "{@value}", "{@value}", "{@value ${}}${cursor}"));
        this.addCompletion(new JavaShorthandCompletion(this, "null", "<code>null</code>", "<code>null</code>"));
        this.addCompletion(new JavaShorthandCompletion(this, "true", "<code>true</code>", "<code>true</code>"));
        this.addCompletion(new JavaShorthandCompletion(this, "false", "<code>false</code>", "<code>false</code>"));
        this.setAutoActivationRules(false, "{@");
    }

    @Override
    protected boolean isValidChar(char ch) {
        return Character.isLetterOrDigit(ch) || ch == '_' || ch == '@' || ch == '{' || ch == '}';
    }

    private static class JavadocTemplateCompletion
    extends JavaTemplateCompletion {
        public JavadocTemplateCompletion(CompletionProvider provider, String inputText, String definitionString, String template) {
            super(provider, inputText, definitionString, template);
            this.setIcon("javadocItemIcon");
        }
    }

    private static class JavadocCompletion
    extends BasicCompletion
    implements JavaSourceCompletion {
        public JavadocCompletion(CompletionProvider provider, String replacementText) {
            super(provider, replacementText);
        }

        @Override
        public Icon getIcon() {
            return IconFactory.get().getIcon("javadocItemIcon");
        }

        @Override
        public void rendererText(Graphics g, int x, int y, boolean selected) {
            g.drawString(this.getReplacementText(), x, y);
        }
    }
}

