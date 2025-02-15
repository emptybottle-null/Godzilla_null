/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import org.apache.xmlbeans.impl.soap.FactoryFinder;
import org.apache.xmlbeans.impl.soap.SOAPConnection;
import org.apache.xmlbeans.impl.soap.SOAPException;

public abstract class SOAPConnectionFactory {
    private static final String DEFAULT_SOAP_CONNECTION_FACTORY = "org.apache.axis.soap.SOAPConnectionFactoryImpl";
    private static final String SF_PROPERTY = "javax.xml.soap.SOAPConnectionFactory";

    public static SOAPConnectionFactory newInstance() throws SOAPException, UnsupportedOperationException {
        try {
            return (SOAPConnectionFactory)FactoryFinder.find(SF_PROPERTY, DEFAULT_SOAP_CONNECTION_FACTORY);
        } catch (Exception exception) {
            throw new SOAPException("Unable to create SOAP connection factory: " + exception.getMessage());
        }
    }

    public abstract SOAPConnection createConnection() throws SOAPException;
}

