/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMultiLevelType
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMultiLevelType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;

public class CTAbstractNumImpl
extends XmlComplexContentImpl
implements CTAbstractNum {
    private static final long serialVersionUID = 1L;
    private static final QName NSID$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "nsid");
    private static final QName MULTILEVELTYPE$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "multiLevelType");
    private static final QName TMPL$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tmpl");
    private static final QName NAME$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "name");
    private static final QName STYLELINK$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "styleLink");
    private static final QName NUMSTYLELINK$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numStyleLink");
    private static final QName LVL$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lvl");
    private static final QName ABSTRACTNUMID$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "abstractNumId");

    public CTAbstractNumImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLongHexNumber getNsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLongHexNumber cTLongHexNumber = null;
            cTLongHexNumber = (CTLongHexNumber)this.get_store().find_element_user(NSID$0, 0);
            if (cTLongHexNumber == null) {
                return null;
            }
            return cTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NSID$0) != 0;
        }
    }

    @Override
    public void setNsid(CTLongHexNumber cTLongHexNumber) {
        this.generatedSetterHelperImpl((XmlObject)cTLongHexNumber, NSID$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLongHexNumber addNewNsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLongHexNumber cTLongHexNumber = null;
            cTLongHexNumber = (CTLongHexNumber)this.get_store().add_element_user(NSID$0);
            return cTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NSID$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMultiLevelType getMultiLevelType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMultiLevelType cTMultiLevelType = null;
            cTMultiLevelType = (CTMultiLevelType)this.get_store().find_element_user(MULTILEVELTYPE$2, 0);
            if (cTMultiLevelType == null) {
                return null;
            }
            return cTMultiLevelType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMultiLevelType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MULTILEVELTYPE$2) != 0;
        }
    }

    @Override
    public void setMultiLevelType(CTMultiLevelType cTMultiLevelType) {
        this.generatedSetterHelperImpl((XmlObject)cTMultiLevelType, MULTILEVELTYPE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMultiLevelType addNewMultiLevelType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMultiLevelType cTMultiLevelType = null;
            cTMultiLevelType = (CTMultiLevelType)this.get_store().add_element_user(MULTILEVELTYPE$2);
            return cTMultiLevelType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMultiLevelType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MULTILEVELTYPE$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLongHexNumber getTmpl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLongHexNumber cTLongHexNumber = null;
            cTLongHexNumber = (CTLongHexNumber)this.get_store().find_element_user(TMPL$4, 0);
            if (cTLongHexNumber == null) {
                return null;
            }
            return cTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTmpl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TMPL$4) != 0;
        }
    }

    @Override
    public void setTmpl(CTLongHexNumber cTLongHexNumber) {
        this.generatedSetterHelperImpl((XmlObject)cTLongHexNumber, TMPL$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLongHexNumber addNewTmpl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLongHexNumber cTLongHexNumber = null;
            cTLongHexNumber = (CTLongHexNumber)this.get_store().add_element_user(TMPL$4);
            return cTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTmpl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TMPL$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(NAME$6, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NAME$6) != 0;
        }
    }

    @Override
    public void setName(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, NAME$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(NAME$6));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NAME$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getStyleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(STYLELINK$8, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStyleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STYLELINK$8) != 0;
        }
    }

    @Override
    public void setStyleLink(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, STYLELINK$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewStyleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(STYLELINK$8));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStyleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STYLELINK$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getNumStyleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(NUMSTYLELINK$10, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNumStyleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMSTYLELINK$10) != 0;
        }
    }

    @Override
    public void setNumStyleLink(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, NUMSTYLELINK$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewNumStyleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(NUMSTYLELINK$10));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNumStyleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMSTYLELINK$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLvl> getLvlList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LvlList
            extends AbstractList<CTLvl> {
                LvlList() {
                }

                @Override
                public CTLvl get(int n) {
                    return CTAbstractNumImpl.this.getLvlArray(n);
                }

                @Override
                public CTLvl set(int n, CTLvl cTLvl) {
                    CTLvl cTLvl2 = CTAbstractNumImpl.this.getLvlArray(n);
                    CTAbstractNumImpl.this.setLvlArray(n, cTLvl);
                    return cTLvl2;
                }

                @Override
                public void add(int n, CTLvl cTLvl) {
                    CTAbstractNumImpl.this.insertNewLvl(n).set(cTLvl);
                }

                @Override
                public CTLvl remove(int n) {
                    CTLvl cTLvl = CTAbstractNumImpl.this.getLvlArray(n);
                    CTAbstractNumImpl.this.removeLvl(n);
                    return cTLvl;
                }

                @Override
                public int size() {
                    return CTAbstractNumImpl.this.sizeOfLvlArray();
                }
            }
            return new LvlList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTLvl[] getLvlArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LVL$12, arrayList);
            CTLvl[] cTLvlArray = new CTLvl[arrayList.size()];
            arrayList.toArray(cTLvlArray);
            return cTLvlArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLvl getLvlArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLvl cTLvl = null;
            cTLvl = (CTLvl)((Object)this.get_store().find_element_user(LVL$12, n));
            if (cTLvl == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLvl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLvlArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LVL$12);
        }
    }

    @Override
    public void setLvlArray(CTLvl[] cTLvlArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTLvlArray, LVL$12);
    }

    @Override
    public void setLvlArray(int n, CTLvl cTLvl) {
        this.generatedSetterHelperImpl(cTLvl, LVL$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLvl insertNewLvl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLvl cTLvl = null;
            cTLvl = (CTLvl)((Object)this.get_store().insert_element_user(LVL$12, n));
            return cTLvl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLvl addNewLvl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLvl cTLvl = null;
            cTLvl = (CTLvl)((Object)this.get_store().add_element_user(LVL$12));
            return cTLvl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLvl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LVL$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getAbstractNumId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ABSTRACTNUMID$14));
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
    public STDecimalNumber xgetAbstractNumId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber = null;
            sTDecimalNumber = (STDecimalNumber)((Object)this.get_store().find_attribute_user(ABSTRACTNUMID$14));
            return sTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAbstractNumId(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ABSTRACTNUMID$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ABSTRACTNUMID$14));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAbstractNumId(STDecimalNumber sTDecimalNumber) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDecimalNumber sTDecimalNumber2 = null;
            sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().find_attribute_user(ABSTRACTNUMID$14));
            if (sTDecimalNumber2 == null) {
                sTDecimalNumber2 = (STDecimalNumber)((Object)this.get_store().add_attribute_user(ABSTRACTNUMID$14));
            }
            sTDecimalNumber2.set(sTDecimalNumber);
        }
    }
}

