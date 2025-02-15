/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList;

public class CTEffectStyleListImpl
extends XmlComplexContentImpl
implements CTEffectStyleList {
    private static final long serialVersionUID = 1L;
    private static final QName EFFECTSTYLE$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "effectStyle");

    public CTEffectStyleListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEffectStyleItem> getEffectStyleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EffectStyleList
            extends AbstractList<CTEffectStyleItem> {
                EffectStyleList() {
                }

                @Override
                public CTEffectStyleItem get(int n) {
                    return CTEffectStyleListImpl.this.getEffectStyleArray(n);
                }

                @Override
                public CTEffectStyleItem set(int n, CTEffectStyleItem cTEffectStyleItem) {
                    CTEffectStyleItem cTEffectStyleItem2 = CTEffectStyleListImpl.this.getEffectStyleArray(n);
                    CTEffectStyleListImpl.this.setEffectStyleArray(n, cTEffectStyleItem);
                    return cTEffectStyleItem2;
                }

                @Override
                public void add(int n, CTEffectStyleItem cTEffectStyleItem) {
                    CTEffectStyleListImpl.this.insertNewEffectStyle(n).set(cTEffectStyleItem);
                }

                @Override
                public CTEffectStyleItem remove(int n) {
                    CTEffectStyleItem cTEffectStyleItem = CTEffectStyleListImpl.this.getEffectStyleArray(n);
                    CTEffectStyleListImpl.this.removeEffectStyle(n);
                    return cTEffectStyleItem;
                }

                @Override
                public int size() {
                    return CTEffectStyleListImpl.this.sizeOfEffectStyleArray();
                }
            }
            return new EffectStyleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEffectStyleItem[] getEffectStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EFFECTSTYLE$0, arrayList);
            CTEffectStyleItem[] cTEffectStyleItemArray = new CTEffectStyleItem[arrayList.size()];
            arrayList.toArray(cTEffectStyleItemArray);
            return cTEffectStyleItemArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectStyleItem getEffectStyleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectStyleItem cTEffectStyleItem = null;
            cTEffectStyleItem = (CTEffectStyleItem)((Object)this.get_store().find_element_user(EFFECTSTYLE$0, n));
            if (cTEffectStyleItem == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEffectStyleItem;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfEffectStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EFFECTSTYLE$0);
        }
    }

    @Override
    public void setEffectStyleArray(CTEffectStyleItem[] cTEffectStyleItemArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEffectStyleItemArray, EFFECTSTYLE$0);
    }

    @Override
    public void setEffectStyleArray(int n, CTEffectStyleItem cTEffectStyleItem) {
        this.generatedSetterHelperImpl(cTEffectStyleItem, EFFECTSTYLE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectStyleItem insertNewEffectStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectStyleItem cTEffectStyleItem = null;
            cTEffectStyleItem = (CTEffectStyleItem)((Object)this.get_store().insert_element_user(EFFECTSTYLE$0, n));
            return cTEffectStyleItem;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectStyleItem addNewEffectStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectStyleItem cTEffectStyleItem = null;
            cTEffectStyleItem = (CTEffectStyleItem)((Object)this.get_store().add_element_user(EFFECTSTYLE$0));
            return cTEffectStyleItem;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEffectStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EFFECTSTYLE$0, n);
        }
    }
}

