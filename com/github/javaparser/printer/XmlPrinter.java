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

public class XmlPrinter {
    private final boolean outputNodeType;

    public XmlPrinter(boolean outputNodeType) {
        this.outputNodeType = outputNodeType;
    }

    public String output(Node node) {
        StringBuilder output = new StringBuilder();
        this.output(node, "root", 0, output);
        return output.toString();
    }

    public void output(Node node, String name, int level, StringBuilder builder) {
        Utils.assertNotNull(node);
        NodeMetaModel metaModel = node.getMetaModel();
        List<PropertyMetaModel> allPropertyMetaModels = metaModel.getAllPropertyMetaModels();
        List attributes = allPropertyMetaModels.stream().filter(PropertyMetaModel::isAttribute).filter(PropertyMetaModel::isSingular).collect(Collectors.toList());
        List subNodes = allPropertyMetaModels.stream().filter(PropertyMetaModel::isNode).filter(PropertyMetaModel::isSingular).collect(Collectors.toList());
        List subLists = allPropertyMetaModels.stream().filter(PropertyMetaModel::isNodeList).collect(Collectors.toList());
        builder.append("<").append(name);
        if (this.outputNodeType) {
            builder.append(XmlPrinter.attribute("type", metaModel.getTypeName()));
        }
        for (PropertyMetaModel attributeMetaModel : attributes) {
            builder.append(XmlPrinter.attribute(attributeMetaModel.getName(), attributeMetaModel.getValue(node).toString()));
        }
        builder.append(">");
        for (PropertyMetaModel subNodeMetaModel : subNodes) {
            Node value = (Node)subNodeMetaModel.getValue(node);
            if (value == null) continue;
            this.output(value, subNodeMetaModel.getName(), level + 1, builder);
        }
        for (PropertyMetaModel subListMetaModel : subLists) {
            NodeList subList = (NodeList)subListMetaModel.getValue(node);
            if (subList == null || subList.isEmpty()) continue;
            String listName = subListMetaModel.getName();
            builder.append("<").append(listName).append(">");
            String singular = listName.substring(0, listName.length() - 1);
            for (Node subListNode : subList) {
                this.output(subListNode, singular, level + 1, builder);
            }
            builder.append(XmlPrinter.close(listName));
        }
        builder.append(XmlPrinter.close(name));
    }

    private static String close(String name) {
        return "</" + name + ">";
    }

    private static String attribute(String name, String value) {
        return " " + name + "='" + value + "'";
    }

    public static void print(Node node) {
        System.out.println(new XmlPrinter(true).output(node));
    }
}

