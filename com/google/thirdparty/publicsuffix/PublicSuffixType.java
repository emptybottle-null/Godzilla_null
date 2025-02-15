/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@Beta
@GwtCompatible
public enum PublicSuffixType {
    PRIVATE(':', ','),
    REGISTRY('!', '?');

    private final char innerNodeCode;
    private final char leafNodeCode;

    private PublicSuffixType(char innerNodeCode, char leafNodeCode) {
        this.innerNodeCode = innerNodeCode;
        this.leafNodeCode = leafNodeCode;
    }

    char getLeafNodeCode() {
        return this.leafNodeCode;
    }

    char getInnerNodeCode() {
        return this.innerNodeCode;
    }

    static PublicSuffixType fromCode(char code) {
        for (PublicSuffixType value : PublicSuffixType.values()) {
            if (value.getInnerNodeCode() != code && value.getLeafNodeCode() != code) continue;
            return value;
        }
        throw new IllegalArgumentException("No enum corresponding to given code: " + code);
    }

    static PublicSuffixType fromIsPrivate(boolean isPrivate) {
        return isPrivate ? PRIVATE : REGISTRY;
    }
}

