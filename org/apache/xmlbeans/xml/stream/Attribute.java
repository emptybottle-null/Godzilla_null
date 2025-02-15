/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.xml.stream;

import org.apache.xmlbeans.xml.stream.XMLName;

public interface Attribute {
    public XMLName getName();

    public String getValue();

    public String getType();

    public XMLName getSchemaType();
}

