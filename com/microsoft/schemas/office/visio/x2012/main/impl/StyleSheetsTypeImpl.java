/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.StyleSheetType;
import com.microsoft.schemas.office.visio.x2012.main.StyleSheetsType;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class StyleSheetsTypeImpl
extends XmlComplexContentImpl
implements StyleSheetsType {
    private static final long serialVersionUID = 1L;
    private static final QName STYLESHEET$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "StyleSheet");

    public StyleSheetsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<StyleSheetType> getStyleSheetList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class StyleSheetList
            extends AbstractList<StyleSheetType> {
                StyleSheetList() {
                }

                @Override
                public StyleSheetType get(int n) {
                    return StyleSheetsTypeImpl.this.getStyleSheetArray(n);
                }

                @Override
                public StyleSheetType set(int n, StyleSheetType styleSheetType) {
                    StyleSheetType styleSheetType2 = StyleSheetsTypeImpl.this.getStyleSheetArray(n);
                    StyleSheetsTypeImpl.this.setStyleSheetArray(n, styleSheetType);
                    return styleSheetType2;
                }

                @Override
                public void add(int n, StyleSheetType styleSheetType) {
                    StyleSheetsTypeImpl.this.insertNewStyleSheet(n).set(styleSheetType);
                }

                @Override
                public StyleSheetType remove(int n) {
                    StyleSheetType styleSheetType = StyleSheetsTypeImpl.this.getStyleSheetArray(n);
                    StyleSheetsTypeImpl.this.removeStyleSheet(n);
                    return styleSheetType;
                }

                @Override
                public int size() {
                    return StyleSheetsTypeImpl.this.sizeOfStyleSheetArray();
                }
            }
            return new StyleSheetList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public StyleSheetType[] getStyleSheetArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(STYLESHEET$0, arrayList);
            StyleSheetType[] styleSheetTypeArray = new StyleSheetType[arrayList.size()];
            arrayList.toArray(styleSheetTypeArray);
            return styleSheetTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public StyleSheetType getStyleSheetArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            StyleSheetType styleSheetType = null;
            styleSheetType = (StyleSheetType)((Object)this.get_store().find_element_user(STYLESHEET$0, n));
            if (styleSheetType == null) {
                throw new IndexOutOfBoundsException();
            }
            return styleSheetType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfStyleSheetArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STYLESHEET$0);
        }
    }

    @Override
    public void setStyleSheetArray(StyleSheetType[] styleSheetTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(styleSheetTypeArray, STYLESHEET$0);
    }

    @Override
    public void setStyleSheetArray(int n, StyleSheetType styleSheetType) {
        this.generatedSetterHelperImpl(styleSheetType, STYLESHEET$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public StyleSheetType insertNewStyleSheet(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            StyleSheetType styleSheetType = null;
            styleSheetType = (StyleSheetType)((Object)this.get_store().insert_element_user(STYLESHEET$0, n));
            return styleSheetType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public StyleSheetType addNewStyleSheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            StyleSheetType styleSheetType = null;
            styleSheetType = (StyleSheetType)((Object)this.get_store().add_element_user(STYLESHEET$0));
            return styleSheetType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeStyleSheet(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STYLESHEET$0, n);
        }
    }
}

