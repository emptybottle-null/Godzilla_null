/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.substwsdl.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument;
import org.apache.xmlbeans.impl.xb.substwsdl.TImport;

public class DefinitionsDocumentImpl
extends XmlComplexContentImpl
implements DefinitionsDocument {
    private static final long serialVersionUID = 1L;
    private static final QName DEFINITIONS$0 = new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "definitions");

    public DefinitionsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DefinitionsDocument.Definitions getDefinitions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DefinitionsDocument.Definitions target = null;
            target = (DefinitionsDocument.Definitions)((Object)this.get_store().find_element_user(DEFINITIONS$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setDefinitions(DefinitionsDocument.Definitions definitions) {
        this.generatedSetterHelperImpl(definitions, DEFINITIONS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DefinitionsDocument.Definitions addNewDefinitions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DefinitionsDocument.Definitions target = null;
            target = (DefinitionsDocument.Definitions)((Object)this.get_store().add_element_user(DEFINITIONS$0));
            return target;
        }
    }

    public static class DefinitionsImpl
    extends XmlComplexContentImpl
    implements DefinitionsDocument.Definitions {
        private static final long serialVersionUID = 1L;
        private static final QName IMPORT$0 = new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "import");
        private static final QName TYPES$2 = new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "types");
        private static final QName MESSAGE$4 = new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "message");
        private static final QName BINDING$6 = new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "binding");
        private static final QName PORTTYPE$8 = new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "portType");
        private static final QName SERVICE$10 = new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "service");

        public DefinitionsImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TImport[] getImportArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(IMPORT$0, targetList);
                TImport[] result = new TImport[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TImport getImportArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TImport target = null;
                target = (TImport)((Object)this.get_store().find_element_user(IMPORT$0, i));
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
                return this.get_store().count_elements(IMPORT$0);
            }
        }

        @Override
        public void setImportArray(TImport[] ximportArray) {
            this.check_orphaned();
            this.arraySetterHelper(ximportArray, IMPORT$0);
        }

        @Override
        public void setImportArray(int i, TImport ximport) {
            this.generatedSetterHelperImpl(ximport, IMPORT$0, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TImport insertNewImport(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TImport target = null;
                target = (TImport)((Object)this.get_store().insert_element_user(IMPORT$0, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public TImport addNewImport() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                TImport target = null;
                target = (TImport)((Object)this.get_store().add_element_user(IMPORT$0));
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
                this.get_store().remove_element(IMPORT$0, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject[] getTypesArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(TYPES$2, targetList);
                XmlObject[] result = new XmlObject[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject getTypesArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().find_element_user(TYPES$2, i));
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
        public int sizeOfTypesArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(TYPES$2);
            }
        }

        @Override
        public void setTypesArray(XmlObject[] typesArray) {
            this.check_orphaned();
            this.arraySetterHelper(typesArray, TYPES$2);
        }

        @Override
        public void setTypesArray(int i, XmlObject types) {
            this.generatedSetterHelperImpl(types, TYPES$2, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject insertNewTypes(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().insert_element_user(TYPES$2, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject addNewTypes() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().add_element_user(TYPES$2));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeTypes(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(TYPES$2, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject[] getMessageArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(MESSAGE$4, targetList);
                XmlObject[] result = new XmlObject[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject getMessageArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().find_element_user(MESSAGE$4, i));
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
        public int sizeOfMessageArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(MESSAGE$4);
            }
        }

        @Override
        public void setMessageArray(XmlObject[] messageArray) {
            this.check_orphaned();
            this.arraySetterHelper(messageArray, MESSAGE$4);
        }

        @Override
        public void setMessageArray(int i, XmlObject message) {
            this.generatedSetterHelperImpl(message, MESSAGE$4, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject insertNewMessage(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().insert_element_user(MESSAGE$4, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject addNewMessage() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().add_element_user(MESSAGE$4));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeMessage(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(MESSAGE$4, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject[] getBindingArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(BINDING$6, targetList);
                XmlObject[] result = new XmlObject[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject getBindingArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().find_element_user(BINDING$6, i));
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
        public int sizeOfBindingArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(BINDING$6);
            }
        }

        @Override
        public void setBindingArray(XmlObject[] bindingArray) {
            this.check_orphaned();
            this.arraySetterHelper(bindingArray, BINDING$6);
        }

        @Override
        public void setBindingArray(int i, XmlObject binding) {
            this.generatedSetterHelperImpl(binding, BINDING$6, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject insertNewBinding(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().insert_element_user(BINDING$6, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject addNewBinding() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().add_element_user(BINDING$6));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeBinding(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(BINDING$6, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject[] getPortTypeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(PORTTYPE$8, targetList);
                XmlObject[] result = new XmlObject[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject getPortTypeArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().find_element_user(PORTTYPE$8, i));
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
        public int sizeOfPortTypeArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(PORTTYPE$8);
            }
        }

        @Override
        public void setPortTypeArray(XmlObject[] portTypeArray) {
            this.check_orphaned();
            this.arraySetterHelper(portTypeArray, PORTTYPE$8);
        }

        @Override
        public void setPortTypeArray(int i, XmlObject portType) {
            this.generatedSetterHelperImpl(portType, PORTTYPE$8, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject insertNewPortType(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().insert_element_user(PORTTYPE$8, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject addNewPortType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().add_element_user(PORTTYPE$8));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removePortType(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(PORTTYPE$8, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject[] getServiceArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(SERVICE$10, targetList);
                XmlObject[] result = new XmlObject[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject getServiceArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().find_element_user(SERVICE$10, i));
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
        public int sizeOfServiceArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(SERVICE$10);
            }
        }

        @Override
        public void setServiceArray(XmlObject[] serviceArray) {
            this.check_orphaned();
            this.arraySetterHelper(serviceArray, SERVICE$10);
        }

        @Override
        public void setServiceArray(int i, XmlObject service) {
            this.generatedSetterHelperImpl(service, SERVICE$10, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject insertNewService(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().insert_element_user(SERVICE$10, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlObject addNewService() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlObject target = null;
                target = (XmlObject)((Object)this.get_store().add_element_user(SERVICE$10));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeService(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(SERVICE$10, i);
            }
        }
    }
}

