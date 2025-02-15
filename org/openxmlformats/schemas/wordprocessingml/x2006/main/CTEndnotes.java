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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.w3c.dom.Node;

public interface CTEndnotes
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTEndnotes.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctendnotescee2type");

    public List<CTFtnEdn> getEndnoteList();

    @Deprecated
    public CTFtnEdn[] getEndnoteArray();

    public CTFtnEdn getEndnoteArray(int var1);

    public int sizeOfEndnoteArray();

    public void setEndnoteArray(CTFtnEdn[] var1);

    public void setEndnoteArray(int var1, CTFtnEdn var2);

    public CTFtnEdn insertNewEndnote(int var1);

    public CTFtnEdn addNewEndnote();

    public void removeEndnote(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTEndnotes.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTEndnotes newInstance() {
            return (CTEndnotes)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTEndnotes newInstance(XmlOptions xmlOptions) {
            return (CTEndnotes)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTEndnotes parse(String string) throws XmlException {
            return (CTEndnotes)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTEndnotes parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTEndnotes)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTEndnotes parse(File file) throws XmlException, IOException {
            return (CTEndnotes)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTEndnotes parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEndnotes)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTEndnotes parse(URL uRL) throws XmlException, IOException {
            return (CTEndnotes)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTEndnotes parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEndnotes)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTEndnotes parse(InputStream inputStream) throws XmlException, IOException {
            return (CTEndnotes)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTEndnotes parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEndnotes)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTEndnotes parse(Reader reader) throws XmlException, IOException {
            return (CTEndnotes)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTEndnotes parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEndnotes)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTEndnotes parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTEndnotes)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTEndnotes parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTEndnotes)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTEndnotes parse(Node node) throws XmlException {
            return (CTEndnotes)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTEndnotes parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTEndnotes)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTEndnotes parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTEndnotes)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTEndnotes parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTEndnotes)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

