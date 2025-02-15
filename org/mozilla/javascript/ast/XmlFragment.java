/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;

public abstract class XmlFragment
extends AstNode {
    public XmlFragment() {
        this.type = 145;
    }

    public XmlFragment(int pos) {
        super(pos);
        this.type = 145;
    }

    public XmlFragment(int pos, int len) {
        super(pos, len);
        this.type = 145;
    }
}

