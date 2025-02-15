/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.List;
import org.dom4j.dtd.Decl;
import org.dom4j.tree.AbstractDocumentType;

public class DefaultDocumentType
extends AbstractDocumentType {
    protected String elementName;
    private String publicID;
    private String systemID;
    private List<Decl> internalDeclarations;
    private List<Decl> externalDeclarations;

    public DefaultDocumentType() {
    }

    public DefaultDocumentType(String elementName, String systemID) {
        this.elementName = elementName;
        this.systemID = systemID;
    }

    public DefaultDocumentType(String elementName, String publicID, String systemID) {
        this.elementName = elementName;
        this.publicID = publicID;
        this.systemID = systemID;
    }

    @Override
    public String getElementName() {
        return this.elementName;
    }

    @Override
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public String getPublicID() {
        return this.publicID;
    }

    @Override
    public void setPublicID(String publicID) {
        this.publicID = publicID;
    }

    @Override
    public String getSystemID() {
        return this.systemID;
    }

    @Override
    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    @Override
    public List<Decl> getInternalDeclarations() {
        return this.internalDeclarations;
    }

    @Override
    public void setInternalDeclarations(List<Decl> internalDeclarations) {
        this.internalDeclarations = internalDeclarations;
    }

    @Override
    public List<Decl> getExternalDeclarations() {
        return this.externalDeclarations;
    }

    @Override
    public void setExternalDeclarations(List<Decl> externalDeclarations) {
        this.externalDeclarations = externalDeclarations;
    }
}

