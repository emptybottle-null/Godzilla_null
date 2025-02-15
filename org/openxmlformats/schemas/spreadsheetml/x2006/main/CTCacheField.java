/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTX
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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSharedItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTX;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STNumFmtId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTCacheField
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCacheField.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcachefieldae21type");

    public CTSharedItems getSharedItems();

    public boolean isSetSharedItems();

    public void setSharedItems(CTSharedItems var1);

    public CTSharedItems addNewSharedItems();

    public void unsetSharedItems();

    public CTFieldGroup getFieldGroup();

    public boolean isSetFieldGroup();

    public void setFieldGroup(CTFieldGroup var1);

    public CTFieldGroup addNewFieldGroup();

    public void unsetFieldGroup();

    public List<CTX> getMpMapList();

    @Deprecated
    public CTX[] getMpMapArray();

    public CTX getMpMapArray(int var1);

    public int sizeOfMpMapArray();

    public void setMpMapArray(CTX[] var1);

    public void setMpMapArray(int var1, CTX var2);

    public CTX insertNewMpMap(int var1);

    public CTX addNewMpMap();

    public void removeMpMap(int var1);

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getName();

    public STXstring xgetName();

    public void setName(String var1);

    public void xsetName(STXstring var1);

    public String getCaption();

    public STXstring xgetCaption();

    public boolean isSetCaption();

    public void setCaption(String var1);

    public void xsetCaption(STXstring var1);

    public void unsetCaption();

    public String getPropertyName();

    public STXstring xgetPropertyName();

    public boolean isSetPropertyName();

    public void setPropertyName(String var1);

    public void xsetPropertyName(STXstring var1);

    public void unsetPropertyName();

    public boolean getServerField();

    public XmlBoolean xgetServerField();

    public boolean isSetServerField();

    public void setServerField(boolean var1);

    public void xsetServerField(XmlBoolean var1);

    public void unsetServerField();

    public boolean getUniqueList();

    public XmlBoolean xgetUniqueList();

    public boolean isSetUniqueList();

    public void setUniqueList(boolean var1);

    public void xsetUniqueList(XmlBoolean var1);

    public void unsetUniqueList();

    public long getNumFmtId();

    public STNumFmtId xgetNumFmtId();

    public boolean isSetNumFmtId();

    public void setNumFmtId(long var1);

    public void xsetNumFmtId(STNumFmtId var1);

    public void unsetNumFmtId();

    public String getFormula();

    public STXstring xgetFormula();

    public boolean isSetFormula();

    public void setFormula(String var1);

    public void xsetFormula(STXstring var1);

    public void unsetFormula();

    public int getSqlType();

    public XmlInt xgetSqlType();

    public boolean isSetSqlType();

    public void setSqlType(int var1);

    public void xsetSqlType(XmlInt var1);

    public void unsetSqlType();

    public int getHierarchy();

    public XmlInt xgetHierarchy();

    public boolean isSetHierarchy();

    public void setHierarchy(int var1);

    public void xsetHierarchy(XmlInt var1);

    public void unsetHierarchy();

    public long getLevel();

    public XmlUnsignedInt xgetLevel();

    public boolean isSetLevel();

    public void setLevel(long var1);

    public void xsetLevel(XmlUnsignedInt var1);

    public void unsetLevel();

    public boolean getDatabaseField();

    public XmlBoolean xgetDatabaseField();

    public boolean isSetDatabaseField();

    public void setDatabaseField(boolean var1);

    public void xsetDatabaseField(XmlBoolean var1);

    public void unsetDatabaseField();

    public long getMappingCount();

    public XmlUnsignedInt xgetMappingCount();

    public boolean isSetMappingCount();

    public void setMappingCount(long var1);

    public void xsetMappingCount(XmlUnsignedInt var1);

    public void unsetMappingCount();

    public boolean getMemberPropertyField();

    public XmlBoolean xgetMemberPropertyField();

    public boolean isSetMemberPropertyField();

    public void setMemberPropertyField(boolean var1);

    public void xsetMemberPropertyField(XmlBoolean var1);

    public void unsetMemberPropertyField();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCacheField.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCacheField newInstance() {
            return (CTCacheField)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCacheField newInstance(XmlOptions xmlOptions) {
            return (CTCacheField)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCacheField parse(String string) throws XmlException {
            return (CTCacheField)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCacheField parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCacheField)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCacheField parse(File file) throws XmlException, IOException {
            return (CTCacheField)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCacheField parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCacheField)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCacheField parse(URL uRL) throws XmlException, IOException {
            return (CTCacheField)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCacheField parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCacheField)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCacheField parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCacheField)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCacheField parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCacheField)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCacheField parse(Reader reader) throws XmlException, IOException {
            return (CTCacheField)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCacheField parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCacheField)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCacheField parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCacheField)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCacheField parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCacheField)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCacheField parse(Node node) throws XmlException {
            return (CTCacheField)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCacheField parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCacheField)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCacheField parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCacheField)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCacheField parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCacheField)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

