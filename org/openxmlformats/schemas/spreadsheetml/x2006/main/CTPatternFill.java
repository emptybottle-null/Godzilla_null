/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;
import org.w3c.dom.Node;

public interface CTPatternFill
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPatternFill.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpatternfill7452type");

    public CTColor getFgColor();

    public boolean isSetFgColor();

    public void setFgColor(CTColor var1);

    public CTColor addNewFgColor();

    public void unsetFgColor();

    public CTColor getBgColor();

    public boolean isSetBgColor();

    public void setBgColor(CTColor var1);

    public CTColor addNewBgColor();

    public void unsetBgColor();

    public STPatternType.Enum getPatternType();

    public STPatternType xgetPatternType();

    public boolean isSetPatternType();

    public void setPatternType(STPatternType.Enum var1);

    public void xsetPatternType(STPatternType var1);

    public void unsetPatternType();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPatternFill.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPatternFill newInstance() {
            return (CTPatternFill)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPatternFill newInstance(XmlOptions xmlOptions) {
            return (CTPatternFill)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPatternFill parse(String string) throws XmlException {
            return (CTPatternFill)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPatternFill parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPatternFill)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPatternFill parse(File file) throws XmlException, IOException {
            return (CTPatternFill)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPatternFill parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPatternFill)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPatternFill parse(URL uRL) throws XmlException, IOException {
            return (CTPatternFill)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPatternFill parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPatternFill)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPatternFill parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPatternFill)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPatternFill parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPatternFill)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPatternFill parse(Reader reader) throws XmlException, IOException {
            return (CTPatternFill)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPatternFill parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPatternFill)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPatternFill parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPatternFill)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPatternFill parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPatternFill)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPatternFill parse(Node node) throws XmlException {
            return (CTPatternFill)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPatternFill parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPatternFill)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPatternFill parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPatternFill)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPatternFill parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPatternFill)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

