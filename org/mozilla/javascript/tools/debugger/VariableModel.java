/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.util.Arrays;
import java.util.Comparator;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import org.mozilla.javascript.tools.debugger.Dim;
import org.mozilla.javascript.tools.debugger.treetable.TreeTableModel;

class VariableModel
implements TreeTableModel {
    private static final String[] cNames = new String[]{" Name", " Value"};
    private static final Class<?>[] cTypes = new Class[]{TreeTableModel.class, String.class};
    private static final VariableNode[] CHILDLESS = new VariableNode[0];
    private Dim debugger;
    private VariableNode root;

    public VariableModel() {
    }

    public VariableModel(Dim debugger, Object scope) {
        this.debugger = debugger;
        this.root = new VariableNode(scope, "this");
    }

    @Override
    public Object getRoot() {
        if (this.debugger == null) {
            return null;
        }
        return this.root;
    }

    @Override
    public int getChildCount(Object nodeObj) {
        if (this.debugger == null) {
            return 0;
        }
        VariableNode node = (VariableNode)nodeObj;
        return this.children(node).length;
    }

    @Override
    public Object getChild(Object nodeObj, int i) {
        if (this.debugger == null) {
            return null;
        }
        VariableNode node = (VariableNode)nodeObj;
        return this.children(node)[i];
    }

    @Override
    public boolean isLeaf(Object nodeObj) {
        if (this.debugger == null) {
            return true;
        }
        VariableNode node = (VariableNode)nodeObj;
        return this.children(node).length == 0;
    }

    @Override
    public int getIndexOfChild(Object parentObj, Object childObj) {
        if (this.debugger == null) {
            return -1;
        }
        VariableNode parent = (VariableNode)parentObj;
        VariableNode child = (VariableNode)childObj;
        VariableNode[] children = this.children(parent);
        for (int i = 0; i != children.length; ++i) {
            if (children[i] != child) continue;
            return i;
        }
        return -1;
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        return column == 0;
    }

    @Override
    public void setValueAt(Object value, Object node, int column) {
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getColumnCount() {
        return cNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return cNames[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return cTypes[column];
    }

    @Override
    public Object getValueAt(Object nodeObj, int column) {
        if (this.debugger == null) {
            return null;
        }
        VariableNode node = (VariableNode)nodeObj;
        switch (column) {
            case 0: {
                return node.toString();
            }
            case 1: {
                String result;
                try {
                    result = this.debugger.objectToString(this.getValue(node));
                } catch (RuntimeException exc) {
                    result = exc.getMessage();
                }
                StringBuilder buf = new StringBuilder();
                int len = result.length();
                for (int i = 0; i < len; ++i) {
                    char ch = result.charAt(i);
                    if (Character.isISOControl(ch)) {
                        ch = ' ';
                    }
                    buf.append(ch);
                }
                return buf.toString();
            }
        }
        return null;
    }

    private VariableNode[] children(VariableNode node) {
        VariableNode[] children;
        if (node.children != null) {
            return node.children;
        }
        Object value = this.getValue(node);
        Object[] ids = this.debugger.getObjectIds(value);
        if (ids == null || ids.length == 0) {
            children = CHILDLESS;
        } else {
            Arrays.sort(ids, new Comparator<Object>(){

                @Override
                public int compare(Object l, Object r) {
                    if (l instanceof String) {
                        if (r instanceof Integer) {
                            return -1;
                        }
                        return ((String)l).compareToIgnoreCase((String)r);
                    }
                    if (r instanceof String) {
                        return 1;
                    }
                    int lint = (Integer)l;
                    int rint = (Integer)r;
                    return lint - rint;
                }
            });
            children = new VariableNode[ids.length];
            for (int i = 0; i != ids.length; ++i) {
                children[i] = new VariableNode(value, ids[i]);
            }
        }
        VariableNode.access$002(node, children);
        return children;
    }

    public Object getValue(VariableNode node) {
        try {
            return this.debugger.getObjectProperty(node.object, node.id);
        } catch (Exception exc) {
            return "undefined";
        }
    }

    private static class VariableNode {
        private Object object;
        private Object id;
        private VariableNode[] children;

        public VariableNode(Object object, Object id) {
            this.object = object;
            this.id = id;
        }

        public String toString() {
            return this.id instanceof String ? (String)this.id : "[" + (Integer)this.id + "]";
        }

        static /* synthetic */ VariableNode[] access$002(VariableNode x0, VariableNode[] x1) {
            x0.children = x1;
            return x1;
        }
    }
}

