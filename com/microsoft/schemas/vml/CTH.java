/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.vml.STTrueFalseBlank
 */
package com.microsoft.schemas.vml;

import com.microsoft.schemas.vml.STTrueFalse;
import com.microsoft.schemas.vml.STTrueFalseBlank;
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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTH
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTH.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cth4cbctype");

    public String getPosition();

    public XmlString xgetPosition();

    public boolean isSetPosition();

    public void setPosition(String var1);

    public void xsetPosition(XmlString var1);

    public void unsetPosition();

    public String getPolar();

    public XmlString xgetPolar();

    public boolean isSetPolar();

    public void setPolar(String var1);

    public void xsetPolar(XmlString var1);

    public void unsetPolar();

    public String getMap();

    public XmlString xgetMap();

    public boolean isSetMap();

    public void setMap(String var1);

    public void xsetMap(XmlString var1);

    public void unsetMap();

    public STTrueFalse.Enum getInvx();

    public STTrueFalse xgetInvx();

    public boolean isSetInvx();

    public void setInvx(STTrueFalse.Enum var1);

    public void xsetInvx(STTrueFalse var1);

    public void unsetInvx();

    public STTrueFalse.Enum getInvy();

    public STTrueFalse xgetInvy();

    public boolean isSetInvy();

    public void setInvy(STTrueFalse.Enum var1);

    public void xsetInvy(STTrueFalse var1);

    public void unsetInvy();

    public STTrueFalseBlank.Enum getSwitch();

    public STTrueFalseBlank xgetSwitch();

    public boolean isSetSwitch();

    public void setSwitch(STTrueFalseBlank.Enum var1);

    public void xsetSwitch(STTrueFalseBlank var1);

    public void unsetSwitch();

    public String getXrange();

    public XmlString xgetXrange();

    public boolean isSetXrange();

    public void setXrange(String var1);

    public void xsetXrange(XmlString var1);

    public void unsetXrange();

    public String getYrange();

    public XmlString xgetYrange();

    public boolean isSetYrange();

    public void setYrange(String var1);

    public void xsetYrange(XmlString var1);

    public void unsetYrange();

    public String getRadiusrange();

    public XmlString xgetRadiusrange();

    public boolean isSetRadiusrange();

    public void setRadiusrange(String var1);

    public void xsetRadiusrange(XmlString var1);

    public void unsetRadiusrange();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTH.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTH newInstance() {
            return (CTH)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTH newInstance(XmlOptions xmlOptions) {
            return (CTH)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTH parse(String string) throws XmlException {
            return (CTH)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTH parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTH)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTH parse(File file) throws XmlException, IOException {
            return (CTH)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTH parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTH)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTH parse(URL uRL) throws XmlException, IOException {
            return (CTH)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTH parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTH)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTH parse(InputStream inputStream) throws XmlException, IOException {
            return (CTH)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTH parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTH)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTH parse(Reader reader) throws XmlException, IOException {
            return (CTH)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTH parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTH)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTH parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTH)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTH parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTH)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTH parse(Node node) throws XmlException {
            return (CTH)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTH parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTH)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTH parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTH)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTH parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTH)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

