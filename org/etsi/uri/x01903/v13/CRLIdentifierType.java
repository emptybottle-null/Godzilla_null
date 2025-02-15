/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigInteger;
import java.net.URL;
import java.util.Calendar;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CRLIdentifierType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CRLIdentifierType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("crlidentifiertypeb702type");

    public String getIssuer();

    public XmlString xgetIssuer();

    public void setIssuer(String var1);

    public void xsetIssuer(XmlString var1);

    public Calendar getIssueTime();

    public XmlDateTime xgetIssueTime();

    public void setIssueTime(Calendar var1);

    public void xsetIssueTime(XmlDateTime var1);

    public BigInteger getNumber();

    public XmlInteger xgetNumber();

    public boolean isSetNumber();

    public void setNumber(BigInteger var1);

    public void xsetNumber(XmlInteger var1);

    public void unsetNumber();

    public String getURI();

    public XmlAnyURI xgetURI();

    public boolean isSetURI();

    public void setURI(String var1);

    public void xsetURI(XmlAnyURI var1);

    public void unsetURI();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CRLIdentifierType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CRLIdentifierType newInstance() {
            return (CRLIdentifierType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CRLIdentifierType newInstance(XmlOptions xmlOptions) {
            return (CRLIdentifierType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CRLIdentifierType parse(String string) throws XmlException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CRLIdentifierType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CRLIdentifierType parse(File file) throws XmlException, IOException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CRLIdentifierType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CRLIdentifierType parse(URL uRL) throws XmlException, IOException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CRLIdentifierType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CRLIdentifierType parse(InputStream inputStream) throws XmlException, IOException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CRLIdentifierType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CRLIdentifierType parse(Reader reader) throws XmlException, IOException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CRLIdentifierType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CRLIdentifierType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CRLIdentifierType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CRLIdentifierType parse(Node node) throws XmlException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CRLIdentifierType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CRLIdentifierType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CRLIdentifierType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CRLIdentifierType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, null);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, xmlOptions);
        }

        private Factory() {
        }
    }
}

