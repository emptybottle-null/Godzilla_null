/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.IncludeType
 *  org.etsi.uri.x01903.v13.ReferenceInfoType
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
import org.etsi.uri.x01903.v13.IncludeType;
import org.etsi.uri.x01903.v13.ReferenceInfoType;
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;
import org.w3c.dom.Node;

public interface GenericTimeStampType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(GenericTimeStampType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("generictimestamptypecdadtype");

    public List<IncludeType> getIncludeList();

    @Deprecated
    public IncludeType[] getIncludeArray();

    public IncludeType getIncludeArray(int var1);

    public int sizeOfIncludeArray();

    public void setIncludeArray(IncludeType[] var1);

    public void setIncludeArray(int var1, IncludeType var2);

    public IncludeType insertNewInclude(int var1);

    public IncludeType addNewInclude();

    public void removeInclude(int var1);

    public List<ReferenceInfoType> getReferenceInfoList();

    @Deprecated
    public ReferenceInfoType[] getReferenceInfoArray();

    public ReferenceInfoType getReferenceInfoArray(int var1);

    public int sizeOfReferenceInfoArray();

    public void setReferenceInfoArray(ReferenceInfoType[] var1);

    public void setReferenceInfoArray(int var1, ReferenceInfoType var2);

    public ReferenceInfoType insertNewReferenceInfo(int var1);

    public ReferenceInfoType addNewReferenceInfo();

    public void removeReferenceInfo(int var1);

    public CanonicalizationMethodType getCanonicalizationMethod();

    public boolean isSetCanonicalizationMethod();

    public void setCanonicalizationMethod(CanonicalizationMethodType var1);

    public CanonicalizationMethodType addNewCanonicalizationMethod();

    public void unsetCanonicalizationMethod();

    public List<EncapsulatedPKIDataType> getEncapsulatedTimeStampList();

    @Deprecated
    public EncapsulatedPKIDataType[] getEncapsulatedTimeStampArray();

    public EncapsulatedPKIDataType getEncapsulatedTimeStampArray(int var1);

    public int sizeOfEncapsulatedTimeStampArray();

    public void setEncapsulatedTimeStampArray(EncapsulatedPKIDataType[] var1);

    public void setEncapsulatedTimeStampArray(int var1, EncapsulatedPKIDataType var2);

    public EncapsulatedPKIDataType insertNewEncapsulatedTimeStamp(int var1);

    public EncapsulatedPKIDataType addNewEncapsulatedTimeStamp();

    public void removeEncapsulatedTimeStamp(int var1);

    public List<AnyType> getXMLTimeStampList();

    @Deprecated
    public AnyType[] getXMLTimeStampArray();

    public AnyType getXMLTimeStampArray(int var1);

    public int sizeOfXMLTimeStampArray();

    public void setXMLTimeStampArray(AnyType[] var1);

    public void setXMLTimeStampArray(int var1, AnyType var2);

    public AnyType insertNewXMLTimeStamp(int var1);

    public AnyType addNewXMLTimeStamp();

    public void removeXMLTimeStamp(int var1);

    public String getId();

    public XmlID xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(XmlID var1);

    public void unsetId();

    public static final class Factory {
        @Deprecated
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(GenericTimeStampType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static GenericTimeStampType newInstance() {
            return (GenericTimeStampType)Factory.getTypeLoader().newInstance(type, null);
        }

        @Deprecated
        public static GenericTimeStampType newInstance(XmlOptions xmlOptions) {
            return (GenericTimeStampType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static GenericTimeStampType parse(String string) throws XmlException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static GenericTimeStampType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static GenericTimeStampType parse(File file) throws XmlException, IOException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static GenericTimeStampType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static GenericTimeStampType parse(URL uRL) throws XmlException, IOException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static GenericTimeStampType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static GenericTimeStampType parse(InputStream inputStream) throws XmlException, IOException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static GenericTimeStampType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static GenericTimeStampType parse(Reader reader) throws XmlException, IOException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static GenericTimeStampType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static GenericTimeStampType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static GenericTimeStampType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static GenericTimeStampType parse(Node node) throws XmlException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static GenericTimeStampType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static GenericTimeStampType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static GenericTimeStampType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (GenericTimeStampType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

