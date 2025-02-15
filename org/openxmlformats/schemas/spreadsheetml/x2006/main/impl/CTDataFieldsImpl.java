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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataFields;

public class CTDataFieldsImpl
extends XmlComplexContentImpl
implements CTDataFields {
    private static final long serialVersionUID = 1L;
    private static final QName DATAFIELD$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "dataField");
    private static final QName COUNT$2 = new QName("", "count");

    public CTDataFieldsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDataField> getDataFieldList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DataFieldList
            extends AbstractList<CTDataField> {
                DataFieldList() {
                }

                @Override
                public CTDataField get(int n) {
                    return CTDataFieldsImpl.this.getDataFieldArray(n);
                }

                @Override
                public CTDataField set(int n, CTDataField cTDataField) {
                    CTDataField cTDataField2 = CTDataFieldsImpl.this.getDataFieldArray(n);
                    CTDataFieldsImpl.this.setDataFieldArray(n, cTDataField);
                    return cTDataField2;
                }

                @Override
                public void add(int n, CTDataField cTDataField) {
                    CTDataFieldsImpl.this.insertNewDataField(n).set(cTDataField);
                }

                @Override
                public CTDataField remove(int n) {
                    CTDataField cTDataField = CTDataFieldsImpl.this.getDataFieldArray(n);
                    CTDataFieldsImpl.this.removeDataField(n);
                    return cTDataField;
                }

                @Override
                public int size() {
                    return CTDataFieldsImpl.this.sizeOfDataFieldArray();
                }
            }
            return new DataFieldList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDataField[] getDataFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DATAFIELD$0, arrayList);
            CTDataField[] cTDataFieldArray = new CTDataField[arrayList.size()];
            arrayList.toArray(cTDataFieldArray);
            return cTDataFieldArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataField getDataFieldArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataField cTDataField = null;
            cTDataField = (CTDataField)((Object)this.get_store().find_element_user(DATAFIELD$0, n));
            if (cTDataField == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDataField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDataFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DATAFIELD$0);
        }
    }

    @Override
    public void setDataFieldArray(CTDataField[] cTDataFieldArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDataFieldArray, DATAFIELD$0);
    }

    @Override
    public void setDataFieldArray(int n, CTDataField cTDataField) {
        this.generatedSetterHelperImpl(cTDataField, DATAFIELD$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataField insertNewDataField(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataField cTDataField = null;
            cTDataField = (CTDataField)((Object)this.get_store().insert_element_user(DATAFIELD$0, n));
            return cTDataField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataField addNewDataField() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataField cTDataField = null;
            cTDataField = (CTDataField)((Object)this.get_store().add_element_user(DATAFIELD$0));
            return cTDataField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDataField(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DATAFIELD$0, n);
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

