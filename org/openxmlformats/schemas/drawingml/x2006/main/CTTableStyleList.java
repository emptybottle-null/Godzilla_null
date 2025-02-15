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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.STGuid;
import org.w3c.dom.Node;

public interface CTTableStyleList
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableStyleList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablestylelist4bdctype");

    public List<CTTableStyle> getTblStyleList();

    @Deprecated
    public CTTableStyle[] getTblStyleArray();

    public CTTableStyle getTblStyleArray(int var1);

    public int sizeOfTblStyleArray();

    public void setTblStyleArray(CTTableStyle[] var1);

    public void setTblStyleArray(int var1, CTTableStyle var2);

    public CTTableStyle insertNewTblStyle(int var1);

    public CTTableStyle addNewTblStyle();

    public void removeTblStyle(int var1);

    public String getDef();

    public STGuid xgetDef();

    public void setDef(String var1);

    public void xsetDef(STGuid var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableStyleList.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableStyleList newInstance() {
            return (CTTableStyleList)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableStyleList newInstance(XmlOptions xmlOptions) {
            return (CTTableStyleList)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableStyleList parse(String string) throws XmlException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableStyleList parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableStyleList parse(File file) throws XmlException, IOException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableStyleList parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableStyleList parse(URL uRL) throws XmlException, IOException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableStyleList parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableStyleList parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableStyleList parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableStyleList parse(Reader reader) throws XmlException, IOException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableStyleList parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableStyleList parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableStyleList parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableStyleList parse(Node node) throws XmlException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableStyleList parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableStyleList parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableStyleList parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableStyleList)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

