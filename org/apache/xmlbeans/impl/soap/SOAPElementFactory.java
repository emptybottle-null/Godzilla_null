/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFactory;

public class SOAPElementFactory {
    private SOAPFactory sf;

    private SOAPElementFactory(SOAPFactory soapfactory) {
        this.sf = soapfactory;
    }

    public SOAPElement create(Name name) throws SOAPException {
        return this.sf.createElement(name);
    }

    public SOAPElement create(String localName) throws SOAPException {
        return this.sf.createElement(localName);
    }

    public SOAPElement create(String localName, String prefix, String uri) throws SOAPException {
        return this.sf.createElement(localName, prefix, uri);
    }

    public static SOAPElementFactory newInstance() throws SOAPException {
        try {
            return new SOAPElementFactory(SOAPFactory.newInstance());
        } catch (Exception exception) {
            throw new SOAPException("Unable to create SOAP Element Factory: " + exception.getMessage());
        }
    }
}

