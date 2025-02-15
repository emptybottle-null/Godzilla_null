/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber;
import org.w3c.dom.Node;

public interface CTColor
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTColor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcolor6d4ftype");

    public Object getVal();

    public STHexColor xgetVal();

    public void setVal(Object var1);

    public void xsetVal(STHexColor var1);

    public STThemeColor.Enum getThemeColor();

    public STThemeColor xgetThemeColor();

    public boolean isSetThemeColor();

    public void setThemeColor(STThemeColor.Enum var1);

    public void xsetThemeColor(STThemeColor var1);

    public void unsetThemeColor();

    public byte[] getThemeTint();

    public STUcharHexNumber xgetThemeTint();

    public boolean isSetThemeTint();

    public void setThemeTint(byte[] var1);

    public void xsetThemeTint(STUcharHexNumber var1);

    public void unsetThemeTint();

    public byte[] getThemeShade();

    public STUcharHexNumber xgetThemeShade();

    public boolean isSetThemeShade();

    public void setThemeShade(byte[] var1);

    public void xsetThemeShade(STUcharHexNumber var1);

    public void unsetThemeShade();

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

