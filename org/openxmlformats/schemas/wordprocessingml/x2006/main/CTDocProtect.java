/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigInteger;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STAlgClass;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STAlgType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STCryptProv;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.w3c.dom.Node;

public interface CTDocProtect
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDocProtect.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdocprotectc611type");

    public STDocProtect.Enum getEdit();

    public STDocProtect xgetEdit();

    public boolean isSetEdit();

    public void setEdit(STDocProtect.Enum var1);

    public void xsetEdit(STDocProtect var1);

    public void unsetEdit();

    public STOnOff.Enum getFormatting();

    public STOnOff xgetFormatting();

    public boolean isSetFormatting();

    public void setFormatting(STOnOff.Enum var1);

    public void xsetFormatting(STOnOff var1);

    public void unsetFormatting();

    public STOnOff.Enum getEnforcement();

    public STOnOff xgetEnforcement();

    public boolean isSetEnforcement();

    public void setEnforcement(STOnOff.Enum var1);

    public void xsetEnforcement(STOnOff var1);

    public void unsetEnforcement();

    public STCryptProv.Enum getCryptProviderType();

    public STCryptProv xgetCryptProviderType();

    public boolean isSetCryptProviderType();

    public void setCryptProviderType(STCryptProv.Enum var1);

    public void xsetCryptProviderType(STCryptProv var1);

    public void unsetCryptProviderType();

    public STAlgClass.Enum getCryptAlgorithmClass();

    public STAlgClass xgetCryptAlgorithmClass();

    public boolean isSetCryptAlgorithmClass();

    public void setCryptAlgorithmClass(STAlgClass.Enum var1);

    public void xsetCryptAlgorithmClass(STAlgClass var1);

    public void unsetCryptAlgorithmClass();

    public STAlgType.Enum getCryptAlgorithmType();

    public STAlgType xgetCryptAlgorithmType();

    public boolean isSetCryptAlgorithmType();

    public void setCryptAlgorithmType(STAlgType.Enum var1);

    public void xsetCryptAlgorithmType(STAlgType var1);

    public void unsetCryptAlgorithmType();

    public BigInteger getCryptAlgorithmSid();

    public STDecimalNumber xgetCryptAlgorithmSid();

    public boolean isSetCryptAlgorithmSid();

    public void setCryptAlgorithmSid(BigInteger var1);

    public void xsetCryptAlgorithmSid(STDecimalNumber var1);

    public void unsetCryptAlgorithmSid();

    public BigInteger getCryptSpinCount();

    public STDecimalNumber xgetCryptSpinCount();

    public boolean isSetCryptSpinCount();

    public void setCryptSpinCount(BigInteger var1);

    public void xsetCryptSpinCount(STDecimalNumber var1);

    public void unsetCryptSpinCount();

    public String getCryptProvider();

    public STString xgetCryptProvider();

    public boolean isSetCryptProvider();

    public void setCryptProvider(String var1);

    public void xsetCryptProvider(STString var1);

    public void unsetCryptProvider();

    public byte[] getAlgIdExt();

    public STLongHexNumber xgetAlgIdExt();

    public boolean isSetAlgIdExt();

    public void setAlgIdExt(byte[] var1);

    public void xsetAlgIdExt(STLongHexNumber var1);

    public void unsetAlgIdExt();

    public String getAlgIdExtSource();

    public STString xgetAlgIdExtSource();

    public boolean isSetAlgIdExtSource();

    public void setAlgIdExtSource(String var1);

    public void xsetAlgIdExtSource(STString var1);

    public void unsetAlgIdExtSource();

    public byte[] getCryptProviderTypeExt();

    public STLongHexNumber xgetCryptProviderTypeExt();

    public boolean isSetCryptProviderTypeExt();

    public void setCryptProviderTypeExt(byte[] var1);

    public void xsetCryptProviderTypeExt(STLongHexNumber var1);

    public void unsetCryptProviderTypeExt();

    public String getCryptProviderTypeExtSource();

    public STString xgetCryptProviderTypeExtSource();

    public boolean isSetCryptProviderTypeExtSource();

    public void setCryptProviderTypeExtSource(String var1);

    public void xsetCryptProviderTypeExtSource(STString var1);

    public void unsetCryptProviderTypeExtSource();

    public byte[] getHash();

    public XmlBase64Binary xgetHash();

    public boolean isSetHash();

    public void setHash(byte[] var1);

    public void xsetHash(XmlBase64Binary var1);

    public void unsetHash();

    public byte[] getSalt();

    public XmlBase64Binary xgetSalt();

    public boolean isSetSalt();

    public void setSalt(byte[] var1);

    public void xsetSalt(XmlBase64Binary var1);

    public void unsetSalt();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDocProtect.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDocProtect newInstance() {
            return (CTDocProtect)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDocProtect newInstance(XmlOptions xmlOptions) {
            return (CTDocProtect)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDocProtect parse(String string) throws XmlException {
            return (CTDocProtect)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDocProtect parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDocProtect)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDocProtect parse(File file) throws XmlException, IOException {
            return (CTDocProtect)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDocProtect parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDocProtect)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDocProtect parse(URL uRL) throws XmlException, IOException {
            return (CTDocProtect)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDocProtect parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDocProtect)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDocProtect parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDocProtect)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDocProtect parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDocProtect)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDocProtect parse(Reader reader) throws XmlException, IOException {
            return (CTDocProtect)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDocProtect parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDocProtect)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDocProtect parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDocProtect)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDocProtect parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDocProtect)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDocProtect parse(Node node) throws XmlException {
            return (CTDocProtect)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDocProtect parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDocProtect)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDocProtect parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDocProtect)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDocProtect parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDocProtect)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

