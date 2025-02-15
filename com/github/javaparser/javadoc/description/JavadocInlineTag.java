/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.javadoc.description;

import com.github.javaparser.javadoc.description.JavadocDescriptionElement;
import com.github.javaparser.utils.Utils;

public class JavadocInlineTag
implements JavadocDescriptionElement {
    private String tagName;
    private Type type;
    private String content;

    public static JavadocDescriptionElement fromText(String text) {
        if (!text.startsWith("{@")) {
            throw new IllegalArgumentException(String.format("Expected to start with '{@'. Text '%s'", text));
        }
        if (!text.endsWith("}")) {
            throw new IllegalArgumentException(String.format("Expected to end with '}'. Text '%s'", text));
        }
        text = text.substring(2, text.length() - 1);
        String tagName = Utils.nextWord(text);
        Type type = Type.fromName(tagName);
        String content = text.substring(tagName.length());
        return new JavadocInlineTag(tagName, type, content);
    }

    public JavadocInlineTag(String tagName, Type type, String content) {
        this.tagName = tagName;
        this.type = type;
        this.content = content;
    }

    public Type getType() {
        return this.type;
    }

    public String getContent() {
        return this.content;
    }

    public String getName() {
        return this.tagName;
    }

    @Override
    public String toText() {
        return "{@" + this.tagName + this.content + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        JavadocInlineTag that = (JavadocInlineTag)o;
        if (this.tagName != null ? !this.tagName.equals(that.tagName) : that.tagName != null) {
            return false;
        }
        if (this.type != that.type) {
            return false;
        }
        return this.content != null ? this.content.equals(that.content) : that.content == null;
    }

    public int hashCode() {
        int result = this.tagName != null ? this.tagName.hashCode() : 0;
        result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
        result = 31 * result + (this.content != null ? this.content.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "JavadocInlineTag{tagName='" + this.tagName + '\'' + ", type=" + (Object)((Object)this.type) + ", content='" + this.content + '\'' + '}';
    }

    public static enum Type {
        CODE,
        DOC_ROOT,
        INHERIT_DOC,
        LINK,
        LINKPLAIN,
        LITERAL,
        VALUE,
        SYSTEM_PROPERTY,
        UNKNOWN;

        private String keyword = Utils.screamingToCamelCase(this.name());

        static Type fromName(String tagName) {
            for (Type t : Type.values()) {
                if (!t.keyword.equals(tagName)) continue;
                return t;
            }
            return UNKNOWN;
        }
    }
}

