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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageFields;

public class CTPageFieldsImpl
extends XmlComplexContentImpl
implements CTPageFields {
    private static final long serialVersionUID = 1L;
    private static final QName PAGEFIELD$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "pageField");
    private static final QName COUNT$2 = new QName("", "count");

    public CTPageFieldsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPageField> getPageFieldList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PageFieldList
            extends AbstractList<CTPageField> {
                PageFieldList() {
                }

                @Override
                public CTPageField get(int n) {
                    return CTPageFieldsImpl.this.getPageFieldArray(n);
                }

                @Override
                public CTPageField set(int n, CTPageField cTPageField) {
                    CTPageField cTPageField2 = CTPageFieldsImpl.this.getPageFieldArray(n);
                    CTPageFieldsImpl.this.setPageFieldArray(n, cTPageField);
                    return cTPageField2;
                }

                @Override
                public void add(int n, CTPageField cTPageField) {
                    CTPageFieldsImpl.this.insertNewPageField(n).set(cTPageField);
                }

                @Override
                public CTPageField remove(int n) {
                    CTPageField cTPageField = CTPageFieldsImpl.this.getPageFieldArray(n);
                    CTPageFieldsImpl.this.removePageField(n);
                    return cTPageField;
                }

                @Override
                public int size() {
                    return CTPageFieldsImpl.this.sizeOfPageFieldArray();
                }
            }
            return new PageFieldList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPageField[] getPageFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PAGEFIELD$0, arrayList);
            CTPageField[] cTPageFieldArray = new CTPageField[arrayList.size()];
            arrayList.toArray(cTPageFieldArray);
            return cTPageFieldArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageField getPageFieldArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageField cTPageField = null;
            cTPageField = (CTPageField)((Object)this.get_store().find_element_user(PAGEFIELD$0, n));
            if (cTPageField == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPageField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPageFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PAGEFIELD$0);
        }
    }

    @Override
    public void setPageFieldArray(CTPageField[] cTPageFieldArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPageFieldArray, PAGEFIELD$0);
    }

    @Override
    public void setPageFieldArray(int n, CTPageField cTPageField) {
        this.generatedSetterHelperImpl(cTPageField, PAGEFIELD$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageField insertNewPageField(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageField cTPageField = null;
            cTPageField = (CTPageField)((Object)this.get_store().insert_element_user(PAGEFIELD$0, n));
            return cTPageField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageField addNewPageField() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageField cTPageField = null;
            cTPageField = (CTPageField)((Object)this.get_store().add_element_user(PAGEFIELD$0));
            return cTPageField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePageField(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PAGEFIELD$0, n);
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

