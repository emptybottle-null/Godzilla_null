/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import org.apache.xmlbeans.impl.soap.AttachmentPart;
import org.apache.xmlbeans.impl.soap.MimeHeaders;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.soap.SOAPPart;

public abstract class SOAPMessage {
    public static final String CHARACTER_SET_ENCODING = "javax.xml.soap.character-set-encoding";
    public static final String WRITE_XML_DECLARATION = "javax.xml.soap.write-xml-declaration";

    public abstract String getContentDescription();

    public abstract void setContentDescription(String var1);

    public abstract SOAPPart getSOAPPart();

    public abstract void removeAllAttachments();

    public abstract int countAttachments();

    public abstract Iterator getAttachments();

    public abstract Iterator getAttachments(MimeHeaders var1);

    public abstract void addAttachmentPart(AttachmentPart var1);

    public abstract AttachmentPart createAttachmentPart();

    public abstract MimeHeaders getMimeHeaders();

    public AttachmentPart createAttachmentPart(Object content, String contentType) {
        AttachmentPart attachmentpart = this.createAttachmentPart();
        attachmentpart.setContent(content, contentType);
        return attachmentpart;
    }

    public abstract void saveChanges() throws SOAPException;

    public abstract boolean saveRequired();

    public abstract void writeTo(OutputStream var1) throws SOAPException, IOException;

    public abstract SOAPBody getSOAPBody() throws SOAPException;

    public abstract SOAPHeader getSOAPHeader() throws SOAPException;

    public abstract void setProperty(String var1, Object var2) throws SOAPException;

    public abstract Object getProperty(String var1) throws SOAPException;
}

