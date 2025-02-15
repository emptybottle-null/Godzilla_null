/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.w3c.dom.Node;

public interface CTSst
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSst.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsst44f3type");

    public List<CTRst> getSiList();

    @Deprecated
    public CTRst[] getSiArray();

    public CTRst getSiArray(int var1);

    public int sizeOfSiArray();

    public void setSiArray(CTRst[] var1);

    public void setSiArray(int var1, CTRst var2);

    public CTRst insertNewSi(int var1);

    public CTRst addNewSi();

    public void removeSi(int var1);

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getCount();

    public XmlUnsignedInt xgetCount();

    public boolean isSetCount();

    public void setCount(long var1);

    public void xsetCount(XmlUnsignedInt var1);

    public void unsetCount();

    public long getUniqueCount();

    public XmlUnsignedInt xgetUniqueCount();

    public boolean isSetUniqueCount();

    public void setUniqueCount(long var1);

    public void xsetUniqueCount(XmlUnsignedInt var1);

    public void unsetUniqueCount();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSst.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSst newInstance() {
            return (CTSst)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSst newInstance(XmlOptions xmlOptions) {
            return (CTSst)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSst parse(String string) throws XmlException {
            return (CTSst)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSst parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSst)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSst parse(File file) throws XmlException, IOException {
            return (CTSst)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSst parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSst)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSst parse(URL uRL) throws XmlException, IOException {
            return (CTSst)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSst parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSst)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSst parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSst)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSst parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSst)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSst parse(Reader reader) throws XmlException, IOException {
            return (CTSst)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSst parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSst)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSst parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSst)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSst parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSst)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSst parse(Node node) throws XmlException {
            return (CTSst)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSst parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSst)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSst parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSst)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSst parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSst)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

