/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.xml.DomContentHandler;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ContentHandler;

public abstract class DomUtils {
    public static List<Element> getChildElementsByTagName(Element ele, String ... childEleNames) {
        Assert.notNull((Object)ele, "Element must not be null");
        Assert.notNull((Object)childEleNames, "Element names collection must not be null");
        List<String> childEleNameList = Arrays.asList(childEleNames);
        NodeList nl = ele.getChildNodes();
        ArrayList<Element> childEles = new ArrayList<Element>();
        for (int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            if (!(node instanceof Element) || !DomUtils.nodeNameMatch(node, childEleNameList)) continue;
            childEles.add((Element)node);
        }
        return childEles;
    }

    public static List<Element> getChildElementsByTagName(Element ele, String childEleName) {
        return DomUtils.getChildElementsByTagName(ele, new String[]{childEleName});
    }

    @Nullable
    public static Element getChildElementByTagName(Element ele, String childEleName) {
        Assert.notNull((Object)ele, "Element must not be null");
        Assert.notNull((Object)childEleName, "Element name must not be null");
        NodeList nl = ele.getChildNodes();
        for (int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            if (!(node instanceof Element) || !DomUtils.nodeNameMatch(node, childEleName)) continue;
            return (Element)node;
        }
        return null;
    }

    @Nullable
    public static String getChildElementValueByTagName(Element ele, String childEleName) {
        Element child = DomUtils.getChildElementByTagName(ele, childEleName);
        return child != null ? DomUtils.getTextValue(child) : null;
    }

    public static List<Element> getChildElements(Element ele) {
        Assert.notNull((Object)ele, "Element must not be null");
        NodeList nl = ele.getChildNodes();
        ArrayList<Element> childEles = new ArrayList<Element>();
        for (int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            if (!(node instanceof Element)) continue;
            childEles.add((Element)node);
        }
        return childEles;
    }

    public static String getTextValue(Element valueEle) {
        Assert.notNull((Object)valueEle, "Element must not be null");
        StringBuilder sb = new StringBuilder();
        NodeList nl = valueEle.getChildNodes();
        for (int i = 0; i < nl.getLength(); ++i) {
            Node item = nl.item(i);
            if ((!(item instanceof CharacterData) || item instanceof Comment) && !(item instanceof EntityReference)) continue;
            sb.append(item.getNodeValue());
        }
        return sb.toString();
    }

    public static boolean nodeNameEquals(Node node, String desiredName) {
        Assert.notNull((Object)node, "Node must not be null");
        Assert.notNull((Object)desiredName, "Desired name must not be null");
        return DomUtils.nodeNameMatch(node, desiredName);
    }

    public static ContentHandler createContentHandler(Node node) {
        return new DomContentHandler(node);
    }

    private static boolean nodeNameMatch(Node node, String desiredName) {
        return desiredName.equals(node.getNodeName()) || desiredName.equals(node.getLocalName());
    }

    private static boolean nodeNameMatch(Node node, Collection<?> desiredNames) {
        return desiredNames.contains(node.getNodeName()) || desiredNames.contains(node.getLocalName());
    }
}

