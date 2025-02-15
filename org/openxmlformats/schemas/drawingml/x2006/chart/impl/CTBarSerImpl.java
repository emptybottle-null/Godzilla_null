/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions
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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTrendline;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public class CTBarSerImpl
extends XmlComplexContentImpl
implements CTBarSer {
    private static final long serialVersionUID = 1L;
    private static final QName IDX$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "idx");
    private static final QName ORDER$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "order");
    private static final QName TX$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "tx");
    private static final QName SPPR$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "spPr");
    private static final QName INVERTIFNEGATIVE$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "invertIfNegative");
    private static final QName PICTUREOPTIONS$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "pictureOptions");
    private static final QName DPT$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dPt");
    private static final QName DLBLS$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dLbls");
    private static final QName TRENDLINE$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "trendline");
    private static final QName ERRBARS$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "errBars");
    private static final QName CAT$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "cat");
    private static final QName VAL$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "val");
    private static final QName SHAPE$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "shape");
    private static final QName EXTLST$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTBarSerImpl(SchemaType schemaType) {
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
    public CTBoolean getInvertIfNegative() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(INVERTIFNEGATIVE$8, 0));
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
    public boolean isSetInvertIfNegative() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INVERTIFNEGATIVE$8) != 0;
        }
    }

    @Override
    public void setInvertIfNegative(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, INVERTIFNEGATIVE$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewInvertIfNegative() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(INVERTIFNEGATIVE$8));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetInvertIfNegative() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INVERTIFNEGATIVE$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPictureOptions getPictureOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPictureOptions cTPictureOptions = null;
            cTPictureOptions = (CTPictureOptions)this.get_store().find_element_user(PICTUREOPTIONS$10, 0);
            if (cTPictureOptions == null) {
                return null;
            }
            return cTPictureOptions;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPictureOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PICTUREOPTIONS$10) != 0;
        }
    }

    @Override
    public void setPictureOptions(CTPictureOptions cTPictureOptions) {
        this.generatedSetterHelperImpl((XmlObject)cTPictureOptions, PICTUREOPTIONS$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPictureOptions addNewPictureOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPictureOptions cTPictureOptions = null;
            cTPictureOptions = (CTPictureOptions)this.get_store().add_element_user(PICTUREOPTIONS$10);
            return cTPictureOptions;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPictureOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PICTUREOPTIONS$10, 0);
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
                    return CTBarSerImpl.this.getDPtArray(n);
                }

                @Override
                public CTDPt set(int n, CTDPt cTDPt) {
                    CTDPt cTDPt2 = CTBarSerImpl.this.getDPtArray(n);
                    CTBarSerImpl.this.setDPtArray(n, cTDPt);
                    return cTDPt2;
                }

                @Override
                public void add(int n, CTDPt cTDPt) {
                    CTBarSerImpl.this.insertNewDPt(n).set((XmlObject)cTDPt);
                }

                @Override
                public CTDPt remove(int n) {
                    CTDPt cTDPt = CTBarSerImpl.this.getDPtArray(n);
                    CTBarSerImpl.this.removeDPt(n);
                    return cTDPt;
                }

                @Override
                public int size() {
                    return CTBarSerImpl.this.sizeOfDPtArray();
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
            this.get_store().find_all_element_users(DPT$12, arrayList);
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
            cTDPt = (CTDPt)this.get_store().find_element_user(DPT$12, n);
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
            return this.get_store().count_elements(DPT$12);
        }
    }

    @Override
    public void setDPtArray(CTDPt[] cTDPtArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTDPtArray, DPT$12);
    }

    @Override
    public void setDPtArray(int n, CTDPt cTDPt) {
        this.generatedSetterHelperImpl((XmlObject)cTDPt, DPT$12, n, (short)2);
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
            cTDPt = (CTDPt)this.get_store().insert_element_user(DPT$12, n);
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
            cTDPt = (CTDPt)this.get_store().add_element_user(DPT$12);
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
            this.get_store().remove_element(DPT$12, n);
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
            cTDLbls = (CTDLbls)((Object)this.get_store().find_element_user(DLBLS$14, 0));
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
            return this.get_store().count_elements(DLBLS$14) != 0;
        }
    }

    @Override
    public void setDLbls(CTDLbls cTDLbls) {
        this.generatedSetterHelperImpl(cTDLbls, DLBLS$14, 0, (short)1);
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
            cTDLbls = (CTDLbls)((Object)this.get_store().add_element_user(DLBLS$14));
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
            this.get_store().remove_element(DLBLS$14, 0);
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
                    return CTBarSerImpl.this.getTrendlineArray(n);
                }

                @Override
                public CTTrendline set(int n, CTTrendline cTTrendline) {
                    CTTrendline cTTrendline2 = CTBarSerImpl.this.getTrendlineArray(n);
                    CTBarSerImpl.this.setTrendlineArray(n, cTTrendline);
                    return cTTrendline2;
                }

                @Override
                public void add(int n, CTTrendline cTTrendline) {
                    CTBarSerImpl.this.insertNewTrendline(n).set((XmlObject)cTTrendline);
                }

                @Override
                public CTTrendline remove(int n) {
                    CTTrendline cTTrendline = CTBarSerImpl.this.getTrendlineArray(n);
                    CTBarSerImpl.this.removeTrendline(n);
                    return cTTrendline;
                }

                @Override
                public int size() {
                    return CTBarSerImpl.this.sizeOfTrendlineArray();
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
            this.get_store().find_all_element_users(TRENDLINE$16, arrayList);
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
            cTTrendline = (CTTrendline)this.get_store().find_element_user(TRENDLINE$16, n);
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
            return this.get_store().count_elements(TRENDLINE$16);
        }
    }

    @Override
    public void setTrendlineArray(CTTrendline[] cTTrendlineArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTTrendlineArray, TRENDLINE$16);
    }

    @Override
    public void setTrendlineArray(int n, CTTrendline cTTrendline) {
        this.generatedSetterHelperImpl((XmlObject)cTTrendline, TRENDLINE$16, n, (short)2);
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
            cTTrendline = (CTTrendline)this.get_store().insert_element_user(TRENDLINE$16, n);
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
            cTTrendline = (CTTrendline)this.get_store().add_element_user(TRENDLINE$16);
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
            this.get_store().remove_element(TRENDLINE$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTErrBars getErrBars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTErrBars cTErrBars = null;
            cTErrBars = (CTErrBars)((Object)this.get_store().find_element_user(ERRBARS$18, 0));
            if (cTErrBars == null) {
                return null;
            }
            return cTErrBars;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetErrBars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ERRBARS$18) != 0;
        }
    }

    @Override
    public void setErrBars(CTErrBars cTErrBars) {
        this.generatedSetterHelperImpl(cTErrBars, ERRBARS$18, 0, (short)1);
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
            cTErrBars = (CTErrBars)((Object)this.get_store().add_element_user(ERRBARS$18));
            return cTErrBars;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetErrBars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ERRBARS$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAxDataSource getCat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAxDataSource cTAxDataSource = null;
            cTAxDataSource = (CTAxDataSource)((Object)this.get_store().find_element_user(CAT$20, 0));
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
    public boolean isSetCat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CAT$20) != 0;
        }
    }

    @Override
    public void setCat(CTAxDataSource cTAxDataSource) {
        this.generatedSetterHelperImpl(cTAxDataSource, CAT$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAxDataSource addNewCat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAxDataSource cTAxDataSource = null;
            cTAxDataSource = (CTAxDataSource)((Object)this.get_store().add_element_user(CAT$20));
            return cTAxDataSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CAT$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumDataSource getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumDataSource cTNumDataSource = null;
            cTNumDataSource = (CTNumDataSource)((Object)this.get_store().find_element_user(VAL$22, 0));
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
    public boolean isSetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VAL$22) != 0;
        }
    }

    @Override
    public void setVal(CTNumDataSource cTNumDataSource) {
        this.generatedSetterHelperImpl(cTNumDataSource, VAL$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumDataSource addNewVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumDataSource cTNumDataSource = null;
            cTNumDataSource = (CTNumDataSource)((Object)this.get_store().add_element_user(VAL$22));
            return cTNumDataSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VAL$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape getShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape cTShape = null;
            cTShape = (CTShape)((Object)this.get_store().find_element_user(SHAPE$24, 0));
            if (cTShape == null) {
                return null;
            }
            return cTShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHAPE$24) != 0;
        }
    }

    @Override
    public void setShape(CTShape cTShape) {
        this.generatedSetterHelperImpl(cTShape, SHAPE$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape addNewShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape cTShape = null;
            cTShape = (CTShape)((Object)this.get_store().add_element_user(SHAPE$24));
            return cTShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHAPE$24, 0);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$26, 0));
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
            return this.get_store().count_elements(EXTLST$26) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$26, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$26));
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
            this.get_store().remove_element(EXTLST$26, 0);
        }
    }
}

