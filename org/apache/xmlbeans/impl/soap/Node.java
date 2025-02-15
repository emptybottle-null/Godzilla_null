/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPException;

public interface Node
extends org.w3c.dom.Node {
    public String getValue();

    public void setParentElement(SOAPElement var1) throws SOAPException;

    public SOAPElement getParentElement();

    public void detachNode();

    public void recycleNode();

    public void setValue(String var1);
}

