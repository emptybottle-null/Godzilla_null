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
import java.math.BigInteger;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STEighthPointMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPointMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber;
import org.w3c.dom.Node;

public interface CTBorder
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBorder.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbordercdfctype");

    public STBorder.Enum getVal();

    public STBorder xgetVal();

    public void setVal(STBorder.Enum var1);

    public void xsetVal(STBorder var1);

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

    public BigInteger getSz();

    public STEighthPointMeasure xgetSz();

    public boolean isSetSz();

    public void setSz(BigInteger var1);

    public void xsetSz(STEighthPointMeasure var1);

    public void unsetSz();

    public BigInteger getSpace();

    public STPointMeasure xgetSpace();

    public boolean isSetSpace();

    public void setSpace(BigInteger var1);

    public void xsetSpace(STPointMeasure var1);

    public void unsetSpace();

    public STOnOff.Enum getShadow();

    public STOnOff xgetShadow();

    public boolean isSetShadow();

    public void setShadow(STOnOff.Enum var1);

    public void xsetShadow(STOnOff var1);

    public void unsetShadow();

    public STOnOff.Enum getFrame();

    public STOnOff xgetFrame();

    public boolean isSetFrame();

    public void setFrame(STOnOff.Enum var1);

    public void xsetFrame(STOnOff var1);

    public void unsetFrame();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBorder.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBorder newInstance() {
            return (CTBorder)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBorder newInstance(XmlOptions xmlOptions) {
            return (CTBorder)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBorder parse(String string) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBorder parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBorder parse(File file) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBorder parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBorder parse(URL uRL) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBorder parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBorder parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBorder parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBorder parse(Reader reader) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBorder parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBorder)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBorder parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBorder parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBorder parse(Node node) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBorder parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBorder)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBorder parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBorder)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBorder parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBorder)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

