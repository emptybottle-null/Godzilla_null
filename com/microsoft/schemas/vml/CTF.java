/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.vml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTF
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTF.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctfbc3atype");

    public String getEqn();

    public XmlString xgetEqn();

    public boolean isSetEqn();

    public void setEqn(String var1);

    public void xsetEqn(XmlString var1);

    public void unsetEqn();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTF.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTF newInstance() {
            return (CTF)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTF newInstance(XmlOptions xmlOptions) {
            return (CTF)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTF parse(String string) throws XmlException {
            return (CTF)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTF parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTF)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTF parse(File file) throws XmlException, IOException {
            return (CTF)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTF parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTF)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTF parse(URL uRL) throws XmlException, IOException {
            return (CTF)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTF parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTF)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTF parse(InputStream inputStream) throws XmlException, IOException {
            return (CTF)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTF parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTF)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTF parse(Reader reader) throws XmlException, IOException {
            return (CTF)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTF parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTF)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTF parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTF)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTF parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTF)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTF parse(Node node) throws XmlException {
            return (CTF)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTF parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTF)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTF parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTF)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTF parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTF)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

