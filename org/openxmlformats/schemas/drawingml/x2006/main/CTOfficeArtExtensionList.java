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
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
import org.w3c.dom.Node;

public interface CTOfficeArtExtensionList
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTOfficeArtExtensionList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctofficeartextensionlista211type");

    public List<CTOfficeArtExtension> getExtList();

    @Deprecated
    public CTOfficeArtExtension[] getExtArray();

    public CTOfficeArtExtension getExtArray(int var1);

    public int sizeOfExtArray();

    public void setExtArray(CTOfficeArtExtension[] var1);

    public void setExtArray(int var1, CTOfficeArtExtension var2);

    public CTOfficeArtExtension insertNewExt(int var1);

    public CTOfficeArtExtension addNewExt();

    public void removeExt(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTOfficeArtExtensionList.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTOfficeArtExtensionList newInstance() {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTOfficeArtExtensionList newInstance(XmlOptions xmlOptions) {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTOfficeArtExtensionList parse(String string) throws XmlException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTOfficeArtExtensionList parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTOfficeArtExtensionList parse(File file) throws XmlException, IOException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTOfficeArtExtensionList parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTOfficeArtExtensionList parse(URL uRL) throws XmlException, IOException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTOfficeArtExtensionList parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTOfficeArtExtensionList parse(InputStream inputStream) throws XmlException, IOException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTOfficeArtExtensionList parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTOfficeArtExtensionList parse(Reader reader) throws XmlException, IOException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTOfficeArtExtensionList parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTOfficeArtExtensionList parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTOfficeArtExtensionList parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTOfficeArtExtensionList parse(Node node) throws XmlException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTOfficeArtExtensionList parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTOfficeArtExtensionList parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTOfficeArtExtensionList parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTOfficeArtExtensionList)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

