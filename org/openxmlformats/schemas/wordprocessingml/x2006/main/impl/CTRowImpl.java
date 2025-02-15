/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlCell
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrEx;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

public class CTRowImpl
extends XmlComplexContentImpl
implements CTRow {
    private static final long serialVersionUID = 1L;
    private static final QName TBLPREX$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblPrEx");
    private static final QName TRPR$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "trPr");
    private static final QName TC$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tc");
    private static final QName CUSTOMXML$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXml");
    private static final QName SDT$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdt");
    private static final QName PROOFERR$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "proofErr");
    private static final QName PERMSTART$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permStart");
    private static final QName PERMEND$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permEnd");
    private static final QName BOOKMARKSTART$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkStart");
    private static final QName BOOKMARKEND$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkEnd");
    private static final QName MOVEFROMRANGESTART$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeStart");
    private static final QName MOVEFROMRANGEEND$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeEnd");
    private static final QName MOVETORANGESTART$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeStart");
    private static final QName MOVETORANGEEND$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeEnd");
    private static final QName COMMENTRANGESTART$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeStart");
    private static final QName COMMENTRANGEEND$30 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeEnd");
    private static final QName CUSTOMXMLINSRANGESTART$32 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeStart");
    private static final QName CUSTOMXMLINSRANGEEND$34 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeEnd");
    private static final QName CUSTOMXMLDELRANGESTART$36 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeStart");
    private static final QName CUSTOMXMLDELRANGEEND$38 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeEnd");
    private static final QName CUSTOMXMLMOVEFROMRANGESTART$40 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeStart");
    private static final QName CUSTOMXMLMOVEFROMRANGEEND$42 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeEnd");
    private static final QName CUSTOMXMLMOVETORANGESTART$44 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeStart");
    private static final QName CUSTOMXMLMOVETORANGEEND$46 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeEnd");
    private static final QName INS$48 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ins");
    private static final QName DEL$50 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "del");
    private static final QName MOVEFROM$52 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFrom");
    private static final QName MOVETO$54 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveTo");
    private static final QName OMATHPARA$56 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMathPara");
    private static final QName OMATH$58 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMath");
    private static final QName RSIDRPR$60 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidRPr");
    private static final QName RSIDR$62 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidR");
    private static final QName RSIDDEL$64 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidDel");
    private static final QName RSIDTR$66 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidTr");

    public CTRowImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblPrEx getTblPrEx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblPrEx cTTblPrEx = null;
            cTTblPrEx = (CTTblPrEx)((Object)this.get_store().find_element_user(TBLPREX$0, 0));
            if (cTTblPrEx == null) {
                return null;
            }
            return cTTblPrEx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblPrEx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLPREX$0) != 0;
        }
    }

    @Override
    public void setTblPrEx(CTTblPrEx cTTblPrEx) {
        this.generatedSetterHelperImpl(cTTblPrEx, TBLPREX$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblPrEx addNewTblPrEx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblPrEx cTTblPrEx = null;
            cTTblPrEx = (CTTblPrEx)((Object)this.get_store().add_element_user(TBLPREX$0));
            return cTTblPrEx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblPrEx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLPREX$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrPr getTrPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrPr cTTrPr = null;
            cTTrPr = (CTTrPr)((Object)this.get_store().find_element_user(TRPR$2, 0));
            if (cTTrPr == null) {
                return null;
            }
            return cTTrPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTrPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TRPR$2) != 0;
        }
    }

    @Override
    public void setTrPr(CTTrPr cTTrPr) {
        this.generatedSetterHelperImpl(cTTrPr, TRPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrPr addNewTrPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrPr cTTrPr = null;
            cTTrPr = (CTTrPr)((Object)this.get_store().add_element_user(TRPR$2));
            return cTTrPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTrPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TRPR$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTc> getTcList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TcList
            extends AbstractList<CTTc> {
                TcList() {
                }

                @Override
                public CTTc get(int n) {
                    return CTRowImpl.this.getTcArray(n);
                }

                @Override
                public CTTc set(int n, CTTc cTTc) {
                    CTTc cTTc2 = CTRowImpl.this.getTcArray(n);
                    CTRowImpl.this.setTcArray(n, cTTc);
                    return cTTc2;
                }

                @Override
                public void add(int n, CTTc cTTc) {
                    CTRowImpl.this.insertNewTc(n).set(cTTc);
                }

                @Override
                public CTTc remove(int n) {
                    CTTc cTTc = CTRowImpl.this.getTcArray(n);
                    CTRowImpl.this.removeTc(n);
                    return cTTc;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfTcArray();
                }
            }
            return new TcList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTc[] getTcArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TC$4, arrayList);
            CTTc[] cTTcArray = new CTTc[arrayList.size()];
            arrayList.toArray(cTTcArray);
            return cTTcArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTc getTcArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTc cTTc = null;
            cTTc = (CTTc)((Object)this.get_store().find_element_user(TC$4, n));
            if (cTTc == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTcArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TC$4);
        }
    }

    @Override
    public void setTcArray(CTTc[] cTTcArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTcArray, TC$4);
    }

    @Override
    public void setTcArray(int n, CTTc cTTc) {
        this.generatedSetterHelperImpl(cTTc, TC$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTc insertNewTc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTc cTTc = null;
            cTTc = (CTTc)((Object)this.get_store().insert_element_user(TC$4, n));
            return cTTc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTc addNewTc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTc cTTc = null;
            cTTc = (CTTc)((Object)this.get_store().add_element_user(TC$4));
            return cTTc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TC$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCustomXmlCell> getCustomXmlList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustomXmlList
            extends AbstractList<CTCustomXmlCell> {
                CustomXmlList() {
                }

                @Override
                public CTCustomXmlCell get(int n) {
                    return CTRowImpl.this.getCustomXmlArray(n);
                }

                @Override
                public CTCustomXmlCell set(int n, CTCustomXmlCell cTCustomXmlCell) {
                    CTCustomXmlCell cTCustomXmlCell2 = CTRowImpl.this.getCustomXmlArray(n);
                    CTRowImpl.this.setCustomXmlArray(n, cTCustomXmlCell);
                    return cTCustomXmlCell2;
                }

                @Override
                public void add(int n, CTCustomXmlCell cTCustomXmlCell) {
                    CTRowImpl.this.insertNewCustomXml(n).set((XmlObject)cTCustomXmlCell);
                }

                @Override
                public CTCustomXmlCell remove(int n) {
                    CTCustomXmlCell cTCustomXmlCell = CTRowImpl.this.getCustomXmlArray(n);
                    CTRowImpl.this.removeCustomXml(n);
                    return cTCustomXmlCell;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCustomXmlArray();
                }
            }
            return new CustomXmlList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCustomXmlCell[] getCustomXmlArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTOMXML$6, arrayList);
            CTCustomXmlCell[] cTCustomXmlCellArray = new CTCustomXmlCell[arrayList.size()];
            arrayList.toArray(cTCustomXmlCellArray);
            return cTCustomXmlCellArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomXmlCell getCustomXmlArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomXmlCell cTCustomXmlCell = null;
            cTCustomXmlCell = (CTCustomXmlCell)this.get_store().find_element_user(CUSTOMXML$6, n);
            if (cTCustomXmlCell == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCustomXmlCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCustomXmlArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMXML$6);
        }
    }

    @Override
    public void setCustomXmlArray(CTCustomXmlCell[] cTCustomXmlCellArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTCustomXmlCellArray, CUSTOMXML$6);
    }

    @Override
    public void setCustomXmlArray(int n, CTCustomXmlCell cTCustomXmlCell) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomXmlCell, CUSTOMXML$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomXmlCell insertNewCustomXml(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomXmlCell cTCustomXmlCell = null;
            cTCustomXmlCell = (CTCustomXmlCell)this.get_store().insert_element_user(CUSTOMXML$6, n);
            return cTCustomXmlCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomXmlCell addNewCustomXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomXmlCell cTCustomXmlCell = null;
            cTCustomXmlCell = (CTCustomXmlCell)this.get_store().add_element_user(CUSTOMXML$6);
            return cTCustomXmlCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCustomXml(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMXML$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSdtCell> getSdtList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SdtList
            extends AbstractList<CTSdtCell> {
                SdtList() {
                }

                @Override
                public CTSdtCell get(int n) {
                    return CTRowImpl.this.getSdtArray(n);
                }

                @Override
                public CTSdtCell set(int n, CTSdtCell cTSdtCell) {
                    CTSdtCell cTSdtCell2 = CTRowImpl.this.getSdtArray(n);
                    CTRowImpl.this.setSdtArray(n, cTSdtCell);
                    return cTSdtCell2;
                }

                @Override
                public void add(int n, CTSdtCell cTSdtCell) {
                    CTRowImpl.this.insertNewSdt(n).set(cTSdtCell);
                }

                @Override
                public CTSdtCell remove(int n) {
                    CTSdtCell cTSdtCell = CTRowImpl.this.getSdtArray(n);
                    CTRowImpl.this.removeSdt(n);
                    return cTSdtCell;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfSdtArray();
                }
            }
            return new SdtList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSdtCell[] getSdtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SDT$8, arrayList);
            CTSdtCell[] cTSdtCellArray = new CTSdtCell[arrayList.size()];
            arrayList.toArray(cTSdtCellArray);
            return cTSdtCellArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtCell getSdtArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtCell cTSdtCell = null;
            cTSdtCell = (CTSdtCell)((Object)this.get_store().find_element_user(SDT$8, n));
            if (cTSdtCell == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSdtCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSdtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SDT$8);
        }
    }

    @Override
    public void setSdtArray(CTSdtCell[] cTSdtCellArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSdtCellArray, SDT$8);
    }

    @Override
    public void setSdtArray(int n, CTSdtCell cTSdtCell) {
        this.generatedSetterHelperImpl(cTSdtCell, SDT$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtCell insertNewSdt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtCell cTSdtCell = null;
            cTSdtCell = (CTSdtCell)((Object)this.get_store().insert_element_user(SDT$8, n));
            return cTSdtCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtCell addNewSdt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtCell cTSdtCell = null;
            cTSdtCell = (CTSdtCell)((Object)this.get_store().add_element_user(SDT$8));
            return cTSdtCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSdt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SDT$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTProofErr> getProofErrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ProofErrList
            extends AbstractList<CTProofErr> {
                ProofErrList() {
                }

                @Override
                public CTProofErr get(int n) {
                    return CTRowImpl.this.getProofErrArray(n);
                }

                @Override
                public CTProofErr set(int n, CTProofErr cTProofErr) {
                    CTProofErr cTProofErr2 = CTRowImpl.this.getProofErrArray(n);
                    CTRowImpl.this.setProofErrArray(n, cTProofErr);
                    return cTProofErr2;
                }

                @Override
                public void add(int n, CTProofErr cTProofErr) {
                    CTRowImpl.this.insertNewProofErr(n).set(cTProofErr);
                }

                @Override
                public CTProofErr remove(int n) {
                    CTProofErr cTProofErr = CTRowImpl.this.getProofErrArray(n);
                    CTRowImpl.this.removeProofErr(n);
                    return cTProofErr;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfProofErrArray();
                }
            }
            return new ProofErrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTProofErr[] getProofErrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PROOFERR$10, arrayList);
            CTProofErr[] cTProofErrArray = new CTProofErr[arrayList.size()];
            arrayList.toArray(cTProofErrArray);
            return cTProofErrArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProofErr getProofErrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProofErr cTProofErr = null;
            cTProofErr = (CTProofErr)((Object)this.get_store().find_element_user(PROOFERR$10, n));
            if (cTProofErr == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTProofErr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfProofErrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PROOFERR$10);
        }
    }

    @Override
    public void setProofErrArray(CTProofErr[] cTProofErrArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTProofErrArray, PROOFERR$10);
    }

    @Override
    public void setProofErrArray(int n, CTProofErr cTProofErr) {
        this.generatedSetterHelperImpl(cTProofErr, PROOFERR$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProofErr insertNewProofErr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProofErr cTProofErr = null;
            cTProofErr = (CTProofErr)((Object)this.get_store().insert_element_user(PROOFERR$10, n));
            return cTProofErr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProofErr addNewProofErr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProofErr cTProofErr = null;
            cTProofErr = (CTProofErr)((Object)this.get_store().add_element_user(PROOFERR$10));
            return cTProofErr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeProofErr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PROOFERR$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPermStart> getPermStartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PermStartList
            extends AbstractList<CTPermStart> {
                PermStartList() {
                }

                @Override
                public CTPermStart get(int n) {
                    return CTRowImpl.this.getPermStartArray(n);
                }

                @Override
                public CTPermStart set(int n, CTPermStart cTPermStart) {
                    CTPermStart cTPermStart2 = CTRowImpl.this.getPermStartArray(n);
                    CTRowImpl.this.setPermStartArray(n, cTPermStart);
                    return cTPermStart2;
                }

                @Override
                public void add(int n, CTPermStart cTPermStart) {
                    CTRowImpl.this.insertNewPermStart(n).set((XmlObject)cTPermStart);
                }

                @Override
                public CTPermStart remove(int n) {
                    CTPermStart cTPermStart = CTRowImpl.this.getPermStartArray(n);
                    CTRowImpl.this.removePermStart(n);
                    return cTPermStart;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfPermStartArray();
                }
            }
            return new PermStartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPermStart[] getPermStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PERMSTART$12, arrayList);
            CTPermStart[] cTPermStartArray = new CTPermStart[arrayList.size()];
            arrayList.toArray(cTPermStartArray);
            return cTPermStartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPermStart getPermStartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPermStart cTPermStart = null;
            cTPermStart = (CTPermStart)this.get_store().find_element_user(PERMSTART$12, n);
            if (cTPermStart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPermStart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPermStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PERMSTART$12);
        }
    }

    @Override
    public void setPermStartArray(CTPermStart[] cTPermStartArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPermStartArray, PERMSTART$12);
    }

    @Override
    public void setPermStartArray(int n, CTPermStart cTPermStart) {
        this.generatedSetterHelperImpl((XmlObject)cTPermStart, PERMSTART$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPermStart insertNewPermStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPermStart cTPermStart = null;
            cTPermStart = (CTPermStart)this.get_store().insert_element_user(PERMSTART$12, n);
            return cTPermStart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPermStart addNewPermStart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPermStart cTPermStart = null;
            cTPermStart = (CTPermStart)this.get_store().add_element_user(PERMSTART$12);
            return cTPermStart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePermStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PERMSTART$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPerm> getPermEndList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PermEndList
            extends AbstractList<CTPerm> {
                PermEndList() {
                }

                @Override
                public CTPerm get(int n) {
                    return CTRowImpl.this.getPermEndArray(n);
                }

                @Override
                public CTPerm set(int n, CTPerm cTPerm) {
                    CTPerm cTPerm2 = CTRowImpl.this.getPermEndArray(n);
                    CTRowImpl.this.setPermEndArray(n, cTPerm);
                    return cTPerm2;
                }

                @Override
                public void add(int n, CTPerm cTPerm) {
                    CTRowImpl.this.insertNewPermEnd(n).set((XmlObject)cTPerm);
                }

                @Override
                public CTPerm remove(int n) {
                    CTPerm cTPerm = CTRowImpl.this.getPermEndArray(n);
                    CTRowImpl.this.removePermEnd(n);
                    return cTPerm;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfPermEndArray();
                }
            }
            return new PermEndList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPerm[] getPermEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PERMEND$14, arrayList);
            CTPerm[] cTPermArray = new CTPerm[arrayList.size()];
            arrayList.toArray(cTPermArray);
            return cTPermArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPerm getPermEndArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPerm cTPerm = null;
            cTPerm = (CTPerm)this.get_store().find_element_user(PERMEND$14, n);
            if (cTPerm == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPerm;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPermEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PERMEND$14);
        }
    }

    @Override
    public void setPermEndArray(CTPerm[] cTPermArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPermArray, PERMEND$14);
    }

    @Override
    public void setPermEndArray(int n, CTPerm cTPerm) {
        this.generatedSetterHelperImpl((XmlObject)cTPerm, PERMEND$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPerm insertNewPermEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPerm cTPerm = null;
            cTPerm = (CTPerm)this.get_store().insert_element_user(PERMEND$14, n);
            return cTPerm;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPerm addNewPermEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPerm cTPerm = null;
            cTPerm = (CTPerm)this.get_store().add_element_user(PERMEND$14);
            return cTPerm;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePermEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PERMEND$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBookmark> getBookmarkStartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BookmarkStartList
            extends AbstractList<CTBookmark> {
                BookmarkStartList() {
                }

                @Override
                public CTBookmark get(int n) {
                    return CTRowImpl.this.getBookmarkStartArray(n);
                }

                @Override
                public CTBookmark set(int n, CTBookmark cTBookmark) {
                    CTBookmark cTBookmark2 = CTRowImpl.this.getBookmarkStartArray(n);
                    CTRowImpl.this.setBookmarkStartArray(n, cTBookmark);
                    return cTBookmark2;
                }

                @Override
                public void add(int n, CTBookmark cTBookmark) {
                    CTRowImpl.this.insertNewBookmarkStart(n).set(cTBookmark);
                }

                @Override
                public CTBookmark remove(int n) {
                    CTBookmark cTBookmark = CTRowImpl.this.getBookmarkStartArray(n);
                    CTRowImpl.this.removeBookmarkStart(n);
                    return cTBookmark;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfBookmarkStartArray();
                }
            }
            return new BookmarkStartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBookmark[] getBookmarkStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BOOKMARKSTART$16, arrayList);
            CTBookmark[] cTBookmarkArray = new CTBookmark[arrayList.size()];
            arrayList.toArray(cTBookmarkArray);
            return cTBookmarkArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBookmark getBookmarkStartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBookmark cTBookmark = null;
            cTBookmark = (CTBookmark)((Object)this.get_store().find_element_user(BOOKMARKSTART$16, n));
            if (cTBookmark == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBookmarkStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOOKMARKSTART$16);
        }
    }

    @Override
    public void setBookmarkStartArray(CTBookmark[] cTBookmarkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBookmarkArray, BOOKMARKSTART$16);
    }

    @Override
    public void setBookmarkStartArray(int n, CTBookmark cTBookmark) {
        this.generatedSetterHelperImpl(cTBookmark, BOOKMARKSTART$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBookmark insertNewBookmarkStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBookmark cTBookmark = null;
            cTBookmark = (CTBookmark)((Object)this.get_store().insert_element_user(BOOKMARKSTART$16, n));
            return cTBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBookmark addNewBookmarkStart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBookmark cTBookmark = null;
            cTBookmark = (CTBookmark)((Object)this.get_store().add_element_user(BOOKMARKSTART$16));
            return cTBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBookmarkStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOOKMARKSTART$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMarkupRange> getBookmarkEndList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BookmarkEndList
            extends AbstractList<CTMarkupRange> {
                BookmarkEndList() {
                }

                @Override
                public CTMarkupRange get(int n) {
                    return CTRowImpl.this.getBookmarkEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRowImpl.this.getBookmarkEndArray(n);
                    CTRowImpl.this.setBookmarkEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRowImpl.this.insertNewBookmarkEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRowImpl.this.getBookmarkEndArray(n);
                    CTRowImpl.this.removeBookmarkEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfBookmarkEndArray();
                }
            }
            return new BookmarkEndList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMarkupRange[] getBookmarkEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BOOKMARKEND$18, arrayList);
            CTMarkupRange[] cTMarkupRangeArray = new CTMarkupRange[arrayList.size()];
            arrayList.toArray(cTMarkupRangeArray);
            return cTMarkupRangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange getBookmarkEndArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(BOOKMARKEND$18, n));
            if (cTMarkupRange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBookmarkEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOOKMARKEND$18);
        }
    }

    @Override
    public void setBookmarkEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, BOOKMARKEND$18);
    }

    @Override
    public void setBookmarkEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, BOOKMARKEND$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange insertNewBookmarkEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(BOOKMARKEND$18, n));
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange addNewBookmarkEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(BOOKMARKEND$18));
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBookmarkEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOOKMARKEND$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMoveBookmark> getMoveFromRangeStartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MoveFromRangeStartList
            extends AbstractList<CTMoveBookmark> {
                MoveFromRangeStartList() {
                }

                @Override
                public CTMoveBookmark get(int n) {
                    return CTRowImpl.this.getMoveFromRangeStartArray(n);
                }

                @Override
                public CTMoveBookmark set(int n, CTMoveBookmark cTMoveBookmark) {
                    CTMoveBookmark cTMoveBookmark2 = CTRowImpl.this.getMoveFromRangeStartArray(n);
                    CTRowImpl.this.setMoveFromRangeStartArray(n, cTMoveBookmark);
                    return cTMoveBookmark2;
                }

                @Override
                public void add(int n, CTMoveBookmark cTMoveBookmark) {
                    CTRowImpl.this.insertNewMoveFromRangeStart(n).set(cTMoveBookmark);
                }

                @Override
                public CTMoveBookmark remove(int n) {
                    CTMoveBookmark cTMoveBookmark = CTRowImpl.this.getMoveFromRangeStartArray(n);
                    CTRowImpl.this.removeMoveFromRangeStart(n);
                    return cTMoveBookmark;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfMoveFromRangeStartArray();
                }
            }
            return new MoveFromRangeStartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMoveBookmark[] getMoveFromRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MOVEFROMRANGESTART$20, arrayList);
            CTMoveBookmark[] cTMoveBookmarkArray = new CTMoveBookmark[arrayList.size()];
            arrayList.toArray(cTMoveBookmarkArray);
            return cTMoveBookmarkArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMoveBookmark getMoveFromRangeStartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMoveBookmark cTMoveBookmark = null;
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().find_element_user(MOVEFROMRANGESTART$20, n));
            if (cTMoveBookmark == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMoveBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMoveFromRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MOVEFROMRANGESTART$20);
        }
    }

    @Override
    public void setMoveFromRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMoveBookmarkArray, MOVEFROMRANGESTART$20);
    }

    @Override
    public void setMoveFromRangeStartArray(int n, CTMoveBookmark cTMoveBookmark) {
        this.generatedSetterHelperImpl(cTMoveBookmark, MOVEFROMRANGESTART$20, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMoveBookmark insertNewMoveFromRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMoveBookmark cTMoveBookmark = null;
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().insert_element_user(MOVEFROMRANGESTART$20, n));
            return cTMoveBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMoveBookmark addNewMoveFromRangeStart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMoveBookmark cTMoveBookmark = null;
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().add_element_user(MOVEFROMRANGESTART$20));
            return cTMoveBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMoveFromRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MOVEFROMRANGESTART$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMarkupRange> getMoveFromRangeEndList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MoveFromRangeEndList
            extends AbstractList<CTMarkupRange> {
                MoveFromRangeEndList() {
                }

                @Override
                public CTMarkupRange get(int n) {
                    return CTRowImpl.this.getMoveFromRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRowImpl.this.getMoveFromRangeEndArray(n);
                    CTRowImpl.this.setMoveFromRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRowImpl.this.insertNewMoveFromRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRowImpl.this.getMoveFromRangeEndArray(n);
                    CTRowImpl.this.removeMoveFromRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfMoveFromRangeEndArray();
                }
            }
            return new MoveFromRangeEndList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMarkupRange[] getMoveFromRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MOVEFROMRANGEEND$22, arrayList);
            CTMarkupRange[] cTMarkupRangeArray = new CTMarkupRange[arrayList.size()];
            arrayList.toArray(cTMarkupRangeArray);
            return cTMarkupRangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange getMoveFromRangeEndArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(MOVEFROMRANGEEND$22, n));
            if (cTMarkupRange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMoveFromRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MOVEFROMRANGEEND$22);
        }
    }

    @Override
    public void setMoveFromRangeEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, MOVEFROMRANGEEND$22);
    }

    @Override
    public void setMoveFromRangeEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, MOVEFROMRANGEEND$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange insertNewMoveFromRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(MOVEFROMRANGEEND$22, n));
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange addNewMoveFromRangeEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(MOVEFROMRANGEEND$22));
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMoveFromRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MOVEFROMRANGEEND$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMoveBookmark> getMoveToRangeStartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MoveToRangeStartList
            extends AbstractList<CTMoveBookmark> {
                MoveToRangeStartList() {
                }

                @Override
                public CTMoveBookmark get(int n) {
                    return CTRowImpl.this.getMoveToRangeStartArray(n);
                }

                @Override
                public CTMoveBookmark set(int n, CTMoveBookmark cTMoveBookmark) {
                    CTMoveBookmark cTMoveBookmark2 = CTRowImpl.this.getMoveToRangeStartArray(n);
                    CTRowImpl.this.setMoveToRangeStartArray(n, cTMoveBookmark);
                    return cTMoveBookmark2;
                }

                @Override
                public void add(int n, CTMoveBookmark cTMoveBookmark) {
                    CTRowImpl.this.insertNewMoveToRangeStart(n).set(cTMoveBookmark);
                }

                @Override
                public CTMoveBookmark remove(int n) {
                    CTMoveBookmark cTMoveBookmark = CTRowImpl.this.getMoveToRangeStartArray(n);
                    CTRowImpl.this.removeMoveToRangeStart(n);
                    return cTMoveBookmark;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfMoveToRangeStartArray();
                }
            }
            return new MoveToRangeStartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMoveBookmark[] getMoveToRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MOVETORANGESTART$24, arrayList);
            CTMoveBookmark[] cTMoveBookmarkArray = new CTMoveBookmark[arrayList.size()];
            arrayList.toArray(cTMoveBookmarkArray);
            return cTMoveBookmarkArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMoveBookmark getMoveToRangeStartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMoveBookmark cTMoveBookmark = null;
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().find_element_user(MOVETORANGESTART$24, n));
            if (cTMoveBookmark == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMoveBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMoveToRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MOVETORANGESTART$24);
        }
    }

    @Override
    public void setMoveToRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMoveBookmarkArray, MOVETORANGESTART$24);
    }

    @Override
    public void setMoveToRangeStartArray(int n, CTMoveBookmark cTMoveBookmark) {
        this.generatedSetterHelperImpl(cTMoveBookmark, MOVETORANGESTART$24, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMoveBookmark insertNewMoveToRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMoveBookmark cTMoveBookmark = null;
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().insert_element_user(MOVETORANGESTART$24, n));
            return cTMoveBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMoveBookmark addNewMoveToRangeStart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMoveBookmark cTMoveBookmark = null;
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().add_element_user(MOVETORANGESTART$24));
            return cTMoveBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMoveToRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MOVETORANGESTART$24, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMarkupRange> getMoveToRangeEndList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MoveToRangeEndList
            extends AbstractList<CTMarkupRange> {
                MoveToRangeEndList() {
                }

                @Override
                public CTMarkupRange get(int n) {
                    return CTRowImpl.this.getMoveToRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRowImpl.this.getMoveToRangeEndArray(n);
                    CTRowImpl.this.setMoveToRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRowImpl.this.insertNewMoveToRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRowImpl.this.getMoveToRangeEndArray(n);
                    CTRowImpl.this.removeMoveToRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfMoveToRangeEndArray();
                }
            }
            return new MoveToRangeEndList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMarkupRange[] getMoveToRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MOVETORANGEEND$26, arrayList);
            CTMarkupRange[] cTMarkupRangeArray = new CTMarkupRange[arrayList.size()];
            arrayList.toArray(cTMarkupRangeArray);
            return cTMarkupRangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange getMoveToRangeEndArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(MOVETORANGEEND$26, n));
            if (cTMarkupRange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMoveToRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MOVETORANGEEND$26);
        }
    }

    @Override
    public void setMoveToRangeEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, MOVETORANGEEND$26);
    }

    @Override
    public void setMoveToRangeEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, MOVETORANGEEND$26, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange insertNewMoveToRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(MOVETORANGEEND$26, n));
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange addNewMoveToRangeEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(MOVETORANGEEND$26));
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMoveToRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MOVETORANGEEND$26, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMarkupRange> getCommentRangeStartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CommentRangeStartList
            extends AbstractList<CTMarkupRange> {
                CommentRangeStartList() {
                }

                @Override
                public CTMarkupRange get(int n) {
                    return CTRowImpl.this.getCommentRangeStartArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRowImpl.this.getCommentRangeStartArray(n);
                    CTRowImpl.this.setCommentRangeStartArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRowImpl.this.insertNewCommentRangeStart(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRowImpl.this.getCommentRangeStartArray(n);
                    CTRowImpl.this.removeCommentRangeStart(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCommentRangeStartArray();
                }
            }
            return new CommentRangeStartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMarkupRange[] getCommentRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COMMENTRANGESTART$28, arrayList);
            CTMarkupRange[] cTMarkupRangeArray = new CTMarkupRange[arrayList.size()];
            arrayList.toArray(cTMarkupRangeArray);
            return cTMarkupRangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange getCommentRangeStartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(COMMENTRANGESTART$28, n));
            if (cTMarkupRange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCommentRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMMENTRANGESTART$28);
        }
    }

    @Override
    public void setCommentRangeStartArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, COMMENTRANGESTART$28);
    }

    @Override
    public void setCommentRangeStartArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, COMMENTRANGESTART$28, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange insertNewCommentRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(COMMENTRANGESTART$28, n));
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange addNewCommentRangeStart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(COMMENTRANGESTART$28));
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCommentRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMMENTRANGESTART$28, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMarkupRange> getCommentRangeEndList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CommentRangeEndList
            extends AbstractList<CTMarkupRange> {
                CommentRangeEndList() {
                }

                @Override
                public CTMarkupRange get(int n) {
                    return CTRowImpl.this.getCommentRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRowImpl.this.getCommentRangeEndArray(n);
                    CTRowImpl.this.setCommentRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRowImpl.this.insertNewCommentRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRowImpl.this.getCommentRangeEndArray(n);
                    CTRowImpl.this.removeCommentRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCommentRangeEndArray();
                }
            }
            return new CommentRangeEndList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMarkupRange[] getCommentRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COMMENTRANGEEND$30, arrayList);
            CTMarkupRange[] cTMarkupRangeArray = new CTMarkupRange[arrayList.size()];
            arrayList.toArray(cTMarkupRangeArray);
            return cTMarkupRangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange getCommentRangeEndArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(COMMENTRANGEEND$30, n));
            if (cTMarkupRange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCommentRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMMENTRANGEEND$30);
        }
    }

    @Override
    public void setCommentRangeEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, COMMENTRANGEEND$30);
    }

    @Override
    public void setCommentRangeEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, COMMENTRANGEEND$30, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange insertNewCommentRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(COMMENTRANGEEND$30, n));
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkupRange addNewCommentRangeEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkupRange cTMarkupRange = null;
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(COMMENTRANGEEND$30));
            return cTMarkupRange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCommentRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMMENTRANGEEND$30, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTrackChange> getCustomXmlInsRangeStartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustomXmlInsRangeStartList
            extends AbstractList<CTTrackChange> {
                CustomXmlInsRangeStartList() {
                }

                @Override
                public CTTrackChange get(int n) {
                    return CTRowImpl.this.getCustomXmlInsRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRowImpl.this.getCustomXmlInsRangeStartArray(n);
                    CTRowImpl.this.setCustomXmlInsRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRowImpl.this.insertNewCustomXmlInsRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRowImpl.this.getCustomXmlInsRangeStartArray(n);
                    CTRowImpl.this.removeCustomXmlInsRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCustomXmlInsRangeStartArray();
                }
            }
            return new CustomXmlInsRangeStartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTrackChange[] getCustomXmlInsRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTOMXMLINSRANGESTART$32, arrayList);
            CTTrackChange[] cTTrackChangeArray = new CTTrackChange[arrayList.size()];
            arrayList.toArray(cTTrackChangeArray);
            return cTTrackChangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange getCustomXmlInsRangeStartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLINSRANGESTART$32, n));
            if (cTTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCustomXmlInsRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMXMLINSRANGESTART$32);
        }
    }

    @Override
    public void setCustomXmlInsRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLINSRANGESTART$32);
    }

    @Override
    public void setCustomXmlInsRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLINSRANGESTART$32, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange insertNewCustomXmlInsRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLINSRANGESTART$32, n));
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange addNewCustomXmlInsRangeStart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLINSRANGESTART$32));
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCustomXmlInsRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMXMLINSRANGESTART$32, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMarkup> getCustomXmlInsRangeEndList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustomXmlInsRangeEndList
            extends AbstractList<CTMarkup> {
                CustomXmlInsRangeEndList() {
                }

                @Override
                public CTMarkup get(int n) {
                    return CTRowImpl.this.getCustomXmlInsRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRowImpl.this.getCustomXmlInsRangeEndArray(n);
                    CTRowImpl.this.setCustomXmlInsRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRowImpl.this.insertNewCustomXmlInsRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRowImpl.this.getCustomXmlInsRangeEndArray(n);
                    CTRowImpl.this.removeCustomXmlInsRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCustomXmlInsRangeEndArray();
                }
            }
            return new CustomXmlInsRangeEndList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMarkup[] getCustomXmlInsRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTOMXMLINSRANGEEND$34, arrayList);
            CTMarkup[] cTMarkupArray = new CTMarkup[arrayList.size()];
            arrayList.toArray(cTMarkupArray);
            return cTMarkupArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup getCustomXmlInsRangeEndArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLINSRANGEEND$34, n));
            if (cTMarkup == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCustomXmlInsRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMXMLINSRANGEEND$34);
        }
    }

    @Override
    public void setCustomXmlInsRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLINSRANGEEND$34);
    }

    @Override
    public void setCustomXmlInsRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLINSRANGEEND$34, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup insertNewCustomXmlInsRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLINSRANGEEND$34, n));
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup addNewCustomXmlInsRangeEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLINSRANGEEND$34));
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCustomXmlInsRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMXMLINSRANGEEND$34, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTrackChange> getCustomXmlDelRangeStartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustomXmlDelRangeStartList
            extends AbstractList<CTTrackChange> {
                CustomXmlDelRangeStartList() {
                }

                @Override
                public CTTrackChange get(int n) {
                    return CTRowImpl.this.getCustomXmlDelRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRowImpl.this.getCustomXmlDelRangeStartArray(n);
                    CTRowImpl.this.setCustomXmlDelRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRowImpl.this.insertNewCustomXmlDelRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRowImpl.this.getCustomXmlDelRangeStartArray(n);
                    CTRowImpl.this.removeCustomXmlDelRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCustomXmlDelRangeStartArray();
                }
            }
            return new CustomXmlDelRangeStartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTrackChange[] getCustomXmlDelRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTOMXMLDELRANGESTART$36, arrayList);
            CTTrackChange[] cTTrackChangeArray = new CTTrackChange[arrayList.size()];
            arrayList.toArray(cTTrackChangeArray);
            return cTTrackChangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange getCustomXmlDelRangeStartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLDELRANGESTART$36, n));
            if (cTTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCustomXmlDelRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMXMLDELRANGESTART$36);
        }
    }

    @Override
    public void setCustomXmlDelRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLDELRANGESTART$36);
    }

    @Override
    public void setCustomXmlDelRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLDELRANGESTART$36, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange insertNewCustomXmlDelRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLDELRANGESTART$36, n));
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange addNewCustomXmlDelRangeStart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLDELRANGESTART$36));
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCustomXmlDelRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMXMLDELRANGESTART$36, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMarkup> getCustomXmlDelRangeEndList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustomXmlDelRangeEndList
            extends AbstractList<CTMarkup> {
                CustomXmlDelRangeEndList() {
                }

                @Override
                public CTMarkup get(int n) {
                    return CTRowImpl.this.getCustomXmlDelRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRowImpl.this.getCustomXmlDelRangeEndArray(n);
                    CTRowImpl.this.setCustomXmlDelRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRowImpl.this.insertNewCustomXmlDelRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRowImpl.this.getCustomXmlDelRangeEndArray(n);
                    CTRowImpl.this.removeCustomXmlDelRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCustomXmlDelRangeEndArray();
                }
            }
            return new CustomXmlDelRangeEndList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMarkup[] getCustomXmlDelRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTOMXMLDELRANGEEND$38, arrayList);
            CTMarkup[] cTMarkupArray = new CTMarkup[arrayList.size()];
            arrayList.toArray(cTMarkupArray);
            return cTMarkupArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup getCustomXmlDelRangeEndArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLDELRANGEEND$38, n));
            if (cTMarkup == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCustomXmlDelRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMXMLDELRANGEEND$38);
        }
    }

    @Override
    public void setCustomXmlDelRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLDELRANGEEND$38);
    }

    @Override
    public void setCustomXmlDelRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLDELRANGEEND$38, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup insertNewCustomXmlDelRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLDELRANGEEND$38, n));
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup addNewCustomXmlDelRangeEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLDELRANGEEND$38));
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCustomXmlDelRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMXMLDELRANGEEND$38, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTrackChange> getCustomXmlMoveFromRangeStartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustomXmlMoveFromRangeStartList
            extends AbstractList<CTTrackChange> {
                CustomXmlMoveFromRangeStartList() {
                }

                @Override
                public CTTrackChange get(int n) {
                    return CTRowImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRowImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                    CTRowImpl.this.setCustomXmlMoveFromRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRowImpl.this.insertNewCustomXmlMoveFromRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRowImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                    CTRowImpl.this.removeCustomXmlMoveFromRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCustomXmlMoveFromRangeStartArray();
                }
            }
            return new CustomXmlMoveFromRangeStartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTrackChange[] getCustomXmlMoveFromRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTOMXMLMOVEFROMRANGESTART$40, arrayList);
            CTTrackChange[] cTTrackChangeArray = new CTTrackChange[arrayList.size()];
            arrayList.toArray(cTTrackChangeArray);
            return cTTrackChangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange getCustomXmlMoveFromRangeStartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLMOVEFROMRANGESTART$40, n));
            if (cTTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCustomXmlMoveFromRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMXMLMOVEFROMRANGESTART$40);
        }
    }

    @Override
    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLMOVEFROMRANGESTART$40);
    }

    @Override
    public void setCustomXmlMoveFromRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLMOVEFROMRANGESTART$40, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange insertNewCustomXmlMoveFromRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVEFROMRANGESTART$40, n));
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange addNewCustomXmlMoveFromRangeStart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLMOVEFROMRANGESTART$40));
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCustomXmlMoveFromRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMXMLMOVEFROMRANGESTART$40, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMarkup> getCustomXmlMoveFromRangeEndList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustomXmlMoveFromRangeEndList
            extends AbstractList<CTMarkup> {
                CustomXmlMoveFromRangeEndList() {
                }

                @Override
                public CTMarkup get(int n) {
                    return CTRowImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRowImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                    CTRowImpl.this.setCustomXmlMoveFromRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRowImpl.this.insertNewCustomXmlMoveFromRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRowImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                    CTRowImpl.this.removeCustomXmlMoveFromRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCustomXmlMoveFromRangeEndArray();
                }
            }
            return new CustomXmlMoveFromRangeEndList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMarkup[] getCustomXmlMoveFromRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTOMXMLMOVEFROMRANGEEND$42, arrayList);
            CTMarkup[] cTMarkupArray = new CTMarkup[arrayList.size()];
            arrayList.toArray(cTMarkupArray);
            return cTMarkupArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup getCustomXmlMoveFromRangeEndArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLMOVEFROMRANGEEND$42, n));
            if (cTMarkup == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCustomXmlMoveFromRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMXMLMOVEFROMRANGEEND$42);
        }
    }

    @Override
    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLMOVEFROMRANGEEND$42);
    }

    @Override
    public void setCustomXmlMoveFromRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLMOVEFROMRANGEEND$42, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup insertNewCustomXmlMoveFromRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVEFROMRANGEEND$42, n));
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup addNewCustomXmlMoveFromRangeEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLMOVEFROMRANGEEND$42));
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCustomXmlMoveFromRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMXMLMOVEFROMRANGEEND$42, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTrackChange> getCustomXmlMoveToRangeStartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustomXmlMoveToRangeStartList
            extends AbstractList<CTTrackChange> {
                CustomXmlMoveToRangeStartList() {
                }

                @Override
                public CTTrackChange get(int n) {
                    return CTRowImpl.this.getCustomXmlMoveToRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRowImpl.this.getCustomXmlMoveToRangeStartArray(n);
                    CTRowImpl.this.setCustomXmlMoveToRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRowImpl.this.insertNewCustomXmlMoveToRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRowImpl.this.getCustomXmlMoveToRangeStartArray(n);
                    CTRowImpl.this.removeCustomXmlMoveToRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCustomXmlMoveToRangeStartArray();
                }
            }
            return new CustomXmlMoveToRangeStartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTrackChange[] getCustomXmlMoveToRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTOMXMLMOVETORANGESTART$44, arrayList);
            CTTrackChange[] cTTrackChangeArray = new CTTrackChange[arrayList.size()];
            arrayList.toArray(cTTrackChangeArray);
            return cTTrackChangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange getCustomXmlMoveToRangeStartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLMOVETORANGESTART$44, n));
            if (cTTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCustomXmlMoveToRangeStartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMXMLMOVETORANGESTART$44);
        }
    }

    @Override
    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLMOVETORANGESTART$44);
    }

    @Override
    public void setCustomXmlMoveToRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLMOVETORANGESTART$44, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange insertNewCustomXmlMoveToRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVETORANGESTART$44, n));
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange addNewCustomXmlMoveToRangeStart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLMOVETORANGESTART$44));
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCustomXmlMoveToRangeStart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMXMLMOVETORANGESTART$44, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMarkup> getCustomXmlMoveToRangeEndList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustomXmlMoveToRangeEndList
            extends AbstractList<CTMarkup> {
                CustomXmlMoveToRangeEndList() {
                }

                @Override
                public CTMarkup get(int n) {
                    return CTRowImpl.this.getCustomXmlMoveToRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRowImpl.this.getCustomXmlMoveToRangeEndArray(n);
                    CTRowImpl.this.setCustomXmlMoveToRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRowImpl.this.insertNewCustomXmlMoveToRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRowImpl.this.getCustomXmlMoveToRangeEndArray(n);
                    CTRowImpl.this.removeCustomXmlMoveToRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfCustomXmlMoveToRangeEndArray();
                }
            }
            return new CustomXmlMoveToRangeEndList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMarkup[] getCustomXmlMoveToRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTOMXMLMOVETORANGEEND$46, arrayList);
            CTMarkup[] cTMarkupArray = new CTMarkup[arrayList.size()];
            arrayList.toArray(cTMarkupArray);
            return cTMarkupArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup getCustomXmlMoveToRangeEndArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLMOVETORANGEEND$46, n));
            if (cTMarkup == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCustomXmlMoveToRangeEndArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMXMLMOVETORANGEEND$46);
        }
    }

    @Override
    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLMOVETORANGEEND$46);
    }

    @Override
    public void setCustomXmlMoveToRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLMOVETORANGEEND$46, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup insertNewCustomXmlMoveToRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVETORANGEEND$46, n));
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup addNewCustomXmlMoveToRangeEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLMOVETORANGEEND$46));
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCustomXmlMoveToRangeEnd(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMXMLMOVETORANGEEND$46, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRunTrackChange> getInsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class InsList
            extends AbstractList<CTRunTrackChange> {
                InsList() {
                }

                @Override
                public CTRunTrackChange get(int n) {
                    return CTRowImpl.this.getInsArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRowImpl.this.getInsArray(n);
                    CTRowImpl.this.setInsArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRowImpl.this.insertNewIns(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRowImpl.this.getInsArray(n);
                    CTRowImpl.this.removeIns(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfInsArray();
                }
            }
            return new InsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRunTrackChange[] getInsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(INS$48, arrayList);
            CTRunTrackChange[] cTRunTrackChangeArray = new CTRunTrackChange[arrayList.size()];
            arrayList.toArray(cTRunTrackChangeArray);
            return cTRunTrackChangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange getInsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(INS$48, n));
            if (cTRunTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfInsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INS$48);
        }
    }

    @Override
    public void setInsArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, INS$48);
    }

    @Override
    public void setInsArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, INS$48, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange insertNewIns(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(INS$48, n));
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange addNewIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(INS$48));
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeIns(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INS$48, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRunTrackChange> getDelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DelList
            extends AbstractList<CTRunTrackChange> {
                DelList() {
                }

                @Override
                public CTRunTrackChange get(int n) {
                    return CTRowImpl.this.getDelArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRowImpl.this.getDelArray(n);
                    CTRowImpl.this.setDelArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRowImpl.this.insertNewDel(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRowImpl.this.getDelArray(n);
                    CTRowImpl.this.removeDel(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfDelArray();
                }
            }
            return new DelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRunTrackChange[] getDelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DEL$50, arrayList);
            CTRunTrackChange[] cTRunTrackChangeArray = new CTRunTrackChange[arrayList.size()];
            arrayList.toArray(cTRunTrackChangeArray);
            return cTRunTrackChangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange getDelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(DEL$50, n));
            if (cTRunTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEL$50);
        }
    }

    @Override
    public void setDelArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, DEL$50);
    }

    @Override
    public void setDelArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, DEL$50, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange insertNewDel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(DEL$50, n));
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange addNewDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(DEL$50));
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEL$50, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRunTrackChange> getMoveFromList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MoveFromList
            extends AbstractList<CTRunTrackChange> {
                MoveFromList() {
                }

                @Override
                public CTRunTrackChange get(int n) {
                    return CTRowImpl.this.getMoveFromArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRowImpl.this.getMoveFromArray(n);
                    CTRowImpl.this.setMoveFromArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRowImpl.this.insertNewMoveFrom(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRowImpl.this.getMoveFromArray(n);
                    CTRowImpl.this.removeMoveFrom(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfMoveFromArray();
                }
            }
            return new MoveFromList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRunTrackChange[] getMoveFromArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MOVEFROM$52, arrayList);
            CTRunTrackChange[] cTRunTrackChangeArray = new CTRunTrackChange[arrayList.size()];
            arrayList.toArray(cTRunTrackChangeArray);
            return cTRunTrackChangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange getMoveFromArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(MOVEFROM$52, n));
            if (cTRunTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMoveFromArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MOVEFROM$52);
        }
    }

    @Override
    public void setMoveFromArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, MOVEFROM$52);
    }

    @Override
    public void setMoveFromArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, MOVEFROM$52, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange insertNewMoveFrom(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(MOVEFROM$52, n));
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange addNewMoveFrom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(MOVEFROM$52));
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMoveFrom(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MOVEFROM$52, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRunTrackChange> getMoveToList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MoveToList
            extends AbstractList<CTRunTrackChange> {
                MoveToList() {
                }

                @Override
                public CTRunTrackChange get(int n) {
                    return CTRowImpl.this.getMoveToArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRowImpl.this.getMoveToArray(n);
                    CTRowImpl.this.setMoveToArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRowImpl.this.insertNewMoveTo(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRowImpl.this.getMoveToArray(n);
                    CTRowImpl.this.removeMoveTo(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfMoveToArray();
                }
            }
            return new MoveToList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRunTrackChange[] getMoveToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MOVETO$54, arrayList);
            CTRunTrackChange[] cTRunTrackChangeArray = new CTRunTrackChange[arrayList.size()];
            arrayList.toArray(cTRunTrackChangeArray);
            return cTRunTrackChangeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange getMoveToArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(MOVETO$54, n));
            if (cTRunTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMoveToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MOVETO$54);
        }
    }

    @Override
    public void setMoveToArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, MOVETO$54);
    }

    @Override
    public void setMoveToArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, MOVETO$54, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange insertNewMoveTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(MOVETO$54, n));
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRunTrackChange addNewMoveTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRunTrackChange cTRunTrackChange = null;
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(MOVETO$54));
            return cTRunTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMoveTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MOVETO$54, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOMathPara> getOMathParaList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class OMathParaList
            extends AbstractList<CTOMathPara> {
                OMathParaList() {
                }

                @Override
                public CTOMathPara get(int n) {
                    return CTRowImpl.this.getOMathParaArray(n);
                }

                @Override
                public CTOMathPara set(int n, CTOMathPara cTOMathPara) {
                    CTOMathPara cTOMathPara2 = CTRowImpl.this.getOMathParaArray(n);
                    CTRowImpl.this.setOMathParaArray(n, cTOMathPara);
                    return cTOMathPara2;
                }

                @Override
                public void add(int n, CTOMathPara cTOMathPara) {
                    CTRowImpl.this.insertNewOMathPara(n).set((XmlObject)cTOMathPara);
                }

                @Override
                public CTOMathPara remove(int n) {
                    CTOMathPara cTOMathPara = CTRowImpl.this.getOMathParaArray(n);
                    CTRowImpl.this.removeOMathPara(n);
                    return cTOMathPara;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfOMathParaArray();
                }
            }
            return new OMathParaList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOMathPara[] getOMathParaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OMATHPARA$56, arrayList);
            CTOMathPara[] cTOMathParaArray = new CTOMathPara[arrayList.size()];
            arrayList.toArray(cTOMathParaArray);
            return cTOMathParaArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOMathPara getOMathParaArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOMathPara cTOMathPara = null;
            cTOMathPara = (CTOMathPara)this.get_store().find_element_user(OMATHPARA$56, n);
            if (cTOMathPara == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOMathPara;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfOMathParaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OMATHPARA$56);
        }
    }

    @Override
    public void setOMathParaArray(CTOMathPara[] cTOMathParaArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTOMathParaArray, OMATHPARA$56);
    }

    @Override
    public void setOMathParaArray(int n, CTOMathPara cTOMathPara) {
        this.generatedSetterHelperImpl((XmlObject)cTOMathPara, OMATHPARA$56, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOMathPara insertNewOMathPara(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOMathPara cTOMathPara = null;
            cTOMathPara = (CTOMathPara)this.get_store().insert_element_user(OMATHPARA$56, n);
            return cTOMathPara;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOMathPara addNewOMathPara() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOMathPara cTOMathPara = null;
            cTOMathPara = (CTOMathPara)this.get_store().add_element_user(OMATHPARA$56);
            return cTOMathPara;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeOMathPara(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OMATHPARA$56, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOMath> getOMathList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class OMathList
            extends AbstractList<CTOMath> {
                OMathList() {
                }

                @Override
                public CTOMath get(int n) {
                    return CTRowImpl.this.getOMathArray(n);
                }

                @Override
                public CTOMath set(int n, CTOMath cTOMath) {
                    CTOMath cTOMath2 = CTRowImpl.this.getOMathArray(n);
                    CTRowImpl.this.setOMathArray(n, cTOMath);
                    return cTOMath2;
                }

                @Override
                public void add(int n, CTOMath cTOMath) {
                    CTRowImpl.this.insertNewOMath(n).set((XmlObject)cTOMath);
                }

                @Override
                public CTOMath remove(int n) {
                    CTOMath cTOMath = CTRowImpl.this.getOMathArray(n);
                    CTRowImpl.this.removeOMath(n);
                    return cTOMath;
                }

                @Override
                public int size() {
                    return CTRowImpl.this.sizeOfOMathArray();
                }
            }
            return new OMathList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOMath[] getOMathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OMATH$58, arrayList);
            CTOMath[] cTOMathArray = new CTOMath[arrayList.size()];
            arrayList.toArray(cTOMathArray);
            return cTOMathArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOMath getOMathArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOMath cTOMath = null;
            cTOMath = (CTOMath)this.get_store().find_element_user(OMATH$58, n);
            if (cTOMath == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOMath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfOMathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OMATH$58);
        }
    }

    @Override
    public void setOMathArray(CTOMath[] cTOMathArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTOMathArray, OMATH$58);
    }

    @Override
    public void setOMathArray(int n, CTOMath cTOMath) {
        this.generatedSetterHelperImpl((XmlObject)cTOMath, OMATH$58, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOMath insertNewOMath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOMath cTOMath = null;
            cTOMath = (CTOMath)this.get_store().insert_element_user(OMATH$58, n);
            return cTOMath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOMath addNewOMath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOMath cTOMath = null;
            cTOMath = (CTOMath)this.get_store().add_element_user(OMATH$58);
            return cTOMath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeOMath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OMATH$58, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getRsidRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDRPR$60));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLongHexNumber xgetRsidRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber = null;
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDRPR$60));
            return sTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRsidRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RSIDRPR$60) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRsidRPr(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDRPR$60));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDRPR$60));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRsidRPr(STLongHexNumber sTLongHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber2 = null;
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDRPR$60));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDRPR$60));
            }
            sTLongHexNumber2.set(sTLongHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRsidRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RSIDRPR$60);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getRsidR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDR$62));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLongHexNumber xgetRsidR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber = null;
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDR$62));
            return sTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRsidR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RSIDR$62) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRsidR(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDR$62));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDR$62));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRsidR(STLongHexNumber sTLongHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber2 = null;
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDR$62));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDR$62));
            }
            sTLongHexNumber2.set(sTLongHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRsidR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RSIDR$62);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getRsidDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDDEL$64));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLongHexNumber xgetRsidDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber = null;
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDDEL$64));
            return sTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRsidDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RSIDDEL$64) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRsidDel(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDDEL$64));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDDEL$64));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRsidDel(STLongHexNumber sTLongHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber2 = null;
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDDEL$64));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDDEL$64));
            }
            sTLongHexNumber2.set(sTLongHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRsidDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RSIDDEL$64);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getRsidTr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDTR$66));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLongHexNumber xgetRsidTr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber = null;
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDTR$66));
            return sTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRsidTr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RSIDTR$66) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRsidTr(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDTR$66));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDTR$66));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRsidTr(STLongHexNumber sTLongHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber2 = null;
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDTR$66));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDTR$66));
            }
            sTLongHexNumber2.set(sTLongHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRsidTr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RSIDTR$66);
        }
    }
}

