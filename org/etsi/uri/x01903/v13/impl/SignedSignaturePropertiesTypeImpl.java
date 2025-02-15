/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.SignatureProductionPlaceType
 */
package org.etsi.uri.x01903.v13.impl;

import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType;
import org.etsi.uri.x01903.v13.SignatureProductionPlaceType;
import org.etsi.uri.x01903.v13.SignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.SignerRoleType;

public class SignedSignaturePropertiesTypeImpl
extends XmlComplexContentImpl
implements SignedSignaturePropertiesType {
    private static final long serialVersionUID = 1L;
    private static final QName SIGNINGTIME$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SigningTime");
    private static final QName SIGNINGCERTIFICATE$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SigningCertificate");
    private static final QName SIGNATUREPOLICYIDENTIFIER$4 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SignaturePolicyIdentifier");
    private static final QName SIGNATUREPRODUCTIONPLACE$6 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SignatureProductionPlace");
    private static final QName SIGNERROLE$8 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SignerRole");
    private static final QName ID$10 = new QName("", "Id");

    public SignedSignaturePropertiesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Calendar getSigningTime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNINGTIME$0, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getCalendarValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDateTime xgetSigningTime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime = null;
            xmlDateTime = (XmlDateTime)((Object)this.get_store().find_element_user(SIGNINGTIME$0, 0));
            return xmlDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSigningTime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNINGTIME$0) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSigningTime(Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNINGTIME$0, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SIGNINGTIME$0));
            }
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSigningTime(XmlDateTime xmlDateTime) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime2 = null;
            xmlDateTime2 = (XmlDateTime)((Object)this.get_store().find_element_user(SIGNINGTIME$0, 0));
            if (xmlDateTime2 == null) {
                xmlDateTime2 = (XmlDateTime)((Object)this.get_store().add_element_user(SIGNINGTIME$0));
            }
            xmlDateTime2.set(xmlDateTime);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSigningTime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNINGTIME$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertIDListType getSigningCertificate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertIDListType certIDListType = null;
            certIDListType = (CertIDListType)((Object)this.get_store().find_element_user(SIGNINGCERTIFICATE$2, 0));
            if (certIDListType == null) {
                return null;
            }
            return certIDListType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSigningCertificate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNINGCERTIFICATE$2) != 0;
        }
    }

    @Override
    public void setSigningCertificate(CertIDListType certIDListType) {
        this.generatedSetterHelperImpl(certIDListType, SIGNINGCERTIFICATE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertIDListType addNewSigningCertificate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertIDListType certIDListType = null;
            certIDListType = (CertIDListType)((Object)this.get_store().add_element_user(SIGNINGCERTIFICATE$2));
            return certIDListType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSigningCertificate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNINGCERTIFICATE$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignaturePolicyIdentifierType getSignaturePolicyIdentifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignaturePolicyIdentifierType signaturePolicyIdentifierType = null;
            signaturePolicyIdentifierType = (SignaturePolicyIdentifierType)((Object)this.get_store().find_element_user(SIGNATUREPOLICYIDENTIFIER$4, 0));
            if (signaturePolicyIdentifierType == null) {
                return null;
            }
            return signaturePolicyIdentifierType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSignaturePolicyIdentifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNATUREPOLICYIDENTIFIER$4) != 0;
        }
    }

    @Override
    public void setSignaturePolicyIdentifier(SignaturePolicyIdentifierType signaturePolicyIdentifierType) {
        this.generatedSetterHelperImpl(signaturePolicyIdentifierType, SIGNATUREPOLICYIDENTIFIER$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignaturePolicyIdentifierType addNewSignaturePolicyIdentifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignaturePolicyIdentifierType signaturePolicyIdentifierType = null;
            signaturePolicyIdentifierType = (SignaturePolicyIdentifierType)((Object)this.get_store().add_element_user(SIGNATUREPOLICYIDENTIFIER$4));
            return signaturePolicyIdentifierType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSignaturePolicyIdentifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNATUREPOLICYIDENTIFIER$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignatureProductionPlaceType getSignatureProductionPlace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignatureProductionPlaceType signatureProductionPlaceType = null;
            signatureProductionPlaceType = (SignatureProductionPlaceType)this.get_store().find_element_user(SIGNATUREPRODUCTIONPLACE$6, 0);
            if (signatureProductionPlaceType == null) {
                return null;
            }
            return signatureProductionPlaceType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSignatureProductionPlace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNATUREPRODUCTIONPLACE$6) != 0;
        }
    }

    @Override
    public void setSignatureProductionPlace(SignatureProductionPlaceType signatureProductionPlaceType) {
        this.generatedSetterHelperImpl((XmlObject)signatureProductionPlaceType, SIGNATUREPRODUCTIONPLACE$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignatureProductionPlaceType addNewSignatureProductionPlace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignatureProductionPlaceType signatureProductionPlaceType = null;
            signatureProductionPlaceType = (SignatureProductionPlaceType)this.get_store().add_element_user(SIGNATUREPRODUCTIONPLACE$6);
            return signatureProductionPlaceType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSignatureProductionPlace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNATUREPRODUCTIONPLACE$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignerRoleType getSignerRole() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignerRoleType signerRoleType = null;
            signerRoleType = (SignerRoleType)((Object)this.get_store().find_element_user(SIGNERROLE$8, 0));
            if (signerRoleType == null) {
                return null;
            }
            return signerRoleType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSignerRole() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNERROLE$8) != 0;
        }
    }

    @Override
    public void setSignerRole(SignerRoleType signerRoleType) {
        this.generatedSetterHelperImpl(signerRoleType, SIGNERROLE$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignerRoleType addNewSignerRole() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignerRoleType signerRoleType = null;
            signerRoleType = (SignerRoleType)((Object)this.get_store().add_element_user(SIGNERROLE$8));
            return signerRoleType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSignerRole() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNERROLE$8, 0);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$10));
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
            xmlID = (XmlID)((Object)this.get_store().find_attribute_user(ID$10));
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
            return this.get_store().find_attribute_user(ID$10) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$10));
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
            xmlID2 = (XmlID)((Object)this.get_store().find_attribute_user(ID$10));
            if (xmlID2 == null) {
                xmlID2 = (XmlID)((Object)this.get_store().add_attribute_user(ID$10));
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
            this.get_store().remove_attribute(ID$10);
        }
    }
}

