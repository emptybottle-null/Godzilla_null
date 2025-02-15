/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtEndPr;

public class CTSdtEndPrImpl
extends XmlComplexContentImpl
implements CTSdtEndPr {
    private static final long serialVersionUID = 1L;
    private static final QName RPR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr");

    public CTSdtEndPrImpl(SchemaType schemaType) {
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
                    return CTSdtEndPrImpl.this.getRPrArray(n);
                }

                @Override
                public CTRPr set(int n, CTRPr cTRPr) {
                    CTRPr cTRPr2 = CTSdtEndPrImpl.this.getRPrArray(n);
                    CTSdtEndPrImpl.this.setRPrArray(n, cTRPr);
                    return cTRPr2;
                }

                @Override
                public void add(int n, CTRPr cTRPr) {
                    CTSdtEndPrImpl.this.insertNewRPr(n).set(cTRPr);
                }

                @Override
                public CTRPr remove(int n) {
                    CTRPr cTRPr = CTSdtEndPrImpl.this.getRPrArray(n);
                    CTSdtEndPrImpl.this.removeRPr(n);
                    return cTRPr;
                }

                @Override
                public int size() {
                    return CTSdtEndPrImpl.this.sizeOfRPrArray();
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
}

