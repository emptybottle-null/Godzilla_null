/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc;

public class FileDescImpl
extends XmlComplexContentImpl
implements FileDesc {
    private static final long serialVersionUID = 1L;
    private static final QName CODE$0 = new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "code");
    private static final QName TSDIR$2 = new QName("", "tsDir");
    private static final QName FOLDER$4 = new QName("", "folder");
    private static final QName FILENAME$6 = new QName("", "fileName");
    private static final QName ROLE$8 = new QName("", "role");
    private static final QName VALIDITY$10 = new QName("", "validity");

    public FileDescImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Code getCode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Code target = null;
            target = (Code)((Object)this.get_store().find_element_user(CODE$0, 0));
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
    public boolean isSetCode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CODE$0) != 0;
        }
    }

    @Override
    public void setCode(Code code) {
        this.generatedSetterHelperImpl(code, CODE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Code addNewCode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Code target = null;
            target = (Code)((Object)this.get_store().add_element_user(CODE$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CODE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTsDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(TSDIR$2));
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
    public XmlToken xgetTsDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_attribute_user(TSDIR$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTsDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TSDIR$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTsDir(String tsDir) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(TSDIR$2));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(TSDIR$2));
            }
            target.setStringValue(tsDir);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTsDir(XmlToken tsDir) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_attribute_user(TSDIR$2));
            if (target == null) {
                target = (XmlToken)((Object)this.get_store().add_attribute_user(TSDIR$2));
            }
            target.set(tsDir);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTsDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TSDIR$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFolder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FOLDER$4));
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
    public XmlToken xgetFolder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_attribute_user(FOLDER$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFolder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FOLDER$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFolder(String folder) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FOLDER$4));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(FOLDER$4));
            }
            target.setStringValue(folder);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFolder(XmlToken folder) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_attribute_user(FOLDER$4));
            if (target == null) {
                target = (XmlToken)((Object)this.get_store().add_attribute_user(FOLDER$4));
            }
            target.set(folder);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFolder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FOLDER$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFileName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FILENAME$6));
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
    public XmlToken xgetFileName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_attribute_user(FILENAME$6));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFileName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FILENAME$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFileName(String fileName) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FILENAME$6));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(FILENAME$6));
            }
            target.setStringValue(fileName);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFileName(XmlToken fileName) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_attribute_user(FILENAME$6));
            if (target == null) {
                target = (XmlToken)((Object)this.get_store().add_attribute_user(FILENAME$6));
            }
            target.set(fileName);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFileName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FILENAME$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FileDesc.Role.Enum getRole() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ROLE$8));
            if (target == null) {
                return null;
            }
            return (FileDesc.Role.Enum)target.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FileDesc.Role xgetRole() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FileDesc.Role target = null;
            target = (FileDesc.Role)((Object)this.get_store().find_attribute_user(ROLE$8));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRole() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ROLE$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRole(FileDesc.Role.Enum role) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ROLE$8));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(ROLE$8));
            }
            target.setEnumValue(role);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRole(FileDesc.Role role) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FileDesc.Role target = null;
            target = (FileDesc.Role)((Object)this.get_store().find_attribute_user(ROLE$8));
            if (target == null) {
                target = (FileDesc.Role)((Object)this.get_store().add_attribute_user(ROLE$8));
            }
            target.set(role);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRole() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ROLE$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getValidity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(VALIDITY$10));
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
    public XmlBoolean xgetValidity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(VALIDITY$10));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetValidity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(VALIDITY$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setValidity(boolean validity) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(VALIDITY$10));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(VALIDITY$10));
            }
            target.setBooleanValue(validity);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetValidity(XmlBoolean validity) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(VALIDITY$10));
            if (target == null) {
                target = (XmlBoolean)((Object)this.get_store().add_attribute_user(VALIDITY$10));
            }
            target.set(validity);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetValidity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(VALIDITY$10);
        }
    }

    public static class RoleImpl
    extends JavaStringEnumerationHolderEx
    implements FileDesc.Role {
        private static final long serialVersionUID = 1L;

        public RoleImpl(SchemaType sType) {
            super(sType, false);
        }

        protected RoleImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}

