/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.PageType;
import com.microsoft.schemas.office.visio.x2012.main.PagesType;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class PagesTypeImpl
extends XmlComplexContentImpl
implements PagesType {
    private static final long serialVersionUID = 1L;
    private static final QName PAGE$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Page");

    public PagesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<PageType> getPageList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PageList
            extends AbstractList<PageType> {
                PageList() {
                }

                @Override
                public PageType get(int n) {
                    return PagesTypeImpl.this.getPageArray(n);
                }

                @Override
                public PageType set(int n, PageType pageType) {
                    PageType pageType2 = PagesTypeImpl.this.getPageArray(n);
                    PagesTypeImpl.this.setPageArray(n, pageType);
                    return pageType2;
                }

                @Override
                public void add(int n, PageType pageType) {
                    PagesTypeImpl.this.insertNewPage(n).set(pageType);
                }

                @Override
                public PageType remove(int n) {
                    PageType pageType = PagesTypeImpl.this.getPageArray(n);
                    PagesTypeImpl.this.removePage(n);
                    return pageType;
                }

                @Override
                public int size() {
                    return PagesTypeImpl.this.sizeOfPageArray();
                }
            }
            return new PageList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public PageType[] getPageArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PAGE$0, arrayList);
            PageType[] pageTypeArray = new PageType[arrayList.size()];
            arrayList.toArray(pageTypeArray);
            return pageTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PageType getPageArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PageType pageType = null;
            pageType = (PageType)((Object)this.get_store().find_element_user(PAGE$0, n));
            if (pageType == null) {
                throw new IndexOutOfBoundsException();
            }
            return pageType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPageArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PAGE$0);
        }
    }

    @Override
    public void setPageArray(PageType[] pageTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(pageTypeArray, PAGE$0);
    }

    @Override
    public void setPageArray(int n, PageType pageType) {
        this.generatedSetterHelperImpl(pageType, PAGE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PageType insertNewPage(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PageType pageType = null;
            pageType = (PageType)((Object)this.get_store().insert_element_user(PAGE$0, n));
            return pageType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PageType addNewPage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PageType pageType = null;
            pageType = (PageType)((Object)this.get_store().add_element_user(PAGE$0));
            return pageType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePage(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PAGE$0, n);
        }
    }
}

