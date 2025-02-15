/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFonts;

public class CTFontsImpl
extends XmlComplexContentImpl
implements CTFonts {
    private static final long serialVersionUID = 1L;
    private static final QName FONT$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "font");
    private static final QName COUNT$2 = new QName("", "count");

    public CTFontsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFont> getFontList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FontList
            extends AbstractList<CTFont> {
                FontList() {
                }

                @Override
                public CTFont get(int n) {
                    return CTFontsImpl.this.getFontArray(n);
                }

                @Override
                public CTFont set(int n, CTFont cTFont) {
                    CTFont cTFont2 = CTFontsImpl.this.getFontArray(n);
                    CTFontsImpl.this.setFontArray(n, cTFont);
                    return cTFont2;
                }

                @Override
                public void add(int n, CTFont cTFont) {
                    CTFontsImpl.this.insertNewFont(n).set(cTFont);
                }

                @Override
                public CTFont remove(int n) {
                    CTFont cTFont = CTFontsImpl.this.getFontArray(n);
                    CTFontsImpl.this.removeFont(n);
                    return cTFont;
                }

                @Override
                public int size() {
                    return CTFontsImpl.this.sizeOfFontArray();
                }
            }
            return new FontList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFont[] getFontArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FONT$0, arrayList);
            CTFont[] cTFontArray = new CTFont[arrayList.size()];
            arrayList.toArray(cTFontArray);
            return cTFontArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFont getFontArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFont cTFont = null;
            cTFont = (CTFont)((Object)this.get_store().find_element_user(FONT$0, n));
            if (cTFont == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFontArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FONT$0);
        }
    }

    @Override
    public void setFontArray(CTFont[] cTFontArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFontArray, FONT$0);
    }

    @Override
    public void setFontArray(int n, CTFont cTFont) {
        this.generatedSetterHelperImpl(cTFont, FONT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFont insertNewFont(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFont cTFont = null;
            cTFont = (CTFont)((Object)this.get_store().insert_element_user(FONT$0, n));
            return cTFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFont addNewFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFont cTFont = null;
            cTFont = (CTFont)((Object)this.get_store().add_element_user(FONT$0));
            return cTFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFont(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FONT$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COUNT$2));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(COUNT$2));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COUNT$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCount(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COUNT$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COUNT$2));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCount(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(COUNT$2));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(COUNT$2));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COUNT$2);
        }
    }
}

