/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigInteger;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.w3c.dom.Node;

public interface CTFtnEdnRef
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTFtnEdnRef.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctftnednref89eetype");

    public STOnOff.Enum getCustomMarkFollows();

    public STOnOff xgetCustomMarkFollows();

    public boolean isSetCustomMarkFollows();

    public void setCustomMarkFollows(STOnOff.Enum var1);

    public void xsetCustomMarkFollows(STOnOff var1);

    public void unsetCustomMarkFollows();

    public BigInteger getId();

    public STDecimalNumber xgetId();

    public void setId(BigInteger var1);

    public void xsetId(STDecimalNumber var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTFtnEdnRef.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTFtnEdnRef newInstance() {
            return (CTFtnEdnRef)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTFtnEdnRef newInstance(XmlOptions xmlOptions) {
            return (CTFtnEdnRef)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTFtnEdnRef parse(String string) throws XmlException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTFtnEdnRef parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTFtnEdnRef parse(File file) throws XmlException, IOException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTFtnEdnRef parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTFtnEdnRef parse(URL uRL) throws XmlException, IOException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTFtnEdnRef parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTFtnEdnRef parse(InputStream inputStream) throws XmlException, IOException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTFtnEdnRef parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTFtnEdnRef parse(Reader reader) throws XmlException, IOException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTFtnEdnRef parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTFtnEdnRef parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTFtnEdnRef parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTFtnEdnRef parse(Node node) throws XmlException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTFtnEdnRef parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTFtnEdnRef parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTFtnEdnRef parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTFtnEdnRef)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

