/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.lexicalpreservation.TextElement;
import com.github.javaparser.printer.lexicalpreservation.TextElementMatcher;

class TextElementMatchers {
    TextElementMatchers() {
    }

    static TextElementMatcher byTokenType(int tokenType) {
        return textElement -> textElement.isToken(tokenType);
    }

    static TextElementMatcher byNode(final Node node) {
        return new TextElementMatcher(){

            @Override
            public boolean match(TextElement textElement) {
                return textElement.isNode(node);
            }

            public String toString() {
                return "match node " + node;
            }
        };
    }
}

