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
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPictureNonVisual;
import org.w3c.dom.Node;

public interface CTPicture
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPicture.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpicturee028type");

    public CTPictureNonVisual getNvPicPr();

    public void setNvPicPr(CTPictureNonVisual var1);

    public CTPictureNonVisual addNewNvPicPr();

    public CTBlipFillProperties getBlipFill();

    public void setBlipFill(CTBlipFillProperties var1);

    public CTBlipFillProperties addNewBlipFill();

    public CTShapeProperties getSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public CTShapeStyle getStyle();

    public boolean isSetStyle();

    public void setStyle(CTShapeStyle var1);

    public CTShapeStyle addNewStyle();

    public void unsetStyle();

    public String getMacro();

    public XmlString xgetMacro();

    public boolean isSetMacro();

    public void setMacro(String var1);

    public void xsetMacro(XmlString var1);

    public void unsetMacro();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPicture.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPicture newInstance() {
            return (CTPicture)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPicture newInstance(XmlOptions xmlOptions) {
            return (CTPicture)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPicture parse(String string) throws XmlException {
            return (CTPicture)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPicture parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPicture)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPicture parse(File file) throws XmlException, IOException {
            return (CTPicture)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPicture parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPicture)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPicture parse(URL uRL) throws XmlException, IOException {
            return (CTPicture)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPicture parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPicture)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPicture parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPicture)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPicture parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPicture)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPicture parse(Reader reader) throws XmlException, IOException {
            return (CTPicture)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPicture parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPicture)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPicture parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPicture)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPicture parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPicture)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPicture parse(Node node) throws XmlException {
            return (CTPicture)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPicture parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPicture)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPicture parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPicture)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPicture parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPicture)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

