/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.c;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.LanguageAwareCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

public class CCompletionProvider
extends LanguageAwareCompletionProvider {
    public CCompletionProvider() {
        this.setDefaultCompletionProvider(this.createCodeCompletionProvider());
        this.setStringCompletionProvider(this.createStringCompletionProvider());
        this.setCommentCompletionProvider(this.createCommentCompletionProvider());
    }

    protected void addShorthandCompletions(DefaultCompletionProvider codeCP) {
        codeCP.addCompletion(new ShorthandCompletion(codeCP, "main", "int main(int argc, char **argv)"));
        codeCP.setAutoActivationRules(true, null);
    }

    protected CompletionProvider createCodeCompletionProvider() {
        DefaultCompletionProvider cp = new DefaultCompletionProvider();
        this.loadCodeCompletionsFromXml(cp);
        this.addShorthandCompletions(cp);
        cp.setAutoActivationRules(true, null);
        return cp;
    }

    protected CompletionProvider createCommentCompletionProvider() {
        DefaultCompletionProvider cp = new DefaultCompletionProvider();
        cp.addCompletion(new BasicCompletion(cp, "TODO:", "A to-do reminder"));
        cp.addCompletion(new BasicCompletion(cp, "FIXME:", "A bug that needs to be fixed"));
        cp.setAutoActivationRules(true, null);
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

    protected String getXmlResource() {
        return "data/c.xml";
    }

    protected void loadCodeCompletionsFromXml(DefaultCompletionProvider cp) {
        ClassLoader cl = this.getClass().getClassLoader();
        String res = this.getXmlResource();
        if (res != null) {
            InputStream in = cl.getResourceAsStream(res);
            try {
                if (in != null) {
                    cp.loadFromXML(in);
                    in.close();
                } else {
                    cp.loadFromXML(new File(res));
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}

