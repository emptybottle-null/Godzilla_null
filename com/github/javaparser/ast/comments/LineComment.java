/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.comments;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.LineCommentMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public class LineComment
extends Comment {
    public LineComment() {
        this(null, "empty");
    }

    @AllFieldsConstructor
    public LineComment(String content) {
        this(null, content);
    }

    public LineComment(TokenRange tokenRange, String content) {
        super(tokenRange, content);
        this.customInitialization();
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public boolean isLineComment() {
        return true;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public LineComment clone() {
        return (LineComment)this.accept(new CloneVisitor(), null);
    }

    @Override
    public LineCommentMetaModel getMetaModel() {
        return JavaParserMetaModel.lineCommentMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public LineComment asLineComment() {
        return this;
    }

    @Override
    public void ifLineComment(Consumer<LineComment> action) {
        action.accept(this);
    }

    @Override
    public Optional<LineComment> toLineComment() {
        return Optional.of(this);
    }
}

