/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLang;
import org.w3c.dom.Node;

public interface CTLanguage
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLanguage.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlanguage7b90type");

    public Object getVal();

    public STLang xgetVal();

    public boolean isSetVal();

    public void setVal(Object var1);

    public void xsetVal(STLang var1);

    public void unsetVal();

    public Object getEastAsia();

    public STLang xgetEastAsia();

    public boolean isSetEastAsia();

    public void setEastAsia(Object var1);

    public void xsetEastAsia(STLang var1);

    public void unsetEastAsia();

    public Object getBidi();

    public STLang xgetBidi();

    public boolean isSetBidi();

    public void setBidi(Object var1);

    public void xsetBidi(STLang var1);

    public void unsetBidi();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLanguage.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLanguage newInstance() {
            return (CTLanguage)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLanguage newInstance(XmlOptions xmlOptions) {
            return (CTLanguage)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLanguage parse(String string) throws XmlException {
            return (CTLanguage)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLanguage parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLanguage)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLanguage parse(File file) throws XmlException, IOException {
            return (CTLanguage)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLanguage parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLanguage)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLanguage parse(URL uRL) throws XmlException, IOException {
            return (CTLanguage)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLanguage parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLanguage)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLanguage parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLanguage)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLanguage parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLanguage)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLanguage parse(Reader reader) throws XmlException, IOException {
            return (CTLanguage)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLanguage parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLanguage)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLanguage parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLanguage)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLanguage parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLanguage)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLanguage parse(Node node) throws XmlException {
            return (CTLanguage)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLanguage parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLanguage)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLanguage parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLanguage)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLanguage parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLanguage)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

