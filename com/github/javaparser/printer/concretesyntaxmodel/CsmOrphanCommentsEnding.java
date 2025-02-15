/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmComment;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.utils.PositionUtils;
import java.util.LinkedList;

public class CsmOrphanCommentsEnding
implements CsmElement {
    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        LinkedList<Node> everything = new LinkedList<Node>();
        everything.addAll(node.getChildNodes());
        PositionUtils.sortByBeginPosition(everything);
        if (everything.isEmpty()) {
            return;
        }
        int commentsAtEnd = 0;
        boolean findingComments = true;
        while (findingComments && commentsAtEnd < everything.size()) {
            Node last = (Node)everything.get(everything.size() - 1 - commentsAtEnd);
            findingComments = last instanceof Comment;
            if (!findingComments) continue;
            ++commentsAtEnd;
        }
        for (int i = 0; i < commentsAtEnd; ++i) {
            Comment c = (Comment)everything.get(everything.size() - commentsAtEnd + i);
            CsmComment.process(c, printer);
        }
    }
}

