/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;
import org.w3c.dom.Node;

public interface CTTextTabStopList
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTextTabStopList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttexttabstoplistf539type");

    public List<CTTextTabStop> getTabList();

    @Deprecated
    public CTTextTabStop[] getTabArray();

    public CTTextTabStop getTabArray(int var1);

    public int sizeOfTabArray();

    public void setTabArray(CTTextTabStop[] var1);

    public void setTabArray(int var1, CTTextTabStop var2);

    public CTTextTabStop insertNewTab(int var1);

    public CTTextTabStop addNewTab();

    public void removeTab(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTextTabStopList.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTextTabStopList newInstance() {
            return (CTTextTabStopList)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTextTabStopList newInstance(XmlOptions xmlOptions) {
            return (CTTextTabStopList)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTextTabStopList parse(String string) throws XmlException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTextTabStopList parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTextTabStopList parse(File file) throws XmlException, IOException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTextTabStopList parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTextTabStopList parse(URL uRL) throws XmlException, IOException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTextTabStopList parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTextTabStopList parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTextTabStopList parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTextTabStopList parse(Reader reader) throws XmlException, IOException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTextTabStopList parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTextTabStopList parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTextTabStopList parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTextTabStopList parse(Node node) throws XmlException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTextTabStopList parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTextTabStopList parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTextTabStopList parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTextTabStopList)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

