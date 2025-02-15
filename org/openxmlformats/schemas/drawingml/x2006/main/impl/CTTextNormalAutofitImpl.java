/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontScalePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextSpacingPercent;

public class CTTextNormalAutofitImpl
extends XmlComplexContentImpl
implements CTTextNormalAutofit {
    private static final long serialVersionUID = 1L;
    private static final QName FONTSCALE$0 = new QName("", "fontScale");
    private static final QName LNSPCREDUCTION$2 = new QName("", "lnSpcReduction");

    public CTTextNormalAutofitImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getFontScale() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FONTSCALE$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(FONTSCALE$0));
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
    public STTextFontScalePercent xgetFontScale() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextFontScalePercent sTTextFontScalePercent = null;
            sTTextFontScalePercent = (STTextFontScalePercent)((Object)this.get_store().find_attribute_user(FONTSCALE$0));
            if (sTTextFontScalePercent == null) {
                sTTextFontScalePercent = (STTextFontScalePercent)this.get_default_attribute_value(FONTSCALE$0);
            }
            return sTTextFontScalePercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFontScale() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FONTSCALE$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFontScale(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FONTSCALE$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FONTSCALE$0));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFontScale(STTextFontScalePercent sTTextFontScalePercent) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextFontScalePercent sTTextFontScalePercent2 = null;
            sTTextFontScalePercent2 = (STTextFontScalePercent)((Object)this.get_store().find_attribute_user(FONTSCALE$0));
            if (sTTextFontScalePercent2 == null) {
                sTTextFontScalePercent2 = (STTextFontScalePercent)((Object)this.get_store().add_attribute_user(FONTSCALE$0));
            }
            sTTextFontScalePercent2.set(sTTextFontScalePercent);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFontScale() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FONTSCALE$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getLnSpcReduction() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LNSPCREDUCTION$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(LNSPCREDUCTION$2));
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
    public STTextSpacingPercent xgetLnSpcReduction() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextSpacingPercent sTTextSpacingPercent = null;
            sTTextSpacingPercent = (STTextSpacingPercent)((Object)this.get_store().find_attribute_user(LNSPCREDUCTION$2));
            if (sTTextSpacingPercent == null) {
                sTTextSpacingPercent = (STTextSpacingPercent)this.get_default_attribute_value(LNSPCREDUCTION$2);
            }
            return sTTextSpacingPercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLnSpcReduction() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LNSPCREDUCTION$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLnSpcReduction(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LNSPCREDUCTION$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LNSPCREDUCTION$2));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLnSpcReduction(STTextSpacingPercent sTTextSpacingPercent) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextSpacingPercent sTTextSpacingPercent2 = null;
            sTTextSpacingPercent2 = (STTextSpacingPercent)((Object)this.get_store().find_attribute_user(LNSPCREDUCTION$2));
            if (sTTextSpacingPercent2 == null) {
                sTTextSpacingPercent2 = (STTextSpacingPercent)((Object)this.get_store().add_attribute_user(LNSPCREDUCTION$2));
            }
            sTTextSpacingPercent2.set(sTTextSpacingPercent);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLnSpcReduction() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LNSPCREDUCTION$2);
        }
    }
}

