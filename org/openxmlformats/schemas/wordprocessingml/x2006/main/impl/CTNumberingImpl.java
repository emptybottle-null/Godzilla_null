/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPicBullet
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPicBullet;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;

public class CTNumberingImpl
extends XmlComplexContentImpl
implements CTNumbering {
    private static final long serialVersionUID = 1L;
    private static final QName NUMPICBULLET$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numPicBullet");
    private static final QName ABSTRACTNUM$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "abstractNum");
    private static final QName NUM$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "num");
    private static final QName NUMIDMACATCLEANUP$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numIdMacAtCleanup");

    public CTNumberingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTNumPicBullet> getNumPicBulletList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NumPicBulletList
            extends AbstractList<CTNumPicBullet> {
                NumPicBulletList() {
                }

                @Override
                public CTNumPicBullet get(int n) {
                    return CTNumberingImpl.this.getNumPicBulletArray(n);
                }

                @Override
                public CTNumPicBullet set(int n, CTNumPicBullet cTNumPicBullet) {
                    CTNumPicBullet cTNumPicBullet2 = CTNumberingImpl.this.getNumPicBulletArray(n);
                    CTNumberingImpl.this.setNumPicBulletArray(n, cTNumPicBullet);
                    return cTNumPicBullet2;
                }

                @Override
                public void add(int n, CTNumPicBullet cTNumPicBullet) {
                    CTNumberingImpl.this.insertNewNumPicBullet(n).set((XmlObject)cTNumPicBullet);
                }

                @Override
                public CTNumPicBullet remove(int n) {
                    CTNumPicBullet cTNumPicBullet = CTNumberingImpl.this.getNumPicBulletArray(n);
                    CTNumberingImpl.this.removeNumPicBullet(n);
                    return cTNumPicBullet;
                }

                @Override
                public int size() {
                    return CTNumberingImpl.this.sizeOfNumPicBulletArray();
                }
            }
            return new NumPicBulletList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTNumPicBullet[] getNumPicBulletArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NUMPICBULLET$0, arrayList);
            CTNumPicBullet[] cTNumPicBulletArray = new CTNumPicBullet[arrayList.size()];
            arrayList.toArray(cTNumPicBulletArray);
            return cTNumPicBulletArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumPicBullet getNumPicBulletArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumPicBullet cTNumPicBullet = null;
            cTNumPicBullet = (CTNumPicBullet)this.get_store().find_element_user(NUMPICBULLET$0, n);
            if (cTNumPicBullet == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTNumPicBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfNumPicBulletArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMPICBULLET$0);
        }
    }

    @Override
    public void setNumPicBulletArray(CTNumPicBullet[] cTNumPicBulletArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTNumPicBulletArray, NUMPICBULLET$0);
    }

    @Override
    public void setNumPicBulletArray(int n, CTNumPicBullet cTNumPicBullet) {
        this.generatedSetterHelperImpl((XmlObject)cTNumPicBullet, NUMPICBULLET$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumPicBullet insertNewNumPicBullet(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumPicBullet cTNumPicBullet = null;
            cTNumPicBullet = (CTNumPicBullet)this.get_store().insert_element_user(NUMPICBULLET$0, n);
            return cTNumPicBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumPicBullet addNewNumPicBullet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumPicBullet cTNumPicBullet = null;
            cTNumPicBullet = (CTNumPicBullet)this.get_store().add_element_user(NUMPICBULLET$0);
            return cTNumPicBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeNumPicBullet(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMPICBULLET$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAbstractNum> getAbstractNumList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AbstractNumList
            extends AbstractList<CTAbstractNum> {
                AbstractNumList() {
                }

                @Override
                public CTAbstractNum get(int n) {
                    return CTNumberingImpl.this.getAbstractNumArray(n);
                }

                @Override
                public CTAbstractNum set(int n, CTAbstractNum cTAbstractNum) {
                    CTAbstractNum cTAbstractNum2 = CTNumberingImpl.this.getAbstractNumArray(n);
                    CTNumberingImpl.this.setAbstractNumArray(n, cTAbstractNum);
                    return cTAbstractNum2;
                }

                @Override
                public void add(int n, CTAbstractNum cTAbstractNum) {
                    CTNumberingImpl.this.insertNewAbstractNum(n).set(cTAbstractNum);
                }

                @Override
                public CTAbstractNum remove(int n) {
                    CTAbstractNum cTAbstractNum = CTNumberingImpl.this.getAbstractNumArray(n);
                    CTNumberingImpl.this.removeAbstractNum(n);
                    return cTAbstractNum;
                }

                @Override
                public int size() {
                    return CTNumberingImpl.this.sizeOfAbstractNumArray();
                }
            }
            return new AbstractNumList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAbstractNum[] getAbstractNumArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ABSTRACTNUM$2, arrayList);
            CTAbstractNum[] cTAbstractNumArray = new CTAbstractNum[arrayList.size()];
            arrayList.toArray(cTAbstractNumArray);
            return cTAbstractNumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAbstractNum getAbstractNumArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAbstractNum cTAbstractNum = null;
            cTAbstractNum = (CTAbstractNum)((Object)this.get_store().find_element_user(ABSTRACTNUM$2, n));
            if (cTAbstractNum == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAbstractNum;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAbstractNumArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ABSTRACTNUM$2);
        }
    }

    @Override
    public void setAbstractNumArray(CTAbstractNum[] cTAbstractNumArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTAbstractNumArray, ABSTRACTNUM$2);
    }

    @Override
    public void setAbstractNumArray(int n, CTAbstractNum cTAbstractNum) {
        this.generatedSetterHelperImpl(cTAbstractNum, ABSTRACTNUM$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAbstractNum insertNewAbstractNum(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAbstractNum cTAbstractNum = null;
            cTAbstractNum = (CTAbstractNum)((Object)this.get_store().insert_element_user(ABSTRACTNUM$2, n));
            return cTAbstractNum;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAbstractNum addNewAbstractNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAbstractNum cTAbstractNum = null;
            cTAbstractNum = (CTAbstractNum)((Object)this.get_store().add_element_user(ABSTRACTNUM$2));
            return cTAbstractNum;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAbstractNum(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ABSTRACTNUM$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTNum> getNumList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NumList
            extends AbstractList<CTNum> {
                NumList() {
                }

                @Override
                public CTNum get(int n) {
                    return CTNumberingImpl.this.getNumArray(n);
                }

                @Override
                public CTNum set(int n, CTNum cTNum) {
                    CTNum cTNum2 = CTNumberingImpl.this.getNumArray(n);
                    CTNumberingImpl.this.setNumArray(n, cTNum);
                    return cTNum2;
                }

                @Override
                public void add(int n, CTNum cTNum) {
                    CTNumberingImpl.this.insertNewNum(n).set(cTNum);
                }

                @Override
                public CTNum remove(int n) {
                    CTNum cTNum = CTNumberingImpl.this.getNumArray(n);
                    CTNumberingImpl.this.removeNum(n);
                    return cTNum;
                }

                @Override
                public int size() {
                    return CTNumberingImpl.this.sizeOfNumArray();
                }
            }
            return new NumList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTNum[] getNumArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NUM$4, arrayList);
            CTNum[] cTNumArray = new CTNum[arrayList.size()];
            arrayList.toArray(cTNumArray);
            return cTNumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNum getNumArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNum cTNum = null;
            cTNum = (CTNum)((Object)this.get_store().find_element_user(NUM$4, n));
            if (cTNum == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTNum;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfNumArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUM$4);
        }
    }

    @Override
    public void setNumArray(CTNum[] cTNumArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTNumArray, NUM$4);
    }

    @Override
    public void setNumArray(int n, CTNum cTNum) {
        this.generatedSetterHelperImpl(cTNum, NUM$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNum insertNewNum(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNum cTNum = null;
            cTNum = (CTNum)((Object)this.get_store().insert_element_user(NUM$4, n));
            return cTNum;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNum addNewNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNum cTNum = null;
            cTNum = (CTNum)((Object)this.get_store().add_element_user(NUM$4));
            return cTNum;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeNum(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUM$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getNumIdMacAtCleanup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(NUMIDMACATCLEANUP$6, 0));
            if (cTDecimalNumber == null) {
                return null;
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNumIdMacAtCleanup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMIDMACATCLEANUP$6) != 0;
        }
    }

    @Override
    public void setNumIdMacAtCleanup(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, NUMIDMACATCLEANUP$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewNumIdMacAtCleanup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(NUMIDMACATCLEANUP$6));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNumIdMacAtCleanup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMIDMACATCLEANUP$6, 0);
        }
    }
}

