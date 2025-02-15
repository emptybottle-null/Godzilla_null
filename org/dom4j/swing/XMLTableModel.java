/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.swing;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.swing.XMLTableDefinition;

public class XMLTableModel
extends AbstractTableModel {
    private XMLTableDefinition definition;
    private Object source;
    private List<Node> rows;

    public XMLTableModel(Element tableDefinition, Object source) {
        this(XMLTableDefinition.load(tableDefinition), source);
    }

    public XMLTableModel(Document tableDefinition, Object source) {
        this(XMLTableDefinition.load(tableDefinition), source);
    }

    public XMLTableModel(XMLTableDefinition definition, Object source) {
        this.definition = definition;
        this.source = source;
    }

    public Object getRowValue(int rowIndex) {
        return this.getRows().get(rowIndex);
    }

    public List<Node> getRows() {
        if (this.rows == null) {
            this.rows = this.definition.getRowXPath().selectNodes(this.source);
        }
        return this.rows;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.definition.getColumnClass(columnIndex);
    }

    @Override
    public int getColumnCount() {
        return this.definition.getColumnCount();
    }

    @Override
    public String getColumnName(int columnIndex) {
        XPath xpath = this.definition.getColumnNameXPath(columnIndex);
        if (xpath != null) {
            System.out.println("Evaluating column xpath: " + xpath + " value: " + xpath.valueOf(this.source));
            return xpath.valueOf(this.source);
        }
        return this.definition.getColumnName(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Object row = this.getRowValue(rowIndex);
            return this.definition.getValueAt(row, columnIndex);
        } catch (Exception e) {
            this.handleException(e);
            return null;
        }
    }

    @Override
    public int getRowCount() {
        return this.getRows().size();
    }

    public XMLTableDefinition getDefinition() {
        return this.definition;
    }

    public void setDefinition(XMLTableDefinition definition) {
        this.definition = definition;
    }

    public Object getSource() {
        return this.source;
    }

    public void setSource(Object source) {
        this.source = source;
        this.rows = null;
    }

    protected void handleException(Exception e) {
        System.out.println("Caught: " + e);
    }
}

