/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.comments;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CommentsCollection {
    private final TreeSet<Comment> comments = new TreeSet(Node.NODE_BY_BEGIN_POSITION);

    public CommentsCollection() {
    }

    public CommentsCollection(Collection<Comment> commentsToCopy) {
        this.comments.addAll(commentsToCopy);
    }

    public Set<LineComment> getLineComments() {
        return this.comments.stream().filter(comment -> comment instanceof LineComment).map(comment -> (LineComment)comment).collect(Collectors.toCollection(() -> new TreeSet(Node.NODE_BY_BEGIN_POSITION)));
    }

    public Set<BlockComment> getBlockComments() {
        return this.comments.stream().filter(comment -> comment instanceof BlockComment).map(comment -> (BlockComment)comment).collect(Collectors.toCollection(() -> new TreeSet(Node.NODE_BY_BEGIN_POSITION)));
    }

    public Set<JavadocComment> getJavadocComments() {
        return this.comments.stream().filter(comment -> comment instanceof JavadocComment).map(comment -> (JavadocComment)comment).collect(Collectors.toCollection(() -> new TreeSet(Node.NODE_BY_BEGIN_POSITION)));
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public boolean contains(Comment comment) {
        if (!comment.getRange().isPresent()) {
            return false;
        }
        Range commentRange = comment.getRange().get();
        for (Comment c : this.getComments()) {
            if (!c.getRange().isPresent()) {
                return false;
            }
            Range cRange = c.getRange().get();
            if (!cRange.begin.equals(commentRange.begin) || cRange.end.line != commentRange.end.line || Math.abs(cRange.end.column - commentRange.end.column) >= 2) continue;
            return true;
        }
        return false;
    }

    public TreeSet<Comment> getComments() {
        return this.comments;
    }

    public int size() {
        return this.comments.size();
    }

    public CommentsCollection minus(CommentsCollection other) {
        CommentsCollection result = new CommentsCollection();
        result.comments.addAll(this.comments.stream().filter(comment -> !other.contains((Comment)comment)).collect(Collectors.toList()));
        return result;
    }

    public CommentsCollection copy() {
        return new CommentsCollection(this.comments);
    }
}

