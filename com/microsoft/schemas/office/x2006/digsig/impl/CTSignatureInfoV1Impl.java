/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.x2006.digsig.STPositiveInteger
 *  com.microsoft.schemas.office.x2006.digsig.STSignatureProviderUrl
 *  com.microsoft.schemas.office.x2006.digsig.STSignatureText
 *  com.microsoft.schemas.office.x2006.digsig.STSignatureType
 *  com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces
 *  com.microsoft.schemas.office.x2006.digsig.STVersion
 */
package com.microsoft.schemas.office.x2006.digsig.impl;

import com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1;
import com.microsoft.schemas.office.x2006.digsig.STPositiveInteger;
import com.microsoft.schemas.office.x2006.digsig.STSignatureComments;
import com.microsoft.schemas.office.x2006.digsig.STSignatureProviderUrl;
import com.microsoft.schemas.office.x2006.digsig.STSignatureText;
import com.microsoft.schemas.office.x2006.digsig.STSignatureType;
import com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces;
import com.microsoft.schemas.office.x2006.digsig.STVersion;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTSignatureInfoV1Impl
extends XmlComplexContentImpl
implements CTSignatureInfoV1 {
    private static final long serialVersionUID = 1L;
    private static final QName SETUPID$0 = new QName("http://schemas.microsoft.com/office/2006/digsig", "SetupID");
    private static final QName SIGNATURETEXT$2 = new QName("http://schemas.microsoft.com/office/2006/digsig", "SignatureText");
    private static final QName SIGNATUREIMAGE$4 = new QName("http://schemas.microsoft.com/office/2006/digsig", "SignatureImage");
    private static final QName SIGNATURECOMMENTS$6 = new QName("http://schemas.microsoft.com/office/2006/digsig", "SignatureComments");
    private static final QName WINDOWSVERSION$8 = new QName("http://schemas.microsoft.com/office/2006/digsig", "WindowsVersion");
    private static final QName OFFICEVERSION$10 = new QName("http://schemas.microsoft.com/office/2006/digsig", "OfficeVersion");
    private static final QName APPLICATIONVERSION$12 = new QName("http://schemas.microsoft.com/office/2006/digsig", "ApplicationVersion");
    private static final QName MONITORS$14 = new QName("http://schemas.microsoft.com/office/2006/digsig", "Monitors");
    private static final QName HORIZONTALRESOLUTION$16 = new QName("http://schemas.microsoft.com/office/2006/digsig", "HorizontalResolution");
    private static final QName VERTICALRESOLUTION$18 = new QName("http://schemas.microsoft.com/office/2006/digsig", "VerticalResolution");
    private static final QName COLORDEPTH$20 = new QName("http://schemas.microsoft.com/office/2006/digsig", "ColorDepth");
    private static final QName SIGNATUREPROVIDERID$22 = new QName("http://schemas.microsoft.com/office/2006/digsig", "SignatureProviderId");
    private static final QName SIGNATUREPROVIDERURL$24 = new QName("http://schemas.microsoft.com/office/2006/digsig", "SignatureProviderUrl");
    private static final QName SIGNATUREPROVIDERDETAILS$26 = new QName("http://schemas.microsoft.com/office/2006/digsig", "SignatureProviderDetails");
    private static final QName SIGNATURETYPE$28 = new QName("http://schemas.microsoft.com/office/2006/digsig", "SignatureType");
    private static final QName DELEGATESUGGESTEDSIGNER$30 = new QName("http://schemas.microsoft.com/office/2006/digsig", "DelegateSuggestedSigner");
    private static final QName DELEGATESUGGESTEDSIGNER2$32 = new QName("http://schemas.microsoft.com/office/2006/digsig", "DelegateSuggestedSigner2");
    private static final QName DELEGATESUGGESTEDSIGNEREMAIL$34 = new QName("http://schemas.microsoft.com/office/2006/digsig", "DelegateSuggestedSignerEmail");
    private static final QName MANIFESTHASHALGORITHM$36 = new QName("http://schemas.microsoft.com/office/2006/digsig", "ManifestHashAlgorithm");

    public CTSignatureInfoV1Impl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSetupID() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SETUPID$0, 0));
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
    public STUniqueIdentifierWithBraces xgetSetupID() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUniqueIdentifierWithBraces sTUniqueIdentifierWithBraces = null;
            sTUniqueIdentifierWithBraces = (STUniqueIdentifierWithBraces)this.get_store().find_element_user(SETUPID$0, 0);
            return sTUniqueIdentifierWithBraces;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSetupID(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SETUPID$0, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SETUPID$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSetupID(STUniqueIdentifierWithBraces sTUniqueIdentifierWithBraces) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUniqueIdentifierWithBraces sTUniqueIdentifierWithBraces2 = null;
            sTUniqueIdentifierWithBraces2 = (STUniqueIdentifierWithBraces)this.get_store().find_element_user(SETUPID$0, 0);
            if (sTUniqueIdentifierWithBraces2 == null) {
                sTUniqueIdentifierWithBraces2 = (STUniqueIdentifierWithBraces)this.get_store().add_element_user(SETUPID$0);
            }
            sTUniqueIdentifierWithBraces2.set((XmlObject)sTUniqueIdentifierWithBraces);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSignatureText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATURETEXT$2, 0));
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
    public STSignatureText xgetSignatureText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignatureText sTSignatureText = null;
            sTSignatureText = (STSignatureText)this.get_store().find_element_user(SIGNATURETEXT$2, 0);
            return sTSignatureText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSignatureText(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATURETEXT$2, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SIGNATURETEXT$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSignatureText(STSignatureText sTSignatureText) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignatureText sTSignatureText2 = null;
            sTSignatureText2 = (STSignatureText)this.get_store().find_element_user(SIGNATURETEXT$2, 0);
            if (sTSignatureText2 == null) {
                sTSignatureText2 = (STSignatureText)this.get_store().add_element_user(SIGNATURETEXT$2);
            }
            sTSignatureText2.set((XmlObject)sTSignatureText);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getSignatureImage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATUREIMAGE$4, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBase64Binary xgetSignatureImage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_element_user(SIGNATUREIMAGE$4, 0));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSignatureImage(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATUREIMAGE$4, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SIGNATUREIMAGE$4));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSignatureImage(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_element_user(SIGNATUREIMAGE$4, 0));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_element_user(SIGNATUREIMAGE$4));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSignatureComments() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATURECOMMENTS$6, 0));
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
    public STSignatureComments xgetSignatureComments() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignatureComments sTSignatureComments = null;
            sTSignatureComments = (STSignatureComments)((Object)this.get_store().find_element_user(SIGNATURECOMMENTS$6, 0));
            return sTSignatureComments;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSignatureComments(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATURECOMMENTS$6, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SIGNATURECOMMENTS$6));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSignatureComments(STSignatureComments sTSignatureComments) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignatureComments sTSignatureComments2 = null;
            sTSignatureComments2 = (STSignatureComments)((Object)this.get_store().find_element_user(SIGNATURECOMMENTS$6, 0));
            if (sTSignatureComments2 == null) {
                sTSignatureComments2 = (STSignatureComments)((Object)this.get_store().add_element_user(SIGNATURECOMMENTS$6));
            }
            sTSignatureComments2.set(sTSignatureComments);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getWindowsVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(WINDOWSVERSION$8, 0));
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
    public STVersion xgetWindowsVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVersion sTVersion = null;
            sTVersion = (STVersion)this.get_store().find_element_user(WINDOWSVERSION$8, 0);
            return sTVersion;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setWindowsVersion(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(WINDOWSVERSION$8, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(WINDOWSVERSION$8));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetWindowsVersion(STVersion sTVersion) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVersion sTVersion2 = null;
            sTVersion2 = (STVersion)this.get_store().find_element_user(WINDOWSVERSION$8, 0);
            if (sTVersion2 == null) {
                sTVersion2 = (STVersion)this.get_store().add_element_user(WINDOWSVERSION$8);
            }
            sTVersion2.set((XmlObject)sTVersion);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getOfficeVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(OFFICEVERSION$10, 0));
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
    public STVersion xgetOfficeVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVersion sTVersion = null;
            sTVersion = (STVersion)this.get_store().find_element_user(OFFICEVERSION$10, 0);
            return sTVersion;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOfficeVersion(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(OFFICEVERSION$10, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(OFFICEVERSION$10));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOfficeVersion(STVersion sTVersion) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVersion sTVersion2 = null;
            sTVersion2 = (STVersion)this.get_store().find_element_user(OFFICEVERSION$10, 0);
            if (sTVersion2 == null) {
                sTVersion2 = (STVersion)this.get_store().add_element_user(OFFICEVERSION$10);
            }
            sTVersion2.set((XmlObject)sTVersion);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getApplicationVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(APPLICATIONVERSION$12, 0));
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
    public STVersion xgetApplicationVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVersion sTVersion = null;
            sTVersion = (STVersion)this.get_store().find_element_user(APPLICATIONVERSION$12, 0);
            return sTVersion;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setApplicationVersion(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(APPLICATIONVERSION$12, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(APPLICATIONVERSION$12));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetApplicationVersion(STVersion sTVersion) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVersion sTVersion2 = null;
            sTVersion2 = (STVersion)this.get_store().find_element_user(APPLICATIONVERSION$12, 0);
            if (sTVersion2 == null) {
                sTVersion2 = (STVersion)this.get_store().add_element_user(APPLICATIONVERSION$12);
            }
            sTVersion2.set((XmlObject)sTVersion);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getMonitors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MONITORS$14, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPositiveInteger xgetMonitors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveInteger sTPositiveInteger = null;
            sTPositiveInteger = (STPositiveInteger)this.get_store().find_element_user(MONITORS$14, 0);
            return sTPositiveInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMonitors(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MONITORS$14, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(MONITORS$14));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMonitors(STPositiveInteger sTPositiveInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveInteger sTPositiveInteger2 = null;
            sTPositiveInteger2 = (STPositiveInteger)this.get_store().find_element_user(MONITORS$14, 0);
            if (sTPositiveInteger2 == null) {
                sTPositiveInteger2 = (STPositiveInteger)this.get_store().add_element_user(MONITORS$14);
            }
            sTPositiveInteger2.set((XmlObject)sTPositiveInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getHorizontalResolution() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(HORIZONTALRESOLUTION$16, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPositiveInteger xgetHorizontalResolution() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveInteger sTPositiveInteger = null;
            sTPositiveInteger = (STPositiveInteger)this.get_store().find_element_user(HORIZONTALRESOLUTION$16, 0);
            return sTPositiveInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHorizontalResolution(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(HORIZONTALRESOLUTION$16, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(HORIZONTALRESOLUTION$16));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHorizontalResolution(STPositiveInteger sTPositiveInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveInteger sTPositiveInteger2 = null;
            sTPositiveInteger2 = (STPositiveInteger)this.get_store().find_element_user(HORIZONTALRESOLUTION$16, 0);
            if (sTPositiveInteger2 == null) {
                sTPositiveInteger2 = (STPositiveInteger)this.get_store().add_element_user(HORIZONTALRESOLUTION$16);
            }
            sTPositiveInteger2.set((XmlObject)sTPositiveInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getVerticalResolution() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VERTICALRESOLUTION$18, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPositiveInteger xgetVerticalResolution() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveInteger sTPositiveInteger = null;
            sTPositiveInteger = (STPositiveInteger)this.get_store().find_element_user(VERTICALRESOLUTION$18, 0);
            return sTPositiveInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVerticalResolution(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VERTICALRESOLUTION$18, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(VERTICALRESOLUTION$18));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVerticalResolution(STPositiveInteger sTPositiveInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveInteger sTPositiveInteger2 = null;
            sTPositiveInteger2 = (STPositiveInteger)this.get_store().find_element_user(VERTICALRESOLUTION$18, 0);
            if (sTPositiveInteger2 == null) {
                sTPositiveInteger2 = (STPositiveInteger)this.get_store().add_element_user(VERTICALRESOLUTION$18);
            }
            sTPositiveInteger2.set((XmlObject)sTPositiveInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getColorDepth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(COLORDEPTH$20, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPositiveInteger xgetColorDepth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveInteger sTPositiveInteger = null;
            sTPositiveInteger = (STPositiveInteger)this.get_store().find_element_user(COLORDEPTH$20, 0);
            return sTPositiveInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setColorDepth(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(COLORDEPTH$20, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(COLORDEPTH$20));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetColorDepth(STPositiveInteger sTPositiveInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveInteger sTPositiveInteger2 = null;
            sTPositiveInteger2 = (STPositiveInteger)this.get_store().find_element_user(COLORDEPTH$20, 0);
            if (sTPositiveInteger2 == null) {
                sTPositiveInteger2 = (STPositiveInteger)this.get_store().add_element_user(COLORDEPTH$20);
            }
            sTPositiveInteger2.set((XmlObject)sTPositiveInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSignatureProviderId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATUREPROVIDERID$22, 0));
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
    public STUniqueIdentifierWithBraces xgetSignatureProviderId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUniqueIdentifierWithBraces sTUniqueIdentifierWithBraces = null;
            sTUniqueIdentifierWithBraces = (STUniqueIdentifierWithBraces)this.get_store().find_element_user(SIGNATUREPROVIDERID$22, 0);
            return sTUniqueIdentifierWithBraces;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSignatureProviderId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATUREPROVIDERID$22, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SIGNATUREPROVIDERID$22));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSignatureProviderId(STUniqueIdentifierWithBraces sTUniqueIdentifierWithBraces) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUniqueIdentifierWithBraces sTUniqueIdentifierWithBraces2 = null;
            sTUniqueIdentifierWithBraces2 = (STUniqueIdentifierWithBraces)this.get_store().find_element_user(SIGNATUREPROVIDERID$22, 0);
            if (sTUniqueIdentifierWithBraces2 == null) {
                sTUniqueIdentifierWithBraces2 = (STUniqueIdentifierWithBraces)this.get_store().add_element_user(SIGNATUREPROVIDERID$22);
            }
            sTUniqueIdentifierWithBraces2.set((XmlObject)sTUniqueIdentifierWithBraces);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSignatureProviderUrl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATUREPROVIDERURL$24, 0));
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
    public STSignatureProviderUrl xgetSignatureProviderUrl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignatureProviderUrl sTSignatureProviderUrl = null;
            sTSignatureProviderUrl = (STSignatureProviderUrl)this.get_store().find_element_user(SIGNATUREPROVIDERURL$24, 0);
            return sTSignatureProviderUrl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSignatureProviderUrl(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATUREPROVIDERURL$24, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SIGNATUREPROVIDERURL$24));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSignatureProviderUrl(STSignatureProviderUrl sTSignatureProviderUrl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignatureProviderUrl sTSignatureProviderUrl2 = null;
            sTSignatureProviderUrl2 = (STSignatureProviderUrl)this.get_store().find_element_user(SIGNATUREPROVIDERURL$24, 0);
            if (sTSignatureProviderUrl2 == null) {
                sTSignatureProviderUrl2 = (STSignatureProviderUrl)this.get_store().add_element_user(SIGNATUREPROVIDERURL$24);
            }
            sTSignatureProviderUrl2.set((XmlObject)sTSignatureProviderUrl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSignatureProviderDetails() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATUREPROVIDERDETAILS$26, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInt xgetSignatureProviderDetails() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt = null;
            xmlInt = (XmlInt)((Object)this.get_store().find_element_user(SIGNATUREPROVIDERDETAILS$26, 0));
            return xmlInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSignatureProviderDetails(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATUREPROVIDERDETAILS$26, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SIGNATUREPROVIDERDETAILS$26));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSignatureProviderDetails(XmlInt xmlInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt2 = null;
            xmlInt2 = (XmlInt)((Object)this.get_store().find_element_user(SIGNATUREPROVIDERDETAILS$26, 0));
            if (xmlInt2 == null) {
                xmlInt2 = (XmlInt)((Object)this.get_store().add_element_user(SIGNATUREPROVIDERDETAILS$26));
            }
            xmlInt2.set(xmlInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSignatureType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATURETYPE$28, 0));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STSignatureType xgetSignatureType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignatureType sTSignatureType = null;
            sTSignatureType = (STSignatureType)this.get_store().find_element_user(SIGNATURETYPE$28, 0);
            return sTSignatureType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSignatureType(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIGNATURETYPE$28, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SIGNATURETYPE$28));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSignatureType(STSignatureType sTSignatureType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSignatureType sTSignatureType2 = null;
            sTSignatureType2 = (STSignatureType)this.get_store().find_element_user(SIGNATURETYPE$28, 0);
            if (sTSignatureType2 == null) {
                sTSignatureType2 = (STSignatureType)this.get_store().add_element_user(SIGNATURETYPE$28);
            }
            sTSignatureType2.set((XmlObject)sTSignatureType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getDelegateSuggestedSigner() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNER$30, 0));
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
    public XmlString xgetDelegateSuggestedSigner() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNER$30, 0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDelegateSuggestedSigner() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DELEGATESUGGESTEDSIGNER$30) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDelegateSuggestedSigner(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNER$30, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DELEGATESUGGESTEDSIGNER$30));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDelegateSuggestedSigner(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNER$30, 0));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_element_user(DELEGATESUGGESTEDSIGNER$30));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDelegateSuggestedSigner() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DELEGATESUGGESTEDSIGNER$30, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getDelegateSuggestedSigner2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNER2$32, 0));
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
    public XmlString xgetDelegateSuggestedSigner2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNER2$32, 0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDelegateSuggestedSigner2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DELEGATESUGGESTEDSIGNER2$32) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDelegateSuggestedSigner2(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNER2$32, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DELEGATESUGGESTEDSIGNER2$32));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDelegateSuggestedSigner2(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNER2$32, 0));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_element_user(DELEGATESUGGESTEDSIGNER2$32));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDelegateSuggestedSigner2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DELEGATESUGGESTEDSIGNER2$32, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getDelegateSuggestedSignerEmail() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNEREMAIL$34, 0));
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
    public XmlString xgetDelegateSuggestedSignerEmail() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNEREMAIL$34, 0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDelegateSuggestedSignerEmail() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DELEGATESUGGESTEDSIGNEREMAIL$34) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDelegateSuggestedSignerEmail(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNEREMAIL$34, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DELEGATESUGGESTEDSIGNEREMAIL$34));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDelegateSuggestedSignerEmail(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(DELEGATESUGGESTEDSIGNEREMAIL$34, 0));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_element_user(DELEGATESUGGESTEDSIGNEREMAIL$34));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDelegateSuggestedSignerEmail() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DELEGATESUGGESTEDSIGNEREMAIL$34, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getManifestHashAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MANIFESTHASHALGORITHM$36, 0));
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
    public XmlAnyURI xgetManifestHashAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI = null;
            xmlAnyURI = (XmlAnyURI)((Object)this.get_store().find_element_user(MANIFESTHASHALGORITHM$36, 0));
            return xmlAnyURI;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetManifestHashAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MANIFESTHASHALGORITHM$36) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setManifestHashAlgorithm(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MANIFESTHASHALGORITHM$36, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(MANIFESTHASHALGORITHM$36));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetManifestHashAlgorithm(XmlAnyURI xmlAnyURI) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI2 = null;
            xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().find_element_user(MANIFESTHASHALGORITHM$36, 0));
            if (xmlAnyURI2 == null) {
                xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().add_element_user(MANIFESTHASHALGORITHM$36));
            }
            xmlAnyURI2.set(xmlAnyURI);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetManifestHashAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MANIFESTHASHALGORITHM$36, 0);
        }
    }
}

