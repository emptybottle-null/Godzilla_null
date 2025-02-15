/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.vml;

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

public interface CTTextPath
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTextPath.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttextpath14f0type");

    public String getId();

    public XmlString xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(XmlString var1);

    public void unsetId();

    public String getStyle();

    public XmlString xgetStyle();

    public boolean isSetStyle();

    public void setStyle(String var1);

    public void xsetStyle(XmlString var1);

    public void unsetStyle();

    public STTrueFalse.Enum getOn();

    public STTrueFalse xgetOn();

    public boolean isSetOn();

    public void setOn(STTrueFalse.Enum var1);

    public void xsetOn(STTrueFalse var1);

    public void unsetOn();

    public STTrueFalse.Enum getFitshape();

    public STTrueFalse xgetFitshape();

    public boolean isSetFitshape();

    public void setFitshape(STTrueFalse.Enum var1);

    public void xsetFitshape(STTrueFalse var1);

    public void unsetFitshape();

    public STTrueFalse.Enum getFitpath();

    public STTrueFalse xgetFitpath();

    public boolean isSetFitpath();

    public void setFitpath(STTrueFalse.Enum var1);

    public void xsetFitpath(STTrueFalse var1);

    public void unsetFitpath();

    public STTrueFalse.Enum getTrim();

    public STTrueFalse xgetTrim();

    public boolean isSetTrim();

    public void setTrim(STTrueFalse.Enum var1);

    public void xsetTrim(STTrueFalse var1);

    public void unsetTrim();

    public STTrueFalse.Enum getXscale();

    public STTrueFalse xgetXscale();

    public boolean isSetXscale();

    public void setXscale(STTrueFalse.Enum var1);

    public void xsetXscale(STTrueFalse var1);

    public void unsetXscale();

    public String getString();

    public XmlString xgetString();

    public boolean isSetString();

    public void setString(String var1);

    public void xsetString(XmlString var1);

    public void unsetString();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTextPath.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTextPath newInstance() {
            return (CTTextPath)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTextPath newInstance(XmlOptions xmlOptions) {
            return (CTTextPath)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTextPath parse(String string) throws XmlException {
            return (CTTextPath)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTextPath parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTextPath)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTextPath parse(File file) throws XmlException, IOException {
            return (CTTextPath)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTextPath parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextPath)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTextPath parse(URL uRL) throws XmlException, IOException {
            return (CTTextPath)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTextPath parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextPath)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTextPath parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTextPath)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTextPath parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextPath)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTextPath parse(Reader reader) throws XmlException, IOException {
            return (CTTextPath)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTextPath parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextPath)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTextPath parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTextPath)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTextPath parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTextPath)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTextPath parse(Node node) throws XmlException {
            return (CTTextPath)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTextPath parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTextPath)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTextPath parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTextPath)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTextPath parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTextPath)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

