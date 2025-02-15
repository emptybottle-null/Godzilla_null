/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dtd;

import org.dom4j.dtd.Decl;

public class InternalEntityDecl
implements Decl {
    private String name;
    private String value;

    public InternalEntityDecl() {
    }

    public InternalEntityDecl(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("<!ENTITY ");
        if (this.name.startsWith("%")) {
            buffer.append("% ");
            buffer.append(this.name.substring(1));
        } else {
            buffer.append(this.name);
        }
        buffer.append(" \"");
        buffer.append(this.escapeEntityValue(this.value));
        buffer.append("\">");
        return buffer.toString();
    }

    private String escapeEntityValue(String text) {
        StringBuffer result = new StringBuffer();
        block7: for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            switch (c) {
                case '<': {
                    result.append("&#38;#60;");
                    continue block7;
                }
                case '>': {
                    result.append("&#62;");
                    continue block7;
                }
                case '&': {
                    result.append("&#38;#38;");
                    continue block7;
                }
                case '\'': {
                    result.append("&#39;");
                    continue block7;
                }
                case '\"': {
                    result.append("&#34;");
                    continue block7;
                }
                default: {
                    if (c < ' ') {
                        result.append("&#" + c + ";");
                        continue block7;
                    }
                    result.append(c);
                }
            }
        }
        return result.toString();
    }
}

