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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheFields;

public class CTCacheFieldsImpl
extends XmlComplexContentImpl
implements CTCacheFields {
    private static final long serialVersionUID = 1L;
    private static final QName CACHEFIELD$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cacheField");
    private static final QName COUNT$2 = new QName("", "count");

    public CTCacheFieldsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCacheField> getCacheFieldList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CacheFieldList
            extends AbstractList<CTCacheField> {
                CacheFieldList() {
                }

                @Override
                public CTCacheField get(int n) {
                    return CTCacheFieldsImpl.this.getCacheFieldArray(n);
                }

                @Override
                public CTCacheField set(int n, CTCacheField cTCacheField) {
                    CTCacheField cTCacheField2 = CTCacheFieldsImpl.this.getCacheFieldArray(n);
                    CTCacheFieldsImpl.this.setCacheFieldArray(n, cTCacheField);
                    return cTCacheField2;
                }

                @Override
                public void add(int n, CTCacheField cTCacheField) {
                    CTCacheFieldsImpl.this.insertNewCacheField(n).set(cTCacheField);
                }

                @Override
                public CTCacheField remove(int n) {
                    CTCacheField cTCacheField = CTCacheFieldsImpl.this.getCacheFieldArray(n);
                    CTCacheFieldsImpl.this.removeCacheField(n);
                    return cTCacheField;
                }

                @Override
                public int size() {
                    return CTCacheFieldsImpl.this.sizeOfCacheFieldArray();
                }
            }
            return new CacheFieldList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCacheField[] getCacheFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CACHEFIELD$0, arrayList);
            CTCacheField[] cTCacheFieldArray = new CTCacheField[arrayList.size()];
            arrayList.toArray(cTCacheFieldArray);
            return cTCacheFieldArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCacheField getCacheFieldArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCacheField cTCacheField = null;
            cTCacheField = (CTCacheField)((Object)this.get_store().find_element_user(CACHEFIELD$0, n));
            if (cTCacheField == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCacheField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCacheFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CACHEFIELD$0);
        }
    }

    @Override
    public void setCacheFieldArray(CTCacheField[] cTCacheFieldArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTCacheFieldArray, CACHEFIELD$0);
    }

    @Override
    public void setCacheFieldArray(int n, CTCacheField cTCacheField) {
        this.generatedSetterHelperImpl(cTCacheField, CACHEFIELD$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCacheField insertNewCacheField(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCacheField cTCacheField = null;
            cTCacheField = (CTCacheField)((Object)this.get_store().insert_element_user(CACHEFIELD$0, n));
            return cTCacheField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCacheField addNewCacheField() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCacheField cTCacheField = null;
            cTCacheField = (CTCacheField)((Object)this.get_store().add_element_user(CACHEFIELD$0));
            return cTCacheField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCacheField(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CACHEFIELD$0, n);
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

