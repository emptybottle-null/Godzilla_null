/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

public class VarMap {
    private boolean caseSensitive = true;
    private String[] name = new String[2];
    private double[] value = new double[2];
    private int numVars = 0;

    public VarMap() {
        this(true);
    }

    public VarMap(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public double getValue(String varName) {
        for (int i = 0; i < this.numVars; ++i) {
            if ((!this.caseSensitive || !this.name[i].equals(varName)) && (this.caseSensitive || !this.name[i].equalsIgnoreCase(varName))) continue;
            return this.value[i];
        }
        throw new RuntimeException("variable value has not been set: " + varName);
    }

    public void setValue(String varName, double val) {
        if (varName == null) {
            throw new IllegalArgumentException("varName cannot be null");
        }
        for (int i = 0; i < this.numVars; ++i) {
            if ((!this.caseSensitive || !this.name[i].equals(varName)) && (this.caseSensitive || !this.name[i].equalsIgnoreCase(varName))) continue;
            this.value[i] = val;
            return;
        }
        if (this.numVars == this.name.length) {
            String[] tmp1 = new String[2 * this.numVars];
            double[] tmp2 = new double[tmp1.length];
            for (int i = 0; i < this.numVars; ++i) {
                tmp1[i] = this.name[i];
                tmp2[i] = this.value[i];
            }
            this.name = tmp1;
            this.value = tmp2;
        }
        this.name[this.numVars] = varName;
        this.value[this.numVars] = val;
        ++this.numVars;
    }

    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    public String[] getVariableNames() {
        String[] arr = new String[this.numVars];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = this.name[i];
        }
        return arr;
    }

    public double[] getValues() {
        double[] arr = new double[this.numVars];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = this.value[i];
        }
        return arr;
    }

    public void remove(String varName) {
        for (int i = 0; i < this.numVars; ++i) {
            if ((!this.caseSensitive || !this.name[i].equals(varName)) && (this.caseSensitive || !this.name[i].equalsIgnoreCase(varName))) continue;
            for (int j = i + 1; j < this.numVars; ++j) {
                this.name[j - 1] = this.name[j];
                this.value[j - 1] = this.value[j];
            }
            --this.numVars;
            this.name[this.numVars] = null;
            this.value[this.numVars] = 0.0;
            break;
        }
    }
}

