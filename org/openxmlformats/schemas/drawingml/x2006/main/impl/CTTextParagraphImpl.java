/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class CTTextParagraphImpl
extends XmlComplexContentImpl
implements CTTextParagraph {
    private static final long serialVersionUID = 1L;
    private static final QName PPR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pPr");
    private static final QName R$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "r");
    private static final QName BR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "br");
    private static final QName FLD$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fld");
    private static final QName ENDPARARPR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "endParaRPr");

    public CTTextParagraphImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(PPR$0, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PPR$0) != 0;
        }
    }

    @Override
    public void setPPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, PPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(PPR$0));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PPR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRegularTextRun> getRList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RList
            extends AbstractList<CTRegularTextRun> {
                RList() {
                }

                @Override
                public CTRegularTextRun get(int n) {
                    return CTTextParagraphImpl.this.getRArray(n);
                }

                @Override
                public CTRegularTextRun set(int n, CTRegularTextRun cTRegularTextRun) {
                    CTRegularTextRun cTRegularTextRun2 = CTTextParagraphImpl.this.getRArray(n);
                    CTTextParagraphImpl.this.setRArray(n, cTRegularTextRun);
                    return cTRegularTextRun2;
                }

                @Override
                public void add(int n, CTRegularTextRun cTRegularTextRun) {
                    CTTextParagraphImpl.this.insertNewR(n).set(cTRegularTextRun);
                }

                @Override
                public CTRegularTextRun remove(int n) {
                    CTRegularTextRun cTRegularTextRun = CTTextParagraphImpl.this.getRArray(n);
                    CTTextParagraphImpl.this.removeR(n);
                    return cTRegularTextRun;
                }

                @Override
                public int size() {
                    return CTTextParagraphImpl.this.sizeOfRArray();
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
    public CTRegularTextRun[] getRArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(R$2, arrayList);
            CTRegularTextRun[] cTRegularTextRunArray = new CTRegularTextRun[arrayList.size()];
            arrayList.toArray(cTRegularTextRunArray);
            return cTRegularTextRunArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRegularTextRun getRArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRegularTextRun cTRegularTextRun = null;
            cTRegularTextRun = (CTRegularTextRun)((Object)this.get_store().find_element_user(R$2, n));
            if (cTRegularTextRun == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRegularTextRun;
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
            return this.get_store().count_elements(R$2);
        }
    }

    @Override
    public void setRArray(CTRegularTextRun[] cTRegularTextRunArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRegularTextRunArray, R$2);
    }

    @Override
    public void setRArray(int n, CTRegularTextRun cTRegularTextRun) {
        this.generatedSetterHelperImpl(cTRegularTextRun, R$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRegularTextRun insertNewR(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRegularTextRun cTRegularTextRun = null;
            cTRegularTextRun = (CTRegularTextRun)((Object)this.get_store().insert_element_user(R$2, n));
            return cTRegularTextRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRegularTextRun addNewR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRegularTextRun cTRegularTextRun = null;
            cTRegularTextRun = (CTRegularTextRun)((Object)this.get_store().add_element_user(R$2));
            return cTRegularTextRun;
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
            this.get_store().remove_element(R$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTextLineBreak> getBrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BrList
            extends AbstractList<CTTextLineBreak> {
                BrList() {
                }

                @Override
                public CTTextLineBreak get(int n) {
                    return CTTextParagraphImpl.this.getBrArray(n);
                }

                @Override
                public CTTextLineBreak set(int n, CTTextLineBreak cTTextLineBreak) {
                    CTTextLineBreak cTTextLineBreak2 = CTTextParagraphImpl.this.getBrArray(n);
                    CTTextParagraphImpl.this.setBrArray(n, cTTextLineBreak);
                    return cTTextLineBreak2;
                }

                @Override
                public void add(int n, CTTextLineBreak cTTextLineBreak) {
                    CTTextParagraphImpl.this.insertNewBr(n).set(cTTextLineBreak);
                }

                @Override
                public CTTextLineBreak remove(int n) {
                    CTTextLineBreak cTTextLineBreak = CTTextParagraphImpl.this.getBrArray(n);
                    CTTextParagraphImpl.this.removeBr(n);
                    return cTTextLineBreak;
                }

                @Override
                public int size() {
                    return CTTextParagraphImpl.this.sizeOfBrArray();
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
    public CTTextLineBreak[] getBrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BR$4, arrayList);
            CTTextLineBreak[] cTTextLineBreakArray = new CTTextLineBreak[arrayList.size()];
            arrayList.toArray(cTTextLineBreakArray);
            return cTTextLineBreakArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextLineBreak getBrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextLineBreak cTTextLineBreak = null;
            cTTextLineBreak = (CTTextLineBreak)((Object)this.get_store().find_element_user(BR$4, n));
            if (cTTextLineBreak == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTextLineBreak;
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
            return this.get_store().count_elements(BR$4);
        }
    }

    @Override
    public void setBrArray(CTTextLineBreak[] cTTextLineBreakArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTextLineBreakArray, BR$4);
    }

    @Override
    public void setBrArray(int n, CTTextLineBreak cTTextLineBreak) {
        this.generatedSetterHelperImpl(cTTextLineBreak, BR$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextLineBreak insertNewBr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextLineBreak cTTextLineBreak = null;
            cTTextLineBreak = (CTTextLineBreak)((Object)this.get_store().insert_element_user(BR$4, n));
            return cTTextLineBreak;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextLineBreak addNewBr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextLineBreak cTTextLineBreak = null;
            cTTextLineBreak = (CTTextLineBreak)((Object)this.get_store().add_element_user(BR$4));
            return cTTextLineBreak;
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
            this.get_store().remove_element(BR$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTextField> getFldList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FldList
            extends AbstractList<CTTextField> {
                FldList() {
                }

                @Override
                public CTTextField get(int n) {
                    return CTTextParagraphImpl.this.getFldArray(n);
                }

                @Override
                public CTTextField set(int n, CTTextField cTTextField) {
                    CTTextField cTTextField2 = CTTextParagraphImpl.this.getFldArray(n);
                    CTTextParagraphImpl.this.setFldArray(n, cTTextField);
                    return cTTextField2;
                }

                @Override
                public void add(int n, CTTextField cTTextField) {
                    CTTextParagraphImpl.this.insertNewFld(n).set(cTTextField);
                }

                @Override
                public CTTextField remove(int n) {
                    CTTextField cTTextField = CTTextParagraphImpl.this.getFldArray(n);
                    CTTextParagraphImpl.this.removeFld(n);
                    return cTTextField;
                }

                @Override
                public int size() {
                    return CTTextParagraphImpl.this.sizeOfFldArray();
                }
            }
            return new FldList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTextField[] getFldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FLD$6, arrayList);
            CTTextField[] cTTextFieldArray = new CTTextField[arrayList.size()];
            arrayList.toArray(cTTextFieldArray);
            return cTTextFieldArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextField getFldArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextField cTTextField = null;
            cTTextField = (CTTextField)((Object)this.get_store().find_element_user(FLD$6, n));
            if (cTTextField == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTextField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FLD$6);
        }
    }

    @Override
    public void setFldArray(CTTextField[] cTTextFieldArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTextFieldArray, FLD$6);
    }

    @Override
    public void setFldArray(int n, CTTextField cTTextField) {
        this.generatedSetterHelperImpl(cTTextField, FLD$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextField insertNewFld(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextField cTTextField = null;
            cTTextField = (CTTextField)((Object)this.get_store().insert_element_user(FLD$6, n));
            return cTTextField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextField addNewFld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextField cTTextField = null;
            cTTextField = (CTTextField)((Object)this.get_store().add_element_user(FLD$6));
            return cTTextField;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFld(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FLD$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextCharacterProperties getEndParaRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextCharacterProperties cTTextCharacterProperties = null;
            cTTextCharacterProperties = (CTTextCharacterProperties)((Object)this.get_store().find_element_user(ENDPARARPR$8, 0));
            if (cTTextCharacterProperties == null) {
                return null;
            }
            return cTTextCharacterProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEndParaRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENDPARARPR$8) != 0;
        }
    }

    @Override
    public void setEndParaRPr(CTTextCharacterProperties cTTextCharacterProperties) {
        this.generatedSetterHelperImpl(cTTextCharacterProperties, ENDPARARPR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextCharacterProperties addNewEndParaRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextCharacterProperties cTTextCharacterProperties = null;
            cTTextCharacterProperties = (CTTextCharacterProperties)((Object)this.get_store().add_element_user(ENDPARARPR$8));
            return cTTextCharacterProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEndParaRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENDPARARPR$8, 0);
        }
    }
}

