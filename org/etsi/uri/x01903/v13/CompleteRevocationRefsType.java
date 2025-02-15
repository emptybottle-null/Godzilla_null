/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.OtherCertStatusRefsType
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
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.etsi.uri.x01903.v13.CRLRefsType;
import org.etsi.uri.x01903.v13.OCSPRefsType;
import org.etsi.uri.x01903.v13.OtherCertStatusRefsType;
import org.w3c.dom.Node;

public interface CompleteRevocationRefsType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CompleteRevocationRefsType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("completerevocationrefstyped8a5type");

    public CRLRefsType getCRLRefs();

    public boolean isSetCRLRefs();

    public void setCRLRefs(CRLRefsType var1);

    public CRLRefsType addNewCRLRefs();

    public void unsetCRLRefs();

    public OCSPRefsType getOCSPRefs();

    public boolean isSetOCSPRefs();

    public void setOCSPRefs(OCSPRefsType var1);

    public OCSPRefsType addNewOCSPRefs();

    public void unsetOCSPRefs();

    public OtherCertStatusRefsType getOtherRefs();

    public boolean isSetOtherRefs();

    public void setOtherRefs(OtherCertStatusRefsType var1);

    public OtherCertStatusRefsType addNewOtherRefs();

    public void unsetOtherRefs();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CompleteRevocationRefsType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CompleteRevocationRefsType newInstance() {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CompleteRevocationRefsType newInstance(XmlOptions xmlOptions) {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CompleteRevocationRefsType parse(String string) throws XmlException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CompleteRevocationRefsType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CompleteRevocationRefsType parse(File file) throws XmlException, IOException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CompleteRevocationRefsType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CompleteRevocationRefsType parse(URL uRL) throws XmlException, IOException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CompleteRevocationRefsType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CompleteRevocationRefsType parse(InputStream inputStream) throws XmlException, IOException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CompleteRevocationRefsType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CompleteRevocationRefsType parse(Reader reader) throws XmlException, IOException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CompleteRevocationRefsType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CompleteRevocationRefsType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CompleteRevocationRefsType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CompleteRevocationRefsType parse(Node node) throws XmlException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CompleteRevocationRefsType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CompleteRevocationRefsType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CompleteRevocationRefsType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CompleteRevocationRefsType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

