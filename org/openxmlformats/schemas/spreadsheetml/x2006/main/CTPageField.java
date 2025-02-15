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
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTPageField
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPageField.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpagefield338atype");

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public int getFld();

    public XmlInt xgetFld();

    public void setFld(int var1);

    public void xsetFld(XmlInt var1);

    public long getItem();

    public XmlUnsignedInt xgetItem();

    public boolean isSetItem();

    public void setItem(long var1);

    public void xsetItem(XmlUnsignedInt var1);

    public void unsetItem();

    public int getHier();

    public XmlInt xgetHier();

    public boolean isSetHier();

    public void setHier(int var1);

    public void xsetHier(XmlInt var1);

    public void unsetHier();

    public String getName();

    public STXstring xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(STXstring var1);

    public void unsetName();

    public String getCap();

    public STXstring xgetCap();

    public boolean isSetCap();

    public void setCap(String var1);

    public void xsetCap(STXstring var1);

    public void unsetCap();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPageField.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPageField newInstance() {
            return (CTPageField)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPageField newInstance(XmlOptions xmlOptions) {
            return (CTPageField)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPageField parse(String string) throws XmlException {
            return (CTPageField)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPageField parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPageField)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPageField parse(File file) throws XmlException, IOException {
            return (CTPageField)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPageField parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageField)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPageField parse(URL uRL) throws XmlException, IOException {
            return (CTPageField)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPageField parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageField)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPageField parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPageField)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPageField parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageField)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPageField parse(Reader reader) throws XmlException, IOException {
            return (CTPageField)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPageField parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageField)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPageField parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPageField)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPageField parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPageField)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPageField parse(Node node) throws XmlException {
            return (CTPageField)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPageField parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPageField)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPageField parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPageField)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPageField parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPageField)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

