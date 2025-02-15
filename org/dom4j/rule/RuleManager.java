/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.rule;

import java.util.HashMap;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.rule.Action;
import org.dom4j.rule.Mode;
import org.dom4j.rule.Pattern;
import org.dom4j.rule.Rule;
import org.dom4j.rule.pattern.NodeTypePattern;

public class RuleManager {
    private HashMap<String, Mode> modes = new HashMap();
    private int appearenceCount;
    private Action valueOfAction;

    public Mode getMode(String modeName) {
        Mode mode = this.modes.get(modeName);
        if (mode == null) {
            mode = this.createMode();
            this.modes.put(modeName, mode);
        }
        return mode;
    }

    public void addRule(Rule rule) {
        rule.setAppearenceCount(++this.appearenceCount);
        Mode mode = this.getMode(rule.getMode());
        Rule[] childRules = rule.getUnionRules();
        if (childRules != null) {
            for (Rule childRule : childRules) {
                mode.addRule(childRule);
            }
        } else {
            mode.addRule(rule);
        }
    }

    public void removeRule(Rule rule) {
        Mode mode = this.getMode(rule.getMode());
        Rule[] childRules = rule.getUnionRules();
        if (childRules != null) {
            for (Rule childRule : childRules) {
                mode.removeRule(childRule);
            }
        } else {
            mode.removeRule(rule);
        }
    }

    public Rule getMatchingRule(String modeName, Node node) {
        Mode mode = this.modes.get(modeName);
        if (mode != null) {
            return mode.getMatchingRule(node);
        }
        System.out.println("Warning: No Mode for mode: " + mode);
        return null;
    }

    public void clear() {
        this.modes.clear();
        this.appearenceCount = 0;
    }

    public Action getValueOfAction() {
        return this.valueOfAction;
    }

    public void setValueOfAction(Action valueOfAction) {
        this.valueOfAction = valueOfAction;
    }

    protected Mode createMode() {
        Mode mode = new Mode();
        this.addDefaultRules(mode);
        return mode;
    }

    protected void addDefaultRules(final Mode mode) {
        Action applyTemplates = new Action(){

            @Override
            public void run(Node node) throws Exception {
                if (node instanceof Element) {
                    mode.applyTemplates((Element)node);
                } else if (node instanceof Document) {
                    mode.applyTemplates((Document)node);
                }
            }
        };
        Action valueOf = this.getValueOfAction();
        this.addDefaultRule(mode, NodeTypePattern.ANY_DOCUMENT, applyTemplates);
        this.addDefaultRule(mode, NodeTypePattern.ANY_ELEMENT, applyTemplates);
        if (valueOf != null) {
            this.addDefaultRule(mode, NodeTypePattern.ANY_ATTRIBUTE, valueOf);
            this.addDefaultRule(mode, NodeTypePattern.ANY_TEXT, valueOf);
        }
    }

    protected void addDefaultRule(Mode mode, Pattern pattern, Action action) {
        Rule rule = this.createDefaultRule(pattern, action);
        mode.addRule(rule);
    }

    protected Rule createDefaultRule(Pattern pattern, Action action) {
        Rule rule = new Rule(pattern, action);
        rule.setImportPrecedence(-1);
        return rule;
    }
}

