/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j;

import java.util.Map;
import org.dom4j.Branch;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.xml.sax.EntityResolver;

public interface Document
extends Branch {
    public Element getRootElement();

    public void setRootElement(Element var1);

    public Document addComment(String var1);

    public Document addProcessingInstruction(String var1, String var2);

    public Document addProcessingInstruction(String var1, Map<String, String> var2);

    public Document addDocType(String var1, String var2, String var3);

    public DocumentType getDocType();

    public void setDocType(DocumentType var1);

    public EntityResolver getEntityResolver();

    public void setEntityResolver(EntityResolver var1);

    public String getXMLEncoding();

    public void setXMLEncoding(String var1);
}

