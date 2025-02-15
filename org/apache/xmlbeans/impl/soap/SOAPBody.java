/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import java.util.Locale;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.w3c.dom.Document;

public interface SOAPBody
extends SOAPElement {
    public SOAPFault addFault() throws SOAPException;

    public boolean hasFault();

    public SOAPFault getFault();

    public SOAPBodyElement addBodyElement(Name var1) throws SOAPException;

    public SOAPFault addFault(Name var1, String var2, Locale var3) throws SOAPException;

    public SOAPFault addFault(Name var1, String var2) throws SOAPException;

    public SOAPBodyElement addDocument(Document var1) throws SOAPException;
}

