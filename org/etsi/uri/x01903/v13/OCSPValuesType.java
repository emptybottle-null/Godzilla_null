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

public interface OCSPValuesType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(OCSPValuesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ocspvaluestypeb421type");

    public List<EncapsulatedPKIDataType> getEncapsulatedOCSPValueList();

    @Deprecated
    public EncapsulatedPKIDataType[] getEncapsulatedOCSPValueArray();

    public EncapsulatedPKIDataType getEncapsulatedOCSPValueArray(int var1);

    public int sizeOfEncapsulatedOCSPValueArray();

    public void setEncapsulatedOCSPValueArray(EncapsulatedPKIDataType[] var1);

    public void setEncapsulatedOCSPValueArray(int var1, EncapsulatedPKIDataType var2);

    public EncapsulatedPKIDataType insertNewEncapsulatedOCSPValue(int var1);

    public EncapsulatedPKIDataType addNewEncapsulatedOCSPValue();

    public void removeEncapsulatedOCSPValue(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(OCSPValuesType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static OCSPValuesType newInstance() {
            return (OCSPValuesType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static OCSPValuesType newInstance(XmlOptions xmlOptions) {
            return (OCSPValuesType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static OCSPValuesType parse(String string) throws XmlException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static OCSPValuesType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static OCSPValuesType parse(File file) throws XmlException, IOException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static OCSPValuesType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static OCSPValuesType parse(URL uRL) throws XmlException, IOException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static OCSPValuesType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static OCSPValuesType parse(InputStream inputStream) throws XmlException, IOException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static OCSPValuesType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static OCSPValuesType parse(Reader reader) throws XmlException, IOException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static OCSPValuesType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static OCSPValuesType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static OCSPValuesType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static OCSPValuesType parse(Node node) throws XmlException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static OCSPValuesType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static OCSPValuesType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static OCSPValuesType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (OCSPValuesType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

