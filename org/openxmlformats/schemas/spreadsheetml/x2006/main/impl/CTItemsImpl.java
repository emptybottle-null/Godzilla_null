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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTItem;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTItems;

public class CTItemsImpl
extends XmlComplexContentImpl
implements CTItems {
    private static final long serialVersionUID = 1L;
    private static final QName ITEM$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "item");
    private static final QName COUNT$2 = new QName("", "count");

    public CTItemsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTItem> getItemList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ItemList
            extends AbstractList<CTItem> {
                ItemList() {
                }

                @Override
                public CTItem get(int n) {
                    return CTItemsImpl.this.getItemArray(n);
                }

                @Override
                public CTItem set(int n, CTItem cTItem) {
                    CTItem cTItem2 = CTItemsImpl.this.getItemArray(n);
                    CTItemsImpl.this.setItemArray(n, cTItem);
                    return cTItem2;
                }

                @Override
                public void add(int n, CTItem cTItem) {
                    CTItemsImpl.this.insertNewItem(n).set(cTItem);
                }

                @Override
                public CTItem remove(int n) {
                    CTItem cTItem = CTItemsImpl.this.getItemArray(n);
                    CTItemsImpl.this.removeItem(n);
                    return cTItem;
                }

                @Override
                public int size() {
                    return CTItemsImpl.this.sizeOfItemArray();
                }
            }
            return new ItemList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTItem[] getItemArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ITEM$0, arrayList);
            CTItem[] cTItemArray = new CTItem[arrayList.size()];
            arrayList.toArray(cTItemArray);
            return cTItemArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTItem getItemArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTItem cTItem = null;
            cTItem = (CTItem)((Object)this.get_store().find_element_user(ITEM$0, n));
            if (cTItem == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTItem;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfItemArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ITEM$0);
        }
    }

    @Override
    public void setItemArray(CTItem[] cTItemArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTItemArray, ITEM$0);
    }

    @Override
    public void setItemArray(int n, CTItem cTItem) {
        this.generatedSetterHelperImpl(cTItem, ITEM$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTItem insertNewItem(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTItem cTItem = null;
            cTItem = (CTItem)((Object)this.get_store().insert_element_user(ITEM$0, n));
            return cTItem;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTItem addNewItem() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTItem cTItem = null;
            cTItem = (CTItem)((Object)this.get_store().add_element_user(ITEM$0));
            return cTItem;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeItem(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ITEM$0, n);
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

