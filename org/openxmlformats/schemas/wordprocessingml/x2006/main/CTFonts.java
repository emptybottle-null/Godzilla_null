/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STHint
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHint;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTheme;
import org.w3c.dom.Node;

public interface CTFonts
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTFonts.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctfonts124etype");

    public STHint.Enum getHint();

    public STHint xgetHint();

    public boolean isSetHint();

    public void setHint(STHint.Enum var1);

    public void xsetHint(STHint var1);

    public void unsetHint();

    public String getAscii();

    public STString xgetAscii();

    public boolean isSetAscii();

    public void setAscii(String var1);

    public void xsetAscii(STString var1);

    public void unsetAscii();

    public String getHAnsi();

    public STString xgetHAnsi();

    public boolean isSetHAnsi();

    public void setHAnsi(String var1);

    public void xsetHAnsi(STString var1);

    public void unsetHAnsi();

    public String getEastAsia();

    public STString xgetEastAsia();

    public boolean isSetEastAsia();

    public void setEastAsia(String var1);

    public void xsetEastAsia(STString var1);

    public void unsetEastAsia();

    public String getCs();

    public STString xgetCs();

    public boolean isSetCs();

    public void setCs(String var1);

    public void xsetCs(STString var1);

    public void unsetCs();

    public STTheme.Enum getAsciiTheme();

    public STTheme xgetAsciiTheme();

    public boolean isSetAsciiTheme();

    public void setAsciiTheme(STTheme.Enum var1);

    public void xsetAsciiTheme(STTheme var1);

    public void unsetAsciiTheme();

    public STTheme.Enum getHAnsiTheme();

    public STTheme xgetHAnsiTheme();

    public boolean isSetHAnsiTheme();

    public void setHAnsiTheme(STTheme.Enum var1);

    public void xsetHAnsiTheme(STTheme var1);

    public void unsetHAnsiTheme();

    public STTheme.Enum getEastAsiaTheme();

    public STTheme xgetEastAsiaTheme();

    public boolean isSetEastAsiaTheme();

    public void setEastAsiaTheme(STTheme.Enum var1);

    public void xsetEastAsiaTheme(STTheme var1);

    public void unsetEastAsiaTheme();

    public STTheme.Enum getCstheme();

    public STTheme xgetCstheme();

    public boolean isSetCstheme();

    public void setCstheme(STTheme.Enum var1);

    public void xsetCstheme(STTheme var1);

    public void unsetCstheme();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTFonts.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTFonts newInstance() {
            return (CTFonts)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTFonts newInstance(XmlOptions xmlOptions) {
            return (CTFonts)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTFonts parse(String string) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTFonts parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTFonts parse(File file) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTFonts parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTFonts parse(URL uRL) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTFonts parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTFonts parse(InputStream inputStream) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTFonts parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTFonts parse(Reader reader) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTFonts parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTFonts parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTFonts parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTFonts parse(Node node) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTFonts parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTFonts parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTFonts)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTFonts parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTFonts)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

