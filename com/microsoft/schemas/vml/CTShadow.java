/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.vml.STShadowType
 */
package com.microsoft.schemas.vml;

import com.microsoft.schemas.vml.STColorType;
import com.microsoft.schemas.vml.STShadowType;
import com.microsoft.schemas.vml.STTrueFalse;
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

public interface CTShadow
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTShadow.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctshadowdfdetype");

    public String getId();

    public XmlString xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(XmlString var1);

    public void unsetId();

    public STTrueFalse.Enum getOn();

    public STTrueFalse xgetOn();

    public boolean isSetOn();

    public void setOn(STTrueFalse.Enum var1);

    public void xsetOn(STTrueFalse var1);

    public void unsetOn();

    public STShadowType.Enum getType();

    public STShadowType xgetType();

    public boolean isSetType();

    public void setType(STShadowType.Enum var1);

    public void xsetType(STShadowType var1);

    public void unsetType();

    public STTrueFalse.Enum getObscured();

    public STTrueFalse xgetObscured();

    public boolean isSetObscured();

    public void setObscured(STTrueFalse.Enum var1);

    public void xsetObscured(STTrueFalse var1);

    public void unsetObscured();

    public String getColor();

    public STColorType xgetColor();

    public boolean isSetColor();

    public void setColor(String var1);

    public void xsetColor(STColorType var1);

    public void unsetColor();

    public String getOpacity();

    public XmlString xgetOpacity();

    public boolean isSetOpacity();

    public void setOpacity(String var1);

    public void xsetOpacity(XmlString var1);

    public void unsetOpacity();

    public String getOffset();

    public XmlString xgetOffset();

    public boolean isSetOffset();

    public void setOffset(String var1);

    public void xsetOffset(XmlString var1);

    public void unsetOffset();

    public String getColor2();

    public STColorType xgetColor2();

    public boolean isSetColor2();

    public void setColor2(String var1);

    public void xsetColor2(STColorType var1);

    public void unsetColor2();

    public String getOffset2();

    public XmlString xgetOffset2();

    public boolean isSetOffset2();

    public void setOffset2(String var1);

    public void xsetOffset2(XmlString var1);

    public void unsetOffset2();

    public String getOrigin();

    public XmlString xgetOrigin();

    public boolean isSetOrigin();

    public void setOrigin(String var1);

    public void xsetOrigin(XmlString var1);

    public void unsetOrigin();

    public String getMatrix();

    public XmlString xgetMatrix();

    public boolean isSetMatrix();

    public void setMatrix(String var1);

    public void xsetMatrix(XmlString var1);

    public void unsetMatrix();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTShadow.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTShadow newInstance() {
            return (CTShadow)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTShadow newInstance(XmlOptions xmlOptions) {
            return (CTShadow)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTShadow parse(String string) throws XmlException {
            return (CTShadow)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTShadow parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTShadow)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTShadow parse(File file) throws XmlException, IOException {
            return (CTShadow)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTShadow parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShadow)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTShadow parse(URL uRL) throws XmlException, IOException {
            return (CTShadow)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTShadow parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShadow)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTShadow parse(InputStream inputStream) throws XmlException, IOException {
            return (CTShadow)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTShadow parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShadow)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTShadow parse(Reader reader) throws XmlException, IOException {
            return (CTShadow)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTShadow parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShadow)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTShadow parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTShadow)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTShadow parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTShadow)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTShadow parse(Node node) throws XmlException {
            return (CTShadow)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTShadow parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTShadow)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTShadow parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTShadow)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTShadow parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTShadow)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

