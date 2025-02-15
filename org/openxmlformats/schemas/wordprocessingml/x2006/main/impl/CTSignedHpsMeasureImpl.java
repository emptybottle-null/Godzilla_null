/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedHpsMeasure;

public class CTSignedHpsMeasureImpl
extends XmlComplexContentImpl
implements CTSignedHpsMeasure {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");

    public CTSignedHpsMeasureImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
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
    public STSignedHpsMeasure xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignedHpsMeasure sTSignedHpsMeasure = null;
            sTSignedHpsMeasure = (STSignedHpsMeasure)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTSignedHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVal(STSignedHpsMeasure sTSignedHpsMeasure) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignedHpsMeasure sTSignedHpsMeasure2 = null;
            sTSignedHpsMeasure2 = (STSignedHpsMeasure)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTSignedHpsMeasure2 == null) {
                sTSignedHpsMeasure2 = (STSignedHpsMeasure)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTSignedHpsMeasure2.set(sTSignedHpsMeasure);
        }
    }
}

