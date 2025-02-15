/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;

public class CTRstImpl
extends XmlComplexContentImpl
implements CTRst {
    private static final long serialVersionUID = 1L;
    private static final QName T$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "t");
    private static final QName R$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "r");
    private static final QName RPH$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "rPh");
    private static final QName PHONETICPR$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "phoneticPr");

    public CTRstImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(T$0, 0));
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
    public STXstring xgetT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring = null;
            sTXstring = (STXstring)((Object)this.get_store().find_element_user(T$0, 0));
            return sTXstring;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(T$0) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setT(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(T$0, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(T$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetT(STXstring sTXstring) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring2 = null;
            sTXstring2 = (STXstring)((Object)this.get_store().find_element_user(T$0, 0));
            if (sTXstring2 == null) {
                sTXstring2 = (STXstring)((Object)this.get_store().add_element_user(T$0));
            }
            sTXstring2.set(sTXstring);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(T$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRElt> getRList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RList
            extends AbstractList<CTRElt> {
                RList() {
                }

                @Override
                public CTRElt get(int n) {
                    return CTRstImpl.this.getRArray(n);
                }

                @Override
                public CTRElt set(int n, CTRElt cTRElt) {
                    CTRElt cTRElt2 = CTRstImpl.this.getRArray(n);
                    CTRstImpl.this.setRArray(n, cTRElt);
                    return cTRElt2;
                }

                @Override
                public void add(int n, CTRElt cTRElt) {
                    CTRstImpl.this.insertNewR(n).set(cTRElt);
                }

                @Override
                public CTRElt remove(int n) {
                    CTRElt cTRElt = CTRstImpl.this.getRArray(n);
                    CTRstImpl.this.removeR(n);
                    return cTRElt;
                }

                @Override
                public int size() {
                    return CTRstImpl.this.sizeOfRArray();
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
    public CTRElt[] getRArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(R$2, arrayList);
            CTRElt[] cTREltArray = new CTRElt[arrayList.size()];
            arrayList.toArray(cTREltArray);
            return cTREltArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRElt getRArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRElt cTRElt = null;
            cTRElt = (CTRElt)((Object)this.get_store().find_element_user(R$2, n));
            if (cTRElt == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRElt;
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
    public void setRArray(CTRElt[] cTREltArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTREltArray, R$2);
    }

    @Override
    public void setRArray(int n, CTRElt cTRElt) {
        this.generatedSetterHelperImpl(cTRElt, R$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRElt insertNewR(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRElt cTRElt = null;
            cTRElt = (CTRElt)((Object)this.get_store().insert_element_user(R$2, n));
            return cTRElt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRElt addNewR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRElt cTRElt = null;
            cTRElt = (CTRElt)((Object)this.get_store().add_element_user(R$2));
            return cTRElt;
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
    public List<CTPhoneticRun> getRPhList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RPhList
            extends AbstractList<CTPhoneticRun> {
                RPhList() {
                }

                @Override
                public CTPhoneticRun get(int n) {
                    return CTRstImpl.this.getRPhArray(n);
                }

                @Override
                public CTPhoneticRun set(int n, CTPhoneticRun cTPhoneticRun) {
                    CTPhoneticRun cTPhoneticRun2 = CTRstImpl.this.getRPhArray(n);
                    CTRstImpl.this.setRPhArray(n, cTPhoneticRun);
                    return cTPhoneticRun2;
                }

                @Override
                public void add(int n, CTPhoneticRun cTPhoneticRun) {
                    CTRstImpl.this.insertNewRPh(n).set(cTPhoneticRun);
                }

                @Override
                public CTPhoneticRun remove(int n) {
                    CTPhoneticRun cTPhoneticRun = CTRstImpl.this.getRPhArray(n);
                    CTRstImpl.this.removeRPh(n);
                    return cTPhoneticRun;
                }

                @Override
                public int size() {
                    return CTRstImpl.this.sizeOfRPhArray();
                }
            }
            return new RPhList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPhoneticRun[] getRPhArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RPH$4, arrayList);
            CTPhoneticRun[] cTPhoneticRunArray = new CTPhoneticRun[arrayList.size()];
            arrayList.toArray(cTPhoneticRunArray);
            return cTPhoneticRunArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhoneticRun getRPhArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhoneticRun cTPhoneticRun = null;
            cTPhoneticRun = (CTPhoneticRun)((Object)this.get_store().find_element_user(RPH$4, n));
            if (cTPhoneticRun == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPhoneticRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRPhArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RPH$4);
        }
    }

    @Override
    public void setRPhArray(CTPhoneticRun[] cTPhoneticRunArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPhoneticRunArray, RPH$4);
    }

    @Override
    public void setRPhArray(int n, CTPhoneticRun cTPhoneticRun) {
        this.generatedSetterHelperImpl(cTPhoneticRun, RPH$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhoneticRun insertNewRPh(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhoneticRun cTPhoneticRun = null;
            cTPhoneticRun = (CTPhoneticRun)((Object)this.get_store().insert_element_user(RPH$4, n));
            return cTPhoneticRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhoneticRun addNewRPh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhoneticRun cTPhoneticRun = null;
            cTPhoneticRun = (CTPhoneticRun)((Object)this.get_store().add_element_user(RPH$4));
            return cTPhoneticRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRPh(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RPH$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhoneticPr getPhoneticPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhoneticPr cTPhoneticPr = null;
            cTPhoneticPr = (CTPhoneticPr)((Object)this.get_store().find_element_user(PHONETICPR$6, 0));
            if (cTPhoneticPr == null) {
                return null;
            }
            return cTPhoneticPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPhoneticPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PHONETICPR$6) != 0;
        }
    }

    @Override
    public void setPhoneticPr(CTPhoneticPr cTPhoneticPr) {
        this.generatedSetterHelperImpl(cTPhoneticPr, PHONETICPR$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhoneticPr addNewPhoneticPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhoneticPr cTPhoneticPr = null;
            cTPhoneticPr = (CTPhoneticPr)((Object)this.get_store().add_element_user(PHONETICPR$6));
            return cTPhoneticPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPhoneticPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PHONETICPR$6, 0);
        }
    }
}

