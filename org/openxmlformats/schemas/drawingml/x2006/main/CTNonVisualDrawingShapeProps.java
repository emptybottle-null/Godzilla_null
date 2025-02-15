/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTShapeLocking
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeLocking;
import org.w3c.dom.Node;

public interface CTNonVisualDrawingShapeProps
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTNonVisualDrawingShapeProps.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctnonvisualdrawingshapepropsf17btype");

    public CTShapeLocking getSpLocks();

    public boolean isSetSpLocks();

    public void setSpLocks(CTShapeLocking var1);

    public CTShapeLocking addNewSpLocks();

    public void unsetSpLocks();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public boolean getTxBox();

    public XmlBoolean xgetTxBox();

    public boolean isSetTxBox();

    public void setTxBox(boolean var1);

    public void xsetTxBox(XmlBoolean var1);

    public void unsetTxBox();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTNonVisualDrawingShapeProps.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTNonVisualDrawingShapeProps newInstance() {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTNonVisualDrawingShapeProps newInstance(XmlOptions xmlOptions) {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTNonVisualDrawingShapeProps parse(String string) throws XmlException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTNonVisualDrawingShapeProps parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTNonVisualDrawingShapeProps parse(File file) throws XmlException, IOException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTNonVisualDrawingShapeProps parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTNonVisualDrawingShapeProps parse(URL uRL) throws XmlException, IOException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTNonVisualDrawingShapeProps parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTNonVisualDrawingShapeProps parse(InputStream inputStream) throws XmlException, IOException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTNonVisualDrawingShapeProps parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTNonVisualDrawingShapeProps parse(Reader reader) throws XmlException, IOException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTNonVisualDrawingShapeProps parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTNonVisualDrawingShapeProps parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTNonVisualDrawingShapeProps parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTNonVisualDrawingShapeProps parse(Node node) throws XmlException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTNonVisualDrawingShapeProps parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTNonVisualDrawingShapeProps parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTNonVisualDrawingShapeProps parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTNonVisualDrawingShapeProps)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

