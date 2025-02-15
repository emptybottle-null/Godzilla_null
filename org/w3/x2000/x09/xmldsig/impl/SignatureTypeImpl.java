/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.w3.x2000.x09.xmldsig.KeyInfoType
 *  org.w3.x2000.x09.xmldsig.SignatureValueType
 */
package org.w3.x2000.x09.xmldsig.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.KeyInfoType;
import org.w3.x2000.x09.xmldsig.ObjectType;
import org.w3.x2000.x09.xmldsig.SignatureType;
import org.w3.x2000.x09.xmldsig.SignatureValueType;
import org.w3.x2000.x09.xmldsig.SignedInfoType;

public class SignatureTypeImpl
extends XmlComplexContentImpl
implements SignatureType {
    private static final long serialVersionUID = 1L;
    private static final QName SIGNEDINFO$0 = new QName("http://www.w3.org/2000/09/xmldsig#", "SignedInfo");
    private static final QName SIGNATUREVALUE$2 = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureValue");
    private static final QName KEYINFO$4 = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyInfo");
    private static final QName OBJECT$6 = new QName("http://www.w3.org/2000/09/xmldsig#", "Object");
    private static final QName ID$8 = new QName("", "Id");

    public SignatureTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignedInfoType getSignedInfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignedInfoType signedInfoType = null;
            signedInfoType = (SignedInfoType)((Object)this.get_store().find_element_user(SIGNEDINFO$0, 0));
            if (signedInfoType == null) {
                return null;
            }
            return signedInfoType;
        }
    }

    @Override
    public void setSignedInfo(SignedInfoType signedInfoType) {
        this.generatedSetterHelperImpl(signedInfoType, SIGNEDINFO$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignedInfoType addNewSignedInfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignedInfoType signedInfoType = null;
            signedInfoType = (SignedInfoType)((Object)this.get_store().add_element_user(SIGNEDINFO$0));
            return signedInfoType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignatureValueType getSignatureValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignatureValueType signatureValueType = null;
            signatureValueType = (SignatureValueType)this.get_store().find_element_user(SIGNATUREVALUE$2, 0);
            if (signatureValueType == null) {
                return null;
            }
            return signatureValueType;
        }
    }

    @Override
    public void setSignatureValue(SignatureValueType signatureValueType) {
        this.generatedSetterHelperImpl((XmlObject)signatureValueType, SIGNATUREVALUE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignatureValueType addNewSignatureValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignatureValueType signatureValueType = null;
            signatureValueType = (SignatureValueType)this.get_store().add_element_user(SIGNATUREVALUE$2);
            return signatureValueType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public KeyInfoType getKeyInfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            KeyInfoType keyInfoType = null;
            keyInfoType = (KeyInfoType)this.get_store().find_element_user(KEYINFO$4, 0);
            if (keyInfoType == null) {
                return null;
            }
            return keyInfoType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetKeyInfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(KEYINFO$4) != 0;
        }
    }

    @Override
    public void setKeyInfo(KeyInfoType keyInfoType) {
        this.generatedSetterHelperImpl((XmlObject)keyInfoType, KEYINFO$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public KeyInfoType addNewKeyInfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            KeyInfoType keyInfoType = null;
            keyInfoType = (KeyInfoType)this.get_store().add_element_user(KEYINFO$4);
            return keyInfoType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetKeyInfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(KEYINFO$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<ObjectType> getObjectList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ObjectList
            extends AbstractList<ObjectType> {
                ObjectList() {
                }

                @Override
                public ObjectType get(int n) {
                    return SignatureTypeImpl.this.getObjectArray(n);
                }

                @Override
                public ObjectType set(int n, ObjectType objectType) {
                    ObjectType objectType2 = SignatureTypeImpl.this.getObjectArray(n);
                    SignatureTypeImpl.this.setObjectArray(n, objectType);
                    return objectType2;
                }

                @Override
                public void add(int n, ObjectType objectType) {
                    SignatureTypeImpl.this.insertNewObject(n).set(objectType);
                }

                @Override
                public ObjectType remove(int n) {
                    ObjectType objectType = SignatureTypeImpl.this.getObjectArray(n);
                    SignatureTypeImpl.this.removeObject(n);
                    return objectType;
                }

                @Override
                public int size() {
                    return SignatureTypeImpl.this.sizeOfObjectArray();
                }
            }
            return new ObjectList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public ObjectType[] getObjectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OBJECT$6, arrayList);
            ObjectType[] objectTypeArray = new ObjectType[arrayList.size()];
            arrayList.toArray(objectTypeArray);
            return objectTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ObjectType getObjectArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ObjectType objectType = null;
            objectType = (ObjectType)((Object)this.get_store().find_element_user(OBJECT$6, n));
            if (objectType == null) {
                throw new IndexOutOfBoundsException();
            }
            return objectType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfObjectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OBJECT$6);
        }
    }

    @Override
    public void setObjectArray(ObjectType[] objectTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(objectTypeArray, OBJECT$6);
    }

    @Override
    public void setObjectArray(int n, ObjectType objectType) {
        this.generatedSetterHelperImpl(objectType, OBJECT$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ObjectType insertNewObject(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ObjectType objectType = null;
            objectType = (ObjectType)((Object)this.get_store().insert_element_user(OBJECT$6, n));
            return objectType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ObjectType addNewObject() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ObjectType objectType = null;
            objectType = (ObjectType)((Object)this.get_store().add_element_user(OBJECT$6));
            return objectType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeObject(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OBJECT$6, n);
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
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$8));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
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
            XmlID xmlID = null;
            xmlID = (XmlID)((Object)this.get_store().find_attribute_user(ID$8));
            return xmlID;
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
            return this.get_store().find_attribute_user(ID$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$8));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId(XmlID xmlID) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlID xmlID2 = null;
            xmlID2 = (XmlID)((Object)this.get_store().find_attribute_user(ID$8));
            if (xmlID2 == null) {
                xmlID2 = (XmlID)((Object)this.get_store().add_attribute_user(ID$8));
            }
            xmlID2.set(xmlID);
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
            this.get_store().remove_attribute(ID$8);
        }
    }
}

