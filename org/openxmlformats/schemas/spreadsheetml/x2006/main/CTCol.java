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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTCol
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCol.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcola95ftype");

    public long getMin();

    public XmlUnsignedInt xgetMin();

    public void setMin(long var1);

    public void xsetMin(XmlUnsignedInt var1);

    public long getMax();

    public XmlUnsignedInt xgetMax();

    public void setMax(long var1);

    public void xsetMax(XmlUnsignedInt var1);

    public double getWidth();

    public XmlDouble xgetWidth();

    public boolean isSetWidth();

    public void setWidth(double var1);

    public void xsetWidth(XmlDouble var1);

    public void unsetWidth();

    public long getStyle();

    public XmlUnsignedInt xgetStyle();

    public boolean isSetStyle();

    public void setStyle(long var1);

    public void xsetStyle(XmlUnsignedInt var1);

    public void unsetStyle();

    public boolean getHidden();

    public XmlBoolean xgetHidden();

    public boolean isSetHidden();

    public void setHidden(boolean var1);

    public void xsetHidden(XmlBoolean var1);

    public void unsetHidden();

    public boolean getBestFit();

    public XmlBoolean xgetBestFit();

    public boolean isSetBestFit();

    public void setBestFit(boolean var1);

    public void xsetBestFit(XmlBoolean var1);

    public void unsetBestFit();

    public boolean getCustomWidth();

    public XmlBoolean xgetCustomWidth();

    public boolean isSetCustomWidth();

    public void setCustomWidth(boolean var1);

    public void xsetCustomWidth(XmlBoolean var1);

    public void unsetCustomWidth();

    public boolean getPhonetic();

    public XmlBoolean xgetPhonetic();

    public boolean isSetPhonetic();

    public void setPhonetic(boolean var1);

    public void xsetPhonetic(XmlBoolean var1);

    public void unsetPhonetic();

    public short getOutlineLevel();

    public XmlUnsignedByte xgetOutlineLevel();

    public boolean isSetOutlineLevel();

    public void setOutlineLevel(short var1);

    public void xsetOutlineLevel(XmlUnsignedByte var1);

    public void unsetOutlineLevel();

    public boolean getCollapsed();

    public XmlBoolean xgetCollapsed();

    public boolean isSetCollapsed();

    public void setCollapsed(boolean var1);

    public void xsetCollapsed(XmlBoolean var1);

    public void unsetCollapsed();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCol.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCol newInstance() {
            return (CTCol)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCol newInstance(XmlOptions xmlOptions) {
            return (CTCol)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCol parse(String string) throws XmlException {
            return (CTCol)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCol parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCol)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCol parse(File file) throws XmlException, IOException {
            return (CTCol)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCol parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCol)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCol parse(URL uRL) throws XmlException, IOException {
            return (CTCol)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCol parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCol)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCol parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCol)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCol parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCol)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCol parse(Reader reader) throws XmlException, IOException {
            return (CTCol)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCol parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCol)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCol parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCol)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCol parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCol)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCol parse(Node node) throws XmlException {
            return (CTCol)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCol parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCol)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCol parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCol)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCol parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCol)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

