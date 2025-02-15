/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSym;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

public class CTRImpl
extends XmlComplexContentImpl
implements CTR {
    private static final long serialVersionUID = 1L;
    private static final QName RPR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr");
    private static final QName BR$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "br");
    private static final QName T$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "t");
    private static final QName DELTEXT$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "delText");
    private static final QName INSTRTEXT$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "instrText");
    private static final QName DELINSTRTEXT$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "delInstrText");
    private static final QName NOBREAKHYPHEN$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noBreakHyphen");
    private static final QName SOFTHYPHEN$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "softHyphen");
    private static final QName DAYSHORT$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dayShort");
    private static final QName MONTHSHORT$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "monthShort");
    private static final QName YEARSHORT$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "yearShort");
    private static final QName DAYLONG$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dayLong");
    private static final QName MONTHLONG$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "monthLong");
    private static final QName YEARLONG$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "yearLong");
    private static final QName ANNOTATIONREF$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "annotationRef");
    private static final QName FOOTNOTEREF$30 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnoteRef");
    private static final QName ENDNOTEREF$32 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "endnoteRef");
    private static final QName SEPARATOR$34 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "separator");
    private static final QName CONTINUATIONSEPARATOR$36 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "continuationSeparator");
    private static final QName SYM$38 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sym");
    private static final QName PGNUM$40 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pgNum");
    private static final QName CR$42 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cr");
    private static final QName TAB$44 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tab");
    private static final QName OBJECT$46 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "object");
    private static final QName PICT$48 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pict");
    private static final QName FLDCHAR$50 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fldChar");
    private static final QName RUBY$52 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ruby");
    private static final QName FOOTNOTEREFERENCE$54 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnoteReference");
    private static final QName ENDNOTEREFERENCE$56 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "endnoteReference");
    private static final QName COMMENTREFERENCE$58 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentReference");
    private static final QName DRAWING$60 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "drawing");
    private static final QName PTAB$62 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ptab");
    private static final QName LASTRENDEREDPAGEBREAK$64 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lastRenderedPageBreak");
    private static final QName RSIDRPR$66 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidRPr");
    private static final QName RSIDDEL$68 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidDel");
    private static final QName RSIDR$70 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidR");

    public CTRImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPr getRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPr cTRPr = null;
            cTRPr = (CTRPr)((Object)this.get_store().find_element_user(RPR$0, 0));
            if (cTRPr == null) {
                return null;
            }
            return cTRPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RPR$0) != 0;
        }
    }

    @Override
    public void setRPr(CTRPr cTRPr) {
        this.generatedSetterHelperImpl(cTRPr, RPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPr addNewRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPr cTRPr = null;
            cTRPr = (CTRPr)((Object)this.get_store().add_element_user(RPR$0));
            return cTRPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RPR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBr> getBrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BrList
            extends AbstractList<CTBr> {
                BrList() {
                }

                @Override
                public CTBr get(int n) {
                    return CTRImpl.this.getBrArray(n);
                }

                @Override
                public CTBr set(int n, CTBr cTBr) {
                    CTBr cTBr2 = CTRImpl.this.getBrArray(n);
                    CTRImpl.this.setBrArray(n, cTBr);
                    return cTBr2;
                }

                @Override
                public void add(int n, CTBr cTBr) {
                    CTRImpl.this.insertNewBr(n).set(cTBr);
                }

                @Override
                public CTBr remove(int n) {
                    CTBr cTBr = CTRImpl.this.getBrArray(n);
                    CTRImpl.this.removeBr(n);
                    return cTBr;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfBrArray();
                }
            }
            return new BrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBr[] getBrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BR$2, arrayList);
            CTBr[] cTBrArray = new CTBr[arrayList.size()];
            arrayList.toArray(cTBrArray);
            return cTBrArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBr getBrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBr cTBr = null;
            cTBr = (CTBr)((Object)this.get_store().find_element_user(BR$2, n));
            if (cTBr == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BR$2);
        }
    }

    @Override
    public void setBrArray(CTBr[] cTBrArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBrArray, BR$2);
    }

    @Override
    public void setBrArray(int n, CTBr cTBr) {
        this.generatedSetterHelperImpl(cTBr, BR$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBr insertNewBr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBr cTBr = null;
            cTBr = (CTBr)((Object)this.get_store().insert_element_user(BR$2, n));
            return cTBr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBr addNewBr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBr cTBr = null;
            cTBr = (CTBr)((Object)this.get_store().add_element_user(BR$2));
            return cTBr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BR$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTText> getTList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TList
            extends AbstractList<CTText> {
                TList() {
                }

                @Override
                public CTText get(int n) {
                    return CTRImpl.this.getTArray(n);
                }

                @Override
                public CTText set(int n, CTText cTText) {
                    CTText cTText2 = CTRImpl.this.getTArray(n);
                    CTRImpl.this.setTArray(n, cTText);
                    return cTText2;
                }

                @Override
                public void add(int n, CTText cTText) {
                    CTRImpl.this.insertNewT(n).set(cTText);
                }

                @Override
                public CTText remove(int n) {
                    CTText cTText = CTRImpl.this.getTArray(n);
                    CTRImpl.this.removeT(n);
                    return cTText;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfTArray();
                }
            }
            return new TList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTText[] getTArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(T$4, arrayList);
            CTText[] cTTextArray = new CTText[arrayList.size()];
            arrayList.toArray(cTTextArray);
            return cTTextArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText getTArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().find_element_user(T$4, n));
            if (cTText == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(T$4);
        }
    }

    @Override
    public void setTArray(CTText[] cTTextArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTextArray, T$4);
    }

    @Override
    public void setTArray(int n, CTText cTText) {
        this.generatedSetterHelperImpl(cTText, T$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText insertNewT(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().insert_element_user(T$4, n));
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText addNewT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().add_element_user(T$4));
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeT(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(T$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTText> getDelTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DelTextList
            extends AbstractList<CTText> {
                DelTextList() {
                }

                @Override
                public CTText get(int n) {
                    return CTRImpl.this.getDelTextArray(n);
                }

                @Override
                public CTText set(int n, CTText cTText) {
                    CTText cTText2 = CTRImpl.this.getDelTextArray(n);
                    CTRImpl.this.setDelTextArray(n, cTText);
                    return cTText2;
                }

                @Override
                public void add(int n, CTText cTText) {
                    CTRImpl.this.insertNewDelText(n).set(cTText);
                }

                @Override
                public CTText remove(int n) {
                    CTText cTText = CTRImpl.this.getDelTextArray(n);
                    CTRImpl.this.removeDelText(n);
                    return cTText;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfDelTextArray();
                }
            }
            return new DelTextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTText[] getDelTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DELTEXT$6, arrayList);
            CTText[] cTTextArray = new CTText[arrayList.size()];
            arrayList.toArray(cTTextArray);
            return cTTextArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText getDelTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().find_element_user(DELTEXT$6, n));
            if (cTText == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDelTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DELTEXT$6);
        }
    }

    @Override
    public void setDelTextArray(CTText[] cTTextArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTextArray, DELTEXT$6);
    }

    @Override
    public void setDelTextArray(int n, CTText cTText) {
        this.generatedSetterHelperImpl(cTText, DELTEXT$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText insertNewDelText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().insert_element_user(DELTEXT$6, n));
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText addNewDelText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().add_element_user(DELTEXT$6));
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDelText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DELTEXT$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTText> getInstrTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class InstrTextList
            extends AbstractList<CTText> {
                InstrTextList() {
                }

                @Override
                public CTText get(int n) {
                    return CTRImpl.this.getInstrTextArray(n);
                }

                @Override
                public CTText set(int n, CTText cTText) {
                    CTText cTText2 = CTRImpl.this.getInstrTextArray(n);
                    CTRImpl.this.setInstrTextArray(n, cTText);
                    return cTText2;
                }

                @Override
                public void add(int n, CTText cTText) {
                    CTRImpl.this.insertNewInstrText(n).set(cTText);
                }

                @Override
                public CTText remove(int n) {
                    CTText cTText = CTRImpl.this.getInstrTextArray(n);
                    CTRImpl.this.removeInstrText(n);
                    return cTText;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfInstrTextArray();
                }
            }
            return new InstrTextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTText[] getInstrTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(INSTRTEXT$8, arrayList);
            CTText[] cTTextArray = new CTText[arrayList.size()];
            arrayList.toArray(cTTextArray);
            return cTTextArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText getInstrTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().find_element_user(INSTRTEXT$8, n));
            if (cTText == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfInstrTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INSTRTEXT$8);
        }
    }

    @Override
    public void setInstrTextArray(CTText[] cTTextArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTextArray, INSTRTEXT$8);
    }

    @Override
    public void setInstrTextArray(int n, CTText cTText) {
        this.generatedSetterHelperImpl(cTText, INSTRTEXT$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText insertNewInstrText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().insert_element_user(INSTRTEXT$8, n));
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText addNewInstrText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().add_element_user(INSTRTEXT$8));
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeInstrText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INSTRTEXT$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTText> getDelInstrTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DelInstrTextList
            extends AbstractList<CTText> {
                DelInstrTextList() {
                }

                @Override
                public CTText get(int n) {
                    return CTRImpl.this.getDelInstrTextArray(n);
                }

                @Override
                public CTText set(int n, CTText cTText) {
                    CTText cTText2 = CTRImpl.this.getDelInstrTextArray(n);
                    CTRImpl.this.setDelInstrTextArray(n, cTText);
                    return cTText2;
                }

                @Override
                public void add(int n, CTText cTText) {
                    CTRImpl.this.insertNewDelInstrText(n).set(cTText);
                }

                @Override
                public CTText remove(int n) {
                    CTText cTText = CTRImpl.this.getDelInstrTextArray(n);
                    CTRImpl.this.removeDelInstrText(n);
                    return cTText;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfDelInstrTextArray();
                }
            }
            return new DelInstrTextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTText[] getDelInstrTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DELINSTRTEXT$10, arrayList);
            CTText[] cTTextArray = new CTText[arrayList.size()];
            arrayList.toArray(cTTextArray);
            return cTTextArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText getDelInstrTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().find_element_user(DELINSTRTEXT$10, n));
            if (cTText == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDelInstrTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DELINSTRTEXT$10);
        }
    }

    @Override
    public void setDelInstrTextArray(CTText[] cTTextArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTextArray, DELINSTRTEXT$10);
    }

    @Override
    public void setDelInstrTextArray(int n, CTText cTText) {
        this.generatedSetterHelperImpl(cTText, DELINSTRTEXT$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText insertNewDelInstrText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().insert_element_user(DELINSTRTEXT$10, n));
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTText addNewDelInstrText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTText cTText = null;
            cTText = (CTText)((Object)this.get_store().add_element_user(DELINSTRTEXT$10));
            return cTText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDelInstrText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DELINSTRTEXT$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getNoBreakHyphenList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NoBreakHyphenList
            extends AbstractList<CTEmpty> {
                NoBreakHyphenList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getNoBreakHyphenArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getNoBreakHyphenArray(n);
                    CTRImpl.this.setNoBreakHyphenArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewNoBreakHyphen(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getNoBreakHyphenArray(n);
                    CTRImpl.this.removeNoBreakHyphen(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfNoBreakHyphenArray();
                }
            }
            return new NoBreakHyphenList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getNoBreakHyphenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NOBREAKHYPHEN$12, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getNoBreakHyphenArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(NOBREAKHYPHEN$12, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfNoBreakHyphenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOBREAKHYPHEN$12);
        }
    }

    @Override
    public void setNoBreakHyphenArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, NOBREAKHYPHEN$12);
    }

    @Override
    public void setNoBreakHyphenArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, NOBREAKHYPHEN$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewNoBreakHyphen(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(NOBREAKHYPHEN$12, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewNoBreakHyphen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(NOBREAKHYPHEN$12));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeNoBreakHyphen(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOBREAKHYPHEN$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getSoftHyphenList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SoftHyphenList
            extends AbstractList<CTEmpty> {
                SoftHyphenList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getSoftHyphenArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getSoftHyphenArray(n);
                    CTRImpl.this.setSoftHyphenArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewSoftHyphen(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getSoftHyphenArray(n);
                    CTRImpl.this.removeSoftHyphen(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfSoftHyphenArray();
                }
            }
            return new SoftHyphenList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getSoftHyphenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SOFTHYPHEN$14, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getSoftHyphenArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(SOFTHYPHEN$14, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSoftHyphenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SOFTHYPHEN$14);
        }
    }

    @Override
    public void setSoftHyphenArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, SOFTHYPHEN$14);
    }

    @Override
    public void setSoftHyphenArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, SOFTHYPHEN$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewSoftHyphen(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(SOFTHYPHEN$14, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewSoftHyphen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(SOFTHYPHEN$14));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSoftHyphen(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SOFTHYPHEN$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getDayShortList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DayShortList
            extends AbstractList<CTEmpty> {
                DayShortList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getDayShortArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getDayShortArray(n);
                    CTRImpl.this.setDayShortArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewDayShort(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getDayShortArray(n);
                    CTRImpl.this.removeDayShort(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfDayShortArray();
                }
            }
            return new DayShortList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getDayShortArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DAYSHORT$16, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getDayShortArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(DAYSHORT$16, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDayShortArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DAYSHORT$16);
        }
    }

    @Override
    public void setDayShortArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, DAYSHORT$16);
    }

    @Override
    public void setDayShortArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, DAYSHORT$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewDayShort(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(DAYSHORT$16, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewDayShort() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(DAYSHORT$16));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDayShort(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DAYSHORT$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getMonthShortList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MonthShortList
            extends AbstractList<CTEmpty> {
                MonthShortList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getMonthShortArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getMonthShortArray(n);
                    CTRImpl.this.setMonthShortArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewMonthShort(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getMonthShortArray(n);
                    CTRImpl.this.removeMonthShort(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfMonthShortArray();
                }
            }
            return new MonthShortList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getMonthShortArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MONTHSHORT$18, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getMonthShortArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(MONTHSHORT$18, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMonthShortArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MONTHSHORT$18);
        }
    }

    @Override
    public void setMonthShortArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, MONTHSHORT$18);
    }

    @Override
    public void setMonthShortArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, MONTHSHORT$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewMonthShort(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(MONTHSHORT$18, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewMonthShort() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(MONTHSHORT$18));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMonthShort(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MONTHSHORT$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getYearShortList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class YearShortList
            extends AbstractList<CTEmpty> {
                YearShortList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getYearShortArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getYearShortArray(n);
                    CTRImpl.this.setYearShortArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewYearShort(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getYearShortArray(n);
                    CTRImpl.this.removeYearShort(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfYearShortArray();
                }
            }
            return new YearShortList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getYearShortArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(YEARSHORT$20, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getYearShortArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(YEARSHORT$20, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfYearShortArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(YEARSHORT$20);
        }
    }

    @Override
    public void setYearShortArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, YEARSHORT$20);
    }

    @Override
    public void setYearShortArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, YEARSHORT$20, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewYearShort(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(YEARSHORT$20, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewYearShort() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(YEARSHORT$20));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeYearShort(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(YEARSHORT$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getDayLongList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DayLongList
            extends AbstractList<CTEmpty> {
                DayLongList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getDayLongArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getDayLongArray(n);
                    CTRImpl.this.setDayLongArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewDayLong(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getDayLongArray(n);
                    CTRImpl.this.removeDayLong(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfDayLongArray();
                }
            }
            return new DayLongList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getDayLongArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DAYLONG$22, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getDayLongArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(DAYLONG$22, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDayLongArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DAYLONG$22);
        }
    }

    @Override
    public void setDayLongArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, DAYLONG$22);
    }

    @Override
    public void setDayLongArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, DAYLONG$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewDayLong(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(DAYLONG$22, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewDayLong() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(DAYLONG$22));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDayLong(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DAYLONG$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getMonthLongList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MonthLongList
            extends AbstractList<CTEmpty> {
                MonthLongList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getMonthLongArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getMonthLongArray(n);
                    CTRImpl.this.setMonthLongArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewMonthLong(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getMonthLongArray(n);
                    CTRImpl.this.removeMonthLong(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfMonthLongArray();
                }
            }
            return new MonthLongList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getMonthLongArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MONTHLONG$24, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getMonthLongArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(MONTHLONG$24, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMonthLongArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MONTHLONG$24);
        }
    }

    @Override
    public void setMonthLongArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, MONTHLONG$24);
    }

    @Override
    public void setMonthLongArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, MONTHLONG$24, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewMonthLong(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(MONTHLONG$24, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewMonthLong() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(MONTHLONG$24));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMonthLong(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MONTHLONG$24, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getYearLongList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class YearLongList
            extends AbstractList<CTEmpty> {
                YearLongList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getYearLongArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getYearLongArray(n);
                    CTRImpl.this.setYearLongArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewYearLong(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getYearLongArray(n);
                    CTRImpl.this.removeYearLong(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfYearLongArray();
                }
            }
            return new YearLongList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getYearLongArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(YEARLONG$26, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getYearLongArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(YEARLONG$26, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfYearLongArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(YEARLONG$26);
        }
    }

    @Override
    public void setYearLongArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, YEARLONG$26);
    }

    @Override
    public void setYearLongArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, YEARLONG$26, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewYearLong(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(YEARLONG$26, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewYearLong() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(YEARLONG$26));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeYearLong(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(YEARLONG$26, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getAnnotationRefList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AnnotationRefList
            extends AbstractList<CTEmpty> {
                AnnotationRefList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getAnnotationRefArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getAnnotationRefArray(n);
                    CTRImpl.this.setAnnotationRefArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewAnnotationRef(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getAnnotationRefArray(n);
                    CTRImpl.this.removeAnnotationRef(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfAnnotationRefArray();
                }
            }
            return new AnnotationRefList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getAnnotationRefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ANNOTATIONREF$28, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getAnnotationRefArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(ANNOTATIONREF$28, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAnnotationRefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ANNOTATIONREF$28);
        }
    }

    @Override
    public void setAnnotationRefArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, ANNOTATIONREF$28);
    }

    @Override
    public void setAnnotationRefArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, ANNOTATIONREF$28, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewAnnotationRef(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(ANNOTATIONREF$28, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewAnnotationRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(ANNOTATIONREF$28));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAnnotationRef(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ANNOTATIONREF$28, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getFootnoteRefList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FootnoteRefList
            extends AbstractList<CTEmpty> {
                FootnoteRefList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getFootnoteRefArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getFootnoteRefArray(n);
                    CTRImpl.this.setFootnoteRefArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewFootnoteRef(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getFootnoteRefArray(n);
                    CTRImpl.this.removeFootnoteRef(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfFootnoteRefArray();
                }
            }
            return new FootnoteRefList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getFootnoteRefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FOOTNOTEREF$30, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getFootnoteRefArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(FOOTNOTEREF$30, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFootnoteRefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FOOTNOTEREF$30);
        }
    }

    @Override
    public void setFootnoteRefArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, FOOTNOTEREF$30);
    }

    @Override
    public void setFootnoteRefArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, FOOTNOTEREF$30, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewFootnoteRef(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(FOOTNOTEREF$30, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewFootnoteRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(FOOTNOTEREF$30));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFootnoteRef(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FOOTNOTEREF$30, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getEndnoteRefList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EndnoteRefList
            extends AbstractList<CTEmpty> {
                EndnoteRefList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getEndnoteRefArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getEndnoteRefArray(n);
                    CTRImpl.this.setEndnoteRefArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewEndnoteRef(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getEndnoteRefArray(n);
                    CTRImpl.this.removeEndnoteRef(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfEndnoteRefArray();
                }
            }
            return new EndnoteRefList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getEndnoteRefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ENDNOTEREF$32, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getEndnoteRefArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(ENDNOTEREF$32, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfEndnoteRefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENDNOTEREF$32);
        }
    }

    @Override
    public void setEndnoteRefArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, ENDNOTEREF$32);
    }

    @Override
    public void setEndnoteRefArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, ENDNOTEREF$32, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewEndnoteRef(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(ENDNOTEREF$32, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewEndnoteRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(ENDNOTEREF$32));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEndnoteRef(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENDNOTEREF$32, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getSeparatorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SeparatorList
            extends AbstractList<CTEmpty> {
                SeparatorList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getSeparatorArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getSeparatorArray(n);
                    CTRImpl.this.setSeparatorArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewSeparator(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getSeparatorArray(n);
                    CTRImpl.this.removeSeparator(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfSeparatorArray();
                }
            }
            return new SeparatorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getSeparatorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SEPARATOR$34, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getSeparatorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(SEPARATOR$34, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSeparatorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SEPARATOR$34);
        }
    }

    @Override
    public void setSeparatorArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, SEPARATOR$34);
    }

    @Override
    public void setSeparatorArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, SEPARATOR$34, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewSeparator(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(SEPARATOR$34, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewSeparator() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(SEPARATOR$34));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSeparator(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SEPARATOR$34, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getContinuationSeparatorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ContinuationSeparatorList
            extends AbstractList<CTEmpty> {
                ContinuationSeparatorList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getContinuationSeparatorArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getContinuationSeparatorArray(n);
                    CTRImpl.this.setContinuationSeparatorArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewContinuationSeparator(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getContinuationSeparatorArray(n);
                    CTRImpl.this.removeContinuationSeparator(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfContinuationSeparatorArray();
                }
            }
            return new ContinuationSeparatorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getContinuationSeparatorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CONTINUATIONSEPARATOR$36, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getContinuationSeparatorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(CONTINUATIONSEPARATOR$36, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfContinuationSeparatorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONTINUATIONSEPARATOR$36);
        }
    }

    @Override
    public void setContinuationSeparatorArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, CONTINUATIONSEPARATOR$36);
    }

    @Override
    public void setContinuationSeparatorArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, CONTINUATIONSEPARATOR$36, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewContinuationSeparator(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(CONTINUATIONSEPARATOR$36, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewContinuationSeparator() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(CONTINUATIONSEPARATOR$36));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeContinuationSeparator(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONTINUATIONSEPARATOR$36, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSym> getSymList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SymList
            extends AbstractList<CTSym> {
                SymList() {
                }

                @Override
                public CTSym get(int n) {
                    return CTRImpl.this.getSymArray(n);
                }

                @Override
                public CTSym set(int n, CTSym cTSym) {
                    CTSym cTSym2 = CTRImpl.this.getSymArray(n);
                    CTRImpl.this.setSymArray(n, cTSym);
                    return cTSym2;
                }

                @Override
                public void add(int n, CTSym cTSym) {
                    CTRImpl.this.insertNewSym(n).set(cTSym);
                }

                @Override
                public CTSym remove(int n) {
                    CTSym cTSym = CTRImpl.this.getSymArray(n);
                    CTRImpl.this.removeSym(n);
                    return cTSym;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfSymArray();
                }
            }
            return new SymList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSym[] getSymArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SYM$38, arrayList);
            CTSym[] cTSymArray = new CTSym[arrayList.size()];
            arrayList.toArray(cTSymArray);
            return cTSymArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSym getSymArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSym cTSym = null;
            cTSym = (CTSym)((Object)this.get_store().find_element_user(SYM$38, n));
            if (cTSym == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSym;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSymArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SYM$38);
        }
    }

    @Override
    public void setSymArray(CTSym[] cTSymArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSymArray, SYM$38);
    }

    @Override
    public void setSymArray(int n, CTSym cTSym) {
        this.generatedSetterHelperImpl(cTSym, SYM$38, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSym insertNewSym(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSym cTSym = null;
            cTSym = (CTSym)((Object)this.get_store().insert_element_user(SYM$38, n));
            return cTSym;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSym addNewSym() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSym cTSym = null;
            cTSym = (CTSym)((Object)this.get_store().add_element_user(SYM$38));
            return cTSym;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSym(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SYM$38, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getPgNumList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PgNumList
            extends AbstractList<CTEmpty> {
                PgNumList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getPgNumArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getPgNumArray(n);
                    CTRImpl.this.setPgNumArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewPgNum(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getPgNumArray(n);
                    CTRImpl.this.removePgNum(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfPgNumArray();
                }
            }
            return new PgNumList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getPgNumArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PGNUM$40, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getPgNumArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(PGNUM$40, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPgNumArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PGNUM$40);
        }
    }

    @Override
    public void setPgNumArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, PGNUM$40);
    }

    @Override
    public void setPgNumArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, PGNUM$40, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewPgNum(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(PGNUM$40, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewPgNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(PGNUM$40));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePgNum(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PGNUM$40, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getCrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CrList
            extends AbstractList<CTEmpty> {
                CrList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getCrArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getCrArray(n);
                    CTRImpl.this.setCrArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewCr(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getCrArray(n);
                    CTRImpl.this.removeCr(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfCrArray();
                }
            }
            return new CrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getCrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CR$42, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getCrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(CR$42, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CR$42);
        }
    }

    @Override
    public void setCrArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, CR$42);
    }

    @Override
    public void setCrArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, CR$42, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewCr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(CR$42, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewCr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(CR$42));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CR$42, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getTabList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TabList
            extends AbstractList<CTEmpty> {
                TabList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getTabArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getTabArray(n);
                    CTRImpl.this.setTabArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewTab(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getTabArray(n);
                    CTRImpl.this.removeTab(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfTabArray();
                }
            }
            return new TabList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getTabArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TAB$44, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getTabArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(TAB$44, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTabArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TAB$44);
        }
    }

    @Override
    public void setTabArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, TAB$44);
    }

    @Override
    public void setTabArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, TAB$44, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewTab(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(TAB$44, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewTab() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(TAB$44));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTab(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TAB$44, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTObject> getObjectList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ObjectList
            extends AbstractList<CTObject> {
                ObjectList() {
                }

                @Override
                public CTObject get(int n) {
                    return CTRImpl.this.getObjectArray(n);
                }

                @Override
                public CTObject set(int n, CTObject cTObject) {
                    CTObject cTObject2 = CTRImpl.this.getObjectArray(n);
                    CTRImpl.this.setObjectArray(n, cTObject);
                    return cTObject2;
                }

                @Override
                public void add(int n, CTObject cTObject) {
                    CTRImpl.this.insertNewObject(n).set(cTObject);
                }

                @Override
                public CTObject remove(int n) {
                    CTObject cTObject = CTRImpl.this.getObjectArray(n);
                    CTRImpl.this.removeObject(n);
                    return cTObject;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfObjectArray();
                }
            }
            return new ObjectList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTObject[] getObjectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OBJECT$46, arrayList);
            CTObject[] cTObjectArray = new CTObject[arrayList.size()];
            arrayList.toArray(cTObjectArray);
            return cTObjectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTObject getObjectArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTObject cTObject = null;
            cTObject = (CTObject)((Object)this.get_store().find_element_user(OBJECT$46, n));
            if (cTObject == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfObjectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OBJECT$46);
        }
    }

    @Override
    public void setObjectArray(CTObject[] cTObjectArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTObjectArray, OBJECT$46);
    }

    @Override
    public void setObjectArray(int n, CTObject cTObject) {
        this.generatedSetterHelperImpl(cTObject, OBJECT$46, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTObject insertNewObject(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTObject cTObject = null;
            cTObject = (CTObject)((Object)this.get_store().insert_element_user(OBJECT$46, n));
            return cTObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTObject addNewObject() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTObject cTObject = null;
            cTObject = (CTObject)((Object)this.get_store().add_element_user(OBJECT$46));
            return cTObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeObject(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OBJECT$46, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPicture> getPictList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PictList
            extends AbstractList<CTPicture> {
                PictList() {
                }

                @Override
                public CTPicture get(int n) {
                    return CTRImpl.this.getPictArray(n);
                }

                @Override
                public CTPicture set(int n, CTPicture cTPicture) {
                    CTPicture cTPicture2 = CTRImpl.this.getPictArray(n);
                    CTRImpl.this.setPictArray(n, cTPicture);
                    return cTPicture2;
                }

                @Override
                public void add(int n, CTPicture cTPicture) {
                    CTRImpl.this.insertNewPict(n).set(cTPicture);
                }

                @Override
                public CTPicture remove(int n) {
                    CTPicture cTPicture = CTRImpl.this.getPictArray(n);
                    CTRImpl.this.removePict(n);
                    return cTPicture;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfPictArray();
                }
            }
            return new PictList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPicture[] getPictArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PICT$48, arrayList);
            CTPicture[] cTPictureArray = new CTPicture[arrayList.size()];
            arrayList.toArray(cTPictureArray);
            return cTPictureArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPicture getPictArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPicture cTPicture = null;
            cTPicture = (CTPicture)((Object)this.get_store().find_element_user(PICT$48, n));
            if (cTPicture == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPicture;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPictArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PICT$48);
        }
    }

    @Override
    public void setPictArray(CTPicture[] cTPictureArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPictureArray, PICT$48);
    }

    @Override
    public void setPictArray(int n, CTPicture cTPicture) {
        this.generatedSetterHelperImpl(cTPicture, PICT$48, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPicture insertNewPict(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPicture cTPicture = null;
            cTPicture = (CTPicture)((Object)this.get_store().insert_element_user(PICT$48, n));
            return cTPicture;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPicture addNewPict() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPicture cTPicture = null;
            cTPicture = (CTPicture)((Object)this.get_store().add_element_user(PICT$48));
            return cTPicture;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePict(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PICT$48, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFldChar> getFldCharList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FldCharList
            extends AbstractList<CTFldChar> {
                FldCharList() {
                }

                @Override
                public CTFldChar get(int n) {
                    return CTRImpl.this.getFldCharArray(n);
                }

                @Override
                public CTFldChar set(int n, CTFldChar cTFldChar) {
                    CTFldChar cTFldChar2 = CTRImpl.this.getFldCharArray(n);
                    CTRImpl.this.setFldCharArray(n, cTFldChar);
                    return cTFldChar2;
                }

                @Override
                public void add(int n, CTFldChar cTFldChar) {
                    CTRImpl.this.insertNewFldChar(n).set(cTFldChar);
                }

                @Override
                public CTFldChar remove(int n) {
                    CTFldChar cTFldChar = CTRImpl.this.getFldCharArray(n);
                    CTRImpl.this.removeFldChar(n);
                    return cTFldChar;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfFldCharArray();
                }
            }
            return new FldCharList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFldChar[] getFldCharArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FLDCHAR$50, arrayList);
            CTFldChar[] cTFldCharArray = new CTFldChar[arrayList.size()];
            arrayList.toArray(cTFldCharArray);
            return cTFldCharArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFldChar getFldCharArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFldChar cTFldChar = null;
            cTFldChar = (CTFldChar)((Object)this.get_store().find_element_user(FLDCHAR$50, n));
            if (cTFldChar == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFldChar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFldCharArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FLDCHAR$50);
        }
    }

    @Override
    public void setFldCharArray(CTFldChar[] cTFldCharArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFldCharArray, FLDCHAR$50);
    }

    @Override
    public void setFldCharArray(int n, CTFldChar cTFldChar) {
        this.generatedSetterHelperImpl(cTFldChar, FLDCHAR$50, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFldChar insertNewFldChar(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFldChar cTFldChar = null;
            cTFldChar = (CTFldChar)((Object)this.get_store().insert_element_user(FLDCHAR$50, n));
            return cTFldChar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFldChar addNewFldChar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFldChar cTFldChar = null;
            cTFldChar = (CTFldChar)((Object)this.get_store().add_element_user(FLDCHAR$50));
            return cTFldChar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFldChar(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FLDCHAR$50, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRuby> getRubyList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RubyList
            extends AbstractList<CTRuby> {
                RubyList() {
                }

                @Override
                public CTRuby get(int n) {
                    return CTRImpl.this.getRubyArray(n);
                }

                @Override
                public CTRuby set(int n, CTRuby cTRuby) {
                    CTRuby cTRuby2 = CTRImpl.this.getRubyArray(n);
                    CTRImpl.this.setRubyArray(n, cTRuby);
                    return cTRuby2;
                }

                @Override
                public void add(int n, CTRuby cTRuby) {
                    CTRImpl.this.insertNewRuby(n).set(cTRuby);
                }

                @Override
                public CTRuby remove(int n) {
                    CTRuby cTRuby = CTRImpl.this.getRubyArray(n);
                    CTRImpl.this.removeRuby(n);
                    return cTRuby;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfRubyArray();
                }
            }
            return new RubyList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRuby[] getRubyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RUBY$52, arrayList);
            CTRuby[] cTRubyArray = new CTRuby[arrayList.size()];
            arrayList.toArray(cTRubyArray);
            return cTRubyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRuby getRubyArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRuby cTRuby = null;
            cTRuby = (CTRuby)((Object)this.get_store().find_element_user(RUBY$52, n));
            if (cTRuby == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRuby;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRubyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RUBY$52);
        }
    }

    @Override
    public void setRubyArray(CTRuby[] cTRubyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRubyArray, RUBY$52);
    }

    @Override
    public void setRubyArray(int n, CTRuby cTRuby) {
        this.generatedSetterHelperImpl(cTRuby, RUBY$52, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRuby insertNewRuby(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRuby cTRuby = null;
            cTRuby = (CTRuby)((Object)this.get_store().insert_element_user(RUBY$52, n));
            return cTRuby;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRuby addNewRuby() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRuby cTRuby = null;
            cTRuby = (CTRuby)((Object)this.get_store().add_element_user(RUBY$52));
            return cTRuby;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRuby(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RUBY$52, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFtnEdnRef> getFootnoteReferenceList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FootnoteReferenceList
            extends AbstractList<CTFtnEdnRef> {
                FootnoteReferenceList() {
                }

                @Override
                public CTFtnEdnRef get(int n) {
                    return CTRImpl.this.getFootnoteReferenceArray(n);
                }

                @Override
                public CTFtnEdnRef set(int n, CTFtnEdnRef cTFtnEdnRef) {
                    CTFtnEdnRef cTFtnEdnRef2 = CTRImpl.this.getFootnoteReferenceArray(n);
                    CTRImpl.this.setFootnoteReferenceArray(n, cTFtnEdnRef);
                    return cTFtnEdnRef2;
                }

                @Override
                public void add(int n, CTFtnEdnRef cTFtnEdnRef) {
                    CTRImpl.this.insertNewFootnoteReference(n).set(cTFtnEdnRef);
                }

                @Override
                public CTFtnEdnRef remove(int n) {
                    CTFtnEdnRef cTFtnEdnRef = CTRImpl.this.getFootnoteReferenceArray(n);
                    CTRImpl.this.removeFootnoteReference(n);
                    return cTFtnEdnRef;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfFootnoteReferenceArray();
                }
            }
            return new FootnoteReferenceList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFtnEdnRef[] getFootnoteReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FOOTNOTEREFERENCE$54, arrayList);
            CTFtnEdnRef[] cTFtnEdnRefArray = new CTFtnEdnRef[arrayList.size()];
            arrayList.toArray(cTFtnEdnRefArray);
            return cTFtnEdnRefArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdnRef getFootnoteReferenceArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdnRef cTFtnEdnRef = null;
            cTFtnEdnRef = (CTFtnEdnRef)((Object)this.get_store().find_element_user(FOOTNOTEREFERENCE$54, n));
            if (cTFtnEdnRef == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFtnEdnRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFootnoteReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FOOTNOTEREFERENCE$54);
        }
    }

    @Override
    public void setFootnoteReferenceArray(CTFtnEdnRef[] cTFtnEdnRefArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFtnEdnRefArray, FOOTNOTEREFERENCE$54);
    }

    @Override
    public void setFootnoteReferenceArray(int n, CTFtnEdnRef cTFtnEdnRef) {
        this.generatedSetterHelperImpl(cTFtnEdnRef, FOOTNOTEREFERENCE$54, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdnRef insertNewFootnoteReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdnRef cTFtnEdnRef = null;
            cTFtnEdnRef = (CTFtnEdnRef)((Object)this.get_store().insert_element_user(FOOTNOTEREFERENCE$54, n));
            return cTFtnEdnRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdnRef addNewFootnoteReference() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdnRef cTFtnEdnRef = null;
            cTFtnEdnRef = (CTFtnEdnRef)((Object)this.get_store().add_element_user(FOOTNOTEREFERENCE$54));
            return cTFtnEdnRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFootnoteReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FOOTNOTEREFERENCE$54, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFtnEdnRef> getEndnoteReferenceList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EndnoteReferenceList
            extends AbstractList<CTFtnEdnRef> {
                EndnoteReferenceList() {
                }

                @Override
                public CTFtnEdnRef get(int n) {
                    return CTRImpl.this.getEndnoteReferenceArray(n);
                }

                @Override
                public CTFtnEdnRef set(int n, CTFtnEdnRef cTFtnEdnRef) {
                    CTFtnEdnRef cTFtnEdnRef2 = CTRImpl.this.getEndnoteReferenceArray(n);
                    CTRImpl.this.setEndnoteReferenceArray(n, cTFtnEdnRef);
                    return cTFtnEdnRef2;
                }

                @Override
                public void add(int n, CTFtnEdnRef cTFtnEdnRef) {
                    CTRImpl.this.insertNewEndnoteReference(n).set(cTFtnEdnRef);
                }

                @Override
                public CTFtnEdnRef remove(int n) {
                    CTFtnEdnRef cTFtnEdnRef = CTRImpl.this.getEndnoteReferenceArray(n);
                    CTRImpl.this.removeEndnoteReference(n);
                    return cTFtnEdnRef;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfEndnoteReferenceArray();
                }
            }
            return new EndnoteReferenceList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFtnEdnRef[] getEndnoteReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ENDNOTEREFERENCE$56, arrayList);
            CTFtnEdnRef[] cTFtnEdnRefArray = new CTFtnEdnRef[arrayList.size()];
            arrayList.toArray(cTFtnEdnRefArray);
            return cTFtnEdnRefArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdnRef getEndnoteReferenceArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdnRef cTFtnEdnRef = null;
            cTFtnEdnRef = (CTFtnEdnRef)((Object)this.get_store().find_element_user(ENDNOTEREFERENCE$56, n));
            if (cTFtnEdnRef == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFtnEdnRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfEndnoteReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENDNOTEREFERENCE$56);
        }
    }

    @Override
    public void setEndnoteReferenceArray(CTFtnEdnRef[] cTFtnEdnRefArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFtnEdnRefArray, ENDNOTEREFERENCE$56);
    }

    @Override
    public void setEndnoteReferenceArray(int n, CTFtnEdnRef cTFtnEdnRef) {
        this.generatedSetterHelperImpl(cTFtnEdnRef, ENDNOTEREFERENCE$56, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdnRef insertNewEndnoteReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdnRef cTFtnEdnRef = null;
            cTFtnEdnRef = (CTFtnEdnRef)((Object)this.get_store().insert_element_user(ENDNOTEREFERENCE$56, n));
            return cTFtnEdnRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdnRef addNewEndnoteReference() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdnRef cTFtnEdnRef = null;
            cTFtnEdnRef = (CTFtnEdnRef)((Object)this.get_store().add_element_user(ENDNOTEREFERENCE$56));
            return cTFtnEdnRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEndnoteReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENDNOTEREFERENCE$56, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMarkup> getCommentReferenceList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CommentReferenceList
            extends AbstractList<CTMarkup> {
                CommentReferenceList() {
                }

                @Override
                public CTMarkup get(int n) {
                    return CTRImpl.this.getCommentReferenceArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRImpl.this.getCommentReferenceArray(n);
                    CTRImpl.this.setCommentReferenceArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRImpl.this.insertNewCommentReference(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRImpl.this.getCommentReferenceArray(n);
                    CTRImpl.this.removeCommentReference(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfCommentReferenceArray();
                }
            }
            return new CommentReferenceList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMarkup[] getCommentReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COMMENTREFERENCE$58, arrayList);
            CTMarkup[] cTMarkupArray = new CTMarkup[arrayList.size()];
            arrayList.toArray(cTMarkupArray);
            return cTMarkupArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup getCommentReferenceArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(COMMENTREFERENCE$58, n));
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
    public int sizeOfCommentReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMMENTREFERENCE$58);
        }
    }

    @Override
    public void setCommentReferenceArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, COMMENTREFERENCE$58);
    }

    @Override
    public void setCommentReferenceArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, COMMENTREFERENCE$58, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup insertNewCommentReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(COMMENTREFERENCE$58, n));
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarkup addNewCommentReference() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarkup cTMarkup = null;
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(COMMENTREFERENCE$58));
            return cTMarkup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCommentReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMMENTREFERENCE$58, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDrawing> getDrawingList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DrawingList
            extends AbstractList<CTDrawing> {
                DrawingList() {
                }

                @Override
                public CTDrawing get(int n) {
                    return CTRImpl.this.getDrawingArray(n);
                }

                @Override
                public CTDrawing set(int n, CTDrawing cTDrawing) {
                    CTDrawing cTDrawing2 = CTRImpl.this.getDrawingArray(n);
                    CTRImpl.this.setDrawingArray(n, cTDrawing);
                    return cTDrawing2;
                }

                @Override
                public void add(int n, CTDrawing cTDrawing) {
                    CTRImpl.this.insertNewDrawing(n).set(cTDrawing);
                }

                @Override
                public CTDrawing remove(int n) {
                    CTDrawing cTDrawing = CTRImpl.this.getDrawingArray(n);
                    CTRImpl.this.removeDrawing(n);
                    return cTDrawing;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfDrawingArray();
                }
            }
            return new DrawingList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDrawing[] getDrawingArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DRAWING$60, arrayList);
            CTDrawing[] cTDrawingArray = new CTDrawing[arrayList.size()];
            arrayList.toArray(cTDrawingArray);
            return cTDrawingArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDrawing getDrawingArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDrawing cTDrawing = null;
            cTDrawing = (CTDrawing)((Object)this.get_store().find_element_user(DRAWING$60, n));
            if (cTDrawing == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDrawing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDrawingArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DRAWING$60);
        }
    }

    @Override
    public void setDrawingArray(CTDrawing[] cTDrawingArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDrawingArray, DRAWING$60);
    }

    @Override
    public void setDrawingArray(int n, CTDrawing cTDrawing) {
        this.generatedSetterHelperImpl(cTDrawing, DRAWING$60, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDrawing insertNewDrawing(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDrawing cTDrawing = null;
            cTDrawing = (CTDrawing)((Object)this.get_store().insert_element_user(DRAWING$60, n));
            return cTDrawing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDrawing addNewDrawing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDrawing cTDrawing = null;
            cTDrawing = (CTDrawing)((Object)this.get_store().add_element_user(DRAWING$60));
            return cTDrawing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDrawing(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DRAWING$60, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPTab> getPtabList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PtabList
            extends AbstractList<CTPTab> {
                PtabList() {
                }

                @Override
                public CTPTab get(int n) {
                    return CTRImpl.this.getPtabArray(n);
                }

                @Override
                public CTPTab set(int n, CTPTab cTPTab) {
                    CTPTab cTPTab2 = CTRImpl.this.getPtabArray(n);
                    CTRImpl.this.setPtabArray(n, cTPTab);
                    return cTPTab2;
                }

                @Override
                public void add(int n, CTPTab cTPTab) {
                    CTRImpl.this.insertNewPtab(n).set(cTPTab);
                }

                @Override
                public CTPTab remove(int n) {
                    CTPTab cTPTab = CTRImpl.this.getPtabArray(n);
                    CTRImpl.this.removePtab(n);
                    return cTPTab;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfPtabArray();
                }
            }
            return new PtabList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPTab[] getPtabArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PTAB$62, arrayList);
            CTPTab[] cTPTabArray = new CTPTab[arrayList.size()];
            arrayList.toArray(cTPTabArray);
            return cTPTabArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPTab getPtabArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPTab cTPTab = null;
            cTPTab = (CTPTab)((Object)this.get_store().find_element_user(PTAB$62, n));
            if (cTPTab == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPTab;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPtabArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PTAB$62);
        }
    }

    @Override
    public void setPtabArray(CTPTab[] cTPTabArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPTabArray, PTAB$62);
    }

    @Override
    public void setPtabArray(int n, CTPTab cTPTab) {
        this.generatedSetterHelperImpl(cTPTab, PTAB$62, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPTab insertNewPtab(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPTab cTPTab = null;
            cTPTab = (CTPTab)((Object)this.get_store().insert_element_user(PTAB$62, n));
            return cTPTab;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPTab addNewPtab() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPTab cTPTab = null;
            cTPTab = (CTPTab)((Object)this.get_store().add_element_user(PTAB$62));
            return cTPTab;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePtab(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PTAB$62, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getLastRenderedPageBreakList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LastRenderedPageBreakList
            extends AbstractList<CTEmpty> {
                LastRenderedPageBreakList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTRImpl.this.getLastRenderedPageBreakArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTRImpl.this.getLastRenderedPageBreakArray(n);
                    CTRImpl.this.setLastRenderedPageBreakArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTRImpl.this.insertNewLastRenderedPageBreak(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTRImpl.this.getLastRenderedPageBreakArray(n);
                    CTRImpl.this.removeLastRenderedPageBreak(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTRImpl.this.sizeOfLastRenderedPageBreakArray();
                }
            }
            return new LastRenderedPageBreakList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getLastRenderedPageBreakArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LASTRENDEREDPAGEBREAK$64, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getLastRenderedPageBreakArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(LASTRENDEREDPAGEBREAK$64, n));
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLastRenderedPageBreakArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LASTRENDEREDPAGEBREAK$64);
        }
    }

    @Override
    public void setLastRenderedPageBreakArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, LASTRENDEREDPAGEBREAK$64);
    }

    @Override
    public void setLastRenderedPageBreakArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, LASTRENDEREDPAGEBREAK$64, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewLastRenderedPageBreak(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(LASTRENDEREDPAGEBREAK$64, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewLastRenderedPageBreak() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(LASTRENDEREDPAGEBREAK$64));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLastRenderedPageBreak(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LASTRENDEREDPAGEBREAK$64, n);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDRPR$66));
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
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDRPR$66));
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
            return this.get_store().find_attribute_user(RSIDRPR$66) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDRPR$66));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDRPR$66));
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
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDRPR$66));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDRPR$66));
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
            this.get_store().remove_attribute(RSIDRPR$66);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDDEL$68));
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
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDDEL$68));
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
            return this.get_store().find_attribute_user(RSIDDEL$68) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDDEL$68));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDDEL$68));
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
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDDEL$68));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDDEL$68));
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
            this.get_store().remove_attribute(RSIDDEL$68);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDR$70));
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
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDR$70));
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
            return this.get_store().find_attribute_user(RSIDR$70) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDR$70));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDR$70));
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
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDR$70));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDR$70));
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
            this.get_store().remove_attribute(RSIDR$70);
        }
    }
}

