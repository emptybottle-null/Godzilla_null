/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.ClaimedRolesListType;

public class ClaimedRolesListTypeImpl
extends XmlComplexContentImpl
implements ClaimedRolesListType {
    private static final long serialVersionUID = 1L;
    private static final QName CLAIMEDROLE$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "ClaimedRole");

    public ClaimedRolesListTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<AnyType> getClaimedRoleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ClaimedRoleList
            extends AbstractList<AnyType> {
                ClaimedRoleList() {
                }

                @Override
                public AnyType get(int n) {
                    return ClaimedRolesListTypeImpl.this.getClaimedRoleArray(n);
                }

                @Override
                public AnyType set(int n, AnyType anyType) {
                    AnyType anyType2 = ClaimedRolesListTypeImpl.this.getClaimedRoleArray(n);
                    ClaimedRolesListTypeImpl.this.setClaimedRoleArray(n, anyType);
                    return anyType2;
                }

                @Override
                public void add(int n, AnyType anyType) {
                    ClaimedRolesListTypeImpl.this.insertNewClaimedRole(n).set(anyType);
                }

                @Override
                public AnyType remove(int n) {
                    AnyType anyType = ClaimedRolesListTypeImpl.this.getClaimedRoleArray(n);
                    ClaimedRolesListTypeImpl.this.removeClaimedRole(n);
                    return anyType;
                }

                @Override
                public int size() {
                    return ClaimedRolesListTypeImpl.this.sizeOfClaimedRoleArray();
                }
            }
            return new ClaimedRoleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public AnyType[] getClaimedRoleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CLAIMEDROLE$0, arrayList);
            AnyType[] anyTypeArray = new AnyType[arrayList.size()];
            arrayList.toArray(anyTypeArray);
            return anyTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyType getClaimedRoleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyType anyType = null;
            anyType = (AnyType)((Object)this.get_store().find_element_user(CLAIMEDROLE$0, n));
            if (anyType == null) {
                throw new IndexOutOfBoundsException();
            }
            return anyType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfClaimedRoleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLAIMEDROLE$0);
        }
    }

    @Override
    public void setClaimedRoleArray(AnyType[] anyTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(anyTypeArray, CLAIMEDROLE$0);
    }

    @Override
    public void setClaimedRoleArray(int n, AnyType anyType) {
        this.generatedSetterHelperImpl(anyType, CLAIMEDROLE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyType insertNewClaimedRole(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyType anyType = null;
            anyType = (AnyType)((Object)this.get_store().insert_element_user(CLAIMEDROLE$0, n));
            return anyType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyType addNewClaimedRole() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyType anyType = null;
            anyType = (AnyType)((Object)this.get_store().add_element_user(CLAIMEDROLE$0));
            return anyType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeClaimedRole(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLAIMEDROLE$0, n);
        }
    }
}

