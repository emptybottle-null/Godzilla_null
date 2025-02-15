/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticAlignment
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticType
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticType;
import org.w3c.dom.Node;

public interface CTPhoneticPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPhoneticPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctphoneticpr898btype");

    public long getFontId();

    public STFontId xgetFontId();

    public void setFontId(long var1);

    public void xsetFontId(STFontId var1);

    public STPhoneticType.Enum getType();

    public STPhoneticType xgetType();

    public boolean isSetType();

    public void setType(STPhoneticType.Enum var1);

    public void xsetType(STPhoneticType var1);

    public void unsetType();

    public STPhoneticAlignment.Enum getAlignment();

    public STPhoneticAlignment xgetAlignment();

    public boolean isSetAlignment();

    public void setAlignment(STPhoneticAlignment.Enum var1);

    public void xsetAlignment(STPhoneticAlignment var1);

    public void unsetAlignment();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPhoneticPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPhoneticPr newInstance() {
            return (CTPhoneticPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPhoneticPr newInstance(XmlOptions xmlOptions) {
            return (CTPhoneticPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPhoneticPr parse(String string) throws XmlException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPhoneticPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPhoneticPr parse(File file) throws XmlException, IOException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPhoneticPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPhoneticPr parse(URL uRL) throws XmlException, IOException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPhoneticPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPhoneticPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPhoneticPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPhoneticPr parse(Reader reader) throws XmlException, IOException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPhoneticPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPhoneticPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPhoneticPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPhoneticPr parse(Node node) throws XmlException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPhoneticPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPhoneticPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPhoneticPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPhoneticPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

