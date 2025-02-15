/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.comments;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.JavadocCommentMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public class JavadocComment
extends Comment {
    public JavadocComment() {
        this(null, "empty");
    }

    @AllFieldsConstructor
    public JavadocComment(String content) {
        this(null, content);
    }

    public JavadocComment(TokenRange tokenRange, String content) {
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

    public Javadoc parse() {
        return StaticJavaParser.parseJavadoc(this.getContent());
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public JavadocComment clone() {
        return (JavadocComment)this.accept(new CloneVisitor(), null);
    }

    @Override
    public JavadocCommentMetaModel getMetaModel() {
        return JavaParserMetaModel.javadocCommentMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isJavadocComment() {
        return true;
    }

    @Override
    public JavadocComment asJavadocComment() {
        return this;
    }

    @Override
    public void ifJavadocComment(Consumer<JavadocComment> action) {
        action.accept(this);
    }

    @Override
    public Optional<JavadocComment> toJavadocComment() {
        return Optional.of(this);
    }
}

