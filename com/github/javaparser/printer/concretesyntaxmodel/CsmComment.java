/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;

public class CsmComment
implements CsmElement {
    static void process(Comment comment, SourcePrinter printer) {
        String content = printer.normalizeEolInTextBlock(comment.getContent());
        if (comment instanceof BlockComment) {
            printer.print("/*");
            printer.print(content);
            printer.println("*/");
        } else if (comment instanceof JavadocComment) {
            printer.print("/**");
            printer.print(content);
            printer.println("*/");
        } else if (comment instanceof LineComment) {
            printer.print("//");
            printer.print(content);
            printer.println();
        } else {
            throw new UnsupportedOperationException(comment.getClass().getSimpleName());
        }
    }

    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        node.getComment().ifPresent(c -> CsmComment.process(c, printer));
    }
}

