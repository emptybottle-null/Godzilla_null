/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STAlgClass;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STAlgType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STCryptProv;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;

public class CTDocProtectImpl
extends XmlComplexContentImpl
implements CTDocProtect {
    private static final long serialVersionUID = 1L;
    private static final QName EDIT$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "edit");
    private static final QName FORMATTING$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "formatting");
    private static final QName ENFORCEMENT$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "enforcement");
    private static final QName CRYPTPROVIDERTYPE$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cryptProviderType");
    private static final QName CRYPTALGORITHMCLASS$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cryptAlgorithmClass");
    private static final QName CRYPTALGORITHMTYPE$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cryptAlgorithmType");
    private static final QName CRYPTALGORITHMSID$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cryptAlgorithmSid");
    private static final QName CRYPTSPINCOUNT$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cryptSpinCount");
    private static final QName CRYPTPROVIDER$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cryptProvider");
    private static final QName ALGIDEXT$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "algIdExt");
    private static final QName ALGIDEXTSOURCE$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "algIdExtSource");
    private static final QName CRYPTPROVIDERTYPEEXT$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cryptProviderTypeExt");
    private static final QName CRYPTPROVIDERTYPEEXTSOURCE$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cryptProviderTypeExtSource");
    private static final QName HASH$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hash");
    private static final QName SALT$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "salt");

    public CTDocProtectImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STDocProtect.Enum getEdit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EDIT$0));
            if (simpleValue == null) {
                return null;
            }
            return (STDocProtect.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STDocProtect xgetEdit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDocProtect sTDocProtect = null;
            sTDocProtect = (STDocProtect)((Object)this.get_store().find_attribute_user(EDIT$0));
            return sTDocProtect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEdit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EDIT$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEdit(STDocProtect.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EDIT$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EDIT$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEdit(STDocProtect sTDocProtect) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDocProtect sTDocProtect2 = null;
            sTDocProtect2 = (STDocProtect)((Object)this.get_store().find_attribute_user(EDIT$0));
            if (sTDocProtect2 == null) {
                sTDocProtect2 = (STDocProtect)((Object)this.get_store().add_attribute_user(EDIT$0));
            }
            sTDocProtect2.set(sTDocProtect);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEdit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EDIT$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getFormatting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FORMATTING$2));
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
    public STOnOff xgetFormatting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(FORMATTING$2));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFormatting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FORMATTING$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFormatting(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FORMATTING$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FORMATTING$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFormatting(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(FORMATTING$2));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(FORMATTING$2));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFormatting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FORMATTING$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getEnforcement() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENFORCEMENT$4));
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
    public STOnOff xgetEnforcement() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(ENFORCEMENT$4));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEnforcement() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ENFORCEMENT$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEnforcement(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENFORCEMENT$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ENFORCEMENT$4));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEnforcement(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(ENFORCEMENT$4));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(ENFORCEMENT$4));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEnforcement() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ENFORCEMENT$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCryptProv.Enum getCryptProviderType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPE$6));
            if (simpleValue == null) {
                return null;
            }
            return (STCryptProv.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCryptProv xgetCryptProviderType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCryptProv sTCryptProv = null;
            sTCryptProv = (STCryptProv)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPE$6));
            return sTCryptProv;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCryptProviderType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CRYPTPROVIDERTYPE$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCryptProviderType(STCryptProv.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPE$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CRYPTPROVIDERTYPE$6));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCryptProviderType(STCryptProv sTCryptProv) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCryptProv sTCryptProv2 = null;
            sTCryptProv2 = (STCryptProv)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPE$6));
            if (sTCryptProv2 == null) {
                sTCryptProv2 = (STCryptProv)((Object)this.get_store().add_attribute_user(CRYPTPROVIDERTYPE$6));
            }
            sTCryptProv2.set(sTCryptProv);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCryptProviderType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CRYPTPROVIDERTYPE$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAlgClass.Enum getCryptAlgorithmClass() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMCLASS$8));
            if (simpleValue == null) {
                return null;
            }
            return (STAlgClass.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAlgClass xgetCryptAlgorithmClass() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAlgClass sTAlgClass = null;
            sTAlgClass = (STAlgClass)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMCLASS$8));
            return sTAlgClass;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCryptAlgorithmClass() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CRYPTALGORITHMCLASS$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCryptAlgorithmClass(STAlgClass.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMCLASS$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CRYPTALGORITHMCLASS$8));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCryptAlgorithmClass(STAlgClass sTAlgClass) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAlgClass sTAlgClass2 = null;
            sTAlgClass2 = (STAlgClass)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMCLASS$8));
            if (sTAlgClass2 == null) {
                sTAlgClass2 = (STAlgClass)((Object)this.get_store().add_attribute_user(CRYPTALGORITHMCLASS$8));
            }
            sTAlgClass2.set(sTAlgClass);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCryptAlgorithmClass() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CRYPTALGORITHMCLASS$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAlgType.Enum getCryptAlgorithmType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMTYPE$10));
            if (simpleValue == null) {
                return null;
            }
            return (STAlgType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAlgType xgetCryptAlgorithmType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAlgType sTAlgType = null;
            sTAlgType = (STAlgType)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMTYPE$10));
            return sTAlgType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCryptAlgorithmType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CRYPTALGORITHMTYPE$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCryptAlgorithmType(STAlgType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMTYPE$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CRYPTALGORITHMTYPE$10));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCryptAlgorithmType(STAlgType sTAlgType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAlgType sTAlgType2 = null;
            sTAlgType2 = (STAlgType)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMTYPE$10));
            if (sTAlgType2 == null) {
                sTAlgType2 = (STAlgType)((Object)this.get_store().add_attribute_user(CRYPTALGORITHMTYPE$10));
            }
            sTAlgType2.set(sTAlgType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCryptAlgorithmType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CRYPTALGORITHMTYPE$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getCryptAlgorithmSid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMSID$12));
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
    public STDecimalNumber xgetCryptAlgorithmSid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber = null;
            sTDecimalNumber = (STDecimalNumber)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMSID$12));
            return sTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCryptAlgorithmSid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CRYPTALGORITHMSID$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCryptAlgorithmSid(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMSID$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CRYPTALGORITHMSID$12));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCryptAlgorithmSid(STDecimalNumber sTDecimalNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber2 = null;
            sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().find_attribute_user(CRYPTALGORITHMSID$12));
            if (sTDecimalNumber2 == null) {
                sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().add_attribute_user(CRYPTALGORITHMSID$12));
            }
            sTDecimalNumber2.set(sTDecimalNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCryptAlgorithmSid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CRYPTALGORITHMSID$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getCryptSpinCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTSPINCOUNT$14));
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
    public STDecimalNumber xgetCryptSpinCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber = null;
            sTDecimalNumber = (STDecimalNumber)((Object)this.get_store().find_attribute_user(CRYPTSPINCOUNT$14));
            return sTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCryptSpinCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CRYPTSPINCOUNT$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCryptSpinCount(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTSPINCOUNT$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CRYPTSPINCOUNT$14));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCryptSpinCount(STDecimalNumber sTDecimalNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber2 = null;
            sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().find_attribute_user(CRYPTSPINCOUNT$14));
            if (sTDecimalNumber2 == null) {
                sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().add_attribute_user(CRYPTSPINCOUNT$14));
            }
            sTDecimalNumber2.set(sTDecimalNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCryptSpinCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CRYPTSPINCOUNT$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getCryptProvider() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTPROVIDER$16));
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
    public STString xgetCryptProvider() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(CRYPTPROVIDER$16));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCryptProvider() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CRYPTPROVIDER$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCryptProvider(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTPROVIDER$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CRYPTPROVIDER$16));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCryptProvider(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(CRYPTPROVIDER$16));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(CRYPTPROVIDER$16));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCryptProvider() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CRYPTPROVIDER$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getAlgIdExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGIDEXT$18));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLongHexNumber xgetAlgIdExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber = null;
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(ALGIDEXT$18));
            return sTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlgIdExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALGIDEXT$18) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlgIdExt(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGIDEXT$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALGIDEXT$18));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlgIdExt(STLongHexNumber sTLongHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber2 = null;
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(ALGIDEXT$18));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(ALGIDEXT$18));
            }
            sTLongHexNumber2.set(sTLongHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlgIdExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALGIDEXT$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAlgIdExtSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGIDEXTSOURCE$20));
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
    public STString xgetAlgIdExtSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(ALGIDEXTSOURCE$20));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlgIdExtSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALGIDEXTSOURCE$20) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlgIdExtSource(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGIDEXTSOURCE$20));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALGIDEXTSOURCE$20));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlgIdExtSource(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(ALGIDEXTSOURCE$20));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(ALGIDEXTSOURCE$20));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlgIdExtSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALGIDEXTSOURCE$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getCryptProviderTypeExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPEEXT$22));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLongHexNumber xgetCryptProviderTypeExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber = null;
            sTLongHexNumber = (STLongHexNumber)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPEEXT$22));
            return sTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCryptProviderTypeExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CRYPTPROVIDERTYPEEXT$22) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCryptProviderTypeExt(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPEEXT$22));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CRYPTPROVIDERTYPEEXT$22));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCryptProviderTypeExt(STLongHexNumber sTLongHexNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLongHexNumber sTLongHexNumber2 = null;
            sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPEEXT$22));
            if (sTLongHexNumber2 == null) {
                sTLongHexNumber2 = (STLongHexNumber)((Object)this.get_store().add_attribute_user(CRYPTPROVIDERTYPEEXT$22));
            }
            sTLongHexNumber2.set(sTLongHexNumber);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCryptProviderTypeExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CRYPTPROVIDERTYPEEXT$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getCryptProviderTypeExtSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPEEXTSOURCE$24));
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
    public STString xgetCryptProviderTypeExtSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPEEXTSOURCE$24));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCryptProviderTypeExtSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CRYPTPROVIDERTYPEEXTSOURCE$24) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCryptProviderTypeExtSource(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPEEXTSOURCE$24));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CRYPTPROVIDERTYPEEXTSOURCE$24));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCryptProviderTypeExtSource(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(CRYPTPROVIDERTYPEEXTSOURCE$24));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(CRYPTPROVIDERTYPEEXTSOURCE$24));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCryptProviderTypeExtSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CRYPTPROVIDERTYPEEXTSOURCE$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getHash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HASH$26));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBase64Binary xgetHash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_attribute_user(HASH$26));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HASH$26) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHash(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HASH$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HASH$26));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHash(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_attribute_user(HASH$26));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_attribute_user(HASH$26));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HASH$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getSalt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SALT$28));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBase64Binary xgetSalt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_attribute_user(SALT$28));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSalt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SALT$28) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSalt(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SALT$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SALT$28));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSalt(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_attribute_user(SALT$28));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_attribute_user(SALT$28));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSalt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SALT$28);
        }
    }
}

