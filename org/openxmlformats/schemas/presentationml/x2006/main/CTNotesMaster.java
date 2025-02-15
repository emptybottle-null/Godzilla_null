/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;
import org.w3c.dom.Node;

public interface CTNotesMaster
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTNotesMaster.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctnotesmaster69ectype");

    public CTCommonSlideData getCSld();

    public void setCSld(CTCommonSlideData var1);

    public CTCommonSlideData addNewCSld();

    public CTColorMapping getClrMap();

    public void setClrMap(CTColorMapping var1);

    public CTColorMapping addNewClrMap();

    public CTHeaderFooter getHf();

    public boolean isSetHf();

    public void setHf(CTHeaderFooter var1);

    public CTHeaderFooter addNewHf();

    public void unsetHf();

    public CTTextListStyle getNotesStyle();

    public boolean isSetNotesStyle();

    public void setNotesStyle(CTTextListStyle var1);

    public CTTextListStyle addNewNotesStyle();

    public void unsetNotesStyle();

    public CTExtensionListModify getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionListModify var1);

    public CTExtensionListModify addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTNotesMaster.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTNotesMaster newInstance() {
            return (CTNotesMaster)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTNotesMaster newInstance(XmlOptions xmlOptions) {
            return (CTNotesMaster)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTNotesMaster parse(String string) throws XmlException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTNotesMaster parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTNotesMaster parse(File file) throws XmlException, IOException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTNotesMaster parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTNotesMaster parse(URL uRL) throws XmlException, IOException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTNotesMaster parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTNotesMaster parse(InputStream inputStream) throws XmlException, IOException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTNotesMaster parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTNotesMaster parse(Reader reader) throws XmlException, IOException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTNotesMaster parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTNotesMaster parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTNotesMaster parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTNotesMaster parse(Node node) throws XmlException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTNotesMaster parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTNotesMaster parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTNotesMaster parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTNotesMaster)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

