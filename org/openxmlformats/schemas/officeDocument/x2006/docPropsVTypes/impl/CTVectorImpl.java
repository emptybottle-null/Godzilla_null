/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTCf
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STCy
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STError
 *  org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STVectorBaseType
 */
package org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl;

import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlDateTime;
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
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTCf;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVariant;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STClsid;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STCy;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STError;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STVectorBaseType;

public class CTVectorImpl
extends XmlComplexContentImpl
implements CTVector {
    private static final long serialVersionUID = 1L;
    private static final QName VARIANT$0 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "variant");
    private static final QName I1$2 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i1");
    private static final QName I2$4 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i2");
    private static final QName I4$6 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i4");
    private static final QName I8$8 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i8");
    private static final QName UI1$10 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui1");
    private static final QName UI2$12 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui2");
    private static final QName UI4$14 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui4");
    private static final QName UI8$16 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui8");
    private static final QName R4$18 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "r4");
    private static final QName R8$20 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "r8");
    private static final QName LPSTR$22 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "lpstr");
    private static final QName LPWSTR$24 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "lpwstr");
    private static final QName BSTR$26 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "bstr");
    private static final QName DATE$28 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "date");
    private static final QName FILETIME$30 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "filetime");
    private static final QName BOOL$32 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "bool");
    private static final QName CY$34 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "cy");
    private static final QName ERROR$36 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "error");
    private static final QName CLSID$38 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "clsid");
    private static final QName CF$40 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "cf");
    private static final QName BASETYPE$42 = new QName("", "baseType");
    private static final QName SIZE$44 = new QName("", "size");

    public CTVectorImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTVariant> getVariantList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class VariantList
            extends AbstractList<CTVariant> {
                VariantList() {
                }

                @Override
                public CTVariant get(int n) {
                    return CTVectorImpl.this.getVariantArray(n);
                }

                @Override
                public CTVariant set(int n, CTVariant cTVariant) {
                    CTVariant cTVariant2 = CTVectorImpl.this.getVariantArray(n);
                    CTVectorImpl.this.setVariantArray(n, cTVariant);
                    return cTVariant2;
                }

                @Override
                public void add(int n, CTVariant cTVariant) {
                    CTVectorImpl.this.insertNewVariant(n).set(cTVariant);
                }

                @Override
                public CTVariant remove(int n) {
                    CTVariant cTVariant = CTVectorImpl.this.getVariantArray(n);
                    CTVectorImpl.this.removeVariant(n);
                    return cTVariant;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfVariantArray();
                }
            }
            return new VariantList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTVariant[] getVariantArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VARIANT$0, arrayList);
            CTVariant[] cTVariantArray = new CTVariant[arrayList.size()];
            arrayList.toArray(cTVariantArray);
            return cTVariantArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVariant getVariantArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVariant cTVariant = null;
            cTVariant = (CTVariant)((Object)this.get_store().find_element_user(VARIANT$0, n));
            if (cTVariant == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTVariant;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfVariantArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VARIANT$0);
        }
    }

    @Override
    public void setVariantArray(CTVariant[] cTVariantArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTVariantArray, VARIANT$0);
    }

    @Override
    public void setVariantArray(int n, CTVariant cTVariant) {
        this.generatedSetterHelperImpl(cTVariant, VARIANT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVariant insertNewVariant(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVariant cTVariant = null;
            cTVariant = (CTVariant)((Object)this.get_store().insert_element_user(VARIANT$0, n));
            return cTVariant;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVariant addNewVariant() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVariant cTVariant = null;
            cTVariant = (CTVariant)((Object)this.get_store().add_element_user(VARIANT$0));
            return cTVariant;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeVariant(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VARIANT$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Byte> getI1List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class I1List
            extends AbstractList<Byte> {
                I1List() {
                }

                @Override
                public Byte get(int n) {
                    return CTVectorImpl.this.getI1Array(n);
                }

                @Override
                public Byte set(int n, Byte by) {
                    Byte by2 = CTVectorImpl.this.getI1Array(n);
                    CTVectorImpl.this.setI1Array(n, by);
                    return by2;
                }

                @Override
                public void add(int n, Byte by) {
                    CTVectorImpl.this.insertI1(n, by);
                }

                @Override
                public Byte remove(int n) {
                    Byte by = CTVectorImpl.this.getI1Array(n);
                    CTVectorImpl.this.removeI1(n);
                    return by;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfI1Array();
                }
            }
            return new I1List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public byte[] getI1Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(I1$2, arrayList);
            byte[] byArray = new byte[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                byArray[i] = ((SimpleValue)arrayList.get(i)).getByteValue();
            }
            return byArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte getI1Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I1$2, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getByteValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlByte> xgetI1List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class I1List
            extends AbstractList<XmlByte> {
                I1List() {
                }

                @Override
                public XmlByte get(int n) {
                    return CTVectorImpl.this.xgetI1Array(n);
                }

                @Override
                public XmlByte set(int n, XmlByte xmlByte) {
                    XmlByte xmlByte2 = CTVectorImpl.this.xgetI1Array(n);
                    CTVectorImpl.this.xsetI1Array(n, xmlByte);
                    return xmlByte2;
                }

                @Override
                public void add(int n, XmlByte xmlByte) {
                    CTVectorImpl.this.insertNewI1(n).set(xmlByte);
                }

                @Override
                public XmlByte remove(int n) {
                    XmlByte xmlByte = CTVectorImpl.this.xgetI1Array(n);
                    CTVectorImpl.this.removeI1(n);
                    return xmlByte;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfI1Array();
                }
            }
            return new I1List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlByte[] xgetI1Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(I1$2, arrayList);
            XmlByte[] xmlByteArray = new XmlByte[arrayList.size()];
            arrayList.toArray(xmlByteArray);
            return xmlByteArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlByte xgetI1Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlByte xmlByte = null;
            xmlByte = (XmlByte)((Object)this.get_store().find_element_user(I1$2, n));
            if (xmlByte == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfI1Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(I1$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI1Array(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(byArray, I1$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI1Array(int n, byte by) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I1$2, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setByteValue(by);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI1Array(XmlByte[] xmlByteArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlByteArray, I1$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI1Array(int n, XmlByte xmlByte) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlByte xmlByte2 = null;
            xmlByte2 = (XmlByte)((Object)this.get_store().find_element_user(I1$2, n));
            if (xmlByte2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlByte2.set(xmlByte);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertI1(int n, byte by) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(I1$2, n));
            simpleValue.setByteValue(by);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addI1(byte by) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(I1$2));
            simpleValue.setByteValue(by);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlByte insertNewI1(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlByte xmlByte = null;
            xmlByte = (XmlByte)((Object)this.get_store().insert_element_user(I1$2, n));
            return xmlByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlByte addNewI1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlByte xmlByte = null;
            xmlByte = (XmlByte)((Object)this.get_store().add_element_user(I1$2));
            return xmlByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeI1(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(I1$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Short> getI2List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class I2List
            extends AbstractList<Short> {
                I2List() {
                }

                @Override
                public Short get(int n) {
                    return CTVectorImpl.this.getI2Array(n);
                }

                @Override
                public Short set(int n, Short s) {
                    Short s2 = CTVectorImpl.this.getI2Array(n);
                    CTVectorImpl.this.setI2Array(n, s);
                    return s2;
                }

                @Override
                public void add(int n, Short s) {
                    CTVectorImpl.this.insertI2(n, s);
                }

                @Override
                public Short remove(int n) {
                    Short s = CTVectorImpl.this.getI2Array(n);
                    CTVectorImpl.this.removeI2(n);
                    return s;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfI2Array();
                }
            }
            return new I2List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public short[] getI2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(I2$4, arrayList);
            short[] sArray = new short[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                sArray[i] = ((SimpleValue)arrayList.get(i)).getShortValue();
            }
            return sArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short getI2Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I2$4, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getShortValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlShort> xgetI2List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class I2List
            extends AbstractList<XmlShort> {
                I2List() {
                }

                @Override
                public XmlShort get(int n) {
                    return CTVectorImpl.this.xgetI2Array(n);
                }

                @Override
                public XmlShort set(int n, XmlShort xmlShort) {
                    XmlShort xmlShort2 = CTVectorImpl.this.xgetI2Array(n);
                    CTVectorImpl.this.xsetI2Array(n, xmlShort);
                    return xmlShort2;
                }

                @Override
                public void add(int n, XmlShort xmlShort) {
                    CTVectorImpl.this.insertNewI2(n).set(xmlShort);
                }

                @Override
                public XmlShort remove(int n) {
                    XmlShort xmlShort = CTVectorImpl.this.xgetI2Array(n);
                    CTVectorImpl.this.removeI2(n);
                    return xmlShort;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfI2Array();
                }
            }
            return new I2List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlShort[] xgetI2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(I2$4, arrayList);
            XmlShort[] xmlShortArray = new XmlShort[arrayList.size()];
            arrayList.toArray(xmlShortArray);
            return xmlShortArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlShort xgetI2Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlShort xmlShort = null;
            xmlShort = (XmlShort)((Object)this.get_store().find_element_user(I2$4, n));
            if (xmlShort == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlShort;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfI2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(I2$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI2Array(short[] sArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sArray, I2$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI2Array(int n, short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I2$4, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI2Array(XmlShort[] xmlShortArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlShortArray, I2$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI2Array(int n, XmlShort xmlShort) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlShort xmlShort2 = null;
            xmlShort2 = (XmlShort)((Object)this.get_store().find_element_user(I2$4, n));
            if (xmlShort2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlShort2.set(xmlShort);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertI2(int n, short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(I2$4, n));
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addI2(short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(I2$4));
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlShort insertNewI2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlShort xmlShort = null;
            xmlShort = (XmlShort)((Object)this.get_store().insert_element_user(I2$4, n));
            return xmlShort;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlShort addNewI2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlShort xmlShort = null;
            xmlShort = (XmlShort)((Object)this.get_store().add_element_user(I2$4));
            return xmlShort;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeI2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(I2$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Integer> getI4List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class I4List
            extends AbstractList<Integer> {
                I4List() {
                }

                @Override
                public Integer get(int n) {
                    return CTVectorImpl.this.getI4Array(n);
                }

                @Override
                public Integer set(int n, Integer n2) {
                    Integer n3 = CTVectorImpl.this.getI4Array(n);
                    CTVectorImpl.this.setI4Array(n, n2);
                    return n3;
                }

                @Override
                public void add(int n, Integer n2) {
                    CTVectorImpl.this.insertI4(n, n2);
                }

                @Override
                public Integer remove(int n) {
                    Integer n2 = CTVectorImpl.this.getI4Array(n);
                    CTVectorImpl.this.removeI4(n);
                    return n2;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfI4Array();
                }
            }
            return new I4List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public int[] getI4Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(I4$6, arrayList);
            int[] nArray = new int[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                nArray[i] = ((SimpleValue)arrayList.get(i)).getIntValue();
            }
            return nArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getI4Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I4$6, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInt> xgetI4List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class I4List
            extends AbstractList<XmlInt> {
                I4List() {
                }

                @Override
                public XmlInt get(int n) {
                    return CTVectorImpl.this.xgetI4Array(n);
                }

                @Override
                public XmlInt set(int n, XmlInt xmlInt) {
                    XmlInt xmlInt2 = CTVectorImpl.this.xgetI4Array(n);
                    CTVectorImpl.this.xsetI4Array(n, xmlInt);
                    return xmlInt2;
                }

                @Override
                public void add(int n, XmlInt xmlInt) {
                    CTVectorImpl.this.insertNewI4(n).set(xmlInt);
                }

                @Override
                public XmlInt remove(int n) {
                    XmlInt xmlInt = CTVectorImpl.this.xgetI4Array(n);
                    CTVectorImpl.this.removeI4(n);
                    return xmlInt;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfI4Array();
                }
            }
            return new I4List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInt[] xgetI4Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(I4$6, arrayList);
            XmlInt[] xmlIntArray = new XmlInt[arrayList.size()];
            arrayList.toArray(xmlIntArray);
            return xmlIntArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInt xgetI4Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt = null;
            xmlInt = (XmlInt)((Object)this.get_store().find_element_user(I4$6, n));
            if (xmlInt == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfI4Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(I4$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI4Array(int[] nArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(nArray, I4$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI4Array(int n, int n2) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I4$6, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setIntValue(n2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI4Array(XmlInt[] xmlIntArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntArray, I4$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI4Array(int n, XmlInt xmlInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt2 = null;
            xmlInt2 = (XmlInt)((Object)this.get_store().find_element_user(I4$6, n));
            if (xmlInt2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInt2.set(xmlInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertI4(int n, int n2) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(I4$6, n));
            simpleValue.setIntValue(n2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addI4(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(I4$6));
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInt insertNewI4(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt = null;
            xmlInt = (XmlInt)((Object)this.get_store().insert_element_user(I4$6, n));
            return xmlInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInt addNewI4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt = null;
            xmlInt = (XmlInt)((Object)this.get_store().add_element_user(I4$6));
            return xmlInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeI4(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(I4$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Long> getI8List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class I8List
            extends AbstractList<Long> {
                I8List() {
                }

                @Override
                public Long get(int n) {
                    return CTVectorImpl.this.getI8Array(n);
                }

                @Override
                public Long set(int n, Long l) {
                    Long l2 = CTVectorImpl.this.getI8Array(n);
                    CTVectorImpl.this.setI8Array(n, l);
                    return l2;
                }

                @Override
                public void add(int n, Long l) {
                    CTVectorImpl.this.insertI8(n, l);
                }

                @Override
                public Long remove(int n) {
                    Long l = CTVectorImpl.this.getI8Array(n);
                    CTVectorImpl.this.removeI8(n);
                    return l;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfI8Array();
                }
            }
            return new I8List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public long[] getI8Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(I8$8, arrayList);
            long[] lArray = new long[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                lArray[i] = ((SimpleValue)arrayList.get(i)).getLongValue();
            }
            return lArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getI8Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I8$8, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlLong> xgetI8List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class I8List
            extends AbstractList<XmlLong> {
                I8List() {
                }

                @Override
                public XmlLong get(int n) {
                    return CTVectorImpl.this.xgetI8Array(n);
                }

                @Override
                public XmlLong set(int n, XmlLong xmlLong) {
                    XmlLong xmlLong2 = CTVectorImpl.this.xgetI8Array(n);
                    CTVectorImpl.this.xsetI8Array(n, xmlLong);
                    return xmlLong2;
                }

                @Override
                public void add(int n, XmlLong xmlLong) {
                    CTVectorImpl.this.insertNewI8(n).set(xmlLong);
                }

                @Override
                public XmlLong remove(int n) {
                    XmlLong xmlLong = CTVectorImpl.this.xgetI8Array(n);
                    CTVectorImpl.this.removeI8(n);
                    return xmlLong;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfI8Array();
                }
            }
            return new I8List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlLong[] xgetI8Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(I8$8, arrayList);
            XmlLong[] xmlLongArray = new XmlLong[arrayList.size()];
            arrayList.toArray(xmlLongArray);
            return xmlLongArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlLong xgetI8Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlLong xmlLong = null;
            xmlLong = (XmlLong)((Object)this.get_store().find_element_user(I8$8, n));
            if (xmlLong == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlLong;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfI8Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(I8$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI8Array(long[] lArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(lArray, I8$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setI8Array(int n, long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(I8$8, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI8Array(XmlLong[] xmlLongArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlLongArray, I8$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetI8Array(int n, XmlLong xmlLong) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlLong xmlLong2 = null;
            xmlLong2 = (XmlLong)((Object)this.get_store().find_element_user(I8$8, n));
            if (xmlLong2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlLong2.set(xmlLong);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertI8(int n, long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(I8$8, n));
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addI8(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(I8$8));
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlLong insertNewI8(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlLong xmlLong = null;
            xmlLong = (XmlLong)((Object)this.get_store().insert_element_user(I8$8, n));
            return xmlLong;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlLong addNewI8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlLong xmlLong = null;
            xmlLong = (XmlLong)((Object)this.get_store().add_element_user(I8$8));
            return xmlLong;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeI8(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(I8$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Short> getUi1List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Ui1List
            extends AbstractList<Short> {
                Ui1List() {
                }

                @Override
                public Short get(int n) {
                    return CTVectorImpl.this.getUi1Array(n);
                }

                @Override
                public Short set(int n, Short s) {
                    Short s2 = CTVectorImpl.this.getUi1Array(n);
                    CTVectorImpl.this.setUi1Array(n, s);
                    return s2;
                }

                @Override
                public void add(int n, Short s) {
                    CTVectorImpl.this.insertUi1(n, s);
                }

                @Override
                public Short remove(int n) {
                    Short s = CTVectorImpl.this.getUi1Array(n);
                    CTVectorImpl.this.removeUi1(n);
                    return s;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfUi1Array();
                }
            }
            return new Ui1List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public short[] getUi1Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(UI1$10, arrayList);
            short[] sArray = new short[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                sArray[i] = ((SimpleValue)arrayList.get(i)).getShortValue();
            }
            return sArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short getUi1Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI1$10, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getShortValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlUnsignedByte> xgetUi1List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Ui1List
            extends AbstractList<XmlUnsignedByte> {
                Ui1List() {
                }

                @Override
                public XmlUnsignedByte get(int n) {
                    return CTVectorImpl.this.xgetUi1Array(n);
                }

                @Override
                public XmlUnsignedByte set(int n, XmlUnsignedByte xmlUnsignedByte) {
                    XmlUnsignedByte xmlUnsignedByte2 = CTVectorImpl.this.xgetUi1Array(n);
                    CTVectorImpl.this.xsetUi1Array(n, xmlUnsignedByte);
                    return xmlUnsignedByte2;
                }

                @Override
                public void add(int n, XmlUnsignedByte xmlUnsignedByte) {
                    CTVectorImpl.this.insertNewUi1(n).set(xmlUnsignedByte);
                }

                @Override
                public XmlUnsignedByte remove(int n) {
                    XmlUnsignedByte xmlUnsignedByte = CTVectorImpl.this.xgetUi1Array(n);
                    CTVectorImpl.this.removeUi1(n);
                    return xmlUnsignedByte;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfUi1Array();
                }
            }
            return new Ui1List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlUnsignedByte[] xgetUi1Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(UI1$10, arrayList);
            XmlUnsignedByte[] xmlUnsignedByteArray = new XmlUnsignedByte[arrayList.size()];
            arrayList.toArray(xmlUnsignedByteArray);
            return xmlUnsignedByteArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedByte xgetUi1Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte = null;
            xmlUnsignedByte = (XmlUnsignedByte)((Object)this.get_store().find_element_user(UI1$10, n));
            if (xmlUnsignedByte == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlUnsignedByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfUi1Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UI1$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi1Array(short[] sArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sArray, UI1$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi1Array(int n, short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI1$10, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi1Array(XmlUnsignedByte[] xmlUnsignedByteArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlUnsignedByteArray, UI1$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi1Array(int n, XmlUnsignedByte xmlUnsignedByte) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte2 = null;
            xmlUnsignedByte2 = (XmlUnsignedByte)((Object)this.get_store().find_element_user(UI1$10, n));
            if (xmlUnsignedByte2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlUnsignedByte2.set(xmlUnsignedByte);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertUi1(int n, short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(UI1$10, n));
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addUi1(short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(UI1$10));
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedByte insertNewUi1(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte = null;
            xmlUnsignedByte = (XmlUnsignedByte)((Object)this.get_store().insert_element_user(UI1$10, n));
            return xmlUnsignedByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedByte addNewUi1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte = null;
            xmlUnsignedByte = (XmlUnsignedByte)((Object)this.get_store().add_element_user(UI1$10));
            return xmlUnsignedByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeUi1(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UI1$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Integer> getUi2List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Ui2List
            extends AbstractList<Integer> {
                Ui2List() {
                }

                @Override
                public Integer get(int n) {
                    return CTVectorImpl.this.getUi2Array(n);
                }

                @Override
                public Integer set(int n, Integer n2) {
                    Integer n3 = CTVectorImpl.this.getUi2Array(n);
                    CTVectorImpl.this.setUi2Array(n, n2);
                    return n3;
                }

                @Override
                public void add(int n, Integer n2) {
                    CTVectorImpl.this.insertUi2(n, n2);
                }

                @Override
                public Integer remove(int n) {
                    Integer n2 = CTVectorImpl.this.getUi2Array(n);
                    CTVectorImpl.this.removeUi2(n);
                    return n2;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfUi2Array();
                }
            }
            return new Ui2List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public int[] getUi2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(UI2$12, arrayList);
            int[] nArray = new int[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                nArray[i] = ((SimpleValue)arrayList.get(i)).getIntValue();
            }
            return nArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getUi2Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI2$12, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlUnsignedShort> xgetUi2List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Ui2List
            extends AbstractList<XmlUnsignedShort> {
                Ui2List() {
                }

                @Override
                public XmlUnsignedShort get(int n) {
                    return CTVectorImpl.this.xgetUi2Array(n);
                }

                @Override
                public XmlUnsignedShort set(int n, XmlUnsignedShort xmlUnsignedShort) {
                    XmlUnsignedShort xmlUnsignedShort2 = CTVectorImpl.this.xgetUi2Array(n);
                    CTVectorImpl.this.xsetUi2Array(n, xmlUnsignedShort);
                    return xmlUnsignedShort2;
                }

                @Override
                public void add(int n, XmlUnsignedShort xmlUnsignedShort) {
                    CTVectorImpl.this.insertNewUi2(n).set(xmlUnsignedShort);
                }

                @Override
                public XmlUnsignedShort remove(int n) {
                    XmlUnsignedShort xmlUnsignedShort = CTVectorImpl.this.xgetUi2Array(n);
                    CTVectorImpl.this.removeUi2(n);
                    return xmlUnsignedShort;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfUi2Array();
                }
            }
            return new Ui2List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlUnsignedShort[] xgetUi2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(UI2$12, arrayList);
            XmlUnsignedShort[] xmlUnsignedShortArray = new XmlUnsignedShort[arrayList.size()];
            arrayList.toArray(xmlUnsignedShortArray);
            return xmlUnsignedShortArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedShort xgetUi2Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedShort xmlUnsignedShort = null;
            xmlUnsignedShort = (XmlUnsignedShort)((Object)this.get_store().find_element_user(UI2$12, n));
            if (xmlUnsignedShort == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlUnsignedShort;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfUi2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UI2$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi2Array(int[] nArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(nArray, UI2$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi2Array(int n, int n2) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI2$12, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setIntValue(n2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi2Array(XmlUnsignedShort[] xmlUnsignedShortArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlUnsignedShortArray, UI2$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi2Array(int n, XmlUnsignedShort xmlUnsignedShort) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedShort xmlUnsignedShort2 = null;
            xmlUnsignedShort2 = (XmlUnsignedShort)((Object)this.get_store().find_element_user(UI2$12, n));
            if (xmlUnsignedShort2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlUnsignedShort2.set(xmlUnsignedShort);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertUi2(int n, int n2) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(UI2$12, n));
            simpleValue.setIntValue(n2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addUi2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(UI2$12));
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedShort insertNewUi2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedShort xmlUnsignedShort = null;
            xmlUnsignedShort = (XmlUnsignedShort)((Object)this.get_store().insert_element_user(UI2$12, n));
            return xmlUnsignedShort;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedShort addNewUi2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedShort xmlUnsignedShort = null;
            xmlUnsignedShort = (XmlUnsignedShort)((Object)this.get_store().add_element_user(UI2$12));
            return xmlUnsignedShort;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeUi2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UI2$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Long> getUi4List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Ui4List
            extends AbstractList<Long> {
                Ui4List() {
                }

                @Override
                public Long get(int n) {
                    return CTVectorImpl.this.getUi4Array(n);
                }

                @Override
                public Long set(int n, Long l) {
                    Long l2 = CTVectorImpl.this.getUi4Array(n);
                    CTVectorImpl.this.setUi4Array(n, l);
                    return l2;
                }

                @Override
                public void add(int n, Long l) {
                    CTVectorImpl.this.insertUi4(n, l);
                }

                @Override
                public Long remove(int n) {
                    Long l = CTVectorImpl.this.getUi4Array(n);
                    CTVectorImpl.this.removeUi4(n);
                    return l;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfUi4Array();
                }
            }
            return new Ui4List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public long[] getUi4Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(UI4$14, arrayList);
            long[] lArray = new long[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                lArray[i] = ((SimpleValue)arrayList.get(i)).getLongValue();
            }
            return lArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getUi4Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI4$14, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlUnsignedInt> xgetUi4List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Ui4List
            extends AbstractList<XmlUnsignedInt> {
                Ui4List() {
                }

                @Override
                public XmlUnsignedInt get(int n) {
                    return CTVectorImpl.this.xgetUi4Array(n);
                }

                @Override
                public XmlUnsignedInt set(int n, XmlUnsignedInt xmlUnsignedInt) {
                    XmlUnsignedInt xmlUnsignedInt2 = CTVectorImpl.this.xgetUi4Array(n);
                    CTVectorImpl.this.xsetUi4Array(n, xmlUnsignedInt);
                    return xmlUnsignedInt2;
                }

                @Override
                public void add(int n, XmlUnsignedInt xmlUnsignedInt) {
                    CTVectorImpl.this.insertNewUi4(n).set(xmlUnsignedInt);
                }

                @Override
                public XmlUnsignedInt remove(int n) {
                    XmlUnsignedInt xmlUnsignedInt = CTVectorImpl.this.xgetUi4Array(n);
                    CTVectorImpl.this.removeUi4(n);
                    return xmlUnsignedInt;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfUi4Array();
                }
            }
            return new Ui4List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlUnsignedInt[] xgetUi4Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(UI4$14, arrayList);
            XmlUnsignedInt[] xmlUnsignedIntArray = new XmlUnsignedInt[arrayList.size()];
            arrayList.toArray(xmlUnsignedIntArray);
            return xmlUnsignedIntArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetUi4Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_element_user(UI4$14, n));
            if (xmlUnsignedInt == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfUi4Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UI4$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi4Array(long[] lArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(lArray, UI4$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi4Array(int n, long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI4$14, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi4Array(XmlUnsignedInt[] xmlUnsignedIntArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlUnsignedIntArray, UI4$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi4Array(int n, XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_element_user(UI4$14, n));
            if (xmlUnsignedInt2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertUi4(int n, long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(UI4$14, n));
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addUi4(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(UI4$14));
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt insertNewUi4(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().insert_element_user(UI4$14, n));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt addNewUi4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().add_element_user(UI4$14));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeUi4(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UI4$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getUi8List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Ui8List
            extends AbstractList<BigInteger> {
                Ui8List() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTVectorImpl.this.getUi8Array(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTVectorImpl.this.getUi8Array(n);
                    CTVectorImpl.this.setUi8Array(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTVectorImpl.this.insertUi8(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTVectorImpl.this.getUi8Array(n);
                    CTVectorImpl.this.removeUi8(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfUi8Array();
                }
            }
            return new Ui8List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getUi8Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(UI8$16, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getUi8Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI8$16, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlUnsignedLong> xgetUi8List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Ui8List
            extends AbstractList<XmlUnsignedLong> {
                Ui8List() {
                }

                @Override
                public XmlUnsignedLong get(int n) {
                    return CTVectorImpl.this.xgetUi8Array(n);
                }

                @Override
                public XmlUnsignedLong set(int n, XmlUnsignedLong xmlUnsignedLong) {
                    XmlUnsignedLong xmlUnsignedLong2 = CTVectorImpl.this.xgetUi8Array(n);
                    CTVectorImpl.this.xsetUi8Array(n, xmlUnsignedLong);
                    return xmlUnsignedLong2;
                }

                @Override
                public void add(int n, XmlUnsignedLong xmlUnsignedLong) {
                    CTVectorImpl.this.insertNewUi8(n).set(xmlUnsignedLong);
                }

                @Override
                public XmlUnsignedLong remove(int n) {
                    XmlUnsignedLong xmlUnsignedLong = CTVectorImpl.this.xgetUi8Array(n);
                    CTVectorImpl.this.removeUi8(n);
                    return xmlUnsignedLong;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfUi8Array();
                }
            }
            return new Ui8List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlUnsignedLong[] xgetUi8Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(UI8$16, arrayList);
            XmlUnsignedLong[] xmlUnsignedLongArray = new XmlUnsignedLong[arrayList.size()];
            arrayList.toArray(xmlUnsignedLongArray);
            return xmlUnsignedLongArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedLong xgetUi8Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedLong xmlUnsignedLong = null;
            xmlUnsignedLong = (XmlUnsignedLong)((Object)this.get_store().find_element_user(UI8$16, n));
            if (xmlUnsignedLong == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlUnsignedLong;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfUi8Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UI8$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi8Array(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, UI8$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUi8Array(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UI8$16, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi8Array(XmlUnsignedLong[] xmlUnsignedLongArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlUnsignedLongArray, UI8$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUi8Array(int n, XmlUnsignedLong xmlUnsignedLong) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedLong xmlUnsignedLong2 = null;
            xmlUnsignedLong2 = (XmlUnsignedLong)((Object)this.get_store().find_element_user(UI8$16, n));
            if (xmlUnsignedLong2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlUnsignedLong2.set(xmlUnsignedLong);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertUi8(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(UI8$16, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addUi8(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(UI8$16));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedLong insertNewUi8(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedLong xmlUnsignedLong = null;
            xmlUnsignedLong = (XmlUnsignedLong)((Object)this.get_store().insert_element_user(UI8$16, n));
            return xmlUnsignedLong;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedLong addNewUi8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedLong xmlUnsignedLong = null;
            xmlUnsignedLong = (XmlUnsignedLong)((Object)this.get_store().add_element_user(UI8$16));
            return xmlUnsignedLong;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeUi8(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UI8$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Float> getR4List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class R4List
            extends AbstractList<Float> {
                R4List() {
                }

                @Override
                public Float get(int n) {
                    return Float.valueOf(CTVectorImpl.this.getR4Array(n));
                }

                @Override
                public Float set(int n, Float f) {
                    Float f2 = Float.valueOf(CTVectorImpl.this.getR4Array(n));
                    CTVectorImpl.this.setR4Array(n, f.floatValue());
                    return f2;
                }

                @Override
                public void add(int n, Float f) {
                    CTVectorImpl.this.insertR4(n, f.floatValue());
                }

                @Override
                public Float remove(int n) {
                    Float f = Float.valueOf(CTVectorImpl.this.getR4Array(n));
                    CTVectorImpl.this.removeR4(n);
                    return f;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfR4Array();
                }
            }
            return new R4List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public float[] getR4Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(R4$18, arrayList);
            float[] fArray = new float[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                fArray[i] = ((SimpleValue)arrayList.get(i)).getFloatValue();
            }
            return fArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public float getR4Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(R4$18, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getFloatValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlFloat> xgetR4List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class R4List
            extends AbstractList<XmlFloat> {
                R4List() {
                }

                @Override
                public XmlFloat get(int n) {
                    return CTVectorImpl.this.xgetR4Array(n);
                }

                @Override
                public XmlFloat set(int n, XmlFloat xmlFloat) {
                    XmlFloat xmlFloat2 = CTVectorImpl.this.xgetR4Array(n);
                    CTVectorImpl.this.xsetR4Array(n, xmlFloat);
                    return xmlFloat2;
                }

                @Override
                public void add(int n, XmlFloat xmlFloat) {
                    CTVectorImpl.this.insertNewR4(n).set(xmlFloat);
                }

                @Override
                public XmlFloat remove(int n) {
                    XmlFloat xmlFloat = CTVectorImpl.this.xgetR4Array(n);
                    CTVectorImpl.this.removeR4(n);
                    return xmlFloat;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfR4Array();
                }
            }
            return new R4List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlFloat[] xgetR4Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(R4$18, arrayList);
            XmlFloat[] xmlFloatArray = new XmlFloat[arrayList.size()];
            arrayList.toArray(xmlFloatArray);
            return xmlFloatArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlFloat xgetR4Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlFloat xmlFloat = null;
            xmlFloat = (XmlFloat)((Object)this.get_store().find_element_user(R4$18, n));
            if (xmlFloat == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlFloat;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfR4Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(R4$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setR4Array(float[] fArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(fArray, R4$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setR4Array(int n, float f) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(R4$18, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setFloatValue(f);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetR4Array(XmlFloat[] xmlFloatArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlFloatArray, R4$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetR4Array(int n, XmlFloat xmlFloat) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlFloat xmlFloat2 = null;
            xmlFloat2 = (XmlFloat)((Object)this.get_store().find_element_user(R4$18, n));
            if (xmlFloat2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlFloat2.set(xmlFloat);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertR4(int n, float f) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(R4$18, n));
            simpleValue.setFloatValue(f);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addR4(float f) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(R4$18));
            simpleValue.setFloatValue(f);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlFloat insertNewR4(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlFloat xmlFloat = null;
            xmlFloat = (XmlFloat)((Object)this.get_store().insert_element_user(R4$18, n));
            return xmlFloat;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlFloat addNewR4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlFloat xmlFloat = null;
            xmlFloat = (XmlFloat)((Object)this.get_store().add_element_user(R4$18));
            return xmlFloat;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeR4(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(R4$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Double> getR8List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class R8List
            extends AbstractList<Double> {
                R8List() {
                }

                @Override
                public Double get(int n) {
                    return CTVectorImpl.this.getR8Array(n);
                }

                @Override
                public Double set(int n, Double d) {
                    Double d2 = CTVectorImpl.this.getR8Array(n);
                    CTVectorImpl.this.setR8Array(n, d);
                    return d2;
                }

                @Override
                public void add(int n, Double d) {
                    CTVectorImpl.this.insertR8(n, d);
                }

                @Override
                public Double remove(int n) {
                    Double d = CTVectorImpl.this.getR8Array(n);
                    CTVectorImpl.this.removeR8(n);
                    return d;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfR8Array();
                }
            }
            return new R8List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public double[] getR8Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(R8$20, arrayList);
            double[] dArray = new double[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                dArray[i] = ((SimpleValue)arrayList.get(i)).getDoubleValue();
            }
            return dArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getR8Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(R8$20, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getDoubleValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlDouble> xgetR8List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class R8List
            extends AbstractList<XmlDouble> {
                R8List() {
                }

                @Override
                public XmlDouble get(int n) {
                    return CTVectorImpl.this.xgetR8Array(n);
                }

                @Override
                public XmlDouble set(int n, XmlDouble xmlDouble) {
                    XmlDouble xmlDouble2 = CTVectorImpl.this.xgetR8Array(n);
                    CTVectorImpl.this.xsetR8Array(n, xmlDouble);
                    return xmlDouble2;
                }

                @Override
                public void add(int n, XmlDouble xmlDouble) {
                    CTVectorImpl.this.insertNewR8(n).set(xmlDouble);
                }

                @Override
                public XmlDouble remove(int n) {
                    XmlDouble xmlDouble = CTVectorImpl.this.xgetR8Array(n);
                    CTVectorImpl.this.removeR8(n);
                    return xmlDouble;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfR8Array();
                }
            }
            return new R8List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlDouble[] xgetR8Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(R8$20, arrayList);
            XmlDouble[] xmlDoubleArray = new XmlDouble[arrayList.size()];
            arrayList.toArray(xmlDoubleArray);
            return xmlDoubleArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDouble xgetR8Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDouble xmlDouble = null;
            xmlDouble = (XmlDouble)((Object)this.get_store().find_element_user(R8$20, n));
            if (xmlDouble == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfR8Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(R8$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setR8Array(double[] dArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(dArray, R8$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setR8Array(int n, double d) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(R8$20, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setDoubleValue(d);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetR8Array(XmlDouble[] xmlDoubleArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlDoubleArray, R8$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetR8Array(int n, XmlDouble xmlDouble) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDouble xmlDouble2 = null;
            xmlDouble2 = (XmlDouble)((Object)this.get_store().find_element_user(R8$20, n));
            if (xmlDouble2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlDouble2.set(xmlDouble);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertR8(int n, double d) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(R8$20, n));
            simpleValue.setDoubleValue(d);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addR8(double d) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(R8$20));
            simpleValue.setDoubleValue(d);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDouble insertNewR8(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDouble xmlDouble = null;
            xmlDouble = (XmlDouble)((Object)this.get_store().insert_element_user(R8$20, n));
            return xmlDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDouble addNewR8() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDouble xmlDouble = null;
            xmlDouble = (XmlDouble)((Object)this.get_store().add_element_user(R8$20));
            return xmlDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeR8(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(R8$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getLpstrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LpstrList
            extends AbstractList<String> {
                LpstrList() {
                }

                @Override
                public String get(int n) {
                    return CTVectorImpl.this.getLpstrArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTVectorImpl.this.getLpstrArray(n);
                    CTVectorImpl.this.setLpstrArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTVectorImpl.this.insertLpstr(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTVectorImpl.this.getLpstrArray(n);
                    CTVectorImpl.this.removeLpstr(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfLpstrArray();
                }
            }
            return new LpstrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getLpstrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LPSTR$22, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getLpstrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LPSTR$22, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetLpstrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LpstrList
            extends AbstractList<XmlString> {
                LpstrList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTVectorImpl.this.xgetLpstrArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTVectorImpl.this.xgetLpstrArray(n);
                    CTVectorImpl.this.xsetLpstrArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTVectorImpl.this.insertNewLpstr(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTVectorImpl.this.xgetLpstrArray(n);
                    CTVectorImpl.this.removeLpstr(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfLpstrArray();
                }
            }
            return new LpstrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetLpstrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LPSTR$22, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetLpstrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(LPSTR$22, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLpstrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LPSTR$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLpstrArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, LPSTR$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLpstrArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LPSTR$22, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLpstrArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, LPSTR$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLpstrArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(LPSTR$22, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertLpstr(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(LPSTR$22, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addLpstr(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(LPSTR$22));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewLpstr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(LPSTR$22, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewLpstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(LPSTR$22));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLpstr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LPSTR$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getLpwstrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LpwstrList
            extends AbstractList<String> {
                LpwstrList() {
                }

                @Override
                public String get(int n) {
                    return CTVectorImpl.this.getLpwstrArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTVectorImpl.this.getLpwstrArray(n);
                    CTVectorImpl.this.setLpwstrArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTVectorImpl.this.insertLpwstr(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTVectorImpl.this.getLpwstrArray(n);
                    CTVectorImpl.this.removeLpwstr(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfLpwstrArray();
                }
            }
            return new LpwstrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getLpwstrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LPWSTR$24, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getLpwstrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LPWSTR$24, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetLpwstrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LpwstrList
            extends AbstractList<XmlString> {
                LpwstrList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTVectorImpl.this.xgetLpwstrArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTVectorImpl.this.xgetLpwstrArray(n);
                    CTVectorImpl.this.xsetLpwstrArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTVectorImpl.this.insertNewLpwstr(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTVectorImpl.this.xgetLpwstrArray(n);
                    CTVectorImpl.this.removeLpwstr(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfLpwstrArray();
                }
            }
            return new LpwstrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetLpwstrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LPWSTR$24, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetLpwstrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(LPWSTR$24, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLpwstrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LPWSTR$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLpwstrArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, LPWSTR$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLpwstrArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LPWSTR$24, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLpwstrArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, LPWSTR$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLpwstrArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(LPWSTR$24, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertLpwstr(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(LPWSTR$24, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addLpwstr(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(LPWSTR$24));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewLpwstr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(LPWSTR$24, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewLpwstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(LPWSTR$24));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLpwstr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LPWSTR$24, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getBstrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BstrList
            extends AbstractList<String> {
                BstrList() {
                }

                @Override
                public String get(int n) {
                    return CTVectorImpl.this.getBstrArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTVectorImpl.this.getBstrArray(n);
                    CTVectorImpl.this.setBstrArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTVectorImpl.this.insertBstr(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTVectorImpl.this.getBstrArray(n);
                    CTVectorImpl.this.removeBstr(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfBstrArray();
                }
            }
            return new BstrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getBstrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BSTR$26, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getBstrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BSTR$26, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetBstrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BstrList
            extends AbstractList<XmlString> {
                BstrList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTVectorImpl.this.xgetBstrArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTVectorImpl.this.xgetBstrArray(n);
                    CTVectorImpl.this.xsetBstrArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTVectorImpl.this.insertNewBstr(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTVectorImpl.this.xgetBstrArray(n);
                    CTVectorImpl.this.removeBstr(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfBstrArray();
                }
            }
            return new BstrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetBstrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BSTR$26, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetBstrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(BSTR$26, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBstrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BSTR$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBstrArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, BSTR$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBstrArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BSTR$26, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBstrArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, BSTR$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBstrArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(BSTR$26, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertBstr(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(BSTR$26, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addBstr(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(BSTR$26));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewBstr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(BSTR$26, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewBstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(BSTR$26));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBstr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BSTR$26, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Calendar> getDateList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DateList
            extends AbstractList<Calendar> {
                DateList() {
                }

                @Override
                public Calendar get(int n) {
                    return CTVectorImpl.this.getDateArray(n);
                }

                @Override
                public Calendar set(int n, Calendar calendar) {
                    Calendar calendar2 = CTVectorImpl.this.getDateArray(n);
                    CTVectorImpl.this.setDateArray(n, calendar);
                    return calendar2;
                }

                @Override
                public void add(int n, Calendar calendar) {
                    CTVectorImpl.this.insertDate(n, calendar);
                }

                @Override
                public Calendar remove(int n) {
                    Calendar calendar = CTVectorImpl.this.getDateArray(n);
                    CTVectorImpl.this.removeDate(n);
                    return calendar;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfDateArray();
                }
            }
            return new DateList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public Calendar[] getDateArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DATE$28, arrayList);
            Calendar[] calendarArray = new Calendar[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                calendarArray[i] = ((SimpleValue)arrayList.get(i)).getCalendarValue();
            }
            return calendarArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Calendar getDateArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DATE$28, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getCalendarValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlDateTime> xgetDateList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DateList
            extends AbstractList<XmlDateTime> {
                DateList() {
                }

                @Override
                public XmlDateTime get(int n) {
                    return CTVectorImpl.this.xgetDateArray(n);
                }

                @Override
                public XmlDateTime set(int n, XmlDateTime xmlDateTime) {
                    XmlDateTime xmlDateTime2 = CTVectorImpl.this.xgetDateArray(n);
                    CTVectorImpl.this.xsetDateArray(n, xmlDateTime);
                    return xmlDateTime2;
                }

                @Override
                public void add(int n, XmlDateTime xmlDateTime) {
                    CTVectorImpl.this.insertNewDate(n).set(xmlDateTime);
                }

                @Override
                public XmlDateTime remove(int n) {
                    XmlDateTime xmlDateTime = CTVectorImpl.this.xgetDateArray(n);
                    CTVectorImpl.this.removeDate(n);
                    return xmlDateTime;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfDateArray();
                }
            }
            return new DateList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlDateTime[] xgetDateArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DATE$28, arrayList);
            XmlDateTime[] xmlDateTimeArray = new XmlDateTime[arrayList.size()];
            arrayList.toArray(xmlDateTimeArray);
            return xmlDateTimeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDateTime xgetDateArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime = null;
            xmlDateTime = (XmlDateTime)((Object)this.get_store().find_element_user(DATE$28, n));
            if (xmlDateTime == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDateArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DATE$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDateArray(Calendar[] calendarArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(calendarArray, DATE$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDateArray(int n, Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DATE$28, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDateArray(XmlDateTime[] xmlDateTimeArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlDateTimeArray, DATE$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDateArray(int n, XmlDateTime xmlDateTime) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime2 = null;
            xmlDateTime2 = (XmlDateTime)((Object)this.get_store().find_element_user(DATE$28, n));
            if (xmlDateTime2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlDateTime2.set(xmlDateTime);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertDate(int n, Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(DATE$28, n));
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addDate(Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DATE$28));
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDateTime insertNewDate(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime = null;
            xmlDateTime = (XmlDateTime)((Object)this.get_store().insert_element_user(DATE$28, n));
            return xmlDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDateTime addNewDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime = null;
            xmlDateTime = (XmlDateTime)((Object)this.get_store().add_element_user(DATE$28));
            return xmlDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDate(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DATE$28, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Calendar> getFiletimeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FiletimeList
            extends AbstractList<Calendar> {
                FiletimeList() {
                }

                @Override
                public Calendar get(int n) {
                    return CTVectorImpl.this.getFiletimeArray(n);
                }

                @Override
                public Calendar set(int n, Calendar calendar) {
                    Calendar calendar2 = CTVectorImpl.this.getFiletimeArray(n);
                    CTVectorImpl.this.setFiletimeArray(n, calendar);
                    return calendar2;
                }

                @Override
                public void add(int n, Calendar calendar) {
                    CTVectorImpl.this.insertFiletime(n, calendar);
                }

                @Override
                public Calendar remove(int n) {
                    Calendar calendar = CTVectorImpl.this.getFiletimeArray(n);
                    CTVectorImpl.this.removeFiletime(n);
                    return calendar;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfFiletimeArray();
                }
            }
            return new FiletimeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public Calendar[] getFiletimeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FILETIME$30, arrayList);
            Calendar[] calendarArray = new Calendar[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                calendarArray[i] = ((SimpleValue)arrayList.get(i)).getCalendarValue();
            }
            return calendarArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Calendar getFiletimeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FILETIME$30, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getCalendarValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlDateTime> xgetFiletimeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FiletimeList
            extends AbstractList<XmlDateTime> {
                FiletimeList() {
                }

                @Override
                public XmlDateTime get(int n) {
                    return CTVectorImpl.this.xgetFiletimeArray(n);
                }

                @Override
                public XmlDateTime set(int n, XmlDateTime xmlDateTime) {
                    XmlDateTime xmlDateTime2 = CTVectorImpl.this.xgetFiletimeArray(n);
                    CTVectorImpl.this.xsetFiletimeArray(n, xmlDateTime);
                    return xmlDateTime2;
                }

                @Override
                public void add(int n, XmlDateTime xmlDateTime) {
                    CTVectorImpl.this.insertNewFiletime(n).set(xmlDateTime);
                }

                @Override
                public XmlDateTime remove(int n) {
                    XmlDateTime xmlDateTime = CTVectorImpl.this.xgetFiletimeArray(n);
                    CTVectorImpl.this.removeFiletime(n);
                    return xmlDateTime;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfFiletimeArray();
                }
            }
            return new FiletimeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlDateTime[] xgetFiletimeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FILETIME$30, arrayList);
            XmlDateTime[] xmlDateTimeArray = new XmlDateTime[arrayList.size()];
            arrayList.toArray(xmlDateTimeArray);
            return xmlDateTimeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDateTime xgetFiletimeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime = null;
            xmlDateTime = (XmlDateTime)((Object)this.get_store().find_element_user(FILETIME$30, n));
            if (xmlDateTime == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFiletimeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILETIME$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFiletimeArray(Calendar[] calendarArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(calendarArray, FILETIME$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFiletimeArray(int n, Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FILETIME$30, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFiletimeArray(XmlDateTime[] xmlDateTimeArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlDateTimeArray, FILETIME$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFiletimeArray(int n, XmlDateTime xmlDateTime) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime2 = null;
            xmlDateTime2 = (XmlDateTime)((Object)this.get_store().find_element_user(FILETIME$30, n));
            if (xmlDateTime2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlDateTime2.set(xmlDateTime);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertFiletime(int n, Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(FILETIME$30, n));
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addFiletime(Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(FILETIME$30));
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDateTime insertNewFiletime(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime = null;
            xmlDateTime = (XmlDateTime)((Object)this.get_store().insert_element_user(FILETIME$30, n));
            return xmlDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDateTime addNewFiletime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime = null;
            xmlDateTime = (XmlDateTime)((Object)this.get_store().add_element_user(FILETIME$30));
            return xmlDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFiletime(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILETIME$30, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<Boolean> getBoolList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BoolList
            extends AbstractList<Boolean> {
                BoolList() {
                }

                @Override
                public Boolean get(int n) {
                    return CTVectorImpl.this.getBoolArray(n);
                }

                @Override
                public Boolean set(int n, Boolean bl) {
                    Boolean bl2 = CTVectorImpl.this.getBoolArray(n);
                    CTVectorImpl.this.setBoolArray(n, bl);
                    return bl2;
                }

                @Override
                public void add(int n, Boolean bl) {
                    CTVectorImpl.this.insertBool(n, bl);
                }

                @Override
                public Boolean remove(int n) {
                    Boolean bl = CTVectorImpl.this.getBoolArray(n);
                    CTVectorImpl.this.removeBool(n);
                    return bl;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfBoolArray();
                }
            }
            return new BoolList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public boolean[] getBoolArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BOOL$32, arrayList);
            boolean[] blArray = new boolean[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                blArray[i] = ((SimpleValue)arrayList.get(i)).getBooleanValue();
            }
            return blArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getBoolArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BOOL$32, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlBoolean> xgetBoolList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BoolList
            extends AbstractList<XmlBoolean> {
                BoolList() {
                }

                @Override
                public XmlBoolean get(int n) {
                    return CTVectorImpl.this.xgetBoolArray(n);
                }

                @Override
                public XmlBoolean set(int n, XmlBoolean xmlBoolean) {
                    XmlBoolean xmlBoolean2 = CTVectorImpl.this.xgetBoolArray(n);
                    CTVectorImpl.this.xsetBoolArray(n, xmlBoolean);
                    return xmlBoolean2;
                }

                @Override
                public void add(int n, XmlBoolean xmlBoolean) {
                    CTVectorImpl.this.insertNewBool(n).set(xmlBoolean);
                }

                @Override
                public XmlBoolean remove(int n) {
                    XmlBoolean xmlBoolean = CTVectorImpl.this.xgetBoolArray(n);
                    CTVectorImpl.this.removeBool(n);
                    return xmlBoolean;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfBoolArray();
                }
            }
            return new BoolList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlBoolean[] xgetBoolArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BOOL$32, arrayList);
            XmlBoolean[] xmlBooleanArray = new XmlBoolean[arrayList.size()];
            arrayList.toArray(xmlBooleanArray);
            return xmlBooleanArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetBoolArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_element_user(BOOL$32, n));
            if (xmlBoolean == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBoolArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOOL$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBoolArray(boolean[] blArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(blArray, BOOL$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBoolArray(int n, boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BOOL$32, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBoolArray(XmlBoolean[] xmlBooleanArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlBooleanArray, BOOL$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBoolArray(int n, XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_element_user(BOOL$32, n));
            if (xmlBoolean2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertBool(int n, boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(BOOL$32, n));
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addBool(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(BOOL$32));
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean insertNewBool(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().insert_element_user(BOOL$32, n));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean addNewBool() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().add_element_user(BOOL$32));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBool(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOOL$32, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getCyList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CyList
            extends AbstractList<String> {
                CyList() {
                }

                @Override
                public String get(int n) {
                    return CTVectorImpl.this.getCyArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTVectorImpl.this.getCyArray(n);
                    CTVectorImpl.this.setCyArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTVectorImpl.this.insertCy(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTVectorImpl.this.getCyArray(n);
                    CTVectorImpl.this.removeCy(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfCyArray();
                }
            }
            return new CyList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getCyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CY$34, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getCyArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CY$34, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STCy> xgetCyList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CyList
            extends AbstractList<STCy> {
                CyList() {
                }

                @Override
                public STCy get(int n) {
                    return CTVectorImpl.this.xgetCyArray(n);
                }

                @Override
                public STCy set(int n, STCy sTCy) {
                    STCy sTCy2 = CTVectorImpl.this.xgetCyArray(n);
                    CTVectorImpl.this.xsetCyArray(n, sTCy);
                    return sTCy2;
                }

                @Override
                public void add(int n, STCy sTCy) {
                    CTVectorImpl.this.insertNewCy(n).set((XmlObject)sTCy);
                }

                @Override
                public STCy remove(int n) {
                    STCy sTCy = CTVectorImpl.this.xgetCyArray(n);
                    CTVectorImpl.this.removeCy(n);
                    return sTCy;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfCyArray();
                }
            }
            return new CyList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STCy[] xgetCyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CY$34, arrayList);
            STCy[] sTCyArray = new STCy[arrayList.size()];
            arrayList.toArray(sTCyArray);
            return sTCyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCy xgetCyArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCy sTCy = null;
            sTCy = (STCy)this.get_store().find_element_user(CY$34, n);
            if (sTCy == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTCy;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CY$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCyArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, CY$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCyArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CY$34, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCyArray(STCy[] sTCyArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper((XmlObject[])sTCyArray, CY$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCyArray(int n, STCy sTCy) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCy sTCy2 = null;
            sTCy2 = (STCy)this.get_store().find_element_user(CY$34, n);
            if (sTCy2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTCy2.set((XmlObject)sTCy);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertCy(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(CY$34, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addCy(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(CY$34));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCy insertNewCy(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCy sTCy = null;
            sTCy = (STCy)this.get_store().insert_element_user(CY$34, n);
            return sTCy;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCy addNewCy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCy sTCy = null;
            sTCy = (STCy)this.get_store().add_element_user(CY$34);
            return sTCy;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCy(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CY$34, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getErrorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ErrorList
            extends AbstractList<String> {
                ErrorList() {
                }

                @Override
                public String get(int n) {
                    return CTVectorImpl.this.getErrorArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTVectorImpl.this.getErrorArray(n);
                    CTVectorImpl.this.setErrorArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTVectorImpl.this.insertError(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTVectorImpl.this.getErrorArray(n);
                    CTVectorImpl.this.removeError(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfErrorArray();
                }
            }
            return new ErrorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getErrorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ERROR$36, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getErrorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ERROR$36, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STError> xgetErrorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ErrorList
            extends AbstractList<STError> {
                ErrorList() {
                }

                @Override
                public STError get(int n) {
                    return CTVectorImpl.this.xgetErrorArray(n);
                }

                @Override
                public STError set(int n, STError sTError) {
                    STError sTError2 = CTVectorImpl.this.xgetErrorArray(n);
                    CTVectorImpl.this.xsetErrorArray(n, sTError);
                    return sTError2;
                }

                @Override
                public void add(int n, STError sTError) {
                    CTVectorImpl.this.insertNewError(n).set((XmlObject)sTError);
                }

                @Override
                public STError remove(int n) {
                    STError sTError = CTVectorImpl.this.xgetErrorArray(n);
                    CTVectorImpl.this.removeError(n);
                    return sTError;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfErrorArray();
                }
            }
            return new ErrorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STError[] xgetErrorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ERROR$36, arrayList);
            STError[] sTErrorArray = new STError[arrayList.size()];
            arrayList.toArray(sTErrorArray);
            return sTErrorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STError xgetErrorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STError sTError = null;
            sTError = (STError)this.get_store().find_element_user(ERROR$36, n);
            if (sTError == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTError;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfErrorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ERROR$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setErrorArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, ERROR$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setErrorArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ERROR$36, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetErrorArray(STError[] sTErrorArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper((XmlObject[])sTErrorArray, ERROR$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetErrorArray(int n, STError sTError) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STError sTError2 = null;
            sTError2 = (STError)this.get_store().find_element_user(ERROR$36, n);
            if (sTError2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTError2.set((XmlObject)sTError);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertError(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(ERROR$36, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addError(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(ERROR$36));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STError insertNewError(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STError sTError = null;
            sTError = (STError)this.get_store().insert_element_user(ERROR$36, n);
            return sTError;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STError addNewError() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STError sTError = null;
            sTError = (STError)this.get_store().add_element_user(ERROR$36);
            return sTError;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeError(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ERROR$36, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getClsidList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ClsidList
            extends AbstractList<String> {
                ClsidList() {
                }

                @Override
                public String get(int n) {
                    return CTVectorImpl.this.getClsidArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTVectorImpl.this.getClsidArray(n);
                    CTVectorImpl.this.setClsidArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTVectorImpl.this.insertClsid(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTVectorImpl.this.getClsidArray(n);
                    CTVectorImpl.this.removeClsid(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfClsidArray();
                }
            }
            return new ClsidList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getClsidArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CLSID$38, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getClsidArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CLSID$38, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STClsid> xgetClsidList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ClsidList
            extends AbstractList<STClsid> {
                ClsidList() {
                }

                @Override
                public STClsid get(int n) {
                    return CTVectorImpl.this.xgetClsidArray(n);
                }

                @Override
                public STClsid set(int n, STClsid sTClsid) {
                    STClsid sTClsid2 = CTVectorImpl.this.xgetClsidArray(n);
                    CTVectorImpl.this.xsetClsidArray(n, sTClsid);
                    return sTClsid2;
                }

                @Override
                public void add(int n, STClsid sTClsid) {
                    CTVectorImpl.this.insertNewClsid(n).set(sTClsid);
                }

                @Override
                public STClsid remove(int n) {
                    STClsid sTClsid = CTVectorImpl.this.xgetClsidArray(n);
                    CTVectorImpl.this.removeClsid(n);
                    return sTClsid;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfClsidArray();
                }
            }
            return new ClsidList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STClsid[] xgetClsidArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CLSID$38, arrayList);
            STClsid[] sTClsidArray = new STClsid[arrayList.size()];
            arrayList.toArray(sTClsidArray);
            return sTClsidArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STClsid xgetClsidArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STClsid sTClsid = null;
            sTClsid = (STClsid)((Object)this.get_store().find_element_user(CLSID$38, n));
            if (sTClsid == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTClsid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfClsidArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLSID$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setClsidArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, CLSID$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setClsidArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CLSID$38, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetClsidArray(STClsid[] sTClsidArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTClsidArray, CLSID$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetClsidArray(int n, STClsid sTClsid) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STClsid sTClsid2 = null;
            sTClsid2 = (STClsid)((Object)this.get_store().find_element_user(CLSID$38, n));
            if (sTClsid2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTClsid2.set(sTClsid);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertClsid(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(CLSID$38, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addClsid(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(CLSID$38));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STClsid insertNewClsid(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STClsid sTClsid = null;
            sTClsid = (STClsid)((Object)this.get_store().insert_element_user(CLSID$38, n));
            return sTClsid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STClsid addNewClsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STClsid sTClsid = null;
            sTClsid = (STClsid)((Object)this.get_store().add_element_user(CLSID$38));
            return sTClsid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeClsid(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLSID$38, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCf> getCfList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CfList
            extends AbstractList<CTCf> {
                CfList() {
                }

                @Override
                public CTCf get(int n) {
                    return CTVectorImpl.this.getCfArray(n);
                }

                @Override
                public CTCf set(int n, CTCf cTCf) {
                    CTCf cTCf2 = CTVectorImpl.this.getCfArray(n);
                    CTVectorImpl.this.setCfArray(n, cTCf);
                    return cTCf2;
                }

                @Override
                public void add(int n, CTCf cTCf) {
                    CTVectorImpl.this.insertNewCf(n).set((XmlObject)cTCf);
                }

                @Override
                public CTCf remove(int n) {
                    CTCf cTCf = CTVectorImpl.this.getCfArray(n);
                    CTVectorImpl.this.removeCf(n);
                    return cTCf;
                }

                @Override
                public int size() {
                    return CTVectorImpl.this.sizeOfCfArray();
                }
            }
            return new CfList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCf[] getCfArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CF$40, arrayList);
            CTCf[] cTCfArray = new CTCf[arrayList.size()];
            arrayList.toArray(cTCfArray);
            return cTCfArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCf getCfArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCf cTCf = null;
            cTCf = (CTCf)this.get_store().find_element_user(CF$40, n);
            if (cTCf == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCfArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CF$40);
        }
    }

    @Override
    public void setCfArray(CTCf[] cTCfArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTCfArray, CF$40);
    }

    @Override
    public void setCfArray(int n, CTCf cTCf) {
        this.generatedSetterHelperImpl((XmlObject)cTCf, CF$40, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCf insertNewCf(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCf cTCf = null;
            cTCf = (CTCf)this.get_store().insert_element_user(CF$40, n);
            return cTCf;
        }
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
            cTCf = (CTCf)this.get_store().add_element_user(CF$40);
            return cTCf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCf(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CF$40, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STVectorBaseType.Enum getBaseType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BASETYPE$42));
            if (simpleValue == null) {
                return null;
            }
            return (STVectorBaseType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STVectorBaseType xgetBaseType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVectorBaseType sTVectorBaseType = null;
            sTVectorBaseType = (STVectorBaseType)this.get_store().find_attribute_user(BASETYPE$42);
            return sTVectorBaseType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBaseType(STVectorBaseType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BASETYPE$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BASETYPE$42));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBaseType(STVectorBaseType sTVectorBaseType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVectorBaseType sTVectorBaseType2 = null;
            sTVectorBaseType2 = (STVectorBaseType)this.get_store().find_attribute_user(BASETYPE$42);
            if (sTVectorBaseType2 == null) {
                sTVectorBaseType2 = (STVectorBaseType)this.get_store().add_attribute_user(BASETYPE$42);
            }
            sTVectorBaseType2.set((XmlObject)sTVectorBaseType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SIZE$44));
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
    public XmlUnsignedInt xgetSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(SIZE$44));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSize(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SIZE$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SIZE$44));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSize(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(SIZE$44));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(SIZE$44));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }
}

