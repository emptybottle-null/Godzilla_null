/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class UnionDocumentImpl
extends XmlComplexContentImpl
implements UnionDocument {
    private static final long serialVersionUID = 1L;
    private static final QName UNION$0 = new QName("http://www.w3.org/2001/XMLSchema", "union");

    public UnionDocumentImpl(SchemaType sType) {
        super(sType);
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
            target = (UnionDocument.Union)((Object)this.get_store().find_element_user(UNION$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setUnion(UnionDocument.Union union) {
        this.generatedSetterHelperImpl(union, UNION$0, 0, (short)1);
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
            target = (UnionDocument.Union)((Object)this.get_store().add_element_user(UNION$0));
            return target;
        }
    }

    public static class UnionImpl
    extends AnnotatedImpl
    implements UnionDocument.Union {
        private static final long serialVersionUID = 1L;
        private static final QName SIMPLETYPE$0 = new QName("http://www.w3.org/2001/XMLSchema", "simpleType");
        private static final QName MEMBERTYPES$2 = new QName("", "memberTypes");

        public UnionImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public LocalSimpleType[] getSimpleTypeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(SIMPLETYPE$0, targetList);
                LocalSimpleType[] result = new LocalSimpleType[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public LocalSimpleType getSimpleTypeArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                LocalSimpleType target = null;
                target = (LocalSimpleType)((Object)this.get_store().find_element_user(SIMPLETYPE$0, i));
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
                return this.get_store().count_elements(SIMPLETYPE$0);
            }
        }

        @Override
        public void setSimpleTypeArray(LocalSimpleType[] simpleTypeArray) {
            this.check_orphaned();
            this.arraySetterHelper(simpleTypeArray, SIMPLETYPE$0);
        }

        @Override
        public void setSimpleTypeArray(int i, LocalSimpleType simpleType) {
            this.generatedSetterHelperImpl(simpleType, SIMPLETYPE$0, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public LocalSimpleType insertNewSimpleType(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                LocalSimpleType target = null;
                target = (LocalSimpleType)((Object)this.get_store().insert_element_user(SIMPLETYPE$0, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public LocalSimpleType addNewSimpleType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                LocalSimpleType target = null;
                target = (LocalSimpleType)((Object)this.get_store().add_element_user(SIMPLETYPE$0));
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
                this.get_store().remove_element(SIMPLETYPE$0, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public List getMemberTypes() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(MEMBERTYPES$2));
                if (target == null) {
                    return null;
                }
                return target.getListValue();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public UnionDocument.Union.MemberTypes xgetMemberTypes() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                UnionDocument.Union.MemberTypes target = null;
                target = (UnionDocument.Union.MemberTypes)((Object)this.get_store().find_attribute_user(MEMBERTYPES$2));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetMemberTypes() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(MEMBERTYPES$2) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setMemberTypes(List memberTypes) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(MEMBERTYPES$2));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(MEMBERTYPES$2));
                }
                target.setListValue(memberTypes);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetMemberTypes(UnionDocument.Union.MemberTypes memberTypes) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                UnionDocument.Union.MemberTypes target = null;
                target = (UnionDocument.Union.MemberTypes)((Object)this.get_store().find_attribute_user(MEMBERTYPES$2));
                if (target == null) {
                    target = (UnionDocument.Union.MemberTypes)((Object)this.get_store().add_attribute_user(MEMBERTYPES$2));
                }
                target.set(memberTypes);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetMemberTypes() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(MEMBERTYPES$2);
            }
        }

        public static class MemberTypesImpl
        extends XmlListImpl
        implements UnionDocument.Union.MemberTypes {
            private static final long serialVersionUID = 1L;

            public MemberTypesImpl(SchemaType sType) {
                super(sType, false);
            }

            protected MemberTypesImpl(SchemaType sType, boolean b) {
                super(sType, b);
            }
        }
    }
}

