/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.DoubleConst;
import javassist.compiler.ast.Visitor;

public class IntConst
extends ASTree {
    private static final long serialVersionUID = 1L;
    protected long value;
    protected int type;

    public IntConst(long v, int tokenId) {
        this.value = v;
        this.type = tokenId;
    }

    public long get() {
        return this.value;
    }

    public void set(long v) {
        this.value = v;
    }

    public int getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return Long.toString(this.value);
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atIntConst(this);
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

    private IntConst compute0(int op, IntConst right) {
        long newValue;
        int type1 = this.type;
        int type2 = right.type;
        int newType = type1 == 403 || type2 == 403 ? 403 : (type1 == 401 && type2 == 401 ? 401 : 402);
        long value1 = this.value;
        long value2 = right.value;
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
            case 124: {
                newValue = value1 | value2;
                break;
            }
            case 94: {
                newValue = value1 ^ value2;
                break;
            }
            case 38: {
                newValue = value1 & value2;
                break;
            }
            case 364: {
                newValue = this.value << (int)value2;
                newType = type1;
                break;
            }
            case 366: {
                newValue = this.value >> (int)value2;
                newType = type1;
                break;
            }
            case 370: {
                newValue = this.value >>> (int)value2;
                newType = type1;
                break;
            }
            default: {
                return null;
            }
        }
        return new IntConst(newValue, newType);
    }

    private DoubleConst compute0(int op, DoubleConst right) {
        double newValue;
        double value1 = this.value;
        double value2 = right.value;
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
        return new DoubleConst(newValue, right.type);
    }
}

