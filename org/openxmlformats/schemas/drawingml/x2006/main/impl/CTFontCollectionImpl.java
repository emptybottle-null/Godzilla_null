/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTSupplementalFont
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontCollection;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSupplementalFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;

public class CTFontCollectionImpl
extends XmlComplexContentImpl
implements CTFontCollection {
    private static final long serialVersionUID = 1L;
    private static final QName LATIN$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "latin");
    private static final QName EA$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "ea");
    private static final QName CS$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "cs");
    private static final QName FONT$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "font");
    private static final QName EXTLST$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");

    public CTFontCollectionImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextFont getLatin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextFont cTTextFont = null;
            cTTextFont = (CTTextFont)((Object)this.get_store().find_element_user(LATIN$0, 0));
            if (cTTextFont == null) {
                return null;
            }
            return cTTextFont;
        }
    }

    @Override
    public void setLatin(CTTextFont cTTextFont) {
        this.generatedSetterHelperImpl(cTTextFont, LATIN$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextFont addNewLatin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextFont cTTextFont = null;
            cTTextFont = (CTTextFont)((Object)this.get_store().add_element_user(LATIN$0));
            return cTTextFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextFont getEa() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextFont cTTextFont = null;
            cTTextFont = (CTTextFont)((Object)this.get_store().find_element_user(EA$2, 0));
            if (cTTextFont == null) {
                return null;
            }
            return cTTextFont;
        }
    }

    @Override
    public void setEa(CTTextFont cTTextFont) {
        this.generatedSetterHelperImpl(cTTextFont, EA$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextFont addNewEa() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextFont cTTextFont = null;
            cTTextFont = (CTTextFont)((Object)this.get_store().add_element_user(EA$2));
            return cTTextFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextFont getCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextFont cTTextFont = null;
            cTTextFont = (CTTextFont)((Object)this.get_store().find_element_user(CS$4, 0));
            if (cTTextFont == null) {
                return null;
            }
            return cTTextFont;
        }
    }

    @Override
    public void setCs(CTTextFont cTTextFont) {
        this.generatedSetterHelperImpl(cTTextFont, CS$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextFont addNewCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextFont cTTextFont = null;
            cTTextFont = (CTTextFont)((Object)this.get_store().add_element_user(CS$4));
            return cTTextFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSupplementalFont> getFontList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FontList
            extends AbstractList<CTSupplementalFont> {
                FontList() {
                }

                @Override
                public CTSupplementalFont get(int n) {
                    return CTFontCollectionImpl.this.getFontArray(n);
                }

                @Override
                public CTSupplementalFont set(int n, CTSupplementalFont cTSupplementalFont) {
                    CTSupplementalFont cTSupplementalFont2 = CTFontCollectionImpl.this.getFontArray(n);
                    CTFontCollectionImpl.this.setFontArray(n, cTSupplementalFont);
                    return cTSupplementalFont2;
                }

                @Override
                public void add(int n, CTSupplementalFont cTSupplementalFont) {
                    CTFontCollectionImpl.this.insertNewFont(n).set((XmlObject)cTSupplementalFont);
                }

                @Override
                public CTSupplementalFont remove(int n) {
                    CTSupplementalFont cTSupplementalFont = CTFontCollectionImpl.this.getFontArray(n);
                    CTFontCollectionImpl.this.removeFont(n);
                    return cTSupplementalFont;
                }

                @Override
                public int size() {
                    return CTFontCollectionImpl.this.sizeOfFontArray();
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
    public CTSupplementalFont[] getFontArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FONT$6, arrayList);
            CTSupplementalFont[] cTSupplementalFontArray = new CTSupplementalFont[arrayList.size()];
            arrayList.toArray(cTSupplementalFontArray);
            return cTSupplementalFontArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSupplementalFont getFontArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSupplementalFont cTSupplementalFont = null;
            cTSupplementalFont = (CTSupplementalFont)this.get_store().find_element_user(FONT$6, n);
            if (cTSupplementalFont == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSupplementalFont;
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
            return this.get_store().count_elements(FONT$6);
        }
    }

    @Override
    public void setFontArray(CTSupplementalFont[] cTSupplementalFontArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSupplementalFontArray, FONT$6);
    }

    @Override
    public void setFontArray(int n, CTSupplementalFont cTSupplementalFont) {
        this.generatedSetterHelperImpl((XmlObject)cTSupplementalFont, FONT$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSupplementalFont insertNewFont(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSupplementalFont cTSupplementalFont = null;
            cTSupplementalFont = (CTSupplementalFont)this.get_store().insert_element_user(FONT$6, n);
            return cTSupplementalFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSupplementalFont addNewFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSupplementalFont cTSupplementalFont = null;
            cTSupplementalFont = (CTSupplementalFont)this.get_store().add_element_user(FONT$6);
            return cTSupplementalFont;
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
            this.get_store().remove_element(FONT$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$8, 0));
            if (cTOfficeArtExtensionList == null) {
                return null;
            }
            return cTOfficeArtExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$8) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$8));
            return cTOfficeArtExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$8, 0);
        }
    }
}

