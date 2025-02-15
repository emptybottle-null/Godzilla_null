/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;

public class TestCaseImpl
extends XmlComplexContentImpl
implements TestCase {
    private static final long serialVersionUID = 1L;
    private static final QName DESCRIPTION$0 = new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "description");
    private static final QName FILES$2 = new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "files");
    private static final QName ID$4 = new QName("", "id");
    private static final QName ORIGIN$6 = new QName("", "origin");
    private static final QName MODIFIED$8 = new QName("", "modified");

    public TestCaseImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getDescription() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(DESCRIPTION$0, 0));
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
    public XmlString xgetDescription() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_element_user(DESCRIPTION$0, 0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDescription() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DESCRIPTION$0) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDescription(String description) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(DESCRIPTION$0, 0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_element_user(DESCRIPTION$0));
            }
            target.setStringValue(description);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDescription(XmlString description) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_element_user(DESCRIPTION$0, 0));
            if (target == null) {
                target = (XmlString)((Object)this.get_store().add_element_user(DESCRIPTION$0));
            }
            target.set(description);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDescription() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DESCRIPTION$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TestCase.Files getFiles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TestCase.Files target = null;
            target = (TestCase.Files)((Object)this.get_store().find_element_user(FILES$2, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setFiles(TestCase.Files files) {
        this.generatedSetterHelperImpl(files, FILES$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TestCase.Files addNewFiles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TestCase.Files target = null;
            target = (TestCase.Files)((Object)this.get_store().add_element_user(FILES$2));
            return target;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$4));
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
            target = (XmlID)((Object)this.get_store().find_attribute_user(ID$4));
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
            return this.get_store().find_attribute_user(ID$4) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$4));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$4));
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
            target = (XmlID)((Object)this.get_store().find_attribute_user(ID$4));
            if (target == null) {
                target = (XmlID)((Object)this.get_store().add_attribute_user(ID$4));
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
            this.get_store().remove_attribute(ID$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ORIGIN$6));
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
    public XmlToken xgetOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_attribute_user(ORIGIN$6));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ORIGIN$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOrigin(String origin) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ORIGIN$6));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(ORIGIN$6));
            }
            target.setStringValue(origin);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOrigin(XmlToken origin) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_attribute_user(ORIGIN$6));
            if (target == null) {
                target = (XmlToken)((Object)this.get_store().add_attribute_user(ORIGIN$6));
            }
            target.set(origin);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ORIGIN$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getModified() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MODIFIED$8));
            if (target == null) {
                return false;
            }
            return target.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetModified() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(MODIFIED$8));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetModified() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MODIFIED$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setModified(boolean modified) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MODIFIED$8));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(MODIFIED$8));
            }
            target.setBooleanValue(modified);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetModified(XmlBoolean modified) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(MODIFIED$8));
            if (target == null) {
                target = (XmlBoolean)((Object)this.get_store().add_attribute_user(MODIFIED$8));
            }
            target.set(modified);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetModified() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MODIFIED$8);
        }
    }

    public static class FilesImpl
    extends XmlComplexContentImpl
    implements TestCase.Files {
        private static final long serialVersionUID = 1L;
        private static final QName FILE$0 = new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "file");

        public FilesImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public FileDesc[] getFileArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(FILE$0, targetList);
                FileDesc[] result = new FileDesc[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public FileDesc getFileArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FileDesc target = null;
                target = (FileDesc)((Object)this.get_store().find_element_user(FILE$0, i));
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
        public int sizeOfFileArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(FILE$0);
            }
        }

        @Override
        public void setFileArray(FileDesc[] fileArray) {
            this.check_orphaned();
            this.arraySetterHelper(fileArray, FILE$0);
        }

        @Override
        public void setFileArray(int i, FileDesc file) {
            this.generatedSetterHelperImpl(file, FILE$0, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public FileDesc insertNewFile(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FileDesc target = null;
                target = (FileDesc)((Object)this.get_store().insert_element_user(FILE$0, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public FileDesc addNewFile() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FileDesc target = null;
                target = (FileDesc)((Object)this.get_store().add_element_user(FILE$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeFile(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(FILE$0, i);
            }
        }
    }
}

