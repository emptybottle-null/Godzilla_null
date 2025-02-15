/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.lexicalpreservation.TextElementMatcher;
import java.util.Optional;

public abstract class TextElement
implements TextElementMatcher {
    abstract String expand();

    abstract boolean isToken(int var1);

    final boolean isCommentToken() {
        return this.isToken(8) || this.isToken(5) || this.isToken(9);
    }

    @Override
    public boolean match(TextElement textElement) {
        return this.equals(textElement);
    }

    abstract boolean isNode(Node var1);

    public abstract boolean isLiteral();

    public abstract boolean isWhiteSpace();

    public abstract boolean isSpaceOrTab();

    public abstract boolean isNewline();

    public abstract boolean isComment();

    public abstract boolean isSeparator();

    public abstract boolean isIdentifier();

    public abstract boolean isPrimitive();

    public final boolean isWhiteSpaceOrComment() {
        return this.isWhiteSpace() || this.isComment();
    }

    public abstract boolean isChildOfClass(Class<? extends Node> var1);

    public boolean isChild() {
        return this.isChildOfClass(Node.class);
    }

    abstract Optional<Range> getRange();

    TextElementMatcher matchByRange() {
        return textElement -> this.getRange().flatMap(r1 -> textElement.getRange().map(r1::equals)).orElse(true);
    }
}

