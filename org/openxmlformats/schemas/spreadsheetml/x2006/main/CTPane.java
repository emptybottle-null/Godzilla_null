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
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPaneState;
import org.w3c.dom.Node;

public interface CTPane
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPane.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpaneaab1type");

    public double getXSplit();

    public XmlDouble xgetXSplit();

    public boolean isSetXSplit();

    public void setXSplit(double var1);

    public void xsetXSplit(XmlDouble var1);

    public void unsetXSplit();

    public double getYSplit();

    public XmlDouble xgetYSplit();

    public boolean isSetYSplit();

    public void setYSplit(double var1);

    public void xsetYSplit(XmlDouble var1);

    public void unsetYSplit();

    public String getTopLeftCell();

    public STCellRef xgetTopLeftCell();

    public boolean isSetTopLeftCell();

    public void setTopLeftCell(String var1);

    public void xsetTopLeftCell(STCellRef var1);

    public void unsetTopLeftCell();

    public STPane.Enum getActivePane();

    public STPane xgetActivePane();

    public boolean isSetActivePane();

    public void setActivePane(STPane.Enum var1);

    public void xsetActivePane(STPane var1);

    public void unsetActivePane();

    public STPaneState.Enum getState();

    public STPaneState xgetState();

    public boolean isSetState();

    public void setState(STPaneState.Enum var1);

    public void xsetState(STPaneState var1);

    public void unsetState();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPane.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPane newInstance() {
            return (CTPane)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPane newInstance(XmlOptions xmlOptions) {
            return (CTPane)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPane parse(String string) throws XmlException {
            return (CTPane)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPane parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPane)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPane parse(File file) throws XmlException, IOException {
            return (CTPane)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPane parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPane)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPane parse(URL uRL) throws XmlException, IOException {
            return (CTPane)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPane parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPane)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPane parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPane)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPane parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPane)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPane parse(Reader reader) throws XmlException, IOException {
            return (CTPane)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPane parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPane)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPane parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPane)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPane parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPane)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPane parse(Node node) throws XmlException {
            return (CTPane)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPane parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPane)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPane parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPane)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPane parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPane)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

