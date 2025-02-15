/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.xml.tree;

import javax.swing.text.Position;
import org.fife.rsta.ac.SourceTreeNode;

public class XmlTreeNode
extends SourceTreeNode {
    private String mainAttr;
    private Position offset;
    private Position endOffset;

    public XmlTreeNode(String name) {
        super(name);
    }

    public boolean containsOffset(int offs) {
        return this.offset != null && this.endOffset != null && offs >= this.offset.getOffset() && offs <= this.endOffset.getOffset();
    }

    public String getElement() {
        return (String)this.getUserObject();
    }

    public int getEndOffset() {
        return this.endOffset != null ? this.endOffset.getOffset() : Integer.MAX_VALUE;
    }

    public String getMainAttr() {
        return this.mainAttr;
    }

    public int getStartOffset() {
        return this.offset != null ? this.offset.getOffset() : -1;
    }

    public void setEndOffset(Position pos) {
        this.endOffset = pos;
    }

    public void setMainAttribute(String attr) {
        this.mainAttr = attr;
    }

    public void setStartOffset(Position pos) {
        this.offset = pos;
    }

    @Override
    public String toString() {
        String text = this.getElement();
        if (this.mainAttr != null) {
            text = text + " " + this.mainAttr;
        }
        return text;
    }
}

