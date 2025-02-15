/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  net.sf.saxon.Configuration
 *  net.sf.saxon.dom.NodeOverNodeInfo
 *  net.sf.saxon.om.DocumentInfo
 *  net.sf.saxon.om.Item
 *  net.sf.saxon.om.NodeInfo
 *  net.sf.saxon.query.DynamicQueryContext
 *  net.sf.saxon.query.StaticQueryContext
 *  net.sf.saxon.query.XQueryExpression
 */
package org.apache.xmlbeans.impl.xquery.saxon;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import net.sf.saxon.Configuration;
import net.sf.saxon.dom.NodeOverNodeInfo;
import net.sf.saxon.om.DocumentInfo;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.XmlTokenSource;
import org.apache.xmlbeans.impl.store.QueryDelegate;
import org.w3c.dom.Node;

public class XBeansXQuery
implements QueryDelegate.QueryInterface {
    private XQueryExpression xquery;
    private String contextVar;
    private Configuration config = new Configuration();

    public XBeansXQuery(String query, String contextVar, Integer boundary, XmlOptions xmlOptions) {
        this.config.setDOMLevel(2);
        this.config.setTreeModel(0);
        StaticQueryContext sc = new StaticQueryContext(this.config);
        Map nsMap = (Map)xmlOptions.get("LOAD_ADDITIONAL_NAMESPACES");
        if (nsMap != null) {
            for (Map.Entry me : nsMap.entrySet()) {
                sc.declareNamespace((String)me.getKey(), (String)me.getValue());
            }
        }
        this.contextVar = contextVar;
        query = boundary == 0 ? "declare variable $" + contextVar + " external;" + query : query.substring(0, boundary) + "declare variable $" + contextVar + " external;" + query.substring(boundary);
        try {
            this.xquery = sc.compileQuery(query);
        } catch (TransformerException e) {
            throw new XmlRuntimeException(e);
        }
    }

    @Override
    public List execQuery(Object node, Map variableBindings) {
        try {
            Node contextNode = (Node)node;
            DocumentInfo contextItem = this.config.buildDocument((Source)new DOMSource(contextNode));
            DynamicQueryContext dc = new DynamicQueryContext(this.config);
            dc.setContextItem((Item)contextItem);
            dc.setParameter(this.contextVar, (Object)contextItem);
            if (variableBindings != null) {
                for (Map.Entry entry : variableBindings.entrySet()) {
                    String key = (String)entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof XmlTokenSource) {
                        Node paramObject = ((XmlTokenSource)value).getDomNode();
                        dc.setParameter(key, (Object)paramObject);
                        continue;
                    }
                    if (!(value instanceof String)) continue;
                    dc.setParameter(key, value);
                }
            }
            List saxonNodes = this.xquery.evaluate(dc);
            ListIterator<NodeOverNodeInfo> it = saxonNodes.listIterator();
            while (it.hasNext()) {
                Object o = it.next();
                if (!(o instanceof NodeInfo)) continue;
                NodeOverNodeInfo n = NodeOverNodeInfo.wrap((NodeInfo)((NodeInfo)o));
                it.set(n);
            }
            return saxonNodes;
        } catch (TransformerException e) {
            throw new RuntimeException("Error binding " + this.contextVar, e);
        }
    }
}

