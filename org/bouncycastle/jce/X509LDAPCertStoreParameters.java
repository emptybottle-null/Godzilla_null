/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce;

import java.security.cert.CertStoreParameters;
import java.security.cert.LDAPCertStoreParameters;
import org.bouncycastle.x509.X509StoreParameters;

public class X509LDAPCertStoreParameters
implements X509StoreParameters,
CertStoreParameters {
    private String ldapURL;
    private String baseDN;
    private String userCertificateAttribute;
    private String cACertificateAttribute;
    private String crossCertificateAttribute;
    private String certificateRevocationListAttribute;
    private String deltaRevocationListAttribute;
    private String authorityRevocationListAttribute;
    private String attributeCertificateAttributeAttribute;
    private String aACertificateAttribute;
    private String attributeDescriptorCertificateAttribute;
    private String attributeCertificateRevocationListAttribute;
    private String attributeAuthorityRevocationListAttribute;
    private String ldapUserCertificateAttributeName;
    private String ldapCACertificateAttributeName;
    private String ldapCrossCertificateAttributeName;
    private String ldapCertificateRevocationListAttributeName;
    private String ldapDeltaRevocationListAttributeName;
    private String ldapAuthorityRevocationListAttributeName;
    private String ldapAttributeCertificateAttributeAttributeName;
    private String ldapAACertificateAttributeName;
    private String ldapAttributeDescriptorCertificateAttributeName;
    private String ldapAttributeCertificateRevocationListAttributeName;
    private String ldapAttributeAuthorityRevocationListAttributeName;
    private String userCertificateSubjectAttributeName;
    private String cACertificateSubjectAttributeName;
    private String crossCertificateSubjectAttributeName;
    private String certificateRevocationListIssuerAttributeName;
    private String deltaRevocationListIssuerAttributeName;
    private String authorityRevocationListIssuerAttributeName;
    private String attributeCertificateAttributeSubjectAttributeName;
    private String aACertificateSubjectAttributeName;
    private String attributeDescriptorCertificateSubjectAttributeName;
    private String attributeCertificateRevocationListIssuerAttributeName;
    private String attributeAuthorityRevocationListIssuerAttributeName;
    private String searchForSerialNumberIn;

    private X509LDAPCertStoreParameters(Builder builder) {
        this.ldapURL = builder.ldapURL;
        this.baseDN = builder.baseDN;
        this.userCertificateAttribute = builder.userCertificateAttribute;
        this.cACertificateAttribute = builder.cACertificateAttribute;
        this.crossCertificateAttribute = builder.crossCertificateAttribute;
        this.certificateRevocationListAttribute = builder.certificateRevocationListAttribute;
        this.deltaRevocationListAttribute = builder.deltaRevocationListAttribute;
        this.authorityRevocationListAttribute = builder.authorityRevocationListAttribute;
        this.attributeCertificateAttributeAttribute = builder.attributeCertificateAttributeAttribute;
        this.aACertificateAttribute = builder.aACertificateAttribute;
        this.attributeDescriptorCertificateAttribute = builder.attributeDescriptorCertificateAttribute;
        this.attributeCertificateRevocationListAttribute = builder.attributeCertificateRevocationListAttribute;
        this.attributeAuthorityRevocationListAttribute = builder.attributeAuthorityRevocationListAttribute;
        this.ldapUserCertificateAttributeName = builder.ldapUserCertificateAttributeName;
        this.ldapCACertificateAttributeName = builder.ldapCACertificateAttributeName;
        this.ldapCrossCertificateAttributeName = builder.ldapCrossCertificateAttributeName;
        this.ldapCertificateRevocationListAttributeName = builder.ldapCertificateRevocationListAttributeName;
        this.ldapDeltaRevocationListAttributeName = builder.ldapDeltaRevocationListAttributeName;
        this.ldapAuthorityRevocationListAttributeName = builder.ldapAuthorityRevocationListAttributeName;
        this.ldapAttributeCertificateAttributeAttributeName = builder.ldapAttributeCertificateAttributeAttributeName;
        this.ldapAACertificateAttributeName = builder.ldapAACertificateAttributeName;
        this.ldapAttributeDescriptorCertificateAttributeName = builder.ldapAttributeDescriptorCertificateAttributeName;
        this.ldapAttributeCertificateRevocationListAttributeName = builder.ldapAttributeCertificateRevocationListAttributeName;
        this.ldapAttributeAuthorityRevocationListAttributeName = builder.ldapAttributeAuthorityRevocationListAttributeName;
        this.userCertificateSubjectAttributeName = builder.userCertificateSubjectAttributeName;
        this.cACertificateSubjectAttributeName = builder.cACertificateSubjectAttributeName;
        this.crossCertificateSubjectAttributeName = builder.crossCertificateSubjectAttributeName;
        this.certificateRevocationListIssuerAttributeName = builder.certificateRevocationListIssuerAttributeName;
        this.deltaRevocationListIssuerAttributeName = builder.deltaRevocationListIssuerAttributeName;
        this.authorityRevocationListIssuerAttributeName = builder.authorityRevocationListIssuerAttributeName;
        this.attributeCertificateAttributeSubjectAttributeName = builder.attributeCertificateAttributeSubjectAttributeName;
        this.aACertificateSubjectAttributeName = builder.aACertificateSubjectAttributeName;
        this.attributeDescriptorCertificateSubjectAttributeName = builder.attributeDescriptorCertificateSubjectAttributeName;
        this.attributeCertificateRevocationListIssuerAttributeName = builder.attributeCertificateRevocationListIssuerAttributeName;
        this.attributeAuthorityRevocationListIssuerAttributeName = builder.attributeAuthorityRevocationListIssuerAttributeName;
        this.searchForSerialNumberIn = builder.searchForSerialNumberIn;
    }

    public Object clone() {
        return this;
    }

    public boolean equal(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof X509LDAPCertStoreParameters)) {
            return false;
        }
        X509LDAPCertStoreParameters x509LDAPCertStoreParameters = (X509LDAPCertStoreParameters)object;
        return this.checkField(this.ldapURL, x509LDAPCertStoreParameters.ldapURL) && this.checkField(this.baseDN, x509LDAPCertStoreParameters.baseDN) && this.checkField(this.userCertificateAttribute, x509LDAPCertStoreParameters.userCertificateAttribute) && this.checkField(this.cACertificateAttribute, x509LDAPCertStoreParameters.cACertificateAttribute) && this.checkField(this.crossCertificateAttribute, x509LDAPCertStoreParameters.crossCertificateAttribute) && this.checkField(this.certificateRevocationListAttribute, x509LDAPCertStoreParameters.certificateRevocationListAttribute) && this.checkField(this.deltaRevocationListAttribute, x509LDAPCertStoreParameters.deltaRevocationListAttribute) && this.checkField(this.authorityRevocationListAttribute, x509LDAPCertStoreParameters.authorityRevocationListAttribute) && this.checkField(this.attributeCertificateAttributeAttribute, x509LDAPCertStoreParameters.attributeCertificateAttributeAttribute) && this.checkField(this.aACertificateAttribute, x509LDAPCertStoreParameters.aACertificateAttribute) && this.checkField(this.attributeDescriptorCertificateAttribute, x509LDAPCertStoreParameters.attributeDescriptorCertificateAttribute) && this.checkField(this.attributeCertificateRevocationListAttribute, x509LDAPCertStoreParameters.attributeCertificateRevocationListAttribute) && this.checkField(this.attributeAuthorityRevocationListAttribute, x509LDAPCertStoreParameters.attributeAuthorityRevocationListAttribute) && this.checkField(this.ldapUserCertificateAttributeName, x509LDAPCertStoreParameters.ldapUserCertificateAttributeName) && this.checkField(this.ldapCACertificateAttributeName, x509LDAPCertStoreParameters.ldapCACertificateAttributeName) && this.checkField(this.ldapCrossCertificateAttributeName, x509LDAPCertStoreParameters.ldapCrossCertificateAttributeName) && this.checkField(this.ldapCertificateRevocationListAttributeName, x509LDAPCertStoreParameters.ldapCertificateRevocationListAttributeName) && this.checkField(this.ldapDeltaRevocationListAttributeName, x509LDAPCertStoreParameters.ldapDeltaRevocationListAttributeName) && this.checkField(this.ldapAuthorityRevocationListAttributeName, x509LDAPCertStoreParameters.ldapAuthorityRevocationListAttributeName) && this.checkField(this.ldapAttributeCertificateAttributeAttributeName, x509LDAPCertStoreParameters.ldapAttributeCertificateAttributeAttributeName) && this.checkField(this.ldapAACertificateAttributeName, x509LDAPCertStoreParameters.ldapAACertificateAttributeName) && this.checkField(this.ldapAttributeDescriptorCertificateAttributeName, x509LDAPCertStoreParameters.ldapAttributeDescriptorCertificateAttributeName) && this.checkField(this.ldapAttributeCertificateRevocationListAttributeName, x509LDAPCertStoreParameters.ldapAttributeCertificateRevocationListAttributeName) && this.checkField(this.ldapAttributeAuthorityRevocationListAttributeName, x509LDAPCertStoreParameters.ldapAttributeAuthorityRevocationListAttributeName) && this.checkField(this.userCertificateSubjectAttributeName, x509LDAPCertStoreParameters.userCertificateSubjectAttributeName) && this.checkField(this.cACertificateSubjectAttributeName, x509LDAPCertStoreParameters.cACertificateSubjectAttributeName) && this.checkField(this.crossCertificateSubjectAttributeName, x509LDAPCertStoreParameters.crossCertificateSubjectAttributeName) && this.checkField(this.certificateRevocationListIssuerAttributeName, x509LDAPCertStoreParameters.certificateRevocationListIssuerAttributeName) && this.checkField(this.deltaRevocationListIssuerAttributeName, x509LDAPCertStoreParameters.deltaRevocationListIssuerAttributeName) && this.checkField(this.authorityRevocationListIssuerAttributeName, x509LDAPCertStoreParameters.authorityRevocationListIssuerAttributeName) && this.checkField(this.attributeCertificateAttributeSubjectAttributeName, x509LDAPCertStoreParameters.attributeCertificateAttributeSubjectAttributeName) && this.checkField(this.aACertificateSubjectAttributeName, x509LDAPCertStoreParameters.aACertificateSubjectAttributeName) && this.checkField(this.attributeDescriptorCertificateSubjectAttributeName, x509LDAPCertStoreParameters.attributeDescriptorCertificateSubjectAttributeName) && this.checkField(this.attributeCertificateRevocationListIssuerAttributeName, x509LDAPCertStoreParameters.attributeCertificateRevocationListIssuerAttributeName) && this.checkField(this.attributeAuthorityRevocationListIssuerAttributeName, x509LDAPCertStoreParameters.attributeAuthorityRevocationListIssuerAttributeName) && this.checkField(this.searchForSerialNumberIn, x509LDAPCertStoreParameters.searchForSerialNumberIn);
    }

    private boolean checkField(Object object, Object object2) {
        if (object == object2) {
            return true;
        }
        if (object == null) {
            return false;
        }
        return object.equals(object2);
    }

    public int hashCode() {
        int n = 0;
        n = this.addHashCode(n, this.userCertificateAttribute);
        n = this.addHashCode(n, this.cACertificateAttribute);
        n = this.addHashCode(n, this.crossCertificateAttribute);
        n = this.addHashCode(n, this.certificateRevocationListAttribute);
        n = this.addHashCode(n, this.deltaRevocationListAttribute);
        n = this.addHashCode(n, this.authorityRevocationListAttribute);
        n = this.addHashCode(n, this.attributeCertificateAttributeAttribute);
        n = this.addHashCode(n, this.aACertificateAttribute);
        n = this.addHashCode(n, this.attributeDescriptorCertificateAttribute);
        n = this.addHashCode(n, this.attributeCertificateRevocationListAttribute);
        n = this.addHashCode(n, this.attributeAuthorityRevocationListAttribute);
        n = this.addHashCode(n, this.ldapUserCertificateAttributeName);
        n = this.addHashCode(n, this.ldapCACertificateAttributeName);
        n = this.addHashCode(n, this.ldapCrossCertificateAttributeName);
        n = this.addHashCode(n, this.ldapCertificateRevocationListAttributeName);
        n = this.addHashCode(n, this.ldapDeltaRevocationListAttributeName);
        n = this.addHashCode(n, this.ldapAuthorityRevocationListAttributeName);
        n = this.addHashCode(n, this.ldapAttributeCertificateAttributeAttributeName);
        n = this.addHashCode(n, this.ldapAACertificateAttributeName);
        n = this.addHashCode(n, this.ldapAttributeDescriptorCertificateAttributeName);
        n = this.addHashCode(n, this.ldapAttributeCertificateRevocationListAttributeName);
        n = this.addHashCode(n, this.ldapAttributeAuthorityRevocationListAttributeName);
        n = this.addHashCode(n, this.userCertificateSubjectAttributeName);
        n = this.addHashCode(n, this.cACertificateSubjectAttributeName);
        n = this.addHashCode(n, this.crossCertificateSubjectAttributeName);
        n = this.addHashCode(n, this.certificateRevocationListIssuerAttributeName);
        n = this.addHashCode(n, this.deltaRevocationListIssuerAttributeName);
        n = this.addHashCode(n, this.authorityRevocationListIssuerAttributeName);
        n = this.addHashCode(n, this.attributeCertificateAttributeSubjectAttributeName);
        n = this.addHashCode(n, this.aACertificateSubjectAttributeName);
        n = this.addHashCode(n, this.attributeDescriptorCertificateSubjectAttributeName);
        n = this.addHashCode(n, this.attributeCertificateRevocationListIssuerAttributeName);
        n = this.addHashCode(n, this.attributeAuthorityRevocationListIssuerAttributeName);
        n = this.addHashCode(n, this.searchForSerialNumberIn);
        return n;
    }

    private int addHashCode(int n, Object object) {
        return n * 29 + (object == null ? 0 : object.hashCode());
    }

    public String getAACertificateAttribute() {
        return this.aACertificateAttribute;
    }

    public String getAACertificateSubjectAttributeName() {
        return this.aACertificateSubjectAttributeName;
    }

    public String getAttributeAuthorityRevocationListAttribute() {
        return this.attributeAuthorityRevocationListAttribute;
    }

    public String getAttributeAuthorityRevocationListIssuerAttributeName() {
        return this.attributeAuthorityRevocationListIssuerAttributeName;
    }

    public String getAttributeCertificateAttributeAttribute() {
        return this.attributeCertificateAttributeAttribute;
    }

    public String getAttributeCertificateAttributeSubjectAttributeName() {
        return this.attributeCertificateAttributeSubjectAttributeName;
    }

    public String getAttributeCertificateRevocationListAttribute() {
        return this.attributeCertificateRevocationListAttribute;
    }

    public String getAttributeCertificateRevocationListIssuerAttributeName() {
        return this.attributeCertificateRevocationListIssuerAttributeName;
    }

    public String getAttributeDescriptorCertificateAttribute() {
        return this.attributeDescriptorCertificateAttribute;
    }

    public String getAttributeDescriptorCertificateSubjectAttributeName() {
        return this.attributeDescriptorCertificateSubjectAttributeName;
    }

    public String getAuthorityRevocationListAttribute() {
        return this.authorityRevocationListAttribute;
    }

    public String getAuthorityRevocationListIssuerAttributeName() {
        return this.authorityRevocationListIssuerAttributeName;
    }

    public String getBaseDN() {
        return this.baseDN;
    }

    public String getCACertificateAttribute() {
        return this.cACertificateAttribute;
    }

    public String getCACertificateSubjectAttributeName() {
        return this.cACertificateSubjectAttributeName;
    }

    public String getCertificateRevocationListAttribute() {
        return this.certificateRevocationListAttribute;
    }

    public String getCertificateRevocationListIssuerAttributeName() {
        return this.certificateRevocationListIssuerAttributeName;
    }

    public String getCrossCertificateAttribute() {
        return this.crossCertificateAttribute;
    }

    public String getCrossCertificateSubjectAttributeName() {
        return this.crossCertificateSubjectAttributeName;
    }

    public String getDeltaRevocationListAttribute() {
        return this.deltaRevocationListAttribute;
    }

    public String getDeltaRevocationListIssuerAttributeName() {
        return this.deltaRevocationListIssuerAttributeName;
    }

    public String getLdapAACertificateAttributeName() {
        return this.ldapAACertificateAttributeName;
    }

    public String getLdapAttributeAuthorityRevocationListAttributeName() {
        return this.ldapAttributeAuthorityRevocationListAttributeName;
    }

    public String getLdapAttributeCertificateAttributeAttributeName() {
        return this.ldapAttributeCertificateAttributeAttributeName;
    }

    public String getLdapAttributeCertificateRevocationListAttributeName() {
        return this.ldapAttributeCertificateRevocationListAttributeName;
    }

    public String getLdapAttributeDescriptorCertificateAttributeName() {
        return this.ldapAttributeDescriptorCertificateAttributeName;
    }

    public String getLdapAuthorityRevocationListAttributeName() {
        return this.ldapAuthorityRevocationListAttributeName;
    }

    public String getLdapCACertificateAttributeName() {
        return this.ldapCACertificateAttributeName;
    }

    public String getLdapCertificateRevocationListAttributeName() {
        return this.ldapCertificateRevocationListAttributeName;
    }

    public String getLdapCrossCertificateAttributeName() {
        return this.ldapCrossCertificateAttributeName;
    }

    public String getLdapDeltaRevocationListAttributeName() {
        return this.ldapDeltaRevocationListAttributeName;
    }

    public String getLdapURL() {
        return this.ldapURL;
    }

    public String getLdapUserCertificateAttributeName() {
        return this.ldapUserCertificateAttributeName;
    }

    public String getSearchForSerialNumberIn() {
        return this.searchForSerialNumberIn;
    }

    public String getUserCertificateAttribute() {
        return this.userCertificateAttribute;
    }

    public String getUserCertificateSubjectAttributeName() {
        return this.userCertificateSubjectAttributeName;
    }

    public static X509LDAPCertStoreParameters getInstance(LDAPCertStoreParameters lDAPCertStoreParameters) {
        String string = "ldap://" + lDAPCertStoreParameters.getServerName() + ":" + lDAPCertStoreParameters.getPort();
        X509LDAPCertStoreParameters x509LDAPCertStoreParameters = new Builder(string, "").build();
        return x509LDAPCertStoreParameters;
    }

    public static class Builder {
        private String ldapURL;
        private String baseDN;
        private String userCertificateAttribute;
        private String cACertificateAttribute;
        private String crossCertificateAttribute;
        private String certificateRevocationListAttribute;
        private String deltaRevocationListAttribute;
        private String authorityRevocationListAttribute;
        private String attributeCertificateAttributeAttribute;
        private String aACertificateAttribute;
        private String attributeDescriptorCertificateAttribute;
        private String attributeCertificateRevocationListAttribute;
        private String attributeAuthorityRevocationListAttribute;
        private String ldapUserCertificateAttributeName;
        private String ldapCACertificateAttributeName;
        private String ldapCrossCertificateAttributeName;
        private String ldapCertificateRevocationListAttributeName;
        private String ldapDeltaRevocationListAttributeName;
        private String ldapAuthorityRevocationListAttributeName;
        private String ldapAttributeCertificateAttributeAttributeName;
        private String ldapAACertificateAttributeName;
        private String ldapAttributeDescriptorCertificateAttributeName;
        private String ldapAttributeCertificateRevocationListAttributeName;
        private String ldapAttributeAuthorityRevocationListAttributeName;
        private String userCertificateSubjectAttributeName;
        private String cACertificateSubjectAttributeName;
        private String crossCertificateSubjectAttributeName;
        private String certificateRevocationListIssuerAttributeName;
        private String deltaRevocationListIssuerAttributeName;
        private String authorityRevocationListIssuerAttributeName;
        private String attributeCertificateAttributeSubjectAttributeName;
        private String aACertificateSubjectAttributeName;
        private String attributeDescriptorCertificateSubjectAttributeName;
        private String attributeCertificateRevocationListIssuerAttributeName;
        private String attributeAuthorityRevocationListIssuerAttributeName;
        private String searchForSerialNumberIn;

        public Builder() {
            this("ldap://localhost:389", "");
        }

        public Builder(String string, String string2) {
            this.ldapURL = string;
            this.baseDN = string2 == null ? "" : string2;
            this.userCertificateAttribute = "userCertificate";
            this.cACertificateAttribute = "cACertificate";
            this.crossCertificateAttribute = "crossCertificatePair";
            this.certificateRevocationListAttribute = "certificateRevocationList";
            this.deltaRevocationListAttribute = "deltaRevocationList";
            this.authorityRevocationListAttribute = "authorityRevocationList";
            this.attributeCertificateAttributeAttribute = "attributeCertificateAttribute";
            this.aACertificateAttribute = "aACertificate";
            this.attributeDescriptorCertificateAttribute = "attributeDescriptorCertificate";
            this.attributeCertificateRevocationListAttribute = "attributeCertificateRevocationList";
            this.attributeAuthorityRevocationListAttribute = "attributeAuthorityRevocationList";
            this.ldapUserCertificateAttributeName = "cn";
            this.ldapCACertificateAttributeName = "cn ou o";
            this.ldapCrossCertificateAttributeName = "cn ou o";
            this.ldapCertificateRevocationListAttributeName = "cn ou o";
            this.ldapDeltaRevocationListAttributeName = "cn ou o";
            this.ldapAuthorityRevocationListAttributeName = "cn ou o";
            this.ldapAttributeCertificateAttributeAttributeName = "cn";
            this.ldapAACertificateAttributeName = "cn o ou";
            this.ldapAttributeDescriptorCertificateAttributeName = "cn o ou";
            this.ldapAttributeCertificateRevocationListAttributeName = "cn o ou";
            this.ldapAttributeAuthorityRevocationListAttributeName = "cn o ou";
            this.userCertificateSubjectAttributeName = "cn";
            this.cACertificateSubjectAttributeName = "o ou";
            this.crossCertificateSubjectAttributeName = "o ou";
            this.certificateRevocationListIssuerAttributeName = "o ou";
            this.deltaRevocationListIssuerAttributeName = "o ou";
            this.authorityRevocationListIssuerAttributeName = "o ou";
            this.attributeCertificateAttributeSubjectAttributeName = "cn";
            this.aACertificateSubjectAttributeName = "o ou";
            this.attributeDescriptorCertificateSubjectAttributeName = "o ou";
            this.attributeCertificateRevocationListIssuerAttributeName = "o ou";
            this.attributeAuthorityRevocationListIssuerAttributeName = "o ou";
            this.searchForSerialNumberIn = "uid serialNumber cn";
        }

        public Builder setUserCertificateAttribute(String string) {
            this.userCertificateAttribute = string;
            return this;
        }

        public Builder setCACertificateAttribute(String string) {
            this.cACertificateAttribute = string;
            return this;
        }

        public Builder setCrossCertificateAttribute(String string) {
            this.crossCertificateAttribute = string;
            return this;
        }

        public Builder setCertificateRevocationListAttribute(String string) {
            this.certificateRevocationListAttribute = string;
            return this;
        }

        public Builder setDeltaRevocationListAttribute(String string) {
            this.deltaRevocationListAttribute = string;
            return this;
        }

        public Builder setAuthorityRevocationListAttribute(String string) {
            this.authorityRevocationListAttribute = string;
            return this;
        }

        public Builder setAttributeCertificateAttributeAttribute(String string) {
            this.attributeCertificateAttributeAttribute = string;
            return this;
        }

        public Builder setAACertificateAttribute(String string) {
            this.aACertificateAttribute = string;
            return this;
        }

        public Builder setAttributeDescriptorCertificateAttribute(String string) {
            this.attributeDescriptorCertificateAttribute = string;
            return this;
        }

        public Builder setAttributeCertificateRevocationListAttribute(String string) {
            this.attributeCertificateRevocationListAttribute = string;
            return this;
        }

        public Builder setAttributeAuthorityRevocationListAttribute(String string) {
            this.attributeAuthorityRevocationListAttribute = string;
            return this;
        }

        public Builder setLdapUserCertificateAttributeName(String string) {
            this.ldapUserCertificateAttributeName = string;
            return this;
        }

        public Builder setLdapCACertificateAttributeName(String string) {
            this.ldapCACertificateAttributeName = string;
            return this;
        }

        public Builder setLdapCrossCertificateAttributeName(String string) {
            this.ldapCrossCertificateAttributeName = string;
            return this;
        }

        public Builder setLdapCertificateRevocationListAttributeName(String string) {
            this.ldapCertificateRevocationListAttributeName = string;
            return this;
        }

        public Builder setLdapDeltaRevocationListAttributeName(String string) {
            this.ldapDeltaRevocationListAttributeName = string;
            return this;
        }

        public Builder setLdapAuthorityRevocationListAttributeName(String string) {
            this.ldapAuthorityRevocationListAttributeName = string;
            return this;
        }

        public Builder setLdapAttributeCertificateAttributeAttributeName(String string) {
            this.ldapAttributeCertificateAttributeAttributeName = string;
            return this;
        }

        public Builder setLdapAACertificateAttributeName(String string) {
            this.ldapAACertificateAttributeName = string;
            return this;
        }

        public Builder setLdapAttributeDescriptorCertificateAttributeName(String string) {
            this.ldapAttributeDescriptorCertificateAttributeName = string;
            return this;
        }

        public Builder setLdapAttributeCertificateRevocationListAttributeName(String string) {
            this.ldapAttributeCertificateRevocationListAttributeName = string;
            return this;
        }

        public Builder setLdapAttributeAuthorityRevocationListAttributeName(String string) {
            this.ldapAttributeAuthorityRevocationListAttributeName = string;
            return this;
        }

        public Builder setUserCertificateSubjectAttributeName(String string) {
            this.userCertificateSubjectAttributeName = string;
            return this;
        }

        public Builder setCACertificateSubjectAttributeName(String string) {
            this.cACertificateSubjectAttributeName = string;
            return this;
        }

        public Builder setCrossCertificateSubjectAttributeName(String string) {
            this.crossCertificateSubjectAttributeName = string;
            return this;
        }

        public Builder setCertificateRevocationListIssuerAttributeName(String string) {
            this.certificateRevocationListIssuerAttributeName = string;
            return this;
        }

        public Builder setDeltaRevocationListIssuerAttributeName(String string) {
            this.deltaRevocationListIssuerAttributeName = string;
            return this;
        }

        public Builder setAuthorityRevocationListIssuerAttributeName(String string) {
            this.authorityRevocationListIssuerAttributeName = string;
            return this;
        }

        public Builder setAttributeCertificateAttributeSubjectAttributeName(String string) {
            this.attributeCertificateAttributeSubjectAttributeName = string;
            return this;
        }

        public Builder setAACertificateSubjectAttributeName(String string) {
            this.aACertificateSubjectAttributeName = string;
            return this;
        }

        public Builder setAttributeDescriptorCertificateSubjectAttributeName(String string) {
            this.attributeDescriptorCertificateSubjectAttributeName = string;
            return this;
        }

        public Builder setAttributeCertificateRevocationListIssuerAttributeName(String string) {
            this.attributeCertificateRevocationListIssuerAttributeName = string;
            return this;
        }

        public Builder setAttributeAuthorityRevocationListIssuerAttributeName(String string) {
            this.attributeAuthorityRevocationListIssuerAttributeName = string;
            return this;
        }

        public Builder setSearchForSerialNumberIn(String string) {
            this.searchForSerialNumberIn = string;
            return this;
        }

        public X509LDAPCertStoreParameters build() {
            if (this.ldapUserCertificateAttributeName == null || this.ldapCACertificateAttributeName == null || this.ldapCrossCertificateAttributeName == null || this.ldapCertificateRevocationListAttributeName == null || this.ldapDeltaRevocationListAttributeName == null || this.ldapAuthorityRevocationListAttributeName == null || this.ldapAttributeCertificateAttributeAttributeName == null || this.ldapAACertificateAttributeName == null || this.ldapAttributeDescriptorCertificateAttributeName == null || this.ldapAttributeCertificateRevocationListAttributeName == null || this.ldapAttributeAuthorityRevocationListAttributeName == null || this.userCertificateSubjectAttributeName == null || this.cACertificateSubjectAttributeName == null || this.crossCertificateSubjectAttributeName == null || this.certificateRevocationListIssuerAttributeName == null || this.deltaRevocationListIssuerAttributeName == null || this.authorityRevocationListIssuerAttributeName == null || this.attributeCertificateAttributeSubjectAttributeName == null || this.aACertificateSubjectAttributeName == null || this.attributeDescriptorCertificateSubjectAttributeName == null || this.attributeCertificateRevocationListIssuerAttributeName == null || this.attributeAuthorityRevocationListIssuerAttributeName == null) {
                throw new IllegalArgumentException("Necessary parameters not specified.");
            }
            return new X509LDAPCertStoreParameters(this);
        }
    }
}

