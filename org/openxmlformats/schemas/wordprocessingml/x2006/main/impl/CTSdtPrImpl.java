/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLock
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPlaceholder
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtComboBox
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtText
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtComboBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDocPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;

public class CTSdtPrImpl
extends XmlComplexContentImpl
implements CTSdtPr {
    private static final long serialVersionUID = 1L;
    private static final QName RPR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr");
    private static final QName ALIAS$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "alias");
    private static final QName LOCK$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lock");
    private static final QName PLACEHOLDER$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "placeholder");
    private static final QName SHOWINGPLCHDR$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "showingPlcHdr");
    private static final QName DATABINDING$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dataBinding");
    private static final QName TEMPORARY$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "temporary");
    private static final QName ID$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "id");
    private static final QName TAG$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tag");
    private static final QName EQUATION$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "equation");
    private static final QName COMBOBOX$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "comboBox");
    private static final QName DATE$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "date");
    private static final QName DOCPARTOBJ$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docPartObj");
    private static final QName DOCPARTLIST$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docPartList");
    private static final QName DROPDOWNLIST$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dropDownList");
    private static final QName PICTURE$30 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "picture");
    private static final QName RICHTEXT$32 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "richText");
    private static final QName TEXT$34 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "text");
    private static final QName CITATION$36 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "citation");
    private static final QName GROUP$38 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "group");
    private static final QName BIBLIOGRAPHY$40 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bibliography");

    public CTSdtPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRPr> getRPrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RPrList
            extends AbstractList<CTRPr> {
                RPrList() {
                }

                @Override
                public CTRPr get(int n) {
                    return CTSdtPrImpl.this.getRPrArray(n);
                }

                @Override
                public CTRPr set(int n, CTRPr cTRPr) {
                    CTRPr cTRPr2 = CTSdtPrImpl.this.getRPrArray(n);
                    CTSdtPrImpl.this.setRPrArray(n, cTRPr);
                    return cTRPr2;
                }

                @Override
                public void add(int n, CTRPr cTRPr) {
                    CTSdtPrImpl.this.insertNewRPr(n).set(cTRPr);
                }

                @Override
                public CTRPr remove(int n) {
                    CTRPr cTRPr = CTSdtPrImpl.this.getRPrArray(n);
                    CTSdtPrImpl.this.removeRPr(n);
                    return cTRPr;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfRPrArray();
                }
            }
            return new RPrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRPr[] getRPrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RPR$0, arrayList);
            CTRPr[] cTRPrArray = new CTRPr[arrayList.size()];
            arrayList.toArray(cTRPrArray);
            return cTRPrArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPr getRPrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPr cTRPr = null;
            cTRPr = (CTRPr)((Object)this.get_store().find_element_user(RPR$0, n));
            if (cTRPr == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRPrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RPR$0);
        }
    }

    @Override
    public void setRPrArray(CTRPr[] cTRPrArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRPrArray, RPR$0);
    }

    @Override
    public void setRPrArray(int n, CTRPr cTRPr) {
        this.generatedSetterHelperImpl(cTRPr, RPR$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPr insertNewRPr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPr cTRPr = null;
            cTRPr = (CTRPr)((Object)this.get_store().insert_element_user(RPR$0, n));
            return cTRPr;
        }
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
    public void removeRPr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RPR$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTString> getAliasList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AliasList
            extends AbstractList<CTString> {
                AliasList() {
                }

                @Override
                public CTString get(int n) {
                    return CTSdtPrImpl.this.getAliasArray(n);
                }

                @Override
                public CTString set(int n, CTString cTString) {
                    CTString cTString2 = CTSdtPrImpl.this.getAliasArray(n);
                    CTSdtPrImpl.this.setAliasArray(n, cTString);
                    return cTString2;
                }

                @Override
                public void add(int n, CTString cTString) {
                    CTSdtPrImpl.this.insertNewAlias(n).set(cTString);
                }

                @Override
                public CTString remove(int n) {
                    CTString cTString = CTSdtPrImpl.this.getAliasArray(n);
                    CTSdtPrImpl.this.removeAlias(n);
                    return cTString;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfAliasArray();
                }
            }
            return new AliasList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTString[] getAliasArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALIAS$2, arrayList);
            CTString[] cTStringArray = new CTString[arrayList.size()];
            arrayList.toArray(cTStringArray);
            return cTStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getAliasArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(ALIAS$2, n));
            if (cTString == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAliasArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALIAS$2);
        }
    }

    @Override
    public void setAliasArray(CTString[] cTStringArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTStringArray, ALIAS$2);
    }

    @Override
    public void setAliasArray(int n, CTString cTString) {
        this.generatedSetterHelperImpl(cTString, ALIAS$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString insertNewAlias(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().insert_element_user(ALIAS$2, n));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewAlias() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(ALIAS$2));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlias(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALIAS$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLock> getLockList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LockList
            extends AbstractList<CTLock> {
                LockList() {
                }

                @Override
                public CTLock get(int n) {
                    return CTSdtPrImpl.this.getLockArray(n);
                }

                @Override
                public CTLock set(int n, CTLock cTLock) {
                    CTLock cTLock2 = CTSdtPrImpl.this.getLockArray(n);
                    CTSdtPrImpl.this.setLockArray(n, cTLock);
                    return cTLock2;
                }

                @Override
                public void add(int n, CTLock cTLock) {
                    CTSdtPrImpl.this.insertNewLock(n).set((XmlObject)cTLock);
                }

                @Override
                public CTLock remove(int n) {
                    CTLock cTLock = CTSdtPrImpl.this.getLockArray(n);
                    CTSdtPrImpl.this.removeLock(n);
                    return cTLock;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfLockArray();
                }
            }
            return new LockList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTLock[] getLockArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LOCK$4, arrayList);
            CTLock[] cTLockArray = new CTLock[arrayList.size()];
            arrayList.toArray(cTLockArray);
            return cTLockArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLock getLockArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLock cTLock = null;
            cTLock = (CTLock)this.get_store().find_element_user(LOCK$4, n);
            if (cTLock == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLockArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LOCK$4);
        }
    }

    @Override
    public void setLockArray(CTLock[] cTLockArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTLockArray, LOCK$4);
    }

    @Override
    public void setLockArray(int n, CTLock cTLock) {
        this.generatedSetterHelperImpl((XmlObject)cTLock, LOCK$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLock insertNewLock(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLock cTLock = null;
            cTLock = (CTLock)this.get_store().insert_element_user(LOCK$4, n);
            return cTLock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLock addNewLock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLock cTLock = null;
            cTLock = (CTLock)this.get_store().add_element_user(LOCK$4);
            return cTLock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLock(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LOCK$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPlaceholder> getPlaceholderList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PlaceholderList
            extends AbstractList<CTPlaceholder> {
                PlaceholderList() {
                }

                @Override
                public CTPlaceholder get(int n) {
                    return CTSdtPrImpl.this.getPlaceholderArray(n);
                }

                @Override
                public CTPlaceholder set(int n, CTPlaceholder cTPlaceholder) {
                    CTPlaceholder cTPlaceholder2 = CTSdtPrImpl.this.getPlaceholderArray(n);
                    CTSdtPrImpl.this.setPlaceholderArray(n, cTPlaceholder);
                    return cTPlaceholder2;
                }

                @Override
                public void add(int n, CTPlaceholder cTPlaceholder) {
                    CTSdtPrImpl.this.insertNewPlaceholder(n).set((XmlObject)cTPlaceholder);
                }

                @Override
                public CTPlaceholder remove(int n) {
                    CTPlaceholder cTPlaceholder = CTSdtPrImpl.this.getPlaceholderArray(n);
                    CTSdtPrImpl.this.removePlaceholder(n);
                    return cTPlaceholder;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfPlaceholderArray();
                }
            }
            return new PlaceholderList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPlaceholder[] getPlaceholderArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PLACEHOLDER$6, arrayList);
            CTPlaceholder[] cTPlaceholderArray = new CTPlaceholder[arrayList.size()];
            arrayList.toArray(cTPlaceholderArray);
            return cTPlaceholderArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPlaceholder getPlaceholderArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPlaceholder cTPlaceholder = null;
            cTPlaceholder = (CTPlaceholder)this.get_store().find_element_user(PLACEHOLDER$6, n);
            if (cTPlaceholder == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPlaceholder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPlaceholderArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PLACEHOLDER$6);
        }
    }

    @Override
    public void setPlaceholderArray(CTPlaceholder[] cTPlaceholderArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPlaceholderArray, PLACEHOLDER$6);
    }

    @Override
    public void setPlaceholderArray(int n, CTPlaceholder cTPlaceholder) {
        this.generatedSetterHelperImpl((XmlObject)cTPlaceholder, PLACEHOLDER$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPlaceholder insertNewPlaceholder(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPlaceholder cTPlaceholder = null;
            cTPlaceholder = (CTPlaceholder)this.get_store().insert_element_user(PLACEHOLDER$6, n);
            return cTPlaceholder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPlaceholder addNewPlaceholder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPlaceholder cTPlaceholder = null;
            cTPlaceholder = (CTPlaceholder)this.get_store().add_element_user(PLACEHOLDER$6);
            return cTPlaceholder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePlaceholder(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PLACEHOLDER$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOnOff> getShowingPlcHdrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ShowingPlcHdrList
            extends AbstractList<CTOnOff> {
                ShowingPlcHdrList() {
                }

                @Override
                public CTOnOff get(int n) {
                    return CTSdtPrImpl.this.getShowingPlcHdrArray(n);
                }

                @Override
                public CTOnOff set(int n, CTOnOff cTOnOff) {
                    CTOnOff cTOnOff2 = CTSdtPrImpl.this.getShowingPlcHdrArray(n);
                    CTSdtPrImpl.this.setShowingPlcHdrArray(n, cTOnOff);
                    return cTOnOff2;
                }

                @Override
                public void add(int n, CTOnOff cTOnOff) {
                    CTSdtPrImpl.this.insertNewShowingPlcHdr(n).set(cTOnOff);
                }

                @Override
                public CTOnOff remove(int n) {
                    CTOnOff cTOnOff = CTSdtPrImpl.this.getShowingPlcHdrArray(n);
                    CTSdtPrImpl.this.removeShowingPlcHdr(n);
                    return cTOnOff;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfShowingPlcHdrArray();
                }
            }
            return new ShowingPlcHdrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOnOff[] getShowingPlcHdrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SHOWINGPLCHDR$8, arrayList);
            CTOnOff[] cTOnOffArray = new CTOnOff[arrayList.size()];
            arrayList.toArray(cTOnOffArray);
            return cTOnOffArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getShowingPlcHdrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SHOWINGPLCHDR$8, n));
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
    public int sizeOfShowingPlcHdrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWINGPLCHDR$8);
        }
    }

    @Override
    public void setShowingPlcHdrArray(CTOnOff[] cTOnOffArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOnOffArray, SHOWINGPLCHDR$8);
    }

    @Override
    public void setShowingPlcHdrArray(int n, CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SHOWINGPLCHDR$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff insertNewShowingPlcHdr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().insert_element_user(SHOWINGPLCHDR$8, n));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewShowingPlcHdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SHOWINGPLCHDR$8));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeShowingPlcHdr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWINGPLCHDR$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDataBinding> getDataBindingList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DataBindingList
            extends AbstractList<CTDataBinding> {
                DataBindingList() {
                }

                @Override
                public CTDataBinding get(int n) {
                    return CTSdtPrImpl.this.getDataBindingArray(n);
                }

                @Override
                public CTDataBinding set(int n, CTDataBinding cTDataBinding) {
                    CTDataBinding cTDataBinding2 = CTSdtPrImpl.this.getDataBindingArray(n);
                    CTSdtPrImpl.this.setDataBindingArray(n, cTDataBinding);
                    return cTDataBinding2;
                }

                @Override
                public void add(int n, CTDataBinding cTDataBinding) {
                    CTSdtPrImpl.this.insertNewDataBinding(n).set((XmlObject)cTDataBinding);
                }

                @Override
                public CTDataBinding remove(int n) {
                    CTDataBinding cTDataBinding = CTSdtPrImpl.this.getDataBindingArray(n);
                    CTSdtPrImpl.this.removeDataBinding(n);
                    return cTDataBinding;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfDataBindingArray();
                }
            }
            return new DataBindingList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDataBinding[] getDataBindingArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DATABINDING$10, arrayList);
            CTDataBinding[] cTDataBindingArray = new CTDataBinding[arrayList.size()];
            arrayList.toArray(cTDataBindingArray);
            return cTDataBindingArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataBinding getDataBindingArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataBinding cTDataBinding = null;
            cTDataBinding = (CTDataBinding)this.get_store().find_element_user(DATABINDING$10, n);
            if (cTDataBinding == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDataBinding;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDataBindingArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DATABINDING$10);
        }
    }

    @Override
    public void setDataBindingArray(CTDataBinding[] cTDataBindingArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTDataBindingArray, DATABINDING$10);
    }

    @Override
    public void setDataBindingArray(int n, CTDataBinding cTDataBinding) {
        this.generatedSetterHelperImpl((XmlObject)cTDataBinding, DATABINDING$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataBinding insertNewDataBinding(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataBinding cTDataBinding = null;
            cTDataBinding = (CTDataBinding)this.get_store().insert_element_user(DATABINDING$10, n);
            return cTDataBinding;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataBinding addNewDataBinding() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataBinding cTDataBinding = null;
            cTDataBinding = (CTDataBinding)this.get_store().add_element_user(DATABINDING$10);
            return cTDataBinding;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDataBinding(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DATABINDING$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOnOff> getTemporaryList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TemporaryList
            extends AbstractList<CTOnOff> {
                TemporaryList() {
                }

                @Override
                public CTOnOff get(int n) {
                    return CTSdtPrImpl.this.getTemporaryArray(n);
                }

                @Override
                public CTOnOff set(int n, CTOnOff cTOnOff) {
                    CTOnOff cTOnOff2 = CTSdtPrImpl.this.getTemporaryArray(n);
                    CTSdtPrImpl.this.setTemporaryArray(n, cTOnOff);
                    return cTOnOff2;
                }

                @Override
                public void add(int n, CTOnOff cTOnOff) {
                    CTSdtPrImpl.this.insertNewTemporary(n).set(cTOnOff);
                }

                @Override
                public CTOnOff remove(int n) {
                    CTOnOff cTOnOff = CTSdtPrImpl.this.getTemporaryArray(n);
                    CTSdtPrImpl.this.removeTemporary(n);
                    return cTOnOff;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfTemporaryArray();
                }
            }
            return new TemporaryList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOnOff[] getTemporaryArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TEMPORARY$12, arrayList);
            CTOnOff[] cTOnOffArray = new CTOnOff[arrayList.size()];
            arrayList.toArray(cTOnOffArray);
            return cTOnOffArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getTemporaryArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(TEMPORARY$12, n));
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
    public int sizeOfTemporaryArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEMPORARY$12);
        }
    }

    @Override
    public void setTemporaryArray(CTOnOff[] cTOnOffArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOnOffArray, TEMPORARY$12);
    }

    @Override
    public void setTemporaryArray(int n, CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, TEMPORARY$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff insertNewTemporary(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().insert_element_user(TEMPORARY$12, n));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewTemporary() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(TEMPORARY$12));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTemporary(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEMPORARY$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDecimalNumber> getIdList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class IdList
            extends AbstractList<CTDecimalNumber> {
                IdList() {
                }

                @Override
                public CTDecimalNumber get(int n) {
                    return CTSdtPrImpl.this.getIdArray(n);
                }

                @Override
                public CTDecimalNumber set(int n, CTDecimalNumber cTDecimalNumber) {
                    CTDecimalNumber cTDecimalNumber2 = CTSdtPrImpl.this.getIdArray(n);
                    CTSdtPrImpl.this.setIdArray(n, cTDecimalNumber);
                    return cTDecimalNumber2;
                }

                @Override
                public void add(int n, CTDecimalNumber cTDecimalNumber) {
                    CTSdtPrImpl.this.insertNewId(n).set(cTDecimalNumber);
                }

                @Override
                public CTDecimalNumber remove(int n) {
                    CTDecimalNumber cTDecimalNumber = CTSdtPrImpl.this.getIdArray(n);
                    CTSdtPrImpl.this.removeId(n);
                    return cTDecimalNumber;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfIdArray();
                }
            }
            return new IdList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDecimalNumber[] getIdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ID$14, arrayList);
            CTDecimalNumber[] cTDecimalNumberArray = new CTDecimalNumber[arrayList.size()];
            arrayList.toArray(cTDecimalNumberArray);
            return cTDecimalNumberArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getIdArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(ID$14, n));
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
    public int sizeOfIdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ID$14);
        }
    }

    @Override
    public void setIdArray(CTDecimalNumber[] cTDecimalNumberArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDecimalNumberArray, ID$14);
    }

    @Override
    public void setIdArray(int n, CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, ID$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber insertNewId(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().insert_element_user(ID$14, n));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(ID$14));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeId(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ID$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTString> getTagList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TagList
            extends AbstractList<CTString> {
                TagList() {
                }

                @Override
                public CTString get(int n) {
                    return CTSdtPrImpl.this.getTagArray(n);
                }

                @Override
                public CTString set(int n, CTString cTString) {
                    CTString cTString2 = CTSdtPrImpl.this.getTagArray(n);
                    CTSdtPrImpl.this.setTagArray(n, cTString);
                    return cTString2;
                }

                @Override
                public void add(int n, CTString cTString) {
                    CTSdtPrImpl.this.insertNewTag(n).set(cTString);
                }

                @Override
                public CTString remove(int n) {
                    CTString cTString = CTSdtPrImpl.this.getTagArray(n);
                    CTSdtPrImpl.this.removeTag(n);
                    return cTString;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfTagArray();
                }
            }
            return new TagList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTString[] getTagArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TAG$16, arrayList);
            CTString[] cTStringArray = new CTString[arrayList.size()];
            arrayList.toArray(cTStringArray);
            return cTStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getTagArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(TAG$16, n));
            if (cTString == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTagArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TAG$16);
        }
    }

    @Override
    public void setTagArray(CTString[] cTStringArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTStringArray, TAG$16);
    }

    @Override
    public void setTagArray(int n, CTString cTString) {
        this.generatedSetterHelperImpl(cTString, TAG$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString insertNewTag(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().insert_element_user(TAG$16, n));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewTag() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(TAG$16));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTag(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TAG$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getEquationList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EquationList
            extends AbstractList<CTEmpty> {
                EquationList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTSdtPrImpl.this.getEquationArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTSdtPrImpl.this.getEquationArray(n);
                    CTSdtPrImpl.this.setEquationArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTSdtPrImpl.this.insertNewEquation(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTSdtPrImpl.this.getEquationArray(n);
                    CTSdtPrImpl.this.removeEquation(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfEquationArray();
                }
            }
            return new EquationList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getEquationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EQUATION$18, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getEquationArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(EQUATION$18, n));
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
    public int sizeOfEquationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EQUATION$18);
        }
    }

    @Override
    public void setEquationArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, EQUATION$18);
    }

    @Override
    public void setEquationArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, EQUATION$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewEquation(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(EQUATION$18, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewEquation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(EQUATION$18));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEquation(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EQUATION$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSdtComboBox> getComboBoxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ComboBoxList
            extends AbstractList<CTSdtComboBox> {
                ComboBoxList() {
                }

                @Override
                public CTSdtComboBox get(int n) {
                    return CTSdtPrImpl.this.getComboBoxArray(n);
                }

                @Override
                public CTSdtComboBox set(int n, CTSdtComboBox cTSdtComboBox) {
                    CTSdtComboBox cTSdtComboBox2 = CTSdtPrImpl.this.getComboBoxArray(n);
                    CTSdtPrImpl.this.setComboBoxArray(n, cTSdtComboBox);
                    return cTSdtComboBox2;
                }

                @Override
                public void add(int n, CTSdtComboBox cTSdtComboBox) {
                    CTSdtPrImpl.this.insertNewComboBox(n).set((XmlObject)cTSdtComboBox);
                }

                @Override
                public CTSdtComboBox remove(int n) {
                    CTSdtComboBox cTSdtComboBox = CTSdtPrImpl.this.getComboBoxArray(n);
                    CTSdtPrImpl.this.removeComboBox(n);
                    return cTSdtComboBox;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfComboBoxArray();
                }
            }
            return new ComboBoxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSdtComboBox[] getComboBoxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COMBOBOX$20, arrayList);
            CTSdtComboBox[] cTSdtComboBoxArray = new CTSdtComboBox[arrayList.size()];
            arrayList.toArray(cTSdtComboBoxArray);
            return cTSdtComboBoxArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtComboBox getComboBoxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtComboBox cTSdtComboBox = null;
            cTSdtComboBox = (CTSdtComboBox)this.get_store().find_element_user(COMBOBOX$20, n);
            if (cTSdtComboBox == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSdtComboBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfComboBoxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMBOBOX$20);
        }
    }

    @Override
    public void setComboBoxArray(CTSdtComboBox[] cTSdtComboBoxArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSdtComboBoxArray, COMBOBOX$20);
    }

    @Override
    public void setComboBoxArray(int n, CTSdtComboBox cTSdtComboBox) {
        this.generatedSetterHelperImpl((XmlObject)cTSdtComboBox, COMBOBOX$20, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtComboBox insertNewComboBox(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtComboBox cTSdtComboBox = null;
            cTSdtComboBox = (CTSdtComboBox)this.get_store().insert_element_user(COMBOBOX$20, n);
            return cTSdtComboBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtComboBox addNewComboBox() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtComboBox cTSdtComboBox = null;
            cTSdtComboBox = (CTSdtComboBox)this.get_store().add_element_user(COMBOBOX$20);
            return cTSdtComboBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeComboBox(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMBOBOX$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSdtDate> getDateList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DateList
            extends AbstractList<CTSdtDate> {
                DateList() {
                }

                @Override
                public CTSdtDate get(int n) {
                    return CTSdtPrImpl.this.getDateArray(n);
                }

                @Override
                public CTSdtDate set(int n, CTSdtDate cTSdtDate) {
                    CTSdtDate cTSdtDate2 = CTSdtPrImpl.this.getDateArray(n);
                    CTSdtPrImpl.this.setDateArray(n, cTSdtDate);
                    return cTSdtDate2;
                }

                @Override
                public void add(int n, CTSdtDate cTSdtDate) {
                    CTSdtPrImpl.this.insertNewDate(n).set((XmlObject)cTSdtDate);
                }

                @Override
                public CTSdtDate remove(int n) {
                    CTSdtDate cTSdtDate = CTSdtPrImpl.this.getDateArray(n);
                    CTSdtPrImpl.this.removeDate(n);
                    return cTSdtDate;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfDateArray();
                }
            }
            return new DateList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSdtDate[] getDateArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DATE$22, arrayList);
            CTSdtDate[] cTSdtDateArray = new CTSdtDate[arrayList.size()];
            arrayList.toArray(cTSdtDateArray);
            return cTSdtDateArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDate getDateArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDate cTSdtDate = null;
            cTSdtDate = (CTSdtDate)this.get_store().find_element_user(DATE$22, n);
            if (cTSdtDate == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSdtDate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDateArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DATE$22);
        }
    }

    @Override
    public void setDateArray(CTSdtDate[] cTSdtDateArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSdtDateArray, DATE$22);
    }

    @Override
    public void setDateArray(int n, CTSdtDate cTSdtDate) {
        this.generatedSetterHelperImpl((XmlObject)cTSdtDate, DATE$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDate insertNewDate(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDate cTSdtDate = null;
            cTSdtDate = (CTSdtDate)this.get_store().insert_element_user(DATE$22, n);
            return cTSdtDate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDate addNewDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDate cTSdtDate = null;
            cTSdtDate = (CTSdtDate)this.get_store().add_element_user(DATE$22);
            return cTSdtDate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDate(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DATE$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSdtDocPart> getDocPartObjList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DocPartObjList
            extends AbstractList<CTSdtDocPart> {
                DocPartObjList() {
                }

                @Override
                public CTSdtDocPart get(int n) {
                    return CTSdtPrImpl.this.getDocPartObjArray(n);
                }

                @Override
                public CTSdtDocPart set(int n, CTSdtDocPart cTSdtDocPart) {
                    CTSdtDocPart cTSdtDocPart2 = CTSdtPrImpl.this.getDocPartObjArray(n);
                    CTSdtPrImpl.this.setDocPartObjArray(n, cTSdtDocPart);
                    return cTSdtDocPart2;
                }

                @Override
                public void add(int n, CTSdtDocPart cTSdtDocPart) {
                    CTSdtPrImpl.this.insertNewDocPartObj(n).set(cTSdtDocPart);
                }

                @Override
                public CTSdtDocPart remove(int n) {
                    CTSdtDocPart cTSdtDocPart = CTSdtPrImpl.this.getDocPartObjArray(n);
                    CTSdtPrImpl.this.removeDocPartObj(n);
                    return cTSdtDocPart;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfDocPartObjArray();
                }
            }
            return new DocPartObjList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSdtDocPart[] getDocPartObjArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DOCPARTOBJ$24, arrayList);
            CTSdtDocPart[] cTSdtDocPartArray = new CTSdtDocPart[arrayList.size()];
            arrayList.toArray(cTSdtDocPartArray);
            return cTSdtDocPartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDocPart getDocPartObjArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDocPart cTSdtDocPart = null;
            cTSdtDocPart = (CTSdtDocPart)((Object)this.get_store().find_element_user(DOCPARTOBJ$24, n));
            if (cTSdtDocPart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSdtDocPart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDocPartObjArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCPARTOBJ$24);
        }
    }

    @Override
    public void setDocPartObjArray(CTSdtDocPart[] cTSdtDocPartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSdtDocPartArray, DOCPARTOBJ$24);
    }

    @Override
    public void setDocPartObjArray(int n, CTSdtDocPart cTSdtDocPart) {
        this.generatedSetterHelperImpl(cTSdtDocPart, DOCPARTOBJ$24, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDocPart insertNewDocPartObj(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDocPart cTSdtDocPart = null;
            cTSdtDocPart = (CTSdtDocPart)((Object)this.get_store().insert_element_user(DOCPARTOBJ$24, n));
            return cTSdtDocPart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDocPart addNewDocPartObj() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDocPart cTSdtDocPart = null;
            cTSdtDocPart = (CTSdtDocPart)((Object)this.get_store().add_element_user(DOCPARTOBJ$24));
            return cTSdtDocPart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDocPartObj(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCPARTOBJ$24, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSdtDocPart> getDocPartListList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DocPartListList
            extends AbstractList<CTSdtDocPart> {
                DocPartListList() {
                }

                @Override
                public CTSdtDocPart get(int n) {
                    return CTSdtPrImpl.this.getDocPartListArray(n);
                }

                @Override
                public CTSdtDocPart set(int n, CTSdtDocPart cTSdtDocPart) {
                    CTSdtDocPart cTSdtDocPart2 = CTSdtPrImpl.this.getDocPartListArray(n);
                    CTSdtPrImpl.this.setDocPartListArray(n, cTSdtDocPart);
                    return cTSdtDocPart2;
                }

                @Override
                public void add(int n, CTSdtDocPart cTSdtDocPart) {
                    CTSdtPrImpl.this.insertNewDocPartList(n).set(cTSdtDocPart);
                }

                @Override
                public CTSdtDocPart remove(int n) {
                    CTSdtDocPart cTSdtDocPart = CTSdtPrImpl.this.getDocPartListArray(n);
                    CTSdtPrImpl.this.removeDocPartList(n);
                    return cTSdtDocPart;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfDocPartListArray();
                }
            }
            return new DocPartListList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSdtDocPart[] getDocPartListArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DOCPARTLIST$26, arrayList);
            CTSdtDocPart[] cTSdtDocPartArray = new CTSdtDocPart[arrayList.size()];
            arrayList.toArray(cTSdtDocPartArray);
            return cTSdtDocPartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDocPart getDocPartListArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDocPart cTSdtDocPart = null;
            cTSdtDocPart = (CTSdtDocPart)((Object)this.get_store().find_element_user(DOCPARTLIST$26, n));
            if (cTSdtDocPart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSdtDocPart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDocPartListArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCPARTLIST$26);
        }
    }

    @Override
    public void setDocPartListArray(CTSdtDocPart[] cTSdtDocPartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSdtDocPartArray, DOCPARTLIST$26);
    }

    @Override
    public void setDocPartListArray(int n, CTSdtDocPart cTSdtDocPart) {
        this.generatedSetterHelperImpl(cTSdtDocPart, DOCPARTLIST$26, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDocPart insertNewDocPartList(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDocPart cTSdtDocPart = null;
            cTSdtDocPart = (CTSdtDocPart)((Object)this.get_store().insert_element_user(DOCPARTLIST$26, n));
            return cTSdtDocPart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDocPart addNewDocPartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDocPart cTSdtDocPart = null;
            cTSdtDocPart = (CTSdtDocPart)((Object)this.get_store().add_element_user(DOCPARTLIST$26));
            return cTSdtDocPart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDocPartList(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCPARTLIST$26, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSdtDropDownList> getDropDownListList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DropDownListList
            extends AbstractList<CTSdtDropDownList> {
                DropDownListList() {
                }

                @Override
                public CTSdtDropDownList get(int n) {
                    return CTSdtPrImpl.this.getDropDownListArray(n);
                }

                @Override
                public CTSdtDropDownList set(int n, CTSdtDropDownList cTSdtDropDownList) {
                    CTSdtDropDownList cTSdtDropDownList2 = CTSdtPrImpl.this.getDropDownListArray(n);
                    CTSdtPrImpl.this.setDropDownListArray(n, cTSdtDropDownList);
                    return cTSdtDropDownList2;
                }

                @Override
                public void add(int n, CTSdtDropDownList cTSdtDropDownList) {
                    CTSdtPrImpl.this.insertNewDropDownList(n).set((XmlObject)cTSdtDropDownList);
                }

                @Override
                public CTSdtDropDownList remove(int n) {
                    CTSdtDropDownList cTSdtDropDownList = CTSdtPrImpl.this.getDropDownListArray(n);
                    CTSdtPrImpl.this.removeDropDownList(n);
                    return cTSdtDropDownList;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfDropDownListArray();
                }
            }
            return new DropDownListList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSdtDropDownList[] getDropDownListArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DROPDOWNLIST$28, arrayList);
            CTSdtDropDownList[] cTSdtDropDownListArray = new CTSdtDropDownList[arrayList.size()];
            arrayList.toArray(cTSdtDropDownListArray);
            return cTSdtDropDownListArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDropDownList getDropDownListArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDropDownList cTSdtDropDownList = null;
            cTSdtDropDownList = (CTSdtDropDownList)this.get_store().find_element_user(DROPDOWNLIST$28, n);
            if (cTSdtDropDownList == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSdtDropDownList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDropDownListArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DROPDOWNLIST$28);
        }
    }

    @Override
    public void setDropDownListArray(CTSdtDropDownList[] cTSdtDropDownListArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSdtDropDownListArray, DROPDOWNLIST$28);
    }

    @Override
    public void setDropDownListArray(int n, CTSdtDropDownList cTSdtDropDownList) {
        this.generatedSetterHelperImpl((XmlObject)cTSdtDropDownList, DROPDOWNLIST$28, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDropDownList insertNewDropDownList(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDropDownList cTSdtDropDownList = null;
            cTSdtDropDownList = (CTSdtDropDownList)this.get_store().insert_element_user(DROPDOWNLIST$28, n);
            return cTSdtDropDownList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtDropDownList addNewDropDownList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtDropDownList cTSdtDropDownList = null;
            cTSdtDropDownList = (CTSdtDropDownList)this.get_store().add_element_user(DROPDOWNLIST$28);
            return cTSdtDropDownList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDropDownList(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DROPDOWNLIST$28, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getPictureList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PictureList
            extends AbstractList<CTEmpty> {
                PictureList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTSdtPrImpl.this.getPictureArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTSdtPrImpl.this.getPictureArray(n);
                    CTSdtPrImpl.this.setPictureArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTSdtPrImpl.this.insertNewPicture(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTSdtPrImpl.this.getPictureArray(n);
                    CTSdtPrImpl.this.removePicture(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfPictureArray();
                }
            }
            return new PictureList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getPictureArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PICTURE$30, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getPictureArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(PICTURE$30, n));
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
    public int sizeOfPictureArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PICTURE$30);
        }
    }

    @Override
    public void setPictureArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, PICTURE$30);
    }

    @Override
    public void setPictureArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, PICTURE$30, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewPicture(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(PICTURE$30, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewPicture() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(PICTURE$30));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePicture(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PICTURE$30, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getRichTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RichTextList
            extends AbstractList<CTEmpty> {
                RichTextList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTSdtPrImpl.this.getRichTextArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTSdtPrImpl.this.getRichTextArray(n);
                    CTSdtPrImpl.this.setRichTextArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTSdtPrImpl.this.insertNewRichText(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTSdtPrImpl.this.getRichTextArray(n);
                    CTSdtPrImpl.this.removeRichText(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfRichTextArray();
                }
            }
            return new RichTextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getRichTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RICHTEXT$32, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getRichTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(RICHTEXT$32, n));
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
    public int sizeOfRichTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RICHTEXT$32);
        }
    }

    @Override
    public void setRichTextArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, RICHTEXT$32);
    }

    @Override
    public void setRichTextArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, RICHTEXT$32, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewRichText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(RICHTEXT$32, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewRichText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(RICHTEXT$32));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRichText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RICHTEXT$32, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSdtText> getTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TextList
            extends AbstractList<CTSdtText> {
                TextList() {
                }

                @Override
                public CTSdtText get(int n) {
                    return CTSdtPrImpl.this.getTextArray(n);
                }

                @Override
                public CTSdtText set(int n, CTSdtText cTSdtText) {
                    CTSdtText cTSdtText2 = CTSdtPrImpl.this.getTextArray(n);
                    CTSdtPrImpl.this.setTextArray(n, cTSdtText);
                    return cTSdtText2;
                }

                @Override
                public void add(int n, CTSdtText cTSdtText) {
                    CTSdtPrImpl.this.insertNewText(n).set((XmlObject)cTSdtText);
                }

                @Override
                public CTSdtText remove(int n) {
                    CTSdtText cTSdtText = CTSdtPrImpl.this.getTextArray(n);
                    CTSdtPrImpl.this.removeText(n);
                    return cTSdtText;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfTextArray();
                }
            }
            return new TextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSdtText[] getTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TEXT$34, arrayList);
            CTSdtText[] cTSdtTextArray = new CTSdtText[arrayList.size()];
            arrayList.toArray(cTSdtTextArray);
            return cTSdtTextArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtText getTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtText cTSdtText = null;
            cTSdtText = (CTSdtText)this.get_store().find_element_user(TEXT$34, n);
            if (cTSdtText == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSdtText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXT$34);
        }
    }

    @Override
    public void setTextArray(CTSdtText[] cTSdtTextArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSdtTextArray, TEXT$34);
    }

    @Override
    public void setTextArray(int n, CTSdtText cTSdtText) {
        this.generatedSetterHelperImpl((XmlObject)cTSdtText, TEXT$34, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtText insertNewText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtText cTSdtText = null;
            cTSdtText = (CTSdtText)this.get_store().insert_element_user(TEXT$34, n);
            return cTSdtText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtText addNewText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtText cTSdtText = null;
            cTSdtText = (CTSdtText)this.get_store().add_element_user(TEXT$34);
            return cTSdtText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXT$34, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getCitationList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CitationList
            extends AbstractList<CTEmpty> {
                CitationList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTSdtPrImpl.this.getCitationArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTSdtPrImpl.this.getCitationArray(n);
                    CTSdtPrImpl.this.setCitationArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTSdtPrImpl.this.insertNewCitation(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTSdtPrImpl.this.getCitationArray(n);
                    CTSdtPrImpl.this.removeCitation(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfCitationArray();
                }
            }
            return new CitationList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getCitationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CITATION$36, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getCitationArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(CITATION$36, n));
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
    public int sizeOfCitationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CITATION$36);
        }
    }

    @Override
    public void setCitationArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, CITATION$36);
    }

    @Override
    public void setCitationArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, CITATION$36, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewCitation(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(CITATION$36, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewCitation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(CITATION$36));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCitation(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CITATION$36, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getGroupList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GroupList
            extends AbstractList<CTEmpty> {
                GroupList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTSdtPrImpl.this.getGroupArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTSdtPrImpl.this.getGroupArray(n);
                    CTSdtPrImpl.this.setGroupArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTSdtPrImpl.this.insertNewGroup(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTSdtPrImpl.this.getGroupArray(n);
                    CTSdtPrImpl.this.removeGroup(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfGroupArray();
                }
            }
            return new GroupList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GROUP$38, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getGroupArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(GROUP$38, n));
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
    public int sizeOfGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GROUP$38);
        }
    }

    @Override
    public void setGroupArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, GROUP$38);
    }

    @Override
    public void setGroupArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, GROUP$38, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewGroup(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(GROUP$38, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(GROUP$38));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGroup(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GROUP$38, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmpty> getBibliographyList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BibliographyList
            extends AbstractList<CTEmpty> {
                BibliographyList() {
                }

                @Override
                public CTEmpty get(int n) {
                    return CTSdtPrImpl.this.getBibliographyArray(n);
                }

                @Override
                public CTEmpty set(int n, CTEmpty cTEmpty) {
                    CTEmpty cTEmpty2 = CTSdtPrImpl.this.getBibliographyArray(n);
                    CTSdtPrImpl.this.setBibliographyArray(n, cTEmpty);
                    return cTEmpty2;
                }

                @Override
                public void add(int n, CTEmpty cTEmpty) {
                    CTSdtPrImpl.this.insertNewBibliography(n).set(cTEmpty);
                }

                @Override
                public CTEmpty remove(int n) {
                    CTEmpty cTEmpty = CTSdtPrImpl.this.getBibliographyArray(n);
                    CTSdtPrImpl.this.removeBibliography(n);
                    return cTEmpty;
                }

                @Override
                public int size() {
                    return CTSdtPrImpl.this.sizeOfBibliographyArray();
                }
            }
            return new BibliographyList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmpty[] getBibliographyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BIBLIOGRAPHY$40, arrayList);
            CTEmpty[] cTEmptyArray = new CTEmpty[arrayList.size()];
            arrayList.toArray(cTEmptyArray);
            return cTEmptyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getBibliographyArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(BIBLIOGRAPHY$40, n));
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
    public int sizeOfBibliographyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BIBLIOGRAPHY$40);
        }
    }

    @Override
    public void setBibliographyArray(CTEmpty[] cTEmptyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmptyArray, BIBLIOGRAPHY$40);
    }

    @Override
    public void setBibliographyArray(int n, CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, BIBLIOGRAPHY$40, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty insertNewBibliography(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().insert_element_user(BIBLIOGRAPHY$40, n));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewBibliography() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(BIBLIOGRAPHY$40));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBibliography(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BIBLIOGRAPHY$40, n);
        }
    }
}

