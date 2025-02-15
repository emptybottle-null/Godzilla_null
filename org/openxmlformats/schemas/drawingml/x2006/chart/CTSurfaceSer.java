/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.w3c.dom.Node;

public interface CTSurfaceSer
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSurfaceSer.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsurfaceser3ca9type");

    public CTUnsignedInt getIdx();

    public void setIdx(CTUnsignedInt var1);

    public CTUnsignedInt addNewIdx();

    public CTUnsignedInt getOrder();

    public void setOrder(CTUnsignedInt var1);

    public CTUnsignedInt addNewOrder();

    public CTSerTx getTx();

    public boolean isSetTx();

    public void setTx(CTSerTx var1);

    public CTSerTx addNewTx();

    public void unsetTx();

    public CTShapeProperties getSpPr();

    public boolean isSetSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public void unsetSpPr();

    public CTAxDataSource getCat();

    public boolean isSetCat();

    public void setCat(CTAxDataSource var1);

    public CTAxDataSource addNewCat();

    public void unsetCat();

    public CTNumDataSource getVal();

    public boolean isSetVal();

    public void setVal(CTNumDataSource var1);

    public CTNumDataSource addNewVal();

    public void unsetVal();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSurfaceSer.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSurfaceSer newInstance() {
            return (CTSurfaceSer)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSurfaceSer newInstance(XmlOptions xmlOptions) {
            return (CTSurfaceSer)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSurfaceSer parse(String string) throws XmlException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSurfaceSer parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSurfaceSer parse(File file) throws XmlException, IOException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSurfaceSer parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSurfaceSer parse(URL uRL) throws XmlException, IOException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSurfaceSer parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSurfaceSer parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSurfaceSer parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSurfaceSer parse(Reader reader) throws XmlException, IOException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSurfaceSer parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSurfaceSer parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSurfaceSer parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSurfaceSer parse(Node node) throws XmlException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSurfaceSer parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSurfaceSer parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSurfaceSer parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSurfaceSer)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

