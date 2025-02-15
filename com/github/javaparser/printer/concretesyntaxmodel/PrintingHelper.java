/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.printer.Stringable;

class PrintingHelper {
    PrintingHelper() {
    }

    static String printToString(Object value) {
        if (value instanceof Stringable) {
            return ((Stringable)value).asString();
        }
        if (value instanceof Enum) {
            return ((Enum)value).name().toLowerCase();
        }
        if (value != null) {
            return value.toString();
        }
        return "";
    }
}

