/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation.parser;

import com.kitfox.svg.animation.parser.AnimTimeParser;
import com.kitfox.svg.animation.parser.AnimTimeParserTreeConstants;
import com.kitfox.svg.animation.parser.Node;

public class SimpleNode
implements Node {
    protected Node parent;
    protected Node[] children;
    protected int id;
    protected Object value;
    protected AnimTimeParser parser;

    public SimpleNode(int i) {
        this.id = i;
    }

    public SimpleNode(AnimTimeParser p, int i) {
        this(i);
        this.parser = p;
    }

    @Override
    public void jjtOpen() {
    }

    @Override
    public void jjtClose() {
    }

    @Override
    public void jjtSetParent(Node n) {
        this.parent = n;
    }

    @Override
    public Node jjtGetParent() {
        return this.parent;
    }

    @Override
    public void jjtAddChild(Node n, int i) {
        if (this.children == null) {
            this.children = new Node[i + 1];
        } else if (i >= this.children.length) {
            Node[] c = new Node[i + 1];
            System.arraycopy(this.children, 0, c, 0, this.children.length);
            this.children = c;
        }
        this.children[i] = n;
    }

    @Override
    public Node jjtGetChild(int i) {
        return this.children[i];
    }

    @Override
    public int jjtGetNumChildren() {
        return this.children == null ? 0 : this.children.length;
    }

    public void jjtSetValue(Object aValue) {
        this.value = aValue;
    }

    public Object jjtGetValue() {
        return this.value;
    }

    public String toString() {
        return AnimTimeParserTreeConstants.jjtNodeName[this.id];
    }

    public String toString(String prefix) {
        return prefix + this.toString();
    }

    protected void dumpString(String s) {
        System.out.println(s);
    }

    public void dump(String prefix) {
        this.dumpString(this.toString(prefix));
        if (this.children != null) {
            for (int i = 0; i < this.children.length; ++i) {
                SimpleNode n = (SimpleNode)this.children[i];
                if (n == null) continue;
                n.dump(prefix + " ");
            }
        }
    }

    @Override
    public int getId() {
        return this.id;
    }
}

