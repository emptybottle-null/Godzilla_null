/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAttr
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAttr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagPr;

public class CTSmartTagPrImpl
extends XmlComplexContentImpl
implements CTSmartTagPr {
    private static final long serialVersionUID = 1L;
    private static final QName ATTR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "attr");

    public CTSmartTagPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAttr> getAttrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AttrList
            extends AbstractList<CTAttr> {
                AttrList() {
                }

                @Override
                public CTAttr get(int n) {
                    return CTSmartTagPrImpl.this.getAttrArray(n);
                }

                @Override
                public CTAttr set(int n, CTAttr cTAttr) {
                    CTAttr cTAttr2 = CTSmartTagPrImpl.this.getAttrArray(n);
                    CTSmartTagPrImpl.this.setAttrArray(n, cTAttr);
                    return cTAttr2;
                }

                @Override
                public void add(int n, CTAttr cTAttr) {
                    CTSmartTagPrImpl.this.insertNewAttr(n).set((XmlObject)cTAttr);
                }

                @Override
                public CTAttr remove(int n) {
                    CTAttr cTAttr = CTSmartTagPrImpl.this.getAttrArray(n);
                    CTSmartTagPrImpl.this.removeAttr(n);
                    return cTAttr;
                }

                @Override
                public int size() {
                    return CTSmartTagPrImpl.this.sizeOfAttrArray();
                }
            }
            return new AttrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAttr[] getAttrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ATTR$0, arrayList);
            CTAttr[] cTAttrArray = new CTAttr[arrayList.size()];
            arrayList.toArray(cTAttrArray);
            return cTAttrArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAttr getAttrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAttr cTAttr = null;
            cTAttr = (CTAttr)this.get_store().find_element_user(ATTR$0, n);
            if (cTAttr == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAttr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAttrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTR$0);
        }
    }

    @Override
    public void setAttrArray(CTAttr[] cTAttrArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAttrArray, ATTR$0);
    }

    @Override
    public void setAttrArray(int n, CTAttr cTAttr) {
        this.generatedSetterHelperImpl((XmlObject)cTAttr, ATTR$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAttr insertNewAttr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAttr cTAttr = null;
            cTAttr = (CTAttr)this.get_store().insert_element_user(ATTR$0, n);
            return cTAttr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAttr addNewAttr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAttr cTAttr = null;
            cTAttr = (CTAttr)this.get_store().add_element_user(ATTR$0);
            return cTAttr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAttr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTR$0, n);
        }
    }
}

