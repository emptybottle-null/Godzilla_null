/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import java.util.Iterator;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.Node;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.w3c.dom.Element;

public interface SOAPElement
extends Node,
Element {
    public SOAPElement addChildElement(Name var1) throws SOAPException;

    public SOAPElement addChildElement(String var1) throws SOAPException;

    public SOAPElement addChildElement(String var1, String var2) throws SOAPException;

    public SOAPElement addChildElement(String var1, String var2, String var3) throws SOAPException;

    public SOAPElement addChildElement(SOAPElement var1) throws SOAPException;

    public SOAPElement addTextNode(String var1) throws SOAPException;

    public SOAPElement addAttribute(Name var1, String var2) throws SOAPException;

    public SOAPElement addNamespaceDeclaration(String var1, String var2) throws SOAPException;

    public String getAttributeValue(Name var1);

    public Iterator getAllAttributes();

    public String getNamespaceURI(String var1);

    public Iterator getNamespacePrefixes();

    public Name getElementName();

    public boolean removeAttribute(Name var1);

    public boolean removeNamespaceDeclaration(String var1);

    public Iterator getChildElements();

    public Iterator getChildElements(Name var1);

    public void setEncodingStyle(String var1) throws SOAPException;

    public String getEncodingStyle();

    public void removeContents();

    public Iterator getVisibleNamespacePrefixes();
}

