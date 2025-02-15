/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import org.apache.xmlbeans.impl.jam.internal.elements.ElementImpl;
import org.apache.xmlbeans.impl.jam.mutable.MComment;
import org.apache.xmlbeans.impl.jam.visitor.JVisitor;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public final class CommentImpl
extends ElementImpl
implements MComment {
    private String mText = null;

    CommentImpl(ElementImpl parent) {
        super(parent);
    }

    @Override
    public void setText(String text) {
        this.mText = text;
    }

    @Override
    public String getText() {
        return this.mText == null ? "" : this.mText;
    }

    @Override
    public void accept(MVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(JVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getQualifiedName() {
        return this.getParent().getQualifiedName() + ".{comment}";
    }
}

