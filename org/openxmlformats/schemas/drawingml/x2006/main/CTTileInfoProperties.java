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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STRectAlignment;
import org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode;
import org.w3c.dom.Node;

public interface CTTileInfoProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTileInfoProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttileinfoproperties2featype");

    public long getTx();

    public STCoordinate xgetTx();

    public boolean isSetTx();

    public void setTx(long var1);

    public void xsetTx(STCoordinate var1);

    public void unsetTx();

    public long getTy();

    public STCoordinate xgetTy();

    public boolean isSetTy();

    public void setTy(long var1);

    public void xsetTy(STCoordinate var1);

    public void unsetTy();

    public int getSx();

    public STPercentage xgetSx();

    public boolean isSetSx();

    public void setSx(int var1);

    public void xsetSx(STPercentage var1);

    public void unsetSx();

    public int getSy();

    public STPercentage xgetSy();

    public boolean isSetSy();

    public void setSy(int var1);

    public void xsetSy(STPercentage var1);

    public void unsetSy();

    public STTileFlipMode.Enum getFlip();

    public STTileFlipMode xgetFlip();

    public boolean isSetFlip();

    public void setFlip(STTileFlipMode.Enum var1);

    public void xsetFlip(STTileFlipMode var1);

    public void unsetFlip();

    public STRectAlignment.Enum getAlgn();

    public STRectAlignment xgetAlgn();

    public boolean isSetAlgn();

    public void setAlgn(STRectAlignment.Enum var1);

    public void xsetAlgn(STRectAlignment var1);

    public void unsetAlgn();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTileInfoProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTileInfoProperties newInstance() {
            return (CTTileInfoProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTileInfoProperties newInstance(XmlOptions xmlOptions) {
            return (CTTileInfoProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTileInfoProperties parse(String string) throws XmlException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTileInfoProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTileInfoProperties parse(File file) throws XmlException, IOException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTileInfoProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTileInfoProperties parse(URL uRL) throws XmlException, IOException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTileInfoProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTileInfoProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTileInfoProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTileInfoProperties parse(Reader reader) throws XmlException, IOException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTileInfoProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTileInfoProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTileInfoProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTileInfoProperties parse(Node node) throws XmlException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTileInfoProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTileInfoProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTileInfoProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTileInfoProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

