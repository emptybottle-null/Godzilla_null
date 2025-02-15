/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlinks;

public class CTHyperlinksImpl
extends XmlComplexContentImpl
implements CTHyperlinks {
    private static final long serialVersionUID = 1L;
    private static final QName HYPERLINK$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "hyperlink");

    public CTHyperlinksImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTHyperlink> getHyperlinkList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HyperlinkList
            extends AbstractList<CTHyperlink> {
                HyperlinkList() {
                }

                @Override
                public CTHyperlink get(int n) {
                    return CTHyperlinksImpl.this.getHyperlinkArray(n);
                }

                @Override
                public CTHyperlink set(int n, CTHyperlink cTHyperlink) {
                    CTHyperlink cTHyperlink2 = CTHyperlinksImpl.this.getHyperlinkArray(n);
                    CTHyperlinksImpl.this.setHyperlinkArray(n, cTHyperlink);
                    return cTHyperlink2;
                }

                @Override
                public void add(int n, CTHyperlink cTHyperlink) {
                    CTHyperlinksImpl.this.insertNewHyperlink(n).set(cTHyperlink);
                }

                @Override
                public CTHyperlink remove(int n) {
                    CTHyperlink cTHyperlink = CTHyperlinksImpl.this.getHyperlinkArray(n);
                    CTHyperlinksImpl.this.removeHyperlink(n);
                    return cTHyperlink;
                }

                @Override
                public int size() {
                    return CTHyperlinksImpl.this.sizeOfHyperlinkArray();
                }
            }
            return new HyperlinkList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTHyperlink[] getHyperlinkArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HYPERLINK$0, arrayList);
            CTHyperlink[] cTHyperlinkArray = new CTHyperlink[arrayList.size()];
            arrayList.toArray(cTHyperlinkArray);
            return cTHyperlinkArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHyperlink getHyperlinkArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHyperlink cTHyperlink = null;
            cTHyperlink = (CTHyperlink)((Object)this.get_store().find_element_user(HYPERLINK$0, n));
            if (cTHyperlink == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTHyperlink;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHyperlinkArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HYPERLINK$0);
        }
    }

    @Override
    public void setHyperlinkArray(CTHyperlink[] cTHyperlinkArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTHyperlinkArray, HYPERLINK$0);
    }

    @Override
    public void setHyperlinkArray(int n, CTHyperlink cTHyperlink) {
        this.generatedSetterHelperImpl(cTHyperlink, HYPERLINK$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHyperlink insertNewHyperlink(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHyperlink cTHyperlink = null;
            cTHyperlink = (CTHyperlink)((Object)this.get_store().insert_element_user(HYPERLINK$0, n));
            return cTHyperlink;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHyperlink addNewHyperlink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHyperlink cTHyperlink = null;
            cTHyperlink = (CTHyperlink)((Object)this.get_store().add_element_user(HYPERLINK$0));
            return cTHyperlink;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHyperlink(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HYPERLINK$0, n);
        }
    }
}

