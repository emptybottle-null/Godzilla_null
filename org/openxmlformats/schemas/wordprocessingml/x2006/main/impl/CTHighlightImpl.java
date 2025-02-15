/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;

public class CTHighlightImpl
extends XmlComplexContentImpl
implements CTHighlight {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");

    public CTHighlightImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHighlightColor.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STHighlightColor.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHighlightColor xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHighlightColor sTHighlightColor = null;
            sTHighlightColor = (STHighlightColor)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTHighlightColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STHighlightColor.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVal(STHighlightColor sTHighlightColor) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHighlightColor sTHighlightColor2 = null;
            sTHighlightColor2 = (STHighlightColor)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTHighlightColor2 == null) {
                sTHighlightColor2 = (STHighlightColor)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTHighlightColor2.set(sTHighlightColor);
        }
    }
}

