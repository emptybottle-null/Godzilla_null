/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.OtherCertStatusValuesType
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
import org.etsi.uri.x01903.v13.CRLValuesType;
import org.etsi.uri.x01903.v13.OCSPValuesType;
import org.etsi.uri.x01903.v13.OtherCertStatusValuesType;
import org.w3c.dom.Node;

public interface RevocationValuesType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(RevocationValuesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("revocationvaluestype9a6etype");

    public CRLValuesType getCRLValues();

    public boolean isSetCRLValues();

    public void setCRLValues(CRLValuesType var1);

    public CRLValuesType addNewCRLValues();

    public void unsetCRLValues();

    public OCSPValuesType getOCSPValues();

    public boolean isSetOCSPValues();

    public void setOCSPValues(OCSPValuesType var1);

    public OCSPValuesType addNewOCSPValues();

    public void unsetOCSPValues();

    public OtherCertStatusValuesType getOtherValues();

    public boolean isSetOtherValues();

    public void setOtherValues(OtherCertStatusValuesType var1);

    public OtherCertStatusValuesType addNewOtherValues();

    public void unsetOtherValues();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(RevocationValuesType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static RevocationValuesType newInstance() {
            return (RevocationValuesType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static RevocationValuesType newInstance(XmlOptions xmlOptions) {
            return (RevocationValuesType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static RevocationValuesType parse(String string) throws XmlException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static RevocationValuesType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static RevocationValuesType parse(File file) throws XmlException, IOException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static RevocationValuesType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static RevocationValuesType parse(URL uRL) throws XmlException, IOException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static RevocationValuesType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static RevocationValuesType parse(InputStream inputStream) throws XmlException, IOException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static RevocationValuesType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static RevocationValuesType parse(Reader reader) throws XmlException, IOException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static RevocationValuesType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static RevocationValuesType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static RevocationValuesType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static RevocationValuesType parse(Node node) throws XmlException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static RevocationValuesType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static RevocationValuesType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static RevocationValuesType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (RevocationValuesType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

