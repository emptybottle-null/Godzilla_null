/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTArray
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTCf
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTEmpty
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTNull
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVstream
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STCy
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STError
 */
package org.openxmlformats.schemas.officeDocument.x2006.customProperties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Calendar;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlShort;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.XmlUnsignedLong;
import org.apache.xmlbeans.XmlUnsignedShort;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTArray;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTCf;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTEmpty;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTNull;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVstream;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STClsid;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STCy;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STError;
import org.w3c.dom.Node;

public interface CTProperty
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTProperty.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctproperty5ffatype");

    public CTVector getVector();

    public boolean isSetVector();

    public void setVector(CTVector var1);

    public CTVector addNewVector();

    public void unsetVector();

    public CTArray getArray();

    public boolean isSetArray();

    public void setArray(CTArray var1);

    public CTArray addNewArray();

    public void unsetArray();

    public byte[] getBlob();

    public XmlBase64Binary xgetBlob();

    public boolean isSetBlob();

    public void setBlob(byte[] var1);

    public void xsetBlob(XmlBase64Binary var1);

    public void unsetBlob();

    public byte[] getOblob();

    public XmlBase64Binary xgetOblob();

    public boolean isSetOblob();

    public void setOblob(byte[] var1);

    public void xsetOblob(XmlBase64Binary var1);

    public void unsetOblob();

    public CTEmpty getEmpty();

    public boolean isSetEmpty();

    public void setEmpty(CTEmpty var1);

    public CTEmpty addNewEmpty();

    public void unsetEmpty();

    public CTNull getNull();

    public boolean isSetNull();

    public void setNull(CTNull var1);

    public CTNull addNewNull();

    public void unsetNull();

    public byte getI1();

    public XmlByte xgetI1();

    public boolean isSetI1();

    public void setI1(byte var1);

    public void xsetI1(XmlByte var1);

    public void unsetI1();

    public short getI2();

    public XmlShort xgetI2();

    public boolean isSetI2();

    public void setI2(short var1);

    public void xsetI2(XmlShort var1);

    public void unsetI2();

    public int getI4();

    public XmlInt xgetI4();

    public boolean isSetI4();

    public void setI4(int var1);

    public void xsetI4(XmlInt var1);

    public void unsetI4();

    public long getI8();

    public XmlLong xgetI8();

    public boolean isSetI8();

    public void setI8(long var1);

    public void xsetI8(XmlLong var1);

    public void unsetI8();

    public int getInt();

    public XmlInt xgetInt();

    public boolean isSetInt();

    public void setInt(int var1);

    public void xsetInt(XmlInt var1);

    public void unsetInt();

    public short getUi1();

    public XmlUnsignedByte xgetUi1();

    public boolean isSetUi1();

    public void setUi1(short var1);

    public void xsetUi1(XmlUnsignedByte var1);

    public void unsetUi1();

    public int getUi2();

    public XmlUnsignedShort xgetUi2();

    public boolean isSetUi2();

    public void setUi2(int var1);

    public void xsetUi2(XmlUnsignedShort var1);

    public void unsetUi2();

    public long getUi4();

    public XmlUnsignedInt xgetUi4();

    public boolean isSetUi4();

    public void setUi4(long var1);

    public void xsetUi4(XmlUnsignedInt var1);

    public void unsetUi4();

    public BigInteger getUi8();

    public XmlUnsignedLong xgetUi8();

    public boolean isSetUi8();

    public void setUi8(BigInteger var1);

    public void xsetUi8(XmlUnsignedLong var1);

    public void unsetUi8();

    public long getUint();

    public XmlUnsignedInt xgetUint();

    public boolean isSetUint();

    public void setUint(long var1);

    public void xsetUint(XmlUnsignedInt var1);

    public void unsetUint();

    public float getR4();

    public XmlFloat xgetR4();

    public boolean isSetR4();

    public void setR4(float var1);

    public void xsetR4(XmlFloat var1);

    public void unsetR4();

    public double getR8();

    public XmlDouble xgetR8();

    public boolean isSetR8();

    public void setR8(double var1);

    public void xsetR8(XmlDouble var1);

    public void unsetR8();

    public BigDecimal getDecimal();

    public XmlDecimal xgetDecimal();

    public boolean isSetDecimal();

    public void setDecimal(BigDecimal var1);

    public void xsetDecimal(XmlDecimal var1);

    public void unsetDecimal();

    public String getLpstr();

    public XmlString xgetLpstr();

    public boolean isSetLpstr();

    public void setLpstr(String var1);

    public void xsetLpstr(XmlString var1);

    public void unsetLpstr();

    public String getLpwstr();

    public XmlString xgetLpwstr();

    public boolean isSetLpwstr();

    public void setLpwstr(String var1);

    public void xsetLpwstr(XmlString var1);

    public void unsetLpwstr();

    public String getBstr();

    public XmlString xgetBstr();

    public boolean isSetBstr();

    public void setBstr(String var1);

    public void xsetBstr(XmlString var1);

    public void unsetBstr();

    public Calendar getDate();

    public XmlDateTime xgetDate();

    public boolean isSetDate();

    public void setDate(Calendar var1);

    public void xsetDate(XmlDateTime var1);

    public void unsetDate();

    public Calendar getFiletime();

    public XmlDateTime xgetFiletime();

    public boolean isSetFiletime();

    public void setFiletime(Calendar var1);

    public void xsetFiletime(XmlDateTime var1);

    public void unsetFiletime();

    public boolean getBool();

    public XmlBoolean xgetBool();

    public boolean isSetBool();

    public void setBool(boolean var1);

    public void xsetBool(XmlBoolean var1);

    public void unsetBool();

    public String getCy();

    public STCy xgetCy();

    public boolean isSetCy();

    public void setCy(String var1);

    public void xsetCy(STCy var1);

    public void unsetCy();

    public String getError();

    public STError xgetError();

    public boolean isSetError();

    public void setError(String var1);

    public void xsetError(STError var1);

    public void unsetError();

    public byte[] getStream();

    public XmlBase64Binary xgetStream();

    public boolean isSetStream();

    public void setStream(byte[] var1);

    public void xsetStream(XmlBase64Binary var1);

    public void unsetStream();

    public byte[] getOstream();

    public XmlBase64Binary xgetOstream();

    public boolean isSetOstream();

    public void setOstream(byte[] var1);

    public void xsetOstream(XmlBase64Binary var1);

    public void unsetOstream();

    public byte[] getStorage();

    public XmlBase64Binary xgetStorage();

    public boolean isSetStorage();

    public void setStorage(byte[] var1);

    public void xsetStorage(XmlBase64Binary var1);

    public void unsetStorage();

    public byte[] getOstorage();

    public XmlBase64Binary xgetOstorage();

    public boolean isSetOstorage();

    public void setOstorage(byte[] var1);

    public void xsetOstorage(XmlBase64Binary var1);

    public void unsetOstorage();

    public CTVstream getVstream();

    public boolean isSetVstream();

    public void setVstream(CTVstream var1);

    public CTVstream addNewVstream();

    public void unsetVstream();

    public String getClsid();

    public STClsid xgetClsid();

    public boolean isSetClsid();

    public void setClsid(String var1);

    public void xsetClsid(STClsid var1);

    public void unsetClsid();

    public CTCf getCf();

    public boolean isSetCf();

    public void setCf(CTCf var1);

    public CTCf addNewCf();

    public void unsetCf();

    public String getFmtid();

    public STClsid xgetFmtid();

    public void setFmtid(String var1);

    public void xsetFmtid(STClsid var1);

    public int getPid();

    public XmlInt xgetPid();

    public void setPid(int var1);

    public void xsetPid(XmlInt var1);

    public String getName();

    public XmlString xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public void unsetName();

    public String getLinkTarget();

    public XmlString xgetLinkTarget();

    public boolean isSetLinkTarget();

    public void setLinkTarget(String var1);

    public void xsetLinkTarget(XmlString var1);

    public void unsetLinkTarget();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTProperty.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTProperty newInstance() {
            return (CTProperty)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTProperty newInstance(XmlOptions xmlOptions) {
            return (CTProperty)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTProperty parse(String string) throws XmlException {
            return (CTProperty)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTProperty parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTProperty)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTProperty parse(File file) throws XmlException, IOException {
            return (CTProperty)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTProperty parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTProperty)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTProperty parse(URL uRL) throws XmlException, IOException {
            return (CTProperty)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTProperty parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTProperty)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTProperty parse(InputStream inputStream) throws XmlException, IOException {
            return (CTProperty)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTProperty parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTProperty)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTProperty parse(Reader reader) throws XmlException, IOException {
            return (CTProperty)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTProperty parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTProperty)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTProperty parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTProperty)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTProperty parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTProperty)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTProperty parse(Node node) throws XmlException {
            return (CTProperty)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTProperty parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTProperty)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTProperty parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTProperty)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTProperty parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTProperty)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

