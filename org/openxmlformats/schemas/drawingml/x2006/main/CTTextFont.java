/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.STPanose
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
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.STPanose;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextTypeface;
import org.w3c.dom.Node;

public interface CTTextFont
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTextFont.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttextfont92b7type");

    public String getTypeface();

    public STTextTypeface xgetTypeface();

    public boolean isSetTypeface();

    public void setTypeface(String var1);

    public void xsetTypeface(STTextTypeface var1);

    public void unsetTypeface();

    public byte[] getPanose();

    public STPanose xgetPanose();

    public boolean isSetPanose();

    public void setPanose(byte[] var1);

    public void xsetPanose(STPanose var1);

    public void unsetPanose();

    public byte getPitchFamily();

    public XmlByte xgetPitchFamily();

    public boolean isSetPitchFamily();

    public void setPitchFamily(byte var1);

    public void xsetPitchFamily(XmlByte var1);

    public void unsetPitchFamily();

    public byte getCharset();

    public XmlByte xgetCharset();

    public boolean isSetCharset();

    public void setCharset(byte var1);

    public void xsetCharset(XmlByte var1);

    public void unsetCharset();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTextFont.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTextFont newInstance() {
            return (CTTextFont)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTextFont newInstance(XmlOptions xmlOptions) {
            return (CTTextFont)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTextFont parse(String string) throws XmlException {
            return (CTTextFont)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTextFont parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTextFont)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTextFont parse(File file) throws XmlException, IOException {
            return (CTTextFont)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTextFont parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextFont)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTextFont parse(URL uRL) throws XmlException, IOException {
            return (CTTextFont)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTextFont parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextFont)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTextFont parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTextFont)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTextFont parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextFont)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTextFont parse(Reader reader) throws XmlException, IOException {
            return (CTTextFont)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTextFont parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextFont)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTextFont parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTextFont)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTextFont parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTextFont)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTextFont parse(Node node) throws XmlException {
            return (CTTextFont)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTextFont parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTextFont)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTextFont parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTextFont)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTextFont parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTextFont)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

