/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j;

public class InvalidXPathException
extends IllegalArgumentException {
    public InvalidXPathException(String xpath) {
        super("Invalid XPath expression: " + xpath);
    }

    public InvalidXPathException(String xpath, String reason) {
        super("Invalid XPath expression: " + xpath + " " + reason);
    }
}

