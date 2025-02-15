/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

public class ParseProblem {
    private Type type;
    private String message;
    private String sourceName;
    private int offset;
    private int length;

    public ParseProblem(Type type, String message, String sourceName, int offset, int length) {
        this.setType(type);
        this.setMessage(message);
        this.setSourceName(sourceName);
        this.setFileOffset(offset);
        this.setLength(length);
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String name) {
        this.sourceName = name;
    }

    public int getFileOffset() {
        return this.offset;
    }

    public void setFileOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append(this.sourceName).append(":");
        sb.append("offset=").append(this.offset).append(",");
        sb.append("length=").append(this.length).append(",");
        sb.append(this.type == Type.Error ? "error: " : "warning: ");
        sb.append(this.message);
        return sb.toString();
    }

    public static enum Type {
        Error,
        Warning;

    }
}

