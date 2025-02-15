/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;

public class ConfigDocumentImpl
extends XmlComplexContentImpl
implements ConfigDocument {
    private static final long serialVersionUID = 1L;
    private static final QName CONFIG$0 = new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "config");

    public ConfigDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ConfigDocument.Config getConfig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ConfigDocument.Config target = null;
            target = (ConfigDocument.Config)((Object)this.get_store().find_element_user(CONFIG$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setConfig(ConfigDocument.Config config) {
        this.generatedSetterHelperImpl(config, CONFIG$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ConfigDocument.Config addNewConfig() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ConfigDocument.Config target = null;
            target = (ConfigDocument.Config)((Object)this.get_store().add_element_user(CONFIG$0));
            return target;
        }
    }

    public static class ConfigImpl
    extends XmlComplexContentImpl
    implements ConfigDocument.Config {
        private static final long serialVersionUID = 1L;
        private static final QName NAMESPACE$0 = new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "namespace");
        private static final QName QNAME$2 = new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "qname");
        private static final QName EXTENSION$4 = new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "extension");
        private static final QName USERTYPE$6 = new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "usertype");

        public ConfigImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Nsconfig[] getNamespaceArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(NAMESPACE$0, targetList);
                Nsconfig[] result = new Nsconfig[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Nsconfig getNamespaceArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Nsconfig target = null;
                target = (Nsconfig)((Object)this.get_store().find_element_user(NAMESPACE$0, i));
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
        public int sizeOfNamespaceArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(NAMESPACE$0);
            }
        }

        @Override
        public void setNamespaceArray(Nsconfig[] namespaceArray) {
            this.check_orphaned();
            this.arraySetterHelper(namespaceArray, NAMESPACE$0);
        }

        @Override
        public void setNamespaceArray(int i, Nsconfig namespace) {
            this.generatedSetterHelperImpl(namespace, NAMESPACE$0, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Nsconfig insertNewNamespace(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Nsconfig target = null;
                target = (Nsconfig)((Object)this.get_store().insert_element_user(NAMESPACE$0, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Nsconfig addNewNamespace() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Nsconfig target = null;
                target = (Nsconfig)((Object)this.get_store().add_element_user(NAMESPACE$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeNamespace(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(NAMESPACE$0, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Qnameconfig[] getQnameArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(QNAME$2, targetList);
                Qnameconfig[] result = new Qnameconfig[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Qnameconfig getQnameArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Qnameconfig target = null;
                target = (Qnameconfig)((Object)this.get_store().find_element_user(QNAME$2, i));
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
        public int sizeOfQnameArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(QNAME$2);
            }
        }

        @Override
        public void setQnameArray(Qnameconfig[] qnameArray) {
            this.check_orphaned();
            this.arraySetterHelper(qnameArray, QNAME$2);
        }

        @Override
        public void setQnameArray(int i, Qnameconfig qname) {
            this.generatedSetterHelperImpl(qname, QNAME$2, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Qnameconfig insertNewQname(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Qnameconfig target = null;
                target = (Qnameconfig)((Object)this.get_store().insert_element_user(QNAME$2, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Qnameconfig addNewQname() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Qnameconfig target = null;
                target = (Qnameconfig)((Object)this.get_store().add_element_user(QNAME$2));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeQname(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(QNAME$2, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Extensionconfig[] getExtensionArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(EXTENSION$4, targetList);
                Extensionconfig[] result = new Extensionconfig[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Extensionconfig getExtensionArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Extensionconfig target = null;
                target = (Extensionconfig)((Object)this.get_store().find_element_user(EXTENSION$4, i));
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
        public int sizeOfExtensionArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(EXTENSION$4);
            }
        }

        @Override
        public void setExtensionArray(Extensionconfig[] extensionArray) {
            this.check_orphaned();
            this.arraySetterHelper(extensionArray, EXTENSION$4);
        }

        @Override
        public void setExtensionArray(int i, Extensionconfig extension) {
            this.generatedSetterHelperImpl(extension, EXTENSION$4, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Extensionconfig insertNewExtension(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Extensionconfig target = null;
                target = (Extensionconfig)((Object)this.get_store().insert_element_user(EXTENSION$4, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Extensionconfig addNewExtension() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Extensionconfig target = null;
                target = (Extensionconfig)((Object)this.get_store().add_element_user(EXTENSION$4));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeExtension(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(EXTENSION$4, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Usertypeconfig[] getUsertypeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(USERTYPE$6, targetList);
                Usertypeconfig[] result = new Usertypeconfig[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Usertypeconfig getUsertypeArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Usertypeconfig target = null;
                target = (Usertypeconfig)((Object)this.get_store().find_element_user(USERTYPE$6, i));
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
        public int sizeOfUsertypeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(USERTYPE$6);
            }
        }

        @Override
        public void setUsertypeArray(Usertypeconfig[] usertypeArray) {
            this.check_orphaned();
            this.arraySetterHelper(usertypeArray, USERTYPE$6);
        }

        @Override
        public void setUsertypeArray(int i, Usertypeconfig usertype) {
            this.generatedSetterHelperImpl(usertype, USERTYPE$6, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Usertypeconfig insertNewUsertype(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Usertypeconfig target = null;
                target = (Usertypeconfig)((Object)this.get_store().insert_element_user(USERTYPE$6, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Usertypeconfig addNewUsertype() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Usertypeconfig target = null;
                target = (Usertypeconfig)((Object)this.get_store().add_element_user(USERTYPE$6));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeUsertype(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(USERTYPE$6, i);
            }
        }
    }
}

