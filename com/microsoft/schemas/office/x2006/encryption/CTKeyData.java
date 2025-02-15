/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption;

import com.microsoft.schemas.office.x2006.encryption.STBlockSize;
import com.microsoft.schemas.office.x2006.encryption.STCipherAlgorithm;
import com.microsoft.schemas.office.x2006.encryption.STCipherChaining;
import com.microsoft.schemas.office.x2006.encryption.STHashAlgorithm;
import com.microsoft.schemas.office.x2006.encryption.STHashSize;
import com.microsoft.schemas.office.x2006.encryption.STKeyBits;
import com.microsoft.schemas.office.x2006.encryption.STSaltSize;
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

public interface CTKeyData
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTKeyData.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ctkeydata6bdbtype");

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

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTKeyData.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTKeyData newInstance() {
            return (CTKeyData)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTKeyData newInstance(XmlOptions xmlOptions) {
            return (CTKeyData)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTKeyData parse(String string) throws XmlException {
            return (CTKeyData)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTKeyData parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTKeyData)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTKeyData parse(File file) throws XmlException, IOException {
            return (CTKeyData)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTKeyData parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyData)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTKeyData parse(URL uRL) throws XmlException, IOException {
            return (CTKeyData)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTKeyData parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyData)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTKeyData parse(InputStream inputStream) throws XmlException, IOException {
            return (CTKeyData)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTKeyData parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyData)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTKeyData parse(Reader reader) throws XmlException, IOException {
            return (CTKeyData)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTKeyData parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyData)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTKeyData parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTKeyData)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTKeyData parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTKeyData)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTKeyData parse(Node node) throws XmlException {
            return (CTKeyData)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTKeyData parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTKeyData)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTKeyData parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTKeyData)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTKeyData parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTKeyData)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

