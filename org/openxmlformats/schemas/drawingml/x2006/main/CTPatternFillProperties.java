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
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetPatternVal;
import org.w3c.dom.Node;

public interface CTPatternFillProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPatternFillProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpatternfillproperties3637type");

    public CTColor getFgClr();

    public boolean isSetFgClr();

    public void setFgClr(CTColor var1);

    public CTColor addNewFgClr();

    public void unsetFgClr();

    public CTColor getBgClr();

    public boolean isSetBgClr();

    public void setBgClr(CTColor var1);

    public CTColor addNewBgClr();

    public void unsetBgClr();

    public STPresetPatternVal.Enum getPrst();

    public STPresetPatternVal xgetPrst();

    public boolean isSetPrst();

    public void setPrst(STPresetPatternVal.Enum var1);

    public void xsetPrst(STPresetPatternVal var1);

    public void unsetPrst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPatternFillProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPatternFillProperties newInstance() {
            return (CTPatternFillProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPatternFillProperties newInstance(XmlOptions xmlOptions) {
            return (CTPatternFillProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPatternFillProperties parse(String string) throws XmlException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPatternFillProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPatternFillProperties parse(File file) throws XmlException, IOException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPatternFillProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPatternFillProperties parse(URL uRL) throws XmlException, IOException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPatternFillProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPatternFillProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPatternFillProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPatternFillProperties parse(Reader reader) throws XmlException, IOException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPatternFillProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPatternFillProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPatternFillProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPatternFillProperties parse(Node node) throws XmlException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPatternFillProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPatternFillProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPatternFillProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPatternFillProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

