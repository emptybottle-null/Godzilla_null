/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument;

public class TestsDocumentImpl
extends XmlComplexContentImpl
implements TestsDocument {
    private static final long serialVersionUID = 1L;
    private static final QName TESTS$0 = new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "tests");

    public TestsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TestsDocument.Tests getTests() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TestsDocument.Tests target = null;
            target = (TestsDocument.Tests)((Object)this.get_store().find_element_user(TESTS$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setTests(TestsDocument.Tests tests) {
        this.generatedSetterHelperImpl(tests, TESTS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TestsDocument.Tests addNewTests() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TestsDocument.Tests target = null;
            target = (TestsDocument.Tests)((Object)this.get_store().add_element_user(TESTS$0));
            return target;
        }
    }

    public static class TestsImpl
    extends XmlComplexContentImpl
    implements TestsDocument.Tests {
        private static final long serialVersionUID = 1L;
        private static final QName TEST$0 = new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "test");

        public TestsImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TestCase[] getTestArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(TEST$0, targetList);
                TestCase[] result = new TestCase[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TestCase getTestArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TestCase target = null;
                target = (TestCase)((Object)this.get_store().find_element_user(TEST$0, i));
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
        public int sizeOfTestArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(TEST$0);
            }
        }

        @Override
        public void setTestArray(TestCase[] testArray) {
            this.check_orphaned();
            this.arraySetterHelper(testArray, TEST$0);
        }

        @Override
        public void setTestArray(int i, TestCase test) {
            this.generatedSetterHelperImpl(test, TEST$0, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TestCase insertNewTest(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TestCase target = null;
                target = (TestCase)((Object)this.get_store().insert_element_user(TEST$0, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TestCase addNewTest() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TestCase target = null;
                target = (TestCase)((Object)this.get_store().add_element_user(TEST$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeTest(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(TEST$0, i);
            }
        }
    }
}

