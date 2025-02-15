/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.w3c.dom.Node;

public interface CTFFCheckBox
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTFFCheckBox.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctffcheckboxf3a5type");

    public CTHpsMeasure getSize();

    public boolean isSetSize();

    public void setSize(CTHpsMeasure var1);

    public CTHpsMeasure addNewSize();

    public void unsetSize();

    public CTOnOff getSizeAuto();

    public boolean isSetSizeAuto();

    public void setSizeAuto(CTOnOff var1);

    public CTOnOff addNewSizeAuto();

    public void unsetSizeAuto();

    public CTOnOff getDefault();

    public boolean isSetDefault();

    public void setDefault(CTOnOff var1);

    public CTOnOff addNewDefault();

    public void unsetDefault();

    public CTOnOff getChecked();

    public boolean isSetChecked();

    public void setChecked(CTOnOff var1);

    public CTOnOff addNewChecked();

    public void unsetChecked();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTFFCheckBox.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTFFCheckBox newInstance() {
            return (CTFFCheckBox)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTFFCheckBox newInstance(XmlOptions xmlOptions) {
            return (CTFFCheckBox)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTFFCheckBox parse(String string) throws XmlException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTFFCheckBox parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTFFCheckBox parse(File file) throws XmlException, IOException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTFFCheckBox parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTFFCheckBox parse(URL uRL) throws XmlException, IOException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTFFCheckBox parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTFFCheckBox parse(InputStream inputStream) throws XmlException, IOException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTFFCheckBox parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTFFCheckBox parse(Reader reader) throws XmlException, IOException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTFFCheckBox parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTFFCheckBox parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTFFCheckBox parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTFFCheckBox parse(Node node) throws XmlException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTFFCheckBox parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTFFCheckBox parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTFFCheckBox parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTFFCheckBox)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

