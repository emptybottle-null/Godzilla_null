/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.intellij.lang.annotations;

import org.intellij.lang.annotations.Language;

class PrintFormatPattern {
    @Language(value="RegExp")
    private static final String ARG_INDEX = "(?:\\d+\\$)?";
    @Language(value="RegExp")
    private static final String FLAGS = "(?:[-#+ 0,(<]*)?";
    @Language(value="RegExp")
    private static final String WIDTH = "(?:\\d+)?";
    @Language(value="RegExp")
    private static final String PRECISION = "(?:\\.\\d+)?";
    @Language(value="RegExp")
    private static final String CONVERSION = "(?:[tT])?(?:[a-zA-Z%])";
    @Language(value="RegExp")
    private static final String TEXT = "[^%]|%%";
    @Language(value="RegExp")
    static final String PRINT_FORMAT = "(?:[^%]|%%|(?:%(?:\\d+\\$)?(?:[-#+ 0,(<]*)?(?:\\d+)?(?:\\.\\d+)?(?:[tT])?(?:[a-zA-Z%])))*";

    PrintFormatPattern() {
    }
}

