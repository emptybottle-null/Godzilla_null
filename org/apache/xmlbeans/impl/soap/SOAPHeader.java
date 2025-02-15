/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import java.util.Iterator;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;

public interface SOAPHeader
extends SOAPElement {
    public SOAPHeaderElement addHeaderElement(Name var1) throws SOAPException;

    public Iterator examineHeaderElements(String var1);

    public Iterator extractHeaderElements(String var1);

    public Iterator examineMustUnderstandHeaderElements(String var1);

    public Iterator examineAllHeaderElements();

    public Iterator extractAllHeaderElements();
}

