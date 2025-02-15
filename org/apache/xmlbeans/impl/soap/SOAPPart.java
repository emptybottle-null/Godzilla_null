/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import java.util.Iterator;
import javax.xml.transform.Source;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.w3c.dom.Document;

public abstract class SOAPPart
implements Document {
    public abstract SOAPEnvelope getEnvelope() throws SOAPException;

    public String getContentId() {
        String[] as = this.getMimeHeader("Content-Id");
        if (as != null && as.length > 0) {
            return as[0];
        }
        return null;
    }

    public String getContentLocation() {
        String[] as = this.getMimeHeader("Content-Location");
        if (as != null && as.length > 0) {
            return as[0];
        }
        return null;
    }

    public void setContentId(String contentId) {
        this.setMimeHeader("Content-Id", contentId);
    }

    public void setContentLocation(String contentLocation) {
        this.setMimeHeader("Content-Location", contentLocation);
    }

    public abstract void removeMimeHeader(String var1);

    public abstract void removeAllMimeHeaders();

    public abstract String[] getMimeHeader(String var1);

    public abstract void setMimeHeader(String var1, String var2);

    public abstract void addMimeHeader(String var1, String var2);

    public abstract Iterator getAllMimeHeaders();

    public abstract Iterator getMatchingMimeHeaders(String[] var1);

    public abstract Iterator getNonMatchingMimeHeaders(String[] var1);

    public abstract void setContent(Source var1) throws SOAPException;

    public abstract Source getContent() throws SOAPException;
}

