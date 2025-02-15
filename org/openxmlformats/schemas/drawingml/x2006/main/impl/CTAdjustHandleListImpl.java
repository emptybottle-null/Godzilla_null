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
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

public class CTAdjustHandleListImpl
extends XmlComplexContentImpl
implements CTAdjustHandleList {
    private static final long serialVersionUID = 1L;
    private static final QName AHXY$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "ahXY");
    private static final QName AHPOLAR$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "ahPolar");

    public CTAdjustHandleListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTXYAdjustHandle> getAhXYList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AhXYList
            extends AbstractList<CTXYAdjustHandle> {
                AhXYList() {
                }

                @Override
                public CTXYAdjustHandle get(int n) {
                    return CTAdjustHandleListImpl.this.getAhXYArray(n);
                }

                @Override
                public CTXYAdjustHandle set(int n, CTXYAdjustHandle cTXYAdjustHandle) {
                    CTXYAdjustHandle cTXYAdjustHandle2 = CTAdjustHandleListImpl.this.getAhXYArray(n);
                    CTAdjustHandleListImpl.this.setAhXYArray(n, cTXYAdjustHandle);
                    return cTXYAdjustHandle2;
                }

                @Override
                public void add(int n, CTXYAdjustHandle cTXYAdjustHandle) {
                    CTAdjustHandleListImpl.this.insertNewAhXY(n).set(cTXYAdjustHandle);
                }

                @Override
                public CTXYAdjustHandle remove(int n) {
                    CTXYAdjustHandle cTXYAdjustHandle = CTAdjustHandleListImpl.this.getAhXYArray(n);
                    CTAdjustHandleListImpl.this.removeAhXY(n);
                    return cTXYAdjustHandle;
                }

                @Override
                public int size() {
                    return CTAdjustHandleListImpl.this.sizeOfAhXYArray();
                }
            }
            return new AhXYList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTXYAdjustHandle[] getAhXYArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AHXY$0, arrayList);
            CTXYAdjustHandle[] cTXYAdjustHandleArray = new CTXYAdjustHandle[arrayList.size()];
            arrayList.toArray(cTXYAdjustHandleArray);
            return cTXYAdjustHandleArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTXYAdjustHandle getAhXYArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTXYAdjustHandle cTXYAdjustHandle = null;
            cTXYAdjustHandle = (CTXYAdjustHandle)((Object)this.get_store().find_element_user(AHXY$0, n));
            if (cTXYAdjustHandle == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTXYAdjustHandle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAhXYArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AHXY$0);
        }
    }

    @Override
    public void setAhXYArray(CTXYAdjustHandle[] cTXYAdjustHandleArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTXYAdjustHandleArray, AHXY$0);
    }

    @Override
    public void setAhXYArray(int n, CTXYAdjustHandle cTXYAdjustHandle) {
        this.generatedSetterHelperImpl(cTXYAdjustHandle, AHXY$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTXYAdjustHandle insertNewAhXY(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTXYAdjustHandle cTXYAdjustHandle = null;
            cTXYAdjustHandle = (CTXYAdjustHandle)((Object)this.get_store().insert_element_user(AHXY$0, n));
            return cTXYAdjustHandle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTXYAdjustHandle addNewAhXY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTXYAdjustHandle cTXYAdjustHandle = null;
            cTXYAdjustHandle = (CTXYAdjustHandle)((Object)this.get_store().add_element_user(AHXY$0));
            return cTXYAdjustHandle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAhXY(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AHXY$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPolarAdjustHandle> getAhPolarList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AhPolarList
            extends AbstractList<CTPolarAdjustHandle> {
                AhPolarList() {
                }

                @Override
                public CTPolarAdjustHandle get(int n) {
                    return CTAdjustHandleListImpl.this.getAhPolarArray(n);
                }

                @Override
                public CTPolarAdjustHandle set(int n, CTPolarAdjustHandle cTPolarAdjustHandle) {
                    CTPolarAdjustHandle cTPolarAdjustHandle2 = CTAdjustHandleListImpl.this.getAhPolarArray(n);
                    CTAdjustHandleListImpl.this.setAhPolarArray(n, cTPolarAdjustHandle);
                    return cTPolarAdjustHandle2;
                }

                @Override
                public void add(int n, CTPolarAdjustHandle cTPolarAdjustHandle) {
                    CTAdjustHandleListImpl.this.insertNewAhPolar(n).set(cTPolarAdjustHandle);
                }

                @Override
                public CTPolarAdjustHandle remove(int n) {
                    CTPolarAdjustHandle cTPolarAdjustHandle = CTAdjustHandleListImpl.this.getAhPolarArray(n);
                    CTAdjustHandleListImpl.this.removeAhPolar(n);
                    return cTPolarAdjustHandle;
                }

                @Override
                public int size() {
                    return CTAdjustHandleListImpl.this.sizeOfAhPolarArray();
                }
            }
            return new AhPolarList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPolarAdjustHandle[] getAhPolarArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AHPOLAR$2, arrayList);
            CTPolarAdjustHandle[] cTPolarAdjustHandleArray = new CTPolarAdjustHandle[arrayList.size()];
            arrayList.toArray(cTPolarAdjustHandleArray);
            return cTPolarAdjustHandleArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPolarAdjustHandle getAhPolarArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPolarAdjustHandle cTPolarAdjustHandle = null;
            cTPolarAdjustHandle = (CTPolarAdjustHandle)((Object)this.get_store().find_element_user(AHPOLAR$2, n));
            if (cTPolarAdjustHandle == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPolarAdjustHandle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAhPolarArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AHPOLAR$2);
        }
    }

    @Override
    public void setAhPolarArray(CTPolarAdjustHandle[] cTPolarAdjustHandleArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPolarAdjustHandleArray, AHPOLAR$2);
    }

    @Override
    public void setAhPolarArray(int n, CTPolarAdjustHandle cTPolarAdjustHandle) {
        this.generatedSetterHelperImpl(cTPolarAdjustHandle, AHPOLAR$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPolarAdjustHandle insertNewAhPolar(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPolarAdjustHandle cTPolarAdjustHandle = null;
            cTPolarAdjustHandle = (CTPolarAdjustHandle)((Object)this.get_store().insert_element_user(AHPOLAR$2, n));
            return cTPolarAdjustHandle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPolarAdjustHandle addNewAhPolar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPolarAdjustHandle cTPolarAdjustHandle = null;
            cTPolarAdjustHandle = (CTPolarAdjustHandle)((Object)this.get_store().add_element_user(AHPOLAR$2));
            return cTPolarAdjustHandle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAhPolar(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AHPOLAR$2, n);
        }
    }
}

