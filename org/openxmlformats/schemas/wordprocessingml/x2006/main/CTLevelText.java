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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.w3c.dom.Node;

public interface CTLevelText
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLevelText.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctleveltext0621type");

    public String getVal();

    public STString xgetVal();

    public boolean isSetVal();

    public void setVal(String var1);

    public void xsetVal(STString var1);

    public void unsetVal();

    public STOnOff.Enum getNull();

    public STOnOff xgetNull();

    public boolean isSetNull();

    public void setNull(STOnOff.Enum var1);

    public void xsetNull(STOnOff var1);

    public void unsetNull();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLevelText.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLevelText newInstance() {
            return (CTLevelText)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLevelText newInstance(XmlOptions xmlOptions) {
            return (CTLevelText)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLevelText parse(String string) throws XmlException {
            return (CTLevelText)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLevelText parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLevelText)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLevelText parse(File file) throws XmlException, IOException {
            return (CTLevelText)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLevelText parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLevelText)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLevelText parse(URL uRL) throws XmlException, IOException {
            return (CTLevelText)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLevelText parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLevelText)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLevelText parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLevelText)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLevelText parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLevelText)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLevelText parse(Reader reader) throws XmlException, IOException {
            return (CTLevelText)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLevelText parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLevelText)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLevelText parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLevelText)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLevelText parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLevelText)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLevelText parse(Node node) throws XmlException {
            return (CTLevelText)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLevelText parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLevelText)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLevelText parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLevelText)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLevelText parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLevelText)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

