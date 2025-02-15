/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.SignatureProductionPlaceType
 */
package org.etsi.uri.x01903.v13;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Calendar;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType;
import org.etsi.uri.x01903.v13.SignatureProductionPlaceType;
import org.etsi.uri.x01903.v13.SignerRoleType;
import org.w3c.dom.Node;

public interface SignedSignaturePropertiesType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SignedSignaturePropertiesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("signedsignaturepropertiestype06abtype");

    public Calendar getSigningTime();

    public XmlDateTime xgetSigningTime();

    public boolean isSetSigningTime();

    public void setSigningTime(Calendar var1);

    public void xsetSigningTime(XmlDateTime var1);

    public void unsetSigningTime();

    public CertIDListType getSigningCertificate();

    public boolean isSetSigningCertificate();

    public void setSigningCertificate(CertIDListType var1);

    public CertIDListType addNewSigningCertificate();

    public void unsetSigningCertificate();

    public SignaturePolicyIdentifierType getSignaturePolicyIdentifier();

    public boolean isSetSignaturePolicyIdentifier();

    public void setSignaturePolicyIdentifier(SignaturePolicyIdentifierType var1);

    public SignaturePolicyIdentifierType addNewSignaturePolicyIdentifier();

    public void unsetSignaturePolicyIdentifier();

    public SignatureProductionPlaceType getSignatureProductionPlace();

    public boolean isSetSignatureProductionPlace();

    public void setSignatureProductionPlace(SignatureProductionPlaceType var1);

    public SignatureProductionPlaceType addNewSignatureProductionPlace();

    public void unsetSignatureProductionPlace();

    public SignerRoleType getSignerRole();

    public boolean isSetSignerRole();

    public void setSignerRole(SignerRoleType var1);

    public SignerRoleType addNewSignerRole();

    public void unsetSignerRole();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(SignedSignaturePropertiesType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static SignedSignaturePropertiesType newInstance() {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static SignedSignaturePropertiesType newInstance(XmlOptions xmlOptions) {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static SignedSignaturePropertiesType parse(String string) throws XmlException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static SignedSignaturePropertiesType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static SignedSignaturePropertiesType parse(File file) throws XmlException, IOException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static SignedSignaturePropertiesType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static SignedSignaturePropertiesType parse(URL uRL) throws XmlException, IOException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static SignedSignaturePropertiesType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static SignedSignaturePropertiesType parse(InputStream inputStream) throws XmlException, IOException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static SignedSignaturePropertiesType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static SignedSignaturePropertiesType parse(Reader reader) throws XmlException, IOException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static SignedSignaturePropertiesType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static SignedSignaturePropertiesType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static SignedSignaturePropertiesType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static SignedSignaturePropertiesType parse(Node node) throws XmlException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static SignedSignaturePropertiesType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static SignedSignaturePropertiesType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static SignedSignaturePropertiesType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (SignedSignaturePropertiesType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

