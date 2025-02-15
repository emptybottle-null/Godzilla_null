/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j;

import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;

public interface Attribute
extends Node {
    public QName getQName();

    public Namespace getNamespace();

    public void setNamespace(Namespace var1);

    public String getNamespacePrefix();

    public String getNamespaceURI();

    public String getQualifiedName();

    public String getValue();

    public void setValue(String var1);

    public Object getData();

    public void setData(Object var1);
}

