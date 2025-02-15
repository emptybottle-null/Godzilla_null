/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.InvalidXPathException;
import org.dom4j.Visitor;
import org.dom4j.XPath;

public interface Node
extends Cloneable {
    public static final short ANY_NODE = 0;
    public static final short ELEMENT_NODE = 1;
    public static final short ATTRIBUTE_NODE = 2;
    public static final short TEXT_NODE = 3;
    public static final short CDATA_SECTION_NODE = 4;
    public static final short ENTITY_REFERENCE_NODE = 5;
    public static final short PROCESSING_INSTRUCTION_NODE = 7;
    public static final short COMMENT_NODE = 8;
    public static final short DOCUMENT_NODE = 9;
    public static final short DOCUMENT_TYPE_NODE = 10;
    public static final short NAMESPACE_NODE = 13;
    public static final short UNKNOWN_NODE = 14;
    public static final short MAX_NODE_TYPE = 14;

    public boolean supportsParent();

    public Element getParent();

    public void setParent(Element var1);

    public Document getDocument();

    public void setDocument(Document var1);

    public boolean isReadOnly();

    public boolean hasContent();

    public String getName();

    public void setName(String var1);

    public String getText();

    public void setText(String var1);

    public String getStringValue();

    public String getPath();

    public String getPath(Element var1);

    public String getUniquePath();

    public String getUniquePath(Element var1);

    public String asXML();

    public void write(Writer var1) throws IOException;

    public short getNodeType();

    public String getNodeTypeName();

    public Node detach();

    public List<Node> selectNodes(String var1);

    public Object selectObject(String var1);

    public List<Node> selectNodes(String var1, String var2);

    public List<Node> selectNodes(String var1, String var2, boolean var3);

    public Node selectSingleNode(String var1);

    public String valueOf(String var1);

    public Number numberValueOf(String var1);

    public boolean matches(String var1);

    public XPath createXPath(String var1) throws InvalidXPathException;

    public Node asXPathResult(Element var1);

    public void accept(Visitor var1);

    public Object clone();
}

