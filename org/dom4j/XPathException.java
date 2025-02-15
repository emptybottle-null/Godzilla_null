/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j;

public class XPathException
extends RuntimeException {
    private String xpath;

    public XPathException(String xpath) {
        super("Exception occurred evaluting XPath: " + xpath);
        this.xpath = xpath;
    }

    public XPathException(String xpath, String reason) {
        super("Exception occurred evaluting XPath: " + xpath + " " + reason);
        this.xpath = xpath;
    }

    public XPathException(String xpath, Exception e) {
        super("Exception occurred evaluting XPath: " + xpath + ". Exception: " + e.getMessage());
        this.xpath = xpath;
    }

    public String getXPath() {
        return this.xpath;
    }
}

