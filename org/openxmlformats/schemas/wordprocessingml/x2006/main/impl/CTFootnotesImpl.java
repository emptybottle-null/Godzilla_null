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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;

public class CTFootnotesImpl
extends XmlComplexContentImpl
implements CTFootnotes {
    private static final long serialVersionUID = 1L;
    private static final QName FOOTNOTE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnote");

    public CTFootnotesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFtnEdn> getFootnoteList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FootnoteList
            extends AbstractList<CTFtnEdn> {
                FootnoteList() {
                }

                @Override
                public CTFtnEdn get(int n) {
                    return CTFootnotesImpl.this.getFootnoteArray(n);
                }

                @Override
                public CTFtnEdn set(int n, CTFtnEdn cTFtnEdn) {
                    CTFtnEdn cTFtnEdn2 = CTFootnotesImpl.this.getFootnoteArray(n);
                    CTFootnotesImpl.this.setFootnoteArray(n, cTFtnEdn);
                    return cTFtnEdn2;
                }

                @Override
                public void add(int n, CTFtnEdn cTFtnEdn) {
                    CTFootnotesImpl.this.insertNewFootnote(n).set(cTFtnEdn);
                }

                @Override
                public CTFtnEdn remove(int n) {
                    CTFtnEdn cTFtnEdn = CTFootnotesImpl.this.getFootnoteArray(n);
                    CTFootnotesImpl.this.removeFootnote(n);
                    return cTFtnEdn;
                }

                @Override
                public int size() {
                    return CTFootnotesImpl.this.sizeOfFootnoteArray();
                }
            }
            return new FootnoteList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFtnEdn[] getFootnoteArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FOOTNOTE$0, arrayList);
            CTFtnEdn[] cTFtnEdnArray = new CTFtnEdn[arrayList.size()];
            arrayList.toArray(cTFtnEdnArray);
            return cTFtnEdnArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdn getFootnoteArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdn cTFtnEdn = null;
            cTFtnEdn = (CTFtnEdn)((Object)this.get_store().find_element_user(FOOTNOTE$0, n));
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
    public int sizeOfFootnoteArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FOOTNOTE$0);
        }
    }

    @Override
    public void setFootnoteArray(CTFtnEdn[] cTFtnEdnArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFtnEdnArray, FOOTNOTE$0);
    }

    @Override
    public void setFootnoteArray(int n, CTFtnEdn cTFtnEdn) {
        this.generatedSetterHelperImpl(cTFtnEdn, FOOTNOTE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdn insertNewFootnote(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdn cTFtnEdn = null;
            cTFtnEdn = (CTFtnEdn)((Object)this.get_store().insert_element_user(FOOTNOTE$0, n));
            return cTFtnEdn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnEdn addNewFootnote() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnEdn cTFtnEdn = null;
            cTFtnEdn = (CTFtnEdn)((Object)this.get_store().add_element_user(FOOTNOTE$0));
            return cTFtnEdn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFootnote(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FOOTNOTE$0, n);
        }
    }
}

