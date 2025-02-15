/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.Name;

public abstract class XmlRef
extends AstNode {
    protected Name namespace;
    protected int atPos = -1;
    protected int colonPos = -1;

    public XmlRef() {
    }

    public XmlRef(int pos) {
        super(pos);
    }

    public XmlRef(int pos, int len) {
        super(pos, len);
    }

    public Name getNamespace() {
        return this.namespace;
    }

    public void setNamespace(Name namespace) {
        this.namespace = namespace;
        if (namespace != null) {
            namespace.setParent(this);
        }
    }

    public boolean isAttributeAccess() {
        return this.atPos >= 0;
    }

    public int getAtPos() {
        return this.atPos;
    }

    public void setAtPos(int atPos) {
        this.atPos = atPos;
    }

    public int getColonPos() {
        return this.colonPos;
    }

    public void setColonPos(int colonPos) {
        this.colonPos = colonPos;
    }
}

