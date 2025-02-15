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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCache;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches;

public class CTPivotCachesImpl
extends XmlComplexContentImpl
implements CTPivotCaches {
    private static final long serialVersionUID = 1L;
    private static final QName PIVOTCACHE$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "pivotCache");

    public CTPivotCachesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPivotCache> getPivotCacheList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PivotCacheList
            extends AbstractList<CTPivotCache> {
                PivotCacheList() {
                }

                @Override
                public CTPivotCache get(int n) {
                    return CTPivotCachesImpl.this.getPivotCacheArray(n);
                }

                @Override
                public CTPivotCache set(int n, CTPivotCache cTPivotCache) {
                    CTPivotCache cTPivotCache2 = CTPivotCachesImpl.this.getPivotCacheArray(n);
                    CTPivotCachesImpl.this.setPivotCacheArray(n, cTPivotCache);
                    return cTPivotCache2;
                }

                @Override
                public void add(int n, CTPivotCache cTPivotCache) {
                    CTPivotCachesImpl.this.insertNewPivotCache(n).set(cTPivotCache);
                }

                @Override
                public CTPivotCache remove(int n) {
                    CTPivotCache cTPivotCache = CTPivotCachesImpl.this.getPivotCacheArray(n);
                    CTPivotCachesImpl.this.removePivotCache(n);
                    return cTPivotCache;
                }

                @Override
                public int size() {
                    return CTPivotCachesImpl.this.sizeOfPivotCacheArray();
                }
            }
            return new PivotCacheList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPivotCache[] getPivotCacheArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PIVOTCACHE$0, arrayList);
            CTPivotCache[] cTPivotCacheArray = new CTPivotCache[arrayList.size()];
            arrayList.toArray(cTPivotCacheArray);
            return cTPivotCacheArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotCache getPivotCacheArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotCache cTPivotCache = null;
            cTPivotCache = (CTPivotCache)((Object)this.get_store().find_element_user(PIVOTCACHE$0, n));
            if (cTPivotCache == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPivotCache;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPivotCacheArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PIVOTCACHE$0);
        }
    }

    @Override
    public void setPivotCacheArray(CTPivotCache[] cTPivotCacheArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPivotCacheArray, PIVOTCACHE$0);
    }

    @Override
    public void setPivotCacheArray(int n, CTPivotCache cTPivotCache) {
        this.generatedSetterHelperImpl(cTPivotCache, PIVOTCACHE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotCache insertNewPivotCache(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotCache cTPivotCache = null;
            cTPivotCache = (CTPivotCache)((Object)this.get_store().insert_element_user(PIVOTCACHE$0, n));
            return cTPivotCache;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotCache addNewPivotCache() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotCache cTPivotCache = null;
            cTPivotCache = (CTPivotCache)((Object)this.get_store().add_element_user(PIVOTCACHE$0));
            return cTPivotCache;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePivotCache(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PIVOTCACHE$0, n);
        }
    }
}

