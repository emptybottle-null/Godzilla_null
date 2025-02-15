/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase;

public class CTTrPrBaseImpl
extends XmlComplexContentImpl
implements CTTrPrBase {
    private static final long serialVersionUID = 1L;
    private static final QName CNFSTYLE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cnfStyle");
    private static final QName DIVID$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "divId");
    private static final QName GRIDBEFORE$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "gridBefore");
    private static final QName GRIDAFTER$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "gridAfter");
    private static final QName WBEFORE$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "wBefore");
    private static final QName WAFTER$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "wAfter");
    private static final QName CANTSPLIT$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cantSplit");
    private static final QName TRHEIGHT$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "trHeight");
    private static final QName TBLHEADER$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblHeader");
    private static final QName TBLCELLSPACING$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCellSpacing");
    private static final QName JC$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "jc");
    private static final QName HIDDEN$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hidden");

    public CTTrPrBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCnf> getCnfStyleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CnfStyleList
            extends AbstractList<CTCnf> {
                CnfStyleList() {
                }

                @Override
                public CTCnf get(int n) {
                    return CTTrPrBaseImpl.this.getCnfStyleArray(n);
                }

                @Override
                public CTCnf set(int n, CTCnf cTCnf) {
                    CTCnf cTCnf2 = CTTrPrBaseImpl.this.getCnfStyleArray(n);
                    CTTrPrBaseImpl.this.setCnfStyleArray(n, cTCnf);
                    return cTCnf2;
                }

                @Override
                public void add(int n, CTCnf cTCnf) {
                    CTTrPrBaseImpl.this.insertNewCnfStyle(n).set((XmlObject)cTCnf);
                }

                @Override
                public CTCnf remove(int n) {
                    CTCnf cTCnf = CTTrPrBaseImpl.this.getCnfStyleArray(n);
                    CTTrPrBaseImpl.this.removeCnfStyle(n);
                    return cTCnf;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfCnfStyleArray();
                }
            }
            return new CnfStyleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCnf[] getCnfStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CNFSTYLE$0, arrayList);
            CTCnf[] cTCnfArray = new CTCnf[arrayList.size()];
            arrayList.toArray(cTCnfArray);
            return cTCnfArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCnf getCnfStyleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCnf cTCnf = null;
            cTCnf = (CTCnf)this.get_store().find_element_user(CNFSTYLE$0, n);
            if (cTCnf == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCnf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCnfStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CNFSTYLE$0);
        }
    }

    @Override
    public void setCnfStyleArray(CTCnf[] cTCnfArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTCnfArray, CNFSTYLE$0);
    }

    @Override
    public void setCnfStyleArray(int n, CTCnf cTCnf) {
        this.generatedSetterHelperImpl((XmlObject)cTCnf, CNFSTYLE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCnf insertNewCnfStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCnf cTCnf = null;
            cTCnf = (CTCnf)this.get_store().insert_element_user(CNFSTYLE$0, n);
            return cTCnf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCnf addNewCnfStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCnf cTCnf = null;
            cTCnf = (CTCnf)this.get_store().add_element_user(CNFSTYLE$0);
            return cTCnf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCnfStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CNFSTYLE$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDecimalNumber> getDivIdList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DivIdList
            extends AbstractList<CTDecimalNumber> {
                DivIdList() {
                }

                @Override
                public CTDecimalNumber get(int n) {
                    return CTTrPrBaseImpl.this.getDivIdArray(n);
                }

                @Override
                public CTDecimalNumber set(int n, CTDecimalNumber cTDecimalNumber) {
                    CTDecimalNumber cTDecimalNumber2 = CTTrPrBaseImpl.this.getDivIdArray(n);
                    CTTrPrBaseImpl.this.setDivIdArray(n, cTDecimalNumber);
                    return cTDecimalNumber2;
                }

                @Override
                public void add(int n, CTDecimalNumber cTDecimalNumber) {
                    CTTrPrBaseImpl.this.insertNewDivId(n).set(cTDecimalNumber);
                }

                @Override
                public CTDecimalNumber remove(int n) {
                    CTDecimalNumber cTDecimalNumber = CTTrPrBaseImpl.this.getDivIdArray(n);
                    CTTrPrBaseImpl.this.removeDivId(n);
                    return cTDecimalNumber;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfDivIdArray();
                }
            }
            return new DivIdList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDecimalNumber[] getDivIdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DIVID$2, arrayList);
            CTDecimalNumber[] cTDecimalNumberArray = new CTDecimalNumber[arrayList.size()];
            arrayList.toArray(cTDecimalNumberArray);
            return cTDecimalNumberArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getDivIdArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(DIVID$2, n));
            if (cTDecimalNumber == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDivIdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DIVID$2);
        }
    }

    @Override
    public void setDivIdArray(CTDecimalNumber[] cTDecimalNumberArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDecimalNumberArray, DIVID$2);
    }

    @Override
    public void setDivIdArray(int n, CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, DIVID$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber insertNewDivId(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().insert_element_user(DIVID$2, n));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewDivId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(DIVID$2));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDivId(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DIVID$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDecimalNumber> getGridBeforeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GridBeforeList
            extends AbstractList<CTDecimalNumber> {
                GridBeforeList() {
                }

                @Override
                public CTDecimalNumber get(int n) {
                    return CTTrPrBaseImpl.this.getGridBeforeArray(n);
                }

                @Override
                public CTDecimalNumber set(int n, CTDecimalNumber cTDecimalNumber) {
                    CTDecimalNumber cTDecimalNumber2 = CTTrPrBaseImpl.this.getGridBeforeArray(n);
                    CTTrPrBaseImpl.this.setGridBeforeArray(n, cTDecimalNumber);
                    return cTDecimalNumber2;
                }

                @Override
                public void add(int n, CTDecimalNumber cTDecimalNumber) {
                    CTTrPrBaseImpl.this.insertNewGridBefore(n).set(cTDecimalNumber);
                }

                @Override
                public CTDecimalNumber remove(int n) {
                    CTDecimalNumber cTDecimalNumber = CTTrPrBaseImpl.this.getGridBeforeArray(n);
                    CTTrPrBaseImpl.this.removeGridBefore(n);
                    return cTDecimalNumber;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfGridBeforeArray();
                }
            }
            return new GridBeforeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDecimalNumber[] getGridBeforeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GRIDBEFORE$4, arrayList);
            CTDecimalNumber[] cTDecimalNumberArray = new CTDecimalNumber[arrayList.size()];
            arrayList.toArray(cTDecimalNumberArray);
            return cTDecimalNumberArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getGridBeforeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(GRIDBEFORE$4, n));
            if (cTDecimalNumber == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGridBeforeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRIDBEFORE$4);
        }
    }

    @Override
    public void setGridBeforeArray(CTDecimalNumber[] cTDecimalNumberArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDecimalNumberArray, GRIDBEFORE$4);
    }

    @Override
    public void setGridBeforeArray(int n, CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, GRIDBEFORE$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber insertNewGridBefore(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().insert_element_user(GRIDBEFORE$4, n));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewGridBefore() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(GRIDBEFORE$4));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGridBefore(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRIDBEFORE$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDecimalNumber> getGridAfterList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GridAfterList
            extends AbstractList<CTDecimalNumber> {
                GridAfterList() {
                }

                @Override
                public CTDecimalNumber get(int n) {
                    return CTTrPrBaseImpl.this.getGridAfterArray(n);
                }

                @Override
                public CTDecimalNumber set(int n, CTDecimalNumber cTDecimalNumber) {
                    CTDecimalNumber cTDecimalNumber2 = CTTrPrBaseImpl.this.getGridAfterArray(n);
                    CTTrPrBaseImpl.this.setGridAfterArray(n, cTDecimalNumber);
                    return cTDecimalNumber2;
                }

                @Override
                public void add(int n, CTDecimalNumber cTDecimalNumber) {
                    CTTrPrBaseImpl.this.insertNewGridAfter(n).set(cTDecimalNumber);
                }

                @Override
                public CTDecimalNumber remove(int n) {
                    CTDecimalNumber cTDecimalNumber = CTTrPrBaseImpl.this.getGridAfterArray(n);
                    CTTrPrBaseImpl.this.removeGridAfter(n);
                    return cTDecimalNumber;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfGridAfterArray();
                }
            }
            return new GridAfterList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDecimalNumber[] getGridAfterArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GRIDAFTER$6, arrayList);
            CTDecimalNumber[] cTDecimalNumberArray = new CTDecimalNumber[arrayList.size()];
            arrayList.toArray(cTDecimalNumberArray);
            return cTDecimalNumberArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getGridAfterArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(GRIDAFTER$6, n));
            if (cTDecimalNumber == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGridAfterArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRIDAFTER$6);
        }
    }

    @Override
    public void setGridAfterArray(CTDecimalNumber[] cTDecimalNumberArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDecimalNumberArray, GRIDAFTER$6);
    }

    @Override
    public void setGridAfterArray(int n, CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, GRIDAFTER$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber insertNewGridAfter(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().insert_element_user(GRIDAFTER$6, n));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewGridAfter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(GRIDAFTER$6));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGridAfter(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRIDAFTER$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTblWidth> getWBeforeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class WBeforeList
            extends AbstractList<CTTblWidth> {
                WBeforeList() {
                }

                @Override
                public CTTblWidth get(int n) {
                    return CTTrPrBaseImpl.this.getWBeforeArray(n);
                }

                @Override
                public CTTblWidth set(int n, CTTblWidth cTTblWidth) {
                    CTTblWidth cTTblWidth2 = CTTrPrBaseImpl.this.getWBeforeArray(n);
                    CTTrPrBaseImpl.this.setWBeforeArray(n, cTTblWidth);
                    return cTTblWidth2;
                }

                @Override
                public void add(int n, CTTblWidth cTTblWidth) {
                    CTTrPrBaseImpl.this.insertNewWBefore(n).set(cTTblWidth);
                }

                @Override
                public CTTblWidth remove(int n) {
                    CTTblWidth cTTblWidth = CTTrPrBaseImpl.this.getWBeforeArray(n);
                    CTTrPrBaseImpl.this.removeWBefore(n);
                    return cTTblWidth;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfWBeforeArray();
                }
            }
            return new WBeforeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTblWidth[] getWBeforeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(WBEFORE$8, arrayList);
            CTTblWidth[] cTTblWidthArray = new CTTblWidth[arrayList.size()];
            arrayList.toArray(cTTblWidthArray);
            return cTTblWidthArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getWBeforeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(WBEFORE$8, n));
            if (cTTblWidth == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfWBeforeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WBEFORE$8);
        }
    }

    @Override
    public void setWBeforeArray(CTTblWidth[] cTTblWidthArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTblWidthArray, WBEFORE$8);
    }

    @Override
    public void setWBeforeArray(int n, CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, WBEFORE$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth insertNewWBefore(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().insert_element_user(WBEFORE$8, n));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewWBefore() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(WBEFORE$8));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeWBefore(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WBEFORE$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTblWidth> getWAfterList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class WAfterList
            extends AbstractList<CTTblWidth> {
                WAfterList() {
                }

                @Override
                public CTTblWidth get(int n) {
                    return CTTrPrBaseImpl.this.getWAfterArray(n);
                }

                @Override
                public CTTblWidth set(int n, CTTblWidth cTTblWidth) {
                    CTTblWidth cTTblWidth2 = CTTrPrBaseImpl.this.getWAfterArray(n);
                    CTTrPrBaseImpl.this.setWAfterArray(n, cTTblWidth);
                    return cTTblWidth2;
                }

                @Override
                public void add(int n, CTTblWidth cTTblWidth) {
                    CTTrPrBaseImpl.this.insertNewWAfter(n).set(cTTblWidth);
                }

                @Override
                public CTTblWidth remove(int n) {
                    CTTblWidth cTTblWidth = CTTrPrBaseImpl.this.getWAfterArray(n);
                    CTTrPrBaseImpl.this.removeWAfter(n);
                    return cTTblWidth;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfWAfterArray();
                }
            }
            return new WAfterList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTblWidth[] getWAfterArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(WAFTER$10, arrayList);
            CTTblWidth[] cTTblWidthArray = new CTTblWidth[arrayList.size()];
            arrayList.toArray(cTTblWidthArray);
            return cTTblWidthArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getWAfterArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(WAFTER$10, n));
            if (cTTblWidth == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfWAfterArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WAFTER$10);
        }
    }

    @Override
    public void setWAfterArray(CTTblWidth[] cTTblWidthArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTblWidthArray, WAFTER$10);
    }

    @Override
    public void setWAfterArray(int n, CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, WAFTER$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth insertNewWAfter(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().insert_element_user(WAFTER$10, n));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewWAfter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(WAFTER$10));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeWAfter(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WAFTER$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOnOff> getCantSplitList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CantSplitList
            extends AbstractList<CTOnOff> {
                CantSplitList() {
                }

                @Override
                public CTOnOff get(int n) {
                    return CTTrPrBaseImpl.this.getCantSplitArray(n);
                }

                @Override
                public CTOnOff set(int n, CTOnOff cTOnOff) {
                    CTOnOff cTOnOff2 = CTTrPrBaseImpl.this.getCantSplitArray(n);
                    CTTrPrBaseImpl.this.setCantSplitArray(n, cTOnOff);
                    return cTOnOff2;
                }

                @Override
                public void add(int n, CTOnOff cTOnOff) {
                    CTTrPrBaseImpl.this.insertNewCantSplit(n).set(cTOnOff);
                }

                @Override
                public CTOnOff remove(int n) {
                    CTOnOff cTOnOff = CTTrPrBaseImpl.this.getCantSplitArray(n);
                    CTTrPrBaseImpl.this.removeCantSplit(n);
                    return cTOnOff;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfCantSplitArray();
                }
            }
            return new CantSplitList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOnOff[] getCantSplitArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CANTSPLIT$12, arrayList);
            CTOnOff[] cTOnOffArray = new CTOnOff[arrayList.size()];
            arrayList.toArray(cTOnOffArray);
            return cTOnOffArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getCantSplitArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(CANTSPLIT$12, n));
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCantSplitArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CANTSPLIT$12);
        }
    }

    @Override
    public void setCantSplitArray(CTOnOff[] cTOnOffArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOnOffArray, CANTSPLIT$12);
    }

    @Override
    public void setCantSplitArray(int n, CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, CANTSPLIT$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff insertNewCantSplit(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().insert_element_user(CANTSPLIT$12, n));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewCantSplit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(CANTSPLIT$12));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCantSplit(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CANTSPLIT$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTHeight> getTrHeightList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TrHeightList
            extends AbstractList<CTHeight> {
                TrHeightList() {
                }

                @Override
                public CTHeight get(int n) {
                    return CTTrPrBaseImpl.this.getTrHeightArray(n);
                }

                @Override
                public CTHeight set(int n, CTHeight cTHeight) {
                    CTHeight cTHeight2 = CTTrPrBaseImpl.this.getTrHeightArray(n);
                    CTTrPrBaseImpl.this.setTrHeightArray(n, cTHeight);
                    return cTHeight2;
                }

                @Override
                public void add(int n, CTHeight cTHeight) {
                    CTTrPrBaseImpl.this.insertNewTrHeight(n).set(cTHeight);
                }

                @Override
                public CTHeight remove(int n) {
                    CTHeight cTHeight = CTTrPrBaseImpl.this.getTrHeightArray(n);
                    CTTrPrBaseImpl.this.removeTrHeight(n);
                    return cTHeight;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfTrHeightArray();
                }
            }
            return new TrHeightList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTHeight[] getTrHeightArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TRHEIGHT$14, arrayList);
            CTHeight[] cTHeightArray = new CTHeight[arrayList.size()];
            arrayList.toArray(cTHeightArray);
            return cTHeightArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeight getTrHeightArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeight cTHeight = null;
            cTHeight = (CTHeight)((Object)this.get_store().find_element_user(TRHEIGHT$14, n));
            if (cTHeight == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTHeight;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTrHeightArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TRHEIGHT$14);
        }
    }

    @Override
    public void setTrHeightArray(CTHeight[] cTHeightArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTHeightArray, TRHEIGHT$14);
    }

    @Override
    public void setTrHeightArray(int n, CTHeight cTHeight) {
        this.generatedSetterHelperImpl(cTHeight, TRHEIGHT$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeight insertNewTrHeight(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeight cTHeight = null;
            cTHeight = (CTHeight)((Object)this.get_store().insert_element_user(TRHEIGHT$14, n));
            return cTHeight;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeight addNewTrHeight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeight cTHeight = null;
            cTHeight = (CTHeight)((Object)this.get_store().add_element_user(TRHEIGHT$14));
            return cTHeight;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTrHeight(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TRHEIGHT$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOnOff> getTblHeaderList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TblHeaderList
            extends AbstractList<CTOnOff> {
                TblHeaderList() {
                }

                @Override
                public CTOnOff get(int n) {
                    return CTTrPrBaseImpl.this.getTblHeaderArray(n);
                }

                @Override
                public CTOnOff set(int n, CTOnOff cTOnOff) {
                    CTOnOff cTOnOff2 = CTTrPrBaseImpl.this.getTblHeaderArray(n);
                    CTTrPrBaseImpl.this.setTblHeaderArray(n, cTOnOff);
                    return cTOnOff2;
                }

                @Override
                public void add(int n, CTOnOff cTOnOff) {
                    CTTrPrBaseImpl.this.insertNewTblHeader(n).set(cTOnOff);
                }

                @Override
                public CTOnOff remove(int n) {
                    CTOnOff cTOnOff = CTTrPrBaseImpl.this.getTblHeaderArray(n);
                    CTTrPrBaseImpl.this.removeTblHeader(n);
                    return cTOnOff;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfTblHeaderArray();
                }
            }
            return new TblHeaderList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOnOff[] getTblHeaderArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TBLHEADER$16, arrayList);
            CTOnOff[] cTOnOffArray = new CTOnOff[arrayList.size()];
            arrayList.toArray(cTOnOffArray);
            return cTOnOffArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getTblHeaderArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(TBLHEADER$16, n));
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTblHeaderArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLHEADER$16);
        }
    }

    @Override
    public void setTblHeaderArray(CTOnOff[] cTOnOffArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOnOffArray, TBLHEADER$16);
    }

    @Override
    public void setTblHeaderArray(int n, CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, TBLHEADER$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff insertNewTblHeader(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().insert_element_user(TBLHEADER$16, n));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewTblHeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(TBLHEADER$16));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTblHeader(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLHEADER$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTblWidth> getTblCellSpacingList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TblCellSpacingList
            extends AbstractList<CTTblWidth> {
                TblCellSpacingList() {
                }

                @Override
                public CTTblWidth get(int n) {
                    return CTTrPrBaseImpl.this.getTblCellSpacingArray(n);
                }

                @Override
                public CTTblWidth set(int n, CTTblWidth cTTblWidth) {
                    CTTblWidth cTTblWidth2 = CTTrPrBaseImpl.this.getTblCellSpacingArray(n);
                    CTTrPrBaseImpl.this.setTblCellSpacingArray(n, cTTblWidth);
                    return cTTblWidth2;
                }

                @Override
                public void add(int n, CTTblWidth cTTblWidth) {
                    CTTrPrBaseImpl.this.insertNewTblCellSpacing(n).set(cTTblWidth);
                }

                @Override
                public CTTblWidth remove(int n) {
                    CTTblWidth cTTblWidth = CTTrPrBaseImpl.this.getTblCellSpacingArray(n);
                    CTTrPrBaseImpl.this.removeTblCellSpacing(n);
                    return cTTblWidth;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfTblCellSpacingArray();
                }
            }
            return new TblCellSpacingList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTblWidth[] getTblCellSpacingArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TBLCELLSPACING$18, arrayList);
            CTTblWidth[] cTTblWidthArray = new CTTblWidth[arrayList.size()];
            arrayList.toArray(cTTblWidthArray);
            return cTTblWidthArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getTblCellSpacingArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(TBLCELLSPACING$18, n));
            if (cTTblWidth == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTblCellSpacingArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLCELLSPACING$18);
        }
    }

    @Override
    public void setTblCellSpacingArray(CTTblWidth[] cTTblWidthArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTblWidthArray, TBLCELLSPACING$18);
    }

    @Override
    public void setTblCellSpacingArray(int n, CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, TBLCELLSPACING$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth insertNewTblCellSpacing(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().insert_element_user(TBLCELLSPACING$18, n));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewTblCellSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(TBLCELLSPACING$18));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTblCellSpacing(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLCELLSPACING$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTJc> getJcList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class JcList
            extends AbstractList<CTJc> {
                JcList() {
                }

                @Override
                public CTJc get(int n) {
                    return CTTrPrBaseImpl.this.getJcArray(n);
                }

                @Override
                public CTJc set(int n, CTJc cTJc) {
                    CTJc cTJc2 = CTTrPrBaseImpl.this.getJcArray(n);
                    CTTrPrBaseImpl.this.setJcArray(n, cTJc);
                    return cTJc2;
                }

                @Override
                public void add(int n, CTJc cTJc) {
                    CTTrPrBaseImpl.this.insertNewJc(n).set(cTJc);
                }

                @Override
                public CTJc remove(int n) {
                    CTJc cTJc = CTTrPrBaseImpl.this.getJcArray(n);
                    CTTrPrBaseImpl.this.removeJc(n);
                    return cTJc;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfJcArray();
                }
            }
            return new JcList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTJc[] getJcArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(JC$20, arrayList);
            CTJc[] cTJcArray = new CTJc[arrayList.size()];
            arrayList.toArray(cTJcArray);
            return cTJcArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTJc getJcArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTJc cTJc = null;
            cTJc = (CTJc)((Object)this.get_store().find_element_user(JC$20, n));
            if (cTJc == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfJcArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(JC$20);
        }
    }

    @Override
    public void setJcArray(CTJc[] cTJcArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTJcArray, JC$20);
    }

    @Override
    public void setJcArray(int n, CTJc cTJc) {
        this.generatedSetterHelperImpl(cTJc, JC$20, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTJc insertNewJc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTJc cTJc = null;
            cTJc = (CTJc)((Object)this.get_store().insert_element_user(JC$20, n));
            return cTJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTJc addNewJc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTJc cTJc = null;
            cTJc = (CTJc)((Object)this.get_store().add_element_user(JC$20));
            return cTJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeJc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(JC$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOnOff> getHiddenList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HiddenList
            extends AbstractList<CTOnOff> {
                HiddenList() {
                }

                @Override
                public CTOnOff get(int n) {
                    return CTTrPrBaseImpl.this.getHiddenArray(n);
                }

                @Override
                public CTOnOff set(int n, CTOnOff cTOnOff) {
                    CTOnOff cTOnOff2 = CTTrPrBaseImpl.this.getHiddenArray(n);
                    CTTrPrBaseImpl.this.setHiddenArray(n, cTOnOff);
                    return cTOnOff2;
                }

                @Override
                public void add(int n, CTOnOff cTOnOff) {
                    CTTrPrBaseImpl.this.insertNewHidden(n).set(cTOnOff);
                }

                @Override
                public CTOnOff remove(int n) {
                    CTOnOff cTOnOff = CTTrPrBaseImpl.this.getHiddenArray(n);
                    CTTrPrBaseImpl.this.removeHidden(n);
                    return cTOnOff;
                }

                @Override
                public int size() {
                    return CTTrPrBaseImpl.this.sizeOfHiddenArray();
                }
            }
            return new HiddenList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOnOff[] getHiddenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HIDDEN$22, arrayList);
            CTOnOff[] cTOnOffArray = new CTOnOff[arrayList.size()];
            arrayList.toArray(cTOnOffArray);
            return cTOnOffArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getHiddenArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(HIDDEN$22, n));
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHiddenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HIDDEN$22);
        }
    }

    @Override
    public void setHiddenArray(CTOnOff[] cTOnOffArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOnOffArray, HIDDEN$22);
    }

    @Override
    public void setHiddenArray(int n, CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, HIDDEN$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff insertNewHidden(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().insert_element_user(HIDDEN$22, n));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(HIDDEN$22));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHidden(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HIDDEN$22, n);
        }
    }
}

