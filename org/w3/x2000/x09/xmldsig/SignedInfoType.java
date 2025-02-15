/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.w3.x2000.x09.xmldsig.SignatureMethodType
 */
package org.w3.x2000.x09.xmldsig;

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
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;
import org.w3.x2000.x09.xmldsig.ReferenceType;
import org.w3.x2000.x09.xmldsig.SignatureMethodType;
import org.w3c.dom.Node;

public interface SignedInfoType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SignedInfoType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("signedinfotype54dbtype");

    public CanonicalizationMethodType getCanonicalizationMethod();

    public void setCanonicalizationMethod(CanonicalizationMethodType var1);

    public CanonicalizationMethodType addNewCanonicalizationMethod();

    public SignatureMethodType getSignatureMethod();

    public void setSignatureMethod(SignatureMethodType var1);

    public SignatureMethodType addNewSignatureMethod();

    public List<ReferenceType> getReferenceList();

    @Deprecated
    public ReferenceType[] getReferenceArray();

    public ReferenceType getReferenceArray(int var1);

    public int sizeOfReferenceArray();

    public void setReferenceArray(ReferenceType[] var1);

    public void setReferenceArray(int var1, ReferenceType var2);

    public ReferenceType insertNewReference(int var1);

    public ReferenceType addNewReference();

    public void removeReference(int var1);

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(SignedInfoType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static SignedInfoType newInstance() {
            return (SignedInfoType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static SignedInfoType newInstance(XmlOptions xmlOptions) {
            return (SignedInfoType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static SignedInfoType parse(String string) throws XmlException {
            return (SignedInfoType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static SignedInfoType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (SignedInfoType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static SignedInfoType parse(File file) throws XmlException, IOException {
            return (SignedInfoType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static SignedInfoType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignedInfoType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static SignedInfoType parse(URL uRL) throws XmlException, IOException {
            return (SignedInfoType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static SignedInfoType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignedInfoType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static SignedInfoType parse(InputStream inputStream) throws XmlException, IOException {
            return (SignedInfoType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static SignedInfoType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignedInfoType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static SignedInfoType parse(Reader reader) throws XmlException, IOException {
            return (SignedInfoType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static SignedInfoType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignedInfoType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static SignedInfoType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (SignedInfoType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static SignedInfoType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (SignedInfoType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static SignedInfoType parse(Node node) throws XmlException {
            return (SignedInfoType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static SignedInfoType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (SignedInfoType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static SignedInfoType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (SignedInfoType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static SignedInfoType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (SignedInfoType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

