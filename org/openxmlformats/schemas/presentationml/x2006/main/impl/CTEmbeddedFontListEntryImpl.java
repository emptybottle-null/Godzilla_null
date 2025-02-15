/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;

public class CTEmbeddedFontListEntryImpl
extends XmlComplexContentImpl
implements CTEmbeddedFontListEntry {
    private static final long serialVersionUID = 1L;
    private static final QName FONT$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "font");
    private static final QName REGULAR$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "regular");
    private static final QName BOLD$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "bold");
    private static final QName ITALIC$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "italic");
    private static final QName BOLDITALIC$8 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "boldItalic");

    public CTEmbeddedFontListEntryImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextFont getFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextFont cTTextFont = null;
            cTTextFont = (CTTextFont)((Object)this.get_store().find_element_user(FONT$0, 0));
            if (cTTextFont == null) {
                return null;
            }
            return cTTextFont;
        }
    }

    @Override
    public void setFont(CTTextFont cTTextFont) {
        this.generatedSetterHelperImpl(cTTextFont, FONT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextFont addNewFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextFont cTTextFont = null;
            cTTextFont = (CTTextFont)((Object)this.get_store().add_element_user(FONT$0));
            return cTTextFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontDataId getRegular() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontDataId cTEmbeddedFontDataId = null;
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId)((Object)this.get_store().find_element_user(REGULAR$2, 0));
            if (cTEmbeddedFontDataId == null) {
                return null;
            }
            return cTEmbeddedFontDataId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRegular() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REGULAR$2) != 0;
        }
    }

    @Override
    public void setRegular(CTEmbeddedFontDataId cTEmbeddedFontDataId) {
        this.generatedSetterHelperImpl(cTEmbeddedFontDataId, REGULAR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontDataId addNewRegular() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontDataId cTEmbeddedFontDataId = null;
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId)((Object)this.get_store().add_element_user(REGULAR$2));
            return cTEmbeddedFontDataId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRegular() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REGULAR$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontDataId getBold() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontDataId cTEmbeddedFontDataId = null;
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId)((Object)this.get_store().find_element_user(BOLD$4, 0));
            if (cTEmbeddedFontDataId == null) {
                return null;
            }
            return cTEmbeddedFontDataId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBold() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOLD$4) != 0;
        }
    }

    @Override
    public void setBold(CTEmbeddedFontDataId cTEmbeddedFontDataId) {
        this.generatedSetterHelperImpl(cTEmbeddedFontDataId, BOLD$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontDataId addNewBold() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontDataId cTEmbeddedFontDataId = null;
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId)((Object)this.get_store().add_element_user(BOLD$4));
            return cTEmbeddedFontDataId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBold() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOLD$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontDataId getItalic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontDataId cTEmbeddedFontDataId = null;
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId)((Object)this.get_store().find_element_user(ITALIC$6, 0));
            if (cTEmbeddedFontDataId == null) {
                return null;
            }
            return cTEmbeddedFontDataId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetItalic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ITALIC$6) != 0;
        }
    }

    @Override
    public void setItalic(CTEmbeddedFontDataId cTEmbeddedFontDataId) {
        this.generatedSetterHelperImpl(cTEmbeddedFontDataId, ITALIC$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontDataId addNewItalic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontDataId cTEmbeddedFontDataId = null;
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId)((Object)this.get_store().add_element_user(ITALIC$6));
            return cTEmbeddedFontDataId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetItalic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ITALIC$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontDataId getBoldItalic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontDataId cTEmbeddedFontDataId = null;
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId)((Object)this.get_store().find_element_user(BOLDITALIC$8, 0));
            if (cTEmbeddedFontDataId == null) {
                return null;
            }
            return cTEmbeddedFontDataId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBoldItalic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOLDITALIC$8) != 0;
        }
    }

    @Override
    public void setBoldItalic(CTEmbeddedFontDataId cTEmbeddedFontDataId) {
        this.generatedSetterHelperImpl(cTEmbeddedFontDataId, BOLDITALIC$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontDataId addNewBoldItalic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontDataId cTEmbeddedFontDataId = null;
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId)((Object)this.get_store().add_element_user(BOLDITALIC$8));
            return cTEmbeddedFontDataId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBoldItalic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOLDITALIC$8, 0);
        }
    }
}

