/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTRadarChartImpl
extends XmlComplexContentImpl
implements CTRadarChart {
    private static final long serialVersionUID = 1L;
    private static final QName RADARSTYLE$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "radarStyle");
    private static final QName VARYCOLORS$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "varyColors");
    private static final QName SER$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "ser");
    private static final QName DLBLS$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dLbls");
    private static final QName AXID$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "axId");
    private static final QName EXTLST$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTRadarChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRadarStyle getRadarStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRadarStyle cTRadarStyle = null;
            cTRadarStyle = (CTRadarStyle)((Object)this.get_store().find_element_user(RADARSTYLE$0, 0));
            if (cTRadarStyle == null) {
                return null;
            }
            return cTRadarStyle;
        }
    }

    @Override
    public void setRadarStyle(CTRadarStyle cTRadarStyle) {
        this.generatedSetterHelperImpl(cTRadarStyle, RADARSTYLE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRadarStyle addNewRadarStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRadarStyle cTRadarStyle = null;
            cTRadarStyle = (CTRadarStyle)((Object)this.get_store().add_element_user(RADARSTYLE$0));
            return cTRadarStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getVaryColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(VARYCOLORS$2, 0));
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
    public boolean isSetVaryColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VARYCOLORS$2) != 0;
        }
    }

    @Override
    public void setVaryColors(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, VARYCOLORS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewVaryColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(VARYCOLORS$2));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVaryColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VARYCOLORS$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRadarSer> getSerList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SerList
            extends AbstractList<CTRadarSer> {
                SerList() {
                }

                @Override
                public CTRadarSer get(int n) {
                    return CTRadarChartImpl.this.getSerArray(n);
                }

                @Override
                public CTRadarSer set(int n, CTRadarSer cTRadarSer) {
                    CTRadarSer cTRadarSer2 = CTRadarChartImpl.this.getSerArray(n);
                    CTRadarChartImpl.this.setSerArray(n, cTRadarSer);
                    return cTRadarSer2;
                }

                @Override
                public void add(int n, CTRadarSer cTRadarSer) {
                    CTRadarChartImpl.this.insertNewSer(n).set(cTRadarSer);
                }

                @Override
                public CTRadarSer remove(int n) {
                    CTRadarSer cTRadarSer = CTRadarChartImpl.this.getSerArray(n);
                    CTRadarChartImpl.this.removeSer(n);
                    return cTRadarSer;
                }

                @Override
                public int size() {
                    return CTRadarChartImpl.this.sizeOfSerArray();
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
    public CTRadarSer[] getSerArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SER$4, arrayList);
            CTRadarSer[] cTRadarSerArray = new CTRadarSer[arrayList.size()];
            arrayList.toArray(cTRadarSerArray);
            return cTRadarSerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRadarSer getSerArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRadarSer cTRadarSer = null;
            cTRadarSer = (CTRadarSer)((Object)this.get_store().find_element_user(SER$4, n));
            if (cTRadarSer == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRadarSer;
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
            return this.get_store().count_elements(SER$4);
        }
    }

    @Override
    public void setSerArray(CTRadarSer[] cTRadarSerArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRadarSerArray, SER$4);
    }

    @Override
    public void setSerArray(int n, CTRadarSer cTRadarSer) {
        this.generatedSetterHelperImpl(cTRadarSer, SER$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRadarSer insertNewSer(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRadarSer cTRadarSer = null;
            cTRadarSer = (CTRadarSer)((Object)this.get_store().insert_element_user(SER$4, n));
            return cTRadarSer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRadarSer addNewSer() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRadarSer cTRadarSer = null;
            cTRadarSer = (CTRadarSer)((Object)this.get_store().add_element_user(SER$4));
            return cTRadarSer;
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
            this.get_store().remove_element(SER$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDLbls getDLbls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDLbls cTDLbls = null;
            cTDLbls = (CTDLbls)((Object)this.get_store().find_element_user(DLBLS$6, 0));
            if (cTDLbls == null) {
                return null;
            }
            return cTDLbls;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDLbls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DLBLS$6) != 0;
        }
    }

    @Override
    public void setDLbls(CTDLbls cTDLbls) {
        this.generatedSetterHelperImpl(cTDLbls, DLBLS$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDLbls addNewDLbls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDLbls cTDLbls = null;
            cTDLbls = (CTDLbls)((Object)this.get_store().add_element_user(DLBLS$6));
            return cTDLbls;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDLbls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DLBLS$6, 0);
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
                    return CTRadarChartImpl.this.getAxIdArray(n);
                }

                @Override
                public CTUnsignedInt set(int n, CTUnsignedInt cTUnsignedInt) {
                    CTUnsignedInt cTUnsignedInt2 = CTRadarChartImpl.this.getAxIdArray(n);
                    CTRadarChartImpl.this.setAxIdArray(n, cTUnsignedInt);
                    return cTUnsignedInt2;
                }

                @Override
                public void add(int n, CTUnsignedInt cTUnsignedInt) {
                    CTRadarChartImpl.this.insertNewAxId(n).set(cTUnsignedInt);
                }

                @Override
                public CTUnsignedInt remove(int n) {
                    CTUnsignedInt cTUnsignedInt = CTRadarChartImpl.this.getAxIdArray(n);
                    CTRadarChartImpl.this.removeAxId(n);
                    return cTUnsignedInt;
                }

                @Override
                public int size() {
                    return CTRadarChartImpl.this.sizeOfAxIdArray();
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
            this.get_store().find_all_element_users(AXID$8, arrayList);
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(AXID$8, n));
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
            return this.get_store().count_elements(AXID$8);
        }
    }

    @Override
    public void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTUnsignedIntArray, AXID$8);
    }

    @Override
    public void setAxIdArray(int n, CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, AXID$8, n, (short)2);
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().insert_element_user(AXID$8, n));
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(AXID$8));
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
            this.get_store().remove_element(AXID$8, n);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$10, 0));
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
            return this.get_store().count_elements(EXTLST$10) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$10, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$10));
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
            this.get_store().remove_element(EXTLST$10, 0);
        }
    }
}

