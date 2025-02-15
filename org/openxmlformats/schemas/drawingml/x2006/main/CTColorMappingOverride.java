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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmptyElement;
import org.w3c.dom.Node;

public interface CTColorMappingOverride
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTColorMappingOverride.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcolormappingoverridea2b2type");

    public CTEmptyElement getMasterClrMapping();

    public boolean isSetMasterClrMapping();

    public void setMasterClrMapping(CTEmptyElement var1);

    public CTEmptyElement addNewMasterClrMapping();

    public void unsetMasterClrMapping();

    public CTColorMapping getOverrideClrMapping();

    public boolean isSetOverrideClrMapping();

    public void setOverrideClrMapping(CTColorMapping var1);

    public CTColorMapping addNewOverrideClrMapping();

    public void unsetOverrideClrMapping();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTColorMappingOverride.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTColorMappingOverride newInstance() {
            return (CTColorMappingOverride)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTColorMappingOverride newInstance(XmlOptions xmlOptions) {
            return (CTColorMappingOverride)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTColorMappingOverride parse(String string) throws XmlException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTColorMappingOverride parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTColorMappingOverride parse(File file) throws XmlException, IOException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTColorMappingOverride parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTColorMappingOverride parse(URL uRL) throws XmlException, IOException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTColorMappingOverride parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTColorMappingOverride parse(InputStream inputStream) throws XmlException, IOException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTColorMappingOverride parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTColorMappingOverride parse(Reader reader) throws XmlException, IOException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTColorMappingOverride parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTColorMappingOverride parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTColorMappingOverride parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTColorMappingOverride parse(Node node) throws XmlException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTColorMappingOverride parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTColorMappingOverride parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTColorMappingOverride parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTColorMappingOverride)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

