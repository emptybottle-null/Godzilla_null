/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.swing;

import java.io.Serializable;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.XPath;

public class XMLTableColumnDefinition
implements Serializable {
    public static final int OBJECT_TYPE = 0;
    public static final int STRING_TYPE = 1;
    public static final int NUMBER_TYPE = 2;
    public static final int NODE_TYPE = 3;
    private int type;
    private String name;
    private XPath xpath;
    private XPath columnNameXPath;

    public XMLTableColumnDefinition() {
    }

    public XMLTableColumnDefinition(String name, String expression, int type) {
        this.name = name;
        this.type = type;
        this.xpath = this.createXPath(expression);
    }

    public XMLTableColumnDefinition(String name, XPath xpath, int type) {
        this.name = name;
        this.xpath = xpath;
        this.type = type;
    }

    public XMLTableColumnDefinition(XPath columnXPath, XPath xpath, int type) {
        this.xpath = xpath;
        this.columnNameXPath = columnXPath;
        this.type = type;
    }

    public static int parseType(String typeName) {
        if (typeName != null && typeName.length() > 0) {
            if (typeName.equals("string")) {
                return 1;
            }
            if (typeName.equals("number")) {
                return 2;
            }
            if (typeName.equals("node")) {
                return 3;
            }
        }
        return 0;
    }

    public Class<?> getColumnClass() {
        switch (this.type) {
            case 1: {
                return String.class;
            }
            case 2: {
                return Number.class;
            }
            case 3: {
                return Node.class;
            }
        }
        return Object.class;
    }

    public Object getValue(Object row) {
        switch (this.type) {
            case 1: {
                return this.xpath.valueOf(row);
            }
            case 2: {
                return this.xpath.numberValueOf(row);
            }
            case 3: {
                return this.xpath.selectSingleNode(row);
            }
        }
        return this.xpath.evaluate(row);
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public XPath getXPath() {
        return this.xpath;
    }

    public void setXPath(XPath xPath) {
        this.xpath = xPath;
    }

    public XPath getColumnNameXPath() {
        return this.columnNameXPath;
    }

    public void setColumnNameXPath(XPath columnNameXPath) {
        this.columnNameXPath = columnNameXPath;
    }

    protected XPath createXPath(String expression) {
        return DocumentHelper.createXPath(expression);
    }

    protected void handleException(Exception e) {
        System.out.println("Caught: " + e);
    }
}

