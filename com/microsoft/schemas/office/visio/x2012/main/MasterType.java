/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.IconType
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.IconType;
import com.microsoft.schemas.office.visio.x2012.main.PageSheetType;
import com.microsoft.schemas.office.visio.x2012.main.RelType;
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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.XmlUnsignedShort;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface MasterType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(MasterType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("mastertype2d97type");

    public PageSheetType getPageSheet();

    public boolean isSetPageSheet();

    public void setPageSheet(PageSheetType var1);

    public PageSheetType addNewPageSheet();

    public void unsetPageSheet();

    public RelType getRel();

    public void setRel(RelType var1);

    public RelType addNewRel();

    public IconType getIcon();

    public boolean isSetIcon();

    public void setIcon(IconType var1);

    public IconType addNewIcon();

    public void unsetIcon();

    public long getID();

    public XmlUnsignedInt xgetID();

    public void setID(long var1);

    public void xsetID(XmlUnsignedInt var1);

    public String getBaseID();

    public XmlString xgetBaseID();

    public boolean isSetBaseID();

    public void setBaseID(String var1);

    public void xsetBaseID(XmlString var1);

    public void unsetBaseID();

    public String getUniqueID();

    public XmlString xgetUniqueID();

    public boolean isSetUniqueID();

    public void setUniqueID(String var1);

    public void xsetUniqueID(XmlString var1);

    public void unsetUniqueID();

    public boolean getMatchByName();

    public XmlBoolean xgetMatchByName();

    public boolean isSetMatchByName();

    public void setMatchByName(boolean var1);

    public void xsetMatchByName(XmlBoolean var1);

    public void unsetMatchByName();

    public String getName();

    public XmlString xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public void unsetName();

    public String getNameU();

    public XmlString xgetNameU();

    public boolean isSetNameU();

    public void setNameU(String var1);

    public void xsetNameU(XmlString var1);

    public void unsetNameU();

    public boolean getIsCustomName();

    public XmlBoolean xgetIsCustomName();

    public boolean isSetIsCustomName();

    public void setIsCustomName(boolean var1);

    public void xsetIsCustomName(XmlBoolean var1);

    public void unsetIsCustomName();

    public boolean getIsCustomNameU();

    public XmlBoolean xgetIsCustomNameU();

    public boolean isSetIsCustomNameU();

    public void setIsCustomNameU(boolean var1);

    public void xsetIsCustomNameU(XmlBoolean var1);

    public void unsetIsCustomNameU();

    public int getIconSize();

    public XmlUnsignedShort xgetIconSize();

    public boolean isSetIconSize();

    public void setIconSize(int var1);

    public void xsetIconSize(XmlUnsignedShort var1);

    public void unsetIconSize();

    public int getPatternFlags();

    public XmlUnsignedShort xgetPatternFlags();

    public boolean isSetPatternFlags();

    public void setPatternFlags(int var1);

    public void xsetPatternFlags(XmlUnsignedShort var1);

    public void unsetPatternFlags();

    public String getPrompt();

    public XmlString xgetPrompt();

    public boolean isSetPrompt();

    public void setPrompt(String var1);

    public void xsetPrompt(XmlString var1);

    public void unsetPrompt();

    public boolean getHidden();

    public XmlBoolean xgetHidden();

    public boolean isSetHidden();

    public void setHidden(boolean var1);

    public void xsetHidden(XmlBoolean var1);

    public void unsetHidden();

    public boolean getIconUpdate();

    public XmlBoolean xgetIconUpdate();

    public boolean isSetIconUpdate();

    public void setIconUpdate(boolean var1);

    public void xsetIconUpdate(XmlBoolean var1);

    public void unsetIconUpdate();

    public int getAlignName();

    public XmlUnsignedShort xgetAlignName();

    public boolean isSetAlignName();

    public void setAlignName(int var1);

    public void xsetAlignName(XmlUnsignedShort var1);

    public void unsetAlignName();

    public int getMasterType();

    public XmlUnsignedShort xgetMasterType();

    public boolean isSetMasterType();

    public void setMasterType(int var1);

    public void xsetMasterType(XmlUnsignedShort var1);

    public void unsetMasterType();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(MasterType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static MasterType newInstance() {
            return (MasterType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static MasterType newInstance(XmlOptions xmlOptions) {
            return (MasterType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static MasterType parse(String string) throws XmlException {
            return (MasterType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static MasterType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (MasterType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static MasterType parse(File file) throws XmlException, IOException {
            return (MasterType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static MasterType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (MasterType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static MasterType parse(URL uRL) throws XmlException, IOException {
            return (MasterType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static MasterType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (MasterType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static MasterType parse(InputStream inputStream) throws XmlException, IOException {
            return (MasterType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static MasterType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (MasterType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static MasterType parse(Reader reader) throws XmlException, IOException {
            return (MasterType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static MasterType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (MasterType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static MasterType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (MasterType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static MasterType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (MasterType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static MasterType parse(Node node) throws XmlException {
            return (MasterType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static MasterType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (MasterType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static MasterType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (MasterType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static MasterType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (MasterType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

