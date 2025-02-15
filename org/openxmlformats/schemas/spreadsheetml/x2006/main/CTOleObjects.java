/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;
import org.w3c.dom.Node;

public interface CTOleObjects
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTOleObjects.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctoleobjects1455type");

    public List<CTOleObject> getOleObjectList();

    @Deprecated
    public CTOleObject[] getOleObjectArray();

    public CTOleObject getOleObjectArray(int var1);

    public int sizeOfOleObjectArray();

    public void setOleObjectArray(CTOleObject[] var1);

    public void setOleObjectArray(int var1, CTOleObject var2);

    public CTOleObject insertNewOleObject(int var1);

    public CTOleObject addNewOleObject();

    public void removeOleObject(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTOleObjects.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTOleObjects newInstance() {
            return (CTOleObjects)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTOleObjects newInstance(XmlOptions xmlOptions) {
            return (CTOleObjects)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTOleObjects parse(String string) throws XmlException {
            return (CTOleObjects)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTOleObjects parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTOleObjects)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTOleObjects parse(File file) throws XmlException, IOException {
            return (CTOleObjects)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTOleObjects parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOleObjects)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTOleObjects parse(URL uRL) throws XmlException, IOException {
            return (CTOleObjects)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTOleObjects parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOleObjects)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTOleObjects parse(InputStream inputStream) throws XmlException, IOException {
            return (CTOleObjects)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTOleObjects parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOleObjects)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTOleObjects parse(Reader reader) throws XmlException, IOException {
            return (CTOleObjects)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTOleObjects parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOleObjects)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTOleObjects parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTOleObjects)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTOleObjects parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTOleObjects)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTOleObjects parse(Node node) throws XmlException {
            return (CTOleObjects)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTOleObjects parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTOleObjects)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTOleObjects parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTOleObjects)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTOleObjects parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTOleObjects)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

