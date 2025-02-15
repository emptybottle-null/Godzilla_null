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
import java.util.List;
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSqref;
import org.w3c.dom.Node;

public interface CTSelection
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSelection.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctselectionca2btype");

    public STPane.Enum getPane();

    public STPane xgetPane();

    public boolean isSetPane();

    public void setPane(STPane.Enum var1);

    public void xsetPane(STPane var1);

    public void unsetPane();

    public String getActiveCell();

    public STCellRef xgetActiveCell();

    public boolean isSetActiveCell();

    public void setActiveCell(String var1);

    public void xsetActiveCell(STCellRef var1);

    public void unsetActiveCell();

    public long getActiveCellId();

    public XmlUnsignedInt xgetActiveCellId();

    public boolean isSetActiveCellId();

    public void setActiveCellId(long var1);

    public void xsetActiveCellId(XmlUnsignedInt var1);

    public void unsetActiveCellId();

    public List getSqref();

    public STSqref xgetSqref();

    public boolean isSetSqref();

    public void setSqref(List var1);

    public void xsetSqref(STSqref var1);

    public void unsetSqref();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSelection.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSelection newInstance() {
            return (CTSelection)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSelection newInstance(XmlOptions xmlOptions) {
            return (CTSelection)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSelection parse(String string) throws XmlException {
            return (CTSelection)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSelection parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSelection)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSelection parse(File file) throws XmlException, IOException {
            return (CTSelection)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSelection parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSelection)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSelection parse(URL uRL) throws XmlException, IOException {
            return (CTSelection)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSelection parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSelection)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSelection parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSelection)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSelection parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSelection)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSelection parse(Reader reader) throws XmlException, IOException {
            return (CTSelection)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSelection parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSelection)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSelection parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSelection)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSelection parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSelection)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSelection parse(Node node) throws XmlException {
            return (CTSelection)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSelection parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSelection)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSelection parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSelection)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSelection parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSelection)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

