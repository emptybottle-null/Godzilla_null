/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbl
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTDLblPos
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class CTDLblsImpl
extends XmlComplexContentImpl
implements CTDLbls {
    private static final long serialVersionUID = 1L;
    private static final QName DLBL$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dLbl");
    private static final QName DELETE$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "delete");
    private static final QName NUMFMT$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "numFmt");
    private static final QName SPPR$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "spPr");
    private static final QName TXPR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "txPr");
    private static final QName DLBLPOS$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dLblPos");
    private static final QName SHOWLEGENDKEY$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "showLegendKey");
    private static final QName SHOWVAL$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "showVal");
    private static final QName SHOWCATNAME$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "showCatName");
    private static final QName SHOWSERNAME$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "showSerName");
    private static final QName SHOWPERCENT$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "showPercent");
    private static final QName SHOWBUBBLESIZE$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "showBubbleSize");
    private static final QName SEPARATOR$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "separator");
    private static final QName SHOWLEADERLINES$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "showLeaderLines");
    private static final QName LEADERLINES$28 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "leaderLines");
    private static final QName EXTLST$30 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTDLblsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDLbl> getDLblList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DLblList
            extends AbstractList<CTDLbl> {
                DLblList() {
                }

                @Override
                public CTDLbl get(int n) {
                    return CTDLblsImpl.this.getDLblArray(n);
                }

                @Override
                public CTDLbl set(int n, CTDLbl cTDLbl) {
                    CTDLbl cTDLbl2 = CTDLblsImpl.this.getDLblArray(n);
                    CTDLblsImpl.this.setDLblArray(n, cTDLbl);
                    return cTDLbl2;
                }

                @Override
                public void add(int n, CTDLbl cTDLbl) {
                    CTDLblsImpl.this.insertNewDLbl(n).set((XmlObject)cTDLbl);
                }

                @Override
                public CTDLbl remove(int n) {
                    CTDLbl cTDLbl = CTDLblsImpl.this.getDLblArray(n);
                    CTDLblsImpl.this.removeDLbl(n);
                    return cTDLbl;
                }

                @Override
                public int size() {
                    return CTDLblsImpl.this.sizeOfDLblArray();
                }
            }
            return new DLblList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDLbl[] getDLblArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DLBL$0, arrayList);
            CTDLbl[] cTDLblArray = new CTDLbl[arrayList.size()];
            arrayList.toArray(cTDLblArray);
            return cTDLblArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDLbl getDLblArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDLbl cTDLbl = null;
            cTDLbl = (CTDLbl)this.get_store().find_element_user(DLBL$0, n);
            if (cTDLbl == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDLbl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDLblArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DLBL$0);
        }
    }

    @Override
    public void setDLblArray(CTDLbl[] cTDLblArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTDLblArray, DLBL$0);
    }

    @Override
    public void setDLblArray(int n, CTDLbl cTDLbl) {
        this.generatedSetterHelperImpl((XmlObject)cTDLbl, DLBL$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDLbl insertNewDLbl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDLbl cTDLbl = null;
            cTDLbl = (CTDLbl)this.get_store().insert_element_user(DLBL$0, n);
            return cTDLbl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDLbl addNewDLbl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDLbl cTDLbl = null;
            cTDLbl = (CTDLbl)this.get_store().add_element_user(DLBL$0);
            return cTDLbl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDLbl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DLBL$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getDelete() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(DELETE$2, 0));
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
    public boolean isSetDelete() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DELETE$2) != 0;
        }
    }

    @Override
    public void setDelete(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, DELETE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewDelete() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(DELETE$2));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDelete() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DELETE$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumFmt getNumFmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumFmt cTNumFmt = null;
            cTNumFmt = (CTNumFmt)((Object)this.get_store().find_element_user(NUMFMT$4, 0));
            if (cTNumFmt == null) {
                return null;
            }
            return cTNumFmt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNumFmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMFMT$4) != 0;
        }
    }

    @Override
    public void setNumFmt(CTNumFmt cTNumFmt) {
        this.generatedSetterHelperImpl(cTNumFmt, NUMFMT$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumFmt addNewNumFmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumFmt cTNumFmt = null;
            cTNumFmt = (CTNumFmt)((Object)this.get_store().add_element_user(NUMFMT$4));
            return cTNumFmt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNumFmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMFMT$4, 0);
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
    public CTTextBody getTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBody cTTextBody = null;
            cTTextBody = (CTTextBody)((Object)this.get_store().find_element_user(TXPR$8, 0));
            if (cTTextBody == null) {
                return null;
            }
            return cTTextBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TXPR$8) != 0;
        }
    }

    @Override
    public void setTxPr(CTTextBody cTTextBody) {
        this.generatedSetterHelperImpl(cTTextBody, TXPR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBody addNewTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBody cTTextBody = null;
            cTTextBody = (CTTextBody)((Object)this.get_store().add_element_user(TXPR$8));
            return cTTextBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TXPR$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDLblPos getDLblPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDLblPos cTDLblPos = null;
            cTDLblPos = (CTDLblPos)this.get_store().find_element_user(DLBLPOS$10, 0);
            if (cTDLblPos == null) {
                return null;
            }
            return cTDLblPos;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDLblPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DLBLPOS$10) != 0;
        }
    }

    @Override
    public void setDLblPos(CTDLblPos cTDLblPos) {
        this.generatedSetterHelperImpl((XmlObject)cTDLblPos, DLBLPOS$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDLblPos addNewDLblPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDLblPos cTDLblPos = null;
            cTDLblPos = (CTDLblPos)this.get_store().add_element_user(DLBLPOS$10);
            return cTDLblPos;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDLblPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DLBLPOS$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getShowLegendKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(SHOWLEGENDKEY$12, 0));
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
    public boolean isSetShowLegendKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWLEGENDKEY$12) != 0;
        }
    }

    @Override
    public void setShowLegendKey(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, SHOWLEGENDKEY$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewShowLegendKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(SHOWLEGENDKEY$12));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowLegendKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWLEGENDKEY$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getShowVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(SHOWVAL$14, 0));
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
    public boolean isSetShowVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWVAL$14) != 0;
        }
    }

    @Override
    public void setShowVal(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, SHOWVAL$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewShowVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(SHOWVAL$14));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWVAL$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getShowCatName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(SHOWCATNAME$16, 0));
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
    public boolean isSetShowCatName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWCATNAME$16) != 0;
        }
    }

    @Override
    public void setShowCatName(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, SHOWCATNAME$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewShowCatName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(SHOWCATNAME$16));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowCatName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWCATNAME$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getShowSerName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(SHOWSERNAME$18, 0));
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
    public boolean isSetShowSerName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWSERNAME$18) != 0;
        }
    }

    @Override
    public void setShowSerName(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, SHOWSERNAME$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewShowSerName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(SHOWSERNAME$18));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowSerName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWSERNAME$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getShowPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(SHOWPERCENT$20, 0));
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
    public boolean isSetShowPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWPERCENT$20) != 0;
        }
    }

    @Override
    public void setShowPercent(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, SHOWPERCENT$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewShowPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(SHOWPERCENT$20));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowPercent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWPERCENT$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getShowBubbleSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(SHOWBUBBLESIZE$22, 0));
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
    public boolean isSetShowBubbleSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWBUBBLESIZE$22) != 0;
        }
    }

    @Override
    public void setShowBubbleSize(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, SHOWBUBBLESIZE$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewShowBubbleSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(SHOWBUBBLESIZE$22));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowBubbleSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWBUBBLESIZE$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSeparator() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SEPARATOR$24, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetSeparator() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(SEPARATOR$24, 0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSeparator() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SEPARATOR$24) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSeparator(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SEPARATOR$24, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SEPARATOR$24));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSeparator(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(SEPARATOR$24, 0));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_element_user(SEPARATOR$24));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSeparator() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SEPARATOR$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getShowLeaderLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(SHOWLEADERLINES$26, 0));
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
    public boolean isSetShowLeaderLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWLEADERLINES$26) != 0;
        }
    }

    @Override
    public void setShowLeaderLines(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, SHOWLEADERLINES$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewShowLeaderLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(SHOWLEADERLINES$26));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowLeaderLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWLEADERLINES$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines getLeaderLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().find_element_user(LEADERLINES$28, 0));
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
    public boolean isSetLeaderLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LEADERLINES$28) != 0;
        }
    }

    @Override
    public void setLeaderLines(CTChartLines cTChartLines) {
        this.generatedSetterHelperImpl(cTChartLines, LEADERLINES$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines addNewLeaderLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().add_element_user(LEADERLINES$28));
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLeaderLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LEADERLINES$28, 0);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$30, 0));
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
            return this.get_store().count_elements(EXTLST$30) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$30, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$30));
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
            this.get_store().remove_element(EXTLST$30, 0);
        }
    }
}

