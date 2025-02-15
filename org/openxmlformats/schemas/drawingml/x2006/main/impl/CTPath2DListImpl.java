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
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DList;

public class CTPath2DListImpl
extends XmlComplexContentImpl
implements CTPath2DList {
    private static final long serialVersionUID = 1L;
    private static final QName PATH$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "path");

    public CTPath2DListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPath2D> getPathList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PathList
            extends AbstractList<CTPath2D> {
                PathList() {
                }

                @Override
                public CTPath2D get(int n) {
                    return CTPath2DListImpl.this.getPathArray(n);
                }

                @Override
                public CTPath2D set(int n, CTPath2D cTPath2D) {
                    CTPath2D cTPath2D2 = CTPath2DListImpl.this.getPathArray(n);
                    CTPath2DListImpl.this.setPathArray(n, cTPath2D);
                    return cTPath2D2;
                }

                @Override
                public void add(int n, CTPath2D cTPath2D) {
                    CTPath2DListImpl.this.insertNewPath(n).set(cTPath2D);
                }

                @Override
                public CTPath2D remove(int n) {
                    CTPath2D cTPath2D = CTPath2DListImpl.this.getPathArray(n);
                    CTPath2DListImpl.this.removePath(n);
                    return cTPath2D;
                }

                @Override
                public int size() {
                    return CTPath2DListImpl.this.sizeOfPathArray();
                }
            }
            return new PathList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPath2D[] getPathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PATH$0, arrayList);
            CTPath2D[] cTPath2DArray = new CTPath2D[arrayList.size()];
            arrayList.toArray(cTPath2DArray);
            return cTPath2DArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2D getPathArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2D cTPath2D = null;
            cTPath2D = (CTPath2D)((Object)this.get_store().find_element_user(PATH$0, n));
            if (cTPath2D == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPath2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PATH$0);
        }
    }

    @Override
    public void setPathArray(CTPath2D[] cTPath2DArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPath2DArray, PATH$0);
    }

    @Override
    public void setPathArray(int n, CTPath2D cTPath2D) {
        this.generatedSetterHelperImpl(cTPath2D, PATH$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2D insertNewPath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2D cTPath2D = null;
            cTPath2D = (CTPath2D)((Object)this.get_store().insert_element_user(PATH$0, n));
            return cTPath2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2D addNewPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2D cTPath2D = null;
            cTPath2D = (CTPath2D)((Object)this.get_store().add_element_user(PATH$0));
            return cTPath2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PATH$0, n);
        }
    }
}

