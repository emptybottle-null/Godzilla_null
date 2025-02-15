/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

public class CTSolidColorFillPropertiesImpl
extends XmlComplexContentImpl
implements CTSolidColorFillProperties {
    private static final long serialVersionUID = 1L;
    private static final QName SCRGBCLR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "scrgbClr");
    private static final QName SRGBCLR$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "srgbClr");
    private static final QName HSLCLR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "hslClr");
    private static final QName SYSCLR$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "sysClr");
    private static final QName SCHEMECLR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "schemeClr");
    private static final QName PRSTCLR$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "prstClr");

    public CTSolidColorFillPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScRgbColor getScrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScRgbColor cTScRgbColor = null;
            cTScRgbColor = (CTScRgbColor)((Object)this.get_store().find_element_user(SCRGBCLR$0, 0));
            if (cTScRgbColor == null) {
                return null;
            }
            return cTScRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetScrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCRGBCLR$0) != 0;
        }
    }

    @Override
    public void setScrgbClr(CTScRgbColor cTScRgbColor) {
        this.generatedSetterHelperImpl(cTScRgbColor, SCRGBCLR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScRgbColor addNewScrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScRgbColor cTScRgbColor = null;
            cTScRgbColor = (CTScRgbColor)((Object)this.get_store().add_element_user(SCRGBCLR$0));
            return cTScRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetScrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCRGBCLR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSRgbColor getSrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSRgbColor cTSRgbColor = null;
            cTSRgbColor = (CTSRgbColor)((Object)this.get_store().find_element_user(SRGBCLR$2, 0));
            if (cTSRgbColor == null) {
                return null;
            }
            return cTSRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SRGBCLR$2) != 0;
        }
    }

    @Override
    public void setSrgbClr(CTSRgbColor cTSRgbColor) {
        this.generatedSetterHelperImpl(cTSRgbColor, SRGBCLR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSRgbColor addNewSrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSRgbColor cTSRgbColor = null;
            cTSRgbColor = (CTSRgbColor)((Object)this.get_store().add_element_user(SRGBCLR$2));
            return cTSRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SRGBCLR$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHslColor getHslClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHslColor cTHslColor = null;
            cTHslColor = (CTHslColor)((Object)this.get_store().find_element_user(HSLCLR$4, 0));
            if (cTHslColor == null) {
                return null;
            }
            return cTHslColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHslClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HSLCLR$4) != 0;
        }
    }

    @Override
    public void setHslClr(CTHslColor cTHslColor) {
        this.generatedSetterHelperImpl(cTHslColor, HSLCLR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHslColor addNewHslClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHslColor cTHslColor = null;
            cTHslColor = (CTHslColor)((Object)this.get_store().add_element_user(HSLCLR$4));
            return cTHslColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHslClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HSLCLR$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSystemColor getSysClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSystemColor cTSystemColor = null;
            cTSystemColor = (CTSystemColor)((Object)this.get_store().find_element_user(SYSCLR$6, 0));
            if (cTSystemColor == null) {
                return null;
            }
            return cTSystemColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSysClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SYSCLR$6) != 0;
        }
    }

    @Override
    public void setSysClr(CTSystemColor cTSystemColor) {
        this.generatedSetterHelperImpl(cTSystemColor, SYSCLR$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSystemColor addNewSysClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSystemColor cTSystemColor = null;
            cTSystemColor = (CTSystemColor)((Object)this.get_store().add_element_user(SYSCLR$6));
            return cTSystemColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSysClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SYSCLR$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchemeColor getSchemeClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchemeColor cTSchemeColor = null;
            cTSchemeColor = (CTSchemeColor)((Object)this.get_store().find_element_user(SCHEMECLR$8, 0));
            if (cTSchemeColor == null) {
                return null;
            }
            return cTSchemeColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSchemeClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCHEMECLR$8) != 0;
        }
    }

    @Override
    public void setSchemeClr(CTSchemeColor cTSchemeColor) {
        this.generatedSetterHelperImpl(cTSchemeColor, SCHEMECLR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchemeColor addNewSchemeClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchemeColor cTSchemeColor = null;
            cTSchemeColor = (CTSchemeColor)((Object)this.get_store().add_element_user(SCHEMECLR$8));
            return cTSchemeColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSchemeClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCHEMECLR$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetColor getPrstClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetColor cTPresetColor = null;
            cTPresetColor = (CTPresetColor)((Object)this.get_store().find_element_user(PRSTCLR$10, 0));
            if (cTPresetColor == null) {
                return null;
            }
            return cTPresetColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrstClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRSTCLR$10) != 0;
        }
    }

    @Override
    public void setPrstClr(CTPresetColor cTPresetColor) {
        this.generatedSetterHelperImpl(cTPresetColor, PRSTCLR$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetColor addNewPrstClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetColor cTPresetColor = null;
            cTPresetColor = (CTPresetColor)((Object)this.get_store().add_element_user(PRSTCLR$10));
            return cTPresetColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrstClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRSTCLR$10, 0);
        }
    }
}

