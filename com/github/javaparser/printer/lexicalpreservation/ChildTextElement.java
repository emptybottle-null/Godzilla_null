/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.printer.lexicalpreservation.NodeText;
import com.github.javaparser.printer.lexicalpreservation.TextElement;
import java.util.Optional;

class ChildTextElement
extends TextElement {
    private final Node child;

    ChildTextElement(Node child) {
        this.child = child;
    }

    @Override
    String expand() {
        return LexicalPreservingPrinter.print(this.child);
    }

    Node getChild() {
        return this.child;
    }

    @Override
    boolean isToken(int tokenKind) {
        return false;
    }

    @Override
    boolean isNode(Node node) {
        return node == this.child;
    }

    NodeText getNodeTextForWrappedNode() {
        return LexicalPreservingPrinter.getOrCreateNodeText(this.child);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ChildTextElement that = (ChildTextElement)o;
        return this.child.equals(that.child);
    }

    public int hashCode() {
        return this.child.hashCode();
    }

    public String toString() {
        return "ChildTextElement{" + this.child + '}';
    }

    @Override
    public boolean isWhiteSpace() {
        return false;
    }

    @Override
    public boolean isSpaceOrTab() {
        return false;
    }

    @Override
    public boolean isNewline() {
        return false;
    }

    @Override
    public boolean isComment() {
        return this.child instanceof Comment;
    }

    @Override
    public boolean isSeparator() {
        return false;
    }

    @Override
    public boolean isIdentifier() {
        return false;
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public boolean isLiteral() {
        return false;
    }

    @Override
    public boolean isChildOfClass(Class<? extends Node> nodeClass) {
        return nodeClass.isInstance(this.child);
    }

    @Override
    Optional<Range> getRange() {
        return this.child.getRange();
    }
}

