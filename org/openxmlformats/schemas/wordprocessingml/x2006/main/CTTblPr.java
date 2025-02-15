/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrChange
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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrChange;
import org.w3c.dom.Node;

public interface CTTblPr
extends CTTblPrBase {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTblPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttblpr5b72type");

    public CTTblPrChange getTblPrChange();

    public boolean isSetTblPrChange();

    public void setTblPrChange(CTTblPrChange var1);

    public CTTblPrChange addNewTblPrChange();

    public void unsetTblPrChange();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTblPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTblPr newInstance() {
            return (CTTblPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTblPr newInstance(XmlOptions xmlOptions) {
            return (CTTblPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTblPr parse(String string) throws XmlException {
            return (CTTblPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTblPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTblPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTblPr parse(File file) throws XmlException, IOException {
            return (CTTblPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTblPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTblPr parse(URL uRL) throws XmlException, IOException {
            return (CTTblPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTblPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTblPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTblPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTblPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTblPr parse(Reader reader) throws XmlException, IOException {
            return (CTTblPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTblPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTblPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTblPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTblPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTblPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTblPr parse(Node node) throws XmlException {
            return (CTTblPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTblPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTblPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTblPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTblPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTblPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTblPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

