/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation.parser;

public interface AnimTimeParserConstants {
    public static final int EOF = 0;
    public static final int LETTER = 6;
    public static final int DIGIT = 7;
    public static final int INTEGER = 8;
    public static final int FLOAT = 9;
    public static final int INDEFINITE = 10;
    public static final int MOUSE_OVER = 11;
    public static final int WHEN_NOT_ACTIVE = 12;
    public static final int UNITS = 13;
    public static final int IDENTIFIER = 14;
    public static final int DEFAULT = 0;
    public static final String[] tokenImage = new String[]{"<EOF>", "\" \"", "\"\\t\"", "\"\\n\"", "\"\\r\"", "\"\\f\"", "<LETTER>", "<DIGIT>", "<INTEGER>", "<FLOAT>", "\"indefinite\"", "\"mouseover\"", "\"whenNotActive\"", "<UNITS>", "<IDENTIFIER>", "\";\"", "\"+\"", "\"-\"", "\":\"", "\".\"", "\"(\"", "\")\""};
}

