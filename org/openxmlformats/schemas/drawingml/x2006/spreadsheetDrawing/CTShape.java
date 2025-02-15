/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShapeNonVisual;
import org.w3c.dom.Node;

public interface CTShape
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTShape.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctshapee40btype");

    public CTShapeNonVisual getNvSpPr();

    public void setNvSpPr(CTShapeNonVisual var1);

    public CTShapeNonVisual addNewNvSpPr();

    public CTShapeProperties getSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public CTShapeStyle getStyle();

    public boolean isSetStyle();

    public void setStyle(CTShapeStyle var1);

    public CTShapeStyle addNewStyle();

    public void unsetStyle();

    public CTTextBody getTxBody();

    public boolean isSetTxBody();

    public void setTxBody(CTTextBody var1);

    public CTTextBody addNewTxBody();

    public void unsetTxBody();

    public String getMacro();

    public XmlString xgetMacro();

    public boolean isSetMacro();

    public void setMacro(String var1);

    public void xsetMacro(XmlString var1);

    public void unsetMacro();

    public String getTextlink();

    public XmlString xgetTextlink();

    public boolean isSetTextlink();

    public void setTextlink(String var1);

    public void xsetTextlink(XmlString var1);

    public void unsetTextlink();

    public boolean getFLocksText();

    public XmlBoolean xgetFLocksText();

    public boolean isSetFLocksText();

    public void setFLocksText(boolean var1);

    public void xsetFLocksText(XmlBoolean var1);

    public void unsetFLocksText();

    public boolean getFPublished();

    public XmlBoolean xgetFPublished();

    public boolean isSetFPublished();

    public void setFPublished(boolean var1);

    public void xsetFPublished(XmlBoolean var1);

    public void unsetFPublished();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTShape.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTShape newInstance() {
            return (CTShape)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTShape newInstance(XmlOptions xmlOptions) {
            return (CTShape)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTShape parse(String string) throws XmlException {
            return (CTShape)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTShape parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTShape)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTShape parse(File file) throws XmlException, IOException {
            return (CTShape)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTShape parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShape)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTShape parse(URL uRL) throws XmlException, IOException {
            return (CTShape)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTShape parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShape)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTShape parse(InputStream inputStream) throws XmlException, IOException {
            return (CTShape)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTShape parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShape)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTShape parse(Reader reader) throws XmlException, IOException {
            return (CTShape)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTShape parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShape)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTShape parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTShape)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTShape parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTShape)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTShape parse(Node node) throws XmlException {
            return (CTShape)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTShape parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTShape)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTShape parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTShape)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTShape parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTShape)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

