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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedIntHex;
import org.w3c.dom.Node;

public interface CTColor
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTColor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcolord2c2type");

    public boolean getAuto();

    public XmlBoolean xgetAuto();

    public boolean isSetAuto();

    public void setAuto(boolean var1);

    public void xsetAuto(XmlBoolean var1);

    public void unsetAuto();

    public long getIndexed();

    public XmlUnsignedInt xgetIndexed();

    public boolean isSetIndexed();

    public void setIndexed(long var1);

    public void xsetIndexed(XmlUnsignedInt var1);

    public void unsetIndexed();

    public byte[] getRgb();

    public STUnsignedIntHex xgetRgb();

    public boolean isSetRgb();

    public void setRgb(byte[] var1);

    public void xsetRgb(STUnsignedIntHex var1);

    public void unsetRgb();

    public long getTheme();

    public XmlUnsignedInt xgetTheme();

    public boolean isSetTheme();

    public void setTheme(long var1);

    public void xsetTheme(XmlUnsignedInt var1);

    public void unsetTheme();

    public double getTint();

    public XmlDouble xgetTint();

    public boolean isSetTint();

    public void setTint(double var1);

    public void xsetTint(XmlDouble var1);

    public void unsetTint();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTColor.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTColor newInstance() {
            return (CTColor)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTColor newInstance(XmlOptions xmlOptions) {
            return (CTColor)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTColor parse(String string) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTColor parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTColor parse(File file) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTColor parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTColor parse(URL uRL) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTColor parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTColor parse(InputStream inputStream) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTColor parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTColor parse(Reader reader) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTColor parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTColor parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTColor parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTColor parse(Node node) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTColor parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTColor parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTColor)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTColor parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTColor)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

