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
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStretchInfoProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties;
import org.w3c.dom.Node;

public interface CTBlipFillProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBlipFillProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctblipfillproperties0382type");

    public CTBlip getBlip();

    public boolean isSetBlip();

    public void setBlip(CTBlip var1);

    public CTBlip addNewBlip();

    public void unsetBlip();

    public CTRelativeRect getSrcRect();

    public boolean isSetSrcRect();

    public void setSrcRect(CTRelativeRect var1);

    public CTRelativeRect addNewSrcRect();

    public void unsetSrcRect();

    public CTTileInfoProperties getTile();

    public boolean isSetTile();

    public void setTile(CTTileInfoProperties var1);

    public CTTileInfoProperties addNewTile();

    public void unsetTile();

    public CTStretchInfoProperties getStretch();

    public boolean isSetStretch();

    public void setStretch(CTStretchInfoProperties var1);

    public CTStretchInfoProperties addNewStretch();

    public void unsetStretch();

    public long getDpi();

    public XmlUnsignedInt xgetDpi();

    public boolean isSetDpi();

    public void setDpi(long var1);

    public void xsetDpi(XmlUnsignedInt var1);

    public void unsetDpi();

    public boolean getRotWithShape();

    public XmlBoolean xgetRotWithShape();

    public boolean isSetRotWithShape();

    public void setRotWithShape(boolean var1);

    public void xsetRotWithShape(XmlBoolean var1);

    public void unsetRotWithShape();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBlipFillProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBlipFillProperties newInstance() {
            return (CTBlipFillProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBlipFillProperties newInstance(XmlOptions xmlOptions) {
            return (CTBlipFillProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBlipFillProperties parse(String string) throws XmlException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBlipFillProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBlipFillProperties parse(File file) throws XmlException, IOException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBlipFillProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBlipFillProperties parse(URL uRL) throws XmlException, IOException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBlipFillProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBlipFillProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBlipFillProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBlipFillProperties parse(Reader reader) throws XmlException, IOException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBlipFillProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBlipFillProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBlipFillProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBlipFillProperties parse(Node node) throws XmlException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBlipFillProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBlipFillProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBlipFillProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBlipFillProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

