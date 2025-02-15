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
import org.apache.xmlbeans.XmlLanguage;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.OpenAttrsImpl;

public class SchemaDocumentImpl
extends XmlComplexContentImpl
implements SchemaDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SCHEMA$0 = new QName("http://www.w3.org/2001/XMLSchema", "schema");

    public SchemaDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SchemaDocument.Schema getSchema() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SchemaDocument.Schema target = null;
            target = (SchemaDocument.Schema)((Object)this.get_store().find_element_user(SCHEMA$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setSchema(SchemaDocument.Schema schema) {
        this.generatedSetterHelperImpl(schema, SCHEMA$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SchemaDocument.Schema addNewSchema() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SchemaDocument.Schema target = null;
            target = (SchemaDocument.Schema)((Object)this.get_store().add_element_user(SCHEMA$0));
            return target;
        }
    }

    public static class SchemaImpl
    extends OpenAttrsImpl
    implements SchemaDocument.Schema {
        private static final long serialVersionUID = 1L;
        private static final QName INCLUDE$0 = new QName("http://www.w3.org/2001/XMLSchema", "include");
        private static final QName IMPORT$2 = new QName("http://www.w3.org/2001/XMLSchema", "import");
        private static final QName REDEFINE$4 = new QName("http://www.w3.org/2001/XMLSchema", "redefine");
        private static final QName ANNOTATION$6 = new QName("http://www.w3.org/2001/XMLSchema", "annotation");
        private static final QName SIMPLETYPE$8 = new QName("http://www.w3.org/2001/XMLSchema", "simpleType");
        private static final QName COMPLEXTYPE$10 = new QName("http://www.w3.org/2001/XMLSchema", "complexType");
        private static final QName GROUP$12 = new QName("http://www.w3.org/2001/XMLSchema", "group");
        private static final QName ATTRIBUTEGROUP$14 = new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup");
        private static final QName ELEMENT$16 = new QName("http://www.w3.org/2001/XMLSchema", "element");
        private static final QName ATTRIBUTE$18 = new QName("http://www.w3.org/2001/XMLSchema", "attribute");
        private static final QName NOTATION$20 = new QName("http://www.w3.org/2001/XMLSchema", "notation");
        private static final QName TARGETNAMESPACE$22 = new QName("", "targetNamespace");
        private static final QName VERSION$24 = new QName("", "version");
        private static final QName FINALDEFAULT$26 = new QName("", "finalDefault");
        private static final QName BLOCKDEFAULT$28 = new QName("", "blockDefault");
        private static final QName ATTRIBUTEFORMDEFAULT$30 = new QName("", "attributeFormDefault");
        private static final QName ELEMENTFORMDEFAULT$32 = new QName("", "elementFormDefault");
        private static final QName ID$34 = new QName("", "id");
        private static final QName LANG$36 = new QName("http://www.w3.org/XML/1998/namespace", "lang");

        public SchemaImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public IncludeDocument.Include[] getIncludeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(INCLUDE$0, targetList);
                IncludeDocument.Include[] result = new IncludeDocument.Include[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public IncludeDocument.Include getIncludeArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                IncludeDocument.Include target = null;
                target = (IncludeDocument.Include)((Object)this.get_store().find_element_user(INCLUDE$0, i));
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
        public int sizeOfIncludeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(INCLUDE$0);
            }
        }

        @Override
        public void setIncludeArray(IncludeDocument.Include[] includeArray) {
            this.check_orphaned();
            this.arraySetterHelper(includeArray, INCLUDE$0);
        }

        @Override
        public void setIncludeArray(int i, IncludeDocument.Include include) {
            this.generatedSetterHelperImpl(include, INCLUDE$0, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public IncludeDocument.Include insertNewInclude(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                IncludeDocument.Include target = null;
                target = (IncludeDocument.Include)((Object)this.get_store().insert_element_user(INCLUDE$0, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public IncludeDocument.Include addNewInclude() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                IncludeDocument.Include target = null;
                target = (IncludeDocument.Include)((Object)this.get_store().add_element_user(INCLUDE$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeInclude(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(INCLUDE$0, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public ImportDocument.Import[] getImportArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(IMPORT$2, targetList);
                ImportDocument.Import[] result = new ImportDocument.Import[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public ImportDocument.Import getImportArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ImportDocument.Import target = null;
                target = (ImportDocument.Import)((Object)this.get_store().find_element_user(IMPORT$2, i));
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
        public int sizeOfImportArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(IMPORT$2);
            }
        }

        @Override
        public void setImportArray(ImportDocument.Import[] ximportArray) {
            this.check_orphaned();
            this.arraySetterHelper(ximportArray, IMPORT$2);
        }

        @Override
        public void setImportArray(int i, ImportDocument.Import ximport) {
            this.generatedSetterHelperImpl(ximport, IMPORT$2, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public ImportDocument.Import insertNewImport(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ImportDocument.Import target = null;
                target = (ImportDocument.Import)((Object)this.get_store().insert_element_user(IMPORT$2, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public ImportDocument.Import addNewImport() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ImportDocument.Import target = null;
                target = (ImportDocument.Import)((Object)this.get_store().add_element_user(IMPORT$2));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeImport(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(IMPORT$2, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public RedefineDocument.Redefine[] getRedefineArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(REDEFINE$4, targetList);
                RedefineDocument.Redefine[] result = new RedefineDocument.Redefine[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public RedefineDocument.Redefine getRedefineArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                RedefineDocument.Redefine target = null;
                target = (RedefineDocument.Redefine)((Object)this.get_store().find_element_user(REDEFINE$4, i));
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
        public int sizeOfRedefineArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(REDEFINE$4);
            }
        }

        @Override
        public void setRedefineArray(RedefineDocument.Redefine[] redefineArray) {
            this.check_orphaned();
            this.arraySetterHelper(redefineArray, REDEFINE$4);
        }

        @Override
        public void setRedefineArray(int i, RedefineDocument.Redefine redefine) {
            this.generatedSetterHelperImpl(redefine, REDEFINE$4, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public RedefineDocument.Redefine insertNewRedefine(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                RedefineDocument.Redefine target = null;
                target = (RedefineDocument.Redefine)((Object)this.get_store().insert_element_user(REDEFINE$4, i));
                return target;
            }
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
                target = (RedefineDocument.Redefine)((Object)this.get_store().add_element_user(REDEFINE$4));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeRedefine(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(REDEFINE$4, i);
            }
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
                this.get_store().find_all_element_users(ANNOTATION$6, targetList);
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
                target = (AnnotationDocument.Annotation)((Object)this.get_store().find_element_user(ANNOTATION$6, i));
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
                return this.get_store().count_elements(ANNOTATION$6);
            }
        }

        @Override
        public void setAnnotationArray(AnnotationDocument.Annotation[] annotationArray) {
            this.check_orphaned();
            this.arraySetterHelper(annotationArray, ANNOTATION$6);
        }

        @Override
        public void setAnnotationArray(int i, AnnotationDocument.Annotation annotation) {
            this.generatedSetterHelperImpl(annotation, ANNOTATION$6, i, (short)2);
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
                target = (AnnotationDocument.Annotation)((Object)this.get_store().insert_element_user(ANNOTATION$6, i));
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
                target = (AnnotationDocument.Annotation)((Object)this.get_store().add_element_user(ANNOTATION$6));
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
                this.get_store().remove_element(ANNOTATION$6, i);
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
                this.get_store().find_all_element_users(SIMPLETYPE$8, targetList);
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
                target = (TopLevelSimpleType)((Object)this.get_store().find_element_user(SIMPLETYPE$8, i));
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
                return this.get_store().count_elements(SIMPLETYPE$8);
            }
        }

        @Override
        public void setSimpleTypeArray(TopLevelSimpleType[] simpleTypeArray) {
            this.check_orphaned();
            this.arraySetterHelper(simpleTypeArray, SIMPLETYPE$8);
        }

        @Override
        public void setSimpleTypeArray(int i, TopLevelSimpleType simpleType) {
            this.generatedSetterHelperImpl(simpleType, SIMPLETYPE$8, i, (short)2);
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
                target = (TopLevelSimpleType)((Object)this.get_store().insert_element_user(SIMPLETYPE$8, i));
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
                target = (TopLevelSimpleType)((Object)this.get_store().add_element_user(SIMPLETYPE$8));
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
                this.get_store().remove_element(SIMPLETYPE$8, i);
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
                this.get_store().find_all_element_users(COMPLEXTYPE$10, targetList);
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
                target = (TopLevelComplexType)((Object)this.get_store().find_element_user(COMPLEXTYPE$10, i));
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
                return this.get_store().count_elements(COMPLEXTYPE$10);
            }
        }

        @Override
        public void setComplexTypeArray(TopLevelComplexType[] complexTypeArray) {
            this.check_orphaned();
            this.arraySetterHelper(complexTypeArray, COMPLEXTYPE$10);
        }

        @Override
        public void setComplexTypeArray(int i, TopLevelComplexType complexType) {
            this.generatedSetterHelperImpl(complexType, COMPLEXTYPE$10, i, (short)2);
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
                target = (TopLevelComplexType)((Object)this.get_store().insert_element_user(COMPLEXTYPE$10, i));
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
                target = (TopLevelComplexType)((Object)this.get_store().add_element_user(COMPLEXTYPE$10));
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
                this.get_store().remove_element(COMPLEXTYPE$10, i);
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
                this.get_store().find_all_element_users(GROUP$12, targetList);
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
                target = (NamedGroup)((Object)this.get_store().find_element_user(GROUP$12, i));
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
                return this.get_store().count_elements(GROUP$12);
            }
        }

        @Override
        public void setGroupArray(NamedGroup[] groupArray) {
            this.check_orphaned();
            this.arraySetterHelper(groupArray, GROUP$12);
        }

        @Override
        public void setGroupArray(int i, NamedGroup group) {
            this.generatedSetterHelperImpl(group, GROUP$12, i, (short)2);
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
                target = (NamedGroup)((Object)this.get_store().insert_element_user(GROUP$12, i));
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
                target = (NamedGroup)((Object)this.get_store().add_element_user(GROUP$12));
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
                this.get_store().remove_element(GROUP$12, i);
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
                this.get_store().find_all_element_users(ATTRIBUTEGROUP$14, targetList);
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
                target = (NamedAttributeGroup)((Object)this.get_store().find_element_user(ATTRIBUTEGROUP$14, i));
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
                return this.get_store().count_elements(ATTRIBUTEGROUP$14);
            }
        }

        @Override
        public void setAttributeGroupArray(NamedAttributeGroup[] attributeGroupArray) {
            this.check_orphaned();
            this.arraySetterHelper(attributeGroupArray, ATTRIBUTEGROUP$14);
        }

        @Override
        public void setAttributeGroupArray(int i, NamedAttributeGroup attributeGroup) {
            this.generatedSetterHelperImpl(attributeGroup, ATTRIBUTEGROUP$14, i, (short)2);
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
                target = (NamedAttributeGroup)((Object)this.get_store().insert_element_user(ATTRIBUTEGROUP$14, i));
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
                target = (NamedAttributeGroup)((Object)this.get_store().add_element_user(ATTRIBUTEGROUP$14));
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
                this.get_store().remove_element(ATTRIBUTEGROUP$14, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelElement[] getElementArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(ELEMENT$16, targetList);
                TopLevelElement[] result = new TopLevelElement[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelElement getElementArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelElement target = null;
                target = (TopLevelElement)((Object)this.get_store().find_element_user(ELEMENT$16, i));
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
        public int sizeOfElementArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(ELEMENT$16);
            }
        }

        @Override
        public void setElementArray(TopLevelElement[] elementArray) {
            this.check_orphaned();
            this.arraySetterHelper(elementArray, ELEMENT$16);
        }

        @Override
        public void setElementArray(int i, TopLevelElement element) {
            this.generatedSetterHelperImpl(element, ELEMENT$16, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelElement insertNewElement(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelElement target = null;
                target = (TopLevelElement)((Object)this.get_store().insert_element_user(ELEMENT$16, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelElement addNewElement() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelElement target = null;
                target = (TopLevelElement)((Object)this.get_store().add_element_user(ELEMENT$16));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeElement(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(ELEMENT$16, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelAttribute[] getAttributeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(ATTRIBUTE$18, targetList);
                TopLevelAttribute[] result = new TopLevelAttribute[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelAttribute getAttributeArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelAttribute target = null;
                target = (TopLevelAttribute)((Object)this.get_store().find_element_user(ATTRIBUTE$18, i));
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
        public int sizeOfAttributeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(ATTRIBUTE$18);
            }
        }

        @Override
        public void setAttributeArray(TopLevelAttribute[] attributeArray) {
            this.check_orphaned();
            this.arraySetterHelper(attributeArray, ATTRIBUTE$18);
        }

        @Override
        public void setAttributeArray(int i, TopLevelAttribute attribute) {
            this.generatedSetterHelperImpl(attribute, ATTRIBUTE$18, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelAttribute insertNewAttribute(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelAttribute target = null;
                target = (TopLevelAttribute)((Object)this.get_store().insert_element_user(ATTRIBUTE$18, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TopLevelAttribute addNewAttribute() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TopLevelAttribute target = null;
                target = (TopLevelAttribute)((Object)this.get_store().add_element_user(ATTRIBUTE$18));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeAttribute(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(ATTRIBUTE$18, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NotationDocument.Notation[] getNotationArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(NOTATION$20, targetList);
                NotationDocument.Notation[] result = new NotationDocument.Notation[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NotationDocument.Notation getNotationArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                NotationDocument.Notation target = null;
                target = (NotationDocument.Notation)((Object)this.get_store().find_element_user(NOTATION$20, i));
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
        public int sizeOfNotationArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(NOTATION$20);
            }
        }

        @Override
        public void setNotationArray(NotationDocument.Notation[] notationArray) {
            this.check_orphaned();
            this.arraySetterHelper(notationArray, NOTATION$20);
        }

        @Override
        public void setNotationArray(int i, NotationDocument.Notation notation) {
            this.generatedSetterHelperImpl(notation, NOTATION$20, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NotationDocument.Notation insertNewNotation(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                NotationDocument.Notation target = null;
                target = (NotationDocument.Notation)((Object)this.get_store().insert_element_user(NOTATION$20, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public NotationDocument.Notation addNewNotation() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                NotationDocument.Notation target = null;
                target = (NotationDocument.Notation)((Object)this.get_store().add_element_user(NOTATION$20));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeNotation(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(NOTATION$20, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getTargetNamespace() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(TARGETNAMESPACE$22));
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
        public XmlAnyURI xgetTargetNamespace() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(TARGETNAMESPACE$22));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetTargetNamespace() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(TARGETNAMESPACE$22) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setTargetNamespace(String targetNamespace) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(TARGETNAMESPACE$22));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(TARGETNAMESPACE$22));
                }
                target.setStringValue(targetNamespace);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetTargetNamespace(XmlAnyURI targetNamespace) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(TARGETNAMESPACE$22));
                if (target == null) {
                    target = (XmlAnyURI)((Object)this.get_store().add_attribute_user(TARGETNAMESPACE$22));
                }
                target.set(targetNamespace);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetTargetNamespace() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(TARGETNAMESPACE$22);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getVersion() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(VERSION$24));
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
        public XmlToken xgetVersion() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlToken target = null;
                target = (XmlToken)((Object)this.get_store().find_attribute_user(VERSION$24));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetVersion() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(VERSION$24) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setVersion(String version) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(VERSION$24));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(VERSION$24));
                }
                target.setStringValue(version);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetVersion(XmlToken version) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlToken target = null;
                target = (XmlToken)((Object)this.get_store().find_attribute_user(VERSION$24));
                if (target == null) {
                    target = (XmlToken)((Object)this.get_store().add_attribute_user(VERSION$24));
                }
                target.set(version);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetVersion() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(VERSION$24);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Object getFinalDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(FINALDEFAULT$26));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_default_attribute_value(FINALDEFAULT$26));
                }
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
        public FullDerivationSet xgetFinalDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FullDerivationSet target = null;
                target = (FullDerivationSet)((Object)this.get_store().find_attribute_user(FINALDEFAULT$26));
                if (target == null) {
                    target = (FullDerivationSet)this.get_default_attribute_value(FINALDEFAULT$26);
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetFinalDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(FINALDEFAULT$26) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setFinalDefault(Object finalDefault) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(FINALDEFAULT$26));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(FINALDEFAULT$26));
                }
                target.setObjectValue(finalDefault);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetFinalDefault(FullDerivationSet finalDefault) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FullDerivationSet target = null;
                target = (FullDerivationSet)((Object)this.get_store().find_attribute_user(FINALDEFAULT$26));
                if (target == null) {
                    target = (FullDerivationSet)((Object)this.get_store().add_attribute_user(FINALDEFAULT$26));
                }
                target.set(finalDefault);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetFinalDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(FINALDEFAULT$26);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Object getBlockDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(BLOCKDEFAULT$28));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_default_attribute_value(BLOCKDEFAULT$28));
                }
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
        public BlockSet xgetBlockDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                BlockSet target = null;
                target = (BlockSet)((Object)this.get_store().find_attribute_user(BLOCKDEFAULT$28));
                if (target == null) {
                    target = (BlockSet)this.get_default_attribute_value(BLOCKDEFAULT$28);
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetBlockDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(BLOCKDEFAULT$28) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setBlockDefault(Object blockDefault) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(BLOCKDEFAULT$28));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(BLOCKDEFAULT$28));
                }
                target.setObjectValue(blockDefault);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetBlockDefault(BlockSet blockDefault) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                BlockSet target = null;
                target = (BlockSet)((Object)this.get_store().find_attribute_user(BLOCKDEFAULT$28));
                if (target == null) {
                    target = (BlockSet)((Object)this.get_store().add_attribute_user(BLOCKDEFAULT$28));
                }
                target.set(blockDefault);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetBlockDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(BLOCKDEFAULT$28);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public FormChoice.Enum getAttributeFormDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(ATTRIBUTEFORMDEFAULT$30));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_default_attribute_value(ATTRIBUTEFORMDEFAULT$30));
                }
                if (target == null) {
                    return null;
                }
                return (FormChoice.Enum)target.getEnumValue();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public FormChoice xgetAttributeFormDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FormChoice target = null;
                target = (FormChoice)((Object)this.get_store().find_attribute_user(ATTRIBUTEFORMDEFAULT$30));
                if (target == null) {
                    target = (FormChoice)this.get_default_attribute_value(ATTRIBUTEFORMDEFAULT$30);
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetAttributeFormDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(ATTRIBUTEFORMDEFAULT$30) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setAttributeFormDefault(FormChoice.Enum attributeFormDefault) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(ATTRIBUTEFORMDEFAULT$30));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(ATTRIBUTEFORMDEFAULT$30));
                }
                target.setEnumValue(attributeFormDefault);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetAttributeFormDefault(FormChoice attributeFormDefault) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FormChoice target = null;
                target = (FormChoice)((Object)this.get_store().find_attribute_user(ATTRIBUTEFORMDEFAULT$30));
                if (target == null) {
                    target = (FormChoice)((Object)this.get_store().add_attribute_user(ATTRIBUTEFORMDEFAULT$30));
                }
                target.set(attributeFormDefault);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetAttributeFormDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(ATTRIBUTEFORMDEFAULT$30);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public FormChoice.Enum getElementFormDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(ELEMENTFORMDEFAULT$32));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_default_attribute_value(ELEMENTFORMDEFAULT$32));
                }
                if (target == null) {
                    return null;
                }
                return (FormChoice.Enum)target.getEnumValue();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public FormChoice xgetElementFormDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FormChoice target = null;
                target = (FormChoice)((Object)this.get_store().find_attribute_user(ELEMENTFORMDEFAULT$32));
                if (target == null) {
                    target = (FormChoice)this.get_default_attribute_value(ELEMENTFORMDEFAULT$32);
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetElementFormDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(ELEMENTFORMDEFAULT$32) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setElementFormDefault(FormChoice.Enum elementFormDefault) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(ELEMENTFORMDEFAULT$32));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(ELEMENTFORMDEFAULT$32));
                }
                target.setEnumValue(elementFormDefault);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetElementFormDefault(FormChoice elementFormDefault) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FormChoice target = null;
                target = (FormChoice)((Object)this.get_store().find_attribute_user(ELEMENTFORMDEFAULT$32));
                if (target == null) {
                    target = (FormChoice)((Object)this.get_store().add_attribute_user(ELEMENTFORMDEFAULT$32));
                }
                target.set(elementFormDefault);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetElementFormDefault() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(ELEMENTFORMDEFAULT$32);
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
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$34));
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
                target = (XmlID)((Object)this.get_store().find_attribute_user(ID$34));
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
                return this.get_store().find_attribute_user(ID$34) != null;
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
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$34));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$34));
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
                target = (XmlID)((Object)this.get_store().find_attribute_user(ID$34));
                if (target == null) {
                    target = (XmlID)((Object)this.get_store().add_attribute_user(ID$34));
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
                this.get_store().remove_attribute(ID$34);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getLang() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(LANG$36));
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
        public XmlLanguage xgetLang() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlLanguage target = null;
                target = (XmlLanguage)((Object)this.get_store().find_attribute_user(LANG$36));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetLang() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(LANG$36) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setLang(String lang) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(LANG$36));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(LANG$36));
                }
                target.setStringValue(lang);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetLang(XmlLanguage lang) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlLanguage target = null;
                target = (XmlLanguage)((Object)this.get_store().find_attribute_user(LANG$36));
                if (target == null) {
                    target = (XmlLanguage)((Object)this.get_store().add_attribute_user(LANG$36));
                }
                target.set(lang);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetLang() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(LANG$36);
            }
        }
    }
}

