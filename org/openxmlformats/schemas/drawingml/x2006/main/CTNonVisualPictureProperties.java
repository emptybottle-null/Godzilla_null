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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPictureLocking;
import org.w3c.dom.Node;

public interface CTNonVisualPictureProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTNonVisualPictureProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctnonvisualpicturepropertiesee3ftype");

    public CTPictureLocking getPicLocks();

    public boolean isSetPicLocks();

    public void setPicLocks(CTPictureLocking var1);

    public CTPictureLocking addNewPicLocks();

    public void unsetPicLocks();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public boolean getPreferRelativeResize();

    public XmlBoolean xgetPreferRelativeResize();

    public boolean isSetPreferRelativeResize();

    public void setPreferRelativeResize(boolean var1);

    public void xsetPreferRelativeResize(XmlBoolean var1);

    public void unsetPreferRelativeResize();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTNonVisualPictureProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTNonVisualPictureProperties newInstance() {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTNonVisualPictureProperties newInstance(XmlOptions xmlOptions) {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTNonVisualPictureProperties parse(String string) throws XmlException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTNonVisualPictureProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTNonVisualPictureProperties parse(File file) throws XmlException, IOException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTNonVisualPictureProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTNonVisualPictureProperties parse(URL uRL) throws XmlException, IOException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTNonVisualPictureProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTNonVisualPictureProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTNonVisualPictureProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTNonVisualPictureProperties parse(Reader reader) throws XmlException, IOException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTNonVisualPictureProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTNonVisualPictureProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTNonVisualPictureProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTNonVisualPictureProperties parse(Node node) throws XmlException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTNonVisualPictureProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTNonVisualPictureProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTNonVisualPictureProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTNonVisualPictureProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

