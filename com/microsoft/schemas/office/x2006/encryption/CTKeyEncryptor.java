/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption;

import com.microsoft.schemas.office.x2006.keyEncryptor.certificate.CTCertificateKeyEncryptor;
import com.microsoft.schemas.office.x2006.keyEncryptor.password.CTPasswordKeyEncryptor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTKeyEncryptor
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTKeyEncryptor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ctkeyencryptor1205type");

    public CTPasswordKeyEncryptor getEncryptedPasswordKey();

    public boolean isSetEncryptedPasswordKey();

    public void setEncryptedPasswordKey(CTPasswordKeyEncryptor var1);

    public CTPasswordKeyEncryptor addNewEncryptedPasswordKey();

    public void unsetEncryptedPasswordKey();

    public CTCertificateKeyEncryptor getEncryptedCertificateKey();

    public boolean isSetEncryptedCertificateKey();

    public void setEncryptedCertificateKey(CTCertificateKeyEncryptor var1);

    public CTCertificateKeyEncryptor addNewEncryptedCertificateKey();

    public void unsetEncryptedCertificateKey();

    public Uri.Enum getUri();

    public Uri xgetUri();

    public boolean isSetUri();

    public void setUri(Uri.Enum var1);

    public void xsetUri(Uri var1);

    public void unsetUri();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTKeyEncryptor.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTKeyEncryptor newInstance() {
            return (CTKeyEncryptor)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTKeyEncryptor newInstance(XmlOptions xmlOptions) {
            return (CTKeyEncryptor)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTKeyEncryptor parse(String string) throws XmlException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTKeyEncryptor parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTKeyEncryptor parse(File file) throws XmlException, IOException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTKeyEncryptor parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTKeyEncryptor parse(URL uRL) throws XmlException, IOException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTKeyEncryptor parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTKeyEncryptor parse(InputStream inputStream) throws XmlException, IOException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTKeyEncryptor parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTKeyEncryptor parse(Reader reader) throws XmlException, IOException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTKeyEncryptor parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTKeyEncryptor parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTKeyEncryptor parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTKeyEncryptor parse(Node node) throws XmlException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTKeyEncryptor parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTKeyEncryptor parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTKeyEncryptor parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTKeyEncryptor)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

    public static interface Uri
    extends XmlToken {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Uri.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("uribad9attrtype");
        public static final Enum HTTP_SCHEMAS_MICROSOFT_COM_OFFICE_2006_KEY_ENCRYPTOR_PASSWORD = Enum.forString("http://schemas.microsoft.com/office/2006/keyEncryptor/password");
        public static final Enum HTTP_SCHEMAS_MICROSOFT_COM_OFFICE_2006_KEY_ENCRYPTOR_CERTIFICATE = Enum.forString("http://schemas.microsoft.com/office/2006/keyEncryptor/certificate");
        public static final int INT_HTTP_SCHEMAS_MICROSOFT_COM_OFFICE_2006_KEY_ENCRYPTOR_PASSWORD = 1;
        public static final int INT_HTTP_SCHEMAS_MICROSOFT_COM_OFFICE_2006_KEY_ENCRYPTOR_CERTIFICATE = 2;

        public StringEnumAbstractBase enumValue();

        public void set(StringEnumAbstractBase var1);

        public static final class Factory {
            private static SoftReference<SchemaTypeLoader> typeLoader;

            public static Uri newValue(Object object) {
                return (Uri)type.newValue(object);
            }

            private static synchronized SchemaTypeLoader getTypeLoader() {
                SchemaTypeLoader schemaTypeLoader;
                SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
                if (schemaTypeLoader == null) {
                    schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(Uri.class.getClassLoader());
                    typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
                }
                return schemaTypeLoader;
            }

            public static Uri newInstance() {
                return (Uri)Factory.getTypeLoader().newInstance(type, null);
            }

            public static Uri newInstance(XmlOptions xmlOptions) {
                return (Uri)Factory.getTypeLoader().newInstance(type, xmlOptions);
            }

            private Factory() {
            }
        }

        public static final class Enum
        extends StringEnumAbstractBase {
            static final int INT_HTTP_SCHEMAS_MICROSOFT_COM_OFFICE_2006_KEY_ENCRYPTOR_PASSWORD = 1;
            static final int INT_HTTP_SCHEMAS_MICROSOFT_COM_OFFICE_2006_KEY_ENCRYPTOR_CERTIFICATE = 2;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("http://schemas.microsoft.com/office/2006/keyEncryptor/password", 1), new Enum("http://schemas.microsoft.com/office/2006/keyEncryptor/certificate", 2)});
            private static final long serialVersionUID = 1L;

            public static Enum forString(String string) {
                return (Enum)table.forString(string);
            }

            public static Enum forInt(int n) {
                return (Enum)table.forInt(n);
            }

            private Enum(String string, int n) {
                super(string, n);
            }

            private Object readResolve() {
                return Enum.forInt(this.intValue());
            }
        }
    }
}

