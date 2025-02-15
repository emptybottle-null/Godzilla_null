/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ConnectsType;
import com.microsoft.schemas.office.visio.x2012.main.PageContentsType;
import com.microsoft.schemas.office.visio.x2012.main.ShapesType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class PageContentsTypeImpl
extends XmlComplexContentImpl
implements PageContentsType {
    private static final long serialVersionUID = 1L;
    private static final QName SHAPES$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Shapes");
    private static final QName CONNECTS$2 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Connects");

    public PageContentsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ShapesType getShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ShapesType shapesType = null;
            shapesType = (ShapesType)((Object)this.get_store().find_element_user(SHAPES$0, 0));
            if (shapesType == null) {
                return null;
            }
            return shapesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHAPES$0) != 0;
        }
    }

    @Override
    public void setShapes(ShapesType shapesType) {
        this.generatedSetterHelperImpl(shapesType, SHAPES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ShapesType addNewShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ShapesType shapesType = null;
            shapesType = (ShapesType)((Object)this.get_store().add_element_user(SHAPES$0));
            return shapesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHAPES$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ConnectsType getConnects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ConnectsType connectsType = null;
            connectsType = (ConnectsType)((Object)this.get_store().find_element_user(CONNECTS$2, 0));
            if (connectsType == null) {
                return null;
            }
            return connectsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetConnects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONNECTS$2) != 0;
        }
    }

    @Override
    public void setConnects(ConnectsType connectsType) {
        this.generatedSetterHelperImpl(connectsType, CONNECTS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ConnectsType addNewConnects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ConnectsType connectsType = null;
            connectsType = (ConnectsType)((Object)this.get_store().add_element_user(CONNECTS$2));
            return connectsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetConnects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONNECTS$2, 0);
        }
    }
}

