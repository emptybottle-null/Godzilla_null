/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.javadoc;

import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.utils.Utils;
import java.util.Optional;

public class JavadocBlockTag {
    private Type type;
    private JavadocDescription content;
    private Optional<String> name = Optional.empty();
    private String tagName;

    public JavadocBlockTag(Type type, String content) {
        this.type = type;
        this.tagName = type.keyword;
        if (type.hasName()) {
            this.name = Optional.of(Utils.nextWord(content));
            content = content.substring(this.name.get().length()).trim();
        }
        this.content = JavadocDescription.parseText(content);
    }

    public JavadocBlockTag(String tagName, String content) {
        this(Type.fromName(tagName), content);
        this.tagName = tagName;
    }

    public static JavadocBlockTag createParamBlockTag(String paramName, String content) {
        return new JavadocBlockTag(Type.PARAM, paramName + " " + content);
    }

    public Type getType() {
        return this.type;
    }

    public JavadocDescription getContent() {
        return this.content;
    }

    public Optional<String> getName() {
        return this.name;
    }

    public String getTagName() {
        return this.tagName;
    }

    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append("@");
        sb.append(this.tagName);
        this.name.ifPresent(s -> sb.append(" ").append((String)s));
        if (!this.content.isEmpty()) {
            sb.append(" ");
            sb.append(this.content.toText());
        }
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        JavadocBlockTag that = (JavadocBlockTag)o;
        if (this.type != that.type) {
            return false;
        }
        if (!this.content.equals(that.content)) {
            return false;
        }
        return this.name.equals(that.name);
    }

    public int hashCode() {
        int result = this.type.hashCode();
        result = 31 * result + this.content.hashCode();
        result = 31 * result + this.name.hashCode();
        return result;
    }

    public String toString() {
        return "JavadocBlockTag{type=" + (Object)((Object)this.type) + ", content='" + this.content + '\'' + ", name=" + this.name + '}';
    }

    public static enum Type {
        AUTHOR,
        DEPRECATED,
        EXCEPTION,
        PARAM,
        RETURN,
        SEE,
        SERIAL,
        SERIAL_DATA,
        SERIAL_FIELD,
        SINCE,
        THROWS,
        VERSION,
        UNKNOWN;

        private String keyword = Utils.screamingToCamelCase(this.name());

        boolean hasName() {
            return this == PARAM || this == EXCEPTION || this == THROWS;
        }

        static Type fromName(String tagName) {
            for (Type t : Type.values()) {
                if (!t.keyword.equals(tagName)) continue;
                return t;
            }
            return UNKNOWN;
        }
    }
}

