/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

import com.graphbuilder.math.FuncMap;
import com.graphbuilder.math.FuncNode;
import com.graphbuilder.math.OpNode;
import com.graphbuilder.math.TermNode;
import com.graphbuilder.math.ValNode;
import com.graphbuilder.math.VarMap;
import com.graphbuilder.math.VarNode;
import com.graphbuilder.struc.Bag;

public abstract class Expression {
    protected Expression parent = null;

    public abstract double eval(VarMap var1, FuncMap var2);

    public boolean isDescendent(Expression x) {
        Expression y = this;
        while (y != null) {
            if (y == x) {
                return true;
            }
            y = y.parent;
        }
        return false;
    }

    public Expression getParent() {
        return this.parent;
    }

    protected void checkBeforeAccept(Expression x) {
        if (x == null) {
            throw new IllegalArgumentException("expression cannot be null");
        }
        if (x.parent != null) {
            throw new IllegalArgumentException("expression must be removed parent");
        }
        if (this.isDescendent(x)) {
            throw new IllegalArgumentException("cyclic reference");
        }
    }

    public String[] getVariableNames() {
        return this.getTermNames(true);
    }

    public String[] getFunctionNames() {
        return this.getTermNames(false);
    }

    private String[] getTermNames(boolean varNames) {
        Bag b = new Bag();
        Expression.getTermNames(this, b, varNames);
        String[] arr = new String[b.size()];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = (String)b.get(i);
        }
        return arr;
    }

    private static void getTermNames(Expression x, Bag b, boolean varNames) {
        block4: {
            block5: {
                block3: {
                    if (!(x instanceof OpNode)) break block3;
                    OpNode o = (OpNode)x;
                    Expression.getTermNames(o.leftChild, b, varNames);
                    Expression.getTermNames(o.rightChild, b, varNames);
                    break block4;
                }
                if (!(x instanceof VarNode)) break block5;
                if (!varNames) break block4;
                VarNode v = (VarNode)x;
                if (b.contains(v.name)) break block4;
                b.add(v.name);
                break block4;
            }
            if (x instanceof FuncNode) {
                FuncNode f = (FuncNode)x;
                if (!varNames && !b.contains(f.name)) {
                    b.add(f.name);
                }
                for (int i = 0; i < f.numChildren(); ++i) {
                    Expression.getTermNames(f.child(i), b, varNames);
                }
            }
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        Expression.toString(this, sb);
        return sb.toString();
    }

    private static void toString(Expression x, StringBuffer sb) {
        if (x instanceof OpNode) {
            OpNode o = (OpNode)x;
            sb.append("(");
            Expression.toString(o.leftChild, sb);
            sb.append(o.getSymbol());
            Expression.toString(o.rightChild, sb);
            sb.append(")");
        } else if (x instanceof TermNode) {
            TermNode t = (TermNode)x;
            if (t.getNegate()) {
                sb.append("(");
                sb.append("-");
            }
            sb.append(t.getName());
            if (t instanceof FuncNode) {
                FuncNode f = (FuncNode)t;
                sb.append("(");
                if (f.numChildren() > 0) {
                    Expression.toString(f.child(0), sb);
                }
                for (int i = 1; i < f.numChildren(); ++i) {
                    sb.append(", ");
                    Expression.toString(f.child(i), sb);
                }
                sb.append(")");
            }
            if (t.getNegate()) {
                sb.append(")");
            }
        } else if (x instanceof ValNode) {
            sb.append(((ValNode)x).val);
        }
    }
}

