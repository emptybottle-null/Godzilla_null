/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.IntConst;
import javassist.compiler.ast.Visitor;

public class DoubleConst
extends ASTree {
    private static final long serialVersionUID = 1L;
    protected double value;
    protected int type;

    public DoubleConst(double v, int tokenId) {
        this.value = v;
        this.type = tokenId;
    }

    public double get() {
        return this.value;
    }

    public void set(double v) {
        this.value = v;
    }

    public int getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atDoubleConst(this);
    }

    public ASTree compute(int op, ASTree right) {
        if (right instanceof IntConst) {
            return this.compute0(op, (IntConst)right);
        }
        if (right instanceof DoubleConst) {
            return this.compute0(op, (DoubleConst)right);
        }
        return null;
    }

    private DoubleConst compute0(int op, DoubleConst right) {
        int newType = this.type == 405 || right.type == 405 ? 405 : 404;
        return DoubleConst.compute(op, this.value, right.value, newType);
    }

    private DoubleConst compute0(int op, IntConst right) {
        return DoubleConst.compute(op, this.value, right.value, this.type);
    }

    private static DoubleConst compute(int op, double value1, double value2, int newType) {
        double newValue;
        switch (op) {
            case 43: {
                newValue = value1 + value2;
                break;
            }
            case 45: {
                newValue = value1 - value2;
                break;
            }
            case 42: {
                newValue = value1 * value2;
                break;
            }
            case 47: {
                newValue = value1 / value2;
                break;
            }
            case 37: {
                newValue = value1 % value2;
                break;
            }
            default: {
                return null;
            }
        }
        return new DoubleConst(newValue, newType);
    }
}

