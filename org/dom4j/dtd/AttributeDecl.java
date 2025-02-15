/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dtd;

import org.dom4j.dtd.Decl;

public class AttributeDecl
implements Decl {
    private String elementName;
    private String attributeName;
    private String type;
    private String value;
    private String valueDefault;

    public AttributeDecl() {
    }

    public AttributeDecl(String elementName, String attributeName, String type, String valueDefault, String value) {
        this.elementName = elementName;
        this.attributeName = attributeName;
        this.type = type;
        this.value = value;
        this.valueDefault = valueDefault;
    }

    public String getElementName() {
        return this.elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueDefault() {
        return this.valueDefault;
    }

    public void setValueDefault(String valueDefault) {
        this.valueDefault = valueDefault;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("<!ATTLIST ");
        buffer.append(this.elementName);
        buffer.append(" ");
        buffer.append(this.attributeName);
        buffer.append(" ");
        buffer.append(this.type);
        buffer.append(" ");
        if (this.valueDefault != null) {
            buffer.append(this.valueDefault);
            if (this.valueDefault.equals("#FIXED")) {
                buffer.append(" \"");
                buffer.append(this.value);
                buffer.append("\"");
            }
        } else {
            buffer.append("\"");
            buffer.append(this.value);
            buffer.append("\"");
        }
        buffer.append(">");
        return buffer.toString();
    }
}

