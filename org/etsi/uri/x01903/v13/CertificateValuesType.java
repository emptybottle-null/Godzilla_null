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
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;
import org.w3c.dom.Node;

public interface CertificateValuesType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CertificateValuesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("certificatevaluestype5c75type");

    public List<EncapsulatedPKIDataType> getEncapsulatedX509CertificateList();

    @Deprecated
    public EncapsulatedPKIDataType[] getEncapsulatedX509CertificateArray();

    public EncapsulatedPKIDataType getEncapsulatedX509CertificateArray(int var1);

    public int sizeOfEncapsulatedX509CertificateArray();

    public void setEncapsulatedX509CertificateArray(EncapsulatedPKIDataType[] var1);

    public void setEncapsulatedX509CertificateArray(int var1, EncapsulatedPKIDataType var2);

    public EncapsulatedPKIDataType insertNewEncapsulatedX509Certificate(int var1);

    public EncapsulatedPKIDataType addNewEncapsulatedX509Certificate();

    public void removeEncapsulatedX509Certificate(int var1);

    public List<AnyType> getOtherCertificateList();

    @Deprecated
    public AnyType[] getOtherCertificateArray();

    public AnyType getOtherCertificateArray(int var1);

    public int sizeOfOtherCertificateArray();

    public void setOtherCertificateArray(AnyType[] var1);

    public void setOtherCertificateArray(int var1, AnyType var2);

    public AnyType insertNewOtherCertificate(int var1);

    public AnyType addNewOtherCertificate();

    public void removeOtherCertificate(int var1);

    public String getId();

    public XmlID xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(XmlID var1);

    public void unsetId();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CertificateValuesType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CertificateValuesType newInstance() {
            return (CertificateValuesType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CertificateValuesType newInstance(XmlOptions xmlOptions) {
            return (CertificateValuesType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CertificateValuesType parse(String string) throws XmlException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CertificateValuesType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CertificateValuesType parse(File file) throws XmlException, IOException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CertificateValuesType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CertificateValuesType parse(URL uRL) throws XmlException, IOException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CertificateValuesType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CertificateValuesType parse(InputStream inputStream) throws XmlException, IOException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CertificateValuesType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CertificateValuesType parse(Reader reader) throws XmlException, IOException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CertificateValuesType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CertificateValuesType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CertificateValuesType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CertificateValuesType parse(Node node) throws XmlException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CertificateValuesType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CertificateValuesType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CertificateValuesType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CertificateValuesType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

