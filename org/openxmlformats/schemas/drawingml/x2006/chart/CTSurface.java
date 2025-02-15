/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.w3c.dom.Node;

public interface CTSurface
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSurface.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsurface5a19type");

    public CTUnsignedInt getThickness();

    public boolean isSetThickness();

    public void setThickness(CTUnsignedInt var1);

    public CTUnsignedInt addNewThickness();

    public void unsetThickness();

    public CTShapeProperties getSpPr();

    public boolean isSetSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public void unsetSpPr();

    public CTPictureOptions getPictureOptions();

    public boolean isSetPictureOptions();

    public void setPictureOptions(CTPictureOptions var1);

    public CTPictureOptions addNewPictureOptions();

    public void unsetPictureOptions();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSurface.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSurface newInstance() {
            return (CTSurface)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSurface newInstance(XmlOptions xmlOptions) {
            return (CTSurface)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSurface parse(String string) throws XmlException {
            return (CTSurface)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSurface parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSurface)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSurface parse(File file) throws XmlException, IOException {
            return (CTSurface)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSurface parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurface)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSurface parse(URL uRL) throws XmlException, IOException {
            return (CTSurface)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSurface parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurface)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSurface parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSurface)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSurface parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurface)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSurface parse(Reader reader) throws XmlException, IOException {
            return (CTSurface)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSurface parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurface)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSurface parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSurface)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSurface parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSurface)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSurface parse(Node node) throws XmlException {
            return (CTSurface)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSurface parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSurface)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSurface parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSurface)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSurface parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSurface)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

