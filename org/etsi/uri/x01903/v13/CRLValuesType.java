/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13;

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
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;
import org.w3c.dom.Node;

public interface CRLValuesType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CRLValuesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("crlvaluestype0ebbtype");

    public List<EncapsulatedPKIDataType> getEncapsulatedCRLValueList();

    @Deprecated
    public EncapsulatedPKIDataType[] getEncapsulatedCRLValueArray();

    public EncapsulatedPKIDataType getEncapsulatedCRLValueArray(int var1);

    public int sizeOfEncapsulatedCRLValueArray();

    public void setEncapsulatedCRLValueArray(EncapsulatedPKIDataType[] var1);

    public void setEncapsulatedCRLValueArray(int var1, EncapsulatedPKIDataType var2);

    public EncapsulatedPKIDataType insertNewEncapsulatedCRLValue(int var1);

    public EncapsulatedPKIDataType addNewEncapsulatedCRLValue();

    public void removeEncapsulatedCRLValue(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CRLValuesType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CRLValuesType newInstance() {
            return (CRLValuesType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CRLValuesType newInstance(XmlOptions xmlOptions) {
            return (CRLValuesType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CRLValuesType parse(String string) throws XmlException {
            return (CRLValuesType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CRLValuesType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CRLValuesType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CRLValuesType parse(File file) throws XmlException, IOException {
            return (CRLValuesType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CRLValuesType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLValuesType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CRLValuesType parse(URL uRL) throws XmlException, IOException {
            return (CRLValuesType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CRLValuesType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLValuesType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CRLValuesType parse(InputStream inputStream) throws XmlException, IOException {
            return (CRLValuesType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CRLValuesType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLValuesType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CRLValuesType parse(Reader reader) throws XmlException, IOException {
            return (CRLValuesType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CRLValuesType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLValuesType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CRLValuesType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CRLValuesType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CRLValuesType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CRLValuesType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CRLValuesType parse(Node node) throws XmlException {
            return (CRLValuesType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CRLValuesType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CRLValuesType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CRLValuesType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CRLValuesType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CRLValuesType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CRLValuesType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

