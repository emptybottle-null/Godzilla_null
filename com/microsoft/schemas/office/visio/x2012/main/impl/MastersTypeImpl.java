/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.MasterShortcutType
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.MasterShortcutType;
import com.microsoft.schemas.office.visio.x2012.main.MasterType;
import com.microsoft.schemas.office.visio.x2012.main.MastersType;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class MastersTypeImpl
extends XmlComplexContentImpl
implements MastersType {
    private static final long serialVersionUID = 1L;
    private static final QName MASTER$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Master");
    private static final QName MASTERSHORTCUT$2 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "MasterShortcut");

    public MastersTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<MasterType> getMasterList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MasterList
            extends AbstractList<MasterType> {
                MasterList() {
                }

                @Override
                public MasterType get(int n) {
                    return MastersTypeImpl.this.getMasterArray(n);
                }

                @Override
                public MasterType set(int n, MasterType masterType) {
                    MasterType masterType2 = MastersTypeImpl.this.getMasterArray(n);
                    MastersTypeImpl.this.setMasterArray(n, masterType);
                    return masterType2;
                }

                @Override
                public void add(int n, MasterType masterType) {
                    MastersTypeImpl.this.insertNewMaster(n).set(masterType);
                }

                @Override
                public MasterType remove(int n) {
                    MasterType masterType = MastersTypeImpl.this.getMasterArray(n);
                    MastersTypeImpl.this.removeMaster(n);
                    return masterType;
                }

                @Override
                public int size() {
                    return MastersTypeImpl.this.sizeOfMasterArray();
                }
            }
            return new MasterList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public MasterType[] getMasterArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MASTER$0, arrayList);
            MasterType[] masterTypeArray = new MasterType[arrayList.size()];
            arrayList.toArray(masterTypeArray);
            return masterTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public MasterType getMasterArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            MasterType masterType = null;
            masterType = (MasterType)((Object)this.get_store().find_element_user(MASTER$0, n));
            if (masterType == null) {
                throw new IndexOutOfBoundsException();
            }
            return masterType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMasterArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MASTER$0);
        }
    }

    @Override
    public void setMasterArray(MasterType[] masterTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(masterTypeArray, MASTER$0);
    }

    @Override
    public void setMasterArray(int n, MasterType masterType) {
        this.generatedSetterHelperImpl(masterType, MASTER$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public MasterType insertNewMaster(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            MasterType masterType = null;
            masterType = (MasterType)((Object)this.get_store().insert_element_user(MASTER$0, n));
            return masterType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public MasterType addNewMaster() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            MasterType masterType = null;
            masterType = (MasterType)((Object)this.get_store().add_element_user(MASTER$0));
            return masterType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMaster(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MASTER$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<MasterShortcutType> getMasterShortcutList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MasterShortcutList
            extends AbstractList<MasterShortcutType> {
                MasterShortcutList() {
                }

                @Override
                public MasterShortcutType get(int n) {
                    return MastersTypeImpl.this.getMasterShortcutArray(n);
                }

                @Override
                public MasterShortcutType set(int n, MasterShortcutType masterShortcutType) {
                    MasterShortcutType masterShortcutType2 = MastersTypeImpl.this.getMasterShortcutArray(n);
                    MastersTypeImpl.this.setMasterShortcutArray(n, masterShortcutType);
                    return masterShortcutType2;
                }

                @Override
                public void add(int n, MasterShortcutType masterShortcutType) {
                    MastersTypeImpl.this.insertNewMasterShortcut(n).set((XmlObject)masterShortcutType);
                }

                @Override
                public MasterShortcutType remove(int n) {
                    MasterShortcutType masterShortcutType = MastersTypeImpl.this.getMasterShortcutArray(n);
                    MastersTypeImpl.this.removeMasterShortcut(n);
                    return masterShortcutType;
                }

                @Override
                public int size() {
                    return MastersTypeImpl.this.sizeOfMasterShortcutArray();
                }
            }
            return new MasterShortcutList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public MasterShortcutType[] getMasterShortcutArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MASTERSHORTCUT$2, arrayList);
            MasterShortcutType[] masterShortcutTypeArray = new MasterShortcutType[arrayList.size()];
            arrayList.toArray(masterShortcutTypeArray);
            return masterShortcutTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public MasterShortcutType getMasterShortcutArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            MasterShortcutType masterShortcutType = null;
            masterShortcutType = (MasterShortcutType)this.get_store().find_element_user(MASTERSHORTCUT$2, n);
            if (masterShortcutType == null) {
                throw new IndexOutOfBoundsException();
            }
            return masterShortcutType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMasterShortcutArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MASTERSHORTCUT$2);
        }
    }

    @Override
    public void setMasterShortcutArray(MasterShortcutType[] masterShortcutTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])masterShortcutTypeArray, MASTERSHORTCUT$2);
    }

    @Override
    public void setMasterShortcutArray(int n, MasterShortcutType masterShortcutType) {
        this.generatedSetterHelperImpl((XmlObject)masterShortcutType, MASTERSHORTCUT$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public MasterShortcutType insertNewMasterShortcut(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            MasterShortcutType masterShortcutType = null;
            masterShortcutType = (MasterShortcutType)this.get_store().insert_element_user(MASTERSHORTCUT$2, n);
            return masterShortcutType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public MasterShortcutType addNewMasterShortcut() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            MasterShortcutType masterShortcutType = null;
            masterShortcutType = (MasterShortcutType)this.get_store().add_element_user(MASTERSHORTCUT$2);
            return masterShortcutType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMasterShortcut(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MASTERSHORTCUT$2, n);
        }
    }
}

