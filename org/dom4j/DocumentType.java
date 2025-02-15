/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j;

import java.util.List;
import org.dom4j.Node;
import org.dom4j.dtd.Decl;

public interface DocumentType
extends Node {
    public String getElementName();

    public void setElementName(String var1);

    public String getPublicID();

    public void setPublicID(String var1);

    public String getSystemID();

    public void setSystemID(String var1);

    public List<Decl> getInternalDeclarations();

    public void setInternalDeclarations(List<Decl> var1);

    public List<Decl> getExternalDeclarations();

    public void setExternalDeclarations(List<Decl> var1);
}

