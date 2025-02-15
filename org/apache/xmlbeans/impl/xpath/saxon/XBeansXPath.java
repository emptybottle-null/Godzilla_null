/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  net.sf.saxon.Configuration
 *  net.sf.saxon.dom.NodeWrapper
 *  net.sf.saxon.om.Item
 *  net.sf.saxon.om.NodeInfo
 *  net.sf.saxon.om.ValueRepresentation
 *  net.sf.saxon.om.VirtualNode
 *  net.sf.saxon.sxpath.IndependentContext
 *  net.sf.saxon.sxpath.XPathDynamicContext
 *  net.sf.saxon.sxpath.XPathEvaluator
 *  net.sf.saxon.sxpath.XPathExpression
 *  net.sf.saxon.sxpath.XPathStaticContext
 *  net.sf.saxon.sxpath.XPathVariable
 *  net.sf.saxon.value.Value
 */
package org.apache.xmlbeans.impl.xpath.saxon;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import net.sf.saxon.Configuration;
import net.sf.saxon.dom.NodeWrapper;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.om.ValueRepresentation;
import net.sf.saxon.om.VirtualNode;
import net.sf.saxon.sxpath.IndependentContext;
import net.sf.saxon.sxpath.XPathDynamicContext;
import net.sf.saxon.sxpath.XPathEvaluator;
import net.sf.saxon.sxpath.XPathExpression;
import net.sf.saxon.sxpath.XPathStaticContext;
import net.sf.saxon.sxpath.XPathVariable;
import net.sf.saxon.value.Value;
import org.apache.xmlbeans.impl.store.PathDelegate;
import org.w3c.dom.Node;

public class XBeansXPath
implements PathDelegate.SelectPathInterface {
    private Object[] namespaceMap;
    private String path;
    private String contextVar;
    private String defaultNS;

    public XBeansXPath(String path, String contextVar, Map namespaceMap, String defaultNS) {
        this.path = path;
        this.contextVar = contextVar;
        this.defaultNS = defaultNS;
        this.namespaceMap = namespaceMap.entrySet().toArray();
    }

    public List selectNodes(Object node) {
        try {
            Node contextNode = (Node)node;
            XPathEvaluator xpe = new XPathEvaluator();
            Configuration config = new Configuration();
            config.setDOMLevel(2);
            config.setTreeModel(0);
            IndependentContext sc = new IndependentContext(config);
            if (this.defaultNS != null) {
                sc.setDefaultElementNamespace(this.defaultNS);
            }
            for (int i = 0; i < this.namespaceMap.length; ++i) {
                Map.Entry entry = (Map.Entry)this.namespaceMap[i];
                sc.declareNamespace((String)entry.getKey(), (String)entry.getValue());
            }
            xpe.setStaticContext((XPathStaticContext)sc);
            XPathVariable thisVar = xpe.declareVariable("", this.contextVar);
            XPathExpression xpath = xpe.createExpression(this.path);
            NodeInfo contextItem = config.unravel((Source)new DOMSource(contextNode));
            XPathDynamicContext dc = xpath.createDynamicContext(null);
            dc.setContextItem((Item)contextItem);
            dc.setVariable(thisVar, (ValueRepresentation)contextItem);
            List saxonNodes = xpath.evaluate(dc);
            ListIterator<Object> it = saxonNodes.listIterator();
            while (it.hasNext()) {
                Object o = it.next();
                if (o instanceof NodeInfo) {
                    if (o instanceof NodeWrapper) {
                        Node n = XBeansXPath.getUnderlyingNode((VirtualNode)((NodeWrapper)o));
                        it.set(n);
                        continue;
                    }
                    it.set(((NodeInfo)o).getStringValue());
                    continue;
                }
                if (!(o instanceof Item)) continue;
                it.set(Value.convertToJava((Item)((Item)o)));
            }
            return saxonNodes;
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List selectPath(Object node) {
        return this.selectNodes(node);
    }

    private static Node getUnderlyingNode(VirtualNode v) {
        Object o = v;
        while (o instanceof VirtualNode) {
            o = o.getUnderlyingNode();
        }
        return (Node)o;
    }
}

