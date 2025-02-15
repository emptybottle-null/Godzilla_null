/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTAcc
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTBar
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTBorderBox
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTBox
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTD
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArr
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTF
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTFunc
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTGroupChr
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTLimLow
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTLimUpp
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTM
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTNary
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTPhant
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTR
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTRad
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTSPre
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubSup
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTSSup
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRun
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTAcc;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTBar;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTBorderBox;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTBox;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTD;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTF;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTFunc;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTGroupChr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTLimLow;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTLimUpp;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTM;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTNary;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTPhant;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTRad;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSPre;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubSup;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrackChangeImpl;

public class CTRunTrackChangeImpl
extends CTTrackChangeImpl
implements CTRunTrackChange {
    private static final long serialVersionUID = 1L;
    private static final QName CUSTOMXML$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXml");
    private static final QName SMARTTAG$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "smartTag");
    private static final QName SDT$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdt");
    private static final QName R$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "r");
    private static final QName PROOFERR$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "proofErr");
    private static final QName PERMSTART$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permStart");
    private static final QName PERMEND$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permEnd");
    private static final QName BOOKMARKSTART$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkStart");
    private static final QName BOOKMARKEND$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkEnd");
    private static final QName MOVEFROMRANGESTART$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeStart");
    private static final QName MOVEFROMRANGEEND$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeEnd");
    private static final QName MOVETORANGESTART$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeStart");
    private static final QName MOVETORANGEEND$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeEnd");
    private static final QName COMMENTRANGESTART$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeStart");
    private static final QName COMMENTRANGEEND$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeEnd");
    private static final QName CUSTOMXMLINSRANGESTART$30 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeStart");
    private static final QName CUSTOMXMLINSRANGEEND$32 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeEnd");
    private static final QName CUSTOMXMLDELRANGESTART$34 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeStart");
    private static final QName CUSTOMXMLDELRANGEEND$36 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeEnd");
    private static final QName CUSTOMXMLMOVEFROMRANGESTART$38 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeStart");
    private static final QName CUSTOMXMLMOVEFROMRANGEEND$40 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeEnd");
    private static final QName CUSTOMXMLMOVETORANGESTART$42 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeStart");
    private static final QName CUSTOMXMLMOVETORANGEEND$44 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeEnd");
    private static final QName INS$46 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ins");
    private static final QName DEL$48 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "del");
    private static final QName MOVEFROM$50 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFrom");
    private static final QName MOVETO$52 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveTo");
    private static final QName OMATHPARA$54 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMathPara");
    private static final QName OMATH$56 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMath");
    private static final QName ACC$58 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "acc");
    private static final QName BAR$60 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "bar");
    private static final QName BOX$62 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "box");
    private static final QName BORDERBOX$64 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "borderBox");
    private static final QName D$66 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "d");
    private static final QName EQARR$68 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "eqArr");
    private static final QName F$70 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "f");
    private static final QName FUNC$72 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "func");
    private static final QName GROUPCHR$74 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "groupChr");
    private static final QName LIMLOW$76 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "limLow");
    private static final QName LIMUPP$78 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "limUpp");
    private static final QName M$80 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "m");
    private static final QName NARY$82 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "nary");
    private static final QName PHANT$84 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "phant");
    private static final QName RAD$86 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "rad");
    private static final QName SPRE$88 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sPre");
    private static final QName SSUB$90 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSub");
    private static final QName SSUBSUP$92 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSubSup");
    private static final QName SSUP$94 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSup");
    private static final QName R2$96 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "r");

    public CTRunTrackChangeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCustomXmlRun> getCustomXmlList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustomXmlList
            extends AbstractList<CTCustomXmlRun> {
                CustomXmlList() {
                }

                @Override
                public CTCustomXmlRun get(int n) {
                    return CTRunTrackChangeImpl.this.getCustomXmlArray(n);
                }

                @Override
                public CTCustomXmlRun set(int n, CTCustomXmlRun cTCustomXmlRun) {
                    CTCustomXmlRun cTCustomXmlRun2 = CTRunTrackChangeImpl.this.getCustomXmlArray(n);
                    CTRunTrackChangeImpl.this.setCustomXmlArray(n, cTCustomXmlRun);
                    return cTCustomXmlRun2;
                }

                @Override
                public void add(int n, CTCustomXmlRun cTCustomXmlRun) {
                    CTRunTrackChangeImpl.this.insertNewCustomXml(n).set((XmlObject)cTCustomXmlRun);
                }

                @Override
                public CTCustomXmlRun remove(int n) {
                    CTCustomXmlRun cTCustomXmlRun = CTRunTrackChangeImpl.this.getCustomXmlArray(n);
                    CTRunTrackChangeImpl.this.removeCustomXml(n);
                    return cTCustomXmlRun;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCustomXmlArray();
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
    public CTCustomXmlRun[] getCustomXmlArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTOMXML$0, arrayList);
            CTCustomXmlRun[] cTCustomXmlRunArray = new CTCustomXmlRun[arrayList.size()];
            arrayList.toArray(cTCustomXmlRunArray);
            return cTCustomXmlRunArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomXmlRun getCustomXmlArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomXmlRun cTCustomXmlRun = null;
            cTCustomXmlRun = (CTCustomXmlRun)this.get_store().find_element_user(CUSTOMXML$0, n);
            if (cTCustomXmlRun == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCustomXmlRun;
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
            return this.get_store().count_elements(CUSTOMXML$0);
        }
    }

    @Override
    public void setCustomXmlArray(CTCustomXmlRun[] cTCustomXmlRunArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTCustomXmlRunArray, CUSTOMXML$0);
    }

    @Override
    public void setCustomXmlArray(int n, CTCustomXmlRun cTCustomXmlRun) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomXmlRun, CUSTOMXML$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomXmlRun insertNewCustomXml(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomXmlRun cTCustomXmlRun = null;
            cTCustomXmlRun = (CTCustomXmlRun)this.get_store().insert_element_user(CUSTOMXML$0, n);
            return cTCustomXmlRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomXmlRun addNewCustomXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomXmlRun cTCustomXmlRun = null;
            cTCustomXmlRun = (CTCustomXmlRun)this.get_store().add_element_user(CUSTOMXML$0);
            return cTCustomXmlRun;
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
            this.get_store().remove_element(CUSTOMXML$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSmartTagRun> getSmartTagList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SmartTagList
            extends AbstractList<CTSmartTagRun> {
                SmartTagList() {
                }

                @Override
                public CTSmartTagRun get(int n) {
                    return CTRunTrackChangeImpl.this.getSmartTagArray(n);
                }

                @Override
                public CTSmartTagRun set(int n, CTSmartTagRun cTSmartTagRun) {
                    CTSmartTagRun cTSmartTagRun2 = CTRunTrackChangeImpl.this.getSmartTagArray(n);
                    CTRunTrackChangeImpl.this.setSmartTagArray(n, cTSmartTagRun);
                    return cTSmartTagRun2;
                }

                @Override
                public void add(int n, CTSmartTagRun cTSmartTagRun) {
                    CTRunTrackChangeImpl.this.insertNewSmartTag(n).set(cTSmartTagRun);
                }

                @Override
                public CTSmartTagRun remove(int n) {
                    CTSmartTagRun cTSmartTagRun = CTRunTrackChangeImpl.this.getSmartTagArray(n);
                    CTRunTrackChangeImpl.this.removeSmartTag(n);
                    return cTSmartTagRun;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfSmartTagArray();
                }
            }
            return new SmartTagList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSmartTagRun[] getSmartTagArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SMARTTAG$2, arrayList);
            CTSmartTagRun[] cTSmartTagRunArray = new CTSmartTagRun[arrayList.size()];
            arrayList.toArray(cTSmartTagRunArray);
            return cTSmartTagRunArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTagRun getSmartTagArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTagRun cTSmartTagRun = null;
            cTSmartTagRun = (CTSmartTagRun)((Object)this.get_store().find_element_user(SMARTTAG$2, n));
            if (cTSmartTagRun == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSmartTagRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSmartTagArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SMARTTAG$2);
        }
    }

    @Override
    public void setSmartTagArray(CTSmartTagRun[] cTSmartTagRunArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSmartTagRunArray, SMARTTAG$2);
    }

    @Override
    public void setSmartTagArray(int n, CTSmartTagRun cTSmartTagRun) {
        this.generatedSetterHelperImpl(cTSmartTagRun, SMARTTAG$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTagRun insertNewSmartTag(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTagRun cTSmartTagRun = null;
            cTSmartTagRun = (CTSmartTagRun)((Object)this.get_store().insert_element_user(SMARTTAG$2, n));
            return cTSmartTagRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTagRun addNewSmartTag() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTagRun cTSmartTagRun = null;
            cTSmartTagRun = (CTSmartTagRun)((Object)this.get_store().add_element_user(SMARTTAG$2));
            return cTSmartTagRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSmartTag(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SMARTTAG$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSdtRun> getSdtList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SdtList
            extends AbstractList<CTSdtRun> {
                SdtList() {
                }

                @Override
                public CTSdtRun get(int n) {
                    return CTRunTrackChangeImpl.this.getSdtArray(n);
                }

                @Override
                public CTSdtRun set(int n, CTSdtRun cTSdtRun) {
                    CTSdtRun cTSdtRun2 = CTRunTrackChangeImpl.this.getSdtArray(n);
                    CTRunTrackChangeImpl.this.setSdtArray(n, cTSdtRun);
                    return cTSdtRun2;
                }

                @Override
                public void add(int n, CTSdtRun cTSdtRun) {
                    CTRunTrackChangeImpl.this.insertNewSdt(n).set(cTSdtRun);
                }

                @Override
                public CTSdtRun remove(int n) {
                    CTSdtRun cTSdtRun = CTRunTrackChangeImpl.this.getSdtArray(n);
                    CTRunTrackChangeImpl.this.removeSdt(n);
                    return cTSdtRun;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfSdtArray();
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
    public CTSdtRun[] getSdtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SDT$4, arrayList);
            CTSdtRun[] cTSdtRunArray = new CTSdtRun[arrayList.size()];
            arrayList.toArray(cTSdtRunArray);
            return cTSdtRunArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtRun getSdtArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtRun cTSdtRun = null;
            cTSdtRun = (CTSdtRun)((Object)this.get_store().find_element_user(SDT$4, n));
            if (cTSdtRun == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSdtRun;
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
            return this.get_store().count_elements(SDT$4);
        }
    }

    @Override
    public void setSdtArray(CTSdtRun[] cTSdtRunArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSdtRunArray, SDT$4);
    }

    @Override
    public void setSdtArray(int n, CTSdtRun cTSdtRun) {
        this.generatedSetterHelperImpl(cTSdtRun, SDT$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtRun insertNewSdt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtRun cTSdtRun = null;
            cTSdtRun = (CTSdtRun)((Object)this.get_store().insert_element_user(SDT$4, n));
            return cTSdtRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtRun addNewSdt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtRun cTSdtRun = null;
            cTSdtRun = (CTSdtRun)((Object)this.get_store().add_element_user(SDT$4));
            return cTSdtRun;
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
            this.get_store().remove_element(SDT$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTR> getRList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RList
            extends AbstractList<CTR> {
                RList() {
                }

                @Override
                public CTR get(int n) {
                    return CTRunTrackChangeImpl.this.getRArray(n);
                }

                @Override
                public CTR set(int n, CTR cTR) {
                    CTR cTR2 = CTRunTrackChangeImpl.this.getRArray(n);
                    CTRunTrackChangeImpl.this.setRArray(n, cTR);
                    return cTR2;
                }

                @Override
                public void add(int n, CTR cTR) {
                    CTRunTrackChangeImpl.this.insertNewR(n).set(cTR);
                }

                @Override
                public CTR remove(int n) {
                    CTR cTR = CTRunTrackChangeImpl.this.getRArray(n);
                    CTRunTrackChangeImpl.this.removeR(n);
                    return cTR;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfRArray();
                }
            }
            return new RList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTR[] getRArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(R$6, arrayList);
            CTR[] cTRArray = new CTR[arrayList.size()];
            arrayList.toArray(cTRArray);
            return cTRArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTR getRArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTR cTR = null;
            cTR = (CTR)((Object)this.get_store().find_element_user(R$6, n));
            if (cTR == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTR;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(R$6);
        }
    }

    @Override
    public void setRArray(CTR[] cTRArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRArray, R$6);
    }

    @Override
    public void setRArray(int n, CTR cTR) {
        this.generatedSetterHelperImpl(cTR, R$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTR insertNewR(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTR cTR = null;
            cTR = (CTR)((Object)this.get_store().insert_element_user(R$6, n));
            return cTR;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTR addNewR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTR cTR = null;
            cTR = (CTR)((Object)this.get_store().add_element_user(R$6));
            return cTR;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeR(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(R$6, n);
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
                    return CTRunTrackChangeImpl.this.getProofErrArray(n);
                }

                @Override
                public CTProofErr set(int n, CTProofErr cTProofErr) {
                    CTProofErr cTProofErr2 = CTRunTrackChangeImpl.this.getProofErrArray(n);
                    CTRunTrackChangeImpl.this.setProofErrArray(n, cTProofErr);
                    return cTProofErr2;
                }

                @Override
                public void add(int n, CTProofErr cTProofErr) {
                    CTRunTrackChangeImpl.this.insertNewProofErr(n).set(cTProofErr);
                }

                @Override
                public CTProofErr remove(int n) {
                    CTProofErr cTProofErr = CTRunTrackChangeImpl.this.getProofErrArray(n);
                    CTRunTrackChangeImpl.this.removeProofErr(n);
                    return cTProofErr;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfProofErrArray();
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
            this.get_store().find_all_element_users(PROOFERR$8, arrayList);
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
            cTProofErr = (CTProofErr)((Object)this.get_store().find_element_user(PROOFERR$8, n));
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
            return this.get_store().count_elements(PROOFERR$8);
        }
    }

    @Override
    public void setProofErrArray(CTProofErr[] cTProofErrArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTProofErrArray, PROOFERR$8);
    }

    @Override
    public void setProofErrArray(int n, CTProofErr cTProofErr) {
        this.generatedSetterHelperImpl(cTProofErr, PROOFERR$8, n, (short)2);
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
            cTProofErr = (CTProofErr)((Object)this.get_store().insert_element_user(PROOFERR$8, n));
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
            cTProofErr = (CTProofErr)((Object)this.get_store().add_element_user(PROOFERR$8));
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
            this.get_store().remove_element(PROOFERR$8, n);
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
                    return CTRunTrackChangeImpl.this.getPermStartArray(n);
                }

                @Override
                public CTPermStart set(int n, CTPermStart cTPermStart) {
                    CTPermStart cTPermStart2 = CTRunTrackChangeImpl.this.getPermStartArray(n);
                    CTRunTrackChangeImpl.this.setPermStartArray(n, cTPermStart);
                    return cTPermStart2;
                }

                @Override
                public void add(int n, CTPermStart cTPermStart) {
                    CTRunTrackChangeImpl.this.insertNewPermStart(n).set((XmlObject)cTPermStart);
                }

                @Override
                public CTPermStart remove(int n) {
                    CTPermStart cTPermStart = CTRunTrackChangeImpl.this.getPermStartArray(n);
                    CTRunTrackChangeImpl.this.removePermStart(n);
                    return cTPermStart;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfPermStartArray();
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
            this.get_store().find_all_element_users(PERMSTART$10, arrayList);
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
            cTPermStart = (CTPermStart)this.get_store().find_element_user(PERMSTART$10, n);
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
            return this.get_store().count_elements(PERMSTART$10);
        }
    }

    @Override
    public void setPermStartArray(CTPermStart[] cTPermStartArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPermStartArray, PERMSTART$10);
    }

    @Override
    public void setPermStartArray(int n, CTPermStart cTPermStart) {
        this.generatedSetterHelperImpl((XmlObject)cTPermStart, PERMSTART$10, n, (short)2);
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
            cTPermStart = (CTPermStart)this.get_store().insert_element_user(PERMSTART$10, n);
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
            cTPermStart = (CTPermStart)this.get_store().add_element_user(PERMSTART$10);
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
            this.get_store().remove_element(PERMSTART$10, n);
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
                    return CTRunTrackChangeImpl.this.getPermEndArray(n);
                }

                @Override
                public CTPerm set(int n, CTPerm cTPerm) {
                    CTPerm cTPerm2 = CTRunTrackChangeImpl.this.getPermEndArray(n);
                    CTRunTrackChangeImpl.this.setPermEndArray(n, cTPerm);
                    return cTPerm2;
                }

                @Override
                public void add(int n, CTPerm cTPerm) {
                    CTRunTrackChangeImpl.this.insertNewPermEnd(n).set((XmlObject)cTPerm);
                }

                @Override
                public CTPerm remove(int n) {
                    CTPerm cTPerm = CTRunTrackChangeImpl.this.getPermEndArray(n);
                    CTRunTrackChangeImpl.this.removePermEnd(n);
                    return cTPerm;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfPermEndArray();
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
            this.get_store().find_all_element_users(PERMEND$12, arrayList);
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
            cTPerm = (CTPerm)this.get_store().find_element_user(PERMEND$12, n);
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
            return this.get_store().count_elements(PERMEND$12);
        }
    }

    @Override
    public void setPermEndArray(CTPerm[] cTPermArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPermArray, PERMEND$12);
    }

    @Override
    public void setPermEndArray(int n, CTPerm cTPerm) {
        this.generatedSetterHelperImpl((XmlObject)cTPerm, PERMEND$12, n, (short)2);
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
            cTPerm = (CTPerm)this.get_store().insert_element_user(PERMEND$12, n);
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
            cTPerm = (CTPerm)this.get_store().add_element_user(PERMEND$12);
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
            this.get_store().remove_element(PERMEND$12, n);
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
                    return CTRunTrackChangeImpl.this.getBookmarkStartArray(n);
                }

                @Override
                public CTBookmark set(int n, CTBookmark cTBookmark) {
                    CTBookmark cTBookmark2 = CTRunTrackChangeImpl.this.getBookmarkStartArray(n);
                    CTRunTrackChangeImpl.this.setBookmarkStartArray(n, cTBookmark);
                    return cTBookmark2;
                }

                @Override
                public void add(int n, CTBookmark cTBookmark) {
                    CTRunTrackChangeImpl.this.insertNewBookmarkStart(n).set(cTBookmark);
                }

                @Override
                public CTBookmark remove(int n) {
                    CTBookmark cTBookmark = CTRunTrackChangeImpl.this.getBookmarkStartArray(n);
                    CTRunTrackChangeImpl.this.removeBookmarkStart(n);
                    return cTBookmark;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfBookmarkStartArray();
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
            this.get_store().find_all_element_users(BOOKMARKSTART$14, arrayList);
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
            cTBookmark = (CTBookmark)((Object)this.get_store().find_element_user(BOOKMARKSTART$14, n));
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
            return this.get_store().count_elements(BOOKMARKSTART$14);
        }
    }

    @Override
    public void setBookmarkStartArray(CTBookmark[] cTBookmarkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBookmarkArray, BOOKMARKSTART$14);
    }

    @Override
    public void setBookmarkStartArray(int n, CTBookmark cTBookmark) {
        this.generatedSetterHelperImpl(cTBookmark, BOOKMARKSTART$14, n, (short)2);
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
            cTBookmark = (CTBookmark)((Object)this.get_store().insert_element_user(BOOKMARKSTART$14, n));
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
            cTBookmark = (CTBookmark)((Object)this.get_store().add_element_user(BOOKMARKSTART$14));
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
            this.get_store().remove_element(BOOKMARKSTART$14, n);
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
                    return CTRunTrackChangeImpl.this.getBookmarkEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRunTrackChangeImpl.this.getBookmarkEndArray(n);
                    CTRunTrackChangeImpl.this.setBookmarkEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRunTrackChangeImpl.this.insertNewBookmarkEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRunTrackChangeImpl.this.getBookmarkEndArray(n);
                    CTRunTrackChangeImpl.this.removeBookmarkEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfBookmarkEndArray();
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
            this.get_store().find_all_element_users(BOOKMARKEND$16, arrayList);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(BOOKMARKEND$16, n));
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
            return this.get_store().count_elements(BOOKMARKEND$16);
        }
    }

    @Override
    public void setBookmarkEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, BOOKMARKEND$16);
    }

    @Override
    public void setBookmarkEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, BOOKMARKEND$16, n, (short)2);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(BOOKMARKEND$16, n));
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(BOOKMARKEND$16));
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
            this.get_store().remove_element(BOOKMARKEND$16, n);
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
                    return CTRunTrackChangeImpl.this.getMoveFromRangeStartArray(n);
                }

                @Override
                public CTMoveBookmark set(int n, CTMoveBookmark cTMoveBookmark) {
                    CTMoveBookmark cTMoveBookmark2 = CTRunTrackChangeImpl.this.getMoveFromRangeStartArray(n);
                    CTRunTrackChangeImpl.this.setMoveFromRangeStartArray(n, cTMoveBookmark);
                    return cTMoveBookmark2;
                }

                @Override
                public void add(int n, CTMoveBookmark cTMoveBookmark) {
                    CTRunTrackChangeImpl.this.insertNewMoveFromRangeStart(n).set(cTMoveBookmark);
                }

                @Override
                public CTMoveBookmark remove(int n) {
                    CTMoveBookmark cTMoveBookmark = CTRunTrackChangeImpl.this.getMoveFromRangeStartArray(n);
                    CTRunTrackChangeImpl.this.removeMoveFromRangeStart(n);
                    return cTMoveBookmark;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfMoveFromRangeStartArray();
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
            this.get_store().find_all_element_users(MOVEFROMRANGESTART$18, arrayList);
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().find_element_user(MOVEFROMRANGESTART$18, n));
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
            return this.get_store().count_elements(MOVEFROMRANGESTART$18);
        }
    }

    @Override
    public void setMoveFromRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMoveBookmarkArray, MOVEFROMRANGESTART$18);
    }

    @Override
    public void setMoveFromRangeStartArray(int n, CTMoveBookmark cTMoveBookmark) {
        this.generatedSetterHelperImpl(cTMoveBookmark, MOVEFROMRANGESTART$18, n, (short)2);
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().insert_element_user(MOVEFROMRANGESTART$18, n));
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().add_element_user(MOVEFROMRANGESTART$18));
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
            this.get_store().remove_element(MOVEFROMRANGESTART$18, n);
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
                    return CTRunTrackChangeImpl.this.getMoveFromRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRunTrackChangeImpl.this.getMoveFromRangeEndArray(n);
                    CTRunTrackChangeImpl.this.setMoveFromRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRunTrackChangeImpl.this.insertNewMoveFromRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRunTrackChangeImpl.this.getMoveFromRangeEndArray(n);
                    CTRunTrackChangeImpl.this.removeMoveFromRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfMoveFromRangeEndArray();
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
            this.get_store().find_all_element_users(MOVEFROMRANGEEND$20, arrayList);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(MOVEFROMRANGEEND$20, n));
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
            return this.get_store().count_elements(MOVEFROMRANGEEND$20);
        }
    }

    @Override
    public void setMoveFromRangeEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, MOVEFROMRANGEEND$20);
    }

    @Override
    public void setMoveFromRangeEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, MOVEFROMRANGEEND$20, n, (short)2);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(MOVEFROMRANGEEND$20, n));
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(MOVEFROMRANGEEND$20));
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
            this.get_store().remove_element(MOVEFROMRANGEEND$20, n);
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
                    return CTRunTrackChangeImpl.this.getMoveToRangeStartArray(n);
                }

                @Override
                public CTMoveBookmark set(int n, CTMoveBookmark cTMoveBookmark) {
                    CTMoveBookmark cTMoveBookmark2 = CTRunTrackChangeImpl.this.getMoveToRangeStartArray(n);
                    CTRunTrackChangeImpl.this.setMoveToRangeStartArray(n, cTMoveBookmark);
                    return cTMoveBookmark2;
                }

                @Override
                public void add(int n, CTMoveBookmark cTMoveBookmark) {
                    CTRunTrackChangeImpl.this.insertNewMoveToRangeStart(n).set(cTMoveBookmark);
                }

                @Override
                public CTMoveBookmark remove(int n) {
                    CTMoveBookmark cTMoveBookmark = CTRunTrackChangeImpl.this.getMoveToRangeStartArray(n);
                    CTRunTrackChangeImpl.this.removeMoveToRangeStart(n);
                    return cTMoveBookmark;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfMoveToRangeStartArray();
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
            this.get_store().find_all_element_users(MOVETORANGESTART$22, arrayList);
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().find_element_user(MOVETORANGESTART$22, n));
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
            return this.get_store().count_elements(MOVETORANGESTART$22);
        }
    }

    @Override
    public void setMoveToRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMoveBookmarkArray, MOVETORANGESTART$22);
    }

    @Override
    public void setMoveToRangeStartArray(int n, CTMoveBookmark cTMoveBookmark) {
        this.generatedSetterHelperImpl(cTMoveBookmark, MOVETORANGESTART$22, n, (short)2);
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().insert_element_user(MOVETORANGESTART$22, n));
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().add_element_user(MOVETORANGESTART$22));
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
            this.get_store().remove_element(MOVETORANGESTART$22, n);
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
                    return CTRunTrackChangeImpl.this.getMoveToRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRunTrackChangeImpl.this.getMoveToRangeEndArray(n);
                    CTRunTrackChangeImpl.this.setMoveToRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRunTrackChangeImpl.this.insertNewMoveToRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRunTrackChangeImpl.this.getMoveToRangeEndArray(n);
                    CTRunTrackChangeImpl.this.removeMoveToRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfMoveToRangeEndArray();
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
            this.get_store().find_all_element_users(MOVETORANGEEND$24, arrayList);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(MOVETORANGEEND$24, n));
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
            return this.get_store().count_elements(MOVETORANGEEND$24);
        }
    }

    @Override
    public void setMoveToRangeEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, MOVETORANGEEND$24);
    }

    @Override
    public void setMoveToRangeEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, MOVETORANGEEND$24, n, (short)2);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(MOVETORANGEEND$24, n));
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(MOVETORANGEEND$24));
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
            this.get_store().remove_element(MOVETORANGEEND$24, n);
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
                    return CTRunTrackChangeImpl.this.getCommentRangeStartArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRunTrackChangeImpl.this.getCommentRangeStartArray(n);
                    CTRunTrackChangeImpl.this.setCommentRangeStartArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRunTrackChangeImpl.this.insertNewCommentRangeStart(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRunTrackChangeImpl.this.getCommentRangeStartArray(n);
                    CTRunTrackChangeImpl.this.removeCommentRangeStart(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCommentRangeStartArray();
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
            this.get_store().find_all_element_users(COMMENTRANGESTART$26, arrayList);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(COMMENTRANGESTART$26, n));
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
            return this.get_store().count_elements(COMMENTRANGESTART$26);
        }
    }

    @Override
    public void setCommentRangeStartArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, COMMENTRANGESTART$26);
    }

    @Override
    public void setCommentRangeStartArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, COMMENTRANGESTART$26, n, (short)2);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(COMMENTRANGESTART$26, n));
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(COMMENTRANGESTART$26));
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
            this.get_store().remove_element(COMMENTRANGESTART$26, n);
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
                    return CTRunTrackChangeImpl.this.getCommentRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRunTrackChangeImpl.this.getCommentRangeEndArray(n);
                    CTRunTrackChangeImpl.this.setCommentRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRunTrackChangeImpl.this.insertNewCommentRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRunTrackChangeImpl.this.getCommentRangeEndArray(n);
                    CTRunTrackChangeImpl.this.removeCommentRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCommentRangeEndArray();
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
            this.get_store().find_all_element_users(COMMENTRANGEEND$28, arrayList);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(COMMENTRANGEEND$28, n));
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
            return this.get_store().count_elements(COMMENTRANGEEND$28);
        }
    }

    @Override
    public void setCommentRangeEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, COMMENTRANGEEND$28);
    }

    @Override
    public void setCommentRangeEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, COMMENTRANGEEND$28, n, (short)2);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(COMMENTRANGEEND$28, n));
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(COMMENTRANGEEND$28));
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
            this.get_store().remove_element(COMMENTRANGEEND$28, n);
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
                    return CTRunTrackChangeImpl.this.getCustomXmlInsRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRunTrackChangeImpl.this.getCustomXmlInsRangeStartArray(n);
                    CTRunTrackChangeImpl.this.setCustomXmlInsRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRunTrackChangeImpl.this.insertNewCustomXmlInsRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRunTrackChangeImpl.this.getCustomXmlInsRangeStartArray(n);
                    CTRunTrackChangeImpl.this.removeCustomXmlInsRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCustomXmlInsRangeStartArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLINSRANGESTART$30, arrayList);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLINSRANGESTART$30, n));
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
            return this.get_store().count_elements(CUSTOMXMLINSRANGESTART$30);
        }
    }

    @Override
    public void setCustomXmlInsRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLINSRANGESTART$30);
    }

    @Override
    public void setCustomXmlInsRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLINSRANGESTART$30, n, (short)2);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLINSRANGESTART$30, n));
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLINSRANGESTART$30));
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
            this.get_store().remove_element(CUSTOMXMLINSRANGESTART$30, n);
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
                    return CTRunTrackChangeImpl.this.getCustomXmlInsRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRunTrackChangeImpl.this.getCustomXmlInsRangeEndArray(n);
                    CTRunTrackChangeImpl.this.setCustomXmlInsRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRunTrackChangeImpl.this.insertNewCustomXmlInsRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRunTrackChangeImpl.this.getCustomXmlInsRangeEndArray(n);
                    CTRunTrackChangeImpl.this.removeCustomXmlInsRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCustomXmlInsRangeEndArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLINSRANGEEND$32, arrayList);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLINSRANGEEND$32, n));
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
            return this.get_store().count_elements(CUSTOMXMLINSRANGEEND$32);
        }
    }

    @Override
    public void setCustomXmlInsRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLINSRANGEEND$32);
    }

    @Override
    public void setCustomXmlInsRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLINSRANGEEND$32, n, (short)2);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLINSRANGEEND$32, n));
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
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLINSRANGEEND$32));
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
            this.get_store().remove_element(CUSTOMXMLINSRANGEEND$32, n);
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
                    return CTRunTrackChangeImpl.this.getCustomXmlDelRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRunTrackChangeImpl.this.getCustomXmlDelRangeStartArray(n);
                    CTRunTrackChangeImpl.this.setCustomXmlDelRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRunTrackChangeImpl.this.insertNewCustomXmlDelRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRunTrackChangeImpl.this.getCustomXmlDelRangeStartArray(n);
                    CTRunTrackChangeImpl.this.removeCustomXmlDelRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCustomXmlDelRangeStartArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLDELRANGESTART$34, arrayList);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLDELRANGESTART$34, n));
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
            return this.get_store().count_elements(CUSTOMXMLDELRANGESTART$34);
        }
    }

    @Override
    public void setCustomXmlDelRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLDELRANGESTART$34);
    }

    @Override
    public void setCustomXmlDelRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLDELRANGESTART$34, n, (short)2);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLDELRANGESTART$34, n));
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLDELRANGESTART$34));
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
            this.get_store().remove_element(CUSTOMXMLDELRANGESTART$34, n);
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
                    return CTRunTrackChangeImpl.this.getCustomXmlDelRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRunTrackChangeImpl.this.getCustomXmlDelRangeEndArray(n);
                    CTRunTrackChangeImpl.this.setCustomXmlDelRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRunTrackChangeImpl.this.insertNewCustomXmlDelRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRunTrackChangeImpl.this.getCustomXmlDelRangeEndArray(n);
                    CTRunTrackChangeImpl.this.removeCustomXmlDelRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCustomXmlDelRangeEndArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLDELRANGEEND$36, arrayList);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLDELRANGEEND$36, n));
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
            return this.get_store().count_elements(CUSTOMXMLDELRANGEEND$36);
        }
    }

    @Override
    public void setCustomXmlDelRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLDELRANGEEND$36);
    }

    @Override
    public void setCustomXmlDelRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLDELRANGEEND$36, n, (short)2);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLDELRANGEEND$36, n));
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
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLDELRANGEEND$36));
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
            this.get_store().remove_element(CUSTOMXMLDELRANGEEND$36, n);
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
                    return CTRunTrackChangeImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRunTrackChangeImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                    CTRunTrackChangeImpl.this.setCustomXmlMoveFromRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRunTrackChangeImpl.this.insertNewCustomXmlMoveFromRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRunTrackChangeImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                    CTRunTrackChangeImpl.this.removeCustomXmlMoveFromRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCustomXmlMoveFromRangeStartArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLMOVEFROMRANGESTART$38, arrayList);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLMOVEFROMRANGESTART$38, n));
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
            return this.get_store().count_elements(CUSTOMXMLMOVEFROMRANGESTART$38);
        }
    }

    @Override
    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLMOVEFROMRANGESTART$38);
    }

    @Override
    public void setCustomXmlMoveFromRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLMOVEFROMRANGESTART$38, n, (short)2);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVEFROMRANGESTART$38, n));
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLMOVEFROMRANGESTART$38));
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
            this.get_store().remove_element(CUSTOMXMLMOVEFROMRANGESTART$38, n);
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
                    return CTRunTrackChangeImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRunTrackChangeImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                    CTRunTrackChangeImpl.this.setCustomXmlMoveFromRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRunTrackChangeImpl.this.insertNewCustomXmlMoveFromRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRunTrackChangeImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                    CTRunTrackChangeImpl.this.removeCustomXmlMoveFromRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCustomXmlMoveFromRangeEndArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLMOVEFROMRANGEEND$40, arrayList);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLMOVEFROMRANGEEND$40, n));
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
            return this.get_store().count_elements(CUSTOMXMLMOVEFROMRANGEEND$40);
        }
    }

    @Override
    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLMOVEFROMRANGEEND$40);
    }

    @Override
    public void setCustomXmlMoveFromRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLMOVEFROMRANGEEND$40, n, (short)2);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVEFROMRANGEEND$40, n));
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
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLMOVEFROMRANGEEND$40));
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
            this.get_store().remove_element(CUSTOMXMLMOVEFROMRANGEEND$40, n);
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
                    return CTRunTrackChangeImpl.this.getCustomXmlMoveToRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRunTrackChangeImpl.this.getCustomXmlMoveToRangeStartArray(n);
                    CTRunTrackChangeImpl.this.setCustomXmlMoveToRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRunTrackChangeImpl.this.insertNewCustomXmlMoveToRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRunTrackChangeImpl.this.getCustomXmlMoveToRangeStartArray(n);
                    CTRunTrackChangeImpl.this.removeCustomXmlMoveToRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCustomXmlMoveToRangeStartArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLMOVETORANGESTART$42, arrayList);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLMOVETORANGESTART$42, n));
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
            return this.get_store().count_elements(CUSTOMXMLMOVETORANGESTART$42);
        }
    }

    @Override
    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLMOVETORANGESTART$42);
    }

    @Override
    public void setCustomXmlMoveToRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLMOVETORANGESTART$42, n, (short)2);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVETORANGESTART$42, n));
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLMOVETORANGESTART$42));
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
            this.get_store().remove_element(CUSTOMXMLMOVETORANGESTART$42, n);
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
                    return CTRunTrackChangeImpl.this.getCustomXmlMoveToRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRunTrackChangeImpl.this.getCustomXmlMoveToRangeEndArray(n);
                    CTRunTrackChangeImpl.this.setCustomXmlMoveToRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRunTrackChangeImpl.this.insertNewCustomXmlMoveToRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRunTrackChangeImpl.this.getCustomXmlMoveToRangeEndArray(n);
                    CTRunTrackChangeImpl.this.removeCustomXmlMoveToRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfCustomXmlMoveToRangeEndArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLMOVETORANGEEND$44, arrayList);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLMOVETORANGEEND$44, n));
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
            return this.get_store().count_elements(CUSTOMXMLMOVETORANGEEND$44);
        }
    }

    @Override
    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLMOVETORANGEEND$44);
    }

    @Override
    public void setCustomXmlMoveToRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLMOVETORANGEEND$44, n, (short)2);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVETORANGEEND$44, n));
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
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLMOVETORANGEEND$44));
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
            this.get_store().remove_element(CUSTOMXMLMOVETORANGEEND$44, n);
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
                    return CTRunTrackChangeImpl.this.getInsArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRunTrackChangeImpl.this.getInsArray(n);
                    CTRunTrackChangeImpl.this.setInsArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChangeImpl.this.insertNewIns(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRunTrackChangeImpl.this.getInsArray(n);
                    CTRunTrackChangeImpl.this.removeIns(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfInsArray();
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
            this.get_store().find_all_element_users(INS$46, arrayList);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(INS$46, n));
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
            return this.get_store().count_elements(INS$46);
        }
    }

    @Override
    public void setInsArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, INS$46);
    }

    @Override
    public void setInsArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, INS$46, n, (short)2);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(INS$46, n));
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(INS$46));
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
            this.get_store().remove_element(INS$46, n);
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
                    return CTRunTrackChangeImpl.this.getDelArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRunTrackChangeImpl.this.getDelArray(n);
                    CTRunTrackChangeImpl.this.setDelArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChangeImpl.this.insertNewDel(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRunTrackChangeImpl.this.getDelArray(n);
                    CTRunTrackChangeImpl.this.removeDel(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfDelArray();
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
            this.get_store().find_all_element_users(DEL$48, arrayList);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(DEL$48, n));
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
            return this.get_store().count_elements(DEL$48);
        }
    }

    @Override
    public void setDelArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, DEL$48);
    }

    @Override
    public void setDelArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, DEL$48, n, (short)2);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(DEL$48, n));
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(DEL$48));
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
            this.get_store().remove_element(DEL$48, n);
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
                    return CTRunTrackChangeImpl.this.getMoveFromArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRunTrackChangeImpl.this.getMoveFromArray(n);
                    CTRunTrackChangeImpl.this.setMoveFromArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChangeImpl.this.insertNewMoveFrom(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRunTrackChangeImpl.this.getMoveFromArray(n);
                    CTRunTrackChangeImpl.this.removeMoveFrom(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfMoveFromArray();
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
            this.get_store().find_all_element_users(MOVEFROM$50, arrayList);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(MOVEFROM$50, n));
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
            return this.get_store().count_elements(MOVEFROM$50);
        }
    }

    @Override
    public void setMoveFromArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, MOVEFROM$50);
    }

    @Override
    public void setMoveFromArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, MOVEFROM$50, n, (short)2);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(MOVEFROM$50, n));
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(MOVEFROM$50));
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
            this.get_store().remove_element(MOVEFROM$50, n);
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
                    return CTRunTrackChangeImpl.this.getMoveToArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRunTrackChangeImpl.this.getMoveToArray(n);
                    CTRunTrackChangeImpl.this.setMoveToArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChangeImpl.this.insertNewMoveTo(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRunTrackChangeImpl.this.getMoveToArray(n);
                    CTRunTrackChangeImpl.this.removeMoveTo(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfMoveToArray();
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
            this.get_store().find_all_element_users(MOVETO$52, arrayList);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(MOVETO$52, n));
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
            return this.get_store().count_elements(MOVETO$52);
        }
    }

    @Override
    public void setMoveToArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, MOVETO$52);
    }

    @Override
    public void setMoveToArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, MOVETO$52, n, (short)2);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(MOVETO$52, n));
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(MOVETO$52));
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
            this.get_store().remove_element(MOVETO$52, n);
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
                    return CTRunTrackChangeImpl.this.getOMathParaArray(n);
                }

                @Override
                public CTOMathPara set(int n, CTOMathPara cTOMathPara) {
                    CTOMathPara cTOMathPara2 = CTRunTrackChangeImpl.this.getOMathParaArray(n);
                    CTRunTrackChangeImpl.this.setOMathParaArray(n, cTOMathPara);
                    return cTOMathPara2;
                }

                @Override
                public void add(int n, CTOMathPara cTOMathPara) {
                    CTRunTrackChangeImpl.this.insertNewOMathPara(n).set((XmlObject)cTOMathPara);
                }

                @Override
                public CTOMathPara remove(int n) {
                    CTOMathPara cTOMathPara = CTRunTrackChangeImpl.this.getOMathParaArray(n);
                    CTRunTrackChangeImpl.this.removeOMathPara(n);
                    return cTOMathPara;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfOMathParaArray();
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
            this.get_store().find_all_element_users(OMATHPARA$54, arrayList);
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
            cTOMathPara = (CTOMathPara)this.get_store().find_element_user(OMATHPARA$54, n);
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
            return this.get_store().count_elements(OMATHPARA$54);
        }
    }

    @Override
    public void setOMathParaArray(CTOMathPara[] cTOMathParaArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTOMathParaArray, OMATHPARA$54);
    }

    @Override
    public void setOMathParaArray(int n, CTOMathPara cTOMathPara) {
        this.generatedSetterHelperImpl((XmlObject)cTOMathPara, OMATHPARA$54, n, (short)2);
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
            cTOMathPara = (CTOMathPara)this.get_store().insert_element_user(OMATHPARA$54, n);
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
            cTOMathPara = (CTOMathPara)this.get_store().add_element_user(OMATHPARA$54);
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
            this.get_store().remove_element(OMATHPARA$54, n);
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
                    return CTRunTrackChangeImpl.this.getOMathArray(n);
                }

                @Override
                public CTOMath set(int n, CTOMath cTOMath) {
                    CTOMath cTOMath2 = CTRunTrackChangeImpl.this.getOMathArray(n);
                    CTRunTrackChangeImpl.this.setOMathArray(n, cTOMath);
                    return cTOMath2;
                }

                @Override
                public void add(int n, CTOMath cTOMath) {
                    CTRunTrackChangeImpl.this.insertNewOMath(n).set((XmlObject)cTOMath);
                }

                @Override
                public CTOMath remove(int n) {
                    CTOMath cTOMath = CTRunTrackChangeImpl.this.getOMathArray(n);
                    CTRunTrackChangeImpl.this.removeOMath(n);
                    return cTOMath;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfOMathArray();
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
            this.get_store().find_all_element_users(OMATH$56, arrayList);
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
            cTOMath = (CTOMath)this.get_store().find_element_user(OMATH$56, n);
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
            return this.get_store().count_elements(OMATH$56);
        }
    }

    @Override
    public void setOMathArray(CTOMath[] cTOMathArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTOMathArray, OMATH$56);
    }

    @Override
    public void setOMathArray(int n, CTOMath cTOMath) {
        this.generatedSetterHelperImpl((XmlObject)cTOMath, OMATH$56, n, (short)2);
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
            cTOMath = (CTOMath)this.get_store().insert_element_user(OMATH$56, n);
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
            cTOMath = (CTOMath)this.get_store().add_element_user(OMATH$56);
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
            this.get_store().remove_element(OMATH$56, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAcc> getAccList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AccList
            extends AbstractList<CTAcc> {
                AccList() {
                }

                @Override
                public CTAcc get(int n) {
                    return CTRunTrackChangeImpl.this.getAccArray(n);
                }

                @Override
                public CTAcc set(int n, CTAcc cTAcc) {
                    CTAcc cTAcc2 = CTRunTrackChangeImpl.this.getAccArray(n);
                    CTRunTrackChangeImpl.this.setAccArray(n, cTAcc);
                    return cTAcc2;
                }

                @Override
                public void add(int n, CTAcc cTAcc) {
                    CTRunTrackChangeImpl.this.insertNewAcc(n).set((XmlObject)cTAcc);
                }

                @Override
                public CTAcc remove(int n) {
                    CTAcc cTAcc = CTRunTrackChangeImpl.this.getAccArray(n);
                    CTRunTrackChangeImpl.this.removeAcc(n);
                    return cTAcc;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfAccArray();
                }
            }
            return new AccList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAcc[] getAccArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ACC$58, arrayList);
            CTAcc[] cTAccArray = new CTAcc[arrayList.size()];
            arrayList.toArray(cTAccArray);
            return cTAccArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAcc getAccArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAcc cTAcc = null;
            cTAcc = (CTAcc)this.get_store().find_element_user(ACC$58, n);
            if (cTAcc == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAcc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAccArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ACC$58);
        }
    }

    @Override
    public void setAccArray(CTAcc[] cTAccArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAccArray, ACC$58);
    }

    @Override
    public void setAccArray(int n, CTAcc cTAcc) {
        this.generatedSetterHelperImpl((XmlObject)cTAcc, ACC$58, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAcc insertNewAcc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAcc cTAcc = null;
            cTAcc = (CTAcc)this.get_store().insert_element_user(ACC$58, n);
            return cTAcc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAcc addNewAcc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAcc cTAcc = null;
            cTAcc = (CTAcc)this.get_store().add_element_user(ACC$58);
            return cTAcc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAcc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ACC$58, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBar> getBarList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BarList
            extends AbstractList<CTBar> {
                BarList() {
                }

                @Override
                public CTBar get(int n) {
                    return CTRunTrackChangeImpl.this.getBarArray(n);
                }

                @Override
                public CTBar set(int n, CTBar cTBar) {
                    CTBar cTBar2 = CTRunTrackChangeImpl.this.getBarArray(n);
                    CTRunTrackChangeImpl.this.setBarArray(n, cTBar);
                    return cTBar2;
                }

                @Override
                public void add(int n, CTBar cTBar) {
                    CTRunTrackChangeImpl.this.insertNewBar(n).set((XmlObject)cTBar);
                }

                @Override
                public CTBar remove(int n) {
                    CTBar cTBar = CTRunTrackChangeImpl.this.getBarArray(n);
                    CTRunTrackChangeImpl.this.removeBar(n);
                    return cTBar;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfBarArray();
                }
            }
            return new BarList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBar[] getBarArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BAR$60, arrayList);
            CTBar[] cTBarArray = new CTBar[arrayList.size()];
            arrayList.toArray(cTBarArray);
            return cTBarArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBar getBarArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBar cTBar = null;
            cTBar = (CTBar)this.get_store().find_element_user(BAR$60, n);
            if (cTBar == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBarArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BAR$60);
        }
    }

    @Override
    public void setBarArray(CTBar[] cTBarArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBarArray, BAR$60);
    }

    @Override
    public void setBarArray(int n, CTBar cTBar) {
        this.generatedSetterHelperImpl((XmlObject)cTBar, BAR$60, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBar insertNewBar(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBar cTBar = null;
            cTBar = (CTBar)this.get_store().insert_element_user(BAR$60, n);
            return cTBar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBar addNewBar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBar cTBar = null;
            cTBar = (CTBar)this.get_store().add_element_user(BAR$60);
            return cTBar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBar(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BAR$60, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBox> getBoxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BoxList
            extends AbstractList<CTBox> {
                BoxList() {
                }

                @Override
                public CTBox get(int n) {
                    return CTRunTrackChangeImpl.this.getBoxArray(n);
                }

                @Override
                public CTBox set(int n, CTBox cTBox) {
                    CTBox cTBox2 = CTRunTrackChangeImpl.this.getBoxArray(n);
                    CTRunTrackChangeImpl.this.setBoxArray(n, cTBox);
                    return cTBox2;
                }

                @Override
                public void add(int n, CTBox cTBox) {
                    CTRunTrackChangeImpl.this.insertNewBox(n).set((XmlObject)cTBox);
                }

                @Override
                public CTBox remove(int n) {
                    CTBox cTBox = CTRunTrackChangeImpl.this.getBoxArray(n);
                    CTRunTrackChangeImpl.this.removeBox(n);
                    return cTBox;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfBoxArray();
                }
            }
            return new BoxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBox[] getBoxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BOX$62, arrayList);
            CTBox[] cTBoxArray = new CTBox[arrayList.size()];
            arrayList.toArray(cTBoxArray);
            return cTBoxArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBox getBoxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBox cTBox = null;
            cTBox = (CTBox)this.get_store().find_element_user(BOX$62, n);
            if (cTBox == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBoxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOX$62);
        }
    }

    @Override
    public void setBoxArray(CTBox[] cTBoxArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBoxArray, BOX$62);
    }

    @Override
    public void setBoxArray(int n, CTBox cTBox) {
        this.generatedSetterHelperImpl((XmlObject)cTBox, BOX$62, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBox insertNewBox(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBox cTBox = null;
            cTBox = (CTBox)this.get_store().insert_element_user(BOX$62, n);
            return cTBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBox addNewBox() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBox cTBox = null;
            cTBox = (CTBox)this.get_store().add_element_user(BOX$62);
            return cTBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBox(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOX$62, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBorderBox> getBorderBoxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BorderBoxList
            extends AbstractList<CTBorderBox> {
                BorderBoxList() {
                }

                @Override
                public CTBorderBox get(int n) {
                    return CTRunTrackChangeImpl.this.getBorderBoxArray(n);
                }

                @Override
                public CTBorderBox set(int n, CTBorderBox cTBorderBox) {
                    CTBorderBox cTBorderBox2 = CTRunTrackChangeImpl.this.getBorderBoxArray(n);
                    CTRunTrackChangeImpl.this.setBorderBoxArray(n, cTBorderBox);
                    return cTBorderBox2;
                }

                @Override
                public void add(int n, CTBorderBox cTBorderBox) {
                    CTRunTrackChangeImpl.this.insertNewBorderBox(n).set((XmlObject)cTBorderBox);
                }

                @Override
                public CTBorderBox remove(int n) {
                    CTBorderBox cTBorderBox = CTRunTrackChangeImpl.this.getBorderBoxArray(n);
                    CTRunTrackChangeImpl.this.removeBorderBox(n);
                    return cTBorderBox;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfBorderBoxArray();
                }
            }
            return new BorderBoxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBorderBox[] getBorderBoxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BORDERBOX$64, arrayList);
            CTBorderBox[] cTBorderBoxArray = new CTBorderBox[arrayList.size()];
            arrayList.toArray(cTBorderBoxArray);
            return cTBorderBoxArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderBox getBorderBoxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderBox cTBorderBox = null;
            cTBorderBox = (CTBorderBox)this.get_store().find_element_user(BORDERBOX$64, n);
            if (cTBorderBox == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBorderBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBorderBoxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BORDERBOX$64);
        }
    }

    @Override
    public void setBorderBoxArray(CTBorderBox[] cTBorderBoxArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBorderBoxArray, BORDERBOX$64);
    }

    @Override
    public void setBorderBoxArray(int n, CTBorderBox cTBorderBox) {
        this.generatedSetterHelperImpl((XmlObject)cTBorderBox, BORDERBOX$64, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderBox insertNewBorderBox(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderBox cTBorderBox = null;
            cTBorderBox = (CTBorderBox)this.get_store().insert_element_user(BORDERBOX$64, n);
            return cTBorderBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderBox addNewBorderBox() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderBox cTBorderBox = null;
            cTBorderBox = (CTBorderBox)this.get_store().add_element_user(BORDERBOX$64);
            return cTBorderBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBorderBox(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BORDERBOX$64, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTD> getDList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DList
            extends AbstractList<CTD> {
                DList() {
                }

                @Override
                public CTD get(int n) {
                    return CTRunTrackChangeImpl.this.getDArray(n);
                }

                @Override
                public CTD set(int n, CTD cTD) {
                    CTD cTD2 = CTRunTrackChangeImpl.this.getDArray(n);
                    CTRunTrackChangeImpl.this.setDArray(n, cTD);
                    return cTD2;
                }

                @Override
                public void add(int n, CTD cTD) {
                    CTRunTrackChangeImpl.this.insertNewD(n).set((XmlObject)cTD);
                }

                @Override
                public CTD remove(int n) {
                    CTD cTD = CTRunTrackChangeImpl.this.getDArray(n);
                    CTRunTrackChangeImpl.this.removeD(n);
                    return cTD;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfDArray();
                }
            }
            return new DList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTD[] getDArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(D$66, arrayList);
            CTD[] cTDArray = new CTD[arrayList.size()];
            arrayList.toArray(cTDArray);
            return cTDArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTD getDArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTD cTD = null;
            cTD = (CTD)this.get_store().find_element_user(D$66, n);
            if (cTD == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTD;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(D$66);
        }
    }

    @Override
    public void setDArray(CTD[] cTDArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTDArray, D$66);
    }

    @Override
    public void setDArray(int n, CTD cTD) {
        this.generatedSetterHelperImpl((XmlObject)cTD, D$66, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTD insertNewD(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTD cTD = null;
            cTD = (CTD)this.get_store().insert_element_user(D$66, n);
            return cTD;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTD addNewD() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTD cTD = null;
            cTD = (CTD)this.get_store().add_element_user(D$66);
            return cTD;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeD(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(D$66, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEqArr> getEqArrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EqArrList
            extends AbstractList<CTEqArr> {
                EqArrList() {
                }

                @Override
                public CTEqArr get(int n) {
                    return CTRunTrackChangeImpl.this.getEqArrArray(n);
                }

                @Override
                public CTEqArr set(int n, CTEqArr cTEqArr) {
                    CTEqArr cTEqArr2 = CTRunTrackChangeImpl.this.getEqArrArray(n);
                    CTRunTrackChangeImpl.this.setEqArrArray(n, cTEqArr);
                    return cTEqArr2;
                }

                @Override
                public void add(int n, CTEqArr cTEqArr) {
                    CTRunTrackChangeImpl.this.insertNewEqArr(n).set((XmlObject)cTEqArr);
                }

                @Override
                public CTEqArr remove(int n) {
                    CTEqArr cTEqArr = CTRunTrackChangeImpl.this.getEqArrArray(n);
                    CTRunTrackChangeImpl.this.removeEqArr(n);
                    return cTEqArr;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfEqArrArray();
                }
            }
            return new EqArrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEqArr[] getEqArrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EQARR$68, arrayList);
            CTEqArr[] cTEqArrArray = new CTEqArr[arrayList.size()];
            arrayList.toArray(cTEqArrArray);
            return cTEqArrArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEqArr getEqArrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEqArr cTEqArr = null;
            cTEqArr = (CTEqArr)this.get_store().find_element_user(EQARR$68, n);
            if (cTEqArr == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEqArr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfEqArrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EQARR$68);
        }
    }

    @Override
    public void setEqArrArray(CTEqArr[] cTEqArrArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTEqArrArray, EQARR$68);
    }

    @Override
    public void setEqArrArray(int n, CTEqArr cTEqArr) {
        this.generatedSetterHelperImpl((XmlObject)cTEqArr, EQARR$68, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEqArr insertNewEqArr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEqArr cTEqArr = null;
            cTEqArr = (CTEqArr)this.get_store().insert_element_user(EQARR$68, n);
            return cTEqArr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEqArr addNewEqArr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEqArr cTEqArr = null;
            cTEqArr = (CTEqArr)this.get_store().add_element_user(EQARR$68);
            return cTEqArr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEqArr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EQARR$68, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTF> getFList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FList
            extends AbstractList<CTF> {
                FList() {
                }

                @Override
                public CTF get(int n) {
                    return CTRunTrackChangeImpl.this.getFArray(n);
                }

                @Override
                public CTF set(int n, CTF cTF) {
                    CTF cTF2 = CTRunTrackChangeImpl.this.getFArray(n);
                    CTRunTrackChangeImpl.this.setFArray(n, cTF);
                    return cTF2;
                }

                @Override
                public void add(int n, CTF cTF) {
                    CTRunTrackChangeImpl.this.insertNewF(n).set((XmlObject)cTF);
                }

                @Override
                public CTF remove(int n) {
                    CTF cTF = CTRunTrackChangeImpl.this.getFArray(n);
                    CTRunTrackChangeImpl.this.removeF(n);
                    return cTF;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfFArray();
                }
            }
            return new FList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTF[] getFArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(F$70, arrayList);
            CTF[] cTFArray = new CTF[arrayList.size()];
            arrayList.toArray(cTFArray);
            return cTFArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTF getFArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTF cTF = null;
            cTF = (CTF)this.get_store().find_element_user(F$70, n);
            if (cTF == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTF;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(F$70);
        }
    }

    @Override
    public void setFArray(CTF[] cTFArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFArray, F$70);
    }

    @Override
    public void setFArray(int n, CTF cTF) {
        this.generatedSetterHelperImpl((XmlObject)cTF, F$70, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTF insertNewF(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTF cTF = null;
            cTF = (CTF)this.get_store().insert_element_user(F$70, n);
            return cTF;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTF addNewF() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTF cTF = null;
            cTF = (CTF)this.get_store().add_element_user(F$70);
            return cTF;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeF(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(F$70, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFunc> getFuncList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FuncList
            extends AbstractList<CTFunc> {
                FuncList() {
                }

                @Override
                public CTFunc get(int n) {
                    return CTRunTrackChangeImpl.this.getFuncArray(n);
                }

                @Override
                public CTFunc set(int n, CTFunc cTFunc) {
                    CTFunc cTFunc2 = CTRunTrackChangeImpl.this.getFuncArray(n);
                    CTRunTrackChangeImpl.this.setFuncArray(n, cTFunc);
                    return cTFunc2;
                }

                @Override
                public void add(int n, CTFunc cTFunc) {
                    CTRunTrackChangeImpl.this.insertNewFunc(n).set((XmlObject)cTFunc);
                }

                @Override
                public CTFunc remove(int n) {
                    CTFunc cTFunc = CTRunTrackChangeImpl.this.getFuncArray(n);
                    CTRunTrackChangeImpl.this.removeFunc(n);
                    return cTFunc;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfFuncArray();
                }
            }
            return new FuncList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFunc[] getFuncArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FUNC$72, arrayList);
            CTFunc[] cTFuncArray = new CTFunc[arrayList.size()];
            arrayList.toArray(cTFuncArray);
            return cTFuncArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFunc getFuncArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFunc cTFunc = null;
            cTFunc = (CTFunc)this.get_store().find_element_user(FUNC$72, n);
            if (cTFunc == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFunc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFuncArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FUNC$72);
        }
    }

    @Override
    public void setFuncArray(CTFunc[] cTFuncArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFuncArray, FUNC$72);
    }

    @Override
    public void setFuncArray(int n, CTFunc cTFunc) {
        this.generatedSetterHelperImpl((XmlObject)cTFunc, FUNC$72, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFunc insertNewFunc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFunc cTFunc = null;
            cTFunc = (CTFunc)this.get_store().insert_element_user(FUNC$72, n);
            return cTFunc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFunc addNewFunc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFunc cTFunc = null;
            cTFunc = (CTFunc)this.get_store().add_element_user(FUNC$72);
            return cTFunc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFunc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FUNC$72, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGroupChr> getGroupChrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GroupChrList
            extends AbstractList<CTGroupChr> {
                GroupChrList() {
                }

                @Override
                public CTGroupChr get(int n) {
                    return CTRunTrackChangeImpl.this.getGroupChrArray(n);
                }

                @Override
                public CTGroupChr set(int n, CTGroupChr cTGroupChr) {
                    CTGroupChr cTGroupChr2 = CTRunTrackChangeImpl.this.getGroupChrArray(n);
                    CTRunTrackChangeImpl.this.setGroupChrArray(n, cTGroupChr);
                    return cTGroupChr2;
                }

                @Override
                public void add(int n, CTGroupChr cTGroupChr) {
                    CTRunTrackChangeImpl.this.insertNewGroupChr(n).set((XmlObject)cTGroupChr);
                }

                @Override
                public CTGroupChr remove(int n) {
                    CTGroupChr cTGroupChr = CTRunTrackChangeImpl.this.getGroupChrArray(n);
                    CTRunTrackChangeImpl.this.removeGroupChr(n);
                    return cTGroupChr;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfGroupChrArray();
                }
            }
            return new GroupChrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGroupChr[] getGroupChrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GROUPCHR$74, arrayList);
            CTGroupChr[] cTGroupChrArray = new CTGroupChr[arrayList.size()];
            arrayList.toArray(cTGroupChrArray);
            return cTGroupChrArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupChr getGroupChrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupChr cTGroupChr = null;
            cTGroupChr = (CTGroupChr)this.get_store().find_element_user(GROUPCHR$74, n);
            if (cTGroupChr == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGroupChr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGroupChrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GROUPCHR$74);
        }
    }

    @Override
    public void setGroupChrArray(CTGroupChr[] cTGroupChrArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTGroupChrArray, GROUPCHR$74);
    }

    @Override
    public void setGroupChrArray(int n, CTGroupChr cTGroupChr) {
        this.generatedSetterHelperImpl((XmlObject)cTGroupChr, GROUPCHR$74, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupChr insertNewGroupChr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupChr cTGroupChr = null;
            cTGroupChr = (CTGroupChr)this.get_store().insert_element_user(GROUPCHR$74, n);
            return cTGroupChr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupChr addNewGroupChr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupChr cTGroupChr = null;
            cTGroupChr = (CTGroupChr)this.get_store().add_element_user(GROUPCHR$74);
            return cTGroupChr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGroupChr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GROUPCHR$74, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLimLow> getLimLowList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LimLowList
            extends AbstractList<CTLimLow> {
                LimLowList() {
                }

                @Override
                public CTLimLow get(int n) {
                    return CTRunTrackChangeImpl.this.getLimLowArray(n);
                }

                @Override
                public CTLimLow set(int n, CTLimLow cTLimLow) {
                    CTLimLow cTLimLow2 = CTRunTrackChangeImpl.this.getLimLowArray(n);
                    CTRunTrackChangeImpl.this.setLimLowArray(n, cTLimLow);
                    return cTLimLow2;
                }

                @Override
                public void add(int n, CTLimLow cTLimLow) {
                    CTRunTrackChangeImpl.this.insertNewLimLow(n).set((XmlObject)cTLimLow);
                }

                @Override
                public CTLimLow remove(int n) {
                    CTLimLow cTLimLow = CTRunTrackChangeImpl.this.getLimLowArray(n);
                    CTRunTrackChangeImpl.this.removeLimLow(n);
                    return cTLimLow;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfLimLowArray();
                }
            }
            return new LimLowList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTLimLow[] getLimLowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LIMLOW$76, arrayList);
            CTLimLow[] cTLimLowArray = new CTLimLow[arrayList.size()];
            arrayList.toArray(cTLimLowArray);
            return cTLimLowArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLimLow getLimLowArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLimLow cTLimLow = null;
            cTLimLow = (CTLimLow)this.get_store().find_element_user(LIMLOW$76, n);
            if (cTLimLow == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLimLow;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLimLowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LIMLOW$76);
        }
    }

    @Override
    public void setLimLowArray(CTLimLow[] cTLimLowArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTLimLowArray, LIMLOW$76);
    }

    @Override
    public void setLimLowArray(int n, CTLimLow cTLimLow) {
        this.generatedSetterHelperImpl((XmlObject)cTLimLow, LIMLOW$76, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLimLow insertNewLimLow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLimLow cTLimLow = null;
            cTLimLow = (CTLimLow)this.get_store().insert_element_user(LIMLOW$76, n);
            return cTLimLow;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLimLow addNewLimLow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLimLow cTLimLow = null;
            cTLimLow = (CTLimLow)this.get_store().add_element_user(LIMLOW$76);
            return cTLimLow;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLimLow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LIMLOW$76, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLimUpp> getLimUppList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LimUppList
            extends AbstractList<CTLimUpp> {
                LimUppList() {
                }

                @Override
                public CTLimUpp get(int n) {
                    return CTRunTrackChangeImpl.this.getLimUppArray(n);
                }

                @Override
                public CTLimUpp set(int n, CTLimUpp cTLimUpp) {
                    CTLimUpp cTLimUpp2 = CTRunTrackChangeImpl.this.getLimUppArray(n);
                    CTRunTrackChangeImpl.this.setLimUppArray(n, cTLimUpp);
                    return cTLimUpp2;
                }

                @Override
                public void add(int n, CTLimUpp cTLimUpp) {
                    CTRunTrackChangeImpl.this.insertNewLimUpp(n).set((XmlObject)cTLimUpp);
                }

                @Override
                public CTLimUpp remove(int n) {
                    CTLimUpp cTLimUpp = CTRunTrackChangeImpl.this.getLimUppArray(n);
                    CTRunTrackChangeImpl.this.removeLimUpp(n);
                    return cTLimUpp;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfLimUppArray();
                }
            }
            return new LimUppList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTLimUpp[] getLimUppArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LIMUPP$78, arrayList);
            CTLimUpp[] cTLimUppArray = new CTLimUpp[arrayList.size()];
            arrayList.toArray(cTLimUppArray);
            return cTLimUppArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLimUpp getLimUppArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLimUpp cTLimUpp = null;
            cTLimUpp = (CTLimUpp)this.get_store().find_element_user(LIMUPP$78, n);
            if (cTLimUpp == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLimUpp;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLimUppArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LIMUPP$78);
        }
    }

    @Override
    public void setLimUppArray(CTLimUpp[] cTLimUppArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTLimUppArray, LIMUPP$78);
    }

    @Override
    public void setLimUppArray(int n, CTLimUpp cTLimUpp) {
        this.generatedSetterHelperImpl((XmlObject)cTLimUpp, LIMUPP$78, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLimUpp insertNewLimUpp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLimUpp cTLimUpp = null;
            cTLimUpp = (CTLimUpp)this.get_store().insert_element_user(LIMUPP$78, n);
            return cTLimUpp;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLimUpp addNewLimUpp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLimUpp cTLimUpp = null;
            cTLimUpp = (CTLimUpp)this.get_store().add_element_user(LIMUPP$78);
            return cTLimUpp;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLimUpp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LIMUPP$78, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTM> getMList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MList
            extends AbstractList<CTM> {
                MList() {
                }

                @Override
                public CTM get(int n) {
                    return CTRunTrackChangeImpl.this.getMArray(n);
                }

                @Override
                public CTM set(int n, CTM cTM) {
                    CTM cTM2 = CTRunTrackChangeImpl.this.getMArray(n);
                    CTRunTrackChangeImpl.this.setMArray(n, cTM);
                    return cTM2;
                }

                @Override
                public void add(int n, CTM cTM) {
                    CTRunTrackChangeImpl.this.insertNewM(n).set((XmlObject)cTM);
                }

                @Override
                public CTM remove(int n) {
                    CTM cTM = CTRunTrackChangeImpl.this.getMArray(n);
                    CTRunTrackChangeImpl.this.removeM(n);
                    return cTM;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfMArray();
                }
            }
            return new MList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTM[] getMArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(M$80, arrayList);
            CTM[] cTMArray = new CTM[arrayList.size()];
            arrayList.toArray(cTMArray);
            return cTMArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTM getMArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTM cTM = null;
            cTM = (CTM)this.get_store().find_element_user(M$80, n);
            if (cTM == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTM;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(M$80);
        }
    }

    @Override
    public void setMArray(CTM[] cTMArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTMArray, M$80);
    }

    @Override
    public void setMArray(int n, CTM cTM) {
        this.generatedSetterHelperImpl((XmlObject)cTM, M$80, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTM insertNewM(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTM cTM = null;
            cTM = (CTM)this.get_store().insert_element_user(M$80, n);
            return cTM;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTM addNewM() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTM cTM = null;
            cTM = (CTM)this.get_store().add_element_user(M$80);
            return cTM;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeM(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(M$80, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTNary> getNaryList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NaryList
            extends AbstractList<CTNary> {
                NaryList() {
                }

                @Override
                public CTNary get(int n) {
                    return CTRunTrackChangeImpl.this.getNaryArray(n);
                }

                @Override
                public CTNary set(int n, CTNary cTNary) {
                    CTNary cTNary2 = CTRunTrackChangeImpl.this.getNaryArray(n);
                    CTRunTrackChangeImpl.this.setNaryArray(n, cTNary);
                    return cTNary2;
                }

                @Override
                public void add(int n, CTNary cTNary) {
                    CTRunTrackChangeImpl.this.insertNewNary(n).set((XmlObject)cTNary);
                }

                @Override
                public CTNary remove(int n) {
                    CTNary cTNary = CTRunTrackChangeImpl.this.getNaryArray(n);
                    CTRunTrackChangeImpl.this.removeNary(n);
                    return cTNary;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfNaryArray();
                }
            }
            return new NaryList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTNary[] getNaryArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NARY$82, arrayList);
            CTNary[] cTNaryArray = new CTNary[arrayList.size()];
            arrayList.toArray(cTNaryArray);
            return cTNaryArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNary getNaryArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNary cTNary = null;
            cTNary = (CTNary)this.get_store().find_element_user(NARY$82, n);
            if (cTNary == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTNary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfNaryArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NARY$82);
        }
    }

    @Override
    public void setNaryArray(CTNary[] cTNaryArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTNaryArray, NARY$82);
    }

    @Override
    public void setNaryArray(int n, CTNary cTNary) {
        this.generatedSetterHelperImpl((XmlObject)cTNary, NARY$82, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNary insertNewNary(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNary cTNary = null;
            cTNary = (CTNary)this.get_store().insert_element_user(NARY$82, n);
            return cTNary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNary addNewNary() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNary cTNary = null;
            cTNary = (CTNary)this.get_store().add_element_user(NARY$82);
            return cTNary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeNary(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NARY$82, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPhant> getPhantList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PhantList
            extends AbstractList<CTPhant> {
                PhantList() {
                }

                @Override
                public CTPhant get(int n) {
                    return CTRunTrackChangeImpl.this.getPhantArray(n);
                }

                @Override
                public CTPhant set(int n, CTPhant cTPhant) {
                    CTPhant cTPhant2 = CTRunTrackChangeImpl.this.getPhantArray(n);
                    CTRunTrackChangeImpl.this.setPhantArray(n, cTPhant);
                    return cTPhant2;
                }

                @Override
                public void add(int n, CTPhant cTPhant) {
                    CTRunTrackChangeImpl.this.insertNewPhant(n).set((XmlObject)cTPhant);
                }

                @Override
                public CTPhant remove(int n) {
                    CTPhant cTPhant = CTRunTrackChangeImpl.this.getPhantArray(n);
                    CTRunTrackChangeImpl.this.removePhant(n);
                    return cTPhant;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfPhantArray();
                }
            }
            return new PhantList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPhant[] getPhantArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PHANT$84, arrayList);
            CTPhant[] cTPhantArray = new CTPhant[arrayList.size()];
            arrayList.toArray(cTPhantArray);
            return cTPhantArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhant getPhantArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhant cTPhant = null;
            cTPhant = (CTPhant)this.get_store().find_element_user(PHANT$84, n);
            if (cTPhant == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPhant;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPhantArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PHANT$84);
        }
    }

    @Override
    public void setPhantArray(CTPhant[] cTPhantArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPhantArray, PHANT$84);
    }

    @Override
    public void setPhantArray(int n, CTPhant cTPhant) {
        this.generatedSetterHelperImpl((XmlObject)cTPhant, PHANT$84, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhant insertNewPhant(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhant cTPhant = null;
            cTPhant = (CTPhant)this.get_store().insert_element_user(PHANT$84, n);
            return cTPhant;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhant addNewPhant() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhant cTPhant = null;
            cTPhant = (CTPhant)this.get_store().add_element_user(PHANT$84);
            return cTPhant;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePhant(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PHANT$84, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRad> getRadList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RadList
            extends AbstractList<CTRad> {
                RadList() {
                }

                @Override
                public CTRad get(int n) {
                    return CTRunTrackChangeImpl.this.getRadArray(n);
                }

                @Override
                public CTRad set(int n, CTRad cTRad) {
                    CTRad cTRad2 = CTRunTrackChangeImpl.this.getRadArray(n);
                    CTRunTrackChangeImpl.this.setRadArray(n, cTRad);
                    return cTRad2;
                }

                @Override
                public void add(int n, CTRad cTRad) {
                    CTRunTrackChangeImpl.this.insertNewRad(n).set((XmlObject)cTRad);
                }

                @Override
                public CTRad remove(int n) {
                    CTRad cTRad = CTRunTrackChangeImpl.this.getRadArray(n);
                    CTRunTrackChangeImpl.this.removeRad(n);
                    return cTRad;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfRadArray();
                }
            }
            return new RadList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRad[] getRadArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RAD$86, arrayList);
            CTRad[] cTRadArray = new CTRad[arrayList.size()];
            arrayList.toArray(cTRadArray);
            return cTRadArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRad getRadArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRad cTRad = null;
            cTRad = (CTRad)this.get_store().find_element_user(RAD$86, n);
            if (cTRad == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRad;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRadArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RAD$86);
        }
    }

    @Override
    public void setRadArray(CTRad[] cTRadArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTRadArray, RAD$86);
    }

    @Override
    public void setRadArray(int n, CTRad cTRad) {
        this.generatedSetterHelperImpl((XmlObject)cTRad, RAD$86, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRad insertNewRad(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRad cTRad = null;
            cTRad = (CTRad)this.get_store().insert_element_user(RAD$86, n);
            return cTRad;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRad addNewRad() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRad cTRad = null;
            cTRad = (CTRad)this.get_store().add_element_user(RAD$86);
            return cTRad;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRad(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RAD$86, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSPre> getSPreList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SPreList
            extends AbstractList<CTSPre> {
                SPreList() {
                }

                @Override
                public CTSPre get(int n) {
                    return CTRunTrackChangeImpl.this.getSPreArray(n);
                }

                @Override
                public CTSPre set(int n, CTSPre cTSPre) {
                    CTSPre cTSPre2 = CTRunTrackChangeImpl.this.getSPreArray(n);
                    CTRunTrackChangeImpl.this.setSPreArray(n, cTSPre);
                    return cTSPre2;
                }

                @Override
                public void add(int n, CTSPre cTSPre) {
                    CTRunTrackChangeImpl.this.insertNewSPre(n).set((XmlObject)cTSPre);
                }

                @Override
                public CTSPre remove(int n) {
                    CTSPre cTSPre = CTRunTrackChangeImpl.this.getSPreArray(n);
                    CTRunTrackChangeImpl.this.removeSPre(n);
                    return cTSPre;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfSPreArray();
                }
            }
            return new SPreList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSPre[] getSPreArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SPRE$88, arrayList);
            CTSPre[] cTSPreArray = new CTSPre[arrayList.size()];
            arrayList.toArray(cTSPreArray);
            return cTSPreArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSPre getSPreArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSPre cTSPre = null;
            cTSPre = (CTSPre)this.get_store().find_element_user(SPRE$88, n);
            if (cTSPre == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSPre;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSPreArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPRE$88);
        }
    }

    @Override
    public void setSPreArray(CTSPre[] cTSPreArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSPreArray, SPRE$88);
    }

    @Override
    public void setSPreArray(int n, CTSPre cTSPre) {
        this.generatedSetterHelperImpl((XmlObject)cTSPre, SPRE$88, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSPre insertNewSPre(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSPre cTSPre = null;
            cTSPre = (CTSPre)this.get_store().insert_element_user(SPRE$88, n);
            return cTSPre;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSPre addNewSPre() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSPre cTSPre = null;
            cTSPre = (CTSPre)this.get_store().add_element_user(SPRE$88);
            return cTSPre;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSPre(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPRE$88, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSSub> getSSubList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SSubList
            extends AbstractList<CTSSub> {
                SSubList() {
                }

                @Override
                public CTSSub get(int n) {
                    return CTRunTrackChangeImpl.this.getSSubArray(n);
                }

                @Override
                public CTSSub set(int n, CTSSub cTSSub) {
                    CTSSub cTSSub2 = CTRunTrackChangeImpl.this.getSSubArray(n);
                    CTRunTrackChangeImpl.this.setSSubArray(n, cTSSub);
                    return cTSSub2;
                }

                @Override
                public void add(int n, CTSSub cTSSub) {
                    CTRunTrackChangeImpl.this.insertNewSSub(n).set((XmlObject)cTSSub);
                }

                @Override
                public CTSSub remove(int n) {
                    CTSSub cTSSub = CTRunTrackChangeImpl.this.getSSubArray(n);
                    CTRunTrackChangeImpl.this.removeSSub(n);
                    return cTSSub;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfSSubArray();
                }
            }
            return new SSubList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSSub[] getSSubArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SSUB$90, arrayList);
            CTSSub[] cTSSubArray = new CTSSub[arrayList.size()];
            arrayList.toArray(cTSSubArray);
            return cTSSubArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSSub getSSubArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSSub cTSSub = null;
            cTSSub = (CTSSub)this.get_store().find_element_user(SSUB$90, n);
            if (cTSSub == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSSub;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSSubArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SSUB$90);
        }
    }

    @Override
    public void setSSubArray(CTSSub[] cTSSubArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSSubArray, SSUB$90);
    }

    @Override
    public void setSSubArray(int n, CTSSub cTSSub) {
        this.generatedSetterHelperImpl((XmlObject)cTSSub, SSUB$90, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSSub insertNewSSub(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSSub cTSSub = null;
            cTSSub = (CTSSub)this.get_store().insert_element_user(SSUB$90, n);
            return cTSSub;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSSub addNewSSub() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSSub cTSSub = null;
            cTSSub = (CTSSub)this.get_store().add_element_user(SSUB$90);
            return cTSSub;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSSub(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SSUB$90, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSSubSup> getSSubSupList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SSubSupList
            extends AbstractList<CTSSubSup> {
                SSubSupList() {
                }

                @Override
                public CTSSubSup get(int n) {
                    return CTRunTrackChangeImpl.this.getSSubSupArray(n);
                }

                @Override
                public CTSSubSup set(int n, CTSSubSup cTSSubSup) {
                    CTSSubSup cTSSubSup2 = CTRunTrackChangeImpl.this.getSSubSupArray(n);
                    CTRunTrackChangeImpl.this.setSSubSupArray(n, cTSSubSup);
                    return cTSSubSup2;
                }

                @Override
                public void add(int n, CTSSubSup cTSSubSup) {
                    CTRunTrackChangeImpl.this.insertNewSSubSup(n).set((XmlObject)cTSSubSup);
                }

                @Override
                public CTSSubSup remove(int n) {
                    CTSSubSup cTSSubSup = CTRunTrackChangeImpl.this.getSSubSupArray(n);
                    CTRunTrackChangeImpl.this.removeSSubSup(n);
                    return cTSSubSup;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfSSubSupArray();
                }
            }
            return new SSubSupList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSSubSup[] getSSubSupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SSUBSUP$92, arrayList);
            CTSSubSup[] cTSSubSupArray = new CTSSubSup[arrayList.size()];
            arrayList.toArray(cTSSubSupArray);
            return cTSSubSupArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSSubSup getSSubSupArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSSubSup cTSSubSup = null;
            cTSSubSup = (CTSSubSup)this.get_store().find_element_user(SSUBSUP$92, n);
            if (cTSSubSup == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSSubSup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSSubSupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SSUBSUP$92);
        }
    }

    @Override
    public void setSSubSupArray(CTSSubSup[] cTSSubSupArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSSubSupArray, SSUBSUP$92);
    }

    @Override
    public void setSSubSupArray(int n, CTSSubSup cTSSubSup) {
        this.generatedSetterHelperImpl((XmlObject)cTSSubSup, SSUBSUP$92, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSSubSup insertNewSSubSup(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSSubSup cTSSubSup = null;
            cTSSubSup = (CTSSubSup)this.get_store().insert_element_user(SSUBSUP$92, n);
            return cTSSubSup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSSubSup addNewSSubSup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSSubSup cTSSubSup = null;
            cTSSubSup = (CTSSubSup)this.get_store().add_element_user(SSUBSUP$92);
            return cTSSubSup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSSubSup(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SSUBSUP$92, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSSup> getSSupList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SSupList
            extends AbstractList<CTSSup> {
                SSupList() {
                }

                @Override
                public CTSSup get(int n) {
                    return CTRunTrackChangeImpl.this.getSSupArray(n);
                }

                @Override
                public CTSSup set(int n, CTSSup cTSSup) {
                    CTSSup cTSSup2 = CTRunTrackChangeImpl.this.getSSupArray(n);
                    CTRunTrackChangeImpl.this.setSSupArray(n, cTSSup);
                    return cTSSup2;
                }

                @Override
                public void add(int n, CTSSup cTSSup) {
                    CTRunTrackChangeImpl.this.insertNewSSup(n).set((XmlObject)cTSSup);
                }

                @Override
                public CTSSup remove(int n) {
                    CTSSup cTSSup = CTRunTrackChangeImpl.this.getSSupArray(n);
                    CTRunTrackChangeImpl.this.removeSSup(n);
                    return cTSSup;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfSSupArray();
                }
            }
            return new SSupList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSSup[] getSSupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SSUP$94, arrayList);
            CTSSup[] cTSSupArray = new CTSSup[arrayList.size()];
            arrayList.toArray(cTSSupArray);
            return cTSSupArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSSup getSSupArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSSup cTSSup = null;
            cTSSup = (CTSSup)this.get_store().find_element_user(SSUP$94, n);
            if (cTSSup == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSSup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSSupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SSUP$94);
        }
    }

    @Override
    public void setSSupArray(CTSSup[] cTSSupArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSSupArray, SSUP$94);
    }

    @Override
    public void setSSupArray(int n, CTSSup cTSSup) {
        this.generatedSetterHelperImpl((XmlObject)cTSSup, SSUP$94, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSSup insertNewSSup(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSSup cTSSup = null;
            cTSSup = (CTSSup)this.get_store().insert_element_user(SSUP$94, n);
            return cTSSup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSSup addNewSSup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSSup cTSSup = null;
            cTSSup = (CTSSup)this.get_store().add_element_user(SSUP$94);
            return cTSSup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSSup(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SSUP$94, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<org.openxmlformats.schemas.officeDocument.x2006.math.CTR> getR2List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class R2List
            extends AbstractList<org.openxmlformats.schemas.officeDocument.x2006.math.CTR> {
                R2List() {
                }

                @Override
                public org.openxmlformats.schemas.officeDocument.x2006.math.CTR get(int n) {
                    return CTRunTrackChangeImpl.this.getR2Array(n);
                }

                @Override
                public org.openxmlformats.schemas.officeDocument.x2006.math.CTR set(int n, org.openxmlformats.schemas.officeDocument.x2006.math.CTR cTR) {
                    org.openxmlformats.schemas.officeDocument.x2006.math.CTR cTR2 = CTRunTrackChangeImpl.this.getR2Array(n);
                    CTRunTrackChangeImpl.this.setR2Array(n, cTR);
                    return cTR2;
                }

                @Override
                public void add(int n, org.openxmlformats.schemas.officeDocument.x2006.math.CTR cTR) {
                    CTRunTrackChangeImpl.this.insertNewR2(n).set((XmlObject)cTR);
                }

                @Override
                public org.openxmlformats.schemas.officeDocument.x2006.math.CTR remove(int n) {
                    org.openxmlformats.schemas.officeDocument.x2006.math.CTR cTR = CTRunTrackChangeImpl.this.getR2Array(n);
                    CTRunTrackChangeImpl.this.removeR2(n);
                    return cTR;
                }

                @Override
                public int size() {
                    return CTRunTrackChangeImpl.this.sizeOfR2Array();
                }
            }
            return new R2List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR[] getR2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(R2$96, arrayList);
            org.openxmlformats.schemas.officeDocument.x2006.math.CTR[] cTRArray = new org.openxmlformats.schemas.officeDocument.x2006.math.CTR[arrayList.size()];
            arrayList.toArray(cTRArray);
            return cTRArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR getR2Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            org.openxmlformats.schemas.officeDocument.x2006.math.CTR cTR = null;
            cTR = (org.openxmlformats.schemas.officeDocument.x2006.math.CTR)this.get_store().find_element_user(R2$96, n);
            if (cTR == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTR;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfR2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(R2$96);
        }
    }

    @Override
    public void setR2Array(org.openxmlformats.schemas.officeDocument.x2006.math.CTR[] cTRArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTRArray, R2$96);
    }

    @Override
    public void setR2Array(int n, org.openxmlformats.schemas.officeDocument.x2006.math.CTR cTR) {
        this.generatedSetterHelperImpl((XmlObject)cTR, R2$96, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR insertNewR2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            org.openxmlformats.schemas.officeDocument.x2006.math.CTR cTR = null;
            cTR = (org.openxmlformats.schemas.officeDocument.x2006.math.CTR)this.get_store().insert_element_user(R2$96, n);
            return cTR;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR addNewR2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            org.openxmlformats.schemas.officeDocument.x2006.math.CTR cTR = null;
            cTR = (org.openxmlformats.schemas.officeDocument.x2006.math.CTR)this.get_store().add_element_user(R2$96);
            return cTR;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeR2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(R2$96, n);
        }
    }
}

