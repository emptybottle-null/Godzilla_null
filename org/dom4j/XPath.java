/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jaxen.FunctionContext
 *  org.jaxen.NamespaceContext
 *  org.jaxen.VariableContext
 */
package org.dom4j;

import java.util.List;
import java.util.Map;
import org.dom4j.Node;
import org.dom4j.NodeFilter;
import org.jaxen.FunctionContext;
import org.jaxen.NamespaceContext;
import org.jaxen.VariableContext;

public interface XPath
extends NodeFilter {
    public String getText();

    @Override
    public boolean matches(Node var1);

    public Object evaluate(Object var1);

    public Object selectObject(Object var1);

    public List<Node> selectNodes(Object var1);

    public List<Node> selectNodes(Object var1, XPath var2);

    public List<Node> selectNodes(Object var1, XPath var2, boolean var3);

    public Node selectSingleNode(Object var1);

    public String valueOf(Object var1);

    public Number numberValueOf(Object var1);

    public boolean booleanValueOf(Object var1);

    public void sort(List<Node> var1);

    public void sort(List<Node> var1, boolean var2);

    public FunctionContext getFunctionContext();

    public void setFunctionContext(FunctionContext var1);

    public NamespaceContext getNamespaceContext();

    public void setNamespaceContext(NamespaceContext var1);

    public void setNamespaceURIs(Map<String, String> var1);

    public VariableContext getVariableContext();

    public void setVariableContext(VariableContext var1);
}

