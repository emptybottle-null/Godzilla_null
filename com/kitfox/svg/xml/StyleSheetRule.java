/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.xml;

public class StyleSheetRule {
    final String styleName;
    final String tag;
    final String className;

    public StyleSheetRule(String styleName, String tag, String className) {
        this.styleName = styleName;
        this.tag = tag;
        this.className = className;
    }

    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.styleName != null ? this.styleName.hashCode() : 0);
        hash = 13 * hash + (this.tag != null ? this.tag.hashCode() : 0);
        hash = 13 * hash + (this.className != null ? this.className.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        StyleSheetRule other = (StyleSheetRule)obj;
        if (this.styleName == null ? other.styleName != null : !this.styleName.equals(other.styleName)) {
            return false;
        }
        if (this.tag == null ? other.tag != null : !this.tag.equals(other.tag)) {
            return false;
        }
        return !(this.className == null ? other.className != null : !this.className.equals(other.className));
    }
}

