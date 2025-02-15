/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.utils.Utils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class JavadocParser {
    private static String BLOCK_TAG_PREFIX = "@";
    private static Pattern BLOCK_PATTERN = Pattern.compile("^\\s*" + BLOCK_TAG_PREFIX, 8);

    JavadocParser() {
    }

    public static Javadoc parse(JavadocComment comment) {
        return JavadocParser.parse(comment.getContent());
    }

    public static Javadoc parse(String commentContent) {
        List<String> blockLines;
        String descriptionText;
        List<String> cleanLines = JavadocParser.cleanLines(Utils.normalizeEolInTextBlock(commentContent, Utils.SYSTEM_EOL));
        int indexOfFirstBlockTag = cleanLines.stream().filter(JavadocParser::isABlockLine).map(cleanLines::indexOf).findFirst().orElse(-1);
        if (indexOfFirstBlockTag == -1) {
            descriptionText = JavadocParser.trimRight(String.join((CharSequence)Utils.SYSTEM_EOL, cleanLines));
            blockLines = Collections.emptyList();
        } else {
            descriptionText = JavadocParser.trimRight(String.join((CharSequence)Utils.SYSTEM_EOL, cleanLines.subList(0, indexOfFirstBlockTag)));
            String tagBlock = cleanLines.subList(indexOfFirstBlockTag, cleanLines.size()).stream().collect(Collectors.joining(Utils.SYSTEM_EOL));
            blockLines = BLOCK_PATTERN.splitAsStream(tagBlock).filter(s1 -> !s1.isEmpty()).map(s -> BLOCK_TAG_PREFIX + s).collect(Collectors.toList());
        }
        Javadoc document = new Javadoc(JavadocDescription.parseText(descriptionText));
        blockLines.forEach(l -> document.addBlockTag(JavadocParser.parseBlockTag(l)));
        return document;
    }

    private static JavadocBlockTag parseBlockTag(String line) {
        line = line.trim().substring(1);
        String tagName = Utils.nextWord(line);
        String rest = line.substring(tagName.length()).trim();
        return new JavadocBlockTag(tagName, rest);
    }

    private static boolean isABlockLine(String line) {
        return line.trim().startsWith(BLOCK_TAG_PREFIX);
    }

    private static String trimRight(String string) {
        while (!string.isEmpty() && Character.isWhitespace(string.charAt(string.length() - 1))) {
            string = string.substring(0, string.length() - 1);
        }
        return string;
    }

    private static List<String> cleanLines(String content) {
        String[] lines = content.split(Utils.SYSTEM_EOL);
        if (lines.length == 0) {
            return Collections.emptyList();
        }
        List<String> cleanedLines = Arrays.stream(lines).map(l -> {
            char c;
            int asteriskIndex = JavadocParser.startsWithAsterisk(l);
            if (asteriskIndex == -1) {
                return l;
            }
            if (l.length() > asteriskIndex + 1 && ((c = l.charAt(asteriskIndex + 1)) == ' ' || c == '\t')) {
                return l.substring(asteriskIndex + 2);
            }
            return l.substring(asteriskIndex + 1);
        }).collect(Collectors.toList());
        if (!(((String)(cleanedLines = cleanedLines.stream().map(l -> l.trim().isEmpty() ? "" : l).collect(Collectors.toList())).get(0)).isEmpty() || ((String)cleanedLines.get(0)).charAt(0) != ' ' && cleanedLines.get(0).charAt(0) != '\t')) {
            cleanedLines.set(0, cleanedLines.get(0).substring(1));
        }
        while (cleanedLines.size() > 0 && ((String)cleanedLines.get(0)).trim().isEmpty()) {
            cleanedLines = cleanedLines.subList(1, cleanedLines.size());
        }
        while (cleanedLines.size() > 0 && cleanedLines.get(cleanedLines.size() - 1).trim().isEmpty()) {
            cleanedLines = cleanedLines.subList(0, cleanedLines.size() - 1);
        }
        return cleanedLines;
    }

    static int startsWithAsterisk(String line) {
        if (line.startsWith("*")) {
            return 0;
        }
        if ((line.startsWith(" ") || line.startsWith("\t")) && line.length() > 1) {
            int res = JavadocParser.startsWithAsterisk(line.substring(1));
            if (res == -1) {
                return -1;
            }
            return 1 + res;
        }
        return -1;
    }
}

