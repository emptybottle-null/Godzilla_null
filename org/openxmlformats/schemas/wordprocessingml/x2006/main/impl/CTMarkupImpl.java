/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;

public class CTMarkupImpl
extends XmlComplexContentImpl
implements CTMarkup {
    private static final long serialVersionUID = 1L;
    private static final QName ID$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "id");

    public CTMarkupImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$0));
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
    public STDecimalNumber xgetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber = null;
            sTDecimalNumber = (STDecimalNumber)((Object)this.get_store().find_attribute_user(ID$0));
            return sTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$0));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId(STDecimalNumber sTDecimalNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber2 = null;
            sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().find_attribute_user(ID$0));
            if (sTDecimalNumber2 == null) {
                sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().add_attribute_user(ID$0));
            }
            sTDecimalNumber2.set(sTDecimalNumber);
        }
    }
}

