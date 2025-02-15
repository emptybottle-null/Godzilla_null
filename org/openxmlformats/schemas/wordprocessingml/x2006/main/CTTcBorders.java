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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.w3c.dom.Node;

public interface CTTcBorders
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTcBorders.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttcbordersa5fatype");

    public CTBorder getTop();

    public boolean isSetTop();

    public void setTop(CTBorder var1);

    public CTBorder addNewTop();

    public void unsetTop();

    public CTBorder getLeft();

    public boolean isSetLeft();

    public void setLeft(CTBorder var1);

    public CTBorder addNewLeft();

    public void unsetLeft();

    public CTBorder getBottom();

    public boolean isSetBottom();

    public void setBottom(CTBorder var1);

    public CTBorder addNewBottom();

    public void unsetBottom();

    public CTBorder getRight();

    public boolean isSetRight();

    public void setRight(CTBorder var1);

    public CTBorder addNewRight();

    public void unsetRight();

    public CTBorder getInsideH();

    public boolean isSetInsideH();

    public void setInsideH(CTBorder var1);

    public CTBorder addNewInsideH();

    public void unsetInsideH();

    public CTBorder getInsideV();

    public boolean isSetInsideV();

    public void setInsideV(CTBorder var1);

    public CTBorder addNewInsideV();

    public void unsetInsideV();

    public CTBorder getTl2Br();

    public boolean isSetTl2Br();

    public void setTl2Br(CTBorder var1);

    public CTBorder addNewTl2Br();

    public void unsetTl2Br();

    public CTBorder getTr2Bl();

    public boolean isSetTr2Bl();

    public void setTr2Bl(CTBorder var1);

    public CTBorder addNewTr2Bl();

    public void unsetTr2Bl();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTcBorders.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTcBorders newInstance() {
            return (CTTcBorders)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTcBorders newInstance(XmlOptions xmlOptions) {
            return (CTTcBorders)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTcBorders parse(String string) throws XmlException {
            return (CTTcBorders)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTcBorders parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTcBorders)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTcBorders parse(File file) throws XmlException, IOException {
            return (CTTcBorders)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTcBorders parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcBorders)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTcBorders parse(URL uRL) throws XmlException, IOException {
            return (CTTcBorders)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTcBorders parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcBorders)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTcBorders parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTcBorders)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTcBorders parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcBorders)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTcBorders parse(Reader reader) throws XmlException, IOException {
            return (CTTcBorders)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTcBorders parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcBorders)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTcBorders parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTcBorders)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTcBorders parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTcBorders)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTcBorders parse(Node node) throws XmlException {
            return (CTTcBorders)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTcBorders parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTcBorders)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTcBorders parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTcBorders)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTcBorders parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTcBorders)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

