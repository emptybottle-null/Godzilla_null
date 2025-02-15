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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrClear;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrType;
import org.w3c.dom.Node;

public interface CTBr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbr7dd8type");

    public STBrType.Enum getType();

    public STBrType xgetType();

    public boolean isSetType();

    public void setType(STBrType.Enum var1);

    public void xsetType(STBrType var1);

    public void unsetType();

    public STBrClear.Enum getClear();

    public STBrClear xgetClear();

    public boolean isSetClear();

    public void setClear(STBrClear.Enum var1);

    public void xsetClear(STBrClear var1);

    public void unsetClear();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBr newInstance() {
            return (CTBr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBr newInstance(XmlOptions xmlOptions) {
            return (CTBr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBr parse(String string) throws XmlException {
            return (CTBr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBr parse(File file) throws XmlException, IOException {
            return (CTBr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBr parse(URL uRL) throws XmlException, IOException {
            return (CTBr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBr parse(Reader reader) throws XmlException, IOException {
            return (CTBr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBr parse(Node node) throws XmlException {
            return (CTBr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

