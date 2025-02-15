/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.rule;

import java.util.ArrayList;
import java.util.Collections;
import org.dom4j.Node;
import org.dom4j.rule.Rule;

public class RuleSet {
    private ArrayList<Rule> rules = new ArrayList();
    private Rule[] ruleArray;

    public String toString() {
        return super.toString() + " [RuleSet: " + this.rules + " ]";
    }

    public Rule getMatchingRule(Node node) {
        Rule[] matches = this.getRuleArray();
        for (int i = matches.length - 1; i >= 0; --i) {
            Rule rule = matches[i];
            if (!rule.matches(node)) continue;
            return rule;
        }
        return null;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
        this.ruleArray = null;
    }

    public void removeRule(Rule rule) {
        this.rules.remove(rule);
        this.ruleArray = null;
    }

    public void addAll(RuleSet that) {
        this.rules.addAll(that.rules);
        this.ruleArray = null;
    }

    protected Rule[] getRuleArray() {
        if (this.ruleArray == null) {
            Collections.sort(this.rules);
            int size = this.rules.size();
            this.ruleArray = new Rule[size];
            this.rules.toArray(this.ruleArray);
        }
        return this.ruleArray;
    }
}

