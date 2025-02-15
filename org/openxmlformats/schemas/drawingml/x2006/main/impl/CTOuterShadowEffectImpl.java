/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.STFixedAngle
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STRectAlignment;

public class CTOuterShadowEffectImpl
extends XmlComplexContentImpl
implements CTOuterShadowEffect {
    private static final long serialVersionUID = 1L;
    private static final QName SCRGBCLR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "scrgbClr");
    private static final QName SRGBCLR$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "srgbClr");
    private static final QName HSLCLR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "hslClr");
    private static final QName SYSCLR$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "sysClr");
    private static final QName SCHEMECLR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "schemeClr");
    private static final QName PRSTCLR$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "prstClr");
    private static final QName BLURRAD$12 = new QName("", "blurRad");
    private static final QName DIST$14 = new QName("", "dist");
    private static final QName DIR$16 = new QName("", "dir");
    private static final QName SX$18 = new QName("", "sx");
    private static final QName SY$20 = new QName("", "sy");
    private static final QName KX$22 = new QName("", "kx");
    private static final QName KY$24 = new QName("", "ky");
    private static final QName ALGN$26 = new QName("", "algn");
    private static final QName ROTWITHSHAPE$28 = new QName("", "rotWithShape");

    public CTOuterShadowEffectImpl(SchemaType schemaType) {
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getBlurRad() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BLURRAD$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(BLURRAD$12));
            }
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
    public STPositiveCoordinate xgetBlurRad() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate = null;
            sTPositiveCoordinate = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(BLURRAD$12));
            if (sTPositiveCoordinate == null) {
                sTPositiveCoordinate = (STPositiveCoordinate)this.get_default_attribute_value(BLURRAD$12);
            }
            return sTPositiveCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBlurRad() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BLURRAD$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBlurRad(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BLURRAD$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BLURRAD$12));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBlurRad(STPositiveCoordinate sTPositiveCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate2 = null;
            sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(BLURRAD$12));
            if (sTPositiveCoordinate2 == null) {
                sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().add_attribute_user(BLURRAD$12));
            }
            sTPositiveCoordinate2.set(sTPositiveCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBlurRad() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BLURRAD$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getDist() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIST$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(DIST$14));
            }
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
    public STPositiveCoordinate xgetDist() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate = null;
            sTPositiveCoordinate = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(DIST$14));
            if (sTPositiveCoordinate == null) {
                sTPositiveCoordinate = (STPositiveCoordinate)this.get_default_attribute_value(DIST$14);
            }
            return sTPositiveCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDist() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DIST$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDist(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIST$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DIST$14));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDist(STPositiveCoordinate sTPositiveCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate2 = null;
            sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(DIST$14));
            if (sTPositiveCoordinate2 == null) {
                sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().add_attribute_user(DIST$14));
            }
            sTPositiveCoordinate2.set(sTPositiveCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDist() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DIST$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIR$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(DIR$16));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPositiveFixedAngle xgetDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveFixedAngle sTPositiveFixedAngle = null;
            sTPositiveFixedAngle = (STPositiveFixedAngle)((Object)this.get_store().find_attribute_user(DIR$16));
            if (sTPositiveFixedAngle == null) {
                sTPositiveFixedAngle = (STPositiveFixedAngle)this.get_default_attribute_value(DIR$16);
            }
            return sTPositiveFixedAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DIR$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDir(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIR$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DIR$16));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDir(STPositiveFixedAngle sTPositiveFixedAngle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveFixedAngle sTPositiveFixedAngle2 = null;
            sTPositiveFixedAngle2 = (STPositiveFixedAngle)((Object)this.get_store().find_attribute_user(DIR$16));
            if (sTPositiveFixedAngle2 == null) {
                sTPositiveFixedAngle2 = (STPositiveFixedAngle)((Object)this.get_store().add_attribute_user(DIR$16));
            }
            sTPositiveFixedAngle2.set(sTPositiveFixedAngle);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DIR$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SX$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SX$18));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPercentage xgetSx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(SX$18));
            if (sTPercentage == null) {
                sTPercentage = (STPercentage)this.get_default_attribute_value(SX$18);
            }
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SX$18) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SX$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SX$18));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSx(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(SX$18));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(SX$18));
            }
            sTPercentage2.set(sTPercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SX$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SY$20));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SY$20));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPercentage xgetSy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(SY$20));
            if (sTPercentage == null) {
                sTPercentage = (STPercentage)this.get_default_attribute_value(SY$20);
            }
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SY$20) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSy(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SY$20));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SY$20));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSy(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(SY$20));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(SY$20));
            }
            sTPercentage2.set(sTPercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SY$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getKx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(KX$22));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(KX$22));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STFixedAngle xgetKx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFixedAngle sTFixedAngle = null;
            sTFixedAngle = (STFixedAngle)this.get_store().find_attribute_user(KX$22);
            if (sTFixedAngle == null) {
                sTFixedAngle = (STFixedAngle)this.get_default_attribute_value(KX$22);
            }
            return sTFixedAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetKx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(KX$22) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setKx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(KX$22));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(KX$22));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetKx(STFixedAngle sTFixedAngle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFixedAngle sTFixedAngle2 = null;
            sTFixedAngle2 = (STFixedAngle)this.get_store().find_attribute_user(KX$22);
            if (sTFixedAngle2 == null) {
                sTFixedAngle2 = (STFixedAngle)this.get_store().add_attribute_user(KX$22);
            }
            sTFixedAngle2.set((XmlObject)sTFixedAngle);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetKx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(KX$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getKy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(KY$24));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(KY$24));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STFixedAngle xgetKy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFixedAngle sTFixedAngle = null;
            sTFixedAngle = (STFixedAngle)this.get_store().find_attribute_user(KY$24);
            if (sTFixedAngle == null) {
                sTFixedAngle = (STFixedAngle)this.get_default_attribute_value(KY$24);
            }
            return sTFixedAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetKy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(KY$24) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setKy(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(KY$24));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(KY$24));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetKy(STFixedAngle sTFixedAngle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFixedAngle sTFixedAngle2 = null;
            sTFixedAngle2 = (STFixedAngle)this.get_store().find_attribute_user(KY$24);
            if (sTFixedAngle2 == null) {
                sTFixedAngle2 = (STFixedAngle)this.get_store().add_attribute_user(KY$24);
            }
            sTFixedAngle2.set((XmlObject)sTFixedAngle);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetKy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(KY$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STRectAlignment.Enum getAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGN$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(ALGN$26));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STRectAlignment.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STRectAlignment xgetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRectAlignment sTRectAlignment = null;
            sTRectAlignment = (STRectAlignment)((Object)this.get_store().find_attribute_user(ALGN$26));
            if (sTRectAlignment == null) {
                sTRectAlignment = (STRectAlignment)this.get_default_attribute_value(ALGN$26);
            }
            return sTRectAlignment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALGN$26) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlgn(STRectAlignment.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGN$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALGN$26));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlgn(STRectAlignment sTRectAlignment) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRectAlignment sTRectAlignment2 = null;
            sTRectAlignment2 = (STRectAlignment)((Object)this.get_store().find_attribute_user(ALGN$26));
            if (sTRectAlignment2 == null) {
                sTRectAlignment2 = (STRectAlignment)((Object)this.get_store().add_attribute_user(ALGN$26));
            }
            sTRectAlignment2.set(sTRectAlignment);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALGN$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getRotWithShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ROTWITHSHAPE$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(ROTWITHSHAPE$28));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetRotWithShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(ROTWITHSHAPE$28));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(ROTWITHSHAPE$28);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRotWithShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ROTWITHSHAPE$28) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRotWithShape(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ROTWITHSHAPE$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ROTWITHSHAPE$28));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRotWithShape(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(ROTWITHSHAPE$28));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(ROTWITHSHAPE$28));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRotWithShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ROTWITHSHAPE$28);
        }
    }
}

