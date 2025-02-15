/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import java.util.Locale;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPException;

public interface SOAPFault
extends SOAPBodyElement {
    public void setFaultCode(String var1) throws SOAPException;

    public String getFaultCode();

    public void setFaultActor(String var1) throws SOAPException;

    public String getFaultActor();

    public void setFaultString(String var1) throws SOAPException;

    public String getFaultString();

    public Detail getDetail();

    public Detail addDetail() throws SOAPException;

    public void setFaultCode(Name var1) throws SOAPException;

    public Name getFaultCodeAsName();

    public void setFaultString(String var1, Locale var2) throws SOAPException;

    public Locale getFaultStringLocale();
}

