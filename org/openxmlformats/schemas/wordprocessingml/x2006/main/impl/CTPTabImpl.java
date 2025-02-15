/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabAlignment
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabLeader
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabRelativeTo
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabAlignment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabLeader;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabRelativeTo;

public class CTPTabImpl
extends XmlComplexContentImpl
implements CTPTab {
    private static final long serialVersionUID = 1L;
    private static final QName ALIGNMENT$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "alignment");
    private static final QName RELATIVETO$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "relativeTo");
    private static final QName LEADER$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "leader");

    public CTPTabImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPTabAlignment.Enum getAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALIGNMENT$0));
            if (simpleValue == null) {
                return null;
            }
            return (STPTabAlignment.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPTabAlignment xgetAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPTabAlignment sTPTabAlignment = null;
            sTPTabAlignment = (STPTabAlignment)this.get_store().find_attribute_user(ALIGNMENT$0);
            return sTPTabAlignment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlignment(STPTabAlignment.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALIGNMENT$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALIGNMENT$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlignment(STPTabAlignment sTPTabAlignment) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPTabAlignment sTPTabAlignment2 = null;
            sTPTabAlignment2 = (STPTabAlignment)this.get_store().find_attribute_user(ALIGNMENT$0);
            if (sTPTabAlignment2 == null) {
                sTPTabAlignment2 = (STPTabAlignment)this.get_store().add_attribute_user(ALIGNMENT$0);
            }
            sTPTabAlignment2.set((XmlObject)sTPTabAlignment);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPTabRelativeTo.Enum getRelativeTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RELATIVETO$2));
            if (simpleValue == null) {
                return null;
            }
            return (STPTabRelativeTo.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPTabRelativeTo xgetRelativeTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPTabRelativeTo sTPTabRelativeTo = null;
            sTPTabRelativeTo = (STPTabRelativeTo)this.get_store().find_attribute_user(RELATIVETO$2);
            return sTPTabRelativeTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRelativeTo(STPTabRelativeTo.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RELATIVETO$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RELATIVETO$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRelativeTo(STPTabRelativeTo sTPTabRelativeTo) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPTabRelativeTo sTPTabRelativeTo2 = null;
            sTPTabRelativeTo2 = (STPTabRelativeTo)this.get_store().find_attribute_user(RELATIVETO$2);
            if (sTPTabRelativeTo2 == null) {
                sTPTabRelativeTo2 = (STPTabRelativeTo)this.get_store().add_attribute_user(RELATIVETO$2);
            }
            sTPTabRelativeTo2.set((XmlObject)sTPTabRelativeTo);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPTabLeader.Enum getLeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LEADER$4));
            if (simpleValue == null) {
                return null;
            }
            return (STPTabLeader.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPTabLeader xgetLeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPTabLeader sTPTabLeader = null;
            sTPTabLeader = (STPTabLeader)this.get_store().find_attribute_user(LEADER$4);
            return sTPTabLeader;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLeader(STPTabLeader.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LEADER$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LEADER$4));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLeader(STPTabLeader sTPTabLeader) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPTabLeader sTPTabLeader2 = null;
            sTPTabLeader2 = (STPTabLeader)this.get_store().find_attribute_user(LEADER$4);
            if (sTPTabLeader2 == null) {
                sTPTabLeader2 = (STPTabLeader)this.get_store().add_attribute_user(LEADER$4);
            }
            sTPTabLeader2.set((XmlObject)sTPTabLeader);
        }
    }
}

