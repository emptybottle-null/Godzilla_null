/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;

public class VariableCompletion
extends BasicCompletion {
    private String type;
    private String definedIn;

    public VariableCompletion(CompletionProvider provider, String name, String type) {
        super(provider, name);
        this.type = type;
    }

    protected void addDefinitionString(StringBuilder sb) {
        sb.append("<html><b>").append(this.getDefinitionString()).append("</b>");
    }

    public String getDefinitionString() {
        StringBuilder sb = new StringBuilder();
        if (this.type != null) {
            sb.append(this.type).append(' ');
        }
        sb.append(this.getName());
        return sb.toString();
    }

    public String getDefinedIn() {
        return this.definedIn;
    }

    public String getName() {
        return this.getReplacementText();
    }

    @Override
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        this.addDefinitionString(sb);
        this.possiblyAddDescription(sb);
        this.possiblyAddDefinedIn(sb);
        return sb.toString();
    }

    @Override
    public String getToolTipText() {
        return this.getDefinitionString();
    }

    public String getType() {
        return this.type;
    }

    protected void possiblyAddDefinedIn(StringBuilder sb) {
        if (this.definedIn != null) {
            sb.append("<hr>Defined in:");
            sb.append(" <em>").append(this.definedIn).append("</em>");
        }
    }

    protected boolean possiblyAddDescription(StringBuilder sb) {
        if (this.getShortDescription() != null) {
            sb.append("<hr><br>");
            sb.append(this.getShortDescription());
            sb.append("<br><br><br>");
            return true;
        }
        return false;
    }

    public void setDefinedIn(String definedIn) {
        this.definedIn = definedIn;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

