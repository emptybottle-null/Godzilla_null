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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames;

public class CTExternalDefinedNamesImpl
extends XmlComplexContentImpl
implements CTExternalDefinedNames {
    private static final long serialVersionUID = 1L;
    private static final QName DEFINEDNAME$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "definedName");

    public CTExternalDefinedNamesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTExternalDefinedName> getDefinedNameList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DefinedNameList
            extends AbstractList<CTExternalDefinedName> {
                DefinedNameList() {
                }

                @Override
                public CTExternalDefinedName get(int n) {
                    return CTExternalDefinedNamesImpl.this.getDefinedNameArray(n);
                }

                @Override
                public CTExternalDefinedName set(int n, CTExternalDefinedName cTExternalDefinedName) {
                    CTExternalDefinedName cTExternalDefinedName2 = CTExternalDefinedNamesImpl.this.getDefinedNameArray(n);
                    CTExternalDefinedNamesImpl.this.setDefinedNameArray(n, cTExternalDefinedName);
                    return cTExternalDefinedName2;
                }

                @Override
                public void add(int n, CTExternalDefinedName cTExternalDefinedName) {
                    CTExternalDefinedNamesImpl.this.insertNewDefinedName(n).set(cTExternalDefinedName);
                }

                @Override
                public CTExternalDefinedName remove(int n) {
                    CTExternalDefinedName cTExternalDefinedName = CTExternalDefinedNamesImpl.this.getDefinedNameArray(n);
                    CTExternalDefinedNamesImpl.this.removeDefinedName(n);
                    return cTExternalDefinedName;
                }

                @Override
                public int size() {
                    return CTExternalDefinedNamesImpl.this.sizeOfDefinedNameArray();
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
    public CTExternalDefinedName[] getDefinedNameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DEFINEDNAME$0, arrayList);
            CTExternalDefinedName[] cTExternalDefinedNameArray = new CTExternalDefinedName[arrayList.size()];
            arrayList.toArray(cTExternalDefinedNameArray);
            return cTExternalDefinedNameArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalDefinedName getDefinedNameArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalDefinedName cTExternalDefinedName = null;
            cTExternalDefinedName = (CTExternalDefinedName)((Object)this.get_store().find_element_user(DEFINEDNAME$0, n));
            if (cTExternalDefinedName == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTExternalDefinedName;
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
    public void setDefinedNameArray(CTExternalDefinedName[] cTExternalDefinedNameArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTExternalDefinedNameArray, DEFINEDNAME$0);
    }

    @Override
    public void setDefinedNameArray(int n, CTExternalDefinedName cTExternalDefinedName) {
        this.generatedSetterHelperImpl(cTExternalDefinedName, DEFINEDNAME$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalDefinedName insertNewDefinedName(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalDefinedName cTExternalDefinedName = null;
            cTExternalDefinedName = (CTExternalDefinedName)((Object)this.get_store().insert_element_user(DEFINEDNAME$0, n));
            return cTExternalDefinedName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalDefinedName addNewDefinedName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalDefinedName cTExternalDefinedName = null;
            cTExternalDefinedName = (CTExternalDefinedName)((Object)this.get_store().add_element_user(DEFINEDNAME$0));
            return cTExternalDefinedName;
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

