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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLatentStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;

public class CTStylesImpl
extends XmlComplexContentImpl
implements CTStyles {
    private static final long serialVersionUID = 1L;
    private static final QName DOCDEFAULTS$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docDefaults");
    private static final QName LATENTSTYLES$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "latentStyles");
    private static final QName STYLE$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "style");

    public CTStylesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocDefaults getDocDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocDefaults cTDocDefaults = null;
            cTDocDefaults = (CTDocDefaults)((Object)this.get_store().find_element_user(DOCDEFAULTS$0, 0));
            if (cTDocDefaults == null) {
                return null;
            }
            return cTDocDefaults;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDocDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCDEFAULTS$0) != 0;
        }
    }

    @Override
    public void setDocDefaults(CTDocDefaults cTDocDefaults) {
        this.generatedSetterHelperImpl(cTDocDefaults, DOCDEFAULTS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocDefaults addNewDocDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocDefaults cTDocDefaults = null;
            cTDocDefaults = (CTDocDefaults)((Object)this.get_store().add_element_user(DOCDEFAULTS$0));
            return cTDocDefaults;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDocDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCDEFAULTS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLatentStyles getLatentStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLatentStyles cTLatentStyles = null;
            cTLatentStyles = (CTLatentStyles)((Object)this.get_store().find_element_user(LATENTSTYLES$2, 0));
            if (cTLatentStyles == null) {
                return null;
            }
            return cTLatentStyles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLatentStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LATENTSTYLES$2) != 0;
        }
    }

    @Override
    public void setLatentStyles(CTLatentStyles cTLatentStyles) {
        this.generatedSetterHelperImpl(cTLatentStyles, LATENTSTYLES$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLatentStyles addNewLatentStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLatentStyles cTLatentStyles = null;
            cTLatentStyles = (CTLatentStyles)((Object)this.get_store().add_element_user(LATENTSTYLES$2));
            return cTLatentStyles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLatentStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LATENTSTYLES$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTStyle> getStyleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class StyleList
            extends AbstractList<CTStyle> {
                StyleList() {
                }

                @Override
                public CTStyle get(int n) {
                    return CTStylesImpl.this.getStyleArray(n);
                }

                @Override
                public CTStyle set(int n, CTStyle cTStyle) {
                    CTStyle cTStyle2 = CTStylesImpl.this.getStyleArray(n);
                    CTStylesImpl.this.setStyleArray(n, cTStyle);
                    return cTStyle2;
                }

                @Override
                public void add(int n, CTStyle cTStyle) {
                    CTStylesImpl.this.insertNewStyle(n).set(cTStyle);
                }

                @Override
                public CTStyle remove(int n) {
                    CTStyle cTStyle = CTStylesImpl.this.getStyleArray(n);
                    CTStylesImpl.this.removeStyle(n);
                    return cTStyle;
                }

                @Override
                public int size() {
                    return CTStylesImpl.this.sizeOfStyleArray();
                }
            }
            return new StyleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTStyle[] getStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(STYLE$4, arrayList);
            CTStyle[] cTStyleArray = new CTStyle[arrayList.size()];
            arrayList.toArray(cTStyleArray);
            return cTStyleArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyle getStyleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyle cTStyle = null;
            cTStyle = (CTStyle)((Object)this.get_store().find_element_user(STYLE$4, n));
            if (cTStyle == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STYLE$4);
        }
    }

    @Override
    public void setStyleArray(CTStyle[] cTStyleArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTStyleArray, STYLE$4);
    }

    @Override
    public void setStyleArray(int n, CTStyle cTStyle) {
        this.generatedSetterHelperImpl(cTStyle, STYLE$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyle insertNewStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyle cTStyle = null;
            cTStyle = (CTStyle)((Object)this.get_store().insert_element_user(STYLE$4, n));
            return cTStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyle addNewStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyle cTStyle = null;
            cTStyle = (CTStyle)((Object)this.get_store().add_element_user(STYLE$4));
            return cTStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STYLE$4, n);
        }
    }
}

