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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber;
import org.w3c.dom.Node;

public interface CTShd
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTShd.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctshd58c3type");

    public STShd.Enum getVal();

    public STShd xgetVal();

    public void setVal(STShd.Enum var1);

    public void xsetVal(STShd var1);

    public Object getColor();

    public STHexColor xgetColor();

    public boolean isSetColor();

    public void setColor(Object var1);

    public void xsetColor(STHexColor var1);

    public void unsetColor();

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

    public Object getFill();

    public STHexColor xgetFill();

    public boolean isSetFill();

    public void setFill(Object var1);

    public void xsetFill(STHexColor var1);

    public void unsetFill();

    public STThemeColor.Enum getThemeFill();

    public STThemeColor xgetThemeFill();

    public boolean isSetThemeFill();

    public void setThemeFill(STThemeColor.Enum var1);

    public void xsetThemeFill(STThemeColor var1);

    public void unsetThemeFill();

    public byte[] getThemeFillTint();

    public STUcharHexNumber xgetThemeFillTint();

    public boolean isSetThemeFillTint();

    public void setThemeFillTint(byte[] var1);

    public void xsetThemeFillTint(STUcharHexNumber var1);

    public void unsetThemeFillTint();

    public byte[] getThemeFillShade();

    public STUcharHexNumber xgetThemeFillShade();

    public boolean isSetThemeFillShade();

    public void setThemeFillShade(byte[] var1);

    public void xsetThemeFillShade(STUcharHexNumber var1);

    public void unsetThemeFillShade();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTShd.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTShd newInstance() {
            return (CTShd)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTShd newInstance(XmlOptions xmlOptions) {
            return (CTShd)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTShd parse(String string) throws XmlException {
            return (CTShd)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTShd parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTShd)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTShd parse(File file) throws XmlException, IOException {
            return (CTShd)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTShd parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShd)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTShd parse(URL uRL) throws XmlException, IOException {
            return (CTShd)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTShd parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShd)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTShd parse(InputStream inputStream) throws XmlException, IOException {
            return (CTShd)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTShd parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShd)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTShd parse(Reader reader) throws XmlException, IOException {
            return (CTShd)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTShd parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShd)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTShd parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTShd)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTShd parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTShd)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTShd parse(Node node) throws XmlException {
            return (CTShd)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTShd parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTShd)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTShd parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTShd)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTShd parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTShd)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

