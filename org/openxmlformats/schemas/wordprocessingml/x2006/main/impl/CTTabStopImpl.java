/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabTlc;

public class CTTabStopImpl
extends XmlComplexContentImpl
implements CTTabStop {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");
    private static final QName LEADER$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "leader");
    private static final QName POS$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pos");

    public CTTabStopImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTabJc.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STTabJc.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTabJc xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTabJc sTTabJc = null;
            sTTabJc = (STTabJc)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTTabJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STTabJc.Enum enum_) {
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
    public void xsetVal(STTabJc sTTabJc) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTabJc sTTabJc2 = null;
            sTTabJc2 = (STTabJc)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTTabJc2 == null) {
                sTTabJc2 = (STTabJc)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTTabJc2.set(sTTabJc);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTabTlc.Enum getLeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LEADER$2));
            if (simpleValue == null) {
                return null;
            }
            return (STTabTlc.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTabTlc xgetLeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTabTlc sTTabTlc = null;
            sTTabTlc = (STTabTlc)((Object)this.get_store().find_attribute_user(LEADER$2));
            return sTTabTlc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LEADER$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLeader(STTabTlc.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LEADER$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LEADER$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLeader(STTabTlc sTTabTlc) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTabTlc sTTabTlc2 = null;
            sTTabTlc2 = (STTabTlc)((Object)this.get_store().find_attribute_user(LEADER$2));
            if (sTTabTlc2 == null) {
                sTTabTlc2 = (STTabTlc)((Object)this.get_store().add_attribute_user(LEADER$2));
            }
            sTTabTlc2.set(sTTabTlc);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LEADER$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(POS$4));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STSignedTwipsMeasure xgetPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignedTwipsMeasure sTSignedTwipsMeasure = null;
            sTSignedTwipsMeasure = (STSignedTwipsMeasure)((Object)this.get_store().find_attribute_user(POS$4));
            return sTSignedTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPos(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(POS$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(POS$4));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPos(STSignedTwipsMeasure sTSignedTwipsMeasure) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignedTwipsMeasure sTSignedTwipsMeasure2 = null;
            sTSignedTwipsMeasure2 = (STSignedTwipsMeasure)((Object)this.get_store().find_attribute_user(POS$4));
            if (sTSignedTwipsMeasure2 == null) {
                sTSignedTwipsMeasure2 = (STSignedTwipsMeasure)((Object)this.get_store().add_attribute_user(POS$4));
            }
            sTSignedTwipsMeasure2.set(sTSignedTwipsMeasure);
        }
    }
}

