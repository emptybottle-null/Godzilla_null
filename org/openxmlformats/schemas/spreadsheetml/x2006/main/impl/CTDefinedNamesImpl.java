/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames;

public class CTDefinedNamesImpl
extends XmlComplexContentImpl
implements CTDefinedNames {
    private static final long serialVersionUID = 1L;
    private static final QName DEFINEDNAME$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "definedName");

    public CTDefinedNamesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDefinedName> getDefinedNameList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DefinedNameList
            extends AbstractList<CTDefinedName> {
                DefinedNameList() {
                }

                @Override
                public CTDefinedName get(int n) {
                    return CTDefinedNamesImpl.this.getDefinedNameArray(n);
                }

                @Override
                public CTDefinedName set(int n, CTDefinedName cTDefinedName) {
                    CTDefinedName cTDefinedName2 = CTDefinedNamesImpl.this.getDefinedNameArray(n);
                    CTDefinedNamesImpl.this.setDefinedNameArray(n, cTDefinedName);
                    return cTDefinedName2;
                }

                @Override
                public void add(int n, CTDefinedName cTDefinedName) {
                    CTDefinedNamesImpl.this.insertNewDefinedName(n).set(cTDefinedName);
                }

                @Override
                public CTDefinedName remove(int n) {
                    CTDefinedName cTDefinedName = CTDefinedNamesImpl.this.getDefinedNameArray(n);
                    CTDefinedNamesImpl.this.removeDefinedName(n);
                    return cTDefinedName;
                }

                @Override
                public int size() {
                    return CTDefinedNamesImpl.this.sizeOfDefinedNameArray();
                }
            }
            return new DefinedNameList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDefinedName[] getDefinedNameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DEFINEDNAME$0, arrayList);
            CTDefinedName[] cTDefinedNameArray = new CTDefinedName[arrayList.size()];
            arrayList.toArray(cTDefinedNameArray);
            return cTDefinedNameArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDefinedName getDefinedNameArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDefinedName cTDefinedName = null;
            cTDefinedName = (CTDefinedName)((Object)this.get_store().find_element_user(DEFINEDNAME$0, n));
            if (cTDefinedName == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDefinedName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDefinedNameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFINEDNAME$0);
        }
    }

    @Override
    public void setDefinedNameArray(CTDefinedName[] cTDefinedNameArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDefinedNameArray, DEFINEDNAME$0);
    }

    @Override
    public void setDefinedNameArray(int n, CTDefinedName cTDefinedName) {
        this.generatedSetterHelperImpl(cTDefinedName, DEFINEDNAME$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDefinedName insertNewDefinedName(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDefinedName cTDefinedName = null;
            cTDefinedName = (CTDefinedName)((Object)this.get_store().insert_element_user(DEFINEDNAME$0, n));
            return cTDefinedName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDefinedName addNewDefinedName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDefinedName cTDefinedName = null;
            cTDefinedName = (CTDefinedName)((Object)this.get_store().add_element_user(DEFINEDNAME$0));
            return cTDefinedName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDefinedName(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFINEDNAME$0, n);
        }
    }
}

