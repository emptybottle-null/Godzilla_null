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

public class DotPrinter {
    private int nodeCount;
    private final boolean outputNodeType;

    public DotPrinter(boolean outputNodeType) {
        this.outputNodeType = outputNodeType;
    }

    public String output(Node node) {
        this.nodeCount = 0;
        StringBuilder output = new StringBuilder();
        output.append("digraph {");
        this.output(node, null, "root", output);
        output.append(Utils.SYSTEM_EOL + "}");
        return output.toString();
    }

    public void output(Node node, String parentNodeName, String name, StringBuilder builder) {
        Utils.assertNotNull(node);
        NodeMetaModel metaModel = node.getMetaModel();
        List<PropertyMetaModel> allPropertyMetaModels = metaModel.getAllPropertyMetaModels();
        List attributes = allPropertyMetaModels.stream().filter(PropertyMetaModel::isAttribute).filter(PropertyMetaModel::isSingular).collect(Collectors.toList());
        List subNodes = allPropertyMetaModels.stream().filter(PropertyMetaModel::isNode).filter(PropertyMetaModel::isSingular).collect(Collectors.toList());
        List subLists = allPropertyMetaModels.stream().filter(PropertyMetaModel::isNodeList).collect(Collectors.toList());
        String ndName = this.nextNodeName();
        if (this.outputNodeType) {
            builder.append(Utils.SYSTEM_EOL + ndName + " [label=\"" + DotPrinter.escape(name) + " (" + metaModel.getTypeName() + ")\"];");
        } else {
            builder.append(Utils.SYSTEM_EOL + ndName + " [label=\"" + DotPrinter.escape(name) + "\"];");
        }
        if (parentNodeName != null) {
            builder.append(Utils.SYSTEM_EOL + parentNodeName + " -> " + ndName + ";");
        }
        for (PropertyMetaModel a : attributes) {
            String attrName = this.nextNodeName();
            builder.append(Utils.SYSTEM_EOL + attrName + " [label=\"" + DotPrinter.escape(a.getName()) + "='" + DotPrinter.escape(a.getValue(node).toString()) + "'\"];");
            builder.append(Utils.SYSTEM_EOL + ndName + " -> " + attrName + ";");
        }
        for (PropertyMetaModel sn : subNodes) {
            Node nd = (Node)sn.getValue(node);
            if (nd == null) continue;
            this.output(nd, ndName, sn.getName(), builder);
        }
        for (PropertyMetaModel sl : subLists) {
            NodeList nl = (NodeList)sl.getValue(node);
            if (nl == null || !nl.isNonEmpty()) continue;
            String ndLstName = this.nextNodeName();
            builder.append(Utils.SYSTEM_EOL + ndLstName + " [label=\"" + DotPrinter.escape(sl.getName()) + "\"];");
            builder.append(Utils.SYSTEM_EOL + ndName + " -> " + ndLstName + ";");
            String slName = sl.getName().substring(0, sl.getName().length() - 1);
            for (Node nd : nl) {
                this.output(nd, ndLstName, slName, builder);
            }
        }
    }

    private String nextNodeName() {
        return "n" + this.nodeCount++;
    }

    private static String escape(String value) {
        return value.replace("\"", "\\\"");
    }
}

