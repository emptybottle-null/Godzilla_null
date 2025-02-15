/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.office.CTRegroupTable
 *  com.microsoft.schemas.office.office.CTRules
 */
package com.microsoft.schemas.office.office;

import com.microsoft.schemas.office.office.CTIdMap;
import com.microsoft.schemas.office.office.CTRegroupTable;
import com.microsoft.schemas.office.office.CTRules;
import com.microsoft.schemas.vml.STExt;
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
import org.w3c.dom.Node;

public interface CTShapeLayout
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTShapeLayout.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctshapelayoutbda4type");

    public CTIdMap getIdmap();

    public boolean isSetIdmap();

    public void setIdmap(CTIdMap var1);

    public CTIdMap addNewIdmap();

    public void unsetIdmap();

    public CTRegroupTable getRegrouptable();

    public boolean isSetRegrouptable();

    public void setRegrouptable(CTRegroupTable var1);

    public CTRegroupTable addNewRegrouptable();

    public void unsetRegrouptable();

    public CTRules getRules();

    public boolean isSetRules();

    public void setRules(CTRules var1);

    public CTRules addNewRules();

    public void unsetRules();

    public STExt.Enum getExt();

    public STExt xgetExt();

    public boolean isSetExt();

    public void setExt(STExt.Enum var1);

    public void xsetExt(STExt var1);

    public void unsetExt();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTShapeLayout.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTShapeLayout newInstance() {
            return (CTShapeLayout)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTShapeLayout newInstance(XmlOptions xmlOptions) {
            return (CTShapeLayout)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTShapeLayout parse(String string) throws XmlException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTShapeLayout parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTShapeLayout parse(File file) throws XmlException, IOException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTShapeLayout parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTShapeLayout parse(URL uRL) throws XmlException, IOException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTShapeLayout parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTShapeLayout parse(InputStream inputStream) throws XmlException, IOException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTShapeLayout parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTShapeLayout parse(Reader reader) throws XmlException, IOException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTShapeLayout parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTShapeLayout parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTShapeLayout parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTShapeLayout parse(Node node) throws XmlException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTShapeLayout parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTShapeLayout parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTShapeLayout parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTShapeLayout)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

