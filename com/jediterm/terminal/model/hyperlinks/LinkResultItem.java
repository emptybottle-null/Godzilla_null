/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model.hyperlinks;

import com.jediterm.terminal.model.hyperlinks.LinkInfo;
import org.jetbrains.annotations.NotNull;

public class LinkResultItem {
    private int myStartOffset;
    private int myEndOffset;
    private LinkInfo myLinkInfo;

    public LinkResultItem(int startOffset, int endOffset, @NotNull LinkInfo linkInfo) {
        if (linkInfo == null) {
            LinkResultItem.$$$reportNull$$$0(0);
        }
        this.myStartOffset = startOffset;
        this.myEndOffset = endOffset;
        this.myLinkInfo = linkInfo;
    }

    public int getStartOffset() {
        return this.myStartOffset;
    }

    public int getEndOffset() {
        return this.myEndOffset;
    }

    public LinkInfo getLinkInfo() {
        return this.myLinkInfo;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "linkInfo", "com/jediterm/terminal/model/hyperlinks/LinkResultItem", "<init>"));
    }
}

