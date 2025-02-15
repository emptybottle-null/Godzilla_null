/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTColorSchemeList
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTCustomColorList
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTObjectStyleDefaults
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorSchemeList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomColorList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTObjectStyleDefaults;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.w3c.dom.Node;

public interface CTOfficeStyleSheet
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTOfficeStyleSheet.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctofficestylesheetce25type");

    public CTBaseStyles getThemeElements();

    public void setThemeElements(CTBaseStyles var1);

    public CTBaseStyles addNewThemeElements();

    public CTObjectStyleDefaults getObjectDefaults();

    public boolean isSetObjectDefaults();

    public void setObjectDefaults(CTObjectStyleDefaults var1);

    public CTObjectStyleDefaults addNewObjectDefaults();

    public void unsetObjectDefaults();

    public CTColorSchemeList getExtraClrSchemeLst();

    public boolean isSetExtraClrSchemeLst();

    public void setExtraClrSchemeLst(CTColorSchemeList var1);

    public CTColorSchemeList addNewExtraClrSchemeLst();

    public void unsetExtraClrSchemeLst();

    public CTCustomColorList getCustClrLst();

    public boolean isSetCustClrLst();

    public void setCustClrLst(CTCustomColorList var1);

    public CTCustomColorList addNewCustClrLst();

    public void unsetCustClrLst();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getName();

    public XmlString xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public void unsetName();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTOfficeStyleSheet.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTOfficeStyleSheet newInstance() {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTOfficeStyleSheet newInstance(XmlOptions xmlOptions) {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTOfficeStyleSheet parse(String string) throws XmlException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTOfficeStyleSheet parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTOfficeStyleSheet parse(File file) throws XmlException, IOException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTOfficeStyleSheet parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTOfficeStyleSheet parse(URL uRL) throws XmlException, IOException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTOfficeStyleSheet parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTOfficeStyleSheet parse(InputStream inputStream) throws XmlException, IOException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTOfficeStyleSheet parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTOfficeStyleSheet parse(Reader reader) throws XmlException, IOException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTOfficeStyleSheet parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTOfficeStyleSheet parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTOfficeStyleSheet parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTOfficeStyleSheet parse(Node node) throws XmlException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTOfficeStyleSheet parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTOfficeStyleSheet parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTOfficeStyleSheet parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTOfficeStyleSheet)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

