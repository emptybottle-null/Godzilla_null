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
import org.etsi.uri.x01903.v13.OCSPRefType;
import org.w3c.dom.Node;

public interface OCSPRefsType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(OCSPRefsType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ocsprefstypef13ftype");

    public List<OCSPRefType> getOCSPRefList();

    @Deprecated
    public OCSPRefType[] getOCSPRefArray();

    public OCSPRefType getOCSPRefArray(int var1);

    public int sizeOfOCSPRefArray();

    public void setOCSPRefArray(OCSPRefType[] var1);

    public void setOCSPRefArray(int var1, OCSPRefType var2);

    public OCSPRefType insertNewOCSPRef(int var1);

    public OCSPRefType addNewOCSPRef();

    public void removeOCSPRef(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(OCSPRefsType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static OCSPRefsType newInstance() {
            return (OCSPRefsType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static OCSPRefsType newInstance(XmlOptions xmlOptions) {
            return (OCSPRefsType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static OCSPRefsType parse(String string) throws XmlException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static OCSPRefsType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static OCSPRefsType parse(File file) throws XmlException, IOException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static OCSPRefsType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static OCSPRefsType parse(URL uRL) throws XmlException, IOException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static OCSPRefsType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static OCSPRefsType parse(InputStream inputStream) throws XmlException, IOException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static OCSPRefsType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static OCSPRefsType parse(Reader reader) throws XmlException, IOException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static OCSPRefsType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static OCSPRefsType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static OCSPRefsType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static OCSPRefsType parse(Node node) throws XmlException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static OCSPRefsType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static OCSPRefsType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static OCSPRefsType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (OCSPRefsType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

