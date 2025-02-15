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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTArea3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTArea3DChartImpl
extends XmlComplexContentImpl
implements CTArea3DChart {
    private static final long serialVersionUID = 1L;
    private static final QName GROUPING$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "grouping");
    private static final QName VARYCOLORS$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "varyColors");
    private static final QName SER$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "ser");
    private static final QName DLBLS$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dLbls");
    private static final QName DROPLINES$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dropLines");
    private static final QName GAPDEPTH$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "gapDepth");
    private static final QName AXID$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "axId");
    private static final QName EXTLST$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTArea3DChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGrouping getGrouping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGrouping cTGrouping = null;
            cTGrouping = (CTGrouping)((Object)this.get_store().find_element_user(GROUPING$0, 0));
            if (cTGrouping == null) {
                return null;
            }
            return cTGrouping;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGrouping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GROUPING$0) != 0;
        }
    }

    @Override
    public void setGrouping(CTGrouping cTGrouping) {
        this.generatedSetterHelperImpl(cTGrouping, GROUPING$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGrouping addNewGrouping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGrouping cTGrouping = null;
            cTGrouping = (CTGrouping)((Object)this.get_store().add_element_user(GROUPING$0));
            return cTGrouping;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGrouping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GROUPING$0, 0);
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
    public List<CTAreaSer> getSerList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SerList
            extends AbstractList<CTAreaSer> {
                SerList() {
                }

                @Override
                public CTAreaSer get(int n) {
                    return CTArea3DChartImpl.this.getSerArray(n);
                }

                @Override
                public CTAreaSer set(int n, CTAreaSer cTAreaSer) {
                    CTAreaSer cTAreaSer2 = CTArea3DChartImpl.this.getSerArray(n);
                    CTArea3DChartImpl.this.setSerArray(n, cTAreaSer);
                    return cTAreaSer2;
                }

                @Override
                public void add(int n, CTAreaSer cTAreaSer) {
                    CTArea3DChartImpl.this.insertNewSer(n).set(cTAreaSer);
                }

                @Override
                public CTAreaSer remove(int n) {
                    CTAreaSer cTAreaSer = CTArea3DChartImpl.this.getSerArray(n);
                    CTArea3DChartImpl.this.removeSer(n);
                    return cTAreaSer;
                }

                @Override
                public int size() {
                    return CTArea3DChartImpl.this.sizeOfSerArray();
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
    public CTAreaSer[] getSerArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SER$4, arrayList);
            CTAreaSer[] cTAreaSerArray = new CTAreaSer[arrayList.size()];
            arrayList.toArray(cTAreaSerArray);
            return cTAreaSerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAreaSer getSerArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAreaSer cTAreaSer = null;
            cTAreaSer = (CTAreaSer)((Object)this.get_store().find_element_user(SER$4, n));
            if (cTAreaSer == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAreaSer;
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
    public void setSerArray(CTAreaSer[] cTAreaSerArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTAreaSerArray, SER$4);
    }

    @Override
    public void setSerArray(int n, CTAreaSer cTAreaSer) {
        this.generatedSetterHelperImpl(cTAreaSer, SER$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAreaSer insertNewSer(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAreaSer cTAreaSer = null;
            cTAreaSer = (CTAreaSer)((Object)this.get_store().insert_element_user(SER$4, n));
            return cTAreaSer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAreaSer addNewSer() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAreaSer cTAreaSer = null;
            cTAreaSer = (CTAreaSer)((Object)this.get_store().add_element_user(SER$4));
            return cTAreaSer;
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
    public CTChartLines getDropLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().find_element_user(DROPLINES$8, 0));
            if (cTChartLines == null) {
                return null;
            }
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDropLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DROPLINES$8) != 0;
        }
    }

    @Override
    public void setDropLines(CTChartLines cTChartLines) {
        this.generatedSetterHelperImpl(cTChartLines, DROPLINES$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines addNewDropLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().add_element_user(DROPLINES$8));
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDropLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DROPLINES$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGapAmount getGapDepth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGapAmount cTGapAmount = null;
            cTGapAmount = (CTGapAmount)((Object)this.get_store().find_element_user(GAPDEPTH$10, 0));
            if (cTGapAmount == null) {
                return null;
            }
            return cTGapAmount;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGapDepth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GAPDEPTH$10) != 0;
        }
    }

    @Override
    public void setGapDepth(CTGapAmount cTGapAmount) {
        this.generatedSetterHelperImpl(cTGapAmount, GAPDEPTH$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGapAmount addNewGapDepth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGapAmount cTGapAmount = null;
            cTGapAmount = (CTGapAmount)((Object)this.get_store().add_element_user(GAPDEPTH$10));
            return cTGapAmount;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGapDepth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GAPDEPTH$10, 0);
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
                    return CTArea3DChartImpl.this.getAxIdArray(n);
                }

                @Override
                public CTUnsignedInt set(int n, CTUnsignedInt cTUnsignedInt) {
                    CTUnsignedInt cTUnsignedInt2 = CTArea3DChartImpl.this.getAxIdArray(n);
                    CTArea3DChartImpl.this.setAxIdArray(n, cTUnsignedInt);
                    return cTUnsignedInt2;
                }

                @Override
                public void add(int n, CTUnsignedInt cTUnsignedInt) {
                    CTArea3DChartImpl.this.insertNewAxId(n).set(cTUnsignedInt);
                }

                @Override
                public CTUnsignedInt remove(int n) {
                    CTUnsignedInt cTUnsignedInt = CTArea3DChartImpl.this.getAxIdArray(n);
                    CTArea3DChartImpl.this.removeAxId(n);
                    return cTUnsignedInt;
                }

                @Override
                public int size() {
                    return CTArea3DChartImpl.this.sizeOfAxIdArray();
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
            this.get_store().find_all_element_users(AXID$12, arrayList);
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(AXID$12, n));
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
            return this.get_store().count_elements(AXID$12);
        }
    }

    @Override
    public void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTUnsignedIntArray, AXID$12);
    }

    @Override
    public void setAxIdArray(int n, CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, AXID$12, n, (short)2);
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().insert_element_user(AXID$12, n));
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(AXID$12));
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
            this.get_store().remove_element(AXID$12, n);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$14, 0));
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
            return this.get_store().count_elements(EXTLST$14) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$14, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$14));
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
            this.get_store().remove_element(EXTLST$14, 0);
        }
    }
}

