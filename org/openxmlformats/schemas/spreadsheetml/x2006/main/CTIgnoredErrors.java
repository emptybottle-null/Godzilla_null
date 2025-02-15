/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredError;
import org.w3c.dom.Node;

public interface CTIgnoredErrors
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTIgnoredErrors.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctignorederrorsbebctype");

    public List<CTIgnoredError> getIgnoredErrorList();

    @Deprecated
    public CTIgnoredError[] getIgnoredErrorArray();

    public CTIgnoredError getIgnoredErrorArray(int var1);

    public int sizeOfIgnoredErrorArray();

    public void setIgnoredErrorArray(CTIgnoredError[] var1);

    public void setIgnoredErrorArray(int var1, CTIgnoredError var2);

    public CTIgnoredError insertNewIgnoredError(int var1);

    public CTIgnoredError addNewIgnoredError();

    public void removeIgnoredError(int var1);

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTIgnoredErrors.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTIgnoredErrors newInstance() {
            return (CTIgnoredErrors)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTIgnoredErrors newInstance(XmlOptions xmlOptions) {
            return (CTIgnoredErrors)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTIgnoredErrors parse(String string) throws XmlException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTIgnoredErrors parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTIgnoredErrors parse(File file) throws XmlException, IOException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTIgnoredErrors parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTIgnoredErrors parse(URL uRL) throws XmlException, IOException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTIgnoredErrors parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTIgnoredErrors parse(InputStream inputStream) throws XmlException, IOException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTIgnoredErrors parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTIgnoredErrors parse(Reader reader) throws XmlException, IOException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTIgnoredErrors parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTIgnoredErrors parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTIgnoredErrors parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTIgnoredErrors parse(Node node) throws XmlException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTIgnoredErrors parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTIgnoredErrors parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTIgnoredErrors parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTIgnoredErrors)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

