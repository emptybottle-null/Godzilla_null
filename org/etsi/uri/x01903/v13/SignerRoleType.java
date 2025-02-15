/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.CertifiedRolesListType
 */
package org.etsi.uri.x01903.v13;

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
import org.etsi.uri.x01903.v13.CertifiedRolesListType;
import org.etsi.uri.x01903.v13.ClaimedRolesListType;
import org.w3c.dom.Node;

public interface SignerRoleType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SignerRoleType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("signerroletypef58etype");

    public ClaimedRolesListType getClaimedRoles();

    public boolean isSetClaimedRoles();

    public void setClaimedRoles(ClaimedRolesListType var1);

    public ClaimedRolesListType addNewClaimedRoles();

    public void unsetClaimedRoles();

    public CertifiedRolesListType getCertifiedRoles();

    public boolean isSetCertifiedRoles();

    public void setCertifiedRoles(CertifiedRolesListType var1);

    public CertifiedRolesListType addNewCertifiedRoles();

    public void unsetCertifiedRoles();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(SignerRoleType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static SignerRoleType newInstance() {
            return (SignerRoleType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static SignerRoleType newInstance(XmlOptions xmlOptions) {
            return (SignerRoleType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static SignerRoleType parse(String string) throws XmlException {
            return (SignerRoleType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static SignerRoleType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (SignerRoleType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static SignerRoleType parse(File file) throws XmlException, IOException {
            return (SignerRoleType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static SignerRoleType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignerRoleType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static SignerRoleType parse(URL uRL) throws XmlException, IOException {
            return (SignerRoleType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static SignerRoleType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignerRoleType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static SignerRoleType parse(InputStream inputStream) throws XmlException, IOException {
            return (SignerRoleType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static SignerRoleType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignerRoleType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static SignerRoleType parse(Reader reader) throws XmlException, IOException {
            return (SignerRoleType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static SignerRoleType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignerRoleType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static SignerRoleType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (SignerRoleType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static SignerRoleType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (SignerRoleType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static SignerRoleType parse(Node node) throws XmlException {
            return (SignerRoleType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static SignerRoleType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (SignerRoleType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static SignerRoleType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (SignerRoleType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static SignerRoleType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (SignerRoleType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

