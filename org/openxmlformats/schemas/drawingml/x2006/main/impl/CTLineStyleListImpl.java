/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList;

public class CTLineStyleListImpl
extends XmlComplexContentImpl
implements CTLineStyleList {
    private static final long serialVersionUID = 1L;
    private static final QName LN$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "ln");

    public CTLineStyleListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLineProperties> getLnList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LnList
            extends AbstractList<CTLineProperties> {
                LnList() {
                }

                @Override
                public CTLineProperties get(int n) {
                    return CTLineStyleListImpl.this.getLnArray(n);
                }

                @Override
                public CTLineProperties set(int n, CTLineProperties cTLineProperties) {
                    CTLineProperties cTLineProperties2 = CTLineStyleListImpl.this.getLnArray(n);
                    CTLineStyleListImpl.this.setLnArray(n, cTLineProperties);
                    return cTLineProperties2;
                }

                @Override
                public void add(int n, CTLineProperties cTLineProperties) {
                    CTLineStyleListImpl.this.insertNewLn(n).set(cTLineProperties);
                }

                @Override
                public CTLineProperties remove(int n) {
                    CTLineProperties cTLineProperties = CTLineStyleListImpl.this.getLnArray(n);
                    CTLineStyleListImpl.this.removeLn(n);
                    return cTLineProperties;
                }

                @Override
                public int size() {
                    return CTLineStyleListImpl.this.sizeOfLnArray();
                }
            }
            return new LnList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTLineProperties[] getLnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LN$0, arrayList);
            CTLineProperties[] cTLinePropertiesArray = new CTLineProperties[arrayList.size()];
            arrayList.toArray(cTLinePropertiesArray);
            return cTLinePropertiesArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties getLnArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().find_element_user(LN$0, n));
            if (cTLineProperties == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LN$0);
        }
    }

    @Override
    public void setLnArray(CTLineProperties[] cTLinePropertiesArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTLinePropertiesArray, LN$0);
    }

    @Override
    public void setLnArray(int n, CTLineProperties cTLineProperties) {
        this.generatedSetterHelperImpl(cTLineProperties, LN$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties insertNewLn(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().insert_element_user(LN$0, n));
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties addNewLn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().add_element_user(LN$0));
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLn(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LN$0, n);
        }
    }
}

