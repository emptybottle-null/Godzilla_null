/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrChange
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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.w3c.dom.Node;

public interface CTTrPr
extends CTTrPrBase {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTrPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttrpr2848type");

    public CTTrackChange getIns();

    public boolean isSetIns();

    public void setIns(CTTrackChange var1);

    public CTTrackChange addNewIns();

    public void unsetIns();

    public CTTrackChange getDel();

    public boolean isSetDel();

    public void setDel(CTTrackChange var1);

    public CTTrackChange addNewDel();

    public void unsetDel();

    public CTTrPrChange getTrPrChange();

    public boolean isSetTrPrChange();

    public void setTrPrChange(CTTrPrChange var1);

    public CTTrPrChange addNewTrPrChange();

    public void unsetTrPrChange();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTrPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTrPr newInstance() {
            return (CTTrPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTrPr newInstance(XmlOptions xmlOptions) {
            return (CTTrPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTrPr parse(String string) throws XmlException {
            return (CTTrPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTrPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTrPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTrPr parse(File file) throws XmlException, IOException {
            return (CTTrPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTrPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTrPr parse(URL uRL) throws XmlException, IOException {
            return (CTTrPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTrPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTrPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTrPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTrPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTrPr parse(Reader reader) throws XmlException, IOException {
            return (CTTrPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTrPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTrPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTrPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTrPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTrPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTrPr parse(Node node) throws XmlException {
            return (CTTrPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTrPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTrPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTrPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTrPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTrPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTrPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

