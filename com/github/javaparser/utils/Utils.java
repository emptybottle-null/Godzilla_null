/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.utils.LineSeparator;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class Utils {
    @Deprecated
    public static final String EOL = LineSeparator.SYSTEM.asRawString();
    @Deprecated
    public static final String SYSTEM_EOL = LineSeparator.SYSTEM.asRawString();

    public static <E> boolean isNullOrEmpty(Collection<E> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> T assertNotNull(T o) {
        if (o == null) {
            throw new AssertionError((Object)"A reference was unexpectedly null.");
        }
        return o;
    }

    public static String assertNonEmpty(String string) {
        if (string == null || string.isEmpty()) {
            throw new AssertionError((Object)"A string was unexpectedly empty.");
        }
        return string;
    }

    public static <T extends Number> T assertNonNegative(T number) {
        if (number.longValue() < 0L) {
            throw new AssertionError((Object)"A number was unexpectedly negative.");
        }
        return number;
    }

    public static <T extends Number> T assertPositive(T number) {
        if (number.longValue() <= 0L) {
            throw new AssertionError((Object)"A number was unexpectedly non-positive.");
        }
        return number;
    }

    public static String escapeEndOfLines(String string) {
        StringBuilder escapedString = new StringBuilder();
        block4: for (char c : string.toCharArray()) {
            switch (c) {
                case '\n': {
                    escapedString.append("\\n");
                    continue block4;
                }
                case '\r': {
                    escapedString.append("\\r");
                    continue block4;
                }
                default: {
                    escapedString.append(c);
                }
            }
        }
        return escapedString.toString();
    }

    public static String readerToString(Reader reader) throws IOException {
        int numChars;
        StringBuilder result = new StringBuilder();
        char[] buffer = new char[8192];
        while ((numChars = reader.read(buffer, 0, buffer.length)) > 0) {
            result.append(buffer, 0, numChars);
        }
        return result.toString();
    }

    @Deprecated
    public static String toCamelCase(String original) {
        return Utils.screamingToCamelCase(original);
    }

    public static String screamingToCamelCase(String original) {
        StringBuilder sb = new StringBuilder();
        String[] parts = original.toLowerCase().split("_");
        for (int i = 0; i < parts.length; ++i) {
            sb.append(i == 0 ? parts[i] : Utils.capitalize(parts[i]));
        }
        return sb.toString();
    }

    public static String camelCaseToScreaming(String input) {
        if (input.isEmpty()) {
            return "";
        }
        StringBuilder scream = new StringBuilder(input.substring(0, 1).toUpperCase());
        for (char c : input.substring(1).toCharArray()) {
            if (Character.isUpperCase(c)) {
                scream.append("_");
            }
            scream.append(Character.toUpperCase(c));
        }
        return scream.toString();
    }

    public static String nextWord(String string) {
        int index;
        for (index = 0; index < string.length() && !Character.isWhitespace(string.charAt(index)); ++index) {
        }
        return string.substring(0, index);
    }

    public static StringBuilder indent(StringBuilder builder, int indentLevel) {
        for (int i = 0; i < indentLevel; ++i) {
            builder.append("\t");
        }
        return builder;
    }

    public static String capitalize(String s) {
        return Utils.stringTransformer(s, "capitalize", String::toUpperCase);
    }

    public static String decapitalize(String s) {
        return Utils.stringTransformer(s, "decapitalize", String::toLowerCase);
    }

    private static String stringTransformer(String s, String operationDescription, Function<String, String> transformation) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException(String.format("You cannot %s an empty string", operationDescription));
        }
        return transformation.apply(s.substring(0, 1)) + s.substring(1);
    }

    public static boolean valueIsNullOrEmpty(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof Optional) {
            if (((Optional)value).isPresent()) {
                value = ((Optional)value).get();
            } else {
                return true;
            }
        }
        return value instanceof Collection && ((Collection)value).isEmpty();
    }

    public static boolean valueIsNullOrEmptyStringOrOptional(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof Optional) {
            if (((Optional)value).isPresent()) {
                value = ((Optional)value).get();
            } else {
                return true;
            }
        }
        return false;
    }

    public static <E> void replaceElementByObjectIdentity(List<E> list, E oldObject, E newObject) {
        int index = Utils.indexOfElementByObjectIdentity(list, oldObject);
        if (index == -1) {
            return;
        }
        list.set(index, newObject);
    }

    public static <E> void removeElementByObjectIdentity(List<E> list, E o) {
        int index = Utils.indexOfElementByObjectIdentity(list, o);
        if (index == -1) {
            return;
        }
        list.remove(index);
    }

    public static <E> int indexOfElementByObjectIdentity(List<E> list, E o) {
        for (int i = 0; i < list.size(); ++i) {
            E listO = list.get(i);
            if (o != listO) continue;
            return i;
        }
        return -1;
    }

    @SafeVarargs
    public static <T> Set<T> set(T ... items) {
        return new HashSet<T>(Arrays.asList(items));
    }

    public static String normalizeEolInTextBlock(String content, String desiredEndOfLineCharacter) {
        return content.replaceAll("\\R", desiredEndOfLineCharacter);
    }

    public static String normalizeEolInTextBlock(String content, LineSeparator desiredEndOfLineCharacter) {
        return Utils.normalizeEolInTextBlock(content, desiredEndOfLineCharacter.asRawString());
    }

    public static String removeFileExtension(String filename) {
        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex == -1) {
            return filename;
        }
        return filename.substring(0, extensionIndex);
    }

    public static String trimTrailingSpaces(String line) {
        while (line.length() > 0 && line.charAt(line.length() - 1) <= ' ') {
            line = line.substring(0, line.length() - 1);
        }
        return line;
    }

    public static boolean hasUnaryMinusAsParent(Node n) {
        return n.getParentNode().filter(parent -> parent instanceof UnaryExpr).map(parent -> (UnaryExpr)parent).map(unaryExpr -> unaryExpr.getOperator() == UnaryExpr.Operator.MINUS).orElse(false);
    }
}

