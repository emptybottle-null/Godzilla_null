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
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;
import org.w3c.dom.Node;

public interface CTAdjustHandleList
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTAdjustHandleList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctadjusthandlelistfdb0type");

    public List<CTXYAdjustHandle> getAhXYList();

    @Deprecated
    public CTXYAdjustHandle[] getAhXYArray();

    public CTXYAdjustHandle getAhXYArray(int var1);

    public int sizeOfAhXYArray();

    public void setAhXYArray(CTXYAdjustHandle[] var1);

    public void setAhXYArray(int var1, CTXYAdjustHandle var2);

    public CTXYAdjustHandle insertNewAhXY(int var1);

    public CTXYAdjustHandle addNewAhXY();

    public void removeAhXY(int var1);

    public List<CTPolarAdjustHandle> getAhPolarList();

    @Deprecated
    public CTPolarAdjustHandle[] getAhPolarArray();

    public CTPolarAdjustHandle getAhPolarArray(int var1);

    public int sizeOfAhPolarArray();

    public void setAhPolarArray(CTPolarAdjustHandle[] var1);

    public void setAhPolarArray(int var1, CTPolarAdjustHandle var2);

    public CTPolarAdjustHandle insertNewAhPolar(int var1);

    public CTPolarAdjustHandle addNewAhPolar();

    public void removeAhPolar(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTAdjustHandleList.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTAdjustHandleList newInstance() {
            return (CTAdjustHandleList)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTAdjustHandleList newInstance(XmlOptions xmlOptions) {
            return (CTAdjustHandleList)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTAdjustHandleList parse(String string) throws XmlException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTAdjustHandleList parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTAdjustHandleList parse(File file) throws XmlException, IOException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTAdjustHandleList parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTAdjustHandleList parse(URL uRL) throws XmlException, IOException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTAdjustHandleList parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTAdjustHandleList parse(InputStream inputStream) throws XmlException, IOException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTAdjustHandleList parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTAdjustHandleList parse(Reader reader) throws XmlException, IOException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTAdjustHandleList parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTAdjustHandleList parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTAdjustHandleList parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTAdjustHandleList parse(Node node) throws XmlException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTAdjustHandleList parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTAdjustHandleList parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTAdjustHandleList parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTAdjustHandleList)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

