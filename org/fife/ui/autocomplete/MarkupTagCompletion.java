/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.util.ArrayList;
import java.util.List;
import org.fife.ui.autocomplete.AbstractCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;

public class MarkupTagCompletion
extends AbstractCompletion {
    private String name;
    private String desc;
    private String definedIn;
    private List<ParameterizedCompletion.Parameter> attrs;

    public MarkupTagCompletion(CompletionProvider provider, String name) {
        super(provider);
        this.name = name;
    }

    protected void addAttributes(StringBuilder sb) {
        int attrCount = this.getAttributeCount();
        if (attrCount > 0) {
            sb.append("<b>Attributes:</b><br>");
            sb.append("<center><table width='90%'><tr><td>");
            for (int i = 0; i < attrCount; ++i) {
                ParameterizedCompletion.Parameter attr = this.getAttribute(i);
                sb.append("&nbsp;&nbsp;&nbsp;<b>");
                sb.append(attr.getName() != null ? attr.getName() : attr.getType());
                sb.append("</b>&nbsp;");
                String desc = attr.getDescription();
                if (desc != null) {
                    sb.append(desc);
                }
                sb.append("<br>");
            }
            sb.append("</td></tr></table></center><br><br>");
        }
    }

    protected void addDefinitionString(StringBuilder sb) {
        sb.append("<html><b>").append(this.name).append("</b>");
    }

    public List<ParameterizedCompletion.Parameter> getAttributes() {
        return this.attrs;
    }

    public ParameterizedCompletion.Parameter getAttribute(int index) {
        return this.attrs.get(index);
    }

    public int getAttributeCount() {
        return this.attrs == null ? 0 : this.attrs.size();
    }

    public String getDefinedIn() {
        return this.definedIn;
    }

    public String getDescription() {
        return this.desc;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getReplacementText() {
        return this.getName();
    }

    @Override
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        this.addDefinitionString(sb);
        this.possiblyAddDescription(sb);
        this.addAttributes(sb);
        this.possiblyAddDefinedIn(sb);
        return sb.toString();
    }

    protected void possiblyAddDefinedIn(StringBuilder sb) {
        if (this.definedIn != null) {
            sb.append("<hr>Defined in:");
            sb.append(" <em>").append(this.definedIn).append("</em>");
        }
    }

    protected void possiblyAddDescription(StringBuilder sb) {
        if (this.desc != null) {
            sb.append("<hr><br>");
            sb.append(this.desc);
            sb.append("<br><br><br>");
        }
    }

    public void setDefinedIn(String definedIn) {
        this.definedIn = definedIn;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

    public void setAttributes(List<? extends ParameterizedCompletion.Parameter> attrs) {
        this.attrs = new ArrayList<ParameterizedCompletion.Parameter>(attrs);
    }
}

