/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTCf
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STCy
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STError
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STVectorBaseType
 */
package org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigInteger;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlDateTime;
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
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTCf;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVariant;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STClsid;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STCy;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STError;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STVectorBaseType;
import org.w3c.dom.Node;

public interface CTVector
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTVector.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctvectorc3e2type");

    public List<CTVariant> getVariantList();

    @Deprecated
    public CTVariant[] getVariantArray();

    public CTVariant getVariantArray(int var1);

    public int sizeOfVariantArray();

    public void setVariantArray(CTVariant[] var1);

    public void setVariantArray(int var1, CTVariant var2);

    public CTVariant insertNewVariant(int var1);

    public CTVariant addNewVariant();

    public void removeVariant(int var1);

    public List<Byte> getI1List();

    @Deprecated
    public byte[] getI1Array();

    public byte getI1Array(int var1);

    public List<XmlByte> xgetI1List();

    @Deprecated
    public XmlByte[] xgetI1Array();

    public XmlByte xgetI1Array(int var1);

    public int sizeOfI1Array();

    public void setI1Array(byte[] var1);

    public void setI1Array(int var1, byte var2);

    public void xsetI1Array(XmlByte[] var1);

    public void xsetI1Array(int var1, XmlByte var2);

    public void insertI1(int var1, byte var2);

    public void addI1(byte var1);

    public XmlByte insertNewI1(int var1);

    public XmlByte addNewI1();

    public void removeI1(int var1);

    public List<Short> getI2List();

    @Deprecated
    public short[] getI2Array();

    public short getI2Array(int var1);

    public List<XmlShort> xgetI2List();

    @Deprecated
    public XmlShort[] xgetI2Array();

    public XmlShort xgetI2Array(int var1);

    public int sizeOfI2Array();

    public void setI2Array(short[] var1);

    public void setI2Array(int var1, short var2);

    public void xsetI2Array(XmlShort[] var1);

    public void xsetI2Array(int var1, XmlShort var2);

    public void insertI2(int var1, short var2);

    public void addI2(short var1);

    public XmlShort insertNewI2(int var1);

    public XmlShort addNewI2();

    public void removeI2(int var1);

    public List<Integer> getI4List();

    @Deprecated
    public int[] getI4Array();

    public int getI4Array(int var1);

    public List<XmlInt> xgetI4List();

    @Deprecated
    public XmlInt[] xgetI4Array();

    public XmlInt xgetI4Array(int var1);

    public int sizeOfI4Array();

    public void setI4Array(int[] var1);

    public void setI4Array(int var1, int var2);

    public void xsetI4Array(XmlInt[] var1);

    public void xsetI4Array(int var1, XmlInt var2);

    public void insertI4(int var1, int var2);

    public void addI4(int var1);

    public XmlInt insertNewI4(int var1);

    public XmlInt addNewI4();

    public void removeI4(int var1);

    public List<Long> getI8List();

    @Deprecated
    public long[] getI8Array();

    public long getI8Array(int var1);

    public List<XmlLong> xgetI8List();

    @Deprecated
    public XmlLong[] xgetI8Array();

    public XmlLong xgetI8Array(int var1);

    public int sizeOfI8Array();

    public void setI8Array(long[] var1);

    public void setI8Array(int var1, long var2);

    public void xsetI8Array(XmlLong[] var1);

    public void xsetI8Array(int var1, XmlLong var2);

    public void insertI8(int var1, long var2);

    public void addI8(long var1);

    public XmlLong insertNewI8(int var1);

    public XmlLong addNewI8();

    public void removeI8(int var1);

    public List<Short> getUi1List();

    @Deprecated
    public short[] getUi1Array();

    public short getUi1Array(int var1);

    public List<XmlUnsignedByte> xgetUi1List();

    @Deprecated
    public XmlUnsignedByte[] xgetUi1Array();

    public XmlUnsignedByte xgetUi1Array(int var1);

    public int sizeOfUi1Array();

    public void setUi1Array(short[] var1);

    public void setUi1Array(int var1, short var2);

    public void xsetUi1Array(XmlUnsignedByte[] var1);

    public void xsetUi1Array(int var1, XmlUnsignedByte var2);

    public void insertUi1(int var1, short var2);

    public void addUi1(short var1);

    public XmlUnsignedByte insertNewUi1(int var1);

    public XmlUnsignedByte addNewUi1();

    public void removeUi1(int var1);

    public List<Integer> getUi2List();

    @Deprecated
    public int[] getUi2Array();

    public int getUi2Array(int var1);

    public List<XmlUnsignedShort> xgetUi2List();

    @Deprecated
    public XmlUnsignedShort[] xgetUi2Array();

    public XmlUnsignedShort xgetUi2Array(int var1);

    public int sizeOfUi2Array();

    public void setUi2Array(int[] var1);

    public void setUi2Array(int var1, int var2);

    public void xsetUi2Array(XmlUnsignedShort[] var1);

    public void xsetUi2Array(int var1, XmlUnsignedShort var2);

    public void insertUi2(int var1, int var2);

    public void addUi2(int var1);

    public XmlUnsignedShort insertNewUi2(int var1);

    public XmlUnsignedShort addNewUi2();

    public void removeUi2(int var1);

    public List<Long> getUi4List();

    @Deprecated
    public long[] getUi4Array();

    public long getUi4Array(int var1);

    public List<XmlUnsignedInt> xgetUi4List();

    @Deprecated
    public XmlUnsignedInt[] xgetUi4Array();

    public XmlUnsignedInt xgetUi4Array(int var1);

    public int sizeOfUi4Array();

    public void setUi4Array(long[] var1);

    public void setUi4Array(int var1, long var2);

    public void xsetUi4Array(XmlUnsignedInt[] var1);

    public void xsetUi4Array(int var1, XmlUnsignedInt var2);

    public void insertUi4(int var1, long var2);

    public void addUi4(long var1);

    public XmlUnsignedInt insertNewUi4(int var1);

    public XmlUnsignedInt addNewUi4();

    public void removeUi4(int var1);

    public List<BigInteger> getUi8List();

    @Deprecated
    public BigInteger[] getUi8Array();

    public BigInteger getUi8Array(int var1);

    public List<XmlUnsignedLong> xgetUi8List();

    @Deprecated
    public XmlUnsignedLong[] xgetUi8Array();

    public XmlUnsignedLong xgetUi8Array(int var1);

    public int sizeOfUi8Array();

    public void setUi8Array(BigInteger[] var1);

    public void setUi8Array(int var1, BigInteger var2);

    public void xsetUi8Array(XmlUnsignedLong[] var1);

    public void xsetUi8Array(int var1, XmlUnsignedLong var2);

    public void insertUi8(int var1, BigInteger var2);

    public void addUi8(BigInteger var1);

    public XmlUnsignedLong insertNewUi8(int var1);

    public XmlUnsignedLong addNewUi8();

    public void removeUi8(int var1);

    public List<Float> getR4List();

    @Deprecated
    public float[] getR4Array();

    public float getR4Array(int var1);

    public List<XmlFloat> xgetR4List();

    @Deprecated
    public XmlFloat[] xgetR4Array();

    public XmlFloat xgetR4Array(int var1);

    public int sizeOfR4Array();

    public void setR4Array(float[] var1);

    public void setR4Array(int var1, float var2);

    public void xsetR4Array(XmlFloat[] var1);

    public void xsetR4Array(int var1, XmlFloat var2);

    public void insertR4(int var1, float var2);

    public void addR4(float var1);

    public XmlFloat insertNewR4(int var1);

    public XmlFloat addNewR4();

    public void removeR4(int var1);

    public List<Double> getR8List();

    @Deprecated
    public double[] getR8Array();

    public double getR8Array(int var1);

    public List<XmlDouble> xgetR8List();

    @Deprecated
    public XmlDouble[] xgetR8Array();

    public XmlDouble xgetR8Array(int var1);

    public int sizeOfR8Array();

    public void setR8Array(double[] var1);

    public void setR8Array(int var1, double var2);

    public void xsetR8Array(XmlDouble[] var1);

    public void xsetR8Array(int var1, XmlDouble var2);

    public void insertR8(int var1, double var2);

    public void addR8(double var1);

    public XmlDouble insertNewR8(int var1);

    public XmlDouble addNewR8();

    public void removeR8(int var1);

    public List<String> getLpstrList();

    @Deprecated
    public String[] getLpstrArray();

    public String getLpstrArray(int var1);

    public List<XmlString> xgetLpstrList();

    @Deprecated
    public XmlString[] xgetLpstrArray();

    public XmlString xgetLpstrArray(int var1);

    public int sizeOfLpstrArray();

    public void setLpstrArray(String[] var1);

    public void setLpstrArray(int var1, String var2);

    public void xsetLpstrArray(XmlString[] var1);

    public void xsetLpstrArray(int var1, XmlString var2);

    public void insertLpstr(int var1, String var2);

    public void addLpstr(String var1);

    public XmlString insertNewLpstr(int var1);

    public XmlString addNewLpstr();

    public void removeLpstr(int var1);

    public List<String> getLpwstrList();

    @Deprecated
    public String[] getLpwstrArray();

    public String getLpwstrArray(int var1);

    public List<XmlString> xgetLpwstrList();

    @Deprecated
    public XmlString[] xgetLpwstrArray();

    public XmlString xgetLpwstrArray(int var1);

    public int sizeOfLpwstrArray();

    public void setLpwstrArray(String[] var1);

    public void setLpwstrArray(int var1, String var2);

    public void xsetLpwstrArray(XmlString[] var1);

    public void xsetLpwstrArray(int var1, XmlString var2);

    public void insertLpwstr(int var1, String var2);

    public void addLpwstr(String var1);

    public XmlString insertNewLpwstr(int var1);

    public XmlString addNewLpwstr();

    public void removeLpwstr(int var1);

    public List<String> getBstrList();

    @Deprecated
    public String[] getBstrArray();

    public String getBstrArray(int var1);

    public List<XmlString> xgetBstrList();

    @Deprecated
    public XmlString[] xgetBstrArray();

    public XmlString xgetBstrArray(int var1);

    public int sizeOfBstrArray();

    public void setBstrArray(String[] var1);

    public void setBstrArray(int var1, String var2);

    public void xsetBstrArray(XmlString[] var1);

    public void xsetBstrArray(int var1, XmlString var2);

    public void insertBstr(int var1, String var2);

    public void addBstr(String var1);

    public XmlString insertNewBstr(int var1);

    public XmlString addNewBstr();

    public void removeBstr(int var1);

    public List<Calendar> getDateList();

    @Deprecated
    public Calendar[] getDateArray();

    public Calendar getDateArray(int var1);

    public List<XmlDateTime> xgetDateList();

    @Deprecated
    public XmlDateTime[] xgetDateArray();

    public XmlDateTime xgetDateArray(int var1);

    public int sizeOfDateArray();

    public void setDateArray(Calendar[] var1);

    public void setDateArray(int var1, Calendar var2);

    public void xsetDateArray(XmlDateTime[] var1);

    public void xsetDateArray(int var1, XmlDateTime var2);

    public void insertDate(int var1, Calendar var2);

    public void addDate(Calendar var1);

    public XmlDateTime insertNewDate(int var1);

    public XmlDateTime addNewDate();

    public void removeDate(int var1);

    public List<Calendar> getFiletimeList();

    @Deprecated
    public Calendar[] getFiletimeArray();

    public Calendar getFiletimeArray(int var1);

    public List<XmlDateTime> xgetFiletimeList();

    @Deprecated
    public XmlDateTime[] xgetFiletimeArray();

    public XmlDateTime xgetFiletimeArray(int var1);

    public int sizeOfFiletimeArray();

    public void setFiletimeArray(Calendar[] var1);

    public void setFiletimeArray(int var1, Calendar var2);

    public void xsetFiletimeArray(XmlDateTime[] var1);

    public void xsetFiletimeArray(int var1, XmlDateTime var2);

    public void insertFiletime(int var1, Calendar var2);

    public void addFiletime(Calendar var1);

    public XmlDateTime insertNewFiletime(int var1);

    public XmlDateTime addNewFiletime();

    public void removeFiletime(int var1);

    public List<Boolean> getBoolList();

    @Deprecated
    public boolean[] getBoolArray();

    public boolean getBoolArray(int var1);

    public List<XmlBoolean> xgetBoolList();

    @Deprecated
    public XmlBoolean[] xgetBoolArray();

    public XmlBoolean xgetBoolArray(int var1);

    public int sizeOfBoolArray();

    public void setBoolArray(boolean[] var1);

    public void setBoolArray(int var1, boolean var2);

    public void xsetBoolArray(XmlBoolean[] var1);

    public void xsetBoolArray(int var1, XmlBoolean var2);

    public void insertBool(int var1, boolean var2);

    public void addBool(boolean var1);

    public XmlBoolean insertNewBool(int var1);

    public XmlBoolean addNewBool();

    public void removeBool(int var1);

    public List<String> getCyList();

    @Deprecated
    public String[] getCyArray();

    public String getCyArray(int var1);

    public List<STCy> xgetCyList();

    @Deprecated
    public STCy[] xgetCyArray();

    public STCy xgetCyArray(int var1);

    public int sizeOfCyArray();

    public void setCyArray(String[] var1);

    public void setCyArray(int var1, String var2);

    public void xsetCyArray(STCy[] var1);

    public void xsetCyArray(int var1, STCy var2);

    public void insertCy(int var1, String var2);

    public void addCy(String var1);

    public STCy insertNewCy(int var1);

    public STCy addNewCy();

    public void removeCy(int var1);

    public List<String> getErrorList();

    @Deprecated
    public String[] getErrorArray();

    public String getErrorArray(int var1);

    public List<STError> xgetErrorList();

    @Deprecated
    public STError[] xgetErrorArray();

    public STError xgetErrorArray(int var1);

    public int sizeOfErrorArray();

    public void setErrorArray(String[] var1);

    public void setErrorArray(int var1, String var2);

    public void xsetErrorArray(STError[] var1);

    public void xsetErrorArray(int var1, STError var2);

    public void insertError(int var1, String var2);

    public void addError(String var1);

    public STError insertNewError(int var1);

    public STError addNewError();

    public void removeError(int var1);

    public List<String> getClsidList();

    @Deprecated
    public String[] getClsidArray();

    public String getClsidArray(int var1);

    public List<STClsid> xgetClsidList();

    @Deprecated
    public STClsid[] xgetClsidArray();

    public STClsid xgetClsidArray(int var1);

    public int sizeOfClsidArray();

    public void setClsidArray(String[] var1);

    public void setClsidArray(int var1, String var2);

    public void xsetClsidArray(STClsid[] var1);

    public void xsetClsidArray(int var1, STClsid var2);

    public void insertClsid(int var1, String var2);

    public void addClsid(String var1);

    public STClsid insertNewClsid(int var1);

    public STClsid addNewClsid();

    public void removeClsid(int var1);

    public List<CTCf> getCfList();

    @Deprecated
    public CTCf[] getCfArray();

    public CTCf getCfArray(int var1);

    public int sizeOfCfArray();

    public void setCfArray(CTCf[] var1);

    public void setCfArray(int var1, CTCf var2);

    public CTCf insertNewCf(int var1);

    public CTCf addNewCf();

    public void removeCf(int var1);

    public STVectorBaseType.Enum getBaseType();

    public STVectorBaseType xgetBaseType();

    public void setBaseType(STVectorBaseType.Enum var1);

    public void xsetBaseType(STVectorBaseType var1);

    public long getSize();

    public XmlUnsignedInt xgetSize();

    public void setSize(long var1);

    public void xsetSize(XmlUnsignedInt var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTVector.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTVector newInstance() {
            return (CTVector)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTVector newInstance(XmlOptions xmlOptions) {
            return (CTVector)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTVector parse(String string) throws XmlException {
            return (CTVector)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTVector parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTVector)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTVector parse(File file) throws XmlException, IOException {
            return (CTVector)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTVector parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTVector)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTVector parse(URL uRL) throws XmlException, IOException {
            return (CTVector)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTVector parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTVector)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTVector parse(InputStream inputStream) throws XmlException, IOException {
            return (CTVector)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTVector parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTVector)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTVector parse(Reader reader) throws XmlException, IOException {
            return (CTVector)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTVector parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTVector)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTVector parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTVector)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTVector parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTVector)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTVector parse(Node node) throws XmlException {
            return (CTVector)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTVector parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTVector)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTVector parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTVector)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTVector parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTVector)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

