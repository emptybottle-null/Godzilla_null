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
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.w3c.dom.Node;

public interface CTTextBody
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTextBody.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttextbodya3catype");

    public CTTextBodyProperties getBodyPr();

    public void setBodyPr(CTTextBodyProperties var1);

    public CTTextBodyProperties addNewBodyPr();

    public CTTextListStyle getLstStyle();

    public boolean isSetLstStyle();

    public void setLstStyle(CTTextListStyle var1);

    public CTTextListStyle addNewLstStyle();

    public void unsetLstStyle();

    public List<CTTextParagraph> getPList();

    @Deprecated
    public CTTextParagraph[] getPArray();

    public CTTextParagraph getPArray(int var1);

    public int sizeOfPArray();

    public void setPArray(CTTextParagraph[] var1);

    public void setPArray(int var1, CTTextParagraph var2);

    public CTTextParagraph insertNewP(int var1);

    public CTTextParagraph addNewP();

    public void removeP(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTextBody.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTextBody newInstance() {
            return (CTTextBody)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTextBody newInstance(XmlOptions xmlOptions) {
            return (CTTextBody)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTextBody parse(String string) throws XmlException {
            return (CTTextBody)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTextBody parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTextBody)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTextBody parse(File file) throws XmlException, IOException {
            return (CTTextBody)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTextBody parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextBody)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTextBody parse(URL uRL) throws XmlException, IOException {
            return (CTTextBody)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTextBody parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextBody)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTextBody parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTextBody)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTextBody parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextBody)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTextBody parse(Reader reader) throws XmlException, IOException {
            return (CTTextBody)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTextBody parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextBody)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTextBody parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTextBody)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTextBody parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTextBody)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTextBody parse(Node node) throws XmlException {
            return (CTTextBody)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTextBody parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTextBody)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTextBody parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTextBody)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTextBody parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTextBody)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

