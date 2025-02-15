/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.w3c.dom.Node;

public interface CTColorScheme
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTColorScheme.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcolorscheme0e99type");

    public CTColor getDk1();

    public void setDk1(CTColor var1);

    public CTColor addNewDk1();

    public CTColor getLt1();

    public void setLt1(CTColor var1);

    public CTColor addNewLt1();

    public CTColor getDk2();

    public void setDk2(CTColor var1);

    public CTColor addNewDk2();

    public CTColor getLt2();

    public void setLt2(CTColor var1);

    public CTColor addNewLt2();

    public CTColor getAccent1();

    public void setAccent1(CTColor var1);

    public CTColor addNewAccent1();

    public CTColor getAccent2();

    public void setAccent2(CTColor var1);

    public CTColor addNewAccent2();

    public CTColor getAccent3();

    public void setAccent3(CTColor var1);

    public CTColor addNewAccent3();

    public CTColor getAccent4();

    public void setAccent4(CTColor var1);

    public CTColor addNewAccent4();

    public CTColor getAccent5();

    public void setAccent5(CTColor var1);

    public CTColor addNewAccent5();

    public CTColor getAccent6();

    public void setAccent6(CTColor var1);

    public CTColor addNewAccent6();

    public CTColor getHlink();

    public void setHlink(CTColor var1);

    public CTColor addNewHlink();

    public CTColor getFolHlink();

    public void setFolHlink(CTColor var1);

    public CTColor addNewFolHlink();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getName();

    public XmlString xgetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTColorScheme.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTColorScheme newInstance() {
            return (CTColorScheme)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTColorScheme newInstance(XmlOptions xmlOptions) {
            return (CTColorScheme)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTColorScheme parse(String string) throws XmlException {
            return (CTColorScheme)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTColorScheme parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTColorScheme)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTColorScheme parse(File file) throws XmlException, IOException {
            return (CTColorScheme)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTColorScheme parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorScheme)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTColorScheme parse(URL uRL) throws XmlException, IOException {
            return (CTColorScheme)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTColorScheme parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorScheme)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTColorScheme parse(InputStream inputStream) throws XmlException, IOException {
            return (CTColorScheme)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTColorScheme parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorScheme)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTColorScheme parse(Reader reader) throws XmlException, IOException {
            return (CTColorScheme)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTColorScheme parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorScheme)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTColorScheme parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTColorScheme)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTColorScheme parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTColorScheme)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTColorScheme parse(Node node) throws XmlException {
            return (CTColorScheme)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTColorScheme parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTColorScheme)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTColorScheme parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTColorScheme)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTColorScheme parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTColorScheme)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

