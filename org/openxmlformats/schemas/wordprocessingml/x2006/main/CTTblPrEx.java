/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExChange
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExChange;
import org.w3c.dom.Node;

public interface CTTblPrEx
extends CTTblPrExBase {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTblPrEx.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttblprex863ftype");

    public CTTblPrExChange getTblPrExChange();

    public boolean isSetTblPrExChange();

    public void setTblPrExChange(CTTblPrExChange var1);

    public CTTblPrExChange addNewTblPrExChange();

    public void unsetTblPrExChange();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTblPrEx.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTblPrEx newInstance() {
            return (CTTblPrEx)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTblPrEx newInstance(XmlOptions xmlOptions) {
            return (CTTblPrEx)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTblPrEx parse(String string) throws XmlException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTblPrEx parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTblPrEx parse(File file) throws XmlException, IOException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTblPrEx parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTblPrEx parse(URL uRL) throws XmlException, IOException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTblPrEx parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTblPrEx parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTblPrEx parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTblPrEx parse(Reader reader) throws XmlException, IOException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTblPrEx parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTblPrEx parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTblPrEx parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTblPrEx parse(Node node) throws XmlException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTblPrEx parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTblPrEx parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTblPrEx parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTblPrEx)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

