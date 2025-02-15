/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STShowDataAs
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataConsolidateFunction;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STNumFmtId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STShowDataAs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTDataField
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDataField.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdatafield6f0ftype");

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getName();

    public STXstring xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(STXstring var1);

    public void unsetName();

    public long getFld();

    public XmlUnsignedInt xgetFld();

    public void setFld(long var1);

    public void xsetFld(XmlUnsignedInt var1);

    public STDataConsolidateFunction.Enum getSubtotal();

    public STDataConsolidateFunction xgetSubtotal();

    public boolean isSetSubtotal();

    public void setSubtotal(STDataConsolidateFunction.Enum var1);

    public void xsetSubtotal(STDataConsolidateFunction var1);

    public void unsetSubtotal();

    public STShowDataAs.Enum getShowDataAs();

    public STShowDataAs xgetShowDataAs();

    public boolean isSetShowDataAs();

    public void setShowDataAs(STShowDataAs.Enum var1);

    public void xsetShowDataAs(STShowDataAs var1);

    public void unsetShowDataAs();

    public int getBaseField();

    public XmlInt xgetBaseField();

    public boolean isSetBaseField();

    public void setBaseField(int var1);

    public void xsetBaseField(XmlInt var1);

    public void unsetBaseField();

    public long getBaseItem();

    public XmlUnsignedInt xgetBaseItem();

    public boolean isSetBaseItem();

    public void setBaseItem(long var1);

    public void xsetBaseItem(XmlUnsignedInt var1);

    public void unsetBaseItem();

    public long getNumFmtId();

    public STNumFmtId xgetNumFmtId();

    public boolean isSetNumFmtId();

    public void setNumFmtId(long var1);

    public void xsetNumFmtId(STNumFmtId var1);

    public void unsetNumFmtId();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDataField.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDataField newInstance() {
            return (CTDataField)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDataField newInstance(XmlOptions xmlOptions) {
            return (CTDataField)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDataField parse(String string) throws XmlException {
            return (CTDataField)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDataField parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDataField)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDataField parse(File file) throws XmlException, IOException {
            return (CTDataField)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDataField parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataField)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDataField parse(URL uRL) throws XmlException, IOException {
            return (CTDataField)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDataField parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataField)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDataField parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDataField)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDataField parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataField)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDataField parse(Reader reader) throws XmlException, IOException {
            return (CTDataField)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDataField parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataField)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDataField parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDataField)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDataField parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDataField)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDataField parse(Node node) throws XmlException {
            return (CTDataField)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDataField parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDataField)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDataField parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDataField)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDataField parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDataField)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

