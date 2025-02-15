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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode;
import org.w3c.dom.Node;

public interface CTGradientFillProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTGradientFillProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctgradientfillproperties81c1type");

    public CTGradientStopList getGsLst();

    public boolean isSetGsLst();

    public void setGsLst(CTGradientStopList var1);

    public CTGradientStopList addNewGsLst();

    public void unsetGsLst();

    public CTLinearShadeProperties getLin();

    public boolean isSetLin();

    public void setLin(CTLinearShadeProperties var1);

    public CTLinearShadeProperties addNewLin();

    public void unsetLin();

    public CTPathShadeProperties getPath();

    public boolean isSetPath();

    public void setPath(CTPathShadeProperties var1);

    public CTPathShadeProperties addNewPath();

    public void unsetPath();

    public CTRelativeRect getTileRect();

    public boolean isSetTileRect();

    public void setTileRect(CTRelativeRect var1);

    public CTRelativeRect addNewTileRect();

    public void unsetTileRect();

    public STTileFlipMode.Enum getFlip();

    public STTileFlipMode xgetFlip();

    public boolean isSetFlip();

    public void setFlip(STTileFlipMode.Enum var1);

    public void xsetFlip(STTileFlipMode var1);

    public void unsetFlip();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTGradientFillProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTGradientFillProperties newInstance() {
            return (CTGradientFillProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTGradientFillProperties newInstance(XmlOptions xmlOptions) {
            return (CTGradientFillProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTGradientFillProperties parse(String string) throws XmlException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTGradientFillProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTGradientFillProperties parse(File file) throws XmlException, IOException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTGradientFillProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTGradientFillProperties parse(URL uRL) throws XmlException, IOException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTGradientFillProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTGradientFillProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTGradientFillProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTGradientFillProperties parse(Reader reader) throws XmlException, IOException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTGradientFillProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTGradientFillProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTGradientFillProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTGradientFillProperties parse(Node node) throws XmlException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTGradientFillProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTGradientFillProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTGradientFillProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTGradientFillProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

