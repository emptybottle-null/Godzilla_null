/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.MasterShortcutType
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.MasterShortcutType;
import com.microsoft.schemas.office.visio.x2012.main.MasterType;
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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface MastersType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(MastersType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("masterstypeaebatype");

    public List<MasterType> getMasterList();

    @Deprecated
    public MasterType[] getMasterArray();

    public MasterType getMasterArray(int var1);

    public int sizeOfMasterArray();

    public void setMasterArray(MasterType[] var1);

    public void setMasterArray(int var1, MasterType var2);

    public MasterType insertNewMaster(int var1);

    public MasterType addNewMaster();

    public void removeMaster(int var1);

    public List<MasterShortcutType> getMasterShortcutList();

    @Deprecated
    public MasterShortcutType[] getMasterShortcutArray();

    public MasterShortcutType getMasterShortcutArray(int var1);

    public int sizeOfMasterShortcutArray();

    public void setMasterShortcutArray(MasterShortcutType[] var1);

    public void setMasterShortcutArray(int var1, MasterShortcutType var2);

    public MasterShortcutType insertNewMasterShortcut(int var1);

    public MasterShortcutType addNewMasterShortcut();

    public void removeMasterShortcut(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(MastersType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static MastersType newInstance() {
            return (MastersType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static MastersType newInstance(XmlOptions xmlOptions) {
            return (MastersType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static MastersType parse(String string) throws XmlException {
            return (MastersType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static MastersType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (MastersType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static MastersType parse(File file) throws XmlException, IOException {
            return (MastersType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static MastersType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (MastersType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static MastersType parse(URL uRL) throws XmlException, IOException {
            return (MastersType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static MastersType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (MastersType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static MastersType parse(InputStream inputStream) throws XmlException, IOException {
            return (MastersType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static MastersType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (MastersType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static MastersType parse(Reader reader) throws XmlException, IOException {
            return (MastersType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static MastersType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (MastersType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static MastersType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (MastersType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static MastersType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (MastersType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static MastersType parse(Node node) throws XmlException {
            return (MastersType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static MastersType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (MastersType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static MastersType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (MastersType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static MastersType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (MastersType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

