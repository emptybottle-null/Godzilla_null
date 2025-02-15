/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.CTF;
import com.microsoft.schemas.vml.CTFormulas;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTFormulasImpl
extends XmlComplexContentImpl
implements CTFormulas {
    private static final long serialVersionUID = 1L;
    private static final QName F$0 = new QName("urn:schemas-microsoft-com:vml", "f");

    public CTFormulasImpl(SchemaType schemaType) {
        super(schemaType);
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
                    return CTFormulasImpl.this.getFArray(n);
                }

                @Override
                public CTF set(int n, CTF cTF) {
                    CTF cTF2 = CTFormulasImpl.this.getFArray(n);
                    CTFormulasImpl.this.setFArray(n, cTF);
                    return cTF2;
                }

                @Override
                public void add(int n, CTF cTF) {
                    CTFormulasImpl.this.insertNewF(n).set(cTF);
                }

                @Override
                public CTF remove(int n) {
                    CTF cTF = CTFormulasImpl.this.getFArray(n);
                    CTFormulasImpl.this.removeF(n);
                    return cTF;
                }

                @Override
                public int size() {
                    return CTFormulasImpl.this.sizeOfFArray();
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
            this.get_store().find_all_element_users(F$0, arrayList);
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
            cTF = (CTF)((Object)this.get_store().find_element_user(F$0, n));
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
            return this.get_store().count_elements(F$0);
        }
    }

    @Override
    public void setFArray(CTF[] cTFArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFArray, F$0);
    }

    @Override
    public void setFArray(int n, CTF cTF) {
        this.generatedSetterHelperImpl(cTF, F$0, n, (short)2);
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
            cTF = (CTF)((Object)this.get_store().insert_element_user(F$0, n));
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
            cTF = (CTF)((Object)this.get_store().add_element_user(F$0));
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
            this.get_store().remove_element(F$0, n);
        }
    }
}

