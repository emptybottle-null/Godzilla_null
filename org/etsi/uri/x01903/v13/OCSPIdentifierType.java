/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Calendar;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.etsi.uri.x01903.v13.ResponderIDType;
import org.w3c.dom.Node;

public interface OCSPIdentifierType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(OCSPIdentifierType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ocspidentifiertype3968type");

    public ResponderIDType getResponderID();

    public void setResponderID(ResponderIDType var1);

    public ResponderIDType addNewResponderID();

    public Calendar getProducedAt();

    public XmlDateTime xgetProducedAt();

    public void setProducedAt(Calendar var1);

    public void xsetProducedAt(XmlDateTime var1);

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(OCSPIdentifierType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static OCSPIdentifierType newInstance() {
            return (OCSPIdentifierType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static OCSPIdentifierType newInstance(XmlOptions xmlOptions) {
            return (OCSPIdentifierType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static OCSPIdentifierType parse(String string) throws XmlException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static OCSPIdentifierType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static OCSPIdentifierType parse(File file) throws XmlException, IOException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static OCSPIdentifierType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static OCSPIdentifierType parse(URL uRL) throws XmlException, IOException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static OCSPIdentifierType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static OCSPIdentifierType parse(InputStream inputStream) throws XmlException, IOException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static OCSPIdentifierType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static OCSPIdentifierType parse(Reader reader) throws XmlException, IOException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static OCSPIdentifierType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static OCSPIdentifierType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static OCSPIdentifierType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static OCSPIdentifierType parse(Node node) throws XmlException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static OCSPIdentifierType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static OCSPIdentifierType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static OCSPIdentifierType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (OCSPIdentifierType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

