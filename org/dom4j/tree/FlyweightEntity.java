/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.AbstractEntity;
import org.dom4j.tree.DefaultEntity;

public class FlyweightEntity
extends AbstractEntity {
    protected String name;
    protected String text;

    protected FlyweightEntity() {
    }

    public FlyweightEntity(String name) {
        this.name = name;
    }

    public FlyweightEntity(String name, String text) {
        this.name = name;
        this.text = text;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        if (this.text == null) {
            throw new UnsupportedOperationException("This Entity is read-only. It cannot be modified");
        }
        this.text = text;
    }

    @Override
    protected Node createXPathResult(Element parent) {
        return new DefaultEntity(parent, this.getName(), this.getText());
    }
}

