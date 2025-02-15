/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class CTTextListStyleImpl
extends XmlComplexContentImpl
implements CTTextListStyle {
    private static final long serialVersionUID = 1L;
    private static final QName DEFPPR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "defPPr");
    private static final QName LVL1PPR$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lvl1pPr");
    private static final QName LVL2PPR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lvl2pPr");
    private static final QName LVL3PPR$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lvl3pPr");
    private static final QName LVL4PPR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lvl4pPr");
    private static final QName LVL5PPR$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lvl5pPr");
    private static final QName LVL6PPR$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lvl6pPr");
    private static final QName LVL7PPR$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lvl7pPr");
    private static final QName LVL8PPR$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lvl8pPr");
    private static final QName LVL9PPR$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lvl9pPr");
    private static final QName EXTLST$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");

    public CTTextListStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getDefPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(DEFPPR$0, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFPPR$0) != 0;
        }
    }

    @Override
    public void setDefPPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, DEFPPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewDefPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(DEFPPR$0));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFPPR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getLvl1PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(LVL1PPR$2, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLvl1PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVL1PPR$2) != 0;
        }
    }

    @Override
    public void setLvl1PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, LVL1PPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewLvl1PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(LVL1PPR$2));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLvl1PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVL1PPR$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getLvl2PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(LVL2PPR$4, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLvl2PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVL2PPR$4) != 0;
        }
    }

    @Override
    public void setLvl2PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, LVL2PPR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewLvl2PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(LVL2PPR$4));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLvl2PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVL2PPR$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getLvl3PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(LVL3PPR$6, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLvl3PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVL3PPR$6) != 0;
        }
    }

    @Override
    public void setLvl3PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, LVL3PPR$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewLvl3PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(LVL3PPR$6));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLvl3PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVL3PPR$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getLvl4PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(LVL4PPR$8, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLvl4PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVL4PPR$8) != 0;
        }
    }

    @Override
    public void setLvl4PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, LVL4PPR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewLvl4PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(LVL4PPR$8));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLvl4PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVL4PPR$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getLvl5PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(LVL5PPR$10, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLvl5PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVL5PPR$10) != 0;
        }
    }

    @Override
    public void setLvl5PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, LVL5PPR$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewLvl5PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(LVL5PPR$10));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLvl5PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVL5PPR$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getLvl6PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(LVL6PPR$12, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLvl6PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVL6PPR$12) != 0;
        }
    }

    @Override
    public void setLvl6PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, LVL6PPR$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewLvl6PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(LVL6PPR$12));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLvl6PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVL6PPR$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getLvl7PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(LVL7PPR$14, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLvl7PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVL7PPR$14) != 0;
        }
    }

    @Override
    public void setLvl7PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, LVL7PPR$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewLvl7PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(LVL7PPR$14));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLvl7PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVL7PPR$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getLvl8PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(LVL8PPR$16, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLvl8PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVL8PPR$16) != 0;
        }
    }

    @Override
    public void setLvl8PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, LVL8PPR$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewLvl8PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(LVL8PPR$16));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLvl8PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVL8PPR$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties getLvl9PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().find_element_user(LVL9PPR$18, 0));
            if (cTTextParagraphProperties == null) {
                return null;
            }
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLvl9PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVL9PPR$18) != 0;
        }
    }

    @Override
    public void setLvl9PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        this.generatedSetterHelperImpl(cTTextParagraphProperties, LVL9PPR$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraphProperties addNewLvl9PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraphProperties cTTextParagraphProperties = null;
            cTTextParagraphProperties = (CTTextParagraphProperties)((Object)this.get_store().add_element_user(LVL9PPR$18));
            return cTTextParagraphProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLvl9PPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVL9PPR$18, 0);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$20, 0));
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
            return this.get_store().count_elements(EXTLST$20) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$20, 0, (short)1);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$20));
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
            this.get_store().remove_element(EXTLST$20, 0);
        }
    }
}

