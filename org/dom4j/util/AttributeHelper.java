/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.util;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.QName;

public class AttributeHelper {
    protected AttributeHelper() {
    }

    public static boolean booleanValue(Element element, String attributeName) {
        return AttributeHelper.booleanValue(element.attribute(attributeName));
    }

    public static boolean booleanValue(Element element, QName attributeQName) {
        return AttributeHelper.booleanValue(element.attribute(attributeQName));
    }

    protected static boolean booleanValue(Attribute attribute) {
        if (attribute == null) {
            return false;
        }
        Object value = attribute.getData();
        if (value == null) {
            return false;
        }
        if (value instanceof Boolean) {
            Boolean b = (Boolean)value;
            return b;
        }
        return "true".equalsIgnoreCase(value.toString());
    }
}

