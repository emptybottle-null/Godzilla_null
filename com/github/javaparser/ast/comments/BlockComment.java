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
import com.github.javaparser.metamodel.BlockCommentMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public class BlockComment
extends Comment {
    public BlockComment() {
        this(null, "empty");
    }

    @AllFieldsConstructor
    public BlockComment(String content) {
        this(null, content);
    }

    public BlockComment(TokenRange tokenRange, String content) {
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
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public BlockComment clone() {
        return (BlockComment)this.accept(new CloneVisitor(), null);
    }

    @Override
    public BlockCommentMetaModel getMetaModel() {
        return JavaParserMetaModel.blockCommentMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isBlockComment() {
        return true;
    }

    @Override
    public BlockComment asBlockComment() {
        return this;
    }

    @Override
    public void ifBlockComment(Consumer<BlockComment> action) {
        action.accept(this);
    }

    @Override
    public Optional<BlockComment> toBlockComment() {
        return Optional.of(this);
    }
}

