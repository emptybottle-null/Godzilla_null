/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;

public class CTLsdExceptionImpl
extends XmlComplexContentImpl
implements CTLsdException {
    private static final long serialVersionUID = 1L;
    private static final QName NAME$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "name");
    private static final QName LOCKED$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "locked");
    private static final QName UIPRIORITY$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "uiPriority");
    private static final QName SEMIHIDDEN$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "semiHidden");
    private static final QName UNHIDEWHENUSED$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "unhideWhenUsed");
    private static final QName QFORMAT$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "qFormat");

    public CTLsdExceptionImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STString xgetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(NAME$0));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setName(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetName(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(NAME$0));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(NAME$0));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LOCKED$2));
            if (simpleValue == null) {
                return null;
            }
            return (STOnOff.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff xgetLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(LOCKED$2));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LOCKED$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLocked(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LOCKED$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LOCKED$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLocked(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(LOCKED$2));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(LOCKED$2));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LOCKED$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getUiPriority() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(UIPRIORITY$4));
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
    public STDecimalNumber xgetUiPriority() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber = null;
            sTDecimalNumber = (STDecimalNumber)((Object)this.get_store().find_attribute_user(UIPRIORITY$4));
            return sTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUiPriority() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(UIPRIORITY$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUiPriority(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(UIPRIORITY$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(UIPRIORITY$4));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUiPriority(STDecimalNumber sTDecimalNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber2 = null;
            sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().find_attribute_user(UIPRIORITY$4));
            if (sTDecimalNumber2 == null) {
                sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().add_attribute_user(UIPRIORITY$4));
            }
            sTDecimalNumber2.set(sTDecimalNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUiPriority() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(UIPRIORITY$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getSemiHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SEMIHIDDEN$6));
            if (simpleValue == null) {
                return null;
            }
            return (STOnOff.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff xgetSemiHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(SEMIHIDDEN$6));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSemiHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SEMIHIDDEN$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSemiHidden(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SEMIHIDDEN$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SEMIHIDDEN$6));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSemiHidden(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(SEMIHIDDEN$6));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(SEMIHIDDEN$6));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSemiHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SEMIHIDDEN$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getUnhideWhenUsed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(UNHIDEWHENUSED$8));
            if (simpleValue == null) {
                return null;
            }
            return (STOnOff.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff xgetUnhideWhenUsed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(UNHIDEWHENUSED$8));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUnhideWhenUsed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(UNHIDEWHENUSED$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUnhideWhenUsed(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(UNHIDEWHENUSED$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(UNHIDEWHENUSED$8));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUnhideWhenUsed(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(UNHIDEWHENUSED$8));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(UNHIDEWHENUSED$8));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUnhideWhenUsed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(UNHIDEWHENUSED$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getQFormat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(QFORMAT$10));
            if (simpleValue == null) {
                return null;
            }
            return (STOnOff.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff xgetQFormat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(QFORMAT$10));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetQFormat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(QFORMAT$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setQFormat(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(QFORMAT$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(QFORMAT$10));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetQFormat(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(QFORMAT$10));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(QFORMAT$10));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetQFormat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(QFORMAT$10);
        }
    }
}

