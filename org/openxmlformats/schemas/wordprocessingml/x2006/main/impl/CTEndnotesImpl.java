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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;

public class CTEndnotesImpl
extends XmlComplexContentImpl
implements CTEndnotes {
    private static final long serialVersionUID = 1L;
    private static final QName ENDNOTE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "endnote");

    public CTEndnotesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFtnEdn> getEndnoteList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EndnoteList
            extends AbstractList<CTFtnEdn> {
                EndnoteList() {
                }

                @Override
                public CTFtnEdn get(int n) {
                    return CTEndnotesImpl.this.getEndnoteArray(n);
                }

                @Override
                public CTFtnEdn set(int n, CTFtnEdn cTFtnEdn) {
                    CTFtnEdn cTFtnEdn2 = CTEndnotesImpl.this.getEndnoteArray(n);
                    CTEndnotesImpl.this.setEndnoteArray(n, cTFtnEdn);
                    return cTFtnEdn2;
                }

                @Override
                public void add(int n, CTFtnEdn cTFtnEdn) {
                    CTEndnotesImpl.this.insertNewEndnote(n).set(cTFtnEdn);
                }

                @Override
                public CTFtnEdn remove(int n) {
                    CTFtnEdn cTFtnEdn = CTEndnotesImpl.this.getEndnoteArray(n);
                    CTEndnotesImpl.this.removeEndnote(n);
                    return cTFtnEdn;
                }

                @Override
                public int size() {
                    return CTEndnotesImpl.this.sizeOfEndnoteArray();
                }
            }
            return new EndnoteList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFtnEdn[] getEndnoteArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ENDNOTE$0, arrayList);
            CTFtnEdn[] cTFtnEdnArray = new CTFtnEdn[arrayList.size()];
            arrayList.toArray(cTFtnEdnArray);
            return cTFtnEdnArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdn getEndnoteArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdn cTFtnEdn = null;
            cTFtnEdn = (CTFtnEdn)((Object)this.get_store().find_element_user(ENDNOTE$0, n));
            if (cTFtnEdn == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFtnEdn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfEndnoteArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENDNOTE$0);
        }
    }

    @Override
    public void setEndnoteArray(CTFtnEdn[] cTFtnEdnArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFtnEdnArray, ENDNOTE$0);
    }

    @Override
    public void setEndnoteArray(int n, CTFtnEdn cTFtnEdn) {
        this.generatedSetterHelperImpl(cTFtnEdn, ENDNOTE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdn insertNewEndnote(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdn cTFtnEdn = null;
            cTFtnEdn = (CTFtnEdn)((Object)this.get_store().insert_element_user(ENDNOTE$0, n));
            return cTFtnEdn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdn addNewEndnote() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdn cTFtnEdn = null;
            cTFtnEdn = (CTFtnEdn)((Object)this.get_store().add_element_user(ENDNOTE$0));
            return cTFtnEdn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEndnote(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENDNOTE$0, n);
        }
    }
}

