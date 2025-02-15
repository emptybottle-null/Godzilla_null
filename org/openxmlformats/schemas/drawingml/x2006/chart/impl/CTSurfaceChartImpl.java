/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTBandFmts
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBandFmts;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTSurfaceChartImpl
extends XmlComplexContentImpl
implements CTSurfaceChart {
    private static final long serialVersionUID = 1L;
    private static final QName WIREFRAME$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "wireframe");
    private static final QName SER$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "ser");
    private static final QName BANDFMTS$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "bandFmts");
    private static final QName AXID$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "axId");
    private static final QName EXTLST$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTSurfaceChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getWireframe() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(WIREFRAME$0, 0));
            if (cTBoolean == null) {
                return null;
            }
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWireframe() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WIREFRAME$0) != 0;
        }
    }

    @Override
    public void setWireframe(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, WIREFRAME$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewWireframe() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(WIREFRAME$0));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWireframe() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WIREFRAME$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSurfaceSer> getSerList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SerList
            extends AbstractList<CTSurfaceSer> {
                SerList() {
                }

                @Override
                public CTSurfaceSer get(int n) {
                    return CTSurfaceChartImpl.this.getSerArray(n);
                }

                @Override
                public CTSurfaceSer set(int n, CTSurfaceSer cTSurfaceSer) {
                    CTSurfaceSer cTSurfaceSer2 = CTSurfaceChartImpl.this.getSerArray(n);
                    CTSurfaceChartImpl.this.setSerArray(n, cTSurfaceSer);
                    return cTSurfaceSer2;
                }

                @Override
                public void add(int n, CTSurfaceSer cTSurfaceSer) {
                    CTSurfaceChartImpl.this.insertNewSer(n).set(cTSurfaceSer);
                }

                @Override
                public CTSurfaceSer remove(int n) {
                    CTSurfaceSer cTSurfaceSer = CTSurfaceChartImpl.this.getSerArray(n);
                    CTSurfaceChartImpl.this.removeSer(n);
                    return cTSurfaceSer;
                }

                @Override
                public int size() {
                    return CTSurfaceChartImpl.this.sizeOfSerArray();
                }
            }
            return new SerList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSurfaceSer[] getSerArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SER$2, arrayList);
            CTSurfaceSer[] cTSurfaceSerArray = new CTSurfaceSer[arrayList.size()];
            arrayList.toArray(cTSurfaceSerArray);
            return cTSurfaceSerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurfaceSer getSerArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurfaceSer cTSurfaceSer = null;
            cTSurfaceSer = (CTSurfaceSer)((Object)this.get_store().find_element_user(SER$2, n));
            if (cTSurfaceSer == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSurfaceSer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSerArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SER$2);
        }
    }

    @Override
    public void setSerArray(CTSurfaceSer[] cTSurfaceSerArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSurfaceSerArray, SER$2);
    }

    @Override
    public void setSerArray(int n, CTSurfaceSer cTSurfaceSer) {
        this.generatedSetterHelperImpl(cTSurfaceSer, SER$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurfaceSer insertNewSer(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurfaceSer cTSurfaceSer = null;
            cTSurfaceSer = (CTSurfaceSer)((Object)this.get_store().insert_element_user(SER$2, n));
            return cTSurfaceSer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurfaceSer addNewSer() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurfaceSer cTSurfaceSer = null;
            cTSurfaceSer = (CTSurfaceSer)((Object)this.get_store().add_element_user(SER$2));
            return cTSurfaceSer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSer(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SER$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBandFmts getBandFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBandFmts cTBandFmts = null;
            cTBandFmts = (CTBandFmts)this.get_store().find_element_user(BANDFMTS$4, 0);
            if (cTBandFmts == null) {
                return null;
            }
            return cTBandFmts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBandFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BANDFMTS$4) != 0;
        }
    }

    @Override
    public void setBandFmts(CTBandFmts cTBandFmts) {
        this.generatedSetterHelperImpl((XmlObject)cTBandFmts, BANDFMTS$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBandFmts addNewBandFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBandFmts cTBandFmts = null;
            cTBandFmts = (CTBandFmts)this.get_store().add_element_user(BANDFMTS$4);
            return cTBandFmts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBandFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BANDFMTS$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTUnsignedInt> getAxIdList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AxIdList
            extends AbstractList<CTUnsignedInt> {
                AxIdList() {
                }

                @Override
                public CTUnsignedInt get(int n) {
                    return CTSurfaceChartImpl.this.getAxIdArray(n);
                }

                @Override
                public CTUnsignedInt set(int n, CTUnsignedInt cTUnsignedInt) {
                    CTUnsignedInt cTUnsignedInt2 = CTSurfaceChartImpl.this.getAxIdArray(n);
                    CTSurfaceChartImpl.this.setAxIdArray(n, cTUnsignedInt);
                    return cTUnsignedInt2;
                }

                @Override
                public void add(int n, CTUnsignedInt cTUnsignedInt) {
                    CTSurfaceChartImpl.this.insertNewAxId(n).set(cTUnsignedInt);
                }

                @Override
                public CTUnsignedInt remove(int n) {
                    CTUnsignedInt cTUnsignedInt = CTSurfaceChartImpl.this.getAxIdArray(n);
                    CTSurfaceChartImpl.this.removeAxId(n);
                    return cTUnsignedInt;
                }

                @Override
                public int size() {
                    return CTSurfaceChartImpl.this.sizeOfAxIdArray();
                }
            }
            return new AxIdList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTUnsignedInt[] getAxIdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AXID$6, arrayList);
            CTUnsignedInt[] cTUnsignedIntArray = new CTUnsignedInt[arrayList.size()];
            arrayList.toArray(cTUnsignedIntArray);
            return cTUnsignedIntArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt getAxIdArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(AXID$6, n));
            if (cTUnsignedInt == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAxIdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AXID$6);
        }
    }

    @Override
    public void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTUnsignedIntArray, AXID$6);
    }

    @Override
    public void setAxIdArray(int n, CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, AXID$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt insertNewAxId(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().insert_element_user(AXID$6, n));
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt addNewAxId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(AXID$6));
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAxId(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AXID$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$8, 0));
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$8) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$8));
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$8, 0);
        }
    }
}

