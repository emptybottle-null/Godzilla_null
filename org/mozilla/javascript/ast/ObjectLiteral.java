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
import org.mozilla.javascript.ast.ObjectProperty;

public class ObjectLiteral
extends AstNode
implements DestructuringForm {
    private static final List<ObjectProperty> NO_ELEMS = Collections.unmodifiableList(new ArrayList());
    private List<ObjectProperty> elements;
    boolean isDestructuring;

    public ObjectLiteral() {
        this.type = 66;
    }

    public ObjectLiteral(int pos) {
        super(pos);
        this.type = 66;
    }

    public ObjectLiteral(int pos, int len) {
        super(pos, len);
        this.type = 66;
    }

    public List<ObjectProperty> getElements() {
        return this.elements != null ? this.elements : NO_ELEMS;
    }

    public void setElements(List<ObjectProperty> elements) {
        if (elements == null) {
            this.elements = null;
        } else {
            if (this.elements != null) {
                this.elements.clear();
            }
            for (ObjectProperty o : elements) {
                this.addElement(o);
            }
        }
    }

    public void addElement(ObjectProperty element) {
        this.assertNotNull(element);
        if (this.elements == null) {
            this.elements = new ArrayList<ObjectProperty>();
        }
        this.elements.add(element);
        element.setParent(this);
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
        sb.append("{");
        if (this.elements != null) {
            this.printList(this.elements, sb);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            for (ObjectProperty prop : this.getElements()) {
                prop.visit(v);
            }
        }
    }
}

