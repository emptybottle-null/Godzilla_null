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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIndexedColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor;

public class CTIndexedColorsImpl
extends XmlComplexContentImpl
implements CTIndexedColors {
    private static final long serialVersionUID = 1L;
    private static final QName RGBCOLOR$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "rgbColor");

    public CTIndexedColorsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRgbColor> getRgbColorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RgbColorList
            extends AbstractList<CTRgbColor> {
                RgbColorList() {
                }

                @Override
                public CTRgbColor get(int n) {
                    return CTIndexedColorsImpl.this.getRgbColorArray(n);
                }

                @Override
                public CTRgbColor set(int n, CTRgbColor cTRgbColor) {
                    CTRgbColor cTRgbColor2 = CTIndexedColorsImpl.this.getRgbColorArray(n);
                    CTIndexedColorsImpl.this.setRgbColorArray(n, cTRgbColor);
                    return cTRgbColor2;
                }

                @Override
                public void add(int n, CTRgbColor cTRgbColor) {
                    CTIndexedColorsImpl.this.insertNewRgbColor(n).set(cTRgbColor);
                }

                @Override
                public CTRgbColor remove(int n) {
                    CTRgbColor cTRgbColor = CTIndexedColorsImpl.this.getRgbColorArray(n);
                    CTIndexedColorsImpl.this.removeRgbColor(n);
                    return cTRgbColor;
                }

                @Override
                public int size() {
                    return CTIndexedColorsImpl.this.sizeOfRgbColorArray();
                }
            }
            return new RgbColorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRgbColor[] getRgbColorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RGBCOLOR$0, arrayList);
            CTRgbColor[] cTRgbColorArray = new CTRgbColor[arrayList.size()];
            arrayList.toArray(cTRgbColorArray);
            return cTRgbColorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRgbColor getRgbColorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRgbColor cTRgbColor = null;
            cTRgbColor = (CTRgbColor)((Object)this.get_store().find_element_user(RGBCOLOR$0, n));
            if (cTRgbColor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRgbColorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RGBCOLOR$0);
        }
    }

    @Override
    public void setRgbColorArray(CTRgbColor[] cTRgbColorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRgbColorArray, RGBCOLOR$0);
    }

    @Override
    public void setRgbColorArray(int n, CTRgbColor cTRgbColor) {
        this.generatedSetterHelperImpl(cTRgbColor, RGBCOLOR$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRgbColor insertNewRgbColor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRgbColor cTRgbColor = null;
            cTRgbColor = (CTRgbColor)((Object)this.get_store().insert_element_user(RGBCOLOR$0, n));
            return cTRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRgbColor addNewRgbColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRgbColor cTRgbColor = null;
            cTRgbColor = (CTRgbColor)((Object)this.get_store().add_element_user(RGBCOLOR$0));
            return cTRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRgbColor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RGBCOLOR$0, n);
        }
    }
}

