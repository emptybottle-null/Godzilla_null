/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.Map;
import org.dom4j.Element;
import org.dom4j.tree.FlyweightProcessingInstruction;

public class DefaultProcessingInstruction
extends FlyweightProcessingInstruction {
    private Element parent;

    public DefaultProcessingInstruction(String target, Map<String, String> values) {
        super(target, values);
    }

    public DefaultProcessingInstruction(String target, String values) {
        super(target, values);
    }

    public DefaultProcessingInstruction(Element parent, String target, String values) {
        super(target, values);
        this.parent = parent;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public void setText(String text) {
        this.text = text;
        this.values = this.parseValues(text);
    }

    @Override
    public void setValues(Map<String, String> values) {
        this.values = values;
        this.text = this.toString(values);
    }

    @Override
    public void setValue(String name, String value) {
        this.values.put(name, value);
    }

    @Override
    public Element getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Element parent) {
        this.parent = parent;
    }

    @Override
    public boolean supportsParent() {
        return true;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }
}

