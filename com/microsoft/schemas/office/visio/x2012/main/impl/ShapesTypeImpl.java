/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType;
import com.microsoft.schemas.office.visio.x2012.main.ShapesType;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class ShapesTypeImpl
extends XmlComplexContentImpl
implements ShapesType {
    private static final long serialVersionUID = 1L;
    private static final QName SHAPE$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Shape");

    public ShapesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<ShapeSheetType> getShapeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ShapeList
            extends AbstractList<ShapeSheetType> {
                ShapeList() {
                }

                @Override
                public ShapeSheetType get(int n) {
                    return ShapesTypeImpl.this.getShapeArray(n);
                }

                @Override
                public ShapeSheetType set(int n, ShapeSheetType shapeSheetType) {
                    ShapeSheetType shapeSheetType2 = ShapesTypeImpl.this.getShapeArray(n);
                    ShapesTypeImpl.this.setShapeArray(n, shapeSheetType);
                    return shapeSheetType2;
                }

                @Override
                public void add(int n, ShapeSheetType shapeSheetType) {
                    ShapesTypeImpl.this.insertNewShape(n).set(shapeSheetType);
                }

                @Override
                public ShapeSheetType remove(int n) {
                    ShapeSheetType shapeSheetType = ShapesTypeImpl.this.getShapeArray(n);
                    ShapesTypeImpl.this.removeShape(n);
                    return shapeSheetType;
                }

                @Override
                public int size() {
                    return ShapesTypeImpl.this.sizeOfShapeArray();
                }
            }
            return new ShapeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public ShapeSheetType[] getShapeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SHAPE$0, arrayList);
            ShapeSheetType[] shapeSheetTypeArray = new ShapeSheetType[arrayList.size()];
            arrayList.toArray(shapeSheetTypeArray);
            return shapeSheetTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ShapeSheetType getShapeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ShapeSheetType shapeSheetType = null;
            shapeSheetType = (ShapeSheetType)((Object)this.get_store().find_element_user(SHAPE$0, n));
            if (shapeSheetType == null) {
                throw new IndexOutOfBoundsException();
            }
            return shapeSheetType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfShapeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHAPE$0);
        }
    }

    @Override
    public void setShapeArray(ShapeSheetType[] shapeSheetTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(shapeSheetTypeArray, SHAPE$0);
    }

    @Override
    public void setShapeArray(int n, ShapeSheetType shapeSheetType) {
        this.generatedSetterHelperImpl(shapeSheetType, SHAPE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ShapeSheetType insertNewShape(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ShapeSheetType shapeSheetType = null;
            shapeSheetType = (ShapeSheetType)((Object)this.get_store().insert_element_user(SHAPE$0, n));
            return shapeSheetType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ShapeSheetType addNewShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ShapeSheetType shapeSheetType = null;
            shapeSheetType = (ShapeSheetType)((Object)this.get_store().add_element_user(SHAPE$0));
            return shapeSheetType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeShape(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHAPE$0, n);
        }
    }
}

