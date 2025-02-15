/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.printer.lexicalpreservation.TextElement;

public interface TextElementMatcher {
    public boolean match(TextElement var1);

    default public TextElementMatcher and(TextElementMatcher textElementMatcher) {
        return textElement -> this.match(textElement) && textElementMatcher.match(textElement);
    }
}

