/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleDerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class SimpleTypeImpl
extends AnnotatedImpl
implements SimpleType {
    private static final long serialVersionUID = 1L;
    private static final QName RESTRICTION$0 = new QName("http://www.w3.org/2001/XMLSchema", "restriction");
    private static final QName LIST$2 = new QName("http://www.w3.org/2001/XMLSchema", "list");
    private static final QName UNION$4 = new QName("http://www.w3.org/2001/XMLSchema", "union");
    private static final QName FINAL$6 = new QName("", "final");
    private static final QName NAME$8 = new QName("", "name");

    public SimpleTypeImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RestrictionDocument.Restriction getRestriction() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RestrictionDocument.Restriction target = null;
            target = (RestrictionDocument.Restriction)((Object)this.get_store().find_element_user(RESTRICTION$0, 0));
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
    public boolean isSetRestriction() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RESTRICTION$0) != 0;
        }
    }

    @Override
    public void setRestriction(RestrictionDocument.Restriction restriction) {
        this.generatedSetterHelperImpl(restriction, RESTRICTION$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RestrictionDocument.Restriction addNewRestriction() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RestrictionDocument.Restriction target = null;
            target = (RestrictionDocument.Restriction)((Object)this.get_store().add_element_user(RESTRICTION$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRestriction() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RESTRICTION$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ListDocument.List getList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ListDocument.List target = null;
            target = (ListDocument.List)((Object)this.get_store().find_element_user(LIST$2, 0));
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
    public boolean isSetList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LIST$2) != 0;
        }
    }

    @Override
    public void setList(ListDocument.List list) {
        this.generatedSetterHelperImpl(list, LIST$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ListDocument.List addNewList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ListDocument.List target = null;
            target = (ListDocument.List)((Object)this.get_store().add_element_user(LIST$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LIST$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public UnionDocument.Union getUnion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            UnionDocument.Union target = null;
            target = (UnionDocument.Union)((Object)this.get_store().find_element_user(UNION$4, 0));
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
    public boolean isSetUnion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UNION$4) != 0;
        }
    }

    @Override
    public void setUnion(UnionDocument.Union union) {
        this.generatedSetterHelperImpl(union, UNION$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public UnionDocument.Union addNewUnion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            UnionDocument.Union target = null;
            target = (UnionDocument.Union)((Object)this.get_store().add_element_user(UNION$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUnion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UNION$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getFinal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FINAL$6));
            if (target == null) {
                return null;
            }
            return target.getObjectValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SimpleDerivationSet xgetFinal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleDerivationSet target = null;
            target = (SimpleDerivationSet)((Object)this.get_store().find_attribute_user(FINAL$6));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFinal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FINAL$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFinal(Object xfinal) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FINAL$6));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(FINAL$6));
            }
            target.setObjectValue(xfinal);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFinal(SimpleDerivationSet xfinal) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleDerivationSet target = null;
            target = (SimpleDerivationSet)((Object)this.get_store().find_attribute_user(FINAL$6));
            if (target == null) {
                target = (SimpleDerivationSet)((Object)this.get_store().add_attribute_user(FINAL$6));
            }
            target.set(xfinal);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFinal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FINAL$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$8));
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
    public XmlNCName xgetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNCName target = null;
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$8));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(NAME$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setName(String name) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$8));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$8));
            }
            target.setStringValue(name);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetName(XmlNCName name) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNCName target = null;
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$8));
            if (target == null) {
                target = (XmlNCName)((Object)this.get_store().add_attribute_user(NAME$8));
            }
            target.set(name);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(NAME$8);
        }
    }
}

