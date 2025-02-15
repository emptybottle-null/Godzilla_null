/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jaxen.FunctionContext
 *  org.jaxen.JaxenException
 *  org.jaxen.NamespaceContext
 *  org.jaxen.SimpleNamespaceContext
 *  org.jaxen.VariableContext
 *  org.jaxen.XPath
 *  org.jaxen.dom4j.Dom4jXPath
 */
package org.dom4j.xpath;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.InvalidXPathException;
import org.dom4j.Node;
import org.dom4j.NodeFilter;
import org.dom4j.XPath;
import org.dom4j.XPathException;
import org.dom4j.xpath.DefaultNamespaceContext;
import org.jaxen.FunctionContext;
import org.jaxen.JaxenException;
import org.jaxen.NamespaceContext;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.VariableContext;
import org.jaxen.dom4j.Dom4jXPath;

public class DefaultXPath
implements XPath,
NodeFilter,
Serializable {
    private String text;
    private org.jaxen.XPath xpath;
    private NamespaceContext namespaceContext;

    public DefaultXPath(String text) throws InvalidXPathException {
        this.text = text;
        this.xpath = DefaultXPath.parse(text);
    }

    public String toString() {
        return "[XPath: " + this.xpath + "]";
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public FunctionContext getFunctionContext() {
        return this.xpath.getFunctionContext();
    }

    @Override
    public void setFunctionContext(FunctionContext functionContext) {
        this.xpath.setFunctionContext(functionContext);
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return this.namespaceContext;
    }

    @Override
    public void setNamespaceURIs(Map<String, String> map) {
        this.setNamespaceContext((NamespaceContext)new SimpleNamespaceContext(map));
    }

    @Override
    public void setNamespaceContext(NamespaceContext namespaceContext) {
        this.namespaceContext = namespaceContext;
        this.xpath.setNamespaceContext(namespaceContext);
    }

    @Override
    public VariableContext getVariableContext() {
        return this.xpath.getVariableContext();
    }

    @Override
    public void setVariableContext(VariableContext variableContext) {
        this.xpath.setVariableContext(variableContext);
    }

    @Override
    public Object evaluate(Object context) {
        try {
            this.setNSContext(context);
            List answer = this.xpath.selectNodes(context);
            if (answer != null && answer.size() == 1) {
                return answer.get(0);
            }
            return answer;
        } catch (JaxenException e) {
            this.handleJaxenException(e);
            return null;
        }
    }

    @Override
    public Object selectObject(Object context) {
        return this.evaluate(context);
    }

    @Override
    public List<Node> selectNodes(Object context) {
        try {
            this.setNSContext(context);
            return this.xpath.selectNodes(context);
        } catch (JaxenException e) {
            this.handleJaxenException(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Node> selectNodes(Object context, XPath sortXPath) {
        List<Node> answer = this.selectNodes(context);
        sortXPath.sort(answer);
        return answer;
    }

    @Override
    public List<Node> selectNodes(Object context, XPath sortXPath, boolean distinct) {
        List<Node> answer = this.selectNodes(context);
        sortXPath.sort(answer, distinct);
        return answer;
    }

    @Override
    public Node selectSingleNode(Object context) {
        try {
            this.setNSContext(context);
            Object answer = this.xpath.selectSingleNode(context);
            if (answer instanceof Node) {
                return (Node)answer;
            }
            if (answer == null) {
                return null;
            }
            throw new XPathException("The result of the XPath expression is not a Node. It was: " + answer + " of type: " + answer.getClass().getName());
        } catch (JaxenException e) {
            this.handleJaxenException(e);
            return null;
        }
    }

    @Override
    public String valueOf(Object context) {
        try {
            this.setNSContext(context);
            return this.xpath.stringValueOf(context);
        } catch (JaxenException e) {
            this.handleJaxenException(e);
            return "";
        }
    }

    @Override
    public Number numberValueOf(Object context) {
        try {
            this.setNSContext(context);
            return this.xpath.numberValueOf(context);
        } catch (JaxenException e) {
            this.handleJaxenException(e);
            return null;
        }
    }

    @Override
    public boolean booleanValueOf(Object context) {
        try {
            this.setNSContext(context);
            return this.xpath.booleanValueOf(context);
        } catch (JaxenException e) {
            this.handleJaxenException(e);
            return false;
        }
    }

    @Override
    public void sort(List<Node> list) {
        this.sort(list, false);
    }

    @Override
    public void sort(List<Node> list, boolean distinct) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            HashMap<Node, Object> sortValues = new HashMap<Node, Object>(size);
            for (Node node : list) {
                Object expression = this.getCompareValue(node);
                sortValues.put(node, expression);
            }
            this.sort(list, sortValues);
            if (distinct) {
                this.removeDuplicates(list, sortValues);
            }
        }
    }

    @Override
    public boolean matches(Node node) {
        try {
            this.setNSContext(node);
            List answer = this.xpath.selectNodes((Object)node);
            if (answer != null && answer.size() > 0) {
                Object item = answer.get(0);
                if (item instanceof Boolean) {
                    return (Boolean)item;
                }
                return answer.contains(node);
            }
            return false;
        } catch (JaxenException e) {
            this.handleJaxenException(e);
            return false;
        }
    }

    protected void sort(List<Node> list, final Map<Node, Object> sortValues) {
        Collections.sort(list, new Comparator<Node>(){

            @Override
            public int compare(Node n1, Node n2) {
                Object o2;
                Object o1 = sortValues.get(n1);
                if (o1 == (o2 = sortValues.get(n2))) {
                    return 0;
                }
                if (o1 instanceof Comparable) {
                    Comparable c1 = (Comparable)o1;
                    return c1.compareTo(o2);
                }
                if (o1 == null) {
                    return 1;
                }
                if (o2 == null) {
                    return -1;
                }
                return o1.equals(o2) ? 0 : -1;
            }
        });
    }

    protected void removeDuplicates(List<Node> list, Map<Node, Object> sortValues) {
        HashSet<Object> distinctValues = new HashSet<Object>();
        Iterator<Node> iter = list.iterator();
        while (iter.hasNext()) {
            Node node = iter.next();
            Object value = sortValues.get(node);
            if (distinctValues.contains(value)) {
                iter.remove();
                continue;
            }
            distinctValues.add(value);
        }
    }

    protected Object getCompareValue(Node node) {
        return this.valueOf(node);
    }

    protected static org.jaxen.XPath parse(String text) {
        try {
            return new Dom4jXPath(text);
        } catch (JaxenException e) {
            throw new InvalidXPathException(text, e.getMessage());
        } catch (RuntimeException runtimeException) {
            throw new InvalidXPathException(text);
        }
    }

    protected void setNSContext(Object context) {
        if (this.namespaceContext == null) {
            this.xpath.setNamespaceContext((NamespaceContext)DefaultNamespaceContext.create(context));
        }
    }

    protected void handleJaxenException(JaxenException exception) throws XPathException {
        throw new XPathException(this.text, (Exception)((Object)exception));
    }
}

