/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import org.dom4j.Comment;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.AbstractComment;
import org.dom4j.tree.DefaultComment;

public class FlyweightComment
extends AbstractComment
implements Comment {
    protected String text;

    public FlyweightComment(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    protected Node createXPathResult(Element parent) {
        return new DefaultComment(parent, this.getText());
    }
}

