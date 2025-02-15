/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import org.dom4j.CharacterData;
import org.dom4j.Element;
import org.dom4j.tree.AbstractNode;

public abstract class AbstractCharacterData
extends AbstractNode
implements CharacterData {
    @Override
    public String getPath(Element context) {
        Element parent = this.getParent();
        return parent != null && parent != context ? parent.getPath(context) + "/text()" : "text()";
    }

    @Override
    public String getUniquePath(Element context) {
        Element parent = this.getParent();
        return parent != null && parent != context ? parent.getUniquePath(context) + "/text()" : "text()";
    }

    @Override
    public void appendText(String text) {
        this.setText(this.getText() + text);
    }
}

