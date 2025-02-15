/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara
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
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;

public class CTSimpleFieldImpl
extends XmlComplexContentImpl
implements CTSimpleField {
    private static final long serialVersionUID = 1L;
    private static final QName FLDDATA$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fldData");
    private static final QName CUSTOMXML$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXml");
    private static final QName SMARTTAG$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "smartTag");
    private static final QName SDT$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdt");
    private static final QName R$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "r");
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
    private static final QName FLDSIMPLE$60 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fldSimple");
    private static final QName HYPERLINK$62 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hyperlink");
    private static final QName SUBDOC$64 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "subDoc");
    private static final QName INSTR$66 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "instr");
    private static final QName FLDLOCK$68 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fldLock");
    private static final QName DIRTY$70 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dirty");

    public CTSimpleFieldImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText getFldData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().find_element_user(FLDDATA$0, 0));
            if (cTText == null) {
                return null;
            }
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFldData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FLDDATA$0) != 0;
        }
    }

    @Override
    public void setFldData(CTText cTText) {
        this.generatedSetterHelperImpl(cTText, FLDDATA$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText addNewFldData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().add_element_user(FLDDATA$0));
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFldData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FLDDATA$0, 0);
        }
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
                    return CTSimpleFieldImpl.this.getCustomXmlArray(n);
                }

                @Override
                public CTCustomXmlRun set(int n, CTCustomXmlRun cTCustomXmlRun) {
                    CTCustomXmlRun cTCustomXmlRun2 = CTSimpleFieldImpl.this.getCustomXmlArray(n);
                    CTSimpleFieldImpl.this.setCustomXmlArray(n, cTCustomXmlRun);
                    return cTCustomXmlRun2;
                }

                @Override
                public void add(int n, CTCustomXmlRun cTCustomXmlRun) {
                    CTSimpleFieldImpl.this.insertNewCustomXml(n).set((XmlObject)cTCustomXmlRun);
                }

                @Override
                public CTCustomXmlRun remove(int n) {
                    CTCustomXmlRun cTCustomXmlRun = CTSimpleFieldImpl.this.getCustomXmlArray(n);
                    CTSimpleFieldImpl.this.removeCustomXml(n);
                    return cTCustomXmlRun;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCustomXmlArray();
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
            this.get_store().find_all_element_users(CUSTOMXML$2, arrayList);
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
            cTCustomXmlRun = (CTCustomXmlRun)this.get_store().find_element_user(CUSTOMXML$2, n);
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
            return this.get_store().count_elements(CUSTOMXML$2);
        }
    }

    @Override
    public void setCustomXmlArray(CTCustomXmlRun[] cTCustomXmlRunArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTCustomXmlRunArray, CUSTOMXML$2);
    }

    @Override
    public void setCustomXmlArray(int n, CTCustomXmlRun cTCustomXmlRun) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomXmlRun, CUSTOMXML$2, n, (short)2);
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
            cTCustomXmlRun = (CTCustomXmlRun)this.get_store().insert_element_user(CUSTOMXML$2, n);
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
            cTCustomXmlRun = (CTCustomXmlRun)this.get_store().add_element_user(CUSTOMXML$2);
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
            this.get_store().remove_element(CUSTOMXML$2, n);
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
                    return CTSimpleFieldImpl.this.getSmartTagArray(n);
                }

                @Override
                public CTSmartTagRun set(int n, CTSmartTagRun cTSmartTagRun) {
                    CTSmartTagRun cTSmartTagRun2 = CTSimpleFieldImpl.this.getSmartTagArray(n);
                    CTSimpleFieldImpl.this.setSmartTagArray(n, cTSmartTagRun);
                    return cTSmartTagRun2;
                }

                @Override
                public void add(int n, CTSmartTagRun cTSmartTagRun) {
                    CTSimpleFieldImpl.this.insertNewSmartTag(n).set(cTSmartTagRun);
                }

                @Override
                public CTSmartTagRun remove(int n) {
                    CTSmartTagRun cTSmartTagRun = CTSimpleFieldImpl.this.getSmartTagArray(n);
                    CTSimpleFieldImpl.this.removeSmartTag(n);
                    return cTSmartTagRun;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfSmartTagArray();
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
            this.get_store().find_all_element_users(SMARTTAG$4, arrayList);
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
            cTSmartTagRun = (CTSmartTagRun)((Object)this.get_store().find_element_user(SMARTTAG$4, n));
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
            return this.get_store().count_elements(SMARTTAG$4);
        }
    }

    @Override
    public void setSmartTagArray(CTSmartTagRun[] cTSmartTagRunArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSmartTagRunArray, SMARTTAG$4);
    }

    @Override
    public void setSmartTagArray(int n, CTSmartTagRun cTSmartTagRun) {
        this.generatedSetterHelperImpl(cTSmartTagRun, SMARTTAG$4, n, (short)2);
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
            cTSmartTagRun = (CTSmartTagRun)((Object)this.get_store().insert_element_user(SMARTTAG$4, n));
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
            cTSmartTagRun = (CTSmartTagRun)((Object)this.get_store().add_element_user(SMARTTAG$4));
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
            this.get_store().remove_element(SMARTTAG$4, n);
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
                    return CTSimpleFieldImpl.this.getSdtArray(n);
                }

                @Override
                public CTSdtRun set(int n, CTSdtRun cTSdtRun) {
                    CTSdtRun cTSdtRun2 = CTSimpleFieldImpl.this.getSdtArray(n);
                    CTSimpleFieldImpl.this.setSdtArray(n, cTSdtRun);
                    return cTSdtRun2;
                }

                @Override
                public void add(int n, CTSdtRun cTSdtRun) {
                    CTSimpleFieldImpl.this.insertNewSdt(n).set(cTSdtRun);
                }

                @Override
                public CTSdtRun remove(int n) {
                    CTSdtRun cTSdtRun = CTSimpleFieldImpl.this.getSdtArray(n);
                    CTSimpleFieldImpl.this.removeSdt(n);
                    return cTSdtRun;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfSdtArray();
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
            this.get_store().find_all_element_users(SDT$6, arrayList);
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
            cTSdtRun = (CTSdtRun)((Object)this.get_store().find_element_user(SDT$6, n));
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
            return this.get_store().count_elements(SDT$6);
        }
    }

    @Override
    public void setSdtArray(CTSdtRun[] cTSdtRunArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSdtRunArray, SDT$6);
    }

    @Override
    public void setSdtArray(int n, CTSdtRun cTSdtRun) {
        this.generatedSetterHelperImpl(cTSdtRun, SDT$6, n, (short)2);
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
            cTSdtRun = (CTSdtRun)((Object)this.get_store().insert_element_user(SDT$6, n));
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
            cTSdtRun = (CTSdtRun)((Object)this.get_store().add_element_user(SDT$6));
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
            this.get_store().remove_element(SDT$6, n);
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
                    return CTSimpleFieldImpl.this.getRArray(n);
                }

                @Override
                public CTR set(int n, CTR cTR) {
                    CTR cTR2 = CTSimpleFieldImpl.this.getRArray(n);
                    CTSimpleFieldImpl.this.setRArray(n, cTR);
                    return cTR2;
                }

                @Override
                public void add(int n, CTR cTR) {
                    CTSimpleFieldImpl.this.insertNewR(n).set(cTR);
                }

                @Override
                public CTR remove(int n) {
                    CTR cTR = CTSimpleFieldImpl.this.getRArray(n);
                    CTSimpleFieldImpl.this.removeR(n);
                    return cTR;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfRArray();
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
            this.get_store().find_all_element_users(R$8, arrayList);
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
            cTR = (CTR)((Object)this.get_store().find_element_user(R$8, n));
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
            return this.get_store().count_elements(R$8);
        }
    }

    @Override
    public void setRArray(CTR[] cTRArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRArray, R$8);
    }

    @Override
    public void setRArray(int n, CTR cTR) {
        this.generatedSetterHelperImpl(cTR, R$8, n, (short)2);
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
            cTR = (CTR)((Object)this.get_store().insert_element_user(R$8, n));
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
            cTR = (CTR)((Object)this.get_store().add_element_user(R$8));
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
            this.get_store().remove_element(R$8, n);
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
                    return CTSimpleFieldImpl.this.getProofErrArray(n);
                }

                @Override
                public CTProofErr set(int n, CTProofErr cTProofErr) {
                    CTProofErr cTProofErr2 = CTSimpleFieldImpl.this.getProofErrArray(n);
                    CTSimpleFieldImpl.this.setProofErrArray(n, cTProofErr);
                    return cTProofErr2;
                }

                @Override
                public void add(int n, CTProofErr cTProofErr) {
                    CTSimpleFieldImpl.this.insertNewProofErr(n).set(cTProofErr);
                }

                @Override
                public CTProofErr remove(int n) {
                    CTProofErr cTProofErr = CTSimpleFieldImpl.this.getProofErrArray(n);
                    CTSimpleFieldImpl.this.removeProofErr(n);
                    return cTProofErr;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfProofErrArray();
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
                    return CTSimpleFieldImpl.this.getPermStartArray(n);
                }

                @Override
                public CTPermStart set(int n, CTPermStart cTPermStart) {
                    CTPermStart cTPermStart2 = CTSimpleFieldImpl.this.getPermStartArray(n);
                    CTSimpleFieldImpl.this.setPermStartArray(n, cTPermStart);
                    return cTPermStart2;
                }

                @Override
                public void add(int n, CTPermStart cTPermStart) {
                    CTSimpleFieldImpl.this.insertNewPermStart(n).set((XmlObject)cTPermStart);
                }

                @Override
                public CTPermStart remove(int n) {
                    CTPermStart cTPermStart = CTSimpleFieldImpl.this.getPermStartArray(n);
                    CTSimpleFieldImpl.this.removePermStart(n);
                    return cTPermStart;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfPermStartArray();
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
                    return CTSimpleFieldImpl.this.getPermEndArray(n);
                }

                @Override
                public CTPerm set(int n, CTPerm cTPerm) {
                    CTPerm cTPerm2 = CTSimpleFieldImpl.this.getPermEndArray(n);
                    CTSimpleFieldImpl.this.setPermEndArray(n, cTPerm);
                    return cTPerm2;
                }

                @Override
                public void add(int n, CTPerm cTPerm) {
                    CTSimpleFieldImpl.this.insertNewPermEnd(n).set((XmlObject)cTPerm);
                }

                @Override
                public CTPerm remove(int n) {
                    CTPerm cTPerm = CTSimpleFieldImpl.this.getPermEndArray(n);
                    CTSimpleFieldImpl.this.removePermEnd(n);
                    return cTPerm;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfPermEndArray();
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
                    return CTSimpleFieldImpl.this.getBookmarkStartArray(n);
                }

                @Override
                public CTBookmark set(int n, CTBookmark cTBookmark) {
                    CTBookmark cTBookmark2 = CTSimpleFieldImpl.this.getBookmarkStartArray(n);
                    CTSimpleFieldImpl.this.setBookmarkStartArray(n, cTBookmark);
                    return cTBookmark2;
                }

                @Override
                public void add(int n, CTBookmark cTBookmark) {
                    CTSimpleFieldImpl.this.insertNewBookmarkStart(n).set(cTBookmark);
                }

                @Override
                public CTBookmark remove(int n) {
                    CTBookmark cTBookmark = CTSimpleFieldImpl.this.getBookmarkStartArray(n);
                    CTSimpleFieldImpl.this.removeBookmarkStart(n);
                    return cTBookmark;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfBookmarkStartArray();
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
                    return CTSimpleFieldImpl.this.getBookmarkEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTSimpleFieldImpl.this.getBookmarkEndArray(n);
                    CTSimpleFieldImpl.this.setBookmarkEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTSimpleFieldImpl.this.insertNewBookmarkEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTSimpleFieldImpl.this.getBookmarkEndArray(n);
                    CTSimpleFieldImpl.this.removeBookmarkEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfBookmarkEndArray();
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
                    return CTSimpleFieldImpl.this.getMoveFromRangeStartArray(n);
                }

                @Override
                public CTMoveBookmark set(int n, CTMoveBookmark cTMoveBookmark) {
                    CTMoveBookmark cTMoveBookmark2 = CTSimpleFieldImpl.this.getMoveFromRangeStartArray(n);
                    CTSimpleFieldImpl.this.setMoveFromRangeStartArray(n, cTMoveBookmark);
                    return cTMoveBookmark2;
                }

                @Override
                public void add(int n, CTMoveBookmark cTMoveBookmark) {
                    CTSimpleFieldImpl.this.insertNewMoveFromRangeStart(n).set(cTMoveBookmark);
                }

                @Override
                public CTMoveBookmark remove(int n) {
                    CTMoveBookmark cTMoveBookmark = CTSimpleFieldImpl.this.getMoveFromRangeStartArray(n);
                    CTSimpleFieldImpl.this.removeMoveFromRangeStart(n);
                    return cTMoveBookmark;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfMoveFromRangeStartArray();
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
                    return CTSimpleFieldImpl.this.getMoveFromRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTSimpleFieldImpl.this.getMoveFromRangeEndArray(n);
                    CTSimpleFieldImpl.this.setMoveFromRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTSimpleFieldImpl.this.insertNewMoveFromRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTSimpleFieldImpl.this.getMoveFromRangeEndArray(n);
                    CTSimpleFieldImpl.this.removeMoveFromRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfMoveFromRangeEndArray();
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
                    return CTSimpleFieldImpl.this.getMoveToRangeStartArray(n);
                }

                @Override
                public CTMoveBookmark set(int n, CTMoveBookmark cTMoveBookmark) {
                    CTMoveBookmark cTMoveBookmark2 = CTSimpleFieldImpl.this.getMoveToRangeStartArray(n);
                    CTSimpleFieldImpl.this.setMoveToRangeStartArray(n, cTMoveBookmark);
                    return cTMoveBookmark2;
                }

                @Override
                public void add(int n, CTMoveBookmark cTMoveBookmark) {
                    CTSimpleFieldImpl.this.insertNewMoveToRangeStart(n).set(cTMoveBookmark);
                }

                @Override
                public CTMoveBookmark remove(int n) {
                    CTMoveBookmark cTMoveBookmark = CTSimpleFieldImpl.this.getMoveToRangeStartArray(n);
                    CTSimpleFieldImpl.this.removeMoveToRangeStart(n);
                    return cTMoveBookmark;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfMoveToRangeStartArray();
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
                    return CTSimpleFieldImpl.this.getMoveToRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTSimpleFieldImpl.this.getMoveToRangeEndArray(n);
                    CTSimpleFieldImpl.this.setMoveToRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTSimpleFieldImpl.this.insertNewMoveToRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTSimpleFieldImpl.this.getMoveToRangeEndArray(n);
                    CTSimpleFieldImpl.this.removeMoveToRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfMoveToRangeEndArray();
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
                    return CTSimpleFieldImpl.this.getCommentRangeStartArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTSimpleFieldImpl.this.getCommentRangeStartArray(n);
                    CTSimpleFieldImpl.this.setCommentRangeStartArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTSimpleFieldImpl.this.insertNewCommentRangeStart(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTSimpleFieldImpl.this.getCommentRangeStartArray(n);
                    CTSimpleFieldImpl.this.removeCommentRangeStart(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCommentRangeStartArray();
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
                    return CTSimpleFieldImpl.this.getCommentRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTSimpleFieldImpl.this.getCommentRangeEndArray(n);
                    CTSimpleFieldImpl.this.setCommentRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTSimpleFieldImpl.this.insertNewCommentRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTSimpleFieldImpl.this.getCommentRangeEndArray(n);
                    CTSimpleFieldImpl.this.removeCommentRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCommentRangeEndArray();
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
                    return CTSimpleFieldImpl.this.getCustomXmlInsRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTSimpleFieldImpl.this.getCustomXmlInsRangeStartArray(n);
                    CTSimpleFieldImpl.this.setCustomXmlInsRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTSimpleFieldImpl.this.insertNewCustomXmlInsRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTSimpleFieldImpl.this.getCustomXmlInsRangeStartArray(n);
                    CTSimpleFieldImpl.this.removeCustomXmlInsRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCustomXmlInsRangeStartArray();
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
                    return CTSimpleFieldImpl.this.getCustomXmlInsRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTSimpleFieldImpl.this.getCustomXmlInsRangeEndArray(n);
                    CTSimpleFieldImpl.this.setCustomXmlInsRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTSimpleFieldImpl.this.insertNewCustomXmlInsRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTSimpleFieldImpl.this.getCustomXmlInsRangeEndArray(n);
                    CTSimpleFieldImpl.this.removeCustomXmlInsRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCustomXmlInsRangeEndArray();
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
                    return CTSimpleFieldImpl.this.getCustomXmlDelRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTSimpleFieldImpl.this.getCustomXmlDelRangeStartArray(n);
                    CTSimpleFieldImpl.this.setCustomXmlDelRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTSimpleFieldImpl.this.insertNewCustomXmlDelRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTSimpleFieldImpl.this.getCustomXmlDelRangeStartArray(n);
                    CTSimpleFieldImpl.this.removeCustomXmlDelRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCustomXmlDelRangeStartArray();
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
                    return CTSimpleFieldImpl.this.getCustomXmlDelRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTSimpleFieldImpl.this.getCustomXmlDelRangeEndArray(n);
                    CTSimpleFieldImpl.this.setCustomXmlDelRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTSimpleFieldImpl.this.insertNewCustomXmlDelRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTSimpleFieldImpl.this.getCustomXmlDelRangeEndArray(n);
                    CTSimpleFieldImpl.this.removeCustomXmlDelRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCustomXmlDelRangeEndArray();
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
                    return CTSimpleFieldImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTSimpleFieldImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                    CTSimpleFieldImpl.this.setCustomXmlMoveFromRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTSimpleFieldImpl.this.insertNewCustomXmlMoveFromRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTSimpleFieldImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                    CTSimpleFieldImpl.this.removeCustomXmlMoveFromRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCustomXmlMoveFromRangeStartArray();
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
                    return CTSimpleFieldImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTSimpleFieldImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                    CTSimpleFieldImpl.this.setCustomXmlMoveFromRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTSimpleFieldImpl.this.insertNewCustomXmlMoveFromRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTSimpleFieldImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                    CTSimpleFieldImpl.this.removeCustomXmlMoveFromRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCustomXmlMoveFromRangeEndArray();
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
                    return CTSimpleFieldImpl.this.getCustomXmlMoveToRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTSimpleFieldImpl.this.getCustomXmlMoveToRangeStartArray(n);
                    CTSimpleFieldImpl.this.setCustomXmlMoveToRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTSimpleFieldImpl.this.insertNewCustomXmlMoveToRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTSimpleFieldImpl.this.getCustomXmlMoveToRangeStartArray(n);
                    CTSimpleFieldImpl.this.removeCustomXmlMoveToRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCustomXmlMoveToRangeStartArray();
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
                    return CTSimpleFieldImpl.this.getCustomXmlMoveToRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTSimpleFieldImpl.this.getCustomXmlMoveToRangeEndArray(n);
                    CTSimpleFieldImpl.this.setCustomXmlMoveToRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTSimpleFieldImpl.this.insertNewCustomXmlMoveToRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTSimpleFieldImpl.this.getCustomXmlMoveToRangeEndArray(n);
                    CTSimpleFieldImpl.this.removeCustomXmlMoveToRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfCustomXmlMoveToRangeEndArray();
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
                    return CTSimpleFieldImpl.this.getInsArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTSimpleFieldImpl.this.getInsArray(n);
                    CTSimpleFieldImpl.this.setInsArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTSimpleFieldImpl.this.insertNewIns(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTSimpleFieldImpl.this.getInsArray(n);
                    CTSimpleFieldImpl.this.removeIns(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfInsArray();
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
                    return CTSimpleFieldImpl.this.getDelArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTSimpleFieldImpl.this.getDelArray(n);
                    CTSimpleFieldImpl.this.setDelArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTSimpleFieldImpl.this.insertNewDel(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTSimpleFieldImpl.this.getDelArray(n);
                    CTSimpleFieldImpl.this.removeDel(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfDelArray();
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
                    return CTSimpleFieldImpl.this.getMoveFromArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTSimpleFieldImpl.this.getMoveFromArray(n);
                    CTSimpleFieldImpl.this.setMoveFromArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTSimpleFieldImpl.this.insertNewMoveFrom(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTSimpleFieldImpl.this.getMoveFromArray(n);
                    CTSimpleFieldImpl.this.removeMoveFrom(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfMoveFromArray();
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
                    return CTSimpleFieldImpl.this.getMoveToArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTSimpleFieldImpl.this.getMoveToArray(n);
                    CTSimpleFieldImpl.this.setMoveToArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTSimpleFieldImpl.this.insertNewMoveTo(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTSimpleFieldImpl.this.getMoveToArray(n);
                    CTSimpleFieldImpl.this.removeMoveTo(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfMoveToArray();
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
                    return CTSimpleFieldImpl.this.getOMathParaArray(n);
                }

                @Override
                public CTOMathPara set(int n, CTOMathPara cTOMathPara) {
                    CTOMathPara cTOMathPara2 = CTSimpleFieldImpl.this.getOMathParaArray(n);
                    CTSimpleFieldImpl.this.setOMathParaArray(n, cTOMathPara);
                    return cTOMathPara2;
                }

                @Override
                public void add(int n, CTOMathPara cTOMathPara) {
                    CTSimpleFieldImpl.this.insertNewOMathPara(n).set((XmlObject)cTOMathPara);
                }

                @Override
                public CTOMathPara remove(int n) {
                    CTOMathPara cTOMathPara = CTSimpleFieldImpl.this.getOMathParaArray(n);
                    CTSimpleFieldImpl.this.removeOMathPara(n);
                    return cTOMathPara;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfOMathParaArray();
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
                    return CTSimpleFieldImpl.this.getOMathArray(n);
                }

                @Override
                public CTOMath set(int n, CTOMath cTOMath) {
                    CTOMath cTOMath2 = CTSimpleFieldImpl.this.getOMathArray(n);
                    CTSimpleFieldImpl.this.setOMathArray(n, cTOMath);
                    return cTOMath2;
                }

                @Override
                public void add(int n, CTOMath cTOMath) {
                    CTSimpleFieldImpl.this.insertNewOMath(n).set((XmlObject)cTOMath);
                }

                @Override
                public CTOMath remove(int n) {
                    CTOMath cTOMath = CTSimpleFieldImpl.this.getOMathArray(n);
                    CTSimpleFieldImpl.this.removeOMath(n);
                    return cTOMath;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfOMathArray();
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
    public List<CTSimpleField> getFldSimpleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FldSimpleList
            extends AbstractList<CTSimpleField> {
                FldSimpleList() {
                }

                @Override
                public CTSimpleField get(int n) {
                    return CTSimpleFieldImpl.this.getFldSimpleArray(n);
                }

                @Override
                public CTSimpleField set(int n, CTSimpleField cTSimpleField) {
                    CTSimpleField cTSimpleField2 = CTSimpleFieldImpl.this.getFldSimpleArray(n);
                    CTSimpleFieldImpl.this.setFldSimpleArray(n, cTSimpleField);
                    return cTSimpleField2;
                }

                @Override
                public void add(int n, CTSimpleField cTSimpleField) {
                    CTSimpleFieldImpl.this.insertNewFldSimple(n).set(cTSimpleField);
                }

                @Override
                public CTSimpleField remove(int n) {
                    CTSimpleField cTSimpleField = CTSimpleFieldImpl.this.getFldSimpleArray(n);
                    CTSimpleFieldImpl.this.removeFldSimple(n);
                    return cTSimpleField;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfFldSimpleArray();
                }
            }
            return new FldSimpleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSimpleField[] getFldSimpleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FLDSIMPLE$60, arrayList);
            CTSimpleField[] cTSimpleFieldArray = new CTSimpleField[arrayList.size()];
            arrayList.toArray(cTSimpleFieldArray);
            return cTSimpleFieldArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSimpleField getFldSimpleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSimpleField cTSimpleField = null;
            cTSimpleField = (CTSimpleField)((Object)this.get_store().find_element_user(FLDSIMPLE$60, n));
            if (cTSimpleField == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSimpleField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFldSimpleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FLDSIMPLE$60);
        }
    }

    @Override
    public void setFldSimpleArray(CTSimpleField[] cTSimpleFieldArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSimpleFieldArray, FLDSIMPLE$60);
    }

    @Override
    public void setFldSimpleArray(int n, CTSimpleField cTSimpleField) {
        this.generatedSetterHelperImpl(cTSimpleField, FLDSIMPLE$60, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSimpleField insertNewFldSimple(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSimpleField cTSimpleField = null;
            cTSimpleField = (CTSimpleField)((Object)this.get_store().insert_element_user(FLDSIMPLE$60, n));
            return cTSimpleField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSimpleField addNewFldSimple() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSimpleField cTSimpleField = null;
            cTSimpleField = (CTSimpleField)((Object)this.get_store().add_element_user(FLDSIMPLE$60));
            return cTSimpleField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFldSimple(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FLDSIMPLE$60, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTHyperlink> getHyperlinkList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HyperlinkList
            extends AbstractList<CTHyperlink> {
                HyperlinkList() {
                }

                @Override
                public CTHyperlink get(int n) {
                    return CTSimpleFieldImpl.this.getHyperlinkArray(n);
                }

                @Override
                public CTHyperlink set(int n, CTHyperlink cTHyperlink) {
                    CTHyperlink cTHyperlink2 = CTSimpleFieldImpl.this.getHyperlinkArray(n);
                    CTSimpleFieldImpl.this.setHyperlinkArray(n, cTHyperlink);
                    return cTHyperlink2;
                }

                @Override
                public void add(int n, CTHyperlink cTHyperlink) {
                    CTSimpleFieldImpl.this.insertNewHyperlink(n).set(cTHyperlink);
                }

                @Override
                public CTHyperlink remove(int n) {
                    CTHyperlink cTHyperlink = CTSimpleFieldImpl.this.getHyperlinkArray(n);
                    CTSimpleFieldImpl.this.removeHyperlink(n);
                    return cTHyperlink;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfHyperlinkArray();
                }
            }
            return new HyperlinkList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTHyperlink[] getHyperlinkArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HYPERLINK$62, arrayList);
            CTHyperlink[] cTHyperlinkArray = new CTHyperlink[arrayList.size()];
            arrayList.toArray(cTHyperlinkArray);
            return cTHyperlinkArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHyperlink getHyperlinkArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHyperlink cTHyperlink = null;
            cTHyperlink = (CTHyperlink)((Object)this.get_store().find_element_user(HYPERLINK$62, n));
            if (cTHyperlink == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTHyperlink;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHyperlinkArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HYPERLINK$62);
        }
    }

    @Override
    public void setHyperlinkArray(CTHyperlink[] cTHyperlinkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTHyperlinkArray, HYPERLINK$62);
    }

    @Override
    public void setHyperlinkArray(int n, CTHyperlink cTHyperlink) {
        this.generatedSetterHelperImpl(cTHyperlink, HYPERLINK$62, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHyperlink insertNewHyperlink(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHyperlink cTHyperlink = null;
            cTHyperlink = (CTHyperlink)((Object)this.get_store().insert_element_user(HYPERLINK$62, n));
            return cTHyperlink;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHyperlink addNewHyperlink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHyperlink cTHyperlink = null;
            cTHyperlink = (CTHyperlink)((Object)this.get_store().add_element_user(HYPERLINK$62));
            return cTHyperlink;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHyperlink(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HYPERLINK$62, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRel> getSubDocList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SubDocList
            extends AbstractList<CTRel> {
                SubDocList() {
                }

                @Override
                public CTRel get(int n) {
                    return CTSimpleFieldImpl.this.getSubDocArray(n);
                }

                @Override
                public CTRel set(int n, CTRel cTRel) {
                    CTRel cTRel2 = CTSimpleFieldImpl.this.getSubDocArray(n);
                    CTSimpleFieldImpl.this.setSubDocArray(n, cTRel);
                    return cTRel2;
                }

                @Override
                public void add(int n, CTRel cTRel) {
                    CTSimpleFieldImpl.this.insertNewSubDoc(n).set(cTRel);
                }

                @Override
                public CTRel remove(int n) {
                    CTRel cTRel = CTSimpleFieldImpl.this.getSubDocArray(n);
                    CTSimpleFieldImpl.this.removeSubDoc(n);
                    return cTRel;
                }

                @Override
                public int size() {
                    return CTSimpleFieldImpl.this.sizeOfSubDocArray();
                }
            }
            return new SubDocList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRel[] getSubDocArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SUBDOC$64, arrayList);
            CTRel[] cTRelArray = new CTRel[arrayList.size()];
            arrayList.toArray(cTRelArray);
            return cTRelArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel getSubDocArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)((Object)this.get_store().find_element_user(SUBDOC$64, n));
            if (cTRel == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSubDocArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SUBDOC$64);
        }
    }

    @Override
    public void setSubDocArray(CTRel[] cTRelArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRelArray, SUBDOC$64);
    }

    @Override
    public void setSubDocArray(int n, CTRel cTRel) {
        this.generatedSetterHelperImpl(cTRel, SUBDOC$64, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel insertNewSubDoc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)((Object)this.get_store().insert_element_user(SUBDOC$64, n));
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel addNewSubDoc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)((Object)this.get_store().add_element_user(SUBDOC$64));
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSubDoc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SUBDOC$64, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getInstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INSTR$66));
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
    public STString xgetInstr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(INSTR$66));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setInstr(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INSTR$66));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(INSTR$66));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetInstr(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(INSTR$66));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(INSTR$66));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getFldLock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FLDLOCK$68));
            if (simpleValue == null) {
                return null;
            }
            return (STOnOff.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff xgetFldLock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(FLDLOCK$68));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFldLock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FLDLOCK$68) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFldLock(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FLDLOCK$68));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FLDLOCK$68));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFldLock(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(FLDLOCK$68));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(FLDLOCK$68));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFldLock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FLDLOCK$68);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIRTY$70));
            if (simpleValue == null) {
                return null;
            }
            return (STOnOff.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff xgetDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(DIRTY$70));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DIRTY$70) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDirty(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIRTY$70));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DIRTY$70));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDirty(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(DIRTY$70));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(DIRTY$70));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDirty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DIRTY$70);
        }
    }
}

