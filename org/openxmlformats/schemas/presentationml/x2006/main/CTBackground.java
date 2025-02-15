/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties;
import org.w3c.dom.Node;

public interface CTBackground
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBackground.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbackground36f7type");

    public CTBackgroundProperties getBgPr();

    public boolean isSetBgPr();

    public void setBgPr(CTBackgroundProperties var1);

    public CTBackgroundProperties addNewBgPr();

    public void unsetBgPr();

    public CTStyleMatrixReference getBgRef();

    public boolean isSetBgRef();

    public void setBgRef(CTStyleMatrixReference var1);

    public CTStyleMatrixReference addNewBgRef();

    public void unsetBgRef();

    public STBlackWhiteMode.Enum getBwMode();

    public STBlackWhiteMode xgetBwMode();

    public boolean isSetBwMode();

    public void setBwMode(STBlackWhiteMode.Enum var1);

    public void xsetBwMode(STBlackWhiteMode var1);

    public void unsetBwMode();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBackground.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBackground newInstance() {
            return (CTBackground)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBackground newInstance(XmlOptions xmlOptions) {
            return (CTBackground)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBackground parse(String string) throws XmlException {
            return (CTBackground)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBackground parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBackground)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBackground parse(File file) throws XmlException, IOException {
            return (CTBackground)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBackground parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBackground)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBackground parse(URL uRL) throws XmlException, IOException {
            return (CTBackground)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBackground parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBackground)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBackground parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBackground)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBackground parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBackground)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBackground parse(Reader reader) throws XmlException, IOException {
            return (CTBackground)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBackground parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBackground)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBackground parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBackground)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBackground parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBackground)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBackground parse(Node node) throws XmlException {
            return (CTBackground)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBackground parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBackground)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBackground parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBackground)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBackground parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBackground)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

