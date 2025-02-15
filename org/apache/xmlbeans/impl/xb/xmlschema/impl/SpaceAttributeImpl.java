/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;

public class SpaceAttributeImpl
extends XmlComplexContentImpl
implements SpaceAttribute {
    private static final long serialVersionUID = 1L;
    private static final QName SPACE$0 = new QName("http://www.w3.org/XML/1998/namespace", "space");

    public SpaceAttributeImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SpaceAttribute.Space.Enum getSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(SPACE$0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(SPACE$0));
            }
            if (target == null) {
                return null;
            }
            return (SpaceAttribute.Space.Enum)target.getEnumValue();
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
            SpaceAttribute.Space target = null;
            target = (SpaceAttribute.Space)((Object)this.get_store().find_attribute_user(SPACE$0));
            if (target == null) {
                target = (SpaceAttribute.Space)this.get_default_attribute_value(SPACE$0);
            }
            return target;
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
    public void setSpace(SpaceAttribute.Space.Enum space) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(SPACE$0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(SPACE$0));
            }
            target.setEnumValue(space);
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
            SpaceAttribute.Space target = null;
            target = (SpaceAttribute.Space)((Object)this.get_store().find_attribute_user(SPACE$0));
            if (target == null) {
                target = (SpaceAttribute.Space)((Object)this.get_store().add_attribute_user(SPACE$0));
            }
            target.set(space);
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

    public static class SpaceImpl
    extends JavaStringEnumerationHolderEx
    implements SpaceAttribute.Space {
        private static final long serialVersionUID = 1L;

        public SpaceImpl(SchemaType sType) {
            super(sType, false);
        }

        protected SpaceImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}

