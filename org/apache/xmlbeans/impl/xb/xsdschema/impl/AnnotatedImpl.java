/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.OpenAttrsImpl;

public class AnnotatedImpl
extends OpenAttrsImpl
implements Annotated {
    private static final long serialVersionUID = 1L;
    private static final QName ANNOTATION$0 = new QName("http://www.w3.org/2001/XMLSchema", "annotation");
    private static final QName ID$2 = new QName("", "id");

    public AnnotatedImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnnotationDocument.Annotation getAnnotation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnnotationDocument.Annotation target = null;
            target = (AnnotationDocument.Annotation)((Object)this.get_store().find_element_user(ANNOTATION$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAnnotation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ANNOTATION$0) != 0;
        }
    }

    @Override
    public void setAnnotation(AnnotationDocument.Annotation annotation) {
        this.generatedSetterHelperImpl(annotation, ANNOTATION$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnnotationDocument.Annotation addNewAnnotation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnnotationDocument.Annotation target = null;
            target = (AnnotationDocument.Annotation)((Object)this.get_store().add_element_user(ANNOTATION$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAnnotation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ANNOTATION$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$2));
            if (target == null) {
                return null;
            }
            return target.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlID xgetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlID target = null;
            target = (XmlID)((Object)this.get_store().find_attribute_user(ID$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ID$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId(String id) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$2));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$2));
            }
            target.setStringValue(id);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId(XmlID id) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlID target = null;
            target = (XmlID)((Object)this.get_store().find_attribute_user(ID$2));
            if (target == null) {
                target = (XmlID)((Object)this.get_store().add_attribute_user(ID$2));
            }
            target.set(id);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ID$2);
        }
    }
}

