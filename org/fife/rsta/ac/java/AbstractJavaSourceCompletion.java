/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.java.JavaSourceCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

public abstract class AbstractJavaSourceCompletion
extends BasicCompletion
implements JavaSourceCompletion {
    public AbstractJavaSourceCompletion(CompletionProvider provider, String replacementText) {
        super(provider, replacementText);
    }

    @Override
    public int compareTo(Completion c2) {
        int rc = -1;
        if (c2 == this) {
            rc = 0;
        } else if (c2 != null && (rc = this.toString().compareToIgnoreCase(c2.toString())) == 0) {
            String clazz1 = this.getClass().getName();
            clazz1 = clazz1.substring(clazz1.lastIndexOf(46));
            String clazz2 = c2.getClass().getName();
            clazz2 = clazz2.substring(clazz2.lastIndexOf(46));
            rc = clazz1.compareTo(clazz2);
        }
        return rc;
    }

    @Override
    public String getAlreadyEntered(JTextComponent comp) {
        String temp = this.getProvider().getAlreadyEnteredText(comp);
        int lastDot = temp.lastIndexOf(46);
        if (lastDot > -1) {
            temp = temp.substring(lastDot + 1);
        }
        return temp;
    }
}

