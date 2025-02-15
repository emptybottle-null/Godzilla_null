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
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList;

public class CTGeomGuideListImpl
extends XmlComplexContentImpl
implements CTGeomGuideList {
    private static final long serialVersionUID = 1L;
    private static final QName GD$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gd");

    public CTGeomGuideListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGeomGuide> getGdList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GdList
            extends AbstractList<CTGeomGuide> {
                GdList() {
                }

                @Override
                public CTGeomGuide get(int n) {
                    return CTGeomGuideListImpl.this.getGdArray(n);
                }

                @Override
                public CTGeomGuide set(int n, CTGeomGuide cTGeomGuide) {
                    CTGeomGuide cTGeomGuide2 = CTGeomGuideListImpl.this.getGdArray(n);
                    CTGeomGuideListImpl.this.setGdArray(n, cTGeomGuide);
                    return cTGeomGuide2;
                }

                @Override
                public void add(int n, CTGeomGuide cTGeomGuide) {
                    CTGeomGuideListImpl.this.insertNewGd(n).set(cTGeomGuide);
                }

                @Override
                public CTGeomGuide remove(int n) {
                    CTGeomGuide cTGeomGuide = CTGeomGuideListImpl.this.getGdArray(n);
                    CTGeomGuideListImpl.this.removeGd(n);
                    return cTGeomGuide;
                }

                @Override
                public int size() {
                    return CTGeomGuideListImpl.this.sizeOfGdArray();
                }
            }
            return new GdList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGeomGuide[] getGdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GD$0, arrayList);
            CTGeomGuide[] cTGeomGuideArray = new CTGeomGuide[arrayList.size()];
            arrayList.toArray(cTGeomGuideArray);
            return cTGeomGuideArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomGuide getGdArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomGuide cTGeomGuide = null;
            cTGeomGuide = (CTGeomGuide)((Object)this.get_store().find_element_user(GD$0, n));
            if (cTGeomGuide == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGeomGuide;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GD$0);
        }
    }

    @Override
    public void setGdArray(CTGeomGuide[] cTGeomGuideArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTGeomGuideArray, GD$0);
    }

    @Override
    public void setGdArray(int n, CTGeomGuide cTGeomGuide) {
        this.generatedSetterHelperImpl(cTGeomGuide, GD$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomGuide insertNewGd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomGuide cTGeomGuide = null;
            cTGeomGuide = (CTGeomGuide)((Object)this.get_store().insert_element_user(GD$0, n));
            return cTGeomGuide;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomGuide addNewGd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomGuide cTGeomGuide = null;
            cTGeomGuide = (CTGeomGuide)((Object)this.get_store().add_element_user(GD$0));
            return cTGeomGuide;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GD$0, n);
        }
    }
}

