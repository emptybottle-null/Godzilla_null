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
package org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlShort;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.XmlUnsignedLong;
import org.apache.xmlbeans.XmlUnsignedShort;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTArray;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTCf;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTEmpty;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTNull;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVstream;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STClsid;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STCy;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STError;

public class CTPropertyImpl
extends XmlComplexContentImpl
implements CTProperty {
    private static final long serialVersionUID = 1L;
    private static final QName VECTOR$0 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "vector");
    private static final QName ARRAY$2 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "array");
    private static final QName BLOB$4 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "blob");
    private static final QName OBLOB$6 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "oblob");
    private static final QName EMPTY$8 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "empty");
    private static final QName NULL$10 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "null");
    private static final QName I1$12 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i1");
    private static final QName I2$14 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i2");
    private static final QName I4$16 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i4");
    private static final QName I8$18 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i8");
    private static final QName INT$20 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "int");
    private static final QName UI1$22 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui1");
    private static final QName UI2$24 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui2");
    private static final QName UI4$26 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui4");
    private static final QName UI8$28 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui8");
    private static final QName UINT$30 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "uint");
    private static final QName R4$32 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "r4");
    private static final QName R8$34 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "r8");
    private static final QName DECIMAL$36 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "decimal");
    private static final QName LPSTR$38 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "lpstr");
    private static final QName LPWSTR$40 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "lpwstr");
    private static final QName BSTR$42 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "bstr");
    private static final QName DATE$44 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "date");
    private static final QName FILETIME$46 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "filetime");
    private static final QName BOOL$48 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "bool");
    private static final QName CY$50 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "cy");
    private static final QName ERROR$52 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "error");
    private static final QName STREAM$54 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "stream");
    private static final QName OSTREAM$56 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ostream");
    private static final QName STORAGE$58 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "storage");
    private static final QName OSTORAGE$60 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ostorage");
    private static final QName VSTREAM$62 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "vstream");
    private static final QName CLSID$64 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "clsid");
    private static final QName CF$66 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "cf");
    private static final QName FMTID$68 = new QName("", "fmtid");
    private static final QName PID$70 = new QName("", "pid");
    private static final QName NAME$72 = new QName("", "name");
    private static final QName LINKTARGET$74 = new QName("", "linkTarget");

    public CTPropertyImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVector getVector() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVector cTVector = null;
            cTVector = (CTVector)((Object)this.get_store().find_element_user(VECTOR$0, 0));
            if (cTVector == null) {
                return null;
            }
            return cTVector;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVector() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VECTOR$0) != 0;
        }
    }

    @Override
    public void setVector(CTVector cTVector) {
        this.generatedSetterHelperImpl(cTVector, VECTOR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVector addNewVector() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVector cTVector = null;
            cTVector = (CTVector)((Object)this.get_store().add_element_user(VECTOR$0));
            return cTVector;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVector() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VECTOR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTArray getArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTArray cTArray = null;
            cTArray = (CTArray)this.get_store().find_element_user(ARRAY$2, 0);
            if (cTArray == null) {
                return null;
            }
            return cTArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ARRAY$2) != 0;
        }
    }

    @Override
    public void setArray(CTArray cTArray) {
        this.generatedSetterHelperImpl((XmlObject)cTArray, ARRAY$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTArray addNewArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTArray cTArray = null;
            cTArray = (CTArray)this.get_store().add_element_user(ARRAY$2);
            return cTArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ARRAY$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getBlob() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BLOB$4, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBase64Binary xgetBlob() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_element_user(BLOB$4, 0));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBlob() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLOB$4) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBlob(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BLOB$4, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(BLOB$4));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBlob(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_element_user(BLOB$4, 0));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_element_user(BLOB$4));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBlob() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLOB$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getOblob() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(OBLOB$6, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBase64Binary xgetOblob() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_element_user(OBLOB$6, 0));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOblob() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OBLOB$6) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOblob(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(OBLOB$6, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(OBLOB$6));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOblob(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_element_user(OBLOB$6, 0));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_element_user(OBLOB$6));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOblob() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OBLOB$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getEmpty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)this.get_store().find_element_user(EMPTY$8, 0);
            if (cTEmpty == null) {
                return null;
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEmpty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EMPTY$8) != 0;
        }
    }

    @Override
    public void setEmpty(CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl((XmlObject)cTEmpty, EMPTY$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewEmpty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)this.get_store().add_element_user(EMPTY$8);
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEmpty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EMPTY$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNull getNull() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNull cTNull = null;
            cTNull = (CTNull)this.get_store().find_element_user(NULL$10, 0);
            if (cTNull == null) {
                return null;
            }
            return cTNull;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNull() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NULL$10) != 0;
        }
    }

    @Override
    public void setNull(CTNull cTNull) {
        this.generatedSetterHelperImpl((XmlObject)cTNull, NULL$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNull addNewNull() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNull cTNull = null;
            cTNull = (CTNull)this.get_store().add_element_user(NULL$10);
            return cTNull;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNull() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NULL$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte getI1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I1$12, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getByteValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlByte xgetI1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlByte xmlByte = null;
            xmlByte = (XmlByte)((Object)this.get_store().find_element_user(I1$12, 0));
            return xmlByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetI1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(I1$12) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI1(byte by) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I1$12, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(I1$12));
            }
            simpleValue.setByteValue(by);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI1(XmlByte xmlByte) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlByte xmlByte2 = null;
            xmlByte2 = (XmlByte)((Object)this.get_store().find_element_user(I1$12, 0));
            if (xmlByte2 == null) {
                xmlByte2 = (XmlByte)((Object)this.get_store().add_element_user(I1$12));
            }
            xmlByte2.set(xmlByte);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetI1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(I1$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short getI2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I2$14, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getShortValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlShort xgetI2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlShort xmlShort = null;
            xmlShort = (XmlShort)((Object)this.get_store().find_element_user(I2$14, 0));
            return xmlShort;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetI2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(I2$14) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI2(short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I2$14, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(I2$14));
            }
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI2(XmlShort xmlShort) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlShort xmlShort2 = null;
            xmlShort2 = (XmlShort)((Object)this.get_store().find_element_user(I2$14, 0));
            if (xmlShort2 == null) {
                xmlShort2 = (XmlShort)((Object)this.get_store().add_element_user(I2$14));
            }
            xmlShort2.set(xmlShort);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetI2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(I2$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getI4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I4$16, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInt xgetI4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt = null;
            xmlInt = (XmlInt)((Object)this.get_store().find_element_user(I4$16, 0));
            return xmlInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetI4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(I4$16) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI4(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I4$16, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(I4$16));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI4(XmlInt xmlInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt2 = null;
            xmlInt2 = (XmlInt)((Object)this.get_store().find_element_user(I4$16, 0));
            if (xmlInt2 == null) {
                xmlInt2 = (XmlInt)((Object)this.get_store().add_element_user(I4$16));
            }
            xmlInt2.set(xmlInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetI4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(I4$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getI8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I8$18, 0));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlLong xgetI8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlLong xmlLong = null;
            xmlLong = (XmlLong)((Object)this.get_store().find_element_user(I8$18, 0));
            return xmlLong;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetI8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(I8$18) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI8(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I8$18, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(I8$18));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI8(XmlLong xmlLong) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlLong xmlLong2 = null;
            xmlLong2 = (XmlLong)((Object)this.get_store().find_element_user(I8$18, 0));
            if (xmlLong2 == null) {
                xmlLong2 = (XmlLong)((Object)this.get_store().add_element_user(I8$18));
            }
            xmlLong2.set(xmlLong);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetI8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(I8$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getInt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(INT$20, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInt xgetInt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt = null;
            xmlInt = (XmlInt)((Object)this.get_store().find_element_user(INT$20, 0));
            return xmlInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetInt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INT$20) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setInt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(INT$20, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(INT$20));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetInt(XmlInt xmlInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt2 = null;
            xmlInt2 = (XmlInt)((Object)this.get_store().find_element_user(INT$20, 0));
            if (xmlInt2 == null) {
                xmlInt2 = (XmlInt)((Object)this.get_store().add_element_user(INT$20));
            }
            xmlInt2.set(xmlInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetInt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INT$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short getUi1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI1$22, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getShortValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedByte xgetUi1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte = null;
            xmlUnsignedByte = (XmlUnsignedByte)((Object)this.get_store().find_element_user(UI1$22, 0));
            return xmlUnsignedByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUi1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UI1$22) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi1(short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI1$22, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(UI1$22));
            }
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi1(XmlUnsignedByte xmlUnsignedByte) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte2 = null;
            xmlUnsignedByte2 = (XmlUnsignedByte)((Object)this.get_store().find_element_user(UI1$22, 0));
            if (xmlUnsignedByte2 == null) {
                xmlUnsignedByte2 = (XmlUnsignedByte)((Object)this.get_store().add_element_user(UI1$22));
            }
            xmlUnsignedByte2.set(xmlUnsignedByte);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUi1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UI1$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getUi2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI2$24, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedShort xgetUi2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedShort xmlUnsignedShort = null;
            xmlUnsignedShort = (XmlUnsignedShort)((Object)this.get_store().find_element_user(UI2$24, 0));
            return xmlUnsignedShort;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUi2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UI2$24) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI2$24, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(UI2$24));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi2(XmlUnsignedShort xmlUnsignedShort) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedShort xmlUnsignedShort2 = null;
            xmlUnsignedShort2 = (XmlUnsignedShort)((Object)this.get_store().find_element_user(UI2$24, 0));
            if (xmlUnsignedShort2 == null) {
                xmlUnsignedShort2 = (XmlUnsignedShort)((Object)this.get_store().add_element_user(UI2$24));
            }
            xmlUnsignedShort2.set(xmlUnsignedShort);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUi2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UI2$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getUi4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI4$26, 0));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetUi4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_element_user(UI4$26, 0));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUi4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UI4$26) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi4(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI4$26, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(UI4$26));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi4(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_element_user(UI4$26, 0));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_element_user(UI4$26));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUi4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UI4$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getUi8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI8$28, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedLong xgetUi8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedLong xmlUnsignedLong = null;
            xmlUnsignedLong = (XmlUnsignedLong)((Object)this.get_store().find_element_user(UI8$28, 0));
            return xmlUnsignedLong;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUi8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UI8$28) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi8(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI8$28, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(UI8$28));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi8(XmlUnsignedLong xmlUnsignedLong) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedLong xmlUnsignedLong2 = null;
            xmlUnsignedLong2 = (XmlUnsignedLong)((Object)this.get_store().find_element_user(UI8$28, 0));
            if (xmlUnsignedLong2 == null) {
                xmlUnsignedLong2 = (XmlUnsignedLong)((Object)this.get_store().add_element_user(UI8$28));
            }
            xmlUnsignedLong2.set(xmlUnsignedLong);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUi8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UI8$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getUint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UINT$30, 0));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetUint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_element_user(UINT$30, 0));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UINT$30) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUint(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UINT$30, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(UINT$30));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUint(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_element_user(UINT$30, 0));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_element_user(UINT$30));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UINT$30, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public float getR4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(R4$32, 0));
            if (simpleValue == null) {
                return 0.0f;
            }
            return simpleValue.getFloatValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlFloat xgetR4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlFloat xmlFloat = null;
            xmlFloat = (XmlFloat)((Object)this.get_store().find_element_user(R4$32, 0));
            return xmlFloat;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetR4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(R4$32) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setR4(float f) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(R4$32, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(R4$32));
            }
            simpleValue.setFloatValue(f);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetR4(XmlFloat xmlFloat) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlFloat xmlFloat2 = null;
            xmlFloat2 = (XmlFloat)((Object)this.get_store().find_element_user(R4$32, 0));
            if (xmlFloat2 == null) {
                xmlFloat2 = (XmlFloat)((Object)this.get_store().add_element_user(R4$32));
            }
            xmlFloat2.set(xmlFloat);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetR4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(R4$32, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getR8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(R8$34, 0));
            if (simpleValue == null) {
                return 0.0;
            }
            return simpleValue.getDoubleValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDouble xgetR8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDouble xmlDouble = null;
            xmlDouble = (XmlDouble)((Object)this.get_store().find_element_user(R8$34, 0));
            return xmlDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetR8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(R8$34) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setR8(double d) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(R8$34, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(R8$34));
            }
            simpleValue.setDoubleValue(d);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetR8(XmlDouble xmlDouble) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDouble xmlDouble2 = null;
            xmlDouble2 = (XmlDouble)((Object)this.get_store().find_element_user(R8$34, 0));
            if (xmlDouble2 == null) {
                xmlDouble2 = (XmlDouble)((Object)this.get_store().add_element_user(R8$34));
            }
            xmlDouble2.set(xmlDouble);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetR8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(R8$34, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigDecimal getDecimal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DECIMAL$36, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getBigDecimalValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDecimal xgetDecimal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDecimal xmlDecimal = null;
            xmlDecimal = (XmlDecimal)((Object)this.get_store().find_element_user(DECIMAL$36, 0));
            return xmlDecimal;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDecimal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DECIMAL$36) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDecimal(BigDecimal bigDecimal) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DECIMAL$36, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DECIMAL$36));
            }
            simpleValue.setBigDecimalValue(bigDecimal);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDecimal(XmlDecimal xmlDecimal) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDecimal xmlDecimal2 = null;
            xmlDecimal2 = (XmlDecimal)((Object)this.get_store().find_element_user(DECIMAL$36, 0));
            if (xmlDecimal2 == null) {
                xmlDecimal2 = (XmlDecimal)((Object)this.get_store().add_element_user(DECIMAL$36));
            }
            xmlDecimal2.set(xmlDecimal);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDecimal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DECIMAL$36, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getLpstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LPSTR$38, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetLpstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(LPSTR$38, 0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLpstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LPSTR$38) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLpstr(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LPSTR$38, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(LPSTR$38));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLpstr(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(LPSTR$38, 0));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_element_user(LPSTR$38));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLpstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LPSTR$38, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getLpwstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LPWSTR$40, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetLpwstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(LPWSTR$40, 0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLpwstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LPWSTR$40) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLpwstr(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LPWSTR$40, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(LPWSTR$40));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLpwstr(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(LPWSTR$40, 0));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_element_user(LPWSTR$40));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLpwstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LPWSTR$40, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getBstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BSTR$42, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetBstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(BSTR$42, 0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BSTR$42) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBstr(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BSTR$42, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(BSTR$42));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBstr(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(BSTR$42, 0));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_element_user(BSTR$42));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BSTR$42, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Calendar getDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DATE$44, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getCalendarValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDateTime xgetDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime = null;
            xmlDateTime = (XmlDateTime)((Object)this.get_store().find_element_user(DATE$44, 0));
            return xmlDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DATE$44) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDate(Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DATE$44, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DATE$44));
            }
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDate(XmlDateTime xmlDateTime) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime2 = null;
            xmlDateTime2 = (XmlDateTime)((Object)this.get_store().find_element_user(DATE$44, 0));
            if (xmlDateTime2 == null) {
                xmlDateTime2 = (XmlDateTime)((Object)this.get_store().add_element_user(DATE$44));
            }
            xmlDateTime2.set(xmlDateTime);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DATE$44, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Calendar getFiletime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FILETIME$46, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getCalendarValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDateTime xgetFiletime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime = null;
            xmlDateTime = (XmlDateTime)((Object)this.get_store().find_element_user(FILETIME$46, 0));
            return xmlDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFiletime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILETIME$46) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFiletime(Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FILETIME$46, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(FILETIME$46));
            }
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFiletime(XmlDateTime xmlDateTime) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime2 = null;
            xmlDateTime2 = (XmlDateTime)((Object)this.get_store().find_element_user(FILETIME$46, 0));
            if (xmlDateTime2 == null) {
                xmlDateTime2 = (XmlDateTime)((Object)this.get_store().add_element_user(FILETIME$46));
            }
            xmlDateTime2.set(xmlDateTime);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFiletime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILETIME$46, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getBool() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BOOL$48, 0));
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetBool() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_element_user(BOOL$48, 0));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBool() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOOL$48) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBool(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BOOL$48, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(BOOL$48));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBool(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_element_user(BOOL$48, 0));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_element_user(BOOL$48));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBool() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOOL$48, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getCy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CY$50, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCy xgetCy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCy sTCy = null;
            sTCy = (STCy)this.get_store().find_element_user(CY$50, 0);
            return sTCy;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CY$50) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCy(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CY$50, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(CY$50));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCy(STCy sTCy) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCy sTCy2 = null;
            sTCy2 = (STCy)this.get_store().find_element_user(CY$50, 0);
            if (sTCy2 == null) {
                sTCy2 = (STCy)this.get_store().add_element_user(CY$50);
            }
            sTCy2.set((XmlObject)sTCy);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CY$50, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getError() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ERROR$52, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STError xgetError() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STError sTError = null;
            sTError = (STError)this.get_store().find_element_user(ERROR$52, 0);
            return sTError;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetError() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ERROR$52) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setError(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ERROR$52, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(ERROR$52));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetError(STError sTError) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STError sTError2 = null;
            sTError2 = (STError)this.get_store().find_element_user(ERROR$52, 0);
            if (sTError2 == null) {
                sTError2 = (STError)this.get_store().add_element_user(ERROR$52);
            }
            sTError2.set((XmlObject)sTError);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetError() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ERROR$52, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getStream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(STREAM$54, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBase64Binary xgetStream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_element_user(STREAM$54, 0));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STREAM$54) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStream(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(STREAM$54, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(STREAM$54));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStream(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_element_user(STREAM$54, 0));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_element_user(STREAM$54));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STREAM$54, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getOstream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(OSTREAM$56, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBase64Binary xgetOstream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_element_user(OSTREAM$56, 0));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOstream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OSTREAM$56) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOstream(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(OSTREAM$56, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(OSTREAM$56));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOstream(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_element_user(OSTREAM$56, 0));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_element_user(OSTREAM$56));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOstream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OSTREAM$56, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getStorage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(STORAGE$58, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBase64Binary xgetStorage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_element_user(STORAGE$58, 0));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStorage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STORAGE$58) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStorage(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(STORAGE$58, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(STORAGE$58));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStorage(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_element_user(STORAGE$58, 0));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_element_user(STORAGE$58));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStorage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STORAGE$58, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getOstorage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(OSTORAGE$60, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBase64Binary xgetOstorage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_element_user(OSTORAGE$60, 0));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOstorage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OSTORAGE$60) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOstorage(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(OSTORAGE$60, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(OSTORAGE$60));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOstorage(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_element_user(OSTORAGE$60, 0));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_element_user(OSTORAGE$60));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOstorage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OSTORAGE$60, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVstream getVstream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVstream cTVstream = null;
            cTVstream = (CTVstream)this.get_store().find_element_user(VSTREAM$62, 0);
            if (cTVstream == null) {
                return null;
            }
            return cTVstream;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVstream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VSTREAM$62) != 0;
        }
    }

    @Override
    public void setVstream(CTVstream cTVstream) {
        this.generatedSetterHelperImpl((XmlObject)cTVstream, VSTREAM$62, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVstream addNewVstream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVstream cTVstream = null;
            cTVstream = (CTVstream)this.get_store().add_element_user(VSTREAM$62);
            return cTVstream;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVstream() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VSTREAM$62, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getClsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CLSID$64, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STClsid xgetClsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STClsid sTClsid = null;
            sTClsid = (STClsid)((Object)this.get_store().find_element_user(CLSID$64, 0));
            return sTClsid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetClsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLSID$64) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setClsid(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CLSID$64, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(CLSID$64));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetClsid(STClsid sTClsid) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STClsid sTClsid2 = null;
            sTClsid2 = (STClsid)((Object)this.get_store().find_element_user(CLSID$64, 0));
            if (sTClsid2 == null) {
                sTClsid2 = (STClsid)((Object)this.get_store().add_element_user(CLSID$64));
            }
            sTClsid2.set(sTClsid);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetClsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLSID$64, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCf getCf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCf cTCf = null;
            cTCf = (CTCf)this.get_store().find_element_user(CF$66, 0);
            if (cTCf == null) {
                return null;
            }
            return cTCf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CF$66) != 0;
        }
    }

    @Override
    public void setCf(CTCf cTCf) {
        this.generatedSetterHelperImpl((XmlObject)cTCf, CF$66, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCf addNewCf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCf cTCf = null;
            cTCf = (CTCf)this.get_store().add_element_user(CF$66);
            return cTCf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CF$66, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFmtid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FMTID$68));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STClsid xgetFmtid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STClsid sTClsid = null;
            sTClsid = (STClsid)((Object)this.get_store().find_attribute_user(FMTID$68));
            return sTClsid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmtid(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FMTID$68));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FMTID$68));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmtid(STClsid sTClsid) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STClsid sTClsid2 = null;
            sTClsid2 = (STClsid)((Object)this.get_store().find_attribute_user(FMTID$68));
            if (sTClsid2 == null) {
                sTClsid2 = (STClsid)((Object)this.get_store().add_attribute_user(FMTID$68));
            }
            sTClsid2.set(sTClsid);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getPid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PID$70));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInt xgetPid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt = null;
            xmlInt = (XmlInt)((Object)this.get_store().find_attribute_user(PID$70));
            return xmlInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPid(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PID$70));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PID$70));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPid(XmlInt xmlInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt2 = null;
            xmlInt2 = (XmlInt)((Object)this.get_store().find_attribute_user(PID$70));
            if (xmlInt2 == null) {
                xmlInt2 = (XmlInt)((Object)this.get_store().add_attribute_user(PID$70));
            }
            xmlInt2.set(xmlInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$72));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(NAME$72));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(NAME$72) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setName(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$72));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$72));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetName(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(NAME$72));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(NAME$72));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(NAME$72);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getLinkTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LINKTARGET$74));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetLinkTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(LINKTARGET$74));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLinkTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LINKTARGET$74) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLinkTarget(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LINKTARGET$74));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LINKTARGET$74));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLinkTarget(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(LINKTARGET$74));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(LINKTARGET$74));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLinkTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LINKTARGET$74);
        }
    }
}

