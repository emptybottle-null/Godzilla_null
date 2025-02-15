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
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ResponderIDType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ResponderIDType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("responderidtype55b9type");

    public String getByName();

    public XmlString xgetByName();

    public boolean isSetByName();

    public void setByName(String var1);

    public void xsetByName(XmlString var1);

    public void unsetByName();

    public byte[] getByKey();

    public XmlBase64Binary xgetByKey();

    public boolean isSetByKey();

    public void setByKey(byte[] var1);

    public void xsetByKey(XmlBase64Binary var1);

    public void unsetByKey();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(ResponderIDType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static ResponderIDType newInstance() {
            return (ResponderIDType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static ResponderIDType newInstance(XmlOptions xmlOptions) {
            return (ResponderIDType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static ResponderIDType parse(String string) throws XmlException {
            return (ResponderIDType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static ResponderIDType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (ResponderIDType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static ResponderIDType parse(File file) throws XmlException, IOException {
            return (ResponderIDType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static ResponderIDType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ResponderIDType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static ResponderIDType parse(URL uRL) throws XmlException, IOException {
            return (ResponderIDType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static ResponderIDType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ResponderIDType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static ResponderIDType parse(InputStream inputStream) throws XmlException, IOException {
            return (ResponderIDType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static ResponderIDType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ResponderIDType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static ResponderIDType parse(Reader reader) throws XmlException, IOException {
            return (ResponderIDType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static ResponderIDType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ResponderIDType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static ResponderIDType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (ResponderIDType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static ResponderIDType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (ResponderIDType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static ResponderIDType parse(Node node) throws XmlException {
            return (ResponderIDType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static ResponderIDType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (ResponderIDType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static ResponderIDType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (ResponderIDType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static ResponderIDType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (ResponderIDType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

