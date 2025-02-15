/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;

public class CTNumImpl
extends XmlComplexContentImpl
implements CTNum {
    private static final long serialVersionUID = 1L;
    private static final QName ABSTRACTNUMID$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "abstractNumId");
    private static final QName LVLOVERRIDE$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lvlOverride");
    private static final QName NUMID$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numId");

    public CTNumImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getAbstractNumId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(ABSTRACTNUMID$0, 0));
            if (cTDecimalNumber == null) {
                return null;
            }
            return cTDecimalNumber;
        }
    }

    @Override
    public void setAbstractNumId(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, ABSTRACTNUMID$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewAbstractNumId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(ABSTRACTNUMID$0));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTNumLvl> getLvlOverrideList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LvlOverrideList
            extends AbstractList<CTNumLvl> {
                LvlOverrideList() {
                }

                @Override
                public CTNumLvl get(int n) {
                    return CTNumImpl.this.getLvlOverrideArray(n);
                }

                @Override
                public CTNumLvl set(int n, CTNumLvl cTNumLvl) {
                    CTNumLvl cTNumLvl2 = CTNumImpl.this.getLvlOverrideArray(n);
                    CTNumImpl.this.setLvlOverrideArray(n, cTNumLvl);
                    return cTNumLvl2;
                }

                @Override
                public void add(int n, CTNumLvl cTNumLvl) {
                    CTNumImpl.this.insertNewLvlOverride(n).set(cTNumLvl);
                }

                @Override
                public CTNumLvl remove(int n) {
                    CTNumLvl cTNumLvl = CTNumImpl.this.getLvlOverrideArray(n);
                    CTNumImpl.this.removeLvlOverride(n);
                    return cTNumLvl;
                }

                @Override
                public int size() {
                    return CTNumImpl.this.sizeOfLvlOverrideArray();
                }
            }
            return new LvlOverrideList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTNumLvl[] getLvlOverrideArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LVLOVERRIDE$2, arrayList);
            CTNumLvl[] cTNumLvlArray = new CTNumLvl[arrayList.size()];
            arrayList.toArray(cTNumLvlArray);
            return cTNumLvlArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumLvl getLvlOverrideArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumLvl cTNumLvl = null;
            cTNumLvl = (CTNumLvl)((Object)this.get_store().find_element_user(LVLOVERRIDE$2, n));
            if (cTNumLvl == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTNumLvl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLvlOverrideArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVLOVERRIDE$2);
        }
    }

    @Override
    public void setLvlOverrideArray(CTNumLvl[] cTNumLvlArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTNumLvlArray, LVLOVERRIDE$2);
    }

    @Override
    public void setLvlOverrideArray(int n, CTNumLvl cTNumLvl) {
        this.generatedSetterHelperImpl(cTNumLvl, LVLOVERRIDE$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumLvl insertNewLvlOverride(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumLvl cTNumLvl = null;
            cTNumLvl = (CTNumLvl)((Object)this.get_store().insert_element_user(LVLOVERRIDE$2, n));
            return cTNumLvl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumLvl addNewLvlOverride() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumLvl cTNumLvl = null;
            cTNumLvl = (CTNumLvl)((Object)this.get_store().add_element_user(LVLOVERRIDE$2));
            return cTNumLvl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLvlOverride(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVLOVERRIDE$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getNumId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NUMID$4));
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
    public STDecimalNumber xgetNumId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber = null;
            sTDecimalNumber = (STDecimalNumber)((Object)this.get_store().find_attribute_user(NUMID$4));
            return sTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNumId(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NUMID$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NUMID$4));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetNumId(STDecimalNumber sTDecimalNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber2 = null;
            sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().find_attribute_user(NUMID$4));
            if (sTDecimalNumber2 == null) {
                sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().add_attribute_user(NUMID$4));
            }
            sTDecimalNumber2.set(sTDecimalNumber);
        }
    }
}

