/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

import com.graphbuilder.math.Expression;
import com.graphbuilder.math.FuncMap;
import com.graphbuilder.math.TermNode;
import com.graphbuilder.math.VarMap;
import com.graphbuilder.struc.Bag;

public class FuncNode
extends TermNode {
    private Bag bag = new Bag(1);
    private double[] of = new double[1];

    public FuncNode(String name, boolean negate) {
        super(name, negate);
    }

    public void add(Expression x) {
        this.insert(x, this.bag.size());
    }

    public void insert(Expression x, int i) {
        this.checkBeforeAccept(x);
        int oldCap = this.bag.getCapacity();
        this.bag.insert(x, i);
        int newCap = this.bag.getCapacity();
        if (oldCap != newCap) {
            this.of = new double[newCap];
        }
        x.parent = this;
    }

    public void remove(Expression x) {
        int size = this.bag.size();
        this.bag.remove(x);
        if (size != this.bag.size()) {
            x.parent = null;
        }
    }

    public int numChildren() {
        return this.bag.size();
    }

    public Expression child(int i) {
        return (Expression)this.bag.get(i);
    }

    public double eval(VarMap v, FuncMap f) {
        int numParam = this.bag.size();
        for (int i = 0; i < numParam; ++i) {
            this.of[i] = this.child(i).eval(v, f);
        }
        double result = f.getFunction(this.name, numParam).of(this.of, numParam);
        if (this.negate) {
            result = -result;
        }
        return result;
    }
}

