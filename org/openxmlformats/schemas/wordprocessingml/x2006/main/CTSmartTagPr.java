/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAttr
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAttr;
import org.w3c.dom.Node;

public interface CTSmartTagPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSmartTagPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsmarttagprf715type");

    public List<CTAttr> getAttrList();

    @Deprecated
    public CTAttr[] getAttrArray();

    public CTAttr getAttrArray(int var1);

    public int sizeOfAttrArray();

    public void setAttrArray(CTAttr[] var1);

    public void setAttrArray(int var1, CTAttr var2);

    public CTAttr insertNewAttr(int var1);

    public CTAttr addNewAttr();

    public void removeAttr(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSmartTagPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSmartTagPr newInstance() {
            return (CTSmartTagPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSmartTagPr newInstance(XmlOptions xmlOptions) {
            return (CTSmartTagPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSmartTagPr parse(String string) throws XmlException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSmartTagPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSmartTagPr parse(File file) throws XmlException, IOException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSmartTagPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSmartTagPr parse(URL uRL) throws XmlException, IOException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSmartTagPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSmartTagPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSmartTagPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSmartTagPr parse(Reader reader) throws XmlException, IOException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSmartTagPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSmartTagPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSmartTagPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSmartTagPr parse(Node node) throws XmlException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSmartTagPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSmartTagPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSmartTagPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSmartTagPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

