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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOrientation;
import org.w3c.dom.Node;

public interface CTScaling
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTScaling.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctscaling1dfftype");

    public CTLogBase getLogBase();

    public boolean isSetLogBase();

    public void setLogBase(CTLogBase var1);

    public CTLogBase addNewLogBase();

    public void unsetLogBase();

    public CTOrientation getOrientation();

    public boolean isSetOrientation();

    public void setOrientation(CTOrientation var1);

    public CTOrientation addNewOrientation();

    public void unsetOrientation();

    public CTDouble getMax();

    public boolean isSetMax();

    public void setMax(CTDouble var1);

    public CTDouble addNewMax();

    public void unsetMax();

    public CTDouble getMin();

    public boolean isSetMin();

    public void setMin(CTDouble var1);

    public CTDouble addNewMin();

    public void unsetMin();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTScaling.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTScaling newInstance() {
            return (CTScaling)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTScaling newInstance(XmlOptions xmlOptions) {
            return (CTScaling)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTScaling parse(String string) throws XmlException {
            return (CTScaling)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTScaling parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTScaling)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTScaling parse(File file) throws XmlException, IOException {
            return (CTScaling)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTScaling parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScaling)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTScaling parse(URL uRL) throws XmlException, IOException {
            return (CTScaling)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTScaling parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScaling)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTScaling parse(InputStream inputStream) throws XmlException, IOException {
            return (CTScaling)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTScaling parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScaling)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTScaling parse(Reader reader) throws XmlException, IOException {
            return (CTScaling)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTScaling parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScaling)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTScaling parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTScaling)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTScaling parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTScaling)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTScaling parse(Node node) throws XmlException {
            return (CTScaling)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTScaling parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTScaling)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTScaling parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTScaling)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTScaling parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTScaling)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

