/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Graphics;
import javax.swing.Icon;
import org.fife.rsta.ac.java.AbstractJavaSourceCompletion;
import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.rjc.ast.LocalVariable;
import org.fife.ui.autocomplete.CompletionProvider;

class LocalVariableCompletion
extends AbstractJavaSourceCompletion {
    private LocalVariable localVar;
    private static final int RELEVANCE = 4;

    public LocalVariableCompletion(CompletionProvider provider, LocalVariable localVar) {
        super(provider, localVar.getName());
        this.localVar = localVar;
        this.setRelevance(4);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LocalVariableCompletion && ((LocalVariableCompletion)obj).getReplacementText().equals(this.getReplacementText());
    }

    @Override
    public Icon getIcon() {
        return IconFactory.get().getIcon("localVariableIcon");
    }

    @Override
    public String getToolTipText() {
        return this.localVar.getType() + " " + this.localVar.getName();
    }

    public int hashCode() {
        return this.getReplacementText().hashCode();
    }

    @Override
    public void rendererText(Graphics g, int x, int y, boolean selected) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.localVar.getName());
        sb.append(" : ");
        sb.append(this.localVar.getType());
        g.drawString(sb.toString(), x, y);
    }
}

