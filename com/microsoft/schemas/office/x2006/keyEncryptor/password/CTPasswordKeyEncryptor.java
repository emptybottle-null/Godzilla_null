/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.keyEncryptor.password;

import com.microsoft.schemas.office.x2006.encryption.STBlockSize;
import com.microsoft.schemas.office.x2006.encryption.STCipherAlgorithm;
import com.microsoft.schemas.office.x2006.encryption.STCipherChaining;
import com.microsoft.schemas.office.x2006.encryption.STHashAlgorithm;
import com.microsoft.schemas.office.x2006.encryption.STHashSize;
import com.microsoft.schemas.office.x2006.encryption.STKeyBits;
import com.microsoft.schemas.office.x2006.encryption.STSaltSize;
import com.microsoft.schemas.office.x2006.encryption.STSpinCount;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTPasswordKeyEncryptor
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPasswordKeyEncryptor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ctpasswordkeyencryptorde24type");

    public int getSaltSize();

    public STSaltSize xgetSaltSize();

    public void setSaltSize(int var1);

    public void xsetSaltSize(STSaltSize var1);

    public int getBlockSize();

    public STBlockSize xgetBlockSize();

    public void setBlockSize(int var1);

    public void xsetBlockSize(STBlockSize var1);

    public long getKeyBits();

    public STKeyBits xgetKeyBits();

    public void setKeyBits(long var1);

    public void xsetKeyBits(STKeyBits var1);

    public int getHashSize();

    public STHashSize xgetHashSize();

    public void setHashSize(int var1);

    public void xsetHashSize(STHashSize var1);

    public STCipherAlgorithm.Enum getCipherAlgorithm();

    public STCipherAlgorithm xgetCipherAlgorithm();

    public void setCipherAlgorithm(STCipherAlgorithm.Enum var1);

    public void xsetCipherAlgorithm(STCipherAlgorithm var1);

    public STCipherChaining.Enum getCipherChaining();

    public STCipherChaining xgetCipherChaining();

    public void setCipherChaining(STCipherChaining.Enum var1);

    public void xsetCipherChaining(STCipherChaining var1);

    public STHashAlgorithm.Enum getHashAlgorithm();

    public STHashAlgorithm xgetHashAlgorithm();

    public void setHashAlgorithm(STHashAlgorithm.Enum var1);

    public void xsetHashAlgorithm(STHashAlgorithm var1);

    public byte[] getSaltValue();

    public XmlBase64Binary xgetSaltValue();

    public void setSaltValue(byte[] var1);

    public void xsetSaltValue(XmlBase64Binary var1);

    public int getSpinCount();

    public STSpinCount xgetSpinCount();

    public void setSpinCount(int var1);

    public void xsetSpinCount(STSpinCount var1);

    public byte[] getEncryptedVerifierHashInput();

    public XmlBase64Binary xgetEncryptedVerifierHashInput();

    public void setEncryptedVerifierHashInput(byte[] var1);

    public void xsetEncryptedVerifierHashInput(XmlBase64Binary var1);

    public byte[] getEncryptedVerifierHashValue();

    public XmlBase64Binary xgetEncryptedVerifierHashValue();

    public void setEncryptedVerifierHashValue(byte[] var1);

    public void xsetEncryptedVerifierHashValue(XmlBase64Binary var1);

    public byte[] getEncryptedKeyValue();

    public XmlBase64Binary xgetEncryptedKeyValue();

    public void setEncryptedKeyValue(byte[] var1);

    public void xsetEncryptedKeyValue(XmlBase64Binary var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPasswordKeyEncryptor.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPasswordKeyEncryptor newInstance() {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPasswordKeyEncryptor newInstance(XmlOptions xmlOptions) {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPasswordKeyEncryptor parse(String string) throws XmlException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPasswordKeyEncryptor parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPasswordKeyEncryptor parse(File file) throws XmlException, IOException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPasswordKeyEncryptor parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPasswordKeyEncryptor parse(URL uRL) throws XmlException, IOException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPasswordKeyEncryptor parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPasswordKeyEncryptor parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPasswordKeyEncryptor parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPasswordKeyEncryptor parse(Reader reader) throws XmlException, IOException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPasswordKeyEncryptor parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPasswordKeyEncryptor parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPasswordKeyEncryptor parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPasswordKeyEncryptor parse(Node node) throws XmlException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPasswordKeyEncryptor parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPasswordKeyEncryptor parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPasswordKeyEncryptor parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPasswordKeyEncryptor)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

