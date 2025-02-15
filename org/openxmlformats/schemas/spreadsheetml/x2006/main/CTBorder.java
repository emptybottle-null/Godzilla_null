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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr;
import org.w3c.dom.Node;

public interface CTBorder
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBorder.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctborderf935type");

    public CTBorderPr getLeft();

    public boolean isSetLeft();

    public void setLeft(CTBorderPr var1);

    public CTBorderPr addNewLeft();

    public void unsetLeft();

    public CTBorderPr getRight();

    public boolean isSetRight();

    public void setRight(CTBorderPr var1);

    public CTBorderPr addNewRight();

    public void unsetRight();

    public CTBorderPr getTop();

    public boolean isSetTop();

    public void setTop(CTBorderPr var1);

    public CTBorderPr addNewTop();

    public void unsetTop();

    public CTBorderPr getBottom();

    public boolean isSetBottom();

    public void setBottom(CTBorderPr var1);

    public CTBorderPr addNewBottom();

    public void unsetBottom();

    public CTBorderPr getDiagonal();

    public boolean isSetDiagonal();

    public void setDiagonal(CTBorderPr var1);

    public CTBorderPr addNewDiagonal();

    public void unsetDiagonal();

    public CTBorderPr getVertical();

    public boolean isSetVertical();

    public void setVertical(CTBorderPr var1);

    public CTBorderPr addNewVertical();

    public void unsetVertical();

    public CTBorderPr getHorizontal();

    public boolean isSetHorizontal();

    public void setHorizontal(CTBorderPr var1);

    public CTBorderPr addNewHorizontal();

    public void unsetHorizontal();

    public boolean getDiagonalUp();

    public XmlBoolean xgetDiagonalUp();

    public boolean isSetDiagonalUp();

    public void setDiagonalUp(boolean var1);

    public void xsetDiagonalUp(XmlBoolean var1);

    public void unsetDiagonalUp();

    public boolean getDiagonalDown();

    public XmlBoolean xgetDiagonalDown();

    public boolean isSetDiagonalDown();

    public void setDiagonalDown(boolean var1);

    public void xsetDiagonalDown(XmlBoolean var1);

    public void unsetDiagonalDown();

    public boolean getOutline();

    public XmlBoolean xgetOutline();

    public boolean isSetOutline();

    public void setOutline(boolean var1);

    public void xsetOutline(XmlBoolean var1);

    public void unsetOutline();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBorder.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBorder newInstance() {
            return (CTBorder)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBorder newInstance(XmlOptions xmlOptions) {
            return (CTBorder)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBorder parse(String string) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBorder parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBorder parse(File file) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBorder parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBorder parse(URL uRL) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBorder parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBorder parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBorder parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBorder parse(Reader reader) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBorder parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBorder parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBorder parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBorder parse(Node node) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBorder parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBorder parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBorder)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBorder parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBorder)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

