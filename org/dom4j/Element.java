/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.Text;

public interface Element
extends Branch {
    public QName getQName();

    public void setQName(QName var1);

    public Namespace getNamespace();

    public QName getQName(String var1);

    public Namespace getNamespaceForPrefix(String var1);

    public Namespace getNamespaceForURI(String var1);

    public List<Namespace> getNamespacesForURI(String var1);

    public String getNamespacePrefix();

    public String getNamespaceURI();

    public String getQualifiedName();

    public List<Namespace> additionalNamespaces();

    public List<Namespace> declaredNamespaces();

    public Element addAttribute(String var1, String var2);

    public Element addAttribute(QName var1, String var2);

    public Element addComment(String var1);

    public Element addCDATA(String var1);

    public Element addEntity(String var1, String var2);

    public Element addNamespace(String var1, String var2);

    public Element addProcessingInstruction(String var1, String var2);

    public Element addProcessingInstruction(String var1, Map<String, String> var2);

    public Element addText(String var1);

    public void add(Attribute var1);

    public void add(CDATA var1);

    public void add(Entity var1);

    public void add(Text var1);

    public void add(Namespace var1);

    public boolean remove(Attribute var1);

    public boolean remove(CDATA var1);

    public boolean remove(Entity var1);

    public boolean remove(Namespace var1);

    public boolean remove(Text var1);

    @Override
    public String getText();

    public String getTextTrim();

    @Override
    public String getStringValue();

    public Object getData();

    public void setData(Object var1);

    public List<Attribute> attributes();

    public void setAttributes(List<Attribute> var1);

    public int attributeCount();

    public Iterator<Attribute> attributeIterator();

    public Attribute attribute(int var1);

    public Attribute attribute(String var1);

    public Attribute attribute(QName var1);

    public String attributeValue(String var1);

    public String attributeValue(String var1, String var2);

    public String attributeValue(QName var1);

    public String attributeValue(QName var1, String var2);

    public void setAttributeValue(String var1, String var2);

    public void setAttributeValue(QName var1, String var2);

    public Element element(String var1);

    public Element element(QName var1);

    public List<Element> elements();

    public List<Element> elements(String var1);

    public List<Element> elements(QName var1);

    public Iterator<Element> elementIterator();

    public Iterator<Element> elementIterator(String var1);

    public Iterator<Element> elementIterator(QName var1);

    public boolean isRootElement();

    public boolean hasMixedContent();

    public boolean isTextOnly();

    public void appendAttributes(Element var1);

    public Element createCopy();

    public Element createCopy(String var1);

    public Element createCopy(QName var1);

    public String elementText(String var1);

    public String elementText(QName var1);

    public String elementTextTrim(String var1);

    public String elementTextTrim(QName var1);

    public Node getXPathResult(int var1);
}

