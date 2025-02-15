/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STRubyAlign
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STRubyAlign;

public class CTRubyAlignImpl
extends XmlComplexContentImpl
implements CTRubyAlign {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");

    public CTRubyAlignImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STRubyAlign.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STRubyAlign.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STRubyAlign xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRubyAlign sTRubyAlign = null;
            sTRubyAlign = (STRubyAlign)this.get_store().find_attribute_user(VAL$0);
            return sTRubyAlign;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STRubyAlign.Enum enum_) {
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
    public void xsetVal(STRubyAlign sTRubyAlign) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRubyAlign sTRubyAlign2 = null;
            sTRubyAlign2 = (STRubyAlign)this.get_store().find_attribute_user(VAL$0);
            if (sTRubyAlign2 == null) {
                sTRubyAlign2 = (STRubyAlign)this.get_store().add_attribute_user(VAL$0);
            }
            sTRubyAlign2.set((XmlObject)sTRubyAlign);
        }
    }
}

