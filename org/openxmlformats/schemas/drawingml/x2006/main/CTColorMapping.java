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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.STColorSchemeIndex;
import org.w3c.dom.Node;

public interface CTColorMapping
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTColorMapping.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcolormapping5bc6type");

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public STColorSchemeIndex.Enum getBg1();

    public STColorSchemeIndex xgetBg1();

    public void setBg1(STColorSchemeIndex.Enum var1);

    public void xsetBg1(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getTx1();

    public STColorSchemeIndex xgetTx1();

    public void setTx1(STColorSchemeIndex.Enum var1);

    public void xsetTx1(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getBg2();

    public STColorSchemeIndex xgetBg2();

    public void setBg2(STColorSchemeIndex.Enum var1);

    public void xsetBg2(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getTx2();

    public STColorSchemeIndex xgetTx2();

    public void setTx2(STColorSchemeIndex.Enum var1);

    public void xsetTx2(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getAccent1();

    public STColorSchemeIndex xgetAccent1();

    public void setAccent1(STColorSchemeIndex.Enum var1);

    public void xsetAccent1(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getAccent2();

    public STColorSchemeIndex xgetAccent2();

    public void setAccent2(STColorSchemeIndex.Enum var1);

    public void xsetAccent2(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getAccent3();

    public STColorSchemeIndex xgetAccent3();

    public void setAccent3(STColorSchemeIndex.Enum var1);

    public void xsetAccent3(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getAccent4();

    public STColorSchemeIndex xgetAccent4();

    public void setAccent4(STColorSchemeIndex.Enum var1);

    public void xsetAccent4(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getAccent5();

    public STColorSchemeIndex xgetAccent5();

    public void setAccent5(STColorSchemeIndex.Enum var1);

    public void xsetAccent5(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getAccent6();

    public STColorSchemeIndex xgetAccent6();

    public void setAccent6(STColorSchemeIndex.Enum var1);

    public void xsetAccent6(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getHlink();

    public STColorSchemeIndex xgetHlink();

    public void setHlink(STColorSchemeIndex.Enum var1);

    public void xsetHlink(STColorSchemeIndex var1);

    public STColorSchemeIndex.Enum getFolHlink();

    public STColorSchemeIndex xgetFolHlink();

    public void setFolHlink(STColorSchemeIndex.Enum var1);

    public void xsetFolHlink(STColorSchemeIndex var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTColorMapping.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTColorMapping newInstance() {
            return (CTColorMapping)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTColorMapping newInstance(XmlOptions xmlOptions) {
            return (CTColorMapping)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTColorMapping parse(String string) throws XmlException {
            return (CTColorMapping)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTColorMapping parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTColorMapping)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTColorMapping parse(File file) throws XmlException, IOException {
            return (CTColorMapping)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTColorMapping parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorMapping)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTColorMapping parse(URL uRL) throws XmlException, IOException {
            return (CTColorMapping)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTColorMapping parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorMapping)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTColorMapping parse(InputStream inputStream) throws XmlException, IOException {
            return (CTColorMapping)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTColorMapping parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorMapping)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTColorMapping parse(Reader reader) throws XmlException, IOException {
            return (CTColorMapping)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTColorMapping parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColorMapping)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTColorMapping parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTColorMapping)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTColorMapping parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTColorMapping)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTColorMapping parse(Node node) throws XmlException {
            return (CTColorMapping)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTColorMapping parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTColorMapping)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTColorMapping parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTColorMapping)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTColorMapping parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTColorMapping)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

