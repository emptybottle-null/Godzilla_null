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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign;
import org.w3c.dom.Node;

public interface CTRubyPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRubyPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctrubyprb2actype");

    public CTRubyAlign getRubyAlign();

    public void setRubyAlign(CTRubyAlign var1);

    public CTRubyAlign addNewRubyAlign();

    public CTHpsMeasure getHps();

    public void setHps(CTHpsMeasure var1);

    public CTHpsMeasure addNewHps();

    public CTHpsMeasure getHpsRaise();

    public void setHpsRaise(CTHpsMeasure var1);

    public CTHpsMeasure addNewHpsRaise();

    public CTHpsMeasure getHpsBaseText();

    public void setHpsBaseText(CTHpsMeasure var1);

    public CTHpsMeasure addNewHpsBaseText();

    public CTLang getLid();

    public void setLid(CTLang var1);

    public CTLang addNewLid();

    public CTOnOff getDirty();

    public boolean isSetDirty();

    public void setDirty(CTOnOff var1);

    public CTOnOff addNewDirty();

    public void unsetDirty();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRubyPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRubyPr newInstance() {
            return (CTRubyPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRubyPr newInstance(XmlOptions xmlOptions) {
            return (CTRubyPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRubyPr parse(String string) throws XmlException {
            return (CTRubyPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRubyPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRubyPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRubyPr parse(File file) throws XmlException, IOException {
            return (CTRubyPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRubyPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRubyPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRubyPr parse(URL uRL) throws XmlException, IOException {
            return (CTRubyPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRubyPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRubyPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRubyPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRubyPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRubyPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRubyPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRubyPr parse(Reader reader) throws XmlException, IOException {
            return (CTRubyPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRubyPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRubyPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRubyPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRubyPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRubyPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRubyPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRubyPr parse(Node node) throws XmlException {
            return (CTRubyPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRubyPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRubyPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRubyPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRubyPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRubyPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRubyPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

