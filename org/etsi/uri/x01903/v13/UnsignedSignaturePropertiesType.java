/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.CounterSignatureType
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
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.CompleteCertificateRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.CounterSignatureType;
import org.etsi.uri.x01903.v13.RevocationValuesType;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;
import org.w3c.dom.Node;

public interface UnsignedSignaturePropertiesType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(UnsignedSignaturePropertiesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("unsignedsignaturepropertiestypecf32type");

    public List<CounterSignatureType> getCounterSignatureList();

    @Deprecated
    public CounterSignatureType[] getCounterSignatureArray();

    public CounterSignatureType getCounterSignatureArray(int var1);

    public int sizeOfCounterSignatureArray();

    public void setCounterSignatureArray(CounterSignatureType[] var1);

    public void setCounterSignatureArray(int var1, CounterSignatureType var2);

    public CounterSignatureType insertNewCounterSignature(int var1);

    public CounterSignatureType addNewCounterSignature();

    public void removeCounterSignature(int var1);

    public List<XAdESTimeStampType> getSignatureTimeStampList();

    @Deprecated
    public XAdESTimeStampType[] getSignatureTimeStampArray();

    public XAdESTimeStampType getSignatureTimeStampArray(int var1);

    public int sizeOfSignatureTimeStampArray();

    public void setSignatureTimeStampArray(XAdESTimeStampType[] var1);

    public void setSignatureTimeStampArray(int var1, XAdESTimeStampType var2);

    public XAdESTimeStampType insertNewSignatureTimeStamp(int var1);

    public XAdESTimeStampType addNewSignatureTimeStamp();

    public void removeSignatureTimeStamp(int var1);

    public List<CompleteCertificateRefsType> getCompleteCertificateRefsList();

    @Deprecated
    public CompleteCertificateRefsType[] getCompleteCertificateRefsArray();

    public CompleteCertificateRefsType getCompleteCertificateRefsArray(int var1);

    public int sizeOfCompleteCertificateRefsArray();

    public void setCompleteCertificateRefsArray(CompleteCertificateRefsType[] var1);

    public void setCompleteCertificateRefsArray(int var1, CompleteCertificateRefsType var2);

    public CompleteCertificateRefsType insertNewCompleteCertificateRefs(int var1);

    public CompleteCertificateRefsType addNewCompleteCertificateRefs();

    public void removeCompleteCertificateRefs(int var1);

    public List<CompleteRevocationRefsType> getCompleteRevocationRefsList();

    @Deprecated
    public CompleteRevocationRefsType[] getCompleteRevocationRefsArray();

    public CompleteRevocationRefsType getCompleteRevocationRefsArray(int var1);

    public int sizeOfCompleteRevocationRefsArray();

    public void setCompleteRevocationRefsArray(CompleteRevocationRefsType[] var1);

    public void setCompleteRevocationRefsArray(int var1, CompleteRevocationRefsType var2);

    public CompleteRevocationRefsType insertNewCompleteRevocationRefs(int var1);

    public CompleteRevocationRefsType addNewCompleteRevocationRefs();

    public void removeCompleteRevocationRefs(int var1);

    public List<CompleteCertificateRefsType> getAttributeCertificateRefsList();

    @Deprecated
    public CompleteCertificateRefsType[] getAttributeCertificateRefsArray();

    public CompleteCertificateRefsType getAttributeCertificateRefsArray(int var1);

    public int sizeOfAttributeCertificateRefsArray();

    public void setAttributeCertificateRefsArray(CompleteCertificateRefsType[] var1);

    public void setAttributeCertificateRefsArray(int var1, CompleteCertificateRefsType var2);

    public CompleteCertificateRefsType insertNewAttributeCertificateRefs(int var1);

    public CompleteCertificateRefsType addNewAttributeCertificateRefs();

    public void removeAttributeCertificateRefs(int var1);

    public List<CompleteRevocationRefsType> getAttributeRevocationRefsList();

    @Deprecated
    public CompleteRevocationRefsType[] getAttributeRevocationRefsArray();

    public CompleteRevocationRefsType getAttributeRevocationRefsArray(int var1);

    public int sizeOfAttributeRevocationRefsArray();

    public void setAttributeRevocationRefsArray(CompleteRevocationRefsType[] var1);

    public void setAttributeRevocationRefsArray(int var1, CompleteRevocationRefsType var2);

    public CompleteRevocationRefsType insertNewAttributeRevocationRefs(int var1);

    public CompleteRevocationRefsType addNewAttributeRevocationRefs();

    public void removeAttributeRevocationRefs(int var1);

    public List<XAdESTimeStampType> getSigAndRefsTimeStampList();

    @Deprecated
    public XAdESTimeStampType[] getSigAndRefsTimeStampArray();

    public XAdESTimeStampType getSigAndRefsTimeStampArray(int var1);

    public int sizeOfSigAndRefsTimeStampArray();

    public void setSigAndRefsTimeStampArray(XAdESTimeStampType[] var1);

    public void setSigAndRefsTimeStampArray(int var1, XAdESTimeStampType var2);

    public XAdESTimeStampType insertNewSigAndRefsTimeStamp(int var1);

    public XAdESTimeStampType addNewSigAndRefsTimeStamp();

    public void removeSigAndRefsTimeStamp(int var1);

    public List<XAdESTimeStampType> getRefsOnlyTimeStampList();

    @Deprecated
    public XAdESTimeStampType[] getRefsOnlyTimeStampArray();

    public XAdESTimeStampType getRefsOnlyTimeStampArray(int var1);

    public int sizeOfRefsOnlyTimeStampArray();

    public void setRefsOnlyTimeStampArray(XAdESTimeStampType[] var1);

    public void setRefsOnlyTimeStampArray(int var1, XAdESTimeStampType var2);

    public XAdESTimeStampType insertNewRefsOnlyTimeStamp(int var1);

    public XAdESTimeStampType addNewRefsOnlyTimeStamp();

    public void removeRefsOnlyTimeStamp(int var1);

    public List<CertificateValuesType> getCertificateValuesList();

    @Deprecated
    public CertificateValuesType[] getCertificateValuesArray();

    public CertificateValuesType getCertificateValuesArray(int var1);

    public int sizeOfCertificateValuesArray();

    public void setCertificateValuesArray(CertificateValuesType[] var1);

    public void setCertificateValuesArray(int var1, CertificateValuesType var2);

    public CertificateValuesType insertNewCertificateValues(int var1);

    public CertificateValuesType addNewCertificateValues();

    public void removeCertificateValues(int var1);

    public List<RevocationValuesType> getRevocationValuesList();

    @Deprecated
    public RevocationValuesType[] getRevocationValuesArray();

    public RevocationValuesType getRevocationValuesArray(int var1);

    public int sizeOfRevocationValuesArray();

    public void setRevocationValuesArray(RevocationValuesType[] var1);

    public void setRevocationValuesArray(int var1, RevocationValuesType var2);

    public RevocationValuesType insertNewRevocationValues(int var1);

    public RevocationValuesType addNewRevocationValues();

    public void removeRevocationValues(int var1);

    public List<CertificateValuesType> getAttrAuthoritiesCertValuesList();

    @Deprecated
    public CertificateValuesType[] getAttrAuthoritiesCertValuesArray();

    public CertificateValuesType getAttrAuthoritiesCertValuesArray(int var1);

    public int sizeOfAttrAuthoritiesCertValuesArray();

    public void setAttrAuthoritiesCertValuesArray(CertificateValuesType[] var1);

    public void setAttrAuthoritiesCertValuesArray(int var1, CertificateValuesType var2);

    public CertificateValuesType insertNewAttrAuthoritiesCertValues(int var1);

    public CertificateValuesType addNewAttrAuthoritiesCertValues();

    public void removeAttrAuthoritiesCertValues(int var1);

    public List<RevocationValuesType> getAttributeRevocationValuesList();

    @Deprecated
    public RevocationValuesType[] getAttributeRevocationValuesArray();

    public RevocationValuesType getAttributeRevocationValuesArray(int var1);

    public int sizeOfAttributeRevocationValuesArray();

    public void setAttributeRevocationValuesArray(RevocationValuesType[] var1);

    public void setAttributeRevocationValuesArray(int var1, RevocationValuesType var2);

    public RevocationValuesType insertNewAttributeRevocationValues(int var1);

    public RevocationValuesType addNewAttributeRevocationValues();

    public void removeAttributeRevocationValues(int var1);

    public List<XAdESTimeStampType> getArchiveTimeStampList();

    @Deprecated
    public XAdESTimeStampType[] getArchiveTimeStampArray();

    public XAdESTimeStampType getArchiveTimeStampArray(int var1);

    public int sizeOfArchiveTimeStampArray();

    public void setArchiveTimeStampArray(XAdESTimeStampType[] var1);

    public void setArchiveTimeStampArray(int var1, XAdESTimeStampType var2);

    public XAdESTimeStampType insertNewArchiveTimeStamp(int var1);

    public XAdESTimeStampType addNewArchiveTimeStamp();

    public void removeArchiveTimeStamp(int var1);

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(UnsignedSignaturePropertiesType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static UnsignedSignaturePropertiesType newInstance() {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static UnsignedSignaturePropertiesType newInstance(XmlOptions xmlOptions) {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static UnsignedSignaturePropertiesType parse(String string) throws XmlException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static UnsignedSignaturePropertiesType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static UnsignedSignaturePropertiesType parse(File file) throws XmlException, IOException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static UnsignedSignaturePropertiesType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static UnsignedSignaturePropertiesType parse(URL uRL) throws XmlException, IOException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static UnsignedSignaturePropertiesType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static UnsignedSignaturePropertiesType parse(InputStream inputStream) throws XmlException, IOException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static UnsignedSignaturePropertiesType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static UnsignedSignaturePropertiesType parse(Reader reader) throws XmlException, IOException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static UnsignedSignaturePropertiesType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static UnsignedSignaturePropertiesType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static UnsignedSignaturePropertiesType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static UnsignedSignaturePropertiesType parse(Node node) throws XmlException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static UnsignedSignaturePropertiesType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static UnsignedSignaturePropertiesType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static UnsignedSignaturePropertiesType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (UnsignedSignaturePropertiesType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

