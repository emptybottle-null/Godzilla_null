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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtEndPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;
import org.w3c.dom.Node;

public interface CTSdtCell
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSdtCell.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsdtcell626dtype");

    public CTSdtPr getSdtPr();

    public boolean isSetSdtPr();

    public void setSdtPr(CTSdtPr var1);

    public CTSdtPr addNewSdtPr();

    public void unsetSdtPr();

    public CTSdtEndPr getSdtEndPr();

    public boolean isSetSdtEndPr();

    public void setSdtEndPr(CTSdtEndPr var1);

    public CTSdtEndPr addNewSdtEndPr();

    public void unsetSdtEndPr();

    public CTSdtContentCell getSdtContent();

    public boolean isSetSdtContent();

    public void setSdtContent(CTSdtContentCell var1);

    public CTSdtContentCell addNewSdtContent();

    public void unsetSdtContent();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSdtCell.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSdtCell newInstance() {
            return (CTSdtCell)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSdtCell newInstance(XmlOptions xmlOptions) {
            return (CTSdtCell)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSdtCell parse(String string) throws XmlException {
            return (CTSdtCell)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSdtCell parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtCell)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSdtCell parse(File file) throws XmlException, IOException {
            return (CTSdtCell)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSdtCell parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtCell)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSdtCell parse(URL uRL) throws XmlException, IOException {
            return (CTSdtCell)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSdtCell parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtCell)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSdtCell parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSdtCell)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSdtCell parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtCell)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSdtCell parse(Reader reader) throws XmlException, IOException {
            return (CTSdtCell)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSdtCell parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtCell)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSdtCell parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSdtCell)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSdtCell parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtCell)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSdtCell parse(Node node) throws XmlException {
            return (CTSdtCell)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSdtCell parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtCell)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSdtCell parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSdtCell)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSdtCell parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSdtCell)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

