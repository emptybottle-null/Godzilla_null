/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

public class CTTextImpl
extends JavaStringHolderEx
implements CTText {
    private static final long serialVersionUID = 1L;
    private static final QName SPACE$0 = new QName("http://www.w3.org/XML/1998/namespace", "space");

    public CTTextImpl(SchemaType schemaType) {
        super(schemaType, true);
    }

    protected CTTextImpl(SchemaType schemaType, boolean bl) {
        super(schemaType, bl);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SpaceAttribute.Space.Enum getSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPACE$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SPACE$0));
            }
            if (simpleValue == null) {
                return null;
            }
            return (SpaceAttribute.Space.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SpaceAttribute.Space xgetSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SpaceAttribute.Space space = null;
            space = (SpaceAttribute.Space)((Object)this.get_store().find_attribute_user(SPACE$0));
            if (space == null) {
                space = (SpaceAttribute.Space)this.get_default_attribute_value(SPACE$0);
            }
            return space;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SPACE$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSpace(SpaceAttribute.Space.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPACE$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SPACE$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSpace(SpaceAttribute.Space space) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SpaceAttribute.Space space2 = null;
            space2 = (SpaceAttribute.Space)((Object)this.get_store().find_attribute_user(SPACE$0));
            if (space2 == null) {
                space2 = (SpaceAttribute.Space)((Object)this.get_store().add_attribute_user(SPACE$0));
            }
            space2.set(space);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SPACE$0);
        }
    }
}

