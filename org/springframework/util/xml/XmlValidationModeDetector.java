/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import java.io.BufferedReader;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

public class XmlValidationModeDetector {
    public static final int VALIDATION_NONE = 0;
    public static final int VALIDATION_AUTO = 1;
    public static final int VALIDATION_DTD = 2;
    public static final int VALIDATION_XSD = 3;
    private static final String DOCTYPE = "DOCTYPE";
    private static final String START_COMMENT = "<!--";
    private static final String END_COMMENT = "-->";
    private boolean inComment;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int detectValidationMode(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));){
            String content;
            boolean isDtdValidated = false;
            while ((content = reader.readLine()) != null) {
                content = this.consumeCommentTokens(content);
                if (this.inComment || !StringUtils.hasText(content)) continue;
                if (this.hasDoctype(content)) {
                    isDtdValidated = true;
                    break;
                }
                if (!this.hasOpeningTag(content)) continue;
            }
            int n = isDtdValidated ? 2 : 3;
            return n;
        } catch (CharConversionException ex) {
            return 1;
        }
    }

    private boolean hasDoctype(String content) {
        return content.contains(DOCTYPE);
    }

    private boolean hasOpeningTag(String content) {
        if (this.inComment) {
            return false;
        }
        int openTagIndex = content.indexOf(60);
        return openTagIndex > -1 && content.length() > openTagIndex + 1 && Character.isLetter(content.charAt(openTagIndex + 1));
    }

    @Nullable
    private String consumeCommentTokens(String line) {
        int indexOfStartComment = line.indexOf(START_COMMENT);
        if (indexOfStartComment == -1 && !line.contains(END_COMMENT)) {
            return line;
        }
        String result = "";
        String currLine = line;
        if (indexOfStartComment >= 0) {
            result = line.substring(0, indexOfStartComment);
            currLine = line.substring(indexOfStartComment);
        }
        while ((currLine = this.consume(currLine)) != null) {
            if (this.inComment || currLine.trim().startsWith(START_COMMENT)) continue;
            return result + currLine;
        }
        return null;
    }

    @Nullable
    private String consume(String line) {
        int index = this.inComment ? this.endComment(line) : this.startComment(line);
        return index == -1 ? null : line.substring(index);
    }

    private int startComment(String line) {
        return this.commentToken(line, START_COMMENT, true);
    }

    private int endComment(String line) {
        return this.commentToken(line, END_COMMENT, false);
    }

    private int commentToken(String line, String token, boolean inCommentIfPresent) {
        int index = line.indexOf(token);
        if (index > -1) {
            this.inComment = inCommentIfPresent;
        }
        return index == -1 ? index : index + token.length();
    }
}

