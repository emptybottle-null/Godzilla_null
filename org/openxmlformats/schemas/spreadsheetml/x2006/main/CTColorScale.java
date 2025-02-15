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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.w3c.dom.Node;

public interface CTColorScale
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTColorScale.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcolorscale1a70type");

    public List<CTCfvo> getCfvoList();

    @Deprecated
    public CTCfvo[] getCfvoArray();

    public CTCfvo getCfvoArray(int var1);

    public int sizeOfCfvoArray();

    public void setCfvoArray(CTCfvo[] var1);

    public void setCfvoArray(int var1, CTCfvo var2);

    public CTCfvo insertNewCfvo(int var1);

    public CTCfvo addNewCfvo();

    public void removeCfvo(int var1);

    public List<CTColor> getColorList();

    @Deprecated
    public CTColor[] getColorArray();

    public CTColor getColorArray(int var1);

    public int sizeOfColorArray();

    public void setColorArray(CTColor[] var1);

    public void setColorArray(int var1, CTColor var2);

    public CTColor insertNewColor(int var1);

    public CTColor addNewColor();

    public void removeColor(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTColorScale.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTColorScale newInstance() {
            return (CTColorScale)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTColorScale newInstance(XmlOptions xmlOptions) {
            return (CTColorScale)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTColorScale parse(String string) throws XmlException {
            return (CTColorScale)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTColorScale parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTColorScale)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTColorScale parse(File file) throws XmlException, IOException {
            return (CTColorScale)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTColorScale parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorScale)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTColorScale parse(URL uRL) throws XmlException, IOException {
            return (CTColorScale)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTColorScale parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorScale)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTColorScale parse(InputStream inputStream) throws XmlException, IOException {
            return (CTColorScale)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTColorScale parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorScale)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTColorScale parse(Reader reader) throws XmlException, IOException {
            return (CTColorScale)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTColorScale parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorScale)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTColorScale parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTColorScale)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTColorScale parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTColorScale)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTColorScale parse(Node node) throws XmlException {
            return (CTColorScale)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTColorScale parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTColorScale)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTColorScale parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTColorScale)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTColorScale parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTColorScale)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

