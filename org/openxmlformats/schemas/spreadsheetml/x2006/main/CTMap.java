/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding
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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding;
import org.w3c.dom.Node;

public interface CTMap
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTMap.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctmap023btype");

    public CTDataBinding getDataBinding();

    public boolean isSetDataBinding();

    public void setDataBinding(CTDataBinding var1);

    public CTDataBinding addNewDataBinding();

    public void unsetDataBinding();

    public long getID();

    public XmlUnsignedInt xgetID();

    public void setID(long var1);

    public void xsetID(XmlUnsignedInt var1);

    public String getName();

    public XmlString xgetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public String getRootElement();

    public XmlString xgetRootElement();

    public void setRootElement(String var1);

    public void xsetRootElement(XmlString var1);

    public String getSchemaID();

    public XmlString xgetSchemaID();

    public void setSchemaID(String var1);

    public void xsetSchemaID(XmlString var1);

    public boolean getShowImportExportValidationErrors();

    public XmlBoolean xgetShowImportExportValidationErrors();

    public void setShowImportExportValidationErrors(boolean var1);

    public void xsetShowImportExportValidationErrors(XmlBoolean var1);

    public boolean getAutoFit();

    public XmlBoolean xgetAutoFit();

    public void setAutoFit(boolean var1);

    public void xsetAutoFit(XmlBoolean var1);

    public boolean getAppend();

    public XmlBoolean xgetAppend();

    public void setAppend(boolean var1);

    public void xsetAppend(XmlBoolean var1);

    public boolean getPreserveSortAFLayout();

    public XmlBoolean xgetPreserveSortAFLayout();

    public void setPreserveSortAFLayout(boolean var1);

    public void xsetPreserveSortAFLayout(XmlBoolean var1);

    public boolean getPreserveFormat();

    public XmlBoolean xgetPreserveFormat();

    public void setPreserveFormat(boolean var1);

    public void xsetPreserveFormat(XmlBoolean var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTMap.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTMap newInstance() {
            return (CTMap)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTMap newInstance(XmlOptions xmlOptions) {
            return (CTMap)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTMap parse(String string) throws XmlException {
            return (CTMap)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTMap parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTMap)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTMap parse(File file) throws XmlException, IOException {
            return (CTMap)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTMap parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMap)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTMap parse(URL uRL) throws XmlException, IOException {
            return (CTMap)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTMap parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMap)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTMap parse(InputStream inputStream) throws XmlException, IOException {
            return (CTMap)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTMap parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMap)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTMap parse(Reader reader) throws XmlException, IOException {
            return (CTMap)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTMap parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMap)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTMap parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTMap)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTMap parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTMap)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTMap parse(Node node) throws XmlException {
            return (CTMap)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTMap parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTMap)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTMap parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTMap)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTMap parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTMap)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

