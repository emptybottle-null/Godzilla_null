/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.DestructuringForm;
import org.mozilla.javascript.ast.NodeVisitor;

public class ArrayLiteral
extends AstNode
implements DestructuringForm {
    private static final List<AstNode> NO_ELEMS = Collections.unmodifiableList(new ArrayList());
    private List<AstNode> elements;
    private int destructuringLength;
    private int skipCount;
    private boolean isDestructuring;

    public ArrayLiteral() {
        this.type = 65;
    }

    public ArrayLiteral(int pos) {
        super(pos);
        this.type = 65;
    }

    public ArrayLiteral(int pos, int len) {
        super(pos, len);
        this.type = 65;
    }

    public List<AstNode> getElements() {
        return this.elements != null ? this.elements : NO_ELEMS;
    }

    public void setElements(List<AstNode> elements) {
        if (elements == null) {
            this.elements = null;
        } else {
            if (this.elements != null) {
                this.elements.clear();
            }
            for (AstNode e : elements) {
                this.addElement(e);
            }
        }
    }

    public void addElement(AstNode element) {
        this.assertNotNull(element);
        if (this.elements == null) {
            this.elements = new ArrayList<AstNode>();
        }
        this.elements.add(element);
        element.setParent(this);
    }

    public int getSize() {
        return this.elements == null ? 0 : this.elements.size();
    }

    public AstNode getElement(int index) {
        if (this.elements == null) {
            throw new IndexOutOfBoundsException("no elements");
        }
        return this.elements.get(index);
    }

    public int getDestructuringLength() {
        return this.destructuringLength;
    }

    public void setDestructuringLength(int destructuringLength) {
        this.destructuringLength = destructuringLength;
    }

    public int getSkipCount() {
        return this.skipCount;
    }

    public void setSkipCount(int count) {
        this.skipCount = count;
    }

    @Override
    public void setIsDestructuring(boolean destructuring) {
        this.isDestructuring = destructuring;
    }

    @Override
    public boolean isDestructuring() {
        return this.isDestructuring;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append("[");
        if (this.elements != null) {
            this.printList(this.elements, sb);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            for (AstNode e : this.getElements()) {
                e.visit(v);
            }
        }
    }
}

