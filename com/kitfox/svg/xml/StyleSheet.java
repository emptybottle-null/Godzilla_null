/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.xml;

import com.kitfox.svg.xml.StyleAttribute;
import com.kitfox.svg.xml.StyleSheetRule;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StyleSheet {
    HashMap<StyleSheetRule, String> ruleMap = new HashMap();

    public static StyleSheet parseSheet(String src) {
        Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "CSS parser not implemented yet");
        return null;
    }

    public void addStyleRule(StyleSheetRule rule, String value) {
        this.ruleMap.put(rule, value);
    }

    public boolean getStyle(StyleAttribute attrib, String tagName, String cssClass) {
        StyleSheetRule rule = new StyleSheetRule(attrib.getName(), tagName, cssClass);
        String value = this.ruleMap.get(rule);
        if (value != null) {
            attrib.setStringValue(value);
            return true;
        }
        rule = new StyleSheetRule(attrib.getName(), null, cssClass);
        value = this.ruleMap.get(rule);
        if (value != null) {
            attrib.setStringValue(value);
            return true;
        }
        rule = new StyleSheetRule(attrib.getName(), tagName, null);
        value = this.ruleMap.get(rule);
        if (value != null) {
            attrib.setStringValue(value);
            return true;
        }
        return false;
    }
}

