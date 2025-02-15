/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.Collections;
import java.util.Map;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.AbstractProcessingInstruction;
import org.dom4j.tree.DefaultProcessingInstruction;

public class FlyweightProcessingInstruction
extends AbstractProcessingInstruction {
    protected String target;
    protected String text;
    protected Map<String, String> values;

    public FlyweightProcessingInstruction() {
    }

    public FlyweightProcessingInstruction(String target, Map<String, String> values) {
        this.target = target;
        this.values = values;
        this.text = this.toString(values);
    }

    public FlyweightProcessingInstruction(String target, String text) {
        this.target = target;
        this.text = text;
        this.values = this.parseValues(text);
    }

    @Override
    public String getTarget() {
        return this.target;
    }

    @Override
    public void setTarget(String target) {
        throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getValue(String name) {
        String answer = this.values.get(name);
        if (answer == null) {
            return "";
        }
        return answer;
    }

    @Override
    public Map<String, String> getValues() {
        return Collections.unmodifiableMap(this.values);
    }

    @Override
    protected Node createXPathResult(Element parent) {
        return new DefaultProcessingInstruction(parent, this.getTarget(), this.getText());
    }
}

