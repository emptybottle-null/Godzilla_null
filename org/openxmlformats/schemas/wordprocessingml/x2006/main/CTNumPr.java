/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering;
import org.w3c.dom.Node;

public interface CTNumPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTNumPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctnumpr16aatype");

    public CTDecimalNumber getIlvl();

    public boolean isSetIlvl();

    public void setIlvl(CTDecimalNumber var1);

    public CTDecimalNumber addNewIlvl();

    public void unsetIlvl();

    public CTDecimalNumber getNumId();

    public boolean isSetNumId();

    public void setNumId(CTDecimalNumber var1);

    public CTDecimalNumber addNewNumId();

    public void unsetNumId();

    public CTTrackChangeNumbering getNumberingChange();

    public boolean isSetNumberingChange();

    public void setNumberingChange(CTTrackChangeNumbering var1);

    public CTTrackChangeNumbering addNewNumberingChange();

    public void unsetNumberingChange();

    public CTTrackChange getIns();

    public boolean isSetIns();

    public void setIns(CTTrackChange var1);

    public CTTrackChange addNewIns();

    public void unsetIns();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTNumPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTNumPr newInstance() {
            return (CTNumPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTNumPr newInstance(XmlOptions xmlOptions) {
            return (CTNumPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTNumPr parse(String string) throws XmlException {
            return (CTNumPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTNumPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTNumPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTNumPr parse(File file) throws XmlException, IOException {
            return (CTNumPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTNumPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTNumPr parse(URL uRL) throws XmlException, IOException {
            return (CTNumPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTNumPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTNumPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTNumPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTNumPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTNumPr parse(Reader reader) throws XmlException, IOException {
            return (CTNumPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTNumPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTNumPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTNumPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTNumPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTNumPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTNumPr parse(Node node) throws XmlException {
            return (CTNumPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTNumPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTNumPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTNumPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTNumPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTNumPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTNumPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

