/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.javadoc.Javadoc;
import java.util.Optional;

public interface NodeWithJavadoc<N extends Node> {
    public Optional<Comment> getComment();

    public Node setComment(Comment var1);

    default public Optional<JavadocComment> getJavadocComment() {
        return this.getComment().filter(comment -> comment instanceof JavadocComment).map(comment -> (JavadocComment)comment);
    }

    default public Optional<Javadoc> getJavadoc() {
        return this.getJavadocComment().map(JavadocComment::parse);
    }

    default public N setJavadocComment(String comment) {
        return this.setJavadocComment(new JavadocComment(comment));
    }

    default public N setJavadocComment(JavadocComment comment) {
        this.setComment(comment);
        return (N)((Node)((Object)this));
    }

    default public N setJavadocComment(String indentation, Javadoc javadoc) {
        return this.setJavadocComment(javadoc.toComment(indentation));
    }

    default public N setJavadocComment(Javadoc javadoc) {
        return this.setJavadocComment(javadoc.toComment());
    }

    default public boolean removeJavaDocComment() {
        return this.hasJavaDocComment() && this.getComment().get().remove();
    }

    default public boolean hasJavaDocComment() {
        return this.getComment().isPresent() && this.getComment().get() instanceof JavadocComment;
    }
}

