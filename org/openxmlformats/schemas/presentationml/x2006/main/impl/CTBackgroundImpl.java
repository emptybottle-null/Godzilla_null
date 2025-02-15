/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties;

public class CTBackgroundImpl
extends XmlComplexContentImpl
implements CTBackground {
    private static final long serialVersionUID = 1L;
    private static final QName BGPR$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "bgPr");
    private static final QName BGREF$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "bgRef");
    private static final QName BWMODE$4 = new QName("", "bwMode");

    public CTBackgroundImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBackgroundProperties getBgPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBackgroundProperties cTBackgroundProperties = null;
            cTBackgroundProperties = (CTBackgroundProperties)((Object)this.get_store().find_element_user(BGPR$0, 0));
            if (cTBackgroundProperties == null) {
                return null;
            }
            return cTBackgroundProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBgPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BGPR$0) != 0;
        }
    }

    @Override
    public void setBgPr(CTBackgroundProperties cTBackgroundProperties) {
        this.generatedSetterHelperImpl(cTBackgroundProperties, BGPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBackgroundProperties addNewBgPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBackgroundProperties cTBackgroundProperties = null;
            cTBackgroundProperties = (CTBackgroundProperties)((Object)this.get_store().add_element_user(BGPR$0));
            return cTBackgroundProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBgPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BGPR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrixReference getBgRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrixReference cTStyleMatrixReference = null;
            cTStyleMatrixReference = (CTStyleMatrixReference)((Object)this.get_store().find_element_user(BGREF$2, 0));
            if (cTStyleMatrixReference == null) {
                return null;
            }
            return cTStyleMatrixReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBgRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BGREF$2) != 0;
        }
    }

    @Override
    public void setBgRef(CTStyleMatrixReference cTStyleMatrixReference) {
        this.generatedSetterHelperImpl(cTStyleMatrixReference, BGREF$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrixReference addNewBgRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrixReference cTStyleMatrixReference = null;
            cTStyleMatrixReference = (CTStyleMatrixReference)((Object)this.get_store().add_element_user(BGREF$2));
            return cTStyleMatrixReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBgRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BGREF$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBlackWhiteMode.Enum getBwMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BWMODE$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(BWMODE$4));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STBlackWhiteMode.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBlackWhiteMode xgetBwMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBlackWhiteMode sTBlackWhiteMode = null;
            sTBlackWhiteMode = (STBlackWhiteMode)((Object)this.get_store().find_attribute_user(BWMODE$4));
            if (sTBlackWhiteMode == null) {
                sTBlackWhiteMode = (STBlackWhiteMode)this.get_default_attribute_value(BWMODE$4);
            }
            return sTBlackWhiteMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBwMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BWMODE$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBwMode(STBlackWhiteMode.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BWMODE$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BWMODE$4));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBwMode(STBlackWhiteMode sTBlackWhiteMode) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBlackWhiteMode sTBlackWhiteMode2 = null;
            sTBlackWhiteMode2 = (STBlackWhiteMode)((Object)this.get_store().find_attribute_user(BWMODE$4));
            if (sTBlackWhiteMode2 == null) {
                sTBlackWhiteMode2 = (STBlackWhiteMode)((Object)this.get_store().add_attribute_user(BWMODE$4));
            }
            sTBlackWhiteMode2.set(sTBlackWhiteMode);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBwMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BWMODE$4);
        }
    }
}

