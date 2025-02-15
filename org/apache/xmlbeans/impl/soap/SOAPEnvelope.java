/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPHeader;

public interface SOAPEnvelope
extends SOAPElement {
    public Name createName(String var1, String var2, String var3) throws SOAPException;

    public Name createName(String var1) throws SOAPException;

    public SOAPHeader getHeader() throws SOAPException;

    public SOAPBody getBody() throws SOAPException;

    public SOAPHeader addHeader() throws SOAPException;

    public SOAPBody addBody() throws SOAPException;
}

