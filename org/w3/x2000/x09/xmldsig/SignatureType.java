/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.w3.x2000.x09.xmldsig.KeyInfoType
 *  org.w3.x2000.x09.xmldsig.SignatureValueType
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
import org.w3.x2000.x09.xmldsig.KeyInfoType;
import org.w3.x2000.x09.xmldsig.ObjectType;
import org.w3.x2000.x09.xmldsig.SignatureValueType;
import org.w3.x2000.x09.xmldsig.SignedInfoType;
import org.w3c.dom.Node;

public interface SignatureType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SignatureType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("signaturetype0a3ftype");

    public SignedInfoType getSignedInfo();

    public void setSignedInfo(SignedInfoType var1);

    public SignedInfoType addNewSignedInfo();

    public SignatureValueType getSignatureValue();

    public void setSignatureValue(SignatureValueType var1);

    public SignatureValueType addNewSignatureValue();

    public KeyInfoType getKeyInfo();

    public boolean isSetKeyInfo();

    public void setKeyInfo(KeyInfoType var1);

    public KeyInfoType addNewKeyInfo();

    public void unsetKeyInfo();

    public List<ObjectType> getObjectList();

    @Deprecated
    public ObjectType[] getObjectArray();

    public ObjectType getObjectArray(int var1);

    public int sizeOfObjectArray();

    public void setObjectArray(ObjectType[] var1);

    public void setObjectArray(int var1, ObjectType var2);

    public ObjectType insertNewObject(int var1);

    public ObjectType addNewObject();

    public void removeObject(int var1);

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(SignatureType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static SignatureType newInstance() {
            return (SignatureType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static SignatureType newInstance(XmlOptions xmlOptions) {
            return (SignatureType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static SignatureType parse(String string) throws XmlException {
            return (SignatureType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static SignatureType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (SignatureType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static SignatureType parse(File file) throws XmlException, IOException {
            return (SignatureType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static SignatureType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static SignatureType parse(URL uRL) throws XmlException, IOException {
            return (SignatureType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static SignatureType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static SignatureType parse(InputStream inputStream) throws XmlException, IOException {
            return (SignatureType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static SignatureType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static SignatureType parse(Reader reader) throws XmlException, IOException {
            return (SignatureType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static SignatureType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static SignatureType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (SignatureType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static SignatureType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (SignatureType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static SignatureType parse(Node node) throws XmlException {
            return (SignatureType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static SignatureType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (SignatureType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static SignatureType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (SignatureType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static SignatureType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (SignatureType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

