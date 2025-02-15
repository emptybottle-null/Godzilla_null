/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.comments;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.CommentMetaModel;
import com.github.javaparser.metamodel.InternalProperty;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class Comment
extends Node {
    private String content;
    @InternalProperty
    private Node commentedNode;

    @AllFieldsConstructor
    public Comment(String content) {
        this(null, content);
    }

    public Comment(TokenRange tokenRange, String content) {
        super(tokenRange);
        this.setContent(content);
        this.customInitialization();
    }

    public String getContent() {
        return this.content;
    }

    public Comment setContent(String content) {
        Utils.assertNotNull(content);
        if (content == this.content) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.CONTENT, this.content, content);
        this.content = content;
        return this;
    }

    public boolean isLineComment() {
        return false;
    }

    public LineComment asLineComment() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not LineComment, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<Node> getCommentedNode() {
        return Optional.ofNullable(this.commentedNode);
    }

    public Comment setCommentedNode(Node commentedNode) {
        this.notifyPropertyChange(ObservableProperty.COMMENTED_NODE, this.commentedNode, commentedNode);
        if (commentedNode == null) {
            this.commentedNode = null;
            return this;
        }
        if (commentedNode == this) {
            throw new IllegalArgumentException();
        }
        if (commentedNode instanceof Comment) {
            throw new IllegalArgumentException();
        }
        this.commentedNode = commentedNode;
        return this;
    }

    public boolean isOrphan() {
        return this.commentedNode == null;
    }

    @Override
    public Node setComment(Comment comment) {
        if (comment != null) {
            throw new IllegalArgumentException("A comment cannot be commented.");
        }
        return super.setComment(comment);
    }

    @Override
    public boolean remove() {
        if (this.commentedNode != null) {
            this.commentedNode.setComment(null);
            return true;
        }
        if (this.getParentNode().isPresent()) {
            return this.getParentNode().get().removeOrphanComment(this);
        }
        return false;
    }

    @Override
    public Node findRootNode() {
        Node n = this.getCommentedNode().orElse(this);
        while (n.getParentNode().isPresent()) {
            n = n.getParentNode().get();
        }
        return n;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public Comment clone() {
        return (Comment)this.accept(new CloneVisitor(), null);
    }

    @Override
    public CommentMetaModel getMetaModel() {
        return JavaParserMetaModel.commentMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    public boolean isBlockComment() {
        return false;
    }

    public BlockComment asBlockComment() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not BlockComment, it is %s", this, this.getClass().getSimpleName()));
    }

    public boolean isJavadocComment() {
        return false;
    }

    public JavadocComment asJavadocComment() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not JavadocComment, it is %s", this, this.getClass().getSimpleName()));
    }

    public void ifBlockComment(Consumer<BlockComment> action) {
    }

    public void ifJavadocComment(Consumer<JavadocComment> action) {
    }

    public void ifLineComment(Consumer<LineComment> action) {
    }

    public Optional<BlockComment> toBlockComment() {
        return Optional.empty();
    }

    public Optional<JavadocComment> toJavadocComment() {
        return Optional.empty();
    }

    public Optional<LineComment> toLineComment() {
        return Optional.empty();
    }
}

