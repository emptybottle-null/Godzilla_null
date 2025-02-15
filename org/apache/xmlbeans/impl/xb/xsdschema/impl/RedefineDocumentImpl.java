/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.OpenAttrsImpl;

public class RedefineDocumentImpl
extends XmlComplexContentImpl
implements RedefineDocument {
    private static final long serialVersionUID = 1L;
    private static final QName REDEFINE$0 = new QName("http://www.w3.org/2001/XMLSchema", "redefine");

    public RedefineDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RedefineDocument.Redefine getRedefine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RedefineDocument.Redefine target = null;
            target = (RedefineDocument.Redefine)((Object)this.get_store().find_element_user(REDEFINE$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setRedefine(RedefineDocument.Redefine redefine) {
        this.generatedSetterHelperImpl(redefine, REDEFINE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RedefineDocument.Redefine addNewRedefine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RedefineDocument.Redefine target = null;
            target = (RedefineDocument.Redefine)((Object)this.get_store().add_element_user(REDEFINE$0));
            return target;
        }
    }

    public static class RedefineImpl
    extends OpenAttrsImpl
    implements RedefineDocument.Redefine {
        private static final long serialVersionUID = 1L;
        private static final QName ANNOTATION$0 = new QName("http://www.w3.org/2001/XMLSchema", "annotation");
        private static final QName SIMPLETYPE$2 = new QName("http://www.w3.org/2001/XMLSchema", "simpleType");
        private static final QName COMPLEXTYPE$4 = new QName("http://www.w3.org/2001/XMLSchema", "complexType");
        private static final QName GROUP$6 = new QName("http://www.w3.org/2001/XMLSchema", "group");
        private static final QName ATTRIBUTEGROUP$8 = new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup");
        private static final QName SCHEMALOCATION$10 = new QName("", "schemaLocation");
        private static final QName ID$12 = new QName("", "id");

        public RedefineImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public AnnotationDocument.Annotation[] getAnnotationArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(ANNOTATION$0, targetList);
                AnnotationDocument.Annotation[] result = new AnnotationDocument.Annotation[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public AnnotationDocument.Annotation getAnnotationArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                AnnotationDocument.Annotation target = null;
                target = (AnnotationDocument.Annotation)((Object)this.get_store().find_element_user(ANNOTATION$0, i));
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int sizeOfAnnotationArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(ANNOTATION$0);
            }
        }

        @Override
        public void setAnnotationArray(AnnotationDocument.Annotation[] annotationArray) {
            this.check_orphaned();
            this.arraySetterHelper(annotationArray, ANNOTATION$0);
        }

        @Override
        public void setAnnotationArray(int i, AnnotationDocument.Annotation annotation) {
            this.generatedSetterHelperImpl(annotation, ANNOTATION$0, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public AnnotationDocument.Annotation insertNewAnnotation(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                AnnotationDocument.Annotation target = null;
                target = (AnnotationDocument.Annotation)((Object)this.get_store().insert_element_user(ANNOTATION$0, i));
                return target;
            }
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
        public void removeAnnotation(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(ANNOTATION$0, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelSimpleType[] getSimpleTypeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(SIMPLETYPE$2, targetList);
                TopLevelSimpleType[] result = new TopLevelSimpleType[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelSimpleType getSimpleTypeArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelSimpleType target = null;
                target = (TopLevelSimpleType)((Object)this.get_store().find_element_user(SIMPLETYPE$2, i));
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int sizeOfSimpleTypeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(SIMPLETYPE$2);
            }
        }

        @Override
        public void setSimpleTypeArray(TopLevelSimpleType[] simpleTypeArray) {
            this.check_orphaned();
            this.arraySetterHelper(simpleTypeArray, SIMPLETYPE$2);
        }

        @Override
        public void setSimpleTypeArray(int i, TopLevelSimpleType simpleType) {
            this.generatedSetterHelperImpl(simpleType, SIMPLETYPE$2, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelSimpleType insertNewSimpleType(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelSimpleType target = null;
                target = (TopLevelSimpleType)((Object)this.get_store().insert_element_user(SIMPLETYPE$2, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelSimpleType addNewSimpleType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelSimpleType target = null;
                target = (TopLevelSimpleType)((Object)this.get_store().add_element_user(SIMPLETYPE$2));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeSimpleType(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(SIMPLETYPE$2, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelComplexType[] getComplexTypeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(COMPLEXTYPE$4, targetList);
                TopLevelComplexType[] result = new TopLevelComplexType[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelComplexType getComplexTypeArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelComplexType target = null;
                target = (TopLevelComplexType)((Object)this.get_store().find_element_user(COMPLEXTYPE$4, i));
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int sizeOfComplexTypeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(COMPLEXTYPE$4);
            }
        }

        @Override
        public void setComplexTypeArray(TopLevelComplexType[] complexTypeArray) {
            this.check_orphaned();
            this.arraySetterHelper(complexTypeArray, COMPLEXTYPE$4);
        }

        @Override
        public void setComplexTypeArray(int i, TopLevelComplexType complexType) {
            this.generatedSetterHelperImpl(complexType, COMPLEXTYPE$4, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelComplexType insertNewComplexType(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelComplexType target = null;
                target = (TopLevelComplexType)((Object)this.get_store().insert_element_user(COMPLEXTYPE$4, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelComplexType addNewComplexType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelComplexType target = null;
                target = (TopLevelComplexType)((Object)this.get_store().add_element_user(COMPLEXTYPE$4));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeComplexType(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(COMPLEXTYPE$4, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NamedGroup[] getGroupArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(GROUP$6, targetList);
                NamedGroup[] result = new NamedGroup[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NamedGroup getGroupArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                NamedGroup target = null;
                target = (NamedGroup)((Object)this.get_store().find_element_user(GROUP$6, i));
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int sizeOfGroupArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(GROUP$6);
            }
        }

        @Override
        public void setGroupArray(NamedGroup[] groupArray) {
            this.check_orphaned();
            this.arraySetterHelper(groupArray, GROUP$6);
        }

        @Override
        public void setGroupArray(int i, NamedGroup group) {
            this.generatedSetterHelperImpl(group, GROUP$6, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NamedGroup insertNewGroup(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                NamedGroup target = null;
                target = (NamedGroup)((Object)this.get_store().insert_element_user(GROUP$6, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NamedGroup addNewGroup() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                NamedGroup target = null;
                target = (NamedGroup)((Object)this.get_store().add_element_user(GROUP$6));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeGroup(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(GROUP$6, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NamedAttributeGroup[] getAttributeGroupArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(ATTRIBUTEGROUP$8, targetList);
                NamedAttributeGroup[] result = new NamedAttributeGroup[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NamedAttributeGroup getAttributeGroupArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                NamedAttributeGroup target = null;
                target = (NamedAttributeGroup)((Object)this.get_store().find_element_user(ATTRIBUTEGROUP$8, i));
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int sizeOfAttributeGroupArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(ATTRIBUTEGROUP$8);
            }
        }

        @Override
        public void setAttributeGroupArray(NamedAttributeGroup[] attributeGroupArray) {
            this.check_orphaned();
            this.arraySetterHelper(attributeGroupArray, ATTRIBUTEGROUP$8);
        }

        @Override
        public void setAttributeGroupArray(int i, NamedAttributeGroup attributeGroup) {
            this.generatedSetterHelperImpl(attributeGroup, ATTRIBUTEGROUP$8, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NamedAttributeGroup insertNewAttributeGroup(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                NamedAttributeGroup target = null;
                target = (NamedAttributeGroup)((Object)this.get_store().insert_element_user(ATTRIBUTEGROUP$8, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NamedAttributeGroup addNewAttributeGroup() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                NamedAttributeGroup target = null;
                target = (NamedAttributeGroup)((Object)this.get_store().add_element_user(ATTRIBUTEGROUP$8));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeAttributeGroup(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(ATTRIBUTEGROUP$8, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getSchemaLocation() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$10));
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
        public XmlAnyURI xgetSchemaLocation() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$10));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setSchemaLocation(String schemaLocation) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$10));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(SCHEMALOCATION$10));
                }
                target.setStringValue(schemaLocation);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetSchemaLocation(XmlAnyURI schemaLocation) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$10));
                if (target == null) {
                    target = (XmlAnyURI)((Object)this.get_store().add_attribute_user(SCHEMALOCATION$10));
                }
                target.set(schemaLocation);
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
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$12));
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
                target = (XmlID)((Object)this.get_store().find_attribute_user(ID$12));
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
                return this.get_store().find_attribute_user(ID$12) != null;
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
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$12));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$12));
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
                target = (XmlID)((Object)this.get_store().find_attribute_user(ID$12));
                if (target == null) {
                    target = (XmlID)((Object)this.get_store().add_attribute_user(ID$12));
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
                this.get_store().remove_attribute(ID$12);
            }
        }
    }
}

