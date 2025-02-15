/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.voms;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.x509.Attribute;
import org.bouncycastle.asn1.x509.IetfAttrSyntax;
import org.bouncycastle.cert.X509AttributeCertificateHolder;

public class VOMSAttribute {
    public static final String VOMS_ATTR_OID = "1.3.6.1.4.1.8005.100.100.4";
    private X509AttributeCertificateHolder myAC;
    private String myHostPort;
    private String myVo;
    private List myStringList = new ArrayList();
    private List myFQANs = new ArrayList();

    public VOMSAttribute(X509AttributeCertificateHolder x509AttributeCertificateHolder) {
        if (x509AttributeCertificateHolder == null) {
            throw new IllegalArgumentException("VOMSAttribute: AttributeCertificate is NULL");
        }
        this.myAC = x509AttributeCertificateHolder;
        Attribute[] attributeArray = x509AttributeCertificateHolder.getAttributes(new ASN1ObjectIdentifier(VOMS_ATTR_OID));
        if (attributeArray == null) {
            return;
        }
        try {
            for (int i = 0; i != attributeArray.length; ++i) {
                IetfAttrSyntax ietfAttrSyntax = IetfAttrSyntax.getInstance(attributeArray[i].getAttributeValues()[0]);
                String string = ((DERIA5String)ietfAttrSyntax.getPolicyAuthority().getNames()[0].getName()).getString();
                int n = string.indexOf("://");
                if (n < 0 || n == string.length() - 1) {
                    throw new IllegalArgumentException("Bad encoding of VOMS policyAuthority : [" + string + "]");
                }
                this.myVo = string.substring(0, n);
                this.myHostPort = string.substring(n + 3);
                if (ietfAttrSyntax.getValueType() != 1) {
                    throw new IllegalArgumentException("VOMS attribute values are not encoded as octet strings, policyAuthority = " + string);
                }
                ASN1OctetString[] aSN1OctetStringArray = (ASN1OctetString[])ietfAttrSyntax.getValues();
                for (int j = 0; j != aSN1OctetStringArray.length; ++j) {
                    String string2 = new String(aSN1OctetStringArray[j].getOctets());
                    FQAN fQAN = new FQAN(string2);
                    if (this.myStringList.contains(string2) || !string2.startsWith("/" + this.myVo + "/")) continue;
                    this.myStringList.add(string2);
                    this.myFQANs.add(fQAN);
                }
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            throw illegalArgumentException;
        } catch (Exception exception) {
            throw new IllegalArgumentException("Badly encoded VOMS extension in AC issued by " + x509AttributeCertificateHolder.getIssuer());
        }
    }

    public X509AttributeCertificateHolder getAC() {
        return this.myAC;
    }

    public List getFullyQualifiedAttributes() {
        return this.myStringList;
    }

    public List getListOfFQAN() {
        return this.myFQANs;
    }

    public String getHostPort() {
        return this.myHostPort;
    }

    public String getVO() {
        return this.myVo;
    }

    public String toString() {
        return "VO      :" + this.myVo + "\n" + "HostPort:" + this.myHostPort + "\n" + "FQANs   :" + this.myFQANs;
    }

    public class FQAN {
        String fqan;
        String group;
        String role;
        String capability;

        public FQAN(String string) {
            this.fqan = string;
        }

        public FQAN(String string, String string2, String string3) {
            this.group = string;
            this.role = string2;
            this.capability = string3;
        }

        public String getFQAN() {
            if (this.fqan != null) {
                return this.fqan;
            }
            this.fqan = this.group + "/Role=" + (this.role != null ? this.role : "") + (this.capability != null ? "/Capability=" + this.capability : "");
            return this.fqan;
        }

        protected void split() {
            int n = this.fqan.length();
            int n2 = this.fqan.indexOf("/Role=");
            if (n2 < 0) {
                return;
            }
            this.group = this.fqan.substring(0, n2);
            int n3 = this.fqan.indexOf("/Capability=", n2 + 6);
            String string = n3 < 0 ? this.fqan.substring(n2 + 6) : this.fqan.substring(n2 + 6, n3);
            this.role = string.length() == 0 ? null : string;
            string = n3 < 0 ? null : this.fqan.substring(n3 + 12);
            this.capability = string == null || string.length() == 0 ? null : string;
        }

        public String getGroup() {
            if (this.group == null && this.fqan != null) {
                this.split();
            }
            return this.group;
        }

        public String getRole() {
            if (this.group == null && this.fqan != null) {
                this.split();
            }
            return this.role;
        }

        public String getCapability() {
            if (this.group == null && this.fqan != null) {
                this.split();
            }
            return this.capability;
        }

        public String toString() {
            return this.getFQAN();
        }
    }
}

