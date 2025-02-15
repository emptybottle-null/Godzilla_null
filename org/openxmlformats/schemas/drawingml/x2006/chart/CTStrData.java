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
import java.util.List;
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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrVal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.w3c.dom.Node;

public interface CTStrData
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTStrData.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctstrdatad58btype");

    public CTUnsignedInt getPtCount();

    public boolean isSetPtCount();

    public void setPtCount(CTUnsignedInt var1);

    public CTUnsignedInt addNewPtCount();

    public void unsetPtCount();

    public List<CTStrVal> getPtList();

    @Deprecated
    public CTStrVal[] getPtArray();

    public CTStrVal getPtArray(int var1);

    public int sizeOfPtArray();

    public void setPtArray(CTStrVal[] var1);

    public void setPtArray(int var1, CTStrVal var2);

    public CTStrVal insertNewPt(int var1);

    public CTStrVal addNewPt();

    public void removePt(int var1);

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTStrData.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTStrData newInstance() {
            return (CTStrData)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTStrData newInstance(XmlOptions xmlOptions) {
            return (CTStrData)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTStrData parse(String string) throws XmlException {
            return (CTStrData)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTStrData parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTStrData)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTStrData parse(File file) throws XmlException, IOException {
            return (CTStrData)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTStrData parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStrData)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTStrData parse(URL uRL) throws XmlException, IOException {
            return (CTStrData)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTStrData parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStrData)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTStrData parse(InputStream inputStream) throws XmlException, IOException {
            return (CTStrData)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTStrData parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStrData)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTStrData parse(Reader reader) throws XmlException, IOException {
            return (CTStrData)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTStrData parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStrData)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTStrData parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTStrData)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTStrData parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTStrData)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTStrData parse(Node node) throws XmlException {
            return (CTStrData)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTStrData parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTStrData)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTStrData parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTStrData)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTStrData parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTStrData)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

