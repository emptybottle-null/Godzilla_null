/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.CpType
 *  com.microsoft.schemas.office.visio.x2012.main.FldType
 *  com.microsoft.schemas.office.visio.x2012.main.PpType
 *  com.microsoft.schemas.office.visio.x2012.main.TpType
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.CpType;
import com.microsoft.schemas.office.visio.x2012.main.FldType;
import com.microsoft.schemas.office.visio.x2012.main.PpType;
import com.microsoft.schemas.office.visio.x2012.main.TextType;
import com.microsoft.schemas.office.visio.x2012.main.TpType;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class TextTypeImpl
extends XmlComplexContentImpl
implements TextType {
    private static final long serialVersionUID = 1L;
    private static final QName CP$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "cp");
    private static final QName PP$2 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "pp");
    private static final QName TP$4 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "tp");
    private static final QName FLD$6 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "fld");

    public TextTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CpType> getCpList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CpList
            extends AbstractList<CpType> {
                CpList() {
                }

                @Override
                public CpType get(int n) {
                    return TextTypeImpl.this.getCpArray(n);
                }

                @Override
                public CpType set(int n, CpType cpType) {
                    CpType cpType2 = TextTypeImpl.this.getCpArray(n);
                    TextTypeImpl.this.setCpArray(n, cpType);
                    return cpType2;
                }

                @Override
                public void add(int n, CpType cpType) {
                    TextTypeImpl.this.insertNewCp(n).set((XmlObject)cpType);
                }

                @Override
                public CpType remove(int n) {
                    CpType cpType = TextTypeImpl.this.getCpArray(n);
                    TextTypeImpl.this.removeCp(n);
                    return cpType;
                }

                @Override
                public int size() {
                    return TextTypeImpl.this.sizeOfCpArray();
                }
            }
            return new CpList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CpType[] getCpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CP$0, arrayList);
            CpType[] cpTypeArray = new CpType[arrayList.size()];
            arrayList.toArray(cpTypeArray);
            return cpTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CpType getCpArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CpType cpType = null;
            cpType = (CpType)this.get_store().find_element_user(CP$0, n);
            if (cpType == null) {
                throw new IndexOutOfBoundsException();
            }
            return cpType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CP$0);
        }
    }

    @Override
    public void setCpArray(CpType[] cpTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cpTypeArray, CP$0);
    }

    @Override
    public void setCpArray(int n, CpType cpType) {
        this.generatedSetterHelperImpl((XmlObject)cpType, CP$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CpType insertNewCp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CpType cpType = null;
            cpType = (CpType)this.get_store().insert_element_user(CP$0, n);
            return cpType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CpType addNewCp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CpType cpType = null;
            cpType = (CpType)this.get_store().add_element_user(CP$0);
            return cpType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CP$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<PpType> getPpList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PpList
            extends AbstractList<PpType> {
                PpList() {
                }

                @Override
                public PpType get(int n) {
                    return TextTypeImpl.this.getPpArray(n);
                }

                @Override
                public PpType set(int n, PpType ppType) {
                    PpType ppType2 = TextTypeImpl.this.getPpArray(n);
                    TextTypeImpl.this.setPpArray(n, ppType);
                    return ppType2;
                }

                @Override
                public void add(int n, PpType ppType) {
                    TextTypeImpl.this.insertNewPp(n).set((XmlObject)ppType);
                }

                @Override
                public PpType remove(int n) {
                    PpType ppType = TextTypeImpl.this.getPpArray(n);
                    TextTypeImpl.this.removePp(n);
                    return ppType;
                }

                @Override
                public int size() {
                    return TextTypeImpl.this.sizeOfPpArray();
                }
            }
            return new PpList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public PpType[] getPpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PP$2, arrayList);
            PpType[] ppTypeArray = new PpType[arrayList.size()];
            arrayList.toArray(ppTypeArray);
            return ppTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PpType getPpArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PpType ppType = null;
            ppType = (PpType)this.get_store().find_element_user(PP$2, n);
            if (ppType == null) {
                throw new IndexOutOfBoundsException();
            }
            return ppType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PP$2);
        }
    }

    @Override
    public void setPpArray(PpType[] ppTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])ppTypeArray, PP$2);
    }

    @Override
    public void setPpArray(int n, PpType ppType) {
        this.generatedSetterHelperImpl((XmlObject)ppType, PP$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PpType insertNewPp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PpType ppType = null;
            ppType = (PpType)this.get_store().insert_element_user(PP$2, n);
            return ppType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PpType addNewPp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PpType ppType = null;
            ppType = (PpType)this.get_store().add_element_user(PP$2);
            return ppType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PP$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<TpType> getTpList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TpList
            extends AbstractList<TpType> {
                TpList() {
                }

                @Override
                public TpType get(int n) {
                    return TextTypeImpl.this.getTpArray(n);
                }

                @Override
                public TpType set(int n, TpType tpType) {
                    TpType tpType2 = TextTypeImpl.this.getTpArray(n);
                    TextTypeImpl.this.setTpArray(n, tpType);
                    return tpType2;
                }

                @Override
                public void add(int n, TpType tpType) {
                    TextTypeImpl.this.insertNewTp(n).set((XmlObject)tpType);
                }

                @Override
                public TpType remove(int n) {
                    TpType tpType = TextTypeImpl.this.getTpArray(n);
                    TextTypeImpl.this.removeTp(n);
                    return tpType;
                }

                @Override
                public int size() {
                    return TextTypeImpl.this.sizeOfTpArray();
                }
            }
            return new TpList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public TpType[] getTpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TP$4, arrayList);
            TpType[] tpTypeArray = new TpType[arrayList.size()];
            arrayList.toArray(tpTypeArray);
            return tpTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TpType getTpArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TpType tpType = null;
            tpType = (TpType)this.get_store().find_element_user(TP$4, n);
            if (tpType == null) {
                throw new IndexOutOfBoundsException();
            }
            return tpType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TP$4);
        }
    }

    @Override
    public void setTpArray(TpType[] tpTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])tpTypeArray, TP$4);
    }

    @Override
    public void setTpArray(int n, TpType tpType) {
        this.generatedSetterHelperImpl((XmlObject)tpType, TP$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TpType insertNewTp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TpType tpType = null;
            tpType = (TpType)this.get_store().insert_element_user(TP$4, n);
            return tpType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TpType addNewTp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TpType tpType = null;
            tpType = (TpType)this.get_store().add_element_user(TP$4);
            return tpType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TP$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<FldType> getFldList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FldList
            extends AbstractList<FldType> {
                FldList() {
                }

                @Override
                public FldType get(int n) {
                    return TextTypeImpl.this.getFldArray(n);
                }

                @Override
                public FldType set(int n, FldType fldType) {
                    FldType fldType2 = TextTypeImpl.this.getFldArray(n);
                    TextTypeImpl.this.setFldArray(n, fldType);
                    return fldType2;
                }

                @Override
                public void add(int n, FldType fldType) {
                    TextTypeImpl.this.insertNewFld(n).set((XmlObject)fldType);
                }

                @Override
                public FldType remove(int n) {
                    FldType fldType = TextTypeImpl.this.getFldArray(n);
                    TextTypeImpl.this.removeFld(n);
                    return fldType;
                }

                @Override
                public int size() {
                    return TextTypeImpl.this.sizeOfFldArray();
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
    public FldType[] getFldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FLD$6, arrayList);
            FldType[] fldTypeArray = new FldType[arrayList.size()];
            arrayList.toArray(fldTypeArray);
            return fldTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FldType getFldArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FldType fldType = null;
            fldType = (FldType)this.get_store().find_element_user(FLD$6, n);
            if (fldType == null) {
                throw new IndexOutOfBoundsException();
            }
            return fldType;
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
    public void setFldArray(FldType[] fldTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])fldTypeArray, FLD$6);
    }

    @Override
    public void setFldArray(int n, FldType fldType) {
        this.generatedSetterHelperImpl((XmlObject)fldType, FLD$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FldType insertNewFld(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FldType fldType = null;
            fldType = (FldType)this.get_store().insert_element_user(FLD$6, n);
            return fldType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FldType addNewFld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FldType fldType = null;
            fldType = (FldType)this.get_store().add_element_user(FLD$6);
            return fldType;
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
}

