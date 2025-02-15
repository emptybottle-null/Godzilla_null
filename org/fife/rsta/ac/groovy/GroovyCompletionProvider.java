/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.groovy;

import org.fife.rsta.ac.groovy.GroovySourceCompletionProvider;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.LanguageAwareCompletionProvider;

public class GroovyCompletionProvider
extends LanguageAwareCompletionProvider {
    public GroovyCompletionProvider() {
        this.setDefaultCompletionProvider(this.createCodeCompletionProvider());
        this.setStringCompletionProvider(this.createStringCompletionProvider());
        this.setCommentCompletionProvider(this.createCommentCompletionProvider());
    }

    protected void addShorthandCompletions(DefaultCompletionProvider codeCP) {
    }

    protected CompletionProvider createCodeCompletionProvider() {
        GroovySourceCompletionProvider cp = new GroovySourceCompletionProvider();
        return cp;
    }

    protected CompletionProvider createCommentCompletionProvider() {
        DefaultCompletionProvider cp = new DefaultCompletionProvider();
        cp.addCompletion(new BasicCompletion(cp, "TODO:", "A to-do reminder"));
        cp.addCompletion(new BasicCompletion(cp, "FIXME:", "A bug that needs to be fixed"));
        return cp;
    }

    protected CompletionProvider createStringCompletionProvider() {
        DefaultCompletionProvider cp = new DefaultCompletionProvider();
        cp.addCompletion(new BasicCompletion(cp, "%c", "char", "Prints a character"));
        cp.addCompletion(new BasicCompletion(cp, "%i", "signed int", "Prints a signed integer"));
        cp.addCompletion(new BasicCompletion(cp, "%f", "float", "Prints a float"));
        cp.addCompletion(new BasicCompletion(cp, "%s", "string", "Prints a string"));
        cp.addCompletion(new BasicCompletion(cp, "%u", "unsigned int", "Prints an unsigned integer"));
        cp.addCompletion(new BasicCompletion(cp, "\\n", "Newline", "Prints a newline"));
        return cp;
    }
}

