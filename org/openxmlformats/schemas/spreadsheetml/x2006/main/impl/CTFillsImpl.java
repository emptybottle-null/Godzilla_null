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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFills;

public class CTFillsImpl
extends XmlComplexContentImpl
implements CTFills {
    private static final long serialVersionUID = 1L;
    private static final QName FILL$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "fill");
    private static final QName COUNT$2 = new QName("", "count");

    public CTFillsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFill> getFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FillList
            extends AbstractList<CTFill> {
                FillList() {
                }

                @Override
                public CTFill get(int n) {
                    return CTFillsImpl.this.getFillArray(n);
                }

                @Override
                public CTFill set(int n, CTFill cTFill) {
                    CTFill cTFill2 = CTFillsImpl.this.getFillArray(n);
                    CTFillsImpl.this.setFillArray(n, cTFill);
                    return cTFill2;
                }

                @Override
                public void add(int n, CTFill cTFill) {
                    CTFillsImpl.this.insertNewFill(n).set(cTFill);
                }

                @Override
                public CTFill remove(int n) {
                    CTFill cTFill = CTFillsImpl.this.getFillArray(n);
                    CTFillsImpl.this.removeFill(n);
                    return cTFill;
                }

                @Override
                public int size() {
                    return CTFillsImpl.this.sizeOfFillArray();
                }
            }
            return new FillList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFill[] getFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FILL$0, arrayList);
            CTFill[] cTFillArray = new CTFill[arrayList.size()];
            arrayList.toArray(cTFillArray);
            return cTFillArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFill getFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFill cTFill = null;
            cTFill = (CTFill)((Object)this.get_store().find_element_user(FILL$0, n));
            if (cTFill == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILL$0);
        }
    }

    @Override
    public void setFillArray(CTFill[] cTFillArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFillArray, FILL$0);
    }

    @Override
    public void setFillArray(int n, CTFill cTFill) {
        this.generatedSetterHelperImpl(cTFill, FILL$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFill insertNewFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFill cTFill = null;
            cTFill = (CTFill)((Object)this.get_store().insert_element_user(FILL$0, n));
            return cTFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFill addNewFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFill cTFill = null;
            cTFill = (CTFill)((Object)this.get_store().add_element_user(FILL$0));
            return cTFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILL$0, n);
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

