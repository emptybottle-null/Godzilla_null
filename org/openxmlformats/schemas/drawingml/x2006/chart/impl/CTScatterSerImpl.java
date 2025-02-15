/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTTrendline
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTrendline;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public class CTScatterSerImpl
extends XmlComplexContentImpl
implements CTScatterSer {
    private static final long serialVersionUID = 1L;
    private static final QName IDX$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "idx");
    private static final QName ORDER$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "order");
    private static final QName TX$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "tx");
    private static final QName SPPR$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "spPr");
    private static final QName MARKER$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "marker");
    private static final QName DPT$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dPt");
    private static final QName DLBLS$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dLbls");
    private static final QName TRENDLINE$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "trendline");
    private static final QName ERRBARS$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "errBars");
    private static final QName XVAL$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "xVal");
    private static final QName YVAL$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "yVal");
    private static final QName SMOOTH$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "smooth");
    private static final QName EXTLST$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTScatterSerImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt getIdx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(IDX$0, 0));
            if (cTUnsignedInt == null) {
                return null;
            }
            return cTUnsignedInt;
        }
    }

    @Override
    public void setIdx(CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, IDX$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt addNewIdx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(IDX$0));
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt getOrder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(ORDER$2, 0));
            if (cTUnsignedInt == null) {
                return null;
            }
            return cTUnsignedInt;
        }
    }

    @Override
    public void setOrder(CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, ORDER$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt addNewOrder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(ORDER$2));
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSerTx getTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSerTx cTSerTx = null;
            cTSerTx = (CTSerTx)((Object)this.get_store().find_element_user(TX$4, 0));
            if (cTSerTx == null) {
                return null;
            }
            return cTSerTx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TX$4) != 0;
        }
    }

    @Override
    public void setTx(CTSerTx cTSerTx) {
        this.generatedSetterHelperImpl(cTSerTx, TX$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSerTx addNewTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSerTx cTSerTx = null;
            cTSerTx = (CTSerTx)((Object)this.get_store().add_element_user(TX$4));
            return cTSerTx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TX$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties getSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().find_element_user(SPPR$6, 0));
            if (cTShapeProperties == null) {
                return null;
            }
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPPR$6) != 0;
        }
    }

    @Override
    public void setSpPr(CTShapeProperties cTShapeProperties) {
        this.generatedSetterHelperImpl(cTShapeProperties, SPPR$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties addNewSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().add_element_user(SPPR$6));
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPPR$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarker getMarker() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarker cTMarker = null;
            cTMarker = (CTMarker)((Object)this.get_store().find_element_user(MARKER$8, 0));
            if (cTMarker == null) {
                return null;
            }
            return cTMarker;
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
            return this.get_store().count_elements(MARKER$8) != 0;
        }
    }

    @Override
    public void setMarker(CTMarker cTMarker) {
        this.generatedSetterHelperImpl(cTMarker, MARKER$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarker addNewMarker() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarker cTMarker = null;
            cTMarker = (CTMarker)((Object)this.get_store().add_element_user(MARKER$8));
            return cTMarker;
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
            this.get_store().remove_element(MARKER$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDPt> getDPtList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DPtList
            extends AbstractList<CTDPt> {
                DPtList() {
                }

                @Override
                public CTDPt get(int n) {
                    return CTScatterSerImpl.this.getDPtArray(n);
                }

                @Override
                public CTDPt set(int n, CTDPt cTDPt) {
                    CTDPt cTDPt2 = CTScatterSerImpl.this.getDPtArray(n);
                    CTScatterSerImpl.this.setDPtArray(n, cTDPt);
                    return cTDPt2;
                }

                @Override
                public void add(int n, CTDPt cTDPt) {
                    CTScatterSerImpl.this.insertNewDPt(n).set((XmlObject)cTDPt);
                }

                @Override
                public CTDPt remove(int n) {
                    CTDPt cTDPt = CTScatterSerImpl.this.getDPtArray(n);
                    CTScatterSerImpl.this.removeDPt(n);
                    return cTDPt;
                }

                @Override
                public int size() {
                    return CTScatterSerImpl.this.sizeOfDPtArray();
                }
            }
            return new DPtList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDPt[] getDPtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DPT$10, arrayList);
            CTDPt[] cTDPtArray = new CTDPt[arrayList.size()];
            arrayList.toArray(cTDPtArray);
            return cTDPtArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDPt getDPtArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDPt cTDPt = null;
            cTDPt = (CTDPt)this.get_store().find_element_user(DPT$10, n);
            if (cTDPt == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDPt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDPtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DPT$10);
        }
    }

    @Override
    public void setDPtArray(CTDPt[] cTDPtArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTDPtArray, DPT$10);
    }

    @Override
    public void setDPtArray(int n, CTDPt cTDPt) {
        this.generatedSetterHelperImpl((XmlObject)cTDPt, DPT$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDPt insertNewDPt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDPt cTDPt = null;
            cTDPt = (CTDPt)this.get_store().insert_element_user(DPT$10, n);
            return cTDPt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDPt addNewDPt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDPt cTDPt = null;
            cTDPt = (CTDPt)this.get_store().add_element_user(DPT$10);
            return cTDPt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDPt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DPT$10, n);
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
            cTDLbls = (CTDLbls)((Object)this.get_store().find_element_user(DLBLS$12, 0));
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
            return this.get_store().count_elements(DLBLS$12) != 0;
        }
    }

    @Override
    public void setDLbls(CTDLbls cTDLbls) {
        this.generatedSetterHelperImpl(cTDLbls, DLBLS$12, 0, (short)1);
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
            cTDLbls = (CTDLbls)((Object)this.get_store().add_element_user(DLBLS$12));
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
            this.get_store().remove_element(DLBLS$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTrendline> getTrendlineList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TrendlineList
            extends AbstractList<CTTrendline> {
                TrendlineList() {
                }

                @Override
                public CTTrendline get(int n) {
                    return CTScatterSerImpl.this.getTrendlineArray(n);
                }

                @Override
                public CTTrendline set(int n, CTTrendline cTTrendline) {
                    CTTrendline cTTrendline2 = CTScatterSerImpl.this.getTrendlineArray(n);
                    CTScatterSerImpl.this.setTrendlineArray(n, cTTrendline);
                    return cTTrendline2;
                }

                @Override
                public void add(int n, CTTrendline cTTrendline) {
                    CTScatterSerImpl.this.insertNewTrendline(n).set((XmlObject)cTTrendline);
                }

                @Override
                public CTTrendline remove(int n) {
                    CTTrendline cTTrendline = CTScatterSerImpl.this.getTrendlineArray(n);
                    CTScatterSerImpl.this.removeTrendline(n);
                    return cTTrendline;
                }

                @Override
                public int size() {
                    return CTScatterSerImpl.this.sizeOfTrendlineArray();
                }
            }
            return new TrendlineList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTrendline[] getTrendlineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TRENDLINE$14, arrayList);
            CTTrendline[] cTTrendlineArray = new CTTrendline[arrayList.size()];
            arrayList.toArray(cTTrendlineArray);
            return cTTrendlineArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrendline getTrendlineArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrendline cTTrendline = null;
            cTTrendline = (CTTrendline)this.get_store().find_element_user(TRENDLINE$14, n);
            if (cTTrendline == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTrendline;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTrendlineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TRENDLINE$14);
        }
    }

    @Override
    public void setTrendlineArray(CTTrendline[] cTTrendlineArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTTrendlineArray, TRENDLINE$14);
    }

    @Override
    public void setTrendlineArray(int n, CTTrendline cTTrendline) {
        this.generatedSetterHelperImpl((XmlObject)cTTrendline, TRENDLINE$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrendline insertNewTrendline(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrendline cTTrendline = null;
            cTTrendline = (CTTrendline)this.get_store().insert_element_user(TRENDLINE$14, n);
            return cTTrendline;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrendline addNewTrendline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrendline cTTrendline = null;
            cTTrendline = (CTTrendline)this.get_store().add_element_user(TRENDLINE$14);
            return cTTrendline;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTrendline(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TRENDLINE$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTErrBars> getErrBarsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ErrBarsList
            extends AbstractList<CTErrBars> {
                ErrBarsList() {
                }

                @Override
                public CTErrBars get(int n) {
                    return CTScatterSerImpl.this.getErrBarsArray(n);
                }

                @Override
                public CTErrBars set(int n, CTErrBars cTErrBars) {
                    CTErrBars cTErrBars2 = CTScatterSerImpl.this.getErrBarsArray(n);
                    CTScatterSerImpl.this.setErrBarsArray(n, cTErrBars);
                    return cTErrBars2;
                }

                @Override
                public void add(int n, CTErrBars cTErrBars) {
                    CTScatterSerImpl.this.insertNewErrBars(n).set(cTErrBars);
                }

                @Override
                public CTErrBars remove(int n) {
                    CTErrBars cTErrBars = CTScatterSerImpl.this.getErrBarsArray(n);
                    CTScatterSerImpl.this.removeErrBars(n);
                    return cTErrBars;
                }

                @Override
                public int size() {
                    return CTScatterSerImpl.this.sizeOfErrBarsArray();
                }
            }
            return new ErrBarsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTErrBars[] getErrBarsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ERRBARS$16, arrayList);
            CTErrBars[] cTErrBarsArray = new CTErrBars[arrayList.size()];
            arrayList.toArray(cTErrBarsArray);
            return cTErrBarsArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTErrBars getErrBarsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTErrBars cTErrBars = null;
            cTErrBars = (CTErrBars)((Object)this.get_store().find_element_user(ERRBARS$16, n));
            if (cTErrBars == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTErrBars;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfErrBarsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ERRBARS$16);
        }
    }

    @Override
    public void setErrBarsArray(CTErrBars[] cTErrBarsArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTErrBarsArray, ERRBARS$16);
    }

    @Override
    public void setErrBarsArray(int n, CTErrBars cTErrBars) {
        this.generatedSetterHelperImpl(cTErrBars, ERRBARS$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTErrBars insertNewErrBars(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTErrBars cTErrBars = null;
            cTErrBars = (CTErrBars)((Object)this.get_store().insert_element_user(ERRBARS$16, n));
            return cTErrBars;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTErrBars addNewErrBars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTErrBars cTErrBars = null;
            cTErrBars = (CTErrBars)((Object)this.get_store().add_element_user(ERRBARS$16));
            return cTErrBars;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeErrBars(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ERRBARS$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAxDataSource getXVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAxDataSource cTAxDataSource = null;
            cTAxDataSource = (CTAxDataSource)((Object)this.get_store().find_element_user(XVAL$18, 0));
            if (cTAxDataSource == null) {
                return null;
            }
            return cTAxDataSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetXVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(XVAL$18) != 0;
        }
    }

    @Override
    public void setXVal(CTAxDataSource cTAxDataSource) {
        this.generatedSetterHelperImpl(cTAxDataSource, XVAL$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAxDataSource addNewXVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAxDataSource cTAxDataSource = null;
            cTAxDataSource = (CTAxDataSource)((Object)this.get_store().add_element_user(XVAL$18));
            return cTAxDataSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetXVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(XVAL$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumDataSource getYVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumDataSource cTNumDataSource = null;
            cTNumDataSource = (CTNumDataSource)((Object)this.get_store().find_element_user(YVAL$20, 0));
            if (cTNumDataSource == null) {
                return null;
            }
            return cTNumDataSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetYVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(YVAL$20) != 0;
        }
    }

    @Override
    public void setYVal(CTNumDataSource cTNumDataSource) {
        this.generatedSetterHelperImpl(cTNumDataSource, YVAL$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumDataSource addNewYVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumDataSource cTNumDataSource = null;
            cTNumDataSource = (CTNumDataSource)((Object)this.get_store().add_element_user(YVAL$20));
            return cTNumDataSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetYVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(YVAL$20, 0);
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
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(SMOOTH$22, 0));
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
            return this.get_store().count_elements(SMOOTH$22) != 0;
        }
    }

    @Override
    public void setSmooth(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, SMOOTH$22, 0, (short)1);
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
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(SMOOTH$22));
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
            this.get_store().remove_element(SMOOTH$22, 0);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$24, 0));
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
            return this.get_store().count_elements(EXTLST$24) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$24, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$24));
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
            this.get_store().remove_element(EXTLST$24, 0);
        }
    }
}

