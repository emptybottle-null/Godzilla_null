/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.javadoc;

import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.utils.Utils;
import java.util.LinkedList;
import java.util.List;

public class Javadoc {
    private JavadocDescription description;
    private List<JavadocBlockTag> blockTags;

    public Javadoc(JavadocDescription description) {
        this.description = description;
        this.blockTags = new LinkedList<JavadocBlockTag>();
    }

    public Javadoc addBlockTag(JavadocBlockTag blockTag) {
        this.blockTags.add(blockTag);
        return this;
    }

    public Javadoc addBlockTag(String tagName, String content) {
        return this.addBlockTag(new JavadocBlockTag(tagName, content));
    }

    public Javadoc addBlockTag(String tagName, String parameter, String content) {
        return this.addBlockTag(tagName, parameter + " " + content);
    }

    public Javadoc addBlockTag(String tagName) {
        return this.addBlockTag(tagName, "");
    }

    public String toText() {
        StringBuilder sb = new StringBuilder();
        if (!this.description.isEmpty()) {
            sb.append(this.description.toText());
            sb.append(Utils.SYSTEM_EOL);
        }
        if (!this.blockTags.isEmpty()) {
            sb.append(Utils.SYSTEM_EOL);
        }
        this.blockTags.forEach(bt -> {
            sb.append(bt.toText());
            sb.append(Utils.SYSTEM_EOL);
        });
        return sb.toString();
    }

    public JavadocComment toComment() {
        return this.toComment("");
    }

    public JavadocComment toComment(String indentation) {
        for (char c : indentation.toCharArray()) {
            if (Character.isWhitespace(c)) continue;
            throw new IllegalArgumentException("The indentation string should be composed only by whitespace characters");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Utils.SYSTEM_EOL);
        String text = this.toText();
        if (!text.isEmpty()) {
            for (String line : text.split(Utils.SYSTEM_EOL)) {
                sb.append(indentation);
                sb.append(" * ");
                sb.append(line);
                sb.append(Utils.SYSTEM_EOL);
            }
        }
        sb.append(indentation);
        sb.append(" ");
        return new JavadocComment(sb.toString());
    }

    public JavadocDescription getDescription() {
        return this.description;
    }

    public List<JavadocBlockTag> getBlockTags() {
        return this.blockTags;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Javadoc document = (Javadoc)o;
        return this.description.equals(document.description) && this.blockTags.equals(document.blockTags);
    }

    public int hashCode() {
        int result = this.description.hashCode();
        result = 31 * result + this.blockTags.hashCode();
        return result;
    }

    public String toString() {
        return "Javadoc{description=" + this.description + ", blockTags=" + this.blockTags + '}';
    }
}

