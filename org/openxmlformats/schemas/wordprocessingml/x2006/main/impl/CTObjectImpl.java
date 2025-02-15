/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTControl
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTControl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTPictureBaseImpl;

public class CTObjectImpl
extends CTPictureBaseImpl
implements CTObject {
    private static final long serialVersionUID = 1L;
    private static final QName CONTROL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "control");
    private static final QName DXAORIG$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dxaOrig");
    private static final QName DYAORIG$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dyaOrig");

    public CTObjectImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTControl getControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTControl cTControl = null;
            cTControl = (CTControl)this.get_store().find_element_user(CONTROL$0, 0);
            if (cTControl == null) {
                return null;
            }
            return cTControl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONTROL$0) != 0;
        }
    }

    @Override
    public void setControl(CTControl cTControl) {
        this.generatedSetterHelperImpl((XmlObject)cTControl, CONTROL$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTControl addNewControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTControl cTControl = null;
            cTControl = (CTControl)this.get_store().add_element_user(CONTROL$0);
            return cTControl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONTROL$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getDxaOrig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DXAORIG$2));
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
    public STTwipsMeasure xgetDxaOrig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTwipsMeasure sTTwipsMeasure = null;
            sTTwipsMeasure = (STTwipsMeasure)((Object)this.get_store().find_attribute_user(DXAORIG$2));
            return sTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDxaOrig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DXAORIG$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDxaOrig(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DXAORIG$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DXAORIG$2));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDxaOrig(STTwipsMeasure sTTwipsMeasure) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTwipsMeasure sTTwipsMeasure2 = null;
            sTTwipsMeasure2 = (STTwipsMeasure)((Object)this.get_store().find_attribute_user(DXAORIG$2));
            if (sTTwipsMeasure2 == null) {
                sTTwipsMeasure2 = (STTwipsMeasure)((Object)this.get_store().add_attribute_user(DXAORIG$2));
            }
            sTTwipsMeasure2.set(sTTwipsMeasure);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDxaOrig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DXAORIG$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getDyaOrig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DYAORIG$4));
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
    public STTwipsMeasure xgetDyaOrig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTwipsMeasure sTTwipsMeasure = null;
            sTTwipsMeasure = (STTwipsMeasure)((Object)this.get_store().find_attribute_user(DYAORIG$4));
            return sTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDyaOrig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DYAORIG$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDyaOrig(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DYAORIG$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DYAORIG$4));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDyaOrig(STTwipsMeasure sTTwipsMeasure) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTwipsMeasure sTTwipsMeasure2 = null;
            sTTwipsMeasure2 = (STTwipsMeasure)((Object)this.get_store().find_attribute_user(DYAORIG$4));
            if (sTTwipsMeasure2 == null) {
                sTTwipsMeasure2 = (STTwipsMeasure)((Object)this.get_store().add_attribute_user(DYAORIG$4));
            }
            sTTwipsMeasure2.set(sTTwipsMeasure);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDyaOrig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DYAORIG$4);
        }
    }
}

