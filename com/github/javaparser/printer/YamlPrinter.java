/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.List;
import java.util.stream.Collectors;

public class YamlPrinter {
    private static final int NUM_SPACES_FOR_INDENT = 4;
    private final boolean outputNodeType;

    public YamlPrinter(boolean outputNodeType) {
        this.outputNodeType = outputNodeType;
    }

    public String output(Node node) {
        StringBuilder output = new StringBuilder();
        output.append("---");
        this.output(node, "root", 0, output);
        output.append(System.lineSeparator() + "...");
        return output.toString();
    }

    public void output(Node node, String name, int level, StringBuilder builder) {
        Utils.assertNotNull(node);
        NodeMetaModel metaModel = node.getMetaModel();
        List<PropertyMetaModel> allPropertyMetaModels = metaModel.getAllPropertyMetaModels();
        List attributes = allPropertyMetaModels.stream().filter(PropertyMetaModel::isAttribute).filter(PropertyMetaModel::isSingular).collect(Collectors.toList());
        List subNodes = allPropertyMetaModels.stream().filter(PropertyMetaModel::isNode).filter(PropertyMetaModel::isSingular).collect(Collectors.toList());
        List subLists = allPropertyMetaModels.stream().filter(PropertyMetaModel::isNodeList).collect(Collectors.toList());
        if (this.outputNodeType) {
            builder.append(System.lineSeparator() + this.indent(level) + name + "(Type=" + metaModel.getTypeName() + "): ");
        } else {
            builder.append(System.lineSeparator() + this.indent(level) + name + ": ");
        }
        ++level;
        for (PropertyMetaModel a : attributes) {
            builder.append(System.lineSeparator() + this.indent(level) + a.getName() + ": " + this.escapeValue(a.getValue(node).toString()));
        }
        for (PropertyMetaModel sn : subNodes) {
            Node nd = (Node)sn.getValue(node);
            if (nd == null) continue;
            this.output(nd, sn.getName(), level, builder);
        }
        for (PropertyMetaModel sl : subLists) {
            NodeList nl = (NodeList)sl.getValue(node);
            if (nl == null || !nl.isNonEmpty()) continue;
            builder.append(System.lineSeparator() + this.indent(level) + sl.getName() + ": ");
            String slName = sl.getName();
            slName = slName.endsWith("s") ? slName.substring(0, sl.getName().length() - 1) : slName;
            for (Node nd : nl) {
                this.output(nd, "- " + slName, level + 1, builder);
            }
        }
    }

    private String indent(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; ++i) {
            for (int j = 0; j < 4; ++j) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private String escapeValue(String value) {
        return "\"" + value.replace("\\", "\\\\").replaceAll("\"", "\\\\\"").replace("\n", "\\n").replace("\r", "\\r").replace("\f", "\\f").replace("\b", "\\b").replace("\t", "\\t") + "\"";
    }

    public static void print(Node node) {
        System.out.println(new YamlPrinter(true).output(node));
    }
}

