/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTPhoneticRun
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPhoneticRun.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctphoneticrun2b2atype");

    public String getT();

    public STXstring xgetT();

    public void setT(String var1);

    public void xsetT(STXstring var1);

    public long getSb();

    public XmlUnsignedInt xgetSb();

    public void setSb(long var1);

    public void xsetSb(XmlUnsignedInt var1);

    public long getEb();

    public XmlUnsignedInt xgetEb();

    public void setEb(long var1);

    public void xsetEb(XmlUnsignedInt var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPhoneticRun.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPhoneticRun newInstance() {
            return (CTPhoneticRun)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPhoneticRun newInstance(XmlOptions xmlOptions) {
            return (CTPhoneticRun)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPhoneticRun parse(String string) throws XmlException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPhoneticRun parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPhoneticRun parse(File file) throws XmlException, IOException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPhoneticRun parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPhoneticRun parse(URL uRL) throws XmlException, IOException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPhoneticRun parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPhoneticRun parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPhoneticRun parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPhoneticRun parse(Reader reader) throws XmlException, IOException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPhoneticRun parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPhoneticRun parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPhoneticRun parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPhoneticRun parse(Node node) throws XmlException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPhoneticRun parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPhoneticRun parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPhoneticRun parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPhoneticRun)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

