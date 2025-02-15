/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.rule;

import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.rule.Action;
import org.dom4j.rule.Mode;
import org.dom4j.rule.Rule;
import org.dom4j.rule.RuleManager;

public class Stylesheet {
    private RuleManager ruleManager = new RuleManager();
    private String modeName;

    public void addRule(Rule rule) {
        this.ruleManager.addRule(rule);
    }

    public void removeRule(Rule rule) {
        this.ruleManager.removeRule(rule);
    }

    public void run(List<Node> list) throws Exception {
        this.run(list, this.modeName);
    }

    public void run(List<Node> list, String mode) throws Exception {
        for (Node node : list) {
            this.run(node, mode);
        }
    }

    public void run(Node node) throws Exception {
        this.run(node, this.modeName);
    }

    public void run(Node node, String mode) throws Exception {
        Mode mod = this.ruleManager.getMode(mode);
        mod.fireRule(node);
    }

    public void applyTemplates(Object input, XPath xpath) throws Exception {
        this.applyTemplates(input, xpath, this.modeName);
    }

    public void applyTemplates(Object input, XPath xpath, String mode) throws Exception {
        Mode mod = this.ruleManager.getMode(mode);
        List<Node> list = xpath.selectNodes(input);
        for (Node current : list) {
            mod.fireRule(current);
        }
    }

    public void applyTemplates(Node node) throws Exception {
        this.applyTemplates(node, this.modeName);
    }

    public void applyTemplates(Element element) throws Exception {
        this.applyTemplates(element, this.modeName);
    }

    public void applyTemplates(Document document) throws Exception {
        this.applyTemplates(document, this.modeName);
    }

    public void applyTemplates(List<Node> list) throws Exception {
        this.applyTemplates(list, this.modeName);
    }

    public void applyTemplates(Node node, String mode) throws Exception {
        if (node instanceof Element) {
            this.applyTemplates((Element)node, mode);
        } else if (node instanceof Document) {
            this.applyTemplates((Document)node, mode);
        }
    }

    public void applyTemplates(Element element, String mode) throws Exception {
        Mode mod = this.ruleManager.getMode(mode);
        int size = element.nodeCount();
        for (int i = 0; i < size; ++i) {
            Node node = element.node(i);
            mod.fireRule(node);
        }
    }

    public void applyTemplates(Document document, String mode) throws Exception {
        Mode mod = this.ruleManager.getMode(mode);
        int size = document.nodeCount();
        for (int i = 0; i < size; ++i) {
            Node node = document.node(i);
            mod.fireRule(node);
        }
    }

    public void applyTemplates(List<? extends Node> list, String mode) throws Exception {
        for (Node node : list) {
            if (node instanceof Element) {
                this.applyTemplates((Element)node, mode);
                continue;
            }
            if (!(node instanceof Document)) continue;
            this.applyTemplates((Document)node, mode);
        }
    }

    public void clear() {
        this.ruleManager.clear();
    }

    public String getModeName() {
        return this.modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public Action getValueOfAction() {
        return this.ruleManager.getValueOfAction();
    }

    public void setValueOfAction(Action valueOfAction) {
        this.ruleManager.setValueOfAction(valueOfAction);
    }
}

