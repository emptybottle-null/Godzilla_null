/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class Comment
extends AstNode {
    private String value;
    private Token.CommentType commentType;

    public Comment(int pos, int len, Token.CommentType type, String value) {
        super(pos, len);
        this.type = 161;
        this.commentType = type;
        this.value = value;
    }

    public Token.CommentType getCommentType() {
        return this.commentType;
    }

    public void setCommentType(Token.CommentType type) {
        this.commentType = type;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder(this.getLength() + 10);
        sb.append(this.makeIndent(depth));
        sb.append(this.value);
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}

