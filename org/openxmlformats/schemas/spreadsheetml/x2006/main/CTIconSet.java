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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;
import org.w3c.dom.Node;

public interface CTIconSet
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTIconSet.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cticonset2648type");

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

    public STIconSetType.Enum getIconSet();

    public STIconSetType xgetIconSet();

    public boolean isSetIconSet();

    public void setIconSet(STIconSetType.Enum var1);

    public void xsetIconSet(STIconSetType var1);

    public void unsetIconSet();

    public boolean getShowValue();

    public XmlBoolean xgetShowValue();

    public boolean isSetShowValue();

    public void setShowValue(boolean var1);

    public void xsetShowValue(XmlBoolean var1);

    public void unsetShowValue();

    public boolean getPercent();

    public XmlBoolean xgetPercent();

    public boolean isSetPercent();

    public void setPercent(boolean var1);

    public void xsetPercent(XmlBoolean var1);

    public void unsetPercent();

    public boolean getReverse();

    public XmlBoolean xgetReverse();

    public boolean isSetReverse();

    public void setReverse(boolean var1);

    public void xsetReverse(XmlBoolean var1);

    public void unsetReverse();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTIconSet.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTIconSet newInstance() {
            return (CTIconSet)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTIconSet newInstance(XmlOptions xmlOptions) {
            return (CTIconSet)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTIconSet parse(String string) throws XmlException {
            return (CTIconSet)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTIconSet parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTIconSet)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTIconSet parse(File file) throws XmlException, IOException {
            return (CTIconSet)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTIconSet parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIconSet)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTIconSet parse(URL uRL) throws XmlException, IOException {
            return (CTIconSet)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTIconSet parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIconSet)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTIconSet parse(InputStream inputStream) throws XmlException, IOException {
            return (CTIconSet)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTIconSet parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIconSet)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTIconSet parse(Reader reader) throws XmlException, IOException {
            return (CTIconSet)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTIconSet parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIconSet)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTIconSet parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTIconSet)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTIconSet parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTIconSet)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTIconSet parse(Node node) throws XmlException {
            return (CTIconSet)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTIconSet parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTIconSet)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTIconSet parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTIconSet)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTIconSet parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTIconSet)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

