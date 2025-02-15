/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.xml.stream;

import java.util.Map;
import org.apache.xmlbeans.xml.stream.Attribute;
import org.apache.xmlbeans.xml.stream.AttributeIterator;
import org.apache.xmlbeans.xml.stream.XMLEvent;
import org.apache.xmlbeans.xml.stream.XMLName;

public interface StartElement
extends XMLEvent {
    public AttributeIterator getAttributes();

    public AttributeIterator getNamespaces();

    public AttributeIterator getAttributesAndNamespaces();

    public Attribute getAttributeByName(XMLName var1);

    public String getNamespaceUri(String var1);

    public Map getNamespaceMap();
}

