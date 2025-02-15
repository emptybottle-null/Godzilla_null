/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger.treetable;

import javax.swing.tree.TreeModel;

public interface TreeTableModel
extends TreeModel {
    public int getColumnCount();

    public String getColumnName(int var1);

    public Class<?> getColumnClass(int var1);

    public Object getValueAt(Object var1, int var2);

    public boolean isCellEditable(Object var1, int var2);

    public void setValueAt(Object var1, Object var2, int var3);
}

