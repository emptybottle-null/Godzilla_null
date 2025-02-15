/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.office.STTrueFalse
 */
package com.microsoft.schemas.vml;

import com.microsoft.schemas.office.office.STInsetMode;
import com.microsoft.schemas.office.office.STTrueFalse;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent;
import org.w3c.dom.Node;

public interface CTTextbox
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTextbox.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttextboxf712type");

    public CTTxbxContent getTxbxContent();

    public boolean isSetTxbxContent();

    public void setTxbxContent(CTTxbxContent var1);

    public CTTxbxContent addNewTxbxContent();

    public void unsetTxbxContent();

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

    public String getInset();

    public XmlString xgetInset();

    public boolean isSetInset();

    public void setInset(String var1);

    public void xsetInset(XmlString var1);

    public void unsetInset();

    public STTrueFalse.Enum getSingleclick();

    public STTrueFalse xgetSingleclick();

    public boolean isSetSingleclick();

    public void setSingleclick(STTrueFalse.Enum var1);

    public void xsetSingleclick(STTrueFalse var1);

    public void unsetSingleclick();

    public STInsetMode.Enum getInsetmode();

    public STInsetMode xgetInsetmode();

    public boolean isSetInsetmode();

    public void setInsetmode(STInsetMode.Enum var1);

    public void xsetInsetmode(STInsetMode var1);

    public void unsetInsetmode();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTextbox.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTextbox newInstance() {
            return (CTTextbox)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTextbox newInstance(XmlOptions xmlOptions) {
            return (CTTextbox)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTextbox parse(String string) throws XmlException {
            return (CTTextbox)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTextbox parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTextbox)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTextbox parse(File file) throws XmlException, IOException {
            return (CTTextbox)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTextbox parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextbox)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTextbox parse(URL uRL) throws XmlException, IOException {
            return (CTTextbox)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTextbox parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextbox)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTextbox parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTextbox)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTextbox parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextbox)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTextbox parse(Reader reader) throws XmlException, IOException {
            return (CTTextbox)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTextbox parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextbox)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTextbox parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTextbox)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTextbox parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTextbox)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTextbox parse(Node node) throws XmlException {
            return (CTTextbox)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTextbox parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTextbox)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTextbox parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTextbox)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTextbox parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTextbox)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

