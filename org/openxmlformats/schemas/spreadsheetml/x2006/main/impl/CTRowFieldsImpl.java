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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowFields;

public class CTRowFieldsImpl
extends XmlComplexContentImpl
implements CTRowFields {
    private static final long serialVersionUID = 1L;
    private static final QName FIELD$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "field");
    private static final QName COUNT$2 = new QName("", "count");

    public CTRowFieldsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTField> getFieldList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FieldList
            extends AbstractList<CTField> {
                FieldList() {
                }

                @Override
                public CTField get(int n) {
                    return CTRowFieldsImpl.this.getFieldArray(n);
                }

                @Override
                public CTField set(int n, CTField cTField) {
                    CTField cTField2 = CTRowFieldsImpl.this.getFieldArray(n);
                    CTRowFieldsImpl.this.setFieldArray(n, cTField);
                    return cTField2;
                }

                @Override
                public void add(int n, CTField cTField) {
                    CTRowFieldsImpl.this.insertNewField(n).set(cTField);
                }

                @Override
                public CTField remove(int n) {
                    CTField cTField = CTRowFieldsImpl.this.getFieldArray(n);
                    CTRowFieldsImpl.this.removeField(n);
                    return cTField;
                }

                @Override
                public int size() {
                    return CTRowFieldsImpl.this.sizeOfFieldArray();
                }
            }
            return new FieldList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTField[] getFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FIELD$0, arrayList);
            CTField[] cTFieldArray = new CTField[arrayList.size()];
            arrayList.toArray(cTFieldArray);
            return cTFieldArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTField getFieldArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTField cTField = null;
            cTField = (CTField)((Object)this.get_store().find_element_user(FIELD$0, n));
            if (cTField == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FIELD$0);
        }
    }

    @Override
    public void setFieldArray(CTField[] cTFieldArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFieldArray, FIELD$0);
    }

    @Override
    public void setFieldArray(int n, CTField cTField) {
        this.generatedSetterHelperImpl(cTField, FIELD$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTField insertNewField(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTField cTField = null;
            cTField = (CTField)((Object)this.get_store().insert_element_user(FIELD$0, n));
            return cTField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTField addNewField() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTField cTField = null;
            cTField = (CTField)((Object)this.get_store().add_element_user(FIELD$0));
            return cTField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeField(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FIELD$0, n);
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
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(COUNT$2));
            }
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
            if (xmlUnsignedInt == null) {
                xmlUnsignedInt = (XmlUnsignedInt)this.get_default_attribute_value(COUNT$2);
            }
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

