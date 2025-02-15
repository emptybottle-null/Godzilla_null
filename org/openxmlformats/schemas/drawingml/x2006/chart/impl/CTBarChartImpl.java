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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOverlap;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTBarChartImpl
extends XmlComplexContentImpl
implements CTBarChart {
    private static final long serialVersionUID = 1L;
    private static final QName BARDIR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "barDir");
    private static final QName GROUPING$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "grouping");
    private static final QName VARYCOLORS$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "varyColors");
    private static final QName SER$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "ser");
    private static final QName DLBLS$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dLbls");
    private static final QName GAPWIDTH$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "gapWidth");
    private static final QName OVERLAP$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "overlap");
    private static final QName SERLINES$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "serLines");
    private static final QName AXID$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "axId");
    private static final QName EXTLST$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTBarChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBarDir getBarDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBarDir cTBarDir = null;
            cTBarDir = (CTBarDir)((Object)this.get_store().find_element_user(BARDIR$0, 0));
            if (cTBarDir == null) {
                return null;
            }
            return cTBarDir;
        }
    }

    @Override
    public void setBarDir(CTBarDir cTBarDir) {
        this.generatedSetterHelperImpl(cTBarDir, BARDIR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBarDir addNewBarDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBarDir cTBarDir = null;
            cTBarDir = (CTBarDir)((Object)this.get_store().add_element_user(BARDIR$0));
            return cTBarDir;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBarGrouping getGrouping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBarGrouping cTBarGrouping = null;
            cTBarGrouping = (CTBarGrouping)((Object)this.get_store().find_element_user(GROUPING$2, 0));
            if (cTBarGrouping == null) {
                return null;
            }
            return cTBarGrouping;
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
            return this.get_store().count_elements(GROUPING$2) != 0;
        }
    }

    @Override
    public void setGrouping(CTBarGrouping cTBarGrouping) {
        this.generatedSetterHelperImpl(cTBarGrouping, GROUPING$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBarGrouping addNewGrouping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBarGrouping cTBarGrouping = null;
            cTBarGrouping = (CTBarGrouping)((Object)this.get_store().add_element_user(GROUPING$2));
            return cTBarGrouping;
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
            this.get_store().remove_element(GROUPING$2, 0);
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
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(VARYCOLORS$4, 0));
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
            return this.get_store().count_elements(VARYCOLORS$4) != 0;
        }
    }

    @Override
    public void setVaryColors(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, VARYCOLORS$4, 0, (short)1);
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
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(VARYCOLORS$4));
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
            this.get_store().remove_element(VARYCOLORS$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBarSer> getSerList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SerList
            extends AbstractList<CTBarSer> {
                SerList() {
                }

                @Override
                public CTBarSer get(int n) {
                    return CTBarChartImpl.this.getSerArray(n);
                }

                @Override
                public CTBarSer set(int n, CTBarSer cTBarSer) {
                    CTBarSer cTBarSer2 = CTBarChartImpl.this.getSerArray(n);
                    CTBarChartImpl.this.setSerArray(n, cTBarSer);
                    return cTBarSer2;
                }

                @Override
                public void add(int n, CTBarSer cTBarSer) {
                    CTBarChartImpl.this.insertNewSer(n).set(cTBarSer);
                }

                @Override
                public CTBarSer remove(int n) {
                    CTBarSer cTBarSer = CTBarChartImpl.this.getSerArray(n);
                    CTBarChartImpl.this.removeSer(n);
                    return cTBarSer;
                }

                @Override
                public int size() {
                    return CTBarChartImpl.this.sizeOfSerArray();
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
    public CTBarSer[] getSerArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SER$6, arrayList);
            CTBarSer[] cTBarSerArray = new CTBarSer[arrayList.size()];
            arrayList.toArray(cTBarSerArray);
            return cTBarSerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBarSer getSerArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBarSer cTBarSer = null;
            cTBarSer = (CTBarSer)((Object)this.get_store().find_element_user(SER$6, n));
            if (cTBarSer == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBarSer;
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
            return this.get_store().count_elements(SER$6);
        }
    }

    @Override
    public void setSerArray(CTBarSer[] cTBarSerArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBarSerArray, SER$6);
    }

    @Override
    public void setSerArray(int n, CTBarSer cTBarSer) {
        this.generatedSetterHelperImpl(cTBarSer, SER$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBarSer insertNewSer(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBarSer cTBarSer = null;
            cTBarSer = (CTBarSer)((Object)this.get_store().insert_element_user(SER$6, n));
            return cTBarSer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBarSer addNewSer() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBarSer cTBarSer = null;
            cTBarSer = (CTBarSer)((Object)this.get_store().add_element_user(SER$6));
            return cTBarSer;
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
            this.get_store().remove_element(SER$6, n);
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
            cTDLbls = (CTDLbls)((Object)this.get_store().find_element_user(DLBLS$8, 0));
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
            return this.get_store().count_elements(DLBLS$8) != 0;
        }
    }

    @Override
    public void setDLbls(CTDLbls cTDLbls) {
        this.generatedSetterHelperImpl(cTDLbls, DLBLS$8, 0, (short)1);
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
            cTDLbls = (CTDLbls)((Object)this.get_store().add_element_user(DLBLS$8));
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
            this.get_store().remove_element(DLBLS$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGapAmount getGapWidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGapAmount cTGapAmount = null;
            cTGapAmount = (CTGapAmount)((Object)this.get_store().find_element_user(GAPWIDTH$10, 0));
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
    public boolean isSetGapWidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GAPWIDTH$10) != 0;
        }
    }

    @Override
    public void setGapWidth(CTGapAmount cTGapAmount) {
        this.generatedSetterHelperImpl(cTGapAmount, GAPWIDTH$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGapAmount addNewGapWidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGapAmount cTGapAmount = null;
            cTGapAmount = (CTGapAmount)((Object)this.get_store().add_element_user(GAPWIDTH$10));
            return cTGapAmount;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGapWidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GAPWIDTH$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOverlap getOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOverlap cTOverlap = null;
            cTOverlap = (CTOverlap)((Object)this.get_store().find_element_user(OVERLAP$12, 0));
            if (cTOverlap == null) {
                return null;
            }
            return cTOverlap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OVERLAP$12) != 0;
        }
    }

    @Override
    public void setOverlap(CTOverlap cTOverlap) {
        this.generatedSetterHelperImpl(cTOverlap, OVERLAP$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOverlap addNewOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOverlap cTOverlap = null;
            cTOverlap = (CTOverlap)((Object)this.get_store().add_element_user(OVERLAP$12));
            return cTOverlap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OVERLAP$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTChartLines> getSerLinesList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SerLinesList
            extends AbstractList<CTChartLines> {
                SerLinesList() {
                }

                @Override
                public CTChartLines get(int n) {
                    return CTBarChartImpl.this.getSerLinesArray(n);
                }

                @Override
                public CTChartLines set(int n, CTChartLines cTChartLines) {
                    CTChartLines cTChartLines2 = CTBarChartImpl.this.getSerLinesArray(n);
                    CTBarChartImpl.this.setSerLinesArray(n, cTChartLines);
                    return cTChartLines2;
                }

                @Override
                public void add(int n, CTChartLines cTChartLines) {
                    CTBarChartImpl.this.insertNewSerLines(n).set(cTChartLines);
                }

                @Override
                public CTChartLines remove(int n) {
                    CTChartLines cTChartLines = CTBarChartImpl.this.getSerLinesArray(n);
                    CTBarChartImpl.this.removeSerLines(n);
                    return cTChartLines;
                }

                @Override
                public int size() {
                    return CTBarChartImpl.this.sizeOfSerLinesArray();
                }
            }
            return new SerLinesList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTChartLines[] getSerLinesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SERLINES$14, arrayList);
            CTChartLines[] cTChartLinesArray = new CTChartLines[arrayList.size()];
            arrayList.toArray(cTChartLinesArray);
            return cTChartLinesArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines getSerLinesArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().find_element_user(SERLINES$14, n));
            if (cTChartLines == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSerLinesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SERLINES$14);
        }
    }

    @Override
    public void setSerLinesArray(CTChartLines[] cTChartLinesArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTChartLinesArray, SERLINES$14);
    }

    @Override
    public void setSerLinesArray(int n, CTChartLines cTChartLines) {
        this.generatedSetterHelperImpl(cTChartLines, SERLINES$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines insertNewSerLines(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().insert_element_user(SERLINES$14, n));
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines addNewSerLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().add_element_user(SERLINES$14));
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSerLines(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SERLINES$14, n);
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
                    return CTBarChartImpl.this.getAxIdArray(n);
                }

                @Override
                public CTUnsignedInt set(int n, CTUnsignedInt cTUnsignedInt) {
                    CTUnsignedInt cTUnsignedInt2 = CTBarChartImpl.this.getAxIdArray(n);
                    CTBarChartImpl.this.setAxIdArray(n, cTUnsignedInt);
                    return cTUnsignedInt2;
                }

                @Override
                public void add(int n, CTUnsignedInt cTUnsignedInt) {
                    CTBarChartImpl.this.insertNewAxId(n).set(cTUnsignedInt);
                }

                @Override
                public CTUnsignedInt remove(int n) {
                    CTUnsignedInt cTUnsignedInt = CTBarChartImpl.this.getAxIdArray(n);
                    CTBarChartImpl.this.removeAxId(n);
                    return cTUnsignedInt;
                }

                @Override
                public int size() {
                    return CTBarChartImpl.this.sizeOfAxIdArray();
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
            this.get_store().find_all_element_users(AXID$16, arrayList);
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(AXID$16, n));
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
            return this.get_store().count_elements(AXID$16);
        }
    }

    @Override
    public void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTUnsignedIntArray, AXID$16);
    }

    @Override
    public void setAxIdArray(int n, CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, AXID$16, n, (short)2);
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().insert_element_user(AXID$16, n));
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
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(AXID$16));
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
            this.get_store().remove_element(AXID$16, n);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$18, 0));
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
            return this.get_store().count_elements(EXTLST$18) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$18, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$18));
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
            this.get_store().remove_element(EXTLST$18, 0);
        }
    }
}

