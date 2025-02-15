/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.rule;

import java.util.HashMap;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.rule.Action;
import org.dom4j.rule.Rule;
import org.dom4j.rule.RuleSet;

public class Mode {
    private RuleSet[] ruleSets = new RuleSet[14];
    private Map<String, RuleSet> elementNameRuleSets;
    private Map<String, RuleSet> attributeNameRuleSets;

    public void fireRule(Node node) throws Exception {
        Action action;
        Rule rule;
        if (node != null && (rule = this.getMatchingRule(node)) != null && (action = rule.getAction()) != null) {
            action.run(node);
        }
    }

    public void applyTemplates(Element element) throws Exception {
        int i;
        int size = element.attributeCount();
        for (i = 0; i < size; ++i) {
            Attribute attribute = element.attribute(i);
            this.fireRule(attribute);
        }
        size = element.nodeCount();
        for (i = 0; i < size; ++i) {
            Node node = element.node(i);
            this.fireRule(node);
        }
    }

    public void applyTemplates(Document document) throws Exception {
        int size = document.nodeCount();
        for (int i = 0; i < size; ++i) {
            Node node = document.node(i);
            this.fireRule(node);
        }
    }

    public void addRule(Rule rule) {
        short matchType = rule.getMatchType();
        String name = rule.getMatchesNodeName();
        if (name != null) {
            if (matchType == 1) {
                this.elementNameRuleSets = this.addToNameMap(this.elementNameRuleSets, name, rule);
            } else if (matchType == 2) {
                this.attributeNameRuleSets = this.addToNameMap(this.attributeNameRuleSets, name, rule);
            }
        }
        if (matchType >= 14) {
            matchType = 0;
        }
        if (matchType == 0) {
            int size = this.ruleSets.length;
            for (int i = 1; i < size; ++i) {
                RuleSet ruleSet = this.ruleSets[i];
                if (ruleSet == null) continue;
                ruleSet.addRule(rule);
            }
        }
        this.getRuleSet(matchType).addRule(rule);
    }

    public void removeRule(Rule rule) {
        short matchType = rule.getMatchType();
        String name = rule.getMatchesNodeName();
        if (name != null) {
            if (matchType == 1) {
                this.removeFromNameMap(this.elementNameRuleSets, name, rule);
            } else if (matchType == 2) {
                this.removeFromNameMap(this.attributeNameRuleSets, name, rule);
            }
        }
        if (matchType >= 14) {
            matchType = 0;
        }
        this.getRuleSet(matchType).removeRule(rule);
        if (matchType != 0) {
            this.getRuleSet(0).removeRule(rule);
        }
    }

    public Rule getMatchingRule(Node node) {
        Rule answer;
        String name;
        RuleSet ruleSet;
        short matchType = node.getNodeType();
        if (matchType == 1) {
            Rule answer2;
            if (this.elementNameRuleSets != null && (ruleSet = this.elementNameRuleSets.get(name = node.getName())) != null && (answer2 = ruleSet.getMatchingRule(node)) != null) {
                return answer2;
            }
        } else if (matchType == 2 && this.attributeNameRuleSets != null && (ruleSet = this.attributeNameRuleSets.get(name = node.getName())) != null && (answer = ruleSet.getMatchingRule(node)) != null) {
            return answer;
        }
        if (matchType < 0 || matchType >= this.ruleSets.length) {
            matchType = 0;
        }
        Rule answer3 = null;
        ruleSet = this.ruleSets[matchType];
        if (ruleSet != null) {
            answer3 = ruleSet.getMatchingRule(node);
        }
        if (answer3 == null && matchType != 0 && (ruleSet = this.ruleSets[0]) != null) {
            answer3 = ruleSet.getMatchingRule(node);
        }
        return answer3;
    }

    protected RuleSet getRuleSet(int matchType) {
        RuleSet ruleSet = this.ruleSets[matchType];
        if (ruleSet == null) {
            RuleSet allRules;
            this.ruleSets[matchType] = ruleSet = new RuleSet();
            if (matchType != 0 && (allRules = this.ruleSets[0]) != null) {
                ruleSet.addAll(allRules);
            }
        }
        return ruleSet;
    }

    protected Map<String, RuleSet> addToNameMap(Map<String, RuleSet> map, String name, Rule rule) {
        RuleSet ruleSet;
        if (map == null) {
            map = new HashMap<String, RuleSet>();
        }
        if ((ruleSet = map.get(name)) == null) {
            ruleSet = new RuleSet();
            map.put(name, ruleSet);
        }
        ruleSet.addRule(rule);
        return map;
    }

    protected void removeFromNameMap(Map<String, RuleSet> map, String name, Rule rule) {
        RuleSet ruleSet;
        if (map != null && (ruleSet = map.get(name)) != null) {
            ruleSet.removeRule(rule);
        }
    }
}

