/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTUpDownBars
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUpDownBars;

public class CTLineChartImpl
extends XmlComplexContentImpl
implements CTLineChart {
    private static final long serialVersionUID = 1L;
    private static final QName GROUPING$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "grouping");
    private static final QName VARYCOLORS$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "varyColors");
    private static final QName SER$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "ser");
    private static final QName DLBLS$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dLbls");
    private static final QName DROPLINES$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dropLines");
    private static final QName HILOWLINES$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "hiLowLines");
    private static final QName UPDOWNBARS$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "upDownBars");
    private static final QName MARKER$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "marker");
    private static final QName SMOOTH$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "smooth");
    private static final QName AXID$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "axId");
    private static final QName EXTLST$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTLineChartImpl(SchemaType schemaType) {
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
    public List<CTLineSer> getSerList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SerList
            extends AbstractList<CTLineSer> {
                SerList() {
                }

                @Override
                public CTLineSer get(int n) {
                    return CTLineChartImpl.this.getSerArray(n);
                }

                @Override
                public CTLineSer set(int n, CTLineSer cTLineSer) {
                    CTLineSer cTLineSer2 = CTLineChartImpl.this.getSerArray(n);
                    CTLineChartImpl.this.setSerArray(n, cTLineSer);
                    return cTLineSer2;
                }

                @Override
                public void add(int n, CTLineSer cTLineSer) {
                    CTLineChartImpl.this.insertNewSer(n).set(cTLineSer);
                }

                @Override
                public CTLineSer remove(int n) {
                    CTLineSer cTLineSer = CTLineChartImpl.this.getSerArray(n);
                    CTLineChartImpl.this.removeSer(n);
                    return cTLineSer;
                }

                @Override
                public int size() {
                    return CTLineChartImpl.this.sizeOfSerArray();
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
    public CTLineSer[] getSerArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SER$4, arrayList);
            CTLineSer[] cTLineSerArray = new CTLineSer[arrayList.size()];
            arrayList.toArray(cTLineSerArray);
            return cTLineSerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineSer getSerArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineSer cTLineSer = null;
            cTLineSer = (CTLineSer)((Object)this.get_store().find_element_user(SER$4, n));
            if (cTLineSer == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLineSer;
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
    public void setSerArray(CTLineSer[] cTLineSerArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTLineSerArray, SER$4);
    }

    @Override
    public void setSerArray(int n, CTLineSer cTLineSer) {
        this.generatedSetterHelperImpl(cTLineSer, SER$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineSer insertNewSer(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineSer cTLineSer = null;
            cTLineSer = (CTLineSer)((Object)this.get_store().insert_element_user(SER$4, n));
            return cTLineSer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineSer addNewSer() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineSer cTLineSer = null;
            cTLineSer = (CTLineSer)((Object)this.get_store().add_element_user(SER$4));
            return cTLineSer;
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
    public CTChartLines getHiLowLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().find_element_user(HILOWLINES$10, 0));
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
    public boolean isSetHiLowLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HILOWLINES$10) != 0;
        }
    }

    @Override
    public void setHiLowLines(CTChartLines cTChartLines) {
        this.generatedSetterHelperImpl(cTChartLines, HILOWLINES$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines addNewHiLowLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().add_element_user(HILOWLINES$10));
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHiLowLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HILOWLINES$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUpDownBars getUpDownBars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUpDownBars cTUpDownBars = null;
            cTUpDownBars = (CTUpDownBars)this.get_store().find_element_user(UPDOWNBARS$12, 0);
            if (cTUpDownBars == null) {
                return null;
            }
            return cTUpDownBars;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUpDownBars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UPDOWNBARS$12) != 0;
        }
    }

    @Override
    public void setUpDownBars(CTUpDownBars cTUpDownBars) {
        this.generatedSetterHelperImpl((XmlObject)cTUpDownBars, UPDOWNBARS$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUpDownBars addNewUpDownBars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUpDownBars cTUpDownBars = null;
            cTUpDownBars = (CTUpDownBars)this.get_store().add_element_user(UPDOWNBARS$12);
            return cTUpDownBars;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUpDownBars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UPDOWNBARS$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getMarker() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(MARKER$14, 0));
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
    public boolean isSetMarker() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MARKER$14) != 0;
        }
    }

    @Override
    public void setMarker(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, MARKER$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewMarker() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(MARKER$14));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMarker() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MARKER$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getSmooth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(SMOOTH$16, 0));
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
    public boolean isSetSmooth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SMOOTH$16) != 0;
        }
    }

    @Override
    public void setSmooth(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, SMOOTH$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewSmooth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(SMOOTH$16));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSmooth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SMOOTH$16, 0);
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
                    return CTLineChartImpl.this.getAxIdArray(n);
                }

                @Override
                public CTUnsignedInt set(int n, CTUnsignedInt cTUnsignedInt) {
                    CTUnsignedInt cTUnsignedInt2 = CTLineChartImpl.this.getAxIdArray(n);
                    CTLineChartImpl.this.setAxIdArray(n, cTUnsignedInt);
                    return cTUnsignedInt2;
                }

                @Override
                public void add(int n, CTUnsignedInt cTUnsignedInt) {
                    CTLineChartImpl.this.insertNewAxId(n).set(cTUnsignedInt);
                }

                @Override
                public CTUnsignedInt remove(int n) {
                    CTUnsignedInt cTUnsignedInt = CTLineChartImpl.this.getAxIdArray(n);
                    CTLineChartImpl.this.removeAxId(n);
                    return cTUnsignedInt;
                }

                @Override
                public int size() {
                    return CTLineChartImpl.this.sizeOfAxIdArray();
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
            this.get_store().find_all_element_users(AXID$18, arrayList);
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(AXID$18, n));
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
            return this.get_store().count_elements(AXID$18);
        }
    }

    @Override
    public void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTUnsignedIntArray, AXID$18);
    }

    @Override
    public void setAxIdArray(int n, CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, AXID$18, n, (short)2);
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().insert_element_user(AXID$18, n));
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(AXID$18));
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
            this.get_store().remove_element(AXID$18, n);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$20, 0));
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
            return this.get_store().count_elements(EXTLST$20) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$20, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$20));
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
            this.get_store().remove_element(EXTLST$20, 0);
        }
    }
}

