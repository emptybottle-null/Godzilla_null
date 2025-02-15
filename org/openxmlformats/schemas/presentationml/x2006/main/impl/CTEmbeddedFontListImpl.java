/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;

public class CTEmbeddedFontListImpl
extends XmlComplexContentImpl
implements CTEmbeddedFontList {
    private static final long serialVersionUID = 1L;
    private static final QName EMBEDDEDFONT$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "embeddedFont");

    public CTEmbeddedFontListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEmbeddedFontListEntry> getEmbeddedFontList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EmbeddedFontList
            extends AbstractList<CTEmbeddedFontListEntry> {
                EmbeddedFontList() {
                }

                @Override
                public CTEmbeddedFontListEntry get(int n) {
                    return CTEmbeddedFontListImpl.this.getEmbeddedFontArray(n);
                }

                @Override
                public CTEmbeddedFontListEntry set(int n, CTEmbeddedFontListEntry cTEmbeddedFontListEntry) {
                    CTEmbeddedFontListEntry cTEmbeddedFontListEntry2 = CTEmbeddedFontListImpl.this.getEmbeddedFontArray(n);
                    CTEmbeddedFontListImpl.this.setEmbeddedFontArray(n, cTEmbeddedFontListEntry);
                    return cTEmbeddedFontListEntry2;
                }

                @Override
                public void add(int n, CTEmbeddedFontListEntry cTEmbeddedFontListEntry) {
                    CTEmbeddedFontListImpl.this.insertNewEmbeddedFont(n).set(cTEmbeddedFontListEntry);
                }

                @Override
                public CTEmbeddedFontListEntry remove(int n) {
                    CTEmbeddedFontListEntry cTEmbeddedFontListEntry = CTEmbeddedFontListImpl.this.getEmbeddedFontArray(n);
                    CTEmbeddedFontListImpl.this.removeEmbeddedFont(n);
                    return cTEmbeddedFontListEntry;
                }

                @Override
                public int size() {
                    return CTEmbeddedFontListImpl.this.sizeOfEmbeddedFontArray();
                }
            }
            return new EmbeddedFontList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEmbeddedFontListEntry[] getEmbeddedFontArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EMBEDDEDFONT$0, arrayList);
            CTEmbeddedFontListEntry[] cTEmbeddedFontListEntryArray = new CTEmbeddedFontListEntry[arrayList.size()];
            arrayList.toArray(cTEmbeddedFontListEntryArray);
            return cTEmbeddedFontListEntryArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontListEntry getEmbeddedFontArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontListEntry cTEmbeddedFontListEntry = null;
            cTEmbeddedFontListEntry = (CTEmbeddedFontListEntry)((Object)this.get_store().find_element_user(EMBEDDEDFONT$0, n));
            if (cTEmbeddedFontListEntry == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEmbeddedFontListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfEmbeddedFontArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EMBEDDEDFONT$0);
        }
    }

    @Override
    public void setEmbeddedFontArray(CTEmbeddedFontListEntry[] cTEmbeddedFontListEntryArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEmbeddedFontListEntryArray, EMBEDDEDFONT$0);
    }

    @Override
    public void setEmbeddedFontArray(int n, CTEmbeddedFontListEntry cTEmbeddedFontListEntry) {
        this.generatedSetterHelperImpl(cTEmbeddedFontListEntry, EMBEDDEDFONT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontListEntry insertNewEmbeddedFont(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontListEntry cTEmbeddedFontListEntry = null;
            cTEmbeddedFontListEntry = (CTEmbeddedFontListEntry)((Object)this.get_store().insert_element_user(EMBEDDEDFONT$0, n));
            return cTEmbeddedFontListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontListEntry addNewEmbeddedFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontListEntry cTEmbeddedFontListEntry = null;
            cTEmbeddedFontListEntry = (CTEmbeddedFontListEntry)((Object)this.get_store().add_element_user(EMBEDDEDFONT$0));
            return cTEmbeddedFontListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEmbeddedFont(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EMBEDDEDFONT$0, n);
        }
    }
}

