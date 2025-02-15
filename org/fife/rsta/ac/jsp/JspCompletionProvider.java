/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.fife.rsta.ac.html.AttributeCompletion;
import org.fife.rsta.ac.html.HtmlCompletionProvider;
import org.fife.rsta.ac.jsp.TldElement;
import org.fife.rsta.ac.jsp.TldFile;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.MarkupTagCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletion;

public class JspCompletionProvider
extends HtmlCompletionProvider {
    private Map<String, TldFile> prefixToTld = new HashMap<String, TldFile>();

    public JspCompletionProvider() {
        this.setAutoActivationRules(false, "<:");
    }

    @Override
    protected List<AttributeCompletion> getAttributeCompletionsForTag(String tagName) {
        int colon;
        List<AttributeCompletion> list = super.getAttributeCompletionsForTag(tagName);
        if (list == null && (colon = tagName.indexOf(58)) > -1) {
            List<ParameterizedCompletion.Parameter> attrs;
            String prefix = tagName.substring(0, colon);
            tagName = tagName.substring(colon + 1);
            TldFile tldFile = this.prefixToTld.get(prefix);
            if (tldFile != null && (attrs = tldFile.getAttributesForTag(tagName)) != null && attrs.size() > -1) {
                list = new ArrayList<AttributeCompletion>();
                for (ParameterizedCompletion.Parameter param : attrs) {
                    list.add(new AttributeCompletion((CompletionProvider)this, param));
                }
            }
        }
        return list;
    }

    @Override
    protected List<Completion> getTagCompletions() {
        ArrayList<Completion> completions = new ArrayList<Completion>(super.getTagCompletions());
        for (Map.Entry<String, TldFile> entry : this.prefixToTld.entrySet()) {
            String prefix = entry.getKey();
            TldFile tld = entry.getValue();
            for (int j = 0; j < tld.getElementCount(); ++j) {
                TldElement elem = tld.getElement(j);
                MarkupTagCompletion mtc = new MarkupTagCompletion((CompletionProvider)this, prefix + ":" + elem.getName());
                mtc.setDescription(elem.getDescription());
                completions.add(mtc);
            }
        }
        Collections.sort(completions);
        return completions;
    }

    @Override
    protected void initCompletions() {
        super.initCompletions();
        try {
            this.loadFromXML("data/jsp.xml");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        this.completions.sort(this.comparator);
    }

    @Override
    protected boolean isValidChar(char ch) {
        return super.isValidChar(ch) || ch == ':';
    }
}

