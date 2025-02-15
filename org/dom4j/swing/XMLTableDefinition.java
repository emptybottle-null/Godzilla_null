/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jaxen.VariableContext
 */
package org.dom4j.swing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.swing.XMLTableColumnDefinition;
import org.jaxen.VariableContext;

public class XMLTableDefinition
implements Serializable,
VariableContext {
    private XPath rowXPath;
    private List<XMLTableColumnDefinition> columns = new ArrayList<XMLTableColumnDefinition>();
    private XMLTableColumnDefinition[] columnArray;
    private Map<String, XMLTableColumnDefinition> columnNameIndex;
    private VariableContext variableContext;
    private Object rowValue;

    public static XMLTableDefinition load(Document definition) {
        return XMLTableDefinition.load(definition.getRootElement());
    }

    public static XMLTableDefinition load(Element definition) {
        XMLTableDefinition answer = new XMLTableDefinition();
        answer.setRowExpression(definition.attributeValue("select"));
        Iterator<Element> iter = definition.elementIterator("column");
        while (iter.hasNext()) {
            Element element = iter.next();
            String expression = element.attributeValue("select");
            String name = element.getText();
            String typeName = element.attributeValue("type", "string");
            String columnXPath = element.attributeValue("columnNameXPath");
            int type = XMLTableColumnDefinition.parseType(typeName);
            if (columnXPath != null) {
                answer.addColumnWithXPathName(columnXPath, expression, type);
                continue;
            }
            answer.addColumn(name, expression, type);
        }
        return answer;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return this.getColumn(columnIndex).getColumnClass();
    }

    public int getColumnCount() {
        return this.columns.size();
    }

    public String getColumnName(int columnIndex) {
        return this.getColumn(columnIndex).getName();
    }

    public XPath getColumnXPath(int columnIndex) {
        return this.getColumn(columnIndex).getXPath();
    }

    public XPath getColumnNameXPath(int columnIndex) {
        return this.getColumn(columnIndex).getColumnNameXPath();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized Object getValueAt(Object row, int columnIndex) {
        XMLTableColumnDefinition column = this.getColumn(columnIndex);
        Object answer = null;
        XMLTableDefinition xMLTableDefinition = this;
        synchronized (xMLTableDefinition) {
            this.rowValue = row;
            answer = column.getValue(row);
            this.rowValue = null;
        }
        return answer;
    }

    public void addColumn(String name, String expression) {
        this.addColumn(name, expression, 0);
    }

    public void addColumn(String name, String expression, int type) {
        XPath xpath = this.createColumnXPath(expression);
        this.addColumn(new XMLTableColumnDefinition(name, xpath, type));
    }

    public void addColumnWithXPathName(String columnNameXPathExpression, String expression, int type) {
        XPath columnNameXPath = this.createColumnXPath(columnNameXPathExpression);
        XPath xpath = this.createColumnXPath(expression);
        this.addColumn(new XMLTableColumnDefinition(columnNameXPath, xpath, type));
    }

    public void addStringColumn(String name, String expression) {
        this.addColumn(name, expression, 1);
    }

    public void addNumberColumn(String name, String expression) {
        this.addColumn(name, expression, 2);
    }

    public void addColumn(XMLTableColumnDefinition column) {
        this.clearCaches();
        this.columns.add(column);
    }

    public void removeColumn(XMLTableColumnDefinition column) {
        this.clearCaches();
        this.columns.remove(column);
    }

    public void clear() {
        this.clearCaches();
        this.columns.clear();
    }

    public XMLTableColumnDefinition getColumn(int index) {
        if (this.columnArray == null) {
            this.columnArray = new XMLTableColumnDefinition[this.columns.size()];
            this.columns.toArray(this.columnArray);
        }
        return this.columnArray[index];
    }

    public XMLTableColumnDefinition getColumn(String columnName) {
        if (this.columnNameIndex == null) {
            this.columnNameIndex = new HashMap<String, XMLTableColumnDefinition>();
            for (XMLTableColumnDefinition column : this.columns) {
                this.columnNameIndex.put(column.getName(), column);
            }
        }
        return this.columnNameIndex.get(columnName);
    }

    public XPath getRowXPath() {
        return this.rowXPath;
    }

    public void setRowXPath(XPath rowXPath) {
        this.rowXPath = rowXPath;
    }

    public void setRowExpression(String xpath) {
        this.setRowXPath(this.createXPath(xpath));
    }

    public Object getVariableValue(String namespaceURI, String prefix, String localName) {
        XMLTableColumnDefinition column = this.getColumn(localName);
        if (column != null) {
            return column.getValue(this.rowValue);
        }
        return null;
    }

    protected XPath createXPath(String expression) {
        return DocumentHelper.createXPath(expression);
    }

    protected XPath createColumnXPath(String expression) {
        XPath xpath = this.createXPath(expression);
        xpath.setVariableContext(this);
        return xpath;
    }

    protected void clearCaches() {
        this.columnArray = null;
        this.columnNameIndex = null;
    }

    protected void handleException(Exception e) {
        System.out.println("Caught: " + e);
    }
}

