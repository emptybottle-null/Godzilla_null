/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.CertifiedRolesListType
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertifiedRolesListType;
import org.etsi.uri.x01903.v13.ClaimedRolesListType;
import org.etsi.uri.x01903.v13.SignerRoleType;

public class SignerRoleTypeImpl
extends XmlComplexContentImpl
implements SignerRoleType {
    private static final long serialVersionUID = 1L;
    private static final QName CLAIMEDROLES$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "ClaimedRoles");
    private static final QName CERTIFIEDROLES$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CertifiedRoles");

    public SignerRoleTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ClaimedRolesListType getClaimedRoles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ClaimedRolesListType claimedRolesListType = null;
            claimedRolesListType = (ClaimedRolesListType)((Object)this.get_store().find_element_user(CLAIMEDROLES$0, 0));
            if (claimedRolesListType == null) {
                return null;
            }
            return claimedRolesListType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetClaimedRoles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLAIMEDROLES$0) != 0;
        }
    }

    @Override
    public void setClaimedRoles(ClaimedRolesListType claimedRolesListType) {
        this.generatedSetterHelperImpl(claimedRolesListType, CLAIMEDROLES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ClaimedRolesListType addNewClaimedRoles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ClaimedRolesListType claimedRolesListType = null;
            claimedRolesListType = (ClaimedRolesListType)((Object)this.get_store().add_element_user(CLAIMEDROLES$0));
            return claimedRolesListType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetClaimedRoles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLAIMEDROLES$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertifiedRolesListType getCertifiedRoles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertifiedRolesListType certifiedRolesListType = null;
            certifiedRolesListType = (CertifiedRolesListType)this.get_store().find_element_user(CERTIFIEDROLES$2, 0);
            if (certifiedRolesListType == null) {
                return null;
            }
            return certifiedRolesListType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCertifiedRoles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CERTIFIEDROLES$2) != 0;
        }
    }

    @Override
    public void setCertifiedRoles(CertifiedRolesListType certifiedRolesListType) {
        this.generatedSetterHelperImpl((XmlObject)certifiedRolesListType, CERTIFIEDROLES$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertifiedRolesListType addNewCertifiedRoles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertifiedRolesListType certifiedRolesListType = null;
            certifiedRolesListType = (CertifiedRolesListType)this.get_store().add_element_user(CERTIFIEDROLES$2);
            return certifiedRolesListType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCertifiedRoles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CERTIFIEDROLES$2, 0);
        }
    }
}

