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
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList;

public class CTGradientStopListImpl
extends XmlComplexContentImpl
implements CTGradientStopList {
    private static final long serialVersionUID = 1L;
    private static final QName GS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gs");

    public CTGradientStopListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGradientStop> getGsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GsList
            extends AbstractList<CTGradientStop> {
                GsList() {
                }

                @Override
                public CTGradientStop get(int n) {
                    return CTGradientStopListImpl.this.getGsArray(n);
                }

                @Override
                public CTGradientStop set(int n, CTGradientStop cTGradientStop) {
                    CTGradientStop cTGradientStop2 = CTGradientStopListImpl.this.getGsArray(n);
                    CTGradientStopListImpl.this.setGsArray(n, cTGradientStop);
                    return cTGradientStop2;
                }

                @Override
                public void add(int n, CTGradientStop cTGradientStop) {
                    CTGradientStopListImpl.this.insertNewGs(n).set(cTGradientStop);
                }

                @Override
                public CTGradientStop remove(int n) {
                    CTGradientStop cTGradientStop = CTGradientStopListImpl.this.getGsArray(n);
                    CTGradientStopListImpl.this.removeGs(n);
                    return cTGradientStop;
                }

                @Override
                public int size() {
                    return CTGradientStopListImpl.this.sizeOfGsArray();
                }
            }
            return new GsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGradientStop[] getGsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GS$0, arrayList);
            CTGradientStop[] cTGradientStopArray = new CTGradientStop[arrayList.size()];
            arrayList.toArray(cTGradientStopArray);
            return cTGradientStopArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientStop getGsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientStop cTGradientStop = null;
            cTGradientStop = (CTGradientStop)((Object)this.get_store().find_element_user(GS$0, n));
            if (cTGradientStop == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGradientStop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GS$0);
        }
    }

    @Override
    public void setGsArray(CTGradientStop[] cTGradientStopArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTGradientStopArray, GS$0);
    }

    @Override
    public void setGsArray(int n, CTGradientStop cTGradientStop) {
        this.generatedSetterHelperImpl(cTGradientStop, GS$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientStop insertNewGs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientStop cTGradientStop = null;
            cTGradientStop = (CTGradientStop)((Object)this.get_store().insert_element_user(GS$0, n));
            return cTGradientStop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientStop addNewGs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientStop cTGradientStop = null;
            cTGradientStop = (CTGradientStop)((Object)this.get_store().add_element_user(GS$0));
            return cTGradientStop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GS$0, n);
        }
    }
}

