/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara
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
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;

public class CTRubyContentImpl
extends XmlComplexContentImpl
implements CTRubyContent {
    private static final long serialVersionUID = 1L;
    private static final QName R$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "r");
    private static final QName PROOFERR$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "proofErr");
    private static final QName PERMSTART$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permStart");
    private static final QName PERMEND$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permEnd");
    private static final QName BOOKMARKSTART$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkStart");
    private static final QName BOOKMARKEND$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkEnd");
    private static final QName MOVEFROMRANGESTART$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeStart");
    private static final QName MOVEFROMRANGEEND$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeEnd");
    private static final QName MOVETORANGESTART$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeStart");
    private static final QName MOVETORANGEEND$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeEnd");
    private static final QName COMMENTRANGESTART$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeStart");
    private static final QName COMMENTRANGEEND$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeEnd");
    private static final QName CUSTOMXMLINSRANGESTART$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeStart");
    private static final QName CUSTOMXMLINSRANGEEND$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeEnd");
    private static final QName CUSTOMXMLDELRANGESTART$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeStart");
    private static final QName CUSTOMXMLDELRANGEEND$30 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeEnd");
    private static final QName CUSTOMXMLMOVEFROMRANGESTART$32 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeStart");
    private static final QName CUSTOMXMLMOVEFROMRANGEEND$34 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeEnd");
    private static final QName CUSTOMXMLMOVETORANGESTART$36 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeStart");
    private static final QName CUSTOMXMLMOVETORANGEEND$38 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeEnd");
    private static final QName INS$40 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ins");
    private static final QName DEL$42 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "del");
    private static final QName MOVEFROM$44 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFrom");
    private static final QName MOVETO$46 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveTo");
    private static final QName OMATHPARA$48 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMathPara");
    private static final QName OMATH$50 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMath");

    public CTRubyContentImpl(SchemaType schemaType) {
        super(schemaType);
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
                    return CTRubyContentImpl.this.getRArray(n);
                }

                @Override
                public CTR set(int n, CTR cTR) {
                    CTR cTR2 = CTRubyContentImpl.this.getRArray(n);
                    CTRubyContentImpl.this.setRArray(n, cTR);
                    return cTR2;
                }

                @Override
                public void add(int n, CTR cTR) {
                    CTRubyContentImpl.this.insertNewR(n).set(cTR);
                }

                @Override
                public CTR remove(int n) {
                    CTR cTR = CTRubyContentImpl.this.getRArray(n);
                    CTRubyContentImpl.this.removeR(n);
                    return cTR;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfRArray();
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
            this.get_store().find_all_element_users(R$0, arrayList);
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
            cTR = (CTR)((Object)this.get_store().find_element_user(R$0, n));
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
            return this.get_store().count_elements(R$0);
        }
    }

    @Override
    public void setRArray(CTR[] cTRArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRArray, R$0);
    }

    @Override
    public void setRArray(int n, CTR cTR) {
        this.generatedSetterHelperImpl(cTR, R$0, n, (short)2);
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
            cTR = (CTR)((Object)this.get_store().insert_element_user(R$0, n));
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
            cTR = (CTR)((Object)this.get_store().add_element_user(R$0));
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
            this.get_store().remove_element(R$0, n);
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
                    return CTRubyContentImpl.this.getProofErrArray(n);
                }

                @Override
                public CTProofErr set(int n, CTProofErr cTProofErr) {
                    CTProofErr cTProofErr2 = CTRubyContentImpl.this.getProofErrArray(n);
                    CTRubyContentImpl.this.setProofErrArray(n, cTProofErr);
                    return cTProofErr2;
                }

                @Override
                public void add(int n, CTProofErr cTProofErr) {
                    CTRubyContentImpl.this.insertNewProofErr(n).set(cTProofErr);
                }

                @Override
                public CTProofErr remove(int n) {
                    CTProofErr cTProofErr = CTRubyContentImpl.this.getProofErrArray(n);
                    CTRubyContentImpl.this.removeProofErr(n);
                    return cTProofErr;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfProofErrArray();
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
            this.get_store().find_all_element_users(PROOFERR$2, arrayList);
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
            cTProofErr = (CTProofErr)((Object)this.get_store().find_element_user(PROOFERR$2, n));
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
            return this.get_store().count_elements(PROOFERR$2);
        }
    }

    @Override
    public void setProofErrArray(CTProofErr[] cTProofErrArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTProofErrArray, PROOFERR$2);
    }

    @Override
    public void setProofErrArray(int n, CTProofErr cTProofErr) {
        this.generatedSetterHelperImpl(cTProofErr, PROOFERR$2, n, (short)2);
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
            cTProofErr = (CTProofErr)((Object)this.get_store().insert_element_user(PROOFERR$2, n));
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
            cTProofErr = (CTProofErr)((Object)this.get_store().add_element_user(PROOFERR$2));
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
            this.get_store().remove_element(PROOFERR$2, n);
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
                    return CTRubyContentImpl.this.getPermStartArray(n);
                }

                @Override
                public CTPermStart set(int n, CTPermStart cTPermStart) {
                    CTPermStart cTPermStart2 = CTRubyContentImpl.this.getPermStartArray(n);
                    CTRubyContentImpl.this.setPermStartArray(n, cTPermStart);
                    return cTPermStart2;
                }

                @Override
                public void add(int n, CTPermStart cTPermStart) {
                    CTRubyContentImpl.this.insertNewPermStart(n).set((XmlObject)cTPermStart);
                }

                @Override
                public CTPermStart remove(int n) {
                    CTPermStart cTPermStart = CTRubyContentImpl.this.getPermStartArray(n);
                    CTRubyContentImpl.this.removePermStart(n);
                    return cTPermStart;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfPermStartArray();
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
            this.get_store().find_all_element_users(PERMSTART$4, arrayList);
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
            cTPermStart = (CTPermStart)this.get_store().find_element_user(PERMSTART$4, n);
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
            return this.get_store().count_elements(PERMSTART$4);
        }
    }

    @Override
    public void setPermStartArray(CTPermStart[] cTPermStartArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPermStartArray, PERMSTART$4);
    }

    @Override
    public void setPermStartArray(int n, CTPermStart cTPermStart) {
        this.generatedSetterHelperImpl((XmlObject)cTPermStart, PERMSTART$4, n, (short)2);
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
            cTPermStart = (CTPermStart)this.get_store().insert_element_user(PERMSTART$4, n);
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
            cTPermStart = (CTPermStart)this.get_store().add_element_user(PERMSTART$4);
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
            this.get_store().remove_element(PERMSTART$4, n);
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
                    return CTRubyContentImpl.this.getPermEndArray(n);
                }

                @Override
                public CTPerm set(int n, CTPerm cTPerm) {
                    CTPerm cTPerm2 = CTRubyContentImpl.this.getPermEndArray(n);
                    CTRubyContentImpl.this.setPermEndArray(n, cTPerm);
                    return cTPerm2;
                }

                @Override
                public void add(int n, CTPerm cTPerm) {
                    CTRubyContentImpl.this.insertNewPermEnd(n).set((XmlObject)cTPerm);
                }

                @Override
                public CTPerm remove(int n) {
                    CTPerm cTPerm = CTRubyContentImpl.this.getPermEndArray(n);
                    CTRubyContentImpl.this.removePermEnd(n);
                    return cTPerm;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfPermEndArray();
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
            this.get_store().find_all_element_users(PERMEND$6, arrayList);
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
            cTPerm = (CTPerm)this.get_store().find_element_user(PERMEND$6, n);
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
            return this.get_store().count_elements(PERMEND$6);
        }
    }

    @Override
    public void setPermEndArray(CTPerm[] cTPermArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPermArray, PERMEND$6);
    }

    @Override
    public void setPermEndArray(int n, CTPerm cTPerm) {
        this.generatedSetterHelperImpl((XmlObject)cTPerm, PERMEND$6, n, (short)2);
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
            cTPerm = (CTPerm)this.get_store().insert_element_user(PERMEND$6, n);
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
            cTPerm = (CTPerm)this.get_store().add_element_user(PERMEND$6);
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
            this.get_store().remove_element(PERMEND$6, n);
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
                    return CTRubyContentImpl.this.getBookmarkStartArray(n);
                }

                @Override
                public CTBookmark set(int n, CTBookmark cTBookmark) {
                    CTBookmark cTBookmark2 = CTRubyContentImpl.this.getBookmarkStartArray(n);
                    CTRubyContentImpl.this.setBookmarkStartArray(n, cTBookmark);
                    return cTBookmark2;
                }

                @Override
                public void add(int n, CTBookmark cTBookmark) {
                    CTRubyContentImpl.this.insertNewBookmarkStart(n).set(cTBookmark);
                }

                @Override
                public CTBookmark remove(int n) {
                    CTBookmark cTBookmark = CTRubyContentImpl.this.getBookmarkStartArray(n);
                    CTRubyContentImpl.this.removeBookmarkStart(n);
                    return cTBookmark;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfBookmarkStartArray();
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
            this.get_store().find_all_element_users(BOOKMARKSTART$8, arrayList);
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
            cTBookmark = (CTBookmark)((Object)this.get_store().find_element_user(BOOKMARKSTART$8, n));
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
            return this.get_store().count_elements(BOOKMARKSTART$8);
        }
    }

    @Override
    public void setBookmarkStartArray(CTBookmark[] cTBookmarkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBookmarkArray, BOOKMARKSTART$8);
    }

    @Override
    public void setBookmarkStartArray(int n, CTBookmark cTBookmark) {
        this.generatedSetterHelperImpl(cTBookmark, BOOKMARKSTART$8, n, (short)2);
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
            cTBookmark = (CTBookmark)((Object)this.get_store().insert_element_user(BOOKMARKSTART$8, n));
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
            cTBookmark = (CTBookmark)((Object)this.get_store().add_element_user(BOOKMARKSTART$8));
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
            this.get_store().remove_element(BOOKMARKSTART$8, n);
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
                    return CTRubyContentImpl.this.getBookmarkEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRubyContentImpl.this.getBookmarkEndArray(n);
                    CTRubyContentImpl.this.setBookmarkEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRubyContentImpl.this.insertNewBookmarkEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRubyContentImpl.this.getBookmarkEndArray(n);
                    CTRubyContentImpl.this.removeBookmarkEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfBookmarkEndArray();
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
            this.get_store().find_all_element_users(BOOKMARKEND$10, arrayList);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(BOOKMARKEND$10, n));
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
            return this.get_store().count_elements(BOOKMARKEND$10);
        }
    }

    @Override
    public void setBookmarkEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, BOOKMARKEND$10);
    }

    @Override
    public void setBookmarkEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, BOOKMARKEND$10, n, (short)2);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(BOOKMARKEND$10, n));
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(BOOKMARKEND$10));
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
            this.get_store().remove_element(BOOKMARKEND$10, n);
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
                    return CTRubyContentImpl.this.getMoveFromRangeStartArray(n);
                }

                @Override
                public CTMoveBookmark set(int n, CTMoveBookmark cTMoveBookmark) {
                    CTMoveBookmark cTMoveBookmark2 = CTRubyContentImpl.this.getMoveFromRangeStartArray(n);
                    CTRubyContentImpl.this.setMoveFromRangeStartArray(n, cTMoveBookmark);
                    return cTMoveBookmark2;
                }

                @Override
                public void add(int n, CTMoveBookmark cTMoveBookmark) {
                    CTRubyContentImpl.this.insertNewMoveFromRangeStart(n).set(cTMoveBookmark);
                }

                @Override
                public CTMoveBookmark remove(int n) {
                    CTMoveBookmark cTMoveBookmark = CTRubyContentImpl.this.getMoveFromRangeStartArray(n);
                    CTRubyContentImpl.this.removeMoveFromRangeStart(n);
                    return cTMoveBookmark;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfMoveFromRangeStartArray();
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
            this.get_store().find_all_element_users(MOVEFROMRANGESTART$12, arrayList);
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().find_element_user(MOVEFROMRANGESTART$12, n));
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
            return this.get_store().count_elements(MOVEFROMRANGESTART$12);
        }
    }

    @Override
    public void setMoveFromRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMoveBookmarkArray, MOVEFROMRANGESTART$12);
    }

    @Override
    public void setMoveFromRangeStartArray(int n, CTMoveBookmark cTMoveBookmark) {
        this.generatedSetterHelperImpl(cTMoveBookmark, MOVEFROMRANGESTART$12, n, (short)2);
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().insert_element_user(MOVEFROMRANGESTART$12, n));
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().add_element_user(MOVEFROMRANGESTART$12));
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
            this.get_store().remove_element(MOVEFROMRANGESTART$12, n);
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
                    return CTRubyContentImpl.this.getMoveFromRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRubyContentImpl.this.getMoveFromRangeEndArray(n);
                    CTRubyContentImpl.this.setMoveFromRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRubyContentImpl.this.insertNewMoveFromRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRubyContentImpl.this.getMoveFromRangeEndArray(n);
                    CTRubyContentImpl.this.removeMoveFromRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfMoveFromRangeEndArray();
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
            this.get_store().find_all_element_users(MOVEFROMRANGEEND$14, arrayList);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(MOVEFROMRANGEEND$14, n));
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
            return this.get_store().count_elements(MOVEFROMRANGEEND$14);
        }
    }

    @Override
    public void setMoveFromRangeEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, MOVEFROMRANGEEND$14);
    }

    @Override
    public void setMoveFromRangeEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, MOVEFROMRANGEEND$14, n, (short)2);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(MOVEFROMRANGEEND$14, n));
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(MOVEFROMRANGEEND$14));
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
            this.get_store().remove_element(MOVEFROMRANGEEND$14, n);
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
                    return CTRubyContentImpl.this.getMoveToRangeStartArray(n);
                }

                @Override
                public CTMoveBookmark set(int n, CTMoveBookmark cTMoveBookmark) {
                    CTMoveBookmark cTMoveBookmark2 = CTRubyContentImpl.this.getMoveToRangeStartArray(n);
                    CTRubyContentImpl.this.setMoveToRangeStartArray(n, cTMoveBookmark);
                    return cTMoveBookmark2;
                }

                @Override
                public void add(int n, CTMoveBookmark cTMoveBookmark) {
                    CTRubyContentImpl.this.insertNewMoveToRangeStart(n).set(cTMoveBookmark);
                }

                @Override
                public CTMoveBookmark remove(int n) {
                    CTMoveBookmark cTMoveBookmark = CTRubyContentImpl.this.getMoveToRangeStartArray(n);
                    CTRubyContentImpl.this.removeMoveToRangeStart(n);
                    return cTMoveBookmark;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfMoveToRangeStartArray();
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
            this.get_store().find_all_element_users(MOVETORANGESTART$16, arrayList);
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().find_element_user(MOVETORANGESTART$16, n));
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
            return this.get_store().count_elements(MOVETORANGESTART$16);
        }
    }

    @Override
    public void setMoveToRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMoveBookmarkArray, MOVETORANGESTART$16);
    }

    @Override
    public void setMoveToRangeStartArray(int n, CTMoveBookmark cTMoveBookmark) {
        this.generatedSetterHelperImpl(cTMoveBookmark, MOVETORANGESTART$16, n, (short)2);
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().insert_element_user(MOVETORANGESTART$16, n));
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
            cTMoveBookmark = (CTMoveBookmark)((Object)this.get_store().add_element_user(MOVETORANGESTART$16));
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
            this.get_store().remove_element(MOVETORANGESTART$16, n);
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
                    return CTRubyContentImpl.this.getMoveToRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRubyContentImpl.this.getMoveToRangeEndArray(n);
                    CTRubyContentImpl.this.setMoveToRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRubyContentImpl.this.insertNewMoveToRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRubyContentImpl.this.getMoveToRangeEndArray(n);
                    CTRubyContentImpl.this.removeMoveToRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfMoveToRangeEndArray();
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
            this.get_store().find_all_element_users(MOVETORANGEEND$18, arrayList);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(MOVETORANGEEND$18, n));
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
            return this.get_store().count_elements(MOVETORANGEEND$18);
        }
    }

    @Override
    public void setMoveToRangeEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, MOVETORANGEEND$18);
    }

    @Override
    public void setMoveToRangeEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, MOVETORANGEEND$18, n, (short)2);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(MOVETORANGEEND$18, n));
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(MOVETORANGEEND$18));
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
            this.get_store().remove_element(MOVETORANGEEND$18, n);
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
                    return CTRubyContentImpl.this.getCommentRangeStartArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRubyContentImpl.this.getCommentRangeStartArray(n);
                    CTRubyContentImpl.this.setCommentRangeStartArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRubyContentImpl.this.insertNewCommentRangeStart(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRubyContentImpl.this.getCommentRangeStartArray(n);
                    CTRubyContentImpl.this.removeCommentRangeStart(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfCommentRangeStartArray();
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
            this.get_store().find_all_element_users(COMMENTRANGESTART$20, arrayList);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(COMMENTRANGESTART$20, n));
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
            return this.get_store().count_elements(COMMENTRANGESTART$20);
        }
    }

    @Override
    public void setCommentRangeStartArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, COMMENTRANGESTART$20);
    }

    @Override
    public void setCommentRangeStartArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, COMMENTRANGESTART$20, n, (short)2);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(COMMENTRANGESTART$20, n));
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(COMMENTRANGESTART$20));
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
            this.get_store().remove_element(COMMENTRANGESTART$20, n);
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
                    return CTRubyContentImpl.this.getCommentRangeEndArray(n);
                }

                @Override
                public CTMarkupRange set(int n, CTMarkupRange cTMarkupRange) {
                    CTMarkupRange cTMarkupRange2 = CTRubyContentImpl.this.getCommentRangeEndArray(n);
                    CTRubyContentImpl.this.setCommentRangeEndArray(n, cTMarkupRange);
                    return cTMarkupRange2;
                }

                @Override
                public void add(int n, CTMarkupRange cTMarkupRange) {
                    CTRubyContentImpl.this.insertNewCommentRangeEnd(n).set(cTMarkupRange);
                }

                @Override
                public CTMarkupRange remove(int n) {
                    CTMarkupRange cTMarkupRange = CTRubyContentImpl.this.getCommentRangeEndArray(n);
                    CTRubyContentImpl.this.removeCommentRangeEnd(n);
                    return cTMarkupRange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfCommentRangeEndArray();
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
            this.get_store().find_all_element_users(COMMENTRANGEEND$22, arrayList);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().find_element_user(COMMENTRANGEEND$22, n));
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
            return this.get_store().count_elements(COMMENTRANGEEND$22);
        }
    }

    @Override
    public void setCommentRangeEndArray(CTMarkupRange[] cTMarkupRangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupRangeArray, COMMENTRANGEEND$22);
    }

    @Override
    public void setCommentRangeEndArray(int n, CTMarkupRange cTMarkupRange) {
        this.generatedSetterHelperImpl(cTMarkupRange, COMMENTRANGEEND$22, n, (short)2);
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().insert_element_user(COMMENTRANGEEND$22, n));
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
            cTMarkupRange = (CTMarkupRange)((Object)this.get_store().add_element_user(COMMENTRANGEEND$22));
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
            this.get_store().remove_element(COMMENTRANGEEND$22, n);
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
                    return CTRubyContentImpl.this.getCustomXmlInsRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRubyContentImpl.this.getCustomXmlInsRangeStartArray(n);
                    CTRubyContentImpl.this.setCustomXmlInsRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRubyContentImpl.this.insertNewCustomXmlInsRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRubyContentImpl.this.getCustomXmlInsRangeStartArray(n);
                    CTRubyContentImpl.this.removeCustomXmlInsRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfCustomXmlInsRangeStartArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLINSRANGESTART$24, arrayList);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLINSRANGESTART$24, n));
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
            return this.get_store().count_elements(CUSTOMXMLINSRANGESTART$24);
        }
    }

    @Override
    public void setCustomXmlInsRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLINSRANGESTART$24);
    }

    @Override
    public void setCustomXmlInsRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLINSRANGESTART$24, n, (short)2);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLINSRANGESTART$24, n));
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLINSRANGESTART$24));
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
            this.get_store().remove_element(CUSTOMXMLINSRANGESTART$24, n);
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
                    return CTRubyContentImpl.this.getCustomXmlInsRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRubyContentImpl.this.getCustomXmlInsRangeEndArray(n);
                    CTRubyContentImpl.this.setCustomXmlInsRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRubyContentImpl.this.insertNewCustomXmlInsRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRubyContentImpl.this.getCustomXmlInsRangeEndArray(n);
                    CTRubyContentImpl.this.removeCustomXmlInsRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfCustomXmlInsRangeEndArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLINSRANGEEND$26, arrayList);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLINSRANGEEND$26, n));
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
            return this.get_store().count_elements(CUSTOMXMLINSRANGEEND$26);
        }
    }

    @Override
    public void setCustomXmlInsRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLINSRANGEEND$26);
    }

    @Override
    public void setCustomXmlInsRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLINSRANGEEND$26, n, (short)2);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLINSRANGEEND$26, n));
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
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLINSRANGEEND$26));
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
            this.get_store().remove_element(CUSTOMXMLINSRANGEEND$26, n);
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
                    return CTRubyContentImpl.this.getCustomXmlDelRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRubyContentImpl.this.getCustomXmlDelRangeStartArray(n);
                    CTRubyContentImpl.this.setCustomXmlDelRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRubyContentImpl.this.insertNewCustomXmlDelRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRubyContentImpl.this.getCustomXmlDelRangeStartArray(n);
                    CTRubyContentImpl.this.removeCustomXmlDelRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfCustomXmlDelRangeStartArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLDELRANGESTART$28, arrayList);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLDELRANGESTART$28, n));
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
            return this.get_store().count_elements(CUSTOMXMLDELRANGESTART$28);
        }
    }

    @Override
    public void setCustomXmlDelRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLDELRANGESTART$28);
    }

    @Override
    public void setCustomXmlDelRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLDELRANGESTART$28, n, (short)2);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLDELRANGESTART$28, n));
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLDELRANGESTART$28));
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
            this.get_store().remove_element(CUSTOMXMLDELRANGESTART$28, n);
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
                    return CTRubyContentImpl.this.getCustomXmlDelRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRubyContentImpl.this.getCustomXmlDelRangeEndArray(n);
                    CTRubyContentImpl.this.setCustomXmlDelRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRubyContentImpl.this.insertNewCustomXmlDelRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRubyContentImpl.this.getCustomXmlDelRangeEndArray(n);
                    CTRubyContentImpl.this.removeCustomXmlDelRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfCustomXmlDelRangeEndArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLDELRANGEEND$30, arrayList);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLDELRANGEEND$30, n));
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
            return this.get_store().count_elements(CUSTOMXMLDELRANGEEND$30);
        }
    }

    @Override
    public void setCustomXmlDelRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLDELRANGEEND$30);
    }

    @Override
    public void setCustomXmlDelRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLDELRANGEEND$30, n, (short)2);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLDELRANGEEND$30, n));
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
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLDELRANGEEND$30));
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
            this.get_store().remove_element(CUSTOMXMLDELRANGEEND$30, n);
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
                    return CTRubyContentImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRubyContentImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                    CTRubyContentImpl.this.setCustomXmlMoveFromRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRubyContentImpl.this.insertNewCustomXmlMoveFromRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRubyContentImpl.this.getCustomXmlMoveFromRangeStartArray(n);
                    CTRubyContentImpl.this.removeCustomXmlMoveFromRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfCustomXmlMoveFromRangeStartArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLMOVEFROMRANGESTART$32, arrayList);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLMOVEFROMRANGESTART$32, n));
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
            return this.get_store().count_elements(CUSTOMXMLMOVEFROMRANGESTART$32);
        }
    }

    @Override
    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLMOVEFROMRANGESTART$32);
    }

    @Override
    public void setCustomXmlMoveFromRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLMOVEFROMRANGESTART$32, n, (short)2);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVEFROMRANGESTART$32, n));
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLMOVEFROMRANGESTART$32));
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
            this.get_store().remove_element(CUSTOMXMLMOVEFROMRANGESTART$32, n);
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
                    return CTRubyContentImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRubyContentImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                    CTRubyContentImpl.this.setCustomXmlMoveFromRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRubyContentImpl.this.insertNewCustomXmlMoveFromRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRubyContentImpl.this.getCustomXmlMoveFromRangeEndArray(n);
                    CTRubyContentImpl.this.removeCustomXmlMoveFromRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfCustomXmlMoveFromRangeEndArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLMOVEFROMRANGEEND$34, arrayList);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLMOVEFROMRANGEEND$34, n));
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
            return this.get_store().count_elements(CUSTOMXMLMOVEFROMRANGEEND$34);
        }
    }

    @Override
    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLMOVEFROMRANGEEND$34);
    }

    @Override
    public void setCustomXmlMoveFromRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLMOVEFROMRANGEEND$34, n, (short)2);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVEFROMRANGEEND$34, n));
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
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLMOVEFROMRANGEEND$34));
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
            this.get_store().remove_element(CUSTOMXMLMOVEFROMRANGEEND$34, n);
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
                    return CTRubyContentImpl.this.getCustomXmlMoveToRangeStartArray(n);
                }

                @Override
                public CTTrackChange set(int n, CTTrackChange cTTrackChange) {
                    CTTrackChange cTTrackChange2 = CTRubyContentImpl.this.getCustomXmlMoveToRangeStartArray(n);
                    CTRubyContentImpl.this.setCustomXmlMoveToRangeStartArray(n, cTTrackChange);
                    return cTTrackChange2;
                }

                @Override
                public void add(int n, CTTrackChange cTTrackChange) {
                    CTRubyContentImpl.this.insertNewCustomXmlMoveToRangeStart(n).set(cTTrackChange);
                }

                @Override
                public CTTrackChange remove(int n) {
                    CTTrackChange cTTrackChange = CTRubyContentImpl.this.getCustomXmlMoveToRangeStartArray(n);
                    CTRubyContentImpl.this.removeCustomXmlMoveToRangeStart(n);
                    return cTTrackChange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfCustomXmlMoveToRangeStartArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLMOVETORANGESTART$36, arrayList);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CUSTOMXMLMOVETORANGESTART$36, n));
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
            return this.get_store().count_elements(CUSTOMXMLMOVETORANGESTART$36);
        }
    }

    @Override
    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] cTTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTrackChangeArray, CUSTOMXMLMOVETORANGESTART$36);
    }

    @Override
    public void setCustomXmlMoveToRangeStartArray(int n, CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CUSTOMXMLMOVETORANGESTART$36, n, (short)2);
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVETORANGESTART$36, n));
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
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CUSTOMXMLMOVETORANGESTART$36));
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
            this.get_store().remove_element(CUSTOMXMLMOVETORANGESTART$36, n);
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
                    return CTRubyContentImpl.this.getCustomXmlMoveToRangeEndArray(n);
                }

                @Override
                public CTMarkup set(int n, CTMarkup cTMarkup) {
                    CTMarkup cTMarkup2 = CTRubyContentImpl.this.getCustomXmlMoveToRangeEndArray(n);
                    CTRubyContentImpl.this.setCustomXmlMoveToRangeEndArray(n, cTMarkup);
                    return cTMarkup2;
                }

                @Override
                public void add(int n, CTMarkup cTMarkup) {
                    CTRubyContentImpl.this.insertNewCustomXmlMoveToRangeEnd(n).set(cTMarkup);
                }

                @Override
                public CTMarkup remove(int n) {
                    CTMarkup cTMarkup = CTRubyContentImpl.this.getCustomXmlMoveToRangeEndArray(n);
                    CTRubyContentImpl.this.removeCustomXmlMoveToRangeEnd(n);
                    return cTMarkup;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfCustomXmlMoveToRangeEndArray();
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
            this.get_store().find_all_element_users(CUSTOMXMLMOVETORANGEEND$38, arrayList);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().find_element_user(CUSTOMXMLMOVETORANGEEND$38, n));
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
            return this.get_store().count_elements(CUSTOMXMLMOVETORANGEEND$38);
        }
    }

    @Override
    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] cTMarkupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMarkupArray, CUSTOMXMLMOVETORANGEEND$38);
    }

    @Override
    public void setCustomXmlMoveToRangeEndArray(int n, CTMarkup cTMarkup) {
        this.generatedSetterHelperImpl(cTMarkup, CUSTOMXMLMOVETORANGEEND$38, n, (short)2);
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
            cTMarkup = (CTMarkup)((Object)this.get_store().insert_element_user(CUSTOMXMLMOVETORANGEEND$38, n));
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
            cTMarkup = (CTMarkup)((Object)this.get_store().add_element_user(CUSTOMXMLMOVETORANGEEND$38));
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
            this.get_store().remove_element(CUSTOMXMLMOVETORANGEEND$38, n);
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
                    return CTRubyContentImpl.this.getInsArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRubyContentImpl.this.getInsArray(n);
                    CTRubyContentImpl.this.setInsArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRubyContentImpl.this.insertNewIns(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRubyContentImpl.this.getInsArray(n);
                    CTRubyContentImpl.this.removeIns(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfInsArray();
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
            this.get_store().find_all_element_users(INS$40, arrayList);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(INS$40, n));
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
            return this.get_store().count_elements(INS$40);
        }
    }

    @Override
    public void setInsArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, INS$40);
    }

    @Override
    public void setInsArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, INS$40, n, (short)2);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(INS$40, n));
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(INS$40));
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
            this.get_store().remove_element(INS$40, n);
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
                    return CTRubyContentImpl.this.getDelArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRubyContentImpl.this.getDelArray(n);
                    CTRubyContentImpl.this.setDelArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRubyContentImpl.this.insertNewDel(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRubyContentImpl.this.getDelArray(n);
                    CTRubyContentImpl.this.removeDel(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfDelArray();
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
            this.get_store().find_all_element_users(DEL$42, arrayList);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(DEL$42, n));
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
            return this.get_store().count_elements(DEL$42);
        }
    }

    @Override
    public void setDelArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, DEL$42);
    }

    @Override
    public void setDelArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, DEL$42, n, (short)2);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(DEL$42, n));
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(DEL$42));
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
            this.get_store().remove_element(DEL$42, n);
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
                    return CTRubyContentImpl.this.getMoveFromArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRubyContentImpl.this.getMoveFromArray(n);
                    CTRubyContentImpl.this.setMoveFromArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRubyContentImpl.this.insertNewMoveFrom(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRubyContentImpl.this.getMoveFromArray(n);
                    CTRubyContentImpl.this.removeMoveFrom(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfMoveFromArray();
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
            this.get_store().find_all_element_users(MOVEFROM$44, arrayList);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(MOVEFROM$44, n));
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
            return this.get_store().count_elements(MOVEFROM$44);
        }
    }

    @Override
    public void setMoveFromArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, MOVEFROM$44);
    }

    @Override
    public void setMoveFromArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, MOVEFROM$44, n, (short)2);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(MOVEFROM$44, n));
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(MOVEFROM$44));
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
            this.get_store().remove_element(MOVEFROM$44, n);
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
                    return CTRubyContentImpl.this.getMoveToArray(n);
                }

                @Override
                public CTRunTrackChange set(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRunTrackChange cTRunTrackChange2 = CTRubyContentImpl.this.getMoveToArray(n);
                    CTRubyContentImpl.this.setMoveToArray(n, cTRunTrackChange);
                    return cTRunTrackChange2;
                }

                @Override
                public void add(int n, CTRunTrackChange cTRunTrackChange) {
                    CTRubyContentImpl.this.insertNewMoveTo(n).set(cTRunTrackChange);
                }

                @Override
                public CTRunTrackChange remove(int n) {
                    CTRunTrackChange cTRunTrackChange = CTRubyContentImpl.this.getMoveToArray(n);
                    CTRubyContentImpl.this.removeMoveTo(n);
                    return cTRunTrackChange;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfMoveToArray();
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
            this.get_store().find_all_element_users(MOVETO$46, arrayList);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().find_element_user(MOVETO$46, n));
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
            return this.get_store().count_elements(MOVETO$46);
        }
    }

    @Override
    public void setMoveToArray(CTRunTrackChange[] cTRunTrackChangeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRunTrackChangeArray, MOVETO$46);
    }

    @Override
    public void setMoveToArray(int n, CTRunTrackChange cTRunTrackChange) {
        this.generatedSetterHelperImpl(cTRunTrackChange, MOVETO$46, n, (short)2);
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().insert_element_user(MOVETO$46, n));
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
            cTRunTrackChange = (CTRunTrackChange)((Object)this.get_store().add_element_user(MOVETO$46));
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
            this.get_store().remove_element(MOVETO$46, n);
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
                    return CTRubyContentImpl.this.getOMathParaArray(n);
                }

                @Override
                public CTOMathPara set(int n, CTOMathPara cTOMathPara) {
                    CTOMathPara cTOMathPara2 = CTRubyContentImpl.this.getOMathParaArray(n);
                    CTRubyContentImpl.this.setOMathParaArray(n, cTOMathPara);
                    return cTOMathPara2;
                }

                @Override
                public void add(int n, CTOMathPara cTOMathPara) {
                    CTRubyContentImpl.this.insertNewOMathPara(n).set((XmlObject)cTOMathPara);
                }

                @Override
                public CTOMathPara remove(int n) {
                    CTOMathPara cTOMathPara = CTRubyContentImpl.this.getOMathParaArray(n);
                    CTRubyContentImpl.this.removeOMathPara(n);
                    return cTOMathPara;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfOMathParaArray();
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
            this.get_store().find_all_element_users(OMATHPARA$48, arrayList);
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
            cTOMathPara = (CTOMathPara)this.get_store().find_element_user(OMATHPARA$48, n);
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
            return this.get_store().count_elements(OMATHPARA$48);
        }
    }

    @Override
    public void setOMathParaArray(CTOMathPara[] cTOMathParaArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTOMathParaArray, OMATHPARA$48);
    }

    @Override
    public void setOMathParaArray(int n, CTOMathPara cTOMathPara) {
        this.generatedSetterHelperImpl((XmlObject)cTOMathPara, OMATHPARA$48, n, (short)2);
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
            cTOMathPara = (CTOMathPara)this.get_store().insert_element_user(OMATHPARA$48, n);
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
            cTOMathPara = (CTOMathPara)this.get_store().add_element_user(OMATHPARA$48);
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
            this.get_store().remove_element(OMATHPARA$48, n);
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
                    return CTRubyContentImpl.this.getOMathArray(n);
                }

                @Override
                public CTOMath set(int n, CTOMath cTOMath) {
                    CTOMath cTOMath2 = CTRubyContentImpl.this.getOMathArray(n);
                    CTRubyContentImpl.this.setOMathArray(n, cTOMath);
                    return cTOMath2;
                }

                @Override
                public void add(int n, CTOMath cTOMath) {
                    CTRubyContentImpl.this.insertNewOMath(n).set((XmlObject)cTOMath);
                }

                @Override
                public CTOMath remove(int n) {
                    CTOMath cTOMath = CTRubyContentImpl.this.getOMathArray(n);
                    CTRubyContentImpl.this.removeOMath(n);
                    return cTOMath;
                }

                @Override
                public int size() {
                    return CTRubyContentImpl.this.sizeOfOMathArray();
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
            this.get_store().find_all_element_users(OMATH$50, arrayList);
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
            cTOMath = (CTOMath)this.get_store().find_element_user(OMATH$50, n);
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
            return this.get_store().count_elements(OMATH$50);
        }
    }

    @Override
    public void setOMathArray(CTOMath[] cTOMathArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTOMathArray, OMATH$50);
    }

    @Override
    public void setOMathArray(int n, CTOMath cTOMath) {
        this.generatedSetterHelperImpl((XmlObject)cTOMath, OMATH$50, n, (short)2);
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
            cTOMath = (CTOMath)this.get_store().insert_element_user(OMATH$50, n);
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
            cTOMath = (CTOMath)this.get_store().add_element_user(OMATH$50);
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
            this.get_store().remove_element(OMATH$50, n);
        }
    }
}

