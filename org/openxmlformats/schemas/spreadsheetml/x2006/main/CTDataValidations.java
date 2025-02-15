/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.w3c.dom.Node;

public interface CTDataValidations
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDataValidations.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdatavalidationse46ftype");

    public List<CTDataValidation> getDataValidationList();

    @Deprecated
    public CTDataValidation[] getDataValidationArray();

    public CTDataValidation getDataValidationArray(int var1);

    public int sizeOfDataValidationArray();

    public void setDataValidationArray(CTDataValidation[] var1);

    public void setDataValidationArray(int var1, CTDataValidation var2);

    public CTDataValidation insertNewDataValidation(int var1);

    public CTDataValidation addNewDataValidation();

    public void removeDataValidation(int var1);

    public boolean getDisablePrompts();

    public XmlBoolean xgetDisablePrompts();

    public boolean isSetDisablePrompts();

    public void setDisablePrompts(boolean var1);

    public void xsetDisablePrompts(XmlBoolean var1);

    public void unsetDisablePrompts();

    public long getXWindow();

    public XmlUnsignedInt xgetXWindow();

    public boolean isSetXWindow();

    public void setXWindow(long var1);

    public void xsetXWindow(XmlUnsignedInt var1);

    public void unsetXWindow();

    public long getYWindow();

    public XmlUnsignedInt xgetYWindow();

    public boolean isSetYWindow();

    public void setYWindow(long var1);

    public void xsetYWindow(XmlUnsignedInt var1);

    public void unsetYWindow();

    public long getCount();

    public XmlUnsignedInt xgetCount();

    public boolean isSetCount();

    public void setCount(long var1);

    public void xsetCount(XmlUnsignedInt var1);

    public void unsetCount();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDataValidations.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDataValidations newInstance() {
            return (CTDataValidations)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDataValidations newInstance(XmlOptions xmlOptions) {
            return (CTDataValidations)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDataValidations parse(String string) throws XmlException {
            return (CTDataValidations)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDataValidations parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDataValidations)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDataValidations parse(File file) throws XmlException, IOException {
            return (CTDataValidations)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDataValidations parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataValidations)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDataValidations parse(URL uRL) throws XmlException, IOException {
            return (CTDataValidations)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDataValidations parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataValidations)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDataValidations parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDataValidations)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDataValidations parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataValidations)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDataValidations parse(Reader reader) throws XmlException, IOException {
            return (CTDataValidations)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDataValidations parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataValidations)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDataValidations parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDataValidations)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDataValidations parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDataValidations)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDataValidations parse(Node node) throws XmlException {
            return (CTDataValidations)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDataValidations parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDataValidations)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDataValidations parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDataValidations)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDataValidations parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDataValidations)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

