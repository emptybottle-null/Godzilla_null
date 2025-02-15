/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.Range;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.utils.PositionUtils;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

class CommentsInserter {
    private final ParserConfiguration configuration;

    CommentsInserter(ParserConfiguration configuration) {
        this.configuration = configuration;
    }

    private void insertComments(CompilationUnit cu, TreeSet<Comment> comments) {
        if (comments.isEmpty()) {
            return;
        }
        List<Node> children = cu.getChildNodes();
        Comment firstComment = comments.iterator().next();
        if (cu.getPackageDeclaration().isPresent() && (children.isEmpty() || PositionUtils.areInOrder(firstComment, cu.getPackageDeclaration().get()))) {
            cu.setComment(firstComment);
            comments.remove(firstComment);
        }
    }

    void insertComments(Node node, TreeSet<Comment> commentsToAttribute) {
        if (commentsToAttribute.isEmpty()) {
            return;
        }
        if (node instanceof CompilationUnit) {
            this.insertComments((CompilationUnit)node, commentsToAttribute);
        }
        List<Node> children = node.getChildNodes().stream().filter(n -> !(n instanceof Modifier)).collect(Collectors.toList());
        boolean attributeToAnnotation = !this.configuration.isIgnoreAnnotationsWhenAttributingComments();
        for (Node child : children) {
            TreeSet commentsInsideChild = new TreeSet(Node.NODE_BY_BEGIN_POSITION);
            commentsInsideChild.addAll(commentsToAttribute.stream().filter(comment -> comment.getRange().isPresent()).filter(comment -> PositionUtils.nodeContains(child, comment, !attributeToAnnotation)).collect(Collectors.toList()));
            commentsToAttribute.removeAll(commentsInsideChild);
            this.insertComments(child, commentsInsideChild);
        }
        this.attributeLineCommentsOnSameLine(commentsToAttribute, children);
        if (!commentsToAttribute.isEmpty() && this.commentIsOnNextLine(node, commentsToAttribute.first())) {
            node.setComment(commentsToAttribute.first());
            commentsToAttribute.remove(commentsToAttribute.first());
        }
        Comment previousComment = null;
        LinkedList<Comment> attributedComments = new LinkedList<Comment>();
        LinkedList<Node> childrenAndComments = new LinkedList<Node>();
        childrenAndComments.addAll(children);
        commentsToAttribute.removeAll(attributedComments);
        childrenAndComments.addAll(commentsToAttribute);
        PositionUtils.sortByBeginPosition(childrenAndComments, this.configuration.isIgnoreAnnotationsWhenAttributingComments());
        for (Node thing : childrenAndComments) {
            if (thing instanceof Comment) {
                previousComment = (Comment)thing;
                if (previousComment.isOrphan()) continue;
                previousComment = null;
                continue;
            }
            if (previousComment == null || thing.getComment().isPresent() || this.configuration.isDoNotAssignCommentsPrecedingEmptyLines() && this.thereAreLinesBetween(previousComment, thing)) continue;
            thing.setComment(previousComment);
            attributedComments.add(previousComment);
            previousComment = null;
        }
        commentsToAttribute.removeAll(attributedComments);
        for (Comment c : commentsToAttribute) {
            if (!c.isOrphan()) continue;
            node.addOrphanComment(c);
        }
    }

    private void attributeLineCommentsOnSameLine(TreeSet<Comment> commentsToAttribute, List<Node> children) {
        LinkedList attributedComments = new LinkedList();
        commentsToAttribute.stream().filter(comment -> comment.getRange().isPresent()).filter(Comment::isLineComment).forEach(comment -> children.stream().filter(child -> child.getRange().isPresent()).forEach(child -> {
            Range commentRange = comment.getRange().get();
            Range childRange = child.getRange().get();
            if (childRange.end.line == commentRange.begin.line && this.attributeLineCommentToNodeOrChild((Node)child, comment.asLineComment())) {
                attributedComments.add(comment);
            }
        }));
        commentsToAttribute.removeAll(attributedComments);
    }

    private boolean attributeLineCommentToNodeOrChild(Node node, LineComment lineComment) {
        if (!node.getRange().isPresent() || !lineComment.getRange().isPresent()) {
            return false;
        }
        if (node.getBegin().get().line == lineComment.getBegin().get().line && !node.getComment().isPresent()) {
            if (!(node instanceof Comment)) {
                node.setComment(lineComment);
            }
            return true;
        }
        LinkedList<Node> children = new LinkedList<Node>();
        children.addAll(node.getChildNodes());
        PositionUtils.sortByBeginPosition(children);
        Collections.reverse(children);
        for (Node child : children) {
            if (!this.attributeLineCommentToNodeOrChild(child, lineComment)) continue;
            return true;
        }
        return false;
    }

    private boolean thereAreLinesBetween(Node a, Node b) {
        if (!a.getRange().isPresent() || !b.getRange().isPresent()) {
            return true;
        }
        if (!PositionUtils.areInOrder(a, b)) {
            return this.thereAreLinesBetween(b, a);
        }
        int endOfA = a.getEnd().get().line;
        return b.getBegin().get().line > endOfA + 1;
    }

    private boolean commentIsOnNextLine(Node a, Comment c) {
        if (!c.getRange().isPresent() || !a.getRange().isPresent()) {
            return false;
        }
        return c.getRange().get().end.line + 1 == a.getRange().get().begin.line;
    }
}

