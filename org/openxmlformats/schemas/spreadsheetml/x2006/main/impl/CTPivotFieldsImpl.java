/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFields;

public class CTPivotFieldsImpl
extends XmlComplexContentImpl
implements CTPivotFields {
    private static final long serialVersionUID = 1L;
    private static final QName PIVOTFIELD$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "pivotField");
    private static final QName COUNT$2 = new QName("", "count");

    public CTPivotFieldsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPivotField> getPivotFieldList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PivotFieldList
            extends AbstractList<CTPivotField> {
                PivotFieldList() {
                }

                @Override
                public CTPivotField get(int n) {
                    return CTPivotFieldsImpl.this.getPivotFieldArray(n);
                }

                @Override
                public CTPivotField set(int n, CTPivotField cTPivotField) {
                    CTPivotField cTPivotField2 = CTPivotFieldsImpl.this.getPivotFieldArray(n);
                    CTPivotFieldsImpl.this.setPivotFieldArray(n, cTPivotField);
                    return cTPivotField2;
                }

                @Override
                public void add(int n, CTPivotField cTPivotField) {
                    CTPivotFieldsImpl.this.insertNewPivotField(n).set(cTPivotField);
                }

                @Override
                public CTPivotField remove(int n) {
                    CTPivotField cTPivotField = CTPivotFieldsImpl.this.getPivotFieldArray(n);
                    CTPivotFieldsImpl.this.removePivotField(n);
                    return cTPivotField;
                }

                @Override
                public int size() {
                    return CTPivotFieldsImpl.this.sizeOfPivotFieldArray();
                }
            }
            return new PivotFieldList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPivotField[] getPivotFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PIVOTFIELD$0, arrayList);
            CTPivotField[] cTPivotFieldArray = new CTPivotField[arrayList.size()];
            arrayList.toArray(cTPivotFieldArray);
            return cTPivotFieldArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotField getPivotFieldArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotField cTPivotField = null;
            cTPivotField = (CTPivotField)((Object)this.get_store().find_element_user(PIVOTFIELD$0, n));
            if (cTPivotField == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPivotField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPivotFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PIVOTFIELD$0);
        }
    }

    @Override
    public void setPivotFieldArray(CTPivotField[] cTPivotFieldArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPivotFieldArray, PIVOTFIELD$0);
    }

    @Override
    public void setPivotFieldArray(int n, CTPivotField cTPivotField) {
        this.generatedSetterHelperImpl(cTPivotField, PIVOTFIELD$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotField insertNewPivotField(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotField cTPivotField = null;
            cTPivotField = (CTPivotField)((Object)this.get_store().insert_element_user(PIVOTFIELD$0, n));
            return cTPivotField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotField addNewPivotField() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotField cTPivotField = null;
            cTPivotField = (CTPivotField)((Object)this.get_store().add_element_user(PIVOTFIELD$0));
            return cTPivotField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePivotField(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PIVOTFIELD$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COUNT$2));
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
    public XmlUnsignedInt xgetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(COUNT$2));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COUNT$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCount(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COUNT$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COUNT$2));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCount(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(COUNT$2));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(COUNT$2));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COUNT$2);
        }
    }
}

