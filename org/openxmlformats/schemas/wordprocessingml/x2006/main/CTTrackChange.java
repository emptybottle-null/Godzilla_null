/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Calendar;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.w3c.dom.Node;

public interface CTTrackChange
extends CTMarkup {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTrackChange.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttrackchangec317type");

    public String getAuthor();

    public STString xgetAuthor();

    public void setAuthor(String var1);

    public void xsetAuthor(STString var1);

    public Calendar getDate();

    public STDateTime xgetDate();

    public boolean isSetDate();

    public void setDate(Calendar var1);

    public void xsetDate(STDateTime var1);

    public void unsetDate();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTrackChange.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTrackChange newInstance() {
            return (CTTrackChange)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTrackChange newInstance(XmlOptions xmlOptions) {
            return (CTTrackChange)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTrackChange parse(String string) throws XmlException {
            return (CTTrackChange)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTrackChange parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTrackChange)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTrackChange parse(File file) throws XmlException, IOException {
            return (CTTrackChange)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTrackChange parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrackChange)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTrackChange parse(URL uRL) throws XmlException, IOException {
            return (CTTrackChange)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTrackChange parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrackChange)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTrackChange parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTrackChange)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTrackChange parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrackChange)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTrackChange parse(Reader reader) throws XmlException, IOException {
            return (CTTrackChange)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTrackChange parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrackChange)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTrackChange parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTrackChange)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTrackChange parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTrackChange)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTrackChange parse(Node node) throws XmlException {
            return (CTTrackChange)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTrackChange parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTrackChange)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTrackChange parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTrackChange)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTrackChange parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTrackChange)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

