/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef
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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.w3c.dom.Node;

public interface CTAxDataSource
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTAxDataSource.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctaxdatasource1440type");

    public CTMultiLvlStrRef getMultiLvlStrRef();

    public boolean isSetMultiLvlStrRef();

    public void setMultiLvlStrRef(CTMultiLvlStrRef var1);

    public CTMultiLvlStrRef addNewMultiLvlStrRef();

    public void unsetMultiLvlStrRef();

    public CTNumRef getNumRef();

    public boolean isSetNumRef();

    public void setNumRef(CTNumRef var1);

    public CTNumRef addNewNumRef();

    public void unsetNumRef();

    public CTNumData getNumLit();

    public boolean isSetNumLit();

    public void setNumLit(CTNumData var1);

    public CTNumData addNewNumLit();

    public void unsetNumLit();

    public CTStrRef getStrRef();

    public boolean isSetStrRef();

    public void setStrRef(CTStrRef var1);

    public CTStrRef addNewStrRef();

    public void unsetStrRef();

    public CTStrData getStrLit();

    public boolean isSetStrLit();

    public void setStrLit(CTStrData var1);

    public CTStrData addNewStrLit();

    public void unsetStrLit();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTAxDataSource.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTAxDataSource newInstance() {
            return (CTAxDataSource)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTAxDataSource newInstance(XmlOptions xmlOptions) {
            return (CTAxDataSource)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTAxDataSource parse(String string) throws XmlException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTAxDataSource parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTAxDataSource parse(File file) throws XmlException, IOException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTAxDataSource parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTAxDataSource parse(URL uRL) throws XmlException, IOException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTAxDataSource parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTAxDataSource parse(InputStream inputStream) throws XmlException, IOException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTAxDataSource parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTAxDataSource parse(Reader reader) throws XmlException, IOException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTAxDataSource parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTAxDataSource parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTAxDataSource parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTAxDataSource parse(Node node) throws XmlException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTAxDataSource parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTAxDataSource parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTAxDataSource parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTAxDataSource)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

