/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.JavaToken;
import java.util.Optional;

public enum LineSeparator {
    CR("\r", "CR (\\r)"),
    LF("\n", "LF (\\n)"),
    CRLF("\r\n", "CRLF (\\r\\n)"),
    SYSTEM(System.getProperty("line.separator"), "SYSTEM : (" + System.getProperty("line.separator").replace("\r", "\\r").replace("\n", "\\n") + ")"),
    ARBITRARY("\n", "ARBITRARY (\\n)"),
    MIXED("", "MIXED"),
    UNKNOWN("", "UNKNOWN"),
    NONE("", "NONE");

    private final String text;
    private final String description;

    private LineSeparator(String text, String description) {
        this.text = text;
        this.description = description;
    }

    private static int count(String haystack, String needle) {
        return (haystack.length() - haystack.replaceAll(needle, "").length()) / needle.length();
    }

    public static LineSeparator detect(String string) {
        int countCr = LineSeparator.count(string, "\r");
        int countLf = LineSeparator.count(string, "\n");
        int countCrLf = LineSeparator.count(string, "\r\n");
        return LineSeparator.getLineEnding(countCr, countLf, countCrLf);
    }

    public static LineSeparator getLineEnding(int countCr, int countLf, int countCrLf) {
        boolean crLfOnly;
        boolean lfOnly;
        boolean crOnly;
        boolean noLineEndings;
        boolean bl = noLineEndings = countCr == 0 && countLf == 0 && countCrLf == 0;
        if (noLineEndings) {
            return NONE;
        }
        boolean bl2 = crOnly = countCr > 0 && countLf == 0 && countCrLf == 0;
        if (crOnly) {
            return CR;
        }
        boolean bl3 = lfOnly = countCr == 0 && countLf > 0 && countCrLf == 0;
        if (lfOnly) {
            return LF;
        }
        boolean bl4 = crLfOnly = countCr == countLf && countLf == countCrLf;
        if (crLfOnly) {
            return CRLF;
        }
        return MIXED;
    }

    public static Optional<LineSeparator> lookup(String ending) {
        if (CR.asRawString().equals(ending)) {
            return Optional.of(CR);
        }
        if (LF.asRawString().equals(ending)) {
            return Optional.of(LF);
        }
        if (CRLF.asRawString().equals(ending)) {
            return Optional.of(CRLF);
        }
        return Optional.empty();
    }

    public static Optional<LineSeparator> lookupEscaped(String ending) {
        if (CR.asEscapedString().equals(ending)) {
            return Optional.of(CR);
        }
        if (LF.asEscapedString().equals(ending)) {
            return Optional.of(LF);
        }
        if (CRLF.asEscapedString().equals(ending)) {
            return Optional.of(CRLF);
        }
        return Optional.empty();
    }

    public String describe() {
        return this.description;
    }

    public boolean equalsString(LineSeparator lineSeparator) {
        return this.text.equals(lineSeparator.asRawString());
    }

    public boolean isStandardEol() {
        return this.equalsString(CR) || this.equalsString(LF) || this.equalsString(CRLF);
    }

    public String asEscapedString() {
        String result = this.text.replace("\r", "\\r").replace("\n", "\\n");
        return result;
    }

    public String asRawString() {
        return this.text;
    }

    private Optional<JavaToken.Kind> asJavaTokenKind() {
        if (this == CR) {
            return Optional.of(JavaToken.Kind.OLD_MAC_EOL);
        }
        if (this == LF) {
            return Optional.of(JavaToken.Kind.UNIX_EOL);
        }
        if (this == CRLF) {
            return Optional.of(JavaToken.Kind.WINDOWS_EOL);
        }
        return Optional.empty();
    }

    public String toString() {
        return this.asRawString();
    }
}

