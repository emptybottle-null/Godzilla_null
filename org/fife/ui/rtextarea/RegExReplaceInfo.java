/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

class RegExReplaceInfo {
    private String matchedText;
    private int startIndex;
    private int endIndex;
    private String replacement;

    RegExReplaceInfo(String matchedText, int start, int end, String replacement) {
        this.matchedText = matchedText;
        this.startIndex = start;
        this.endIndex = end;
        this.replacement = replacement;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public String getMatchedText() {
        return this.matchedText;
    }

    public String getReplacement() {
        return this.replacement;
    }

    public int getStartIndex() {
        return this.startIndex;
    }
}

