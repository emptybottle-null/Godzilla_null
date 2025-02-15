/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jaxen.Context
 *  org.jaxen.ContextSupport
 *  org.jaxen.JaxenException
 *  org.jaxen.NamespaceContext
 *  org.jaxen.SimpleNamespaceContext
 *  org.jaxen.SimpleVariableContext
 *  org.jaxen.VariableContext
 *  org.jaxen.XPathFunctionContext
 *  org.jaxen.dom4j.DocumentNavigator
 *  org.jaxen.pattern.Pattern
 *  org.jaxen.pattern.PatternParser
 *  org.jaxen.saxpath.SAXPathException
 */
package org.dom4j.xpath;

import java.util.Collections;
import org.dom4j.InvalidXPathException;
import org.dom4j.Node;
import org.dom4j.XPathException;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.NamespaceContext;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.SimpleVariableContext;
import org.jaxen.VariableContext;
import org.jaxen.XPathFunctionContext;
import org.jaxen.dom4j.DocumentNavigator;
import org.jaxen.pattern.Pattern;
import org.jaxen.pattern.PatternParser;
import org.jaxen.saxpath.SAXPathException;

public class XPathPattern
implements org.dom4j.rule.Pattern {
    private String text;
    private Pattern pattern;
    private Context context;

    public XPathPattern(Pattern pattern) {
        this.pattern = pattern;
        this.text = pattern.getText();
        this.context = new Context(this.getContextSupport());
    }

    public XPathPattern(String text) {
        this.text = text;
        this.context = new Context(this.getContextSupport());
        try {
            this.pattern = PatternParser.parse((String)text);
        } catch (SAXPathException e) {
            throw new InvalidXPathException(text, e.getMessage());
        } catch (RuntimeException e) {
            throw new InvalidXPathException(text);
        }
    }

    @Override
    public boolean matches(Node node) {
        try {
            this.context.setNodeSet(Collections.singletonList(node));
            return this.pattern.matches((Object)node, this.context);
        } catch (JaxenException e) {
            this.handleJaxenException(e);
            return false;
        }
    }

    public String getText() {
        return this.text;
    }

    @Override
    public double getPriority() {
        return this.pattern.getPriority();
    }

    @Override
    public org.dom4j.rule.Pattern[] getUnionPatterns() {
        Pattern[] patterns = this.pattern.getUnionPatterns();
        if (patterns != null) {
            int size = patterns.length;
            org.dom4j.rule.Pattern[] answer = new XPathPattern[size];
            for (int i = 0; i < size; ++i) {
                answer[i] = new XPathPattern(patterns[i]);
            }
            return answer;
        }
        return null;
    }

    @Override
    public short getMatchType() {
        return this.pattern.getMatchType();
    }

    @Override
    public String getMatchesNodeName() {
        return this.pattern.getMatchesNodeName();
    }

    public void setVariableContext(VariableContext variableContext) {
        this.context.getContextSupport().setVariableContext(variableContext);
    }

    public String toString() {
        return "[XPathPattern: text: " + this.text + " Pattern: " + this.pattern + "]";
    }

    protected ContextSupport getContextSupport() {
        return new ContextSupport((NamespaceContext)new SimpleNamespaceContext(), XPathFunctionContext.getInstance(), (VariableContext)new SimpleVariableContext(), DocumentNavigator.getInstance());
    }

    protected void handleJaxenException(JaxenException exception) throws XPathException {
        throw new XPathException(this.text, (Exception)((Object)exception));
    }
}

