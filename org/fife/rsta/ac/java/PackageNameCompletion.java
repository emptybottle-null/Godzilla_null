/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Graphics;
import javax.swing.Icon;
import org.fife.rsta.ac.java.AbstractJavaSourceCompletion;
import org.fife.rsta.ac.java.IconFactory;
import org.fife.ui.autocomplete.CompletionProvider;

class PackageNameCompletion
extends AbstractJavaSourceCompletion {
    public PackageNameCompletion(CompletionProvider provider, String text, String alreadyEntered) {
        super(provider, text.substring(text.lastIndexOf(46) + 1));
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PackageNameCompletion && ((PackageNameCompletion)obj).getReplacementText().equals(this.getReplacementText());
    }

    @Override
    public Icon getIcon() {
        return IconFactory.get().getIcon("packageIcon");
    }

    public int hashCode() {
        return this.getReplacementText().hashCode();
    }

    @Override
    public void rendererText(Graphics g, int x, int y, boolean selected) {
        g.drawString(this.getInputText(), x, y);
    }
}

