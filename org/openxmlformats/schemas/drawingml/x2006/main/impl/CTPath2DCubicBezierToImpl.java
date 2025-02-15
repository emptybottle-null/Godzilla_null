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
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;

public class CTPath2DCubicBezierToImpl
extends XmlComplexContentImpl
implements CTPath2DCubicBezierTo {
    private static final long serialVersionUID = 1L;
    private static final QName PT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pt");

    public CTPath2DCubicBezierToImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAdjPoint2D> getPtList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PtList
            extends AbstractList<CTAdjPoint2D> {
                PtList() {
                }

                @Override
                public CTAdjPoint2D get(int n) {
                    return CTPath2DCubicBezierToImpl.this.getPtArray(n);
                }

                @Override
                public CTAdjPoint2D set(int n, CTAdjPoint2D cTAdjPoint2D) {
                    CTAdjPoint2D cTAdjPoint2D2 = CTPath2DCubicBezierToImpl.this.getPtArray(n);
                    CTPath2DCubicBezierToImpl.this.setPtArray(n, cTAdjPoint2D);
                    return cTAdjPoint2D2;
                }

                @Override
                public void add(int n, CTAdjPoint2D cTAdjPoint2D) {
                    CTPath2DCubicBezierToImpl.this.insertNewPt(n).set(cTAdjPoint2D);
                }

                @Override
                public CTAdjPoint2D remove(int n) {
                    CTAdjPoint2D cTAdjPoint2D = CTPath2DCubicBezierToImpl.this.getPtArray(n);
                    CTPath2DCubicBezierToImpl.this.removePt(n);
                    return cTAdjPoint2D;
                }

                @Override
                public int size() {
                    return CTPath2DCubicBezierToImpl.this.sizeOfPtArray();
                }
            }
            return new PtList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAdjPoint2D[] getPtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PT$0, arrayList);
            CTAdjPoint2D[] cTAdjPoint2DArray = new CTAdjPoint2D[arrayList.size()];
            arrayList.toArray(cTAdjPoint2DArray);
            return cTAdjPoint2DArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjPoint2D getPtArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjPoint2D cTAdjPoint2D = null;
            cTAdjPoint2D = (CTAdjPoint2D)((Object)this.get_store().find_element_user(PT$0, n));
            if (cTAdjPoint2D == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAdjPoint2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PT$0);
        }
    }

    @Override
    public void setPtArray(CTAdjPoint2D[] cTAdjPoint2DArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTAdjPoint2DArray, PT$0);
    }

    @Override
    public void setPtArray(int n, CTAdjPoint2D cTAdjPoint2D) {
        this.generatedSetterHelperImpl(cTAdjPoint2D, PT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjPoint2D insertNewPt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjPoint2D cTAdjPoint2D = null;
            cTAdjPoint2D = (CTAdjPoint2D)((Object)this.get_store().insert_element_user(PT$0, n));
            return cTAdjPoint2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjPoint2D addNewPt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjPoint2D cTAdjPoint2D = null;
            cTAdjPoint2D = (CTAdjPoint2D)((Object)this.get_store().add_element_user(PT$0));
            return cTAdjPoint2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PT$0, n);
        }
    }
}

