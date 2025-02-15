/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption.impl;

import com.microsoft.schemas.office.x2006.encryption.CTKeyData;
import com.microsoft.schemas.office.x2006.encryption.STBlockSize;
import com.microsoft.schemas.office.x2006.encryption.STCipherAlgorithm;
import com.microsoft.schemas.office.x2006.encryption.STCipherChaining;
import com.microsoft.schemas.office.x2006.encryption.STHashAlgorithm;
import com.microsoft.schemas.office.x2006.encryption.STHashSize;
import com.microsoft.schemas.office.x2006.encryption.STKeyBits;
import com.microsoft.schemas.office.x2006.encryption.STSaltSize;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTKeyDataImpl
extends XmlComplexContentImpl
implements CTKeyData {
    private static final long serialVersionUID = 1L;
    private static final QName SALTSIZE$0 = new QName("", "saltSize");
    private static final QName BLOCKSIZE$2 = new QName("", "blockSize");
    private static final QName KEYBITS$4 = new QName("", "keyBits");
    private static final QName HASHSIZE$6 = new QName("", "hashSize");
    private static final QName CIPHERALGORITHM$8 = new QName("", "cipherAlgorithm");
    private static final QName CIPHERCHAINING$10 = new QName("", "cipherChaining");
    private static final QName HASHALGORITHM$12 = new QName("", "hashAlgorithm");
    private static final QName SALTVALUE$14 = new QName("", "saltValue");

    public CTKeyDataImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSaltSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SALTSIZE$0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STSaltSize xgetSaltSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSaltSize sTSaltSize = null;
            sTSaltSize = (STSaltSize)((Object)this.get_store().find_attribute_user(SALTSIZE$0));
            return sTSaltSize;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSaltSize(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SALTSIZE$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SALTSIZE$0));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSaltSize(STSaltSize sTSaltSize) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSaltSize sTSaltSize2 = null;
            sTSaltSize2 = (STSaltSize)((Object)this.get_store().find_attribute_user(SALTSIZE$0));
            if (sTSaltSize2 == null) {
                sTSaltSize2 = (STSaltSize)((Object)this.get_store().add_attribute_user(SALTSIZE$0));
            }
            sTSaltSize2.set(sTSaltSize);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getBlockSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BLOCKSIZE$2));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBlockSize xgetBlockSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBlockSize sTBlockSize = null;
            sTBlockSize = (STBlockSize)((Object)this.get_store().find_attribute_user(BLOCKSIZE$2));
            return sTBlockSize;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBlockSize(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BLOCKSIZE$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BLOCKSIZE$2));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBlockSize(STBlockSize sTBlockSize) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBlockSize sTBlockSize2 = null;
            sTBlockSize2 = (STBlockSize)((Object)this.get_store().find_attribute_user(BLOCKSIZE$2));
            if (sTBlockSize2 == null) {
                sTBlockSize2 = (STBlockSize)((Object)this.get_store().add_attribute_user(BLOCKSIZE$2));
            }
            sTBlockSize2.set(sTBlockSize);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getKeyBits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(KEYBITS$4));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STKeyBits xgetKeyBits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STKeyBits sTKeyBits = null;
            sTKeyBits = (STKeyBits)((Object)this.get_store().find_attribute_user(KEYBITS$4));
            return sTKeyBits;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setKeyBits(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(KEYBITS$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(KEYBITS$4));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetKeyBits(STKeyBits sTKeyBits) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STKeyBits sTKeyBits2 = null;
            sTKeyBits2 = (STKeyBits)((Object)this.get_store().find_attribute_user(KEYBITS$4));
            if (sTKeyBits2 == null) {
                sTKeyBits2 = (STKeyBits)((Object)this.get_store().add_attribute_user(KEYBITS$4));
            }
            sTKeyBits2.set(sTKeyBits);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getHashSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HASHSIZE$6));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHashSize xgetHashSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHashSize sTHashSize = null;
            sTHashSize = (STHashSize)((Object)this.get_store().find_attribute_user(HASHSIZE$6));
            return sTHashSize;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHashSize(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HASHSIZE$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HASHSIZE$6));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHashSize(STHashSize sTHashSize) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHashSize sTHashSize2 = null;
            sTHashSize2 = (STHashSize)((Object)this.get_store().find_attribute_user(HASHSIZE$6));
            if (sTHashSize2 == null) {
                sTHashSize2 = (STHashSize)((Object)this.get_store().add_attribute_user(HASHSIZE$6));
            }
            sTHashSize2.set(sTHashSize);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCipherAlgorithm.Enum getCipherAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CIPHERALGORITHM$8));
            if (simpleValue == null) {
                return null;
            }
            return (STCipherAlgorithm.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCipherAlgorithm xgetCipherAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCipherAlgorithm sTCipherAlgorithm = null;
            sTCipherAlgorithm = (STCipherAlgorithm)((Object)this.get_store().find_attribute_user(CIPHERALGORITHM$8));
            return sTCipherAlgorithm;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCipherAlgorithm(STCipherAlgorithm.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CIPHERALGORITHM$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CIPHERALGORITHM$8));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCipherAlgorithm(STCipherAlgorithm sTCipherAlgorithm) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCipherAlgorithm sTCipherAlgorithm2 = null;
            sTCipherAlgorithm2 = (STCipherAlgorithm)((Object)this.get_store().find_attribute_user(CIPHERALGORITHM$8));
            if (sTCipherAlgorithm2 == null) {
                sTCipherAlgorithm2 = (STCipherAlgorithm)((Object)this.get_store().add_attribute_user(CIPHERALGORITHM$8));
            }
            sTCipherAlgorithm2.set(sTCipherAlgorithm);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCipherChaining.Enum getCipherChaining() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CIPHERCHAINING$10));
            if (simpleValue == null) {
                return null;
            }
            return (STCipherChaining.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCipherChaining xgetCipherChaining() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCipherChaining sTCipherChaining = null;
            sTCipherChaining = (STCipherChaining)((Object)this.get_store().find_attribute_user(CIPHERCHAINING$10));
            return sTCipherChaining;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCipherChaining(STCipherChaining.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CIPHERCHAINING$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CIPHERCHAINING$10));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCipherChaining(STCipherChaining sTCipherChaining) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCipherChaining sTCipherChaining2 = null;
            sTCipherChaining2 = (STCipherChaining)((Object)this.get_store().find_attribute_user(CIPHERCHAINING$10));
            if (sTCipherChaining2 == null) {
                sTCipherChaining2 = (STCipherChaining)((Object)this.get_store().add_attribute_user(CIPHERCHAINING$10));
            }
            sTCipherChaining2.set(sTCipherChaining);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHashAlgorithm.Enum getHashAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HASHALGORITHM$12));
            if (simpleValue == null) {
                return null;
            }
            return (STHashAlgorithm.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHashAlgorithm xgetHashAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHashAlgorithm sTHashAlgorithm = null;
            sTHashAlgorithm = (STHashAlgorithm)((Object)this.get_store().find_attribute_user(HASHALGORITHM$12));
            return sTHashAlgorithm;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHashAlgorithm(STHashAlgorithm.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HASHALGORITHM$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HASHALGORITHM$12));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHashAlgorithm(STHashAlgorithm sTHashAlgorithm) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHashAlgorithm sTHashAlgorithm2 = null;
            sTHashAlgorithm2 = (STHashAlgorithm)((Object)this.get_store().find_attribute_user(HASHALGORITHM$12));
            if (sTHashAlgorithm2 == null) {
                sTHashAlgorithm2 = (STHashAlgorithm)((Object)this.get_store().add_attribute_user(HASHALGORITHM$12));
            }
            sTHashAlgorithm2.set(sTHashAlgorithm);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getSaltValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SALTVALUE$14));
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
    public XmlBase64Binary xgetSaltValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_attribute_user(SALTVALUE$14));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSaltValue(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SALTVALUE$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SALTVALUE$14));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSaltValue(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_attribute_user(SALTVALUE$14));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_attribute_user(SALTVALUE$14));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }
}

