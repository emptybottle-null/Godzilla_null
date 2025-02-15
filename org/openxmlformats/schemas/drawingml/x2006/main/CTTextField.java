/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STGuid;
import org.w3c.dom.Node;

public interface CTTextField
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTextField.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttextfield187etype");

    public CTTextCharacterProperties getRPr();

    public boolean isSetRPr();

    public void setRPr(CTTextCharacterProperties var1);

    public CTTextCharacterProperties addNewRPr();

    public void unsetRPr();

    public CTTextParagraphProperties getPPr();

    public boolean isSetPPr();

    public void setPPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewPPr();

    public void unsetPPr();

    public String getT();

    public XmlString xgetT();

    public boolean isSetT();

    public void setT(String var1);

    public void xsetT(XmlString var1);

    public void unsetT();

    public String getId();

    public STGuid xgetId();

    public void setId(String var1);

    public void xsetId(STGuid var1);

    public String getType();

    public XmlString xgetType();

    public boolean isSetType();

    public void setType(String var1);

    public void xsetType(XmlString var1);

    public void unsetType();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTextField.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTextField newInstance() {
            return (CTTextField)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTextField newInstance(XmlOptions xmlOptions) {
            return (CTTextField)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTextField parse(String string) throws XmlException {
            return (CTTextField)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTextField parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTextField)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTextField parse(File file) throws XmlException, IOException {
            return (CTTextField)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTextField parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextField)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTextField parse(URL uRL) throws XmlException, IOException {
            return (CTTextField)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTextField parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextField)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTextField parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTextField)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTextField parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextField)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTextField parse(Reader reader) throws XmlException, IOException {
            return (CTTextField)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTextField parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextField)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTextField parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTextField)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTextField parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTextField)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTextField parse(Node node) throws XmlException {
            return (CTTextField)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTextField parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTextField)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTextField parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTextField)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTextField parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTextField)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

