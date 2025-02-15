/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.JavaToken;
import com.github.javaparser.Token;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;

abstract class GeneratedJavaParserTokenManagerBase {
    GeneratedJavaParserTokenManagerBase() {
    }

    private static TokenRange tokenRange(Token token) {
        JavaToken javaToken = token.javaToken;
        return new TokenRange(javaToken, javaToken);
    }

    static Comment createCommentFromToken(Token token) {
        String commentText = token.image;
        if (token.kind == 8) {
            return new JavadocComment(GeneratedJavaParserTokenManagerBase.tokenRange(token), commentText.substring(3, commentText.length() - 2));
        }
        if (token.kind == 9) {
            return new BlockComment(GeneratedJavaParserTokenManagerBase.tokenRange(token), commentText.substring(2, commentText.length() - 2));
        }
        if (token.kind == 5) {
            return new LineComment(GeneratedJavaParserTokenManagerBase.tokenRange(token), commentText.substring(2));
        }
        throw new AssertionError((Object)"Unexpectedly got passed a non-comment token.");
    }
}

