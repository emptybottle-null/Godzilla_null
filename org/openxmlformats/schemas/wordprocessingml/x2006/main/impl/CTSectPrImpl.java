/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocGrid
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnProps
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLineNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPrChange
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectType
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLineNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

public class CTSectPrImpl
extends XmlComplexContentImpl
implements CTSectPr {
    private static final long serialVersionUID = 1L;
    private static final QName HEADERREFERENCE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "headerReference");
    private static final QName FOOTERREFERENCE$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footerReference");
    private static final QName FOOTNOTEPR$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnotePr");
    private static final QName ENDNOTEPR$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "endnotePr");
    private static final QName TYPE$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "type");
    private static final QName PGSZ$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pgSz");
    private static final QName PGMAR$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pgMar");
    private static final QName PAPERSRC$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "paperSrc");
    private static final QName PGBORDERS$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pgBorders");
    private static final QName LNNUMTYPE$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lnNumType");
    private static final QName PGNUMTYPE$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pgNumType");
    private static final QName COLS$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cols");
    private static final QName FORMPROT$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "formProt");
    private static final QName VALIGN$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "vAlign");
    private static final QName NOENDNOTE$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noEndnote");
    private static final QName TITLEPG$30 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "titlePg");
    private static final QName TEXTDIRECTION$32 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textDirection");
    private static final QName BIDI$34 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bidi");
    private static final QName RTLGUTTER$36 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rtlGutter");
    private static final QName DOCGRID$38 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docGrid");
    private static final QName PRINTERSETTINGS$40 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "printerSettings");
    private static final QName SECTPRCHANGE$42 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sectPrChange");
    private static final QName RSIDRPR$44 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidRPr");
    private static final QName RSIDDEL$46 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidDel");
    private static final QName RSIDR$48 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidR");
    private static final QName RSIDSECT$50 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidSect");

    public CTSectPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTHdrFtrRef> getHeaderReferenceList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HeaderReferenceList
            extends AbstractList<CTHdrFtrRef> {
                HeaderReferenceList() {
                }

                @Override
                public CTHdrFtrRef get(int n) {
                    return CTSectPrImpl.this.getHeaderReferenceArray(n);
                }

                @Override
                public CTHdrFtrRef set(int n, CTHdrFtrRef cTHdrFtrRef) {
                    CTHdrFtrRef cTHdrFtrRef2 = CTSectPrImpl.this.getHeaderReferenceArray(n);
                    CTSectPrImpl.this.setHeaderReferenceArray(n, cTHdrFtrRef);
                    return cTHdrFtrRef2;
                }

                @Override
                public void add(int n, CTHdrFtrRef cTHdrFtrRef) {
                    CTSectPrImpl.this.insertNewHeaderReference(n).set(cTHdrFtrRef);
                }

                @Override
                public CTHdrFtrRef remove(int n) {
                    CTHdrFtrRef cTHdrFtrRef = CTSectPrImpl.this.getHeaderReferenceArray(n);
                    CTSectPrImpl.this.removeHeaderReference(n);
                    return cTHdrFtrRef;
                }

                @Override
                public int size() {
                    return CTSectPrImpl.this.sizeOfHeaderReferenceArray();
                }
            }
            return new HeaderReferenceList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTHdrFtrRef[] getHeaderReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HEADERREFERENCE$0, arrayList);
            CTHdrFtrRef[] cTHdrFtrRefArray = new CTHdrFtrRef[arrayList.size()];
            arrayList.toArray(cTHdrFtrRefArray);
            return cTHdrFtrRefArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHdrFtrRef getHeaderReferenceArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHdrFtrRef cTHdrFtrRef = null;
            cTHdrFtrRef = (CTHdrFtrRef)((Object)this.get_store().find_element_user(HEADERREFERENCE$0, n));
            if (cTHdrFtrRef == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTHdrFtrRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHeaderReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HEADERREFERENCE$0);
        }
    }

    @Override
    public void setHeaderReferenceArray(CTHdrFtrRef[] cTHdrFtrRefArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTHdrFtrRefArray, HEADERREFERENCE$0);
    }

    @Override
    public void setHeaderReferenceArray(int n, CTHdrFtrRef cTHdrFtrRef) {
        this.generatedSetterHelperImpl(cTHdrFtrRef, HEADERREFERENCE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHdrFtrRef insertNewHeaderReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHdrFtrRef cTHdrFtrRef = null;
            cTHdrFtrRef = (CTHdrFtrRef)((Object)this.get_store().insert_element_user(HEADERREFERENCE$0, n));
            return cTHdrFtrRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHdrFtrRef addNewHeaderReference() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHdrFtrRef cTHdrFtrRef = null;
            cTHdrFtrRef = (CTHdrFtrRef)((Object)this.get_store().add_element_user(HEADERREFERENCE$0));
            return cTHdrFtrRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHeaderReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HEADERREFERENCE$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTHdrFtrRef> getFooterReferenceList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FooterReferenceList
            extends AbstractList<CTHdrFtrRef> {
                FooterReferenceList() {
                }

                @Override
                public CTHdrFtrRef get(int n) {
                    return CTSectPrImpl.this.getFooterReferenceArray(n);
                }

                @Override
                public CTHdrFtrRef set(int n, CTHdrFtrRef cTHdrFtrRef) {
                    CTHdrFtrRef cTHdrFtrRef2 = CTSectPrImpl.this.getFooterReferenceArray(n);
                    CTSectPrImpl.this.setFooterReferenceArray(n, cTHdrFtrRef);
                    return cTHdrFtrRef2;
                }

                @Override
                public void add(int n, CTHdrFtrRef cTHdrFtrRef) {
                    CTSectPrImpl.this.insertNewFooterReference(n).set(cTHdrFtrRef);
                }

                @Override
                public CTHdrFtrRef remove(int n) {
                    CTHdrFtrRef cTHdrFtrRef = CTSectPrImpl.this.getFooterReferenceArray(n);
                    CTSectPrImpl.this.removeFooterReference(n);
                    return cTHdrFtrRef;
                }

                @Override
                public int size() {
                    return CTSectPrImpl.this.sizeOfFooterReferenceArray();
                }
            }
            return new FooterReferenceList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTHdrFtrRef[] getFooterReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FOOTERREFERENCE$2, arrayList);
            CTHdrFtrRef[] cTHdrFtrRefArray = new CTHdrFtrRef[arrayList.size()];
            arrayList.toArray(cTHdrFtrRefArray);
            return cTHdrFtrRefArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHdrFtrRef getFooterReferenceArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHdrFtrRef cTHdrFtrRef = null;
            cTHdrFtrRef = (CTHdrFtrRef)((Object)this.get_store().find_element_user(FOOTERREFERENCE$2, n));
            if (cTHdrFtrRef == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTHdrFtrRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFooterReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FOOTERREFERENCE$2);
        }
    }

    @Override
    public void setFooterReferenceArray(CTHdrFtrRef[] cTHdrFtrRefArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTHdrFtrRefArray, FOOTERREFERENCE$2);
    }

    @Override
    public void setFooterReferenceArray(int n, CTHdrFtrRef cTHdrFtrRef) {
        this.generatedSetterHelperImpl(cTHdrFtrRef, FOOTERREFERENCE$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHdrFtrRef insertNewFooterReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHdrFtrRef cTHdrFtrRef = null;
            cTHdrFtrRef = (CTHdrFtrRef)((Object)this.get_store().insert_element_user(FOOTERREFERENCE$2, n));
            return cTHdrFtrRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHdrFtrRef addNewFooterReference() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHdrFtrRef cTHdrFtrRef = null;
            cTHdrFtrRef = (CTHdrFtrRef)((Object)this.get_store().add_element_user(FOOTERREFERENCE$2));
            return cTHdrFtrRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFooterReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FOOTERREFERENCE$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnProps getFootnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnProps cTFtnProps = null;
            cTFtnProps = (CTFtnProps)this.get_store().find_element_user(FOOTNOTEPR$4, 0);
            if (cTFtnProps == null) {
                return null;
            }
            return cTFtnProps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFootnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FOOTNOTEPR$4) != 0;
        }
    }

    @Override
    public void setFootnotePr(CTFtnProps cTFtnProps) {
        this.generatedSetterHelperImpl((XmlObject)cTFtnProps, FOOTNOTEPR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnProps addNewFootnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnProps cTFtnProps = null;
            cTFtnProps = (CTFtnProps)this.get_store().add_element_user(FOOTNOTEPR$4);
            return cTFtnProps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFootnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FOOTNOTEPR$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEdnProps getEndnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEdnProps cTEdnProps = null;
            cTEdnProps = (CTEdnProps)this.get_store().find_element_user(ENDNOTEPR$6, 0);
            if (cTEdnProps == null) {
                return null;
            }
            return cTEdnProps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEndnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENDNOTEPR$6) != 0;
        }
    }

    @Override
    public void setEndnotePr(CTEdnProps cTEdnProps) {
        this.generatedSetterHelperImpl((XmlObject)cTEdnProps, ENDNOTEPR$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEdnProps addNewEndnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEdnProps cTEdnProps = null;
            cTEdnProps = (CTEdnProps)this.get_store().add_element_user(ENDNOTEPR$6);
            return cTEdnProps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEndnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENDNOTEPR$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSectType getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSectType cTSectType = null;
            cTSectType = (CTSectType)this.get_store().find_element_user(TYPE$8, 0);
            if (cTSectType == null) {
                return null;
            }
            return cTSectType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TYPE$8) != 0;
        }
    }

    @Override
    public void setType(CTSectType cTSectType) {
        this.generatedSetterHelperImpl((XmlObject)cTSectType, TYPE$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSectType addNewType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSectType cTSectType = null;
            cTSectType = (CTSectType)this.get_store().add_element_user(TYPE$8);
            return cTSectType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TYPE$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageSz getPgSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageSz cTPageSz = null;
            cTPageSz = (CTPageSz)this.get_store().find_element_user(PGSZ$10, 0);
            if (cTPageSz == null) {
                return null;
            }
            return cTPageSz;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPgSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PGSZ$10) != 0;
        }
    }

    @Override
    public void setPgSz(CTPageSz cTPageSz) {
        this.generatedSetterHelperImpl((XmlObject)cTPageSz, PGSZ$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageSz addNewPgSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageSz cTPageSz = null;
            cTPageSz = (CTPageSz)this.get_store().add_element_user(PGSZ$10);
            return cTPageSz;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPgSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PGSZ$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageMar getPgMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageMar cTPageMar = null;
            cTPageMar = (CTPageMar)this.get_store().find_element_user(PGMAR$12, 0);
            if (cTPageMar == null) {
                return null;
            }
            return cTPageMar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPgMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PGMAR$12) != 0;
        }
    }

    @Override
    public void setPgMar(CTPageMar cTPageMar) {
        this.generatedSetterHelperImpl((XmlObject)cTPageMar, PGMAR$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageMar addNewPgMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageMar cTPageMar = null;
            cTPageMar = (CTPageMar)this.get_store().add_element_user(PGMAR$12);
            return cTPageMar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPgMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PGMAR$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPaperSource getPaperSrc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPaperSource cTPaperSource = null;
            cTPaperSource = (CTPaperSource)this.get_store().find_element_user(PAPERSRC$14, 0);
            if (cTPaperSource == null) {
                return null;
            }
            return cTPaperSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPaperSrc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PAPERSRC$14) != 0;
        }
    }

    @Override
    public void setPaperSrc(CTPaperSource cTPaperSource) {
        this.generatedSetterHelperImpl((XmlObject)cTPaperSource, PAPERSRC$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPaperSource addNewPaperSrc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPaperSource cTPaperSource = null;
            cTPaperSource = (CTPaperSource)this.get_store().add_element_user(PAPERSRC$14);
            return cTPaperSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPaperSrc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PAPERSRC$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageBorders getPgBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageBorders cTPageBorders = null;
            cTPageBorders = (CTPageBorders)this.get_store().find_element_user(PGBORDERS$16, 0);
            if (cTPageBorders == null) {
                return null;
            }
            return cTPageBorders;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPgBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PGBORDERS$16) != 0;
        }
    }

    @Override
    public void setPgBorders(CTPageBorders cTPageBorders) {
        this.generatedSetterHelperImpl((XmlObject)cTPageBorders, PGBORDERS$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageBorders addNewPgBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageBorders cTPageBorders = null;
            cTPageBorders = (CTPageBorders)this.get_store().add_element_user(PGBORDERS$16);
            return cTPageBorders;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPgBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PGBORDERS$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineNumber getLnNumType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineNumber cTLineNumber = null;
            cTLineNumber = (CTLineNumber)this.get_store().find_element_user(LNNUMTYPE$18, 0);
            if (cTLineNumber == null) {
                return null;
            }
            return cTLineNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLnNumType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LNNUMTYPE$18) != 0;
        }
    }

    @Override
    public void setLnNumType(CTLineNumber cTLineNumber) {
        this.generatedSetterHelperImpl((XmlObject)cTLineNumber, LNNUMTYPE$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineNumber addNewLnNumType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineNumber cTLineNumber = null;
            cTLineNumber = (CTLineNumber)this.get_store().add_element_user(LNNUMTYPE$18);
            return cTLineNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLnNumType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LNNUMTYPE$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageNumber getPgNumType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageNumber cTPageNumber = null;
            cTPageNumber = (CTPageNumber)this.get_store().find_element_user(PGNUMTYPE$20, 0);
            if (cTPageNumber == null) {
                return null;
            }
            return cTPageNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPgNumType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PGNUMTYPE$20) != 0;
        }
    }

    @Override
    public void setPgNumType(CTPageNumber cTPageNumber) {
        this.generatedSetterHelperImpl((XmlObject)cTPageNumber, PGNUMTYPE$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageNumber addNewPgNumType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageNumber cTPageNumber = null;
            cTPageNumber = (CTPageNumber)this.get_store().add_element_user(PGNUMTYPE$20);
            return cTPageNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPgNumType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PGNUMTYPE$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColumns getCols() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColumns cTColumns = null;
            cTColumns = (CTColumns)this.get_store().find_element_user(COLS$22, 0);
            if (cTColumns == null) {
                return null;
            }
            return cTColumns;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCols() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLS$22) != 0;
        }
    }

    @Override
    public void setCols(CTColumns cTColumns) {
        this.generatedSetterHelperImpl((XmlObject)cTColumns, COLS$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColumns addNewCols() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColumns cTColumns = null;
            cTColumns = (CTColumns)this.get_store().add_element_user(COLS$22);
            return cTColumns;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCols() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLS$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getFormProt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(FORMPROT$24, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFormProt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FORMPROT$24) != 0;
        }
    }

    @Override
    public void setFormProt(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, FORMPROT$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewFormProt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(FORMPROT$24));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFormProt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FORMPROT$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVerticalJc getVAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVerticalJc cTVerticalJc = null;
            cTVerticalJc = (CTVerticalJc)((Object)this.get_store().find_element_user(VALIGN$26, 0));
            if (cTVerticalJc == null) {
                return null;
            }
            return cTVerticalJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VALIGN$26) != 0;
        }
    }

    @Override
    public void setVAlign(CTVerticalJc cTVerticalJc) {
        this.generatedSetterHelperImpl(cTVerticalJc, VALIGN$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVerticalJc addNewVAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVerticalJc cTVerticalJc = null;
            cTVerticalJc = (CTVerticalJc)((Object)this.get_store().add_element_user(VALIGN$26));
            return cTVerticalJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VALIGN$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getNoEndnote() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(NOENDNOTE$28, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNoEndnote() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOENDNOTE$28) != 0;
        }
    }

    @Override
    public void setNoEndnote(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, NOENDNOTE$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewNoEndnote() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(NOENDNOTE$28));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNoEndnote() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOENDNOTE$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getTitlePg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(TITLEPG$30, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTitlePg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TITLEPG$30) != 0;
        }
    }

    @Override
    public void setTitlePg(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, TITLEPG$30, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewTitlePg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(TITLEPG$30));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTitlePg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TITLEPG$30, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextDirection getTextDirection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextDirection cTTextDirection = null;
            cTTextDirection = (CTTextDirection)this.get_store().find_element_user(TEXTDIRECTION$32, 0);
            if (cTTextDirection == null) {
                return null;
            }
            return cTTextDirection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTextDirection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXTDIRECTION$32) != 0;
        }
    }

    @Override
    public void setTextDirection(CTTextDirection cTTextDirection) {
        this.generatedSetterHelperImpl((XmlObject)cTTextDirection, TEXTDIRECTION$32, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextDirection addNewTextDirection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextDirection cTTextDirection = null;
            cTTextDirection = (CTTextDirection)this.get_store().add_element_user(TEXTDIRECTION$32);
            return cTTextDirection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTextDirection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXTDIRECTION$32, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getBidi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(BIDI$34, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBidi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BIDI$34) != 0;
        }
    }

    @Override
    public void setBidi(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, BIDI$34, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewBidi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(BIDI$34));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBidi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BIDI$34, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getRtlGutter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(RTLGUTTER$36, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRtlGutter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RTLGUTTER$36) != 0;
        }
    }

    @Override
    public void setRtlGutter(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, RTLGUTTER$36, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewRtlGutter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(RTLGUTTER$36));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRtlGutter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RTLGUTTER$36, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocGrid getDocGrid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocGrid cTDocGrid = null;
            cTDocGrid = (CTDocGrid)this.get_store().find_element_user(DOCGRID$38, 0);
            if (cTDocGrid == null) {
                return null;
            }
            return cTDocGrid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDocGrid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCGRID$38) != 0;
        }
    }

    @Override
    public void setDocGrid(CTDocGrid cTDocGrid) {
        this.generatedSetterHelperImpl((XmlObject)cTDocGrid, DOCGRID$38, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocGrid addNewDocGrid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocGrid cTDocGrid = null;
            cTDocGrid = (CTDocGrid)this.get_store().add_element_user(DOCGRID$38);
            return cTDocGrid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDocGrid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCGRID$38, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel getPrinterSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)((Object)this.get_store().find_element_user(PRINTERSETTINGS$40, 0));
            if (cTRel == null) {
                return null;
            }
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrinterSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRINTERSETTINGS$40) != 0;
        }
    }

    @Override
    public void setPrinterSettings(CTRel cTRel) {
        this.generatedSetterHelperImpl(cTRel, PRINTERSETTINGS$40, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel addNewPrinterSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)((Object)this.get_store().add_element_user(PRINTERSETTINGS$40));
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrinterSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRINTERSETTINGS$40, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSectPrChange getSectPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSectPrChange cTSectPrChange = null;
            cTSectPrChange = (CTSectPrChange)this.get_store().find_element_user(SECTPRCHANGE$42, 0);
            if (cTSectPrChange == null) {
                return null;
            }
            return cTSectPrChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSectPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SECTPRCHANGE$42) != 0;
        }
    }

    @Override
    public void setSectPrChange(CTSectPrChange cTSectPrChange) {
        this.generatedSetterHelperImpl((XmlObject)cTSectPrChange, SECTPRCHANGE$42, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSectPrChange addNewSectPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSectPrChange cTSectPrChange = null;
            cTSectPrChange = (CTSectPrChange)this.get_store().add_element_user(SECTPRCHANGE$42);
            return cTSectPrChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSectPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SECTPRCHANGE$42, 0);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDRPR$44));
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
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDRPR$44));
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
            return this.get_store().find_attribute_user(RSIDRPR$44) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDRPR$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDRPR$44));
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
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDRPR$44));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDRPR$44));
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
            this.get_store().remove_attribute(RSIDRPR$44);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDDEL$46));
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
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDDEL$46));
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
            return this.get_store().find_attribute_user(RSIDDEL$46) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDDEL$46));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDDEL$46));
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
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDDEL$46));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDDEL$46));
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
            this.get_store().remove_attribute(RSIDDEL$46);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDR$48));
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
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDR$48));
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
            return this.get_store().find_attribute_user(RSIDR$48) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDR$48));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDR$48));
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
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDR$48));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDR$48));
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
            this.get_store().remove_attribute(RSIDR$48);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getRsidSect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDSECT$50));
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
    public STLongHexNumber xgetRsidSect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber = null;
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDSECT$50));
            return sTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRsidSect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RSIDSECT$50) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRsidSect(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RSIDSECT$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RSIDSECT$50));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRsidSect(STLongHexNumber sTLongHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber2 = null;
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(RSIDSECT$50));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(RSIDSECT$50));
            }
            sTLongHexNumber2.set(sTLongHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRsidSect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RSIDSECT$50);
        }
    }
}

