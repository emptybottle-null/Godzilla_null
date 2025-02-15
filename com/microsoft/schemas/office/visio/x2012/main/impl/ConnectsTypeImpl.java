/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ConnectType;
import com.microsoft.schemas.office.visio.x2012.main.ConnectsType;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class ConnectsTypeImpl
extends XmlComplexContentImpl
implements ConnectsType {
    private static final long serialVersionUID = 1L;
    private static final QName CONNECT$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Connect");

    public ConnectsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<ConnectType> getConnectList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ConnectList
            extends AbstractList<ConnectType> {
                ConnectList() {
                }

                @Override
                public ConnectType get(int n) {
                    return ConnectsTypeImpl.this.getConnectArray(n);
                }

                @Override
                public ConnectType set(int n, ConnectType connectType) {
                    ConnectType connectType2 = ConnectsTypeImpl.this.getConnectArray(n);
                    ConnectsTypeImpl.this.setConnectArray(n, connectType);
                    return connectType2;
                }

                @Override
                public void add(int n, ConnectType connectType) {
                    ConnectsTypeImpl.this.insertNewConnect(n).set(connectType);
                }

                @Override
                public ConnectType remove(int n) {
                    ConnectType connectType = ConnectsTypeImpl.this.getConnectArray(n);
                    ConnectsTypeImpl.this.removeConnect(n);
                    return connectType;
                }

                @Override
                public int size() {
                    return ConnectsTypeImpl.this.sizeOfConnectArray();
                }
            }
            return new ConnectList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public ConnectType[] getConnectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CONNECT$0, arrayList);
            ConnectType[] connectTypeArray = new ConnectType[arrayList.size()];
            arrayList.toArray(connectTypeArray);
            return connectTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ConnectType getConnectArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ConnectType connectType = null;
            connectType = (ConnectType)((Object)this.get_store().find_element_user(CONNECT$0, n));
            if (connectType == null) {
                throw new IndexOutOfBoundsException();
            }
            return connectType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfConnectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONNECT$0);
        }
    }

    @Override
    public void setConnectArray(ConnectType[] connectTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(connectTypeArray, CONNECT$0);
    }

    @Override
    public void setConnectArray(int n, ConnectType connectType) {
        this.generatedSetterHelperImpl(connectType, CONNECT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ConnectType insertNewConnect(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ConnectType connectType = null;
            connectType = (ConnectType)((Object)this.get_store().insert_element_user(CONNECT$0, n));
            return connectType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ConnectType addNewConnect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ConnectType connectType = null;
            connectType = (ConnectType)((Object)this.get_store().add_element_user(CONNECT$0));
            return connectType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeConnect(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONNECT$0, n);
        }
    }
}

